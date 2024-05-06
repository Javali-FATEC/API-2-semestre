package javalee.com;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javalee.com.bd_connection.DbConnection;
import javafx.event.ActionEvent;
import java.util.HashMap;

public class StatusReportController {

    @FXML
    private ObservableList<String> cityList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cityChoiceBox;

    @FXML
    private Text alerts;

    private Boolean status = false;

    private String citySelected;

    private List<String> stations_ids = new ArrayList<>();;

    private HashMap<String, String> averageResults = new HashMap<String, String>();

    @FXML
    private void initialize() throws SQLException {
        changeAlert(status, "");
        String sql = "SELECT * FROM db_javalee.cidade";
        ResultSet cityQueryResult = helpDB(sql);
        if (cityQueryResult == null) {
            changeAlert(true, "Não foi possível carregar as cidades");
            return;
        }
        while (cityQueryResult.next()) {
            String cityName = cityQueryResult.getString("nome_cidade");
            cityList.add(cityName);
        }
        cityChoiceBox.setItems(cityList);
        
    }

    @FXML
    private void reportGenerate(ActionEvent event) throws SQLException, IOException {
        changeAlert(false, "");
        citySelected = cityChoiceBox.getValue();
        if (citySelected == null) {
            changeAlert(true, "Selecione uma cidade");
            return;
        }
        prepareForNewResults();
        generateMediaDateReport();
        if (averageResults.isEmpty()) {
            changeAlert(true, "Não existem Média dessa cidade");
            return;
        }
        App.openStatusReportResult(averageResults, citySelected);
        return;
                
    };

    private void generateMediaDateReport() throws SQLException{
        getWeatherStationsIds();
        getAverageResultsFromIdList();
    }

    private void getWeatherStationsIds() throws SQLException {
        changeAlert(false, "");
        String sql = "SELECT e.id_estacao FROM db_javalee.cidade c JOIN db_javalee.estacao e ON c.id_cidade = e.id_cidade WHERE c.nome_cidade = '" + citySelected + "'";
        ResultSet stationsQueryResult = helpDB(sql);
        if (!stationsQueryResult.next()) {
            return;
        } else {
            do {
                String station_id = stationsQueryResult.getString("id_estacao");
                stations_ids.add(station_id);
            } while (stationsQueryResult.next());
        }
    }

    private void getAverageResultsFromIdList() throws SQLException{
        if (stations_ids.size() == 0){
            return;
        }
        String ids = stations_ids.toString().replace("[", "(").replace("]", ")");
        String sql = "SELECT m.nome, r.id_metrica, AVG(valor) AS media FROM db_javalee.registro r JOIN db_javalee.metrica m ON r.id_metrica = m.id_metrica WHERE id_estacao IN" + ids + " GROUP BY r.id_metrica, m.nome";
        ResultSet resultQueryAverageResults = helpDB(sql);
        if (resultQueryAverageResults == null) {return;}
        while(resultQueryAverageResults.next()){
            String metrica_nome = resultQueryAverageResults.getString("nome");
            String average = resultQueryAverageResults.getString("media");
               
            averageResults.put(metrica_nome, average);
        }
    }

    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }

    private void changeAlert(Boolean status, String message) {
        alerts.setText(message);
        alerts.setVisible(status);
    }

    private void prepareForNewResults() {
        averageResults.clear();
        stations_ids.clear();
    }
};
