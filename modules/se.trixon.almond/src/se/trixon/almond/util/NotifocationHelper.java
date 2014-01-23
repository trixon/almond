package se.trixon.almond.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import org.openide.awt.Notification;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NotifocationHelper {

    private static final ScheduledExecutorService sSCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    public static void displayTextNotification(String title, String text, int autoCloseDelayMilliSeconds) {
        ImageIcon icon = ImageUtilities.loadImageIcon("se/trixon/almond/res/null.png", true);
        final Notification notification = NotificationDisplayer.getDefault().notify(title, icon, text, null);

        if (autoCloseDelayMilliSeconds > 0) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    notification.clear();
                }
            };
            sSCHEDULED_EXECUTOR_SERVICE.schedule(task, autoCloseDelayMilliSeconds, TimeUnit.MILLISECONDS);
        }
    }
}
