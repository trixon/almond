/* 
 * Copyright 2018 Patrik Karlsson.
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
package se.trixon.almond.nbp.imageviewer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import se.trixon.almond.util.Scaler;

/**
 *
 * @author Patrik Karlsson
 */
public class ImagePanel extends JPanel {

    private Image mImage;
    private Dimension mImageDimension = new Dimension();
    private boolean mIsTransparentAdd = true;
    private Paint mPaint;

    public ImagePanel() {
        super();
    }

    public ImagePanel(Image image) {
        super();
        setImage(image);
        setLayout(new BorderLayout());
    }

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
        if (mIsTransparentAdd) {
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

    public void setImage(Image image) {
        mImage = image;

        if (image == null) {
            mImageDimension = new Dimension(0, 0);
        } else {
            mImageDimension = new Dimension(image.getWidth(null), image.getHeight(null));
        }

        repaint();
    }

    public void setPaint(Paint painter) {
        mPaint = painter;
        repaint();
    }

    /*
     *  Controls whether components added to this panel should automatically
     *  be made transparent. That is, setOpaque(false) will be invoked.
     *  The default is set to true.
     */
    public void setTransparentAdd(boolean isTransparentAdd) {
        mIsTransparentAdd = isTransparentAdd;
    }

    /*
     *  Custom painting code for drawing the ACTUAL image as the background.
     *  The image is positioned in the panel based on the horizontal and
     *  vertical alignments specified.
     */
    private void drawImage(Graphics g) {
        Dimension panelDimension = getSize();

        Scaler scaler = new Scaler(new Dimension(mImageDimension));
        scaler.setHeight(panelDimension.height);
        scaler.setWidth(panelDimension.width);
        Dimension scaledImage = scaler.getDimension();

        int x = (panelDimension.width - scaledImage.width) / 2;
        int y = (panelDimension.height - scaledImage.height) / 2;

        g.drawImage(mImage, x, y, scaledImage.width, scaledImage.height, this);
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

        drawImage(g);
    }
}
