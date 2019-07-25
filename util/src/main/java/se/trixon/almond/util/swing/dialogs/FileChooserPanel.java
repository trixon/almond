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
package se.trixon.almond.util.swing.dialogs;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström
 */
public class FileChooserPanel extends javax.swing.JPanel {

    private static final String PATH_SEPARATOR = System.getProperty("path.separator"); //NOI18N
    private DropTarget mDropTarget;
    private final JFileChooser mFileChooser = new JFileChooser();
    private int mMode;
    private FileChooserButtonListener mFileChooserButtonListener;
    private String mTitle;
    private DropMode mDropMode = DropMode.SINGLE;
    private File[] mPaths;
    private static FileNameExtensionFilter mFilter;

    /**
     * Creates new form FileChooserPanel
     */
    public FileChooserPanel() {
        initComponents();
        init();
    }

    public void addFilter(FileNameExtensionFilter filter) {
        mFileChooser.addChoosableFileFilter(filter);
    }

    public void setFilter(FileNameExtensionFilter filter) {
        mFilter = filter;
        mFileChooser.setFileFilter(filter);
    }

    public void clearFilters() {
        mFileChooser.resetChoosableFileFilters();
    }

    public FileNameExtensionFilter getFilter() {
        return mFilter;
    }

    public FileChooserPanel(int mode, String title) {
        initComponents();
        init();
        mMode = mode;
        mTitle = title;
        mFileChooser.setFileSelectionMode(mMode);
    }

    public DropMode getDropMode() {
        return mDropMode;
    }

    public JFileChooser getFileChooser() {
        return mFileChooser;
    }

    public String getHeader() {
        return mLabel.getText();
    }

    public int getMode() {
        return mMode;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isSelected() {
        return mCheckBox.isSelected();
    }

    public void setDropMode(DropMode dropMode) {
        mDropMode = dropMode;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setButtonListener(FileChooserButtonListener fileChooserButtonListener) {
        mFileChooserButtonListener = fileChooserButtonListener;
    }

    public void setMode(int mode) {
        mMode = mode;
        mFileChooser.setFileSelectionMode(mMode);
    }

    public void setCheckBoxMode(boolean checkBoxMode) {
        mLabel.setVisible(!checkBoxMode);
        mCheckBox.setVisible(checkBoxMode);
    }

    public JButton getButton() {
        return mButton;
    }

    public JLabel getLabel() {
        return mLabel;
    }

    public String getPath() {
        return mTextField.getText();
    }

    public File[] getPaths() {
        return mPaths;
    }

    public JTextField getTextField() {
        return mTextField;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mLabel.setEnabled(enabled);
        mButton.setEnabled(enabled);
        mTextField.setEnabled(enabled);
        activateDropTarget(enabled);
    }

    public void setHeader(String string) {
        mLabel.setText(string);
        mCheckBox.setText(string);
    }

    public void setPath(String path) {
        mTextField.setText(path);
    }

    public void setSelected(boolean selected) {
        mCheckBox.setSelected(selected);
    }

    private void activateDropTarget(boolean activate) {
        if (activate) {
            mTextField.setDropTarget(mDropTarget);
        } else {
            mTextField.setDropTarget(null);
        }
    }

    private void init() {
        mButton.setIcon(MaterialIcon._File.FOLDER_OPEN.getImageIcon(22));

        mDropTarget = new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    @SuppressWarnings("unchecked")
                    LinkedList<File> droppedFiles = new LinkedList<>((List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                    List<File> invalidFiles = new LinkedList<>();

                    for (File droppedFile : droppedFiles) {
                        if (droppedFile.isFile()) {
                            if (mMode == JFileChooser.DIRECTORIES_ONLY) {
                                invalidFiles.add(droppedFile);
                            }
                        } else if (droppedFile.isDirectory()) {
                            if (mMode == JFileChooser.FILES_ONLY) {
                                invalidFiles.add(droppedFile);
                            }
                        } else {
                            invalidFiles.add(droppedFile);
                        }
                    }

                    for (File invalidFile : invalidFiles) {
                        droppedFiles.remove(invalidFile);
                    }

                    mPaths = droppedFiles.toArray(new File[droppedFiles.size()]);

                    if (mDropMode == DropMode.SINGLE) {
                        String path = "";
                        if (droppedFiles.size() > 0) {
                            path = droppedFiles.get(0).getAbsolutePath();
                        }
                        mTextField.setText(path);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (File file : droppedFiles) {
                            sb.append(file.getAbsolutePath()).append(PATH_SEPARATOR);
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        mTextField.setText(sb.toString());
                    }
                    try {
                        mFileChooserButtonListener.onFileChooserDrop(FileChooserPanel.this);
                    } catch (Exception e) {
                    }

                } catch (UnsupportedFlavorException | IOException ex) {
                }
            }
        };

        activateDropTarget(true);
        mCheckBox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mLabel = new javax.swing.JLabel();
        mCheckBox = new javax.swing.JCheckBox();
        mTextField = new javax.swing.JTextField();
        toolBar = new javax.swing.JToolBar();
        mButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        mLabel.setText(Dict.PATH.toString());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(mLabel, gridBagConstraints);

        mCheckBox.setText(Dict.PATH.toString());
        mCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(mCheckBox, gridBagConstraints);

        mTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(mTextField, gridBagConstraints);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setBorderPainted(false);

        mButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonActionPerformed(evt);
            }
        });
        toolBar.add(mButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(toolBar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void mButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonActionPerformed
        try {
            mFileChooserButtonListener.onFileChooserPreSelect(this);
        } catch (Exception e) {
        }

        if (mTitle != null) {
            mFileChooser.setDialogTitle(mTitle);
        }

        File baseDirectory = new File(mTextField.getText());

        if (baseDirectory.exists() && baseDirectory.isFile()) {
            baseDirectory = baseDirectory.getParentFile();
        }

        mFileChooser.setCurrentDirectory(baseDirectory);

        int returnVal = mFileChooser.showOpenDialog(mButton.getTopLevelAncestor());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            mPaths = mFileChooser.getSelectedFiles();
            if (mFileChooser.isMultiSelectionEnabled()) {
                //TODO Join array?
            } else {
                mTextField.setText(mFileChooser.getSelectedFile().toString());
            }
            try {
                mFileChooserButtonListener.onFileChooserOk(this, mFileChooser.getSelectedFile());
            } catch (Exception e) {
            }
        } else {
            try {
                mFileChooserButtonListener.onFileChooserCancel(this);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_mButtonActionPerformed

    private void mTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTextFieldActionPerformed
        File file = new File(mTextField.getText().trim());
        setPath(file.getAbsolutePath());

        if (file.exists() && file.isDirectory()) {
            try {
                mFileChooserButtonListener.onFileChooserOk(this, file);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_mTextFieldActionPerformed

    private void mCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCheckBoxActionPerformed
        setEnabled(mCheckBox.isSelected());
        try {
            mFileChooserButtonListener.onFileChooserCheckBoxChange(this, mCheckBox.isSelected());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mButton;
    private javax.swing.JCheckBox mCheckBox;
    private javax.swing.JLabel mLabel;
    private javax.swing.JTextField mTextField;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public enum DropMode {

        MULTI,
        SINGLE;
    }

    public interface FileChooserButtonListener {

        public void onFileChooserCancel(FileChooserPanel fileChooserPanel);

        public void onFileChooserCheckBoxChange(FileChooserPanel fileChooserPanel, boolean isSelected);

        public void onFileChooserDrop(FileChooserPanel fileChooserPanel);

        public void onFileChooserOk(FileChooserPanel fileChooserPanel, File file);

        public void onFileChooserPreSelect(FileChooserPanel fileChooserPanel);
    }
}
