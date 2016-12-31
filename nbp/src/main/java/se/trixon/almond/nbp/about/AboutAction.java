/* 
 * Copyright 2016 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.almond.nbp.about;

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
 * @author Patrik Karlsson
 */
public final class AboutAction implements ActionListener {

    private static ResourceBundle sAboutBundle = null;
    private static ImageIcon sImageIcon = null;
    private static ResourceBundle sLicenseBundle = null;

    public static ResourceBundle getAboutBundle() {
        return sAboutBundle;
    }

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
            sImageIcon = ImageUtilities.loadImageIcon("se/trixon/almond/nbp/about/default_logo.png", false);
        }

        String appTitle = sAboutBundle.getString("application.title");
        String title = NbBundle.getMessage(License.class, "CTL_DialogTitleAbout", appTitle);
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
