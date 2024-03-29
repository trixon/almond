/*
 * Copyright 2023 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.almond.nbp.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.ImageUtilities;

/**
 *
 *
 *
 * @author Patrik Karlström
 *
 */
public class NotifocationHelper {

    private static final ScheduledExecutorService sSCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    public static void displayTextNotification(String title, String text, int autoCloseDelayMilliSeconds) {

        var icon = ImageUtilities.loadImageIcon("se/trixon/almond/nbp/res/null.png", true);
        var notification = NotificationDisplayer.getDefault().notify(title, icon, text, null);

        if (autoCloseDelayMilliSeconds > 0) {
            Runnable task = notification::clear;

            sSCHEDULED_EXECUTOR_SERVICE.schedule(task, autoCloseDelayMilliSeconds, TimeUnit.MILLISECONDS);
        }
    }
}
