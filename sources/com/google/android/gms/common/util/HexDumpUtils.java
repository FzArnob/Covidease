package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.appinventor.components.runtime.util.Ev3Constants;

@KeepForSdk
public final class HexDumpUtils {
    public HexDumpUtils() {
    }

    @KeepForSdk
    public static String dump(byte[] bArr, int i, int i2, boolean z) {
        StringBuilder sb;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        if (bArr2 == null || bArr2.length == 0 || i3 < 0 || i4 <= 0 || i3 + i4 > bArr2.length) {
            return null;
        }
        int i5 = 57;
        if (z2) {
            i5 = 57 + 18;
        }
        new StringBuilder(i5 * (((i4 + 16) - 1) / 16));
        StringBuilder sb2 = sb;
        int i6 = 0;
        int i7 = 0;
        int i8 = i4;
        int i9 = i3;
        while (i8 > 0) {
            if (i6 == 0) {
                i7 = i9;
                if (i4 < 65536) {
                    StringBuilder append = sb2.append(String.format("%04X:", new Object[]{Integer.valueOf(i9)}));
                } else {
                    StringBuilder append2 = sb2.append(String.format("%08X:", new Object[]{Integer.valueOf(i9)}));
                }
            } else if (i6 == 8) {
                StringBuilder append3 = sb2.append(" -");
            }
            StringBuilder append4 = sb2.append(String.format(" %02X", new Object[]{Integer.valueOf(bArr2[i9] & Ev3Constants.Opcode.TST)}));
            i8--;
            i6++;
            if (z2 && (i6 == 16 || i8 == 0)) {
                int i10 = 16 - i6;
                int i11 = i10;
                if (i10 > 0) {
                    for (int i12 = 0; i12 < i11; i12++) {
                        StringBuilder append5 = sb2.append("   ");
                    }
                }
                if (i11 >= 8) {
                    StringBuilder append6 = sb2.append("  ");
                }
                StringBuilder append7 = sb2.append("  ");
                for (int i13 = 0; i13 < i6; i13++) {
                    char c = (char) bArr2[i7 + i13];
                    StringBuilder append8 = sb2.append((c < ' ' || c > '~') ? '.' : c);
                }
            }
            if (i6 == 16 || i8 == 0) {
                StringBuilder append9 = sb2.append(10);
                i6 = 0;
            }
            i9++;
        }
        return sb2.toString();
    }
}
