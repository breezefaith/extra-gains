package iTravel;

import iTravel.adapter.EmployeeProfileAdapter;
import iTravel.entity.EmployeeProfile;
import javafx.event.ActionEvent;
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

public class AddProfileController implements Initializable {

    private EmployeeProfileAdapter profileAdapter;

    @FXML
    public Button btnAdd;
    @FXML
    public TextField textfieldEmployeeNumber;
    @FXML
    public TextField textfieldEmployeeName;

    public void setProfileAdapter(EmployeeProfileAdapter profileAdapter) {
        this.profileAdapter = profileAdapter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addProfile(ActionEvent actionEvent) {
        try {
            EmployeeProfile profile=new EmployeeProfile();
            profile.setEmployeeNumber(textfieldEmployeeNumber.getText());
            profile.setEmployeeName(textfieldEmployeeName.getText());
            profileAdapter.addProfile(profile);
            ((Stage)btnAdd.getScene().getWindow()).close();
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
