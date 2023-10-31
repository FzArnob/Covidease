package gnu.xquery.util;

import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XTimeType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.Unit;

public class Compare extends Procedure2 {
    public static final Compare $Eq = make("=", 8);
    public static final Compare $Ex$Eq = make("!=", 23);
    public static final Compare $Gr = make(">", 16);
    public static final Compare $Gr$Eq = make(">=", 24);
    public static final Compare $Ls = make("<", 4);
    public static final Compare $Ls$Eq = make("<=", 12);
    static final int LENIENT_COMPARISON = 64;
    static final int LENIENT_EQ = 72;
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int RESULT_NAN = -2;
    static final int RESULT_NEQ = -3;
    static final int TRUE_IF_EQU = 8;
    static final int TRUE_IF_GRT = 16;
    static final int TRUE_IF_LSS = 4;
    static final int TRUE_IF_NAN = 2;
    static final int TRUE_IF_NEQ = 1;
    static final int VALUE_COMPARISON = 32;
    public static final Compare valEq = make("eq", 40);
    public static final Compare valGe = make("ge", 56);
    public static final Compare valGt = make("gt", 48);
    public static final Compare valLe = make("le", 44);
    public static final Compare valLt = make("lt", 36);
    public static final Compare valNe = make("ne", 55);
    int flags;

    public Compare() {
    }

    public static Compare make(String name, int flags2) {
        Compare compare;
        new Compare();
        Compare proc = compare;
        proc.setName(name);
        proc.setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateCompare");
        proc.flags = flags2;
        return proc;
    }

    public static boolean apply(int i, Object obj, Object obj2, NamedCollator namedCollator) {
        int flags2 = i;
        Object arg1 = obj;
        Object arg2 = obj2;
        NamedCollator collator = namedCollator;
        if (arg1 instanceof Values) {
            Values values1 = (Values) arg1;
            int i2 = 0;
            while (true) {
                int index = i2;
                int next = values1.nextDataIndex(index);
                if (next < 0) {
                    return false;
                }
                if (apply(flags2, values1.getPosNext(index << 1), arg2, collator)) {
                    return true;
                }
                i2 = next;
            }
        } else if (!(arg2 instanceof Values)) {
            return atomicCompare(flags2, KNode.atomicValue(arg1), KNode.atomicValue(arg2), collator);
        } else {
            Values values2 = (Values) arg2;
            int i3 = 0;
            while (true) {
                int index2 = i3;
                int next2 = values2.nextDataIndex(index2);
                if (next2 < 0) {
                    return false;
                }
                if (apply(flags2, arg1, values2.getPosNext(index2 << 1), collator)) {
                    return true;
                }
                i3 = next2;
            }
        }
    }

    public static boolean equalityComparison(int i) {
        int flags2 = i;
        return ((flags2 & 16) != 0) == ((flags2 & 4) != 0);
    }

    public static boolean atomicCompare(int i, Object obj, Object obj2, NamedCollator namedCollator) {
        int comp;
        Throwable th;
        int comp2;
        int comp3;
        Throwable th2;
        int i2;
        Object obj3;
        Object obj4;
        int flags2 = i;
        Object arg1 = obj;
        Object arg2 = obj2;
        NamedCollator collator = namedCollator;
        if (arg1 instanceof UntypedAtomic) {
            Object str = arg1.toString();
            if ((flags2 & 32) != 0) {
                arg1 = str;
            } else if (arg2 instanceof DateTime) {
                arg1 = XTimeType.parseDateTime(str, ((DateTime) arg2).components());
            } else if (arg2 instanceof Duration) {
                arg1 = Duration.parse(str, ((Duration) arg2).unit());
            } else if (arg2 instanceof Number) {
                new DFloNum((String) str);
                arg1 = obj4;
            } else if (arg2 instanceof Boolean) {
                arg1 = XDataType.booleanType.valueOf(str);
            } else {
                arg1 = str;
            }
        }
        if (arg2 instanceof UntypedAtomic) {
            Object str2 = arg2.toString();
            if ((flags2 & 32) != 0) {
                arg2 = str2;
            } else if (arg1 instanceof DateTime) {
                arg2 = XTimeType.parseDateTime(str2, ((DateTime) arg1).components());
            } else if (arg1 instanceof Duration) {
                arg2 = Duration.parse(str2, ((Duration) arg1).unit());
            } else if (arg1 instanceof Number) {
                new DFloNum((String) str2);
                arg2 = obj3;
            } else if (arg1 instanceof Boolean) {
                arg2 = XDataType.booleanType.valueOf(str2);
            } else {
                arg2 = str2;
            }
        }
        if ((arg1 instanceof Number) || (arg2 instanceof Number)) {
            if (arg1 instanceof Duration) {
                if (!(arg2 instanceof Duration)) {
                    comp = -3;
                } else {
                    Duration d1 = (Duration) arg1;
                    Duration d2 = (Duration) arg2;
                    if ((d1.unit != d2.unit || d1.unit == Unit.duration) && !equalityComparison(flags2)) {
                        comp = -3;
                    } else {
                        comp = Duration.compare(d1, d2);
                    }
                }
            } else if (arg1 instanceof DateTime) {
                if (!(arg2 instanceof DateTime)) {
                    comp = -3;
                } else {
                    DateTime d12 = (DateTime) arg1;
                    DateTime d22 = (DateTime) arg2;
                    int m1 = d12.components();
                    if (m1 != d22.components()) {
                        comp = -3;
                    } else if (equalityComparison(flags2) || m1 == 112 || m1 == 14 || m1 == 126) {
                        comp = DateTime.compare(d12, d22);
                    } else {
                        comp = -3;
                    }
                }
            } else if ((arg2 instanceof Duration) || (arg2 instanceof DateTime)) {
                comp = -3;
            } else {
                comp = NumberCompare.compare(arg1, arg2, false);
            }
            if (comp != -3 || (flags2 & 64) != 0) {
                return NumberCompare.checkCompareCode(comp, flags2);
            }
            Throwable th3 = th;
            new IllegalArgumentException("values cannot be compared");
            throw th3;
        }
        if (arg1 instanceof Symbol) {
            if (!(arg2 instanceof Symbol) || !equalityComparison(flags2)) {
                comp2 = -3;
            } else {
                if (arg1.equals(arg2)) {
                    i2 = 0;
                } else {
                    i2 = -2;
                }
                comp2 = i2;
            }
        } else if (arg1 instanceof Boolean) {
            if (arg2 instanceof Boolean) {
                boolean b1 = ((Boolean) arg1).booleanValue();
                boolean b2 = ((Boolean) arg2).booleanValue();
                comp2 = b1 == b2 ? 0 : b2 ? -1 : 1;
            } else {
                comp2 = -3;
            }
        } else if ((arg2 instanceof Boolean) || (arg2 instanceof Symbol)) {
            comp2 = -3;
        } else {
            String str1 = arg1.toString();
            String str22 = arg2.toString();
            if (collator != null) {
                comp3 = collator.compare(str1, str22);
            } else {
                comp3 = NamedCollator.codepointCompare(str1, str22);
            }
            comp2 = comp3 < 0 ? -1 : comp3 > 0 ? 1 : 0;
        }
        if (comp2 != -3 || (flags2 & 64) != 0) {
            return NumberCompare.checkCompareCode(comp2, flags2);
        }
        Throwable th4 = th2;
        new IllegalArgumentException("values cannot be compared");
        throw th4;
    }

    public Object apply2(Object obj, Object obj2) {
        Object arg1 = obj;
        Object arg2 = obj2;
        if ((this.flags & 32) == 0) {
            return apply(this.flags, arg1, arg2, (NamedCollator) null) ? Boolean.TRUE : Boolean.FALSE;
        } else if (arg1 == null || arg1 == Values.empty) {
            return arg1;
        } else {
            if (arg2 == null || arg2 == Values.empty) {
                return arg2;
            }
            return atomicCompare(this.flags, KNode.atomicValue(arg1), KNode.atomicValue(arg2), (NamedCollator) null) ? Boolean.TRUE : Boolean.FALSE;
        }
    }
}
