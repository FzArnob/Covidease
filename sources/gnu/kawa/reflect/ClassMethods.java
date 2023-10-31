package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.GenericProc;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.util.Vector;

public class ClassMethods extends Procedure2 {
    public static final ClassMethods classMethods;

    public ClassMethods() {
    }

    static {
        ClassMethods classMethods2;
        new ClassMethods();
        classMethods = classMethods2;
        classMethods.setName("class-methods");
    }

    public Object apply2(Object arg0, Object arg1) {
        return apply(this, arg0, arg1);
    }

    public static MethodProc apply(Procedure procedure, Object obj, Object obj2) {
        ClassType dtype;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Procedure thisProc = procedure;
        Object arg0 = obj;
        Object arg1 = obj2;
        if (arg0 instanceof Class) {
            arg0 = Type.make((Class) arg0);
        }
        if (arg0 instanceof ClassType) {
            dtype = (ClassType) arg0;
        } else if ((arg0 instanceof String) || (arg0 instanceof FString) || (arg0 instanceof Symbol)) {
            dtype = ClassType.make(arg0.toString());
        } else {
            Throwable th4 = th;
            new WrongType(thisProc, 0, (ClassCastException) null);
            throw th4;
        }
        if ((arg1 instanceof String) || (arg1 instanceof FString) || (arg1 instanceof Symbol)) {
            String mname = arg1.toString();
            if (!"<init>".equals(mname)) {
                mname = Compilation.mangleName(mname);
            }
            MethodProc result = apply(dtype, mname, 0, Language.getDefaultLanguage());
            if (result != null) {
                return result;
            }
            Throwable th5 = th2;
            new StringBuilder();
            new RuntimeException(sb.append("no applicable method named `").append(mname).append("' in ").append(dtype.getName()).toString());
            throw th5;
        }
        Throwable th6 = th3;
        new WrongType(thisProc, 1, (ClassCastException) null);
        throw th6;
    }

    private static int removeRedundantMethods(Vector vector) {
        Vector methods = vector;
        int mlength = methods.size();
        int i = 1;
        while (i < mlength) {
            Method method1 = (Method) methods.elementAt(i);
            ClassType class1 = method1.getDeclaringClass();
            Type[] types1 = method1.getParameterTypes();
            int tlen = types1.length;
            int j = 0;
            while (true) {
                if (j >= i) {
                    i++;
                    break;
                }
                Method method2 = (Method) methods.elementAt(j);
                Type[] types2 = method2.getParameterTypes();
                if (tlen == types2.length) {
                    int k = tlen;
                    do {
                        k--;
                        if (k < 0 || types1[k] != types2[k]) {
                        }
                        k--;
                        break;
                    } while (types1[k] != types2[k]);
                    if (k < 0) {
                        if (class1.isSubtype(method2.getDeclaringClass())) {
                            methods.setElementAt(method1, j);
                        }
                        methods.setElementAt(methods.elementAt(mlength - 1), i);
                        mlength--;
                    }
                }
                j++;
            }
        }
        return mlength;
    }

    public static PrimProcedure[] getMethods(ObjectType objectType, String str, char c, ClassType classType, Language language) {
        Vector vector;
        int removeRedundantMethods;
        PrimProcedure pproc;
        Method method;
        ObjectType dtype = objectType;
        String mname = str;
        char mode = c;
        ClassType caller = classType;
        Language language2 = language;
        if (dtype == Type.tostring_type) {
            dtype = Type.string_type;
        }
        MethodFilter methodFilter = r24;
        MethodFilter methodFilter2 = new MethodFilter(mname, 0, 0, caller, mode == 'P' ? null : dtype);
        MethodFilter filter = methodFilter;
        boolean named_class_only = mode == 'P' || "<init>".equals(mname);
        new Vector();
        Vector methods = vector;
        int methods2 = dtype.getMethods(filter, named_class_only ? 0 : 2, methods);
        if (!named_class_only && (!(dtype instanceof ClassType) || ((ClassType) dtype).isInterface())) {
            int methods3 = Type.pointer_type.getMethods(filter, 0, methods);
        }
        if (named_class_only) {
            removeRedundantMethods = methods.size();
        } else {
            removeRedundantMethods = removeRedundantMethods(methods);
        }
        int mlength = removeRedundantMethods;
        PrimProcedure[] result = new PrimProcedure[mlength];
        int count = 0;
        int i = mlength;
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            Method method2 = (Method) methods.elementAt(i);
            if (!named_class_only && method2.getDeclaringClass() != dtype) {
                Type itype = dtype.getImplementationType();
                if (itype instanceof ClassType) {
                    new Method(method2, (ClassType) itype);
                    method2 = method;
                }
            }
            new PrimProcedure(method2, mode, language2);
            int i2 = count;
            count++;
            result[i2] = pproc;
        }
    }

    public static long selectApplicable(PrimProcedure[] primProcedureArr, Type[] typeArr) {
        PrimProcedure[] methods = primProcedureArr;
        Type[] atypes = typeArr;
        int limit = methods.length;
        int numDefApplicable = 0;
        int numPosApplicable = 0;
        int i = 0;
        while (i < limit) {
            int code = methods[i].isApplicable(atypes);
            if (code < 0) {
                PrimProcedure tmp = methods[limit - 1];
                methods[limit - 1] = methods[i];
                methods[i] = tmp;
                limit--;
            } else if (code > 0) {
                PrimProcedure tmp2 = methods[numDefApplicable];
                methods[numDefApplicable] = methods[i];
                methods[i] = tmp2;
                numDefApplicable++;
                i++;
            } else {
                numPosApplicable++;
                i++;
            }
        }
        return (((long) numDefApplicable) << 32) + ((long) numPosApplicable);
    }

    public static int selectApplicable(PrimProcedure[] primProcedureArr, int i) {
        PrimProcedure[] methods = primProcedureArr;
        int numArgs = i;
        int limit = methods.length;
        int numTooManyArgs = 0;
        int numTooFewArgs = 0;
        int numOk = 0;
        int i2 = 0;
        while (i2 < limit) {
            int num = methods[i2].numArgs();
            int min = Procedure.minArgs(num);
            int max = Procedure.maxArgs(num);
            boolean ok = false;
            if (numArgs < min) {
                numTooFewArgs++;
            } else if (numArgs <= max || max < 0) {
                ok = true;
            } else {
                numTooManyArgs++;
            }
            if (ok) {
                numOk++;
                i2++;
            } else {
                PrimProcedure tmp = methods[limit - 1];
                methods[limit - 1] = methods[i2];
                methods[i2] = tmp;
                limit--;
            }
        }
        return numOk > 0 ? numOk : numTooFewArgs > 0 ? MethodProc.NO_MATCH_TOO_FEW_ARGS : numTooManyArgs > 0 ? MethodProc.NO_MATCH_TOO_MANY_ARGS : 0;
    }

    public static MethodProc apply(ObjectType objectType, String str, char mode, Language language) {
        StringBuilder sb;
        GenericProc genericProc;
        ObjectType dtype = objectType;
        String mname = str;
        PrimProcedure[] methods = getMethods(dtype, mname, mode, (ClassType) null, language);
        GenericProc gproc = null;
        PrimProcedure pproc = null;
        for (int i = 0; i < methods.length; i++) {
            PrimProcedure cur = methods[i];
            if (pproc != null && gproc == null) {
                new GenericProc();
                gproc = genericProc;
                gproc.add((MethodProc) pproc);
            }
            pproc = cur;
            if (gproc != null) {
                gproc.add((MethodProc) pproc);
            }
        }
        if (gproc == null) {
            return pproc;
        }
        new StringBuilder();
        gproc.setName(sb.append(dtype.getName()).append(".").append(mname).toString());
        return gproc;
    }

    static String checkName(Expression expression, boolean z) {
        String nam;
        Expression exp = expression;
        boolean reversible = z;
        if (!(exp instanceof QuoteExp)) {
            return null;
        }
        Object name = ((QuoteExp) exp).getValue();
        if ((name instanceof FString) || (name instanceof String)) {
            nam = name.toString();
        } else if (!(name instanceof Symbol)) {
            return null;
        } else {
            nam = ((Symbol) name).getName();
        }
        if (Compilation.isValidJavaName(nam)) {
            return nam;
        }
        return Compilation.mangleName(nam, reversible);
    }

    static String checkName(Expression expression) {
        Expression exp = expression;
        if (!(exp instanceof QuoteExp)) {
            return null;
        }
        Object name = ((QuoteExp) exp).getValue();
        if ((name instanceof FString) || (name instanceof String)) {
            return name.toString();
        }
        if (name instanceof Symbol) {
            return ((Symbol) name).getName();
        }
        return null;
    }
}
