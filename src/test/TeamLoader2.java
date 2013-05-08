package test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Florian
 */
public class TeamLoader2 {

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Map<String, String[]> Teams = new HashMap<>();

    public void loadTeams() {
        try {
            bufferedReader = new BufferedReader(new FileReader("Teams.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] tmp = line.split(":");
                Teams.put(tmp[0], tmp[1].split(","));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean teamExist(String teamName) {

        loadTeams();
        if (Teams.containsKey(teamName)) {
            return true;
        } else {
            return false;
        }

    }

    public Map<String, String[]> getAllTeams() {
        loadTeams();
        return Teams;
    }

    public String[] getTeam(String teamName) {
        if (teamExist(teamName)) {
            return Teams.get(teamName);
        } else {
            return null;
        }
    }

    public void updateTeam(String teamName, String[] team) {
        if (teamExist(teamName)) {
            Teams.put(teamName, team);
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("Teams.txt"));
                loadTeams();
                for(Map.Entry e : Teams.entrySet()){
                    String teamToWrite = e.getKey() + ":";
                    String[] tmp = (String[]) e.getValue();
                    for(int i = 0; i < tmp.length;i++){
                        if(i != tmp.length -1){
                            teamToWrite = teamToWrite + tmp[i] + ",";
                        }else{
                            teamToWrite = teamToWrite + tmp[i];
                        }
                    }
                     bufferedWriter.write(teamToWrite);
                     bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Es gibt kein Team mit diesem Namen");
        }

    }

    public void saveNewTeam(String teamName, String[] team) {
        if (!teamExist(teamName)) {
            Teams.put(teamName, team);
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("Teams.txt"));
                loadTeams();
                for(Map.Entry e : Teams.entrySet()){
                    String teamToWrite = e.getKey() + ":";
                    String[] tmp = (String[]) e.getValue();
                    for(int i = 0; i < tmp.length;i++){
                        if(i != tmp.length -1){
                            teamToWrite = teamToWrite + tmp[i] + ",";
                        }else{
                            teamToWrite = teamToWrite + tmp[i];
                        }
                    }
                     bufferedWriter.write(teamToWrite);
                     bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Diese Team gibt es Bereits!");
        }

    }
    public static void main(String...args){
        TeamLoader2 tm = new TeamLoader2();
        Map<String,String[]> tmp =tm.getAllTeams();
        for(Map.Entry e : tmp.entrySet()){
            System.out.println(e.getKey() + "=" + e.getValue().toString());
        }
        tm.saveNewTeam("Team1", new String[]{"Hallo","Test"});
        tmp =tm.getAllTeams();
        for(Map.Entry e : tmp.entrySet()){
            System.out.println(e.getKey() + "=" + e.getValue().toString());
        }
        tm.updateTeam("Team1", new String[]{"Hallo","Test","Kekse"});
    }
}
