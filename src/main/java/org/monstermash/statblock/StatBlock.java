package org.monstermash.statblock;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import java.awt.Color;
//import java.awt.Dimension;

public class StatBlock {
//    private static final Logger LOG = LogManager.getLogger(StatBlock.class);
//    private static final Color TRANSPARENT_COLOR = new Color(238, 238, 238);

    public static Pane renderImage() {
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

    private static Pane getWindowContents() {
        GridPane content = new GridPane();
        content.add(header(), 0, 0);
        content.add(getNewSeparator(), 0, 1);
        return content;
    }

    private static Pane header() {
//        returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
//        returnMe.setOpaque(false);

        //Actual content
        String nameData = "Ghoul of the rotting tree";
        Label nameLabel = new Label(nameData);
//        nameLabel.setFont(titleFont(20));
        Label sizeTypeTag = new Label(String.format("%s %s(%s)",
            "Obrovský",
            "Mrtvák",
            "Revenant"
        ));
        sizeTypeTag.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//        sizeTypeTag.setFont(typeFont(10));
        return new VBox(nameLabel, sizeTypeTag);
    }

//    private static JPanel getNewBuffer() {
//        JPanel buffer = new JPanel();
//        buffer.setOpaque(false);
//        buffer.setPreferredSize(new Dimension(100, 50));
//        buffer.setMinimumSize(buffer.getPreferredSize());
//        return buffer;
//    }

//    public static Font titleFont(double fontSize) {
//        return new Resource("alegreya/AlegreyaSC-Regular.otf")
//            .withSize(fontSize);
//    }
//
//    public static Font typeFont(double fontSize) {
//        return new Resource("opensans/OpenSans-Italic.ttf")
//            .withSize(fontSize);
//    }
//
//    public static Font regularFont(double fontSize) {
//        return new Resource("opensans/OpenSans-Regular.ttf")
//            .withSize(fontSize);
//    }

    private static Pane getNewSeparator() {
        return new Separator();
    }



}