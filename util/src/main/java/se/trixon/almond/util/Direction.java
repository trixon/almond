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
package se.trixon.almond.util;

/**
 *
 * @author Patrik Karlström
 */
public enum Direction {
    NORTH(0, Dict.Geometry.DIRECTION_N.toString(), "N"),
    NORTH_EAST(45, Dict.Geometry.DIRECTION_NE.toString(), "NE"),
    EAST(90, Dict.Geometry.DIRECTION_E.toString(), "E"),
    SOUTH_EAST(135, Dict.Geometry.DIRECTION_SE.toString(), "SE"),
    SOUTH(180, Dict.Geometry.DIRECTION_S.toString(), "S"),
    SOUTH_WEST(225, Dict.Geometry.DIRECTION_SW.toString(), "SW"),
    WEST(270, Dict.Geometry.DIRECTION_W.toString(), "W"),
    NORTH_WEST(315, Dict.Geometry.DIRECTION_NW.toString(), "NW"),
    CENTER(-1, Dict.Geometry.DIRECTION_C.toString(), "C");
    private final int mAzimuth;
    private final String mName;
    private final String mShortName;

    public static Direction fromAzimuth(int azimuth) {
        var margin = 360d / 16d;
        for (var direction : values()) {
            if (MathHelper.isBetween(direction.getAzimuth() - margin, direction.getAzimuth() + margin, azimuth * 1.0)) {
                return direction;
            }
        }
        return CENTER;
    }

    private Direction(int azimuth, String name, String shortName) {
        mAzimuth = azimuth;
        mName = name;
        mShortName = shortName;
    }

    public int getAzimuth() {
        return mAzimuth;
    }

    public String getName() {
        return mName;
    }

    public String getShortName() {
        return mShortName;
    }
}
