/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * Model fuer einen Termin, mit Name, Start- und End-Zeit sowie Ort
 * @author michi
 */
public class TerminModel implements Comparable<TerminModel>{

    private String name;
    private Date start;
    private Date ende;
    private String ort;

    public TerminModel(String name, Date start, Date ende, String ort) {
        this.name = name;
        this.start = start;
        this.ende = ende;
        this.ort = ort;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnde() {
        return ende;
    }

    public String getOrt() {
        return ort;
    }
    
    @Override
    public String toString() {
        return (name + ": " + start + " - " + ende + " (" + ort + ")");
    }
    
    //um Events nach der Startzeit zu sortieren
    @Override
    public int compareTo(TerminModel o) {
       return this.start.compareTo(o.start); 
    }
    
}
