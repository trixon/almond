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
package se.trixon.almond.nbp;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import se.trixon.almond.nbp.output.OutputLineMode;
import se.trixon.almond.util.PrefsHelper;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström
 */
public class NbHelper {

    private static final Preferences sLafPreferences = NbPreferences.root().node("laf");

    public static ProgressHandle createAndStartProgressHandle(String displayName, boolean indeterminate) {
        var progressHandle = ProgressHandle.createHandle(displayName);
        progressHandle.start();
        if (indeterminate) {
            progressHandle.switchToIndeterminate();
        }

        return progressHandle;
    }

    public static Preferences getLafPreferences() {
        return sLafPreferences;
    }

    public static boolean isNightMode() {
        return StringUtils.containsIgnoreCase(sLafPreferences.get("laf", ""), "dark");
    }

    public static void initNightModeIfNeeded() {
        if (isNightMode()) {
            FxHelper.setDarkThemeEnabled(true);
            var color = FxHelper.getFillColorForDarkTheme();
            MaterialIcon.setDefaultColor(color);
            se.trixon.almond.util.icons.material.swing.MaterialIcon.setDefaultColor(FxHelper.colorToColor(color));

            OutputLineMode.setNightMode(true);
        }

    }

    public static void setLafAccentColor(String color) {
        try {
            var preferences = NbPreferences.root().node("org/netbeans/swing/laf/flatlaf");
            PrefsHelper.putIfAbsent(preferences, "accentColor", color);
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static void setLafDefault(String mode) {
        try {
            var defaultLAF = !SystemUtils.IS_OS_MAC ? "com.formdev.flatlaf.Flat" + mode + "Laf" : "com.formdev.flatlaf.themes.FlatMac" + mode + "Laf";
            PrefsHelper.putIfAbsent(sLafPreferences, "laf", defaultLAF);
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
