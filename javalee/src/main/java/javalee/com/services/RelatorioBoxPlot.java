package javalee.com.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.LinkedList;

import javalee.com.bd_connection.DbConnection;

public class RelatorioBoxPlot {
    private String primeiroQuartil;
    private String segundoQuartil;
    private String terceiroQuartil;
    private Float limiteSuperior;
    private Float limiteInferior;
    private List<String> valoresOutliers; 

    
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
                terceiroQuartil = quartis.getString("q3");
                segundoQuartil = quartis.getString("q2");
                primeiroQuartil = quartis.getString("q1");

                String minimo = terceiroQuartil.replaceAll("[^\\d\\.]", "");
                String maximo = primeiroQuartil.replaceAll("[^\\d\\.]", "");

                Float terceiroQuartilFloat = Float.parseFloat(minimo);
                Float primeiroQuartilFloat = Float.parseFloat(maximo);

                limiteSuperior = terceiroQuartilFloat + 1.5f * (terceiroQuartilFloat - primeiroQuartilFloat);
                limiteInferior = primeiroQuartilFloat - 1.5f * (terceiroQuartilFloat - primeiroQuartilFloat);

                obterValoresOutliers(estacao, dataType, dataRecebida);
            }            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }       
    }

    public String getTerceiroQuartil() {
        return terceiroQuartil;
    }

    public String getSegundoQuartil() {
        return segundoQuartil;
    }

    public String getPrimeiroQuartil() {
        return primeiroQuartil;
    }

    public Float getLimiteSuperior() {
        return limiteSuperior;
    }

    public Float getLimiteInferior(){
         return limiteInferior;
    }

    public String getValoresOutliers(){
        String retorno = "Sem outliers nesse relatório.";
        if(valoresOutliers.size() != 0)
        {
            String delimitador = ",";
            retorno = String.join(delimitador, valoresOutliers);
        }

        return retorno;
    }

    private void obterValoresOutliers(String estacao, String dataType, String dataRecebida){
        DbConnection db = new DbConnection();
        this.valoresOutliers = new LinkedList<String>();

        DateTimeFormatter formatoAtual = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter novoFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFormatada = LocalDate.parse(dataRecebida, formatoAtual);
        String dataParaBD = dataFormatada.format(novoFormato);
        
        try{
            ResultSet outliers = db.executeWithReturn(
                "SELECT " + 
                "registro.valor " +
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
                " AND (registro.valor > " + this.limiteSuperior + " OR registro.valor < " + this.limiteInferior + ") " +
                " GROUP BY " +
                " unidade_medida.nome " + ", registro.valor" );
            
            while (outliers.next()) {
                System.out.println(outliers.getString("valor"));
                this.valoresOutliers.add(outliers.getString("valor"));
            }
        } catch (SQLException e){
        } finally {
            db.Desconnect();
        }
    }
}
