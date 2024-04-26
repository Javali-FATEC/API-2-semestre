package javalee.com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javalee.com.services.DataFile;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    @FXML
    private Button cityNameLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label stationLabel;

    private DataFile dataFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
        // cityLabel.setText("Cidade: " + this.dataFile.getCity());
        cityLabel.setText(this.dataFile.getCity());
        stationLabel.setText(this.dataFile.getStation());
    }

    @FXML
    private void previweData(ActionEvent event){
        try{
            App.openPreviewData("preview-data",this.dataFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void seeInconsistencies(ActionEvent event){
        try{
            App.openSeeInconsistencies("see-inconsistencies",this.dataFile.getLineErros());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}