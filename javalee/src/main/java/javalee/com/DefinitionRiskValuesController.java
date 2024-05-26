package javalee.com;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javalee.com.bd_connection.DbConnection;
import javalee.com.services.InsertMaxMinValues;
import javalee.com.services.utilInterno;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

public class DefinitionRiskValuesController {

    @FXML
    private Button saveRiskValues;

    @FXML
    private Button editRiskValue;

    @FXML
    private ObservableList<String> metricList = FXCollections.observableArrayList();

    @FXML
    private TextField txtRiskMax;

    @FXML
    private ComboBox<String> cbDataType;

    @FXML
    private TextField txtRiskMin;

    @FXML
    private List<String> metrics_ids = new ArrayList<>();

    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }

    @FXML
    private void initialize() throws SQLException {

        String sql = "SELECT * FROM metrica";
        ResultSet metricResultSet = helpDB(sql);
        while (metricResultSet.next()) {

            String metricName = metricResultSet.getString("nome");

            metricList.add(metricName);
        }
        cbDataType.setItems(metricList);

        cbDataType.setItems(metricList);

        cbDataType.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        txtRiskMax.clear();
                        txtRiskMin.clear();
                        saveRiskValues.setDisable(false);
                        saveRiskValues.setText("Salvar");

                    }
                }
        );

    }

    @FXML
    void saveData(ActionEvent event) {

        String selectedMetric = cbDataType.getSelectionModel().getSelectedItem();
        String maxInputValue = txtRiskMax.getText();
        String minInputValue = txtRiskMin.getText();
      
        if (maxInputValue.isEmpty() || minInputValue.isEmpty()) {
          utilInterno.alertError("Preencha os valores máximo e mínimo", "Erro");
          return;
        }
    
        String maxStringValue = Double.toString(Double.parseDouble(maxInputValue));
        String minStringValue = Double.toString(Double.parseDouble(minInputValue));
      
        InsertMaxMinValues value = new InsertMaxMinValues();
        value.updateMaxValue(selectedMetric, maxStringValue);
        value.updateMinValue(selectedMetric, minStringValue);
        saveRiskValues.setDisable(true);
        saveRiskValues.setText("Sucesso!");
      }
    

    @FXML
    void edit(ActionEvent event) {
        saveRiskValues.setDisable(false);
        saveRiskValues.setText("Salvar");

    }
}