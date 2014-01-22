package se.trixon.almond.swing;

import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ModelCreator {

    public static DefaultComboBoxModel createDefaultComboBoxModel(Model model) {
        ResourceBundle bundle = NbBundle.getBundle(ModelCreator.class);
        switch (model) {
            case BEGINS_CONTAINS_ENDS:
                return new DefaultComboBoxModel(new String[]{
                    bundle.getString("startsWith"),
                    bundle.getString("contains"),
                    bundle.getString("endsWith")});
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
