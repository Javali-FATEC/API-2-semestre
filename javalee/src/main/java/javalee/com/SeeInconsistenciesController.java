package javalee.com;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Map;

public class SeeInconsistenciesController implements Initializable{

    private Map<String, String> lineErrors;
  

    @FXML
    private TableView<String[]> tableLineErrors;

    @FXML
    private Label typeMeasurementLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setLineErrors(Map<String, String> lineErrors) {
        this.lineErrors = lineErrors;
        listaDados();
    }

    private void listaDados(){
        tableLineErrors.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableLineErrors.getItems().clear();
        tableLineErrors.getColumns().clear();

        if(tableLineErrors.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Linha");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Risco");

            coluna1.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[0]);
            });
            coluna2.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[1]);
            });
            
            coluna1.setPrefWidth(50);
            coluna1.setMaxWidth(500);

            tableLineErrors.getColumns().addAll(coluna1, coluna2);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        for (Map.Entry<String, String> entry : lineErrors.entrySet()) {
            String[] rowData = new String[]{entry.getKey(),entry.getValue()};
            dataTable.add(rowData);
        }

        tableLineErrors.setItems(dataTable);
    }

}

