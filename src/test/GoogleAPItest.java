/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michi
 */
public class GoogleAPItest {
    
    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String SERVICE_ACCOUNT = "777633749381-sesph1uuf3un0mtpipob67qsc67krctv@developer.gserviceaccount.com";
    
    public static void main(String[] args) {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
            GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(SERVICE_ACCOUNT)
                .setServiceAccountScopes(CalendarScopes.CALENDAR_READONLY)
                .setServiceAccountPrivateKeyFromP12File(new File("key.p12"))
                .build();

            com.google.api.services.calendar.Calendar client = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("TeamAwarenessSystem/0.1").build();
            
            CalendarList feed = client.calendarList().list().execute();

            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
            cal.set(java.util.Calendar.MINUTE, 0);
            cal.set(java.util.Calendar.SECOND, 0);
            cal.set(java.util.Calendar.MILLISECOND, 0);
            DateTime start = new DateTime(cal.getTime());
            cal.add(java.util.Calendar.DAY_OF_MONTH, +7);
            DateTime stop = new DateTime(cal.getTime());
                    
            if (feed.getItems() != null) {
                for (CalendarListEntry entry : feed.getItems()) {
                    System.out.println("User: " + entry.getSummary());
                    Events events = client.events().list(entry.getId()).setTimeMin(start).setTimeMax(stop).execute();
        
                    if(events.getItems() != null)
                      for (Event event : events.getItems()) {
                          String a = event.getStart().getDateTime()==null?event.getStart().getDate().toString():event.getStart().getDateTime().toString();
                          String b = event.getEnd().getDateTime()==null?event.getEnd().getDate().toString():event.getEnd().getDateTime().toString();
                          System.out.println(event.getSummary() + " - " + a + " - " + b);
                      }
                }
            }
               
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(GoogleAPItest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
