package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class BindingInitializer extends Initializer {
    static final ClassType typeThreadLocation = ClassType.make("gnu.mapping.ThreadLocation");
    Declaration decl;
    Expression value;

    public static void create(Declaration declaration, Expression value2, Compilation compilation) {
        BindingInitializer bindingInitializer;
        Declaration decl2 = declaration;
        Compilation comp = compilation;
        new BindingInitializer(decl2, value2);
        BindingInitializer init = bindingInitializer;
        if (decl2.field == null ? decl2.isStatic() : decl2.field.getStaticFlag()) {
            init.next = comp.clinitChain;
            comp.clinitChain = init;
            return;
        }
        init.next = comp.mainLambda.initChain;
        comp.mainLambda.initChain = init;
    }

    public BindingInitializer(Declaration declaration, Expression value2) {
        Declaration decl2 = declaration;
        this.decl = decl2;
        this.value = value2;
        this.field = decl2.field;
    }

    public void emit(Compilation compilation) {
        Type type;
        Object val;
        Compilation comp = compilation;
        if (!this.decl.ignorable()) {
            CodeAttr code = comp.getCode();
            if (!(this.value instanceof QuoteExp) || (val = ((QuoteExp) this.value).getValue()) == null || (val instanceof String) || comp.litTable.findLiteral(val).field != this.field) {
                int line = this.decl.getLineNumber();
                SourceMessages messages = comp.getMessages();
                SourceLocator saveLoc = messages.swapSourceLocator(this.decl);
                if (line > 0) {
                    code.putLineNumber(this.decl.getFileName(), line);
                }
                if (this.field != null && !this.field.getStaticFlag()) {
                    code.emitPushThis();
                }
                if (this.value == null) {
                    Object property = (!comp.getLanguage().hasSeparateFunctionNamespace() || !this.decl.isProcedureDecl()) ? null : EnvironmentKey.FUNCTION;
                    Object name = this.decl.getSymbol();
                    if (this.decl.getFlag(268500992)) {
                        if (name instanceof String) {
                            name = Namespace.EmptyNamespace.getSymbol((String) name);
                        }
                        comp.compileConstant(name, Target.pushObject);
                        if (property == null) {
                            code.emitPushNull();
                        } else {
                            comp.compileConstant(property, Target.pushObject);
                        }
                        code.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("getInstance", 2));
                    } else if (this.decl.isFluid()) {
                        Type[] atypes = new Type[1];
                        atypes[0] = name instanceof Symbol ? Compilation.typeSymbol : Type.toStringType;
                        comp.compileConstant(name, Target.pushObject);
                        code.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("makeAnonymous", atypes));
                    } else {
                        comp.compileConstant(name, Target.pushObject);
                        code.emitInvokeStatic(makeLocationMethod(name));
                    }
                } else {
                    if (this.field == null) {
                        type = this.decl.getType();
                    } else {
                        type = this.field.getType();
                    }
                    this.value.compileWithPosition(comp, StackTarget.getInstance(type));
                }
                if (this.field == null) {
                    Variable var = this.decl.getVariable();
                    if (var == null) {
                        var = this.decl.allocateVariable(code);
                    }
                    code.emitStore(var);
                } else if (this.field.getStaticFlag()) {
                    code.emitPutStatic(this.field);
                } else {
                    code.emitPutField(this.field);
                }
                SourceLocator swapSourceLocator = messages.swapSourceLocator(saveLoc);
            }
        } else if (this.value != null) {
            this.value.compile(comp, Target.Ignore);
        }
    }

    public static Method makeLocationMethod(Object name) {
        Type[] atypes = new Type[1];
        if (name instanceof Symbol) {
            atypes[0] = Compilation.typeSymbol;
        } else {
            atypes[0] = Type.javalangStringType;
        }
        return Compilation.typeLocation.getDeclaredMethod("make", atypes);
    }
}
