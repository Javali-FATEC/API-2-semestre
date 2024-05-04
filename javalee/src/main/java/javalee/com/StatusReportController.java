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

    private int id_city;

    
    @FXML
    private void initialize() throws SQLException {
        String sql = "SELECT * FROM cidade";
        String typeReturn = "executeWithReturn";
        ResultSet cityResult = helpDB(sql, typeReturn);
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
    private void reportGenerate(ActionEvent event) throws SQLException {
        System.out.println("Gerar relatório");
        String citySelected = cityChoiceBox.getValue();
        System.out.println(citySelected);
        if (citySelected == null) {
            return;
        }
        String sql = "SELECT * FROM cidade WHERE nome_cidade = '" + citySelected + "'";
        String typeReturn = "executeWithReturn";
        ResultSet cityInformation = helpDB(sql, typeReturn);
        if(cityInformation.next()){
            id_city = cityInformation.getInt("id_cidade");
        }
        List<String> estacoes_ids = getEstacoesIds();
        if (estacoes_ids == null) {
            return;
        }
        getResultsFromIdList(estacoes_ids);{

        }

    };

    private List<String> getEstacoesIds() throws SQLException {
        String query_estacao = "SELECT * FROM estacao WHERE id_cidade = '" + id_city + "'";
        String typeReturn = "executeWithReturn";
        ResultSet resultEstacoes = helpDB(query_estacao, typeReturn);
        List<String> ids_estations = new ArrayList<String>();
    
        if (resultEstacoes == null) {
            return ids_estations;
        }
        while (resultEstacoes.next()) {
            String estation_id = resultEstacoes.getString("id_estacao");
            ids_estations.add(estation_id);

        }
        return ids_estations;
    };

    private ResultSet helpDB(String query, String typeReturn) {
        DbConnection db = new DbConnection();
        if (typeReturn == "executeWithReturn") {
            ResultSet resultSet = db.executeWithReturn(query);

        }
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }
}
