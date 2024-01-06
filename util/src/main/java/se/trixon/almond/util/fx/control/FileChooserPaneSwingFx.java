/*
 * Copyright 2023 Patrik Karlström <patrik@trixon.se>.
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

import java.awt.Component;
import java.io.File;
import javafx.scene.control.SelectionMode;
import javax.swing.JFileChooser;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class FileChooserPaneSwingFx extends FileChooserPane {

    private final JFileChooser mFileChooser = new JFileChooser();
    private int mFileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;
    private Component mParent;

    public FileChooserPaneSwingFx() {
    }

    public FileChooserPaneSwingFx(String title) {
        super(title);
    }

    public FileChooserPaneSwingFx(String title, String headerLabelText) {
        super(title, headerLabelText);
    }

    public FileChooserPaneSwingFx(String title, Component parent, int fileSelectionMode) {
        super(title, ObjectMode.DIRECTORY, SelectionMode.SINGLE);
        mParent = parent;
        mFileSelectionMode = fileSelectionMode;
        initSwing();
    }

    public FileChooserPaneSwingFx(String title, Component parent, int fileSelectionMode, String checkBoxText) {
        super(title, ObjectMode.DIRECTORY, SelectionMode.SINGLE, checkBoxText);
        mParent = parent;
        mFileSelectionMode = fileSelectionMode;
        initSwing();
    }

    public FileChooserPaneSwingFx(String title, String headerLabelText, Component parent, int fileSelectionMode) {
        super(title, headerLabelText, ObjectMode.DIRECTORY, SelectionMode.SINGLE);
        mParent = parent;
        mFileSelectionMode = fileSelectionMode;
        initSwing();
    }

    private void initSwing() {
        mFileChooser.setFileSelectionMode(mFileSelectionMode);

        getButton().setOnAction(actionEvent -> {
            try {
                getFileChooserListener().onFileChooserPreSelect(this);
            } catch (Exception e) {
            }

            if (getTitle() != null) {
                mFileChooser.setDialogTitle(getTitle());
            }

            var text = getTextField().getText();

            if (StringUtils.isNotBlank(text)) {
                var f = new File(text);

                if (f.isFile()) {
                    mFileChooser.setSelectedFile(f);
                    mFileChooser.setCurrentDirectory(f.getParentFile());
                } else if (f.isDirectory()) {
                    mFileChooser.setCurrentDirectory(f);
                }
            }

            int returnVal = mFileChooser.showOpenDialog(mParent);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (mFileChooser.isMultiSelectionEnabled()) {
                    //TODO Join array?
                } else {
                    getTextField().setText(mFileChooser.getSelectedFile().toString());
                }
                try {
                    getFileChooserListener().onFileChooserOk(this, mFileChooser.getSelectedFile());
                } catch (Exception e) {
                }
            } else {
                try {
                    getFileChooserListener().onFileChooserCancel(this);
                } catch (Exception e) {
                }
            }
        });
    }

}
