package se.trixon.almond;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum AlmondOptions {

    INSTANCE;
    private static final boolean DEFAULT_CONFIRM_EXIT = true;
    private static final String KEY_CONFIRM_EXIT = "confirmExit";
    private Preferences mPreferences = NbPreferences.forModule(AlmondOptions.class);

    public boolean getConfirmExit() {
        return mPreferences.getBoolean(KEY_CONFIRM_EXIT, DEFAULT_CONFIRM_EXIT);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setConfirmExit(boolean confirmExit) {
        mPreferences.putBoolean(KEY_CONFIRM_EXIT, confirmExit);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }
}
