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
package se.trixon.almond.nbp.fx;

import java.awt.BorderLayout;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public abstract class FxTopComponent extends TopComponent {

    private static final WindowManager WINDOW_MANAGER = WindowManager.getDefault();
    private ResourceBundle mBundle;

    private final JFXPanel mFxPanel = new JFXPanel();
    private Scene mScene;

    public FxTopComponent() {
        setLayout(new BorderLayout());
        add(mFxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            initFX();
            mFxPanel.setScene(mScene);
        });
    }

    public ResourceBundle getBundle() {
        if (mBundle == null) {
            mBundle = NbBundle.getBundle(getClass());
        }

        return mBundle;
    }

    public String getBundleString(String key) {
        return getBundle().getString(key);
    }

    public JFXPanel getFxPanel() {
        return mFxPanel;
    }

    public Scene getScene() {
        return mScene;
    }

    public void resetFx() {
        removeAll();
        mFxPanel.setVisible(true);
        add(mFxPanel, BorderLayout.CENTER);
    }

    public void setScene(Scene scene) {
        mScene = scene;
        FxHelper.loadDarkTheme(scene);
    }

    public void toggleOpened() {
        boolean activeInMode = WINDOW_MANAGER.findMode(this).getSelectedTopComponent() == this;

        if (activeInMode) {
            close();
        } else {
            open();
        }
    }

    @Override
    protected void componentActivated() {
        super.componentActivated();
        Platform.runLater(() -> {
            fxComponentActivated();
        });
    }

    @Override
    protected void componentClosed() {
        Platform.runLater(() -> {
            fxComponentClosed();
        });
        super.componentClosed();
    }

    @Override
    protected void componentDeactivated() {
        Platform.runLater(() -> {
            fxComponentDeactivated();
        });
        super.componentDeactivated();
    }

    @Override
    protected void componentHidden() {
        Platform.runLater(() -> {
            fxComponentHidden();
        });
        super.componentHidden();
    }

    @Override
    protected void componentOpened() {
        super.componentOpened();
        Platform.runLater(() -> {
            fxComponentOpened();
        });
    }

    @Override
    protected void componentShowing() {
        super.componentShowing();
        Platform.runLater(() -> {
            fxComponentShowing();
        });
    }

    protected void fxComponentActivated() {
    }

    protected void fxComponentClosed() {
    }

    protected void fxComponentDeactivated() {
    }

    protected void fxComponentHidden() {
    }

    protected void fxComponentOpened() {
    }

    protected void fxComponentShowing() {
    }

    /**
     * Runs on the JavaFX Application Thread.
     */
    protected abstract void initFX();
}
