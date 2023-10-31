package com.shaded.fasterxml.jackson.core.p005io;

import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import java.util.Arrays;

/* renamed from: com.shaded.fasterxml.jackson.core.io.CharTypes */
public final class CharTypes {
    private static final byte[] HEX_BYTES;
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    static final int[] sHexValues = new int[128];
    static final int[] sInputCodes;
    static final int[] sInputCodesComment = new int[256];
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUtf8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sOutputEscapes128;

    public CharTypes() {
    }

    static {
        int i;
        int length = HEX_CHARS.length;
        HEX_BYTES = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            HEX_BYTES[i2] = (byte) HEX_CHARS[i2];
        }
        int[] iArr = new int[256];
        for (int i3 = 0; i3 < 32; i3++) {
            iArr[i3] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        int[] iArr2 = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, iArr2, 0, sInputCodes.length);
        for (int i4 = 128; i4 < 256; i4++) {
            if ((i4 & YaVersion.YOUNG_ANDROID_VERSION) == 192) {
                i = 2;
            } else if ((i4 & 240) == 224) {
                i = 3;
            } else if ((i4 & ComponentConstants.LISTVIEW_PREFERRED_WIDTH) == 240) {
                i = 4;
            } else {
                i = -1;
            }
            iArr2[i4] = i;
        }
        sInputCodesUtf8 = iArr2;
        int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i5 = 33; i5 < 256; i5++) {
            if (Character.isJavaIdentifierPart((char) i5)) {
                iArr3[i5] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        sInputCodesJsNames = iArr3;
        int[] iArr4 = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, iArr4, 0, sInputCodesJsNames.length);
        Arrays.fill(iArr4, 128, 128, 0);
        sInputCodesUtf8JsNames = iArr4;
        System.arraycopy(sInputCodesUtf8, 128, sInputCodesComment, 128, 128);
        Arrays.fill(sInputCodesComment, 0, 32, -1);
        sInputCodesComment[9] = 0;
        sInputCodesComment[10] = 10;
        sInputCodesComment[13] = 13;
        sInputCodesComment[42] = 42;
        int[] iArr5 = new int[128];
        for (int i6 = 0; i6 < 32; i6++) {
            iArr5[i6] = -1;
        }
        iArr5[34] = 34;
        iArr5[92] = 92;
        iArr5[8] = 98;
        iArr5[9] = 116;
        iArr5[12] = 102;
        iArr5[10] = 110;
        iArr5[13] = 114;
        sOutputEscapes128 = iArr5;
        Arrays.fill(sHexValues, -1);
        for (int i7 = 0; i7 < 10; i7++) {
            sHexValues[48 + i7] = i7;
        }
        for (int i8 = 0; i8 < 6; i8++) {
            sHexValues[97 + i8] = 10 + i8;
            sHexValues[65 + i8] = 10 + i8;
        }
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeUtf8() {
        return sInputCodesUtf8;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int charToHex(int i) {
        int i2 = i;
        return i2 > 127 ? -1 : sHexValues[i2];
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        StringBuilder sb2 = sb;
        String str2 = str;
        int[] iArr = sOutputEscapes128;
        int length = iArr.length;
        int length2 = str2.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str2.charAt(i);
            if (charAt >= length || iArr[charAt] == 0) {
                StringBuilder append = sb2.append(charAt);
            } else {
                StringBuilder append2 = sb2.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    StringBuilder append3 = sb2.append('u');
                    StringBuilder append4 = sb2.append('0');
                    StringBuilder append5 = sb2.append('0');
                    int i3 = -(i2 + 1);
                    StringBuilder append6 = sb2.append(HEX_CHARS[i3 >> 4]);
                    StringBuilder append7 = sb2.append(HEX_CHARS[i3 & 15]);
                } else {
                    StringBuilder append8 = sb2.append((char) i2);
                }
            }
        }
    }

    public static char[] copyHexChars() {
        return (char[]) HEX_CHARS.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[]) HEX_BYTES.clone();
    }
}
