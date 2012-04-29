package se.trixon.almond.util;

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

  public static TreeSet<Integer> getSet(String aString) {
    TreeSet<Integer> treeSet = new TreeSet<Integer>();
    String[] arg = aString.split(",");
    for (int i = 0; i < arg.length; i++) {
      try {
        int value = Integer.valueOf(arg[i].trim());
        treeSet.add(value);
      } catch (java.lang.NumberFormatException e) {
        String[] interval = arg[i].split("-");
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
