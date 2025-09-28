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
            return sNightMode ? Color.YELLOW : Color.ORANGE.darker();
        }

        public static Color error() {
            return sNightMode ? Color.decode("#FF4040") : Color.decode("#BF0000");
        }

        public static Color info() {
            return sNightMode ? Color.CYAN : Color.BLUE;
        }

        public static boolean isNightMode() {
            return sNightMode;
        }

        public static Color standard() {
            return sNightMode ? Color.LIGHT_GRAY : Color.BLACK;
        }

        public static Color ok() {
            return sNightMode ? Color.decode("#A8C023") : Color.decode("#007C00");
        }

        public static Color warning() {
            return sNightMode ? Color.decode("#FFC66D") : Color.ORANGE;
        }
    }
}
