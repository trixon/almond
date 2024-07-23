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

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.ArrayHelper;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.StringHelper;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class CronSection extends VBox {

    private final RadioButton mAllRadioButton = new RadioButton(Dict.ALL.toString());
    private final CheckBox mCheckBox = new CheckBox(Dict.EVERY.toString());
    private final SimpleStringProperty mCronProperty = new SimpleStringProperty("*");
    private final Label mLabel = new Label();
    private final ListView<String> mListView = new ListView<>();
    private final int mMode;
    private final int mOffset;
    private final RadioButton mSelectedRadioButton = new RadioButton(Dict.SELECTED.toString());
    private Spinner<Integer> mSpinner;

    public CronSection(int mode) {
        mMode = mode;
        if (mMode == 12 || mMode == 31) {
            mOffset = 1;
        } else {
            mOffset = 0;
        }

        createUI();
        initBindings();
        initListeners();
    }

    public SimpleStringProperty cronProperty() {
        return mCronProperty;
    }

    public String getCron() {
        return mCronProperty.get();
    }

    void load(String cronString) {
        boolean hasLast = cronString.contains("L");

        if (hasLast) {
            cronString = cronString.replace(",L", "").replace("L", "");
        }

        String[] every = StringUtils.split(cronString, "/");
        String ab = every[0];
        String c = null;

        if (every.length == 2) {
            c = every[1];
            mSpinner.getValueFactory().setValue(Integer.valueOf(c));
        }

        mCheckBox.setSelected(c != null);

        if (ab.contains("*")) {
            mAllRadioButton.setSelected(true);
        } else {
            mSelectedRadioButton.setSelected(true);
            var selections = StringHelper.intervalStringToArray(ab);
            var indices = ArrayHelper.stringToInt(selections);
            indices = ArrayHelper.adjustOffset(indices, -1 * mOffset);

            if (hasLast) {
                indices = ArrayUtils.add(indices, mListView.getItems().size() - 1);
            }

            mListView.getSelectionModel().clearSelection();
            for (int index : indices) {
                mListView.getSelectionModel().select(index);
            }
        }
    }

    private void createUI() {
        setSpacing(FxHelper.getUIScaled(4));

        var title = switch (mMode) {
            case 60 ->
                Dict.Time.MINUTES.toString();
            case 24 ->
                Dict.Time.HOURS.toString();
            case 31 ->
                Dict.Time.DAYS.toString();
            case 12 ->
                Dict.Time.MONTHS.toString();
            case 7 ->
                Dict.Time.DAYS_OF_WEEK.toString();
            default ->
                "-";
        };

        mLabel.setText(title);
        mLabel.setStyle("-fx-font-size: %dpx;".formatted((int) (FxHelper.getScaledFontSize() * 1.4)));

        var toggleGroup = new ToggleGroup();
        mAllRadioButton.setToggleGroup(toggleGroup);
        mSelectedRadioButton.setToggleGroup(toggleGroup);
        mListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        mAllRadioButton.setSelected(true);

        var textStyle = TextStyle.FULL;
        var locale = Locale.getDefault();
        if (Set.of(60, 24, 31).contains(mMode)) {
            for (int i = 0; i < mMode; i++) {
                mListView.getItems().add(String.valueOf(i));
            }
            if (31 == mMode) {
                mListView.getItems().add(String.valueOf(mMode));
                mListView.getItems().add(Dict.LAST.toString());
                mListView.getItems().remove(0);
            }
        } else if (12 == mMode) {
            for (int i = 0; i < mMode; i++) {
                mListView.getItems().add(Month.of(i + 1).getDisplayName(textStyle, locale));
            }
        } else if (7 == mMode) {
            mListView.getItems().add(DayOfWeek.of(7).getDisplayName(textStyle, locale));
            for (int i = 1; i < 7; i++) {
                mListView.getItems().add(DayOfWeek.of(i).getDisplayName(textStyle, locale));
            }
        }

        mSpinner = new Spinner<>(2, mMode - 1, 2);
        //FxHelper.setEditable(true, mSpinner);
        //FxHelper.autoCommitSpinners(mSpinner);
        var box = new VBox(getSpacing(), mCheckBox, mSpinner);
        box.setPadding(FxHelper.getUIScaledInsets(8, 0, 8, 0));
        getChildren().addAll(
                mLabel,
                mAllRadioButton,
                mSelectedRadioButton,
                mListView,
                box
        );

        VBox.setVgrow(mListView, Priority.ALWAYS);
    }

    private void initBindings() {
        mListView.disableProperty().bind(mAllRadioButton.selectedProperty());
        mSpinner.disableProperty().bind(mCheckBox.selectedProperty().not());
    }

    private void initListeners() {
        mAllRadioButton.selectedProperty().addListener((p, o, n) -> {
            if (n) {
                mListView.getSelectionModel().clearSelection();
            }
            recalculateExpression();
        });

        ChangeListener<Object> listener = (p, o, n) -> {
            recalculateExpression();
        };

        mListView.getSelectionModel().selectedItemProperty().addListener(listener);
        mCheckBox.selectedProperty().addListener(listener);
        mSpinner.valueProperty().addListener(listener);
    }

    private void recalculateExpression() {
        var sb = new StringBuilder();
        if (mAllRadioButton.isSelected() || mListView.getSelectionModel().isEmpty()) {
            sb.append("*");
        } else {
            var rawIndices = mListView.getSelectionModel().getSelectedIndices().stream().mapToInt(x -> x).toArray();
            var indices = ArrayHelper.adjustOffset(rawIndices, mOffset);
            sb.append(StringHelper.arrayToIntervalString(indices));
        }

        if (mCheckBox.isSelected()) {
            sb.append("/").append(mSpinner.getValue());
        }

        var cronString = sb.toString();

        if (mSelectedRadioButton.isSelected() && mMode == 31) {
            cronString = cronString.replace("32", "L");
        }

        mCronProperty.set(cronString);
    }

}
