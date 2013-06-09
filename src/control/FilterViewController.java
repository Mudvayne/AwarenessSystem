package control;

import model.TeamFilterModel;
import exceptions.TeamAlreadyExistsException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.CalendarModel;
import model.EmployeeModel;
import model.FilterEntry;
import model.FilterNameEntry;
import model.TableEntry;
import model.FilterModel;
import model.TerminModel;

/**
 * Controller für FilterView
 *
 * @author Manuel Wurth
 */
public class FilterViewController implements Initializable, Observer {

    private AwarenessSystem main;
    private CalendarModel calendarModel;
    private TeamFilterModel teamFilterModel;
    EventHandler<ActionEvent> handler;
    private boolean okClicked = false;
    private Map<String, String[]> teams = new HashMap<>();
    private int countCheckBox = 0;
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
    @FXML
    private TextField FilterViewTextField;

    public void setCalendarModel(CalendarModel calendarModel) {
        this.calendarModel = calendarModel;
        calendarModel.addObserver(this);
    }

    public void setTeamFilterModel(TeamFilterModel teamFilterModel) {
        this.teamFilterModel = teamFilterModel;
        teams = teamFilterModel.getAllTeams();
        teamFilterModel.addObserver(this);
    }

    public void setMainApp(AwarenessSystem main) {
        this.main = main;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleMouseReleasedTeam(MouseEvent event) {
        int position = filterViewTable.getFocusModel().getFocusedCell().getRow();
        FilterNameEntry teamName = (FilterNameEntry) filterViewTable.getItems().get(position);

        String[] namen = teams.get(teamName.getColFilterName());
        final ObservableList<FilterEntry> data = FXCollections.observableList(calendarModel.getFilterEntrys());
        // final ObservableList<FilterEntry> tmp = FXCollections.observableList(calendarModel.getFilterEntrys());


        for (FilterEntry e : data) {
            for (int i = 0; i < namen.length; i++) {
                if (namen[i].equals(e.getColMitarbeiter())) {
                    e.setColAuswahl(true);
                }
            }
        }

        mitarbeiterViewTable.setItems(data);



    }

    @FXML
    private void handleButtonNewFilterAction(ActionEvent event) throws TeamAlreadyExistsException {

        FilterViewTextField.setEditable(true);
        FilterViewTextField.setDisable(false);
        FilterViewTextField.setText("Bitte geben sie einen neuen TeamNamen ein.");
        final ObservableList<FilterEntry> data = FXCollections.observableList(calendarModel.getFilterEntrys());
        mitarbeiterViewTable.setItems(data);

    }

    @FXML
    private void handelMouseClickedTextField(MouseEvent event) {
        if (FilterViewTextField.getText().equals("Bitte geben sie einen neuen TeamNamen ein.") || FilterViewTextField.getText().equals("Leerer Team Name nicht Erlaubt") || FilterViewTextField.getText().equals("Es Existiert bereits ein Team mit diesem Namen")) {
            FilterViewTextField.setText("");
        }


    }

    @FXML
    private void handleButtonDeleteFilterAction(ActionEvent event) {
        int position = filterViewTable.getFocusModel().getFocusedCell().getRow();
        if (position == -1) {
            FilterViewTextField.setText("Bitte wählen sie ein Team Aus um es zu Löschen.");
        } else {
            FilterNameEntry teamName = (FilterNameEntry) filterViewTable.getItems().get(position);
            teamFilterModel.deleteTeam(teamName.getColFilterName());
            FilterViewTextField.setText("Team wurde Erfolgreich gelöscht.");
        }

    }

    @FXML
    private void handleEmployeIsSelected(ActionEvent event) {
        System.out.println("test");
    }

  
    @FXML
    private void handleButtonSaveFiltersAction(ActionEvent event) throws IOException {
        if (FilterViewTextField.getText().equals("")) {
            FilterViewTextField.setText("Leerer Team Name nicht Erlaubt");
        }
        if (FilterViewTextField.getText().equals("Bitte geben sie einen neuen TeamNamen ein.") || FilterViewTextField.getText().equals("Leerer Team Name nicht Erlaubt") || FilterViewTextField.getText().equals("Es Existiert bereits ein Team mit diesem Namen")) {
        } else {

            final ObservableList<FilterEntry> data = mitarbeiterViewTable.getItems();
            ArrayList<String> team = new ArrayList<>();
            for (FilterEntry e : data) {
                if (e.getColAuswahl()) {
                    team.add(e.getColMitarbeiter());
                }
            }
            String[] teamTmp = new String[team.size()];
            team.toArray(teamTmp);

            try {
                // TableColumn isSelectedColumn =
                //isSelectedColumn.getProperties();
                teamFilterModel.safeNewTeam(FilterViewTextField.getText(), teamTmp);
            } catch (TeamAlreadyExistsException ex) {
                FilterViewTextField.setText("Es Existiert bereits ein Team mit diesem Namen");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterName.setCellValueFactory(new PropertyValueFactory<FilterNameEntry, String>("colFilterName"));
        mitarbeiter.setCellValueFactory(new PropertyValueFactory<FilterEntry, String>("colMitarbeiter"));
        auswahl.setCellValueFactory(new PropertyValueFactory<FilterEntry, Boolean>("colAuswahl"));
        auswahl.setCellFactory(setCheckBox());
        auswahl.setEditable(true);


        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CheckBox cb = (CheckBox) event.getSource();
                TableColumn column = (TableColumn) cb.getUserData();
                if (cb.isSelected()) {
                    System.out.println(cb.getUserData());
                   
                } else {
                    System.out.println("false");
                }

            }
        };


    }

    private Callback<TableColumn<FilterEntry, Boolean>, TableCell<FilterEntry, Boolean>> setCheckBox() {
        return new Callback<TableColumn<FilterEntry, Boolean>, TableCell<FilterEntry, Boolean>>() {
            @Override
            public TableCell<FilterEntry, Boolean> call(TableColumn<FilterEntry, Boolean> param) {
                return new TableCell<FilterEntry, Boolean>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            // Use a SimpleDateFormat or similar in the format method
                            if (item != null) {
                                
                                CheckBox check = new CheckBox();
                                check.setSelected(item.booleanValue());
                                check.setOnAction(handler);
                                
                                setGraphic(check);
                                countCheckBox++;
                            }
                        } else {
                            setText(null);
                        }
                    }
                };

            }
        };
    }

   /* private Callback<TableColumn<FilterEntry, Boolean>, CheckBoxTableCell<FilterEntry, Boolean>> setCheckBox() {
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
    }*/

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
            teams = teamFilterModel.getAllTeams();
            updateTeamTable();
        }

    }
}
