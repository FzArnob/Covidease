package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;

public class MakeList extends ProcedureN implements Inlineable {
    public static final MakeList list;

    public MakeList() {
    }

    static {
        MakeList makeList;
        new MakeList();
        list = makeList;
        list.setName("list");
    }

    public static Object list$V(Object[] objArr) {
        LList lList;
        Object[] args = objArr;
        LList result = LList.Empty;
        int i = args.length;
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            new Pair(args[i], result);
            result = lList;
        }
    }

    public Object applyN(Object[] args) {
        return list$V(args);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        compile(exp.getArgs(), 0, comp);
        target.compileFromStack(comp, exp.getType());
    }

    public static void compile(Expression[] expressionArr, int i, Compilation compilation) {
        StringBuilder sb;
        QuoteExp quoteExp;
        Expression[] args = expressionArr;
        int offset = i;
        Compilation comp = compilation;
        int len = args.length - offset;
        CodeAttr code = comp.getCode();
        if (len == 0) {
            new QuoteExp(LList.Empty);
            quoteExp.compile(comp, Target.pushObject);
        } else if (len <= 4) {
            for (int i2 = 0; i2 < len; i2++) {
                args[offset + i2].compile(comp, Target.pushObject);
            }
            ClassType classType = Compilation.scmListType;
            new StringBuilder();
            code.emitInvokeStatic(classType.getDeclaredMethod(sb.append("list").append(len).toString(), (Type[]) null));
        } else {
            args[offset].compile(comp, Target.pushObject);
            code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list1", (Type[]) null));
            code.emitDup(1);
            int offset2 = offset + 1;
            int len2 = len - 1;
            while (len2 >= 4) {
                args[offset2].compile(comp, Target.pushObject);
                args[offset2 + 1].compile(comp, Target.pushObject);
                args[offset2 + 2].compile(comp, Target.pushObject);
                args[offset2 + 3].compile(comp, Target.pushObject);
                len2 -= 4;
                offset2 += 4;
                code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain4", (Type[]) null));
            }
            while (len2 > 0) {
                args[offset2].compile(comp, Target.pushObject);
                len2--;
                offset2++;
                code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain1", (Type[]) null));
            }
            code.emitPop(1);
        }
    }

    public Type getReturnType(Expression[] args) {
        return args.length > 0 ? Compilation.typePair : LangObjType.listType;
    }
}
