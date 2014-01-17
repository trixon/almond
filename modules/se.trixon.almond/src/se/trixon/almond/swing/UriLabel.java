package se.trixon.almond.swing;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Icon;
import javax.swing.JLabel;

public class UriLabel extends JLabel {

    private URI mUri;

    public UriLabel() {
        init();
    }

    public UriLabel(Icon icon) {
        super(icon);
        init();
    }

    public UriLabel(Icon icon, int horizontalAlignment) {
        super(icon, horizontalAlignment);
        init();
    }

    public UriLabel(String text) {
        super(text);
        init();
    }

    public UriLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        init();
    }

    public UriLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    @Override
    public void setText(String text) {
        super.setText("<html><a href=\"\">" + text + "</a>");
    }

    public void setURI(URI uri) {
        mUri = uri;
    }

    public void setURI(String uriString) {
        try {
            mUri = new URI(uriString);
        } catch (URISyntaxException ex) {
            System.err.println(ex);
        }
    }

    private void init() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                launchURI(evt);
            }
        });
    }

    private void launchURI(MouseEvent evt) {
        if (mUri != null) {
            try {
                Desktop.getDesktop().browse(mUri);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
