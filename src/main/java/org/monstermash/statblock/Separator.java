package org.monstermash.statblock;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

final class Separator extends VBox {
    private static final Logger LOG = LogManager.getLogger(Separator.class);

    Separator() {
        super(loadImage());
    }

    private static ImageView loadImage() {
        InputStream url = Separator.class.getResourceAsStream("/images/separator.png");
//        BufferedImage img;
        Image image = new Image(url);
//        try {
//            LOG.trace("Loading separator...");
////            img = ImageIO.read(url);
//        } catch (IOException e) {
//            throw new RuntimeException("Cannot read separator");
//        }
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        return iv1;
    }

}