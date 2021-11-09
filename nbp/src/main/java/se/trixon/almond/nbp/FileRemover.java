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
package se.trixon.almond.nbp;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlström
 */
public class FileRemover {

    private final Set<String> mDeniedRemoves = new HashSet<>();

    public Set<String> getDeniedRemoves() {
        return mDeniedRemoves;
    }

    public boolean isSuccessful() {
        return mDeniedRemoves.isEmpty();
    }

    public boolean remove(String[] paths) {
        mDeniedRemoves.clear();
        boolean filesRemoved = false;
        int numOfFiles = paths.length;
        if (numOfFiles > 0) {
            String title = numOfFiles == 1 ? Dict.Dialog.TITLE_REMOVE_FILE.toString() : Dict.Dialog.TITLE_REMOVE_FILES.toString();
            String message = numOfFiles == 1 ? Dict.Dialog.MESSAGE_REMOVE_FILE.toString() : Dict.Dialog.MESSAGE_REMOVE_FILES.toString();

            if (numOfFiles == 1) {
                message = String.format(message, paths[0]);
            } else {
                StringBuilder builder = new StringBuilder();
                for (String path : paths) {
                    builder.append(path).append("\n");
                }
                builder.deleteCharAt(builder.length() - 1);
                message = String.format(message, numOfFiles, builder.toString());
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
                for (String path : paths) {
                    try {
                        File file = new File(path);
                        FileObject fileObject = FileUtil.createData(file);
                        fileObject.delete();
                    } catch (IOException ex) {
                        mDeniedRemoves.add(path);
                    }
                }
                filesRemoved = true;
            }
        }

        return filesRemoved;
    }
}
