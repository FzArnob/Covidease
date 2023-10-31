package gnu.kawa.xml;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.kawa.functions.Arithmetic;
import gnu.math.IntNum;
import gnu.math.RealNum;
import java.math.BigDecimal;

public class XIntegerType extends XDataType {
    public static final XIntegerType byteType;
    public static final XIntegerType intType;
    public static final XIntegerType integerType;
    public static final XIntegerType longType;
    public static final XIntegerType negativeIntegerType;
    public static final XIntegerType nonNegativeIntegerType;
    public static final XIntegerType nonPositiveIntegerType;
    public static final XIntegerType positiveIntegerType;
    public static final XIntegerType shortType;
    static ClassType typeIntNum = ClassType.make("gnu.math.IntNum");
    public static final XIntegerType unsignedByteType;
    public static final XIntegerType unsignedIntType;
    public static final XIntegerType unsignedLongType;
    public static final XIntegerType unsignedShortType;
    boolean isUnsignedType;
    public final IntNum maxValue;
    public final IntNum minValue;

    static {
        XIntegerType xIntegerType;
        XIntegerType xIntegerType2;
        XIntegerType xIntegerType3;
        XIntegerType xIntegerType4;
        XIntegerType xIntegerType5;
        XIntegerType xIntegerType6;
        XIntegerType xIntegerType7;
        XIntegerType xIntegerType8;
        XIntegerType xIntegerType9;
        XIntegerType xIntegerType10;
        XIntegerType xIntegerType11;
        XIntegerType xIntegerType12;
        XIntegerType xIntegerType13;
        new XIntegerType(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, decimalType, 5, (IntNum) null, (IntNum) null);
        integerType = xIntegerType;
        new XIntegerType("long", (XDataType) integerType, 8, IntNum.make(Long.MIN_VALUE), IntNum.make(Long.MAX_VALUE));
        longType = xIntegerType2;
        new XIntegerType("int", (XDataType) longType, 9, IntNum.make(Integer.MIN_VALUE), IntNum.make(Integer.MAX_VALUE));
        intType = xIntegerType3;
        new XIntegerType("short", (XDataType) intType, 10, IntNum.make(-32768), IntNum.make(32767));
        shortType = xIntegerType4;
        new XIntegerType("byte", (XDataType) shortType, 11, IntNum.make(-128), IntNum.make(127));
        byteType = xIntegerType5;
        new XIntegerType("nonPositiveInteger", (XDataType) integerType, 6, (IntNum) null, IntNum.zero());
        nonPositiveIntegerType = xIntegerType6;
        new XIntegerType("negativeInteger", (XDataType) nonPositiveIntegerType, 7, (IntNum) null, IntNum.minusOne());
        negativeIntegerType = xIntegerType7;
        new XIntegerType("nonNegativeInteger", (XDataType) integerType, 12, IntNum.zero(), (IntNum) null);
        nonNegativeIntegerType = xIntegerType8;
        new XIntegerType("unsignedLong", (XDataType) nonNegativeIntegerType, 13, IntNum.zero(), IntNum.valueOf("18446744073709551615"));
        unsignedLongType = xIntegerType9;
        new XIntegerType("unsignedInt", (XDataType) unsignedLongType, 14, IntNum.zero(), IntNum.make(4294967295L));
        unsignedIntType = xIntegerType10;
        new XIntegerType("unsignedShort", (XDataType) unsignedIntType, 15, IntNum.zero(), IntNum.make(65535));
        unsignedShortType = xIntegerType11;
        new XIntegerType("unsignedByte", (XDataType) unsignedShortType, 16, IntNum.zero(), IntNum.make(255));
        unsignedByteType = xIntegerType12;
        new XIntegerType("positiveInteger", (XDataType) nonNegativeIntegerType, 17, IntNum.one(), (IntNum) null);
        positiveIntegerType = xIntegerType13;
    }

    public boolean isUnsignedType() {
        return this.isUnsignedType;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XIntegerType(java.lang.String r13, gnu.kawa.xml.XDataType r14, int r15, gnu.math.IntNum r16, gnu.math.IntNum r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r11 = r5
            r6.<init>((java.lang.Object) r7, (gnu.kawa.xml.XDataType) r8, (int) r9, (gnu.math.IntNum) r10, (gnu.math.IntNum) r11)
            r6 = r0
            r7 = r1
            java.lang.String r8 = "unsigned"
            boolean r7 = r7.startsWith(r8)
            r6.isUnsignedType = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.XIntegerType.<init>(java.lang.String, gnu.kawa.xml.XDataType, int, gnu.math.IntNum, gnu.math.IntNum):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XIntegerType(Object name, XDataType base, int typeCode, IntNum min, IntNum max) {
        super(name, typeIntNum, typeCode);
        this.minValue = min;
        this.maxValue = max;
        this.baseType = base;
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof IntNum)) {
            return false;
        }
        if (this == integerType) {
            return true;
        }
        XDataType integerType2 = obj2 instanceof XInteger ? ((XInteger) obj2).getIntegerType() : integerType;
        while (true) {
            XDataType objType = integerType2;
            if (objType == null) {
                return false;
            }
            if (objType == this) {
                return true;
            }
            integerType2 = objType.baseType;
        }
    }

    public Object coerceFromObject(Object obj) {
        return valueOf((IntNum) obj);
    }

    public IntNum valueOf(IntNum intNum) {
        Throwable th;
        StringBuilder sb;
        IntNum intNum2;
        IntNum value = intNum;
        if (this == integerType) {
            return value;
        }
        if ((this.minValue == null || IntNum.compare(value, this.minValue) >= 0) && (this.maxValue == null || IntNum.compare(value, this.maxValue) <= 0)) {
            new XInteger(value, this);
            return intNum2;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("cannot cast ").append(value).append(" to ").append(this.name).toString());
        throw th2;
    }

    public Object cast(Object obj) {
        Object value = obj;
        if (value instanceof Boolean) {
            return valueOf(((Boolean) value).booleanValue() ? IntNum.one() : IntNum.zero());
        } else if (value instanceof IntNum) {
            return valueOf((IntNum) value);
        } else {
            if (value instanceof BigDecimal) {
                return valueOf(Arithmetic.asIntNum((BigDecimal) value));
            }
            if (value instanceof RealNum) {
                return valueOf(((RealNum) value).toExactInt(3));
            }
            if (value instanceof Number) {
                return valueOf(RealNum.toExactInt(((Number) value).doubleValue(), 3));
            }
            return super.cast(value);
        }
    }

    public Object valueOf(String value) {
        return valueOf(IntNum.valueOf(value.trim(), 10));
    }

    public IntNum valueOf(String value, int radix) {
        return valueOf(IntNum.valueOf(value.trim(), radix));
    }
}
