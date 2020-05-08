package scenario.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scenario.Main;
import scenario.dao.GradeDao;
import scenario.dao.PersonDao;
import scenario.entity.Person;
import scenario.entity.Role;
import scenario.jdbc.JDBCTool;
import scenario.util.AlertUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView<Person> tableViewPerson;
    public TableColumn<Person, Integer> tableColumnId;
    public TableColumn<Person, String> tableColumnName;
    public TableColumn<Person, String> tableColumnSex;
    public TableColumn<Person, Integer> tableColumnAge;
    public TableColumn<Person, Role> tableColumnRole;
    public TableColumn<Person, String> tableColumnLocation;
    public Button btnView;
    public Button btnAdd;
    public Button btnDelete;

    private PersonDao personDao = JDBCTool.getPersonDao();
    private GradeDao gradeDao = JDBCTool.getGradeDao();
    private List<Person> persons;
    private ObservableList<Person> observableList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            persons = personDao.findAll();
            observableList = FXCollections.observableList(persons);
            tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableColumnSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
            tableColumnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            tableColumnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
            tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            tableViewPerson.setItems(observableList);

        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void initial(Stage primaryStage) {

    }

    public void clickBtnView(ActionEvent actionEvent) {
        Person selectItem = tableViewPerson.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            AlertUtil.show(Alert.AlertType.WARNING, "Please choose one row");
            return;
        }
        if ("student".equals(selectItem.getRole().getRoleName()) == false) {
            AlertUtil.show(Alert.AlertType.WARNING, "Please choose one student");
            return;
        }

        URL location = Main.class.getResource("fxml/Student.fxml");
        System.out.println(location.getPath());
        System.out.println(location.getFile());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            BorderPane borderPane = fxmlLoader.load();
            StudentContrlloer studentContrlloer = fxmlLoader.getController();

            studentContrlloer.initial(selectItem);
            Stage stage = new Stage();
            stage.setTitle("Student");
            stage.setScene(new Scene(borderPane));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickBtnAdd(ActionEvent actionEvent) {
        URL location = Main.class.getResource("fxml/AddPerson.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            BorderPane borderPane = fxmlLoader.load();
            AddPersonController addPersonController = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Add Person");
            stage.setScene(new Scene(borderPane));
            stage.setResizable(false);
            addPersonController.initial(this, stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickBtnDelete(ActionEvent actionEvent) {
        Person selectItem = tableViewPerson.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            AlertUtil.show(Alert.AlertType.WARNING, "Please choose one row");
            return;
        }
        try {
            if (personDao.delete(selectItem) != 1) {
                AlertUtil.show(Alert.AlertType.WARNING, "no such row");
                return;
            }
            if ("student".equals(selectItem.getRole().getRoleName())) {
                gradeDao.deleteBySid(selectItem.getId());
            }
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void refresh() {
        try {
            persons = personDao.findAll();
            observableList = FXCollections.observableList(persons);
            tableViewPerson.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }
}
