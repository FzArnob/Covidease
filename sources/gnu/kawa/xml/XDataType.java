package gnu.kawa.xml;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.text.Path;
import gnu.text.Printable;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class XDataType extends Type implements TypeValue {
    public static final int ANY_ATOMIC_TYPE_CODE = 3;
    public static final int ANY_SIMPLE_TYPE_CODE = 2;
    public static final int ANY_URI_TYPE_CODE = 33;
    public static final int BASE64_BINARY_TYPE_CODE = 34;
    public static final int BOOLEAN_TYPE_CODE = 31;
    public static final int BYTE_TYPE_CODE = 11;
    public static final int DATE_TIME_TYPE_CODE = 20;
    public static final int DATE_TYPE_CODE = 21;
    public static final int DAY_TIME_DURATION_TYPE_CODE = 30;
    public static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1);
    public static final int DECIMAL_TYPE_CODE = 4;
    public static final Double DOUBLE_ONE = makeDouble(1.0d);
    public static final int DOUBLE_TYPE_CODE = 19;
    public static final Double DOUBLE_ZERO = makeDouble(0.0d);
    public static final int DURATION_TYPE_CODE = 28;
    public static final int ENTITY_TYPE_CODE = 47;
    public static final Float FLOAT_ONE = makeFloat(1.0f);
    public static final int FLOAT_TYPE_CODE = 18;
    public static final Float FLOAT_ZERO = makeFloat(0.0f);
    public static final int G_DAY_TYPE_CODE = 26;
    public static final int G_MONTH_DAY_TYPE_CODE = 25;
    public static final int G_MONTH_TYPE_CODE = 27;
    public static final int G_YEAR_MONTH_TYPE_CODE = 23;
    public static final int G_YEAR_TYPE_CODE = 24;
    public static final int HEX_BINARY_TYPE_CODE = 35;
    public static final int IDREF_TYPE_CODE = 46;
    public static final int ID_TYPE_CODE = 45;
    public static final int INTEGER_TYPE_CODE = 5;
    public static final int INT_TYPE_CODE = 9;
    public static final int LANGUAGE_TYPE_CODE = 41;
    public static final int LONG_TYPE_CODE = 8;
    public static final int NAME_TYPE_CODE = 43;
    public static final int NCNAME_TYPE_CODE = 44;
    public static final int NEGATIVE_INTEGER_TYPE_CODE = 7;
    public static final int NMTOKEN_TYPE_CODE = 42;
    public static final int NONNEGATIVE_INTEGER_TYPE_CODE = 12;
    public static final int NON_POSITIVE_INTEGER_TYPE_CODE = 6;
    public static final int NORMALIZED_STRING_TYPE_CODE = 39;
    public static final int NOTATION_TYPE_CODE = 36;
    public static final XDataType NotationType;
    public static final int POSITIVE_INTEGER_TYPE_CODE = 17;
    public static final int QNAME_TYPE_CODE = 32;
    public static final int SHORT_TYPE_CODE = 10;
    public static final int STRING_TYPE_CODE = 38;
    public static final int TIME_TYPE_CODE = 22;
    public static final int TOKEN_TYPE_CODE = 40;
    public static final int UNSIGNED_BYTE_TYPE_CODE = 16;
    public static final int UNSIGNED_INT_TYPE_CODE = 14;
    public static final int UNSIGNED_LONG_TYPE_CODE = 13;
    public static final int UNSIGNED_SHORT_TYPE_CODE = 15;
    public static final int UNTYPED_ATOMIC_TYPE_CODE = 37;
    public static final int UNTYPED_TYPE_CODE = 48;
    public static final int YEAR_MONTH_DURATION_TYPE_CODE = 29;
    public static final XDataType anyAtomicType;
    public static final XDataType anySimpleType;
    public static final XDataType anyURIType;
    public static final XDataType base64BinaryType;
    public static final XDataType booleanType;
    public static final XDataType dayTimeDurationType;
    public static final XDataType decimalType;
    public static final XDataType doubleType;
    public static final XDataType durationType;
    public static final XDataType floatType;
    public static final XDataType hexBinaryType;
    public static final XDataType stringStringType;
    public static final XDataType stringType;
    public static final XDataType untypedAtomicType;
    public static final XDataType untypedType;
    public static final XDataType yearMonthDurationType;
    XDataType baseType;
    Type implementationType;
    Object name;
    int typeCode;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XDataType(java.lang.Object r7, gnu.bytecode.Type r8, int r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r2
            r4.<init>(r5)
            r4 = r0
            r5 = r1
            r4.name = r5
            r4 = r1
            if (r4 == 0) goto L_0x0019
            r4 = r0
            r5 = r1
            java.lang.String r5 = r5.toString()
            r4.setName(r5)
        L_0x0019:
            r4 = r0
            r5 = r2
            r4.implementationType = r5
            r4 = r0
            r5 = r3
            r4.typeCode = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.XDataType.<init>(java.lang.Object, gnu.bytecode.Type, int):void");
    }

    static {
        XDataType xDataType;
        XDataType xDataType2;
        XDataType xDataType3;
        XDataType xDataType4;
        XDataType xDataType5;
        XDataType xDataType6;
        XDataType xDataType7;
        XDataType xDataType8;
        XDataType xDataType9;
        XDataType xDataType10;
        XDataType xDataType11;
        XDataType xDataType12;
        XDataType xDataType13;
        XDataType xDataType14;
        XDataType xDataType15;
        XDataType xDataType16;
        XDataType xDataType17;
        new XDataType("anySimpleType", Type.objectType, 2);
        anySimpleType = xDataType;
        new XDataType("anyAtomicType", Type.objectType, 3);
        anyAtomicType = xDataType2;
        new XDataType(PropertyTypeConstants.PROPERTY_TYPE_STRING, ClassType.make("java.lang.CharSequence"), 38);
        stringType = xDataType3;
        new XDataType("String", ClassType.make("java.lang.String"), 38);
        stringStringType = xDataType4;
        new XDataType(PropertyTypeConstants.PROPERTY_TYPE_STRING, ClassType.make("gnu.kawa.xml.UntypedAtomic"), 37);
        untypedAtomicType = xDataType5;
        new XDataType("base64Binary", ClassType.make("gnu.kawa.xml.Base64Binary"), 34);
        base64BinaryType = xDataType6;
        new XDataType("hexBinary", ClassType.make("gnu.kawa.xml.HexBinary"), 35);
        hexBinaryType = xDataType7;
        new XDataType(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, Type.booleanType, 31);
        booleanType = xDataType8;
        new XDataType("anyURI", ClassType.make("gnu.text.Path"), 33);
        anyURIType = xDataType9;
        new XDataType("NOTATION", ClassType.make("gnu.kawa.xml.Notation"), 36);
        NotationType = xDataType10;
        new XDataType("decimal", ClassType.make("java.lang.Number"), 4);
        decimalType = xDataType11;
        new XDataType(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, ClassType.make("java.lang.Float"), 18);
        floatType = xDataType12;
        new XDataType("double", ClassType.make("java.lang.Double"), 19);
        doubleType = xDataType13;
        new XDataType("duration", ClassType.make("gnu.math.Duration"), 28);
        durationType = xDataType14;
        new XDataType("yearMonthDuration", ClassType.make("gnu.math.Duration"), 29);
        yearMonthDurationType = xDataType15;
        new XDataType("dayTimeDuration", ClassType.make("gnu.math.Duration"), 30);
        dayTimeDurationType = xDataType16;
        new XDataType("untyped", Type.objectType, 48);
        untypedType = xDataType17;
    }

    public Class getReflectClass() {
        return this.implementationType.getReflectClass();
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        Compilation.getCurrent().compileConstant(this, Target.pushObject);
        Method meth = ClassType.make("gnu.kawa.xml.XDataType").getDeclaredMethod("coerceFromObject", 1);
        code.emitSwap();
        code.emitInvokeVirtual(meth);
        this.implementationType.emitCoerceFromObject(code);
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (this.typeCode == 31) {
            this.implementationType.emitCoerceToObject(code);
        } else {
            super.emitCoerceToObject(code);
        }
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        Variable incoming = variable;
        Declaration decl = declaration;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (this.typeCode == 31) {
            if (incoming != null) {
                code.emitLoad(incoming);
            }
            Type.javalangBooleanType.emitIsInstance(code);
            code.emitIfIntNotZero();
            if (decl != null) {
                code.emitLoad(incoming);
                Type.booleanType.emitCoerceFromObject(code);
                decl.compileStore(comp);
                return;
            }
            return;
        }
        comp.compileConstant(this, Target.pushObject);
        if (incoming == null) {
            code.emitSwap();
        } else {
            code.emitLoad(incoming);
        }
        code.emitInvokeVirtual(Compilation.typeType.getDeclaredMethod("isInstance", 1));
        code.emitIfIntNotZero();
        if (decl != null) {
            code.emitLoad(incoming);
            emitCoerceFromObject(code);
            decl.compileStore(comp);
        }
    }

    public Expression convertValue(Expression expression) {
        Expression expression2 = expression;
        return null;
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        switch (this.typeCode) {
            case 2:
                if ((obj2 instanceof SeqPosition) || (obj2 instanceof Nodes)) {
                    return false;
                }
                return true;
            case 3:
                return !(obj2 instanceof Values) && !(obj2 instanceof SeqPosition);
            case 4:
                return (obj2 instanceof BigDecimal) || (obj2 instanceof IntNum);
            case 18:
                return obj2 instanceof Float;
            case 19:
                return obj2 instanceof Double;
            case 28:
                return obj2 instanceof Duration;
            case 29:
                return (obj2 instanceof Duration) && ((Duration) obj2).unit() == Unit.month;
            case 30:
                return (obj2 instanceof Duration) && ((Duration) obj2).unit() == Unit.second;
            case 31:
                return obj2 instanceof Boolean;
            case 33:
                return obj2 instanceof Path;
            case 37:
                return obj2 instanceof UntypedAtomic;
            case 38:
                return obj2 instanceof CharSequence;
            case 48:
                return true;
            default:
                return super.isInstance(obj2);
        }
    }

    public void emitIsInstance(Variable incoming, Compilation comp, Target target) {
        InstanceOf.emitIsInstance(this, incoming, comp, target);
    }

    public String toString(Object value) {
        return value.toString();
    }

    public void print(Object obj, Consumer consumer) {
        Object value = obj;
        Consumer out = consumer;
        if (value instanceof Printable) {
            ((Printable) value).print(out);
        } else {
            out.write(toString(value));
        }
    }

    public boolean castable(Object value) {
        try {
            Object cast = cast(value);
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            return false;
        }
    }

    public Object cast(Object value) {
        Object obj;
        Object obj2;
        Throwable th;
        Object obj3;
        Object value2 = KNode.atomicValue(value);
        if (value2 instanceof UntypedAtomic) {
            if (this.typeCode == 37) {
                return value2;
            }
            return valueOf(value2.toString());
        } else if (value2 instanceof String) {
            return valueOf(value2.toString());
        } else {
            switch (this.typeCode) {
                case 4:
                    if (value2 instanceof BigDecimal) {
                        return value2;
                    }
                    if (value2 instanceof RealNum) {
                        return ((RealNum) value2).asBigDecimal();
                    }
                    if ((value2 instanceof Float) || (value2 instanceof Double)) {
                        return BigDecimal.valueOf(((Number) value2).doubleValue());
                    }
                    if (value2 instanceof Boolean) {
                        return cast(((Boolean) value2).booleanValue() ? IntNum.one() : IntNum.zero());
                    }
                    break;
                case 18:
                    if (value2 instanceof Float) {
                        return value2;
                    }
                    if (value2 instanceof Number) {
                        return makeFloat(((Number) value2).floatValue());
                    }
                    if (value2 instanceof Boolean) {
                        return ((Boolean) value2).booleanValue() ? FLOAT_ONE : FLOAT_ZERO;
                    }
                    break;
                case 19:
                    if (value2 instanceof Double) {
                        return value2;
                    }
                    if (value2 instanceof Number) {
                        return makeDouble(((Number) value2).doubleValue());
                    }
                    if (value2 instanceof Boolean) {
                        return ((Boolean) value2).booleanValue() ? DOUBLE_ONE : DOUBLE_ZERO;
                    }
                    break;
                case 20:
                case 21:
                case 22:
                    if (value2 instanceof DateTime) {
                        return ((DateTime) value2).cast(XTimeType.components(((XTimeType) this).typeCode));
                    }
                    break;
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    if (value2 instanceof DateTime) {
                        int dstMask = XTimeType.components(((XTimeType) this).typeCode);
                        DateTime dt = (DateTime) value2;
                        int srcMask = dt.components();
                        if (dstMask == srcMask || (srcMask & 14) == 14) {
                            return dt.cast(dstMask);
                        }
                        Throwable th2 = th;
                        new ClassCastException();
                        throw th2;
                    }
                    break;
                case 28:
                    return castToDuration(value2, Unit.duration);
                case 29:
                    return castToDuration(value2, Unit.month);
                case 30:
                    return castToDuration(value2, Unit.second);
                case 31:
                    if (value2 instanceof Boolean) {
                        return ((Boolean) value2).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
                    } else if (value2 instanceof Number) {
                        double d = ((Number) value2).doubleValue();
                        return (d == 0.0d || Double.isNaN(d)) ? Boolean.FALSE : Boolean.TRUE;
                    }
                    break;
                case 33:
                    return URIPath.makeURI(value2);
                case 34:
                    if (value2 instanceof BinaryObject) {
                        new Base64Binary(((BinaryObject) value2).getBytes());
                        return obj2;
                    }
                    break;
                case 35:
                    break;
                case 37:
                    new UntypedAtomic(TextUtils.stringValue(value2));
                    return obj3;
                case 38:
                    return TextUtils.asString(value2);
            }
            if (value2 instanceof BinaryObject) {
                new HexBinary(((BinaryObject) value2).getBytes());
                return obj;
            }
            return coerceFromObject(value2);
        }
    }

    /* access modifiers changed from: package-private */
    public Duration castToDuration(Object obj, Unit unit) {
        Object value = obj;
        Unit unit2 = unit;
        if (!(value instanceof Duration)) {
            return (Duration) coerceFromObject(value);
        }
        Duration dur = (Duration) value;
        if (dur.unit() == unit2) {
            return dur;
        }
        int months = dur.getTotalMonths();
        long seconds = dur.getTotalSeconds();
        int nanos = dur.getNanoSecondsOnly();
        if (unit2 == Unit.second) {
            months = 0;
        }
        if (unit2 == Unit.month) {
            seconds = 0;
            nanos = 0;
        }
        return Duration.make(months, seconds, nanos, unit2);
    }

    public Object coerceFromObject(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        if (isInstance(obj2)) {
            return obj2;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("cannot cast ").append(obj2).append(" to ").append(this.name).toString());
        throw th2;
    }

    public int compare(Type type) {
        Type other = type;
        if (this == other || ((this == stringStringType && other == stringType) || (this == stringType && other == stringStringType))) {
            return 0;
        }
        return this.implementationType.compare(other);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0126 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object valueOf(java.lang.String r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r4 = r0
            int r4 = r4.typeCode
            switch(r4) {
                case 4: goto L_0x00db;
                case 18: goto L_0x00a3;
                case 19: goto L_0x00a3;
                case 28: goto L_0x0132;
                case 29: goto L_0x013a;
                case 30: goto L_0x0142;
                case 31: goto L_0x0045;
                case 33: goto L_0x0039;
                case 34: goto L_0x014a;
                case 35: goto L_0x0152;
                case 37: goto L_0x002e;
                case 38: goto L_0x002b;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            java.lang.String r7 = "valueOf not implemented for "
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r0
            java.lang.Object r7 = r7.name
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r4
        L_0x002b:
            r4 = r1
            r0 = r4
        L_0x002d:
            return r0
        L_0x002e:
            gnu.kawa.xml.UntypedAtomic r4 = new gnu.kawa.xml.UntypedAtomic
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            r0 = r4
            goto L_0x002d
        L_0x0039:
            r4 = r1
            r5 = 1
            java.lang.String r4 = gnu.xml.TextUtils.replaceWhitespace(r4, r5)
            gnu.text.URIPath r4 = gnu.text.URIPath.makeURI(r4)
            r0 = r4
            goto L_0x002d
        L_0x0045:
            r4 = r1
            java.lang.String r4 = r4.trim()
            r1 = r4
            r4 = r1
            java.lang.String r5 = "true"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x005f
            r4 = r1
            java.lang.String r5 = "1"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0063
        L_0x005f:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            r0 = r4
            goto L_0x002d
        L_0x0063:
            r4 = r1
            java.lang.String r5 = "false"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0077
            r4 = r1
            java.lang.String r5 = "0"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
        L_0x0077:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r0 = r4
            goto L_0x002d
        L_0x007b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            java.lang.String r7 = "not a valid boolean: '"
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r1
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = "'"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r4
        L_0x00a3:
            r4 = r1
            java.lang.String r4 = r4.trim()
            r1 = r4
            java.lang.String r4 = "INF"
            r5 = r1
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00c6
            java.lang.String r4 = "Infinity"
            r1 = r4
        L_0x00b7:
            r4 = r0
            int r4 = r4.typeCode
            r5 = 18
            if (r4 != r5) goto L_0x00d5
            r4 = r1
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
        L_0x00c3:
            r0 = r4
            goto L_0x002d
        L_0x00c6:
            java.lang.String r4 = "-INF"
            r5 = r1
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00b7
            java.lang.String r4 = "-Infinity"
            r1 = r4
            goto L_0x00b7
        L_0x00d5:
            r4 = r1
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            goto L_0x00c3
        L_0x00db:
            r4 = r1
            java.lang.String r4 = r4.trim()
            r1 = r4
            r4 = r1
            int r4 = r4.length()
            r2 = r4
        L_0x00e7:
            int r2 = r2 + -1
            r4 = r2
            if (r4 < 0) goto L_0x0126
            r4 = r1
            r5 = r2
            char r4 = r4.charAt(r5)
            r3 = r4
            r4 = r3
            r5 = 101(0x65, float:1.42E-43)
            if (r4 == r5) goto L_0x00fd
            r4 = r3
            r5 = 69
            if (r4 != r5) goto L_0x0125
        L_0x00fd:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            java.lang.String r7 = "not a valid decimal: '"
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r1
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = "'"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r4
        L_0x0125:
            goto L_0x00e7
        L_0x0126:
            java.math.BigDecimal r4 = new java.math.BigDecimal
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            r0 = r4
            goto L_0x002d
        L_0x0132:
            r4 = r1
            gnu.math.Duration r4 = gnu.math.Duration.parseDuration(r4)
            r0 = r4
            goto L_0x002d
        L_0x013a:
            r4 = r1
            gnu.math.Duration r4 = gnu.math.Duration.parseYearMonthDuration(r4)
            r0 = r4
            goto L_0x002d
        L_0x0142:
            r4 = r1
            gnu.math.Duration r4 = gnu.math.Duration.parseDayTimeDuration(r4)
            r0 = r4
            goto L_0x002d
        L_0x014a:
            r4 = r1
            gnu.kawa.xml.Base64Binary r4 = gnu.kawa.xml.Base64Binary.valueOf(r4)
            r0 = r4
            goto L_0x002d
        L_0x0152:
            r4 = r1
            gnu.kawa.xml.HexBinary r4 = gnu.kawa.xml.HexBinary.valueOf(r4)
            r0 = r4
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.XDataType.valueOf(java.lang.String):java.lang.Object");
    }

    public static Float makeFloat(float value) {
        return Float.valueOf(value);
    }

    public static Double makeDouble(double value) {
        return Double.valueOf(value);
    }

    public Procedure getConstructor() {
        return null;
    }
}
