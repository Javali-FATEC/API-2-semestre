package javalee.com.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javalee.com.bd_connection.DbConnection;

public class LimiteSuperior {
    private String valorMaximo;
    private String valorMinimo;
    private Float limiteSuperior;
    
    public LimiteSuperior(String estacao, String dataType, String dataRecebida){
        DbConnection db = new DbConnection();
        
        ResultSet idEstacao = db.executeWithReturn("SELECT id_estacao FROM estacao WHERE codigo = '"+ estacao +"'");
        ResultSet idMetrica = db.executeWithReturn("SELECT id_metrica FROM metrica WHERE nome = '"+ dataType +"'");
        ResultSet idUnidadeMedida = db.executeWithReturn("SELECT id_unidade_medida FROM metrica WHERE nome = '"+ dataType +"'");        

        DateTimeFormatter formatoAtual = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter novoFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFormatada = LocalDate.parse(dataRecebida, formatoAtual);
        String dataParaBD = dataFormatada.format(novoFormato);

        try{
            if (idEstacao.next() && idMetrica.next()){
                int estacaoId = idEstacao.getInt("id_estacao");   
                int metricaId = idMetrica.getInt("id_metrica");

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
                    "WHERE " +
                    " registro.id_estacao = '"+ estacaoId +"' AND registro.id_metrica = '"+ metricaId +"' AND DATE(registro.data_hora) = '"+dataParaBD+"'" +
                    "GROUP BY " +
                    " unidade_medida.nome");
                
                if (quartis.next()) {
                    valorMinimo = quartis.getString("q1");
                    valorMaximo = quartis.getString("q3");

                    String minimo = valorMinimo.replaceAll("[^\\d\\.]", "");
                    String maximo = valorMaximo.replaceAll("[^\\d\\.]", "");

                    Float valorMinimoFloat = Float.parseFloat(minimo);
                    Float valorMaximoFloat = Float.parseFloat(maximo);
    
                    limiteSuperior = valorMaximoFloat + 1.5f * (valorMaximoFloat - valorMinimoFloat);
                }
            }

            // if (idUnidadeMedida.next()){
            //     int unidadeMedidaId = idUnidadeMedida.getInt("id_unidade_medida");

            //     ResultSet valorUnidadeMedida = db.executeWithReturn("SELECT nome FROM unidade_medida WHERE id_unidade_medida = '"+ unidadeMedidaId +"'");

            //     unidadeMedida = valorUnidadeMedida.getString("id_unidade_medida");
            // }
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }       
    }

    public String getValorMinimo() {
        //retorna o q1
        return "Valor minimo: " + valorMinimo;
    }

    public String getValorMaximo() {
        //retorna o q3
        return "Valor maximo: " + valorMaximo;
    }

    public Float getLimiteSuperior() {
        // retorna o limite superior
        return limiteSuperior;
    }

    public static void main(String[] args) {
        LimiteSuperior limite = new LimiteSuperior("83726", "Chuva", "01/11/2023");

        System.out.println(limite.getValorMinimo());
        System.out.println(limite.getValorMaximo());
        System.out.println(limite.getLimiteSuperior());
        // System.out.println(limite.unidadeMedida);
    }
}
