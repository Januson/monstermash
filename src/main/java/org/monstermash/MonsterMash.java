package org.monstermash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.monstermash.statblock.StatBlock;
import org.monstermash.ui.I18N;

import java.util.Locale;


public class MonsterMash extends Application {

    /** number of language switches. */
    private Integer numSwitches = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.titleProperty().bind(I18N.createStringBinding("window.title"));

        // create content
        BorderPane content = new BorderPane();

        // at the top two buttons
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);

        Button buttonEnglish = I18N.buttonForKey("button.english");
        buttonEnglish.setOnAction((evt) -> switchLanguage(Locale.ENGLISH));
        hbox.getChildren().add(buttonEnglish);

        Button buttonGerman = I18N.buttonForKey("button.german");
        buttonGerman.setOnAction((evt) -> switchLanguage(new Locale("cs", "CZ")));
        hbox.getChildren().add(buttonGerman);

        content.setTop(hbox);

        // a label to display the number of changes, recalculating the text on every change
        final Label label = I18N.labelForValue(() -> I18N.get("label.numSwitches", numSwitches));
        content.setBottom(label);

        primaryStage.setScene(new Scene(content, 400, 200));
        primaryStage.show();
    }

    /**
     * sets the given Locale in the I18N class and keeps count of the number of switches.
     *
     * @param locale
     *         the new local to set
     */
    private void switchLanguage(Locale locale) {
        numSwitches++;
        I18N.setLocale(locale);
    }

//    @Override
//    public void start(Stage primaryStage) {
//        Locale.setDefault(new Locale("cs"));
//        primaryStage.setTitle("MonsterMash!");
//        FlowPane pane = new FlowPane(Orientation.VERTICAL);
////        GridPane pane = new GridPane();
//        pane.setAlignment(Pos.TOP_LEFT);
//        pane.setHgap(10);
//        pane.setVgap(10);
//        pane.setPadding(new Insets(25, 25, 25, 25));
//
//        pane.getChildren().add(header());
//        pane.getChildren().add(stats());
//        pane.getChildren().add(speed());
//        pane.getChildren().add(defense1());
//        pane.getChildren().add(StatBlock.renderImage());
//
//        FlowPane main = new FlowPane(Orientation.VERTICAL);
//        main.getChildren().add(createMenuBar(primaryStage));
//        main.getChildren().add(pane);
//        Scene scene = new Scene(main, 600, 550);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    public Pane header() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);
//        pane.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Monster Stats");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 1);
        final TextField name = new TextField();
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        pane.add(name, 1, 1);

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

        return pane;
    }

    private Pane stats() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);

        Label fortitudeLabel = new Label("Zdatnost:");
        pane.add(fortitudeLabel, 0, 0);
        Spinner<Integer> fortitude = new Spinner<>(-1, 5, 0);
        fortitude.setPrefWidth(50);
        fortitude.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Fortitude changed from " + oldValue + " to " + newValue);
        });
        pane.add(fortitude, 0, 1);

        Label reflexesLabel = new Label("Reflexes");
        pane.add(reflexesLabel, 1, 0);
        Spinner<Integer> reflexes = new Spinner<>(-1, 5, 0);
        reflexes.setPrefWidth(50);
        pane.add(reflexes, 1, 1);

        Label intelligenceLabel = new Label("Intelligence");
        pane.add(intelligenceLabel, 2, 0);
        Spinner<Integer> intelligence = new Spinner<>(-1, 5, 0);
        intelligence.setPrefWidth(50);
        pane.add(intelligence, 2, 1);

        Label insightLabel = new Label("Insight");
        pane.add(insightLabel, 3, 0);
        Spinner<Integer> insight = new Spinner<>(-1, 5, 0);
        insight.setPrefWidth(50);
        pane.add(insight, 3, 1);

        return pane;
    }

    private Pane speed() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        Label speedLabel = new Label("Speed:");
        pane.add(speedLabel, 0, 0);
        Spinner<Integer> speed = new Spinner<>(0, 1000, 0, 5);
        speed.setPrefWidth(50);
        pane.add(speed, 0, 1);

        Label swimLabel = new Label("Swim");
        pane.add(swimLabel, 1, 0);
        Spinner<Integer> swim = new Spinner<>(0, 1000, 0, 5);
        swim.setPrefWidth(50);
        pane.add(swim, 1, 1);

        Label burrowLabel = new Label("Burrow");
        pane.add(burrowLabel, 2, 0);
        Spinner<Integer> burrow = new Spinner<>(0, 1000, 0, 5);
        burrow.setPrefWidth(50);
        pane.add(burrow, 2, 1);


        Label climbLabel = new Label("Climb");
        pane.add(climbLabel, 3, 0);
        Spinner<Integer> climb = new Spinner<>(0, 1000, 0, 5);
        climb.setPrefWidth(50);
        pane.add(climb, 3, 1);

        Label flightLabel = new Label("Flight");
        pane.add(flightLabel, 4, 0);
        Spinner<Integer> flight = new Spinner<>(0, 1000, 0, 5);
        flight.setPrefWidth(50);
        pane.add(flight, 4, 1);

        Label hoverLabel = new Label("Hover");
        pane.add(hoverLabel, 5, 0);
        CheckBox hover = new CheckBox();
//        Spinner<Integer> hover = new Spinner<>(-1, 5, 0);
        hover.setPrefWidth(50);
        pane.add(hover, 5, 1);

        return pane;
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

    private TabPane defense1() {
        TabPane tabPane = new TabPane();

        Tab conditions = new Tab("Conditions", conditions());
        Tab damage = new Tab("Damage", damage());

        tabPane.getTabs().add(conditions);
        tabPane.getTabs().add(damage);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;
    }

    private ListView conditions() {
        ListView<Pane> listView = new ListView<>();

        listView.getItems().add(item("Blinded"));
        listView.getItems().add(item("Exhausted"));
        listView.getItems().add(item("Prone"));
        listView.setPrefWidth(10);
        listView.setPrefHeight(80);
        return listView;
    }

    private ListView damage() {
        ListView<Pane> listView = new ListView<>();

        listView.getItems().add(item("Acid"));
        listView.getItems().add(item("Poison"));
        listView.getItems().add(item("Necrotic"));
        listView.setPrefWidth(10);
        listView.setPrefHeight(80);
        return listView;
    }

    private Pane item(final String name) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label(name), 0, 0);
        gridPane.add(drop(), 1, 0);
        return new HBox(new Label(name));
    }

    private ChoiceBox<State> drop() {
        final var typeChoice = new ChoiceBox<State>();
        typeChoice.getItems().setAll(State.values());
        return typeChoice;
    }

    enum State{
        Immunity,
        Resistance,
        Vulnerability,
        None,
    }

    private HBox radios() {
        final ToggleGroup group = new ToggleGroup();
        final var immune = new RadioButton("Immune");
        immune.setToggleGroup(group);
        final var resistant = new RadioButton("Resistant");
        resistant.setToggleGroup(group);
        final var normal = new RadioButton("Normal");
        normal.setToggleGroup(group);
        normal.setSelected(true);
        final var vulnerable = new RadioButton("Vulnerable");
        vulnerable.setToggleGroup(group);
        return new HBox(immune, resistant, normal, vulnerable);
    }

//    public void captureAndSaveDisplay(WritableImage writableImage) {
//        FileChooser fileChooser = new FileChooser();
//
//        //Set extension filter
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
//
//        //Prompt user to select a file
//        File file = fileChooser.showSaveDialog(null);
//        if (file != null) {
//            try {
//                //Pad the capture area
////                WritableImage writableImage = new WritableImage((int) getWidth() + 20,
////                    (int)getHeight() + 20);
////                snapshot(null, writableImage);
////                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
//                //Write the snapshot to the chosen file
////                ImageIO.write(renderedImage, "png", file);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public Pane createMenuBar(Stage primaryStage) {
        final var fileMenu = new Menu("File");
        final var export = new MenuItem("Export to...");
        final var settings = new MenuItem("Settings");
        final var exit = new MenuItem("Exit");

        fileMenu.getItems().add(export);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(settings);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exit);
        settings.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("This is a Dialog"));
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            });

        final var langMenu = new Menu("Language");
        final var czech = new RadioMenuItem("Czech");
        final var english = new RadioMenuItem("English");

        final var toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(czech);
        toggleGroup.getToggles().add(english);

        langMenu.getItems().add(czech);
        langMenu.getItems().add(english);

        final var menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(langMenu);
        return new VBox(menuBar);
    }

    public static void main(String[] args) {
        Application.launch(MonsterMash.class, args);
    }

}