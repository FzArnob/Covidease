package kawa;

import android.support.p003v7.widget.helper.ItemTouchHelper;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TelnetInputStream extends FilterInputStream {
    static final int SB_IAC = 400;
    protected byte[] buf = new byte[512];
    Telnet connection;
    int count;
    int pos;
    int state = 0;
    int subCommandLength = 0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TelnetInputStream(InputStream in, Telnet conn) throws IOException {
        super(in);
        this.connection = conn;
    }

    public int read() throws IOException {
        StringBuilder sb;
        while (true) {
            if (this.pos >= this.count) {
                int avail = this.in.available();
                if (avail <= 0) {
                    avail = 1;
                } else if (avail > this.buf.length - this.subCommandLength) {
                    avail = this.buf.length - this.subCommandLength;
                }
                int avail2 = this.in.read(this.buf, this.subCommandLength, avail);
                this.pos = this.subCommandLength;
                this.count = avail2;
                if (avail2 <= 0) {
                    return -1;
                }
            }
            byte[] bArr = this.buf;
            int i = this.pos;
            int i2 = i + 1;
            this.pos = i2;
            int ch = bArr[i] & 255;
            if (this.state == 0) {
                if (ch != 255) {
                    return ch;
                }
                this.state = 255;
            } else if (this.state == 255) {
                if (ch == 255) {
                    this.state = 0;
                    return 255;
                } else if (ch == 251 || ch == 252 || ch == 253 || ch == 254 || ch == 250) {
                    this.state = ch;
                } else if (ch == 244) {
                    System.err.println("Interrupt Process");
                    this.state = 0;
                } else if (ch == 236) {
                    return -1;
                } else {
                    this.state = 0;
                }
            } else if (this.state == 251 || this.state == 252 || this.state == 253 || this.state == 254) {
                this.connection.handle(this.state, ch);
                this.state = 0;
            } else if (this.state == 250) {
                if (ch == 255) {
                    this.state = 400;
                } else {
                    byte[] bArr2 = this.buf;
                    int i3 = this.subCommandLength;
                    int i4 = i3 + 1;
                    this.subCommandLength = i4;
                    bArr2[i3] = (byte) ch;
                }
            } else if (this.state != 400) {
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb.append("Bad state ").append(this.state).toString());
            } else if (ch == 255) {
                byte[] bArr3 = this.buf;
                int i5 = this.subCommandLength;
                int i6 = i5 + 1;
                this.subCommandLength = i6;
                bArr3[i5] = (byte) ch;
                this.state = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            } else if (ch == 240) {
                this.connection.subCommand(this.buf, 0, this.subCommandLength);
                this.state = 0;
                this.subCommandLength = 0;
            } else {
                this.state = 0;
                this.subCommandLength = 0;
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte ch;
        byte[] b = bArr;
        int offset = i;
        int length = i2;
        if (length <= 0) {
            return 0;
        }
        int done = 0;
        if (this.state != 0 || this.pos >= this.count) {
            int ch2 = read();
            if (ch2 < 0) {
                return ch2;
            }
            int i3 = offset;
            offset++;
            b[i3] = (byte) ch2;
            done = 0 + 1;
        }
        if (this.state == 0) {
            while (this.pos < this.count && done < length && (ch = this.buf[this.pos]) != -1) {
                int i4 = offset;
                offset++;
                b[i4] = ch;
                done++;
                this.pos++;
            }
        }
        return done;
    }
}
