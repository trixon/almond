package se.trixon.almond.swing;

import java.awt.Color;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import se.trixon.almond.util.AGraphics;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AColorChooserButton extends JButton {

    private static final String COLOR_SYMBOL = "█";

    private Color mColor = Color.BLACK;
    private String mText;
    private Color mTextColor = Color.BLACK;

    public AColorChooserButton() {
        super();
        init();
    }

    public AColorChooserButton(String text) {
        super(text);
        init();
    }

    public AColorChooserButton(Action action) {
        super(action);
        init();
    }

    public AColorChooserButton(Icon icon) {
        super(icon);
        init();
    }

    public AColorChooserButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    public Color getColor() {
        return mColor;
    }

    public Color getTextColor() {
        return mTextColor;
    }

    public void setColor(Color color) {
        mColor = color;
        setText(mText);
    }

    @Override
    public void setText(String text) {
        mText = text;
        String color = AGraphics.colorToHex(mColor);
        String textColor = AGraphics.colorToHex(mTextColor);
        super.setText(String.format("<html><font color=%s>%s <font color=%s>%s</html>", color, COLOR_SYMBOL, textColor, text));
    }

    public void setTextColor(Color textColor) {
        mTextColor = textColor;
    }

    private void init() {
        setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
    }
}
