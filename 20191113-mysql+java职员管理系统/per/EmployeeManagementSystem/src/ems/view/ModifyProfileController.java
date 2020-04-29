package ems.view;

import ems.dao.EmployeeDao;
import ems.entity.Employee;
import ems.util.AlertUtil;
import ems.util.JDBCUtil;
import ems.util.ResourcesUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyProfileController implements Initializable {
    public Button btnDelete;
    public Button btnCancel;
    public Button btnSave;
    public Label labelTitle;
    public ComboBox<Employee> comboBoxEid;
    public ComboBox<String> comboBoxCountry;
    public TextField textFieldName;
    public ComboBox<String> comboBoxProvince;
    public ComboBox<String> comboBoxCity;
    public TextField textFieldPostalCode;
    public TextField textFieldStreetAddr;

    private ObservableList<Employee> observableList;
    private Employee employee;
    private MainController mainController;
    private Stage stage;

    private EmployeeDao employeeDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeDao = JDBCUtil.getEmployeeDao();

//        comboBoxEid.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {
//            @Override
//            public ListCell<Employee> call(ListView<Employee> param) {
//                return null;
//            }
//        });

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

        comboBoxEid.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
                if (oldValue != null && newValue != oldValue) {
                    loadItem(newValue);
                }
            }
        });
    }

    public void delete(ActionEvent actionEvent) {
        try {
            ObservableList<Employee> observableList = comboBoxEid.getItems();
            employeeDao.delete(employee.getEid());
            AlertUtil.show(Alert.AlertType.INFORMATION, "Delete profile successfully!");
            int index = observableList.indexOf(employee);
            int next = index;
            if (index == observableList.size() - 1) {
                next = 0;
            }
            observableList.remove(index);
            this.loadItem(observableList.get(next));
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, "Failed to delete profile!");
        }
    }

    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void save(ActionEvent actionEvent) {
        employee.setName(textFieldName.getText());
        employee.setCountry(comboBoxCountry.getSelectionModel().getSelectedItem());
        employee.setProvince(comboBoxProvince.getSelectionModel().getSelectedItem());
        employee.setCity(comboBoxCity.getSelectionModel().getSelectedItem());
        employee.setPostalCode(textFieldPostalCode.getText());
        employee.setStreetAddress(textFieldStreetAddr.getText());

        try {
            employeeDao.update(employee);
            AlertUtil.show(Alert.AlertType.INFORMATION, "Updated profile successfully!");
            this.loadItem(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, "Failed to update profile!");
        }
    }

    public void initial(MainController mainController, Stage stage) {
        try {
            this.observableList = FXCollections.observableList(employeeDao.findAll());
            this.comboBoxEid.setItems(observableList);
            this.employee = this.observableList.get(0);
            loadItem(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.show(Alert.AlertType.ERROR, e.getMessage());
        }
        this.mainController = mainController;
        this.stage = stage;
    }

    private boolean checkTextFieldValid() {
        if ("".equals(textFieldName.getText()) == false && "".equals(textFieldPostalCode.getText()) == false && "".equals(textFieldStreetAddr.getText()) == false) {
            return true;
        } else {
            return false;
        }
    }

    private void loadItem(Employee employee) {
        this.employee = employee;

        comboBoxEid.setValue(employee);
        textFieldName.setText(employee.getName());
        comboBoxCountry.getSelectionModel().select(employee.getCountry());
        comboBoxProvince.getSelectionModel().select(employee.getProvince());
        comboBoxCity.getSelectionModel().select(employee.getCity());
        textFieldPostalCode.setText(employee.getPostalCode());
        textFieldStreetAddr.setText(employee.getStreetAddress());

        btnSave.setDisable(true);
    }
}
