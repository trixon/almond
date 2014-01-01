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

    private OutputWriter mErr;
    private final InputOutput mIo;
    private OutputWriter mOut;
    private boolean mUseTimestamps = true;

    public Monitor(String tag, boolean newIO, boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
        mIo = IOProvider.getDefault().getIO(tag, newIO);
    }

    public void errln(String message) {
        mErr = mIo.getErr();
        printDate(mErr);
        mErr.println(message);
        mErr.close();
    }

    public boolean isUseTimestamps() {
        return mUseTimestamps;
    }

    public void outln(String message) {
        mOut = mIo.getOut();
        printDate(mOut);
        mOut.println(message);
        mOut.close();
    }

    public void outlnEvent(String message) {
        mOut = mIo.getOut();
        printDate(mOut);
        mOut.println(message);
        mOut.close();
    }

    public void setUseTimestamps(boolean useTimestamps) {
        mUseTimestamps = useTimestamps;
    }

    private void printDate(OutputWriter anOutputWriter) {
        if (mUseTimestamps) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
            Calendar calendar = Calendar.getInstance();
            anOutputWriter.print(sdf.format(calendar.getTime()) + ": ");
        }
    }
}
