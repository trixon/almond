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
package se.trixon.almond.util.fx.control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.controlsfx.control.RangeSlider;

/**
 *
 * @author Patrik Karlström
 */
public class DateRangeSlider extends RangeSlider {

    private final SimpleObjectProperty<LocalDate> mHighDateProperty = new SimpleObjectProperty<>();
    private final SimpleStringProperty mHighStringProperty = new SimpleStringProperty();
    private final SimpleObjectProperty<LocalDate> mLowDateProperty = new SimpleObjectProperty<>();
    private final SimpleStringProperty mLowStringProperty = new SimpleStringProperty();
    private final SimpleObjectProperty<LocalDate> mMaxDateProperty = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDate> mMinDateProperty = new SimpleObjectProperty<>();

    public DateRangeSlider() {
        init();
        initListeners();
    }

    public LocalDate getHighDate() {
        return mHighDateProperty.getValue();
    }

    public LocalDate getLowDate() {
        return mLowDateProperty.getValue();
    }

    public LocalDate getMaxDate() {
        return mMaxDateProperty.getValue();
    }

    public LocalDate getMinDate() {
        return mMinDateProperty.getValue();
    }

    public SimpleObjectProperty<LocalDate> highDateProperty() {
        return mHighDateProperty;
    }

    public SimpleStringProperty highStringProperty() {
        return mHighStringProperty;
    }

    public SimpleObjectProperty<LocalDate> lowDateProperty() {
        return mLowDateProperty;
    }

    public SimpleStringProperty lowStringProperty() {
        return mLowStringProperty;
    }

    public SimpleObjectProperty<LocalDate> maxDateProperty() {
        return mMaxDateProperty;
    }

    public SimpleObjectProperty<LocalDate> minDateProperty() {
        return mMinDateProperty;
    }

    public void reset() {
        setLowDate(getMinDate());
        setHighDate(getMaxDate());
    }

    public void setHighDate(LocalDate localDate) {
        mHighDateProperty.setValue(localDate);
    }

    public void setLowDate(LocalDate localDate) {
        mLowDateProperty.setValue(localDate);
    }

    public void setLowHighDate(LocalDate lowDate, LocalDate highDate) {
        if (lowDate.isAfter(getHighDate())) {
            setHighDate(highDate);
            setLowDate(lowDate);
        } else {
            setLowDate(lowDate);
            setHighDate(highDate);
        }
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

        mLowDateProperty.setValue(minDate);
        mHighDateProperty.setValue(maxDate);

        setMin(0);

        if (ObjectUtils.allNotNull(minDate, maxDate)) {
            setMax(ChronoUnit.DAYS.between(minDate, maxDate));
        }

        setLowValue(getMin());
        setHighValue(getMax());
    }

    private void init() {
        setMajorTickUnit(7);
        setMinorTickCount(1);
    }

    private void initListeners() {
        lowValueProperty().addListener((p, o, n) -> {
            if (getMinDate() != null) {
                lowDateProperty().setValue(getMinDate().plusDays(n.intValue()));
            }
        });

        highValueProperty().addListener((p, o, n) -> {
            if (getMinDate() != null) {
                highDateProperty().setValue(getMinDate().plusDays(n.intValue()));
            }
        });

        lowDateProperty().addListener((p, o, n) -> {
            if (getMinDate() != null) {
                setLowValue(ChronoUnit.DAYS.between(getMinDate(), n));
            }
        });

        highDateProperty().addListener((p, o, n) -> {
            if (getMaxDate() != null) {
                setHighValue(ChronoUnit.DAYS.between(getMinDate(), n));
            }
        });

        mLowDateProperty.addListener((p, o, n) -> {
            mLowStringProperty.set(n.toString());
        });

        mLowStringProperty.addListener((p, o, n) -> {
            mLowDateProperty.set(LocalDate.parse(n));
        });

        mHighDateProperty.addListener((p, o, n) -> {
            mHighStringProperty.set(n.toString());
        });

        mHighStringProperty.addListener((p, o, n) -> {
            mHighDateProperty.set(LocalDate.parse(n));
        });
    }
}
