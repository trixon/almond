/* 
 * Copyright 2018 Patrik Karlsson.
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
package se.trixon.almond.nbp.dialogs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.border.EmptyBorder;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson
 */
public class ColorChooserDialog {

    public static Color showDialog(Color color) {
        ColorChooserDialog colorChooser = new ColorChooserDialog(color);

        return colorChooser.mColorChooser.getColor();
    }
    private final JColorChooser mColorChooser = new JColorChooser();
    private final Color mCurrentColor;
    private DialogDescriptor mDialogDescriptor;

    private ColorChooserDialog(Color color) {
        mCurrentColor = color;
        mColorChooser.setColor(color);
        init();
    }

    private void init() {
        String dialogTitle = NbBundle.getMessage(ColorChooserDialog.class, "CTL_DialogTitleColor");
        mColorChooser.setBorder(new EmptyBorder(10, 10, 0, 10));
        final JButton resetButton = new JButton(NbBundle.getMessage(ColorChooserDialog.class, "CTL_Reset"));

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetButton) {
                    mColorChooser.setColor(mCurrentColor);
                } else if (mDialogDescriptor.getValue() == DialogDescriptor.CANCEL_OPTION) {
                    mColorChooser.setColor(mCurrentColor);
                }
            }
        };

        mDialogDescriptor = new DialogDescriptor(
                mColorChooser,
                dialogTitle,
                true,
                new Object[]{DialogDescriptor.OK_OPTION, DialogDescriptor.CANCEL_OPTION},
                DialogDescriptor.OK_OPTION,
                DialogDescriptor.DEFAULT_ALIGN,
                null,
                actionListener);

        mDialogDescriptor.setAdditionalOptions(new Object[]{resetButton});
        DialogDisplayer.getDefault().notify(mDialogDescriptor);
    }
}
