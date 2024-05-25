package javalee.com.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javalee.com.bd_connection.DbConnection;

public class RelatorioBoxPlot {
    private String primeiroQuartil;
    private String segundoQuartil;
    private String terceiroQuartil;
    private Float limiteSuperior;
    private Float limiteInferior;

    
    public RelatorioBoxPlot(String estacao, String dataType, String dataRecebida){
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
                " CONCAT(PERCENTILE_CONT(0.50) WITHIN GROUP (ORDER BY registro.valor), unidade_medida.nome) AS q2, " +
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
                segundoQuartil = quartis.getString("q2");
                primeiroQuartil = quartis.getString("q3");

                String minimo = terceiroQuartil.replaceAll("[^\\d\\.]", "");
                String maximo = primeiroQuartil.replaceAll("[^\\d\\.]", "");

                Float terceiroQuartilFloat = Float.parseFloat(minimo);
                Float primeiroQuartilFloat = Float.parseFloat(maximo);

                limiteSuperior = terceiroQuartilFloat + 1.5f * (terceiroQuartilFloat - primeiroQuartilFloat);
                limiteInferior = primeiroQuartilFloat - 1.5f * (terceiroQuartilFloat - primeiroQuartilFloat);
            }            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }       
    }

    public String getterceiroQuartil() {
        return terceiroQuartil;
    }

    public String getSegundoQuartil() {
        return segundoQuartil;
    }

    public String getprimeiroQuartil() {
        return primeiroQuartil;
    }

    public Float getLimiteSuperior() {
        return limiteSuperior;
    }

    public Float getLimiteInferior(){
         return limiteInferior;
    }

}
