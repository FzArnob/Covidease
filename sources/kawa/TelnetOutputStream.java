package kawa;

import android.support.p003v7.widget.helper.ItemTouchHelper;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TelnetOutputStream extends FilterOutputStream {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TelnetOutputStream(OutputStream out) {
        super(out);
    }

    public void write(int i) throws IOException {
        int value = i;
        if (value == 255) {
            this.out.write(value);
        }
        this.out.write(value);
    }

    public void write(byte[] bArr) throws IOException {
        byte[] b = bArr;
        write(b, 0, b.length);
    }

    public void write(byte[] bArr, int i, int len) throws IOException {
        byte[] b = bArr;
        int off = i;
        int limit = off + len;
        for (int i2 = off; i2 < limit; i2++) {
            if (b[i2] == -1) {
                this.out.write(b, off, (i2 + 1) - off);
                off = i2;
            }
        }
        this.out.write(b, off, limit - off);
    }

    public void writeCommand(int code) throws IOException {
        this.out.write(255);
        this.out.write(code);
    }

    public final void writeCommand(int code, int option) throws IOException {
        this.out.write(255);
        this.out.write(code);
        this.out.write(option);
    }

    public final void writeDo(int option) throws IOException {
        writeCommand(Telnet.f261DO, option);
    }

    public final void writeDont(int option) throws IOException {
        writeCommand(Telnet.DONT, option);
    }

    public final void writeWill(int option) throws IOException {
        writeCommand(Telnet.WILL, option);
    }

    public final void writeWont(int option) throws IOException {
        writeCommand(Telnet.WONT, option);
    }

    public final void writeSubCommand(int option, byte[] command) throws IOException {
        writeCommand(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, option);
        write(command);
        writeCommand(240);
    }
}
