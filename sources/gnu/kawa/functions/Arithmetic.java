package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic {
    public static final int BIGDECIMAL_CODE = 5;
    public static final int BIGINTEGER_CODE = 3;
    public static final int DOUBLE_CODE = 8;
    public static final int FLOAT_CODE = 7;
    public static final int FLONUM_CODE = 9;
    public static final int INTNUM_CODE = 4;
    public static final int INT_CODE = 1;
    public static final int LONG_CODE = 2;
    public static final int NUMERIC_CODE = 11;
    public static final int RATNUM_CODE = 6;
    public static final int REALNUM_CODE = 10;
    static LangObjType typeDFloNum = LangObjType.dflonumType;
    static LangObjType typeIntNum = LangObjType.integerType;
    static ClassType typeNumber = ClassType.make("java.lang.Number");
    static ClassType typeNumeric = ClassType.make("gnu.math.Numeric");
    static LangObjType typeRatNum = LangObjType.rationalType;
    static LangObjType typeRealNum = LangObjType.realType;

    public Arithmetic() {
    }

    public static int classifyValue(Object obj) {
        Object value = obj;
        if (value instanceof Numeric) {
            if (value instanceof IntNum) {
                return 4;
            }
            if (value instanceof RatNum) {
                return 6;
            }
            if (value instanceof DFloNum) {
                return 9;
            }
            if (value instanceof RealNum) {
                return 10;
            }
            return 11;
        } else if (!(value instanceof Number)) {
            return -1;
        } else {
            if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
                return 1;
            }
            if (value instanceof Long) {
                return 2;
            }
            if (value instanceof Float) {
                return 7;
            }
            if (value instanceof Double) {
                return 8;
            }
            if (value instanceof BigInteger) {
                return 3;
            }
            if (value instanceof BigDecimal) {
                return 5;
            }
            return -1;
        }
    }

    public static Type kindType(int kind) {
        switch (kind) {
            case 1:
                return LangPrimType.intType;
            case 2:
                return LangPrimType.longType;
            case 3:
                return ClassType.make("java.math.BigInteger");
            case 4:
                return typeIntNum;
            case 5:
                return ClassType.make("java.math.BigDecimal");
            case 6:
                return typeRatNum;
            case 7:
                return LangPrimType.floatType;
            case 8:
                return LangPrimType.doubleType;
            case 9:
                return typeDFloNum;
            case 10:
                return typeRealNum;
            case 11:
                return typeNumeric;
            default:
                return Type.pointer_type;
        }
    }

    public static int classifyType(Type type) {
        Type type2 = type;
        if (type2 instanceof PrimType) {
            char sig = type2.getSignature().charAt(0);
            if (sig == 'V' || sig == 'Z' || sig == 'C') {
                return 0;
            }
            if (sig == 'D') {
                return 8;
            }
            if (sig == 'F') {
                return 7;
            }
            if (sig == 'J') {
                return 2;
            }
            return 1;
        }
        String tname = type2.getName();
        if (type2.isSubtype(typeIntNum)) {
            return 4;
        }
        if (type2.isSubtype(typeRatNum)) {
            return 6;
        }
        if (type2.isSubtype(typeDFloNum)) {
            return 9;
        }
        if ("java.lang.Double".equals(tname)) {
            return 8;
        }
        if ("java.lang.Float".equals(tname)) {
            return 7;
        }
        if ("java.lang.Long".equals(tname)) {
            return 2;
        }
        if ("java.lang.Integer".equals(tname) || "java.lang.Short".equals(tname) || "java.lang.Byte".equals(tname)) {
            return 1;
        }
        if ("java.math.BigInteger".equals(tname)) {
            return 3;
        }
        if ("java.math.BigDecimal".equals(tname)) {
            return 5;
        }
        if (type2.isSubtype(typeRealNum)) {
            return 10;
        }
        if (type2.isSubtype(typeNumeric)) {
            return 11;
        }
        return 0;
    }

    public static int asInt(Object value) {
        return ((Number) value).intValue();
    }

    public static long asLong(Object value) {
        return ((Number) value).longValue();
    }

    public static float asFloat(Object value) {
        return ((Number) value).floatValue();
    }

    public static double asDouble(Object value) {
        return ((Number) value).doubleValue();
    }

    public static BigInteger asBigInteger(Object obj) {
        BigInteger bigInteger;
        Object value = obj;
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if (!(value instanceof IntNum)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        new BigInteger(value.toString());
        return bigInteger;
    }

    public static IntNum asIntNum(BigDecimal value) {
        return IntNum.valueOf(value.toBigInteger().toString(), 10);
    }

    public static IntNum asIntNum(BigInteger value) {
        return IntNum.valueOf(value.toString(), 10);
    }

    public static IntNum asIntNum(Object obj) {
        Object value = obj;
        if (value instanceof IntNum) {
            return (IntNum) value;
        }
        if (value instanceof BigInteger) {
            return IntNum.valueOf(value.toString(), 10);
        }
        if (value instanceof BigDecimal) {
            return asIntNum((BigDecimal) value);
        }
        return IntNum.make(((Number) value).longValue());
    }

    public static BigDecimal asBigDecimal(Object obj) {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        Object value = obj;
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            new BigDecimal((BigInteger) value);
            return bigDecimal2;
        } else if ((value instanceof Long) || (value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
            return BigDecimal.valueOf(((Number) value).longValue());
        } else {
            new BigDecimal(value.toString());
            return bigDecimal;
        }
    }

    public static RatNum asRatNum(Object obj) {
        Object value = obj;
        if (value instanceof RatNum) {
            return (RatNum) value;
        }
        if (value instanceof BigInteger) {
            return IntNum.valueOf(value.toString(), 10);
        }
        if (value instanceof BigDecimal) {
            return RatNum.valueOf((BigDecimal) value);
        }
        return IntNum.make(((Number) value).longValue());
    }

    public static Numeric asNumeric(Object obj) {
        Object value = obj;
        Numeric n = Numeric.asNumericOrNull(value);
        return n != null ? n : (Numeric) value;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005b, code lost:
        if (r2 != 10) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006b, code lost:
        if (r2 != 10) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return asNumeric(r1).toString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return java.lang.Float.toString(asFloat(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return java.lang.Double.toString(asDouble(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(java.lang.Object r7, int r8) {
        /*
            r1 = r7
            r2 = r8
            r4 = r1
            int r4 = classifyValue(r4)
            r3 = r4
            r4 = r3
            switch(r4) {
                case 1: goto L_0x0018;
                case 2: goto L_0x0024;
                case 3: goto L_0x0030;
                case 4: goto L_0x003c;
                case 5: goto L_0x0048;
                case 6: goto L_0x000c;
                case 7: goto L_0x0058;
                case 8: goto L_0x0068;
                case 9: goto L_0x0068;
                default: goto L_0x000c;
            }
        L_0x000c:
            r4 = r1
            gnu.math.Numeric r4 = asNumeric(r4)
            r5 = r2
            java.lang.String r4 = r4.toString(r5)
            r1 = r4
        L_0x0017:
            return r1
        L_0x0018:
            r4 = r1
            int r4 = asInt(r4)
            r5 = r2
            java.lang.String r4 = java.lang.Integer.toString(r4, r5)
            r1 = r4
            goto L_0x0017
        L_0x0024:
            r4 = r1
            long r4 = asLong(r4)
            r6 = r2
            java.lang.String r4 = java.lang.Long.toString(r4, r6)
            r1 = r4
            goto L_0x0017
        L_0x0030:
            r4 = r1
            java.math.BigInteger r4 = asBigInteger(r4)
            r5 = r2
            java.lang.String r4 = r4.toString(r5)
            r1 = r4
            goto L_0x0017
        L_0x003c:
            r4 = r1
            gnu.math.IntNum r4 = asIntNum((java.lang.Object) r4)
            r5 = r2
            java.lang.String r4 = r4.toString(r5)
            r1 = r4
            goto L_0x0017
        L_0x0048:
            r4 = r2
            r5 = 10
            if (r4 != r5) goto L_0x0058
            r4 = r1
            java.math.BigDecimal r4 = asBigDecimal(r4)
            java.lang.String r4 = r4.toString()
            r1 = r4
            goto L_0x0017
        L_0x0058:
            r4 = r2
            r5 = 10
            if (r4 != r5) goto L_0x0068
            r4 = r1
            float r4 = asFloat(r4)
            java.lang.String r4 = java.lang.Float.toString(r4)
            r1 = r4
            goto L_0x0017
        L_0x0068:
            r4 = r2
            r5 = 10
            if (r4 != r5) goto L_0x000c
            r4 = r1
            double r4 = asDouble(r4)
            java.lang.String r4 = java.lang.Double.toString(r4)
            r1 = r4
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.Arithmetic.toString(java.lang.Object, int):java.lang.String");
    }

    public static Object convert(Object obj, int code) {
        Object value = obj;
        switch (code) {
            case 1:
                if (value instanceof Integer) {
                    return value;
                }
                return Integer.valueOf(((Number) value).intValue());
            case 2:
                if (value instanceof Long) {
                    return value;
                }
                return Long.valueOf(((Number) value).longValue());
            case 3:
                return asBigInteger(value);
            case 4:
                return asIntNum(value);
            case 5:
                return asBigDecimal(value);
            case 6:
                return asRatNum(value);
            case 7:
                if (value instanceof Float) {
                    return value;
                }
                return Float.valueOf(asFloat(value));
            case 8:
                if (value instanceof Double) {
                    return value;
                }
                return Double.valueOf(asDouble(value));
            case 9:
                if (value instanceof DFloNum) {
                    return value;
                }
                return DFloNum.make(asDouble(value));
            case 10:
                return (RealNum) asNumeric(value);
            case 11:
                return asNumeric(value);
            default:
                return (Number) value;
        }
    }

    public static boolean isExact(Number number) {
        Number num = number;
        if (num instanceof Numeric) {
            return ((Numeric) num).isExact();
        }
        return !(num instanceof Double) && !(num instanceof Float) && !(num instanceof BigDecimal);
    }

    public static Number toExact(Number number) {
        Number num = number;
        if (num instanceof Numeric) {
            return ((Numeric) num).toExact();
        }
        if ((num instanceof Double) || (num instanceof Float) || (num instanceof BigDecimal)) {
            return DFloNum.toExact(num.doubleValue());
        }
        return num;
    }

    public static Number toInexact(Number number) {
        Number num = number;
        if (num instanceof Numeric) {
            return ((Numeric) num).toInexact();
        }
        if ((num instanceof Double) || (num instanceof Float) || (num instanceof BigDecimal)) {
            return num;
        }
        return Double.valueOf(num.doubleValue());
    }
}
