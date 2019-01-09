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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import se.trixon.almond.util.AlmondOptions.AlmondOptionsEvent;
import se.trixon.almond.util.AlmondOptions.AlmondOptionsWatcher;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class AlmondUI extends AlmondGui {

    private final ArrayList<AlmondOptionsWatcher> mAlmondOptionsWatchers = new ArrayList<>();
    private JFrame mFrame = null;
    protected final AlmondOptions mAlmondOptions = AlmondOptions.getInstance();

    public static AlmondUI getInstance() {
        return Holder.INSTANCE;
    }

    private AlmondUI() {
        if (IS_MAC) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        mAlmondOptions.getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            String key = evt.getKey();
            final AlmondOptionsEvent almondOptionsEvent;

            if (key.equalsIgnoreCase(AlmondOptions.KEY_MENU_ICONS)) {
                almondOptionsEvent = AlmondOptionsEvent.MENU_ICONS;
            } else if (key.equalsIgnoreCase(AlmondOptions.KEY_FORCE_LOOK_AND_FEEL)
                    || key.equalsIgnoreCase(AlmondOptions.KEY_LOOK_AND_FEEL)) {
                almondOptionsEvent = AlmondOptionsEvent.LOOK_AND_FEEL;
            } else if (key.equalsIgnoreCase(AlmondOptions.KEY_ICON_THEME)) {
                almondOptionsEvent = AlmondOptionsEvent.ICON_THEME;
            } else if (key.equalsIgnoreCase(AlmondOptions.KEY_MENU_MODE)) {
                almondOptionsEvent = AlmondOptionsEvent.MENU_MODE;
            } else {
                almondOptionsEvent = null;
            }

            initClientOption(almondOptionsEvent);
            notifyOptionWatchers(almondOptionsEvent);
        });
    }

    public void addOptionsWatcher(AlmondOptionsWatcher almondOptionsWatcher) {
        mAlmondOptionsWatchers.add(almondOptionsWatcher);
    }

    public void addWindowWatcher(JFrame frame) {
        mFrame = frame;

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingHelper.frameStateSave(frame);
            }

            @Override
            public void windowOpened(WindowEvent e) {
//                try {
//                    SwingHelper.frameStateRestore(frame, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
//                } catch (BackingStoreException ex) {
//                    Logger.getLogger(AlmondUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });

        try {
            SwingHelper.frameStateRestore(frame, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        } catch (BackingStoreException ex) {
            Logger.getLogger(AlmondUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JFrame getFrame() {
        return mFrame;
    }

    public void initLookAndFeel() {
        if (mAlmondOptions.isForceLookAndFeel()) {
            try {
                UIManager.setLookAndFeel(SwingHelper.getLookAndFeelClassName(mAlmondOptions.getLookAndFeel()));
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Xlog.timedErr(ex.getMessage());
            }
        }
    }

    public void initoptions() {
        for (AlmondOptionsEvent optionsEvent : AlmondOptionsEvent.values()) {
            initClientOption(optionsEvent);
            notifyOptionWatchers(optionsEvent);
        }
    }

    public void installDarcula() {
        UIManager.installLookAndFeel("Darcula", "com.bulenkov.darcula.DarculaLaf");
    }

    private void initClientOption(AlmondOptionsEvent almondOptionsEvent) {
        switch (almondOptionsEvent) {
            case LOOK_AND_FEEL:
                if (mAlmondOptions.isForceLookAndFeel()) {
                    try {
                        UIManager.setLookAndFeel(SwingHelper.getLookAndFeelClassName(mAlmondOptions.getLookAndFeel()));
                        if (mAlmondOptions.getLookAndFeel().equalsIgnoreCase("Darcula")) {
                            int iconSize = ICON_SIZE_LARGE;
                            UIDefaults uiDefaults = UIManager.getLookAndFeelDefaults();
                            uiDefaults.put("OptionPane.informationIcon", MaterialIcon._Action.INFO_OUTLINE.get(iconSize, IconColor.WHITE));
                            uiDefaults.put("OptionPane.errorIcon", MaterialIcon._Alert.ERROR_OUTLINE.get(iconSize, IconColor.WHITE));
                            uiDefaults.put("OptionPane.questionIcon", MaterialIcon._Action.HELP_OUTLINE.get(iconSize, IconColor.WHITE));
                            uiDefaults.put("OptionPane.warningIcon", MaterialIcon._Alert.WARNING.get(iconSize, IconColor.WHITE));
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        Xlog.timedErr(ex.getMessage());
                    }
                }
                break;
        }
    }

    private void notifyOptionWatchers(AlmondOptionsEvent almondOptionsEvent) {
        SwingUtilities.invokeLater(() -> {
            mAlmondOptionsWatchers.stream().forEach((optionsWatcher) -> {
                try {
                    optionsWatcher.onAlmondOptions(almondOptionsEvent);
                } catch (Exception e) {
                }
            });
        });
    }

    private static class Holder {

        private static final AlmondUI INSTANCE = new AlmondUI();
    }
}
