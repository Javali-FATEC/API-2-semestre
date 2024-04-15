package javalee.com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    @FXML
    private Button cityNameLabel;

    private DataFile dataFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @FXML
    private void previweData(ActionEvent event){
        System.out.println(this.dataFile.getIdEstacao());
        try{
            App.openPreviewData("preview-data",this.dataFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void seeInconsistencies(ActionEvent event){
        System.out.println(this.dataFile.getIdEstacao());
        try{
            App.openSeeInconsistencies("see-inconsistencies",this.dataFile.getLineErros());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

