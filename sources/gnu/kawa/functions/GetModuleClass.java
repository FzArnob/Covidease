package gnu.kawa.functions;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BindingInitializer;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URLPath;

public class GetModuleClass extends ProcedureN implements Inlineable {
    private static Symbol CLASS_RESOURCE_NAME = Namespace.getDefaultSymbol("$class_resource_URL$");
    public static final GetModuleClass getModuleClass;
    public static final GetModuleClass getModuleUri;
    public static final GetModuleClass getModuleUriDummy;
    static final Method maker = typeURLPath.getDeclaredMethod("classResourcePath", 1);
    static final ClassType typeURLPath = ClassType.make("gnu.text.URLPath");

    public GetModuleClass() {
    }

    static {
        GetModuleClass getModuleClass2;
        GetModuleClass getModuleClass3;
        GetModuleClass getModuleClass4;
        new GetModuleClass();
        getModuleClass = getModuleClass2;
        new GetModuleClass();
        getModuleUri = getModuleClass3;
        new GetModuleClass();
        getModuleUriDummy = getModuleClass4;
    }

    public Object applyN(Object[] objArr) {
        Throwable th;
        Object[] objArr2 = objArr;
        Throwable th2 = th;
        new Error("get-module-class must be inlined");
        throw th2;
    }

    public int numArgs() {
        return this == getModuleUriDummy ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 0;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        if (this == getModuleUriDummy) {
            ReferenceExp ref = (ReferenceExp) exp.getArgs()[0];
            ref.compile(comp, target2);
            Declaration decl = ref.getBinding();
            Expression init = decl.getValue();
            if (init != null) {
                BindingInitializer.create(decl, init, comp);
                decl.setValue((Expression) null);
                return;
            }
            return;
        }
        comp.loadClassRef(comp.mainClass);
        if (this == getModuleUri) {
            comp.getCode().emitInvoke(maker);
        }
        target2.compileFromStack(comp, exp.getType());
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return this == getModuleClass ? Type.javalangClassType : typeURLPath;
    }

    public static Expression getModuleClassURI(Compilation compilation) {
        ReferenceExp referenceExp;
        Expression expression;
        Declaration declaration;
        Expression clas;
        Expression expression2;
        Expression value;
        Compilation comp = compilation;
        Declaration decl = comp.mainLambda.lookup(CLASS_RESOURCE_NAME);
        if (decl == null) {
            new Declaration((Object) CLASS_RESOURCE_NAME, (Type) typeURLPath);
            decl = declaration;
            decl.setFlag(536889344);
            if (comp.immediate) {
                Path path = comp.minfo.getSourceAbsPath();
                if (path == null) {
                    path = Path.currentPath();
                }
                if (!(path instanceof URLPath)) {
                    path = URLPath.valueOf(path.toURL());
                }
                value = QuoteExp.getInstance(path);
            } else {
                new ApplyExp((Procedure) getModuleClass, Expression.noExpressions);
                Expression expression3 = expression2;
                new ApplyExp(maker, clas);
                value = expression3;
            }
            decl.setValue(value);
            comp.mainLambda.add((Declaration) null, decl);
        }
        new ReferenceExp(decl);
        ReferenceExp ref = referenceExp;
        if (comp.immediate) {
            return ref;
        }
        Expression expression4 = expression;
        new ApplyExp((Procedure) getModuleUriDummy, ref);
        return expression4;
    }
}
