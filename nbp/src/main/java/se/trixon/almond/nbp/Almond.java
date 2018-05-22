/*
 * Copyright 2018 Patrik Karlström.
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
import javax.swing.SwingUtilities;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlström
 */
public class Almond {

    public static boolean ASK_CONFIRM_EXIT = false;
    public static int ICON_LARGE = 24;
    public static int ICON_SMALL = 16;
    private final static WindowManager sWindowManager = WindowManager.getDefault();

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
            sWindowManager.findTopComponent(id).open();
            sWindowManager.findTopComponent(id).requestActive();
        });
    }
}
