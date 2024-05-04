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
import java.util.HashMap;

public class StatusReportController {

    @FXML
    private ObservableList<String> cityList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cityChoiceBox;

    private int id_city;

    private List<String> estacoes_ids;

    private HashMap<String, String> metricasCadastradasDB = new HashMap<String, String>();

    
    @FXML
    private void initialize() throws SQLException {
        String sql = "SELECT * FROM cidade";
        ResultSet cityResult = helpDB(sql);
        if (cityResult == null) {
            return;
        }
        while (cityResult.next()) {
            String cityName = cityResult.getString("nome_cidade");
            cityList.add(cityName);
        }
        cityChoiceBox.setItems(cityList);
        ResultSet metricasCadastradas = helpDB("SELECT * FROM metrica");
        if (metricasCadastradas != null) {
            while (metricasCadastradas.next()) {
                metricasCadastradasDB.put(metricasCadastradas.getString("id_metrica"), metricasCadastradas.getString("nome_metrica"));
            }
        }
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
        ResultSet cityInformation = helpDB(sql);
        if(cityInformation.next()){
            id_city = cityInformation.getInt("id_cidade");
        }
        estacoes_ids = getEstacoesIds();
        if (estacoes_ids == null) {
            estacoes_ids = new ArrayList<>();
        }
        ResultSet mediaResult = getMediaResultsFromIdList();

        while (mediaResult.next()) {
            System.out.println(mediaResult.getString("valor"));
            
        }
    };

    private List<String> getEstacoesIds() throws SQLException {
        String sql = "SELECT * FROM estacao WHERE id_cidade = '" + id_city + "'";
        ResultSet resultEstacoes = helpDB(sql);
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

    private ResultSet getMediaResultsFromIdList() {
        String ids = estacoes_ids.toString().replace("[", "(").replace("]", ")");
        String sql = "SELECT id_metrica, AVG(valor) AS media FROM registro WHERE id_estacao IN" + ids + " GROUP BY id_metrica";
        ResultSet resultResults = helpDB(sql);
        return resultResults;
    }

    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }
}
