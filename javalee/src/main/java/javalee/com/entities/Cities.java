package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javalee.com.bd_connection.DbConnection;

public class Cities {

    private List<City> listCity;

    public City searchCity(String buscador) {
        
        DbConnection db = new DbConnection();
        
        ResultSet resultStation = null;
        String query = "";
        
        try {
            int num = Integer.parseInt(buscador);
            query = "SELECT * FROM cidade WHERE id_cidade = '" + num + "'";
        } catch (NumberFormatException e) {
            if (buscador.length() == 2) {
                query = "SELECT * FROM cidade WHERE sigla_cidade = '" + buscador + "'";
            } else {
                query = "SELECT * FROM cidade WHERE nome_cidade = '" + buscador + "'";
            }
        }

        if (!query.isEmpty()) {
            resultStation = db.executeWithReturn(query);
        }
        
        City city = null;
        try {
            if (resultStation.next()) {
                String sigla = resultStation.getString("sigla_cidade");
                int idCidade = resultStation.getInt("id_cidade");
                String nomeCidade = resultStation.getString("nome_cidade");

                city = new City(sigla, nomeCidade, idCidade);
            }

        } catch (Exception e) {
        };
        db.Desconnect();

        return city;
    }

    public boolean isExist(String sigla) {
        return searchCity(sigla) != null;
    }

    public City createCity(String sigla, String nome) {
        City newCity = new City(sigla, nome);
        DbConnection db = new DbConnection();
        db.executeNotReturn(newCity.toInsertSql());
        db.Desconnect();

        return searchCity(sigla);
    }

    public List<City> getListCity() {
        return listCity;
    }

    public List<City> getAllCity() {
        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM cidade");
        List<City> result = new LinkedList<>();

        try {
            while (resultStation.next()) {
                int idCidade = resultStation.getInt("id_cidade");
                String sigla = resultStation.getString("sigla_cidade");
                String nomeCidade = resultStation.getString("nome_cidade");

                result.add(new City(sigla, nomeCidade, idCidade));
                listCity.add(new City(sigla, nomeCidade, idCidade));
            }
        } catch (Exception e) {
        }
        db.Desconnect();

        return result;
    }

    public ObservableList<String> getAllNamesCities() {
        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM cidade");
        ObservableList<String> listNameCity = FXCollections.observableArrayList();

        try {
            while (resultStation.next()) {
                String nomeCidade = resultStation.getString("nome_cidade");

                listNameCity.add(nomeCidade);
            }
        } catch (Exception e) {
        }
        db.Desconnect();

        return listNameCity;
    }
}
