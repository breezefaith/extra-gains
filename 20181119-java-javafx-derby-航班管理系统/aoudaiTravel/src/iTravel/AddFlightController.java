package iTravel;

import iTravel.adapter.FlightAdapter;
import iTravel.entity.Flight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFlightController implements Initializable {

    private FlightAdapter flightAdapter;

    @FXML
    private Button btnAdd;
    @FXML
    private TextField textfieldFlightNumber;
    @FXML
    private TextField textfieldOrigin;
    @FXML
    private TextField textfieldBusinessSeats;
    @FXML
    private TextField textfieldDestination;
    @FXML
    private TextField textfieldExecutiveSeats;
    @FXML
    private TextField textfieldBusinessFare;
    @FXML
    private TextField textfieldExecutiveFare;
    @FXML
    private TextField textfieldAircraftType;


    public void setFlightAdapter(FlightAdapter flightAdapter) {
        this.flightAdapter = flightAdapter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addFlight(ActionEvent actionEvent) {
        try {
            Flight flight = new Flight();
            flight.setFlightNumber(textfieldFlightNumber.getText());
            flight.setOrigin(textfieldOrigin.getText());
            flight.setDestination(textfieldDestination.getText());
            flight.setBusinessSeats(Integer.valueOf(textfieldBusinessSeats.getText()));
            flight.setExecutiveSeats(Integer.valueOf(textfieldExecutiveSeats.getText()));
            flight.setBusinessFare(Double.valueOf(textfieldBusinessFare.getText()));
            flight.setExecutiveFare(Double.valueOf(textfieldExecutiveFare.getText()));
            flight.setAircraftType(textfieldAircraftType.getText());

            if(flightAdapter.addFlight(flight)){
                displayAlert("Add flight success!");
            }
            Stage stage=(Stage) btnAdd.getScene().getWindow();
            stage.close();

        }catch (Exception e){
            displayAlert(e.getMessage());
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

            stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
    }
}
