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
package se.trixon.almond.util.swing;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.InputEvent;
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
import se.trixon.almond.util.SystemHelper;

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
        for (var component : container.getComponents()) {
            if (component instanceof AbstractButton abstractButton) {
                abstractButton.setBorderPainted(enable);
            }
        }
    }

    public static void clearText(Container container) {
        for (var component : container.getComponents()) {
            if (component instanceof AbstractButton abstractButton) {
                abstractButton.setText(null);
            } else if (component instanceof Container container2) {
                clearText(container2);
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

        if (container instanceof JMenu menu) {
            components = menu.getMenuComponents();
        } else {
            components = container.getComponents();
        }

        for (var component : components) {
            if (component instanceof JMenu menu) {
                clearToolTipText(menu);
            } else if (component instanceof AbstractButton abstractButton) {
                abstractButton.setToolTipText(null);
            } else if (component instanceof Container container2) {
                clearToolTipText(container2);
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
        for (var component : container.getComponents()) {
            if (!ArrayUtils.contains(excludedComponents, component)) {
                component.setEnabled(enable);
                if (component instanceof Container container2) {
                    enableComponents(container2, enable);
                }
            }
        }
    }

    public static void frameStateRestore(Preferences preferences, JFrame frame, int defaultWidth, int defaultHeight) throws BackingStoreException {
        if (PrefsHelper.keyExists(preferences, FRAME_X) && PrefsHelper.keyExists(preferences, FRAME_Y) && PrefsHelper.keyExists(preferences, FRAME_H) && PrefsHelper.keyExists(preferences, FRAME_W)) {
            int x = preferences.getInt(FRAME_X, -1);
            int y = preferences.getInt(FRAME_Y, -1);
            frame.setLocation(x, y);

            int h = preferences.getInt(FRAME_H, -1);
            int w = preferences.getInt(FRAME_W, -1);
            frame.setSize(w, h);

            if (PrefsHelper.keyExists(preferences, FRAME_STATE)) {
                frame.setExtendedState(preferences.getInt(FRAME_STATE, -1));
            }
        } else {
            frame.setSize(defaultWidth, defaultHeight);
            frame.setLocationRelativeTo(null);
        }
    }

    public static void frameStateSave(Preferences preferences, JFrame frame) {
        preferences.putInt(FRAME_H, frame.getHeight());
        preferences.putInt(FRAME_W, frame.getWidth());
        preferences.putInt(FRAME_X, frame.getX());
        preferences.putInt(FRAME_Y, frame.getY());
        preferences.putInt(FRAME_STATE, frame.getExtendedState());

        try {
            preferences.sync();
        } catch (BackingStoreException ex) {
            Logger.getLogger(SwingHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
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
//        double defaultFontSize = 12.0;
//        Integer fontSize = (Integer) UIManager.get("customFontSize");
//
//        return fontSize == null ? 1.0 : fontSize / defaultFontSize;
        return SystemHelper.getUIScale();
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

    public static Insets getUIScaledInsets(int value) {
        return getUIScaledInsets(value, value, value, value);
    }

    public static Insets getUIScaledInsets(int top, int left, int bottom, int right) {
        return new Insets((int) (top * getUIScale()), (int) (left * getUIScale()), (int) (bottom * getUIScale()), (int) (right * getUIScale()));
    }

    public static void makeWindowResizable(Component component) {
        component.addHierarchyListener(hierarchyEvent -> {
            var window = SwingUtilities.getWindowAncestor(component);
            if (window instanceof Dialog dialog) {
                if (!dialog.isResizable()) {
                    dialog.setResizable(true);
                }
            }
        });
    }

    /**
     * Tries to activate a window and excute a runnable after that.
     *
     * Usually needed when using drag and drop
     *
     *
     * @param c the component to find its window
     * @param r the runnable to execute when focused
     */
    public static void requestWindowFocusAndRun(Component c, Runnable r) {
        try {
            //The Robot is needed to override Focus stealing prevention
            var robot = new Robot();
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        } catch (AWTException ex) {
            //If the platform configuration does not allow low-level input control,
            //for example, if the X-Window systems XTEST 2.2 extension is not activated.
            //
            //Request focus and hope for the best
            SwingUtilities.invokeLater(() -> {
                SwingUtilities.getWindowAncestor(c).requestFocus();
            });
        } finally {
            //Give it some time to get activated before running...
            runLaterDelayed(20, r);
        }
    }

    public static void runAndWait(long delay, Runnable r) {
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(r);
        });

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(SwingHelper.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Run r now if isEventDispatchThread, else invokeLater
     *
     * @param r
     */
    public static void runLater(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            SwingUtilities.invokeLater(r);
        }
    }

    public static void runLaterDelayed(long delay, Runnable r) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                SwingUtilities.invokeLater(r);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwingHelper.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void setComponentsFont(Container container, Font font) {
        container.setFont(font);

        for (var component : container.getComponents()) {
            component.setFont(font);
            if (component instanceof JMenu menu) {
                setComponentsFont(menu, font);
            }
        }
    }

    public static void setFullScreen(Window window) {
        var graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        if (graphicsDevice.isFullScreenSupported()) {
            graphicsDevice.setFullScreenWindow(window);
        }
    }
}
