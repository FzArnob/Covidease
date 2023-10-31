package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.bytecode.Access;

@ShowFirstParty
@KeepForSdk
public class Hex {
    private static final char[] zzgy = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', Access.CLASS_CONTEXT, 'D', 'E', Access.FIELD_CONTEXT};
    private static final char[] zzgz = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public Hex() {
    }

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr, boolean z) {
        StringBuilder sb;
        byte[] bArr2 = bArr;
        boolean z2 = z;
        int length = bArr2.length;
        new StringBuilder(length << 1);
        StringBuilder sb2 = sb;
        int i = 0;
        while (i < length && (!z2 || i != length - 1 || (bArr2[i] & Ev3Constants.Opcode.TST) != 0)) {
            StringBuilder append = sb2.append(zzgy[(bArr2[i] & 240) >>> 4]);
            StringBuilder append2 = sb2.append(zzgy[bArr2[i] & 15]);
            i++;
        }
        return sb2.toString();
    }

    @KeepForSdk
    public static String bytesToStringLowercase(byte[] bArr) {
        String str;
        byte[] bArr2 = bArr;
        char[] cArr = new char[(bArr2.length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            byte b = bArr2[i2] & Ev3Constants.Opcode.TST;
            int i3 = i;
            int i4 = i + 1;
            cArr[i3] = zzgz[b >>> 4];
            int i5 = i4;
            i = i4 + 1;
            cArr[i5] = zzgz[b & 15];
        }
        new String(cArr);
        return str;
    }

    @KeepForSdk
    public static byte[] stringToBytes(String str) throws IllegalArgumentException {
        Throwable th;
        String str2 = str;
        int length = str2.length();
        int i = length;
        if (length % 2 != 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Hex string has odd number of characters");
            throw th2;
        }
        byte[] bArr = new byte[(i / 2)];
        for (int i2 = 0; i2 < i; i2 += 2) {
            bArr[i2 / 2] = (byte) Integer.parseInt(str2.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }
}
