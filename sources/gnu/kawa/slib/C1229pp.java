package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lib.ports;

/* renamed from: gnu.kawa.slib.pp */
/* compiled from: pp.scm */
public class C1229pp extends ModuleBody {
    public static final C1229pp $instance;
    static final IntNum Lit0 = IntNum.make(79);
    static final SimpleSymbol Lit1;
    public static final ModuleMethod pretty$Mnprint;

    static {
        SimpleSymbol simpleSymbol;
        C1229pp ppVar;
        ModuleMethod moduleMethod;
        new SimpleSymbol("pretty-print");
        Lit1 = (SimpleSymbol) simpleSymbol.readResolve();
        new C1229pp();
        $instance = ppVar;
        new ModuleMethod($instance, 2, Lit1, 8193);
        pretty$Mnprint = moduleMethod;
        $instance.run();
    }

    public C1229pp() {
        ModuleInfo.register(this);
    }

    public static Object prettyPrint(Object obj) {
        return prettyPrint(obj, ports.current$Mnoutput$Mnport.apply0());
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    /* renamed from: gnu.kawa.slib.pp$frame */
    /* compiled from: pp.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object port;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pp.scm:9");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 1) {
                return lambda1(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda1(Object s) {
            ports.display(s, this.port);
            return Boolean.TRUE;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object prettyPrint(Object obj, Object port) {
        frame frame2;
        new frame();
        frame frame3 = frame2;
        frame3.port = port;
        return genwrite.genericWrite(obj, Boolean.FALSE, Lit0, frame3.lambda$Fn1);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector == 2) {
            return prettyPrint(obj2);
        }
        return super.apply1(moduleMethod2, obj2);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector == 2) {
            return prettyPrint(obj3, obj4);
        }
        return super.apply2(moduleMethod2, obj3, obj4);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 2) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        callContext2.value1 = obj2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 2) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        callContext2.value1 = obj3;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
    }
}
