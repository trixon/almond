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
package se.trixon.almond.util.swing.dialogs.about;

import se.trixon.almond.util.AboutModel;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrik Karlström
 */
public class LibrariesTab extends BaseListTab {

    private final AboutModel mAboutModel;
    private final ArrayList<String> mLibraries = new ArrayList<>();

    public LibrariesTab(AboutModel aboutModel) {
        super();
        mAboutModel = aboutModel;
        init();
    }

    private void init() {
        String javaVersion = System.getProperty("java.runtime.version");
        String javaName = System.getProperty("java.runtime.name");
        String java = String.format("%s %s", javaName, javaVersion);
        mLibraries.add(java);

        ArrayList<String> libraries = new ArrayList<>();
        libraries.add("Almond-Util");

        if (mAboutModel.getLibraries() != null) {
            String[] libs = StringUtils.split(mAboutModel.getLibraries(), ";");
            libraries.addAll(Arrays.asList(libs));
        }

        libraries.sort((String o1, String o2) -> o1.compareToIgnoreCase(o2));
        mLibraries.addAll(libraries);

        StringBuilder builder = new StringBuilder();
        mLibraries.forEach((library) -> {
            builder.append(LIST_SIGN).append(library).append("\n");
        });

        editorPane.setText(builder.toString());
    }
}
