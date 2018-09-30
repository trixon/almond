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
package se.trixon.almond.util.fx;

import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Patrik Karlström
 */
public class ImagePanel extends JFXPanel {

    private StackPane mRoot;
    private Scene mScene;
    private ImageView mImageView;

    public ImagePanel() {
        setPreferredSize(new Dimension(640, 480));
        Platform.runLater(() -> {
            createScene();
        });
    }

    public ImageView getImageView() {
        return mImageView;
    }

    private void createScene() {
        mImageView = new ImageView();
        mImageView.setPreserveRatio(true);
        mImageView.setPickOnBounds(true);

        mRoot = new StackPane();
        mRoot.getChildren().add(mImageView);
        mScene = new Scene(mRoot);
        setScene(mScene);
    }
}
