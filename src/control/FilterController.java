/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TeamFilterModel;

/**
 *
 * @author Flo
 */
public class FilterController extends Thread{
  /*  
    
    private final TeamFilterModel teamFilterModel;
    
    public FilterController(TeamFilterModel teamFilterModel){
        this.teamFilterModel = teamFilterModel;
    }
    
     private void fillModel() {
         teamFilterModel.loadTeams();
         teamFilterModel.notifyObservers();
         System.out.println("FilterController: TeamFilterModel updated");
     }
     
     
      @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        
            while(teamFilterModel.isProgRunning()){
             //   fillModel();
                try {
              //    sleep(60000);
                } catch (InterruptedException ex) {
                       //do nothing
                }
            }
       
    }*/
}
