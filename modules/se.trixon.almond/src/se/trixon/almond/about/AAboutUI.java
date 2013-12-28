package se.trixon.almond.about;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbBundle;
import se.trixon.almond.swing.ALabelURL;
import se.trixon.almond.swing.ATabbedPane;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AAboutUI extends JPanel {

    private JPanel aboutPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel versionLabel = new JLabel();
    private JLabel usesLabel = new JLabel();
    private JLabel copyrightLabel;
    private JLabel descriptionLabel;
    private ATabbedPane tabbedPane;
    private JScrollPane authorScrollPane;
    private JScrollPane licenseScrollPane;
    private JScrollPane thanksScrollPane;
    private JScrollPane translationScrollPane;
    private JScrollPane propertiesScrollPane;
    private JTextArea authorTextArea;
    private JTextArea licenseTextArea;
    private JTextArea thanksTextArea;
    private JTextArea translationTextArea;
    private JTable propertiesTable;
    private ALabelURL uriLabel;
    private Insets textAreaInsets = new Insets(10, 10, 10, 10);
    private DefaultTableModel defaultTableModel;
    private ResourceBundle resourceBundle = NbBundle.getBundle(AAbout.class);
    private ResourceBundle applicationResourceBundle;
    private ResourceBundle licenseResourceBundle;

    public AAboutUI(ResourceBundle aResourceBundle, ImageIcon anImageIcon, ResourceBundle aLicenseResourceBundle) {
        applicationResourceBundle = aResourceBundle;
        licenseResourceBundle = aLicenseResourceBundle;
        logoLabel.setIcon(anImageIcon);
        init();
    }

    private void addSystemProperty(DefaultTableModel aTableModel, String aKey) {
        aTableModel.addRow(new Object[]{aKey, System.getProperty(aKey)});
    }

    private void init() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(12, 3, 12, 3));
        setPreferredSize(new Dimension(500, 400));
        initMain();
        initHeader();
        initSystemProperties();
    }

    private void initAbout() {
        aboutPanel = new JPanel();
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        Font font = new java.awt.Font("Dialog", 0, 14);

        uriLabel = new ALabelURL();
        descriptionLabel = new JLabel();
        copyrightLabel = new JLabel();

        descriptionLabel.setFont(font);
        copyrightLabel.setFont(font);
        uriLabel.setFont(font);

        copyrightLabel.setText(getString("application.copyright"));
        uriLabel.setText(getString("application.uriText"));
        nameLabel.setText(getString("application.title"));
        descriptionLabel.setText(getString("application.description"));
        uriLabel.setURI(getString("application.uri"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel aboutInfoPanel = new JPanel(gridBagLayout);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        gridBagLayout.setConstraints(descriptionLabel, gridBagConstraints);
        aboutInfoPanel.add(descriptionLabel, gridBagConstraints);

        gridBagLayout.setConstraints(copyrightLabel, gridBagConstraints);
        aboutInfoPanel.add(copyrightLabel, gridBagConstraints);

        gridBagConstraints.ipady = 8;

        gridBagLayout.setConstraints(uriLabel, gridBagConstraints);
        aboutInfoPanel.add(uriLabel, gridBagConstraints);

        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.add(Box.createVerticalGlue());
        aboutPanel.add(aboutInfoPanel);
        aboutPanel.add(Box.createVerticalGlue());

        aboutPanel.setName("about");
        tabbedPane.add(aboutPanel);
        tabbedPane.setTitleAt(aboutPanel, resourceBundle.getString("CTL_About"));
    }

    private void initAuthor() {
        authorScrollPane = new JScrollPane();

        authorTextArea = new JTextArea();
        authorTextArea.setEditable(false);
        authorTextArea.setMargin(textAreaInsets);
        authorTextArea.setLineWrap(true);
        authorTextArea.setWrapStyleWord(true);
        authorTextArea.setText(getString("application.author"));
        authorTextArea.setCaretPosition(0);

        authorScrollPane.setViewportView(authorTextArea);
        tabbedPane.add(authorScrollPane);
        tabbedPane.setTitleAt(authorScrollPane, resourceBundle.getString("CTL_Author"));
    }

    private void initHeader() {
        JPanel headerPanel = new JPanel();
        JPanel headerTextPanel = new JPanel();

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(8, 15, 10, 15));

        headerTextPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.Y_AXIS));

        nameLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        versionLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        usesLabel.setFont(new java.awt.Font("Dialog", 0, 12));

        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(headerTextPanel, BorderLayout.CENTER);
        headerTextPanel.add(nameLabel);
        headerTextPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        headerTextPanel.add(versionLabel);
        headerTextPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        headerTextPanel.add(usesLabel);

        String javaVersion = System.getProperty("java.runtime.version");
        String javaName = System.getProperty("java.runtime.name");

        versionLabel.setText(getString("application.version") + ", " + getString("application.date"));

        String license = getLicenseString("license.name");
        String resourceString = resourceBundle.getString("CTL_License");
        license = String.format(resourceString, license);

        resourceString = resourceBundle.getString("CTL_Using");
        String uses = String.format(resourceString, javaName + " " + javaVersion);
        usesLabel.setText("<html>" + " " + uses);

        add(headerPanel, BorderLayout.NORTH);
    }

    private void initLicense() {
        licenseScrollPane = new JScrollPane();
        licenseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        licenseTextArea = new JTextArea();
        licenseTextArea.setMargin(textAreaInsets);
        licenseTextArea.setEditable(false);
        licenseTextArea.setText(getLicenseString("license.text"));
        licenseTextArea.setCaretPosition(0);

        licenseScrollPane.setViewportView(licenseTextArea);
        tabbedPane.add(licenseScrollPane);
        tabbedPane.setTitleAt(licenseScrollPane, resourceBundle.getString("CTL_DialogTitleLicense"));
    }

    private void initMain() {
        tabbedPane = new ATabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        initAbout();
        initProperties();
        initLicense();
        initAuthor();
        initThanks();
        initTranslation();
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.setTextAndMnemonic();
    }

    private void initProperties() {
        propertiesScrollPane = new JScrollPane();
        propertiesTable = new JTable();

        defaultTableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        propertiesTable.setModel(defaultTableModel);

        defaultTableModel.addColumn("");
        defaultTableModel.addColumn("");

        propertiesTable.getColumnModel().getColumn(1).setPreferredWidth(280);
        propertiesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        propertiesTable.setRowSelectionAllowed(false);

        propertiesScrollPane.setViewportView(propertiesTable);
        tabbedPane.add(propertiesScrollPane);
        tabbedPane.setTitleAt(propertiesScrollPane, resourceBundle.getString("CTL_Properties"));
    }

    private void initSystemProperties() {
        propertiesTable.getColumnModel().getColumn(0).setHeaderValue(resourceBundle.getString("CTL_Key"));
        propertiesTable.getColumnModel().getColumn(1).setHeaderValue(resourceBundle.getString("CTL_Value"));

        addSystemProperty(defaultTableModel, "user.country");
        addSystemProperty(defaultTableModel, "user.home");
        addSystemProperty(defaultTableModel, "user.language");
        addSystemProperty(defaultTableModel, "user.name");
        addSystemProperty(defaultTableModel, "user.timezone");

        defaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(defaultTableModel, "os.arch");
        addSystemProperty(defaultTableModel, "os.name");
        addSystemProperty(defaultTableModel, "os.version");

        defaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(defaultTableModel, "file.encoding.pkg");
        addSystemProperty(defaultTableModel, "file.encoding");
        addSystemProperty(defaultTableModel, "file.separator");
        addSystemProperty(defaultTableModel, "path.separator");

        defaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(defaultTableModel, "java.awt.graphicsenv");
        addSystemProperty(defaultTableModel, "java.awt.printerjob");
        addSystemProperty(defaultTableModel, "java.class.path");
        addSystemProperty(defaultTableModel, "java.class.version");
        addSystemProperty(defaultTableModel, "java.endorsed.dirs");
        addSystemProperty(defaultTableModel, "java.ext.dirs");
        addSystemProperty(defaultTableModel, "java.home");
        addSystemProperty(defaultTableModel, "java.io.tmpdir");
        addSystemProperty(defaultTableModel, "java.library.path");
        addSystemProperty(defaultTableModel, "java.runtime.name");
        addSystemProperty(defaultTableModel, "java.runtime.version");
        addSystemProperty(defaultTableModel, "java.specification.name");
        addSystemProperty(defaultTableModel, "java.specification.vendor");
        addSystemProperty(defaultTableModel, "java.specification.version");
        addSystemProperty(defaultTableModel, "java.vendor");
        addSystemProperty(defaultTableModel, "java.vendor.url.bug");
        addSystemProperty(defaultTableModel, "java.vendor.url");
        addSystemProperty(defaultTableModel, "java.version");
        addSystemProperty(defaultTableModel, "java.vm.info");
        addSystemProperty(defaultTableModel, "java.vm.name");
        addSystemProperty(defaultTableModel, "java.vm.specification.name");
        addSystemProperty(defaultTableModel, "java.vm.specification.vendor");
        addSystemProperty(defaultTableModel, "java.vm.specification.version");
        addSystemProperty(defaultTableModel, "java.vm.vendor");
        addSystemProperty(defaultTableModel, "java.vm.version");

        defaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(defaultTableModel, "sun.arch.data.model");
        addSystemProperty(defaultTableModel, "sun.boot.class.path");
        addSystemProperty(defaultTableModel, "sun.boot.library.path");
        addSystemProperty(defaultTableModel, "sun.cpu.endian");
        addSystemProperty(defaultTableModel, "sun.cpu.isalist");
        addSystemProperty(defaultTableModel, "sun.io.unicode.encoding");
        addSystemProperty(defaultTableModel, "sun.java.launcher");
        addSystemProperty(defaultTableModel, "sun.jnu.encoding");
        addSystemProperty(defaultTableModel, "sun.management.compiler");
        addSystemProperty(defaultTableModel, "sun.os.patch.level");
    }

    private void initThanks() {
        thanksScrollPane = new JScrollPane();

        thanksTextArea = new JTextArea();
        thanksTextArea.setMargin(textAreaInsets);
        thanksTextArea.setLineWrap(true);
        thanksTextArea.setWrapStyleWord(true);
        thanksTextArea.setEditable(false);
        thanksTextArea.setText(getString("application.thanks"));
        thanksTextArea.setCaretPosition(0);

        thanksScrollPane.setViewportView(thanksTextArea);
        tabbedPane.add(thanksScrollPane);
        tabbedPane.setTitleAt(thanksScrollPane, resourceBundle.getString("CTL_Thanks"));
    }

    private void initTranslation() {
        translationScrollPane = new JScrollPane();

        translationTextArea = new JTextArea();
        translationTextArea.setMargin(textAreaInsets);
        translationTextArea.setLineWrap(true);
        translationTextArea.setWrapStyleWord(true);
        translationTextArea.setEditable(false);
        translationTextArea.setText(getString("application.translation"));
        translationTextArea.setCaretPosition(0);

        translationScrollPane.setViewportView(translationTextArea);
        tabbedPane.add(translationScrollPane);
        tabbedPane.setTitleAt(translationScrollPane, resourceBundle.getString("CTL_Translation"));
    }

    private String getString(String aKey) {
        String string = "";

        try {
            string = applicationResourceBundle.getString(aKey);
        } catch (java.util.MissingResourceException e) {
        }

        return string;
    }

    private String getLicenseString(String aKey) {
        String string = "";

        try {
            string = licenseResourceBundle.getString(aKey);
        } catch (java.util.MissingResourceException e) {
        } catch (NullPointerException e) {
        }

        return string;
    }
}
