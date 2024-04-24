package javalee.com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MasterScreenController {
  
   @FXML
    private void openScreenImportCSV(ActionEvent event) {
      try {
        App.openImportCSV("analysisInterface");
      } catch (IOException e){
        e.printStackTrace();
      }
    }
}
