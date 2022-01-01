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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
 * @author Patrik Karlström
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

    public static BufferedImage colorize(BufferedImage bufferedImage, java.awt.Color color) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage colorizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = colorizedImage.createGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();

        return colorizedImage;
    }

    public static Image colorize(Image image, java.awt.Color color) {
        return colorize(toBufferedImage(image), color);
    }

    public static BufferedImage componentToImage(Component component, Rectangle region) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(component.getForeground());
        graphics.setFont(component.getFont());
        component.paintAll(graphics);

        if (region == null) {
            region = new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        }

        return bufferedImage.getSubimage(region.x, region.y, region.width, region.height);
    }

    public static BufferedImage createBorderedImage(BufferedImage bi, Color c, int borderSize) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        int borderedImageWidth = width + borderSize * 2;
        int borderedImageHeight = height + borderSize * 2;

        BufferedImage borderedImage = new BufferedImage(borderedImageWidth, borderedImageHeight, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g2 = borderedImage.createGraphics();
        g2.setColor(c);
        g2.fillRect(0, 0, borderedImageWidth, borderedImageHeight);
        g2.drawImage(bi, borderSize, borderSize, width + borderSize, height + borderSize, 0, 0, width, height, c, null);

        return borderedImage;
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

    public static int getBrightness(java.awt.Color c) {
        return (int) Math.sqrt(
                c.getRed() * c.getRed() * .241
                + c.getGreen() * c.getGreen() * .691
                + c.getBlue() * c.getBlue() * .068);
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

    public static BufferedImage rotate(BufferedImage bufferedImage, int orientation) {
        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();

        Graphics2D g2 = bufferedImage.createGraphics();
        Map<Integer, Integer> rotMap = new HashMap<>();
        rotMap.put(1, 0);
        rotMap.put(3, 180);
        rotMap.put(6, 90);
        rotMap.put(8, 270);

        double rotation = Math.toRadians(rotMap.getOrDefault(orientation, 0));
        double x = w / 2d;
        double y = h / 2d;

        AffineTransform affineTransform = AffineTransform.getRotateInstance(rotation, x, y);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);

        if (orientation == 3) {
            g2.drawImage(affineTransformOp.filter(bufferedImage, null), 0, 0, null);
        } else if (orientation == 6 || orientation == 8) {
            BufferedImage rotatedImage = new BufferedImage(w, w, bufferedImage.getType());
            g2 = rotatedImage.createGraphics();
            affineTransform = AffineTransform.getRotateInstance(rotation, x, x);
            if (orientation == 6) {
                affineTransform.translate(0d, (double) w - h);
            }

            g2.drawImage(bufferedImage, affineTransform, null);
            BufferedImage rotatedCropImage = new BufferedImage(h, w, bufferedImage.getType());
            g2 = rotatedCropImage.createGraphics();
            g2.drawImage(rotatedImage, null, null);

            bufferedImage = rotatedCropImage;
        }

        return bufferedImage;
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

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }
}
