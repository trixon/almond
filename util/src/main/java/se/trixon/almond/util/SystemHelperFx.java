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
package se.trixon.almond.util;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 *
 * @author Patrik Karlström
 */
public class SystemHelperFx extends SystemHelper {

    public static boolean copyToClipboard(BufferedImage bufferedImage) {
        return copyToClipboard(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    public static boolean copyToClipboard(Image image) {
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putImage(image);

        return Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    public static Image getResourceAsImage(Class cls, String name) {
        Image image = null;
        try {
            String path = "/%s%s".formatted(getPackageAsPath(cls), name);
            image = new Image(path);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return image;
    }

    public static ImageView getResourceAsImageView(Class cls, String name) {
        ImageView imageView = null;
        try {
            String path = "/%s%s".formatted(getPackageAsPath(cls), name);
            imageView = new ImageView(new Image(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return imageView;
    }

}
