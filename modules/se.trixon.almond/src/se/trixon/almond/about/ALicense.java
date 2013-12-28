package se.trixon.almond.about;

import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ALicense {

    private ALicensePanel licensePanel;

    private ALicense(String aLicense) {
        licensePanel = new ALicensePanel(aLicense);
        showIt();
    }

    public static void showDialog(String aLicense) {
        ALicense license = new ALicense(aLicense);
    }

    private void showIt() {
        String dialogTitle = NbBundle.getMessage(ALicense.class, "CTL_DialogTitleLicense");

        DialogDescriptor dialogDescriptor = new DialogDescriptor(
                licensePanel,
                dialogTitle,
                true,
                new Object[]{DialogDescriptor.CLOSED_OPTION},
                DialogDescriptor.CLOSED_OPTION,
                DialogDescriptor.DEFAULT_ALIGN,
                null,
                null);

        DialogDisplayer.getDefault().notify(dialogDescriptor);
    }
}
