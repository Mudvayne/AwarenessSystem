package control;

import model.TeamFilterModel;
import exceptions.TeamAlreadyExistsException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.CalendarModel;
import model.FilterEntry;
import model.FilterNameEntry;

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
    private final static TeamFilterModel tfc = new TeamFilterModel();
    @FXML
    private TableView<FilterNameEntry> filterViewTable;
    @FXML
    private Button submittNewTeam;
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
    @FXML
    private TextField teamNameTextField;

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
        submittNewTeam.setDisable(false);
        teamNameTextField.setDisable(false);
        FilterViewTextField.setText("Bitte geben sie einen neuen TeamNamen ein.");
        final ObservableList<FilterEntry> data = FXCollections.observableList(calendarModel.getFilterEntrys());
        mitarbeiterViewTable.setItems(data);

    }

    @FXML
    private void handelMouseClickedTextField(MouseEvent event) {
        if (FilterViewTextField.getText().equals("Bitte geben sie einen neuen TeamNamen ein.") || FilterViewTextField.getText().equals("Leerer Team Name nicht Erlaubt") || FilterViewTextField.getText().equals("Es Existiert bereits ein Team mit diesem Namen")) {
            teamNameTextField.setText("");
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
    private void handleButtonSaveFiltersAction(ActionEvent event) throws IOException {
        if (teamNameTextField.getText().equals("")) {
            FilterViewTextField.setText("Leerer Team Name nicht Erlaubt");
        }
            final ObservableList<FilterEntry> data = mitarbeiterViewTable.getItems();
            ArrayList<String> team = new ArrayList<>();
            for (FilterEntry e : data) {
                if (e.getColAuswahl()) {
                    team.add(e.getColMitarbeiter());
                }
            }
            if (team.size() == 0) {
                FilterViewTextField.setText("Ein Leeres Team ist nicht Erlaubt bitte wählen sie Einen mindestens einen Mitarbeiter für ihr Team aus!");
            } else {
                String[] teamTmp = new String[team.size()];
                team.toArray(teamTmp);

                try {
                    teamFilterModel.safeNewTeam(teamNameTextField.getText(), teamTmp);
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

        auswahl.setCellFactory(new Callback<TableColumn<FilterEntry, Object>, TableCell<FilterEntry, Object>>() {
            @Override
            public TableCell<FilterEntry, Object> call(TableColumn<FilterEntry, Object> param) {
                return new CheckBoxTableCell();
            }
        });
        auswahl.setOnEditCommit(
                new EventHandler<CellEditEvent<FilterEntry, Object>>() {
            @Override
            public void handle(CellEditEvent<FilterEntry, Object> t) {
                int row = t.getTablePosition().getRow();
                FilterEntry property = (FilterEntry) t.getTableView().getItems().get(row);
                System.out.println(t.getNewValue());
                System.out.println(property);
                property.setColAuswahl((boolean) t.getNewValue());

            }
        });
        auswahl.setEditable(true);
    }

    class CheckBoxTableCell extends TableCell<FilterEntry, Object> {

        private TextField textField;
        private CheckBox checkBox;

        public CheckBoxTableCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();

                if (getItem() instanceof Boolean) {
                    createCheckBox();
                    setText(null);
                    setGraphic(checkBox);
                } else {
                    createTextField();
                    setText(null);
                    setGraphic(textField);
                    textField.selectAll();
                }
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            if (getItem() instanceof Boolean) {
                setText(getItem().toString());
            } else {
                setText((String) getItem());
            }
            setGraphic(null);
        }

        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (getItem() instanceof Boolean) {
                        if (checkBox != null) {
                            checkBox.setSelected(getBoolean());
                        }
                        setText(null);
                        setGraphic(checkBox);
                    } else {
                        if (textField != null) {
                            textField.setText(getString());
                        }
                        setText(null);
                        setGraphic(textField);
                    }
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }

        private Boolean getBoolean() {
            return getItem() == null ? false : (Boolean) getItem();
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }

        private void createCheckBox() {
            checkBox = new CheckBox();
            checkBox.setSelected(getBoolean());
            checkBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            checkBox.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        commitEdit(checkBox.isSelected());
                    }
                }
            });
        }
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
            teams = teamFilterModel.getAllTeams();
            updateTeamTable();
        }

    }
}
