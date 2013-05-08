package model;

import java.util.Date;
import java.util.TreeSet;

/**
 * Model fuer einen User,
 * repraesentiert genau einen Mitarbeiter,
 * mit dessen Name, Mail und Terminen
 * @author michi
 */
public class User implements Comparable<User>{
    private String name;
    private String mail;
    private TreeSet<Event> events;
    private Date update;

    public User(String name, String mail, Date update) {
        this.name = name;
        this.mail = mail;
        this.events = new TreeSet<>();
        this.update = update;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TreeSet<Event> getEvents() {
        return events;
    }

    public void setEvents(TreeSet<Event> events) {
        this.events = events;
    }



    @Override
    public String toString() {
        String output = name + " (" + mail + ") " + update;
        for (Event event : events) {
            output+= "\n" + event;
        }
        return output;
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }
}
