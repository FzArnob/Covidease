package gnu.kawa.xml;

public class HexBinary extends BinaryObject {
    public HexBinary(byte[] data) {
        this.data = data;
    }

    static HexBinary valueOf(String str) {
        HexBinary hexBinary;
        new HexBinary(parseHexBinary(str));
        return hexBinary;
    }

    static byte[] parseHexBinary(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        String str2 = str.trim();
        int len = str2.length();
        if ((len & 1) != 0) {
            Throwable th3 = th2;
            new IllegalArgumentException("hexBinary string length not a multiple of 2");
            throw th3;
        }
        int len2 = len >> 1;
        byte[] result = new byte[len2];
        for (int i = 0; i < len2; i++) {
            int d1 = Character.digit(str2.charAt(2 * i), 16);
            int d2 = Character.digit(str2.charAt((2 * i) + 1), 16);
            int bad = -1;
            if (d1 < 0) {
                bad = 2 * i;
            } else if (d2 < 0) {
                bad = (2 * i) + 1;
            }
            if (bad >= 0) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("invalid hexBinary character at position ").append(bad).toString());
                throw th4;
            }
            result[i] = (byte) ((16 * d1) + d2);
        }
        return result;
    }

    static char forHexDigit(int i) {
        int val = i;
        return val < 10 ? (char) (val + 48) : (char) ((val - 10) + 65);
    }

    public StringBuffer toString(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        byte[] bb = this.data;
        int len = bb.length;
        for (int i = 0; i < len; i++) {
            byte b = bb[i];
            StringBuffer append = sbuf.append(forHexDigit((b >> 4) & 15));
            StringBuffer append2 = sbuf.append(forHexDigit(b & 15));
        }
        return sbuf;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        return toString(stringBuffer).toString();
    }
}
