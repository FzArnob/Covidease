package gnu.text;

import java.io.Reader;

public class NullReader extends Reader {
    public static final NullReader nullReader;

    public NullReader() {
    }

    static {
        NullReader nullReader2;
        new NullReader();
        nullReader = nullReader2;
    }

    public int read(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        return -1;
    }

    public boolean ready() {
        return true;
    }

    public void close() {
    }
}
