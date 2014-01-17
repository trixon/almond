package se.trixon.almond.about;

import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class License {

    private final LicensePanel mLicensePanel;

    private License(String license) {
        mLicensePanel = new LicensePanel(license);
        showIt();
    }

    private void showIt() {
        String dialogTitle = NbBundle.getMessage(License.class, "CTL_DialogTitleLicense");

        DialogDescriptor dialogDescriptor = new DialogDescriptor(
                mLicensePanel,
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
