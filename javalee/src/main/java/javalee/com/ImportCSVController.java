package javalee.com;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


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
    private void openNewWindow(ActionEvent event, DataFile dataFile){
        try{
            App.openWindowAnalysis("analysisInterface",dataFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void openToolTip(ActionEvent event){
        try{
            App.openWindowToolTip("open-tool-tip");
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

        try{
            if(selecteFile == null){
                throw new FileException();
            }
            ExtractDataFileName dataFileName = new ExtractDataFileName(selecteFile.getName());

            ListMeasurement listMeasurement = new ListMeasurement(new FileReader(selecteFile),typeCsvChoiceBox.getValue());
            listMeasurement.setInterator(separateChoiceBox.getValue());         

            DataFile dataFile = new DataFile(dataFileName.getCity(),dataFileName.getStation(),listMeasurement.getListDataFile(),listMeasurement.getLineErros());
            openNewWindow(event, dataFile);
        }
        catch(FileException e){
            utilInterno.alertError(e.getMessage());
        }
        catch(ExceptionInvalidFileName e){
            utilInterno.alertError(e.getMessage());
        }
        catch (IOException e) {
            utilInterno.alertError("Impossível interpretar CSV");
        }
    }
}
