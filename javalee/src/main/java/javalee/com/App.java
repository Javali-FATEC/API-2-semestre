package javalee.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javalee.com.entities.RelatorioMedia;
import javalee.com.services.DataFile;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Image image = new Image(getClass().getResourceAsStream("javalee.jpg"));
        stage.getIcons().add(image);
        scene = new Scene(loadFXML("masterScreen"));
        stage.setScene(scene);
        stage.setTitle("Tela inicial");
        stage.show();

        stage.setOnCloseRequest(event -> System.exit(0));
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void openWindowAnalysis(String analysisInterface, DataFile dataFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(analysisInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        AnalysisController controller = fxmlLoader.getController();
        controller.setDataFile(dataFile);

        stage.setTitle("Dados Importados - " + dataFile.getCity() + "/" + dataFile.getStation());
        stage.show();
    }

    static void openWindowReportBoxPlot(String boxPlotReport, String dataFile, String dataFormatada) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(boxPlotReport + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        BoxPlotReportController controller = fxmlLoader.getController();
        controller.setBoxPlotReport(dataFile, dataFormatada);

        stage.setTitle("Relatório BoxPlot");
        stage.show();
    }

    static void openPreviewData(String opernPreviewDataInterface, DataFile dataFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(opernPreviewDataInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        PreviewDataController controller = fxmlLoader.getController();
        controller.setDataFile(dataFile);

        stage.setTitle("Dados Importados - " + dataFile.getCity() + "/" + dataFile.getStation());
        stage.show();
    }

    static void openWindowToolTip(String opernPreviewDataInterface) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(opernPreviewDataInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        stage.setTitle("Padrões Usados");
        stage.show();
    }

    static void openSeeInconsistencies(String opernPreviewDataInterface, Map<String, String> lineErrors)
            throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(opernPreviewDataInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        SeeInconsistenciesController controller = fxmlLoader.getController();
        controller.setLineErrors(lineErrors);

        stage.setTitle("Lista de Inconsistncias");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void openImportCSV(String analysisInterface) throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("importCSV");
        stage.setScene(new Scene(root));
        stage.setTitle("Importar CSV");
        stage.show();
    }

    static void openStatusReport() throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("statusReport");
        stage.setScene(new Scene(root));
        stage.setTitle("Relatório Situacional");
        stage.show();
    }

    static void openBoxPlotReport() throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("boxPlotScreen");
        stage.setScene(new Scene(root));
        stage.setTitle("Gerar relatório BoxPlot");
        stage.show();
    }

    static void openBoxPlotReportData() throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("boxPlotReport");
        stage.setScene(new Scene(root));
        stage.setTitle("Dados do relatório BoxPlot");
        stage.show();
    }

    static void openStatusReportByDate() throws IOException {
        Stage stage = new Stage();
        Parent root = loadFXML("statusReportByDate");
        stage.setScene(new Scene(root));
        stage.setTitle("Relatório Situacional 2");
        stage.show();
    }

    static void openReportData(List<RelatorioMedia> relatorio, String nomeCidade) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("reportData.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        ReportDataController controller = fxmlLoader.getController();
        controller.setRelatorio(relatorio);
        stage.setTitle("Relatorio da cidade: " + nomeCidade);
        stage.show();
    }

    static void openStatusReportResult(HashMap<String, String> mediasResults, String cityName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("resultReport.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        StatusReportResultController controller = fxmlLoader.getController();
        controller.setData(mediasResults);
        stage.setTitle("Dados Situacionais cidade "+cityName);
        stage.show();
    }

    static void openDefinitionRiskValues() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("definitionRiskValues.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        DefinitionRiskValuesController controller = fxmlLoader.getController();
        stage.setTitle("Definir valores de risco");
        stage.show();
        
    }

    static void openManageStations() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("manageStations.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Gerenciar Estações");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}