package org.monstermash.ui;

import java.util.Locale;

public enum Language {
    CZECH(new Locale("cs", "CZ")),
    ENGLISH(Locale.ENGLISH);

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale locale() {
        return locale;
    }

}
