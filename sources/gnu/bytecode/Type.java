package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.util.AbstractWeakHashTable;
import java.io.PrintWriter;
import java.util.HashMap;

public abstract class Type implements java.lang.reflect.Type {
    public static final PrimType booleanType;
    public static final Method booleanValue_method = javalangBooleanType.addMethod("booleanValue", typeArray0, (Type) booleanType, 1);
    public static final ClassType boolean_ctype = javalangBooleanType;
    public static final PrimType boolean_type = booleanType;
    public static final PrimType byteType;
    public static final PrimType byte_type = byteType;
    public static final PrimType charType;
    public static final PrimType char_type = charType;
    public static final Method clone_method = Method.makeCloneMethod(objectType);
    public static final PrimType doubleType;
    public static final Method doubleValue_method = javalangNumberType.addMethod("doubleValue", typeArray0, (Type) doubleType, 1);
    public static final PrimType double_type = doubleType;
    public static final ObjectType errorType;
    public static final PrimType floatType;
    public static final Method floatValue_method = javalangNumberType.addMethod("floatValue", typeArray0, (Type) floatType, 1);
    public static final PrimType float_type = floatType;
    public static final PrimType intType;
    public static final Method intValue_method = javalangNumberType.addMethod("intValue", typeArray0, (Type) intType, 1);
    public static final PrimType int_type = intType;
    public static final ClassType java_lang_Class_type = javalangClassType;
    public static final ClassType javalangBooleanType = ClassType.make("java.lang.Boolean");
    public static final ClassType javalangClassType = ClassType.make("java.lang.Class");
    public static final ClassType javalangNumberType = ClassType.make("java.lang.Number");
    public static final ClassType javalangObjectType = ClassType.make("java.lang.Object");
    public static ClassType javalangStringType = ClassType.make("java.lang.String");
    public static final ClassType javalangThrowableType = ClassType.make("java.lang.Throwable");
    public static final PrimType longType;
    public static final Method longValue_method = javalangNumberType.addMethod("longValue", typeArray0, (Type) longType, 1);
    public static final PrimType long_type = longType;
    static ClassToTypeMap mapClassToType;
    static HashMap<String, Type> mapNameToType;
    public static final PrimType neverReturnsType;
    public static final ObjectType nullType;
    public static final ClassType number_type = javalangNumberType;
    public static final ClassType objectType = javalangObjectType;
    public static final ClassType pointer_type = javalangObjectType;
    public static final PrimType shortType;
    public static final PrimType short_type = shortType;
    public static final ClassType string_type = javalangStringType;
    public static final ClassType throwable_type = javalangThrowableType;
    public static final ClassType toStringType;
    public static final Method toString_method = objectType.getDeclaredMethod("toString", 0);
    public static final ClassType tostring_type = toStringType;
    public static final Type[] typeArray0 = new Type[0];
    public static final PrimType voidType;
    public static final PrimType void_type = voidType;
    ArrayType array_type;
    protected Class reflectClass;
    String signature;
    int size;
    String this_name;

    public abstract Object coerceFromObject(Object obj);

    public abstract int compare(Type type);

    protected Type() {
    }

    public Type getImplementationType() {
        return this;
    }

    public Type getRealType() {
        return this;
    }

    public boolean isExisting() {
        return true;
    }

    public static Type lookupType(String str) {
        String name = str;
        HashMap<String, Type> map = mapNameToType;
        HashMap<String, Type> hashMap = map;
        HashMap<String, Type> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                Type type = map.get(name);
                return type;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<String, Type> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: gnu.bytecode.ArrayType} */
    /* JADX WARNING: type inference failed for: r6v16, types: [gnu.bytecode.ClassType] */
    /* JADX WARNING: type inference failed for: r7v5, types: [gnu.bytecode.ClassType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.bytecode.Type getType(java.lang.String r10) {
        /*
            r0 = r10
            java.util.HashMap<java.lang.String, gnu.bytecode.Type> r6 = mapNameToType
            r1 = r6
            r6 = r1
            r9 = r6
            r6 = r9
            r7 = r9
            r2 = r7
            monitor-enter(r6)
            r6 = r1
            r7 = r0
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x004b }
            gnu.bytecode.Type r6 = (gnu.bytecode.Type) r6     // Catch:{ all -> 0x004b }
            r3 = r6
            r6 = r3
            if (r6 != 0) goto L_0x002d
            r6 = r0
            java.lang.String r7 = "[]"
            boolean r6 = r6.endsWith(r7)     // Catch:{ all -> 0x004b }
            if (r6 == 0) goto L_0x0032
            r6 = r0
            gnu.bytecode.ArrayType r6 = gnu.bytecode.ArrayType.make((java.lang.String) r6)     // Catch:{ all -> 0x004b }
            r3 = r6
        L_0x0026:
            r6 = r1
            r7 = r0
            r8 = r3
            java.lang.Object r6 = r6.put(r7, r8)     // Catch:{ all -> 0x004b }
        L_0x002d:
            r6 = r3
            r7 = r2
            monitor-exit(r7)     // Catch:{ all -> 0x004b }
            r0 = r6
            return r0
        L_0x0032:
            gnu.bytecode.ClassType r6 = new gnu.bytecode.ClassType     // Catch:{ all -> 0x004b }
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)     // Catch:{ all -> 0x004b }
            r4 = r6
            r6 = r4
            r9 = r6
            r6 = r9
            r7 = r9
            int r7 = r7.flags     // Catch:{ all -> 0x004b }
            r8 = 16
            r7 = r7 | 16
            r6.flags = r7     // Catch:{ all -> 0x004b }
            r6 = r4
            r3 = r6
            goto L_0x0026
        L_0x004b:
            r6 = move-exception
            r5 = r6
            r6 = r2
            monitor-exit(r6)     // Catch:{ all -> 0x004b }
            r6 = r5
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.Type.getType(java.lang.String):gnu.bytecode.Type");
    }

    public static synchronized void registerTypeForClass(Class cls, Type type) {
        ClassToTypeMap classToTypeMap;
        Class clas = cls;
        Type type2 = type;
        synchronized (Type.class) {
            ClassToTypeMap map = mapClassToType;
            if (map == null) {
                new ClassToTypeMap();
                ClassToTypeMap classToTypeMap2 = classToTypeMap;
                map = classToTypeMap2;
                mapClassToType = classToTypeMap2;
            }
            type2.reflectClass = clas;
            Object put = map.put(clas, type2);
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: gnu.bytecode.ArrayType} */
    /* JADX WARNING: type inference failed for: r7v21, types: [gnu.bytecode.ClassType] */
    /* JADX WARNING: type inference failed for: r8v3, types: [gnu.bytecode.ClassType] */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    public static synchronized gnu.bytecode.Type make(java.lang.Class r12) {
        /*
            r0 = r12
            java.lang.Class<gnu.bytecode.Type> r10 = gnu.bytecode.Type.class
            monitor-enter(r10)
            gnu.bytecode.Type$ClassToTypeMap r7 = mapClassToType     // Catch:{ all -> 0x0049 }
            if (r7 == 0) goto L_0x0019
            gnu.bytecode.Type$ClassToTypeMap r7 = mapClassToType     // Catch:{ all -> 0x0049 }
            r8 = r0
            java.lang.Object r7 = r7.get(r8)     // Catch:{ all -> 0x0049 }
            gnu.bytecode.Type r7 = (gnu.bytecode.Type) r7     // Catch:{ all -> 0x0049 }
            r2 = r7
            r7 = r2
            if (r7 == 0) goto L_0x0019
            r7 = r2
            r0 = r7
        L_0x0017:
            monitor-exit(r10)
            return r0
        L_0x0019:
            r7 = r0
            boolean r7 = r7.isArray()     // Catch:{ all -> 0x0049 }
            if (r7 == 0) goto L_0x0036
            r7 = r0
            java.lang.Class r7 = r7.getComponentType()     // Catch:{ all -> 0x0049 }
            gnu.bytecode.Type r7 = make(r7)     // Catch:{ all -> 0x0049 }
            gnu.bytecode.ArrayType r7 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r7)     // Catch:{ all -> 0x0049 }
            r1 = r7
        L_0x002e:
            r7 = r0
            r8 = r1
            registerTypeForClass(r7, r8)     // Catch:{ all -> 0x0049 }
            r7 = r1
            r0 = r7
            goto L_0x0017
        L_0x0036:
            r7 = r0
            boolean r7 = r7.isPrimitive()     // Catch:{ all -> 0x0049 }
            if (r7 == 0) goto L_0x004c
            java.lang.Error r7 = new java.lang.Error     // Catch:{ all -> 0x0049 }
            r11 = r7
            r7 = r11
            r8 = r11
            java.lang.String r9 = "internal error - primitive type not found"
            r8.<init>(r9)     // Catch:{ all -> 0x0049 }
            throw r7     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x004c:
            r7 = r0
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0049 }
            r2 = r7
            java.util.HashMap<java.lang.String, gnu.bytecode.Type> r7 = mapNameToType     // Catch:{ all -> 0x0049 }
            r3 = r7
            r7 = r3
            r11 = r7
            r7 = r11
            r8 = r11
            r4 = r8
            monitor-enter(r7)     // Catch:{ all -> 0x0049 }
            r7 = r3
            r8 = r2
            java.lang.Object r7 = r7.get(r8)     // Catch:{ all -> 0x0095 }
            gnu.bytecode.Type r7 = (gnu.bytecode.Type) r7     // Catch:{ all -> 0x0095 }
            r1 = r7
            r7 = r1
            if (r7 == 0) goto L_0x0072
            r7 = r1
            java.lang.Class r7 = r7.reflectClass     // Catch:{ all -> 0x0095 }
            r8 = r0
            if (r7 == r8) goto L_0x0092
            r7 = r1
            java.lang.Class r7 = r7.reflectClass     // Catch:{ all -> 0x0095 }
            if (r7 == 0) goto L_0x0092
        L_0x0072:
            gnu.bytecode.ClassType r7 = new gnu.bytecode.ClassType     // Catch:{ all -> 0x0095 }
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r2
            r8.<init>(r9)     // Catch:{ all -> 0x0095 }
            r5 = r7
            r7 = r5
            r11 = r7
            r7 = r11
            r8 = r11
            int r8 = r8.flags     // Catch:{ all -> 0x0095 }
            r9 = 16
            r8 = r8 | 16
            r7.flags = r8     // Catch:{ all -> 0x0095 }
            r7 = r5
            r1 = r7
            java.util.HashMap<java.lang.String, gnu.bytecode.Type> r7 = mapNameToType     // Catch:{ all -> 0x0095 }
            r8 = r2
            r9 = r1
            java.lang.Object r7 = r7.put(r8, r9)     // Catch:{ all -> 0x0095 }
        L_0x0092:
            r7 = r4
            monitor-exit(r7)     // Catch:{ all -> 0x0095 }
            goto L_0x002e
        L_0x0095:
            r7 = move-exception
            r6 = r7
            r7 = r4
            monitor-exit(r7)     // Catch:{ all -> 0x0095 }
            r7 = r6
            throw r7     // Catch:{ all -> 0x0049 }
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.Type.make(java.lang.Class):gnu.bytecode.Type");
    }

    public String getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: protected */
    public void setSignature(String sig) {
        String str = sig;
        this.signature = str;
    }

    Type(String nam, String sig) {
        this.this_name = nam;
        this.signature = sig;
    }

    public Type(Type type) {
        Type type2 = type;
        this.this_name = type2.this_name;
        this.signature = type2.signature;
        this.size = type2.size;
        this.reflectClass = type2.reflectClass;
    }

    public Type promote() {
        return this.size < 4 ? intType : this;
    }

    public final int getSize() {
        return this.size;
    }

    public int getSizeInWords() {
        return this.size > 4 ? 2 : 1;
    }

    public final boolean isVoid() {
        return this.size == 0;
    }

    public static PrimType signatureToPrimitive(char sig) {
        switch (sig) {
            case 'B':
                return byteType;
            case 'C':
                return charType;
            case 'D':
                return doubleType;
            case 'F':
                return floatType;
            case 'I':
                return intType;
            case 'J':
                return longType;
            case 'S':
                return shortType;
            case 'V':
                return voidType;
            case 'Z':
                return booleanType;
            default:
                return null;
        }
    }

    public static Type signatureToType(String str, int i, int i2) {
        Type type;
        String sig = str;
        int off = i;
        int len = i2;
        if (len == 0) {
            return null;
        }
        char c = sig.charAt(off);
        if (len == 1 && (type = signatureToPrimitive(c)) != null) {
            return type;
        }
        if (c == '[') {
            Type type2 = signatureToType(sig, off + 1, len - 1);
            return type2 == null ? null : ArrayType.make(type2);
        } else if (c == 'L' && len > 2 && sig.indexOf(59, off) == (len - 1) + off) {
            return ClassType.make(sig.substring(off + 1, (len - 1) + off).replace('/', '.'));
        } else {
            return null;
        }
    }

    public static Type signatureToType(String str) {
        String sig = str;
        return signatureToType(sig, 0, sig.length());
    }

    public static void printSignature(String str, int i, int i2, PrintWriter printWriter) {
        String sig = str;
        int off = i;
        int len = i2;
        PrintWriter out = printWriter;
        if (len != 0) {
            char c = sig.charAt(off);
            if (len == 1) {
                Type type = signatureToPrimitive(c);
                if (type != null) {
                    out.print(type.getName());
                }
            } else if (c == '[') {
                printSignature(sig, off + 1, len - 1, out);
                out.print("[]");
            } else if (c == 'L' && len > 2 && sig.indexOf(59, off) == (len - 1) + off) {
                out.print(sig.substring(off + 1, (len - 1) + off).replace('/', '.'));
            } else {
                PrintWriter append = out.append(sig, off, len - off);
            }
        }
    }

    public static int signatureLength(String str, int i) {
        int end;
        String sig = str;
        int pos = i;
        if (sig.length() <= pos) {
            return -1;
        }
        char c = sig.charAt(pos);
        int arrays = 0;
        while (c == '[') {
            arrays++;
            pos++;
            c = sig.charAt(pos);
        }
        if (signatureToPrimitive(c) != null) {
            return arrays + 1;
        }
        if (c != 'L' || (end = sig.indexOf(59, pos)) <= 0) {
            return -1;
        }
        return ((arrays + end) + 1) - pos;
    }

    public static int signatureLength(String sig) {
        return signatureLength(sig, 0);
    }

    public static String signatureToName(String str) {
        StringBuffer stringBuffer;
        Type type;
        String sig = str;
        int len = sig.length();
        if (len == 0) {
            return null;
        }
        char c = sig.charAt(0);
        if (len == 1 && (type = signatureToPrimitive(c)) != null) {
            return type.getName();
        }
        if (c == '[') {
            int arrays = 1;
            if (1 < len && sig.charAt(1) == '[') {
                arrays = 1 + 1;
            }
            String sig2 = signatureToName(sig.substring(arrays));
            if (sig2 == null) {
                return null;
            }
            new StringBuffer(50);
            StringBuffer buf = stringBuffer;
            StringBuffer append = buf.append(sig2);
            while (true) {
                arrays--;
                if (arrays < 0) {
                    return buf.toString();
                }
                StringBuffer append2 = buf.append("[]");
            }
        } else if (c == 'L' && len > 2 && sig.indexOf(59) == len - 1) {
            return sig.substring(1, len - 1).replace('/', '.');
        } else {
            return null;
        }
    }

    public final String getName() {
        return this.this_name;
    }

    /* access modifiers changed from: protected */
    public void setName(String name) {
        String str = name;
        this.this_name = str;
    }

    public static boolean isValidJavaTypeName(String str) {
        boolean z;
        String name = str;
        boolean in_name = false;
        int len = name.length();
        while (len > 2 && name.charAt(len - 1) == ']' && name.charAt(len - 2) == '[') {
            len -= 2;
        }
        int i = 0;
        while (i < len) {
            char ch = name.charAt(i);
            if (ch != '.') {
                if (in_name) {
                    if (!Character.isJavaIdentifierPart(ch)) {
                        return false;
                    }
                } else if (!Character.isJavaIdentifierStart(ch)) {
                    return false;
                }
                z = true;
            } else if (!in_name) {
                return false;
            } else {
                z = false;
            }
            in_name = z;
            i++;
        }
        return i == len;
    }

    public boolean isInstance(Object obj) {
        return getReflectClass().isInstance(obj);
    }

    public final boolean isSubtype(Type other) {
        int comp = compare(other);
        return comp == -1 || comp == 0;
    }

    public static Type lowestCommonSuperType(Type type, Type type2) {
        Type t1 = type;
        Type t2 = type2;
        if (t1 == neverReturnsType) {
            return t2;
        }
        if (t2 == neverReturnsType) {
            return t1;
        }
        if (t1 == null || t2 == null) {
            return null;
        }
        if (!(t1 instanceof PrimType) || !(t2 instanceof PrimType)) {
            if (t1.isSubtype(t2)) {
                return t2;
            }
            if (t2.isSubtype(t1)) {
                return t1;
            }
            if (!(t1 instanceof ClassType) || !(t2 instanceof ClassType)) {
                return objectType;
            }
            ClassType c1 = (ClassType) t1;
            ClassType c2 = (ClassType) t2;
            if (c1.isInterface() || c2.isInterface()) {
                return objectType;
            }
            return lowestCommonSuperType(c1.getSuperclass(), c2.getSuperclass());
        } else if (t1 == t2) {
            return t1;
        } else {
            Type t12 = ((PrimType) t1).promotedType();
            return t12 == ((PrimType) t2).promotedType() ? t12 : null;
        }
    }

    protected static int swappedCompareResult(int i) {
        int code = i;
        return code == 1 ? -1 : code == -1 ? 1 : code;
    }

    public static boolean isMoreSpecific(Type[] typeArr, Type[] typeArr2) {
        Type[] t1 = typeArr;
        Type[] t2 = typeArr2;
        if (t1.length != t2.length) {
            return false;
        }
        int i = t1.length;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (t1[i].isSubtype(t2[i]));
        return false;
    }

    public void emitIsInstance(CodeAttr code) {
        code.emitInstanceof(this);
    }

    public Object coerceToObject(Object obj) {
        return obj;
    }

    public void emitConvertFromPrimitive(Type stackType, CodeAttr code) {
        stackType.emitCoerceToObject(code);
    }

    public void emitCoerceToObject(CodeAttr code) {
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        Throwable th;
        StringBuilder sb;
        CodeAttr codeAttr2 = codeAttr;
        Throwable th2 = th;
        new StringBuilder();
        new Error(sb.append("unimplemented emitCoerceFromObject for ").append(this).toString());
        throw th2;
    }

    static {
        PrimType primType;
        PrimType primType2;
        PrimType primType3;
        PrimType primType4;
        PrimType primType5;
        PrimType primType6;
        PrimType primType7;
        PrimType primType8;
        PrimType primType9;
        HashMap<String, Type> hashMap;
        PrimType primType10;
        ObjectType objectType2;
        ObjectType objectType3;
        ClassType classType;
        new PrimType("byte", "B", 1, Byte.TYPE);
        byteType = primType;
        new PrimType("short", "S", 2, Short.TYPE);
        shortType = primType2;
        new PrimType("int", "I", 4, Integer.TYPE);
        intType = primType3;
        new PrimType("long", "J", 8, Long.TYPE);
        longType = primType4;
        new PrimType(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, "F", 4, Float.TYPE);
        floatType = primType5;
        new PrimType("double", "D", 8, Double.TYPE);
        doubleType = primType6;
        new PrimType(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, "Z", 1, Boolean.TYPE);
        booleanType = primType7;
        new PrimType("char", "C", 2, Character.TYPE);
        charType = primType8;
        new PrimType("void", "V", 0, Void.TYPE);
        voidType = primType9;
        new HashMap<>();
        mapNameToType = hashMap;
        Type put = mapNameToType.put("byte", byteType);
        Type put2 = mapNameToType.put("short", shortType);
        Type put3 = mapNameToType.put("int", intType);
        Type put4 = mapNameToType.put("long", longType);
        Type put5 = mapNameToType.put(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, floatType);
        Type put6 = mapNameToType.put("double", doubleType);
        Type put7 = mapNameToType.put(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, booleanType);
        Type put8 = mapNameToType.put("char", charType);
        Type put9 = mapNameToType.put("void", voidType);
        new PrimType(voidType);
        neverReturnsType = primType10;
        neverReturnsType.this_name = "(never-returns)";
        new ObjectType("(type of null)");
        nullType = objectType2;
        new ClassType("(error type)");
        errorType = objectType3;
        new ClassType("java.lang.String");
        toStringType = classType;
    }

    public Class getReflectClass() {
        return this.reflectClass;
    }

    public void setReflectClass(Class rclass) {
        Class cls = rclass;
        this.reflectClass = cls;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Type ").append(getName()).toString();
    }

    public int hashCode() {
        String name = toString();
        return name == null ? 0 : name.hashCode();
    }

    static class ClassToTypeMap extends AbstractWeakHashTable<Class, Type> {
        ClassToTypeMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(Type type) {
            return type.reflectClass;
        }

        /* access modifiers changed from: protected */
        public boolean matches(Class oldValue, Class newValue) {
            return oldValue == newValue;
        }
    }
}
