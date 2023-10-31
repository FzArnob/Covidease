package gnu.bytecode;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.shaded.apache.http.HttpStatus;

public class ClassType extends ObjectType implements AttrContainer, Externalizable, Member {
    public static final int JDK_1_1_VERSION = 2949123;
    public static final int JDK_1_2_VERSION = 3014656;
    public static final int JDK_1_3_VERSION = 3080192;
    public static final int JDK_1_4_VERSION = 3145728;
    public static final int JDK_1_5_VERSION = 3211264;
    public static final int JDK_1_6_VERSION = 3276800;
    public static final int JDK_1_7_VERSION = 3342336;
    public static final ClassType[] noClasses = new ClassType[0];
    int Code_name_index;
    int ConstantValue_name_index;
    int LineNumberTable_name_index;
    int LocalVariableTable_name_index;
    int access_flags;
    Attribute attributes;
    int classfileFormatVersion = JDK_1_1_VERSION;
    ConstantPool constants;
    public Method constructor;
    boolean emitDebugInfo = true;
    Member enclosingMember;
    Field fields;
    int fields_count;
    ClassType firstInnerClass;
    int[] interfaceIndexes;
    ClassType[] interfaces;
    Field last_field;
    Method last_method;
    Method methods;
    int methods_count;
    ClassType nextInnerClass;
    SourceDebugExtAttr sourceDbgExt;
    ClassType superClass;
    int superClassIndex = -1;
    int thisClassIndex;

    public short getClassfileMajorVersion() {
        return (short) (this.classfileFormatVersion >> 16);
    }

    public short getClassfileMinorVersion() {
        return (short) (this.classfileFormatVersion & 65535);
    }

    public void setClassfileVersion(int major, int minor) {
        int i = ((major & 65535) * 65536) + (minor * 65535);
        this.classfileFormatVersion = i;
    }

    public void setClassfileVersion(int code) {
        int i = code;
        this.classfileFormatVersion = i;
    }

    public int getClassfileVersion() {
        return this.classfileFormatVersion;
    }

    public void setClassfileVersionJava5() {
        setClassfileVersion(JDK_1_5_VERSION);
    }

    public static ClassType make(String name) {
        return (ClassType) Type.getType(name);
    }

    public static ClassType make(String name, ClassType classType) {
        ClassType superClass2 = classType;
        ClassType type = make(name);
        if (type.superClass == null) {
            type.setSuper(superClass2);
        }
        return type;
    }

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attributes2) {
        Attribute attribute = attributes2;
        this.attributes = attribute;
    }

    public final ConstantPool getConstants() {
        return this.constants;
    }

    public final CpoolEntry getConstant(int i) {
        int i2 = i;
        if (this.constants == null || this.constants.pool == null || i2 > this.constants.count) {
            return null;
        }
        return this.constants.pool[i2];
    }

    public final synchronized int getModifiers() {
        int i;
        synchronized (this) {
            if (!(this.access_flags != 0 || (this.flags & 16) == 0 || getReflectClass() == null)) {
                this.access_flags = this.reflectClass.getModifiers();
            }
            i = this.access_flags;
        }
        return i;
    }

    public final boolean getStaticFlag() {
        return (getModifiers() & 8) != 0;
    }

    public final void setModifiers(int flags) {
        int i = flags;
        this.access_flags = i;
    }

    public final void addModifiers(int flags) {
        this.access_flags |= flags;
    }

    public synchronized String getSimpleName() {
        String str;
        synchronized (this) {
            if (!((this.flags & 16) == 0 || getReflectClass() == null)) {
                try {
                    str = this.reflectClass.getSimpleName();
                } catch (Throwable th) {
                    Throwable th2 = th;
                }
            }
            String name = getName();
            int dot = name.lastIndexOf(46);
            if (dot > 0) {
                name = name.substring(dot + 1);
            }
            int dollar = name.lastIndexOf(36);
            if (dollar >= 0) {
                int len = name.length();
                int start = dollar + 1;
                while (start < len && (ch = name.charAt(start)) >= '0' && ch <= '9') {
                    start++;
                }
                name = name.substring(start);
            }
            str = name;
        }
        return str;
    }

    public void addMemberClass(ClassType classType) {
        ClassType member = classType;
        ClassType prev = null;
        ClassType classType2 = this.firstInnerClass;
        while (true) {
            ClassType entry = classType2;
            if (entry != null) {
                if (entry != member) {
                    prev = entry;
                    classType2 = entry.nextInnerClass;
                } else {
                    return;
                }
            } else if (prev == null) {
                this.firstInnerClass = member;
                return;
            } else {
                prev.nextInnerClass = member;
                return;
            }
        }
    }

    public ClassType getDeclaredClass(String str) {
        String simpleName = str;
        addMemberClasses();
        ClassType classType = this.firstInnerClass;
        while (true) {
            ClassType member = classType;
            if (member == null) {
                return null;
            }
            if (simpleName.equals(member.getSimpleName())) {
                return member;
            }
            classType = member.nextInnerClass;
        }
    }

    public ClassType getDeclaringClass() {
        addEnclosingMember();
        if (this.enclosingMember instanceof ClassType) {
            return (ClassType) this.enclosingMember;
        }
        return null;
    }

    public Member getEnclosingMember() {
        addEnclosingMember();
        return this.enclosingMember;
    }

    public void setEnclosingMember(Member member) {
        Member member2 = member;
        this.enclosingMember = member2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void addEnclosingMember() {
        synchronized (this) {
            if ((this.flags & 24) == 16) {
                Class clas = getReflectClass();
                this.flags |= 8;
                Class dclas = clas.getEnclosingClass();
                if (dclas != null) {
                    if (!clas.isMemberClass()) {
                        Method rmeth = clas.getEnclosingMethod();
                        if (rmeth != null) {
                            this.enclosingMember = addMethod(rmeth);
                        } else {
                            Constructor rcons = clas.getEnclosingConstructor();
                            if (rcons != null) {
                                this.enclosingMember = addMethod(rcons);
                            }
                        }
                    }
                    this.enclosingMember = (ClassType) Type.make(dclas);
                }
            }
        }
    }

    public synchronized void addMemberClasses() {
        synchronized (this) {
            if ((this.flags & 20) == 16) {
                Class clas = getReflectClass();
                this.flags |= 4;
                Class[] memberClasses = clas.getClasses();
                int numMembers = memberClasses.length;
                if (numMembers > 0) {
                    for (int i = 0; i < numMembers; i++) {
                        addMemberClass((ClassType) Type.make(memberClasses[i]));
                    }
                }
            }
        }
    }

    public final boolean hasOuterLink() {
        Field fields2 = getFields();
        return (this.flags & 32) != 0;
    }

    public ClassType getOuterLinkType() {
        return !hasOuterLink() ? null : (ClassType) getDeclaredField("this$0").getType();
    }

    public final Field setOuterLink(ClassType classType) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        ClassType outer = classType;
        if ((this.flags & 16) != 0) {
            Throwable th4 = th3;
            new StringBuilder();
            new Error(sb3.append("setOuterLink called for existing class ").append(getName()).toString());
            throw th4;
        }
        Field field = getDeclaredField("this$0");
        if (field == null) {
            field = addField("this$0", outer);
            this.flags |= 32;
            Method method = this.methods;
            while (true) {
                Method meth = method;
                if (meth == null) {
                    break;
                }
                if ("<init>".equals(meth.getName())) {
                    if (meth.code != null) {
                        Throwable th5 = th2;
                        new StringBuilder();
                        new Error(sb2.append("setOuterLink called when ").append(meth).append(" has code").toString());
                        throw th5;
                    }
                    Type[] arg_types = meth.arg_types;
                    Type[] new_types = new Type[(arg_types.length + 1)];
                    System.arraycopy(arg_types, 0, new_types, 1, arg_types.length);
                    new_types[0] = outer;
                    meth.arg_types = new_types;
                    meth.signature = null;
                }
                method = meth.getNext();
            }
        } else if (!outer.equals(field.getType())) {
            Throwable th6 = th;
            new StringBuilder();
            new Error(sb.append("inconsistent setOuterLink call for ").append(getName()).toString());
            throw th6;
        }
        return field;
    }

    public boolean isAccessible(Member member, ObjectType objectType) {
        Member member2 = member;
        ObjectType receiver = objectType;
        if (member2.getStaticFlag()) {
            receiver = null;
        }
        return isAccessible(member2.getDeclaringClass(), receiver, member2.getModifiers());
    }

    public boolean isAccessible(ClassType classType, ObjectType objectType, int i) {
        ClassType declaring = classType;
        ObjectType receiver = objectType;
        int modifiers = i;
        int cmods = declaring.getModifiers();
        if ((modifiers & 1) != 0 && (cmods & 1) != 0) {
            return true;
        }
        String callerName = getName();
        String className = declaring.getName();
        if (callerName.equals(className)) {
            return true;
        }
        if ((modifiers & 2) != 0) {
            return false;
        }
        int dot = callerName.lastIndexOf(46);
        String callerPackage = dot >= 0 ? callerName.substring(0, dot) : "";
        int dot2 = className.lastIndexOf(46);
        if (callerPackage.equals(dot2 >= 0 ? className.substring(0, dot2) : "")) {
            return true;
        }
        if ((cmods & 1) == 0) {
            return false;
        }
        if ((modifiers & 4) == 0 || !isSubclass(declaring) || ((receiver instanceof ClassType) && !((ClassType) receiver).isSubclass(this))) {
            return false;
        }
        return true;
    }

    public void setName(String str) {
        StringBuilder sb;
        String name = str;
        this.this_name = name;
        new StringBuilder();
        setSignature(sb.append("L").append(name.replace('.', '/')).append(";").toString());
    }

    public void setStratum(String str) {
        SourceDebugExtAttr sourceDebugExtAttr;
        String stratum = str;
        if (this.sourceDbgExt == null) {
            new SourceDebugExtAttr(this);
            this.sourceDbgExt = sourceDebugExtAttr;
        }
        this.sourceDbgExt.addStratum(stratum);
    }

    public void setSourceFile(String str) {
        String name = str;
        if (this.sourceDbgExt != null) {
            this.sourceDbgExt.addFile(name);
            if (this.sourceDbgExt.fileCount > 1) {
                return;
            }
        }
        String name2 = SourceFileAttr.fixSourceFile(name);
        int slash = name2.lastIndexOf(47);
        if (slash >= 0) {
            name2 = name2.substring(slash + 1);
        }
        SourceFileAttr.setSourceFile(this, name2);
    }

    public void setSuper(String str) {
        String name = str;
        setSuper(name == null ? Type.pointer_type : make(name));
    }

    public void setSuper(ClassType superClass2) {
        ClassType classType = superClass2;
        this.superClass = classType;
    }

    public synchronized ClassType getSuperclass() {
        ClassType classType;
        synchronized (this) {
            if (this.superClass == null && !isInterface() && !"java.lang.Object".equals(getName()) && (this.flags & 16) != 0 && getReflectClass() != null) {
                this.superClass = (ClassType) make(this.reflectClass.getSuperclass());
            }
            classType = this.superClass;
        }
        return classType;
    }

    public String getPackageName() {
        String name = getName();
        int index = name.lastIndexOf(46);
        return index < 0 ? "" : name.substring(0, index);
    }

    public synchronized ClassType[] getInterfaces() {
        ClassType[] classTypeArr;
        synchronized (this) {
            if (!(this.interfaces != null || (this.flags & 16) == 0 || getReflectClass() == null)) {
                Class[] reflectInterfaces = this.reflectClass.getInterfaces();
                int numInterfaces = reflectInterfaces.length;
                this.interfaces = numInterfaces == 0 ? noClasses : new ClassType[numInterfaces];
                for (int i = 0; i < numInterfaces; i++) {
                    this.interfaces[i] = (ClassType) Type.make(reflectInterfaces[i]);
                }
            }
            classTypeArr = this.interfaces;
        }
        return classTypeArr;
    }

    public void setInterfaces(ClassType[] interfaces2) {
        ClassType[] classTypeArr = interfaces2;
        this.interfaces = classTypeArr;
    }

    public void addInterface(ClassType classType) {
        int oldCount;
        ClassType newInterface = classType;
        if (this.interfaces == null || this.interfaces.length == 0) {
            oldCount = 0;
            this.interfaces = new ClassType[1];
        } else {
            oldCount = this.interfaces.length;
            int i = oldCount;
            do {
                i--;
                if (i < 0) {
                    ClassType[] newInterfaces = new ClassType[(oldCount + 1)];
                    System.arraycopy(this.interfaces, 0, newInterfaces, 0, oldCount);
                    this.interfaces = newInterfaces;
                }
            } while (this.interfaces[i] != newInterface);
            return;
        }
        this.interfaces[oldCount] = newInterface;
    }

    public final boolean isInterface() {
        return (getModifiers() & 512) != 0;
    }

    public final void setInterface(boolean val) {
        if (val) {
            this.access_flags |= 1536;
            return;
        }
        this.access_flags &= -513;
    }

    public ClassType() {
    }

    public ClassType(String class_name) {
        setName(class_name);
    }

    public final synchronized Field getFields() {
        Field field;
        synchronized (this) {
            if ((this.flags & 17) == 16) {
                addFields();
            }
            field = this.fields;
        }
        return field;
    }

    public final int getFieldCount() {
        return this.fields_count;
    }

    public Field getDeclaredField(String str) {
        String name = str;
        Field fields2 = getFields();
        while (true) {
            Field field = fields2;
            if (field == null) {
                return null;
            }
            if (name.equals(field.name)) {
                return field;
            }
            fields2 = field.next;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized gnu.bytecode.Field getField(java.lang.String r12, int r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r10 = r11
            monitor-enter(r10)
            r7 = r0
            r3 = r7
        L_0x0007:
            r7 = r3
            r8 = r1
            gnu.bytecode.Field r7 = r7.getDeclaredField(r8)     // Catch:{ all -> 0x0053 }
            r4 = r7
            r7 = r4
            if (r7 == 0) goto L_0x0022
            r7 = r2
            r8 = -1
            if (r7 == r8) goto L_0x001e
            r7 = r4
            int r7 = r7.getModifiers()     // Catch:{ all -> 0x0053 }
            r8 = r2
            r7 = r7 & r8
            if (r7 == 0) goto L_0x0022
        L_0x001e:
            r7 = r4
            r0 = r7
        L_0x0020:
            monitor-exit(r10)
            return r0
        L_0x0022:
            r7 = r3
            gnu.bytecode.ClassType[] r7 = r7.getInterfaces()     // Catch:{ all -> 0x0053 }
            r5 = r7
            r7 = r5
            if (r7 == 0) goto L_0x0046
            r7 = 0
            r6 = r7
        L_0x002d:
            r7 = r6
            r8 = r5
            int r8 = r8.length     // Catch:{ all -> 0x0053 }
            if (r7 >= r8) goto L_0x0046
            r7 = r5
            r8 = r6
            r7 = r7[r8]     // Catch:{ all -> 0x0053 }
            r8 = r1
            r9 = r2
            gnu.bytecode.Field r7 = r7.getField(r8, r9)     // Catch:{ all -> 0x0053 }
            r4 = r7
            r7 = r4
            if (r7 == 0) goto L_0x0043
            r7 = r4
            r0 = r7
            goto L_0x0020
        L_0x0043:
            int r6 = r6 + 1
            goto L_0x002d
        L_0x0046:
            r7 = r3
            gnu.bytecode.ClassType r7 = r7.getSuperclass()     // Catch:{ all -> 0x0053 }
            r3 = r7
            r7 = r3
            if (r7 != 0) goto L_0x0052
            r7 = 0
            r0 = r7
            goto L_0x0020
        L_0x0052:
            goto L_0x0007
        L_0x0053:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.getField(java.lang.String, int):gnu.bytecode.Field");
    }

    public Field getField(String name) {
        return getField(name, 1);
    }

    public Field addField() {
        Field field;
        new Field(this);
        return field;
    }

    public Field addField(String name) {
        Field field;
        new Field(this);
        Field field2 = field;
        field2.setName(name);
        return field2;
    }

    public final Field addField(String name, Type type) {
        Field field;
        new Field(this);
        Field field2 = field;
        field2.setName(name);
        field2.setType(type);
        return field2;
    }

    public final Field addField(String name, Type type, int flags) {
        Field field = addField(name, type);
        field.flags = flags;
        return field;
    }

    public synchronized void addFields() {
        Field[] fields2;
        synchronized (this) {
            Class clas = getReflectClass();
            try {
                fields2 = clas.getDeclaredFields();
            } catch (SecurityException e) {
                SecurityException securityException = e;
                fields2 = clas.getFields();
            }
            int count = fields2.length;
            for (int i = 0; i < count; i++) {
                Field field = fields2[i];
                if ("this$0".equals(field.getName())) {
                    this.flags |= 32;
                }
                Field addField = addField(field.getName(), Type.make(field.getType()), field.getModifiers());
            }
            this.flags |= 1;
        }
    }

    public final Method getMethods() {
        return this.methods;
    }

    public final int getMethodCount() {
        return this.methods_count;
    }

    /* access modifiers changed from: package-private */
    public Method addMethod() {
        Method method;
        new Method(this, 0);
        return method;
    }

    public Method addMethod(String name) {
        return addMethod(name, 0);
    }

    public Method addMethod(String name, int flags) {
        Method method;
        new Method(this, flags);
        Method method2 = method;
        method2.setName(name);
        return method2;
    }

    public Method addMethod(String name, Type[] arg_types, Type return_type, int flags) {
        return addMethod(name, flags, arg_types, return_type);
    }

    public synchronized Method addMethod(String str, int i, Type[] typeArr, Type type) {
        Method method;
        String name = str;
        int flags = i;
        Type[] arg_types = typeArr;
        Type return_type = type;
        synchronized (this) {
            Method method2 = getDeclaredMethod(name, arg_types);
            if (method2 == null || !return_type.equals(method2.getReturnType()) || (flags & method2.access_flags) != flags) {
                Method method3 = addMethod(name, flags);
                method3.arg_types = arg_types;
                method3.return_type = return_type;
                method = method3;
            } else {
                method = method2;
            }
        }
        return method;
    }

    public Method addMethod(Method method) {
        Method method2 = method;
        int modifiers = method2.getModifiers();
        Class[] paramTypes = method2.getParameterTypes();
        int j = paramTypes.length;
        Type[] args = new Type[j];
        while (true) {
            j--;
            if (j >= 0) {
                args[j] = Type.make(paramTypes[j]);
            } else {
                return addMethod(method2.getName(), modifiers, args, Type.make(method2.getReturnType()));
            }
        }
    }

    public Method addMethod(Constructor constructor2) {
        Constructor method = constructor2;
        Class[] paramTypes = method.getParameterTypes();
        int modifiers = method.getModifiers();
        int j = paramTypes.length;
        Type[] args = new Type[j];
        while (true) {
            j--;
            if (j < 0) {
                return addMethod("<init>", modifiers, args, (Type) Type.voidType);
            }
            args[j] = Type.make(paramTypes[j]);
        }
    }

    public Method addMethod(String name, String signature, int flags) {
        Method meth = addMethod(name, flags);
        meth.setSignature(signature);
        return meth;
    }

    public Method getMethod(Method method) {
        Method method2 = method;
        String name = method2.getName();
        Class[] parameterClasses = method2.getParameterTypes();
        Type[] parameterTypes = new Type[parameterClasses.length];
        int i = parameterClasses.length;
        while (true) {
            i--;
            if (i < 0) {
                return addMethod(name, method2.getModifiers(), parameterTypes, Type.make(method2.getReturnType()));
            }
            parameterTypes[i] = Type.make(parameterClasses[i]);
        }
    }

    public final synchronized Method getDeclaredMethods() {
        Method method;
        synchronized (this) {
            if ((this.flags & 18) == 16) {
                addMethods(getReflectClass());
            }
            method = this.methods;
        }
        return method;
    }

    public final int countMethods(Filter filter, int searchSupers) {
        Vector vector;
        new Vector();
        Vector vec = vector;
        int methods2 = getMethods(filter, searchSupers, vec);
        return vec.size();
    }

    public Method[] getMethods(Filter filter, boolean searchSupers) {
        return getMethods(filter, searchSupers ? 1 : 0);
    }

    public Method[] getMethods(Filter filter, int searchSupers) {
        Vector vector;
        new Vector();
        Vector vector2 = vector;
        int methods2 = getMethods(filter, searchSupers, vector2);
        int count = vector2.size();
        Method[] result = new Method[count];
        for (int i = 0; i < count; i++) {
            result[i] = (Method) vector2.elementAt(i);
        }
        return result;
    }

    public int getMethods(Filter filter, int searchSupers, Method[] methodArr, int i) {
        Vector vector;
        Method[] result = methodArr;
        int offset = i;
        new Vector();
        Vector vector2 = vector;
        int methods2 = getMethods(filter, searchSupers, vector2);
        int count = vector2.size();
        for (int i2 = 0; i2 < count; i2++) {
            result[offset + i2] = (Method) vector2.elementAt(i2);
        }
        return count;
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        ClassType[] interfaces2;
        Filter filter2 = filter;
        int searchSupers = i;
        List<Method> result = list;
        int count = 0;
        String inheritingPackage = null;
        ClassType classType = this;
        while (true) {
            ClassType ctype = classType;
            if (ctype == null) {
                break;
            }
            String curPackage = ctype.getPackageName();
            Method declaredMethods = ctype.getDeclaredMethods();
            while (true) {
                Method meth = declaredMethods;
                if (meth == null) {
                    break;
                }
                if (ctype != this) {
                    int mmods = meth.getModifiers();
                    if ((mmods & 2) == 0) {
                        if ((mmods & 5) == 0 && !curPackage.equals(inheritingPackage)) {
                        }
                    }
                    declaredMethods = meth.getNext();
                }
                if (filter2.select(meth)) {
                    if (result != null) {
                        boolean add = result.add(meth);
                    }
                    count++;
                }
                declaredMethods = meth.getNext();
            }
            inheritingPackage = curPackage;
            if (searchSupers == 0) {
                break;
            }
            if (searchSupers > 1 && (interfaces2 = ctype.getInterfaces()) != null) {
                for (int i2 = 0; i2 < interfaces2.length; i2++) {
                    count += interfaces2[i2].getMethods(filter2, searchSupers, result);
                }
            }
            classType = ctype.getSuperclass();
        }
        return count;
    }

    static class AbstractMethodFilter implements Filter {
        public static final AbstractMethodFilter instance;

        AbstractMethodFilter() {
        }

        static {
            AbstractMethodFilter abstractMethodFilter;
            new AbstractMethodFilter();
            instance = abstractMethodFilter;
        }

        public boolean select(Object value) {
            return ((Method) value).isAbstract();
        }
    }

    public Method[] getAbstractMethods() {
        return getMethods((Filter) AbstractMethodFilter.instance, 2);
    }

    public Method getDeclaredMethod(String str, Type[] typeArr) {
        Method method;
        String name = str;
        Type[] arg_types = typeArr;
        int needOuterLinkArg = (!"<init>".equals(name) || !hasOuterLink()) ? 0 : 1;
        Method declaredMethods = getDeclaredMethods();
        while (true) {
            method = declaredMethods;
            if (method == null) {
                return null;
            }
            if (name.equals(method.getName())) {
                Type[] method_args = method.getParameterTypes();
                if (arg_types != null && (arg_types != method_args || needOuterLinkArg != 0)) {
                    int i = arg_types.length;
                    if (i != method_args.length - needOuterLinkArg) {
                        continue;
                    } else {
                        while (true) {
                            i--;
                            if (i < 0) {
                                break;
                            }
                            Type meth_type = method_args[i + needOuterLinkArg];
                            Type need_type = arg_types[i];
                            if (!(meth_type == need_type || need_type == null)) {
                                if (!meth_type.getSignature().equals(need_type.getSignature())) {
                                    break;
                                }
                            }
                        }
                        if (i < 0) {
                            return method;
                        }
                    }
                }
            }
            declaredMethods = method.next;
        }
        return method;
    }

    public synchronized Method getDeclaredMethod(String str, int i) {
        Method method;
        Throwable th;
        StringBuilder sb;
        String name = str;
        int argCount = i;
        synchronized (this) {
            Method result = null;
            int needOuterLinkArg = (!"<init>".equals(name) || !hasOuterLink()) ? 0 : 1;
            Method declaredMethods = getDeclaredMethods();
            while (true) {
                Method method2 = declaredMethods;
                if (method2 != null) {
                    if (name.equals(method2.getName()) && argCount + needOuterLinkArg == method2.getParameterTypes().length) {
                        if (result != null) {
                            Throwable th2 = th;
                            new StringBuilder();
                            new Error(sb.append("ambiguous call to getDeclaredMethod(\"").append(name).append("\", ").append(argCount).append(")\n - ").append(result).append("\n - ").append(method2).toString());
                            throw th2;
                        }
                        result = method2;
                    }
                    declaredMethods = method2.next;
                } else {
                    method = result;
                }
            }
        }
        return method;
    }

    public synchronized Method getMethod(String str, Type[] typeArr) {
        Method method;
        String name = str;
        Type[] arg_types = typeArr;
        synchronized (this) {
            ClassType cl = this;
            while (true) {
                Method method2 = cl.getDeclaredMethod(name, arg_types);
                if (method2 != null) {
                    method = method2;
                    break;
                }
                cl = cl.getSuperclass();
                if (cl == null) {
                    ClassType cl2 = this;
                    loop1:
                    while (true) {
                        ClassType[] interfaces2 = cl2.getInterfaces();
                        if (interfaces2 != null) {
                            int i = 0;
                            while (true) {
                                if (i >= interfaces2.length) {
                                    break;
                                }
                                Method method3 = interfaces2[i].getDeclaredMethod(name, arg_types);
                                if (method3 != null) {
                                    method = method3;
                                    break loop1;
                                }
                                i++;
                            }
                        }
                        cl2 = cl2.getSuperclass();
                        if (cl2 == null) {
                            method = null;
                            break;
                        }
                    }
                }
            }
        }
        return method;
    }

    public synchronized void addMethods(Class cls) {
        Method[] methods2;
        Constructor[] cmethods;
        Class clas = cls;
        synchronized (this) {
            this.flags |= 2;
            try {
                methods2 = clas.getDeclaredMethods();
            } catch (SecurityException e) {
                SecurityException securityException = e;
                methods2 = clas.getMethods();
            }
            int count = methods2.length;
            for (int i = 0; i < count; i++) {
                Method method = methods2[i];
                if (method.getDeclaringClass().equals(clas)) {
                    Method addMethod = addMethod(method);
                }
            }
            try {
                cmethods = clas.getDeclaredConstructors();
            } catch (SecurityException e2) {
                SecurityException securityException2 = e2;
                cmethods = clas.getConstructors();
            }
            int count2 = cmethods.length;
            for (int i2 = 0; i2 < count2; i2++) {
                Constructor method2 = cmethods[i2];
                if (method2.getDeclaringClass().equals(clas)) {
                    Method addMethod2 = addMethod(method2);
                }
            }
        }
    }

    public Method[] getMatchingMethods(String str, Type[] typeArr, int i) {
        Vector vector;
        String name = str;
        Type[] paramTypes = typeArr;
        int flags = i;
        int nMatches = 0;
        new Vector(10);
        Vector matches = vector;
        Method method = this.methods;
        while (true) {
            Method method2 = method;
            if (method2 != null) {
                if (name.equals(method2.getName()) && (flags & 8) == (method2.access_flags & 8) && (flags & 1) <= (method2.access_flags & 1) && method2.arg_types.length == paramTypes.length) {
                    nMatches++;
                    matches.addElement(method2);
                }
                method = method2.getNext();
            } else {
                Method[] result = new Method[nMatches];
                matches.copyInto(result);
                return result;
            }
        }
    }

    public void doFixups() {
        InnerClassesAttr innerClassesAttr;
        EnclosingMethodAttr enclosingMethodAttr;
        ConstantPool constantPool;
        if (this.constants == null) {
            new ConstantPool();
            this.constants = constantPool;
        }
        if (this.thisClassIndex == 0) {
            this.thisClassIndex = this.constants.addClass((ObjectType) this).index;
        }
        if (this.superClass == this) {
            setSuper((ClassType) null);
        }
        if (this.superClassIndex < 0) {
            this.superClassIndex = this.superClass == null ? 0 : this.constants.addClass((ObjectType) this.superClass).index;
        }
        if (this.interfaces != null && this.interfaceIndexes == null) {
            int n = this.interfaces.length;
            this.interfaceIndexes = new int[n];
            for (int i = 0; i < n; i++) {
                this.interfaceIndexes[i] = this.constants.addClass((ObjectType) this.interfaces[i]).index;
            }
        }
        Field field = this.fields;
        while (true) {
            Field field2 = field;
            if (field2 == null) {
                break;
            }
            field2.assign_constants(this);
            field = field2.next;
        }
        Method method = this.methods;
        while (true) {
            Method method2 = method;
            if (method2 == null) {
                break;
            }
            method2.assignConstants();
            method = method2.next;
        }
        if (this.enclosingMember instanceof Method) {
            EnclosingMethodAttr attr = EnclosingMethodAttr.getFirstEnclosingMethod(getAttributes());
            if (attr == null) {
                new EnclosingMethodAttr(this);
                attr = enclosingMethodAttr;
            }
            attr.method = (Method) this.enclosingMember;
        } else if (this.enclosingMember instanceof ClassType) {
            CpoolClass addClass = this.constants.addClass((ObjectType) (ClassType) this.enclosingMember);
        }
        ClassType classType = this.firstInnerClass;
        while (true) {
            ClassType member = classType;
            if (member == null) {
                break;
            }
            CpoolClass addClass2 = this.constants.addClass((ObjectType) member);
            classType = member.nextInnerClass;
        }
        InnerClassesAttr innerAttr = InnerClassesAttr.getFirstInnerClasses(getAttributes());
        if (innerAttr != null) {
            innerAttr.setSkipped(true);
        }
        Attribute.assignConstants(this, this);
        for (int i2 = 1; i2 <= this.constants.count; i2++) {
            CpoolEntry entry = this.constants.pool[i2];
            if (entry instanceof CpoolClass) {
                CpoolClass centry = (CpoolClass) entry;
                if ((centry.clas instanceof ClassType) && ((ClassType) centry.clas).getEnclosingMember() != null) {
                    if (innerAttr == null) {
                        new InnerClassesAttr(this);
                        innerAttr = innerClassesAttr;
                    }
                    innerAttr.addClass(centry, this);
                }
            }
        }
        if (innerAttr != null) {
            innerAttr.setSkipped(false);
            innerAttr.assignConstants(this);
        }
    }

    public void writeToStream(OutputStream stream) throws IOException {
        DataOutputStream dataOutputStream;
        new DataOutputStream(stream);
        DataOutputStream dstr = dataOutputStream;
        doFixups();
        dstr.writeInt(-889275714);
        dstr.writeShort(getClassfileMinorVersion());
        dstr.writeShort(getClassfileMajorVersion());
        if (this.constants == null) {
            dstr.writeShort(1);
        } else {
            this.constants.write(dstr);
        }
        dstr.writeShort(this.access_flags);
        dstr.writeShort(this.thisClassIndex);
        dstr.writeShort(this.superClassIndex);
        if (this.interfaceIndexes == null) {
            dstr.writeShort(0);
        } else {
            int interfaces_count = this.interfaceIndexes.length;
            dstr.writeShort(interfaces_count);
            for (int i = 0; i < interfaces_count; i++) {
                dstr.writeShort(this.interfaceIndexes[i]);
            }
        }
        dstr.writeShort(this.fields_count);
        Field field = this.fields;
        while (true) {
            Field field2 = field;
            if (field2 == null) {
                break;
            }
            field2.write(dstr, this);
            field = field2.next;
        }
        dstr.writeShort(this.methods_count);
        Method method = this.methods;
        while (true) {
            Method method2 = method;
            if (method2 != null) {
                method2.write(dstr, this);
                method = method2.next;
            } else {
                Attribute.writeAll(this, dstr);
                this.flags |= 3;
                return;
            }
        }
    }

    public void writeToFile(String filename) throws IOException {
        OutputStream outputStream;
        OutputStream outputStream2;
        new FileOutputStream(filename);
        new BufferedOutputStream(outputStream2);
        OutputStream stream = outputStream;
        writeToStream(stream);
        stream.close();
    }

    public void writeToFile() throws IOException {
        StringBuilder sb;
        new StringBuilder();
        writeToFile(sb.append(this.this_name.replace('.', File.separatorChar)).append(".class").toString());
    }

    public byte[] writeToArray() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        new ByteArrayOutputStream(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        ByteArrayOutputStream stream = byteArrayOutputStream;
        try {
            writeToStream(stream);
            return stream.toByteArray();
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new InternalError(ex.toString());
            throw th2;
        }
    }

    public static byte[] to_utf8(String str) {
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        int str_len = str2.length();
        int utf_len = 0;
        for (int i = 0; i < str_len; i++) {
            int c = str2.charAt(i);
            if (c > 0 && c <= 127) {
                utf_len++;
            } else if (c <= 2047) {
                utf_len += 2;
            } else {
                utf_len += 3;
            }
        }
        byte[] buffer = new byte[utf_len];
        int j = 0;
        for (int i2 = 0; i2 < str_len; i2++) {
            int c2 = str2.charAt(i2);
            if (c2 > 0 && c2 <= 127) {
                int i3 = j;
                j++;
                buffer[i3] = (byte) c2;
            } else if (c2 <= 2047) {
                int i4 = j;
                int j2 = j + 1;
                buffer[i4] = (byte) (192 | ((c2 >> 6) & 31));
                int i5 = j2;
                j = j2 + 1;
                buffer[i5] = (byte) (128 | ((c2 >> 0) & 63));
            } else {
                int i6 = j;
                int j3 = j + 1;
                buffer[i6] = (byte) (224 | ((c2 >> 12) & 15));
                int i7 = j3;
                int j4 = j3 + 1;
                buffer[i7] = (byte) (128 | ((c2 >> 6) & 63));
                int i8 = j4;
                j = j4 + 1;
                buffer[i8] = (byte) (128 | ((c2 >> 0) & 63));
            }
        }
        return buffer;
    }

    public final boolean implementsInterface(ClassType classType) {
        ClassType iface = classType;
        if (this == iface) {
            return true;
        }
        ClassType baseClass = getSuperclass();
        if (baseClass != null && baseClass.implementsInterface(iface)) {
            return true;
        }
        ClassType[] interfaces2 = getInterfaces();
        if (interfaces2 != null) {
            int i = interfaces2.length;
            do {
                i--;
                if (i >= 0) {
                }
            } while (!interfaces2[i].implementsInterface(iface));
            return true;
        }
        return false;
    }

    public final boolean isSubclass(String str) {
        String cname = str;
        ClassType ctype = this;
        while (!cname.equals(ctype.getName())) {
            ctype = ctype.getSuperclass();
            if (ctype == null) {
                return false;
            }
        }
        return true;
    }

    public final boolean isSubclass(ClassType classType) {
        ClassType other = classType;
        if (other.isInterface()) {
            return implementsInterface(other);
        }
        if ((this == toStringType && other == javalangStringType) || (this == javalangStringType && other == toStringType)) {
            return true;
        }
        ClassType classType2 = this;
        while (true) {
            ClassType baseClass = classType2;
            if (baseClass == null) {
                return false;
            }
            if (baseClass == other) {
                return true;
            }
            classType2 = baseClass.getSuperclass();
        }
    }

    public int compare(Type type) {
        Type other = type;
        if (other == nullType) {
            return 1;
        }
        if (!(other instanceof ClassType)) {
            return swappedCompareResult(other.compare(this));
        }
        String name = getName();
        if (name != null && name.equals(other.getName())) {
            return 0;
        }
        ClassType cother = (ClassType) other;
        if (isSubclass(cother)) {
            return -1;
        }
        if (cother.isSubclass(this)) {
            return 1;
        }
        if (this == toStringType) {
            return cother == Type.javalangObjectType ? -1 : 1;
        } else if (cother == toStringType) {
            return this == Type.javalangObjectType ? 1 : -1;
        } else if (isInterface()) {
            return cother == Type.javalangObjectType ? -1 : -2;
        } else if (!cother.isInterface()) {
            return -3;
        } else {
            return this == Type.javalangObjectType ? 1 : -2;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ClassType ").append(getName()).toString();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getName());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName(in.readUTF());
        this.flags |= 16;
    }

    /* JADX INFO: finally extract failed */
    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        HashMap<String, Type> map = mapNameToType;
        HashMap<String, Type> hashMap = map;
        HashMap<String, Type> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                Type found = map.get(name);
                if (found != null) {
                    Type type = found;
                    return type;
                }
                Type put = map.put(name, this);
                return this;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<String, Type> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    public void cleanupAfterCompilation() {
        Method method = this.methods;
        while (true) {
            Method meth = method;
            if (meth != null) {
                meth.cleanupAfterCompilation();
                method = meth.getNext();
            } else {
                this.constants = null;
                this.attributes = null;
                this.sourceDbgExt = null;
                return;
            }
        }
    }

    public Method checkSingleAbstractMethod() {
        Method[] methods2 = getAbstractMethods();
        int nmethods = methods2.length;
        Method result = null;
        for (int i = 0; i < nmethods; i++) {
            Method meth = methods2[i];
            Method mimpl = getMethod(meth.getName(), meth.getParameterTypes());
            if (mimpl == null || mimpl.isAbstract()) {
                if (result != null) {
                    return null;
                }
                result = meth;
            }
        }
        return result;
    }
}
