/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * test
 * @author Mudvayne
 */
public class AwarenessSystem extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Team Awareness System");
        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        try {
                // Load the root layout from the fxml file
                FXMLLoader loader = new FXMLLoader(AwarenessSystem.class.getResource("/view/RootLayout.fxml"));
                
                rootLayout = (BorderPane) loader.load();
                Scene scene = new Scene(rootLayout);
                primaryStage.setScene(scene);

                primaryStage.show();
        } catch (IOException e) {
                // Exception gets thrown if the fxml file could not be loaded
                e.printStackTrace();
        }

        showCalendarView();
    }
    
    public void showCalendarView() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(AwarenessSystem.class.getResource("/view/CalendarView.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);

            // Give the controller access to the main app
            CalendarViewController controller = loader.getController();
            controller.setMainApp(this);
        } 
        catch (IOException e) {
                // Exception gets thrown if the fxml file could not be loaded
                e.printStackTrace();
        }
    }
    
    public boolean showFilterView() throws Exception {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(AwarenessSystem.class.getResource("/view/FilterView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Filters");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller
            FilterViewController controller = loader.getController();
            controller.setMainApp(this);

            dialogStage.showAndWait();
            
            return controller.isOkClicked();

        } 
        catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}