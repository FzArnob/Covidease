package gnu.mapping;

import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class TtyInPort extends InPort {
    protected boolean promptEmitted;
    protected Procedure prompter;
    protected OutPort tie;

    public Procedure getPrompter() {
        return this.prompter;
    }

    public void setPrompter(Procedure prompter2) {
        Procedure procedure = prompter2;
        this.prompter = procedure;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TtyInPort(InputStream in, Path name, OutPort tie2) {
        super(in, name);
        setConvertCR(true);
        this.tie = tie2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TtyInPort(Reader in, Path name, OutPort tie2) {
        super(in, name);
        setConvertCR(true);
        this.tie = tie2;
    }

    public int fill(int len) throws IOException {
        int count = this.f247in.read(this.buffer, this.pos, len);
        if (this.tie != null && count > 0) {
            this.tie.echo(this.buffer, this.pos, count);
        }
        return count;
    }

    public void emitPrompt(String prompt) throws IOException {
        this.tie.print(prompt);
        this.tie.flush();
        this.tie.clearBuffer();
    }

    public void lineStart(boolean revisited) throws IOException {
        Throwable th;
        StringBuilder sb;
        String string;
        if (!revisited) {
            if (this.tie != null) {
                this.tie.freshLine();
            }
            if (this.prompter != null) {
                try {
                    Object prompt = this.prompter.apply1(this);
                    if (prompt != null && (string = prompt.toString()) != null && string.length() > 0) {
                        emitPrompt(string);
                        this.promptEmitted = true;
                    }
                } catch (Throwable th2) {
                    Throwable ex = th2;
                    Throwable th3 = th;
                    new StringBuilder();
                    new IOException(sb.append("Error when evaluating prompt:").append(ex).toString());
                    throw th3;
                }
            }
        }
    }

    public int read() throws IOException {
        if (this.tie != null) {
            this.tie.flush();
        }
        int ch = super.read();
        if (ch < 0) {
            if (this.promptEmitted && (this.tie != null)) {
                this.tie.println();
            }
        }
        this.promptEmitted = false;
        return ch;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        char[] cbuf = cArr;
        int off = i;
        int len = i2;
        if (this.tie != null) {
            this.tie.flush();
        }
        int count = super.read(cbuf, off, len);
        if (count < 0) {
            if (this.promptEmitted && (this.tie != null)) {
                this.tie.println();
            }
        }
        this.promptEmitted = false;
        return count;
    }
}
