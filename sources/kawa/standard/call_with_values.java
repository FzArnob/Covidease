package kawa.standard;

import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class call_with_values extends Procedure2 {
    public static final call_with_values callWithValues;

    public call_with_values() {
    }

    static {
        call_with_values call_with_values;
        new call_with_values();
        callWithValues = call_with_values;
        callWithValues.setName("call-with-values");
    }

    public static Object callWithValues(Procedure producer, Procedure procedure) throws Throwable {
        Procedure consumer = procedure;
        Object values = producer.apply0();
        if (values instanceof Values) {
            return ((Values) values).call_with(consumer);
        }
        return consumer.apply1(values);
    }

    public Object apply2(Object producer, Object consumer) throws Throwable {
        return callWithValues((Procedure) producer, (Procedure) consumer);
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        Procedure.checkArgCount(this, 2);
        Object[] args = ctx.getArgs();
        Object values = ((Procedure) args[0]).apply0();
        Procedure consumer = (Procedure) args[1];
        if (values instanceof Values) {
            consumer.checkN(((Values) values).getValues(), ctx);
            return;
        }
        consumer.check1(values, ctx);
    }
}
