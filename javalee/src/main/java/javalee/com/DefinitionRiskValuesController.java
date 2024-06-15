package javalee.com;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.ValorRisco;
import javalee.com.entities.ValoresRisco;
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
    private CheckBox ckHabilitaMaximo;

    @FXML
    private CheckBox ckHabilitaMinimo;

    @FXML
    private ObservableList<String> metricList = FXCollections.observableArrayList();

    @FXML
    private TextField txtRiskMax;

    @FXML
    private ComboBox<String> cbDataType;

    ObservableList<String> rowsTypeMeasurement = FXCollections.observableArrayList(
        "Chuva",
        "Direção do Vento",
        "Direção do Vento",
        "Insolação",
        "Nebulosidade",
        "Ponto de Orvalho Instantâneo",
        "Ponto de Orvalho Máximo",
        "Ponto de Orvalho Mínimo",
        "Pressão",
        "Pressão Máxima",
        "Pressão Mínima",
        "Rajada de Vento",
        "Radiação Solar",
        "Temperatura",
        "Temperatura Máxima",
        "Temperatura Mínima",
        "Umidade",
        "Umidade Relativa Máxima",
        "Umidade Relativa Mínima",
        "Velocidade do Vento",
        "Velocidade do Vento"
    );

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
        cbDataType.setItems(rowsTypeMeasurement);
        buscaValoresRisco("Temperatura");
    }

    @FXML
    void saveData(ActionEvent event) {
        String selectedMetric = cbDataType.getValue();

        String maxInputValue = txtRiskMax.getText();
        String minInputValue = txtRiskMin.getText();
      
        if (
            ( ( maxInputValue.isEmpty() || !utilInterno.isNumeric(maxInputValue) ) && ckHabilitaMaximo.isSelected()) ||
            ( ( minInputValue.isEmpty() || !utilInterno.isNumeric(maxInputValue) ) && ckHabilitaMinimo.isSelected())
        )
        {
          utilInterno.alertError("Preencha os valores máximo e mínimo", "Erro");
          return;
        }
        else
        {
            String maxStringValue = "NULL";
            String minStringValue = "NULL";
      
            InsertMaxMinValues value = new InsertMaxMinValues();
            if(ckHabilitaMaximo.isSelected())
            {
                maxStringValue = "'" + Double.toString(Double.parseDouble(maxInputValue)) + "'";
            }
            if(ckHabilitaMinimo.isSelected())
            {
                minStringValue = "'" + Double.toString(Double.parseDouble(minInputValue)) + "'";
            }

            value.updateMaxValue(selectedMetric, maxStringValue);
            value.updateMinValue(selectedMetric, minStringValue);

            utilInterno.alertSucesso("Valores de risco salvos com sucesso", "Sucesso");
        }
      }
    

    @FXML
    void edit(ActionEvent event) {
        saveRiskValues.setDisable(false);
        saveRiskValues.setText("Salvar");
    }

    @FXML
    void selecionaMetrica(ActionEvent event) {
        String selectedMetric = cbDataType.getSelectionModel().getSelectedItem();
        buscaValoresRisco(selectedMetric);
    }

    @FXML
    void habilitaMaximo(ActionEvent event) {
        if (ckHabilitaMaximo.isSelected()) {
            txtRiskMax.setDisable(false);
            txtRiskMax.setText("");
        } else {
            txtRiskMax.setDisable(true);
            txtRiskMax.setText("");
        }
    }

    @FXML
    void habilitaMinimo(ActionEvent event) {
        if (ckHabilitaMinimo.isSelected()) {
            txtRiskMin.setDisable(false);
            txtRiskMin.setText("");
        } else {
            txtRiskMin.setDisable(true);
            txtRiskMin.setText("");
        }
    }

    private void buscaValoresRisco( String metrica)
    {
        ValoresRisco valoresRisco = new ValoresRisco();
        ValorRisco valorRisco = valoresRisco.buscaValoreRiscoMetrica(metrica);

        if(valorRisco.getValorMaximo() == null )
        {
            ckHabilitaMaximo.setSelected(false);
            txtRiskMax.setDisable(true);
            txtRiskMax.setText("");
        }
        else
        {
            ckHabilitaMaximo.setSelected(true);
            txtRiskMax.setDisable(false);
            txtRiskMax.setText(String.valueOf(valorRisco.getValorMaximo()).replace(".", ","));
        }

        if(valorRisco.getValorMinimo() == null )
        {
            ckHabilitaMinimo.setSelected(false);
            txtRiskMin.setDisable(true);
            txtRiskMin.setText("");
        }
        else
        {
            ckHabilitaMinimo.setSelected(true);
            txtRiskMin.setDisable(false);
            txtRiskMin.setText(String.valueOf(valorRisco.getValorMinimo()).replace(".", ","));
        }
    }
}