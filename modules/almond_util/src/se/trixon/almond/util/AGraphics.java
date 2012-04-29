package se.trixon.almond.util;

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
public class AGraphics {

  public static String colorToHex(Color aColor) {
    String rgb = Integer.toHexString(aColor.getRGB());
    return rgb.substring(2);
  }

  public static int colorToHexInt(Color aColor) {
    Integer integer = Integer.decode("0x" + colorToHex(aColor));
    return integer.intValue();
  }

  public static String colorToString(Color aColor) {
    return "#" + colorToHex(aColor);
  }

  public static Color colorAndMask(Color aColor, int aMask) {
    int baseColor = AGraphics.colorToHexInt(aColor);
    Integer activeColorValue = baseColor & aMask;

    Color maskedColor = Color.decode("#" + Integer.toHexString(activeColorValue.intValue()));
    return maskedColor;
  }

  public static void drawCircle(int x, int y, int aRadius, Graphics2D aGraphics2D) {
    aGraphics2D.drawOval(x - aRadius, y - aRadius, aRadius * 2, aRadius * 2);
  }

  public static void drawCircleSector(int x, int y, int aRadius, int sStart, int aStop, Graphics2D aGraphics2D) {
    aGraphics2D.drawArc(x - aRadius, y - aRadius, aRadius * 2, aRadius * 2, sStart, aStop);
  }

  public static void drawTriangle(int x, int y, int aSize, Graphics2D aGraphics2D) {
    int xa[] = {x, x + aSize / 2, x - aSize / 2};
    int ya[] = {y - aSize / 2, y + aSize / 2, y + aSize / 2};
    aGraphics2D.drawPolygon(xa, ya, 3);
  }

  public static void fillCircle(int x, int y, int aRadius, Graphics2D aGraphics2D) {
    aGraphics2D.fillOval(x - aRadius, y - aRadius, aRadius * 2, aRadius * 2);
  }

  public static void fillCircleSector(int x, int y, int aRadius, int aStart, int aStop, Graphics2D aGraphics2D) {
    aGraphics2D.fillArc(x - aRadius, y - aRadius, aRadius * 2, aRadius * 2, aStart, aStop);
  }

  public static void fillTriangle(int x, int y, int aSize, Graphics2D aGraphics2D) {
    int xa[] = {x, x + aSize / 2, x - aSize / 2};
    int ya[] = {y - aSize / 2, y + aSize / 2, y + aSize / 2};
    aGraphics2D.fillPolygon(xa, ya, 3);
  }

  public static BufferedImage flipBufferedImage(BufferedImage aBufferedImage) {
    return flipBufferedImageX(flipBufferedImageY(aBufferedImage));
  }

  public static BufferedImage flipBufferedImageX(BufferedImage aBufferedImage) {
    AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
    affineTransform.translate(-aBufferedImage.getWidth(null), 0);
    AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    return affineTransformOp.filter(aBufferedImage, null);
  }

  public static BufferedImage flipBufferedImageY(BufferedImage aBufferedImage) {
    AffineTransform affineTransform = AffineTransform.getScaleInstance(1, -1);
    affineTransform.translate(-aBufferedImage.getWidth(null), 0);
    AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    return affineTransformOp.filter(aBufferedImage, null);
  }

  public static BufferedImage getBufferedImage(URL anUrl) {
    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read(anUrl);
    } catch (IOException ex) {
      Logger.getLogger(AGraphics.class.getName()).log(Level.SEVERE, null, ex);
    }
    return bufferedImage;
  }

  public static Image scaleImage(Image anOriginalImage, Dimension aDimension) {
    Image newImage;
    int height = anOriginalImage.getHeight(null);
    int width = anOriginalImage.getWidth(null);

    if (width > height) {
      newImage = anOriginalImage.getScaledInstance((int) aDimension.getWidth(), (int) (aDimension.getWidth() * height) / width, Image.SCALE_SMOOTH);
    } else {
      newImage = anOriginalImage.getScaledInstance((int) (aDimension.getWidth() * width) / height, (int) aDimension.getWidth(), Image.SCALE_SMOOTH);
    }

    return newImage;
  }

  public static void setImageIcon(AbstractButton anAbstractButton, URL anUrl) {
    if (anUrl != null) {
      anAbstractButton.setIcon(new ImageIcon(anUrl));
    } else {
      anAbstractButton.setIcon(null);
    }
  }

  public static void setImageIcon(JLabel jLabel, URL anUrl) {
    if (anUrl != null) {
      jLabel.setIcon(new ImageIcon(anUrl));
    } else {
      jLabel.setIcon(null);
    }
  }
}
