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
package se.trixon.almond.util.io;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class PxyPoint extends CoordinatePoint {

    private static final int MAX_DECIMALS = 4;
    private static String sLineEnding;

    public static void setLineEnding(String lineEnding) {
        PxyPoint.sLineEnding = lineEnding;
    }
    private String mPointCode = "";
    private String mPointId = "";
    private String mRemark = "";
    private String mSpecialCode = "";
    private int mXYPrecision = 3;
    private int mZPrecision = 3;

    public PxyPoint() {
    }

    public PxyPoint(String line) throws NumberFormatException {
        setPointId(line.substring(0, 12));
        mX = Double.parseDouble(line.substring(14, 26));
        mY = Double.parseDouble(line.substring(26, 38));
        mZ = Double.parseDouble(line.substring(38, 50));
        setPointCode(line.substring(51, 59));
        setSpecialCode(line.substring(60, 62));
        setRemark(line.substring(62, 74));
    }

    public PxyPoint(String pointId, double x, double y, double z, String pointCode) {
        setPointId(pointId);
        mX = x;
        mY = y;
        mZ = z;
        setPointCode(pointCode);
    }

    public String getPointCode() {
        return mPointCode;
    }

    public String getPointId() {
        return mPointId;
    }

    public String getRemark() {
        return mRemark;
    }

    public String getSpecialCode() {
        return mSpecialCode;
    }

    public int getXYPrecision() {
        return mXYPrecision;
    }

    public int getZPrecision() {
        return mZPrecision;
    }

    public final void setPointCode(String pointCode) {
        mPointCode = StringUtils.truncate(pointCode, 8).trim();
    }

    public final void setPointId(String pointId) {
        mPointId = StringUtils.truncate(pointId, 12).trim();

    }

    public final void setRemark(String remark) {
        mRemark = StringUtils.truncate(remark, 12).trim();
    }

    public final void setSpecialCode(String specialCode) {
        mSpecialCode = StringUtils.truncate(specialCode, 2).trim();
    }

    public void setXYPrecision(int xyPrecision) {
        mXYPrecision = Math.min(xyPrecision, MAX_DECIMALS);
    }

    public void setZPrecision(int zPrecision) {
        mZPrecision = Math.min(zPrecision, MAX_DECIMALS);
    }

    @Override
    public String toString() {
        int xyInt = mXYPrecision == 0 ? 1 : 0;
        int zInt = mZPrecision == 0 ? 1 : 0;

        int paddingXY = MAX_DECIMALS - mXYPrecision;
        if (paddingXY == MAX_DECIMALS) {
            paddingXY++;
        }
        String xyPadding = new String(new char[paddingXY]).replace("\\0", " ");

        int paddingZ = MAX_DECIMALS - mZPrecision;
        if (paddingZ == MAX_DECIMALS) {
            paddingZ++;
        }
        String zPadding = new String(new char[paddingZ]).replace("\\0", " ");

        String xyFormat = String.format(Locale.ENGLISH, "%%%d.%df", 12 - MAX_DECIMALS + mXYPrecision - xyInt, mXYPrecision);
        String zFormat = String.format(Locale.ENGLISH, "%%%d.%df", 12 - MAX_DECIMALS + mZPrecision - zInt, mZPrecision);

        StringBuilder builder = new StringBuilder();
        builder.append("%-12s".formatted(mPointId));
        builder.append("  ");
        builder.append(String.format(Locale.ENGLISH, xyFormat, mX)).append(xyPadding);
        builder.append(String.format(Locale.ENGLISH, xyFormat, mY)).append(xyPadding);
        builder.append(String.format(Locale.ENGLISH, zFormat, mZ)).append(zPadding);
        builder.append(" ");
        builder.append("%-8s".formatted(mPointCode));
        builder.append(" ");
        builder.append("%-2s".formatted(mSpecialCode));
        builder.append("%-12s".formatted(mRemark));
        builder.append(",").append(sLineEnding);

        return builder.toString();
    }
}
