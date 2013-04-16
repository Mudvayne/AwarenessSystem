/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Mudvayne
 */
public class ViewController implements Initializable {
    
    @FXML
    private void handleButtonLoginAction(ActionEvent event)
    {
        System.out.println("login");
    }
    
    @FXML
    private void handleButtonFiltersAction(ActionEvent event) throws Exception {
       
        System.out.println("filter editieren");
        /*Stage parent = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("FilterView.fxml"));
        
        Scene scene = new Scene(root);
        
        parent.setScene(scene);
        parent.setTitle("Team Awareness System");
        parent.show();
        */
    }
    
    @FXML
    private void handleButtonNewFilterAction(ActionEvent event)
    {
        System.out.println("neuer filter");
    }
    
    @FXML
    private void handleButtonDeleteFilterAction(ActionEvent event)
    {
        System.out.println("filter löschen");
    }
    
    @FXML
    private void handleButtonSaveFiltersAction(ActionEvent event)
    {
        System.out.println("filter speichern");
    }
    
    @FXML
    private void handleButtonCancelFiltersAction(ActionEvent event)
    {
        System.out.println("filter abbrechen");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
