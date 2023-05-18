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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import se.trixon.almond.util.icons.material.swing.MaterialIcon.IconGetter;

/**
 *
 * @author Patrik Karlström
 */
public abstract class AlmondAction extends AbstractAction {

    public static final String ALMOND_KEY = "almond_key";
    public static final String ALMOND_SMALL_ICON_KEY = "almond_small_icon";

    private Enum mIconEnum = null;

    public AlmondAction() {
        super();
    }

    public AlmondAction(String name) {
        super(name);
    }

    public AlmondAction(String name, Icon icon) {
        super(name, icon);
    }

    public Enum getIconEnum() {
        return mIconEnum;
    }

    public void setIconEnum(Enum iconEnum) {
        mIconEnum = iconEnum;
    }

    public void updateIcon() {
        if (mIconEnum != null) {
            if (mIconEnum instanceof IconGetter iconGetter) {
                //MaterialIcon
                putValue(Action.LARGE_ICON_KEY, iconGetter.getImageIcon(AlmondGui.ICON_SIZE_NORMAL));
                ImageIcon imageIcon = iconGetter.getImageIcon(AlmondGui.ICON_SIZE_SMALL);
                putValue(ALMOND_SMALL_ICON_KEY, imageIcon);
                putValue(Action.SMALL_ICON, AlmondOptions.getInstance().isDisplayMenuIcons() ? imageIcon : null);
            } else {
                throw new AssertionError();
            }
        }
    }
}
