package javalee.com;


import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

public class ManageCitiesController {

    @FXML
    private Button saveCities;

    @FXML
    private Button editCities;

    @FXML
    private ComboBox<City> cBoxCity;

    @FXML
    private ObservableList<City> listCity = FXCollections.observableArrayList();

    @FXML
    private ResultSet helpDB(String query) {
        DbConnection db = new DbConnection();
        ResultSet resultSet = db.executeWithReturn(query);
        db.Desconnect();
        return resultSet;
    }

    @FXML
    public void initialize (){
        onComboBox();
    }

    @FXML
    public void onComboBox () {
        Cities cities = new Cities();
        List<City> cityList = cities.getAllCity();
        listCity.addAll(cityList);
        cBoxCity.setItems(listCity);
    }
}
