package gnu.xquery.util;

import gnu.kawa.functions.AddOp;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Reduce {
    public Reduce() {
    }

    public static Object sum(Object arg) throws Throwable {
        return sum(arg, IntNum.zero());
    }

    public static Object sum(Object obj, Object obj2) throws Throwable {
        Object arg = obj;
        Object zero = obj2;
        if (!(arg instanceof Values)) {
            return (Number) MinMax.convert(arg);
        }
        TreeList tlist = (TreeList) arg;
        int pos = 0;
        Object next = tlist.getPosNext(0);
        if (next == Sequence.eofValue) {
            return zero;
        }
        Object convert = MinMax.convert(next);
        while (true) {
            Object result = convert;
            pos = tlist.nextPos(pos);
            Object next2 = tlist.getPosNext(pos);
            if (next2 == Sequence.eofValue) {
                return result;
            }
            convert = AddOp.apply2(1, result, MinMax.convert(next2));
        }
    }
}
