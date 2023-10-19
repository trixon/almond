/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.trixon.almond.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Patrik Karlström
 */
public enum SDict {
    ALARM,
    ALARMS,
    ALARM_HEIGHT,
    ALARM_PLANE,
    DIMENSION,
    FREQUENCY,
    HAS_VALID_FROM,
    HAS_VALID_TO,
    IS_INVALID,
    IS_VALID,
    LATEST,
    LATEST_S,
    MEASUREMENT,
    MEASUREMENTS,
    OPERATOR,
    POINT,
    POINTS,
    ROLLING,
    SURVEYOR,
    SURVEYORS,
    TRACE,
    TRACE_1D,
    TRACE_2D,
    TRACE_3D,
    VALID_FROM,
    VALID_FROM_TO,
    VALID_TO,
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
