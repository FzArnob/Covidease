package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_implements extends Syntax {
    public static final module_implements module_implements;

    public module_implements() {
    }

    static {
        module_implements module_implements2;
        new module_implements();
        module_implements = module_implements2;
        module_implements.setName("module-implements");
    }

    public void scanForm(Pair form, ScopeExp scopeExp, Translator translator) {
        StringBuilder sb;
        ScopeExp scopeExp2 = scopeExp;
        Translator tr = translator;
        Object args = form.getCdr();
        int len = LList.listLength(args, false);
        if (len < 0) {
            new StringBuilder();
            Expression syntaxError = tr.syntaxError(sb.append("improper argument list for ").append(getName()).toString());
            return;
        }
        ClassType[] interfaces = new ClassType[len];
        for (int i = 0; i < len; i++) {
            Pair pair = (Pair) args;
            interfaces[i] = (ClassType) tr.exp2Type(pair);
            args = pair.getCdr();
        }
        ModuleExp module = tr.getModule();
        module.setInterfaces(interfaces);
        module.setFlag(131072);
    }
}
