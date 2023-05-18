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
package se.trixon.almond.util.swing.dialogs;

import se.trixon.almond.util.AlmondOptions;

/**
 *
 * @author Patrik Karlström
 */
public class MenuModePanel extends javax.swing.JPanel {

    private transient final AlmondOptions mAlmondOptions = AlmondOptions.getInstance();

    /**
     * Creates new form MenuModePanel
     */
    public MenuModePanel() {
        initComponents();
        load();
    }

    public MenuMode getMenuMode() {
        return menuBarRadioButton.isSelected() ? MenuMode.BAR : MenuMode.BUTTON;
    }

    public void save() {
        mAlmondOptions.setMenuMode(getMenuMode());
    }

    public void setMenuMode(MenuMode menuMode) {
        if (menuMode == MenuMode.BAR) {
            menuBarRadioButton.setSelected(true);
        } else {
            menuButtonRadioButton.setSelected(true);
        }
    }

    private void load() {
        setMenuMode(mAlmondOptions.getMenuMode());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        label = new javax.swing.JLabel();
        menuBarRadioButton = new javax.swing.JRadioButton();
        menuButtonRadioButton = new javax.swing.JRadioButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/almond/util/swing/dialogs/Bundle"); // NOI18N
        label.setText(bundle.getString("MenuModePanel.label.text")); // NOI18N

        buttonGroup.add(menuBarRadioButton);
        menuBarRadioButton.setText(bundle.getString("MenuModePanel.menuBarRadioButton.text")); // NOI18N

        buttonGroup.add(menuButtonRadioButton);
        menuButtonRadioButton.setText(bundle.getString("MenuModePanel.menuButtonRadioButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuBarRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuButtonRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menuBarRadioButton)
                    .addComponent(menuButtonRadioButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel label;
    private javax.swing.JRadioButton menuBarRadioButton;
    private javax.swing.JRadioButton menuButtonRadioButton;
    // End of variables declaration//GEN-END:variables

    public enum MenuMode {
        BAR, BUTTON;
    }
}
