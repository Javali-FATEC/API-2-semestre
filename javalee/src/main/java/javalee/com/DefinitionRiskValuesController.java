package javalee.com;

import javafx.fxml.Initializable;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javalee.com.bd_connection.DbConnection;

public class DefinitionRiskValuesController{

    @FXML
    private ObservableList<String> metricList = FXCollections.observableArrayList();

    @FXML
    private TextField txtRiskMax;

    @FXML
    private ComboBox<?> cbDataType;

    @FXML
    private TextField txtRiskMin;

    private Boolean status = false;
    private String citySelected;
    private List<String> stations_ids = new ArrayList<>();;

    private HashMap<String, String> averageResults = new HashMap<String, String>();

    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }

    private void initialize() throws SQLClientInfoException{
        String sql = "SELECT * FROM db_javalee.metrica";
        ResultSet metricQueryResult = helpDB(sql);
        if (metricQueryResult == null) {
            System.out.println("ERRO");
            
        }
        while (metricQueryResult.next()) {
            String metricName = metricQueryResult.getString("nome");
            metricList.add(metricName);
        }
        cbDataType.setItems(metricList);
    }




}

