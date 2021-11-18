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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class GeoLine {

    private LinkedHashMap<String, String> mAttributes = new LinkedHashMap<>();

    private boolean mClosedPolygon = false;
    private String mCode = "";
    private String mLineNumber = "";
    private LinkedList<GeoPoint> mPoints = new LinkedList<>();

    public GeoLine() {
    }

    public GeoLine(LinkedList<String> section) {
        this(section.pollFirst());
        if (!section.isEmpty()) {
            section.pollFirst();
            section.pollLast();
            if (section.size() == 1) {
                section.add(GeoHelper.KEY_BEGIN);
                section.add(GeoHelper.KEY_END);
            }
            mPoints.addAll(GeoHelper.parsePointList(this, section));
        }
    }

    public GeoLine(String line) {
        line = StringUtils.removeStart(line.trim(), "Line");
        String[] segments = StringUtils.splitPreserveAllTokens(line.trim(), ",");
        mLineNumber = StringUtils.remove(segments[0], "\"");
        mClosedPolygon = StringUtils.trim(segments[1]).equalsIgnoreCase("1");
        mCode = StringUtils.remove(segments[2], "\"");
    }

    public LinkedHashMap<String, String> getAttributes() {
        return mAttributes;
    }

    public String getCode() {
        return mCode;
    }

    public String getLineNumber() {
        return mLineNumber;
    }

    public List<GeoPoint> getPoints() {
        return mPoints;
    }

    public boolean isClosedPolygon() {
        return mClosedPolygon;
    }

    public void setAttributes(LinkedHashMap<String, String> attributes) {
        mAttributes = attributes;
    }

    public void setClosedPolygon(boolean closedPolygon) {
        mClosedPolygon = closedPolygon;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public void setLineNumber(String lineNumber) {
        mLineNumber = lineNumber;
    }

    public void setPoints(LinkedList<GeoPoint> points) {
        mPoints = points;
    }

    @Override
    public String toString() {
        return toStringBuilder().toString();
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Line \"%s\",%s,\"%s\"", mLineNumber, mClosedPolygon ? "1" : "0", mCode)).append(Geo.LINE_ENDING);
        sb.append("\tbegin").append(Geo.LINE_ENDING);
        sb.append(GeoHelper.pointListToStringBuilder(mPoints, 2));
        sb.append("\tend").append(Geo.LINE_ENDING);

        return sb;
    }
}
