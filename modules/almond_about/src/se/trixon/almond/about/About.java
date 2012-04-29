package se.trixon.almond.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public final class About implements ActionListener {

  private static ResourceBundle aboutBundle = null;
  private static ResourceBundle licenseBundle = null;
  private static ImageIcon imageIcon = null;

  public static void setAboutBundle(ResourceBundle aResourceBundle) {
    aboutBundle = aResourceBundle;
  }

  public static void setImageIcon(ImageIcon imageIcon) {
    About.imageIcon = imageIcon;
  }

  public static void setLicenseBundle(ResourceBundle aResourceBundle) {
    licenseBundle = aResourceBundle;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      if (aboutBundle == null) {
        aboutBundle = ResourceBundle.getBundle("se/trixon/almond/about/about");
      }

      if (licenseBundle == null) {
        licenseBundle = ResourceBundle.getBundle("se/trixon/almond/about/license");
      }

      if (imageIcon == null) {
        imageIcon = ImageUtilities.loadImageIcon("se/trixon/almond/about/res/default_logo.png", false);
      }

      AAbout.showDialog(aboutBundle, imageIcon, licenseBundle);
    } catch (java.util.MissingResourceException exception) {
      System.err.println(exception.getMessage());
    }
  }
}
