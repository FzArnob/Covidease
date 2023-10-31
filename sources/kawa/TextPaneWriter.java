package kawa;

import java.io.Writer;
import javax.swing.text.AttributeSet;

/* compiled from: ReplPaneOutPort */
class TextPaneWriter extends Writer {
    ReplDocument document;
    String str = "";
    AttributeSet style;

    public TextPaneWriter(ReplDocument document2, AttributeSet style2) {
        this.document = document2;
        this.style = style2;
    }

    public synchronized void write(int i) {
        StringBuilder sb;
        int x = i;
        synchronized (this) {
            new StringBuilder();
            this.str = sb.append(this.str).append((char) x).toString();
            if (x == 10) {
                flush();
            }
        }
    }

    public void write(String str2) {
        this.document.write(str2, this.style);
    }

    public synchronized void write(char[] cArr, int i, int i2) {
        String str2;
        char[] data = cArr;
        int off = i;
        int len = i2;
        synchronized (this) {
            flush();
            if (len != 0) {
                new String(data, off, len);
                write(str2);
            }
        }
    }

    public synchronized void flush() {
        synchronized (this) {
            String s = this.str;
            if (!s.equals("")) {
                this.str = "";
                write(s);
            }
        }
    }

    public void close() {
        flush();
    }
}
