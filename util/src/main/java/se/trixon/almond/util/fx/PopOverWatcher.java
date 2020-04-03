/*
 * Copyright 2020 Patrik Karlström.
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
package se.trixon.almond.util.fx;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.scene.Node;
import javax.swing.JFrame;
import org.controlsfx.control.PopOver;

/**
 * This class hides open PopOvers when their JFrame gets deactivaded and shows them upon JFrame activation.
 *
 * @author Patrik Karlström
 */
public class PopOverWatcher {

    /**
     * The JFrame to listen for windowsFocus changes
     */
    private JFrame mFrame;
    /**
     * A map of PopOvers that at one time was opened, they might be closed now.
     */
    private final LinkedHashMap<PopOver, Node> mPopOvers = new LinkedHashMap<>();

    public static PopOverWatcher getInstance() {
        return PopOverWatcherHolder.INSTANCE;
    }

    private PopOverWatcher() {
    }

    /**
     * Call this when opening a PopOver in order to register a PopOver to be handled
     *
     * @param popOver
     * @param node
     */
    public void registerPopOver(PopOver popOver, Node node) {
        mPopOvers.remove(popOver);//This is done in order to secure correct ordering
        mPopOvers.put(popOver, node);
    }

    /**
     * Set the JFrame to listen for windowsFocus changes
     *
     * @param frame
     */
    public void setFrame(JFrame frame) {
        mFrame = frame;

        mFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                Platform.runLater(() -> {
                    for (Map.Entry<PopOver, Node> entry : mPopOvers.entrySet()) {
                        entry.getKey().show(entry.getValue());
                    }
                });
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                Platform.runLater(() -> {
                    var popOvers = new ArrayList<PopOver>(mPopOvers.keySet());
                    Collections.reverse(popOvers);
                    for (PopOver popOver : popOvers) {
                        if (popOver.isShowing()) {
                            popOver.hide();
                        } else {
                            mPopOvers.keySet().remove(popOver);
                        }
                    }
                });
            }
        });
    }

    private static class PopOverWatcherHolder {

        private static final PopOverWatcher INSTANCE = new PopOverWatcher();
    }
}
