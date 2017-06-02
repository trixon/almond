/*
 * Copyright 2017 Patrik Karlsson.
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "se.trixon.almond.nbp.actions.QuitAction"
)
@ActionRegistration(
        displayName = "#CTL_QuitAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 2700)
    ,
    @ActionReference(path = "Shortcuts", name = "D-Q")
})
@Messages("CTL_QuitAction=Quit")
public final class QuitAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        LifecycleManager.getDefault().exit();
    }
}
