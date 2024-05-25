package javalee.com;

import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

public class BoxPlotController {
    
  @FXML
  private void openScreenBoxPlotReportData(ActionEvent event) {
    try {
      App.openBoxPlotReportData();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
