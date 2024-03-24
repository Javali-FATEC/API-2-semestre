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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("importCSV"));
        stage.setScene(scene);
        // stage.getIcons().add(new Image("/javalee.jpg"));
        // C:\Users\vinic\OneDrive\Área de Trabalho\fatec\2º semestre\API-2-semestre\javalee\src\images\javalee.jpg
        // C:\Users\vinic\OneDrive\Área de Trabalho\fatec\2º semestre\API-2-semestre\javalee\src\main\java\javalee\com\App.java
        stage.setTitle("Importar CSV");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}