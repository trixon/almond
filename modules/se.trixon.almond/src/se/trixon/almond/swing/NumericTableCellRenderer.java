package se.trixon.almond.swing;

import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NumericTableCellRenderer extends DefaultTableCellRenderer {

    private Class mClass;

    public NumericTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public NumericTableCellRenderer(Class c) {
        this();
        mClass = c;
    }

    @Override
    public void setValue(Object value) {
        Object result = value;
        if ((value != null) && (value instanceof Number)) {
            Number numberValue = (Number) value;
            NumberFormat formatter;

            if (mClass == Double.class || mClass == Float.class) {
                formatter = NumberFormat.getNumberInstance();
            } else {
                formatter = NumberFormat.getIntegerInstance();
            }
            result = formatter.format(numberValue.doubleValue());
        }

        super.setValue(result);
    }
}
