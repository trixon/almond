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

import javafx.scene.paint.Color;

/**
 *
 * @author Patrik Karlström
 */
public class GraphicsHelperFx extends GraphicsHelper {

    public static int getBrightness(Color c) {
        return (int) Math.sqrt(
                c.getRed() * c.getRed() * .241
                + c.getGreen() * c.getGreen() * .691
                + c.getBlue() * c.getBlue() * .068);
    }

    public static Color getContrastColor(Color c) {
        return getContrastColor(c, Color.WHITE, Color.BLACK);
    }

    private static Color getContrastColor(Color c, Color brightColor, Color darkColor) {
        return getBrightness(c) < 128 ? brightColor : darkColor;
    }
}
