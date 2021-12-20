/*
 * Copyright 2020 Patrik Karlström.
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

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import se.trixon.almond.nbp.NbHelper;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.SystemInformation;
import se.trixon.almond.util.swing.LogPanel;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class NbSystemInformation {

    private SystemInformation mSystemInformation;

    public void displayDialog() {
        var progressHandle = NbHelper.createAndStartProgressHandle(Dict.SYSTEM_INFORMATION.toString(), true);
        var logPanel = new LogPanel();
        if (mSystemInformation == null) {
            mSystemInformation = new SystemInformation();
        }
        logPanel.println(mSystemInformation.generateSystemInformation());
        logPanel.scrollToTop();
        logPanel.getTextArea().setEditable(true);
        logPanel.setPreferredSize(SwingHelper.getUIScaledDim(800, 600));
        progressHandle.finish();

        var d = new NotifyDescriptor(
                logPanel,
                Dict.SYSTEM_INFORMATION.toString(),
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                new String[]{Dict.COPY.toString(), Dict.CLOSE.toString()},
                Dict.CLOSE.toString());

        if (Dict.COPY.toString() == DialogDisplayer.getDefault().notify(d)) {
            SystemHelper.copyToClipboard(logPanel.getText());
        }
    }
}
