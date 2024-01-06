/*
 * Copyright 2024 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.nbp.output;

import java.awt.Color;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public enum OutputLineMode {
    ALERT,
    STANDARD,
    INFO,
    ERROR,
    OK,
    WARNING;
    private static boolean sNightMode;

    public static void setNightMode(boolean nightMode) {
        sNightMode = nightMode;
    }

    private OutputLineMode() {
    }

    public Color getColor() {
        switch (this) {
            case OK -> {
                return Colors.ok();
            }
            case ALERT -> {
                return Colors.alert();
            }
            case ERROR -> {
                return Colors.error();
            }
            case STANDARD -> {
                return Colors.standard();
            }
            case INFO -> {
                return Colors.info();
            }
            case WARNING -> {
                return Colors.warning();
            }
            default ->
                throw new AssertionError();
        }
    }

    public class Colors {

        public static Color alert() {
            if (sNightMode) {
                return Color.YELLOW;
            } else {
                return Color.YELLOW.darker();
//            return Color.MAGENTA;
            }
        }

        public static Color error() {
            if (sNightMode) {
                return Color.RED;
            } else {
                return Color.RED;
            }
        }

        public static Color info() {
            if (sNightMode) {
                return Color.CYAN;
            } else {
                return Color.BLUE;
            }
        }

        public static boolean isNightMode() {
            return sNightMode;
        }

        public static Color standard() {
            if (sNightMode) {
                return Color.LIGHT_GRAY;
            } else {
                return Color.BLACK;
            }
        }

        public static Color ok() {
            if (sNightMode) {
                return Color.GREEN;
            } else {
                return Color.GREEN.darker();
            }
        }

        public static Color warning() {
            if (sNightMode) {
                return Color.ORANGE;
            } else {
                return Color.ORANGE;
            }
        }
    }
}
