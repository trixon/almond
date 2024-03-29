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
package se.trixon.almond.nbp.dialogs;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Patrik Karlström
 */
public class NbMessage {

    public static void error(String title, String message) {
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(message, NotifyDescriptor.ERROR_MESSAGE);
        notifyDescriptor.setTitle(title);
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }

    public static void information(String title, String message) {
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(message, NotifyDescriptor.INFORMATION_MESSAGE);
        notifyDescriptor.setTitle(title);
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }

    public static void warning(String title, String message) {
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(message, NotifyDescriptor.WARNING_MESSAGE);
        notifyDescriptor.setTitle(title);
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }
}
