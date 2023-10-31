package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;

public class Ev3BinaryParser {
    private static byte B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Ev3Constants.Opcode.f52JR;
    private static byte Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = 3;
    private static byte hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = Ev3Constants.Opcode.MOVEF_F;
    private static byte hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Byte.MIN_VALUE;
    private static byte qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 2;
    private static byte vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = 31;
    private static byte vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = 1;
    private static byte wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = 32;

    public Ev3BinaryParser() {
    }

    /* renamed from: com.google.appinventor.components.runtime.util.Ev3BinaryParser$a */
    static class C1126a {
        public char hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        public int size;

        public C1126a(char c, int i) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = c;
            this.size = i;
        }
    }

    public static byte[] pack(String str, Object... objArr) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        C1126a aVar;
        Throwable th7;
        Object[] objArr2 = objArr;
        String[] split = str.split("(?<=\\D)");
        String[] strArr = split;
        C1126a[] aVarArr = new C1126a[split.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String str2 = strArr[i3];
            String str3 = str2;
            String str4 = str2;
            String str5 = str4;
            char charAt = str3.charAt(str4.length() - 1);
            int i4 = 1;
            boolean z = false;
            if (str5.length() != 1) {
                i4 = Integer.parseInt(str5.substring(0, str5.length() - 1));
                z = true;
                if (i4 <= 0) {
                    Throwable th8 = th7;
                    new IllegalArgumentException("Illegal format string");
                    throw th8;
                }
            }
            switch (charAt) {
                case 'B':
                    i2 += i4;
                    i++;
                    break;
                case 'F':
                    i2 += i4 << 2;
                    i++;
                    break;
                case 'H':
                    i2 += i4 << 1;
                    i++;
                    break;
                case 'I':
                    i2 += i4 << 2;
                    i++;
                    break;
                case 'L':
                    i2 += i4 << 3;
                    i++;
                    break;
                case 'S':
                    if (!z) {
                        i2 += ((String) objArr2[i]).length() + 1;
                        i++;
                        break;
                    } else {
                        Throwable th9 = th5;
                        new IllegalArgumentException("Illegal format string");
                        throw th9;
                    }
                case 'b':
                    i2 += i4;
                    i += i4;
                    break;
                case 'f':
                    i2 += i4 << 2;
                    i += i4;
                    break;
                case 'h':
                    i2 += i4 << 1;
                    i += i4;
                    break;
                case 'i':
                    i2 += i4 << 2;
                    i += i4;
                    break;
                case 'l':
                    i2 += i4 << 3;
                    i += i4;
                    break;
                case 's':
                    if (i4 == ((String) objArr2[i]).length()) {
                        i2 += i4;
                        i++;
                        break;
                    } else {
                        Throwable th10 = th6;
                        new IllegalArgumentException("Illegal format string");
                        throw th10;
                    }
                case 'x':
                    i2 += i4;
                    break;
                default:
                    Throwable th11 = th4;
                    new IllegalArgumentException("Illegal format string");
                    throw th11;
            }
            new C1126a(charAt, i4);
            aVarArr[i3] = aVar;
        }
        if (i != objArr2.length) {
            Throwable th12 = th3;
            new IllegalArgumentException("Illegal format string");
            throw th12;
        }
        int i5 = 0;
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        ByteBuffer byteBuffer = allocate;
        ByteBuffer order = allocate.order(ByteOrder.LITTLE_ENDIAN);
        C1126a[] aVarArr2 = aVarArr;
        C1126a[] aVarArr3 = aVarArr2;
        int length = aVarArr2.length;
        for (int i6 = 0; i6 < length; i6++) {
            C1126a aVar2 = aVarArr3[i6];
            C1126a aVar3 = aVar2;
            switch (aVar2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
                case 'B':
                    ByteBuffer put = byteBuffer.put((byte[]) objArr2[i5]);
                    i5++;
                    break;
                case 'F':
                    for (int i7 = 0; i7 < aVar3.size; i7++) {
                        ByteBuffer putFloat = byteBuffer.putFloat(((float[]) objArr2[i5])[i7]);
                    }
                    i5++;
                    break;
                case 'H':
                    for (int i8 = 0; i8 < aVar3.size; i8++) {
                        ByteBuffer putShort = byteBuffer.putShort(((short[]) objArr2[i5])[i8]);
                    }
                    i5++;
                    break;
                case 'I':
                    for (int i9 = 0; i9 < aVar3.size; i9++) {
                        ByteBuffer putInt = byteBuffer.putInt(((int[]) objArr2[i5])[i9]);
                    }
                    i5++;
                    break;
                case 'L':
                    for (int i10 = 0; i10 < aVar3.size; i10++) {
                        ByteBuffer putLong = byteBuffer.putLong(((long[]) objArr2[i5])[i10]);
                    }
                    i5++;
                    break;
                case 'S':
                    try {
                        ByteBuffer put2 = byteBuffer.put(((String) objArr2[i5]).getBytes("US-ASCII"));
                        ByteBuffer put3 = byteBuffer.put((byte) 0);
                        i5++;
                        break;
                    } catch (UnsupportedEncodingException e) {
                        Throwable th13 = th;
                        new IllegalArgumentException();
                        throw th13;
                    }
                case 'b':
                    for (int i11 = 0; i11 < aVar3.size; i11++) {
                        ByteBuffer put4 = byteBuffer.put(((Byte) objArr2[i5]).byteValue());
                        i5++;
                    }
                    break;
                case 'f':
                    for (int i12 = 0; i12 < aVar3.size; i12++) {
                        ByteBuffer putFloat2 = byteBuffer.putFloat(((Float) objArr2[i5]).floatValue());
                        i5++;
                    }
                    break;
                case 'h':
                    for (int i13 = 0; i13 < aVar3.size; i13++) {
                        ByteBuffer putShort2 = byteBuffer.putShort(((Short) objArr2[i5]).shortValue());
                        i5++;
                    }
                    break;
                case 'i':
                    for (int i14 = 0; i14 < aVar3.size; i14++) {
                        ByteBuffer putInt2 = byteBuffer.putInt(((Integer) objArr2[i5]).intValue());
                        i5++;
                    }
                    break;
                case 'l':
                    for (int i15 = 0; i15 < aVar3.size; i15++) {
                        ByteBuffer putLong2 = byteBuffer.putLong(((Long) objArr2[i5]).longValue());
                        i5++;
                    }
                    break;
                case 's':
                    try {
                        ByteBuffer put5 = byteBuffer.put(((String) objArr2[i5]).getBytes("US-ASCII"));
                        i5++;
                        break;
                    } catch (UnsupportedEncodingException e2) {
                        Throwable th14 = th2;
                        new IllegalArgumentException();
                        throw th14;
                    }
                case 'x':
                    for (int i16 = 0; i16 < aVar3.size; i16++) {
                        ByteBuffer put6 = byteBuffer.put((byte) 0);
                    }
                    break;
            }
        }
        return byteBuffer.array();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0092, code lost:
        r4 = r4 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object[] unpack(java.lang.String r15, byte[] r16) throws java.lang.IllegalArgumentException {
        /*
            r0 = r15
            r1 = r16
            r9 = r0
            java.lang.String r10 = "(?<=\\D)"
            java.lang.String[] r9 = r9.split(r10)
            r0 = r9
            java.util.ArrayList r9 = new java.util.ArrayList
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()
            r2 = r9
            r9 = r1
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r9)
            r14 = r9
            r9 = r14
            r10 = r14
            r1 = r10
            java.nio.ByteOrder r10 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r9 = r9.order(r10)
            r9 = r0
            int r9 = r9.length
            r3 = r9
            r9 = 0
            r4 = r9
        L_0x0029:
            r9 = r4
            r10 = r3
            if (r9 >= r10) goto L_0x021f
            r9 = r0
            r10 = r4
            r9 = r9[r10]
            r5 = r9
            r9 = 0
            r6 = r9
            r9 = 1
            r7 = r9
            r9 = r5
            r14 = r9
            r9 = r14
            r10 = r14
            int r10 = r10.length()
            r11 = 1
            int r10 = r10 + -1
            char r9 = r9.charAt(r10)
            r8 = r9
            r9 = r5
            int r9 = r9.length()
            r10 = 1
            if (r9 <= r10) goto L_0x0074
            r9 = 1
            r6 = r9
            r9 = r5
            r10 = 0
            r11 = r5
            int r11 = r11.length()
            r12 = 1
            int r11 = r11 + -1
            java.lang.String r9 = r9.substring(r10, r11)
            int r9 = java.lang.Integer.parseInt(r9)
            r14 = r9
            r9 = r14
            r10 = r14
            r7 = r10
            if (r9 > 0) goto L_0x0074
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "Illegal format string"
            r10.<init>(r11)
            throw r9
        L_0x0074:
            r9 = r8
            switch(r9) {
                case 36: goto L_0x01fd;
                case 66: goto L_0x00ad;
                case 70: goto L_0x017f;
                case 72: goto L_0x00d8;
                case 73: goto L_0x0111;
                case 76: goto L_0x0148;
                case 83: goto L_0x01c6;
                case 98: goto L_0x0095;
                case 102: goto L_0x0166;
                case 104: goto L_0x00c0;
                case 105: goto L_0x00f9;
                case 108: goto L_0x012f;
                case 115: goto L_0x019d;
                case 120: goto L_0x0084;
                default: goto L_0x0078;
            }
        L_0x0078:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "Illegal format string"
            r10.<init>(r11)
            throw r9
        L_0x0084:
            r9 = 0
            r5 = r9
        L_0x0086:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x0092
            r9 = r1
            byte r9 = r9.get()
            int r5 = r5 + 1
            goto L_0x0086
        L_0x0092:
            int r4 = r4 + 1
            goto L_0x0029
        L_0x0095:
            r9 = 0
            r5 = r9
        L_0x0097:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x00ac
            r9 = r2
            r10 = r1
            byte r10 = r10.get()
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            boolean r9 = r9.add(r10)
            int r5 = r5 + 1
            goto L_0x0097
        L_0x00ac:
            goto L_0x0092
        L_0x00ad:
            r9 = r7
            byte[] r9 = new byte[r9]
            r5 = r9
            r9 = r1
            r10 = r5
            r11 = 0
            r12 = r7
            java.nio.ByteBuffer r9 = r9.get(r10, r11, r12)
            r9 = r2
            r10 = r5
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x00c0:
            r9 = 0
            r5 = r9
        L_0x00c2:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x00d7
            r9 = r2
            r10 = r1
            short r10 = r10.getShort()
            java.lang.Short r10 = java.lang.Short.valueOf(r10)
            boolean r9 = r9.add(r10)
            int r5 = r5 + 1
            goto L_0x00c2
        L_0x00d7:
            goto L_0x0092
        L_0x00d8:
            r9 = r7
            short[] r9 = new short[r9]
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x00de:
            r9 = r6
            r10 = r7
            if (r9 >= r10) goto L_0x00f2
            r9 = r5
            r10 = r6
            r11 = r1
            short r11 = r11.getShort()
            r9[r10] = r11
            r9 = r6
            r10 = 1
            int r9 = r9 + 1
            short r9 = (short) r9
            r6 = r9
            goto L_0x00de
        L_0x00f2:
            r9 = r2
            r10 = r5
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x00f9:
            r9 = 0
            r6 = r9
        L_0x00fb:
            r9 = r6
            r10 = r7
            if (r9 >= r10) goto L_0x0110
            r9 = r2
            r10 = r1
            int r10 = r10.getInt()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            boolean r9 = r9.add(r10)
            int r6 = r6 + 1
            goto L_0x00fb
        L_0x0110:
            goto L_0x0092
        L_0x0111:
            r9 = r7
            int[] r9 = new int[r9]
            r6 = r9
            r9 = 0
            r5 = r9
        L_0x0117:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x0127
            r9 = r6
            r10 = r5
            r11 = r1
            int r11 = r11.getInt()
            r9[r10] = r11
            int r5 = r5 + 1
            goto L_0x0117
        L_0x0127:
            r9 = r2
            r10 = r6
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x012f:
            r9 = 0
            r5 = r9
        L_0x0131:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x0146
            r9 = r2
            r10 = r1
            long r10 = r10.getLong()
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            boolean r9 = r9.add(r10)
            int r5 = r5 + 1
            goto L_0x0131
        L_0x0146:
            goto L_0x0092
        L_0x0148:
            r9 = r7
            long[] r9 = new long[r9]
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x014e:
            r9 = r6
            r10 = r7
            if (r9 >= r10) goto L_0x015e
            r9 = r5
            r10 = r6
            r11 = r1
            long r11 = r11.getLong()
            r9[r10] = r11
            int r6 = r6 + 1
            goto L_0x014e
        L_0x015e:
            r9 = r2
            r10 = r5
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x0166:
            r9 = 0
            r6 = r9
        L_0x0168:
            r9 = r6
            r10 = r7
            if (r9 >= r10) goto L_0x017d
            r9 = r2
            r10 = r1
            float r10 = r10.getFloat()
            java.lang.Float r10 = java.lang.Float.valueOf(r10)
            boolean r9 = r9.add(r10)
            int r6 = r6 + 1
            goto L_0x0168
        L_0x017d:
            goto L_0x0092
        L_0x017f:
            r9 = r7
            float[] r9 = new float[r9]
            r6 = r9
            r9 = 0
            r5 = r9
        L_0x0185:
            r9 = r5
            r10 = r7
            if (r9 >= r10) goto L_0x0195
            r9 = r6
            r10 = r5
            r11 = r1
            float r11 = r11.getFloat()
            r9[r10] = r11
            int r5 = r5 + 1
            goto L_0x0185
        L_0x0195:
            r9 = r2
            r10 = r6
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x019d:
            r9 = r7
            byte[] r9 = new byte[r9]
            r5 = r9
            r9 = r1
            r10 = r5
            r11 = 0
            r12 = r7
            java.nio.ByteBuffer r9 = r9.get(r10, r11, r12)
            r9 = r2
            java.lang.String r10 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x01bc }
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r5
            java.lang.String r13 = "US-ASCII"
            r11.<init>(r12, r13)     // Catch:{ UnsupportedEncodingException -> 0x01bc }
            boolean r9 = r9.add(r10)     // Catch:{ UnsupportedEncodingException -> 0x01bc }
            goto L_0x0092
        L_0x01bc:
            r9 = move-exception
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()
            throw r9
        L_0x01c6:
            r9 = r6
            if (r9 == 0) goto L_0x01d5
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "Illegal format string"
            r10.<init>(r11)
            throw r9
        L_0x01d5:
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()
            r5 = r9
        L_0x01de:
            r9 = r1
            byte r9 = r9.get()
            r14 = r9
            r9 = r14
            r10 = r14
            r6 = r10
            if (r9 == 0) goto L_0x01f1
            r9 = r5
            r10 = r6
            char r10 = (char) r10
            java.lang.StringBuffer r9 = r9.append(r10)
            goto L_0x01de
        L_0x01f1:
            r9 = r2
            r10 = r5
            java.lang.String r10 = r10.toString()
            boolean r9 = r9.add(r10)
            goto L_0x0092
        L_0x01fd:
            r9 = r6
            if (r9 == 0) goto L_0x020c
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "Illegal format string"
            r10.<init>(r11)
            throw r9
        L_0x020c:
            r9 = r1
            boolean r9 = r9.hasRemaining()
            if (r9 == 0) goto L_0x0078
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "Illegal format string"
            r10.<init>(r11)
            throw r9
        L_0x021f:
            r9 = r2
            java.lang.Object[] r9 = r9.toArray()
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.Ev3BinaryParser.unpack(java.lang.String, byte[]):java.lang.Object[]");
    }

    public static byte[] encodeLC0(byte b) {
        Throwable th;
        byte b2 = b;
        if (b2 < -31 || b2 > 31) {
            Throwable th2 = th;
            new IllegalArgumentException("Encoded value must be in range [0, 127]");
            throw th2;
        }
        return new byte[]{(byte) (b2 & hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO)};
    }

    public static byte[] encodeLC1(byte b) {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) b;
        return bArr2;
    }

    public static byte[] encodeLC2(short s) {
        short s2 = s;
        byte[] bArr = new byte[3];
        bArr[0] = (byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) s2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (s2 >>> 8);
        return bArr3;
    }

    public static byte[] encodeLC4(int i) {
        int i2 = i;
        byte[] bArr = new byte[5];
        bArr[0] = (byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (i2 >>> 8);
        byte[] bArr4 = bArr3;
        bArr4[3] = (byte) (i2 >>> 16);
        byte[] bArr5 = bArr4;
        bArr5[4] = (byte) (i2 >>> 24);
        return bArr5;
    }

    public static byte[] encodeLV0(int i) {
        return new byte[]{(byte) ((i & vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)};
    }

    public static byte[] encodeLV1(int i) {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i;
        return bArr2;
    }

    public static byte[] encodeLV2(int i) {
        int i2 = i;
        byte[] bArr = new byte[3];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (i2 >>> 8);
        return bArr3;
    }

    public static byte[] encodeLV4(int i) {
        int i2 = i;
        byte[] bArr = new byte[5];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (i2 >>> 8);
        byte[] bArr4 = bArr3;
        bArr4[3] = (byte) (i2 >>> 16);
        byte[] bArr5 = bArr4;
        bArr5[4] = (byte) (i2 >>> 24);
        return bArr5;
    }

    public static byte[] encodeGV0(int i) {
        return new byte[]{(byte) ((i & vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou)};
    }

    public static byte[] encodeGV1(int i) {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i;
        return bArr2;
    }

    public static byte[] encodeGV2(int i) {
        int i2 = i;
        byte[] bArr = new byte[3];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (i2 >>> 8);
        return bArr3;
    }

    public static byte[] encodeGV4(int i) {
        int i2 = i;
        byte[] bArr = new byte[5];
        bArr[0] = (byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) i2;
        byte[] bArr3 = bArr2;
        bArr3[2] = (byte) (i2 >>> 8);
        byte[] bArr4 = bArr3;
        bArr4[3] = (byte) (i2 >>> 16);
        byte[] bArr5 = bArr4;
        bArr5[4] = (byte) (i2 >>> 24);
        return bArr5;
    }

    public static byte[] encodeSystemCommand(byte b, boolean z, Object... objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        byte b2 = b;
        boolean z2 = z;
        Object[] objArr2 = objArr;
        int i = 2;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr3;
        int length = objArr3.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr4[i2];
            Object obj2 = obj;
            if (obj instanceof Byte) {
                i++;
            } else if (obj2 instanceof Short) {
                i += 2;
            } else if (obj2 instanceof Integer) {
                i += 4;
            } else if (obj2 instanceof String) {
                i += ((String) obj2).length() + 1;
            } else {
                Throwable th4 = th3;
                new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
                throw th4;
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        ByteBuffer byteBuffer = allocate;
        ByteBuffer order = allocate.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer put = byteBuffer.put(z2 ? (byte) 1 : -127);
        ByteBuffer put2 = byteBuffer.put(b2);
        Object[] objArr5 = objArr2;
        Object[] objArr6 = objArr5;
        int length2 = objArr5.length;
        for (int i3 = 0; i3 < length2; i3++) {
            Object obj3 = objArr6[i3];
            Object obj4 = obj3;
            if (obj3 instanceof Byte) {
                ByteBuffer put3 = byteBuffer.put(((Byte) obj4).byteValue());
            } else if (obj4 instanceof Short) {
                ByteBuffer putShort = byteBuffer.putShort(((Short) obj4).shortValue());
            } else if (obj4 instanceof Integer) {
                ByteBuffer putInt = byteBuffer.putInt(((Integer) obj4).intValue());
            } else if (obj4 instanceof String) {
                try {
                    ByteBuffer put4 = byteBuffer.put(((String) obj4).getBytes("US-ASCII"));
                    ByteBuffer put5 = byteBuffer.put((byte) 0);
                } catch (UnsupportedEncodingException e) {
                    Throwable th5 = th2;
                    new IllegalArgumentException("Non-ASCII string encoding is not supported");
                    throw th5;
                }
            } else {
                Throwable th6 = th;
                new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
                throw th6;
            }
        }
        return byteBuffer.array();
    }

    public static byte[] encodeDirectCommand(byte b, boolean z, int i, int i2, String str, Object... objArr) {
        Throwable th;
        ArrayList arrayList;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        StringBuilder sb;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        byte b2 = b;
        boolean z2 = z;
        int i3 = i;
        int i4 = i2;
        String str2 = str;
        Object[] objArr2 = objArr;
        if (i3 < 0 || i3 > 1023 || i4 < 0 || i4 > 63 || str2.length() != objArr2.length) {
            Throwable th8 = th;
            new IllegalArgumentException();
            throw th8;
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (int i5 = 0; i5 < str2.length(); i5++) {
            char charAt = str2.charAt(i5);
            Object obj = objArr2[i5];
            switch (charAt) {
                case 'c':
                    if (obj instanceof Byte) {
                        if (((Byte) obj).byteValue() <= 31 && ((Byte) obj).byteValue() >= -31) {
                            boolean add = arrayList2.add(encodeLC0(((Byte) obj).byteValue()));
                            break;
                        } else {
                            boolean add2 = arrayList2.add(encodeLC1(((Byte) obj).byteValue()));
                            break;
                        }
                    } else if (obj instanceof Short) {
                        boolean add3 = arrayList2.add(encodeLC2(((Short) obj).shortValue()));
                        break;
                    } else if (obj instanceof Integer) {
                        boolean add4 = arrayList2.add(encodeLC4(((Integer) obj).intValue()));
                        break;
                    } else {
                        Throwable th9 = th7;
                        new IllegalArgumentException();
                        throw th9;
                    }
                case 'g':
                    if (obj instanceof Byte) {
                        if (((Byte) obj).byteValue() <= 31 && ((Byte) obj).byteValue() >= -31) {
                            boolean add5 = arrayList2.add(encodeGV0(((Byte) obj).byteValue()));
                            break;
                        } else {
                            boolean add6 = arrayList2.add(encodeGV1(((Byte) obj).byteValue()));
                            break;
                        }
                    } else if (obj instanceof Short) {
                        boolean add7 = arrayList2.add(encodeGV2(((Short) obj).shortValue()));
                        break;
                    } else if (obj instanceof Integer) {
                        boolean add8 = arrayList2.add(encodeGV4(((Integer) obj).intValue()));
                        break;
                    } else {
                        Throwable th10 = th5;
                        new IllegalArgumentException();
                        throw th10;
                    }
                    break;
                case 'l':
                    if (obj instanceof Byte) {
                        if (((Byte) obj).byteValue() <= 31 && ((Byte) obj).byteValue() >= -31) {
                            boolean add9 = arrayList2.add(encodeLV0(((Byte) obj).byteValue()));
                            break;
                        } else {
                            boolean add10 = arrayList2.add(encodeLV1(((Byte) obj).byteValue()));
                            break;
                        }
                    } else if (obj instanceof Short) {
                        boolean add11 = arrayList2.add(encodeLV2(((Short) obj).shortValue()));
                        break;
                    } else if (obj instanceof Integer) {
                        boolean add12 = arrayList2.add(encodeLV4(((Integer) obj).intValue()));
                        break;
                    } else {
                        Throwable th11 = th6;
                        new IllegalArgumentException();
                        throw th11;
                    }
                    break;
                case 's':
                    if (!(obj instanceof String)) {
                        Throwable th12 = th3;
                        new IllegalArgumentException();
                        throw th12;
                    }
                    ArrayList arrayList3 = arrayList2;
                    try {
                        new StringBuilder();
                        boolean add13 = arrayList3.add(sb.append((String) obj).append(0).toString().getBytes("US-ASCII"));
                        break;
                    } catch (UnsupportedEncodingException e) {
                        Throwable th13 = th4;
                        new IllegalArgumentException();
                        throw th13;
                    }
                default:
                    Throwable th14 = th2;
                    new IllegalArgumentException("Illegal format string");
                    throw th14;
            }
        }
        int i6 = 4;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            i6 += ((byte[]) it.next()).length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i6);
        ByteBuffer byteBuffer = allocate;
        ByteBuffer order = allocate.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer put = byteBuffer.put(z2 ? 0 : Byte.MIN_VALUE);
        byte[] bArr = new byte[2];
        bArr[0] = (byte) i3;
        byte[] bArr2 = bArr;
        bArr2[1] = (byte) (((i3 >>> 8) & 3) | (i4 << 2));
        ByteBuffer put2 = byteBuffer.put(bArr2);
        ByteBuffer put3 = byteBuffer.put(b2);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ByteBuffer put4 = byteBuffer.put((byte[]) it2.next());
        }
        return byteBuffer.array();
    }
}
