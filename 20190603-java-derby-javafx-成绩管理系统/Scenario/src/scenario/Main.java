package scenario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenario.controller.MainController;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            URL location=getClass().getResource("fxml/Main.fxml");
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();

            primaryStage.setTitle("Scenario");
            primaryStage.setScene(new Scene(root));
            primaryStage.setFullScreen(false);
            primaryStage.setMaximized(true);

            MainController mainController=fxmlLoader.getController();
            mainController.initial(primaryStage);

            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
