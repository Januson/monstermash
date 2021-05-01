package org.monstermash.statblock;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.monstermash.stats.Trait;

public class TraitRow extends Label {
    public TraitRow(Trait trait) {
//        super(
//            new JLabelBrownOverview(label),
//            new JLabelBrown(value)
//        );
        Text text1=new Text(trait.name() + ". ");
        text1.setStyle("-fx-font-weight: bold");
        text1.setFont(new Resource("/opensans/OpenSans-Regular.ttf").withSize(12));

        Text text2=new Text(trait.description() + ". ");
        text2.setFont(new Resource("/opensans/OpenSans-Regular.ttf").withSize(12));

        TextFlow value = new TextFlow(text1, text2);
        value.setTextAlignment(TextAlignment.JUSTIFY);
        setGraphic(value);
        setHeight(value.getHeight()*3);
        setWrapText(true);
    }
}
