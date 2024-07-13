package se.trixon.almond.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringWriter;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputListener;
import org.openide.windows.OutputWriter;

public final class TrivialIOProvider extends IOProvider {

    private static final Reader in = new BufferedReader(new InputStreamReader(System.in)) {
        @Override
        public void close() {
            // do nothing, prevent blocking between System.in.read() and System.in.close();
        }
    };

    private static final PrintStream out = System.out;
    private static final PrintStream err = System.err;

    public TrivialIOProvider() {
    }

    @Override
    public InputOutput getIO(String name, boolean newIO) {
        return new TrivialIO(name);
    }

    @Override
    public OutputWriter getStdOut() {
        return new TrivialOW(out, "stdout"); // NOI18N
    }

    @SuppressWarnings("deprecation")
    private final class TrivialIO implements InputOutput {

        private final String name;

        public TrivialIO(String name) {
            this.name = name;
        }

        @Override
        public Reader getIn() {
            return in;
        }

        @Override
        public OutputWriter getOut() {
            return new TrivialOW(out, name);
        }

        @Override
        public OutputWriter getErr() {
            return new TrivialOW(err, name);
        }

        @Override
        public Reader flushReader() {
            return getIn();
        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public boolean isErrSeparated() {
            return false;
        }

        @Override
        public boolean isFocusTaken() {
            return false;
        }

        @Override
        public void closeInputOutput() {
        }

        @Override
        public void select() {
        }

        @Override
        public void setErrSeparated(boolean value) {
        }

        @Override
        public void setErrVisible(boolean value) {
        }

        @Override
        public void setFocusTaken(boolean value) {
        }

        @Override
        public void setInputVisible(boolean value) {
        }

        @Override
        public void setOutputVisible(boolean value) {
        }

    }

    private static final class TrivialOW extends OutputWriter {

        private static int count = 0;
        private final String name;
        private final PrintStream stream;

        public TrivialOW(PrintStream stream, String name) {
            // XXX using super(new PrintWriter(stream)) does not seem to work for some reason!
            super(new StringWriter());
            this.stream = stream;
            if (name != null) {
                this.name = name;
            } else {
                this.name = "anon-" + ++count; // NOI18N
            }
        }

        private void prefix(boolean hyperlink) {
            if (hyperlink) {
                stream.print("[" + name + "]* "); // NOI18N
            } else {
                stream.print("[" + name + "]  "); // NOI18N
            }
        }

        @Override
        public void println(String s, OutputListener l) throws IOException {
            prefix(l != null);
            stream.println(s);
        }

        @Override
        public void reset() throws IOException {
        }

        @Override
        public void println(float x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(double x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println() {
            prefix(false);
            stream.println();
        }

        @Override
        public void println(Object x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(int x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(char x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(long x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(char[] x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(boolean x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void println(String x) {
            prefix(false);
            stream.println(x);
        }

        @Override
        public void write(int c) {
            stream.write(c);
        }

        @Override
        public void write(char[] buf, int off, int len) {
            String s = new String(buf, off, len);
            if (s.endsWith("\n")) {
                println(s.substring(0, s.length() - 1));
            } else {
                try {
                    stream.write(s.getBytes());
                } catch (IOException x) {
                }
            }
        }

        @Override
        public void write(String s, int off, int len) {
            s = s.substring(off, off + len);
            if (s.endsWith("\n")) {
                println(s.substring(0, s.length() - 1));
            } else {
                try {
                    stream.write(s.getBytes());
                } catch (IOException x) {
                }
            }
        }

    }
}
