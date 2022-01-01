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
package se.trixon.almond.util.fx;

import java.util.ResourceBundle;
import javafx.scene.image.ImageView;

/**
 *
 * @author Patrik Karlström
 */
public class AboutModel extends se.trixon.almond.util.AboutModel {

    private ImageView mImageView = null;

    public AboutModel(ResourceBundle resourceBundle, ImageView imageView) {
        setBundle(resourceBundle);
        setImageView(imageView);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    @Override
    public Object getLogo() {
        return getImageView();
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
        mImageView.setPreserveRatio(true);
        mImageView.setFitHeight(100);
        mImageView.setFitWidth(100);
    }

}
