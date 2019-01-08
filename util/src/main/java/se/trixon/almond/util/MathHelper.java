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
package se.trixon.almond.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Patrik Karlström
 */
public class MathHelper {

    public static String convertIntegerToString(Integer i) {
        return i == null ? null : i.toString();
    }

    public static String convertLongToString(Long l, boolean suppressNull) {
        return l == null ? suppressNull ? "" : null : l.toString();
    }

    public static Double convertStringToDouble(String s) {
        return s == null || s.trim().isEmpty() ? null : Double.valueOf(s.trim());
    }

    public static Integer convertStringToInteger(String s) {
        return s == null || s.trim().isEmpty() ? null : Integer.valueOf(s.trim());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    public static int round(double value) {
        return (int) Math.round(value);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
