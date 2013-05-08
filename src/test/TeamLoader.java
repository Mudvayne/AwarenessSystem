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
 * @author Flo
 */
public class TeamLoader {

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Map<String,String[]> Teams = new HashMap<>();

    public TeamLoader() {
       
    }
   
    public String[] getTeam(String teamName) throws Exception {
       
        if (teamExist(teamName)) {
            try {
                 bufferedReader = new BufferedReader(new FileReader("Teams.txt"));
                String[] names = null;
                String line = bufferedReader.readLine();
                while (line != null) {
                    if (line.contains(teamName)) {
                        String[] tmp = line.split(":");
                        names = tmp[1].split(",");

                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                return names;
            } catch (IOException ex) {
                Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            throw new Exception("Es gibt kein Team mit diesem Namen");
        }
        return null;
    }

    public void saveTeam(String teamName, String[] teamMembers) {
        if (teamExist(teamName)) {
            System.out.println("Dieses Team mit diesem Namen Existiert bereits!");
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("Teams.txt", true));
                String team = teamName + ":";
                for (int i = 0; i < teamMembers.length; i++) {
                    if (i == teamMembers.length - 1) {
                        team = team + teamMembers[i];
                    } else {
                        team = team + teamMembers[i] + ",";
                    }
                }

                bufferedWriter.write(team);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean teamExist(String teamName) {
        
        try {
            bufferedReader = new BufferedReader(new FileReader("Teams.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(teamName)) {
                    return true;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(TeamLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String... args) throws Exception {
        TeamLoader tm = new TeamLoader();
        tm.saveTeam("Team2", new String[]{"hallo1@gmail.de", "Test1@gmail.com"});
        String[] tmp = tm.getTeam("Team2");
        System.out.println(tmp.toString());
               
    }
}
