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

public class ALabelURL extends JLabel {

  private URI uri;

  public ALabelURL() {
    init();
  }

  public ALabelURL(Icon anIcon) {
    super(anIcon);
    init();
  }

  public ALabelURL(Icon anIcon, int aHorizontalAlignment) {
    super(anIcon, aHorizontalAlignment);
    init();
  }

  public ALabelURL(String aText) {
    super(aText);
    init();
  }

  public ALabelURL(String aText, Icon anIcon, int aHorizontalAlignment) {
    super(aText, anIcon, aHorizontalAlignment);
    init();
  }

  public ALabelURL(String aText, int aHorizontalAlignment) {
    super(aText, aHorizontalAlignment);
    init();
  }

  public void setURI(URI anUri) {
    this.uri = anUri;
  }

  public void setURI(String anUri) {
    try {
      this.uri = new URI(anUri);
    } catch (URISyntaxException ex) {
      System.err.println(ex);
    }
  }

  @Override
  public void setText(String aText) {
    super.setText("<html><a href=\"\">" + aText + "</a>");
  }

  private void init() {
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    addMouseListener(new java.awt.event.MouseAdapter() {

      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        launchURL(evt);
      }
    });
  }

  private void launchURL(MouseEvent evt) {
    if (uri != null) {
      try {
        Desktop.getDesktop().browse(uri);
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
