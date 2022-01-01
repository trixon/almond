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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class Geo extends CoordinateFile {

    public static final String LINE_ENDING = "\r\n";

    public static final String NO_NEXT_SECTION = "DONOTBREAKONTHISSTOPSTRING";
    private LinkedHashMap<String, String> mAttributes = new LinkedHashMap<>();
    private CoordinateFormat mCoordinateFormat = CoordinateFormat.FORMATTED;
    private GeoHeader mHeader;
    private LinkedList<GeoLine> mLines = new LinkedList<>();
    private LinkedList<GeoPoint> mPoints = new LinkedList<>();
    private LinkedList<String> mRawLines;

    public Geo() {
        GeoPoint.setLineEnding(LINE_ENDING);
    }

    public Geo(GeoHeader geoHeader) {
        setHeader(geoHeader);
    }

    public void addPoint(GeoPoint geoPoint) {
        try {
            if (mPoints.isEmpty()) {
                mPath2D.moveTo(geoPoint.getY(), geoPoint.getX());
            } else {
                mPath2D.lineTo(geoPoint.getY(), geoPoint.getX());
            }
        } catch (NullPointerException e) {
            //nvm
        }

        mPoints.add(geoPoint);
    }

    public LinkedHashMap<String, String> getAttributes() {
        return mAttributes;
    }

    public CoordinateFormat getCoordinateFormat() {
        return mCoordinateFormat;
    }

    public GeoHeader getHeader() {
        return mHeader;
    }

    public List<GeoLine> getLines() {
        return mLines;
    }

    public List<GeoPoint> getPoints() {
        return mPoints;
    }

    @Override
    public boolean isValid(File file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(File file) throws IOException {
        mRawLines = new LinkedList<>(FileUtils.readLines(file, mCharset));
        var elements = StringUtils.split(mRawLines.peek(), ",");
        if (elements.length < 3 || !StringUtils.containsIgnoreCase(elements[2], "UTF-8")) {
            mCharset = StandardCharsets.ISO_8859_1;
            mRawLines = new LinkedList<>(FileUtils.readLines(file, mCharset));
        }

        var headerSection = GeoHelper.extractSection("FileHeader", "PointList", mRawLines);
        mHeader = new GeoHeader(headerSection);

        try {
            var section = GeoHelper.extractSection("PointList", "LineList", mRawLines);
            var points = GeoHelper.parsePointList(null, section);
            mPoints.addAll(points);
        } catch (Exception e) {
            throw new IOException("Parse PointList Failed: ", e);
        }

        try {
            var section = GeoHelper.extractSection("LineList", "AttributeList", mRawLines);
            GeoHelper.stripWrapper(section);

            while (!section.isEmpty()) {
                var lineSection = GeoHelper.extractSection("Line", NO_NEXT_SECTION, section);
                mLines.add(new GeoLine(lineSection));
            }
        } catch (Exception e) {
            throw new IOException("Parse LineList Failed: ", e);
        }

        try {
            var section = GeoHelper.extractSection("AttributeList", NO_NEXT_SECTION, mRawLines);
            mAttributes.putAll(GeoHelper.getAttributes(section));
        } catch (Exception e) {
            throw new IOException("Parse AttributeList Failed: ", e);
        }
    }

    public void setAttributes(LinkedHashMap<String, String> attributes) {
        mAttributes = attributes;
    }

    public void setHeader(GeoHeader header) {
        mHeader = header;
    }

    @Override
    public void setLineEnding(String lineEnding) {
        super.setLineEnding(lineEnding);
        GeoPoint.setLineEnding(lineEnding);
    }

    public void setLines(LinkedList<GeoLine> lines) {
        mLines = lines;
    }

    public void setPoints(LinkedList<GeoPoint> points) {
        mPoints = points;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(mHeader.toStringBuilder());
        sb.append(GeoHelper.pointListToStringBuilder(this, mPoints, 0));
        sb.append(GeoHelper.lineListToStringBuilder(this, mLines));
        sb.append(GeoHelper.attributeListToStringBuilder(mAttributes, 0));

        return sb.toString();
    }

    public void write(GeoPoint geoPoint) throws IOException {
        mWriter.write(geoPoint.toString());
    }

    public void write(File file) throws IOException {
        write(file, CoordinateFormat.FORMATTED);
    }

    public void write(File file, CoordinateFormat coordinateFormat) throws IOException {
        mCoordinateFormat = coordinateFormat;
        FileUtils.writeStringToFile(file, toString(), mCharset);
    }
}
