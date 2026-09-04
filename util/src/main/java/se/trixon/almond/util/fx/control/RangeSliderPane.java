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

import com.dlsc.gemsfx.Spacer;
import com.dlsc.gemsfx.util.SessionManager;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.controlsfx.control.RangeSlider;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public class RangeSliderPane extends GridPane {

    private final CheckBox mCheckBox = new CheckBox();
    private final CheckBox mInvertCheckBox = new CheckBox("Invertera");
    private final DoubleProperty mMaxProperty = new SimpleDoubleProperty();
    private Spinner<Double> mMaxSpinner;
    private double mMaxValue;
    private final DoubleProperty mMinProperty = new SimpleDoubleProperty();
    private Spinner<Double> mMinSpinner;
    private double mMinValue;
    private RangeSlider mSlider;
    private boolean mWithSpinners = true;

    public RangeSliderPane(String title, double minValue, double maxValue, boolean withSpinners) {
        super(FxHelper.getUIScaled(8), FxHelper.getUIScaled(2));
        mMinValue = minValue;
        mMaxValue = maxValue;
        mCheckBox.setText(title);
        mWithSpinners = withSpinners;

        createUI();
    }

    public RangeSliderPane(String title, double minValue, double maxValue) {
        super(FxHelper.getUIScaled(8), FxHelper.getUIScaled(2));
        mMinValue = minValue;
        mMaxValue = maxValue;
        mCheckBox.setText(title);

        createUI();
    }

    public void clear() {
        mCheckBox.setSelected(false);
        mInvertCheckBox.setSelected(false);
        mSlider.setLowValue(mMinValue);
        mSlider.setHighValue(mMaxValue);
    }

    public void initSession(String key, SessionManager sessionManager) {
        sessionManager.register(key + ".enabled", mCheckBox.selectedProperty());
        sessionManager.register(key + ".inverted", mInvertCheckBox.selectedProperty());
        sessionManager.register(key + ".min", mSlider.lowValueProperty());
        sessionManager.register(key + ".max", mSlider.highValueProperty());
    }

    public BooleanProperty invertedProperty() {
        return mInvertCheckBox.selectedProperty();
    }

    public boolean isInvertIncluded() {
        return mInvertCheckBox.isVisible();
    }

    public boolean isValueValid(double value) {
        if (selectedProperty().get()) {
            var inRange = FxHelper.inRange(value, minProperty(), maxProperty());
            if (isInvertIncluded() && invertedProperty().get()) {
                return !inRange;
            } else {
                return inRange;
            }
        } else {
            return true;
        }
    }

    public DoubleProperty maxProperty() {
        return mMaxProperty;
    }

    public DoubleProperty minProperty() {
        return mMinProperty;
    }

    public BooleanProperty selectedProperty() {
        return mCheckBox.selectedProperty();
    }

    public void setInvertIncluded(boolean enabled) {
        mInvertCheckBox.setVisible(enabled);
        mInvertCheckBox.setManaged(enabled);
    }

    public void setMinMaxValue(double minValue, double maxValue) {
        mMinValue = minValue;
        mMaxValue = maxValue;
        mSlider.setMin(minValue);
        mSlider.setMax(maxValue);

        var minValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minValue, maxValue, mMinSpinner.getValue());
        mMinSpinner.setValueFactory(minValueFactory);
        var maxValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minValue, maxValue, mMaxSpinner.getValue());
        mMaxSpinner.setValueFactory(maxValueFactory);
    }

    private void createUI() {
        var sliderWidth = FxHelper.getUIScaled(275.0);
        var spinnerWidth = FxHelper.getUIScaled(85.0);

        mSlider = new RangeSlider(mMinValue, mMaxValue, mMinValue, mMaxValue);
        mSlider.setBlockIncrement(1.0);
        mSlider.setShowTickLabels(true);
        mSlider.setShowTickMarks(true);
        mMinSpinner = new Spinner<>(mMinValue, mMaxValue, mMinValue, 0.1);
        mMaxSpinner = new Spinner<>(mMinValue, mMaxValue, mMaxValue, 0.1);
        mInvertCheckBox.setVisible(false);
        mInvertCheckBox.setManaged(false);

        var checkBoxBox = new HBox(mCheckBox, new Spacer(), mInvertCheckBox);
        add(checkBoxBox, 0, 0, GridPane.REMAINING, 1);
        if (mWithSpinners) {
            add(mMinSpinner, 0, 1);
        }
        add(mSlider, 1, 1);
        if (mWithSpinners) {
            add(mMaxSpinner, 2, 1);
        }

        List.of(mInvertCheckBox, mSlider, mMinSpinner, mMaxSpinner)
                .forEach(node -> node.disableProperty().bind(mCheckBox.selectedProperty().not()));

        GridPane.setFillWidth(mSlider, true);
        GridPane.setHgrow(mSlider, Priority.ALWAYS);
        mMinSpinner.setMinWidth(spinnerWidth);
        mMaxSpinner.setMinWidth(spinnerWidth);
        mMinSpinner.setPrefWidth(spinnerWidth);
        mMaxSpinner.setPrefWidth(spinnerWidth);
        mSlider.setPrefWidth(sliderWidth);
        mSlider.setMinWidth(sliderWidth);

        mSlider.lowValueProperty().addListener((p, o, n) -> {
            mMinSpinner.getValueFactory().setValue(n.doubleValue());
        });
        mMinSpinner.valueProperty().addListener((p, o, n) -> {
            mSlider.setLowValue(n);
        });

        mSlider.highValueProperty().addListener((p, o, n) -> {
            mMaxSpinner.getValueFactory().setValue(n.doubleValue());
        });
        mMaxSpinner.valueProperty().addListener((p, o, n) -> {
            mSlider.setHighValue(n);
        });

        FxHelper.setEditable(true, mMinSpinner, mMaxSpinner);
        FxHelper.autoCommitSpinners(mMinSpinner, mMaxSpinner);

        mMinProperty.bind(mSlider.lowValueProperty());
        mMaxProperty.bind(mSlider.highValueProperty());
    }

}
