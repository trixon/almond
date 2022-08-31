/*
 * Copyright 2022 Patrik Karlström.
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

import java.awt.Dimension;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import javafx.geometry.Point2D;

/**
 *
 * @author Patrik Karlström
 */
public class MathHelper {

    /**
     * Calculates circle center from two points and a radius. A negative radius
     * returns the other center.
     *
     * @param p1
     * @param p2
     * @param r
     * @return
     */
    public static Point2D calculateCircleCenter(Point2D p1, Point2D p2, double r) {
        var x1 = p1.getX();
        var y1 = p1.getY();
        var x2 = p2.getX();
        var y2 = p2.getY();

        var d = p1.distance(p2);
        var x3 = (x1 + x2) / 2;
        var y3 = (y1 + y2) / 2;
        var q = Math.signum(r) * Math.sqrt(r * r - Math.pow((d / 2), 2)) / d;

//        var x = x3 - Math.signum(r) * Math.sqrt(r * r - Math.pow((d / 2), 2)) * (y1 - y2) / d;
//        var y = y3 - Math.signum(r) * Math.sqrt(r * r - Math.pow((d / 2), 2)) * (x2 - x1) / d;
        var x = x3 - q * (y1 - y2);
        var y = y3 - q * (x2 - x1);

        return new Point2D(x, y);
    }

    public static double convertDoubleToDouble(Double d) {
        return d == null ? 0 : d;
    }

    public static String convertDoubleToString(Locale locale, Double d, int decimals) {
        if (d == null) {
            return "";
        } else {
            String format = "%%.%df".formatted(decimals);

            return String.format(locale, format, d);
        }
    }

    public static String convertDoubleToString(Locale locale, Double d) {
        if (d == null) {
            return "";
        } else {
            return String.format(locale, "%f", d);
        }
    }

    public static String convertDoubleToString(Double d, int decimals) {
        return convertDoubleToString(Locale.getDefault(), d, decimals);
    }

    public static String convertDoubleToString(Double d) {
        return convertDoubleToString(Locale.getDefault(), d);
    }

    public static Integer convertIntegerToInteger(Integer i) {
        return i == null ? 0 : i;
    }

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

    public static Point indexToPoint(int index, Dimension d) {
        return new Point(index % d.width, index / d.width);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(pointToIndex(new Point(3, 1), new Dimension(5, 3)));
        System.out.println(indexToPoint(14, new Dimension(5, 3)));
    }

    public static int pointToIndex(Point p, Dimension d) {
        return p.y * d.width + p.x;
    }

    public static int round(double value) {
        return (int) Math.round(value);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
