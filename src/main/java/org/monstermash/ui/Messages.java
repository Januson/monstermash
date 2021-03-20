package org.monstermash.ui;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class Messages {

    private final Languages languages;

    public Messages(final Languages languages) {
        this.languages = languages;
    }

    /**
     * Gets the string with the given key from the resource bundle for the current locale and uses it as first argument
     * to MessageFormat.format, passing in the optional args and returning the result.
     *
     * @param key  message key
     * @param args optional arguments for the message
     * @return localized formatted string
     */
    public String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("/bundles/messages", this.languages.get());
        return MessageFormat.format(bundle.getString(key), args);
    }

}