package javalee.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    //importCSV.fxml
    @Override
    public void start(Stage stage) throws IOException {
        Image image = new Image(getClass().getResourceAsStream("javalee.jpg"));

        stage.getIcons().add(image);

        scene = new Scene(loadFXML("importCSV"));
        stage.setScene(scene);
        // stage.getIcons().add(new Image("/javalee.jpg"));
        stage.setTitle("Importar CSV");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void openWindowAnalysis(String analysisInterface, DataFile dataFile) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(analysisInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        AnalysisController controller = fxmlLoader.getController();
        controller.setDataFile(dataFile);

        stage.setTitle("Dados Importados - " + dataFile.getSiglaCidade() + "/" + dataFile.getIdEstacao());
        stage.show();
    }

    static void openPreviewData(String opernPreviewDataInterface, DataFile dataFile) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(opernPreviewDataInterface + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        PreviewDataController controller = fxmlLoader.getController();
        controller.setDataFile(dataFile);

        stage.setTitle("Dados Importados - " + dataFile.getSiglaCidade() + "/" + dataFile.getIdEstacao());
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}