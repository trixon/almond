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
package se.trixon.almond.util.swing.dialogs;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.HashMap;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlström
 */
public class SimpleDialog {

    private static final HashMap<String, FileNameExtensionFilter> sExtensionFilters = new HashMap<>();
    private static final JFileChooser sFileChooser = new JFileChooser();
    private static FileNameExtensionFilter sFilter;
    private static Component sParent;
    private static File sPath;
    private static File[] sPaths = new File[0];
    private static String sTitle;

    public static void addFilter(FileNameExtensionFilter filter) {
        sFileChooser.addChoosableFileFilter(filter);
    }

    public static void addFilters(FileNameExtensionFilter... filters) {
        for (FileNameExtensionFilter filter : filters) {
            addFilter(filter);
        }
    }

    public static void addFilters(String... filters) {
        for (String filter : filters) {
            addFilter(sExtensionFilters.get(filter));
        }
    }

    public static void clearFilters() {
        sFileChooser.resetChoosableFileFilters();
    }

    public static void clearSelection() {
        File currentDirectory = sFileChooser.getCurrentDirectory();
        sFileChooser.setSelectedFile(new File(""));
        sFileChooser.setSelectedFiles(new File[]{new File("")});
        sFileChooser.setCurrentDirectory(currentDirectory);
        sFileChooser.rescanCurrentDirectory();
    }

    public static HashMap<String, FileNameExtensionFilter> getExtensionFilters() {
        return sExtensionFilters;
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

    @Deprecated
    public static boolean saveFile(String... extensions) {
        return saveFile();
    }

    public static boolean saveFile() {
        if (sFileChooser.showSaveDialog(sParent) != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File file = sFileChooser.getSelectedFile();

        if (!sFileChooser.getFileFilter().accept(file)) {
            var fileNameExtensionFilter = (FileNameExtensionFilter) sFileChooser.getFileFilter();
            var extensions = fileNameExtensionFilter.getExtensions();
            String suffix;

            if (file.getName().endsWith(".")) {
                suffix = extensions[0];
            } else {
                suffix = "." + extensions[0];
            }

            file = new File(file.getAbsolutePath() + suffix);
        }

        if (file.exists()) {
            var result = JOptionPane.showConfirmDialog(sParent,
                    Dict.Dialog.MESSAGE_FILE_EXISTS.toString().formatted(file.getAbsolutePath()),
                    Dict.Dialog.TITLE_FILE_EXISTS.toString(),
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.NO_OPTION) {
                return saveFile();
            }
        }

        sPath = file;

        return true;
    }

    public static Color selectColor(Color color) {
        Color newColor = JColorChooser.showDialog(sParent, Dict.SELECT_COLOR.toString(), color);

        return newColor;
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

    public static void setFilter(String filterExt) {
        setFilter(sExtensionFilters.get(filterExt));
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
