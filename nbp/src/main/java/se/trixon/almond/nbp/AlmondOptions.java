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

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlström
 */
public enum AlmondOptions {

    INSTANCE;
    public static final boolean DEFAULT_ALWAYS_ON_TOP = false;
    public static final boolean DEFAULT_CONFIRM_EXIT = true;
    public static final String KEY_ALWAYS_ON_TOP = "alwaysOnTop";
    public static final String KEY_CONFIRM_EXIT = "confirmExit";
    private Preferences mPreferences = NbPreferences.forModule(AlmondOptions.class);

    public boolean getAlwaysOnTop() {
        return mPreferences.getBoolean(KEY_ALWAYS_ON_TOP, DEFAULT_ALWAYS_ON_TOP);
    }

    public boolean getConfirmExit() {
        return mPreferences.getBoolean(KEY_CONFIRM_EXIT, DEFAULT_CONFIRM_EXIT);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setAlwaysOnTop(boolean alwaysOnTop) {
        mPreferences.putBoolean(KEY_ALWAYS_ON_TOP, alwaysOnTop);
    }

    public void setConfirmExit(boolean confirmExit) {
        mPreferences.putBoolean(KEY_CONFIRM_EXIT, confirmExit);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }
}
