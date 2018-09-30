/*
 * Copyright 2018 Patrik Karlström.
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

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Patrik Karlström
 */
public abstract class CoordinateFile {

    protected Charset mCharset = Charset.forName("Latin1");
    protected String mLineEnding = "\r\n";
    protected Path mPath;
    protected final Path2D.Double mPath2D = new Path2D.Double();
    protected BufferedReader mReader;
    protected int mValuePrecision = 8;
    protected BufferedWriter mWriter;
    protected int mXYPrecision = 8;

    public void closeWriter() throws IOException {
        mWriter.close();
    }

    public Rectangle2D getBounds2D() {
        return mPath2D.getBounds2D();
    }

    public Charset getCharset() {
        return mCharset;
    }

    public File getFile() {
        return mPath.toFile();
    }

    public String getLineEnding() {
        return mLineEnding;
    }

    public Path getPath() {
        return mPath;
    }

    public Path2D.Double getPath2D() {
        return mPath2D;
    }

    public int getValuePrecision() {
        return mValuePrecision;
    }

    public int getXYPrecision() {
        return mXYPrecision;
    }

    public abstract boolean isValid(File file);

    public void openWriter(File file) throws IOException {
        mWriter = Files.newBufferedWriter(file.toPath(), mCharset);
    }

    public void setCharset(Charset charset) {
        mCharset = charset;
    }

    public void setLineEnding(String lineEnding) {
        mLineEnding = lineEnding;
    }

    public void setValuePrecision(int valuePrecision) {
        mValuePrecision = valuePrecision;
    }

    public void setXYPrecision(int XYPrecision) {
        mXYPrecision = XYPrecision;
    }
}
