package se.trixon.almond.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Dict {

    DATE, NAME, PATH, SIZE, TYPE, VERSION;
    private final ResourceBundle mDefaultResourceBundle = ResourceBundle.getBundle("se/trixon/almond/util/Dict", Locale.getDefault());
    private final ResourceBundle mResourceBundle = ResourceBundle.getBundle("se/trixon/almond/util/Dict");

    public String getString() {
        return mResourceBundle.getString(name().toLowerCase());
    }

    public String getStringDefault() {
        return mDefaultResourceBundle.getString(name().toLowerCase());
    }
}
