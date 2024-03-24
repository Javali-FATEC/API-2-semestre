package javalee.com;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ImportCSVController {
    ObservableList<String> separateRowsCSV = FXCollections.observableArrayList("virgula ( , )", "ponto e virgula ( ; )"); 

    @FXML
    private ChoiceBox<String> separateChoiceBox;

    @FXML
    private void initialize(){
        separateChoiceBox.setItems(separateRowsCSV);
    }
}
