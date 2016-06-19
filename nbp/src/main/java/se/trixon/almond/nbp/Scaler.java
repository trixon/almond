/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.almond.nbp;

import java.awt.Dimension;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Scaler {

    private Dimension mDimension;

    public Scaler(Dimension dimension) {
        mDimension = dimension;
    }

    public Dimension getDimension() {
        return mDimension;
    }

    public void setDimension(Dimension dimension) {
        mDimension = dimension;
    }

    public Dimension setHeight(int height) {
        if (mDimension.height <= height) {
            return mDimension;
        }

        double factor = (double) mDimension.height / height;
        int scaledWidth = (int) (mDimension.width / factor);

        mDimension.setSize(scaledWidth, height);

        return mDimension;
    }

    public Dimension setWidth(int width) {
        if (mDimension.width <= width) {
            return mDimension;
        }

        double factor = (double) mDimension.width / width;
        int scaledHeight = (int) (mDimension.height / factor);

        mDimension.setSize(width, scaledHeight);

        return mDimension;
    }
}
