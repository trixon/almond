package se.trixon.almond.about;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ALicensePanel extends JScrollPane {

  private JTextArea textArea;

  public ALicensePanel(String aLicenseText) {
    init();
    textArea.setText(aLicenseText);
    resetPosition();
  }

  private void resetPosition() {
    textArea.setCaretPosition(0);
    getVerticalScrollBar().setValue(0);
  }

  private void init() {
    textArea = new JTextArea();
    setViewportView(textArea);
    setPreferredSize(new Dimension(685, 440));

    textArea.setMargin(new Insets(5, 5, 5, 0));
    textArea.setFont(new java.awt.Font("Monospaced", 0, 13));
    textArea.setEditable(false);

    setBorder(new EmptyBorder(8, 10, 0, 10));
  }
}
