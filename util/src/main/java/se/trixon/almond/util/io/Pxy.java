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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class Pxy extends CoordinateFile {

    private static final Logger LOGGER = Logger.getLogger(Pxy.class.getName());

    public static FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("*.pxy", "pxy");
    }

    private String mDate = "";
    private String mDescription = "";
    private String mIdText = "XYZ-COORD-FILE";
    private final List<PxyPoint> mPoints = new LinkedList<>();
    private String mReserved1 = "";
    private String mReserved2 = "";
    private String mVersion = "V1.00";

    public Pxy() {
        mDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        PxyPoint.setLineEnding("\r\n");
    }

    public Pxy(Charset charset) {
        this();
        mCharset = charset;
    }

    public void addPoint(PxyPoint pxyPoint) {
        if (mPoints.isEmpty()) {
            mPath2D.moveTo(pxyPoint.getY(), pxyPoint.getX());
        } else {
            mPath2D.lineTo(pxyPoint.getY(), pxyPoint.getX());
        }

        mPoints.add(pxyPoint);
    }

    public void clear() {
        mPoints.clear();
    }

    public String getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getIdText() {
        return mIdText;
    }

    public List<PxyPoint> getPoints() {
        return mPoints;
    }

    public String getReserved1() {
        return mReserved1;
    }

    public String getReserved2() {
        return mReserved2;
    }

    public String getVersion() {
        return mVersion;
    }

    @Override
    public boolean isValid(File file) {
        boolean valid = false;

        try {
            read(file);
            valid = true;
        } catch (IOException | NumberFormatException | StringIndexOutOfBoundsException ex) {
            valid = false;
            LOGGER.warning(ex.getMessage());
        } finally {
            try {
                mReader.close();
            } catch (IOException ex) {
                // nvm
            }
        }

        return valid;
    }

    @Override
    public void openWriter(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-16s,", mIdText));
        builder.append(String.format("%-5s,", mVersion));
        builder.append(String.format("%-10s,", mDate));
        builder.append(String.format("%-40s", mReserved1));
        builder.append(",").append(mLineEnding);
        builder.append(String.format("%-40s,", mDescription));
        builder.append(String.format("%-33s", mReserved2));
        builder.append(",").append(mLineEnding);

        mWriter = Files.newBufferedWriter(file.toPath(), mCharset);
        mWriter.write(builder.toString());
    }

    public void read(File file) throws IOException, NumberFormatException, StringIndexOutOfBoundsException, MalformedInputException {
        mReader = Files.newBufferedReader(file.toPath(), mCharset);

        String line = mReader.readLine();
        setIdText(line.substring(0, 16));
        setVersion(line.substring(17, 22));
        setDate(line.substring(23, 33));
        setReserved1(line.substring(34, 73));

        line = mReader.readLine();
        setDescription(line.substring(0, 38));
        setReserved2(line.substring(41, 73));

        while ((line = mReader.readLine()) != null) {
            PxyPoint pxyPoint = new PxyPoint(line);

            if (mPoints.isEmpty()) {
                mPath2D.moveTo(pxyPoint.getY(), pxyPoint.getX());
            } else {
                mPath2D.lineTo(pxyPoint.getY(), pxyPoint.getX());
            }

            mPoints.add(pxyPoint);
        }

        mReader.close();
    }

    public void setDate(String date) {
        date = StringUtils.truncate(date, 10);
        if (!date.isEmpty()) {
            mDate = date;
        }
    }

    public void setDescription(String description) {
        mDescription = StringUtils.truncate(description, 39);
    }

    public void setIdText(String idText) {
        mIdText = StringUtils.truncate(idText, 16);
    }

    @Override
    public void setLineEnding(String lineEnding) {
        super.setLineEnding(lineEnding);
        PxyPoint.setLineEnding(lineEnding);
    }

    public void setReserved1(String reserved1) {
        mReserved1 = StringUtils.truncate(reserved1, 39);
    }

    public void setReserved2(String reserved2) {
        mReserved2 = StringUtils.truncate(reserved2, 33);
    }

    public void setVersion(String version) {
        mVersion = StringUtils.truncate(version, 5);
    }

    public void write(PxyPoint pxyPoint) throws IOException {
        mWriter.write(pxyPoint.toString());
    }

    public void write(File file) throws IOException {
        openWriter(file);

        for (PxyPoint pxyPoint : mPoints) {
            mWriter.write(pxyPoint.toString());
        }

        closeWriter();
    }
}
