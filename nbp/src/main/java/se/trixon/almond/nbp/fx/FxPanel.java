/*
 * Copyright 2023 Patrik Karlström.
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

import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import se.trixon.almond.util.fx.FxHelper;

/**
 *
 * @author Patrik Karlström
 */
public abstract class FxPanel extends JFXPanel {

    public FxPanel() {
        setPreferredSize(new Dimension(200, 200));
    }

    public void initFx() {
        initFx(null);
    }

    public void initFx(Runnable runnable) {
        Platform.runLater(() -> {
            fxConstructor();
            FxHelper.loadDarkTheme(getScene());

            if (runnable != null) {
                runnable.run();
            }
        });
    }

    protected abstract void fxConstructor();

}
