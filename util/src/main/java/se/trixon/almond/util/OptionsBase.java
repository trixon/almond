/*
 * Copyright 2018 Patrik Karlström.
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
package se.trixon.almond.util;

import java.util.prefs.Preferences;

/**
 *
 * @author Patrik Karlström
 */
public abstract class OptionsBase {

    protected Preferences mPreferences;

    public OptionsBase() {
    }

    public String get(String key, String... def) {
        return mPreferences.get(key, def == null || def.length == 0 ? "" : def[0]);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public boolean is(String key, boolean... def) {
        return mPreferences.getBoolean(key, def == null || def.length == 0 ? true : def[0]);
    }

    public void set(String key, boolean value) {
        mPreferences.putBoolean(key, value);
    }

    public void set(String key, String value) {
        mPreferences.put(key, value);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }

}
