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
package se.trixon.almond.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Patrik Karlström
 */
public class ImageScaler {

    private final GraphicsConfiguration mGraphicsConfiguration;
    private final GraphicsDevice mGraphicsDevice;
    private final GraphicsEnvironment mGraphicsEnvironment;

    public static ImageScaler getInstance() {
        return Holder.INSTANCE;
    }

    private ImageScaler() {
        mGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        mGraphicsDevice = mGraphicsEnvironment.getDefaultScreenDevice();
        mGraphicsConfiguration = mGraphicsDevice.getDefaultConfiguration();
    }

    public BufferedImage getScaledImage(File file, double ratio) throws IOException {
        return getScaledImage(ImageIO.read(file), ratio);
    }

    public BufferedImage getScaledImage(File file, Dimension dimension) throws IOException {
        return getScaledImage(ImageIO.read(file), dimension);
    }

    public BufferedImage getScaledImage(BufferedImage originalBufferedImage, Dimension maxDimension) {
        Scaler scaler = new Scaler(new Dimension(originalBufferedImage.getWidth(), originalBufferedImage.getHeight()));
        scaler.setHeight(maxDimension.height);
        scaler.setWidth(maxDimension.width);

        int w = scaler.getDimension().width;
        int h = scaler.getDimension().height;
        BufferedImage scaledBufferedImage = mGraphicsConfiguration.createCompatibleImage(w, h, Transparency.OPAQUE);
        Graphics2D g2d = scaledBufferedImage.createGraphics();

        double xScale = (double) w / originalBufferedImage.getWidth();
        double yScale = (double) h / originalBufferedImage.getHeight();

        AffineTransform affineTransform = AffineTransform.getScaleInstance(xScale, yScale);
        g2d.drawRenderedImage(originalBufferedImage, affineTransform);
        g2d.dispose();

        return scaledBufferedImage;
    }

    public BufferedImage getScaledImage(BufferedImage originalBufferedImage, double ratio) {
        int w = (int) (originalBufferedImage.getWidth() * ratio);
        int h = (int) (originalBufferedImage.getHeight() * ratio);

        return getScaledImage(originalBufferedImage, new Dimension(w, h));
    }

    private static class Holder {

        private static final ImageScaler INSTANCE = new ImageScaler();
    }
}
