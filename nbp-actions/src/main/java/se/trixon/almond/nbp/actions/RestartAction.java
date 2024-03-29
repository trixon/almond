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
        id = "se.trixon.almond.nbp.actions.RestartAction"
)
@ActionRegistration(
        displayName = "#CTL_RestartAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 2699),})
@Messages("CTL_RestartAction=&Restart")
public final class RestartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        LifecycleManager.getDefault().markForRestart();
        LifecycleManager.getDefault().exit();
    }
}
