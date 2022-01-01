/* 
 * Copyright 2022 Patrik Karlström.
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
import javafx.application.Platform;
import javax.swing.BorderFactory;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.nbp.dialogs.NbAboutFx;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.swing.dialogs.about.AboutPanel;

/**
 *
 * @author Patrik Karlström
 */
public final class AboutAction implements ActionListener {

    private static AboutModel sAboutModel;
    private static boolean sFx;

    public static void setAboutModel(AboutModel aboutModel) {
        sAboutModel = aboutModel;
    }

    public static void setFx(boolean fx) {
        sFx = fx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sFx) {
            Platform.runLater(() -> {
                Almond.activateWindow(false);
                NbAboutFx nbAboutFx = new NbAboutFx(sAboutModel);
                nbAboutFx.display();
                Almond.activateWindow(true);
            });
        } else {
            AboutPanel aboutPanel = new AboutPanel(sAboutModel);
            aboutPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));

            DialogDescriptor dialogDescriptor = new DialogDescriptor(
                    aboutPanel,
                    String.format(Dict.ABOUT_S.toString(), sAboutModel.getAppName()),
                    false,
                    new Object[]{NotifyDescriptor.CLOSED_OPTION},
                    NotifyDescriptor.CLOSED_OPTION,
                    DialogDescriptor.DEFAULT_ALIGN,
                    null,
                    null);

            DialogDisplayer.getDefault().notify(dialogDescriptor);
        }
    }
}
