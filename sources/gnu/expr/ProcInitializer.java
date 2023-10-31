package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.mapping.Environment;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;

public class ProcInitializer extends Initializer {
    LambdaExp proc;

    public ProcInitializer(LambdaExp lambdaExp, Compilation compilation, Field field) {
        LambdaExp lexp = lambdaExp;
        Compilation comp = compilation;
        Field field2 = field;
        this.field = field2;
        this.proc = lexp;
        LambdaExp heapLambda = field2.getStaticFlag() ? comp.getModule() : lexp.getOwningLambda();
        if (!(heapLambda instanceof ModuleExp) || !comp.isStatic()) {
            this.next = heapLambda.initChain;
            heapLambda.initChain = this;
            return;
        }
        this.next = comp.clinitChain;
        comp.clinitChain = this;
    }

    public static void emitLoadModuleMethod(LambdaExp lambdaExp, Compilation compilation) {
        String initName;
        Symbol sym;
        LambdaExp proc2 = lambdaExp;
        Compilation comp = compilation;
        Declaration pdecl = proc2.nameDecl;
        Object pname = pdecl == null ? proc2.getName() : pdecl.getSymbol();
        ModuleMethod oldproc = null;
        if (!(!comp.immediate || pname == null || pdecl == null)) {
            Environment env = Environment.getCurrent();
            if (pname instanceof Symbol) {
                sym = (Symbol) pname;
            } else {
                sym = Symbol.make("", pname.toString().intern());
            }
            Object old = env.get(sym, comp.getLanguage().getEnvPropertyFor(proc2.nameDecl), (Object) null);
            if (old instanceof ModuleMethod) {
                oldproc = (ModuleMethod) old;
            }
        }
        CodeAttr code = comp.getCode();
        ClassType procClass = Compilation.typeModuleMethod;
        if (oldproc == null) {
            code.emitNew(procClass);
            code.emitDup(1);
            initName = "<init>";
        } else {
            comp.compileConstant(oldproc, Target.pushValue(procClass));
            initName = "init";
        }
        Method initModuleMethod = procClass.getDeclaredMethod(initName, 4);
        LambdaExp owning = proc2.getNeedsClosureEnv() ? proc2.getOwningLambda() : comp.getModule();
        if ((owning instanceof ClassExp) && owning.staticLinkField != null) {
            code.emitLoad(code.getCurrentScope().getVariable(1));
        } else if (!(owning instanceof ModuleExp) || (comp.moduleClass == comp.mainClass && !comp.method.getStaticFlag())) {
            code.emitPushThis();
        } else {
            if (comp.moduleInstanceVar == null) {
                comp.moduleInstanceVar = code.locals.current_scope.addVariable(code, comp.moduleClass, "$instance");
                if (comp.moduleClass == comp.mainClass || comp.isStatic()) {
                    code.emitGetStatic(comp.moduleInstanceMainField);
                } else {
                    code.emitNew(comp.moduleClass);
                    code.emitDup((Type) comp.moduleClass);
                    code.emitInvokeSpecial(comp.moduleClass.constructor);
                    comp.moduleInstanceMainField = comp.moduleClass.addField("$main", comp.mainClass, 0);
                    code.emitDup((Type) comp.moduleClass);
                    code.emitPushThis();
                    code.emitPutField(comp.moduleInstanceMainField);
                }
                code.emitStore(comp.moduleInstanceVar);
            }
            code.emitLoad(comp.moduleInstanceVar);
        }
        code.emitPushInt(proc2.getSelectorValue(comp));
        comp.compileConstant(pname, Target.pushObject);
        code.emitPushInt(proc2.min_args | ((proc2.keywords == null ? proc2.max_args : -1) << 12));
        code.emitInvoke(initModuleMethod);
        if (proc2.properties != null) {
            int len = proc2.properties.length;
            for (int i = 0; i < len; i += 2) {
                Object key = proc2.properties[i];
                if (!(key == null || key == PropertySet.nameKey)) {
                    Object val = proc2.properties[i + 1];
                    code.emitDup(1);
                    comp.compileConstant(key);
                    Target target = Target.pushObject;
                    if (val instanceof Expression) {
                        ((Expression) val).compile(comp, target);
                    } else {
                        comp.compileConstant(val, target);
                    }
                    code.emitInvokeVirtual(ClassType.make("gnu.mapping.PropertySet").getDeclaredMethod("setProperty", 2));
                }
            }
        }
    }

    public void emit(Compilation compilation) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (!this.field.getStaticFlag()) {
            code.emitPushThis();
        }
        emitLoadModuleMethod(this.proc, comp);
        if (this.field.getStaticFlag()) {
            code.emitPutStatic(this.field);
        } else {
            code.emitPutField(this.field);
        }
    }

    public void reportError(String message, Compilation compilation) {
        StringBuffer stringBuffer;
        Compilation comp = compilation;
        String saveFile = comp.getFileName();
        int saveLine = comp.getLineNumber();
        int saveColumn = comp.getColumnNumber();
        comp.setLocation(this.proc);
        String name = this.proc.getName();
        new StringBuffer(message);
        StringBuffer sbuf = stringBuffer;
        if (name == null) {
            StringBuffer append = sbuf.append("unnamed procedure");
        } else {
            StringBuffer append2 = sbuf.append("procedure ");
            StringBuffer append3 = sbuf.append(name);
        }
        comp.error('e', sbuf.toString());
        comp.setLine(saveFile, saveLine, saveColumn);
    }
}
