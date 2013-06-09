package model;

import java.util.HashMap;
import java.util.Map;
import exceptions.TeamAlreadyExistsException;
import exceptions.TeamNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import model.FilterModel;

/**
 *
 * @author Florian Neuner
 */
public class TeamFilterModel extends Observable{

    private Map<String, String[]> teams ;
    private FilterModel model = new FilterModel();

     private boolean progRunning;

    public boolean isProgRunning() {
        synchronized(this) {
            return progRunning;
        }
    }

    
    public void setProgRunning(boolean progRunning) {
        this.progRunning = progRunning;
    }
    
    public TeamFilterModel(){
        this.teams = new HashMap<>();
        this.progRunning = true;
        loadTeams();
    }
  
    public String[] getTeam(String teamName) {
        if (teamExist(teamName)) {
            return teams.get(teamName);
        } else {
            return null;
        }
        
        // return teamExists(teamName) == true ? return Teams.get(teamName) : return null;
    }
    
    public void deleteTeam(String teamName){
        teams.remove(teamName);
        model.remove(teamName);
        setChanged();
         this.notifyObservers();
    }
     public List<FilterNameEntry> getFilterNameEntrys(){
        List<FilterNameEntry> table = new ArrayList<>();
        synchronized(this){
            for(String entry : teams.keySet()){
                String teamName = entry;
                table.add(new FilterNameEntry(teamName));
            }
        }
        return table;
    }

    public void updateTeams(String teamName, String[] team) throws TeamNotFoundException{
        if (teamExist(teamName)) {
            teams.put(teamName, team);
            model.PersistsTeams(teams);
            setChanged();
            this.notifyObservers();
        }else{
            throw new TeamNotFoundException("This team not exists in your team filters.");
        }
    }
    
    public  void safeNewTeam(String teamName, String[] team) throws TeamAlreadyExistsException{
         if (!teamExist(teamName)) {
            teams.put(teamName, team);
            model.PersistsTeams(teams);
            setChanged();
             this.notifyObservers();           
         }else{
             throw new TeamAlreadyExistsException("There is already a Team with this Team-Name.");
         }
    }
    
    public Map<String, String[]> getAllTeams() {
        loadTeams();
        return teams;
    }

    public void loadTeams() {
        teams = model.getTeams();
        setChanged();
        //this.notifyObservers();
    }

    private boolean teamExist(String teamName) {
        loadTeams();
        return teams.containsKey(teamName);
    }
}
