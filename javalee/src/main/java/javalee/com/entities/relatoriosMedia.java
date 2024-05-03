package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;

import javalee.com.bd_connection.DbConnection;

public class relatoriosMedia {

    public LinkedList<RelatorioMedia> listRelatorios;
    public int cidade_id;

    public LinkedList<RelatorioMedia> searchCidadeRelatorioMetric(String nome_cidade, String data_inicio,
            String data_final) {

        this.listRelatorios = new LinkedList<RelatorioMedia>();

        DbConnection db = new DbConnection();

        ResultSet resultMetric = db.executeWithReturn("SELECT * FROM cidade WHERE nome_cidade = '" + nome_cidade + "'");

        try {
            if (resultMetric.next()) {
                this.cidade_id = resultMetric.getInt("id_cidade");
            }

        } catch (Exception e) {
        }

        var sql = "SELECT" +
                " DATE_TRUNC('hour', r.data_hora) AS hora_arredondada," +
                " AVG(r.valor) AS valor," +
                " m.nome as dado," +
                " um.nome as unidade" +
                " FROM" +
                " registro r" +
                " left join db_javalee.estacao e  on r.id_estacao = e.id_estacao" +
                " left join db_javalee.cidade c  on c.id_cidade = e.id_cidade" +
                " left join db_javalee.metrica m  on m.id_metrica = m.id_metrica" +
                " left join db_javalee.unidade_medida um  on m.id_unidade_medida  = um.id_unidade_medida" +
                " WHERE" +
                " c.id_cidade=" + this.cidade_id +
                " GROUP BY" +
                " hora_arredondada," +
                " m.nome," +
                " um.nome" +
                " ORDER BY " +
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

    public static void main(String[] args) {
        relatoriosMedia relatorios = new relatoriosMedia();

        LinkedList<RelatorioMedia> resultados = relatorios.searchCidadeRelatorioMetric("Nova Iorque", "dataInicio",
                "dataFinal");

        for (RelatorioMedia relatorio : resultados) {
            System.out.println("Hora: " + relatorio.getHoraMedia() + ", Valor: " + relatorio.getValor() + ", Dado: "
                    + relatorio.getDado() + ", Unidade: " + relatorio.getUnidade());
        }
    }
}
