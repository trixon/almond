/*
 * Copyright 2026 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.swingfx;

import javafx.scene.paint.Color;
import static se.trixon.almond.core.AlmondConstants.FORMAT_HEX_3;
import static se.trixon.almond.core.AlmondConstants.FORMAT_HEX_4;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class SwingFxHelper {

    public static java.awt.Color colorToAwtColor(Color color) {
        if (color == null) {
            return java.awt.Color.BLACK;
        } else {
            return new java.awt.Color(
                    (float) color.getRed(),
                    (float) color.getGreen(),
                    (float) color.getBlue(),
                    (float) color.getOpacity());
        }
    }

    public static java.awt.Color colorToColor(Color color) {
        if (color == null) {
            return java.awt.Color.BLACK;
        } else {
            return new java.awt.Color(
                    (float) color.getRed(),
                    (float) color.getGreen(),
                    (float) color.getBlue(),
                    (float) color.getOpacity());
        }
    }

    public static javafx.scene.paint.Color colorToFxColor(java.awt.Color color) {
        if (color == null) {
            return javafx.scene.paint.Color.BLACK;
        } else {
            return new javafx.scene.paint.Color(
                    (float) color.getRed() / 255,
                    (float) color.getGreen() / 255,
                    (float) color.getBlue() / 255,
                    (float) color.getAlpha() / 255);
        }
    }

    public static String colorToHexABGR(java.awt.Color color) {
        return FORMAT_HEX_4.formatted(
                color.getAlpha(),
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexBGR(java.awt.Color color) {
        return FORMAT_HEX_3.formatted(
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexRGB(java.awt.Color color) {
        return FORMAT_HEX_3.formatted(
                color.getBlue(),
                color.getGreen(),
                color.getRed());
    }

    public static String colorToHexRGBA(java.awt.Color color) {
        return FORMAT_HEX_4.formatted(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                color.getAlpha()
        );
    }

}
