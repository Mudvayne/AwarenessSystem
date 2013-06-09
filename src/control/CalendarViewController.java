package control;

import com.sun.javafx.collections.transformation.FilteredList;
import com.sun.javafx.collections.transformation.SortedList;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.CalendarModel;
import model.FilterEntry;
import model.FilterNameEntry;
import model.TableEntry;
import model.TeamFilterModel;

/**
 * Controller Klasse für die CalendarView
 *
 * @author Manuel Wurth
 */
public class CalendarViewController implements Initializable, Observer {

    private AwarenessSystem main;
    private CalendarModel calendarModel;
    private TeamFilterModel teamFilterModel;
    private ObservableList<TableEntry> shownTableList;
    private Map<String, String[]> teams;
    @FXML
    private TableView<TableEntry> calendarViewTable;
    @FXML
    private TableColumn tableColName;
    @FXML
    private ComboBox teamComboBox;
    @FXML
    private FXCollections observableArrayList;
    @FXML
    private TableColumn tableCol6;
    @FXML
    private TableColumn tableCol7;
    @FXML
    private TableColumn tableCol8;
    @FXML
    private TableColumn tableCol9;
    @FXML
    private TableColumn tableCol10;
    @FXML
    private TableColumn tableCol11;
    @FXML
    private TableColumn tableCol12;
    @FXML
    private TableColumn tableCol13;
    @FXML
    private TableColumn tableCol14;
    @FXML
    private TableColumn tableCol15;
    @FXML
    private TableColumn tableCol16;
    @FXML
    private TableColumn tableCol17;
    @FXML
    private TableColumn tableCol18;
    @FXML
    private TableColumn tableCol19;
    @FXML
    private TextField searchField;

    public void setMainApp(AwarenessSystem main) {
        this.main = main;
    }

    public void setCalendarModel(CalendarModel calendarModel) {
        this.calendarModel = calendarModel;
        calendarModel.addObserver(this);
    }

    public void setTeamFilterModel(TeamFilterModel teamFilterModel) {
        this.teamFilterModel = teamFilterModel;
        teams = teamFilterModel.getAllTeams();
        teamFilterModel.addObserver(this);
    }

    @FXML
    private void handleButtonFiltersAction(ActionEvent event) throws Exception {
        main.showFilterView();
    }

    @FXML
    private void handleSearchTextHasChanged(KeyEvent event) {
       // final ObservableList<TableEntry> data = FXCollections.observableList(calendarModel.getTableEntrys());
        final ObservableList<TableEntry> singlePerson = FXCollections.observableList(calendarModel.getTableEntrys());
        singlePerson.clear();
        boolean found = false;
        for (int i = 0; i < shownTableList.size(); i++) {
            if (shownTableList.get(i).getColName().contains(searchField.getCharacters().toString())) {
                singlePerson.add(shownTableList.get(i));
                calendarViewTable.setItems(singlePerson);
                found = true;
            }
        }
        if (!found) {
            calendarViewTable.setItems(shownTableList);
        }
    }

    @FXML
    private void handleDropDownAction(ActionEvent event) {

        String[] namen = teams.get(teamComboBox.getValue().toString());

        final ObservableList<TableEntry> data = FXCollections.observableList(calendarModel.getTableEntrys());
        if (teamComboBox.getValue().toString().equals("ALL")) {
            calendarViewTable.setItems(shownTableList);
        } else {
            final ObservableList<TableEntry> selectedTeam = FXCollections.observableList(calendarModel.getTableEntrys());
            selectedTeam.clear();
            final ObservableList<FilterEntry> data2 = FXCollections.observableList(calendarModel.getFilterEntrys());


            for (FilterEntry e : data2) {
                for (int i = 0; i < namen.length; i++) {
                    if (namen[i].equals(e.getColMitarbeiter())) {
                        e.setColAuswahl(true);
                    }
                }
            }

            for (TableEntry e : data) {
                for (FilterEntry f : data2) {
                    if (e.getColName().equals(f.getColMitarbeiter())) {
                        if (f.getColAuswahl() == true) {
                            selectedTeam.add(e);

                        }
                    }
                }
            }

            shownTableList = selectedTeam;

            calendarViewTable.setItems(shownTableList);

        }
    }

    public void handle(ActionEvent event) {
        searchField.requestFocus();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableCol6.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col6"));
        tableCol7.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col7"));
        tableCol8.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col8"));
        tableCol9.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col9"));
        tableCol10.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col10"));
        tableCol11.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col11"));
        tableCol12.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col12"));
        tableCol13.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col13"));
        tableCol14.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col14"));
        tableCol15.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col15"));
        tableCol16.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col16"));
        tableCol17.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col17"));
        tableCol18.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col18"));
        tableCol19.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("col19"));
        tableColName.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("colName"));

        tableCol6.setCellFactory(setColour());
        tableCol7.setCellFactory(setColour());
        tableCol8.setCellFactory(setColour());
        tableCol9.setCellFactory(setColour());
        tableCol10.setCellFactory(setColour());
        tableCol11.setCellFactory(setColour());
        tableCol12.setCellFactory(setColour());
        tableCol13.setCellFactory(setColour());
        tableCol14.setCellFactory(setColour());
        tableCol15.setCellFactory(setColour());
        tableCol16.setCellFactory(setColour());
        tableCol17.setCellFactory(setColour());
        tableCol18.setCellFactory(setColour());
        tableCol19.setCellFactory(setColour());

       
    }

    private Callback<TableColumn<TableEntry, String>, TableCell<TableEntry, String>> setColour() {
        return new Callback<TableColumn<TableEntry, String>, TableCell<TableEntry, String>>() {
            @Override
            public TableCell<TableEntry, String> call(TableColumn<TableEntry, String> param) {
                return new TableCell<TableEntry, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            // Use a SimpleDateFormat or similar in the format method
                            if (item.equals("n.a.")) {
                                setStyle("-fx-background-color:#FF0000;");
                            }
                        } else {
                            setText(null);
                        }
                    }
                };

            }
        };
    }

    public void updateTeamTable() {
        final ObservableList<FilterNameEntry> data = FXCollections.observableList(teamFilterModel.getFilterNameEntrys());
        //teamComboBox.getItems().clear();
        for (FilterNameEntry e : data) {
            teamComboBox.getItems().add(e.getColFilterName());
        }
        teamComboBox.getItems().add("ALL");
    }

    public void updateCalendarTable() {
        //boolean[] times = { true, false, true, false, false, true, false, true, false, false, true, false, true, false }; //dummys
        final ObservableList<TableEntry> data = FXCollections.observableList(calendarModel.getTableEntrys());
        shownTableList = data;
        /*
         FXCollections.observableArrayList(
         new TableEntry("Hans", times),
         new TableEntry("Klaus", times),
         new TableEntry("Hugo", times),
         new TableEntry("Hubert", times)
         );*/
        calendarViewTable.setItems(shownTableList);
        /*
         EmployeeModel[] employees = (EmployeeModel[]) calendarModel.getMitarbeiterList().toArray();
         for(int i = 0 ; i < calendarModel.getMitarbeiterList().size() ; i++)
         {   
         boolean[] times2 = { true, false, true, false, false, true, false, true, false, false, true, false, true, false }; //dummywerte
         FXCollections.observableArrayList(new TableEntry(employees[i].getName(), times2));
         }
        
         calendarViewTable.setItems(data);
         */
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

        if (o.getClass() == CalendarModel.class) {
            updateCalendarTable();
        }
        if (o.getClass() == TeamFilterModel.class) {
            teams = teamFilterModel.getAllTeams();
            updateTeamTable();
        }

    }

    public void doStyling() {
    }
}
