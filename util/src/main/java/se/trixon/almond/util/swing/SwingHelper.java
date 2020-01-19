/*
 * Copyright 2019 Patrik Karlström.
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
package se.trixon.almond.util.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.commons.lang3.ArrayUtils;
import se.trixon.almond.util.PrefsHelper;

/**
 *
 * @author Patrik Karlström
 */
public class SwingHelper {

    private static final String FRAME_H = "Frame_Height";
    private static final String FRAME_STATE = "Frame_State";
    private static final String FRAME_W = "Frame_Width";
    private static final String FRAME_X = "Frame_X";
    private static final String FRAME_Y = "Frame_Y";

    public static void borderPainted(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                ((AbstractButton) component).setBorderPainted(enable);
            }
        }
    }

    public static void clearText(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                ((AbstractButton) component).setText(null);
            } else if (component instanceof Container) {
                clearText((Container) component);
            }
        }
    }

    public static void clearTextButtons(AbstractButton... abstractButtons) {
        for (AbstractButton abstractButton : abstractButtons) {
            abstractButton.setText(null);
        }
    }

    public static void clearToolTipText(Container container) {
        Component[] components;

        if (container instanceof JMenu) {
            components = ((JMenu) container).getMenuComponents();
        } else {
            components = container.getComponents();
        }

        for (Component component : components) {
            if (component instanceof JMenu) {
                clearToolTipText((JMenu) component);
            } else if (component instanceof AbstractButton) {
                ((AbstractButton) component).setToolTipText(null);
            } else if (component instanceof Container) {
                clearToolTipText((Container) component);
            }
        }
    }

    public static String comboBoxModelToString(ComboBoxModel model) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < model.getSize(); i++) {
            builder.append(model.getElementAt(i));
            if (i < model.getSize() - 1) {
                builder.append(";");
            }
        }

        return builder.toString();
    }

    public static void enableComponents(Container container, boolean enable, Component... excludedComponents) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (!ArrayUtils.contains(excludedComponents, component)) {
                component.setEnabled(enable);
                if (component instanceof Container) {
                    enableComponents((Container) component, enable);
                }
            }
        }
    }

    public static void frameStateRestore(JFrame frame, int defaultWidth, int defaultHeight) throws BackingStoreException {
        Class<? extends JFrame> c = frame.getClass();
        Preferences p = Preferences.userNodeForPackage(c);

        if (PrefsHelper.keyExists(p, FRAME_X) && PrefsHelper.keyExists(p, FRAME_Y) && PrefsHelper.keyExists(p, FRAME_H) && PrefsHelper.keyExists(p, FRAME_W)) {
            int x = p.getInt(FRAME_X, -1);
            int y = p.getInt(FRAME_Y, -1);
            frame.setLocation(x, y);

            int h = p.getInt(FRAME_H, -1);
            int w = p.getInt(FRAME_W, -1);
            frame.setSize(w, h);

            if (PrefsHelper.keyExists(p, FRAME_STATE)) {
                frame.setExtendedState(p.getInt(FRAME_STATE, -1));
            }
        } else {
            frame.setSize(defaultWidth, defaultHeight);
            frame.setLocationRelativeTo(null);
        }
    }

    public static void frameStateSave(JFrame frame) {
        Class<? extends JFrame> c = frame.getClass();
        Preferences p = Preferences.userNodeForPackage(c);

        p.putInt(FRAME_H, frame.getHeight());
        p.putInt(FRAME_W, frame.getWidth());
        p.putInt(FRAME_X, frame.getX());
        p.putInt(FRAME_Y, frame.getY());
        p.putInt(FRAME_STATE, frame.getExtendedState());
    }

    public static String getLookAndFeelClassName(String name) {
        String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (name.equalsIgnoreCase(info.getName())) {
                lookAndFeelClassName = info.getClassName();
                break;
            }
        }

        return lookAndFeelClassName;

    }

    public static DefaultComboBoxModel<String> getLookAndFeelComboBoxModel(boolean addSystem) {
        return new DefaultComboBoxModel<>(getLookAndFeelNames(addSystem));
    }

    public static String[] getLookAndFeelNames(boolean addSystem) {
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        int size = lookAndFeelInfos.length;
        if (addSystem) {
            size++;
        }

        String[] names = new String[size];
        for (int i = 0; i < lookAndFeelInfos.length; i++) {
            names[i] = lookAndFeelInfos[i].getName();
        }

        if (addSystem) {
            names[lookAndFeelInfos.length] = "System";
        }

        Arrays.sort(names);

        return names;
    }

    public static AbstractButton getSelectedButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button;
            }
        }

        return null;
    }

    public static double getUIScale() {
        double defaultFontSize = 12.0;
        Integer fontSize = (Integer) UIManager.get("customFontSize");

        return fontSize == null ? 1.0 : fontSize / defaultFontSize;
    }

    public static double getUIScaled(double value) {
        return value * getUIScale();
    }

    public static int getUIScaled(int value) {
        return (int) (value * getUIScale());
    }

    public static Dimension getUIScaledDim(int width, int height) {
        return new Dimension((int) (width * getUIScale()), (int) (height * getUIScale()));
    }

    public static Dimension getUIScaledDim(double width, double height) {
        return new Dimension((int) (width * getUIScale()), (int) (height * getUIScale()));
    }

    public static void makeWindowResizable(Component component) {
        component.addHierarchyListener((HierarchyEvent e) -> {
            Window window = SwingUtilities.getWindowAncestor(component);
            if (window instanceof Dialog) {
                Dialog dialog = (Dialog) window;
                if (!dialog.isResizable()) {
                    dialog.setResizable(true);
                }
            }
        });
    }

    public static void runLaterDelayed(long delay, Runnable r) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwingHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.invokeLater(r);
        }).start();
    }

    public static void setComponentsFont(Container container, Font font) {
        Component[] components = container.getComponents();
        container.setFont(font);

        for (Component component : components) {
            component.setFont(font);
            if (component instanceof JMenu) {
                setComponentsFont((Container) component, font);
            }
        }
    }
}
