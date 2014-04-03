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
package se.trixon.almond;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import se.trixon.almond.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class FileRemover {

    private final String[] mPaths;

    public FileRemover(String[] paths) {
        int numOfFiles = paths.length;
        mPaths = paths;
        if (numOfFiles > 0) {
            String title = numOfFiles == 1 ? Dict.REMOVE_FILE_TITLE.getString() : Dict.REMOVE_FILES_TITLE.getString();
            String message = numOfFiles == 1 ? Dict.REMOVE_FILE_MESSAGE.getString() : Dict.REMOVE_FILES_MESSAGE.getString();

            if (numOfFiles == 1) {
                message = String.format(message, paths[0]);
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < paths.length; i++) {
                    builder.append(paths[i]).append("\n");
                }
                builder.deleteCharAt(builder.length() - 1);
            }

            NotifyDescriptor notifyDescriptor = new NotifyDescriptor(
                    message,
                    title,
                    NotifyDescriptor.YES_NO_OPTION,
                    NotifyDescriptor.QUESTION_MESSAGE,
                    null,
                    null);
            Object result = DialogDisplayer.getDefault().notify(notifyDescriptor);
            if (result == NotifyDescriptor.YES_OPTION) {
                System.err.println("remove files");
            }
        }
    }
}
