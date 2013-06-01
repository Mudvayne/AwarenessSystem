package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CalendarModel;
import model.EmployeeModel;
import model.TableEntry;

/**
 * Controller Klasse für die CalendarView
 * @author Manuel Wurth
 */
public class CalendarViewController implements Initializable{
    
    private AwarenessSystem main;
    private CalendarModel calendarModel;
    
    @FXML
    private TableView<TableEntry> calendarViewTable;
    @FXML
    private TableColumn tableColName;
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
    
    public void setMainApp(AwarenessSystem main)
    {
        this.main = main;
    }
    
    public void setCalendarModel(CalendarModel calendarModel)
    {
        this.calendarModel = calendarModel;
    }
    
    @FXML
    private void handleButtonFiltersAction(ActionEvent event) throws Exception {
        main.showFilterView();
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
       
        tableCol6.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col6"));
        tableCol7.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col7"));
        tableCol8.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col8"));
        tableCol9.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col9"));
        tableCol10.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col10"));
        tableCol11.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col11"));
        tableCol12.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col12"));
        tableCol13.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col13"));
        tableCol14.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col14"));
        tableCol15.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col15"));
        tableCol16.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col16"));
        tableCol17.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col17"));
        tableCol18.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col18"));
        tableCol19.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("col19"));
        tableColName.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("colName"));
    }
    
    public void updateCalendarTable()
    {
        boolean[] times = { true, false, true, false, false, true, false, true, false, false, true, false, true, false }; //dummys
        final ObservableList<TableEntry> data =
        FXCollections.observableArrayList(
            new TableEntry("Hans", times),
            new TableEntry("Klaus", times),
            new TableEntry("Hugo", times),
            new TableEntry("Hubert", times)
        );
        calendarViewTable.setItems(data);
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
}
