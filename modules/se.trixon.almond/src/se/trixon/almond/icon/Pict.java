/*
 * Copyright 2014 Patrik Karlsson.
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
package se.trixon.almond.icon;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Pict {

    private static ImageIcon get(String baseName, int size) {
        String fileName = String.format("/%s/res/%s.png", Pict.class.getPackage().getName().replace(".", "/"), baseName.toLowerCase().replace("_", "-"));

        ImageIcon imageIcon = new ImageIcon(Pict.class.getResource(fileName));
        Image scaledImage = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public enum Actions {

        ARROW_RIGHT,
        CONFIGURE,
        DOCUMENT_EDIT,
        DOCUMENT_EDIT_DECRYPT_VERIFY,
        DOCUMENT_OPEN_FOLDER,
        DOCUMENT_OPEN,
        DOCUMENT_SAVE_ALL,
        DOCUMENT_SAVE_AS,
        DOCUMENT_SAVE,
        EDIT_CLEAR_LIST,
        EDIT_CLEAR,
        EDIT_COPY,
        EDIT_DELETE,
        EDIT_FIND,
        EDIT_SELECT_ALL,
        HELP_CONTENTS,
        MERGE,
        PROCESS_STOP,
        TRASH_EMPTY,
        VIEW_REFRESH,
        WINDOW_CLOSE;

        public ImageIcon get(int size) {
            return Pict.get("actions/" + name(), size);
        }
    }
}
