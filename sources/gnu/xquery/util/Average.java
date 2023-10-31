package gnu.xquery.util;

import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Average extends Procedure1 {
    public static final Average avg;

    static {
        Average average;
        new Average("avg");
        avg = average;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Average(String name) {
        super(name);
    }

    public Object apply1(Object obj) throws Throwable {
        Object arg = obj;
        Values sum = Values.empty;
        int count = 0;
        if (arg instanceof Values) {
            TreeList tlist = (TreeList) arg;
            int i = 0;
            while (true) {
                int index = i;
                Object next = tlist.getPosNext(index);
                if (next == Sequence.eofValue) {
                    break;
                }
                count++;
                sum = sum == Values.empty ? next : ArithOp.add.apply2(sum, next);
                i = tlist.nextPos(index);
            }
        } else {
            count = 1;
            sum = arg;
        }
        if (sum == Values.empty) {
            return sum;
        }
        Object apply2 = ArithOp.div.apply2(sum, IntNum.make(count));
        Object obj2 = apply2;
        return apply2;
    }
}
