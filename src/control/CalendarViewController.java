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
    
    @FXML
    private void handleButtonFiltersAction(ActionEvent event) throws Exception {
       
        Stage stage = AwarenessSystem.stage;
        Parent page = (Parent) FXMLLoader.load(AwarenessSystem.class.getResource("FilterView.fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        stage.show();
        /*Stage parent = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("FilterView.fxml"));
        
        Scene scene = new Scene(root);
        
        parent.setScene(scene);
        parent.setTitle("Team Awareness System");
        parent.show();
        */
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
