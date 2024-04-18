package javalee.com;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javalee.com.exceptions.*;
import javalee.com.services.*;

public class ImportCSVController {
    ObservableList<String> separateRowsCSV = FXCollections.observableArrayList("virgula ( , )", "ponto e virgula ( ; )"); 
    ObservableList<String> typeOfCsv = FXCollections.observableArrayList("Automático","Padrão A", "Padrão B");

    @FXML
    private ChoiceBox<String> separateChoiceBox;

    @FXML
    private ChoiceBox<String> typeCsvChoiceBox;

    @FXML
    private void initialize(){
        separateChoiceBox.setItems(separateRowsCSV);
        separateChoiceBox.setValue("ponto e virgula ( ; )");
        typeCsvChoiceBox.setItems(typeOfCsv);
        typeCsvChoiceBox.setValue("Automático");
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
        try{
            DataFile dataFile = OpenCSV.OpenFileCSV(separateChoiceBox.getValue(),typeCsvChoiceBox.getValue());
            openNewWindow(event, dataFile);
        }
        catch(FileException e){
            utilInterno.alertError(e.getMessage(),"Erro no Arquivo");
        }
        catch(ExceptionInvalidFileName e){
            utilInterno.alertError(e.getMessage(),"Erro no Arquivo");
        }
        catch (IOException e) {
            utilInterno.alertError("Impossível interpretar CSV","Erro no Arquivo");
        }
    }
}
