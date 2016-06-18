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
package se.trixon.util.icons.material;

import java.awt.Image;
import javax.swing.ImageIcon;
import se.trixon.util.icons.IconColor;

/**
 *
 * @author Patrik Karlsson
 */
public class MaterialIcon {

    private static ImageIcon get(String dir, String baseName, int size, IconColor iconColor) {
        String path = MaterialIcon.class.getPackage().getName().replace(".", "/");
        String fileName = String.format("/%s/%s/ic_%s_%s_48dp.png", path, dir, baseName.toLowerCase(), iconColor.name().toLowerCase());
        //System.out.println(fileName);
        ImageIcon imageIcon = new ImageIcon(MaterialIcon.class.getResource(fileName));
        Image scaledImage = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public enum Action {

        HELP,
        HELP_OUTLINE,
        HOME,
        INFO,
        INFO_OUTLINE,
        SCHEDULE,
        SETTINGS;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Alert {

        ERROR,
        ERROR_OUTLINE,
        WARNING;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Av {

        PLAY_ARROW,
        PLAY_CIRCLE_FILLED_WHITE;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Communication {

        CALL_MADE,
        CALL_RECEIVED;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Content {

        ADD,
        ADD_BOX,
        ADD_CIRCLE,
        ADD_CIRCLE_OUTLINE,
        CLEAR,
        CONTENT_COPY,
        REMOVE,
        REMOVE_CIRCLE,
        REMOVE_CIRCLE_OUTLINE,
        SAVE;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Editor {

        MODE_EDIT,
        VERTICAL_ALIGN_BOTTOM,
        VERTICAL_ALIGN_TOP;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

    public enum Navigation {

        ARROW_BACK,
        ARROW_DOWNWARD,
        ARROW_FORWARD,
        ARROW_UPWARD,
        CANCEL,
        CLOSE,
        MENU;

        public ImageIcon get(int size, IconColor iconColor) {
            return MaterialIcon.get(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);
        }
    }

}
