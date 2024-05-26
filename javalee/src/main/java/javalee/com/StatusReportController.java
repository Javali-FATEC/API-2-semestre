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
import javalee.com.services.utilInterno;
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

    private List<String> stations_ids = new ArrayList<>();

    private HashMap<String, String> averageResults = new HashMap<String, String>();

    @FXML
    private void initialize() throws SQLException {
        String sql = "SELECT * FROM cidade";
        ResultSet cityQueryResult = helpDB(sql);
        if (cityQueryResult == null) {
            utilInterno.alertError("Não foi possível carregar as cidades"
            , "Erro");
                        }
        while (cityQueryResult.next()) {
            String cityName = cityQueryResult.getString("nome_cidade");
            cityList.add(cityName);
        }
        cityChoiceBox.setItems(cityList);

    }

    @FXML
    private void reportGenerate(ActionEvent event) throws SQLException, IOException {
        citySelected = cityChoiceBox.getValue();
        if (citySelected == null) {
            utilInterno.alertError("Selecione uma cidade", "Erro");
             return;
        }
        prepareForNewResults();
        generateMediaDateReport();
        if (averageResults.isEmpty()) {
            utilInterno.alertError("Não existem Média dessa cidade", "Dado inexistente");
        }
        App.openStatusReportResult(averageResults, citySelected);
        return;

    };

    private void generateMediaDateReport() throws SQLException {
        getWeatherStationsIds();
        getAverageResultsFromIdList();
    }

    private void getWeatherStationsIds() throws SQLException {
        String sql = "SELECT e.id_estacao FROM cidade c JOIN estacao e ON c.id_cidade = e.id_cidade WHERE c.nome_cidade = '" + citySelected + "'";
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

    private void getAverageResultsFromIdList() throws SQLException {
        if (stations_ids.size() == 0) {
            return;
        }
        String ids = stations_ids.toString().replace("[", "(").replace("]", ")");
        String maxDateHour = searchLastDate(ids);
        String sql = "SELECT m.nome, r.id_metrica, AVG(valor) AS media FROM registro r JOIN metrica m ON r.id_metrica = m.id_metrica WHERE id_estacao IN " + ids + " AND TO_CHAR(r.data_hora, 'YYYY-MM-DD HH24:MI:SS') = '" + maxDateHour + "' GROUP BY r.id_metrica, m.nome";
        ResultSet resultQueryAverageResults = helpDB(sql);
        if (resultQueryAverageResults == null) {
            return;
        }
        while (resultQueryAverageResults.next()) {
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

    private String searchLastDate(String ids) throws SQLException {
        String sql =  "SELECT MAX(r.data_hora) FROM registro r JOIN metrica m ON r.id_metrica = m.id_metrica WHERE id_estacao IN" + ids + "";
        ResultSet resultQueryAverageResults = helpDB(sql);
        if (resultQueryAverageResults.next()) {
            String maxDateHour = resultQueryAverageResults.getString("max");
            return maxDateHour;
        }
        return "";

    }
};
