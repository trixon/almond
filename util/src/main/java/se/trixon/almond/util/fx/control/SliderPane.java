/*
 * Copyright 2024 Patrik Karlström.
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
package se.trixon.almond.util.fx.control;

import com.dlsc.gemsfx.util.SessionManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class SliderPane extends BorderPane {

    private final CheckBox mCheckBox = new CheckBox();
    private final boolean mDisplayCheckBox;
    private final boolean mDisplaySpinner;
    private final double mMaxValue;
    private double mMinValue;
    private Slider mSlider;
    private Spinner<Double> mSpinner;
    private final double mStep;
    private final DoubleProperty mValueProperty = new SimpleDoubleProperty();

    public SliderPane(String title, double maxValue) {
        this(title, maxValue, true);
    }

    public SliderPane(String title, double maxValue, boolean displayspinner) {
        this(title, maxValue, displayspinner, 0.1);
    }

    public SliderPane(String title, double maxValue, boolean displayspinner, double step) {
        this(title, maxValue, displayspinner, true, step);
    }

    public SliderPane(String title, double maxValue, boolean displaySpinner, boolean displayCheckBox, double step) {
        this(title, 0, maxValue, displaySpinner, displayCheckBox, step);
    }

    public SliderPane(String title, double minValue, double maxValue, boolean displaySpinner, boolean displayCheckBox, double step) {
        mStep = step;
        mMinValue = minValue;
        mMaxValue = maxValue;
        mCheckBox.setText(title);
        mDisplaySpinner = displaySpinner;
        mDisplayCheckBox = displayCheckBox;
        if (!displayCheckBox) {
            setSelected(true);
        }
        createUI();
    }

    public void clear() {
        mCheckBox.setSelected(false);
        mSlider.setValue(0);
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public Double getValue() {
        return mValueProperty.getValue();
    }

    public void initSession(String key, SessionManager sessionManager) {
        sessionManager.register(key + ".enabled", mCheckBox.selectedProperty());
        sessionManager.register(key + ".value", mSlider.valueProperty());
    }

    public boolean isSelected() {
        return mCheckBox.isSelected();
    }

    public BooleanProperty selectedProperty() {
        return mCheckBox.selectedProperty();
    }

    public void setSelected(boolean selected) {
        mCheckBox.setSelected(selected);
    }

    public void setTitle(String title) {
        mCheckBox.setText(title);
    }

    public void setValue(double value) {
        mValueProperty.setValue(value);
    }

    public DoubleProperty valueProperty() {
        return mValueProperty;
    }

    private void createUI() {
        var spinnerWidth = FxHelper.getUIScaled(65.0);

        mSlider = new Slider(mMinValue, mMaxValue, 0.1);
        mSlider.setBlockIncrement(1.0);
        mSlider.setShowTickLabels(true);
        mSlider.setShowTickMarks(true);
        mSpinner = new Spinner<>(mMinValue, mMaxValue, 0, mStep);
        if (mDisplayCheckBox) {
            mCheckBox.setPadding(FxHelper.getUIScaledInsets(0, 0, 2, 0));
            setTop(mCheckBox);
        } else {
            setTop(new Label(mCheckBox.getText()));
        }
        if (mDisplaySpinner) {
            var leftBorderPane = new BorderPane(mSpinner);
            leftBorderPane.setPadding(FxHelper.getUIScaledInsets(0, 8, 0, 0));
            setLeft(leftBorderPane);
        }
        setCenter(mSlider);

        mSlider.disableProperty().bind(mCheckBox.selectedProperty().not());
        mSpinner.disableProperty().bind(mCheckBox.selectedProperty().not());

        mSpinner.setPrefWidth(spinnerWidth);
        mSlider.valueProperty().addListener((p, o, n) -> {
            mSpinner.getValueFactory().setValue(n.doubleValue());
        });
        mSpinner.valueProperty().addListener((p, o, n) -> {
            mSlider.setValue(n);
        });

        FxHelper.setEditable(true, mSpinner);
        FxHelper.autoCommitSpinners(mSpinner);

        mValueProperty.bindBidirectional(mSlider.valueProperty());
    }

}
