/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mudvayne
 */
public class CalendarView {
    
    private Scene scene;
    private Parent parent;
    
    public CalendarView() throws IOException
    {
        parent = FXMLLoader.load(getClass().getResource("/view/CalendarView.fxml"));
        this.scene = new Scene(parent);
    }
    
    public void show(Stage stage)
    {
        stage.setTitle("AxxG - JavaFX MVC Beispiel");
        stage.setScene(scene);
        stage.show();
    }
}
