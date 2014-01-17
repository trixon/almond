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
public class LicensePanel extends JScrollPane {

    private JTextArea mTextArea;

    public LicensePanel(String licenseText) {
        init();
        mTextArea.setText(licenseText);
        resetPosition();
    }

    private void init() {
        mTextArea = new JTextArea();
        setViewportView(mTextArea);
        setPreferredSize(new Dimension(685, 440));

        mTextArea.setMargin(new Insets(5, 5, 5, 0));
        mTextArea.setFont(new java.awt.Font("Monospaced", 0, 13));
        mTextArea.setEditable(false);

        setBorder(new EmptyBorder(8, 10, 0, 10));
    }

    private void resetPosition() {
        mTextArea.setCaretPosition(0);
        getVerticalScrollBar().setValue(0);
    }
}
