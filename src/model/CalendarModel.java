package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;

/**
 * Model fuer alle Kalender,
 * Baut beim Start die Verbindung zur Google Kalender API auf und befuellt das Model
 * @author Michael Unverzart
 */
public class CalendarModel extends Observable {

    private Map<EmployeeModel, Set<TerminModel>> mitarbeiterTermineList;
    private boolean progRunning;

    public boolean isProgRunning() {
        synchronized(this) {
            return progRunning;
        }
    }

    public void setProgRunning(boolean progRunning) {
        this.progRunning = progRunning;
    }
    
    public CalendarModel() {      
        mitarbeiterTermineList = new TreeMap<>();
        this.progRunning = true;
    }
    
    public void setMitarbeiterTermine(EmployeeModel em, Set<TerminModel> stm) {
        mitarbeiterTermineList.put(em, stm);
        setChanged();
    }
    
    public List<TableEntry> getTableEntrys() {
        List<TableEntry> table = new ArrayList<>();
        synchronized(this) {
            for (Map.Entry<EmployeeModel, Set<TerminModel>> entry : mitarbeiterTermineList.entrySet()) {
                EmployeeModel employeeModel = entry.getKey();
                Set<TerminModel> set = entry.getValue();

                table.add(new TableEntry(employeeModel.getName(), convertTermin2Table(set)));
            }
        }
        return table;
    }
    
    private boolean[] convertTermin2Table(Set<TerminModel> termine) {
        boolean[] table = { true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        for (TerminModel terminModel : termine) {
            for (int i = 6; i <= 19; i++){
                cal.set(Calendar.HOUR_OF_DAY, i+1);
                if(terminModel.getStart().before(cal.getTime())) {
                    table[i-6] = false;
                    if(terminModel.getEnde().before(cal.getTime()) || terminModel.getEnde().equals(cal.getTime()))
                        break;
                }
            }
        }
        return table;
    }
    
    public Map<EmployeeModel, Set<TerminModel>> getMitarbeiterTermineList() {
        synchronized(this) {
            return mitarbeiterTermineList;
        }
    }

    public Set<EmployeeModel> getMitarbeiterList() {
        synchronized(this) {
            return mitarbeiterTermineList.keySet();
        }
    }
    
    public Set<TerminModel> getTermine(EmployeeModel mitarbeiter) {
        synchronized(this) {
            return mitarbeiterTermineList.get(mitarbeiter);
        }
    }
    
    @Override
    public String toString() {
        return "KalenderModel{" + "mitarbeiterTermineList=" + mitarbeiterTermineList + '}';
    }
    
}
