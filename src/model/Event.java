package model;

import java.util.Date;

/**
 * Model fuer ein Event,
 * repraesentiert genau einen Eintrag im Kalender 
 * mit Name, Anfangszeit, Endzeit und Ort
 * @author michi
 */
public class Event implements Comparable<Event>{
    private String name;
    private Date start;
    private Date ende;
    private String ort;

    public Event(String name, Date start, Date ende, String ort) {
        this.name = name;
        this.start = start;
        this.ende = ende;
        this.ort = ort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnde() {
        return ende;
    }

    public void setEnde(Date ende) {
        this.ende = ende;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
    
    @Override
    public String toString() {
        return (name + " : " + start + " - " + ende + " (" + ort + ")");
    }

    //um Events nach der Startzeit zu sortieren
    @Override
    public int compareTo(Event o) {
       return this.start.compareTo(o.start); 
    }
}
