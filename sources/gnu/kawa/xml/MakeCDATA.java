package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.xml.TextUtils;

public class MakeCDATA extends MethodProc {
    public static final MakeCDATA makeCDATA;

    public MakeCDATA() {
    }

    static {
        MakeCDATA makeCDATA2;
        new MakeCDATA();
        makeCDATA = makeCDATA2;
    }

    public void apply(CallContext callContext) {
        StringBuffer stringBuffer;
        CallContext ctx = callContext;
        Consumer saved = ctx.consumer;
        XConsumer out = NodeConstructor.pushNodeContext(ctx);
        try {
            new StringBuffer();
            StringBuffer sbuf = stringBuffer;
            String endMarker = Location.UNBOUND;
            while (true) {
                Object arg = ctx.getNextArg(endMarker);
                if (arg == endMarker) {
                    int n = sbuf.length();
                    char[] chars = new char[n];
                    sbuf.getChars(0, n, chars, 0);
                    out.writeCDATA(chars, 0, n);
                    NodeConstructor.popNodeContext(saved, ctx);
                    return;
                }
                TextUtils.stringValue(arg, sbuf);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            NodeConstructor.popNodeContext(saved, ctx);
            throw th2;
        }
    }
}
