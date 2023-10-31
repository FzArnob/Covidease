package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class IntegerRange extends MethodProc {
    public static final IntNum MAX_INT = IntNum.make(Integer.MAX_VALUE);
    public static final IntNum MIN_INT = IntNum.make(Integer.MIN_VALUE);
    public static final IntegerRange integerRange;

    static {
        IntegerRange integerRange2;
        new IntegerRange("to");
        integerRange = integerRange2;
    }

    public IntegerRange(String name) {
        setName(name);
    }

    public static void integerRange(IntNum intNum, IntNum intNum2, Consumer consumer) {
        IntNum first = intNum;
        IntNum last = intNum2;
        Consumer out = consumer;
        if (IntNum.compare(first, MIN_INT) < 0 || IntNum.compare(last, MAX_INT) > 0) {
            while (IntNum.compare(first, last) <= 0) {
                out.writeObject(first);
                first = IntNum.add(first, 1);
            }
            return;
        }
        int fst = first.intValue();
        int lst = last.intValue();
        if (fst <= lst) {
            while (true) {
                out.writeInt(fst);
                if (fst != lst) {
                    fst++;
                } else {
                    return;
                }
            }
        }
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Object first = ctx.getNextArg();
        Object last = ctx.getNextArg();
        ctx.lastArg();
        Object first2 = KNode.atomicValue(first);
        Object last2 = KNode.atomicValue(last);
        if (first2 != Values.empty && first2 != null && last2 != Values.empty && last2 != null) {
            if (first2 instanceof UntypedAtomic) {
                first2 = IntNum.valueOf(first2.toString().trim(), 10);
            }
            if (last2 instanceof UntypedAtomic) {
                last2 = IntNum.valueOf(last2.toString().trim(), 10);
            }
            integerRange((IntNum) first2, (IntNum) last2, ctx.consumer);
        }
    }
}
