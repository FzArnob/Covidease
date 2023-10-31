package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Expression;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.ListPat;
import kawa.lang.Pattern;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prim_method extends Syntax {
    public static final prim_method interface_method;
    public static final prim_method op1;
    private static Pattern pattern3;
    private static Pattern pattern4;
    public static final prim_method static_method;
    public static final prim_method virtual_method;
    int op_code;

    static {
        prim_method prim_method;
        prim_method prim_method2;
        prim_method prim_method3;
        prim_method prim_method4;
        Pattern pattern;
        Pattern pattern2;
        new prim_method(182);
        virtual_method = prim_method;
        virtual_method.setName("primitive-virtual-method");
        new prim_method(184);
        static_method = prim_method2;
        static_method.setName("primitive-static-method");
        new prim_method(185);
        interface_method = prim_method3;
        interface_method.setName("primitive-interface-method");
        new prim_method();
        op1 = prim_method4;
        op1.setName("primitive-op1");
        new ListPat(3);
        pattern3 = pattern;
        new ListPat(4);
        pattern4 = pattern2;
    }

    /* access modifiers changed from: package-private */
    public int opcode() {
        return this.op_code;
    }

    public prim_method(int opcode) {
        this.op_code = opcode;
    }

    public prim_method() {
    }

    public Expression rewrite(Object obj, Translator translator) {
        StringBuilder sb;
        Pair pair;
        char code;
        StringBuilder sb2;
        PrimProcedure primProcedure;
        PrimProcedure proc;
        Expression expression;
        PrimProcedure primProcedure2;
        StringBuilder sb3;
        Object obj2 = obj;
        Translator tr = translator;
        Object[] match = new Object[4];
        if (this.op_code != 0 ? !pattern4.match(obj2, match, 0) : !pattern3.match(obj2, match, 1)) {
            new StringBuilder();
            return tr.syntaxError(sb.append("wrong number of arguments to ").append(getName()).append("(opcode:").append(this.op_code).append(")").toString());
        } else if (!(match[3] instanceof LList)) {
            new StringBuilder();
            return tr.syntaxError(sb3.append("missing/invalid parameter list in ").append(getName()).toString());
        } else {
            LList argp = (LList) match[3];
            int narg = argp.size();
            Type[] args = new Type[narg];
            for (int i = 0; i < narg; i++) {
                Pair p = (Pair) argp;
                args[i] = tr.exp2Type(p);
                argp = (LList) p.getCdr();
            }
            new Pair(match[2], (Object) null);
            Type rtype = tr.exp2Type(pair);
            if (this.op_code == 0) {
                new PrimProcedure(((Number) match[1]).intValue(), rtype, args);
                proc = primProcedure2;
            } else {
                ClassType cl = null;
                Type ctype = tr.exp2Type((Pair) obj2);
                if (ctype != null) {
                    ctype = ctype.getImplementationType();
                }
                try {
                    cl = (ClassType) ctype;
                    Class reflectClass = cl.getReflectClass();
                } catch (Exception e) {
                    Exception exc = e;
                    if (cl == null) {
                        code = 'e';
                    } else {
                        code = 'w';
                        cl.setExisting(false);
                    }
                    new StringBuilder();
                    tr.error(code, sb2.append("unknown class: ").append(match[0]).toString());
                }
                if (match[1] instanceof Pair) {
                    Pair pair2 = (Pair) match[1];
                    Pair p2 = pair2;
                    if (pair2.getCar() == LispLanguage.quote_sym) {
                        match[1] = ((Pair) p2.getCdr()).getCar();
                    }
                }
                new PrimProcedure(this.op_code, cl, match[1].toString(), rtype, args);
                proc = primProcedure;
            }
            new QuoteExp(proc);
            return expression;
        }
    }
}
