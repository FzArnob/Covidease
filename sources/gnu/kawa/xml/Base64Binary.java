package gnu.kawa.xml;

public class Base64Binary extends BinaryObject {
    public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    public Base64Binary(byte[] data) {
        this.data = data;
    }

    public static Base64Binary valueOf(String str) {
        Base64Binary base64Binary;
        new Base64Binary(str);
        return base64Binary;
    }

    public Base64Binary(String str) {
        Throwable th;
        int v;
        Throwable th2;
        StringBuilder sb;
        String str2 = str;
        int len = str2.length();
        int blen = 0;
        for (int i = 0; i < len; i++) {
            char ch = str2.charAt(i);
            if (!Character.isWhitespace(ch) && ch != '=') {
                blen++;
            }
        }
        byte[] bytes = new byte[((3 * blen) / 4)];
        int value = 0;
        int buffered = 0;
        int padding = 0;
        int blen2 = 0;
        for (int i2 = 0; i2 < len; i2++) {
            char ch2 = str2.charAt(i2);
            if (ch2 >= 'A' && ch2 <= 'Z') {
                v = ch2 - 'A';
            } else if (ch2 >= 'a' && ch2 <= 'z') {
                v = (ch2 - 'a') + 26;
            } else if (ch2 >= '0' && ch2 <= '9') {
                v = (ch2 - '0') + 52;
            } else if (ch2 == '+') {
                v = 62;
            } else if (ch2 == '/') {
                v = 63;
            } else {
                if (Character.isWhitespace(ch2)) {
                    continue;
                } else if (ch2 == '=') {
                    padding++;
                } else {
                    v = -1;
                }
            }
            if (v < 0 || padding > 0) {
                Throwable th3 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb.append("illegal character in base64Binary string at position ").append(i2).toString());
                throw th3;
            }
            value = (value << 6) + v;
            buffered++;
            if (buffered == 4) {
                int i3 = blen2;
                int blen3 = blen2 + 1;
                bytes[i3] = (byte) (value >> 16);
                int i4 = blen3;
                int blen4 = blen3 + 1;
                bytes[i4] = (byte) (value >> 8);
                int i5 = blen4;
                blen2 = blen4 + 1;
                bytes[i5] = (byte) value;
                buffered = 0;
            }
        }
        if (buffered + padding <= 0 ? blen2 != bytes.length : !(buffered + padding == 4 && (value & ((1 << padding) - 1)) == 0 && (blen2 + 3) - padding == bytes.length)) {
            Throwable th4 = th;
            new IllegalArgumentException();
            throw th4;
        }
        switch (padding) {
            case 1:
                int i6 = blen2;
                int blen5 = blen2 + 1;
                bytes[i6] = (byte) (value << 10);
                int i7 = blen5;
                int blen6 = blen5 + 1;
                bytes[i7] = (byte) (value >> 2);
                break;
            case 2:
                int i8 = blen2;
                int blen7 = blen2 + 1;
                bytes[i8] = (byte) (value >> 4);
                break;
        }
        this.data = bytes;
    }

    public StringBuffer toString(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        byte[] bb = this.data;
        int len = bb.length;
        int value = 0;
        int i = 0;
        while (i < len) {
            value = (value << 8) | (bb[i] & 255);
            i++;
            if (i % 3 == 0) {
                StringBuffer append = sbuf.append(ENCODING.charAt((value >> 18) & 63));
                StringBuffer append2 = sbuf.append(ENCODING.charAt((value >> 12) & 63));
                StringBuffer append3 = sbuf.append(ENCODING.charAt((value >> 6) & 63));
                StringBuffer append4 = sbuf.append(ENCODING.charAt(value & 63));
            }
        }
        switch (len % 3) {
            case 1:
                StringBuffer append5 = sbuf.append(ENCODING.charAt((value >> 2) & 63));
                StringBuffer append6 = sbuf.append(ENCODING.charAt((value << 4) & 63));
                StringBuffer append7 = sbuf.append("==");
                break;
            case 2:
                StringBuffer append8 = sbuf.append(ENCODING.charAt((value >> 10) & 63));
                StringBuffer append9 = sbuf.append(ENCODING.charAt((value >> 4) & 63));
                StringBuffer append10 = sbuf.append(ENCODING.charAt((value << 2) & 63));
                StringBuffer append11 = sbuf.append('=');
                break;
        }
        return sbuf;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        return toString(stringBuffer).toString();
    }
}
