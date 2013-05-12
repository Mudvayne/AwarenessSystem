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
import javafx.scene.control.TableView;


/**
 *
 * @author Mudvayne
 */
public class FilterViewController implements Initializable {
    
    private AwarenessSystem main;
    private boolean okClicked = false;
    
    public void setMainApp(AwarenessSystem main)
    {
        this.main = main;
    }
    
    public boolean isOkClicked()
    {
        return okClicked;
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
    private void handleButtonSaveFiltersAction(ActionEvent event) throws IOException
    {
        okClicked = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }
}
