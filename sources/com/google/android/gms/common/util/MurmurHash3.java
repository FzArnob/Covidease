package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MurmurHash3 {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x00d7, code lost:
        r12 = (r6 | (r0[r5] & 255)) * -862048943;
        r4 = r4 ^ (((r12 << 15) | (r12 >>> 17)) * 461845907);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0084, code lost:
        r12 = r4 ^ r2;
        r8 = r12;
        r12 = r12;
        r4 = r12;
        r12 = (r8 ^ (r12 >>> 16)) * -2048144789;
        r8 = r12;
        r12 = r12;
        r4 = r12;
        r12 = (r8 ^ (r12 >>> 13)) * -1028477387;
        r8 = r12;
        r12 = r12;
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x00b4, code lost:
        return r8 ^ (r12 >>> 16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x00c5, code lost:
        r6 = r6 | ((r0[r5 + 1] & 255) << 8);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int murmurhash3_x86_32(byte[] r13, int r14, int r15, int r16) {
        /*
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r8 = r3
            r4 = r8
            r8 = r1
            r9 = r2
            r10 = -4
            r9 = r9 & -4
            int r8 = r8 + r9
            r5 = r8
            r8 = r1
            r6 = r8
        L_0x0010:
            r8 = r6
            r9 = r5
            if (r8 >= r9) goto L_0x007b
            r8 = r0
            r9 = r6
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = r0
            r10 = r6
            r11 = 1
            int r10 = r10 + 1
            byte r9 = r9[r10]
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = 8
            int r9 = r9 << 8
            r8 = r8 | r9
            r9 = r0
            r10 = r6
            r11 = 2
            int r10 = r10 + 2
            byte r9 = r9[r10]
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = 16
            int r9 = r9 << 16
            r8 = r8 | r9
            r9 = r0
            r10 = r6
            r11 = 3
            int r10 = r10 + 3
            byte r9 = r9[r10]
            r10 = 24
            int r9 = r9 << 24
            r8 = r8 | r9
            r9 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r8 = r8 * r9
            r12 = r8
            r8 = r12
            r9 = r12
            r7 = r9
            r9 = 15
            int r8 = r8 << 15
            r9 = r7
            r10 = 17
            int r9 = r9 >>> 17
            r8 = r8 | r9
            r9 = 461845907(0x1b873593, float:2.2368498E-22)
            int r8 = r8 * r9
            r7 = r8
            r8 = r4
            r9 = r7
            r8 = r8 ^ r9
            r12 = r8
            r8 = r12
            r9 = r12
            r4 = r9
            r9 = 13
            int r8 = r8 << 13
            r9 = r4
            r10 = 19
            int r9 = r9 >>> 19
            r8 = r8 | r9
            r9 = 5
            int r8 = r8 * 5
            r9 = -430675100(0xffffffffe6546b64, float:-2.5078068E23)
            int r8 = r8 + r9
            r4 = r8
            int r6 = r6 + 4
            goto L_0x0010
        L_0x007b:
            r8 = 0
            r6 = r8
            r8 = r2
            r9 = 3
            r8 = r8 & 3
            switch(r8) {
                case 1: goto L_0x00d7;
                case 2: goto L_0x00c5;
                case 3: goto L_0x00b5;
                default: goto L_0x0084;
            }
        L_0x0084:
            r8 = r4
            r9 = r2
            r8 = r8 ^ r9
            r12 = r8
            r8 = r12
            r9 = r12
            r12 = r9
            r9 = r12
            r10 = r12
            r4 = r10
            r10 = 16
            int r9 = r9 >>> 16
            r8 = r8 ^ r9
            r9 = -2048144789(0xffffffff85ebca6b, float:-2.217365E-35)
            int r8 = r8 * r9
            r12 = r8
            r8 = r12
            r9 = r12
            r12 = r9
            r9 = r12
            r10 = r12
            r4 = r10
            r10 = 13
            int r9 = r9 >>> 13
            r8 = r8 ^ r9
            r9 = -1028477387(0xffffffffc2b2ae35, float:-89.34025)
            int r8 = r8 * r9
            r12 = r8
            r8 = r12
            r9 = r12
            r12 = r9
            r9 = r12
            r10 = r12
            r4 = r10
            r10 = 16
            int r9 = r9 >>> 16
            r8 = r8 ^ r9
            r0 = r8
            return r0
        L_0x00b5:
            r8 = r0
            r9 = r5
            r10 = 2
            int r9 = r9 + 2
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 16
            int r8 = r8 << 16
            r6 = r8
        L_0x00c5:
            r8 = r6
            r9 = r0
            r10 = r5
            r11 = 1
            int r10 = r10 + 1
            byte r9 = r9[r10]
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = 8
            int r9 = r9 << 8
            r8 = r8 | r9
            r6 = r8
        L_0x00d7:
            r8 = r6
            r9 = r0
            r10 = r5
            byte r9 = r9[r10]
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r8 = r8 | r9
            r9 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r8 = r8 * r9
            r12 = r8
            r8 = r12
            r9 = r12
            r6 = r9
            r9 = 15
            int r8 = r8 << 15
            r9 = r6
            r10 = 17
            int r9 = r9 >>> 17
            r8 = r8 | r9
            r9 = 461845907(0x1b873593, float:2.2368498E-22)
            int r8 = r8 * r9
            r6 = r8
            r8 = r4
            r9 = r6
            r8 = r8 ^ r9
            r4 = r8
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.MurmurHash3.murmurhash3_x86_32(byte[], int, int, int):int");
    }

    private MurmurHash3() {
    }
}
