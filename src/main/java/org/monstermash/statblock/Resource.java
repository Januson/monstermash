//package org.monstermash.statblock;
//
//import javafx.scene.text.Font;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//final class Resource {
//    private static final Logger LOG = LogManager.getLogger(Resource.class);
//
//    private final String path;
//
//    Resource(final String path) {
//        this.path = path;
//    }
//
//    Font withSize(double fontSize) {
//        final var fontData = getSeparator();
//        final var myFont = getFont(fontData);
//        if (myFont == null) {
//            return null;
//        }
//        return resized((float) fontSize, myFont);
//    }
//
//
//
//    private InputStream getSeparator() {
//        return Resource.class.getResourceAsStream("/fonts/" + this.path);
//    }
//
//    private Font getFont(InputStream istream) {
//            return Font.loadFont(istream);
//        } catch (FontFormatException | IOException ex) {
//            LOG.fatal("Could not load font");
//            return null;
//        }
//    }
//
//    private Font resized(float fontSize, Font myFont) {
//        return myFont.deriveFont(fontSize);
//    }
//
//}