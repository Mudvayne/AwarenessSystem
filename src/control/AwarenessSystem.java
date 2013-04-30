/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * test
 * @author Mudvayne
 */
public class AwarenessSystem extends Application {
    
    
    protected static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("CalendarView.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("FilterView.fxml"));
        
        stage.setScene(new Scene(root));
        stage.setTitle("Team Awareness System");
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}