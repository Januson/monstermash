package org.monstermash.ui;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;

public class Languages extends ObjectBinding<Locale> {

    private final ObjectProperty<Locale> current = new SimpleObjectProperty<>(Locale.getDefault());

    public Languages() {
        bind(this.current);
    }

    @Override
    protected Locale computeValue() {
        return this.current.get();
    }

    public void switchTo(final Language language) {
        this.current.set(language.locale());
        Locale.setDefault(language.locale());
    }

}
