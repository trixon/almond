package se.trixon.almond;

import java.awt.Frame;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import se.trixon.almond.dialogs.ConfirmExitDialog;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    private final AlmondOptions mOptions = AlmondOptions.INSTANCE;
    private Frame mFrame;

    @Override
    public boolean closing() {
        boolean exit = true;

        if (mOptions.getConfirmExit()) {
            Object result = DialogDisplayer.getDefault().notify(ConfirmExitDialog.getDescriptor());
            exit = result == NotifyDescriptor.OK_OPTION;
        }

        return exit;
    }

    @Override
    public void restored() {
        AlmondOptions.getPreferences().addPreferenceChangeListener(new PreferenceChangeListener() {

            @Override
            public void preferenceChange(PreferenceChangeEvent evt) {
                if (evt.getKey().equalsIgnoreCase(AlmondOptions.KEY_ALWAYS_ON_TOP)) {
                    mFrame.setAlwaysOnTop(mOptions.getAlwaysOnTop());
                }
            }
        });

        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                mFrame = WindowManager.getDefault().getMainWindow();
                mFrame.setAlwaysOnTop(mOptions.getAlwaysOnTop());
            }
        });
    }
}
