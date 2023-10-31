package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

/* compiled from: srfi2.scm */
public class srfi2 extends ModuleBody {
    public static final srfi2 $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1;
    static final SyntaxTemplate Lit10;
    static final SyntaxPattern Lit11;
    static final SyntaxTemplate Lit12;
    static final SyntaxPattern Lit13;
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15;
    static final SyntaxTemplate Lit16;
    static final SyntaxTemplate Lit17;
    static final SyntaxTemplate Lit18;
    static final SyntaxPattern Lit19;
    static final SyntaxTemplate Lit2;
    static final SyntaxTemplate Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxPattern Lit3;
    static final SyntaxTemplate Lit4;
    static final SyntaxPattern Lit5;
    static final SyntaxTemplate Lit6;
    static final SyntaxPattern Lit7;
    static final SyntaxTemplate Lit8;
    static final SyntaxTemplate Lit9;
    public static final Macro and$Mnlet$St;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SyntaxTemplate syntaxTemplate;
        SyntaxPattern syntaxPattern;
        SyntaxTemplate syntaxTemplate2;
        SyntaxTemplate syntaxTemplate3;
        SyntaxTemplate syntaxTemplate4;
        SyntaxPattern syntaxPattern2;
        SyntaxTemplate syntaxTemplate5;
        SyntaxPattern syntaxPattern3;
        SyntaxTemplate syntaxTemplate6;
        SyntaxPattern syntaxPattern4;
        SyntaxTemplate syntaxTemplate7;
        SyntaxTemplate syntaxTemplate8;
        SyntaxTemplate syntaxTemplate9;
        SyntaxPattern syntaxPattern5;
        SyntaxTemplate syntaxTemplate10;
        SyntaxPattern syntaxPattern6;
        SyntaxTemplate syntaxTemplate11;
        SyntaxPattern syntaxPattern7;
        SyntaxTemplate syntaxTemplate12;
        SimpleSymbol simpleSymbol3;
        SyntaxPattern syntaxPattern8;
        SimpleSymbol simpleSymbol4;
        srfi2 srfi2;
        Procedure procedure;
        new SimpleSymbol("let");
        Lit22 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("and");
        Lit21 = (SimpleSymbol) simpleSymbol2.readResolve();
        SyntaxTemplate syntaxTemplate13 = syntaxTemplate;
        new SyntaxTemplate("\u0001", "\u0018\u0004", new Object[]{Boolean.TRUE}, 0);
        Lit20 = syntaxTemplate13;
        new SyntaxPattern("\f\u0007\f\b\b", new Object[0], 1);
        Lit19 = syntaxPattern;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit18 = syntaxTemplate2;
        SyntaxTemplate syntaxTemplate14 = syntaxTemplate3;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{Lit21}, 0);
        Lit17 = syntaxTemplate14;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit16 = syntaxTemplate4;
        new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\b", new Object[0], 3);
        Lit15 = syntaxPattern2;
        SyntaxTemplate syntaxTemplate15 = syntaxTemplate5;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{Lit21}, 0);
        Lit14 = syntaxTemplate15;
        new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\u0013\b", new Object[0], 3);
        Lit13 = syntaxPattern3;
        SyntaxTemplate syntaxTemplate16 = syntaxTemplate6;
        Object[] objArr = new Object[2];
        objArr[0] = Lit22;
        Object[] objArr2 = objArr;
        objArr2[1] = Lit21;
        new SyntaxTemplate("\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u0011\u0018\f\t\u000b\b\t\u0003\b\u001a", objArr2, 0);
        Lit12 = syntaxTemplate16;
        new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\u001b\b", new Object[0], 4);
        Lit11 = syntaxPattern4;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit10 = syntaxTemplate7;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit9 = syntaxTemplate8;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit8 = syntaxTemplate9;
        new SyntaxPattern("\f\u0007\u001c\f\u000f\b\b", new Object[0], 2);
        Lit7 = syntaxPattern5;
        new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit6 = syntaxTemplate10;
        new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\b\b", new Object[0], 2);
        Lit5 = syntaxPattern6;
        SyntaxTemplate syntaxTemplate17 = syntaxTemplate11;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u000b", new Object[]{Lit22}, 0);
        Lit4 = syntaxTemplate17;
        new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\b\b", new Object[0], 3);
        Lit3 = syntaxPattern7;
        SyntaxTemplate syntaxTemplate18 = syntaxTemplate12;
        new SimpleSymbol("begin");
        new SyntaxTemplate("\u0001\u0003\u0001\u0000", "\t\u0003\b\u0011\r\u000b\b\b\u0011\u0018\u0004\t\u0013\u001a", new Object[]{(SimpleSymbol) simpleSymbol3.readResolve()}, 1);
        Lit2 = syntaxTemplate18;
        new SyntaxPattern("\f\u0007,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4);
        Lit1 = syntaxPattern8;
        new SimpleSymbol("and-let*");
        Lit0 = (SimpleSymbol) simpleSymbol4.readResolve();
        new srfi2();
        $instance = srfi2;
        new ModuleMethod($instance, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        and$Mnlet$St = Macro.make(Lit0, procedure, $instance);
        $instance.run();
    }

    public srfi2() {
        ModuleInfo.register(this);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        return moduleMethod2.selector == 1 ? lambda1(obj2) : super.apply1(moduleMethod2, obj2);
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

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda1(Object obj) {
        Object form;
        Object[] objArr;
        Object[] objArr2;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        Object obj2 = form2;
        if (Lit1.match(form2, allocVars, 0)) {
            form = Lit2.execute(allocVars, TemplateScope.make());
        } else if (Lit3.match(form2, allocVars, 0)) {
            form = Lit4.execute(allocVars, TemplateScope.make());
        } else if (Lit5.match(form2, allocVars, 0)) {
            form = Lit6.execute(allocVars, TemplateScope.make());
        } else if (Lit7.match(form2, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit8.execute(allocVars, TemplateScope.make()))) {
                form = Lit9.execute(allocVars, TemplateScope.make());
            } else {
                Object execute = Lit10.execute(allocVars, TemplateScope.make());
                Object obj3 = "expected a variable name";
                Object obj4 = obj3;
                if (obj3 instanceof Object[]) {
                    objArr2 = (Object[]) obj4;
                } else {
                    Object obj5 = obj4;
                    Object[] objArr3 = new Object[1];
                    objArr2 = objArr3;
                    objArr3[0] = obj5;
                }
                form = prim_syntax.syntaxError(execute, objArr2);
            }
        } else if (Lit11.match(form2, allocVars, 0)) {
            form = Lit12.execute(allocVars, TemplateScope.make());
        } else if (Lit13.match(form2, allocVars, 0)) {
            form = Lit14.execute(allocVars, TemplateScope.make());
        } else if (Lit15.match(form2, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit16.execute(allocVars, TemplateScope.make()))) {
                form = Lit17.execute(allocVars, TemplateScope.make());
            } else {
                Object execute2 = Lit18.execute(allocVars, TemplateScope.make());
                Object obj6 = "expected a variable name";
                Object obj7 = obj6;
                if (obj6 instanceof Object[]) {
                    objArr = (Object[]) obj7;
                } else {
                    Object obj8 = obj7;
                    Object[] objArr4 = new Object[1];
                    objArr = objArr4;
                    objArr4[0] = obj8;
                }
                form = prim_syntax.syntaxError(execute2, objArr);
            }
        } else if (Lit19.match(form2, allocVars, 0)) {
            form = Lit20.execute(allocVars, TemplateScope.make());
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }
}
