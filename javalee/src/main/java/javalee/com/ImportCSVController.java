package javalee.com;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ImportCSVController {
    ObservableList<String> separateRowsCSV = FXCollections.observableArrayList(",", ";"); 

    @FXML
    private ChoiceBox<String> separateChoiceBox;

    @FXML
    private void initialize(){
        separateChoiceBox.setItems(separateRowsCSV);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
