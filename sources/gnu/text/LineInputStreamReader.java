package gnu.text;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class LineInputStreamReader extends LineBufferedReader {
    byte[] barr = new byte[8192];
    ByteBuffer bbuf = ByteBuffer.wrap(this.barr);
    char[] carr;
    CharBuffer cbuf = null;
    Charset cset;
    CharsetDecoder decoder;
    InputStream istrm;

    public void setCharset(Charset charset) {
        Charset cset2 = charset;
        this.cset = cset2;
        this.decoder = cset2.newDecoder();
    }

    public void setCharset(String str) {
        Throwable th;
        StringBuilder sb;
        String name = str;
        Charset cset2 = Charset.forName(name);
        if (this.cset == null) {
            setCharset(cset2);
        } else if (!cset2.equals(this.cset)) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("encoding ").append(name).append(" does not match previous ").append(this.cset).toString());
            throw th2;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LineInputStreamReader(InputStream in) {
        super((Reader) null);
        Buffer position = this.bbuf.position(this.barr.length);
        this.istrm = in;
    }

    public void close() throws IOException {
        if (this.f247in != null) {
            this.f247in.close();
        }
        this.istrm.close();
    }

    private int fillBytes(int i) throws IOException {
        int remaining = i;
        int n = this.istrm.read(this.barr, remaining, this.barr.length - remaining);
        Buffer position = this.bbuf.position(0);
        Buffer limit = this.bbuf.limit(remaining + (n < 0 ? 0 : n));
        return n;
    }

    public void markStart() throws IOException {
    }

    public void resetStart(int pos) throws IOException {
        Buffer position = this.bbuf.position(pos);
    }

    public int getByte() throws IOException {
        if (this.bbuf.hasRemaining() || fillBytes(0) > 0) {
            return this.bbuf.get() & Ev3Constants.Opcode.TST;
        }
        return -1;
    }

    public int fill(int i) throws IOException {
        int count;
        int len = i;
        if (this.cset == null) {
            setCharset("UTF-8");
        }
        if (this.buffer != this.carr) {
            this.cbuf = CharBuffer.wrap(this.buffer);
            this.carr = this.buffer;
        }
        Buffer limit = this.cbuf.limit(this.pos + len);
        Buffer position = this.cbuf.position(this.pos);
        boolean eof = false;
        while (true) {
            CoderResult cres = this.decoder.decode(this.bbuf, this.cbuf, false);
            count = this.cbuf.position() - this.pos;
            if (count > 0 || !cres.isUnderflow()) {
                break;
            }
            int rem = this.bbuf.remaining();
            if (rem > 0) {
                ByteBuffer compact = this.bbuf.compact();
            }
            if (fillBytes(rem) < 0) {
                eof = true;
                break;
            }
        }
        return (count != 0 || !eof) ? count : -1;
    }

    public boolean ready() throws IOException {
        return this.pos < this.limit || this.bbuf.hasRemaining() || this.istrm.available() > 0;
    }
}
