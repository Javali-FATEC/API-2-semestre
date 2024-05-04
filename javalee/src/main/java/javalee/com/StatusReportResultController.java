package javalee.com;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class StatusReportResultController implements Initializable{

    @FXML
    private String cityReportTitle;

    @FXML
    private TableView<String[]> tableDataSituationCity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void setData(HashMap<String, String> mediasResults) {
        listDates(mediasResults);
    }

    private void listDates(HashMap<String, String> mediasResults) {
        tableDataSituationCity.getItems().clear();
        tableDataSituationCity.getColumns().clear();

        if(tableDataSituationCity.getColumns().isEmpty()) {
            TableColumn<String[], String> column1 = new TableColumn<>("Métrica");
            TableColumn<String[], String> column2 = new TableColumn<>("Média");

            column1.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[0]);
            });
            column2.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[1]);
            });

            tableDataSituationCity.getColumns().addAll(column1, column2);
            mediasResults.forEach((key, value) -> {
                tableDataSituationCity.getItems().add(new String[]{key, value});
            });

        }

    }


    
}