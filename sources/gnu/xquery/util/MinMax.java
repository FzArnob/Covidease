package gnu.xquery.util;

import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

public class MinMax {
    public MinMax() {
    }

    public static Object min(Object arg, NamedCollator collation) {
        return minMax(arg, false, collation);
    }

    public static Object max(Object arg, NamedCollator collation) {
        return minMax(arg, true, collation);
    }

    public static Object minMax(Object obj, boolean z, NamedCollator namedCollator) {
        Throwable th;
        boolean castNeeded;
        Object arg = obj;
        boolean returnMax = z;
        NamedCollator collation = namedCollator;
        if (arg instanceof Values) {
            TreeList tlist = (TreeList) arg;
            int pos = 0;
            int flags = returnMax ? 16 : 4;
            Object cur = tlist.getPosNext(0);
            if (cur == Sequence.eofValue) {
                return Values.empty;
            }
            Double result = convert(cur);
            while (true) {
                pos = tlist.nextPos(pos);
                Object cur2 = tlist.getPosNext(pos);
                if (cur2 == Sequence.eofValue) {
                    return result;
                }
                Object cur3 = convert(cur2);
                if ((result instanceof Number) || (cur3 instanceof Number)) {
                    int code1 = Arithmetic.classifyValue(result);
                    int code2 = Arithmetic.classifyValue(cur3);
                    int rcode = NumberCompare.compare(result, code1, cur3, code2, false);
                    if (rcode == -3) {
                        Throwable th2 = th;
                        new IllegalArgumentException("values cannot be compared");
                        throw th2;
                    }
                    int code = code1 < code2 ? code2 : code1;
                    if (rcode == -2) {
                        result = NumberValue.NaN;
                        castNeeded = true;
                    } else if (!NumberCompare.checkCompareCode(rcode, flags)) {
                        castNeeded = code != code2;
                        result = cur3;
                    } else {
                        castNeeded = code != code1;
                    }
                    if (castNeeded) {
                        result = Arithmetic.convert(result, code);
                    }
                } else if (!Compare.atomicCompare(flags, result, cur3, collation)) {
                    result = cur3;
                }
            }
        } else {
            Object arg2 = convert(arg);
            boolean atomicCompare = Compare.atomicCompare(16, arg2, arg2, collation);
            return arg2;
        }
    }

    static Object convert(Object arg) {
        Object arg2 = KNode.atomicValue(arg);
        if (arg2 instanceof UntypedAtomic) {
            arg2 = (Double) XDataType.doubleType.valueOf(TextUtils.stringValue(arg2));
        }
        return arg2;
    }
}
