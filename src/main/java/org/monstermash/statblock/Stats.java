package org.monstermash.statblock;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.monstermash.ui.TextBinder;

public record Stats(Stat fortitude, Stat reflexes, Stat intelligence, Stat insight) {

    public Stats(final TextBinder binder) {
        this(
            new Stat(new SimpleStringProperty(), new SimpleObjectProperty<>(0)),
            new Stat(new SimpleStringProperty(), new SimpleObjectProperty<>(0)),
            new Stat(new SimpleStringProperty(), new SimpleObjectProperty<>(0)),
            new Stat(new SimpleStringProperty(), new SimpleObjectProperty<>(0))
        );
        binder.bind(this.fortitude.name(), "ui.monster.stats.fortitude");
        binder.bind(this.reflexes.name(), "ui.monster.stats.reflexes");
        binder.bind(this.intelligence.name(), "ui.monster.stats.intelligence");
        binder.bind(this.insight.name(), "ui.monster.stats.insight");
    }

}