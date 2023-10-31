package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: keywords.scm */
public class keywords extends ModuleBody {
    public static final keywords $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod keyword$Mn$Grstring;
    public static final ModuleMethod keyword$Qu;
    public static final ModuleMethod string$Mn$Grkeyword;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        keywords keywords;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        new SimpleSymbol("string->keyword");
        Lit2 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("keyword->string");
        Lit1 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("keyword?");
        Lit0 = (SimpleSymbol) simpleSymbol3.readResolve();
        new keywords();
        $instance = keywords;
        keywords keywords2 = $instance;
        new ModuleMethod(keywords2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keyword$Qu = moduleMethod;
        new ModuleMethod(keywords2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keyword$Mn$Grstring = moduleMethod2;
        new ModuleMethod(keywords2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grkeyword = moduleMethod3;
        $instance.run();
    }

    public keywords() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isKeyword(Object object) {
        return Keyword.isKeyword(object);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Keyword)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static CharSequence keyword$To$String(Keyword keyword) {
        return keyword.getName();
    }

    public static Keyword string$To$Keyword(String string) {
        return Keyword.make(string);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isKeyword(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return keyword$To$String((Keyword) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "keyword->string", 1, obj2);
                    throw th2;
                }
            case 3:
                Object obj3 = obj2;
                return string$To$Keyword(obj3 == null ? null : obj3.toString());
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
