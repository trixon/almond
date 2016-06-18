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
package se.trixon.almond.imageviewer;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.Exceptions;
import se.trixon.almond.SwingHelper;
import se.trixon.almond.dialogs.FileChooserPanel;
import se.trixon.almond.dictionary.Dict;
import se.trixon.almond.icon.Pict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ImageViewPanel extends JPanel {

    private static final int ICON_SIZE = 24;
    private ImageIcon mPauseImageIcon;
    private ImageIcon mPlayImageIcon;
    private JButton mStartButton;
    private final LinkedList<File> mFiles = new LinkedList<>();
    private int mIndex;
    private Timer mTimer;
    private DropTarget mDropTarget;

    /**
     * Creates new form PreviewPanel
     */
    public ImageViewPanel() {
        initComponents();
        init();
        initDropTarget();
    }

    public void add(File[] files) {
        mFiles.addAll(Arrays.asList(files));
        fileListChanged();
    }

    public void addReplace(File[] files) {
        mFiles.clear();
        add(files);
    }

    public void clear() {
        mFiles.clear();
    }

    private void display(int index) {
        BufferedImage bufferedImage = null;
        String name = "";

        if (index > -1) {
            try {
                bufferedImage = ImageIO.read(mFiles.get(index));
                if (bufferedImage == null) {
                    name = "Error loading: " + mFiles.get(index).getName();
                } else {
                    name = mFiles.get(index).getName();
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        imagePanel.setImage(bufferedImage);
        label.setText(name);

        updateButtonState();
    }

    private void fileListChanged() {
        if (mFiles.size() > 0) {
            slider.setMaximum(mFiles.size() - 1);
            mIndex = slider.getMaximum();
            slider.setValue(mIndex);
        } else {
            slider.setMinimum(0);
            slider.setMaximum(0);
            slider.setValue(0);
            display(-1);
        }

        updateButtonState();
        slider.requestFocus();
    }

    private int getSpeedDelay() {
        return speedSlider.getMaximum() - speedSlider.getValue() + 50;
    }

    private void init() {
        int colVal = 0x33;
        label.setBackground(new Color(colVal, colVal, colVal, 196));
        label.setOpaque(true);
        label.setText("");

        previewLabel.setVisible(false);
        playPopupMenu.add(speedSlider, 4);
        mPlayImageIcon = Pict.Actions.MEDIA_PLAYBACK_START.get((int) (ICON_SIZE * 1.5));
        mPauseImageIcon = Pict.Actions.MEDIA_PLAYBACK_PAUSE.get((int) (ICON_SIZE * 1.5));
        mStartButton = DropDownButtonFactory.createDropDownButton(mPlayImageIcon, playPopupMenu);
        mStartButton.setBorder(null);
        mStartButton.setBorderPainted(false);
        mStartButton.setFocusPainted(false);
        mStartButton.setFocusable(false);
        mStartButton.addActionListener((ActionEvent e) -> {
            playMenuItem.doClick();
        });
        toolBar.add(mStartButton, 1);

        mStartButton.setToolTipText(Dict.PLAY.getString());
        prevButton.setIcon(Pict.Actions.MEDIA_SEEK_BACKWARD.get(ICON_SIZE));
        nextButton.setIcon(Pict.Actions.MEDIA_SEEK_FORWARD.get(ICON_SIZE));

        updateButtonState();

        mTimer = new Timer(getSpeedDelay(), (ActionEvent e) -> {
            boolean shouldStop = false;
            if (reversedCheckBoxMenuItem.isSelected()) {
                if (slider.getValue() > 0) {
                    slider.setValue(slider.getValue() - 1);
                } else {
                    shouldStop = true;
                }
            } else {
                if (slider.getValue() < slider.getMaximum()) {
                    slider.setValue(slider.getValue() + 1);
                } else {
                    shouldStop = true;
                }
            }

            if (shouldStop) {
                mTimer.stop();
                mStartButton.setIcon(mPlayImageIcon);
            }
        });

        mTimer.setInitialDelay(getSpeedDelay());
    }

    private void initDropTarget() {
        mDropTarget = new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    Collections.sort(droppedFiles);
                    File[] files = droppedFiles.toArray(new File[droppedFiles.size()]);
                    addReplace(files);
                    System.err.println("drop");
                } catch (UnsupportedFlavorException | IOException ex) {
                }
            }
        };

        imagePanel.setDropTarget(mDropTarget);
    }

    private void updateButtonState() {
        mStartButton.setEnabled(mFiles.size() > 1);
        SwingHelper.enableComponents(playPopupMenu, mStartButton.isEnabled());
        speedMenuItem.setEnabled(false);
        prevButton.setEnabled(mIndex > 0);
        nextButton.setEnabled(mIndex < mFiles.size() - 1);
        slider.setEnabled(mFiles.size() > 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playPopupMenu = new javax.swing.JPopupMenu();
        playMenuItem = new javax.swing.JMenuItem();
        playAllMenuItem = new javax.swing.JMenuItem();
        reversedCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        speedMenuItem = new javax.swing.JMenuItem();
        speedSlider = new javax.swing.JSlider();
        previewLabel = new javax.swing.JLabel();
        imagePanel = new se.trixon.almond.imageviewer.ImagePanel();
        label = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        toolbarSeparator1 = new javax.swing.JToolBar.Separator();
        slider = new javax.swing.JSlider();

        org.openide.awt.Mnemonics.setLocalizedText(playMenuItem, org.openide.util.NbBundle.getMessage(ImageViewPanel.class, "ImageViewPanel.playMenuItem.text")); // NOI18N
        playMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playMenuItemActionPerformed(evt);
            }
        });
        playPopupMenu.add(playMenuItem);

        org.openide.awt.Mnemonics.setLocalizedText(playAllMenuItem, org.openide.util.NbBundle.getMessage(ImageViewPanel.class, "ImageViewPanel.playAllMenuItem.text")); // NOI18N
        playAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAllMenuItemActionPerformed(evt);
            }
        });
        playPopupMenu.add(playAllMenuItem);

        org.openide.awt.Mnemonics.setLocalizedText(reversedCheckBoxMenuItem, org.openide.util.NbBundle.getMessage(ImageViewPanel.class, "ImageViewPanel.reversedCheckBoxMenuItem.text")); // NOI18N
        playPopupMenu.add(reversedCheckBoxMenuItem);

        org.openide.awt.Mnemonics.setLocalizedText(speedMenuItem, org.openide.util.NbBundle.getMessage(ImageViewPanel.class, "ImageViewPanel.speedMenuItem.text")); // NOI18N
        speedMenuItem.setEnabled(false);
        playPopupMenu.add(speedMenuItem);

        speedSlider.setMaximum(2000);
        speedSlider.setValue(1700);
        speedSlider.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        setLayout(new java.awt.BorderLayout());

        previewLabel.setBackground(new java.awt.Color(102, 102, 102));
        previewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(previewLabel, org.openide.util.NbBundle.getMessage(ImageViewPanel.class, "ImageViewPanel.previewLabel.text")); // NOI18N
        previewLabel.setEnabled(false);
        previewLabel.setOpaque(true);
        add(previewLabel, java.awt.BorderLayout.PAGE_START);

        imagePanel.setBackground(new java.awt.Color(102, 102, 102));

        label.setForeground(new java.awt.Color(204, 204, 204));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(label, "jLabel1"); // NOI18N

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(label)
                .addGap(0, 249, Short.MAX_VALUE))
        );

        add(imagePanel, java.awt.BorderLayout.CENTER);

        controlPanel.setLayout(new java.awt.BorderLayout());

        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);

        prevButton.setToolTipText(Dict.PREVIOUS.getString());
        prevButton.setFocusable(false);
        prevButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prevButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        toolBar.add(prevButton);

        nextButton.setToolTipText(Dict.NEXT.getString());
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        toolBar.add(nextButton);
        toolBar.add(toolbarSeparator1);

        controlPanel.add(toolBar, java.awt.BorderLayout.WEST);

        slider.setMaximum(0);
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderStateChanged(evt);
            }
        });
        controlPanel.add(slider, java.awt.BorderLayout.CENTER);

        add(controlPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        slider.setValue(slider.getValue() - 1);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        slider.setValue(slider.getValue() + 1);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderStateChanged
        mIndex = slider.getValue();
        if (mFiles.size() > 0) {
            display(mIndex);
        }
    }//GEN-LAST:event_sliderStateChanged

    private void playMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playMenuItemActionPerformed
        if (mTimer.isRunning()) {
            mTimer.stop();
            mStartButton.setIcon(mPlayImageIcon);
        } else {
            if (reversedCheckBoxMenuItem.isSelected() && slider.getValue() == 0) {
                slider.setValue(slider.getMaximum());
            } else if (!reversedCheckBoxMenuItem.isSelected() && slider.getValue() == slider.getMaximum()) {
                slider.setValue(0);
            }
            mTimer.start();
            mStartButton.setIcon(mPauseImageIcon);
        }
    }//GEN-LAST:event_playMenuItemActionPerformed

    private void playAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAllMenuItemActionPerformed
        if (reversedCheckBoxMenuItem.isSelected()) {
            slider.setValue(slider.getMaximum());
        } else {
            slider.setValue(0);
        }

        playMenuItem.doClick();
    }//GEN-LAST:event_playAllMenuItemActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        mTimer.setDelay(getSpeedDelay());
        mTimer.setInitialDelay(getSpeedDelay());
    }//GEN-LAST:event_speedSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private se.trixon.almond.imageviewer.ImagePanel imagePanel;
    private javax.swing.JLabel label;
    private javax.swing.JButton nextButton;
    private javax.swing.JMenuItem playAllMenuItem;
    private javax.swing.JMenuItem playMenuItem;
    private javax.swing.JPopupMenu playPopupMenu;
    private javax.swing.JButton prevButton;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JCheckBoxMenuItem reversedCheckBoxMenuItem;
    private javax.swing.JSlider slider;
    private javax.swing.JMenuItem speedMenuItem;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JToolBar.Separator toolbarSeparator1;
    // End of variables declaration//GEN-END:variables
}
