package se.trixon.almond;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Monitor {

    private InputOutput io;
    private OutputWriter out;
    private OutputWriter err;
    private boolean useTimestamps = true;

    public Monitor(String tag, boolean newIO, boolean useTimestamps) {
        this.useTimestamps = useTimestamps;
        io = IOProvider.getDefault().getIO(tag, newIO);
    }

    public void errln(String aMessage) {
        err = io.getErr();
        printDate(err);
        err.println(aMessage);
        err.close();
    }

    public void outln(String aMessage) {
        out = io.getOut();
        printDate(out);
        out.println(aMessage);
        out.close();
    }

    public void outlnEvent(String aMessage) {
        out = io.getOut();
        printDate(out);
        out.println(aMessage);
        out.close();
    }

    public boolean isUseTimestamps() {
        return useTimestamps;
    }

    public void setUseTimestamps(boolean useTimestamps) {
        this.useTimestamps = useTimestamps;
    }

    private void printDate(OutputWriter anOutputWriter) {
        if (useTimestamps) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
            Calendar calendar = Calendar.getInstance();
            anOutputWriter.print(sdf.format(calendar.getTime()) + ": ");
        }
    }
}
