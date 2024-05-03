package javalee.com;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javalee.com.bd_connection.DbConnection;
import javafx.event.ActionEvent;

public class StatusReportController {

    @FXML
    private ObservableList<String> cityList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cityChoiceBox;

    private String citySelected;

    
    @FXML
    private void initialize() throws SQLException {
        DbConnection db = new DbConnection();
        ResultSet cityResult = db.executeWithReturn("SELECT * FROM cidade");
        db.Desconnect();
        if (cityResult == null) {
            return;
        }
        while (cityResult.next()) {
            String cityName = cityResult.getString("nome_cidade");
            cityList.add(cityName);

        }
        cityChoiceBox.setItems(cityList);
        System.out.println(cityList);
    }


    @FXML
    private void generateReport(ActionEvent event) {
        DbConnection db = new DbConnection();

        String citySelected = cityChoiceBox.getValue();
        List<String> estacoes = getEstacoes();
        if (estacoes == null) {
            return;
        }
    };

    private List getEstacoes() throws SQLException {
        DbConnection db = new DbConnection();
        String query_estacao = "SELECT * FROM estacao WHERE nome_cidade = '" + citySelected + "'";
        List<String> ids_estations = new ArrayList<String>();
        ResultSet resultEstacoes = db.executeWithReturn(query_estacao);
        db.Desconnect();
    
        if (resultEstacoes == null) {
            return ids_estations;
        }
        while (resultEstacoes.next()) {
            String estation_id = resultEstacoes.getString("id_estacao");
            ids_estations.add(estation_id);

        }
        return ids_estations;
    };
}
