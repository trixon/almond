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
import se.trixon.almond.swing.ATabbedPane;
import se.trixon.almond.swing.UriLabel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutPanel extends JPanel {

    private final ResourceBundle mApplicationResourceBundle;
    private JScrollPane mAuthorScrollPane;
    private JTextArea mAuthorTextArea;
    private JLabel mCopyrightLabel;
    private DefaultTableModel mDefaultTableModel;
    private JLabel mDescriptionLabel;
    private final ResourceBundle mLicenseResourceBundle;
    private JScrollPane mLicenseScrollPane;
    private JTextArea mLicenseTextArea;
    private final JLabel mLogoLabel = new JLabel();
    private final JLabel mNameLabel = new JLabel();

    private JPanel mPanel;
    private JScrollPane mPropertiesScrollPane;
    private JTable mPropertiesTable;
    private final ResourceBundle mResourceBundle = NbBundle.getBundle(AboutPanel.class);
    private ATabbedPane mTabbedPane;
    private final Insets mTextAreaInsets = new Insets(10, 10, 10, 10);
    private JScrollPane mThanksScrollPane;
    private JTextArea mThanksTextArea;
    private JScrollPane mTranslationScrollPane;
    private JTextArea mTranslationTextArea;
    private UriLabel mUriLabel;
    private final JLabel mUsesLabel = new JLabel();
    private final JLabel mVersionLabel = new JLabel();

    public AboutPanel(ResourceBundle resourceBundle, ImageIcon imageIcon, ResourceBundle licenseResourceBundle) {
        mApplicationResourceBundle = resourceBundle;
        mLicenseResourceBundle = licenseResourceBundle;
        mLogoLabel.setIcon(imageIcon);
        init();
    }

    private void addSystemProperty(DefaultTableModel tableModel, String key) {
        tableModel.addRow(new Object[]{key, System.getProperty(key)});
    }

    private String getLicenseString(String key) {
        String string = "";

        try {
            string = mLicenseResourceBundle.getString(key);
        } catch (java.util.MissingResourceException | NullPointerException e) {
        }

        return string;
    }

    private String getString(String key) {
        String string = "";

        try {
            string = mApplicationResourceBundle.getString(key);
        } catch (java.util.MissingResourceException e) {
        }

        return string;
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
        mPanel = new JPanel();
        mPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        Font font = new java.awt.Font("Dialog", 0, 14);

        mUriLabel = new UriLabel();
        mDescriptionLabel = new JLabel();
        mCopyrightLabel = new JLabel();

        mDescriptionLabel.setFont(font);
        mCopyrightLabel.setFont(font);
        mUriLabel.setFont(font);

        mCopyrightLabel.setText(getString("application.copyright"));
        mUriLabel.setText(getString("application.uriText"));
        mNameLabel.setText(getString("application.title"));
        mDescriptionLabel.setText(getString("application.description"));
        mUriLabel.setURI(getString("application.uri"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel aboutInfoPanel = new JPanel(gridBagLayout);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        gridBagLayout.setConstraints(mDescriptionLabel, gridBagConstraints);
        aboutInfoPanel.add(mDescriptionLabel, gridBagConstraints);

        gridBagLayout.setConstraints(mCopyrightLabel, gridBagConstraints);
        aboutInfoPanel.add(mCopyrightLabel, gridBagConstraints);

        gridBagConstraints.ipady = 8;

        gridBagLayout.setConstraints(mUriLabel, gridBagConstraints);
        aboutInfoPanel.add(mUriLabel, gridBagConstraints);

        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.add(Box.createVerticalGlue());
        mPanel.add(aboutInfoPanel);
        mPanel.add(Box.createVerticalGlue());

        mPanel.setName("about");
        mTabbedPane.add(mPanel);
        mTabbedPane.setTitleAt(mPanel, mResourceBundle.getString("CTL_About"));
    }

    private void initAuthor() {
        mAuthorScrollPane = new JScrollPane();

        mAuthorTextArea = new JTextArea();
        mAuthorTextArea.setEditable(false);
        mAuthorTextArea.setMargin(mTextAreaInsets);
        mAuthorTextArea.setLineWrap(true);
        mAuthorTextArea.setWrapStyleWord(true);
        mAuthorTextArea.setText(getString("application.author"));
        mAuthorTextArea.setCaretPosition(0);

        mAuthorScrollPane.setViewportView(mAuthorTextArea);
        mTabbedPane.add(mAuthorScrollPane);
        mTabbedPane.setTitleAt(mAuthorScrollPane, mResourceBundle.getString("CTL_Author"));
    }

    private void initHeader() {
        JPanel headerTextPanel = new JPanel();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(8, 15, 10, 15));

        headerTextPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.Y_AXIS));

        mNameLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        mVersionLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        mUsesLabel.setFont(new java.awt.Font("Dialog", 0, 12));

        headerPanel.add(mLogoLabel, BorderLayout.WEST);
        headerPanel.add(headerTextPanel, BorderLayout.CENTER);
        headerTextPanel.add(mNameLabel);
        headerTextPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        headerTextPanel.add(mVersionLabel);
        headerTextPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        headerTextPanel.add(mUsesLabel);

        mVersionLabel.setText(String.format("%s, %s", getString("application.version"), getString("application.date")));
        String javaVersion = System.getProperty("java.runtime.version");
        String javaName = System.getProperty("java.runtime.name");
        String uses = NbBundle.getMessage(AboutAction.class, "CTL_Using", javaName, javaVersion);

        mUsesLabel.setText(uses);

        add(headerPanel, BorderLayout.NORTH);
    }

    private void initLicense() {
        mLicenseScrollPane = new JScrollPane();
        mLicenseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        mLicenseTextArea = new JTextArea();
        mLicenseTextArea.setMargin(mTextAreaInsets);
        mLicenseTextArea.setEditable(false);
        mLicenseTextArea.setText(getLicenseString("license.text"));
        mLicenseTextArea.setCaretPosition(0);

        mLicenseScrollPane.setViewportView(mLicenseTextArea);
        mTabbedPane.add(mLicenseScrollPane);
        mTabbedPane.setTitleAt(mLicenseScrollPane, mResourceBundle.getString("CTL_DialogTitleLicense"));
    }

    private void initMain() {
        mTabbedPane = new ATabbedPane();
        mTabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        initAbout();
        initProperties();
        initLicense();
        initAuthor();
        initThanks();
        initTranslation();
        add(mTabbedPane, BorderLayout.CENTER);
        mTabbedPane.setTextAndMnemonic();
    }

    private void initProperties() {
        mPropertiesScrollPane = new JScrollPane();
        mPropertiesTable = new JTable();

        mDefaultTableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mPropertiesTable.setModel(mDefaultTableModel);

        mDefaultTableModel.addColumn("");
        mDefaultTableModel.addColumn("");

        mPropertiesTable.getColumnModel().getColumn(1).setPreferredWidth(280);
        mPropertiesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        mPropertiesTable.setRowSelectionAllowed(false);

        mPropertiesScrollPane.setViewportView(mPropertiesTable);
        mTabbedPane.add(mPropertiesScrollPane);
        mTabbedPane.setTitleAt(mPropertiesScrollPane, mResourceBundle.getString("CTL_Properties"));
    }

    private void initSystemProperties() {
        mPropertiesTable.getColumnModel().getColumn(0).setHeaderValue(mResourceBundle.getString("CTL_Key"));
        mPropertiesTable.getColumnModel().getColumn(1).setHeaderValue(mResourceBundle.getString("CTL_Value"));

        addSystemProperty(mDefaultTableModel, "user.country");
        addSystemProperty(mDefaultTableModel, "user.home");
        addSystemProperty(mDefaultTableModel, "user.language");
        addSystemProperty(mDefaultTableModel, "user.name");
        addSystemProperty(mDefaultTableModel, "user.timezone");

        mDefaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(mDefaultTableModel, "os.arch");
        addSystemProperty(mDefaultTableModel, "os.name");
        addSystemProperty(mDefaultTableModel, "os.version");

        mDefaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(mDefaultTableModel, "file.encoding.pkg");
        addSystemProperty(mDefaultTableModel, "file.encoding");
        addSystemProperty(mDefaultTableModel, "file.separator");
        addSystemProperty(mDefaultTableModel, "path.separator");

        mDefaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(mDefaultTableModel, "java.awt.graphicsenv");
        addSystemProperty(mDefaultTableModel, "java.awt.printerjob");
        addSystemProperty(mDefaultTableModel, "java.class.path");
        addSystemProperty(mDefaultTableModel, "java.class.version");
        addSystemProperty(mDefaultTableModel, "java.endorsed.dirs");
        addSystemProperty(mDefaultTableModel, "java.ext.dirs");
        addSystemProperty(mDefaultTableModel, "java.home");
        addSystemProperty(mDefaultTableModel, "java.io.tmpdir");
        addSystemProperty(mDefaultTableModel, "java.library.path");
        addSystemProperty(mDefaultTableModel, "java.runtime.name");
        addSystemProperty(mDefaultTableModel, "java.runtime.version");
        addSystemProperty(mDefaultTableModel, "java.specification.name");
        addSystemProperty(mDefaultTableModel, "java.specification.vendor");
        addSystemProperty(mDefaultTableModel, "java.specification.version");
        addSystemProperty(mDefaultTableModel, "java.vendor");
        addSystemProperty(mDefaultTableModel, "java.vendor.url.bug");
        addSystemProperty(mDefaultTableModel, "java.vendor.url");
        addSystemProperty(mDefaultTableModel, "java.version");
        addSystemProperty(mDefaultTableModel, "java.vm.info");
        addSystemProperty(mDefaultTableModel, "java.vm.name");
        addSystemProperty(mDefaultTableModel, "java.vm.specification.name");
        addSystemProperty(mDefaultTableModel, "java.vm.specification.vendor");
        addSystemProperty(mDefaultTableModel, "java.vm.specification.version");
        addSystemProperty(mDefaultTableModel, "java.vm.vendor");
        addSystemProperty(mDefaultTableModel, "java.vm.version");

        mDefaultTableModel.addRow(new Object[]{"", ""});
        addSystemProperty(mDefaultTableModel, "sun.arch.data.model");
        addSystemProperty(mDefaultTableModel, "sun.boot.class.path");
        addSystemProperty(mDefaultTableModel, "sun.boot.library.path");
        addSystemProperty(mDefaultTableModel, "sun.cpu.endian");
        addSystemProperty(mDefaultTableModel, "sun.cpu.isalist");
        addSystemProperty(mDefaultTableModel, "sun.io.unicode.encoding");
        addSystemProperty(mDefaultTableModel, "sun.java.launcher");
        addSystemProperty(mDefaultTableModel, "sun.jnu.encoding");
        addSystemProperty(mDefaultTableModel, "sun.management.compiler");
        addSystemProperty(mDefaultTableModel, "sun.os.patch.level");
    }

    private void initThanks() {
        mThanksScrollPane = new JScrollPane();

        mThanksTextArea = new JTextArea();
        mThanksTextArea.setMargin(mTextAreaInsets);
        mThanksTextArea.setLineWrap(true);
        mThanksTextArea.setWrapStyleWord(true);
        mThanksTextArea.setEditable(false);
        mThanksTextArea.setText(getString("application.thanks"));
        mThanksTextArea.setCaretPosition(0);

        mThanksScrollPane.setViewportView(mThanksTextArea);
        mTabbedPane.add(mThanksScrollPane);
        mTabbedPane.setTitleAt(mThanksScrollPane, mResourceBundle.getString("CTL_Thanks"));
    }

    private void initTranslation() {
        mTranslationScrollPane = new JScrollPane();

        mTranslationTextArea = new JTextArea();
        mTranslationTextArea.setMargin(mTextAreaInsets);
        mTranslationTextArea.setLineWrap(true);
        mTranslationTextArea.setWrapStyleWord(true);
        mTranslationTextArea.setEditable(false);
        mTranslationTextArea.setText(getString("application.translation"));
        mTranslationTextArea.setCaretPosition(0);

        mTranslationScrollPane.setViewportView(mTranslationTextArea);
        mTabbedPane.add(mTranslationScrollPane);
        mTabbedPane.setTitleAt(mTranslationScrollPane, mResourceBundle.getString("CTL_Translation"));
    }
}
