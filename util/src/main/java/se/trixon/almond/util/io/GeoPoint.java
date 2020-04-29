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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import se.trixon.almond.util.MathHelper;

/**
 *
 * @author Patrik Karlström
 */
public class GeoPoint extends CoordinatePoint {

    private static String sLineEnding = "\r\n";
    private List<GeoAttribute> mAttributes = new LinkedList<>();
    private String mPointCode = "";
    private String mPointId = "";
    private String mRemark = "";
    private String mSpecialCode = "";

    public static void setLineEnding(String lineEnding) {
        GeoPoint.sLineEnding = lineEnding;
    }

    public GeoPoint() {
    }

    public GeoPoint(LinkedList<String> section) {
        this(section.pollFirst());
        if (!section.isEmpty()) {
            //TODO Parse attributes
            //System.out.println(String.join("\n", section));
        }
    }

    public GeoPoint(String line) throws NumberFormatException {
        line = StringUtils.removeStart(line.trim(), "Point");
        String[] aa = getFirstItem(line);
        setPointId(aa[0]);

        String[] parts = StringUtils.splitPreserveAllTokens(aa[1], ",");
        mX = MathHelper.convertStringToDouble(parts[0]);
        mY = MathHelper.convertStringToDouble(parts[1]);
        mZ = MathHelper.convertStringToDouble(parts[2]);

        aa[1] = StringUtils.substring(aa[1], StringUtils.ordinalIndexOf(aa[1], ",", 3) + 1);
        aa = getFirstItem(aa[1]);
        setPointCode(aa[0]);
        aa = getFirstItem(aa[1]);
        setSpecialCode(aa[0]);
        aa = getFirstItem(aa[1]);
        setRemark(aa[0]);
    }

    public GeoPoint(String pointId, Double x, Double y, Double z, String pointCode) {
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

    public void setAttributes(List<GeoAttribute> attributes) {
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
//        Point "5647",60039.3739,3670.4064,0,"141",,
        String line = String.format(Locale.ENGLISH, "Point \"%s\",%f,%f,%f,\"%s\",\"%s\",\"%s\"%s",
                getPointId(),
                getX(),
                getY(),
                getZ(),
                getPointCode(),
                getSpecialCode(),
                getRemark(),
                sLineEnding);

        return line;
    }

    private String[] getFirstItem(String input) {
        String segment;
        String remaining;

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
