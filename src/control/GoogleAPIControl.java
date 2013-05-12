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
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.Users;

/**
 * Control welche die Anbindung zu Google managed.
 * Fuellt die Model Users mit den User und deren Events
 * @author michi
 */
public class GoogleAPIControl {
    
    private HttpTransport HTTP_TRANSPORT;
    private JsonFactory JSON_FACTORY = new JacksonFactory();
    private String SERVICE_ACCOUNT = "777633749381-sesph1uuf3un0mtpipob67qsc67krctv@developer.gserviceaccount.com";
    private com.google.api.services.calendar.Calendar client;
    
    private Users users;
    
    /**
     * Konstuktor befuellt das Model initial
     * @param users
     */
    public GoogleAPIControl(Users users) {
        this.users = users;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            
            GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(SERVICE_ACCOUNT)
                    .setServiceAccountScopes(CalendarScopes.CALENDAR_READONLY)
                    .setServiceAccountPrivateKeyFromP12File(new File("key.p12"))
                    .build();
            
            client = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("TeamAwarenessSystem/0.1").build();
            
            CalendarList calendarList = client.calendarList().list().execute();
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            DateTime startRange = new DateTime(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, +3);
            DateTime stopRange = new DateTime(cal.getTime());
            
            if (calendarList.getItems() != null) {
                for (CalendarListEntry entry : calendarList.getItems()) {

                    Events events = client.events().list(entry.getId()).setTimeMin(startRange).setTimeMax(stopRange).execute();
                    
                    //last update auslesen und Zeitzonen korrektur 
                    Date update = new Date(events.getUpdated().getValue() - TimeZone.getDefault().getRawOffset() - TimeZone.getDefault().getDSTSavings());
                    
                    User user = new User(entry.getSummary(), entry.getId(), update );
                    
                    if(events.getItems() != null)
                      for (Event event : events.getItems()) {
                          Date start;
                          // ganztaegiges Event?
                          if (event.getStart().getDateTime()==null) {
                              // konvertiere DateTime zu Date mit Zeitzonen korrektur
                              start = new Date(event.getStart().getDate().getValue() - TimeZone.getDefault().getRawOffset() - TimeZone.getDefault().getDSTSavings());
                          } else {
                              // konvertiere DateTime zu Date
                              start = new Date(event.getStart().getDateTime().getValue());
                          }
                          Date stop;
                          // ganztaegiges Event?
                          if (event.getEnd().getDateTime()==null) {
                              // konvertiere DateTime zu Date mit Zeitzonen korrektur
                              stop = new Date(event.getEnd().getDate().getValue() - TimeZone.getDefault().getRawOffset() - TimeZone.getDefault().getDSTSavings());
                          } else {
                              // konvertiere DateTime zu Date
                              stop = new Date(event.getEnd().getDateTime().getValue());
                          }
                          user.getEvents().add(new model.Event(event.getSummary(),start,stop,event.getLocation()));
                      }
                    this.users.getList().add(user);
                }
            }
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(GoogleAPIControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update() {
        
    }
    
    
}
