package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.xml.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class ArithOp extends Procedure1or2 implements Inlineable {
    static final BigInteger TEN = BigInteger.valueOf(10);
    public static final ArithOp add;
    public static final ArithOp div;
    public static final ArithOp idiv;
    public static final ArithOp minus;
    public static final ArithOp mod;
    public static final ArithOp mul;
    public static final ArithOp plus;
    public static final ArithOp sub;

    /* renamed from: op */
    char f250op;

    static {
        ArithOp arithOp;
        ArithOp arithOp2;
        ArithOp arithOp3;
        ArithOp arithOp4;
        ArithOp arithOp5;
        ArithOp arithOp6;
        ArithOp arithOp7;
        ArithOp arithOp8;
        new ArithOp("+", '+', 2);
        add = arithOp;
        new ArithOp("-", '-', 2);
        sub = arithOp2;
        new ArithOp("*", '*', 2);
        mul = arithOp3;
        new ArithOp("div", 'd', 2);
        div = arithOp4;
        new ArithOp("idiv", 'i', 2);
        idiv = arithOp5;
        new ArithOp("mod", 'm', 2);
        mod = arithOp6;
        new ArithOp("+", 'P', 1);
        plus = arithOp7;
        new ArithOp("-", Access.METHOD_CONTEXT, 1);
        minus = arithOp8;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArithOp(String name, char op, int i) {
        super(name);
        int i2 = i;
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateArithOp");
        this.f250op = op;
    }

    public Object apply1(Object obj) throws Throwable {
        Throwable th;
        Object arg1 = obj;
        if (arg1 == Values.empty || arg1 == null) {
            return arg1;
        }
        if ((arg1 instanceof KNode) || (arg1 instanceof UntypedAtomic)) {
            arg1 = XDataType.doubleType.valueOf(TextUtils.stringValue(arg1));
        }
        switch (this.f250op) {
            case 'M':
                switch (Arithmetic.classifyValue(arg1)) {
                    case 7:
                        return XDataType.makeFloat(-Arithmetic.asFloat(arg1));
                    case 8:
                        return XDataType.makeDouble(-Arithmetic.asDouble(arg1));
                    default:
                        if (arg1 instanceof Numeric) {
                            return ((Numeric) arg1).neg();
                        }
                        return AddOp.apply2(-1, IntNum.zero(), arg1);
                }
            case 'P':
                return AddOp.apply2(1, IntNum.zero(), arg1);
            default:
                Throwable th2 = th;
                new UnsupportedOperationException(getName());
                throw th2;
        }
    }

    public static BigDecimal div(BigDecimal d1, BigDecimal d2) {
        return d1.divide(d2, MathContext.DECIMAL128);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Throwable th;
        Object obj3;
        Object obj4;
        Throwable th2;
        Object arg1 = obj;
        Object arg2 = obj2;
        if (arg1 == Values.empty || arg1 == null) {
            return arg1;
        }
        if (arg2 == Values.empty || arg2 == null) {
            return arg2;
        }
        if ((arg1 instanceof KNode) || (arg1 instanceof UntypedAtomic)) {
            arg1 = XDataType.doubleType.valueOf(TextUtils.stringValue(arg1));
        }
        if ((arg2 instanceof KNode) || (arg2 instanceof UntypedAtomic)) {
            arg2 = XDataType.doubleType.valueOf(TextUtils.stringValue(arg2));
        }
        switch (this.f250op) {
            case '*':
                return MultiplyOp.$St.apply2(arg1, arg2);
            case '+':
                return AddOp.apply2(1, arg1, arg2);
            case '-':
                return AddOp.apply2(-1, arg1, arg2);
            default:
                int code1 = Arithmetic.classifyValue(arg1);
                int code2 = Arithmetic.classifyValue(arg2);
                int code = code1 < code2 ? code2 : code1;
                switch (this.f250op) {
                    case 'd':
                        if (code1 >= 0 && code2 >= 0) {
                            if (code <= 6) {
                                return div((BigDecimal) XDataType.decimalType.cast(arg1), (BigDecimal) XDataType.decimalType.cast(arg2));
                            } else if (code == 7) {
                                new Float(((Number) arg1).floatValue() / ((Number) arg2).floatValue());
                                return obj4;
                            } else if (code == 8) {
                                new Double(((Number) arg1).doubleValue() / ((Number) arg2).doubleValue());
                                return obj3;
                            } else if ((arg1 instanceof Duration) && (arg2 instanceof Duration)) {
                                Duration dur1 = (Duration) arg1;
                                Duration dur2 = (Duration) arg2;
                                if (dur1.unit() == Unit.second && dur2.unit() == Unit.second) {
                                    long s1 = dur1.getTotalSeconds();
                                    long s2 = dur2.getTotalSeconds();
                                    return div(TimeUtils.secondsBigDecimalFromDuration(s1, dur1.getNanoSecondsOnly()), TimeUtils.secondsBigDecimalFromDuration(s2, dur2.getNanoSecondsOnly()));
                                }
                                if (dur1.unit() == Unit.month && dur2.unit() == Unit.month) {
                                    return div(BigDecimal.valueOf((long) dur1.getTotalMonths()), BigDecimal.valueOf((long) dur2.getTotalMonths()));
                                }
                                Throwable th3 = th;
                                new ArithmeticException("divide of incompatible durations");
                                throw th3;
                            } else if (code >= 0) {
                                return Arithmetic.asNumeric(arg1).div(Arithmetic.asNumeric(arg2));
                            }
                        }
                        break;
                    case 'i':
                        if (code1 >= 0 && code2 >= 0) {
                            if (code <= 4) {
                                return IntNum.quotient(Arithmetic.asIntNum(arg1), Arithmetic.asIntNum(arg2));
                            } else if (code <= 6) {
                                return Arithmetic.asIntNum(((BigDecimal) XDataType.decimalType.cast(arg1)).divide((BigDecimal) XDataType.decimalType.cast(arg2), 0, 1));
                            } else if (code <= 7) {
                                return RealNum.toExactInt((double) (((Number) arg1).floatValue() / ((Number) arg2).floatValue()), 3);
                            } else {
                                return RealNum.toExactInt(((Number) arg1).doubleValue() / ((Number) arg2).doubleValue(), 3);
                            }
                        }
                    case 'm':
                        if (code1 >= 0 && code2 >= 0) {
                            if (code <= 4) {
                                return IntNum.remainder(Arithmetic.asIntNum(arg1), Arithmetic.asIntNum(arg2));
                            } else if (code <= 6) {
                                return sub.apply2(arg1, mul.apply2(idiv.apply2(arg1, arg2), arg2));
                            } else {
                                if (code <= 7) {
                                    return XDataType.makeFloat(Arithmetic.asFloat(arg1) % Arithmetic.asFloat(arg2));
                                } else if (code <= 9) {
                                    double d = Arithmetic.asDouble(arg1) % Arithmetic.asDouble(arg2);
                                    if (code == 9) {
                                        return DFloNum.make(d);
                                    }
                                    return XDataType.makeDouble(d);
                                }
                            }
                        }
                        break;
                }
                Throwable th4 = th2;
                new UnsupportedOperationException(getName());
                throw th4;
        }
    }

    public void compile(ApplyExp exp, Compilation comp, Target target) {
        ApplyExp.compile(exp, comp, target);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }
}
