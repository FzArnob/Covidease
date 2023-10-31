package gnu.math;

public class BitOps {
    static final byte[] bit4_count = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    private BitOps() {
    }

    public static boolean bitValue(IntNum intNum, int i) {
        IntNum x = intNum;
        int bitno = i;
        int i2 = x.ival;
        if (x.words == null) {
            return bitno >= 32 ? i2 < 0 : ((i2 >> bitno) & 1) != 0;
        }
        int wordno = bitno >> 5;
        return wordno >= i2 ? x.words[i2 + -1] < 0 : ((x.words[wordno] >> bitno) & 1) != 0;
    }

    static int[] dataBufferFor(IntNum intNum, int i) {
        int[] data;
        IntNum x = intNum;
        int bitno = i;
        int i2 = x.ival;
        int nwords = (bitno + 1) >> 5;
        if (x.words == null) {
            if (nwords == 0) {
                nwords = 1;
            }
            data = new int[nwords];
            data[0] = i2;
            if (i2 < 0) {
                for (int j = 1; j < nwords; j++) {
                    data[j] = -1;
                }
            }
        } else {
            int nwords2 = (bitno + 1) >> 5;
            data = new int[(nwords2 > i2 ? nwords2 : i2)];
            int j2 = i2;
            while (true) {
                j2--;
                if (j2 < 0) {
                    break;
                }
                data[j2] = x.words[j2];
            }
            if (data[i2 - 1] < 0) {
                for (int j3 = i2; j3 < nwords2; j3++) {
                    data[j3] = -1;
                }
            }
        }
        return data;
    }

    public static IntNum setBitValue(IntNum intNum, int i, int newValue) {
        int oldValue;
        IntNum x = intNum;
        int bitno = i;
        int newValue2 = newValue & 1;
        int i2 = x.ival;
        if (x.words == null) {
            if (((i2 >> (bitno < 31 ? bitno : 31)) & 1) == newValue2) {
                return x;
            }
            if (bitno < 63) {
                return IntNum.make(((long) i2) ^ ((long) (1 << bitno)));
            }
        } else {
            int wordno = bitno >> 5;
            if (wordno >= i2) {
                oldValue = x.words[i2 + -1] < 0 ? 1 : 0;
            } else {
                oldValue = (x.words[wordno] >> bitno) & 1;
            }
            if (oldValue == newValue2) {
                return x;
            }
        }
        int[] data = dataBufferFor(x, bitno);
        int[] iArr = data;
        int i3 = bitno >> 5;
        iArr[i3] = iArr[i3] ^ (1 << (bitno & 31));
        return IntNum.make(data, data.length);
    }

    public static boolean test(IntNum intNum, int i) {
        IntNum x = intNum;
        int y = i;
        if (x.words == null) {
            return (x.ival & y) != 0;
        }
        return y < 0 || (x.words[0] & y) != 0;
    }

    public static boolean test(IntNum intNum, IntNum intNum2) {
        IntNum x = intNum;
        IntNum y = intNum2;
        if (y.words == null) {
            return test(x, y.ival);
        }
        if (x.words == null) {
            return test(y, x.ival);
        }
        if (x.ival < y.ival) {
            IntNum temp = x;
            x = y;
            y = temp;
        }
        for (int i = 0; i < y.ival; i++) {
            if ((x.words[i] & y.words[i]) != 0) {
                return true;
            }
        }
        return y.isNegative();
    }

    public static IntNum and(IntNum intNum, int i) {
        IntNum x = intNum;
        int y = i;
        if (x.words == null) {
            return IntNum.make(x.ival & y);
        }
        if (y >= 0) {
            return IntNum.make(x.words[0] & y);
        }
        int len = x.ival;
        int[] words = new int[len];
        words[0] = x.words[0] & y;
        while (true) {
            len--;
            if (len <= 0) {
                return IntNum.make(words, x.ival);
            }
            words[len] = x.words[len];
        }
    }

    public static IntNum and(IntNum intNum, IntNum intNum2) {
        IntNum x = intNum;
        IntNum y = intNum2;
        if (y.words == null) {
            return and(x, y.ival);
        }
        if (x.words == null) {
            return and(y, x.ival);
        }
        if (x.ival < y.ival) {
            IntNum temp = x;
            x = y;
            y = temp;
        }
        int len = y.isNegative() ? x.ival : y.ival;
        int[] words = new int[len];
        int i = 0;
        while (i < y.ival) {
            words[i] = x.words[i] & y.words[i];
            i++;
        }
        while (i < len) {
            words[i] = x.words[i];
            i++;
        }
        return IntNum.make(words, len);
    }

    public static IntNum ior(IntNum x, IntNum y) {
        return bitOp(7, x, y);
    }

    public static IntNum xor(IntNum x, IntNum y) {
        return bitOp(6, x, y);
    }

    public static IntNum not(IntNum x) {
        return bitOp(12, x, IntNum.zero());
    }

    public static int swappedOp(int op) {
        return "\u0000\u0001\u0004\u0005\u0002\u0003\u0006\u0007\b\t\f\r\n\u000b\u000e\u000f".charAt(op);
    }

    public static IntNum bitOp(int i, IntNum intNum, IntNum intNum2) {
        IntNum intNum3;
        int op = i;
        IntNum x = intNum;
        IntNum y = intNum2;
        switch (op) {
            case 0:
                return IntNum.zero();
            case 1:
                return and(x, y);
            case 3:
                return x;
            case 5:
                return y;
            case 15:
                return IntNum.minusOne();
            default:
                new IntNum();
                IntNum result = intNum3;
                setBitOp(result, op, x, y);
                return result.canonicalize();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009f, code lost:
        if ((r9 + 1) < r7) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a2, code lost:
        if (r5 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a4, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c8, code lost:
        if ((r9 + 1) < r7) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cb, code lost:
        if (r5 < 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cd, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f8, code lost:
        if ((r9 + 1) < r7) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fb, code lost:
        if (r5 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fd, code lost:
        r10 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011d, code lost:
        if ((r9 + 1) < r7) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x013f, code lost:
        if ((r9 + 1) < r7) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0142, code lost:
        if (r5 >= 0) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0144, code lost:
        r12 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0145, code lost:
        r10 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0148, code lost:
        r12 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0168, code lost:
        if ((r9 + 1) < r7) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x016b, code lost:
        if (r5 < 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016d, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0192, code lost:
        if ((r9 + 1) < r7) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0195, code lost:
        if (r5 < 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0197, code lost:
        r10 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01bc, code lost:
        if ((r9 + 1) < r7) goto L_0x019b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01bf, code lost:
        if (r5 < 0) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01c1, code lost:
        r12 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c2, code lost:
        r10 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01c5, code lost:
        r12 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01e6, code lost:
        if ((r9 + 1) < r7) goto L_0x01c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x020b, code lost:
        if ((r9 + 1) < r7) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x020e, code lost:
        if (r5 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0210, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x023e, code lost:
        if ((r9 + 1) < r7) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0241, code lost:
        if (r5 < 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0243, code lost:
        r10 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0268, code lost:
        if ((r9 + 1) < r7) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x026b, code lost:
        if (r5 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x026d, code lost:
        r10 = 2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setBitOp(gnu.math.IntNum r16, int r17, gnu.math.IntNum r18, gnu.math.IntNum r19) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r12 = r3
            int[] r12 = r12.words
            if (r12 != 0) goto L_0x004c
        L_0x000d:
            r12 = r3
            int[] r12 = r12.words
            if (r12 != 0) goto L_0x0066
            r12 = r3
            int r12 = r12.ival
            r5 = r12
            r12 = 1
            r7 = r12
        L_0x0018:
            r12 = r2
            int[] r12 = r12.words
            if (r12 != 0) goto L_0x0072
            r12 = r2
            int r12 = r12.ival
            r4 = r12
            r12 = 1
            r6 = r12
        L_0x0023:
            r12 = r6
            r13 = 1
            if (r12 <= r13) goto L_0x002c
            r12 = r0
            r13 = r6
            r12.realloc(r13)
        L_0x002c:
            r12 = r0
            int[] r12 = r12.words
            r8 = r12
            r12 = 0
            r9 = r12
            r12 = 0
            r10 = r12
            r12 = r1
            switch(r12) {
                case 0: goto L_0x007e;
                case 1: goto L_0x0096;
                case 2: goto L_0x00bc;
                case 3: goto L_0x00d1;
                case 4: goto L_0x00ec;
                case 5: goto L_0x0116;
                case 6: goto L_0x0136;
                case 7: goto L_0x015f;
                case 8: goto L_0x0186;
                case 9: goto L_0x01b0;
                case 10: goto L_0x01dc;
                case 11: goto L_0x01ff;
                case 12: goto L_0x0214;
                case 13: goto L_0x0232;
                case 14: goto L_0x025c;
                default: goto L_0x0038;
            }
        L_0x0038:
            r12 = -1
            r11 = r12
        L_0x003a:
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r6
            if (r12 != r13) goto L_0x0043
            r12 = 0
            r10 = r12
        L_0x0043:
            r12 = r10
            switch(r12) {
                case 0: goto L_0x0271;
                case 1: goto L_0x0286;
                case 2: goto L_0x029c;
                default: goto L_0x0047;
            }
        L_0x0047:
            r12 = r0
            r13 = r9
            r12.ival = r13
        L_0x004b:
            return
        L_0x004c:
            r12 = r2
            int[] r12 = r12.words
            if (r12 == 0) goto L_0x0059
            r12 = r2
            int r12 = r12.ival
            r13 = r3
            int r13 = r13.ival
            if (r12 >= r13) goto L_0x000d
        L_0x0059:
            r12 = r2
            r4 = r12
            r12 = r3
            r2 = r12
            r12 = r4
            r3 = r12
            r12 = r1
            int r12 = swappedOp(r12)
            r1 = r12
            goto L_0x000d
        L_0x0066:
            r12 = r3
            int[] r12 = r12.words
            r13 = 0
            r12 = r12[r13]
            r5 = r12
            r12 = r3
            int r12 = r12.ival
            r7 = r12
            goto L_0x0018
        L_0x0072:
            r12 = r2
            int[] r12 = r12.words
            r13 = 0
            r12 = r12[r13]
            r4 = r12
            r12 = r2
            int r12 = r12.ival
            r6 = r12
            goto L_0x0023
        L_0x007e:
            r12 = 0
            r11 = r12
            goto L_0x003a
        L_0x0081:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x0096:
            r12 = r4
            r13 = r5
            r12 = r12 & r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x0081
            r12 = r5
            if (r12 >= 0) goto L_0x003a
            r12 = 1
            r10 = r12
            goto L_0x003a
        L_0x00a7:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x00bc:
            r12 = r4
            r13 = r5
            r14 = -1
            r13 = r13 ^ -1
            r12 = r12 & r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x00a7
            r12 = r5
            if (r12 < 0) goto L_0x003a
            r12 = 1
            r10 = r12
            goto L_0x003a
        L_0x00d1:
            r12 = r4
            r11 = r12
            r12 = 1
            r10 = r12
            goto L_0x003a
        L_0x00d7:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x00ec:
            r12 = r4
            r13 = -1
            r12 = r12 ^ -1
            r13 = r5
            r12 = r12 & r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x00d7
            r12 = r5
            if (r12 >= 0) goto L_0x003a
            r12 = 2
            r10 = r12
            goto L_0x003a
        L_0x0101:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x0116:
            r12 = r5
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x0101
            goto L_0x003a
        L_0x0121:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x0136:
            r12 = r4
            r13 = r5
            r12 = r12 ^ r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x0121
            r12 = r5
            if (r12 >= 0) goto L_0x0148
            r12 = 2
        L_0x0145:
            r10 = r12
            goto L_0x003a
        L_0x0148:
            r12 = 1
            goto L_0x0145
        L_0x014a:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x015f:
            r12 = r4
            r13 = r5
            r12 = r12 | r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x014a
            r12 = r5
            if (r12 < 0) goto L_0x003a
            r12 = 1
            r10 = r12
            goto L_0x003a
        L_0x0171:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x0186:
            r12 = r4
            r13 = r5
            r12 = r12 | r13
            r13 = -1
            r12 = r12 ^ -1
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x0171
            r12 = r5
            if (r12 < 0) goto L_0x003a
            r12 = 2
            r10 = r12
            goto L_0x003a
        L_0x019b:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x01b0:
            r12 = r4
            r13 = r5
            r12 = r12 ^ r13
            r13 = -1
            r12 = r12 ^ -1
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x019b
            r12 = r5
            if (r12 < 0) goto L_0x01c5
            r12 = 2
        L_0x01c2:
            r10 = r12
            goto L_0x003a
        L_0x01c5:
            r12 = 1
            goto L_0x01c2
        L_0x01c7:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x01dc:
            r12 = r5
            r13 = -1
            r12 = r12 ^ -1
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x01c7
            goto L_0x003a
        L_0x01ea:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x01ff:
            r12 = r4
            r13 = r5
            r14 = -1
            r13 = r13 ^ -1
            r12 = r12 | r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x01ea
            r12 = r5
            if (r12 >= 0) goto L_0x003a
            r12 = 1
            r10 = r12
            goto L_0x003a
        L_0x0214:
            r12 = r4
            r13 = -1
            r12 = r12 ^ -1
            r11 = r12
            r12 = 2
            r10 = r12
            goto L_0x003a
        L_0x021d:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x0232:
            r12 = r4
            r13 = -1
            r12 = r12 ^ -1
            r13 = r5
            r12 = r12 | r13
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x021d
            r12 = r5
            if (r12 < 0) goto L_0x003a
            r12 = 2
            r10 = r12
            goto L_0x003a
        L_0x0247:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            r12 = r2
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r4 = r12
            r12 = r3
            int[] r12 = r12.words
            r13 = r9
            r12 = r12[r13]
            r5 = r12
        L_0x025c:
            r12 = r4
            r13 = r5
            r12 = r12 & r13
            r13 = -1
            r12 = r12 ^ -1
            r11 = r12
            r12 = r9
            r13 = 1
            int r12 = r12 + 1
            r13 = r7
            if (r12 < r13) goto L_0x0247
            r12 = r5
            if (r12 >= 0) goto L_0x003a
            r12 = 2
            r10 = r12
            goto L_0x003a
        L_0x0271:
            r12 = r9
            if (r12 != 0) goto L_0x027d
            r12 = r8
            if (r12 != 0) goto L_0x027d
            r12 = r0
            r13 = r11
            r12.ival = r13
            goto L_0x004b
        L_0x027d:
            r12 = r8
            r13 = r9
            int r9 = r9 + 1
            r14 = r11
            r12[r13] = r14
            goto L_0x0047
        L_0x0286:
            r12 = r8
            r13 = r9
            r14 = r11
            r12[r13] = r14
        L_0x028b:
            int r9 = r9 + 1
            r12 = r9
            r13 = r6
            if (r12 >= r13) goto L_0x0047
            r12 = r8
            r13 = r9
            r14 = r2
            int[] r14 = r14.words
            r15 = r9
            r14 = r14[r15]
            r12[r13] = r14
            goto L_0x028b
        L_0x029c:
            r12 = r8
            r13 = r9
            r14 = r11
            r12[r13] = r14
        L_0x02a1:
            int r9 = r9 + 1
            r12 = r9
            r13 = r6
            if (r12 >= r13) goto L_0x0047
            r12 = r8
            r13 = r9
            r14 = r2
            int[] r14 = r14.words
            r15 = r9
            r14 = r14[r15]
            r15 = -1
            r14 = r14 ^ -1
            r12[r13] = r14
            goto L_0x02a1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.BitOps.setBitOp(gnu.math.IntNum, int, gnu.math.IntNum, gnu.math.IntNum):void");
    }

    public static IntNum extract(IntNum intNum, int i, int i2) {
        int x_len;
        long l;
        IntNum x = intNum;
        int startBit = i;
        int endBit = i2;
        if (endBit < 32) {
            return IntNum.make(((x.words == null ? x.ival : x.words[0]) & ((-1 << endBit) ^ -1)) >> startBit);
        }
        if (x.words != null) {
            x_len = x.ival;
        } else if (x.ival >= 0) {
            return IntNum.make(startBit >= 31 ? 0 : x.ival >> startBit);
        } else {
            x_len = 1;
        }
        boolean neg = x.isNegative();
        if (endBit > 32 * x_len) {
            endBit = 32 * x_len;
            if (!neg && startBit == 0) {
                return x;
            }
        } else {
            x_len = (endBit + 31) >> 5;
        }
        int length = endBit - startBit;
        if (length < 64) {
            if (x.words == null) {
                l = (long) (x.ival >> (startBit >= 32 ? 31 : startBit));
            } else {
                l = MPN.rshift_long(x.words, x_len, startBit);
            }
            return IntNum.make(l & ((-1 << length) ^ -1));
        }
        int startWord = startBit >> 5;
        int[] buf = new int[(((endBit >> 5) + 1) - startWord)];
        if (x.words == null) {
            buf[0] = startBit >= 32 ? -1 : x.ival >> startBit;
        } else {
            MPN.rshift0(buf, x.words, startWord, x_len - startWord, startBit & 31);
        }
        int x_len2 = length >> 5;
        int[] iArr = buf;
        int i3 = x_len2;
        iArr[i3] = iArr[i3] & ((-1 << length) ^ -1);
        return IntNum.make(buf, x_len2 + 1);
    }

    public static int lowestBitSet(int i) {
        int i2 = i;
        if (i2 == 0) {
            return -1;
        }
        int index = 0;
        while ((i2 & 255) == 0) {
            i2 >>>= 8;
            index += 8;
        }
        while ((i2 & 3) == 0) {
            i2 >>>= 2;
            index += 2;
        }
        if ((i2 & 1) == 0) {
            index++;
        }
        return index;
    }

    public static int lowestBitSet(IntNum intNum) {
        IntNum x = intNum;
        int[] x_words = x.words;
        if (x_words == null) {
            return lowestBitSet(x.ival);
        }
        int x_len = x.ival;
        while (0 < x_len) {
            int b = lowestBitSet(x_words[0]);
            if (b >= 0) {
                return (32 * 0) + b;
            }
        }
        return -1;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int bitCount(int r6) {
        /*
            r0 = r6
            r2 = 0
            r1 = r2
        L_0x0003:
            r2 = r0
            if (r2 == 0) goto L_0x0018
            r2 = r1
            byte[] r3 = bit4_count
            r4 = r0
            r5 = 15
            r4 = r4 & 15
            byte r3 = r3[r4]
            int r2 = r2 + r3
            r1 = r2
            r2 = r0
            r3 = 4
            int r2 = r2 >>> 4
            r0 = r2
            goto L_0x0003
        L_0x0018:
            r2 = r1
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.BitOps.bitCount(int):int");
    }

    public static int bitCount(int[] iArr, int i) {
        int[] x = iArr;
        int len = i;
        int i2 = 0;
        while (true) {
            int count = i2;
            len--;
            if (len < 0) {
                return count;
            }
            i2 = count + bitCount(x[len]);
        }
    }

    public static int bitCount(IntNum intNum) {
        int x_len;
        int i;
        IntNum x = intNum;
        int[] x_words = x.words;
        if (x_words == null) {
            x_len = 1;
            i = bitCount(x.ival);
        } else {
            x_len = x.ival;
            i = bitCount(x_words, x_len);
        }
        return x.isNegative() ? (x_len * 32) - i : i;
    }

    public static IntNum reverseBits(IntNum intNum, int i, int i2) {
        int wi;
        IntNum x = intNum;
        int start = i;
        int end = i2;
        int ival = x.ival;
        if (x.words != null || end >= 63) {
            int[] data = dataBufferFor(x, end - 1);
            int i3 = start;
            for (int j = end - 1; i3 < j; j--) {
                int ii = i3 >> 5;
                int jj = j >> 5;
                int wi2 = data[ii];
                int biti = (wi2 >> i3) & 1;
                if (ii == jj) {
                    wi = ((int) (((long) wi2) & (((1 << i3) | (1 << j)) ^ -1))) | (biti << j) | (((wi2 >> j) & 1) << i3);
                } else {
                    int wj = data[jj];
                    wi = (wi2 & ((1 << (i3 & 31)) ^ -1)) | (((wj >> (j & 31)) & 1) << (i3 & 31));
                    data[jj] = (wj & ((1 << (j & 31)) ^ -1)) | (biti << (j & 31));
                }
                data[ii] = wi;
                i3++;
            }
            return IntNum.make(data, data.length);
        }
        long w = (long) ival;
        int i4 = start;
        for (int j2 = end - 1; i4 < j2; j2--) {
            w = (w & (((1 << i4) | (1 << j2)) ^ -1)) | (((w >> i4) & 1) << j2) | (((w >> j2) & 1) << i4);
            i4++;
        }
        return IntNum.make(w);
    }
}
