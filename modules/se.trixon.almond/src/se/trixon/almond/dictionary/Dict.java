package se.trixon.almond.dictionary;

import java.util.Locale;
import java.util.ResourceBundle;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Dict {

    CATEGORY, CONTAINS, COPYRIGHT, DATE, DESCRIPTION, DESTINATION, ENDS_WITH, FILE_EXISTS_MESSAGE, FILE_EXISTS_TITLE, FILE_NOT_FOUND_MESSAGE, FILE_NOT_FOUND_TITLE, INFORMATION, LICENSE, NAME, PATH, PROJECT, ROWS_NOT_SELECTED_MESSAGE, ROWS_NOT_SELECTED_TITLE, SEARCHING_IN, SIZE, SOURCE, STARTS_WITH, TASK_COMPLETED, TYPE, VERSION;
    private final ResourceBundle mDefaultResourceBundle = ResourceBundle.getBundle("se/trixon/almond/dictionary/Bundle", Locale.getDefault());
    private final ResourceBundle mResourceBundle = NbBundle.getBundle(Dict.class);

    public String getString() {
        return mResourceBundle.getString(name().toLowerCase());
    }

    public String getStringDefault() {
        return mDefaultResourceBundle.getString(name().toLowerCase());
    }
}
