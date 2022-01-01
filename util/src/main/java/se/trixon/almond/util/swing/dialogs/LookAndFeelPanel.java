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

import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström
 */
public class LookAndFeelPanel extends javax.swing.JPanel {

    private transient final AlmondOptions mAlmondOptions = AlmondOptions.getInstance();

    /**
     * Creates new form LookAndFeelPanel
     */
    public LookAndFeelPanel() {
        initComponents();
        load();
    }

    public void save() {
        mAlmondOptions.setForceLookAndFeel(lafForceCheckBox.isSelected());
        mAlmondOptions.setLookAndFeel((String) lafComboBox.getSelectedItem());
        mAlmondOptions.setDisplayMenuIcons(menuIconsCheckBox.isSelected());
    }

    private void load() {
        lafForceCheckBox.setSelected(mAlmondOptions.isForceLookAndFeel());
        lafComboBox.setModel(SwingHelper.getLookAndFeelComboBoxModel(true));
        lafComboBox.setSelectedItem(mAlmondOptions.getLookAndFeel());
        lafForceCheckBoxActionPerformed(null);
        menuIconsCheckBox.setSelected(mAlmondOptions.isDisplayMenuIcons());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lafForceCheckBox = new javax.swing.JCheckBox();
        lafLabel = new javax.swing.JLabel();
        lafComboBox = new javax.swing.JComboBox<>();
        menuIconsCheckBox = new javax.swing.JCheckBox();

        jLabel1.setFont(jLabel1.getFont().deriveFont((jLabel1.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
        jLabel1.setText(Dict.LOOK_AND_FEEL.toString());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/almond/util/swing/dialogs/Bundle"); // NOI18N
        lafForceCheckBox.setText(bundle.getString("LookAndFeelPanel.lafForceCheckBox.text")); // NOI18N
        lafForceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lafForceCheckBoxActionPerformed(evt);
            }
        });

        lafLabel.setText(Dict.THEME.toString());

        menuIconsCheckBox.setText(bundle.getString("LookAndFeelPanel.menuIconsCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lafComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lafForceCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuIconsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lafLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lafForceCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lafLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lafComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuIconsCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lafForceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lafForceCheckBoxActionPerformed
        lafComboBox.setEnabled(lafForceCheckBox.isSelected());
        lafLabel.setEnabled(lafForceCheckBox.isSelected());
    }//GEN-LAST:event_lafForceCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> lafComboBox;
    private javax.swing.JCheckBox lafForceCheckBox;
    private javax.swing.JLabel lafLabel;
    private javax.swing.JCheckBox menuIconsCheckBox;
    // End of variables declaration//GEN-END:variables
}
