/*
 * Copyright 2025 Patrik Karlström.
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
package se.trixon.almond.util;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 *
 * @author Patrik Karlström
 */
public class MinMaxCollection {

    private double mMax;
    private double mMin;

    public MinMaxCollection() {
        reset();
    }

    public void add(Double value) {
        if (value != null) {
            if (value > mMax) {
                mMax = value;
            }

            if (value < mMin) {
                mMin = value;
            }
        }
    }

    public void add(Double... values) {
        for (var value : values) {
            add(value);
        }
    }

    public void add(OptionalDouble value) {
        if (value.isPresent()) {
            add(value.getAsDouble());
        }
    }

    public void add(DoubleStream stream) {
        add(stream.min());
        add(stream.max());
    }

    public double getMax() {
        return mMax;
    }

    public double getMin() {
        return mMin;
    }

    public final void reset() {
        mMin = Double.MAX_VALUE;
        mMax = Double.MIN_VALUE;
    }
}
