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
package se.trixon.almond.util.fx.dialogs.cron;

import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class CronPane extends GridPane {

    private final SimpleStringProperty mCronProperty = new SimpleStringProperty();
    private final CronSection[] mCronSections = new CronSection[5];
    private final ComboBox<CronPreset> mPresetComboBox = new ComboBox<>();

    public CronPane() {
        createUI();
        initPresets();
        initListeners();
    }

    public SimpleStringProperty cronProperty() {
        return mCronProperty;
    }

    public String getCron() {
        return mCronProperty.get();
    }

    public void load(CronItem cronItem) {
        String cronString;
        if (cronItem == null || cronItem.getName() == null) {
            cronString = "0 * * * *";
        } else {
            cronString = cronItem.getName();
        }

        var cronItems = cronString.split(" ");

        for (int i = 0; i < 5; i++) {
            try {
                mCronSections[i].load(cronItems[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                mCronSections[i].load("*");
            }
        }
    }

    private void createUI() {
        setHgap(FxHelper.getUIScaled(16));
        var presetLabel = new Label(Dict.PRESETS.toString());
        var presetBox = new VBox(0, presetLabel, mPresetComboBox);
        presetBox.setPadding(FxHelper.getUIScaledInsets(8, 0, 8, 0));
        add(presetBox, 0, 0, REMAINING, 1);

        var limits = new int[]{60, 24, 31, 12, 7};
        for (int i = 0; i < mCronSections.length; i++) {
            var cronSection = new CronSection(limits[i]);
            mCronSections[i] = cronSection;

            add(cronSection, i, 2);
        }
        FxHelper.autoSizeRegionVertical(mCronSections);
    }

    private void initListeners() {
        mPresetComboBox.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
            load(new CronItem(n.pattern()));
        });

        for (var cronSection : mCronSections) {
            cronSection.cronProperty().addListener((p, o, n) -> {
                var crons = Arrays.stream(mCronSections).map(section -> section.getCron()).toList();
                mCronProperty.set(String.join(" ", crons));
            });
        }
    }

    private void initPresets() {
        var bundle = SystemHelper.getBundle(CronPreset.class, "Bundle");

        mPresetComboBox.getItems().setAll(
                new CronPreset(bundle.getString("preset1"), "*/10 * * * * *"),
                new CronPreset(bundle.getString("preset2"), "0 * * * * *"),
                new CronPreset(bundle.getString("preset3"), "*/15 8-17 * * 1-5"),
                new CronPreset(bundle.getString("preset4"), "0 12 */2 * *"),
                new CronPreset(bundle.getString("preset5"), "* 6-8,18-20 * 5 *")
        );
    }
}
