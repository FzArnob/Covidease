package gnu.kawa.functions;

import gnu.mapping.Procedure;

public class DivideOp extends ArithOp {
    public static final DivideOp $Sl;
    public static final DivideOp div;
    public static final DivideOp div0;
    public static final DivideOp idiv;
    public static final DivideOp mod;
    public static final DivideOp mod0;
    public static final DivideOp modulo;
    public static final DivideOp quotient;
    public static final DivideOp remainder;
    int rounding_mode;

    public int getRoundingMode() {
        return this.rounding_mode;
    }

    static {
        DivideOp divideOp;
        DivideOp divideOp2;
        DivideOp divideOp3;
        DivideOp divideOp4;
        DivideOp divideOp5;
        DivideOp divideOp6;
        DivideOp divideOp7;
        DivideOp divideOp8;
        DivideOp divideOp9;
        new DivideOp("/", 4);
        $Sl = divideOp;
        new DivideOp("idiv", 7);
        idiv = divideOp2;
        new DivideOp("quotient", 6);
        quotient = divideOp3;
        new DivideOp("remainder", 8);
        remainder = divideOp4;
        new DivideOp("modulo", 8);
        modulo = divideOp5;
        new DivideOp("div", 6);
        div = divideOp6;
        new DivideOp("mod", 8);
        mod = divideOp7;
        new DivideOp("div0", 6);
        div0 = divideOp8;
        new DivideOp("mod0", 8);
        mod0 = divideOp9;
        idiv.rounding_mode = 3;
        quotient.rounding_mode = 3;
        remainder.rounding_mode = 3;
        modulo.rounding_mode = 1;
        div.rounding_mode = 5;
        mod.rounding_mode = 5;
        div0.rounding_mode = 4;
        mod0.rounding_mode = 4;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DivideOp(String name, int op) {
        super(name, op);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forDiv");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0261, code lost:
        r21 = java.math.RoundingMode.HALF_EVEN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0265, code lost:
        new java.math.MathContext(0, r21);
        r22 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x027e, code lost:
        switch(r3.f61op) {
            case 4: goto L_0x02a2;
            case 5: goto L_0x0281;
            case 6: goto L_0x02ad;
            case 7: goto L_0x02ba;
            case 8: goto L_0x02d7;
            default: goto L_0x0281;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x02a2, code lost:
        r6 = r18.divide(r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02ad, code lost:
        r6 = r18.divideToIntegralValue(r19, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02ba, code lost:
        r6 = r18.divideToIntegralValue(r19, r22).toBigInteger();
        r11 = 3;
        r7 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02d7, code lost:
        r6 = r18.remainder(r19, r22);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object applyN(java.lang.Object[] r38) throws java.lang.Throwable {
        /*
            r37 = this;
            r3 = r37
            r4 = r38
            r30 = r4
            r0 = r30
            int r0 = r0.length
            r30 = r0
            r5 = r30
            r30 = r5
            if (r30 != 0) goto L_0x0018
            gnu.math.IntNum r30 = gnu.math.IntNum.one()
            r3 = r30
        L_0x0017:
            return r3
        L_0x0018:
            r30 = r4
            r31 = 0
            r30 = r30[r31]
            java.lang.Number r30 = (java.lang.Number) r30
            r6 = r30
            r30 = r5
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x003b
            r30 = r3
            gnu.math.IntNum r31 = gnu.math.IntNum.one()
            r32 = r6
            java.lang.Object r30 = r30.apply2(r31, r32)
            r3 = r30
            goto L_0x0017
        L_0x003b:
            r30 = r6
            int r30 = gnu.kawa.functions.Arithmetic.classifyValue(r30)
            r7 = r30
            r30 = 1
            r8 = r30
        L_0x0047:
            r30 = r8
            r31 = r5
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x043e
            r30 = r4
            r31 = r8
            r30 = r30[r31]
            r9 = r30
            r30 = r9
            int r30 = gnu.kawa.functions.Arithmetic.classifyValue(r30)
            r10 = r30
            r30 = r7
            r31 = r10
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x011d
            r30 = r10
        L_0x006d:
            r7 = r30
            r30 = r7
            r11 = r30
            r30 = r7
            r31 = 4
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x00ac
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 4: goto L_0x0121;
                case 5: goto L_0x0121;
                default: goto L_0x0088;
            }
        L_0x0088:
            r30 = r3
            r0 = r30
            int r0 = r0.rounding_mode
            r30 = r0
            r31 = 3
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x012f
            r30 = r7
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x00ac
            r30 = r7
            r31 = 2
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x012f
        L_0x00ac:
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            r31 = 5
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x0135
            r30 = r7
            r31 = 10
            r0 = r30
            r1 = r31
            if (r0 > r1) goto L_0x0135
            r30 = 10
            r11 = r30
            r30 = r7
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x00e2
            r30 = r7
            r31 = 7
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x00e2
            r30 = 9
            r7 = r30
        L_0x00e2:
            r30 = r11
            switch(r30) {
                case 1: goto L_0x0162;
                case 2: goto L_0x01a9;
                case 3: goto L_0x00e7;
                case 4: goto L_0x01de;
                case 5: goto L_0x0244;
                case 6: goto L_0x00e7;
                case 7: goto L_0x00e7;
                case 8: goto L_0x00e7;
                case 9: goto L_0x02e4;
                default: goto L_0x00e7;
            }
        L_0x00e7:
            r30 = r6
            gnu.math.Numeric r30 = gnu.kawa.functions.Arithmetic.asNumeric(r30)
            r27 = r30
            r30 = r9
            gnu.math.Numeric r30 = gnu.kawa.functions.Arithmetic.asNumeric(r30)
            r28 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x0377
            r30 = r28
            boolean r30 = r30.isZero()
            if (r30 == 0) goto L_0x0377
            r30 = r28
            boolean r30 = r30.isExact()
            if (r30 == 0) goto L_0x036f
            r30 = r27
        L_0x0119:
            r3 = r30
            goto L_0x0017
        L_0x011d:
            r30 = r7
            goto L_0x006d
        L_0x0121:
            r30 = 4
            r36 = r30
            r30 = r36
            r31 = r36
            r7 = r31
            r11 = r30
            goto L_0x00ac
        L_0x012f:
            r30 = 4
            r11 = r30
            goto L_0x00ac
        L_0x0135:
            r30 = r11
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x0149
            r30 = r11
            r31 = 7
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x00e2
        L_0x0149:
            r30 = 9
            r11 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            r31 = 7
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x00e2
            r30 = r11
            r7 = r30
            goto L_0x00e2
        L_0x0162:
            r30 = r6
            int r30 = gnu.kawa.functions.Arithmetic.asInt(r30)
            r12 = r30
            r30 = r9
            int r30 = gnu.kawa.functions.Arithmetic.asInt(r30)
            r13 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 8: goto L_0x01a0;
                default: goto L_0x017d;
            }
        L_0x017d:
            r30 = r12
            r31 = r13
            int r30 = r30 / r31
            r12 = r30
        L_0x0185:
            r30 = r12
            java.lang.Integer r30 = java.lang.Integer.valueOf(r30)
            r6 = r30
        L_0x018d:
            r30 = r7
            r31 = r11
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x019c
            r30 = r7
            switch(r30) {
                case 1: goto L_0x03fc;
                case 2: goto L_0x040a;
                case 3: goto L_0x0434;
                case 4: goto L_0x019c;
                case 5: goto L_0x019c;
                case 6: goto L_0x019c;
                case 7: goto L_0x0418;
                case 8: goto L_0x0426;
                default: goto L_0x019c;
            }
        L_0x019c:
            int r8 = r8 + 1
            goto L_0x0047
        L_0x01a0:
            r30 = r12
            r31 = r13
            int r30 = r30 % r31
            r12 = r30
            goto L_0x0185
        L_0x01a9:
            r30 = r6
            long r30 = gnu.kawa.functions.Arithmetic.asLong(r30)
            r14 = r30
            r30 = r9
            long r30 = gnu.kawa.functions.Arithmetic.asLong(r30)
            r16 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 8: goto L_0x01d5;
                default: goto L_0x01c4;
            }
        L_0x01c4:
            r30 = r14
            r32 = r16
            long r30 = r30 / r32
            r14 = r30
        L_0x01cc:
            r30 = r14
            java.lang.Long r30 = java.lang.Long.valueOf(r30)
            r6 = r30
            goto L_0x018d
        L_0x01d5:
            r30 = r14
            r32 = r16
            long r30 = r30 % r32
            r14 = r30
            goto L_0x01cc
        L_0x01de:
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 4: goto L_0x021c;
                case 5: goto L_0x01e9;
                case 6: goto L_0x01ea;
                case 7: goto L_0x01ea;
                case 8: goto L_0x0203;
                default: goto L_0x01e9;
            }
        L_0x01e9:
            goto L_0x018d
        L_0x01ea:
            r30 = r6
            gnu.math.IntNum r30 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r30)
            r31 = r9
            gnu.math.IntNum r31 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r31)
            r32 = r3
            int r32 = r32.getRoundingMode()
            gnu.math.IntNum r30 = gnu.math.IntNum.quotient(r30, r31, r32)
            r6 = r30
            goto L_0x01e9
        L_0x0203:
            r30 = r6
            gnu.math.IntNum r30 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r30)
            r31 = r9
            gnu.math.IntNum r31 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r31)
            r32 = r3
            int r32 = r32.getRoundingMode()
            gnu.math.IntNum r30 = gnu.math.IntNum.remainder(r30, r31, r32)
            r6 = r30
            goto L_0x01e9
        L_0x021c:
            r30 = r6
            gnu.math.IntNum r30 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r30)
            r31 = r9
            gnu.math.IntNum r31 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r31)
            gnu.math.RatNum r30 = gnu.math.RatNum.make(r30, r31)
            r6 = r30
            r30 = r6
            r0 = r30
            boolean r0 = r0 instanceof gnu.math.IntNum
            r30 = r0
            if (r30 == 0) goto L_0x0241
            r30 = 4
        L_0x023a:
            r7 = r30
            r30 = r7
            r11 = r30
            goto L_0x01e9
        L_0x0241:
            r30 = 6
            goto L_0x023a
        L_0x0244:
            r30 = r6
            java.math.BigDecimal r30 = gnu.kawa.functions.Arithmetic.asBigDecimal(r30)
            r18 = r30
            r30 = r9
            java.math.BigDecimal r30 = gnu.kawa.functions.Arithmetic.asBigDecimal(r30)
            r19 = r30
            r30 = 0
            r20 = r30
            r30 = r3
            int r30 = r30.getRoundingMode()
            switch(r30) {
                case 1: goto L_0x0283;
                case 2: goto L_0x0288;
                case 3: goto L_0x028d;
                case 4: goto L_0x0261;
                case 5: goto L_0x0292;
                default: goto L_0x0261;
            }
        L_0x0261:
            java.math.RoundingMode r30 = java.math.RoundingMode.HALF_EVEN
            r21 = r30
        L_0x0265:
            java.math.MathContext r30 = new java.math.MathContext
            r36 = r30
            r30 = r36
            r31 = r36
            r32 = r20
            r33 = r21
            r31.<init>(r32, r33)
            r22 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 4: goto L_0x02a2;
                case 5: goto L_0x0281;
                case 6: goto L_0x02ad;
                case 7: goto L_0x02ba;
                case 8: goto L_0x02d7;
                default: goto L_0x0281;
            }
        L_0x0281:
            goto L_0x018d
        L_0x0283:
            java.math.RoundingMode r30 = java.math.RoundingMode.FLOOR
            r21 = r30
            goto L_0x0265
        L_0x0288:
            java.math.RoundingMode r30 = java.math.RoundingMode.CEILING
            r21 = r30
            goto L_0x0265
        L_0x028d:
            java.math.RoundingMode r30 = java.math.RoundingMode.DOWN
            r21 = r30
            goto L_0x0265
        L_0x0292:
            r30 = r19
            int r30 = r30.signum()
            if (r30 >= 0) goto L_0x029f
            java.math.RoundingMode r30 = java.math.RoundingMode.CEILING
        L_0x029c:
            r21 = r30
            goto L_0x0261
        L_0x029f:
            java.math.RoundingMode r30 = java.math.RoundingMode.FLOOR
            goto L_0x029c
        L_0x02a2:
            r30 = r18
            r31 = r19
            java.math.BigDecimal r30 = r30.divide(r31)
            r6 = r30
            goto L_0x0281
        L_0x02ad:
            r30 = r18
            r31 = r19
            r32 = r22
            java.math.BigDecimal r30 = r30.divideToIntegralValue(r31, r32)
            r6 = r30
            goto L_0x0281
        L_0x02ba:
            r30 = r18
            r31 = r19
            r32 = r22
            java.math.BigDecimal r30 = r30.divideToIntegralValue(r31, r32)
            java.math.BigInteger r30 = r30.toBigInteger()
            r6 = r30
            r30 = 3
            r36 = r30
            r30 = r36
            r31 = r36
            r11 = r31
            r7 = r30
            goto L_0x0281
        L_0x02d7:
            r30 = r18
            r31 = r19
            r32 = r22
            java.math.BigDecimal r30 = r30.remainder(r31, r32)
            r6 = r30
            goto L_0x0281
        L_0x02e4:
            r30 = r6
            double r30 = gnu.kawa.functions.Arithmetic.asDouble(r30)
            r23 = r30
            r30 = r9
            double r30 = gnu.kawa.functions.Arithmetic.asDouble(r30)
            r25 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 4: goto L_0x0301;
                case 5: goto L_0x0301;
                case 6: goto L_0x030e;
                case 7: goto L_0x0325;
                case 8: goto L_0x0344;
                default: goto L_0x02ff;
            }
        L_0x02ff:
            goto L_0x018d
        L_0x0301:
            r30 = r23
            r32 = r25
            double r30 = r30 / r32
            gnu.math.DFloNum r30 = gnu.math.DFloNum.make(r30)
            r6 = r30
            goto L_0x02ff
        L_0x030e:
            r30 = r23
            r32 = r25
            double r30 = r30 / r32
            r32 = r3
            int r32 = r32.getRoundingMode()
            double r30 = gnu.math.RealNum.toInt(r30, r32)
            java.lang.Double r30 = java.lang.Double.valueOf(r30)
            r6 = r30
            goto L_0x02ff
        L_0x0325:
            r30 = r23
            r32 = r25
            double r30 = r30 / r32
            r32 = r3
            int r32 = r32.getRoundingMode()
            gnu.math.IntNum r30 = gnu.math.RealNum.toExactInt(r30, r32)
            r6 = r30
            r30 = 4
            r36 = r30
            r30 = r36
            r31 = r36
            r11 = r31
            r7 = r30
            goto L_0x02ff
        L_0x0344:
            r30 = r25
            r32 = 0
            int r30 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r30 == 0) goto L_0x0366
            r30 = r23
            r32 = r23
            r34 = r25
            double r32 = r32 / r34
            r34 = r3
            int r34 = r34.getRoundingMode()
            double r32 = gnu.math.RealNum.toInt(r32, r34)
            r34 = r25
            double r32 = r32 * r34
            double r30 = r30 - r32
            r23 = r30
        L_0x0366:
            r30 = r23
            gnu.math.DFloNum r30 = gnu.math.DFloNum.make(r30)
            r6 = r30
            goto L_0x02ff
        L_0x036f:
            r30 = r27
            gnu.math.Numeric r30 = r30.toInexact()
            goto L_0x0119
        L_0x0377:
            r30 = r27
            r31 = r28
            gnu.math.Numeric r30 = r30.div(r31)
            r29 = r30
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x03ad
            r30 = r27
            r31 = r29
            gnu.math.RealNum r31 = (gnu.math.RealNum) r31
            r32 = r3
            int r32 = r32.getRoundingMode()
            gnu.math.RealNum r31 = r31.toInt(r32)
            r32 = r28
            gnu.math.Numeric r31 = r31.mul(r32)
            gnu.math.Numeric r30 = r30.sub(r31)
            r29 = r30
        L_0x03ad:
            r30 = r3
            r0 = r30
            int r0 = r0.f61op
            r30 = r0
            switch(r30) {
                case 5: goto L_0x03f2;
                case 6: goto L_0x03de;
                case 7: goto L_0x03be;
                default: goto L_0x03b8;
            }
        L_0x03b8:
            r30 = r29
            r6 = r30
            goto L_0x018d
        L_0x03be:
            r30 = r29
            gnu.math.RealNum r30 = (gnu.math.RealNum) r30
            r31 = r3
            r0 = r31
            int r0 = r0.rounding_mode
            r31 = r0
            gnu.math.IntNum r30 = r30.toExactInt((int) r31)
            r6 = r30
            r30 = 4
            r36 = r30
            r30 = r36
            r31 = r36
            r7 = r31
            r11 = r30
            goto L_0x018d
        L_0x03de:
            r30 = r29
            gnu.math.RealNum r30 = (gnu.math.RealNum) r30
            r31 = r3
            r0 = r31
            int r0 = r0.rounding_mode
            r31 = r0
            gnu.math.RealNum r30 = r30.toInt(r31)
            r6 = r30
            goto L_0x018d
        L_0x03f2:
            r30 = r29
            gnu.math.Numeric r30 = r30.toInexact()
            r6 = r30
            goto L_0x018d
        L_0x03fc:
            r30 = r6
            int r30 = r30.intValue()
            java.lang.Integer r30 = java.lang.Integer.valueOf(r30)
            r6 = r30
            goto L_0x019c
        L_0x040a:
            r30 = r6
            long r30 = r30.longValue()
            java.lang.Long r30 = java.lang.Long.valueOf(r30)
            r6 = r30
            goto L_0x019c
        L_0x0418:
            r30 = r6
            float r30 = r30.floatValue()
            java.lang.Float r30 = java.lang.Float.valueOf(r30)
            r6 = r30
            goto L_0x019c
        L_0x0426:
            r30 = r6
            double r30 = r30.doubleValue()
            java.lang.Double r30 = java.lang.Double.valueOf(r30)
            r6 = r30
            goto L_0x019c
        L_0x0434:
            r30 = r6
            java.math.BigInteger r30 = gnu.kawa.functions.Arithmetic.asBigInteger(r30)
            r6 = r30
            goto L_0x019c
        L_0x043e:
            r30 = r6
            r3 = r30
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.DivideOp.applyN(java.lang.Object[]):java.lang.Object");
    }

    public int numArgs() {
        return this.f61op == 4 ? -4095 : 8194;
    }
}
