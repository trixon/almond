package se.trixon.almond.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AFileChooserPanel extends JPanel {

  private JLabel label = new JLabel();
  private JTextField textField = new JTextField();
  private JButton button = new JButton("…");
  private JFileChooser fileChooser;
  private int mode;

  public AFileChooserPanel(int aMode, String aLabelText) {
    mode = aMode;
    label.setText(aLabelText);
    init();
  }

  public JButton getButton() {
    return button;
  }

  public JLabel getLabel() {
    return label;
  }

  public String getPath() {
    return textField.getText();
  }

  public JTextField getTextField() {
    return textField;
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    button.setEnabled(enabled);
    textField.setEnabled(enabled);
  }

  public void setPath(String anIconThemePath) {
    textField.setText(anIconThemePath);
  }

  private void init() {
    textField.setMinimumSize(new Dimension(400, 10));
    setLayout(new BorderLayout());
    add(label, BorderLayout.PAGE_START);
    add(textField, BorderLayout.CENTER);
    add(button, BorderLayout.LINE_END);

    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent evt) {
        fileChooser = new JFileChooser(textField.getText());
        fileChooser.setFileSelectionMode(mode);
        fileChooser.showOpenDialog(button.getTopLevelAncestor());

        if (fileChooser.getSelectedFile() != null) {
          textField.setText(fileChooser.getSelectedFile().toString());
        }
      }
    });
  }
}
