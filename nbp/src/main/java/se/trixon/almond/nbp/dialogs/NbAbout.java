/*
 * Copyright 2023 Patrik Karlström.
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
package se.trixon.almond.nbp.dialogs;

import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.swing.SwingHelper;
import se.trixon.almond.util.swing.dialogs.about.AboutPanel;

/**
 *
 * @author Patrik Karlström
 */
public class NbAbout {

    private final AboutModel mAboutModel;
    private final AboutPanel mAboutPanel;

    public NbAbout(AboutModel aboutModel) {
        mAboutModel = aboutModel;
        mAboutPanel = new AboutPanel(aboutModel);
        int inset = 16;
        mAboutPanel.setBorder(new EmptyBorder(SwingHelper.getUIScaledInsets(inset, inset, 0, inset)));
    }

    public void display() {
        var d = new DialogDescriptor(
                mAboutPanel,
                Dict.ABOUT_S.toString().formatted(mAboutModel.getAppName()),
                true,
                new Object[]{Dict.CLOSE.toString()},
                Dict.CLOSE.toString(),
                0,
                null,
                null
        );

        DialogDisplayer.getDefault().notify(d);
        SwingUtilities.invokeLater(() -> {
            mAboutPanel.setPreferredSize(SwingHelper.getUIScaledDim(700, 400));
        });
    }
}
