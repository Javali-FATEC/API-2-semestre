package javalee.com.entities;

import java.sql.ResultSet;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class Cities {
    private List<City> listCity;

    public City searchCity(String sigla){
        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM db_javalee.cidade WHERE sigla_cidade = '" + sigla + "'");
        City city = null;

        try {
            if (resultStation.next()) {
                int idCidade = resultStation.getInt("id_cidade");
                String nomeCidade = resultStation.getString("nome_cidade");

                city = new City(sigla,nomeCidade,idCidade); 
            }
            
        } catch (Exception e) {}
        db.Desconnect();
        
        return city;
    }

    public boolean isExist(String sigla){
        return searchCity(sigla) != null;
    }

    public City createCity(String sigla,String nome){
        City newCity = new City(sigla, nome);
        DbConnection db = new DbConnection();
        db.executeNotReturn(newCity.toInsertSql());
        db.Desconnect();

        return searchCity(sigla);
    }

    public List<City> getListCity() {
        return listCity;
    }


}
