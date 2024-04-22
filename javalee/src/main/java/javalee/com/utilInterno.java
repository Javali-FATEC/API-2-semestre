package javalee.com;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class utilInterno {
    public static void alertError(String mensagemErro){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Item selecionado");
        alert.setHeaderText(null);
        alert.setContentText(mensagemErro);
        alert.showAndWait();
    }
}
