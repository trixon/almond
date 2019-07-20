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

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import org.apache.commons.lang3.SystemUtils;
import se.trixon.almond.util.swing.dialogs.MenuModePanel;
import se.trixon.almond.util.swing.dialogs.Message;

/**
 *
 * @author Patrik Karlström
 */
public class AlmondOptions {

    public static final String KEY_FORCE_LOOK_AND_FEEL = "forceLookAndFeel";
    public static final String KEY_ICON_THEME = "lookAndFeelIcons";
    public static final String KEY_LOOK_AND_FEEL = "lookAndFeel";
    public static final String KEY_MENU_ICONS = "displayMenuIcons";
    public static final String KEY_MENU_MODE = "menu_mode";

    private static final boolean DEFAULT_FORCE_LOOK_AND_FEEL = true;
    private static final boolean DEFAULT_MENU_ICONS = !SystemUtils.IS_OS_MAC;

    private static final MenuModePanel.MenuMode DEFAULT_MENU_MODE = SystemUtils.IS_OS_MAC ? MenuModePanel.MenuMode.BAR : MenuModePanel.MenuMode.BUTTON;
    private Color mButtonBackground;
    private Preferences mPreferences = Preferences.userNodeForPackage(AlmondOptions.class);
    private boolean mRestartRequired;

    public static AlmondOptions getInstance() {
        return Holder.INSTANCE;
    }

    private AlmondOptions() {
        mPreferences.addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (!mRestartRequired) {
                mRestartRequired = true;
                Message.information(AlmondUI.getInstance().getFrame(),
                        Dict.Dialog.TITLE_RESTART_REQUIRED.toString(),
                        Dict.Dialog.MESSAGE_RESTART_REQUIRED.toString()
                );
            }

            try {
                mPreferences.flush();
            } catch (BackingStoreException ex) {
                Logger.getLogger(AlmondOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public int getIconTheme() {
        return mPreferences.getInt(KEY_ICON_THEME, getDefaultIconTheme());
    }

    public String getLookAndFeel() {
        return mPreferences.get(KEY_LOOK_AND_FEEL, getDefaultLookAndFeel());
    }

    public MenuModePanel.MenuMode getMenuMode() {
        return MenuModePanel.MenuMode.values()[mPreferences.getInt(KEY_MENU_MODE, DEFAULT_MENU_MODE.ordinal())];
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public boolean isDisplayMenuIcons() {
        return mPreferences.getBoolean(KEY_MENU_ICONS, DEFAULT_MENU_ICONS);
    }

    public boolean isForceLookAndFeel() {
        return mPreferences.getBoolean(KEY_FORCE_LOOK_AND_FEEL, DEFAULT_FORCE_LOOK_AND_FEEL);
    }

    public boolean isMacLookAndFeel() {
        return (SystemUtils.IS_OS_MAC && getLookAndFeel().equalsIgnoreCase("system")) || getLookAndFeel().equalsIgnoreCase("mac os x");
    }

    public void setDisplayMenuIcons(boolean value) {
        if (value != isDisplayMenuIcons()) {
            mPreferences.putBoolean(KEY_MENU_ICONS, value);
        }
    }

    public void setForceLookAndFeel(boolean value) {
        if (value != isForceLookAndFeel()) {
            mPreferences.putBoolean(KEY_FORCE_LOOK_AND_FEEL, value);
        }
    }

    public void setIconTheme(int value) {
        if (value != getIconTheme()) {
            mPreferences.putInt(KEY_ICON_THEME, value);
        }
    }

    public void setLookAndFeel(String value) {
        if (!value.equalsIgnoreCase(getLookAndFeel())) {
            mPreferences.put(KEY_LOOK_AND_FEEL, value);
        }
    }

    public void setMenuMode(MenuModePanel.MenuMode menuMode) {
        if (menuMode != getMenuMode()) {
            mPreferences.putInt(KEY_MENU_MODE, menuMode.ordinal());
        }
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }

    private int getDefaultIconTheme() {
        if (getLookAndFeel().equalsIgnoreCase("darcula")) {
            return 1;
        } else {
            return 0;
        }
    }

    private String getDefaultLookAndFeel() {
        return "System";
    }

    public enum AlmondOptionsEvent {

        ICON_THEME,
        LOOK_AND_FEEL,
        MENU_ICONS,
        MENU_MODE;
    }

    public interface AlmondOptionsWatcher {

        void onAlmondOptions(AlmondOptionsEvent almondOptionsEvent);
    }

    private static class Holder {

        private static final AlmondOptions INSTANCE = new AlmondOptions();
    }
}
