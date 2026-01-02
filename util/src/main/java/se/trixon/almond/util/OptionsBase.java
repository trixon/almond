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
package se.trixon.almond.util;

import com.dlsc.gemsfx.util.SessionManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author Patrik Karlström
 */
public abstract class OptionsBase {

    public static final boolean DEFAULT_FULL_SCREEN = false;

    public static final String KEY_APP_FIRST_RUN = "app.first_run";
    public static final String KEY_APP_START_COUNTER = "app.start_counter";
    public static final String KEY_FULL_SCREEN = "fullscreen";
    protected Preferences mPreferences;
    private SessionManager mSessionManager;

    public OptionsBase() {
    }

    public String get(String key, String... def) {
        return mPreferences.get(key, def == null || def.length == 0 ? "" : def[0]);
    }

    public double getDouble(String key, Double... def) {
        return mPreferences.getDouble(key, def == null || def.length == 0 ? 0 : def[0]);
    }

    public float getFloat(String key, Float... def) {
        return mPreferences.getFloat(key, def == null || def.length == 0 ? 0 : def[0]);
    }

    public int getInt(String key, Integer... def) {
        return mPreferences.getInt(key, def == null || def.length == 0 ? 0 : def[0]);
    }

    /**
     *
     * <p>
     * @param key
     * @return a key with simple class name as prefix
     */
    public String getKey(String key) {
        return "%s.%s".formatted(getClass().getSimpleName(), key);
    }

    /**
     *
     * <p>
     * @param key
     * @return a key with simple class name and options as prefix
     */
    public String getKeyOptions(String key) {
        return "options.%s.%s".formatted(getClass().getSimpleName(), key);
    }

    public long getLong(String key, Long... def) {
        return mPreferences.getLong(key, def == null || def.length == 0 ? 0 : def[0]);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public SessionManager getSessionManager() {
        return mSessionManager;
    }

    public SessionManager initSession(Preferences preferences) {
        var sessionManager = new SessionManager(preferences);
        initSession(sessionManager);

        return sessionManager;
    }

    public void initSession(SessionManager sessionManager) {
    }

    public boolean invert(String key, boolean def) {
        put(key, !is(key, def));
        return is(key);
    }

    public boolean is(String key, boolean... def) {
        return mPreferences.getBoolean(key, def == null || def.length == 0 ? true : def[0]);
    }

    public void put(String key, boolean value) {
        mPreferences.putBoolean(key, value);
    }

    public void put(String key, int value) {
        mPreferences.putInt(key, value);
    }

    public void put(String key, long value) {
        mPreferences.putLong(key, value);
    }

    public void put(String key, double value) {
        mPreferences.putDouble(key, value);
    }

    public void put(String key, float value) {
        mPreferences.putFloat(key, value);
    }

    public void put(String key, String value) {
        mPreferences.put(key, value);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
        mSessionManager = new SessionManager(preferences);
        initSession(mSessionManager);
    }

    public void sync() {
        try {
            mPreferences.sync();
        } catch (BackingStoreException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
