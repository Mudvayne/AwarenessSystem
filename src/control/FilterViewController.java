package control;

import model.TeamFilterModel;
import exceptions.TeamAlreadyExistsException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.CalendarModel;
import model.FilterEntry;
import model.FilterNameEntry;
import model.TableEntry;
import model.FilterModel;

/**
 * Controller für FilterView
 *
 * @author Manuel Wurth
 */
public class FilterViewController implements Initializable, Observer {

    private AwarenessSystem main;
    private CalendarModel calendarModel;
    private TeamFilterModel teamFilterModel;
    private boolean okClicked = false;
    private Map<String, String[]> teams = new HashMap<>();
    private final static TeamFilterModel tfc = new TeamFilterModel();
    @FXML
    private TableView<FilterNameEntry> filterViewTable;
    @FXML
    private TableView<FilterEntry> mitarbeiterViewTable;
    @FXML
    private TableColumn mitarbeiter;
    @FXML
    private TableColumn auswahl;
    @FXML
    private TableColumn filterName;

    public void setCalendarModel(CalendarModel calendarModel) {
        this.calendarModel = calendarModel;
        calendarModel.addObserver(this);
    }

    public void setTeamFilterModel(TeamFilterModel teamFilterModel) {
        this.teamFilterModel = teamFilterModel;
        teamFilterModel.addObserver(this);
    }

    public void setMainApp(AwarenessSystem main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleButtonNewFilterAction(ActionEvent event) throws TeamAlreadyExistsException {

        tfc.safeNewTeam("Hallo" + Math.random() * 20000, new String[]{"hallo1@gmail.de", "Test1@gmail.com"});
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
        filterName.setCellValueFactory(new PropertyValueFactory<FilterNameEntry, String>("colFilterName"));
        mitarbeiter.setCellValueFactory(new PropertyValueFactory<FilterEntry, String>("colMitarbeiter"));
        auswahl.setCellValueFactory(new PropertyValueFactory<FilterEntry, Boolean>("colAuswahl"));


        
        auswahl.setCellFactory(CheckBoxTableCell.forTableColumn(auswahl));
        auswahl.setEditable(true);





    }

    
    private Callback<TableColumn<FilterEntry, Boolean>, CheckBoxTableCell<FilterEntry, Boolean>> setCheckBox() {
        return new Callback<TableColumn<FilterEntry, Boolean>, CheckBoxTableCell<FilterEntry, Boolean>>() {
            @Override
            public CheckBoxTableCell<FilterEntry, Boolean> call(TableColumn<FilterEntry, Boolean> param) {
                return new CheckBoxTableCell<FilterEntry, Boolean>() {
                    protected void updateItem(boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setEditable(true);

                            if (item) {
                            }
                        } else {
                            setText(null);
                        }
                    }
                };

            }
        };
    }

    public void updateMitarbeiterTabel() {
        final ObservableList<FilterEntry> data = FXCollections.observableList(calendarModel.getFilterEntrys());
        mitarbeiterViewTable.setItems(data);
    }

    public void updateTeamTable() {
        final ObservableList<FilterNameEntry> data = FXCollections.observableList(teamFilterModel.getFilterNameEntrys());
        filterViewTable.setItems(data);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        
        if (o.getClass() == CalendarModel.class) {
            updateMitarbeiterTabel();
        }
        if (o.getClass() == TeamFilterModel.class) {
            updateTeamTable();
        }

    }
}
