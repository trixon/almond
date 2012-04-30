package se.trixon.almond.util;

import java.util.Date;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AMonitor {

  private InputOutput io;
  private OutputWriter out;
  private OutputWriter err;
  private boolean useTimestamps = true;

  public AMonitor(String tag, boolean newIO, boolean useTimestamps) {
    this.useTimestamps = useTimestamps;
    io = IOProvider.getDefault().getIO(tag, newIO);
    out = io.getOut();
    err = io.getErr();
  }

  public void errln(String aMessage) {
    printDate(err);
    err.println(aMessage);
  }

  public void outln(String aMessage) {
    printDate(out);
    out.println(aMessage);
  }

  public boolean isUseTimestamps() {
    return useTimestamps;
  }

  public void setUseTimestamps(boolean useTimestamps) {
    this.useTimestamps = useTimestamps;
  }

  private void printDate(OutputWriter anOutputWriter) {
    Date date = new Date();
    if (useTimestamps) {
      anOutputWriter.print(date + ": ");
    }
  }
}
