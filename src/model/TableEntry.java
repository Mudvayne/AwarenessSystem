/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Manuel Wurth
 */
public class TableEntry {
    
    private SimpleStringProperty colName;
    private SimpleStringProperty col6;
    private SimpleStringProperty col7;
    private SimpleStringProperty col8;
    private SimpleStringProperty col9;
    private SimpleStringProperty col10;
    private SimpleStringProperty col11;
    private SimpleStringProperty col12;
    private SimpleStringProperty col13;
    private SimpleStringProperty col14;
    private SimpleStringProperty col15;
    private SimpleStringProperty col16;
    private SimpleStringProperty col17;
    private SimpleStringProperty col18;
    private SimpleStringProperty col19;
    
    public TableEntry(String employeeName, boolean[] available)
    {
        this.colName = new SimpleStringProperty(employeeName);
        this.col6 = new SimpleStringProperty(String.valueOf(available[0] ? "" : "n.a."));
        this.col7 = new SimpleStringProperty(String.valueOf(available[1] ? "" : "n.a."));
        this.col8 = new SimpleStringProperty(String.valueOf(available[2] ? "" : "n.a."));
        this.col9 = new SimpleStringProperty(String.valueOf(available[3] ? "" : "n.a."));
        this.col10 = new SimpleStringProperty(String.valueOf(available[4] ? "" : "n.a."));
        this.col11 = new SimpleStringProperty(String.valueOf(available[5] ? "" : "n.a."));
        this.col12 = new SimpleStringProperty(String.valueOf(available[6] ? "" : "n.a."));
        this.col13 = new SimpleStringProperty(String.valueOf(available[7] ? "" : "n.a."));
        this.col14 = new SimpleStringProperty(String.valueOf(available[8] ? "" : "n.a."));
        this.col15 = new SimpleStringProperty(String.valueOf(available[9] ? "" : "n.a."));
        this.col16 = new SimpleStringProperty(String.valueOf(available[10] ? "" : "n.a."));
        this.col17 = new SimpleStringProperty(String.valueOf(available[11] ? "" : "n.a."));
        this.col18 = new SimpleStringProperty(String.valueOf(available[12] ? "" : "n.a."));
        this.col19 = new SimpleStringProperty(String.valueOf(available[13] ? "" : "n.a."));
    }
    
    /**
     * @return the colName
     */
    public String getColName()
    {
        return colName.get();
    }

    /**
     * @return the col6
     */
    public String getCol6() {
        return col6.get();
    }

    /**
     * @return the col7
     */
    public String getCol7() {
        return col7.get();
    }

    /**
     * @return the col8
     */
    public String getCol8() {
        return col8.get();
    }

    /**
     * @return the col9
     */
    public String getCol9() {
        return col9.get();
    }

    /**
     * @return the col10
     */
    public String getCol10() {
        return col10.get();
    }

    /**
     * @return the col11
     */
    public String getCol11() {
        return col11.get();
    }

    /**
     * @return the col12
     */
    public String getCol12() {
        return col12.get();
    }

    /**
     * @return the col13
     */
    public String getCol13() {
        return col13.get();
    }

    /**
     * @return the col14
     */
    public String getCol14() {
        return col14.get();
    }

    /**
     * @return the col15
     */
    public String getCol15() {
        return col15.get();
    }

    /**
     * @return the col16
     */
    public String getCol16() {
        return col16.get();
    }

    /**
     * @return the col17
     */
    public String getCol17() {
        return col17.get();
    }

    /**
     * @return the col18
     */
    public String getCol18() {
        return col18.get();
    }

    /**
     * @return the col19
     */
    public String getCol19() {
        return col19.get();
    }
}
