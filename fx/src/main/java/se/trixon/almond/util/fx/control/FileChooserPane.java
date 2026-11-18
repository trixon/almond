/*
 * Copyright 2023 Patrik Karlström.
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
package se.trixon.almond.util.fx.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class FileChooserPane extends BorderPane {

    private final Button mButton = new Button(null, new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN_ALT).size(FxHelper.getScaledFontSize() * 1.5));
    private final CheckBox mCheckBox = new CheckBox();
    private final DirectoryChooser mDirectoryChooser = new DirectoryChooser();
    private boolean mEnabledPathExpander = true;
    private final FileChooser mFileChooser = new FileChooser();
    private FileChooserListener mFileChooserListener;
    private final List<File> mFiles = new ArrayList<>();
    private final Label mLabel = new Label();
    private ObjectMode mObjectMode = ObjectMode.DIRECTORY;
    private SelectionMode mSelectionMode = SelectionMode.SINGLE;
    private final TextField mTextField = new TextField();
    private String mTitle = Dict.OPEN.toString();

    public FileChooserPane() {
        init();
    }

    public FileChooserPane(String title) {
        init();
        mTitle = title;
    }

    public FileChooserPane(String title, String headerLabelText) {
        init();
        mTitle = title;
        setHeaderLabelText(headerLabelText);
    }

    public FileChooserPane(String title, ObjectMode objectMode, SelectionMode selectionMode) {
        init();
        mTitle = title;
        setSelectionMode(selectionMode);
        setObjectMode(objectMode);
    }

    public FileChooserPane(String title, ObjectMode objectMode, SelectionMode selectionMode, String checkBoxText) {
        init();
        mTitle = title;
        setSelectionMode(selectionMode);
        setObjectMode(objectMode);
        setHeaderCheckBoxText(checkBoxText);
    }

    public FileChooserPane(String title, String headerLabelText, ObjectMode objectMode, SelectionMode selectionMode) {
        init();
        mTitle = title;
        setHeaderLabelText(headerLabelText);
        setSelectionMode(selectionMode);
        setObjectMode(objectMode);
    }

    /**
     * Applies only to file object mode
     *
     * @param filter
     */
    public void addFilter(ExtensionFilter filter) {
        mFileChooser.getExtensionFilters().add(filter);
    }

    /**
     * Applies only to file object mode
     *
     * @param filters
     */
    public void addFilters(ExtensionFilter... filters) {
        mFileChooser.getExtensionFilters().addAll(filters);
    }

    /**
     * Applies only to file object mode
     */
    public void clearFilters() {
        mFileChooser.getExtensionFilters().clear();
    }

    public Button getButton() {
        return mButton;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public DirectoryChooser getDirectoryChooser() {
        return mDirectoryChooser;
    }

    public FileChooser getFileChooser() {
        return mFileChooser;
    }

    public FileChooserListener getFileChooserListener() {
        return mFileChooserListener;
    }

    /**
     * Applies only to file object mode
     *
     * @return
     */
    public ExtensionFilter getFilter() {
        return mFileChooser.getSelectedExtensionFilter();
    }

    public Label getLabel() {
        return mLabel;
    }

    public ObjectMode getObjectMode() {
        return mObjectMode;
    }

    public File getPath() {
        if (StringUtils.isBlank(getPathAsString())) {
            return null;
        } else {
            return new File(getPathAsString());
        }
    }

    public String getPathAsString() {
        return mTextField.getText();
    }

    public File[] getPaths() {
        String[] pathStrings = StringUtils.split(mTextField.getText(), File.pathSeparator);
        File[] paths = new File[pathStrings.length];

        for (int i = 0; i < pathStrings.length; i++) {
            paths[i] = new File(pathStrings[i]);
        }

        return paths;
    }

    public SelectionMode getSelectionMode() {
        return mSelectionMode;
    }

    public TextField getTextField() {
        return mTextField;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isEnabledPathExpander() {
        return mEnabledPathExpander;
    }

    public void setEnabledPathExpander(boolean enabledPathExpander) {
        mEnabledPathExpander = enabledPathExpander;
    }

    public void setFileChooserListener(FileChooserListener fileChooserListener) {
        mFileChooserListener = fileChooserListener;
    }

    /**
     * Applies only to file object mode
     *
     * @param filter
     */
    public void setFilter(ExtensionFilter filter) {
        mFileChooser.setSelectedExtensionFilter(filter);
    }

    public void setGraphic(Node value) {
        mButton.setGraphic(value);
    }

    public void setHeaderCheckBoxText(String value) {
        setTop(mCheckBox);
        mCheckBox.setText(value);
        mTextField.disableProperty().bind(mCheckBox.selectedProperty().not());
        mButton.disableProperty().bind(mCheckBox.selectedProperty().not());
    }

    public void setHeaderLabelText(String value) {
        setTop(mLabel);
        mLabel.setText(value);
    }

    public void setObjectMode(ObjectMode objectMode) {
        mObjectMode = objectMode;
        if (mObjectMode == ObjectMode.DIRECTORY) {
            mSelectionMode = SelectionMode.SINGLE;
        }
    }

    public void setPath(String path) {
        mTextField.setText(path);
    }

    public void setPath(File path) {
        try {
            mTextField.setText(path.getPath());
        } catch (Exception e) {
        }
    }

    public void setSelected(boolean selected) {
        mCheckBox.setSelected(selected);
    }

    /**
     * Applies only to file object mode
     *
     * @param selectionMode
     */
    public void setSelectionMode(SelectionMode selectionMode) {
        mSelectionMode = selectionMode;
    }

    public void setTitle(String title) {
        mTitle = title;

    }

    private void init() {
        setCenter(mTextField);
        setRight(mButton);
        FxHelper.undecorateButton(mButton);
        mButton.setTooltip(new Tooltip(Dict.SELECT.toString()));

        mTextField.setOnAction(actionEvent -> {
            if (mEnabledPathExpander) {
                var file = new File(mTextField.getText().trim());
                setPath(file.getAbsolutePath());

                //if (file.exists() && file.isDirectory()) {
                try {
                    mFileChooserListener.onFileChooserOk(this, file);
                } catch (Exception e) {
                }
                //}
            }
        });

        setOnDragOver(dragEvent -> {
            var dragboard = dragEvent.getDragboard();
            if (dragboard.hasFiles()) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
            }
        });

        setOnDragDropped(dragEvent -> {
            populateValidatedFileList(dragEvent.getDragboard().getFiles());
            try {
                if (!mFiles.isEmpty()) {
                    if (mSelectionMode == SelectionMode.MULTIPLE) {
                        mFileChooserListener.onFileChooserDrop(FileChooserPane.this, mFiles);
                    } else {
                        mFileChooserListener.onFileChooserDrop(FileChooserPane.this, mFiles.get(0));
                    }

                }
            } catch (Exception e) {
            }
        });

        mButton.setOnAction(actionEvent -> {
            try {
                mFileChooserListener.onFileChooserPreSelect(this);
            } catch (Exception e) {
            }

            var window = mButton.getScene().getWindow();
            boolean cancelled = true;

            if (mObjectMode == ObjectMode.DIRECTORY) {
                mDirectoryChooser.setTitle(mTitle);
                setInitialDirectory(mDirectoryChooser);
                var file = mDirectoryChooser.showDialog(window);
                if (file != null) {
                    populateValidatedFileList(Arrays.asList(file));
                }
                cancelled = file == null;
            } else {
                mFileChooser.setTitle(mTitle);
                setInitialDirectory(mFileChooser);
                if (mSelectionMode == SelectionMode.MULTIPLE) {
                    var files = mFileChooser.showOpenMultipleDialog(window);
                    cancelled = files == null;
                    if (!cancelled) {
                        populateValidatedFileList(files);
                    }
                } else {
                    var file = mFileChooser.showOpenDialog(window);
                    if (file != null) {
                        populateValidatedFileList(Arrays.asList(file));
                    }
                    cancelled = file == null;
                }
            }

            try {
                if (cancelled) {
                    mFileChooserListener.onFileChooserCancel(this);
                } else {
                    if (mSelectionMode == SelectionMode.MULTIPLE) {
                        mFileChooserListener.onFileChooserOk(this, mFiles);
                    } else {
                        mFileChooserListener.onFileChooserOk(this, mFiles.get(0));
                    }
                }
            } catch (Exception e) {
            }

        });

        mCheckBox.setOnAction(actionEvent -> {
            try {
                mFileChooserListener.onFileChooserCheckBoxChange(this, mCheckBox.isSelected());
            } catch (Exception e) {
            }
        });

        FxHelper.setPadding(FxHelper.getUIScaledInsets(0, 0, 2, 0), mLabel, mCheckBox);
    }

    private void populateValidatedFileList(List<File> files) {
        mFiles.clear();
        List<String> mFileStrings = new ArrayList<>();

        files.stream()
                .filter((file) -> ((file.isDirectory() && mObjectMode == ObjectMode.DIRECTORY) || (file.isFile() && mObjectMode == ObjectMode.FILE)))
                .forEachOrdered((file) -> {
                    mFiles.add(file);
                    mFileStrings.add(file.getPath());
                });

        String sum = null;

        if (mSelectionMode == SelectionMode.MULTIPLE) {
            sum = String.join(File.pathSeparator, mFileStrings);
        } else {
            if (!mFileStrings.isEmpty()) {
                sum = mFileStrings.get(0);
            }
        }

        mTextField.setText(sum);
    }

    private void setInitialDirectory(DirectoryChooser chooser) {
        if (!StringUtils.isBlank(mTextField.getText())) {
            var firstPath = StringUtils.split(mTextField.getText(), File.pathSeparatorChar)[0];
            var f = new File(firstPath);
            if (f.isDirectory()) {
                chooser.setInitialDirectory(f);
            } else if (f.isFile()) {
                chooser.setInitialDirectory(f.getParentFile());
            }
        }
    }

    private void setInitialDirectory(FileChooser chooser) {
        try {
            String firstPath = StringUtils.split(mTextField.getText(), File.pathSeparatorChar)[0];
            File f = new File(firstPath);
            if (f.isFile()) {
                chooser.setInitialDirectory(f.getParentFile());
                chooser.setInitialFileName(f.getName());
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            //nvm
        }
    }

    public enum ObjectMode {

        DIRECTORY,
        FILE;
    }

    public interface FileChooserListener {

        public void onFileChooserCancel(FileChooserPane chooserPane);

        public void onFileChooserCheckBoxChange(FileChooserPane chooserPane, boolean selected);

        public void onFileChooserDrop(FileChooserPane chooserPane, File file);

        public void onFileChooserDrop(FileChooserPane chooserPane, List<File> files);

        public void onFileChooserOk(FileChooserPane chooserPane, File file);

        public void onFileChooserOk(FileChooserPane chooserPane, List<File> files);

        public void onFileChooserPreSelect(FileChooserPane chooserPane);
    }
}
