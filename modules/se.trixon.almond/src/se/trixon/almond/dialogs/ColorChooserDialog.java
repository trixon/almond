package se.trixon.almond.dialogs;

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
 * @author Patrik Karlsson <patrik@trixon.se>
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
