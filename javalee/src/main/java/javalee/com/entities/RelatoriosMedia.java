package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import javafx.util.Callback;
import javalee.com.bd_connection.DbConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RelatoriosMedia {

    public LinkedList<RelatorioMedia> listRelatorios;
    public int cidade_id;
    public LinkedList<String> lista_cidades;

    public LinkedList<RelatorioMedia> searchCidadeRelatorioMetric(String nome_cidade, String data_inicio,String data_final) {

        this.listRelatorios = new LinkedList<RelatorioMedia>();

        DbConnection db = new DbConnection();

        ResultSet resultMetric = db.executeWithReturn("SELECT * FROM cidade WHERE nome_cidade = '" + nome_cidade + "'");

        try {
            if (resultMetric.next()) {
                this.cidade_id = resultMetric.getInt("id_cidade");
            }

        } catch (Exception e) {
        }
        DateTimeFormatter formatoAtual = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter novoFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(data_inicio, formatoAtual);
        String data_inicio_formatada = data.format(novoFormato);
        LocalDate datab = LocalDate.parse(data_final, formatoAtual);
        String data_final_formatada = datab.format(novoFormato);

        var sql = "SELECT" +  
        " DATE_TRUNC('hour', r.data_hora) AS hora_arredondada,"+
        " AVG(r.valor) AS valor,"+
        " m.nome as dado," + 
        " um.nome as unidade" +
        " FROM" +
        " registro r"+
        " left join estacao e  on r.id_estacao = e.id_estacao"+
        " left join cidade c  on c.id_cidade = e.id_cidade" +
        " left join metrica m  on m.id_metrica = m.id_metrica"+
        " left join unidade_medida um  on m.id_unidade_medida  = um.id_unidade_medida" +
        " WHERE" +
            " c.id_cidade=" + this.cidade_id +
            " AND r.data_hora BETWEEN '" + data_inicio_formatada + "' AND '" + data_final_formatada+"'" +
        " GROUP BY" +
            " hora_arredondada,"+
            " m.nome,"+
            " um.nome"+
        " ORDER BY "+
            " hora_arredondada;";

        ResultSet resultRelatorio = db.executeWithReturn(sql);

        try {
            while (resultRelatorio.next()) {
                String hora_media = resultRelatorio.getString("hora_arredondada");
                int valor = resultRelatorio.getInt("valor");
                String dado = resultRelatorio.getString("dado");
                String unidade = resultRelatorio.getString("unidade");
                RelatorioMedia RelatorioMedia = new RelatorioMedia(hora_media, valor, dado, unidade);
                this.listRelatorios.add(RelatorioMedia);
            }

        } catch (Exception e) {
        }

        db.Desconnect();
        return listRelatorios;
    }

    public LinkedList<String> ListCidades() {
        LinkedList<String> lista_cidades = new LinkedList<String>();

        DbConnection db = new DbConnection();

        ResultSet resultCidade = db.executeWithReturn("SELECT cidade.nome_cidade FROM cidade ");

        try {
            while (resultCidade.next()) {
                String cidade = resultCidade.getString("nome_cidade");
                lista_cidades.add(cidade);
            }
        } catch (Exception e) {
        }

        return lista_cidades;
    }

}
