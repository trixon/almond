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
    NORTH(0, Dict.Geometry.DIRECTION_N.toString()),
    NORTH_EAST(45, Dict.Geometry.DIRECTION_NE.toString()),
    EAST(90, Dict.Geometry.DIRECTION_E.toString()),
    SOUTH_EAST(135, Dict.Geometry.DIRECTION_SE.toString()),
    SOUTH(180, Dict.Geometry.DIRECTION_S.toString()),
    SOUTH_WEST(225, Dict.Geometry.DIRECTION_SW.toString()),
    WEST(270, Dict.Geometry.DIRECTION_W.toString()),
    NORTH_WEST(315, Dict.Geometry.DIRECTION_NW.toString()),
    CENTER(-1, Dict.Geometry.DIRECTION_C.toString());
    private final int mAzimuth;
    private final String mName;

    private Direction(int azimuth, String name) {
        mAzimuth = azimuth;
        mName = name;
    }

    public int getAzimuth() {
        return mAzimuth;
    }

    public String getName() {
        return mName;
    }
}
