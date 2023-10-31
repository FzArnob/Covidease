package gnu.kawa.functions;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class CountValues extends Procedure1 {
    public static final CountValues countValues;

    public CountValues() {
    }

    static {
        CountValues countValues2;
        new CountValues();
        countValues = countValues2;
    }

    public static int countValues(Object obj) {
        Object arg = obj;
        return arg instanceof Values ? ((Values) arg).size() : 1;
    }

    public Object apply1(Object arg) {
        return IntNum.make(countValues(arg));
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer consumer = ctx.consumer;
        Object arg = ctx.getNextArg();
        ctx.lastArg();
        consumer.writeInt(countValues(arg));
    }
}
