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
public class AColorChooser {

  private JColorChooser jColorChooser = new JColorChooser();
  private Color currentColor;
  private DialogDescriptor dialogDescriptor;

  private AColorChooser(Color aColor) {
    currentColor = aColor;
    jColorChooser.setColor(aColor);
    init();
  }

  public static Color showDialog(Color aColor) {
    AColorChooser colorChooser = new AColorChooser(aColor);

    return colorChooser.jColorChooser.getColor();
  }

  private void init() {
    String dialogTitle = NbBundle.getMessage(AColorChooser.class, "CTL_DialogTitleColor");
    jColorChooser.setBorder(new EmptyBorder(10, 10, 0, 10));
    final JButton resetButton = new JButton(NbBundle.getMessage(AColorChooser.class, "CTL_Reset"));

    ActionListener actionListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
          jColorChooser.setColor(currentColor);
        } else if (dialogDescriptor.getValue() == DialogDescriptor.CANCEL_OPTION) {
          jColorChooser.setColor(currentColor);
        }
      }
    };

    dialogDescriptor = new DialogDescriptor(
            jColorChooser,
            dialogTitle,
            true,
            new Object[]{DialogDescriptor.OK_OPTION, DialogDescriptor.CANCEL_OPTION},
            DialogDescriptor.OK_OPTION,
            DialogDescriptor.DEFAULT_ALIGN,
            null,
            actionListener);

    dialogDescriptor.setAdditionalOptions(new Object[]{resetButton});
    DialogDisplayer.getDefault().notify(dialogDescriptor);
  }
}
