package scenario.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scenario.dao.PersonDao;
import scenario.dao.RoleDao;
import scenario.entity.Person;
import scenario.entity.Role;
import scenario.jdbc.JDBCTool;
import scenario.util.AlertUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddPersonController implements Initializable {
    public Button btnAdd;
    public TextField textFieldName;
    public TextField textFieldLocation;
    public ChoiceBox<String> choiceBoxSex;
    public ChoiceBox<Role> choiceBoxRole;
    public TextField textFieldAge;

    private ObservableList<String> observableListSex;
    private ObservableList<Role> observableListRole;

    private RoleDao roleDao = JDBCTool.getRoleDao();
    private PersonDao personDao = JDBCTool.getPersonDao();

    private List<Role> roles;

    private MainController mainController;
    private Stage stage;

    private List<String> sexs = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initial(MainController mainController, Stage stage) {
        try {
            this.mainController = mainController;
            this.stage = stage;
            roles = roleDao.findAll();
            observableListRole = FXCollections.observableList(roles);
            choiceBoxRole.setItems(observableListRole);

            sexs.add("male");
            sexs.add("female");
            observableListSex = FXCollections.observableList(sexs);
            choiceBoxSex.setItems(observableListSex);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    public void clickBtnAdd(ActionEvent actionEvent) {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName(textFieldName.getText());
        person.setSex(choiceBoxSex.getValue());
        person.setRole(choiceBoxRole.getValue());
        person.setAge(Integer.valueOf(textFieldAge.getText()));
        person.setLocation(textFieldLocation.getText());

        try {
            if (personDao.add(person) != 1) {
                AlertUtil.show(Alert.AlertType.WARNING, "add failed.");
                return;
            }
            stage.close();
            mainController.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }

    }
}
