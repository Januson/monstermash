package org.monstermash.statblock;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.monstermash.stats.Attribute;
import org.monstermash.stats.DamageModifier;
import org.monstermash.stats.Language;
import org.monstermash.stats.Sense;
import org.monstermash.stats.Skill;
import org.monstermash.ui.Messages;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.monstermash.stats.DamageModifier.DamageMods.IMMUNE;
import static org.monstermash.stats.DamageModifier.DamageMods.RESISTANT;
import static org.monstermash.stats.DamageModifier.DamageMods.VULNERABLE;

//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import java.awt.Color;
//import java.awt.Dimension;

public class Statblock {
//    private static final Logger LOG = LogManager.getLogger(StatBlock.class);
//    private static final Color TRANSPARENT_COLOR = new Color(238, 238, 238);

    private final Monster monster;
    private final Messages messages;

    public Statblock(final Monster monster, Messages messages) {
        this.monster = monster;
        this.messages = messages;
    }

    public Pane renderImage() {
//        if (currentRenderWindow != null) {
//            currentRenderWindow.dispose();
//            currentRenderWindow = null;
//        }
//
//        currentRenderWindow = renderWindow;
//        renderWindow.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                currentRenderWindow = null;
//            }
//        });
//        GridPane pane = ;
//        windowContents.setBackground(TRANSPARENT_COLOR);
//        windowContents.add(getNewBuffer(), constraints);
//        windowContents.add(getNewEndCap(), constraints);
//        pane.add(getWindowContents());
//        windowContents.add(getNewEndCap(), constraints);
//        windowContents.add(getNewBuffer(), constraints);
//        Dimension newDimension = windowContents.getPreferredSize();
//        newDimension.width = 450;
//        newDimension.height += newDimension.height / 4;
//        newDimension.height -= 10;
//        windowContents.setPreferredSize(newDimension);
        return new VBox(getWindowContents());
    }

    private Pane getWindowContents() {
        GridPane content = new GridPane();
        Color white = Color.web("#fdf1dd");
        content.setPrefWidth(450);
        content.setBackground(new Background(new BackgroundFill(white, CornerRadii.EMPTY, Insets.EMPTY)));
        content.setBorder(new Border(new BorderStroke(Color.web("#DCDCDC"),
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        content.add(header(), 0, 0);
        content.add(getNewSeparator(), 0, 1);
        content.add(mainStats(), 0, 2);
        content.add(getNewSeparator(), 0, 3);
        content.add(stats(), 0, 4);
        content.add(getNewSeparator(), 0, 5);
        content.add(miscAttributes(), 0, 6);
        content.add(getNewSeparator(), 0, 7);
//        content.add(traits(), 0, 6);
//        content.add(getNewSeparator(), 0, 7);
        return content;
    }

    private Pane header() {
//        returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
//        returnMe.setOpaque(false);

        //Actual content
        Label nameLabel = new Label(this.monster.name());
//        nameLabel.setFont(titleFont(20));
        Label sizeTypeTag = new Label(String.format("%s %s",
            this.messages.get(this.monster.size().key()),
            this.messages.get(this.monster.type().key()).toLowerCase(Locale.ROOT)
        ));
        sizeTypeTag.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//        sizeTypeTag.setFont(typeFont(10));
        return new VBox(nameLabel, sizeTypeTag);
    }

    private Pane stats() {
//        returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
//        returnMe.setOpaque(false);

        //Actual content
        List<Stat> stats = List.of(
            this.monster.stats().fortitude(),
            this.monster.stats().reflexes(),
            this.monster.stats().intelligence(),
            this.monster.stats().insight()
        );
        Node[] children = new Node[4];
        List<VBox> collect = stats.stream()
            .map(stat -> new VBox(
                new Label(stat.name().getValue()),
                new Label(stat.value().getValue().toString())
            )).collect(Collectors.toList());
//        sizeTypeTag.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//        sizeTypeTag.setFont(typeFont(10));
        return new HBox(1.0, collect.toArray(children));
    }

    private Pane mainStats() {
        return new VBox(
            new HBox(
                new Label("Base Roll: "),
                new Label("5")
            ),
            new HBox(
                new Label("Armor: "),
                new Label("5")
            ),
            new HBox(
                new Label("Speed: "),
                new Label("Walk 5ft")
            )
        );
    }

    private Pane miscAttributes() {
        List<Label> misc = new LinkedList<>();
        //Misc
        List<Skill> skillModifiers = List.of(
            new Skill("Akrobacie", 2),
            new Skill("Skrývání", 3),
            new Skill("Vnímání", 1)
        );

        if (skillModifiers.size() > 0) {
            String joinedSkills = skillModifiers.stream().map(Skill::asText).collect(Collectors.joining(", "));
            Label label = new Label("Skills: " + joinedSkills);
            misc.add(label);
        }
        List<DamageModifier> damageImmunities = List.of(
            new DamageModifier(IMMUNE, DamageModifier.ModifierType.DAMAGE, "Radiant"),
            new DamageModifier(IMMUNE, DamageModifier.ModifierType.DAMAGE, "Fire"),
            new DamageModifier(IMMUNE, DamageModifier.ModifierType.DAMAGE, "Crushing")
        );
        List<DamageModifier> damageResistances = List.of(
            new DamageModifier(RESISTANT, DamageModifier.ModifierType.DAMAGE, "Necrotic")
        );
        List<DamageModifier> damageVulnerabilities = List.of(
            new DamageModifier(VULNERABLE, DamageModifier.ModifierType.DAMAGE, "Frost")
        );
        List<DamageModifier> conditionImmunities = List.of(
            new DamageModifier(IMMUNE, DamageModifier.ModifierType.CONDITION, "Prone")
        );
        List<DamageModifier> conditionResistances = List.of(
            new DamageModifier(RESISTANT, DamageModifier.ModifierType.CONDITION, "Petrified")
        );
        List<DamageModifier> conditionVulnerabilities = List.of(
            new DamageModifier(VULNERABLE, DamageModifier.ModifierType.CONDITION, "Blinded")
        );

        //Print Modifiers
        if (damageImmunities.size() > 0) {
            String string = damageImmunities.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Damage Immunities: " + string);
            misc.add(data);
        }
        if (damageResistances.size() > 0) {
            String string = damageResistances.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Damage Resistances: " + string);
            misc.add(data);
        }
        if (damageVulnerabilities.size() > 0) {
            String string = damageVulnerabilities.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Damage Vulnerabilities: " + string);
            misc.add(data);
        }
        if (conditionImmunities.size() > 0) {
            String string = conditionImmunities.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Condition Immunities: " + string);
            misc.add(data);
        }
        if (conditionResistances.size() > 0) {
            String string = conditionResistances.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Condition Resistances: " + string);
            misc.add(data);
        }
        if (conditionVulnerabilities.size() > 0) {
            String string = conditionVulnerabilities.stream().map(DamageModifier::asText).collect(Collectors.joining(", "));
            Label data = new Label("Condition Vulnerabilities: " + string);
            misc.add(data);
        }
        //Senses
        List<Attribute> senses = List.of(
            new Sense("Bullshitsense", 180)
        );
        if (senses.size() > 0) {
            String string = senses.stream().map(Attribute::asText).collect(Collectors.joining(", "));
            Label data = new Label("Senses: " + string);
            misc.add(data);
        }
        //Languages
        List<Language> languages = List.of(
            new Language("Obecná"),
            new Language("Tail tongue")
        );
        if (languages.size() > 0) {
            String string = languages.stream().map(Attribute::asText).collect(Collectors.joining(", "));
            Label data = new Label("Languages: " + string);
            misc.add(data);
        }
        Node[] nodes = new Node[9];
        return new VBox(misc.toArray(nodes));
    }

//    private  JPanel getNewBuffer() {
//        JPanel buffer = new JPanel();
//        buffer.setOpaque(false);
//        buffer.setPreferredSize(new Dimension(100, 50));
//        buffer.setMinimumSize(buffer.getPreferredSize());
//        return buffer;
//    }

//    public  Font titleFont(double fontSize) {
//        return new Resource("alegreya/AlegreyaSC-Regular.otf")
//            .withSize(fontSize);
//    }
//
//    public  Font typeFont(double fontSize) {
//        return new Resource("opensans/OpenSans-Italic.ttf")
//            .withSize(fontSize);
//    }
//
//    public  Font regularFont(double fontSize) {
//        return new Resource("opensans/OpenSans-Regular.ttf")
//            .withSize(fontSize);
//    }

    private Pane getNewSeparator() {
        return new Separator();
    }


}
