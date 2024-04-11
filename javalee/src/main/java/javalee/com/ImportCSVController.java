package javalee.com;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;

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

    @FXML
    private void handleSelectFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo CSV", "*.csv"));
        File selecteFile = fileChooser.showOpenDialog(null);

        if (selecteFile != null){
            openNewWindow(event);
        }
    }
}
