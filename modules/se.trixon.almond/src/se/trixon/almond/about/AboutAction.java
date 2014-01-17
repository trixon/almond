package se.trixon.almond.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public final class AboutAction implements ActionListener {

    private static ResourceBundle sAboutBundle = null;
    private static ImageIcon sImageIcon = null;
    private static ResourceBundle sLicenseBundle = null;

    public static void setAboutBundle(ResourceBundle resourceBundle) {
        sAboutBundle = resourceBundle;
    }

    public static void setImageIcon(ImageIcon imageIcon) {
        AboutAction.sImageIcon = imageIcon;
    }

    public static void setLicenseBundle(ResourceBundle resourceBundle) {
        sLicenseBundle = resourceBundle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sImageIcon == null) {
            sImageIcon = ImageUtilities.loadImageIcon("se/trixon/almond/about/res/default_logo.png", false);
        }

        String title = String.format(NbBundle.getMessage(License.class, "CTL_DialogTitleAbout"), sAboutBundle.getString("application.title"));
        AboutPanel aboutPanel = new AboutPanel(sAboutBundle, sImageIcon, sLicenseBundle);
        DialogDescriptor dialogDescriptor = new DialogDescriptor(
                aboutPanel,
                title,
                false,
                new Object[]{DialogDescriptor.CLOSED_OPTION},
                DialogDescriptor.CLOSED_OPTION,
                DialogDescriptor.DEFAULT_ALIGN,
                null,
                null);
        DialogDisplayer.getDefault().notify(dialogDescriptor);
    }
}
