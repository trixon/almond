package se.trixon.almond;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class FileHelper {

    public static String convertStreamToString(java.io.InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }
}
