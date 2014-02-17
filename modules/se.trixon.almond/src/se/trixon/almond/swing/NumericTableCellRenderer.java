package se.trixon.almond.swing;

import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NumericTableCellRenderer extends DefaultTableCellRenderer {

    public NumericTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public void setValue(Object value) {
        Object result = value;
        if ((value != null) && (value instanceof Number)) {
            Number numberValue = (Number) value;
            NumberFormat formatter = NumberFormat.getIntegerInstance();
            result = formatter.format(numberValue.doubleValue());
        }

        super.setValue(result);
    }
}
