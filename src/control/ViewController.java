/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.StageStyle;

/**
 *
 * @author Mudvayne
 */
public class ViewController implements Initializable {
    
    @FXML
    private void handleButtonLoginAction(ActionEvent event) throws IOException
    {
        Stage stage = AwarenessSystem.stage;
        Parent page = (Parent) FXMLLoader.load(AwarenessSystem.class.getResource("CalendarView.fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        stage.show();
        
        System.out.println("login");
    }
    
     
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
    private void handleButtonCancelFiltersAction(ActionEvent event) throws IOException
    {
       Stage stage = AwarenessSystem.stage;
        Parent page = (Parent) FXMLLoader.load(AwarenessSystem.class.getResource("CalendarView.fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
