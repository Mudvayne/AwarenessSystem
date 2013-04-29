/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gdata.client.calendar.*;
import com.google.gdata.data.calendar.*;
import java.net.URL;
import sun.print.resources.serviceui;

/**
 *
 * @author Flo
 */
public class Calendars {

    public static void main(String... args) {
        try {
            CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
                myService.setUserCredentials("myaccount@gmail.com", "mypass");
                URL feedUrl = new URL("https://www.google.com/calendar/feeds/username@gmail.com/public/full");
                CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
                System.out.println("Your calendars:");
                System.out.println();
                for (int i = 0; i < resultFeed.getEntries().size(); i++) {
                    CalendarEntry entry = resultFeed.getEntries().get(i);
                    System.out.println("\t" + entry.getTitle().getPlainText());
                }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

