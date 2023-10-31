package gnu.commonlisp.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractFormat;
import gnu.mapping.Environment;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import kawa.lang.Lambda;
import kawa.standard.Scheme;
import kawa.standard.begin;

public class CommonLisp extends Lisp2 {
    static boolean charIsInt = false;
    public static final Environment clispEnvironment = Environment.make("clisp-environment");
    static final AbstractFormat displayFormat;
    public static final CommonLisp instance;
    public static final NumberCompare numEqu = NumberCompare.make(instance, "=", 8);
    public static final NumberCompare numGEq = NumberCompare.make(instance, ">=", 24);
    public static final NumberCompare numGrt = NumberCompare.make(instance, ">", 16);
    public static final NumberCompare numLEq = NumberCompare.make(instance, "<=", 12);
    public static final NumberCompare numLss = NumberCompare.make(instance, "<", 4);
    static final AbstractFormat writeFormat;
    LangPrimType booleanType;

    static {
        CommonLisp commonLisp;
        AbstractFormat abstractFormat;
        AbstractFormat abstractFormat2;
        new CommonLisp();
        instance = commonLisp;
        instance.define("t", TRUE);
        instance.define("nil", FALSE);
        Environment saveEnv = Environment.setSaveCurrent(clispEnvironment);
        try {
            instance.initLisp();
            Environment.restoreCurrent(saveEnv);
            new DisplayFormat(true, Access.CLASS_CONTEXT);
            writeFormat = abstractFormat;
            new DisplayFormat(false, Access.CLASS_CONTEXT);
            displayFormat = abstractFormat2;
        } catch (Throwable th) {
            Throwable th2 = th;
            Environment.restoreCurrent(saveEnv);
            throw th2;
        }
    }

    public static Object getCharacter(int i) {
        int c = i;
        if (charIsInt) {
            return IntNum.make(c);
        }
        return Char.make((char) c);
    }

    public static Numeric asNumber(Object obj) {
        Object arg = obj;
        if (arg instanceof Char) {
            return IntNum.make(((Char) arg).intValue());
        }
        return (Numeric) arg;
    }

    public static char asChar(Object obj) {
        int i;
        Throwable th;
        Object x = obj;
        if (x instanceof Char) {
            return ((Char) x).charValue();
        }
        if (x instanceof Numeric) {
            i = ((Numeric) x).intValue();
        } else {
            i = -1;
        }
        if (i >= 0 && i <= 65535) {
            return (char) i;
        }
        Throwable th2 = th;
        new ClassCastException("not a character value");
        throw th2;
    }

    public String getName() {
        return "CommonLisp";
    }

    public CommonLisp() {
        this.environ = clispEnvironment;
    }

    /* access modifiers changed from: package-private */
    public void initLisp() {
        Lambda lambda;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Procedure procedure;
        Object obj10;
        Object obj11;
        Object obj12;
        LocationEnumeration e = Scheme.builtin().enumerateAllLocations();
        while (e.hasMoreElements()) {
            importLocation(e.nextLocation());
        }
        try {
            loadClass("kawa.lib.prim_syntax");
            loadClass("kawa.lib.std_syntax");
            loadClass("kawa.lib.lists");
            loadClass("kawa.lib.strings");
            loadClass("gnu.commonlisp.lisp.PrimOps");
        } catch (ClassNotFoundException e2) {
            ClassNotFoundException classNotFoundException = e2;
        }
        new Lambda();
        Lambda lambda2 = lambda;
        lambda2.setKeywords(asSymbol("&optional"), asSymbol("&rest"), asSymbol("&key"));
        lambda2.defaultDefault = nilExpr;
        defun("lambda", (Object) lambda2);
        new defun(lambda2);
        defun("defun", obj);
        new defvar(false);
        defun("defvar", obj2);
        new defvar(true);
        defun("defconst", obj3);
        new defun(lambda2);
        defun("defsubst", obj4);
        new function(lambda2);
        defun("function", obj5);
        new setq();
        defun("setq", obj6);
        new prog1("prog1", 1);
        defun("prog1", obj7);
        defun("prog2", (Object) prog1.prog2);
        new begin();
        defun("progn", obj8);
        new UnwindProtect();
        defun("unwind-protect", obj9);
        new Not(this);
        Procedure not = procedure;
        defun("not", (Object) not);
        defun("null", (Object) not);
        new IsEq(this, "eq");
        defun("eq", obj10);
        new IsEqual(this, "equal");
        defun("equal", obj11);
        new InstanceOf(this);
        defun("typep", obj12);
        defun("princ", (Object) displayFormat);
        defun("prin1", (Object) writeFormat);
        defProcStFld("=", "gnu.commonlisp.lang.CommonLisp", "numEqu");
        defProcStFld("<", "gnu.commonlisp.lang.CommonLisp", "numLss");
        defProcStFld(">", "gnu.commonlisp.lang.CommonLisp", "numGrt");
        defProcStFld("<=", "gnu.commonlisp.lang.CommonLisp", "numLEq");
        defProcStFld(">=", "gnu.commonlisp.lang.CommonLisp", "numGEq");
        defProcStFld("functionp", "gnu.commonlisp.lisp.PrimOps");
    }

    public static CommonLisp getInstance() {
        return instance;
    }

    public static void registerEnvironment() {
        Language.setDefaults(instance);
    }

    public AbstractFormat getFormat(boolean readable) {
        return readable ? writeFormat : displayFormat;
    }

    public Type getTypeFor(String str) {
        String name = str;
        if (name == "t") {
            name = "java.lang.Object";
        }
        return Scheme.string2Type(name);
    }

    public Type getTypeFor(Class cls) {
        LangPrimType langPrimType;
        Class clas = cls;
        if (!clas.isPrimitive()) {
            return Type.make(clas);
        }
        String name = clas.getName();
        if (!name.equals(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)) {
            return Scheme.getNamedType(name);
        }
        if (this.booleanType == null) {
            new LangPrimType(Type.booleanType, this);
            this.booleanType = langPrimType;
        }
        return this.booleanType;
    }
}
