package javalee.com.conexao_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javalee.com.patters.Patter;

public class Teste {

    public static void main(String[] args) {

        DbConnection db = new DbConnection();
        db.connect_to_db("db_javalee", "", "");

    }

}
