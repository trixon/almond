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
package se.trixon.almond.nbp;

import java.awt.Frame;
import java.util.prefs.PreferenceChangeEvent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.dialogs.ConfirmExitDialog;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    private final AlmondOptions mOptions = AlmondOptions.INSTANCE;
    private Frame mFrame;

    @Override
    public boolean closing() {
        boolean exit = true;

        if (Almond.ASK_CONFIRM_EXIT && mOptions.getConfirmExit()) {
            Object result = DialogDisplayer.getDefault().notify(ConfirmExitDialog.getDescriptor());
            exit = result == NotifyDescriptor.OK_OPTION;
        }

        return exit;
    }

    @Override
    public void restored() {
        AlmondOptions.getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equalsIgnoreCase(AlmondOptions.KEY_ALWAYS_ON_TOP)) {
                mFrame.setAlwaysOnTop(mOptions.getAlwaysOnTop());
            }
        });

        WindowManager.getDefault().invokeWhenUIReady(() -> {
            mFrame = WindowManager.getDefault().getMainWindow();
            mFrame.setAlwaysOnTop(mOptions.getAlwaysOnTop());
        });
    }
}
