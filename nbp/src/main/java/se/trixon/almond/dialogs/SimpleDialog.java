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
package se.trixon.almond.dialogs;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import se.trixon.almond.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class SimpleDialog {

    private static final JFileChooser sFileChooser = new JFileChooser();
    private static FileNameExtensionFilter sFilter;
    private static Component sParent;
    private static File sPath;
    private static File[] sPaths = new File[0];
    private static String sTitle;

    public static void addFilter(FileNameExtensionFilter filter) {
        sFileChooser.addChoosableFileFilter(filter);
    }

    public static void clearFilters() {
        sFileChooser.resetChoosableFileFilters();
    }

    public static FileNameExtensionFilter getFilter() {
        return sFilter;
    }

    public static Component getParent() {
        return sParent;
    }

    public static File getPath() {
        return sPath;
    }

    public static File[] getPaths() {
        return sPaths;
    }

    public static String getTitle() {
        return sTitle;
    }

    public static boolean openFile() {
        return openFile(false);
    }

    public static boolean openFile(boolean multiSelection) {
        sFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        sFileChooser.setMultiSelectionEnabled(multiSelection);
        sPaths = new File[0];

        int result = sFileChooser.showOpenDialog(sParent);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (multiSelection) {
                sPaths = sFileChooser.getSelectedFiles();
            } else {
                sPath = sFileChooser.getSelectedFile();
            }
        }

        return result == JFileChooser.APPROVE_OPTION;
    }

    public static boolean openFileAndDirectoy() {
        return openFileAndDirectoy(false);
    }

    public static boolean openFileAndDirectoy(boolean multiSelection) {
        sFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        sFileChooser.setMultiSelectionEnabled(multiSelection);
        sPaths = new File[0];

        int result = sFileChooser.showOpenDialog(sParent);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (multiSelection) {
                sPaths = sFileChooser.getSelectedFiles();
            } else {
                sPath = sFileChooser.getSelectedFile();
            }
        }

        return result == JFileChooser.APPROVE_OPTION;
    }

    public static boolean saveFile(String... extensions) {
        int result = sFileChooser.showSaveDialog(sParent);
        if (result != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File file = sFileChooser.getSelectedFile();

        if (extensions != null && extensions.length > 0) {
            String fileExt = FilenameUtils.getExtension(file.getName());
            boolean validExt = false;

            for (String extension : extensions) {
                if (fileExt.toLowerCase().equalsIgnoreCase(extension)) {
                    validExt = true;
                    break;
                }
            }

            if (!validExt) {
                String suffix;
                if (file.getName().endsWith(".")) {
                    suffix = extensions[0];
                } else {
                    suffix = "." + extensions[0];
                }
                file = new File(file.getAbsolutePath() + suffix);
            }
        }

        if (file.exists()) {
            NotifyDescriptor notifyDescriptor = new NotifyDescriptor(
                    String.format(Dict.FILE_EXISTS_MESSAGE.getString(), file.getAbsolutePath()),
                    Dict.FILE_EXISTS_TITLE.getString(),
                    NotifyDescriptor.DEFAULT_OPTION,
                    NotifyDescriptor.QUESTION_MESSAGE,
                    null,
                    null);
            Object value = DialogDisplayer.getDefault().notify(notifyDescriptor);

            if (NotifyDescriptor.CANCEL_OPTION == value) {
                return saveFile();
            }
        }

        sPath = file;

        return true;
    }

    public static void selectColor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static boolean selectDirectory() {
        sFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = sFileChooser.showOpenDialog(sParent);
        if (result == JFileChooser.APPROVE_OPTION) {
            sPath = sFileChooser.getSelectedFile();
        }

        return result == JFileChooser.APPROVE_OPTION;
    }

    public static void selectFont() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void setFilter(FileNameExtensionFilter filter) {
        sFilter = filter;
        sFileChooser.setFileFilter(filter);
    }

    public static void setParent(Component parent) {
        sParent = parent;
    }

    public static void setPath(File path) {
        sPath = path;
        sFileChooser.setCurrentDirectory(sPath);
    }

    public static void setSelectedFile(File file) {
        sFileChooser.setSelectedFile(file);
    }

    public static void setTitle(String title) {
        sTitle = title;
        sFileChooser.setDialogTitle(sTitle);
    }

    public static void showHidden(boolean showHidden) {
        sFileChooser.setFileHidingEnabled(!showHidden);
    }

    private SimpleDialog() {
    }

}
