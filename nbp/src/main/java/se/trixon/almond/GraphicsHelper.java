/* 
 * Copyright 2015 Patrik Karlsson.
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
package se.trixon.almond;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GraphicsHelper {

    public static Color colorAndMask(Color color, int mask) {
        int baseColor = GraphicsHelper.colorToHexInt(color);
        Integer activeColorValue = baseColor & mask;

        Color maskedColor = Color.decode("#" + Integer.toHexString(activeColorValue.intValue()));

        return maskedColor;
    }

    public static String colorToAABBGGRR(Color color, String... prefixSuffix) {
        String rr = StringUtils.leftPad(Integer.toHexString(color.getRed()), 2, "0");
        String gg = StringUtils.leftPad(Integer.toHexString(color.getGreen()), 2, "0");
        String bb = StringUtils.leftPad(Integer.toHexString(color.getBlue()), 2, "0");
        String aa = StringUtils.leftPad(Integer.toHexString(color.getAlpha()), 2, "0");
        StringBuilder builder = new StringBuilder();

        if (prefixSuffix.length > 0) {
            builder.append(prefixSuffix[0]);
        }

        builder.append(aa).append(bb).append(gg).append(rr);

        if (prefixSuffix.length > 1) {
            builder.append(prefixSuffix[1]);
        }

        return builder.toString();
    }

    public static String colorToHex(Color color) {
        String rgb = Integer.toHexString(color.getRGB());

        return rgb.substring(2);
    }

    public static int colorToHexInt(Color color) {
        return Integer.decode("0x" + colorToHex(color));
    }

    public static String colorToString(Color color) {
        return "#" + colorToHex(color);
    }

    public static void drawCircle(int x, int y, int radius, Graphics2D graphics2D) {
        graphics2D.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void drawCircleSector(int x, int y, int radius, int start, int stop, Graphics2D graphics2D) {
        graphics2D.drawArc(x - radius, y - radius, radius * 2, radius * 2, start, stop);
    }

    public static void drawTriangle(int x, int y, int size, Graphics2D graphics2D) {
        int xa[] = {x, x + size / 2, x - size / 2};
        int ya[] = {y - size / 2, y + size / 2, y + size / 2};
        graphics2D.drawPolygon(xa, ya, 3);
    }

    public static void fillCircle(int x, int y, int radius, Graphics2D graphics2D) {
        graphics2D.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void fillCircleSector(int x, int y, int radius, int start, int stop, Graphics2D graphics2D) {
        graphics2D.fillArc(x - radius, y - radius, radius * 2, radius * 2, start, stop);
    }

    public static void fillTriangle(int x, int y, int size, Graphics2D graphics2D) {
        int xa[] = {x, x + size / 2, x - size / 2};
        int ya[] = {y - size / 2, y + size / 2, y + size / 2};
        graphics2D.fillPolygon(xa, ya, 3);
    }

    public static BufferedImage flipBufferedImage(BufferedImage bufferedImage) {
        return flipBufferedImageX(flipBufferedImageY(bufferedImage));
    }

    public static BufferedImage flipBufferedImageX(BufferedImage bufferedImage) {
        AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-bufferedImage.getWidth(null), 0);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return affineTransformOp.filter(bufferedImage, null);
    }

    public static BufferedImage flipBufferedImageY(BufferedImage bufferedImage) {
        AffineTransform affineTransform = AffineTransform.getScaleInstance(1, -1);
        affineTransform.translate(-bufferedImage.getWidth(null), 0);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return affineTransformOp.filter(bufferedImage, null);
    }

    public static BufferedImage getBufferedImage(URL url) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(GraphicsHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bufferedImage;
    }

    public static Dimension getImgageDimension(File imageFile) throws IOException {
        ImageInputStream inputStream = ImageIO.createImageInputStream(imageFile);

        try {
            final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(inputStream);
            if (imageReaders.hasNext()) {
                ImageReader imageReader = imageReaders.next();
                try {
                    imageReader.setInput(inputStream);
                    return new Dimension(imageReader.getWidth(0), imageReader.getHeight(0));
                } finally {
                    imageReader.dispose();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return null;
    }

    public static Image scaleImage(Image image, Dimension dimension) {
        Image newImage;
        int height = image.getHeight(null);
        int width = image.getWidth(null);

        if (width > height) {
            newImage = image.getScaledInstance((int) dimension.getWidth(), (int) (dimension.getWidth() * height) / width, Image.SCALE_SMOOTH);
        } else {
            newImage = image.getScaledInstance((int) (dimension.getWidth() * width) / height, (int) dimension.getWidth(), Image.SCALE_SMOOTH);
        }

        return newImage;
    }

    public static void setImageIcon(AbstractButton abstractButton, URL url) {
        if (url != null) {
            abstractButton.setIcon(new ImageIcon(url));
        } else {
            abstractButton.setIcon(null);
        }
    }

    public static void setImageIcon(JLabel label, URL url) {
        if (url != null) {
            label.setIcon(new ImageIcon(url));
        } else {
            label.setIcon(null);
        }
    }
}
