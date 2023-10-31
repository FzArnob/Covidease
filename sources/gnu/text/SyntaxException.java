package gnu.text;

import java.io.PrintWriter;

public class SyntaxException extends Exception {
    String header;
    public int maxToPrint = 10;
    SourceMessages messages;

    public SyntaxException(SourceMessages messages2) {
        this.messages = messages2;
    }

    public SyntaxException(String header2, SourceMessages messages2) {
        this.header = header2;
        this.messages = messages2;
    }

    public final String getHeader() {
        return this.header;
    }

    public final void setHeader(String header2) {
        String str = header2;
        this.header = str;
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void printAll(PrintWriter printWriter, int i) {
        PrintWriter out = printWriter;
        int max = i;
        if (this.header != null) {
            out.println(this.header);
        }
        this.messages.printAll(out, max);
    }

    public void clear() {
        this.messages.clear();
    }

    public String getMessage() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        if (this.header != null) {
            StringBuffer append = buffer.append(this.header);
        }
        int max = this.maxToPrint;
        SourceError sourceError = this.messages.firstError;
        while (true) {
            SourceError err = sourceError;
            if (err == null) {
                break;
            }
            max--;
            if (max < 0) {
                break;
            }
            StringBuffer append2 = buffer.append(10);
            StringBuffer append3 = buffer.append(err);
            sourceError = err.next;
        }
        return buffer.toString();
    }
}
