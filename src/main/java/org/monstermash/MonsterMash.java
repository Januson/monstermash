package org.monstermash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.monstermash.ui.StatBlock;

import java.net.URL;
import java.nio.file.Paths;


public class MonsterMash extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL sceneFile = Paths.get("./src/main/resources/fxml/scene.fxml").toUri().toURL();
//        URL sceneFile = MonsterMash.class.getResource("/fxml/scene.fxml");
        Parent root = FXMLLoader.load(sceneFile);

        Scene scene = new Scene(root);
        URL stylesFile = Paths.get("./src/main/resources/styles/styles.css").toUri().toURL();
        scene.getStylesheets().add(stylesFile.toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}