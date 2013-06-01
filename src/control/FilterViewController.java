package control;

import exceptions.TeamAlreadyExistsException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * Controller für FilterView
 * @author Manuel Wurth
 */
public class FilterViewController implements Initializable, Observer {

    private AwarenessSystem main;
    private boolean okClicked = false;
    private Map<String, String[]> teams = new HashMap<>();
    private final static TeamFilterController tfc = new TeamFilterController();
    
    public void setMainApp(AwarenessSystem main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleButtonNewFilterAction(ActionEvent event) throws TeamAlreadyExistsException {
        
        tfc.safeNewTeam("Hallo" + Math.random()*20000, new String[]{"hallo1@gmail.de","Test1@gmail.com"});
        System.out.println("neuer filter");
    }

    @FXML
    private void handleButtonDeleteFilterAction(ActionEvent event) {
        System.out.println("filter löschen");
    }

    @FXML
    private void handleButtonSaveFiltersAction(ActionEvent event) throws IOException {
        okClicked = true;
        System.out.println("filter speichern");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @Override
    public void update(Observable o, Object arg) {
        teams = (Map<String, String[]>) arg;
        System.out.println("teams has been changed");
    }
}
