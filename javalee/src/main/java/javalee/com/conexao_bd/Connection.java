package javalee.com.conexao_bd;

public class Connection {

    public static void main(String[] args) {

        DbConnection db = new DbConnection();
        db.connect_to_db("db_javalee", "postgres", "1234");

    }

}
