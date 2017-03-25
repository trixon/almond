/*
 * Copyright 2017 Patrik Karlsson.
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
package se.trixon.almond.nbp.dialogs;

import org.openide.DialogDescriptor;
import org.openide.NotificationLineSupport;
import se.trixon.almond.util.swing.dialogs.cron.CronExprChangeListener;
import se.trixon.almond.util.swing.dialogs.cron.CronPanel;

/**
 *
 * @author Patrik Karlsson
 */
public class NbCronPanel extends CronPanel implements CronExprChangeListener {

    private DialogDescriptor mDialogDescriptor;
    private NotificationLineSupport mNotificationLineSupport;

    public NbCronPanel() {
        getNotificationLine().setVisible(false);
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

    public void setDialogDescriptor(DialogDescriptor dialogDescriptor) {
        mDialogDescriptor = dialogDescriptor;
        mNotificationLineSupport = dialogDescriptor.createNotificationLineSupport();
    }
}
