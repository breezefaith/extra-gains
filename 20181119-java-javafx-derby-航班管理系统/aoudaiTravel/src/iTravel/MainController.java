/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iTravel;

import iTravel.adapter.EmployeeProfileAdapter;
import iTravel.adapter.FlightAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Abdelkader
 */
public class MainController implements Initializable {
    private Connection conn;

    @FXML
    private MenuBar mainMenu;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:FlightDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            controller.setAlertText(msg);
            stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    @FXML
    public void addFlight(ActionEvent actionEvent) throws IOException {
        FlightAdapter flightAdapter = new FlightAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFlight.fxml"));
        Parent addFlight = (Parent) fxmlLoader.load();
        AddFlightController addFlightController = (AddFlightController) fxmlLoader.getController();
        addFlightController.setFlightAdapter(flightAdapter);

        Scene scene = new Scene(addFlight);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Add New Flight");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }


    public void removeFlight(ActionEvent actionEvent) throws IOException {
        FlightAdapter flightAdapter = new FlightAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveFlight.fxml"));
        Parent removeFlight = (Parent) fxmlLoader.load();
        RemoveFlightController removeFlightController = (RemoveFlightController) fxmlLoader.getController();
        removeFlightController.setFlightAdapter(flightAdapter);

        Scene scene = new Scene(removeFlight);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Remove A Flight");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    public void addProfile(ActionEvent actionEvent) throws IOException {
        EmployeeProfileAdapter profileAdapter = new EmployeeProfileAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProfile.fxml"));
        Parent removeProfile = (Parent) fxmlLoader.load();
        AddProfileController addProfileController = (AddProfileController) fxmlLoader.getController();
        addProfileController.setProfileAdapter(profileAdapter);

        Scene scene = new Scene(removeProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Add New Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    public void removeProfile(ActionEvent actionEvent) throws IOException {
        EmployeeProfileAdapter profileAdapter = new EmployeeProfileAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveProfile.fxml"));
        Parent addProfile = (Parent) fxmlLoader.load();
        RemoveProfileController removeFlightController = (RemoveProfileController) fxmlLoader.getController();
        removeFlightController.setProfileAdapter(profileAdapter);

        Scene scene = new Scene(addProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Remove A Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    public void modifyProfile(ActionEvent actionEvent) throws IOException {
        EmployeeProfileAdapter profileAdapter = new EmployeeProfileAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyProfile.fxml"));
        Parent modifyProfile = (Parent) fxmlLoader.load();
        ModifyProfileController modifyFlightController = (ModifyProfileController) fxmlLoader.getController();
        modifyFlightController.setProfileAdapter(profileAdapter);

        Scene scene = new Scene(modifyProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Change A Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }
}

