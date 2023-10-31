package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class MakeResponseHeader extends MethodProc {
    public static MakeResponseHeader makeResponseHeader;

    public MakeResponseHeader() {
    }

    static {
        MakeResponseHeader makeResponseHeader2;
        new MakeResponseHeader();
        makeResponseHeader = makeResponseHeader2;
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        String key = ctx.getNextArg().toString();
        Object val = ctx.getNextArg();
        ctx.lastArg();
        Consumer out = ctx.consumer;
        out.startAttribute(key);
        out.write(val.toString());
        out.endAttribute();
    }
}
