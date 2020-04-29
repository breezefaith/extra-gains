package ems.view;

import ems.dao.EmployeeDao;
import ems.entity.Employee;
import ems.util.AlertUtil;
import ems.util.JDBCUtil;
import ems.util.ResourcesUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProfileController implements Initializable {
    public Button btnCancel;
    public Button btnSave;
    public Label labelTitle;
    public TextField textFieldEid;
    public TextField textFieldName;
    public ComboBox<String> comboBoxCountry;
    public ComboBox<String> comboBoxProvince;
    public ComboBox<String> comboBoxCity;
    public TextField textFieldPostalCode;
    public TextField textFieldStreetAddr;

    private EmployeeDao employeeDao;
    private MainController mainController;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeDao = JDBCUtil.getEmployeeDao();

        try {
            comboBoxCountry.setItems(FXCollections.observableList(ResourcesUtil.getCoutriesList()));
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }

        comboBoxCountry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue == null || oldValue.equals(newValue) == false) {
                    comboBoxProvince.getItems().clear();
                    comboBoxCity.getItems().clear();
                    textFieldPostalCode.setText("");
                    textFieldStreetAddr.setText("");

                    comboBoxCity.setDisable(true);
                    textFieldPostalCode.setDisable(true);
                    textFieldStreetAddr.setDisable(true);

                    comboBoxProvince.setDisable(false);
                    try {
                        comboBoxProvince.setItems(FXCollections.observableList(ResourcesUtil.getProvincesList(newValue)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
                    }
                    comboBoxProvince.getSelectionModel().clearSelection();
                }
            }
        });

        comboBoxProvince.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue == null || oldValue.equals(newValue) == false) {
                    comboBoxCity.getItems().clear();
                    textFieldPostalCode.setText("");
                    textFieldStreetAddr.setText("");

                    comboBoxCity.setDisable(false);
                    textFieldPostalCode.setDisable(true);
                    textFieldStreetAddr.setDisable(true);

                    try {
                        comboBoxCity.setItems(FXCollections.observableList(ResourcesUtil.getCityList(comboBoxCountry.getSelectionModel().getSelectedItem(), newValue)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
                    }
                    comboBoxCity.getSelectionModel().clearSelection();
                }
            }
        });

        comboBoxCity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    textFieldPostalCode.setText("");
                    textFieldStreetAddr.setText("");

                    textFieldPostalCode.setDisable(false);
                    textFieldStreetAddr.setDisable(false);
                }
            }
        });

        textFieldEid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (checkTextFieldValid()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            }
        });

        textFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (checkTextFieldValid()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            }
        });

        textFieldPostalCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (checkTextFieldValid()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            }
        });

        textFieldStreetAddr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (checkTextFieldValid()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            }
        });
    }

    private boolean checkTextFieldValid() {
        if ("".equals(textFieldEid.getText()) == false && "".equals(textFieldName.getText()) == false && "".equals(textFieldPostalCode.getText()) == false && "".equals(textFieldStreetAddr.getText()) == false) {
            return true;
        } else {
            return false;
        }
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        Employee employee = new Employee();
        employee.setEid(textFieldEid.getText());
        employee.setName(textFieldName.getText());
        employee.setCountry(comboBoxCountry.getSelectionModel().getSelectedItem());
        employee.setProvince(comboBoxProvince.getSelectionModel().getSelectedItem());
        employee.setCity(comboBoxCity.getSelectionModel().getSelectedItem());
        employee.setPostalCode(textFieldPostalCode.getText());
        employee.setStreetAddress(textFieldStreetAddr.getText());

        try {
            employeeDao.insert(employee);
            AlertUtil.show(Alert.AlertType.INFORMATION, "Added profile successfully!");
            this.stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, "Failed to add profile!");
        }
    }

    public void initial(MainController mainController, Stage stage) {
        this.mainController = mainController;
        this.stage = stage;
    }
}
