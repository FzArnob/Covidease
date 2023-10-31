package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;

public class DistinctValues {
    public DistinctValues() {
    }

    public static void distinctValues$X(Object values, NamedCollator coll, CallContext ctx) {
        Consumer consumer;
        new DistinctValuesConsumer(coll, ctx.consumer);
        Values.writeValues(values, consumer);
    }
}
