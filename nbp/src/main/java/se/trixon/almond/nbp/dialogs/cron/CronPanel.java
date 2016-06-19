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
package se.trixon.almond.nbp.dialogs.cron;

import java.util.ArrayList;
import java.util.ResourceBundle;
import org.openide.DialogDescriptor;
import org.openide.NotificationLineSupport;
import org.openide.util.NbBundle;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CronPanel extends javax.swing.JPanel implements CronExprChangeListener {

    ElementPanel[] mElementPanels = new ElementPanel[5];
    private final ResourceBundle mBundle;
    private DialogDescriptor mDialogDescriptor;
    private NotificationLineSupport mNotificationLineSupport;
    private CronExprChangeListener mCronExprChangeListener;

    /**
     * Creates new form CronPanel
     */
    public CronPanel() {
        initComponents();
        mBundle = NbBundle.getBundle(CronPanel.class);

        initPresets();
        mElementPanels[0] = elementMinutePanel;
        mElementPanels[1] = elementHourPanel;
        mElementPanels[2] = elementDomPanel;
        mElementPanels[3] = elementMonthPanel;
        mElementPanels[4] = elementDowPanel;

        for (ElementPanel elementPanel : mElementPanels) {
            elementPanel.setCronExprChangeListener(this);
        }
    }

    public String getCronString() {
        String cron = String.format("%s %s %s %s %s",
                mElementPanels[0].getCronString(),
                mElementPanels[1].getCronString(),
                mElementPanels[2].getCronString(),
                mElementPanels[3].getCronString(),
                mElementPanels[4].getCronString());

        return cron;
    }

    public boolean isCronValid() {
        boolean valid = true;

        for (ElementPanel elementPanel : mElementPanels) {
            String cron = elementPanel.getCronString();
            if (cron.isEmpty() || cron.startsWith("/")) {
                valid = false;
            }
        }

        return valid;
    }

    @Override
    public void onCronExprChanged() {
        try {
            if (isCronValid()) {
                if (mCronExprChangeListener != null) {
                    mCronExprChangeListener.onCronExprChanged(getCronString());
                }
                mNotificationLineSupport.setInformationMessage(getCronString());
                mDialogDescriptor.setValid(true);
            } else {
                if (mCronExprChangeListener != null) {
                    mCronExprChangeListener.onCronExprInvalid();
                }
                mNotificationLineSupport.setErrorMessage(getCronString());
                mDialogDescriptor.setValid(false);
            }

        } catch (NullPointerException e) {
        }
    }

    @Override
    public void onCronExprChanged(String cronString) {
        // nvm
    }

    @Override
    public void onCronExprInvalid() {
        // nvm
    }

    public void setCronString(String cronString) {
        if (cronString == null) {
            cronString = "0 * * * *";
        }
        String[] cronItems = cronString.split(" ");

        for (int i = 0; i < 5; i++) {
            try {
                mElementPanels[i].setCronString(cronItems[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                mElementPanels[i].setCronString("*");
            }
        }

        onCronExprChanged();
    }

    public void setDialogDescriptor(DialogDescriptor dialogDescriptor) {
        mDialogDescriptor = dialogDescriptor;
        mNotificationLineSupport = dialogDescriptor.createNotificationLineSupport();
    }

    public void setCronExprChangeListener(CronExprChangeListener cronExprChangeListener) {
        mCronExprChangeListener = cronExprChangeListener;
    }

    private void initPresets() {
        ArrayList<Preset> presets = new ArrayList<>();
        presets.add(new Preset());

        presets.add(new Preset(mBundle.getString("preset1"), "*/10 * * * * *"));
        presets.add(new Preset(mBundle.getString("preset2"), "0 * * * * *"));
        presets.add(new Preset(mBundle.getString("preset3"), "*/15 8-17 * * 1-5"));
        presets.add(new Preset(mBundle.getString("preset4"), "0 12 */2 * *"));
        presets.add(new Preset(mBundle.getString("preset5"), "* 6-8,18-20 * 5 *"));

        presetComboBox.setModel(new javax.swing.DefaultComboBoxModel(presets.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        presetLabel = new javax.swing.JLabel();
        presetComboBox = new javax.swing.JComboBox();
        elementsPanel = new javax.swing.JPanel();
        elementMinutePanel = new se.trixon.almond.nbp.dialogs.cron.ElementMinutePanel();
        elementHourPanel = new se.trixon.almond.nbp.dialogs.cron.ElementHourPanel();
        elementDomPanel = new se.trixon.almond.nbp.dialogs.cron.ElementDomPanel();
        elementMonthPanel = new se.trixon.almond.nbp.dialogs.cron.ElementMonthPanel();
        elementDowPanel = new se.trixon.almond.nbp.dialogs.cron.ElementDowPanel();

        org.openide.awt.Mnemonics.setLocalizedText(presetLabel, Dict.PRESETS.getString());

        presetComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetComboBoxActionPerformed(evt);
            }
        });

        elementsPanel.setLayout(new java.awt.GridLayout(1, 0));

        elementMinutePanel.setText(org.openide.util.NbBundle.getMessage(CronPanel.class, "CronPanel.elementMinutePanel.text")); // NOI18N
        elementsPanel.add(elementMinutePanel);

        elementHourPanel.setText(org.openide.util.NbBundle.getMessage(CronPanel.class, "CronPanel.elementHourPanel.text")); // NOI18N
        elementsPanel.add(elementHourPanel);

        elementDomPanel.setText(org.openide.util.NbBundle.getMessage(CronPanel.class, "CronPanel.elementDomPanel.text")); // NOI18N
        elementsPanel.add(elementDomPanel);

        elementMonthPanel.setText(org.openide.util.NbBundle.getMessage(CronPanel.class, "CronPanel.elementMonthPanel.text")); // NOI18N
        elementsPanel.add(elementMonthPanel);

        elementDowPanel.setText(org.openide.util.NbBundle.getMessage(CronPanel.class, "CronPanel.elementDowPanel.text")); // NOI18N
        elementsPanel.add(elementDowPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presetLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(presetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(elementsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presetLabel)
                    .addComponent(presetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elementsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void presetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presetComboBoxActionPerformed
        if (presetComboBox.getSelectedIndex() > 0) {
            Preset preset = (Preset) presetComboBox.getSelectedItem();
            setCronString(preset.getPattern());
        }
    }//GEN-LAST:event_presetComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private se.trixon.almond.nbp.dialogs.cron.ElementDomPanel elementDomPanel;
    private se.trixon.almond.nbp.dialogs.cron.ElementDowPanel elementDowPanel;
    private se.trixon.almond.nbp.dialogs.cron.ElementHourPanel elementHourPanel;
    private se.trixon.almond.nbp.dialogs.cron.ElementMinutePanel elementMinutePanel;
    private se.trixon.almond.nbp.dialogs.cron.ElementMonthPanel elementMonthPanel;
    private javax.swing.JPanel elementsPanel;
    private javax.swing.JComboBox presetComboBox;
    private javax.swing.JLabel presetLabel;
    // End of variables declaration//GEN-END:variables
}
