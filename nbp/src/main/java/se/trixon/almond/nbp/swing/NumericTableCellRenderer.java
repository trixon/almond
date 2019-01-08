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
package se.trixon.almond.nbp.swing;

import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Patrik Karlström
 */
public class NumericTableCellRenderer extends DefaultTableCellRenderer {

    private int fmFractionDigits;
    private Class mClass;

    public NumericTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public NumericTableCellRenderer(Class c, int fractionDigits) {
        this();
        mClass = c;
        fmFractionDigits = fractionDigits;
    }

    @Override
    public void setValue(Object value) {
        Object result = value;

        if ((value != null) && (value instanceof Number)) {
            Number numberValue = (Number) value;
            NumberFormat formatter;

            if (mClass == Double.class || mClass == Float.class) {
                formatter = NumberFormat.getNumberInstance();
                formatter.setMinimumFractionDigits(fmFractionDigits);
                formatter.setMaximumFractionDigits(fmFractionDigits);
            } else {
                formatter = NumberFormat.getIntegerInstance();
            }

            result = formatter.format(numberValue.doubleValue());
        }

        super.setValue(result);
    }
}
