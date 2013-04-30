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

/**
 *
 * @author Mudvayne
 */
public class FilterViewController implements Initializable {
    
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
    private void handleButtonSaveFiltersAction(ActionEvent event) throws IOException
    {
        showCalendarView();
    }
    
    @FXML
    private void handleButtonCancelFiltersAction(ActionEvent event) throws IOException
    {
        showCalendarView();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void showCalendarView() throws IOException
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
}
