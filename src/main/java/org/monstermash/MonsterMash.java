package org.monstermash;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


public class MonsterMash extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        URL sceneFile = Paths.get("./src/main/resources/fxml/scene.fxml").toUri().toURL();
////        URL sceneFile = MonsterMash.class.getResource("/fxml/scene.fxml");
//        Parent root = FXMLLoader.load(sceneFile);
//
//        Scene scene = new Scene(root);
//        URL stylesFile = Paths.get("./src/main/resources/styles/styles.css").toUri().toURL();
//        scene.getStylesheets().add(stylesFile.toExternalForm());
//
//        stage.setTitle("JavaFX and Gradle");
//        stage.setScene(scene);
//        stage.show();


        primaryStage.setTitle("JavaFX Tax Calculator");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Tax Calculator");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Label name = new Label("Name:");
        pane.add(name, 0, 1);
        final TextField nameField = new TextField();
        pane.add(nameField, 1, 1);

        Label type = new Label("Type:");
        pane.add(type, 0, 2);
        ChoiceBox<Type> typeChoice = new ChoiceBox<>();
        typeChoice.getItems().setAll(Type.values());
        typeChoice.setValue(Type.BEAST);
        ChoiceBox<Size> sizeChoice = new ChoiceBox<>();
        sizeChoice.getItems().setAll(Size.values());
        sizeChoice.setValue(Size.MEDIUM);
        HBox typeSize = new HBox(sizeChoice, typeChoice);
        pane.add(typeSize, 1, 2);

        Label percent = new Label("% Tax:");
        pane.add(percent, 0, 3);
        final TextField percentField = new TextField();
        pane.add(percentField, 1, 3);

        Button calculateButton = new Button("Calculate");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(calculateButton);
        pane.add(hbox, 1, 4);

        final Text taxMessage = new Text();
        pane.add(taxMessage, 1, 6);

        calculateButton.setOnAction(t -> {
//            Double income = Double.parseDouble(nameField.getText());
//            Double tax = Double.parseDouble(percentField.getText()) / 100;
            taxMessage.setText("Tax incurred:" + "income * tax");
            WritableImage snapshot = pane.snapshot(new SnapshotParameters(), null);
//            pane.getChildren().add(new ImageView(snapshot));
//            System.out.println(vbox.getChildren().size());
            captureAndSaveDisplay(snapshot);
        });

        pane.add(tabs(), 1, 7);

        Scene scene = new Scene(pane, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public enum Size {
        TINY,
        SMALL,
        MEDIUM,
        LARGE,
        HUGE,
        GARGANTUAN;
    }

    public enum Type {
        BEAST,
        HUMANOID,
        UNDEAD;
    }

    private TabPane tabs() {
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Planes", new Label("Show all planes available"));
        Tab tab2 = new Tab("Cars", new Label("Show all cars available"));
        Tab tab3 = new Tab("Boats", new Label("Show all boats available"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;
    }

    public void captureAndSaveDisplay(WritableImage writableImage){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                //Pad the capture area
//                WritableImage writableImage = new WritableImage((int) getWidth() + 20,
//                    (int)getHeight() + 20);
//                snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}