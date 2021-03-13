module monstermash {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;
    requires java.logging;

    requires org.apache.logging.log4j;

    opens org.monstermash to javafx.fxml;
    exports org.monstermash;
}