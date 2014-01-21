package se.trixon.almond.dialogs;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class FileChooserPanel extends javax.swing.JPanel {
    private JFileChooser mFileChooser;
    private int mMode;

    /**
     * Creates new form FileChooserPanel
     */
    public FileChooserPanel() {
        initComponents();
        init();
    }

    public JFileChooser getFileChooser() {
        return mFileChooser;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public JButton getButton() {
        return mButton;
    }

    public JLabel getLabel() {
        return mLabel;
    }

    public String getPath() {
        return mTextField.getText();
    }

    public JTextField getTextField() {
        return mTextField;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mButton.setEnabled(enabled);
        mTextField.setEnabled(enabled);
    }

    public void setPath(String path) {
        mTextField.setText(path);
    }

    private void init() {
        mTextField.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    mTextField.setText(droppedFiles.get(0).getAbsolutePath());
                } catch (UnsupportedFlavorException | IOException ex) {
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mLabel = new javax.swing.JLabel();
        mTextField = new javax.swing.JTextField();
        mButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(mLabel, org.openide.util.NbBundle.getMessage(FileChooserPanel.class, "FileChooserPanel.mLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(mButton, "…"); // NOI18N
        mButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mButton)
                .addGap(2, 2, 2))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonActionPerformed
        mFileChooser = new JFileChooser(mTextField.getText());
        mFileChooser.setFileSelectionMode(mMode);
        mFileChooser.showOpenDialog(mButton.getTopLevelAncestor());

        if (mFileChooser.getSelectedFile() != null) {
            mTextField.setText(mFileChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_mButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mButton;
    private javax.swing.JLabel mLabel;
    private javax.swing.JTextField mTextField;
    // End of variables declaration//GEN-END:variables
}
