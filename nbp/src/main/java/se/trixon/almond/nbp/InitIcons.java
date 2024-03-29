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
package se.trixon.almond.nbp;

import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlström
 */
public class InitIcons {

    public static void run() {
        String category = "Actions/File";
        String id = "se.trixon.almond.nbp.actions.QuitAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Navigation.CLOSE.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Navigation.CLOSE.getImageIcon(Almond.ICON_LARGE));

        //TODO Fix missing icons
        category = "Actions/Edit";
        id = "org-openide-actions-CutAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_CUT.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_CUT.getImageIcon(Almond.ICON_LARGE));

        id = "org-openide-actions-CopyAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_COPY.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_COPY.getImageIcon(Almond.ICON_LARGE));

        id = "org-openide-actions-PasteAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_LARGE));

        id = "org-openide-actions-FindAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_LARGE));

        id = "org-openide-actions-UndoAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_LARGE));

        id = "org-openide-actions-RedoAction";
        ActionHelper.setIconSmall(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_SMALL));
        ActionHelper.setIconLarge(category, id, MaterialIcon._Content.CONTENT_PASTE.getImageIcon(Almond.ICON_LARGE));
    }
}
