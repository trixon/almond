/*
 * Copyright 2018 Patrik Karlström.
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
package se.trixon.almond.util.swing.dialogs.about;

import se.trixon.almond.util.AboutModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import se.trixon.almond.util.AlmondAction;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.Scaler;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class AboutPanel extends javax.swing.JPanel {

    private AboutModel mAboutModel;
    private static String sAppName;
    private final HashSet<TabComponentListener> mTabComponentListeners = new HashSet<>();

    public static AlmondAction getAction(Component parentComponent, AboutPanel aboutPanel) {
        AlmondAction action = new AlmondAction(Dict.ABOUT.toString()) {

            @Override
            public void actionPerformed(ActionEvent e) {
                aboutPanel.reset();
                SwingHelper.makeWindowResizable(aboutPanel);
                Object[] options = {Dict.CLOSE.toString()};

                JOptionPane.showOptionDialog(
                        parentComponent,
                        aboutPanel,
                        String.format(Dict.ABOUT_S.toString(), sAppName),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
        };

        return action;
    }

    /**
     * Creates new form AboutPanel
     */
    public AboutPanel() {
        initComponents();
        init();
    }

    public AboutPanel(AboutModel aboutModel) {
        initComponents();
        mAboutModel = aboutModel;
        init();
    }

    private void addLastComponent() {
        mTabComponentListeners.add((TabComponentListener) tabbedPane.getComponentAt(tabbedPane.getTabCount() - 1));

    }

    private void init() {
        if (mAboutModel.getImageIcon() != null) {
            populateIcon(mAboutModel.getImageIcon());
        }
        appNameLabel.setText(mAboutModel.getAppName());
        sAppName = mAboutModel.getAppName();
        appVersionLabel.setText(String.format("%s %s", Dict.VERSION.toString(), mAboutModel.getAppVersion()));

        tabbedPane.add(new AboutTab(mAboutModel), Dict.ABOUT.toString());
        tabbedPane.add(new LibrariesTab(mAboutModel), Dict.LIBRARIES.toString());
        addLastComponent();

        if (mAboutModel.getAuthors() != null) {
            tabbedPane.add(new AuthorsTab(mAboutModel), Dict.AUTHORS.toString());
            addLastComponent();
        }

        if (mAboutModel.getTranslation() != null) {
            tabbedPane.add(new TranslationTab(mAboutModel), Dict.TRANSLATION.toString());
            addLastComponent();
        }

        if (mAboutModel.getThanksTo() != null) {
            tabbedPane.add(new ThanksToTab(mAboutModel), Dict.THANKS_TO.toString());
            addLastComponent();
        }

        tabbedPane.add(new PropertiesTab(), Dict.PROPERTIES.toString());
        addLastComponent();
    }

    private void populateIcon(ImageIcon imageIcon) {
        Scaler scaler = new Scaler(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        int maxSize = 72;
        scaler.setHeight(maxSize);
        scaler.setWidth(maxSize);

        Image image = imageIcon.getImage().getScaledInstance(scaler.getDimension().width, scaler.getDimension().height, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(image));
    }

    private void reset() {
        tabbedPane.setSelectedIndex(0);
        mTabComponentListeners.forEach((listbox) -> {
            listbox.reset();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        iconLabel = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        appNameLabel = new javax.swing.JLabel();
        appVersionLabel = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        add(iconLabel, gridBagConstraints);

        appNameLabel.setFont(appNameLabel.getFont().deriveFont(appNameLabel.getFont().getSize()+5f));
        appNameLabel.setText("NAME"); // NOI18N

        appVersionLabel.setText("VERSION"); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(appVersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(appNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appVersionLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(panel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        add(tabbedPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appNameLabel;
    private javax.swing.JLabel appVersionLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    public interface TabComponentListener {

        void reset();
    }
}
