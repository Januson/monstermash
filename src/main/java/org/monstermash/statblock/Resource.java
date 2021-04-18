package org.monstermash.statblock;

import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final class Resource {
    private static final Logger LOG = LogManager.getLogger(Resource.class);

    private final String path;

    Resource(final String path) {
        this.path = path;
    }

    Font withSize(double fontSize) {
        final var fontData = getSeparator();
        return getFont(fontData, fontSize);
    }



    private InputStream getSeparator() {
        return Resource.class.getResourceAsStream("/fonts/" + this.path);
    }

    private Font getFont(InputStream istream, double fontSize) {
        return Font.loadFont(istream, fontSize);
    }

}