package gnu.mapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class LogWriter extends FilterWriter {
    private Writer log;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogWriter(Writer out) {
        super(out);
    }

    public final Writer getLogFile() {
        return this.log;
    }

    public void setLogFile(Writer log2) {
        Writer writer = log2;
        this.log = writer;
    }

    public void setLogFile(String name) throws IOException {
        Writer writer;
        Writer writer2;
        Writer writer3;
        Writer writer4 = writer;
        new FileWriter(name);
        new BufferedWriter(writer3);
        new PrintWriter(writer2);
        this.log = writer4;
    }

    public void closeLogFile() throws IOException {
        if (this.log != null) {
            this.log.close();
        }
        this.log = null;
    }

    public void write(int i) throws IOException {
        int c = i;
        if (this.log != null) {
            this.log.write(c);
        }
        super.write(c);
    }

    public void echo(char[] cArr, int i, int i2) throws IOException {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.log != null) {
            this.log.write(buf, off, len);
        }
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.log != null) {
            this.log.write(buf, off, len);
        }
        super.write(buf, off, len);
    }

    public void write(String str, int i, int i2) throws IOException {
        String str2 = str;
        int off = i;
        int len = i2;
        if (this.log != null) {
            this.log.write(str2, off, len);
        }
        super.write(str2, off, len);
    }

    public void flush() throws IOException {
        if (this.log != null) {
            this.log.flush();
        }
        super.flush();
    }

    public void close() throws IOException {
        if (this.log != null) {
            this.log.close();
        }
        super.close();
    }
}
