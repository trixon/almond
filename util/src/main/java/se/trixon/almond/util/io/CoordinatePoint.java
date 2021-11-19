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
package se.trixon.almond.util.io;

import java.util.Locale;
import se.trixon.almond.util.MathHelper;

/**
 *
 * @author Patrik Karlström
 */
public class CoordinatePoint {

    private static int DECIMALS_X = 3;
    private static int DECIMALS_Y = 3;
    private static int DECIMALS_Z = 3;

    protected Double mX;
    protected String mXRaw;
    protected Double mY;
    protected String mYRaw;
    protected Double mZ;
    protected String mZRaw;

    public static int getDecimalsX() {
        return DECIMALS_X;
    }

    public static int getDecimalsY() {
        return DECIMALS_Y;
    }

    public static int getDecimalsZ() {
        return DECIMALS_Z;
    }

    public static void setDecimalsX(int decimals) {
        DECIMALS_X = decimals;
    }

    public static void setDecimalsY(int decimals) {
        DECIMALS_Y = decimals;
    }

    public static void setDecimalsZ(int decimals) {
        DECIMALS_Z = decimals;
    }

    public Double getX() {
        return mX;
    }

    public String getXFormatted() {
        return getXFormatted(Locale.ENGLISH);
    }

    public String getXFormatted(Locale locale) {
        return MathHelper.convertDoubleToString(locale, getX(), getDecimalsX());
    }

    public String getXRaw() {
        return mXRaw;
    }

    public Double getY() {
        return mY;
    }

    public String getYFormatted() {
        return getXFormatted(Locale.ENGLISH);
    }

    public String getYFormatted(Locale locale) {
        return MathHelper.convertDoubleToString(locale, getY(), getDecimalsY());
    }

    public String getYRaw() {
        return mYRaw;
    }

    public Double getZ() {
        return mZ;
    }

    public String getZFormatted() {
        return getZFormatted(Locale.ENGLISH);
    }

    public String getZFormatted(Locale locale) {
        return MathHelper.convertDoubleToString(locale, getZ(), getDecimalsZ());
    }

    public String getZRaw() {
        return mZRaw;
    }

    public void setX(Double value) {
        mX = value;
        mXRaw = MathHelper.convertDoubleToString(Locale.ENGLISH, value);
    }

    public void setXRaw(String raw) {
        mXRaw = raw;
        mX = MathHelper.convertStringToDouble(raw);
    }

    public void setY(Double value) {
        mY = value;
        mYRaw = MathHelper.convertDoubleToString(Locale.ENGLISH, value);
    }

    public void setYRaw(String raw) {
        mYRaw = raw;
        mY = MathHelper.convertStringToDouble(raw);
    }

    public void setZ(Double value) {
        mZ = value;
        mZRaw = MathHelper.convertDoubleToString(Locale.ENGLISH, value);
    }

    public void setZRaw(String raw) {
        mZRaw = raw;
        mZ = MathHelper.convertStringToDouble(raw);
    }
}
