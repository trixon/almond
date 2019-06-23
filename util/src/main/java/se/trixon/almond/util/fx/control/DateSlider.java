/*
 * Copyright 2019 Patrik Karlström.
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import org.apache.commons.lang3.ObjectUtils;

/**
 *
 * @author Patrik Karlström
 */
public class DateSlider extends Slider {

    private final SimpleObjectProperty<LocalDate> mDateProperty = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDate> mMaxDateProperty = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDate> mMinDateProperty = new SimpleObjectProperty<>();

    public DateSlider() {
        init();
        initListeners();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return mDateProperty;
    }

    public LocalDate getDate() {
        return mDateProperty.getValue();
    }

    public LocalDate getMaxDate() {
        return mMaxDateProperty.getValue();
    }

    public LocalDate getMinDate() {
        return mMinDateProperty.getValue();
    }

    public SimpleObjectProperty<LocalDate> maxDateProperty() {
        return mMaxDateProperty;
    }

    public SimpleObjectProperty<LocalDate> minDateProperty() {
        return mMinDateProperty;
    }

    public void setDate(LocalDate localDate) {
        mDateProperty.setValue(localDate);
    }

    public void setMaxDate(LocalDate localDate) {
        mMaxDateProperty.setValue(localDate);
    }

    public void setMinDate(LocalDate localDate) {
        mMinDateProperty.setValue(localDate);
    }

    public void setMinMaxDate(LocalDate minDate, LocalDate maxDate) {
        mMinDateProperty.setValue(minDate);
        mMaxDateProperty.setValue(maxDate);

        mDateProperty.setValue(maxDate);

        setMin(0);

        if (ObjectUtils.allNotNull(minDate, maxDate)) {
            setMax(ChronoUnit.DAYS.between(minDate, maxDate));
        }

        setValue(getMin());
    }

    private void init() {
        setMajorTickUnit(7);
        setMinorTickCount(1);
    }

    private void initListeners() {
        valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            dateProperty().setValue(getMinDate().plusDays(t1.intValue()));
        });

        dateProperty().addListener((ObservableValue<? extends LocalDate> ov, LocalDate t, LocalDate t1) -> {
            if (getMaxDate() != null) {
                setValue(ChronoUnit.DAYS.between(getMinDate(), t1));
            }
        });
    }
}
