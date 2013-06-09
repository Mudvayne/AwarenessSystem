/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian N.
 */
public class FilterModel  {

    private  BufferedReader bufferedReader;
    private  BufferedWriter bufferedWriter;
    private  Map<String, String[]> teams = new HashMap<>();
   
    public FilterModel(){
        
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
        } catch (IOException ex ) {
            Logger.getLogger(FilterModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(String teamName){
        teams.remove(teamName);
        PersistsTeams(teams);
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
           
            
        } catch (IOException ex) {
            Logger.getLogger(FilterModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
