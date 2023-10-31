package com.google.appinventor.components.runtime.repackaged.org.json;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Arrays;

public class Kim {
    private byte[] bytes;
    private int hashcode;
    public int length;
    private String string;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Kim(byte[] r13, int r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r7 = r0
            r7.<init>()
            r7 = r0
            r8 = 0
            r7.bytes = r8
            r7 = r0
            r8 = 0
            r7.hashcode = r8
            r7 = r0
            r8 = 0
            r7.length = r8
            r7 = r0
            r8 = 0
            r7.string = r8
            r7 = 1
            r4 = r7
            r7 = r0
            r8 = 0
            r7.hashcode = r8
            r7 = r0
            r8 = r3
            r9 = r2
            int r8 = r8 - r9
            r7.length = r8
            r7 = r0
            int r7 = r7.length
            if (r7 <= 0) goto L_0x006b
            r7 = r0
            r8 = r0
            int r8 = r8.length
            byte[] r8 = new byte[r8]
            r7.bytes = r8
            r7 = 0
            r5 = r7
        L_0x0033:
            r7 = r5
            r8 = r0
            int r8 = r8.length
            if (r7 >= r8) goto L_0x005d
            r7 = r1
            r8 = r5
            r9 = r2
            int r8 = r8 + r9
            byte r7 = r7[r8]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & 255(0xff, float:3.57E-43)
            r6 = r7
            r7 = r4
            r8 = r6
            int r7 = r7 + r8
            r4 = r7
            r7 = r0
            r11 = r7
            r7 = r11
            r8 = r11
            int r8 = r8.hashcode
            r9 = r4
            int r8 = r8 + r9
            r7.hashcode = r8
            r7 = r0
            byte[] r7 = r7.bytes
            r8 = r5
            r9 = r6
            byte r9 = (byte) r9
            r7[r8] = r9
            int r5 = r5 + 1
            goto L_0x0033
        L_0x005d:
            r7 = r0
            r11 = r7
            r7 = r11
            r8 = r11
            int r8 = r8.hashcode
            r9 = r4
            r10 = 16
            int r9 = r9 << 16
            int r8 = r8 + r9
            r7.hashcode = r8
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.Kim.<init>(byte[], int, int):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Kim(byte[] bytes2, int length2) {
        this(bytes2, 0, length2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Kim(Kim kim, int from, int thru) {
        this(kim.bytes, from, thru);
    }

    public Kim(String str) throws JSONException {
        Throwable th;
        String string2 = str;
        this.bytes = null;
        this.hashcode = 0;
        this.length = 0;
        this.string = null;
        int stringLength = string2.length();
        this.hashcode = 0;
        this.length = 0;
        if (stringLength > 0) {
            int i = 0;
            while (i < stringLength) {
                int c = string2.charAt(i);
                if (c <= 127) {
                    this.length++;
                } else if (c <= 16383) {
                    this.length += 2;
                } else {
                    if (c >= 55296 && c <= 57343) {
                        i++;
                        int d = string2.charAt(i);
                        if (c > 56319 || d < 56320 || d > 57343) {
                            Throwable th2 = th;
                            new JSONException("Bad UTF16");
                            throw th2;
                        }
                    }
                    this.length += 3;
                }
                i++;
            }
            this.bytes = new byte[this.length];
            int at = 0;
            int sum = 1;
            int i2 = 0;
            while (i2 < stringLength) {
                int character = string2.charAt(i2);
                if (character <= 127) {
                    this.bytes[at] = (byte) character;
                    sum += character;
                    this.hashcode += sum;
                } else if (character <= 16383) {
                    int b = 128 | (character >>> 7);
                    this.bytes[at] = (byte) b;
                    int sum2 = sum + b;
                    this.hashcode += sum2;
                    at++;
                    int b2 = character & 127;
                    this.bytes[at] = (byte) b2;
                    sum = sum2 + b2;
                    this.hashcode += sum;
                } else {
                    if (character >= 55296 && character <= 56319) {
                        i2++;
                        character = (((character & 1023) << 10) | (string2.charAt(i2) & 1023)) + 0;
                    }
                    int b3 = 128 | (character >>> 14);
                    this.bytes[at] = (byte) b3;
                    int sum3 = sum + b3;
                    this.hashcode += sum3;
                    int at2 = at + 1;
                    int b4 = 128 | ((character >>> 7) & 255);
                    this.bytes[at2] = (byte) b4;
                    int sum4 = sum3 + b4;
                    this.hashcode += sum4;
                    at = at2 + 1;
                    int b5 = character & 127;
                    this.bytes[at] = (byte) b5;
                    sum = sum4 + b5;
                    this.hashcode += sum;
                }
                at++;
                i2++;
            }
            this.hashcode += sum << 16;
        }
    }

    public int characterAt(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int at = i;
        int c = get(at);
        if ((c & 128) == 0) {
            return c;
        }
        int c1 = get(at + 1);
        if ((c1 & 128) == 0) {
            int character = ((c & 127) << 7) | c1;
            if (character > 127) {
                return character;
            }
        } else {
            int c2 = get(at + 2);
            int character2 = ((c & 127) << 14) | ((c1 & 127) << 7) | c2;
            if ((c2 & 128) == 0 && character2 > 16383 && character2 <= 1114111 && (character2 < 55296 || character2 > 57343)) {
                return character2;
            }
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("Bad character at ").append(at).toString());
        throw th2;
    }

    public static int characterSize(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int character = i;
        if (character < 0 || character > 1114111) {
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("Bad character ").append(character).toString());
            throw th2;
        }
        return character <= 127 ? 1 : character <= 16383 ? 2 : 3;
    }

    public int copy(byte[] bytes2, int i) {
        int at = i;
        System.arraycopy(this.bytes, 0, bytes2, at, this.length);
        return at + this.length;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof Kim)) {
            return false;
        }
        Kim that = (Kim) obj2;
        if (this == that) {
            return true;
        }
        if (this.hashcode != that.hashcode) {
            return false;
        }
        return Arrays.equals(this.bytes, that.bytes);
    }

    public int get(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int at = i;
        if (at >= 0 && at <= this.length) {
            return this.bytes[at] & Ev3Constants.Opcode.TST;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("Bad character at ").append(at).toString());
        throw th2;
    }

    public int hashCode() {
        return this.hashcode;
    }

    public String toString() throws JSONException {
        String str;
        if (this.string == null) {
            int length2 = 0;
            char[] chars = new char[this.length];
            int i = 0;
            while (true) {
                int at = i;
                if (at >= this.length) {
                    break;
                }
                int c = characterAt(at);
                if (c < 65536) {
                    chars[length2] = (char) c;
                } else {
                    chars[length2] = (char) (55296 | ((c - 65536) >>> 10));
                    length2++;
                    chars[length2] = (char) (56320 | (c & 1023));
                }
                length2++;
                i = at + characterSize(c);
            }
            new String(chars, 0, length2);
            this.string = str;
        }
        return this.string;
    }
}
