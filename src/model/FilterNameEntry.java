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
public class FilterNameEntry {
 
    
    
     private SimpleStringProperty colFilterName;
     
      public FilterNameEntry(String colFilterName) {
          this.colFilterName = new SimpleStringProperty(colFilterName);
      }
      /**
       * 
       * @return  
       */
      public String getColFilterName(){
          return colFilterName.get();
      }
}
