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
package se.trixon.almond.util.fx.session;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Spinner;

/**
 *
 * @author Patrik Karlström
 */
public class SessionDoubleSpinner extends Spinner<Double> {

    private SpinnerDoubleSession mSession;

    public SessionDoubleSpinner() {
        init();
    }

    public SessionDoubleSpinner(double d, double d1, double d2) {
        super(d, d1, d2);
        init();
    }

    public SessionDoubleSpinner(double d, double d1, double d2, double d3) {
        super(d, d1, d2, d3);
        init();
    }

    public SpinnerDoubleSession getSession() {
        return mSession;
    }

    public SimpleDoubleProperty sessionValueProperty() {
        return mSession.valueProperty();
    }

    public void load() {
        mSession.load();
    }

    private void init() {
        mSession = new SpinnerDoubleSession(this);
    }
}
