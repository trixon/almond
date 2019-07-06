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
package se.trixon.almond.util.icons;

import javax.swing.UIManager;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlström
 */
public enum IconColor {
    BLACK, WHITE;
    private static IconColor mDefault;

    public static IconColor getDefault() {
        return mDefault;
    }

    public static IconColor getForBackground(java.awt.Color background) {
        return GraphicsHelper.getBrightness(background) < 130 ? IconColor.WHITE : IconColor.BLACK;
    }

    public static IconColor getForBackground(javafx.scene.paint.Color background) {
        return GraphicsHelper.getBrightness(background) < 130 ? IconColor.WHITE : IconColor.BLACK;
    }

    public static void initFx() {
        mDefault = BLACK;
    }

    public static void initSwing() {
        java.awt.Color color = UIManager.getLookAndFeel().getDefaults().getColor("Button.background");
        mDefault = getForBackground(color);
    }

    public static void setDefault(IconColor iconColor) {
        mDefault = iconColor;
    }
}
