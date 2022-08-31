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
package se.trixon.almond.util.fx.dialogs;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FilenameUtils;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class SimpleDialog {

    private static final HashMap<String, ExtensionFilter> sExtensionFilters = new HashMap<>();
    private static final FileChooser sFileChooser = new FileChooser();
    private static ExtensionFilter sFilter;
    private static Window sOwner;
    private static File sPath;
    private static File[] sPaths = new File[0];
    private static String sTitle;

    public static void addFilter(ExtensionFilter filter) {
        sFileChooser.getExtensionFilters().add(filter);
    }

    public static void addFilters(String... filters) {
        for (String filter : filters) {
            addFilter(sExtensionFilters.get(filter));
        }
    }

    public static void clearFilters() {
        sFileChooser.getExtensionFilters().clear();
    }

    public static HashMap<String, ExtensionFilter> getExtensionFilters() {
        return sExtensionFilters;
    }

//    public static void clearSelection() {
//        File currentDirectory = sFileChooser.getInitialDirectory();//TODO Current?
//
//        sFileChooser.setInitialFileName("");
//
////        sFileChooser.setSelectedFile(new File(""));
////        sFileChooser.setSelectedFiles(new File[]{new File("")});
////        sFileChooser.setCurrentDirectory(currentDirectory);
////        sFileChooser.rescanCurrentDirectory();
//    }
    public static ExtensionFilter getFilter() {
        return sFilter;
    }

    public static Window getOwner() {
        return sOwner;
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
        File file = sFileChooser.showOpenDialog(sOwner);
        if (null == file) {
            return false;
        } else {
            sPath = file;
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean saveFile(String... extensions) {
        File file = sFileChooser.showSaveDialog(sOwner);

        if (null == file) {
            return false;
        }

        boolean fileExistsAlreadyConfirmed = file.isFile();

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

        //Show only confirm dialog if JavaFX dit not confirmed its existance.
        if (file.exists() && !fileExistsAlreadyConfirmed) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(sOwner);

            alert.setTitle(Dict.Dialog.TITLE_FILE_EXISTS.toString());
            alert.setHeaderText(Dict.Dialog.MESSAGE_FILE_EXISTS.toString().formatted(file.getAbsolutePath()));

            ButtonType replaceButtonType = new ButtonType(Dict.REPLACE.toString(), ButtonData.YES);
            ButtonType cancelButtonType = new ButtonType(Dict.CANCEL.toString(), ButtonData.NO);

            alert.getButtonTypes().setAll(replaceButtonType, cancelButtonType);
            Optional<ButtonType> result = FxHelper.showAndWait(alert, (Stage) sOwner);

            if (result.isPresent() && result.get() == cancelButtonType) {
                return saveFile();
            }
        }

        sPath = file;

        return true;
    }

    public static void setFilter(String filterExt) {
        setFilter(sExtensionFilters.get(filterExt));
    }

    public static void setFilter(ExtensionFilter filter) {
        sFilter = filter;
        sFileChooser.setSelectedExtensionFilter(filter);
    }

    public static void setOwner(Window owner) {
        sOwner = owner;
    }

    public static void setPath(File path) {
        sPath = path;
        sFileChooser.setInitialDirectory(path);
    }

    public static void setSelectedFile(File file) {
        setSelectedFile(file.getName());
    }

    public static void setSelectedFile(String filename) {
        sFileChooser.setInitialFileName(filename);
    }

    public static void setTitle(String title) {
        sTitle = title;
        sFileChooser.setTitle(title);
    }

    private SimpleDialog() {
    }

}
