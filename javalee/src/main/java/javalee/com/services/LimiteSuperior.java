package javalee.com.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javalee.com.bd_connection.DbConnection;

public class LimiteSuperior {
    private String primeiroQuartil;
    private String terceiroQuartil;
    private Float limiteSuperior;
    private Float limiteInferior;

    
    public LimiteSuperior(String estacao, String dataType, String dataRecebida){
        DbConnection db = new DbConnection();

        DateTimeFormatter formatoAtual = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter novoFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFormatada = LocalDate.parse(dataRecebida, formatoAtual);
        String dataParaBD = dataFormatada.format(novoFormato);

        try{
            ResultSet quartis = db.executeWithReturn(
                "SELECT " + 
                " unidade_medida.nome AS unidade, " +
                " CONCAT(PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY registro.valor), unidade_medida.nome) AS q1, " +
                " CONCAT(PERCENTILE_CONT(0.75) WITHIN GROUP (ORDER BY registro.valor), unidade_medida.nome) AS q3 " +
                "FROM " +
                " registro " +
                "INNER JOIN " +
                " metrica ON metrica.id_metrica = registro.id_metrica " +
                "INNER JOIN " +
                " unidade_medida ON unidade_medida.id_unidade_medida = metrica.id_unidade_medida " +
                "INNER JOIN " +
                " estacao ON estacao.id_estacao = registro.id_estacao " +
                "WHERE " +
                " estacao.codigo = '"+ estacao +"' AND metrica.nome = '"+ dataType +"' AND DATE(registro.data_hora) = '"+dataParaBD+"'" +
                "GROUP BY " +
                " unidade_medida.nome");
            
            if (quartis.next()) {
                terceiroQuartil = quartis.getString("q1");
                primeiroQuartil = quartis.getString("q3");

                String minimo = terceiroQuartil.replaceAll("[^\\d\\.]", "");
                String maximo = primeiroQuartil.replaceAll("[^\\d\\.]", "");

                Float terceiroQuartilFloat = Float.parseFloat(minimo);
                Float primeiroQuartilFloat = Float.parseFloat(maximo);

                limiteSuperior = terceiroQuartilFloat + 1.5f * (primeiroQuartilFloat - terceiroQuartilFloat);
                limiteInferior = primeiroQuartilFloat - 1.5f * (primeiroQuartilFloat - terceiroQuartilFloat);
            }            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }       
    }

    public String getterceiroQuartil() {
        return "Valor minimo: " + terceiroQuartil;
    }

    public String getprimeiroQuartil() {
        return "Valor maximo: " + primeiroQuartil;
    }

    public Float getLimiteSuperior() {
        return limiteSuperior;
    }

    public Float getLimiteInferior(){
         return limiteInferior;
    }
    public static void main(String[] args) {
        LimiteSuperior limite = new LimiteSuperior("83726", "Chuva", "01/11/2023");

        System.out.println(limite.getterceiroQuartil());
        System.out.println(limite.getprimeiroQuartil());
        System.out.println(limite.getLimiteSuperior());
        System.out.println(limite.getLimiteInferior());
    }
}
