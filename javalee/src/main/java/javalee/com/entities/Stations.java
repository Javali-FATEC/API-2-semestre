package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class Stations {

    private List<Station> listStations;

    public Stations() {
        this.listStations = new LinkedList<Station>();
    }

    public Station searchStation(String code, int idCity){

        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM db_javalee.estacao WHERE codigo = '" + code + "'");
        Station station = null;

        try {
            if (resultStation.next()) {
                int idReturn = resultStation.getInt("id_estacao");
                String codeReturn = resultStation.getString("codigo");
                station = new Station(idReturn,codeReturn); 
            }else{
                db.executeNotReturn("INSERT INTO db_javalee.estacao (id_cidade, codigo) VALUES ("+idCity+", '"+code+"');");
                return searchStation(code, idCity);
            }
            
        } catch (Exception e) {
            
        }
        db.Desconnect();
        
        return station;
    }

    public void loadStation(){

        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM db_javalee.estacao");

        try {
            while (resultStation.next()) {
                int idReturn = resultStation.getInt("id_estacao");
                String codeReturn = resultStation.getString("codigo");
                this.listStations.add(new Station(idReturn,codeReturn)); 
                
            }
            
        } catch (Exception e) {}
        db.Desconnect();
    }
}
