package javalee.com.conexao_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javalee.com.patters.Patter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Teste {

    
    public static void main(String[] args) {

        String sql = "INSERT INTO registro (id_metrica, id_estacao, valor, data_hora) VALUES (1, 101, 123.4567890123, '2024-04-25 10:30:00')";

        DbConnection db = new DbConnection();
        db.connect_to_db("db_javalee", "postgres", "1234");

        db.Save(sql);

        
 
    }

}
