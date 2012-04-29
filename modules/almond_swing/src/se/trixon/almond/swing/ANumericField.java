package se.trixon.almond.swing;

import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ANumericField extends JTextField {

  private boolean selectedByMouse;

  public ANumericField() {
    init();
  }

  public ANumericField(int aValue) {
    init();
    setText(String.valueOf(aValue));
  }

  public ANumericField(double aValue) {
    init();
    setText(String.valueOf(aValue));
  }

  private ANumericField(String text) {
  }

  private ANumericField(String text, int columns) {
  }

  private ANumericField(Document doc, String text, int columns) {
  }

  public double getValue() throws NumberFormatException {
    return Double.parseDouble(getText().replace(",", "."));
  }

  private void init() {
    setHorizontalAlignment(JTextField.TRAILING);
    setText("0");
    setDragEnabled(true);

    addKeyListener(new KeyAdapter() {

      @Override
      public void keyTyped(KeyEvent evt) {
        if (isNumericInput(evt) == false) {
          evt.consume();
        }
      }
    });

    addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent me) {
//                selectedByMouse = true;
      }
    });

    addFocusListener(new FocusAdapter() {

      @Override
      public void focusGained(FocusEvent fe) {
        if (selectedByMouse) {
          selectedByMouse = false;
        } else {
//                    selectAll();
        }
      }
    });
  }

  private boolean isNumericInput(KeyEvent evt) {
    char c = evt.getKeyChar();
    String text = ((JTextField) evt.getComponent()).getText();

    boolean isNumeric = false;
    boolean decimalSignTaken = text.contains(".") || text.contains(",");

    if (((Character.isDigit(c)
            || (c == '.')
            || (c == ',')
            || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE)
            || ((c == KeyEvent.VK_MINUS) && (text.isEmpty()))))) {
      isNumeric = true;

      if ((c == '.') || (c == ',')) {
        if (decimalSignTaken) {
          isNumeric = false;
        }
      }
    }

    return isNumeric;
  }
}
