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

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Patrik Karlström
 */
public class Geo extends CoordinateFile {

    public static final String LINE_ENDING = "\r\n";

    public static final String NO_NEXT_SECTION = "DONOTBREAKONTHISSTOPSTRING";
    private LinkedHashMap<String, String> mAttributes = new LinkedHashMap<>();
    private GeoHeader mHeader;
    private LinkedList<GeoLine> mLines = new LinkedList<>();
    private LinkedList<GeoPoint> mPoints = new LinkedList<>();
    private LinkedList<String> mRawLines;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestMain();
    }

    public Geo() {
        GeoPoint.setLineEnding("\r\n");
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
        mHeader = new GeoHeader(GeoHelper.getSection("FileHeader", "PointList", mRawLines));

        try {
            parsePointList(GeoHelper.getSection("PointList", "LineList", mRawLines));
        } catch (Exception e) {
            System.err.println("Parse PointList Failed: " + e);
        }
        try {
            parseLineList(GeoHelper.getSection("LineList", "AttributeList", mRawLines));
        } catch (Exception e) {
            System.err.println("Parse LineList Failed: " + e);
        }
        try {
            parseAttributeList(GeoHelper.getSection("AttributeList", NO_NEXT_SECTION, mRawLines));
        } catch (Exception e) {
            System.err.println("Parse AttributeList Failed: " + e);
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
        StringBuilder sb = new StringBuilder();
        sb.append(mHeader.toStringBuilder());
        sb.append(GeoHelper.pointListToStringBuilder(mPoints, 0));
        sb.append(GeoHelper.lineListToStringBuilder(mLines));
        sb.append(GeoHelper.attributeListToStringBuilder(mAttributes, 0));

        return sb.toString();
    }

    public void write(GeoPoint geoPoint) throws IOException {
        mWriter.write(geoPoint.toString());
    }

    public void write(File file) throws IOException {
        FileUtils.writeStringToFile(file, toString(), mCharset);
    }

    private void parseAttributeList(LinkedList<String> section) {
        mAttributes.putAll(GeoHelper.getAttributes(section));
    }

    private void parseLine(LinkedList<String> section) {
        mLines.add(new GeoLine(section));
    }

    private void parseLineList(LinkedList<String> section) {
        GeoHelper.stripWrapper(section);

        while (!section.isEmpty()) {
            parseLine(GeoHelper.getSection("Line", NO_NEXT_SECTION, section));
        }
    }

    private void parsePointList(LinkedList<String> section) {
        mPoints.addAll(GeoHelper.parsePointList(section));
    }
}
