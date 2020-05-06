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
package se.trixon.almond.nbp.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(
        category = "View",
        id = "se.trixon.almond.nbp.StayOnTopAction"
)
@ActionRegistration(
        displayName = "#CTL_StayOnTopAction"
)
@Messages("CTL_StayOnTopAction=Always on top")
public final class StayOnTopAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame frame = WindowManager.getDefault().getMainWindow();
        frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
    }
}
