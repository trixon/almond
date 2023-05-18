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
public class CircularInt {

    private int mMax;
    private int mMin;
    private int mValue;

    public CircularInt(int min, int max) {
        mMax = max;
        mMin = min;
        mValue = 0;
    }

    public CircularInt(int min, int max, int value) {
        this(min, max);
        mValue = value;
    }

    public int dec() {
        mValue = peekPrev();
        return mValue;
    }

    public int get() {
        return mValue;
    }

    public int inc() {
        mValue = peekNext();
        return mValue;
    }

    public int peekNext() {
        int peek = mValue + 1;
        if (peek > mMax) {
            peek = 0;
        }
        return peek;
    }

    public int peekPrev() {
        int peek = mValue - 1;
        if (peek < mMin) {
            peek = mMax;
        }
        return peek;
    }

    public void set(int aValue) {
        mValue = Math.min(mMax, Math.max(mMin, aValue));
    }
}
