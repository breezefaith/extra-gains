package ems;

import ems.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL location=getClass().getResource("view/main.fxml");
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();

            primaryStage.setTitle("Employee Profile Management System");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(true);
            primaryStage.setFullScreen(false);

            MainController mainController=fxmlLoader.getController();
            mainController.initial(primaryStage);

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
