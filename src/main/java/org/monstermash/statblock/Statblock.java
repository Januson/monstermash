package org.monstermash.statblock;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.monstermash.ui.Messages;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        content.add(header(), 0, 0);
        content.add(getNewSeparator(), 0, 1);
        content.add(stats(), 0, 2);
        content.add(getNewSeparator(), 0, 3);
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
