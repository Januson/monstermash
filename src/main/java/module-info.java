module monstermash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens org.monstermash to javafx.fxml;
    exports org.monstermash;
}