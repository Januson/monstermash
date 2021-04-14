package org.monstermash;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
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
import javafx.util.StringConverter;
import org.monstermash.statblock.Monster;
import org.monstermash.statblock.Size;
import org.monstermash.statblock.Statblock;
import org.monstermash.statblock.Translatable;
import org.monstermash.statblock.Type;
import org.monstermash.ui.Language;
import org.monstermash.ui.Languages;
import org.monstermash.ui.Messages;
import org.monstermash.ui.TextBinder;


public class MonsterMash extends Application {

    private Integer numSwitches = 0;
    private final Languages languages = new Languages();
    private final Messages messages = new Messages(languages);
    private final TextBinder binder = new TextBinder(languages, messages);
    private final Monster monster = new Monster();
    private final Statblock statblock = new Statblock(monster, messages);


    /**
     * sets the given Locale in the I18N class and keeps count of the number of switches.
     *
     * @param language the new local to set
     */
    private void switchLanguage(Language language) {
        numSwitches++;
        this.languages.switchTo(language);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MonsterMash!");
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
//        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        pane.getChildren().add(header());
        pane.getChildren().add(stats());
        pane.getChildren().add(speed());
        pane.getChildren().add(defense1());

        FlowPane main = new FlowPane(Orientation.VERTICAL);
        main.getChildren().add(createMenuBar(primaryStage));
        main.getChildren().add(pane);
        Scene scene = new Scene(main, 600, 550);
        primaryStage.getIcons().add(icon());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Image icon() {
        return new Image(MonsterMash.class.getResourceAsStream("/images/icon.png"));
    }

    public Pane header() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);
//        pane.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Monster Stats");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Label nameLabel = new Label();
        this.binder.bind(nameLabel.textProperty(), "ui.monster.name");
        pane.add(nameLabel, 0, 1);
        final TextField name = new TextField("Monster");
        name.textProperty().set("Monster");
        this.monster.nameProperty().bind(name.textProperty());
        name.deselect();
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        pane.add(name, 1, 1);

        Label typeLabel = new Label();
        this.binder.bind(typeLabel.textProperty(), "ui.monster.type");
        pane.add(typeLabel, 0, 2);
        ChoiceBox<Type> typeChoice = new ChoiceBox<>();
        typeChoice.getItems().setAll(Type.values());
        typeChoice.setValue(Type.BEAST);
        StringConverter<Type> converter = new StringConverter<>() {
            @Override
            public String toString(Type object) {
                return messages.get(object.key());
            }

            @Override
            public Type fromString(String string) {
                return null;
            }
        };
        typeChoice.setConverter(converter);
        this.monster.typeProperty().bind(Bindings.select(typeChoice.getSelectionModel().selectedItemProperty()));
        ChoiceBox<Size> sizeChoice = new ChoiceBox<>();
        sizeChoice.getItems().setAll(Size.values());
        sizeChoice.setValue(Size.MEDIUM);
        StringConverter<Size> sizeConverter = new StringConverter<>() {
            @Override
            public String toString(Size object) {
                return messages.get(object.key());
            }

            @Override
            public Size fromString(String string) {
                return null;
            }
        };
        sizeChoice.setConverter(sizeConverter);
        this.monster.sizeProperty().bind(Bindings.select(sizeChoice.getSelectionModel().selectedItemProperty()));
        HBox typeSize = new HBox(sizeChoice, typeChoice);
        pane.add(typeSize, 1, 2);

        return pane;
    }

    private Pane stats() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);

        Label fortitudeLabel = new Label();
        this.binder.bind(fortitudeLabel.textProperty(), "ui.monster.stats.fortitude");
        pane.add(fortitudeLabel, 0, 0);
        Spinner<Integer> fortitude = new Spinner<>(-1, 5, 0);
        fortitude.setPrefWidth(50);
        fortitude.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Fortitude changed from " + oldValue + " to " + newValue);
        });
        pane.add(fortitude, 0, 1);

        Label reflexesLabel = new Label("Reflexes");
        this.binder.bind(reflexesLabel.textProperty(), "ui.monster.stats.reflexes");
        pane.add(reflexesLabel, 1, 0);
        Spinner<Integer> reflexes = new Spinner<>(-1, 5, 0);
        reflexes.setPrefWidth(50);
        pane.add(reflexes, 1, 1);

        Label intelligenceLabel = new Label("Intelligence");
        this.binder.bind(intelligenceLabel.textProperty(), "ui.monster.stats.intelligence");
        pane.add(intelligenceLabel, 2, 0);
        Spinner<Integer> intelligence = new Spinner<>(-1, 5, 0);
        intelligence.setPrefWidth(50);
        pane.add(intelligence, 2, 1);

        Label insightLabel = new Label("Insight");
        this.binder.bind(insightLabel.textProperty(), "ui.monster.stats.insight");
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

        Label speedLabel = new Label();
        this.binder.bind(speedLabel.textProperty(), "ui.monster.speed.walk");
        pane.add(speedLabel, 0, 0);
        Spinner<Integer> speed = new Spinner<>(0, 1000, 0, 5);
        speed.setPrefWidth(50);
        pane.add(speed, 0, 1);

        Label swimLabel = new Label();
        this.binder.bind(swimLabel.textProperty(), "ui.monster.speed.swim");
        pane.add(swimLabel, 1, 0);
        Spinner<Integer> swim = new Spinner<>(0, 1000, 0, 5);
        swim.setPrefWidth(50);
        pane.add(swim, 1, 1);

        Label burrowLabel = new Label();
        this.binder.bind(burrowLabel.textProperty(), "ui.monster.speed.burrow");
        pane.add(burrowLabel, 2, 0);
        Spinner<Integer> burrow = new Spinner<>(0, 1000, 0, 5);
        burrow.setPrefWidth(50);
        pane.add(burrow, 2, 1);


        Label climbLabel = new Label();
        this.binder.bind(climbLabel.textProperty(), "ui.monster.speed.climb");
        pane.add(climbLabel, 3, 0);
        Spinner<Integer> climb = new Spinner<>(0, 1000, 0, 5);
        climb.setPrefWidth(50);
        pane.add(climb, 3, 1);

        Label flightLabel = new Label();
        this.binder.bind(flightLabel.textProperty(), "ui.monster.speed.fly");
        pane.add(flightLabel, 4, 0);
        Spinner<Integer> flight = new Spinner<>(0, 1000, 0, 5);
        flight.setPrefWidth(50);
        pane.add(flight, 4, 1);

        Label hoverLabel = new Label();
        this.binder.bind(hoverLabel.textProperty(), "ui.monster.speed.hover");
        pane.add(hoverLabel, 5, 0);
        CheckBox hover = new CheckBox();
//        Spinner<Integer> hover = new Spinner<>(-1, 5, 0);
        hover.setPrefWidth(50);
        pane.add(hover, 5, 1);

        return pane;
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

    enum State {
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
        final var fileMenu = new Menu();
        this.binder.bind(fileMenu.textProperty(), "ui.menu.file");

        final var render = new MenuItem();
        this.binder.bind(render.textProperty(), "ui.menu.file.render");
        render.setOnAction(event -> showStatblock(primaryStage));

        final var export = new MenuItem();
        this.binder.bind(export.textProperty(), "ui.menu.file.export");

        final var settings = new MenuItem();
        this.binder.bind(settings.textProperty(), "ui.menu.file.settings");
        settings.setOnAction(event -> showSettings(primaryStage));

        final var exit = new MenuItem();
        this.binder.bind(exit.textProperty(), "ui.menu.file.exit");
        exit.setOnAction(event -> primaryStage.close());

        fileMenu.getItems().add(render);
        fileMenu.getItems().add(export);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(settings);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exit);

        final var helpMenu = new Menu();
        this.binder.bind(helpMenu.textProperty(), "ui.menu.help");
        final var about = new MenuItem();
        this.binder.bind(about.textProperty(), "ui.menu.help.about");

        helpMenu.getItems().add(about);

        final var menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(helpMenu);
        return new VBox(menuBar);
    }

    private void showSettings(Stage primaryStage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        binder.bind(dialog.titleProperty(), "ui.settings.title");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);

        Button buttonEnglish = binder.buttonForKey("button.english");
        buttonEnglish.setOnAction((evt) -> switchLanguage(Language.ENGLISH));
        hbox.getChildren().add(buttonEnglish);

        Button buttonGerman = binder.buttonForKey("button.german");
        buttonGerman.setOnAction((evt) -> switchLanguage(Language.CZECH));
        hbox.getChildren().add(buttonGerman);


        // a label to display the number of changes, recalculating the text on every change
        final Label label = binder.labelForValue(() -> messages.get("label.numSwitches", numSwitches));

        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        dialogVbox.getChildren().add(hbox);
        dialogVbox.getChildren().add(label);

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.getIcons().add(icon());
        dialog.show();
    }

    private void showStatblock(Stage primaryStage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        binder.bind(dialog.titleProperty(), "ui.statblock.title");
        Scene dialogScene = new Scene(this.statblock.renderImage(), 300, 200);
        dialog.setScene(dialogScene);
        dialog.getIcons().add(icon());
        dialog.show();
    }

    public static void main(String[] args) {
        Application.launch(MonsterMash.class, args);
    }

}