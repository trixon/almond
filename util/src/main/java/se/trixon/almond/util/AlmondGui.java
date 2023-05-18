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

import org.apache.commons.lang3.SystemUtils;

/**
 *
 * @author Patrik Karlström
 */
public abstract class AlmondGui {

    public static final int ICON_SIZE_LARGE = 32;
    public static final int ICON_SIZE_NORMAL = 24;
    public static final int ICON_SIZE_SMALL = 16;
    protected static final int DEFAULT_FRAME_HEIGHT = 700;
    protected static final int DEFAULT_FRAME_WIDTH = 900;
    protected static final boolean IS_MAC = SystemUtils.IS_OS_MAC;

    public AlmondGui() {
    }

}
