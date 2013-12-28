package se.trixon.almond;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    public static String colorToHex(Color color) {
        String rgb = Integer.toHexString(color.getRGB());

        return rgb.substring(2);
    }

    public static int colorToHexInt(Color color) {
        Integer integer = Integer.decode("0x" + colorToHex(color));

        return integer.intValue();
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
