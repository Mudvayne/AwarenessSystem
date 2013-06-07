/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Flo
 */
public class FilterEntry {

    private SimpleStringProperty colMitarbeiter;
    private SimpleStringProperty colAuswahl;
    
    /**
     * 
     * @param employeeName
     * @param taken 
     */
    public FilterEntry(String employeeName) {
        this.colMitarbeiter = new SimpleStringProperty(employeeName);
        this.colAuswahl = new SimpleStringProperty(String.valueOf(false));
    }

    /**
     * 
     * @return the colMitarbeiter
     */
    public String getColMitarbeiter() {
        return colMitarbeiter.get();
    }

    /**
     * 
     * @return the colAuswahl
     */
    public String getColAuswahl() {
        return colAuswahl.get();
    }
}
