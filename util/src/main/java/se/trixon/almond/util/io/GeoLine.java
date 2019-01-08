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

/**
 *
 * @author Patrik Karlström
 */
public class GeoLine {

    private List<GeoAttribute> mAttributes = new LinkedList<>();
    private boolean mClosedPolygon = false;
    private String mCode = "";
    private String mLineNumber = "";
    private List<GeoPoint> mPoints = new LinkedList<>();

    public GeoLine() {
    }

    public List<GeoAttribute> getAttributes() {
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

    public void setAttributes(List<GeoAttribute> attributes) {
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

    public void setPoints(List<GeoPoint> points) {
        mPoints = points;
    }
}
