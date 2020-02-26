/*
 * Copyright 2019 Patrik Karlström.
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

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Patrik Karlström
 */
public class PrefsHelper {

    public static boolean keyExists(Preferences preferences, String key) {
        return preferences.get(key, null) != null;
    }

    public static void put(Preferences preferences, String key, String value) {
        preferences.put(key, value);
    }

    /**
     * Put value for key if missing in preferences
     *
     * @param preferences
     * @param key
     * @param value
     * @return
     * @throws BackingStoreException
     */
    public static boolean putIfAbsent(Preferences preferences, String key, String value) throws BackingStoreException {
        if (!ArrayUtils.contains(preferences.keys(), key)) {
            put(preferences, key, value);
            return true;
        } else {
            return false;
        }
    }

}
