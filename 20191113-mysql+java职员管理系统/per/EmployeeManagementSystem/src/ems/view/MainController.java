package ems.view;

import ems.Main;
import ems.dao.EmployeeDao;
import ems.entity.Employee;
import ems.util.AlertUtil;
import ems.util.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public MenuItem menuItemClose;
    public MenuItem menuItemAddProfile;
    public MenuItem menuItemModifyProfile;

    private Stage primaryStage;

    private EmployeeDao employeeDao;
    private List<Employee> employees;
    private ObservableList<Employee> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initial(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void close(ActionEvent actionEvent) {
        primaryStage.close();
    }

    public void addProfile(ActionEvent actionEvent) {
        URL location = Main.class.getResource("view/addProfile.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            BorderPane borderPane = fxmlLoader.load();
            AddProfileController addProfileController = fxmlLoader.getController();

            Stage stage = new Stage();

            stage.setTitle("Add New Employee Profile Data");
            stage.setScene(new Scene(borderPane));
            stage.setResizable(false);
            stage.show();

            addProfileController.initial(this, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyProfile(ActionEvent actionEvent) {
        URL location = Main.class.getResource("view/modifyProfile.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            BorderPane borderPane = fxmlLoader.load();
            ModifyProfileController modifyProfileController = fxmlLoader.getController();

            Stage stage = new Stage();

            stage.setTitle("Modify Employee Profile Data");
            stage.setScene(new Scene(borderPane));
            stage.setResizable(false);
            stage.show();

            modifyProfileController.initial(this, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
