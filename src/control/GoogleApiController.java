package control;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CalendarModel;
import model.EmployeeModel;
import model.TerminModel;

/**
 *
 * @author michi
 */
public class GoogleApiController extends Thread{
    
    //fuer den Verbindungsaufbau zur Google Calendar API
    private HttpTransport HTTP_TRANSPORT;
    private JsonFactory JSON_FACTORY;
    private com.google.api.services.calendar.Calendar client;
    private final CalendarModel calendar;
    
    public GoogleApiController(CalendarModel cm){
        super();
        calendar = cm;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            JSON_FACTORY = new JacksonFactory();
            GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId("777633749381-sesph1uuf3un0mtpipob67qsc67krctv@developer.gserviceaccount.com")
                    .setServiceAccountScopes(CalendarScopes.CALENDAR_READONLY)
                    .setServiceAccountPrivateKeyFromP12File(new File("key.p12"))
                    .build();
            client = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("TeamAwarenessSystem/0.1").build();
            System.out.println("GoogleApiController: connected");
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(GoogleApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    // initialisiert die mitarbeiterTermineList mit den Daten aus der API
    private void fillModel() throws IOException {
        // Range der Events festlegen, minDay = heute, maxDay = heute + 1
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        DateTime minDay = new DateTime(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, +1);
        DateTime maxDay = new DateTime(cal.getTime());
        //CalendarList liefert alle sichtbaren Kalender
        CalendarList calendarList = client.calendarList().list().execute();
        if (calendarList.getItems() != null)
            for (CalendarListEntry entry : calendarList.getItems()) {
                //Events liefert alle Termine in dem Kalender fuer die Range
                Events events = client.events().list(entry.getId()).setTimeMin(minDay).setTimeMax(maxDay).execute();
                Set<TerminModel> termine = new TreeSet<>();
                if(events.getItems() != null)
                    for (Event event : events.getItems()) {
                        //Termin 
                        if (event.getTransparency() == null) {
                            // korrigiere Start und End-Datum falls ganztaegiges Event
                            Date start = new Date(event.getStart().getDateTime()==null?event.getStart().getDate().getValue()-TimeZone.getDefault().getRawOffset()-TimeZone.getDefault().getDSTSavings():event.getStart().getDateTime().getValue());
                            Date end = new Date(event.getEnd().getDateTime()==null?event.getEnd().getDate().getValue()-TimeZone.getDefault().getRawOffset()-TimeZone.getDefault().getDSTSavings():event.getEnd().getDateTime().getValue());
                            termine.add(new TerminModel(event.getSummary(),start,end,event.getLocation()));
                        }    
                    }
                synchronized(calendar) {
                    calendar.setMitarbeiterTermine(new EmployeeModel(entry.getSummary(),entry.getId()), termine);
                }
            }
        synchronized(calendar) {
            calendar.notifyObservers();
        }
        System.out.println("GoogleApiController: CalendarModel updated");
        System.out.println(calendar);
    }
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        try {
            while(calendar.isProgRunning()){
                fillModel();
                try {
                    sleep(60000);
                } catch (InterruptedException ex) {
                       //do nothing
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GoogleApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
