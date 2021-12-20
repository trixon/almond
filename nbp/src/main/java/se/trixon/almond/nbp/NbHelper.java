/*
 * Copyright 2021 Patrik Karlström.
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
package se.trixon.almond.nbp;

import org.netbeans.api.progress.ProgressHandle;

/**
 *
 * @author Patrik Karlström
 */
public class NbHelper {

    public static ProgressHandle createAndStartProgressHandle(String displayName, boolean indeterminate) {
        var progressHandle = ProgressHandle.createHandle(displayName);
        progressHandle.start();
        if (indeterminate) {
            progressHandle.switchToIndeterminate();
        }

        return progressHandle;
    }

}
