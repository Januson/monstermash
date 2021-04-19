package org.monstermash.statblock;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.monstermash.statblock.Resource;

public class StatRow extends Label {
    public StatRow(String label, String value) {
//        super(
//            new JLabelBrownOverview(label),
//            new JLabelBrown(value)
//        );
        Text text1=new Text(label + ": ");
        text1.setStyle("-fx-font-weight: bold");
        text1.setFill(Color.web("#902717"));
        text1.setFont(new Resource("/opensans/OpenSans-Regular.ttf").withSize(12));

        Text text2=new Text(value);
        text2.setFill(Color.web("#902717"));
        text2.setFont(new Resource("/opensans/OpenSans-Regular.ttf").withSize(12));

        setGraphic(new TextFlow(text1, text2));
    }
}
