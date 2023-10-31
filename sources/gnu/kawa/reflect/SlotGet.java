package gnu.kawa.reflect;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class SlotGet extends Procedure2 implements HasSetter, Inlineable {
    public static final SlotGet field;
    static Class[] noClasses = new Class[0];
    public static final SlotGet slotRef;
    public static final SlotGet staticField;
    boolean isStatic;
    Procedure setter;

    static {
        SlotGet slotGet;
        SlotGet slotGet2;
        SlotGet slotGet3;
        new SlotGet("field", false, SlotSet.set$Mnfield$Ex);
        field = slotGet;
        new SlotGet("slot-ref", false, SlotSet.set$Mnfield$Ex);
        slotRef = slotGet2;
        new SlotGet("static-field", true, SlotSet.set$Mnstatic$Mnfield$Ex);
        staticField = slotGet3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SlotGet(String name, boolean isStatic2) {
        super(name);
        this.isStatic = isStatic2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotGet");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SlotGet(String name, boolean isStatic2, Procedure setter2) {
        this(name, isStatic2);
        this.setter = setter2;
    }

    public static Object field(Object obj, String fname) {
        return field.apply2(obj, fname);
    }

    public static Object staticField(Object obj, String fname) {
        return staticField.apply2(obj, fname);
    }

    public Object apply2(Object obj, Object obj2) {
        String name;
        String fname;
        Throwable th;
        Object arg1 = obj;
        Object arg2 = obj2;
        String getName = null;
        String isName = null;
        if (arg2 instanceof Field) {
            fname = ((Field) arg2).getName();
            name = Compilation.demangleName(fname, true);
        } else if (arg2 instanceof Method) {
            String mname = ((Method) arg2).getName();
            name = Compilation.demangleName(mname, false);
            if (mname.startsWith("get")) {
                getName = mname;
            } else if (mname.startsWith("is")) {
                isName = mname;
            }
            fname = null;
        } else if ((arg2 instanceof SimpleSymbol) || (arg2 instanceof CharSequence)) {
            name = arg2.toString();
            fname = Compilation.mangleNameIfNeeded(name);
        } else {
            Throwable th2 = th;
            new WrongType((Procedure) this, 2, arg2, PropertyTypeConstants.PROPERTY_TYPE_STRING);
            throw th2;
        }
        if ("class".equals(fname)) {
            fname = "class";
        } else if ("length".equals(fname)) {
            fname = "length";
        }
        return getSlotValue(this.isStatic, arg1, name, fname, getName, isName, Language.getDefaultLanguage());
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object getSlotValue(boolean r17, java.lang.Object r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, gnu.expr.Language r23) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r12 = r0
            if (r12 == 0) goto L_0x0031
            r12 = r1
            java.lang.Class r12 = coerceToClass(r12)
        L_0x0016:
            r7 = r12
            r12 = r3
            java.lang.String r13 = "length"
            if (r12 != r13) goto L_0x0037
            r12 = r7
            boolean r12 = r12.isArray()
            if (r12 == 0) goto L_0x0037
            r12 = r1
            int r12 = java.lang.reflect.Array.getLength(r12)
            r8 = r12
            r12 = r8
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r0 = r12
        L_0x0030:
            return r0
        L_0x0031:
            r12 = r1
            java.lang.Class r12 = r12.getClass()
            goto L_0x0016
        L_0x0037:
            r12 = r3
            java.lang.String r13 = "class"
            if (r12 != r13) goto L_0x0040
            r12 = r7
            r0 = r12
            goto L_0x0030
        L_0x0040:
            r12 = 0
            r8 = r12
            r12 = r3
            if (r12 == 0) goto L_0x00a5
            r12 = r7
            r13 = r3
            java.lang.reflect.Field r12 = r12.getField(r13)     // Catch:{ Exception -> 0x008a }
            r9 = r12
        L_0x004c:
            r12 = r9
            if (r12 == 0) goto L_0x00a5
            r12 = r0
            if (r12 == 0) goto L_0x008f
            r12 = r9
            int r12 = r12.getModifiers()
            r13 = 8
            r12 = r12 & 8
            if (r12 != 0) goto L_0x008f
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "cannot access non-static field '"
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r3
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = 39
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r12
        L_0x008a:
            r12 = move-exception
            r10 = r12
            r12 = 0
            r9 = r12
            goto L_0x004c
        L_0x008f:
            r12 = r6
            r13 = r9
            java.lang.Class r13 = r13.getType()     // Catch:{ IllegalAccessException -> 0x00a1, Exception -> 0x00fe }
            r14 = r9
            r15 = r1
            java.lang.Object r14 = r14.get(r15)     // Catch:{ IllegalAccessException -> 0x00a1, Exception -> 0x00fe }
            java.lang.Object r12 = r12.coerceToObject(r13, r14)     // Catch:{ IllegalAccessException -> 0x00a1, Exception -> 0x00fe }
            r0 = r12
            goto L_0x0030
        L_0x00a1:
            r12 = move-exception
            r10 = r12
            r12 = 1
            r8 = r12
        L_0x00a5:
            r12 = 0
            r9 = r12
            r12 = 0
            r10 = r12
            r12 = r4
            if (r12 == 0) goto L_0x0105
            r12 = r4
        L_0x00ad:
            r9 = r12
            r12 = r7
            r13 = r9
            java.lang.Class[] r14 = noClasses     // Catch:{ Exception -> 0x010e }
            java.lang.reflect.Method r12 = r12.getMethod(r13, r14)     // Catch:{ Exception -> 0x010e }
            r10 = r12
        L_0x00b7:
            r12 = r0
            if (r12 == 0) goto L_0x0128
            r12 = r10
            int r12 = r12.getModifiers()     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r13 = 8
            r12 = r12 & 8
            if (r12 != 0) goto L_0x0128
            java.lang.RuntimeException r12 = new java.lang.RuntimeException     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            java.lang.String r15 = "cannot call non-static getter method '"
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r15 = r9
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r15 = 39
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            java.lang.String r14 = r14.toString()     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r13.<init>(r14)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            throw r12     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
        L_0x00f2:
            r12 = move-exception
            r9 = r12
            r12 = r9
            java.lang.Throwable r12 = r12.getTargetException()
            java.lang.RuntimeException r12 = gnu.mapping.WrappedException.wrapIfNeeded(r12)
            throw r12
        L_0x00fe:
            r12 = move-exception
            r10 = r12
            r12 = r10
            r12.printStackTrace()
            goto L_0x00a5
        L_0x0105:
            java.lang.String r12 = "get"
            r13 = r2
            java.lang.String r12 = gnu.expr.ClassExp.slotToMethodName(r12, r13)     // Catch:{ Exception -> 0x010e }
            goto L_0x00ad
        L_0x010e:
            r12 = move-exception
            r11 = r12
            r12 = r5
            if (r12 == 0) goto L_0x011f
            r12 = r5
        L_0x0114:
            r9 = r12
            r12 = r7
            r13 = r9
            java.lang.Class[] r14 = noClasses     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            java.lang.reflect.Method r12 = r12.getMethod(r13, r14)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r10 = r12
            goto L_0x00b7
        L_0x011f:
            java.lang.String r12 = "is"
            r13 = r2
            java.lang.String r12 = gnu.expr.ClassExp.slotToMethodName(r12, r13)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            goto L_0x0114
        L_0x0128:
            r12 = r10
            r13 = r1
            java.lang.Object[] r14 = gnu.mapping.Values.noArgs     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            java.lang.Object r12 = r12.invoke(r13, r14)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r11 = r12
            r12 = r6
            r13 = r10
            java.lang.Class r13 = r13.getReturnType()     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r14 = r11
            java.lang.Object r12 = r12.coerceToObject(r13, r14)     // Catch:{ InvocationTargetException -> 0x00f2, IllegalAccessException -> 0x0141, NoSuchMethodException -> 0x016f }
            r11 = r12
            r12 = r11
            r0 = r12
            goto L_0x0030
        L_0x0141:
            r12 = move-exception
            r9 = r12
            r12 = 1
            r8 = r12
        L_0x0145:
            r12 = r8
            if (r12 == 0) goto L_0x0172
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "illegal access for field "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r3
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r12
        L_0x016f:
            r12 = move-exception
            r9 = r12
            goto L_0x0145
        L_0x0172:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            java.lang.String r15 = "no such field "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r3
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " in "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r7
            java.lang.String r15 = r15.getName()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotGet.getSlotValue(boolean, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String, gnu.expr.Language):java.lang.Object");
    }

    static Class coerceToClass(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (obj2 instanceof Class) {
            return (Class) obj2;
        }
        if (obj2 instanceof Type) {
            return ((Type) obj2).getReflectClass();
        }
        Throwable th2 = th;
        new RuntimeException("argument is neither Class nor Type");
        throw th2;
    }

    public void setN(Object[] objArr) {
        Throwable th;
        Object[] args = objArr;
        int nargs = args.length;
        if (nargs != 3) {
            Throwable th2 = th;
            new WrongArguments(getSetter(), nargs);
            throw th2;
        }
        set2(args[0], args[1], args[2]);
    }

    public void set2(Object obj, Object name, Object value) {
        SlotSet.apply(this.isStatic, obj, (String) name, value);
    }

    public static Member lookupMember(ObjectType objectType, String str, ClassType classType) {
        ObjectType clas = objectType;
        String name = str;
        ClassType caller = classType;
        Field field2 = clas.getField(Compilation.mangleNameIfNeeded(name), -1);
        if (field2 != null) {
            if (caller == null) {
                caller = Type.pointer_type;
            }
            if (caller.isAccessible(field2, clas)) {
                return field2;
            }
        }
        Method method = clas.getMethod(ClassExp.slotToMethodName("get", name), Type.typeArray0);
        if (method == null) {
            return field2;
        }
        return method;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Type type;
        Target pushValue;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        Expression arg0 = args[0];
        Expression arg1 = args[1];
        Language language = comp.getLanguage();
        if (this.isStatic) {
            type = language.getTypeFor(arg0);
        } else {
            type = arg0.getType();
        }
        Type type2 = type;
        CodeAttr code = comp.getCode();
        if ((type2 instanceof ObjectType) && (arg1 instanceof QuoteExp)) {
            ObjectType ctype = (ObjectType) type2;
            Object part = ((QuoteExp) arg1).getValue();
            if (part instanceof Field) {
                Field field2 = (Field) part;
                boolean isStaticField = (field2.getModifiers() & 8) != 0;
                Expression expression = args[0];
                Compilation compilation2 = comp;
                if (isStaticField) {
                    pushValue = Target.Ignore;
                } else {
                    pushValue = Target.pushValue(ctype);
                }
                expression.compile(compilation2, pushValue);
                if (!isStaticField) {
                    code.emitGetField(field2);
                } else if (0 == 0) {
                    code.emitGetStatic(field2);
                }
                target2.compileFromStack(comp, language.getLangTypeFor(field2.getType()));
                return;
            } else if (part instanceof Method) {
                Method method = (Method) part;
                int modifiers = method.getModifiers();
                boolean isStaticMethod = method.getStaticFlag();
                args[0].compile(comp, isStaticMethod ? Target.Ignore : Target.pushValue(ctype));
                if (isStaticMethod) {
                    code.emitInvokeStatic(method);
                } else {
                    code.emitInvoke(method);
                }
                target2.compileFromStack(comp, method.getReturnType());
                return;
            }
        }
        String name = ClassMethods.checkName(arg1);
        if ((type2 instanceof ArrayType) && "length".equals(name)) {
            if (!this.isStatic) {
                args[0].compile(comp, Target.pushValue(type2));
                code.emitArrayLength();
                target2.compileFromStack(comp, LangPrimType.intType);
                return;
            }
        }
        ApplyExp.compile(exp, comp, target2);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] args = expressionArr;
        if (args.length == 2) {
            Expression arg0 = args[0];
            Expression arg1 = args[1];
            if (arg1 instanceof QuoteExp) {
                Object part = ((QuoteExp) arg1).getValue();
                if (part instanceof Field) {
                    return ((Field) part).getType();
                }
                if (part instanceof Method) {
                    return ((Method) part).getReturnType();
                }
                if (!this.isStatic && (arg0.getType() instanceof ArrayType) && "length".equals(ClassMethods.checkName(arg1, true))) {
                    return LangPrimType.intType;
                }
            }
        }
        return Type.pointer_type;
    }

    public Procedure getSetter() {
        return this.setter == null ? super.getSetter() : this.setter;
    }

    public static ApplyExp makeGetField(Expression value, String fieldName) {
        Expression expression;
        ApplyExp applyExp;
        new QuoteExp(fieldName);
        new ApplyExp((Procedure) field, value, expression);
        return applyExp;
    }
}
