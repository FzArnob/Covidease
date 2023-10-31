package gnu.commonlisp.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.reflect.FieldLocation;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;
import kawa.lang.Syntax;

public abstract class Lisp2 extends LispLanguage {
    public static final LList FALSE = LList.Empty;
    public static final Symbol TRUE = Namespace.getDefault().getSymbol("t");
    public static final Expression nilExpr;

    public Lisp2() {
    }

    static {
        Expression expression;
        new QuoteExp(FALSE);
        nilExpr = expression;
    }

    public boolean isTrue(Object value) {
        return value != FALSE;
    }

    public Object booleanObject(boolean b) {
        return b ? TRUE : FALSE;
    }

    public void emitPushBoolean(boolean value, CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (value) {
            code.emitGetStatic(ClassType.make("gnu.commonlisp.lang.Lisp2").getDeclaredField("TRUE"));
        } else {
            code.emitGetStatic(Compilation.scmListType.getDeclaredField("Empty"));
        }
    }

    public Object noValue() {
        return FALSE;
    }

    public boolean hasSeparateFunctionNamespace() {
        return true;
    }

    public boolean selfEvaluatingSymbol(Object obj) {
        Object obj2 = obj;
        return (obj2 instanceof Keyword) || obj2 == TRUE || obj2 == FALSE;
    }

    public Object getEnvPropertyFor(Field fld, Object obj) {
        Object value = obj;
        if (Compilation.typeProcedure.getReflectClass().isAssignableFrom(fld.getType()) || (value instanceof Syntax)) {
            return EnvironmentKey.FUNCTION;
        }
        return null;
    }

    public int getNamespaceOf(Declaration declaration) {
        Declaration decl = declaration;
        if (decl.isAlias()) {
            return 3;
        }
        return decl.isProcedureDecl() ? 2 : 1;
    }

    public static Object asSymbol(String str) {
        String name = str;
        if (name == "nil") {
            return FALSE;
        }
        return Environment.getCurrent().getSymbol(name);
    }

    /* access modifiers changed from: protected */
    public Symbol fromLangSymbol(Object obj) {
        Object obj2 = obj;
        if (obj2 == LList.Empty) {
            return this.environ.getSymbol("nil");
        }
        return super.fromLangSymbol(obj2);
    }

    public static Object getString(String name) {
        Object obj;
        new FString(name);
        return obj;
    }

    public static Object getString(Symbol symbol) {
        return getString(symbol.getName());
    }

    /* access modifiers changed from: protected */
    public void defun(String str, Object obj) {
        String name = str;
        Object value = obj;
        this.environ.define(getSymbol(name), EnvironmentKey.FUNCTION, value);
        if (value instanceof Named) {
            Named n = (Named) value;
            if (n.getName() == null) {
                n.setName(name);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void defun(Symbol symbol, Object obj) {
        Symbol sym = symbol;
        Object value = obj;
        this.environ.define(sym, EnvironmentKey.FUNCTION, value);
        if (value instanceof Procedure) {
            Procedure n = (Procedure) value;
            if (n.getSymbol() == null) {
                n.setSymbol(sym);
            }
        }
    }

    private void defun(Procedure procedure) {
        Procedure proc = procedure;
        defun(proc.getName(), (Object) proc);
    }

    /* access modifiers changed from: protected */
    public void importLocation(Location location) {
        Location loc = location;
        Symbol name = ((NamedLocation) loc).getKeySymbol();
        if (!this.environ.isBound(name, EnvironmentKey.FUNCTION)) {
            Location loc2 = loc.getBase();
            if (!(loc2 instanceof FieldLocation) || !((FieldLocation) loc2).isProcedureOrSyntax()) {
                Object obj = loc2.get((Object) null);
                Object val = obj;
                if (obj == null) {
                    return;
                }
                if ((val instanceof Procedure) || (val instanceof Syntax)) {
                    defun(name, val);
                } else if (val instanceof LangObjType) {
                    defun(name, (Object) ((LangObjType) val).getConstructor());
                } else {
                    define(name.getName(), val);
                }
            } else {
                NamedLocation addLocation = this.environ.addLocation(name, EnvironmentKey.FUNCTION, loc2);
            }
        }
    }

    public ReadTable createReadTable() {
        ReadTable readTable;
        new Lisp2ReadTable();
        ReadTable tab = readTable;
        tab.initialize();
        tab.setInitialColonIsKeyword(true);
        return tab;
    }
}
