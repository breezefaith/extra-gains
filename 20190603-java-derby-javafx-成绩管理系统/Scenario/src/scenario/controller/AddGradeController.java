package scenario.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scenario.dao.GradeDao;
import scenario.entity.Grade;
import scenario.entity.Student;
import scenario.jdbc.JDBCTool;
import scenario.util.AlertUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddGradeController implements Initializable {
    public Button btnAdd;
    public TextField textFieldCourse;
    public TextField textFieldTerm;
    public TextField textFieldMark;

    private Student student;
    private GradeDao gradeDao = JDBCTool.getGradeDao();
    private StudentContrlloer studentContrlloer;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudentContrlloer(StudentContrlloer studentContrlloer) {
        this.studentContrlloer = studentContrlloer;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clickBtnAdd(ActionEvent actionEvent) {
        Grade grade = new Grade();
        grade.setGid(UUID.randomUUID().toString());
        grade.setSid(student.getId());
        grade.setCourse(textFieldCourse.getText());
        grade.setTerm(textFieldTerm.getText());
        grade.setMark(Integer.valueOf(textFieldMark.getText()));

        try {
            if (gradeDao.add(grade) != 1) {
                AlertUtil.show(Alert.AlertType.WARNING, "insert failded!");
                return;
            }
            stage.close();
            studentContrlloer.refresh();

        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
    }
}
