/* 
 * Copyright 2015 Patrik Karlsson.
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
        CHRONOMETER,
        CONFIGURE,
        DOCUMENT_EDIT,
        DOCUMENT_EDIT_DECRYPT_VERIFY,
        DOCUMENT_OPEN_FOLDER,
        DOCUMENT_OPEN,
        DOCUMENT_REVERT,
        DOCUMENT_SAVE_ALL,
        DOCUMENT_SAVE_AS,
        DOCUMENT_SAVE,
        DOWNLOAD,
        DOWNLOAD_LATER,
        FOLDER_DOWNLOADS,
        EDIT_CLEAR_LIST,
        EDIT_CLEAR,
        EDIT_COPY,
        EDIT_DELETE,
        EDIT_FIND,
        EDIT_REDO,
        EDIT_SELECT_ALL,
        EDIT_UNDO,
        FORMAT_JUSTIFY_FILL,
        HELP_CONTENTS,
        LIST_ADD,
        LIST_REMOVE,
        MEDIA_EJECT,
        MEDIA_PLAYBACK_PAUSE,
        MEDIA_PLAYBACK_START,
        MEDIA_PLAYBACK_STOP,
        MEDIA_RECORD,
        MEDIA_SEEK_BACKWARD,
        MEDIA_SEEK_FORWARD,
        MEDIA_SKIP_BACKWARD,
        MEDIA_SKIP_FORWARD,
        MERGE,
        PROCESS_STOP,
        TAB_NEW,
        TRASH_EMPTY,
        VIEW_CATALOG,
        VIEW_FILTER,
        VIEW_PREVIEW,
        VIEW_REFRESH,
        WINDOW_CLOSE,
        ZOOM_FIT_BEST,
        ZOOM_IN,
        ZOOM_ORIGINAL,
        ZOOM_OUT;

        public ImageIcon get(int size) {
            return Pict.get("actions/" + name(), size);
        }
    }

    public enum Categories {

        APPLICATIONS_INTERNET,
        APPLICATIONS_SYSTEM;

        public ImageIcon get(int size) {
            return Pict.get("categories/" + name(), size);
        }
    }

    public enum Places {

        BOOKMARKS;

        public ImageIcon get(int size) {
            return Pict.get("places/" + name(), size);
        }
    }
}
