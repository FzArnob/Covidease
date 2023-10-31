package gnu.text;

import android.support.p000v4.app.NotificationCompat;
import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Char implements Comparable, Externalizable {
    static Char[] ascii = new Char[128];
    static char[] charNameValues = {' ', 9, 10, 10, 13, 12, 8, 27, 127, 127, 127, 7, 7, 11, 0};
    static String[] charNames;
    static CharMap hashTable;
    int value;

    public Char() {
    }

    Char(int ch) {
        this.value = ch;
    }

    public void print(Consumer out) {
        print(this.value, out);
    }

    public static void print(int i, Consumer consumer) {
        int i2 = i;
        Consumer out = consumer;
        if (i2 >= 65536) {
            out.write((int) (char) (((i2 - 65536) >> 10) + 55296));
            out.write((int) (char) ((i2 & 1023) + 56320));
            return;
        }
        out.write((int) (char) i2);
    }

    public final char charValue() {
        return (char) this.value;
    }

    public final int intValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    static {
        CharMap charMap;
        Char charR;
        new CharMap();
        hashTable = charMap;
        int i = 128;
        while (true) {
            i--;
            if (i >= 0) {
                new Char(i);
                ascii[i] = charR;
            } else {
                String[] strArr = new String[15];
                strArr[0] = "space";
                String[] strArr2 = strArr;
                strArr2[1] = "tab";
                String[] strArr3 = strArr2;
                strArr3[2] = "newline";
                String[] strArr4 = strArr3;
                strArr4[3] = "linefeed";
                String[] strArr5 = strArr4;
                strArr5[4] = "return";
                String[] strArr6 = strArr5;
                strArr6[5] = "page";
                String[] strArr7 = strArr6;
                strArr7[6] = "backspace";
                String[] strArr8 = strArr7;
                strArr8[7] = "esc";
                String[] strArr9 = strArr8;
                strArr9[8] = "delete";
                String[] strArr10 = strArr9;
                strArr10[9] = "del";
                String[] strArr11 = strArr10;
                strArr11[10] = "rubout";
                String[] strArr12 = strArr11;
                strArr12[11] = NotificationCompat.CATEGORY_ALARM;
                String[] strArr13 = strArr12;
                strArr13[12] = "bel";
                String[] strArr14 = strArr13;
                strArr14[13] = "vtab";
                String[] strArr15 = strArr14;
                strArr15[14] = "nul";
                charNames = strArr15;
                return;
            }
        }
    }

    public static Char make(int i) {
        int ch = i;
        if (ch < 128) {
            return ascii[ch];
        }
        CharMap charMap = hashTable;
        CharMap charMap2 = charMap;
        synchronized (charMap) {
            try {
                Char charR = hashTable.get(ch);
                return charR;
            } catch (Throwable th) {
                Throwable th2 = th;
                CharMap charMap3 = charMap2;
                throw th2;
            }
        }
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        return obj2 != null && (obj2 instanceof Char) && ((Char) obj2).intValue() == this.value;
    }

    public static int nameToChar(String str) {
        char ch;
        String name = str;
        int i = charNames.length;
        do {
            i--;
            if (i < 0) {
                int i2 = charNames.length;
                do {
                    i2--;
                    if (i2 < 0) {
                        int i3 = name.length();
                        if (i3 > 1 && name.charAt(0) == 'u') {
                            int value2 = 0;
                            int pos = 1;
                            while (pos != i3) {
                                int dig = Character.digit(name.charAt(pos), 16);
                                if (dig >= 0) {
                                    value2 = (value2 << 4) + dig;
                                    pos++;
                                }
                            }
                            return value2;
                        }
                        if (i3 == 3 && name.charAt(1) == '-' && ((ch = name.charAt(0)) == 'c' || ch == 'C')) {
                            return name.charAt(2) & 31;
                        }
                        return -1;
                    }
                } while (!charNames[i2].equalsIgnoreCase(name));
                return charNameValues[i2];
            }
        } while (!charNames[i].equals(name));
        return charNameValues[i];
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append('\'');
        if (this.value < 32 || this.value >= 127 || this.value == 39) {
            StringBuffer append2 = buf.append('\\');
            if (this.value == 39) {
                StringBuffer append3 = buf.append('\'');
            } else if (this.value == 10) {
                StringBuffer append4 = buf.append('n');
            } else if (this.value == 13) {
                StringBuffer append5 = buf.append('r');
            } else if (this.value == 9) {
                StringBuffer append6 = buf.append('t');
            } else if (this.value < 256) {
                String str = Integer.toOctalString(this.value);
                int i = 3 - str.length();
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    StringBuffer append7 = buf.append('0');
                }
                StringBuffer append8 = buf.append(str);
            } else {
                StringBuffer append9 = buf.append('u');
                String str2 = Integer.toHexString(this.value);
                int i2 = 4 - str2.length();
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        break;
                    }
                    StringBuffer append10 = buf.append('0');
                }
                StringBuffer append11 = buf.append(str2);
            }
        } else {
            StringBuffer append12 = buf.append((char) this.value);
        }
        StringBuffer append13 = buf.append('\'');
        return buf.toString();
    }

    public static String toScmReadableString(int i) {
        StringBuffer stringBuffer;
        int ch = i;
        new StringBuffer(20);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("#\\");
        for (int i2 = 0; i2 < charNameValues.length; i2++) {
            if (((char) ch) == charNameValues[i2]) {
                StringBuffer append2 = sbuf.append(charNames[i2]);
                return sbuf.toString();
            }
        }
        if (ch < 32 || ch > 127) {
            StringBuffer append3 = sbuf.append('x');
            StringBuffer append4 = sbuf.append(Integer.toString(ch, 16));
        } else {
            StringBuffer append5 = sbuf.append((char) ch);
        }
        return sbuf.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        if (this.value > 55296) {
            if (this.value > 65535) {
                out.writeChar(((this.value - 65536) >> 10) + 55296);
                this.value = (this.value & 1023) + 56320;
            } else if (this.value <= 56319) {
                out.writeChar(this.value);
                this.value = 0;
            }
        }
        out.writeChar(this.value);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        char next;
        ObjectInput in = objectInput;
        this.value = in.readChar();
        if (this.value >= 55296 && this.value < 56319 && (next = in.readChar()) >= 56320 && next <= 57343) {
            this.value = ((this.value - 55296) << 10) + (next - 56320) + 65536;
        }
    }

    public Object readResolve() throws ObjectStreamException {
        return make(this.value);
    }

    public int compareTo(Object o) {
        return this.value - ((Char) o).value;
    }
}
