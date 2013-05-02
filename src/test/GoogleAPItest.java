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
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
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

    public static void main(String[] args) {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
            GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId("777633749381-sesph1uuf3un0mtpipob67qsc67krctv@developer.gserviceaccount.com")
                .setServiceAccountScopes(CalendarScopes.CALENDAR_READONLY)
                .setServiceAccountPrivateKeyFromP12File(new File("key.p12"))
                .build();

            Calendar calendar = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("TeamAwarenessSystem/0.1").build();


            CalendarList feed = calendar.calendarList().list().execute();

            if (feed.getItems() != null) {
                for (CalendarListEntry entry : feed.getItems()) {
                    System.out.println("-----------------------------------------------");
                    System.out.println("ID: " + entry.getId());
                    System.out.println("Summary: " + entry.getSummary());
                    System.out.println("Description: " + entry.getDescription());
                }
            }
               
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(GoogleAPItest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
