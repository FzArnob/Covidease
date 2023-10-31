package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.Iterator;

public class IteratorItems extends MethodProc {
    public static IteratorItems iteratorItems;

    public IteratorItems() {
    }

    static {
        IteratorItems iteratorItems2;
        new IteratorItems();
        iteratorItems = iteratorItems2;
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer out = ctx.consumer;
        Object arg = ctx.getNextArg();
        ctx.lastArg();
        Iterator iter = (Iterator) arg;
        while (iter.hasNext()) {
            Values.writeValues(iter.next(), out);
        }
    }
}
