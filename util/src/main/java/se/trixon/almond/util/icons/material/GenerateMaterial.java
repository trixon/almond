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
package se.trixon.almond.util.icons.material;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
class GenerateMaterial {

    private static final String DIR_DEST = "/home/pata/temp/material";
    private static final String DIR_SOURCE = "/home/pata/temp/material-design-icons-3.0.1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new GenerateMaterial();
        } catch (IOException ex) {
            Logger.getLogger(GenerateMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GenerateMaterial() throws IOException {
        File rootDir = new File(DIR_SOURCE);
        File destDir = new File(DIR_DEST);
        StringBuilder builder = new StringBuilder();
        addHeader(builder);

        FileVisitor fileVisitor = new FileVisitor(destDir);
        EnumSet<FileVisitOption> fileVisitOptions = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(rootDir.toPath(), fileVisitOptions, Integer.MAX_VALUE, fileVisitor);
        fileVisitor.addEnums(builder);
        addFooter(builder);
        System.out.println(builder.toString());
    }

    private void addHeader(StringBuilder builder) {
        builder.append("package se.trixon.almond.util.icons.material;").append("\n");
        builder.append("import java.awt.Image;").append("\n");
        builder.append("import javax.swing.ImageIcon;").append("\n");
        builder.append("import se.trixon.almond.util.icons.IconColor;").append("\n");
        builder.append("public class MaterialIcon {").append("\n");

        builder.append("private static ImageIcon getImageIcon(String dir, String baseName, int size, IconColor iconColor) {").append("\n");
        builder.append("String path = MaterialIcon.class.getPackage().getName().replace(\".\", \"/\");").append("\n");
        builder.append("String fileName = String.format(\"/%s/%s/%s_%s.png\", path, dir, baseName.toLowerCase(), iconColor.name().toLowerCase());").append("\n");
        builder.append("ImageIcon imageIcon = new ImageIcon(MaterialIcon.class.getResource(fileName));").append("\n");
        builder.append("Image scaledImage = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);").append("\n");
        builder.append("return new ImageIcon(scaledImage);").append("\n");
        builder.append("}").append("\n");

        builder.append("private static ImageView getImageView(String dir, String baseName, int size, IconColor iconColor) {").append("\n");
        builder.append("BufferedImage bufferedImage = GraphicsHelper.toBufferedImage(getImageIcon(dir, baseName, size, iconColor).getImage());").append("\n");
        builder.append("return new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));").append("\n");
        builder.append("}").append("\n");

        builder.append("").append("\n");
    }

    private void addFooter(StringBuilder builder) {
        builder.append("public interface IconGetter{").append("\n");
        builder.append("public ImageIcon get(int size, IconColor iconColor);").append("\n");
        builder.append("public ImageIcon get(int size);").append("\n");
        builder.append("public ImageView getImageView(int size, IconColor iconColor);").append("\n");
        builder.append("public ImageView getImageView(int size);").append("\n");
        builder.append("}").append("\n");
        builder.append("").append("\n");
        builder.append("}").append("\n");
    }

    class FileVisitor extends SimpleFileVisitor<Path> {

        private final File mDestDir;
        private boolean mInterrupted;
        private String mCategory;
        private final Set<String> mEnums = new TreeSet<>();

        public FileVisitor(File destDir) {
            mDestDir = destDir;
        }

        public void addEnums(StringBuilder builder) {
            mEnums.forEach((enumItem) -> {
                builder.append(enumItem);
            });
        }

        public boolean isInterrupted() {
            return mInterrupted;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (Thread.interrupted()) {
                mInterrupted = true;
                return FileVisitResult.TERMINATE;
            }

            if (dir.toString().contains("2x_web")) {
                mCategory = dir.getParent().getFileName().toString();
                StringBuilder builder = new StringBuilder();
                builder.append(String.format("public enum _%s implements IconGetter{", StringUtils.capitalize(mCategory.toLowerCase()))).append("\n");
                File categoryDir = new File(mDestDir, "_" + mCategory);
                FileUtils.forceMkdir(categoryDir);

                String[] filePaths = dir.toFile().list();

                if (filePaths != null && filePaths.length > 0) {
                    Set<String> treeSet = new TreeSet<>();
                    for (String fileName : filePaths) {
                        File file = new File(dir.toFile(), fileName);
                        String name = file.getName().toLowerCase();
                        if (name.endsWith("_48dp.png")) {
                            name = name.replace("ic_", "").replace("_48dp.png", ".png");
                            if (StringUtils.isNumeric(StringUtils.left(name, 1))) {
                                name = "_" + name;
                            }
                            FileUtils.copyFile(file, new File(categoryDir, name), true);

                            name = name.replace("_black", "").replace("_white", "").replace(".png", "");
                            treeSet.add(name);
                        }
                    }

                    treeSet.forEach((name) -> {
                        builder.append(name.toUpperCase()).append(",").append("\n");
                    });
                    builder.deleteCharAt(builder.length() - 1);
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append(";").append("\n");

                    builder.append("@Override").append("\n");
                    builder.append("public ImageIcon get(int size, IconColor iconColor) {").append("\n");
                    builder.append("return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);").append("\n");
                    builder.append("}").append("\n");

                    builder.append("@Override").append("\n");
                    builder.append("public ImageIcon get(int size) {").append("\n");
                    builder.append("return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, IconColor.getDefault());").append("\n");
                    builder.append("}").append("\n");

                    builder.append("@Override").append("\n");
                    builder.append("public ImageView getImageView(int size, IconColor iconColor) {").append("\n");
                    builder.append("return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, iconColor);").append("\n");
                    builder.append("}").append("\n");

                    builder.append("@Override").append("\n");
                    builder.append("public ImageView getImageView(int size) {").append("\n");
                    builder.append("return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, IconColor.getDefault());").append("\n");
                    builder.append("}").append("\n");
                }
                builder.append("}").append("\n");
                mEnums.add(builder.toString());
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }
}
