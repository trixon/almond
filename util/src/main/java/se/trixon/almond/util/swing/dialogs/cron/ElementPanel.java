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
package se.trixon.almond.util.swing.dialogs.cron;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.ArrayHelper;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.StringHelper;

/**
 *
 * @author Patrik Karlström
 */
public abstract class ElementPanel extends javax.swing.JPanel {

    protected final DefaultListModel<String> mListModel = new DefaultListModel<>();
    protected final ArrayList<String> mArray = new ArrayList<>();
    private int mOffset = 0;
    private transient CronExprChangeListener mCronExprChangeListener;
    private boolean mHasLast;

    /**
     * Creates new form ElementPanel
     */
    public ElementPanel() {
        initComponents();
        list.addListSelectionListener((ListSelectionEvent e) -> {
            exprChanged();
        });
        spinner.addChangeListener((ChangeEvent e) -> {
            exprChanged();
        });
    }

    public String getCronString() {
        StringBuilder stringBuilder = new StringBuilder();
        int[] indices = null;
        if (allRadioButton.isSelected()) {
            stringBuilder.append("*");
        } else {
            indices = ArrayHelper.adjustOffset(list.getSelectedIndices(), mOffset);
            stringBuilder.append(StringHelper.arrayToIntervalString(indices));
        }

        if (checkBox.isSelected()) {
            stringBuilder.append("/").append(spinner.getValue());
        }

        String cronString = stringBuilder.toString();

        if (selectedRadioButton.isSelected() && mHasLast) {
            String lastIndex = String.valueOf(mListModel.getSize());
            cronString = cronString.replace(lastIndex, "L");
        }

        return cronString;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setCronExprChangeListener(CronExprChangeListener cronExprChangeListener) {
        mCronExprChangeListener = cronExprChangeListener;
    }

    public void setHasLast(boolean hasLast) {
        mHasLast = hasLast;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public void setCronString(String cronString) {
        boolean hasLast = cronString.contains("L");

        if (hasLast) {
            cronString = cronString.replace(",L", "").replace("L", "");
        }

        String[] every = StringUtils.split(cronString, "/");
        String ab = every[0];
        String c = null;

        if (every.length == 2) {
            c = every[1];
            spinner.setValue(Integer.valueOf(c));
        }

        checkBox.setSelected(c != null);
        spinner.setEnabled(c != null);

        if (ab.contains("*")) {
            allRadioButton.doClick();
        } else {
            selectedRadioButton.doClick();
            String[] selections = StringHelper.intervalStringToArray(ab);
            int[] indices = ArrayHelper.stringToInt(selections);
            indices = ArrayHelper.adjustOffset(indices, -1 * mOffset);

            if (hasLast) {
                indices = ArrayUtils.add(indices, mListModel.getSize() - 1);
            }

            list.setSelectedIndices(indices);
        }
    }

    public JList getList() {
        return list;
    }

    public void setText(String text) {
        label.setText(text);
    }

    protected void initModel(ArrayList<String> arrayList) {
        arrayList.stream().forEach((string) -> {
            mListModel.addElement(string);
        });

        list.setModel(mListModel);
        spinner.setModel(new SpinnerNumberModel(2, 2, mListModel.getSize() + mOffset - 1, 1));
    }

    private void exprChanged() {
        mCronExprChangeListener.onCronExprChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        label = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        allRadioButton = new javax.swing.JRadioButton();
        selectedRadioButton = new javax.swing.JRadioButton();
        checkBox = new javax.swing.JCheckBox();
        spinner = new javax.swing.JSpinner();

        setPreferredSize(new java.awt.Dimension(150, 300));

        label.setText("Label"); // NOI18N

        list.setEnabled(false);
        scrollPane.setViewportView(list);

        buttonGroup.add(allRadioButton);
        allRadioButton.setSelected(true);
        allRadioButton.setText(Dict.ALL.toString());
        allRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(selectedRadioButton);
        selectedRadioButton.setText(Dict.SELECTED.toString());
        selectedRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedRadioButtonActionPerformed(evt);
            }
        });

        checkBox.setText(Dict.EVERY.toString());
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addComponent(allRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(checkBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spinner))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectedRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedRadioButtonActionPerformed
        list.setEnabled(true);
        exprChanged();
    }//GEN-LAST:event_selectedRadioButtonActionPerformed

    private void allRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allRadioButtonActionPerformed
        list.clearSelection();
        list.setEnabled(false);
        exprChanged();
    }//GEN-LAST:event_allRadioButtonActionPerformed

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActionPerformed
        spinner.setEnabled(checkBox.isSelected());
        exprChanged();
    }//GEN-LAST:event_checkBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> list;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JRadioButton selectedRadioButton;
    private javax.swing.JSpinner spinner;
    // End of variables declaration//GEN-END:variables
}
