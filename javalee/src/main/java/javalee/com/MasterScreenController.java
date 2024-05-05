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
  private void openStatusReport2(ActionEvent event) {
    try {
      App.openStatusReportByDate();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
