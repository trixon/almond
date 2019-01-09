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
package se.trixon.almond.nbp;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlström
 */
public class Almond {

    public static boolean ASK_CONFIRM_EXIT = false;
    public static int ICON_LARGE = 24;
    public static int ICON_SMALL = 16;
    private final static WindowManager WINDOW_MANAGER = WindowManager.getDefault();
    private static boolean sWasSelected;

    public static void activateWindow(boolean active) {
        SwingUtilities.invokeLater(() -> {
            getFrame().setEnabled(active);
        });
    }

    public static Frame getFrame() {
        return WindowManager.getDefault().getMainWindow();
    }

    public static void openAndActivateTopComponent(String id) {
        SwingUtilities.invokeLater(() -> {
            WINDOW_MANAGER.findTopComponent(id).open();
            WINDOW_MANAGER.findTopComponent(id).requestActive();
        });
    }

    public static synchronized boolean requestActive(String preferredID) {
        Runnable r = () -> {
            try {
                TopComponent topComponent = WINDOW_MANAGER.findTopComponent(preferredID);
                Mode mode = WINDOW_MANAGER.findMode(topComponent);
                sWasSelected = mode.getSelectedTopComponent() == topComponent;
                topComponent.requestActive();
            } catch (Exception e) {
                //Could not find top component...
                sWasSelected = false;
            }
        };

        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException | InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return sWasSelected;
    }
}
