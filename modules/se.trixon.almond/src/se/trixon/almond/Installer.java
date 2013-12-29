package se.trixon.almond;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;
import se.trixon.almond.dialogs.ConfirmExitDialog;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    @Override
    public boolean closing() {
        boolean exit = true;

        if (AlmondOptions.INSTANCE.getConfirmExit()) {
            Object result = DialogDisplayer.getDefault().notify(ConfirmExitDialog.getDescriptor());
            exit = result == NotifyDescriptor.OK_OPTION;
        }

        return exit;
    }
}
