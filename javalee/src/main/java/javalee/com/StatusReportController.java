package javalee.com;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javalee.com.bd_connection.DbConnection;
import javafx.event.ActionEvent;
import java.util.HashMap;

public class StatusReportController {

    private static Scene scene;

    @FXML
    private ObservableList<String> cityList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cityChoiceBox;

    private String citySelected;

    private List<String> estacoes_ids = new ArrayList<>();;

    private HashMap<String, HashMap<String, String>> mediasResults = new HashMap<String, HashMap<String, String>>();

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
                metricasCadastradasDB.put(metricasCadastradas.getString("id_metrica"), metricasCadastradas.getString("nome"));
            }
        }
    }

    @FXML
    private void reportGenerate(ActionEvent event) throws SQLException {
        citySelected = cityChoiceBox.getValue();
        if (citySelected == null) {
            return;
        }
        reportGenerateMediaDate();
        if (mediasResults.isEmpty()) {
            return;
        }


        System.out.println("Gerar relatório");
                
    };

    private void reportGenerateMediaDate() throws SQLException{
        getEstacoesIds();
        getMediaResultsFromIdList();
    }

    private void getEstacoesIds() throws SQLException {
        String sql = "SELECT e.id_estacao FROM cidade c JOIN estacao e ON c.id_cidade = e.id_cidade WHERE c.nome_cidade = '" + citySelected + "'";
        ResultSet resultEstacoes = helpDB(sql);
        if (resultEstacoes == null) {
            return;
        }
        while (resultEstacoes.next()) {
            String estation_id = resultEstacoes.getString("id_estacao");
            estacoes_ids.add(estation_id);
        }
    };

    private void getMediaResultsFromIdList() throws SQLException{
        String ids = estacoes_ids.toString().replace("[", "(").replace("]", ")");
        String sql = "SELECT m.nome, r.id_metrica, AVG(valor) AS media FROM registro r JOIN metrica m ON r.id_metrica = m.id_metrica WHERE id_estacao IN" + ids + " GROUP BY r.id_metrica, m.nome";
        ResultSet resultResults = helpDB(sql);
        if (resultResults == null) {return;}
        while(resultResults.next()){
            String metrica_id = resultResults.getString("id_metrica");
            String metrica_nome = resultResults.getString("nome");
            String media = resultResults.getString("media");
        
            HashMap<String, String> innerMap = new HashMap<>();
            innerMap.put("metrica_nome", metrica_nome);
            innerMap.put("media", media);
        
            mediasResults.put(metrica_id, innerMap);
        }
    }

    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }

};
