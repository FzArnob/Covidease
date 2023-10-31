package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.CheckedTarget;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import kawa.standard.Scheme;

public class SlotSet extends Procedure3 implements Inlineable {
    public static final SlotSet set$Mnfield$Ex;
    public static final SlotSet set$Mnstatic$Mnfield$Ex;
    public static final SlotSet setFieldReturnObject;
    static final Type[] type1Array = new Type[1];
    boolean isStatic;
    boolean returnSelf;

    static {
        SlotSet slotSet;
        SlotSet slotSet2;
        SlotSet slotSet3;
        new SlotSet("set-field!", false);
        set$Mnfield$Ex = slotSet;
        new SlotSet("set-static-field!", true);
        set$Mnstatic$Mnfield$Ex = slotSet2;
        new SlotSet("set-field-return-object!", false);
        setFieldReturnObject = slotSet3;
        setFieldReturnObject.returnSelf = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SlotSet(String name, boolean isStatic2) {
        super(name);
        this.isStatic = isStatic2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotSet");
    }

    public static void setField(Object obj, String name, Object value) {
        apply(false, obj, name, value);
    }

    public static void setStaticField(Object obj, String name, Object value) {
        apply(true, obj, name, value);
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void apply(boolean r21, java.lang.Object r22, java.lang.Object r23, java.lang.Object r24) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            gnu.expr.Language r15 = gnu.expr.Language.getDefaultLanguage()
            r4 = r15
            r15 = 0
            r5 = r15
            r15 = r2
            boolean r15 = r15 instanceof java.lang.String
            if (r15 != 0) goto L_0x001e
            r15 = r2
            boolean r15 = r15 instanceof gnu.lists.FString
            if (r15 != 0) goto L_0x001e
            r15 = r2
            boolean r15 = r15 instanceof gnu.mapping.Symbol
            if (r15 == 0) goto L_0x005d
        L_0x001e:
            r15 = r2
            java.lang.String r15 = r15.toString()
            r6 = r15
            r15 = r6
            java.lang.String r15 = gnu.expr.Compilation.mangleNameIfNeeded(r15)
            r7 = r15
            r15 = r0
            if (r15 == 0) goto L_0x0057
            r15 = r1
            java.lang.Class r15 = gnu.kawa.reflect.SlotGet.coerceToClass(r15)
        L_0x0032:
            r8 = r15
        L_0x0033:
            r15 = r2
            boolean r15 = r15 instanceof gnu.bytecode.Field     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
            if (r15 == 0) goto L_0x0070
            r15 = r2
            gnu.bytecode.Field r15 = (gnu.bytecode.Field) r15     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
            java.lang.reflect.Field r15 = r15.getReflectField()     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
        L_0x003f:
            r9 = r15
            r15 = r9
            java.lang.Class r15 = r15.getType()     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
            r10 = r15
            r15 = r9
            r16 = r1
            r17 = r4
            r18 = r10
            r19 = r3
            java.lang.Object r17 = r17.coerceFromObject(r18, r19)     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
            r15.set(r16, r17)     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
        L_0x0056:
            return
        L_0x0057:
            r15 = r1
            java.lang.Class r15 = r15.getClass()
            goto L_0x0032
        L_0x005d:
            r15 = r2
            gnu.bytecode.Member r15 = (gnu.bytecode.Member) r15
            java.lang.String r15 = r15.getName()
            r20 = r15
            r15 = r20
            r16 = r20
            r6 = r16
            r7 = r15
            r15 = 0
            r8 = r15
            goto L_0x0033
        L_0x0070:
            r15 = r8
            r16 = r7
            java.lang.reflect.Field r15 = r15.getField(r16)     // Catch:{ NoSuchFieldException -> 0x0078, IllegalAccessException -> 0x00ff }
            goto L_0x003f
        L_0x0078:
            r15 = move-exception
            r9 = r15
        L_0x007a:
            r15 = 0
            r9 = r15
            r15 = r2
            boolean r15 = r15 instanceof gnu.bytecode.Method     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r10 = r15
            r15 = r10
            if (r15 == 0) goto L_0x0105
            r15 = r7
        L_0x0084:
            r11 = r15
            r15 = r10
            if (r15 == 0) goto L_0x0094
            r15 = r11
            java.lang.String r16 = "set"
            boolean r15 = r15.startsWith(r16)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            if (r15 != 0) goto L_0x0094
            r15 = 0
            r10 = r15
        L_0x0094:
            r15 = r10
            if (r15 == 0) goto L_0x0110
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x011a }
            r20 = r15
            r15 = r20
            r16 = r20
            r16.<init>()     // Catch:{ Exception -> 0x011a }
            java.lang.String r16 = "get"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x011a }
            r16 = r11
            r17 = 3
            java.lang.String r16 = r16.substring(r17)     // Catch:{ Exception -> 0x011a }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x011a }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x011a }
        L_0x00b9:
            r12 = r15
            r15 = r8
            r16 = r12
            java.lang.Class[] r17 = gnu.kawa.reflect.SlotGet.noClasses     // Catch:{ Exception -> 0x011a }
            java.lang.reflect.Method r15 = r15.getMethod(r16, r17)     // Catch:{ Exception -> 0x011a }
            r9 = r15
        L_0x00c4:
            r15 = 1
            java.lang.Class[] r15 = new java.lang.Class[r15]     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r12 = r15
            r15 = r12
            r16 = 0
            r17 = r9
            java.lang.Class r17 = r17.getReturnType()     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r15[r16] = r17     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r15 = r8
            r16 = r11
            r17 = r12
            java.lang.reflect.Method r15 = r15.getMethod(r16, r17)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r13 = r15
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r14 = r15
            r15 = r14
            r16 = 0
            r17 = r4
            r18 = r12
            r19 = 0
            r18 = r18[r19]     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r19 = r3
            java.lang.Object r17 = r17.coerceFromObject(r18, r19)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r15[r16] = r17     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r15 = r13
            r16 = r1
            r17 = r14
            java.lang.Object r15 = r15.invoke(r16, r17)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            goto L_0x0056
        L_0x00ff:
            r15 = move-exception
            r9 = r15
            r15 = 1
            r5 = r15
            goto L_0x007a
        L_0x0105:
            java.lang.String r15 = "set"
            r16 = r6
            java.lang.String r15 = gnu.expr.ClassExp.slotToMethodName(r15, r16)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            goto L_0x0084
        L_0x0110:
            java.lang.String r15 = "get"
            r16 = r6
            java.lang.String r15 = gnu.expr.ClassExp.slotToMethodName(r15, r16)     // Catch:{ Exception -> 0x011a }
            goto L_0x00b9
        L_0x011a:
            r15 = move-exception
            r12 = r15
            r15 = r10
            if (r15 == 0) goto L_0x014e
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r20 = r15
            r15 = r20
            r16 = r20
            r16.<init>()     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            java.lang.String r16 = "is"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r16 = r11
            r17 = 3
            java.lang.String r16 = r16.substring(r17)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            java.lang.String r15 = r15.toString()     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
        L_0x0141:
            r13 = r15
            r15 = r8
            r16 = r13
            java.lang.Class[] r17 = gnu.kawa.reflect.SlotGet.noClasses     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            java.lang.reflect.Method r15 = r15.getMethod(r16, r17)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            r9 = r15
            goto L_0x00c4
        L_0x014e:
            java.lang.String r15 = "is"
            r16 = r6
            java.lang.String r15 = gnu.expr.ClassExp.slotToMethodName(r15, r16)     // Catch:{ InvocationTargetException -> 0x0158, IllegalAccessException -> 0x0164, NoSuchMethodException -> 0x0193 }
            goto L_0x0141
        L_0x0158:
            r15 = move-exception
            r9 = r15
            r15 = r9
            java.lang.Throwable r15 = r15.getTargetException()
            java.lang.RuntimeException r15 = gnu.mapping.WrappedException.wrapIfNeeded(r15)
            throw r15
        L_0x0164:
            r15 = move-exception
            r9 = r15
            r15 = 1
            r5 = r15
        L_0x0168:
            r15 = r5
            if (r15 == 0) goto L_0x0196
            java.lang.RuntimeException r15 = new java.lang.RuntimeException
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r20 = r17
            r17 = r20
            r18 = r20
            r18.<init>()
            java.lang.String r18 = "illegal access for field "
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r6
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r16.<init>(r17)
            throw r15
        L_0x0193:
            r15 = move-exception
            r9 = r15
            goto L_0x0168
        L_0x0196:
            java.lang.RuntimeException r15 = new java.lang.RuntimeException
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r20 = r17
            r17 = r20
            r18 = r20
            r18.<init>()
            java.lang.String r18 = "no such field "
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r6
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r18 = " in "
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r8
            java.lang.String r18 = r18.getName()
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r16.<init>(r17)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotSet.apply(boolean, java.lang.Object, java.lang.Object, java.lang.Object):void");
    }

    public Object apply3(Object obj, Object fname, Object value) {
        Object obj2 = obj;
        apply(this.isStatic, obj2, fname, value);
        return this.returnSelf ? obj2 : Values.empty;
    }

    public static Member lookupMember(ObjectType objectType, String str, ClassType classType) {
        ObjectType clas = objectType;
        String name = str;
        ClassType caller = classType;
        Field field = clas.getField(Compilation.mangleNameIfNeeded(name), -1);
        if (field != null) {
            if (caller == null) {
                caller = Type.pointer_type;
            }
            if (caller.isAccessible(field, clas)) {
                return field;
            }
        }
        Method method = clas.getMethod(ClassExp.slotToMethodName("set", name), type1Array);
        if (method == null) {
            return field;
        }
        return method;
    }

    static void compileSet(Procedure procedure, ObjectType objectType, Expression expression, Object obj, Compilation compilation) {
        StringBuilder sb;
        StringBuilder sb2;
        Procedure thisProc = procedure;
        ObjectType objectType2 = objectType;
        Expression valArg = expression;
        Object part = obj;
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        Language language = comp.getLanguage();
        boolean isStatic2 = (thisProc instanceof SlotSet) && ((SlotSet) thisProc).isStatic;
        if (part instanceof Field) {
            Field field = (Field) part;
            boolean isStaticField = field.getStaticFlag();
            Type ftype = language.getLangTypeFor(field.getType());
            if (isStatic2 && !isStaticField) {
                new StringBuilder();
                comp.error('e', sb2.append("cannot access non-static field `").append(field.getName()).append("' using `").append(thisProc.getName()).append('\'').toString());
            }
            valArg.compile(comp, CheckedTarget.getInstance(ftype));
            if (isStaticField) {
                code.emitPutStatic(field);
            } else {
                code.emitPutField(field);
            }
        } else if (part instanceof Method) {
            Method method = (Method) part;
            boolean isStaticMethod = method.getStaticFlag();
            if (isStatic2 && !isStaticMethod) {
                new StringBuilder();
                comp.error('e', sb.append("cannot call non-static getter method `").append(method.getName()).append("' using `").append(thisProc.getName()).append('\'').toString());
            }
            valArg.compile(comp, CheckedTarget.getInstance(language.getLangTypeFor(method.getParameterTypes()[0])));
            if (isStaticMethod) {
                code.emitInvokeStatic(method);
            } else {
                code.emitInvoke(method);
            }
            if (!method.getReturnType().isVoid()) {
                code.emitPop(1);
            }
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ClassType classType;
        String name;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        int nargs = args.length;
        if (nargs != 3) {
            String msg = nargs < 3 ? "too few" : "too many";
            new StringBuilder();
            comp.error('e', sb3.append(msg).append(" arguments to `").append(getName()).append('\'').toString());
            comp.compileConstant((Object) null, target2);
            return;
        }
        Expression arg0 = args[0];
        Expression arg1 = args[1];
        Expression expression = args[2];
        Type type = this.isStatic ? Scheme.exp2Type(arg0) : arg0.getType();
        Member part = null;
        if ((type instanceof ObjectType) && (arg1 instanceof QuoteExp)) {
            Object val1 = ((QuoteExp) arg1).getValue();
            ObjectType ctype = (ObjectType) type;
            if (comp.curClass != null) {
                classType = comp.curClass;
            } else {
                classType = comp.mainClass;
            }
            ClassType caller = classType;
            if ((val1 instanceof String) || (val1 instanceof FString) || (val1 instanceof Symbol)) {
                name = val1.toString();
                part = lookupMember(ctype, name, caller);
                if (part == null && type != Type.pointer_type && comp.warnUnknownMember()) {
                    new StringBuilder();
                    comp.error('w', sb2.append("no slot `").append(name).append("' in ").append(ctype.getName()).toString());
                }
            } else if (val1 instanceof Member) {
                part = (Member) val1;
                name = part.getName();
            } else {
                name = null;
            }
            if (part != null) {
                boolean isStaticField = (part.getModifiers() & 8) != 0;
                if (caller != null && !caller.isAccessible(part, ctype)) {
                    new StringBuilder();
                    comp.error('e', sb.append("slot '").append(name).append("' in ").append(part.getDeclaringClass().getName()).append(" not accessible here").toString());
                }
                args[0].compile(comp, isStaticField ? Target.Ignore : Target.pushValue(ctype));
                if (this.returnSelf) {
                    comp.getCode().emitDup(ctype.getImplementationType());
                }
                compileSet(this, ctype, args[2], part, comp);
                if (this.returnSelf) {
                    target2.compileFromStack(comp, ctype);
                    return;
                } else {
                    comp.compileConstant(Values.empty, target2);
                    return;
                }
            }
        }
        ApplyExp.compile(exp, comp, target2);
    }
}
