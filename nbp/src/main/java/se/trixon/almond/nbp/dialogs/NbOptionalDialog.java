/*
 * Copyright 2021 Patrik Karlström.
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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JCheckBox;
import org.apache.commons.lang3.StringUtils;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbPreferences;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.OptionsBase;

/**
 *
 * @author Patrik Karlström
 */
public class NbOptionalDialog {

    private static final Options sOptions = new Options();

    public static boolean requestShowDialog(Class cls, String id, int messageType, String title, String message, String checkBoxText) {
        String key = cls.getName() + "_" + id;

        if (sOptions.is(key, true)) {
            var checkBox = new JCheckBox(StringUtils.defaultString(checkBoxText, Dict.SHOW_THIS_AGAIN.toString()));
            checkBox.setSelected(true);

            String[] options = new String[]{Dict.CLOSE.toString()};

            var d = new DialogDescriptor(
                    message,
                    title,
                    true,
                    options,
                    Dict.CLOSE.toString(),
                    0,
                    null,
                    null
            );

            d.setMessageType(messageType);
            d.setAdditionalOptions(new Object[]{checkBox});
            d.setClosingOptions(options);

            DialogDisplayer.getDefault().notify(d);

            sOptions.put(key, checkBox.isSelected());

            return true;
        }

        return false;
    }

    public static void reset(String key) {
        sOptions.put(key, true);
    }

    public static void resetAll() {
        try {
            sOptions.getPreferences().clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(NbOptionalDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setPreferences(Preferences preferences) {
        sOptions.setPreferences(preferences);
    }

    public static class Options extends OptionsBase {

        public Options() {
            setPreferences(NbPreferences.forModule(NbOptionalDialog.class).node("optionalDialogState"));
        }
    }
}
