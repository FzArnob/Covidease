package gnu.kawa.lispexpr;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.functions.MakeList;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.util.List;
import org.shaded.apache.http.cookie.ClientCookie;

public class LangObjType extends ObjectType implements TypeValue {
    private static final int CLASSTYPE_TYPE_CODE = 6;
    private static final int CLASS_TYPE_CODE = 4;
    private static final int DFLONUM_TYPE_CODE = 15;
    private static final int FILEPATH_TYPE_CODE = 2;
    private static final int INTEGER_TYPE_CODE = 7;
    private static final int LIST_TYPE_CODE = 11;
    private static final int NUMERIC_TYPE_CODE = 10;
    private static final int PATH_TYPE_CODE = 1;
    private static final int RATIONAL_TYPE_CODE = 8;
    private static final int REAL_TYPE_CODE = 9;
    private static final int REGEX_TYPE_CODE = 14;
    private static final int STRING_TYPE_CODE = 13;
    private static final int TYPE_TYPE_CODE = 5;
    public static final LangObjType URIType;
    private static final int URI_TYPE_CODE = 3;
    static final String VARARGS_SUFFIX = "";
    private static final int VECTOR_TYPE_CODE = 12;
    public static final LangObjType dflonumType;
    public static final LangObjType filepathType;
    public static final LangObjType integerType;
    public static final LangObjType listType;
    static PrimProcedure makeFilepathProc;
    static PrimProcedure makePathProc;
    static PrimProcedure makeURIProc;
    public static final LangObjType numericType;
    public static final LangObjType pathType;
    public static final LangObjType rationalType;
    public static final LangObjType realType;
    public static final LangObjType regexType;
    public static final LangObjType stringType;
    static final ClassType typeArithmetic = ClassType.make("gnu.kawa.functions.Arithmetic");
    public static final LangObjType typeClass;
    public static final LangObjType typeClassType;
    public static final ClassType typeLangObjType = ClassType.make("gnu.kawa.lispexpr.LangObjType");
    public static final LangObjType typeType;
    public static final LangObjType vectorType;
    ClassType implementationType;
    final int typeCode;

    static {
        LangObjType langObjType;
        LangObjType langObjType2;
        LangObjType langObjType3;
        LangObjType langObjType4;
        LangObjType langObjType5;
        LangObjType langObjType6;
        LangObjType langObjType7;
        LangObjType langObjType8;
        LangObjType langObjType9;
        LangObjType langObjType10;
        LangObjType langObjType11;
        LangObjType langObjType12;
        LangObjType langObjType13;
        LangObjType langObjType14;
        LangObjType langObjType15;
        PrimProcedure primProcedure;
        PrimProcedure primProcedure2;
        PrimProcedure primProcedure3;
        new LangObjType(ClientCookie.PATH_ATTR, "gnu.text.Path", 1);
        pathType = langObjType;
        new LangObjType("filepath", "gnu.text.FilePath", 2);
        filepathType = langObjType2;
        new LangObjType("URI", "gnu.text.URIPath", 3);
        URIType = langObjType3;
        new LangObjType("class", "java.lang.Class", 4);
        typeClass = langObjType4;
        new LangObjType("type", "gnu.bytecode.Type", 5);
        typeType = langObjType5;
        new LangObjType("class-type", "gnu.bytecode.ClassType", 6);
        typeClassType = langObjType6;
        new LangObjType("number", "gnu.math.Numeric", 10);
        numericType = langObjType7;
        new LangObjType("real", "gnu.math.RealNum", 9);
        realType = langObjType8;
        new LangObjType("rational", "gnu.math.RatNum", 8);
        rationalType = langObjType9;
        new LangObjType(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, "gnu.math.IntNum", 7);
        integerType = langObjType10;
        new LangObjType("DFloNum", "gnu.math.DFloNum", 15);
        dflonumType = langObjType11;
        new LangObjType("vector", "gnu.lists.FVector", 12);
        vectorType = langObjType12;
        new LangObjType("regex", "java.util.regex.Pattern", 14);
        regexType = langObjType13;
        new LangObjType(PropertyTypeConstants.PROPERTY_TYPE_STRING, "java.lang.CharSequence", 13);
        stringType = langObjType14;
        new LangObjType("list", "gnu.lists.LList", 11);
        listType = langObjType15;
        new PrimProcedure("gnu.text.Path", "valueOf", 1);
        makePathProc = primProcedure;
        new PrimProcedure("gnu.text.FilePath", "makeFilePath", 1);
        makeFilepathProc = primProcedure2;
        new PrimProcedure("gnu.text.URIPath", "makeURI", 1);
        makeURIProc = primProcedure3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LangObjType(String name, String implClass, int typeCode2) {
        super(name);
        this.implementationType = ClassType.make(implClass);
        this.typeCode = typeCode2;
        setSignature(this.implementationType.getSignature());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        if (r1 == typeClass) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        if (r1 != typeClass.implementationType) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
        if (r1 == typeType) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        if (r1 != typeClass.implementationType) goto L_0x0008;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0083, code lost:
        if ((r1 instanceof gnu.bytecode.PrimType) == false) goto L_0x0008;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0091, code lost:
        switch(r1.getSignature().charAt(0)) {
            case 68: goto L_0x0096;
            case 69: goto L_0x0094;
            case 70: goto L_0x0096;
            default: goto L_0x0094;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return getImplementationType().compare(r1.getImplementationType());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compare(gnu.bytecode.Type r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            int r3 = r3.typeCode
            switch(r3) {
                case 4: goto L_0x0018;
                case 5: goto L_0x0033;
                case 6: goto L_0x004e;
                case 7: goto L_0x006c;
                case 8: goto L_0x0008;
                case 9: goto L_0x0080;
                case 10: goto L_0x0008;
                case 11: goto L_0x0008;
                case 12: goto L_0x0008;
                case 13: goto L_0x0008;
                case 14: goto L_0x0008;
                case 15: goto L_0x0080;
                default: goto L_0x0008;
            }
        L_0x0008:
            r3 = r0
            gnu.bytecode.Type r3 = r3.getImplementationType()
            r4 = r1
            gnu.bytecode.Type r4 = r4.getImplementationType()
            int r3 = r3.compare(r4)
            r0 = r3
        L_0x0017:
            return r0
        L_0x0018:
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeType
            if (r3 == r4) goto L_0x0030
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClassType
            if (r3 == r4) goto L_0x0030
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeType
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 == r4) goto L_0x0030
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClassType
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 != r4) goto L_0x0008
        L_0x0030:
            r3 = -1
            r0 = r3
            goto L_0x0017
        L_0x0033:
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClass
            if (r3 == r4) goto L_0x004b
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClassType
            if (r3 == r4) goto L_0x004b
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClass
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 == r4) goto L_0x004b
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClassType
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 != r4) goto L_0x004e
        L_0x004b:
            r3 = 1
            r0 = r3
            goto L_0x0017
        L_0x004e:
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClass
            if (r3 == r4) goto L_0x005a
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClass
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 != r4) goto L_0x005d
        L_0x005a:
            r3 = 1
            r0 = r3
            goto L_0x0017
        L_0x005d:
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeType
            if (r3 == r4) goto L_0x0069
            r3 = r1
            gnu.kawa.lispexpr.LangObjType r4 = typeClass
            gnu.bytecode.ClassType r4 = r4.implementationType
            if (r3 != r4) goto L_0x0008
        L_0x0069:
            r3 = -1
            r0 = r3
            goto L_0x0017
        L_0x006c:
            r3 = r1
            boolean r3 = r3 instanceof gnu.bytecode.PrimType
            if (r3 == 0) goto L_0x0080
            r3 = r1
            java.lang.String r3 = r3.getSignature()
            r4 = 0
            char r3 = r3.charAt(r4)
            r2 = r3
            r3 = r2
            switch(r3) {
                case 66: goto L_0x009a;
                case 73: goto L_0x009a;
                case 74: goto L_0x009a;
                case 83: goto L_0x009a;
                default: goto L_0x0080;
            }
        L_0x0080:
            r3 = r1
            boolean r3 = r3 instanceof gnu.bytecode.PrimType
            if (r3 == 0) goto L_0x0008
            r3 = r1
            java.lang.String r3 = r3.getSignature()
            r4 = 0
            char r3 = r3.charAt(r4)
            r2 = r3
            r3 = r2
            switch(r3) {
                case 68: goto L_0x0096;
                case 69: goto L_0x0094;
                case 70: goto L_0x0096;
                default: goto L_0x0094;
            }
        L_0x0094:
            goto L_0x0008
        L_0x0096:
            r3 = 1
            r0 = r3
            goto L_0x0017
        L_0x009a:
            r3 = 1
            r0 = r3
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangObjType.compare(gnu.bytecode.Type):int");
    }

    public Field getField(String name, int mask) {
        return this.implementationType.getField(name, mask);
    }

    public Method getMethod(String name, Type[] arg_types) {
        return this.implementationType.getMethod(name, arg_types);
    }

    public Method getDeclaredMethod(String name, int argCount) {
        return this.implementationType.getDeclaredMethod(name, argCount);
    }

    public int getMethods(Filter filter, int searchSupers, List<Method> result) {
        return this.implementationType.getMethods(filter, searchSupers, result);
    }

    public Class getReflectClass() {
        return this.implementationType.getReflectClass();
    }

    public Type getRealType() {
        return this.implementationType;
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        Variable incoming = variable;
        Compilation comp = compilation;
        Target target2 = target;
        switch (this.typeCode) {
            case 11:
            case 12:
            case 13:
            case 14:
                this.implementationType.emitIsInstance(comp.getCode());
                target2.compileFromStack(comp, comp.getLanguage().getTypeFor(Boolean.TYPE));
                return;
            default:
                InstanceOf.emitIsInstance(this, incoming, comp, target2);
                return;
        }
    }

    public static Numeric coerceNumeric(Object obj) {
        Throwable th;
        Object value = obj;
        Numeric rval = Numeric.asNumericOrNull(value);
        if (rval != null || value == null) {
            return rval;
        }
        Throwable th2 = th;
        new WrongType(-4, value, (Type) numericType);
        throw th2;
    }

    public static RealNum coerceRealNum(Object obj) {
        Throwable th;
        Object value = obj;
        RealNum rval = RealNum.asRealNumOrNull(value);
        if (rval != null || value == null) {
            return rval;
        }
        Throwable th2 = th;
        new WrongType(-4, value, (Type) realType);
        throw th2;
    }

    public static DFloNum coerceDFloNum(Object obj) {
        Throwable th;
        Object value = obj;
        DFloNum rval = DFloNum.asDFloNumOrNull(value);
        if (rval != null || value == null) {
            return rval;
        }
        Throwable th2 = th;
        new WrongType(-4, value, (Type) dflonumType);
        throw th2;
    }

    public static RatNum coerceRatNum(Object obj) {
        Throwable th;
        Object value = obj;
        RatNum rval = RatNum.asRatNumOrNull(value);
        if (rval != null || value == null) {
            return rval;
        }
        Throwable th2 = th;
        new WrongType(-4, value, (Type) rationalType);
        throw th2;
    }

    public static IntNum coerceIntNum(Object obj) {
        Throwable th;
        Object value = obj;
        IntNum ival = IntNum.asIntNumOrNull(value);
        if (ival != null || value == null) {
            return ival;
        }
        Throwable th2 = th;
        new WrongType(-4, value, (Type) integerType);
        throw th2;
    }

    public static Class coerceToClassOrNull(Object obj) {
        Object type = obj;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (!(type instanceof Type) || !(type instanceof ClassType) || (type instanceof PairClassType)) {
            return null;
        }
        return ((ClassType) type).getReflectClass();
    }

    public static Class coerceToClass(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        Class coerced = coerceToClassOrNull(obj2);
        if (coerced != null || obj2 == null) {
            return coerced;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("cannot cast ").append(obj2).append(" to type").toString());
        throw th2;
    }

    public static ClassType coerceToClassTypeOrNull(Object obj) {
        Object type = obj;
        if (type instanceof ClassType) {
            return (ClassType) type;
        }
        if (type instanceof Class) {
            Type t = Language.getDefaultLanguage().getTypeFor((Class) type);
            if (t instanceof ClassType) {
                return (ClassType) t;
            }
        }
        return null;
    }

    public static ClassType coerceToClassType(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        ClassType coerced = coerceToClassTypeOrNull(obj2);
        if (coerced != null || obj2 == null) {
            return coerced;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("cannot cast ").append(obj2).append(" to class-type").toString());
        throw th2;
    }

    public static Type coerceToTypeOrNull(Object obj) {
        Object type = obj;
        if (type instanceof Type) {
            return (Type) type;
        }
        if (type instanceof Class) {
            return Language.getDefaultLanguage().getTypeFor((Class) type);
        }
        return null;
    }

    public static Type coerceToType(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        Type coerced = coerceToTypeOrNull(obj2);
        if (coerced != null || obj2 == null) {
            return coerced;
        }
        Throwable th2 = th;
        new StringBuilder();
        new ClassCastException(sb.append("cannot cast ").append(obj2).append(" to type").toString());
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public Method coercionMethod() {
        switch (this.typeCode) {
            case 4:
                return typeLangObjType.getDeclaredMethod("coerceToClass", 1);
            case 5:
                return typeLangObjType.getDeclaredMethod("coerceToType", 1);
            case 6:
                return typeLangObjType.getDeclaredMethod("coerceToClassType", 1);
            case 7:
                return typeLangObjType.getDeclaredMethod("coerceIntNum", 1);
            case 8:
                return typeLangObjType.getDeclaredMethod("coerceRatNum", 1);
            case 9:
                return typeLangObjType.getDeclaredMethod("coerceRealNum", 1);
            case 10:
                return typeLangObjType.getDeclaredMethod("coerceNumeric", 1);
            case 11:
            case 12:
            case 13:
            case 14:
                return null;
            case 15:
                return typeLangObjType.getDeclaredMethod("coerceDFloNum", 1);
            default:
                return ((PrimProcedure) getConstructor()).getMethod();
        }
    }

    /* access modifiers changed from: package-private */
    public Method coercionOrNullMethod() {
        String mname;
        ClassType methodDeclaringClass = this.implementationType;
        switch (this.typeCode) {
            case 1:
                mname = "coerceToPathOrNull";
                break;
            case 2:
                mname = "coerceToFilePathOrNull";
                break;
            case 3:
                mname = "coerceToURIPathOrNull";
                break;
            case 4:
                methodDeclaringClass = typeLangObjType;
                mname = "coerceToClassOrNull";
                break;
            case 5:
                methodDeclaringClass = typeLangObjType;
                mname = "coerceToTypeOrNull";
                break;
            case 6:
                methodDeclaringClass = typeLangObjType;
                mname = "coerceToClassTypeOrNull";
                break;
            case 7:
                methodDeclaringClass = this.implementationType;
                mname = "asIntNumOrNull";
                break;
            case 8:
                methodDeclaringClass = this.implementationType;
                mname = "asRatNumOrNull";
                break;
            case 9:
                methodDeclaringClass = this.implementationType;
                mname = "asRealNumOrNull";
                break;
            case 10:
                methodDeclaringClass = this.implementationType;
                mname = "asNumericOrNull";
                break;
            case 15:
                methodDeclaringClass = this.implementationType;
                mname = "asDFloNumOrNull";
                break;
            default:
                return null;
        }
        return methodDeclaringClass.getDeclaredMethod(mname, 1);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        Variable incoming = variable;
        Declaration decl = declaration;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        Method method = coercionOrNullMethod();
        if (method != null) {
            code.emitInvokeStatic(method);
        }
        if (decl != null) {
            code.emitDup();
            decl.compileStore(comp);
        }
        if (method != null) {
            code.emitIfNotNull();
            return;
        }
        this.implementationType.emitIsInstance(code);
        code.emitIfIntNotZero();
    }

    public Object coerceFromObject(Object obj) {
        Object obj2 = obj;
        switch (this.typeCode) {
            case 1:
                return Path.valueOf(obj2);
            case 2:
                return FilePath.makeFilePath(obj2);
            case 3:
                return URIPath.makeURI(obj2);
            case 4:
                return coerceToClass(obj2);
            case 5:
                return coerceToType(obj2);
            case 6:
                return coerceToClassType(obj2);
            case 7:
                return coerceIntNum(obj2);
            case 8:
                return coerceRatNum(obj2);
            case 9:
                return coerceRealNum(obj2);
            case 10:
                return coerceNumeric(obj2);
            case 15:
                return coerceDFloNum(obj2);
            default:
                return super.coerceFromObject(obj2);
        }
    }

    public void emitConvertFromPrimitive(Type type, CodeAttr codeAttr) {
        Type stackType = type;
        CodeAttr code = codeAttr;
        Type argType = null;
        String cname = null;
        switch (this.typeCode) {
            case 7:
            case 8:
            case 9:
            case 10:
                if (stackType instanceof PrimType) {
                    if (stackType != Type.intType && stackType != Type.byteType && stackType != Type.shortType) {
                        if (stackType != Type.longType) {
                            if (this.typeCode == 9 || this.typeCode == 10) {
                                if (stackType == Type.floatType) {
                                    code.emitConvert(Type.float_type, Type.double_type);
                                    stackType = Type.doubleType;
                                }
                                if (stackType == Type.doubleType) {
                                    cname = "gnu.math.DFloNum";
                                    argType = Type.doubleType;
                                    break;
                                }
                            }
                        } else {
                            cname = "gnu.math.IntNum";
                            argType = Type.long_type;
                            break;
                        }
                    } else {
                        cname = "gnu.math.IntNum";
                        argType = Type.int_type;
                        break;
                    }
                }
                break;
            case 15:
                if (stackType instanceof PrimType) {
                    if (stackType == Type.intType || stackType == Type.byteType || stackType == Type.shortType || stackType == Type.longType || stackType == Type.floatType) {
                        code.emitConvert(stackType, Type.doubleType);
                        stackType = Type.doubleType;
                    }
                    if (stackType == Type.doubleType) {
                        cname = "gnu.math.DFloNum";
                        argType = stackType;
                        break;
                    }
                }
                break;
        }
        if (cname != null) {
            code.emitInvokeStatic(ClassType.make(cname).getDeclaredMethod("make", new Type[]{argType}));
            return;
        }
        super.emitConvertFromPrimitive(stackType, code);
    }

    public Expression convertValue(Expression expression) {
        ApplyExp applyExp;
        Expression value = expression;
        if (this.typeCode == 7 || this.typeCode == 10 || this.typeCode == 9 || this.typeCode == 8 || this.typeCode == 15) {
            return null;
        }
        Method method = coercionMethod();
        if (method == null) {
            return null;
        }
        ApplyExp applyExp2 = applyExp;
        new ApplyExp(method, value);
        ApplyExp aexp = applyExp2;
        aexp.setType(this);
        return aexp;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        switch (this.typeCode) {
            case 11:
            case 12:
            case 13:
            case 14:
                code.emitCheckcast(this.implementationType);
                return;
            default:
                code.emitInvoke(coercionMethod());
                return;
        }
    }

    public Procedure getConstructor() {
        Procedure procedure;
        Procedure procedure2;
        Procedure procedure3;
        switch (this.typeCode) {
            case 1:
                return makePathProc;
            case 2:
                return makeFilepathProc;
            case 3:
                return makeURIProc;
            case 11:
                return MakeList.list;
            case 12:
                new PrimProcedure("gnu.lists.FVector", "make", 1);
                return procedure3;
            case 13:
                new PrimProcedure("kawa.lib.strings", "$make$string$", 1);
                return procedure2;
            case 14:
                new PrimProcedure("java.util.regex.Pattern", "compile", 1);
                return procedure;
            default:
                return null;
        }
    }
}
