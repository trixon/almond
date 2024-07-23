/*
 * Copyright 2024 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.nbp.fx;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javax.swing.SwingUtilities;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotificationLineSupport;
import se.trixon.almond.util.fx.FxHelper;
import se.trixon.almond.util.fx.control.editable_list.DefaultEditableListItem;
import se.trixon.almond.util.fx.control.editable_list.EditableList;
import se.trixon.almond.util.fx.dialogs.cron.CronPane;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class NbCronPane {

    private EditableList<DefaultEditableListItem> mEditableList;
    private final int mIconSize;
    private final ObjectProperty<ObservableList<DefaultEditableListItem>> mItemsProperty = new SimpleObjectProperty<>();
    private NotificationLineSupport mNotificationLineSupport;

    public NbCronPane(int iconSize) {
        mIconSize = iconSize;
        mItemsProperty.setValue(FXCollections.observableArrayList());
        createUI();
    }

    public EditableList<DefaultEditableListItem> getEditableList() {
        return mEditableList;
    }

    public final ObservableList<DefaultEditableListItem> getItems() {
        return mItemsProperty.get();
    }

    public ObjectProperty<ObservableList<DefaultEditableListItem>> itemsProperty() {
        return mItemsProperty;
    }

    private void createUI() {
        mEditableList = new NbEditableList.Builder<DefaultEditableListItem>()
                .setIconSize(mIconSize)
                .setOnEdit((title, item) -> {
                    edit(title, item);
                    mEditableList.getListView().refresh();
                })
                .setOnRemoveAll(() -> {
                    getItems().clear();
                })
                .setOnRemove(item -> {
                    getItems().remove(item);
                })
                .setOnClone(item -> {
                    var clone = new DefaultEditableListItem(item.getName());
                    getItems().add(clone);
                    mEditableList.getListView().refresh();
                    return clone;
                })
                .setItemsProperty(itemsProperty())
                .build();
    }

    private void edit(String title, DefaultEditableListItem item) {
        var cronPane = new CronPane();
        cronPane.cronProperty().addListener((p, o, n) -> {
            mNotificationLineSupport.setInformationMessage(n);
        });
        cronPane.setPadding(FxHelper.getUIScaledInsets(2, 8, 0, 8));
        var scene = new Scene(cronPane);
        var dialogPanel = new FxDialogPanel() {
            @Override
            protected void fxConstructor() {
                setScene(scene);
            }
        };
        dialogPanel.setPreferredSize(SwingHelper.getUIScaledDim(800, 500));

        SwingUtilities.invokeLater(() -> {
            var d = new DialogDescriptor(dialogPanel, title);
            mNotificationLineSupport = d.createNotificationLineSupport();
            dialogPanel.setNotifyDescriptor(d);
            dialogPanel.initFx(() -> {
                cronPane.load(item);
                mNotificationLineSupport.setInformationMessage(cronPane.getCron());
            });

            if (DialogDescriptor.OK_OPTION == DialogDisplayer.getDefault().notify(d)) {
                Platform.runLater(() -> {
                    if (item == null) {
                        var newItem = new DefaultEditableListItem(cronPane.getCron());
                        getItems().add(newItem);
                    } else {
                        item.setName(cronPane.getCron());
                    }
                });
            }
        });
    }
}
