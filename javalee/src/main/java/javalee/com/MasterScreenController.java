package javalee.com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MasterScreenController {

  @FXML
  private void openScreenImportCSV(ActionEvent event) {
    try {
      App.openImportCSV("analysisInterface");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void openScreenStatusReport(ActionEvent event) {
    try {
      App.openStatusReport();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void openScreenBoxPlotReport(ActionEvent event) {
    try {
      App.openBoxPlotReport();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void openStatusReport2(ActionEvent event) {
    try {
      App.openStatusReportByDate();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openDefinitionRiskValues(ActionEvent event) {
    try {
      App.openDefinitionRiskValues();
    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  @FXML
  void openManageStations(ActionEvent event){
    try{
      App.openManageStations();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

}
