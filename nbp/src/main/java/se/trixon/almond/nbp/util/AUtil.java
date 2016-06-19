/* 
 * Copyright 2016 Patrik Karlsson.
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

import java.util.Collection;
import java.util.TreeSet;
import javax.swing.Action;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AUtil {

    /**
     *
     * @param string
     * @return 1,2,3,7,8,9 from "1-3,7-9"
     */
    public static TreeSet<Integer> convertStringToIntSet(String string) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        String[] args = string.split(",");

        for (String arg : args) {
            try {
                int value = Integer.valueOf(arg.trim());
                treeSet.add(value);
            } catch (java.lang.NumberFormatException e) {
                String[] interval = arg.split("-");
                int start = Math.min(Integer.valueOf(interval[0].trim()), Integer.valueOf(interval[1].trim()));
                int stop = Math.max(Integer.valueOf(interval[0].trim()), Integer.valueOf(interval[1].trim()));
                for (int j = start; j <= stop; j++) {
                    treeSet.add(j);
                }
            }
        }

        return treeSet;
    }

    public static Action getActionInstance(String aFolder, String aFile) {
        Lookup pathLookup = Lookups.forPath(aFolder);
        final String INSTANCE_FILE = aFile;
        Lookup.Template<Action> actionTemplate = new Lookup.Template<Action>(Action.class, aFolder + INSTANCE_FILE, null);
        Lookup.Result<Action> lookupResult = pathLookup.lookup(actionTemplate);
        Collection<? extends Action> foundActions = lookupResult.allInstances();

        Action resultAction = null;

        for (Action action : foundActions) {
            resultAction = action;
        }

        return resultAction;
    }
}
