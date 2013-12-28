package se.trixon.almond.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "se.trixon.almond.actions.QuitAction"
)
@ActionRegistration(
        iconBase = "se/trixon/almond/actions/resources/application-exit.png",
        displayName = "#CTL_QuitAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 2700),
    @ActionReference(path = "Shortcuts", name = "D-Q")
})
@Messages("CTL_QuitAction=Quit")
public final class QuitAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        LifecycleManager.getDefault().exit();
    }
}
