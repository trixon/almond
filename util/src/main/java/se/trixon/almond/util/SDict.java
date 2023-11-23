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
package se.trixon.almond.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Patrik Karlström
 */
public enum SDict {
    ACOUSTIC,
    ALARM,
    ALARMS,
    ALARM_HEIGHT,
    ALARM_LEVEL,
    ALARM_PLANE,
    DIMENSION,
    FREQUENCY,
    GROUNDWATER,
    HAS_VALID_FROM,
    HAS_VALID_TO,
    HYDROGEOLOGY,
    IS_INVALID,
    IS_VALID,
    LATEST,
    LATEST_S,
    LEVEL,
    MEASUREMENT,
    MEASUREMENT_NEED,
    MEASUREMENTS,
    OPERATOR,
    POINT,
    POINTS,
    ROLLING,
    SURVEYOR,
    SURVEYORS,
    TOPOGRAPHY,
    TRACE,
    TRACE_1D,
    TRACE_2D,
    TRACE_3D,
    VALID_FROM,
    VALID_FROM_TO,
    VALID_TO,
    VECTOR,
    VECTOR_1D,
    VECTOR_2D,
    VECTOR_3D,
    WITHOUT_VALID_FROM,
    WITHOUT_VALID_TO,
    ZERO;
    private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(SDict.class) + "SDict", Locale.getDefault());

    private static String getString(ResourceBundle bundle, String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "Key not found: " + key;
        }
    }

    public String toLower() {
        return toString().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
    }

    public String toUpper() {
        return toString().toUpperCase(Locale.ROOT);
    }

}
