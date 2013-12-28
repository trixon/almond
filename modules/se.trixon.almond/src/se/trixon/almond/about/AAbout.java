package se.trixon.almond.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AAbout {

    private static AAbout about;
    private static AAbout aboutAlmond;
    private static boolean notAboutAlmond;
    private final JButton almondButton = new JButton("Almond");
    private AAboutUI aboutUI;
    private ResourceBundle resourceBundle;
    private DialogDescriptor dialogDescriptor;
    private ImageIcon imageIcon;
    private ResourceBundle licenseResourceBundle;

    private AAbout(ResourceBundle aResourceBundle, ImageIcon anImageIcon, ResourceBundle aLicenseResourceBundle) {
        resourceBundle = aResourceBundle;
        imageIcon = anImageIcon;
        licenseResourceBundle = aLicenseResourceBundle;
        showIt();
    }

    public static synchronized AAbout showDialog(ResourceBundle aResourceBundle, ImageIcon anImageIcon, ResourceBundle aLicenseResourceBundle) {
        notAboutAlmond = true;
        if (about == null) {
            about = new AAbout(aResourceBundle, anImageIcon, aLicenseResourceBundle);
        } else {
            about.showIt();
        }

        return about;
    }

    public static synchronized AAbout showDialogAlmond(ResourceBundle aResourceBundle, ImageIcon anImageIcon, ResourceBundle aLicenseResourceBundle) {
        notAboutAlmond = false;
        if (aboutAlmond == null) {
            aboutAlmond = new AAbout(aResourceBundle, anImageIcon, aLicenseResourceBundle);
        } else {
            aboutAlmond.showIt();
        }

        return aboutAlmond;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private void showIt() {
        String dialogTitle = String.format(NbBundle.getMessage(ALicense.class,
                "CTL_DialogTitleAbout"), resourceBundle.getString("application.title"));
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == almondButton) {
                    try {
                        ResourceBundle aboutBundle = ResourceBundle.getBundle("se/trixon/almond/about/about");
                        ResourceBundle licenseBundle = ResourceBundle.getBundle("se/trixon/almond/about/license");
                        ImageIcon almondImageIcon = ImageUtilities.loadImageIcon("se/trixon/almond/about/res/default_logo.png", false);

                        AAbout.showDialogAlmond(aboutBundle, almondImageIcon, licenseBundle);
                    } catch (java.util.MissingResourceException exception) {
                        System.err.println(exception.getMessage());
                    }

                }
            }
        };

        if (dialogDescriptor == null || aboutUI.getTopLevelAncestor() == null) {
            aboutUI = new AAboutUI(resourceBundle, imageIcon, licenseResourceBundle);

            dialogDescriptor = new DialogDescriptor(
                    aboutUI,
                    dialogTitle,
                    false,
                    new Object[]{DialogDescriptor.CLOSED_OPTION},
                    DialogDescriptor.CLOSED_OPTION,
                    DialogDescriptor.DEFAULT_ALIGN,
                    null,
                    actionListener);
            if (notAboutAlmond) {
                dialogDescriptor.setAdditionalOptions(new Object[]{almondButton});
            }
            DialogDisplayer.getDefault().notify(dialogDescriptor);
        } else {
            aboutUI.getTopLevelAncestor().setVisible(true);
        }
    }
}
