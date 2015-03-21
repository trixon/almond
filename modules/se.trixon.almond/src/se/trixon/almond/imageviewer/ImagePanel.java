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
package se.trixon.almond.imageviewer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author Patrik Karlsson
 */
public class ImagePanel extends JPanel {

    private boolean isTransparentAdd = true;
    private float mAlignmentX = 0.5f;
    private float mAlignmentY = 0.5f;
    private Image mImage;
    private Paint mPaint;
    private Style mStyle = Style.SCALED;

    public ImagePanel() {
    }

    /*
     *  Set image as the background with the SCALED style
     */
    public ImagePanel(Image image) {
        this(image, Style.SCALED);
    }

    /*
     *  Set image as the background with the specified style
     */
    public ImagePanel(Image image, Style style) {
        setImage(image);
        setStyle(style);
        setLayout(new BorderLayout());
    }

    /*
     *  Set image as the backround with the specified style and alignment
     */
    public ImagePanel(Image image, Style style, float alignmentX, float alignmentY) {
        setImage(image);
        setStyle(style);
        setImageAlignmentX(alignmentX);
        setImageAlignmentY(alignmentY);
        setLayout(new BorderLayout());
    }

    /*
     *  Use the Paint interface to paint a background
     */
    public ImagePanel(Paint painter) {
        setPaint(painter);
        setLayout(new BorderLayout());
    }

    /*
     *  Override method so we can make the component transparent
     */
    public void add(JComponent component) {
        add(component, null);
    }

    /*
     *  Override method so we can make the component transparent
     */
    public void add(JComponent component, Object constraints) {
        if (isTransparentAdd) {
            makeComponentTransparent(component);
        }
        
        super.add(component, constraints);
    }

    /*
     *  Override to provide a preferred size equal to the image size
    */
    @Override
    public Dimension getPreferredSize() {
        if (mImage == null) {
            return super.getPreferredSize();
        } else {
            return new Dimension(mImage.getWidth(null), mImage.getHeight(null));
        }
    }

    /*
     *	Set the image used as the background
     */
    public void setImage(Image image) {
        this.mImage = image;
        repaint();
    }

    /*
     *  Specify the horizontal alignment of the image when using ACTUAL style
     */
    public void setImageAlignmentX(float alignmentX) {
        this.mAlignmentX = alignmentX > 1.0f ? 1.0f : alignmentX < 0.0f ? 0.0f : alignmentX;
        repaint();
    }

    /*
     *  Specify the horizontal alignment of the image when using ACTUAL style
     */
    public void setImageAlignmentY(float alignmentY) {
        this.mAlignmentY = alignmentY > 1.0f ? 1.0f : alignmentY < 0.0f ? 0.0f : alignmentY;
        repaint();
    }

    /*
     *	Set the Paint object used to paint the background
     */
    public void setPaint(Paint painter) {
        this.mPaint = painter;
        repaint();
    }

    /*
     *	Set the style used to paint the background image
     */
    public void setStyle(Style style) {
        this.mStyle = style;
        repaint();
    }

    /*
     *  Controls whether components added to this panel should automatically
     *  be made transparent. That is, setOpaque(false) will be invoked.
     *  The default is set to true.
     */
    public void setTransparentAdd(boolean isTransparentAdd) {
        this.isTransparentAdd = isTransparentAdd;
    }

    /*
     *  Custom painting code for drawing the ACTUAL image as the background.
     *  The image is positioned in the panel based on the horizontal and
     *  vertical alignments specified.
     */
    private void drawActual(Graphics g) {
        Dimension d = getSize();
        Insets insets = getInsets();
        int width = d.width - insets.left - insets.right;
        int height = d.height - insets.top - insets.left;
        float x = (width - mImage.getWidth(null)) * mAlignmentX;
        float y = (height - mImage.getHeight(null)) * mAlignmentY;
        g.drawImage(mImage, (int) x + insets.left, (int) y + insets.top, this);
    }

    /*
     *  Custom painting code for drawing a SCALED image as the background
     */
    private void drawScaled(Graphics g) {
        Dimension d = getSize();
        g.drawImage(mImage, 0, 0, d.width, d.height, null);
    }

    /*
     *  Custom painting code for drawing TILED images as the background
     */
    private void drawTiled(Graphics g) {
        Dimension d = getSize();
        int width = mImage.getWidth(null);
        int height = mImage.getHeight(null);
        
        for (int x = 0; x < d.width; x += width) {
            for (int y = 0; y < d.height; y += height) {
                g.drawImage(mImage, x, y, null, null);
            }
        }
    }

    /*
     *	Try to make the component transparent.
     *  For components that use renderers, like JTable, you will also need to
     *  change the renderer to be transparent. An easy way to do this it to
     *  set the background of the table to a Color using an alpha value of 0.
     */
    private void makeComponentTransparent(JComponent component) {
        component.setOpaque(false);

        if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) component;
            JViewport viewport = scrollPane.getViewport();
            viewport.setOpaque(false);
            Component c = viewport.getView();

            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(false);
            }
        }
    }

    /*
    *  Add custom painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  Invoke the painter for the background
        if (mPaint != null) {
            Dimension d = getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(mPaint);
            g2.fill(new Rectangle(0, 0, d.width, d.height));
        }

        //  Draw the image
        if (mImage == null) {
            return;
        }

        switch (mStyle) {
            case SCALED:
                drawScaled(g);
                break;

            case TILED:
                drawTiled(g);
                break;

            case ACTUAL:
                drawActual(g);
                break;

            default:
                drawScaled(g);
        }
    }

    public enum Style {

        SCALED,
        TILED,
        ACTUAL;
    }
}
