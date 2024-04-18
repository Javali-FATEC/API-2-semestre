package javalee.com.services;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class utilInterno {
    public static void alertError(String message, String title){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}
