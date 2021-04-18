package org.monstermash.statblock;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.monstermash.MonsterMash;
import org.monstermash.ui.TextBinder;

public class Monster {

    private final StringProperty name;
    private final ObjectProperty<Size> size;
    private final ObjectProperty<Type> type;
    private final StringProperty subtype;
    private final Stats stats;

    public Monster(TextBinder binder) {
        this.name = new SimpleStringProperty();
        this.size = new SimpleObjectProperty<>();
        this.type = new SimpleObjectProperty<>();
        this.subtype = new SimpleStringProperty();
        this.stats = new Stats(binder);
    }

    public String name() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Size size() {
        return size.get();
    }

    public ObjectProperty<Size> sizeProperty() {
        return size;
    }

    public Type type() {
        return type.get();
    }

    public ObjectProperty<Type> typeProperty() {
        return type;
    }

    public String subtype() {
        return subtype.get();
    }

    public StringProperty subtypeProperty() {
        return subtype;
    }

    public Stats stats() {
        return this.stats;
    }
}