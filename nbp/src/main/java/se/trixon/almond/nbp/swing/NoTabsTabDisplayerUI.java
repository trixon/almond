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
package se.trixon.almond.nbp.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JComponent;
import javax.swing.SingleSelectionModel;
import javax.swing.plaf.ComponentUI;
import org.netbeans.swing.tabcontrol.TabDisplayer;
import org.netbeans.swing.tabcontrol.TabDisplayerUI;

/**
 *
 * @author Patrik Karlsson
 */
public class NoTabsTabDisplayerUI extends TabDisplayerUI {

    public static final String ID = "se.trixon.almond.nbp.swing.NoTabsTabDisplayerUI";
    private static final int[] PTS = new int[]{0, 0, 0};

    public static ComponentUI createUI(JComponent jc) {
        assert jc instanceof TabDisplayer;
        return new NoTabsTabDisplayerUI((TabDisplayer) jc);
    }

    public NoTabsTabDisplayerUI(TabDisplayer displayer) {
        super(displayer);
    }

    @Override
    public int dropIndexOfPoint(Point point) {
        return -1;
    }

    public String getCommandAtPoint(Point point) {
        return null;
    }

    @Override
    public Polygon getExactTabIndication(int i) {
        //Should never be called
        return new Polygon(PTS, PTS, PTS.length);
    }

    @Override
    public Polygon getInsertTabIndication(int i) {
        return new Polygon(PTS, PTS, PTS.length);
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        return new Dimension(0, 0);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return new Dimension(0, 0);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(0, 0);
    }

    @Override
    public Rectangle getTabRect(int i, Rectangle rectangle) {
        return new Rectangle(0, 0, 0, 0);
    }

    @Override
    public void registerShortcuts(JComponent jComponent) {
        //do nothing
    }

    @Override
    public int tabForCoordinate(Point point) {
        return -1;
    }

    @Override
    public void unregisterShortcuts(JComponent jComponent) {
        //do nothing
    }

    @Override
    protected void cancelRequestAttention(int i) {
        //do nothing
    }

    @Override
    protected SingleSelectionModel createSelectionModel() {
        return new DefaultSingleSelectionModel();
    }

    @Override
    protected void requestAttention(int i) {
        //do nothing
    }
}
