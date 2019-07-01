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
package se.trixon.almond.nbp;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.commons.lang3.ArrayUtils;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlström
 */
public class DarculaDefaultsManager {

    private final Preferences mPreferences = NbPreferences.forModule(DarculaDefaultsManager.class).node("darcula");

    public static DarculaDefaultsManager getInstance() {
        return Holder.INSTANCE;

    }

    private DarculaDefaultsManager() {
    }

    public void put(String key, String value) {
        mPreferences.put(key, value);
    }

    public void putIfAbsent(String key, String value) {
        try {
            if (!ArrayUtils.contains(mPreferences.keys(), key)) {
                put(key, value);
            }
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static class Holder {

        private static final DarculaDefaultsManager INSTANCE = new DarculaDefaultsManager();
    }
}
