/*
 * Copyright 2022 Patrik Karlström.
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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.text.WordUtils;

/**
 *
 * @author Patrik Karlström
 */
public abstract class AlmondActionManager {

    public static final String ABOUT = "about";
    public static final String ABOUT_DATE_FORMAT = "about_date_format";
    public static final String ADD = "add";
    public static final String CANCEL = "cancel";
    public static final String CLEAR = "clear";
    public static final String CLONE = "clone";
    public static final String CLOSE = "close";
    public static final String HELP = "help";
    public static final String MENU = "menu";
    public static final String NEW = "new";
    public static final String OPEN = "open";
    public static final String OPTIONS = "options";
    public static final String PROPERTIES = "properties";
    public static final String QUIT = "shutdownServerAndWindow";
    public static final String REDO = "redo";
    public static final String REMOVE = "remove";
    public static final String REMOVE_ALL = "remove_all";
    public static final String RENAME = "rename";
    public static final String SAVE = "save";
    public static final String SAVE_AS = "save_as";
    public static final String START = "start";
    public static final String UNDO = "undo";

    protected static final boolean IS_MAC = SystemUtils.IS_OS_MAC;

    protected ActionMap mActionMap;
    protected final LinkedList<AlmondAction> mActions = new LinkedList<>();
    protected final AlmondOptions mAlmondOptions = AlmondOptions.getInstance();
    protected final LinkedList<AlmondAction> mBaseActions = new LinkedList<>();
    protected final LinkedList<AlmondAction> mConditionallyEnabledActions = new LinkedList<>();
    protected InputMap mInputMap;

    public Action getAction(String key) {
        return mActionMap.get(key);
    }

    public LinkedList<AlmondAction> getActions() {
        return mActions;
    }

    public LinkedList<AlmondAction> getBaseActions() {
        return mBaseActions;
    }

    public LinkedList<AlmondAction> getConditionallyEnabledActions() {
        return mConditionallyEnabledActions;
    }

    protected int getOptionsKey() {
        //return SystemUtils.IS_OS_MAC ? KeyEvent.VK_COMMA : KeyEvent.VK_P;
        return KeyEvent.VK_COMMA;
    }

    protected void initAboutDateFormatAction() {
        KeyStroke keyStroke = null;
        String title = Dict.ABOUT_S.toString().formatted(Dict.DATE_PATTERN.toString().toLowerCase());
        AlmondAction action = new AlmondAction(title) {

            @Override
            public void actionPerformed(ActionEvent e) {
                SystemHelper.desktopBrowse("https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html");
            }
        };

        initAction(action, ABOUT_DATE_FORMAT, keyStroke, null, true);
    }

    protected void initAction(AlmondAction action, String key, KeyStroke keyStroke, Enum iconEnum, boolean baseAction) {
        action.putValue(Action.ACCELERATOR_KEY, keyStroke);
        String shortDescription = (String) action.getValue(Action.NAME);

        if (keyStroke != null) {
            String shortcut = WordUtils.capitalizeFully(keyStroke.toString());
            shortcut = StringUtils.replace(shortcut, " Pressed ", "+");
            shortcut = StringUtils.replace(shortcut, "Pressed ", "");

            shortDescription = "%s (%s)".formatted(
                    shortDescription,
                    shortcut
            );
        }

        action.putValue(Action.SHORT_DESCRIPTION, shortDescription);
        action.putValue("hideActionText", true);
        action.setIconEnum(iconEnum);
        action.updateIcon();

        mInputMap.put(keyStroke, key);
        mActionMap.put(key, action);

        if (baseAction) {
            mBaseActions.add(action);
        } else {
            mConditionallyEnabledActions.add(action);
        }

        mActions.add(action);
    }

    protected void initHelpAction(String url) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        AlmondAction action = new AlmondAction(Dict.DOCUMENTATION.toString()) {

            @Override
            public void actionPerformed(ActionEvent e) {
                SystemHelper.desktopBrowse(url);
            }
        };

        initAction(action, HELP, keyStroke, null, true);
    }
}
