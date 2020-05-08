package scenario.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scenario.Main;
import scenario.dao.GradeDao;
import scenario.entity.Grade;
import scenario.entity.Person;
import scenario.entity.Student;
import scenario.jdbc.JDBCTool;
import scenario.util.AlertUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StudentContrlloer implements Initializable {

    public TextField textFieldId;
    public TextField textFieldName;
    public TextField textFieldSex;
    public TextField textFieldAge;
    public TextField textFieldRole;
    public TextField textFieldLocation;
    public Button btnAdd;
    public Button btnDelete;
    public TableView<Grade> tableViewGrade;
    public TableColumn tableColumnGid;
    public TableColumn tableColumnCourse;
    public TableColumn tableColumnMark;
    public TableColumn tableColumnTerm;

    private ObservableList<Grade> observableList;
    private Student student;
    private List<Grade> grades;
    private GradeDao gradeDao = JDBCTool.getGradeDao();

    public StudentContrlloer() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initial(Person person) {
        student = new Student(person);

        textFieldId.setText(String.valueOf(student.getId()));
        textFieldAge.setText(String.valueOf(student.getAge()));
        textFieldLocation.setText(String.valueOf(student.getLocation()));
        textFieldName.setText(String.valueOf(student.getName()));
        textFieldRole.setText(String.valueOf(student.getRole()));
        textFieldSex.setText(String.valueOf(student.getSex()));

        try {
            grades = gradeDao.findByStudent(student);
            student.setGrades(grades);
            observableList = FXCollections.observableList(grades);

            tableColumnGid.setCellValueFactory(new PropertyValueFactory<>("gid"));
            tableColumnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
            tableColumnMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
            tableColumnTerm.setCellValueFactory(new PropertyValueFactory<>("term"));

            tableViewGrade.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void clickBtnAdd(ActionEvent actionEvent) {
        URL location = Main.class.getResource("fxml/AddGrade.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            BorderPane borderPane = fxmlLoader.load();
            AddGradeController addGradeController = fxmlLoader.getController();

            addGradeController.setStudent(student);
            addGradeController.setStudentContrlloer(this);
            Stage stage = new Stage();
            stage.setTitle("Add Grade");
            stage.setScene(new Scene(borderPane));
            stage.setResizable(false);
            addGradeController.setStage(stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickBtnDelete(ActionEvent actionEvent) {
        Grade selectItem = tableViewGrade.getSelectionModel().getSelectedItem();
        try {
            if (gradeDao.delete(selectItem) != 1) {
                AlertUtil.show(Alert.AlertType.WARNING, "delete failed.");
                return;
            }
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void refresh() {
        try {
            grades = gradeDao.findByStudent(student);
            student.setGrades(grades);
            observableList = FXCollections.observableList(grades);
            tableViewGrade.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }

    }
}
