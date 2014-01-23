package se.trixon.almond.swing;

import javax.swing.DefaultComboBoxModel;
import se.trixon.almond.dictionary.Dict;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ModelCreator {

    public static DefaultComboBoxModel createDefaultComboBoxModel(Model model) {
        switch (model) {
            case BEGINS_CONTAINS_ENDS:
                return new DefaultComboBoxModel(new String[]{
                    Dict.STARTS_WITH.getString(),
                    Dict.CONTAINS.getString(),
                    Dict.ENDS_WITH.getString()});
            default:
                return null;
        }
    }

    public enum Model {

        /**
         * Begins with, contains, ends with.
         */
        BEGINS_CONTAINS_ENDS;
    }
}
