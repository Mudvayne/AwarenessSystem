/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Flo
 */
public class FilterEntry {

    private SimpleStringProperty colMitarbeiter;
    private SimpleBooleanProperty colAuswahl;
    
    /**
     * 
     * @param employeeName
     * @param taken 
     */
    public FilterEntry(String employeeName, boolean isSelected) {
        this.colMitarbeiter = new SimpleStringProperty(employeeName);
        this.colAuswahl = new SimpleBooleanProperty(isSelected);
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
    public Boolean getColAuswahl() {
        return colAuswahl.get();
    }

    public void setColAuswahl(boolean colAuswahl) {
        this.colAuswahl =  new SimpleBooleanProperty(colAuswahl);
    }
    
    
}
