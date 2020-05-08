package scenario.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void show(Alert.AlertType type, String message) {
        Alert information = new Alert(type, message);
        information.showAndWait();
    }
}
