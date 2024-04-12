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
    private void handleSelectFile(ActionEvent event){
        System.out.println();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo CSV", "*.csv"));
        File selecteFile = fileChooser.showOpenDialog(null);

        try{
            if(selecteFile == null){
                throw new FileException();
            }
            
            ExtractDataFileName dataFileName = new ExtractDataFileName(selecteFile.getName());

            BufferedReader reader = new BufferedReader(new FileReader(selecteFile));
            String line;
            int lineNumber = 0;
            LinkedList<DataMeasurement> listDataFile = new LinkedList<DataMeasurement>();
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                line = line.replace(";;", "; ;");
                String[] parts = line.split(";");
                if(separateChoiceBox.getValue()=="virgula ( , )")
                {
                    line = line.replace(",,", ", ,");
                    parts = line.split(";");
                }
                if(typeCsvChoiceBox.getValue() == "Padrão A") {
                    if(lineNumber>0)
                    {
                        DataMeasurement temperatura = new DataMeasurement("Temperatura","ºC",parts[0],parts[1],parts[2]);
                        temperatura.convertTemp();
                        listDataFile.add(temperatura);
                        listDataFile.add(new DataMeasurement("Umidade", "Umidade Relativa (%)",parts[0],parts[1], parts[3]));
                        listDataFile.add(new DataMeasurement("Pressão", "hPa",parts[0],parts[1], parts[4]));
                        listDataFile.add(new DataMeasurement("Velocidade do Vento", "m/s",parts[0],parts[1], parts[5]));
                        listDataFile.add(new DataMeasurement("Direção do Vento", "m/s",parts[0],parts[1], parts[6]));
                        listDataFile.add(new DataMeasurement("Nebulosidade", "Decimos",parts[0],parts[1], parts[7]));
                        listDataFile.add(new DataMeasurement("Insolação", "h",parts[0],parts[1], parts[8]));
                        listDataFile.add(new DataMeasurement("Temperatura Máxima", "ºC",parts[0],parts[1], parts[9]));
                        listDataFile.add(new DataMeasurement("Temperatura Mínima", "ºC",parts[0],parts[1], parts[10]));
                        String lastValue = "";
                        boolean lastValueIsPresent = parts.length > 11;
                        if(lastValueIsPresent) {
                            lastValue = parts[11];
                        }
                        listDataFile.add(new DataMeasurement("Chuva", "mm",parts[0],parts[1], lastValue));
                    }
                }
            }

            DataFile dataFile = new DataFile(dataFileName.getCity(),dataFileName.getStation(),listDataFile);

            openNewWindow(event, dataFile);
        }
        catch(FileException e){
            System.out.println(e.getMessage());
        }
        catch(ExceptionInvalidFileName e){
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
