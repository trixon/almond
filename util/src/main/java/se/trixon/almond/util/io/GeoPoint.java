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
package se.trixon.almond.util.io;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GeoPoint extends CoordinatePoint {

    private static String sLineEnding = "\r\n";
    private LinkedHashMap<String, String> mAttributes;
    private String mPointCode = "";
    private String mPointId = "";
    private String mRemark = "";
    private String mSpecialCode = "";
    private transient Geo mGeo;

    public static void setLineEnding(String lineEnding) {
        GeoPoint.sLineEnding = lineEnding;
    }

    public GeoPoint() {
    }

    public GeoPoint(LinkedList<String> section) {
        this(section.pollFirst());
        if (!section.isEmpty()) {
            section.pollFirst();
            section.pollLast();
            if (!section.isEmpty()) {
                mAttributes = new LinkedHashMap<>();
                mAttributes.putAll(GeoHelper.getAttributes(section));
            }
            //TODO Replicate empty lines on writing
        }
    }

    public GeoPoint(String row) throws NumberFormatException {
        row = StringUtils.removeStart(row.trim(), "Point");
        var elements = getFirstItem(row);
        setPointId(elements[0]);
        var parts = StringUtils.splitPreserveAllTokens(elements[1], ",");
        setXRaw(parts[0]);
        setYRaw(parts[1]);
        setZRaw(parts[2]);
        elements[1] = StringUtils.substring(elements[1], StringUtils.ordinalIndexOf(elements[1], ",", 3) + 1);
        elements = getFirstItem(elements[1]);
        setPointCode(elements[0]);
        elements = getFirstItem(elements[1]);
        setSpecialCode(elements[0]);
        elements = getFirstItem(elements[1]);
        setRemark(elements[0]);
    }

    public GeoPoint(String pointId, Double x, Double y, Double z, String pointCode) {
        setPointId(pointId);
        setX(x);
        setY(y);
        setZ(z);
        setPointCode(pointCode);
    }

    public LinkedHashMap<String, String> getAttributes() {
        return mAttributes;
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

    public void setAttributes(LinkedHashMap<String, String> attributes) {
        mAttributes = attributes;
    }

    public void setPointCode(String pointCode) {
        mPointCode = pointCode;
    }

    public void setPointId(String pointId) {
        mPointId = pointId;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public void setSpecialCode(String specialCode) {
        mSpecialCode = specialCode;
    }

    @Override
    public String toString() {
        String row = String.format("Point %s,%s,%s,%s,%s,%s,%s%s",
                GeoHelper.toQuotedString(getPointId()),
                getX(mGeo.getCoordinateFormat()),
                getY(mGeo.getCoordinateFormat()),
                getZ(mGeo.getCoordinateFormat()),
                GeoHelper.toQuotedString(getPointCode()),
                GeoHelper.toQuotedString(getSpecialCode()),
                GeoHelper.toQuotedString(getRemark()),
                sLineEnding);

        return row;
    }

    void setGeo(Geo geo) {
        mGeo = geo;
    }

    private String[] getFirstItem(String input) {
        String segment;
        String remaining;
        input = input.strip();
        if (StringUtils.startsWith(input, ",")) {
            segment = "";
            remaining = StringUtils.removeStart(input, ",");
        } else {
            int begin = StringUtils.ordinalIndexOf(input, "\"", 1) + 1;
            int end = StringUtils.ordinalIndexOf(input, "\"", 2);
            segment = StringUtils.substring(input, begin, end);
            remaining = StringUtils.substring(input, end + 2);
        }

        return new String[]{segment, remaining};
    }
}
