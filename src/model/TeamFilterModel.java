/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.FilterViewController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class TeamFilterModel extends Observable {

    private  BufferedReader bufferedReader;
    private  BufferedWriter bufferedWriter;
    private  Map<String, String[]> teams = new HashMap<>();
   

    public TeamFilterModel(){
        this.addObserver(new FilterViewController());
    }
    
    public Map<String, String[]> getTeams() {
        loadTeam();
        return teams;
    }

    private void loadTeam() {
        try {
            bufferedReader = new BufferedReader(new FileReader("Teams.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] tmp = line.split(":");
                teams.put(tmp[0], tmp[1].split(","));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeamFilterModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TeamFilterModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  void PersistsTeams(Map<String, String[]> teamMap) {
        teams = teamMap;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("Teams.txt"));
            for (Map.Entry e : teams.entrySet()) {
                String teamToWrite = e.getKey() + ":";
                String[] tmp = (String[]) e.getValue();
                for (int i = 0; i < tmp.length; i++) {
                    if (i != tmp.length - 1) {
                        teamToWrite = teamToWrite + tmp[i] + ",";
                    } else {
                        teamToWrite = teamToWrite + tmp[i];
                    }
                }
                bufferedWriter.write(teamToWrite);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            if(countObservers()>0){
                setChanged();
                notifyObservers(teams);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TeamFilterModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void main(String...args){
        Map<String,String[]> tmp = new HashMap<String,String[]>();
        tmp.put("Hallo", new String[]{"hallo1@gmail.de","Test1@gmail.com"});
        TeamFilterModel tm = new TeamFilterModel();
        tm.PersistsTeams(tmp);
    }
    
}
