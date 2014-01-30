package se.trixon.almond;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum AlmondOptions {

    INSTANCE;
    public static final boolean DEFAULT_ALWAYS_ON_TOP = false;
    public static final boolean DEFAULT_CONFIRM_EXIT = true;
    public static final String KEY_ALWAYS_ON_TOP = "alwaysOnTop";
    public static final String KEY_CONFIRM_EXIT = "confirmExit";
    private static Preferences mPreferences = NbPreferences.forModule(AlmondOptions.class);

    public static Preferences getPreferences() {
        return mPreferences;
    }

    public boolean getAlwaysOnTop() {
        return mPreferences.getBoolean(KEY_ALWAYS_ON_TOP, DEFAULT_ALWAYS_ON_TOP);
    }

    public boolean getConfirmExit() {
        return mPreferences.getBoolean(KEY_CONFIRM_EXIT, DEFAULT_CONFIRM_EXIT);
    }

    public void setAlwaysOnTop(boolean alwaysOnTop) {
        mPreferences.putBoolean(KEY_ALWAYS_ON_TOP, alwaysOnTop);
    }

    public void setConfirmExit(boolean confirmExit) {
        mPreferences.putBoolean(KEY_CONFIRM_EXIT, confirmExit);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }
}
