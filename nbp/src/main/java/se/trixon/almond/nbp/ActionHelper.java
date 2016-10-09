/*
 * Copyright 2016 Patrik Karlsson.
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

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import org.openide.filesystems.FileUtil;
import se.trixon.almond.util.icons.IconColor;

/**
 *
 * @author Patrik Karlsson
 */
public class ActionHelper {

    private static final IconColor sIconColor = se.trixon.almond.util.AlmondOptions.getInstance().getIconColor();

    public static void add(ActionMap actionMap, final String key, final Runnable runnable) {

        actionMap.put(key, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                runnable.run();
            }
        });
    }

    public static void remove(ActionMap actionMap, final String key) {

        actionMap.remove(key);
    }

    public static void setIconLarge(String category, String id, Icon icon) {
        setIcon(category, id, icon, Action.LARGE_ICON_KEY);
    }

    public static void setIconSmall(String category, String id, Icon icon) {
        setIcon(category, id, icon, Action.SMALL_ICON);
    }

    private static void setIcon(String category, String id, Icon icon, String key) {
        String path = String.format("%s/%s.instance", category, id.replace(".", "-"));
        //System.out.println(path);
        Action action = FileUtil.getConfigObject(path, Action.class);

        if (action != null) {
            action.putValue(key, icon);
        } else {
            System.err.println("Action not fond: " + path);
        }
    }
}
