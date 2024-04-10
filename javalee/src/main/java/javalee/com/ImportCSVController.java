package javalee.com;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ImportCSVController {
    ObservableList<String> separateRowsCSV = FXCollections.observableArrayList("virgula ( , )", "ponto e virgula ( ; )"); 
    ObservableList<String> typeOfCsv = FXCollections.observableArrayList("Padrão A", "Padrão B");

    @FXML
    private ChoiceBox<String> separateChoiceBox;

    @FXML
    private ChoiceBox<String> typeCsvChoiceBox;

    @FXML
    private void initialize(){
        separateChoiceBox.setItems(separateRowsCSV);
        typeCsvChoiceBox.setItems(typeOfCsv);
    }

    @FXML
    private void openNewWindow(ActionEvent event){
        try{
            App.openWindowAnalysis("analysisInterface");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
