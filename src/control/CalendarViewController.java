/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mudvayne
 */
public class CalendarViewController implements Initializable{
    
    private AwarenessSystem main;
    
    public void setMainApp(AwarenessSystem main)
    {
        this.main = main;
    }
    
    @FXML
    private void handleButtonFiltersAction(ActionEvent event) throws Exception {
        main.showFilterView();
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event)
    {
        System.out.println("suche ...");
    }
    
    @FXML
    private void handleDropDownAction(ActionEvent event)
    {
        System.out.println("dropdown action");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}
