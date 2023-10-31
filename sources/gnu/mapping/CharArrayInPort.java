package gnu.mapping;

import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.text.NullReader;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

public class CharArrayInPort extends InPort {
    static final Path stringPath = Path.valueOf("<string>");

    public CharArrayInPort make(CharSequence charSequence) {
        CharArrayInPort charArrayInPort;
        CharArrayInPort charArrayInPort2;
        CharSequence seq = charSequence;
        if (seq instanceof FString) {
            FString fstr = (FString) seq;
            new CharArrayInPort(fstr.data, fstr.size);
            return charArrayInPort2;
        }
        int len = seq.length();
        char[] buf = new char[len];
        if (seq instanceof String) {
            ((String) seq).getChars(0, len, buf, 0);
        } else if (!(seq instanceof CharSeq)) {
            int i = len;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                buf[i] = seq.charAt(i);
            }
        } else {
            ((CharSeq) seq).getChars(0, len, buf, 0);
        }
        new CharArrayInPort(buf, len);
        return charArrayInPort;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CharArrayInPort(char[] buffer, int i) {
        super((Reader) NullReader.nullReader, stringPath);
        Throwable th;
        int len = i;
        try {
            setBuffer(buffer);
            this.limit = len;
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new Error(ex.toString());
            throw th2;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CharArrayInPort(char[] r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            int r4 = r4.length
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.CharArrayInPort.<init>(char[]):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CharArrayInPort(String string) {
        this(string.toCharArray());
    }

    public int read() throws IOException {
        if (this.pos >= this.limit) {
            return -1;
        }
        return super.read();
    }
}
