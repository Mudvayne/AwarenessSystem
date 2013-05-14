/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.HashMap;
import java.util.Map;
import exceptions.TeamAlreadyExistsException;
import exceptions.TeamNotFoundException;
import model.TeamFilterModel;

/**
 *
 * @author Florian
 */
public class TeamFilterController {

    private Map<String, String[]> Teams = new HashMap<>();
    private TeamFilterModel model = new TeamFilterModel();

  
    public String[] getTeam(String teamName) {
        if (teamExist(teamName)) {
            return Teams.get(teamName);
        } else {
            return null;
        }
    }

    public void updateTeams(String teamName, String[] team) throws TeamNotFoundException{
        if (teamExist(teamName)) {
            Teams.put(teamName, team);
            model.PersistsTeams(Teams);
        }else{
            throw new TeamNotFoundException("This Team not Exists in your Team Filters.");
        }
    }
    
    public  void safeNewTeam(String teamName, String[] team) throws TeamAlreadyExistsException{
         if (!teamExist(teamName)) {
            Teams.put(teamName, team);
            model.PersistsTeams(Teams);
         }else{
             throw new TeamAlreadyExistsException("There is already a Team with this Team-Name.");
         }
    }
    
    public Map<String, String[]> getAllTeams() {
        loadTeams();
        return Teams;
    }

    private void loadTeams() {
        Teams = model.getTeams();
    }

    private boolean teamExist(String teamName) {
        loadTeams();
        if (Teams.containsKey(teamName)) {
            return true;
        } else {
            return false;
        }
    }
}
