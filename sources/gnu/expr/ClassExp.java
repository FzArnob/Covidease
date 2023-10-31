package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.OutPort;
import java.util.Hashtable;
import java.util.Vector;

public class ClassExp extends LambdaExp {
    public static final int CLASS_SPECIFIED = 65536;
    public static final int HAS_SUBCLASS = 131072;
    public static final int INTERFACE_SPECIFIED = 32768;
    public static final int IS_ABSTRACT = 16384;
    public String classNameSpecifier;
    public LambdaExp clinitMethod;
    boolean explicitInit;
    public LambdaExp initMethod;
    ClassType instanceType;
    boolean partsDeclared;
    boolean simple;
    public int superClassIndex = -1;
    public Expression[] supers;

    public boolean isSimple() {
        return this.simple;
    }

    public void setSimple(boolean value) {
        boolean z = value;
        this.simple = z;
    }

    public final boolean isAbstract() {
        return getFlag(16384);
    }

    public boolean isMakingClassPair() {
        return this.type != this.instanceType;
    }

    public Type getType() {
        return this.simple ? Compilation.typeClass : Compilation.typeClassType;
    }

    public ClassType getClassType() {
        return this.type;
    }

    public ClassExp() {
    }

    public ClassExp(boolean simple2) {
        ClassType classType;
        this.simple = simple2;
        ClassType classType2 = classType;
        new ClassType();
        ClassType classType3 = classType2;
        this.type = classType3;
        this.instanceType = classType3;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return true;
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        if (!(target2 instanceof IgnoreTarget)) {
            ClassType compileMembers = compileMembers(comp);
            compilePushClass(comp, target2);
        }
    }

    public void compilePushClass(Compilation compilation, Target target) {
        int nargs;
        ClassType typeType;
        Compilation comp = compilation;
        Target target2 = target;
        ClassType new_class = this.type;
        CodeAttr code = comp.getCode();
        comp.loadClassRef(new_class);
        boolean needsLink = getNeedsClosureEnv();
        if (!isSimple() || needsLink) {
            if (isMakingClassPair() || needsLink) {
                if (new_class == this.instanceType) {
                    code.emitDup((Type) this.instanceType);
                } else {
                    comp.loadClassRef(this.instanceType);
                }
                typeType = ClassType.make("gnu.expr.PairClassType");
                nargs = needsLink ? 3 : 2;
            } else {
                typeType = ClassType.make("gnu.bytecode.Type");
                nargs = 1;
            }
            Type[] argsClass = new Type[nargs];
            if (needsLink) {
                getOwningLambda().loadHeapFrame(comp);
                nargs--;
                argsClass[nargs] = Type.pointer_type;
            }
            ClassType typeClass = ClassType.make("java.lang.Class");
            while (true) {
                nargs--;
                if (nargs >= 0) {
                    argsClass[nargs] = typeClass;
                } else {
                    code.emitInvokeStatic(typeType.addMethod("make", argsClass, (Type) typeType, 9));
                    target2.compileFromStack(comp, typeType);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ClassType getCompiledClassType(Compilation compilation) {
        Compilation compilation2 = compilation;
        return this.type;
    }

    public void setTypes(Compilation compilation) {
        int length;
        ClassType[] interfaces;
        String name;
        int nlen;
        String name2;
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuffer stringBuffer2;
        PairClassType pairClassType;
        int modifiers;
        StringBuilder sb2;
        StringBuilder sb3;
        Compilation comp = compilation;
        if (this.supers == null) {
            length = 0;
        } else {
            length = this.supers.length;
        }
        int nsupers = length;
        ClassType[] superTypes = new ClassType[nsupers];
        ClassType superType = null;
        int j = 0;
        for (int i = 0; i < nsupers; i++) {
            Type st = Language.getDefaultLanguage().getTypeFor(this.supers[i]);
            if (!(st instanceof ClassType)) {
                comp.setLine(this.supers[i]);
                comp.error('e', "invalid super type");
            } else {
                ClassType t = (ClassType) st;
                try {
                    modifiers = t.getModifiers();
                } catch (RuntimeException e) {
                    RuntimeException runtimeException = e;
                    modifiers = 0;
                    if (comp != null) {
                        new StringBuilder();
                        comp.error('e', sb2.append("unknown super-type ").append(t.getName()).toString());
                    }
                }
                if ((modifiers & 512) == 0) {
                    if (j < i) {
                        new StringBuilder();
                        comp.error('e', sb3.append("duplicate superclass for ").append(this).toString());
                    }
                    superType = t;
                    this.superClassIndex = i;
                } else {
                    int i2 = j;
                    j++;
                    superTypes[i2] = t;
                }
            }
        }
        if (!(superType == null || (this.flags & 32768) == 0)) {
            comp.error('e', "cannot be interface since has superclass");
        }
        if (!this.simple && superType == null && (this.flags & 65536) == 0 && (getFlag(131072) || (this.nameDecl != null && this.nameDecl.isPublic()))) {
            new PairClassType();
            PairClassType ptype = pairClassType;
            this.type = ptype;
            ptype.setInterface(true);
            ptype.instanceType = this.instanceType;
            this.instanceType.setSuper(Type.pointer_type);
            this.instanceType.setInterfaces(new ClassType[]{this.type});
        } else if (getFlag(32768)) {
            this.instanceType.setInterface(true);
        }
        this.type.setSuper(superType == null ? Type.pointer_type : superType);
        if (j == nsupers) {
            interfaces = superTypes;
        } else {
            interfaces = new ClassType[j];
            System.arraycopy(superTypes, 0, interfaces, 0, j);
        }
        this.type.setInterfaces(interfaces);
        if (this.type.getName() == null) {
            if (this.classNameSpecifier != null) {
                name = this.classNameSpecifier;
            } else {
                name = getName();
                if (name != null && (nlen = name.length()) > 2 && name.charAt(0) == '<' && name.charAt(nlen - 1) == '>') {
                    name = name.substring(1, nlen - 1);
                }
            }
            if (name == null) {
                new StringBuffer(100);
                StringBuffer nbuf = stringBuffer2;
                ClassType classFor = comp.getModule().classFor(comp);
                StringBuffer append = nbuf.append(comp.mainClass.getName());
                StringBuffer append2 = nbuf.append('$');
                int len = nbuf.length();
                int i3 = 0;
                while (true) {
                    StringBuffer append3 = nbuf.append(i3);
                    name2 = nbuf.toString();
                    if (comp.findNamedClass(name2) == null) {
                        break;
                    }
                    nbuf.setLength(len);
                    i3++;
                }
            } else if (!isSimple() || (this instanceof ObjectExp)) {
                name2 = comp.generateClassName(name);
            } else {
                int start = 0;
                new StringBuffer(100);
                StringBuffer nbuf2 = stringBuffer;
                while (true) {
                    int dot = name.indexOf(46, start);
                    if (dot < 0) {
                        break;
                    }
                    StringBuffer append4 = nbuf2.append(Compilation.mangleNameIfNeeded(name.substring(start, dot)));
                    start = dot + 1;
                    if (start < name.length()) {
                        StringBuffer append5 = nbuf2.append('.');
                    }
                }
                if (start == 0) {
                    String mainName = comp.mainClass == null ? null : comp.mainClass.getName();
                    int dot2 = mainName == null ? -1 : mainName.lastIndexOf(46);
                    if (dot2 > 0) {
                        StringBuffer append6 = nbuf2.append(mainName.substring(0, dot2 + 1));
                    } else if (comp.classPrefix != null) {
                        StringBuffer append7 = nbuf2.append(comp.classPrefix);
                    }
                } else if (start == 1 && start < name.length()) {
                    nbuf2.setLength(0);
                    StringBuffer append8 = nbuf2.append(comp.mainClass.getName());
                    StringBuffer append9 = nbuf2.append('$');
                }
                if (start < name.length()) {
                    StringBuffer append10 = nbuf2.append(Compilation.mangleNameIfNeeded(name.substring(start)));
                }
                name2 = nbuf2.toString();
            }
            this.type.setName(name2);
            comp.addClass(this.type);
            if (isMakingClassPair()) {
                ClassType classType = this.instanceType;
                new StringBuilder();
                classType.setName(sb.append(this.type.getName()).append("$class").toString());
                comp.addClass(this.instanceType);
            }
        }
    }

    public void declareParts(Compilation compilation) {
        Hashtable hashtable;
        Compilation comp = compilation;
        if (!this.partsDeclared) {
            this.partsDeclared = true;
            new Hashtable();
            Hashtable hashtable2 = hashtable;
            Declaration firstDecl = firstDecl();
            while (true) {
                Declaration decl = firstDecl;
                if (decl == null) {
                    break;
                }
                if (decl.getCanRead()) {
                    int flags = decl.getAccessFlags(1);
                    if (decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) {
                        flags |= 8;
                    }
                    if (isMakingClassPair()) {
                        int flags2 = flags | 1024;
                        Type ftype = decl.getType().getImplementationType();
                        Method addMethod = this.type.addMethod(slotToMethodName("get", decl.getName()), flags2, Type.typeArray0, ftype);
                        Method addMethod2 = this.type.addMethod(slotToMethodName("set", decl.getName()), flags2, new Type[]{ftype}, (Type) Type.voidType);
                    } else {
                        String fname = Compilation.mangleNameIfNeeded(decl.getName());
                        decl.field = this.instanceType.addField(fname, decl.getType(), flags);
                        decl.setSimple(false);
                        Declaration old = (Declaration) hashtable2.get(fname);
                        if (old != null) {
                            duplicateDeclarationError(old, decl, comp);
                        }
                        Object put = hashtable2.put(fname, decl);
                    }
                }
                firstDecl = decl.nextDecl();
            }
            LambdaExp lambdaExp = this.firstChild;
            while (true) {
                LambdaExp child = lambdaExp;
                if (child == null) {
                    break;
                }
                if (child.isAbstract()) {
                    setFlag(16384);
                }
                if ("*init*".equals(child.getName())) {
                    this.explicitInit = true;
                    if (child.isAbstract()) {
                        comp.error('e', "*init* method cannot be abstract", child);
                    }
                    if (this.type instanceof PairClassType) {
                        comp.error('e', "'*init*' methods only supported for simple classes");
                    }
                }
                child.outer = this;
                if (!(child == this.initMethod || child == this.clinitMethod || child.nameDecl == null || child.nameDecl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) || !isMakingClassPair()) {
                    child.addMethodFor(this.type, comp, (ObjectType) null);
                }
                if (isMakingClassPair()) {
                    child.addMethodFor(this.instanceType, comp, this.type);
                }
                lambdaExp = child.nextSibling;
            }
            if (!this.explicitInit && !this.instanceType.isInterface()) {
                Method constructor = Compilation.getConstructor(this.instanceType, this);
            }
            if (isAbstract()) {
                this.instanceType.setModifiers(this.instanceType.getModifiers() | 1024);
            }
            if (this.nameDecl != null) {
                this.instanceType.setModifiers((this.instanceType.getModifiers() & -2) | this.nameDecl.getAccessFlags(1));
            }
        }
    }

    static void getImplMethods(ClassType classType, String str, Type[] typeArr, Vector vector) {
        StringBuilder sb;
        ClassType implType;
        ClassType interfaceType = classType;
        String mname = str;
        Type[] paramTypes = typeArr;
        Vector vec = vector;
        if (interfaceType instanceof PairClassType) {
            implType = ((PairClassType) interfaceType).instanceType;
        } else if (interfaceType.isInterface()) {
            try {
                Class reflectClass = interfaceType.getReflectClass();
                if (reflectClass != null) {
                    new StringBuilder();
                    implType = (ClassType) Type.make(Class.forName(sb.append(interfaceType.getName()).append("$class").toString(), false, reflectClass.getClassLoader()));
                } else {
                    return;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                return;
            }
        } else {
            return;
        }
        Type[] itypes = new Type[(paramTypes.length + 1)];
        itypes[0] = interfaceType;
        System.arraycopy(paramTypes, 0, itypes, 1, paramTypes.length);
        Method implMethod = implType.getDeclaredMethod(mname, itypes);
        if (implMethod != null) {
            int count = vec.size();
            if (count == 0 || !vec.elementAt(count - 1).equals(implMethod)) {
                vec.addElement(implMethod);
                return;
            }
            return;
        }
        ClassType[] superInterfaces = interfaceType.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
            getImplMethods(superInterfaces[i], mname, paramTypes, vec);
        }
    }

    private static void usedSuperClasses(ClassType classType, Compilation compilation) {
        ClassType clas = classType;
        Compilation comp = compilation;
        comp.usedClass(clas.getSuperclass());
        ClassType[] interfaces = clas.getInterfaces();
        if (interfaces != null) {
            int i = interfaces.length;
            while (true) {
                i--;
                if (i >= 0) {
                    comp.usedClass(interfaces[i]);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x047f A[Catch:{ all -> 0x039e }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x040d A[Catch:{ all -> 0x039e }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0423 A[Catch:{ all -> 0x039e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.ClassType compileMembers(gnu.expr.Compilation r34) {
        /*
            r33 = this;
            r3 = r33
            r4 = r34
            r27 = r4
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.curClass
            r27 = r0
            r5 = r27
            r27 = r4
            r0 = r27
            gnu.bytecode.Method r0 = r0.method
            r27 = r0
            r6 = r27
            r27 = r3
            r28 = r4
            gnu.bytecode.ClassType r27 = r27.getCompiledClassType(r28)     // Catch:{ all -> 0x039e }
            r7 = r27
            r27 = r4
            r28 = r7
            r0 = r28
            r1 = r27
            r1.curClass = r0     // Catch:{ all -> 0x039e }
            r27 = r3
            gnu.expr.LambdaExp r27 = r27.outerLambda()     // Catch:{ all -> 0x039e }
            r8 = r27
            r27 = 0
            r9 = r27
            r27 = r8
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.ClassExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x011f
            r27 = r8
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r9 = r27
        L_0x004c:
            r27 = r9
            if (r27 == 0) goto L_0x006a
            r27 = r7
            r28 = r9
            r27.setEnclosingMember(r28)     // Catch:{ all -> 0x039e }
            r27 = r9
            r0 = r27
            boolean r0 = r0 instanceof gnu.bytecode.ClassType     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x006a
            r27 = r9
            gnu.bytecode.ClassType r27 = (gnu.bytecode.ClassType) r27     // Catch:{ all -> 0x039e }
            r28 = r7
            r27.addMemberClass(r28)     // Catch:{ all -> 0x039e }
        L_0x006a:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r7
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x00a0
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r28 = r0
            r27.setEnclosingMember(r28)     // Catch:{ all -> 0x039e }
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r27.addMemberClass(r28)     // Catch:{ all -> 0x039e }
        L_0x00a0:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r4
            usedSuperClasses(r27, r28)     // Catch:{ all -> 0x039e }
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x00d0
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r4
            usedSuperClasses(r27, r28)     // Catch:{ all -> 0x039e }
        L_0x00d0:
            r27 = r3
            java.lang.String r27 = r27.getFileName()     // Catch:{ all -> 0x039e }
            r10 = r27
            r27 = r10
            if (r27 == 0) goto L_0x00e3
            r27 = r7
            r28 = r10
            r27.setSourceFile(r28)     // Catch:{ all -> 0x039e }
        L_0x00e3:
            r27 = r4
            r0 = r27
            gnu.expr.LambdaExp r0 = r0.curLambda     // Catch:{ all -> 0x039e }
            r27 = r0
            r11 = r27
            r27 = r4
            r28 = r3
            r0 = r28
            r1 = r27
            r1.curLambda = r0     // Catch:{ all -> 0x039e }
            r27 = r3
            r28 = r4
            r27.allocFrame(r28)     // Catch:{ all -> 0x039e }
            r27 = r3
            r0 = r27
            gnu.expr.LambdaExp r0 = r0.firstChild     // Catch:{ all -> 0x039e }
            r27 = r0
            r13 = r27
        L_0x0108:
            r27 = r13
            if (r27 == 0) goto L_0x03de
            r27 = r13
            boolean r27 = r27.isAbstract()     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x015d
        L_0x0114:
            r27 = r13
            r0 = r27
            gnu.expr.LambdaExp r0 = r0.nextSibling     // Catch:{ all -> 0x039e }
            r27 = r0
            r13 = r27
            goto L_0x0108
        L_0x011f:
            r27 = r8
            if (r27 == 0) goto L_0x0133
            r27 = r8
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.ModuleExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 != 0) goto L_0x0133
            r27 = r6
            r9 = r27
            goto L_0x004c
        L_0x0133:
            r27 = r8
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.ModuleExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x004c
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            java.lang.String r27 = r27.getName()     // Catch:{ all -> 0x039e }
            r28 = 36
            int r27 = r27.indexOf(r28)     // Catch:{ all -> 0x039e }
            if (r27 <= 0) goto L_0x004c
            r27 = r8
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r9 = r27
            goto L_0x004c
        L_0x015d:
            r27 = r4
            r0 = r27
            gnu.bytecode.Method r0 = r0.method     // Catch:{ all -> 0x039e }
            r27 = r0
            r14 = r27
            r27 = r4
            r0 = r27
            gnu.expr.LambdaExp r0 = r0.curLambda     // Catch:{ all -> 0x039e }
            r27 = r0
            r15 = r27
            r27 = r4
            java.lang.String r27 = r27.getFileName()     // Catch:{ all -> 0x039e }
            r16 = r27
            r27 = r4
            int r27 = r27.getLineNumber()     // Catch:{ all -> 0x039e }
            r17 = r27
            r27 = r4
            int r27 = r27.getColumnNumber()     // Catch:{ all -> 0x039e }
            r18 = r27
            r27 = r4
            r28 = r13
            r27.setLine((gnu.expr.Expression) r28)     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r13
            gnu.bytecode.Method r28 = r28.getMainMethod()     // Catch:{ all -> 0x039e }
            r0 = r28
            r1 = r27
            r1.method = r0     // Catch:{ all -> 0x039e }
            r27 = r13
            r0 = r27
            gnu.expr.Declaration r0 = r0.nameDecl     // Catch:{ all -> 0x039e }
            r27 = r0
            r19 = r27
            r27 = r19
            if (r27 == 0) goto L_0x01b6
            r27 = r19
            r28 = 2048(0x800, double:1.0118E-320)
            boolean r27 = r27.getFlag(r28)     // Catch:{ all -> 0x039e }
            if (r27 != 0) goto L_0x01c4
        L_0x01b6:
            r27 = r13
            r28 = r4
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.curClass     // Catch:{ all -> 0x039e }
            r28 = r0
            gnu.bytecode.Variable r27 = r27.declareThis(r28)     // Catch:{ all -> 0x039e }
        L_0x01c4:
            r27 = r4
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r0 = r28
            r1 = r27
            r1.curClass = r0     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r13
            r0 = r28
            r1 = r27
            r1.curLambda = r0     // Catch:{ all -> 0x039e }
            r27 = r4
            r0 = r27
            gnu.bytecode.Method r0 = r0.method     // Catch:{ all -> 0x039e }
            r27 = r0
            r27.initCode()     // Catch:{ all -> 0x039e }
            r27 = r13
            r28 = r4
            r27.allocChildClasses(r28)     // Catch:{ all -> 0x039e }
            r27 = r13
            r28 = r4
            r27.allocParameters(r28)     // Catch:{ all -> 0x039e }
            java.lang.String r27 = "*init*"
            r28 = r13
            java.lang.String r28 = r28.getName()     // Catch:{ all -> 0x039e }
            boolean r27 = r27.equals(r28)     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x03cf
            r27 = r4
            gnu.bytecode.CodeAttr r27 = r27.getCode()     // Catch:{ all -> 0x039e }
            r12 = r27
            r27 = r3
            r0 = r27
            gnu.bytecode.Field r0 = r0.staticLinkField     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x023b
            r27 = r12
            r27.emitPushThis()     // Catch:{ all -> 0x039e }
            r27 = r12
            r28 = r12
            gnu.bytecode.Scope r28 = r28.getCurrentScope()     // Catch:{ all -> 0x039e }
            r29 = 1
            gnu.bytecode.Variable r28 = r28.getVariable(r29)     // Catch:{ all -> 0x039e }
            r27.emitLoad(r28)     // Catch:{ all -> 0x039e }
            r27 = r12
            r28 = r3
            r0 = r28
            gnu.bytecode.Field r0 = r0.staticLinkField     // Catch:{ all -> 0x039e }
            r28 = r0
            r27.emitPutField(r28)     // Catch:{ all -> 0x039e }
        L_0x023b:
            r27 = r13
            r0 = r27
            gnu.expr.Expression r0 = r0.body     // Catch:{ all -> 0x039e }
            r27 = r0
            r20 = r27
        L_0x0245:
            r27 = r20
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.BeginExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x0273
            r27 = r20
            gnu.expr.BeginExp r27 = (gnu.expr.BeginExp) r27     // Catch:{ all -> 0x039e }
            r21 = r27
            r27 = r21
            r0 = r27
            int r0 = r0.length     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 != 0) goto L_0x0264
            r27 = 0
            r20 = r27
        L_0x0263:
            goto L_0x0245
        L_0x0264:
            r27 = r21
            r0 = r27
            gnu.expr.Expression[] r0 = r0.exps     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = 0
            r27 = r27[r28]     // Catch:{ all -> 0x039e }
            r20 = r27
            goto L_0x0263
        L_0x0273:
            r27 = 0
            r21 = r27
            r27 = r20
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.ApplyExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x02e4
            r27 = r20
            gnu.expr.ApplyExp r27 = (gnu.expr.ApplyExp) r27     // Catch:{ all -> 0x039e }
            r0 = r27
            gnu.expr.Expression r0 = r0.func     // Catch:{ all -> 0x039e }
            r27 = r0
            r32 = r27
            r27 = r32
            r28 = r32
            r23 = r28
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.QuoteExp     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x02e4
            r27 = r23
            gnu.expr.QuoteExp r27 = (gnu.expr.QuoteExp) r27     // Catch:{ all -> 0x039e }
            java.lang.Object r27 = r27.getValue()     // Catch:{ all -> 0x039e }
            r32 = r27
            r27 = r32
            r28 = r32
            r22 = r28
            r0 = r27
            boolean r0 = r0 instanceof gnu.expr.PrimProcedure     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x02e4
            r27 = r22
            gnu.expr.PrimProcedure r27 = (gnu.expr.PrimProcedure) r27     // Catch:{ all -> 0x039e }
            r24 = r27
            r27 = r24
            boolean r27 = r27.isSpecial()     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x02e4
            java.lang.String r27 = "<init>"
            r28 = r24
            r0 = r28
            gnu.bytecode.Method r0 = r0.method     // Catch:{ all -> 0x039e }
            r28 = r0
            java.lang.String r28 = r28.getName()     // Catch:{ all -> 0x039e }
            boolean r27 = r27.equals(r28)     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x02e4
            r27 = r24
            r0 = r27
            gnu.bytecode.Method r0 = r0.method     // Catch:{ all -> 0x039e }
            r27 = r0
            gnu.bytecode.ClassType r27 = r27.getDeclaringClass()     // Catch:{ all -> 0x039e }
            r21 = r27
        L_0x02e4:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            gnu.bytecode.ClassType r27 = r27.getSuperclass()     // Catch:{ all -> 0x039e }
            r24 = r27
            r27 = r21
            if (r27 == 0) goto L_0x03b8
            r27 = r20
            r28 = r4
            gnu.expr.Target r29 = gnu.expr.Target.Ignore     // Catch:{ all -> 0x039e }
            r27.compileWithPosition(r28, r29)     // Catch:{ all -> 0x039e }
            r27 = r21
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0323
            r27 = r21
            r28 = r24
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0323
            r27 = r4
            r28 = 101(0x65, float:1.42E-43)
            java.lang.String r29 = "call to <init> for not this or super class"
            r27.error(r28, r29)     // Catch:{ all -> 0x039e }
        L_0x0323:
            r27 = r13
            r28 = r4
            r27.enterFunction(r28)     // Catch:{ all -> 0x039e }
            r27 = r21
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0354
            r27 = r4
            r28 = r3
            r29 = r4
            gnu.bytecode.ClassType r28 = r28.getCompiledClassType(r29)     // Catch:{ all -> 0x039e }
            java.util.Vector r29 = new java.util.Vector     // Catch:{ all -> 0x039e }
            r32 = r29
            r29 = r32
            r30 = r32
            r31 = 10
            r30.<init>(r31)     // Catch:{ all -> 0x039e }
            r27.callInitMethods(r28, r29)     // Catch:{ all -> 0x039e }
        L_0x0354:
            r27 = r21
            if (r27 == 0) goto L_0x03c7
            r27 = r13
            r0 = r27
            gnu.expr.Expression r0 = r0.body     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r4
            gnu.expr.Expression.compileButFirst(r27, r28)     // Catch:{ all -> 0x039e }
        L_0x0365:
            r27 = r13
            r28 = r4
            r27.compileEnd(r28)     // Catch:{ all -> 0x039e }
            r27 = r13
            r28 = r4
            r27.generateApplyMethods(r28)     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r14
            r0 = r28
            r1 = r27
            r1.method = r0     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r7
            r0 = r28
            r1 = r27
            r1.curClass = r0     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r15
            r0 = r28
            r1 = r27
            r1.curLambda = r0     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r16
            r29 = r17
            r30 = r18
            r27.setLine(r28, r29, r30)     // Catch:{ all -> 0x039e }
            goto L_0x0114
        L_0x039e:
            r27 = move-exception
            r26 = r27
            r27 = r4
            r28 = r5
            r0 = r28
            r1 = r27
            r1.curClass = r0
            r27 = r4
            r28 = r6
            r0 = r28
            r1 = r27
            r1.method = r0
            r27 = r26
            throw r27
        L_0x03b8:
            r27 = r24
            if (r27 == 0) goto L_0x0323
            r27 = r24
            r28 = r4
            r29 = r3
            invokeDefaultSuperConstructor(r27, r28, r29)     // Catch:{ all -> 0x039e }
            goto L_0x0323
        L_0x03c7:
            r27 = r13
            r28 = r4
            r27.compileBody(r28)     // Catch:{ all -> 0x039e }
            goto L_0x0365
        L_0x03cf:
            r27 = r13
            r28 = r4
            r27.enterFunction(r28)     // Catch:{ all -> 0x039e }
            r27 = r13
            r28 = r4
            r27.compileBody(r28)     // Catch:{ all -> 0x039e }
            goto L_0x0365
        L_0x03de:
            r27 = r3
            r0 = r27
            boolean r0 = r0.explicitInit     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 != 0) goto L_0x0464
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            boolean r27 = r27.isInterface()     // Catch:{ all -> 0x039e }
            if (r27 != 0) goto L_0x0464
            r27 = r4
            r28 = r3
            r0 = r28
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r28 = r0
            r29 = r3
            r27.generateConstructor(r28, r29)     // Catch:{ all -> 0x039e }
        L_0x0405:
            r27 = r3
            boolean r27 = r27.isAbstract()     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x047f
            r27 = 0
            r13 = r27
            r27 = 0
            r14 = r27
        L_0x0415:
            r27 = 0
            r15 = r27
        L_0x0419:
            r27 = r15
            r28 = r14
            r0 = r27
            r1 = r28
            if (r0 >= r1) goto L_0x0688
            r27 = r13
            r28 = r15
            r27 = r27[r28]     // Catch:{ all -> 0x039e }
            r16 = r27
            r27 = r16
            java.lang.String r27 = r27.getName()     // Catch:{ all -> 0x039e }
            r17 = r27
            r27 = r16
            gnu.bytecode.Type[] r27 = r27.getParameterTypes()     // Catch:{ all -> 0x039e }
            r18 = r27
            r27 = r16
            gnu.bytecode.Type r27 = r27.getReturnType()     // Catch:{ all -> 0x039e }
            r19 = r27
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r17
            r29 = r18
            gnu.bytecode.Method r27 = r27.getMethod(r28, r29)     // Catch:{ all -> 0x039e }
            r20 = r27
            r27 = r20
            if (r27 == 0) goto L_0x0498
            r27 = r20
            boolean r27 = r27.isAbstract()     // Catch:{ all -> 0x039e }
            if (r27 != 0) goto L_0x0498
        L_0x0461:
            int r15 = r15 + 1
            goto L_0x0419
        L_0x0464:
            r27 = r3
            r0 = r27
            gnu.expr.Initializer r0 = r0.initChain     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 == 0) goto L_0x0405
            r27 = r3
            r0 = r27
            gnu.expr.Initializer r0 = r0.initChain     // Catch:{ all -> 0x039e }
            r27 = r0
            java.lang.String r28 = "unimplemented: explicit constructor cannot initialize "
            r29 = r4
            r27.reportError(r28, r29)     // Catch:{ all -> 0x039e }
            goto L_0x0405
        L_0x047f:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            gnu.bytecode.Method[] r27 = r27.getAbstractMethods()     // Catch:{ all -> 0x039e }
            r13 = r27
            r27 = r13
            r0 = r27
            int r0 = r0.length     // Catch:{ all -> 0x039e }
            r27 = r0
            r14 = r27
            goto L_0x0415
        L_0x0498:
            r27 = r17
            int r27 = r27.length()     // Catch:{ all -> 0x039e }
            r28 = 3
            r0 = r27
            r1 = r28
            if (r0 <= r1) goto L_0x05cf
            r27 = r17
            r28 = 2
            char r27 = r27.charAt(r28)     // Catch:{ all -> 0x039e }
            r28 = 116(0x74, float:1.63E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05cf
            r27 = r17
            r28 = 1
            char r27 = r27.charAt(r28)     // Catch:{ all -> 0x039e }
            r28 = 101(0x65, float:1.42E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05cf
            r27 = r17
            r28 = 0
            char r27 = r27.charAt(r28)     // Catch:{ all -> 0x039e }
            r32 = r27
            r27 = r32
            r28 = r32
            r21 = r28
            r28 = 103(0x67, float:1.44E-43)
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x04e8
            r27 = r21
            r28 = 115(0x73, float:1.61E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05cf
        L_0x04e8:
            r27 = r21
            r28 = 115(0x73, float:1.61E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05a1
            r27 = r19
            boolean r27 = r27.isVoid()     // Catch:{ all -> 0x039e }
            if (r27 == 0) goto L_0x05a1
            r27 = r18
            r0 = r27
            int r0 = r0.length     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = 1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05a1
            r27 = r18
            r28 = 0
            r27 = r27[r28]     // Catch:{ all -> 0x039e }
            r22 = r27
        L_0x0511:
            java.lang.StringBuilder r27 = new java.lang.StringBuilder     // Catch:{ all -> 0x039e }
            r32 = r27
            r27 = r32
            r28 = r32
            r28.<init>()     // Catch:{ all -> 0x039e }
            r28 = r17
            r29 = 3
            char r28 = r28.charAt(r29)     // Catch:{ all -> 0x039e }
            char r28 = java.lang.Character.toLowerCase(r28)     // Catch:{ all -> 0x039e }
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ all -> 0x039e }
            r28 = r17
            r29 = 4
            java.lang.String r28 = r28.substring(r29)     // Catch:{ all -> 0x039e }
            java.lang.StringBuilder r27 = r27.append(r28)     // Catch:{ all -> 0x039e }
            java.lang.String r27 = r27.toString()     // Catch:{ all -> 0x039e }
            r23 = r27
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r23
            gnu.bytecode.Field r27 = r27.getField(r28)     // Catch:{ all -> 0x039e }
            r24 = r27
            r27 = r24
            if (r27 != 0) goto L_0x0566
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r23
            r29 = r22
            r30 = 1
            gnu.bytecode.Field r27 = r27.addField(r28, r29, r30)     // Catch:{ all -> 0x039e }
            r24 = r27
        L_0x0566:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r17
            r29 = 1
            r30 = r18
            r31 = r19
            gnu.bytecode.Method r27 = r27.addMethod((java.lang.String) r28, (int) r29, (gnu.bytecode.Type[]) r30, (gnu.bytecode.Type) r31)     // Catch:{ all -> 0x039e }
            r25 = r27
            r27 = r25
            gnu.bytecode.CodeAttr r27 = r27.startCode()     // Catch:{ all -> 0x039e }
            r12 = r27
            r27 = r12
            r27.emitPushThis()     // Catch:{ all -> 0x039e }
            r27 = r21
            r28 = 103(0x67, float:1.44E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x05ba
            r27 = r12
            r28 = r24
            r27.emitGetField(r28)     // Catch:{ all -> 0x039e }
        L_0x059a:
            r27 = r12
            r27.emitReturn()     // Catch:{ all -> 0x039e }
            goto L_0x0461
        L_0x05a1:
            r27 = r21
            r28 = 103(0x67, float:1.44E-43)
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0461
            r27 = r18
            r0 = r27
            int r0 = r0.length     // Catch:{ all -> 0x039e }
            r27 = r0
            if (r27 != 0) goto L_0x0461
            r27 = r19
            r22 = r27
            goto L_0x0511
        L_0x05ba:
            r27 = r12
            r28 = r12
            r29 = 1
            gnu.bytecode.Variable r28 = r28.getArg(r29)     // Catch:{ all -> 0x039e }
            r27.emitLoad(r28)     // Catch:{ all -> 0x039e }
            r27 = r12
            r28 = r24
            r27.emitPutField(r28)     // Catch:{ all -> 0x039e }
            goto L_0x059a
        L_0x05cf:
            java.util.Vector r27 = new java.util.Vector     // Catch:{ all -> 0x039e }
            r32 = r27
            r27 = r32
            r28 = r32
            r28.<init>()     // Catch:{ all -> 0x039e }
            r22 = r27
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.type     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r17
            r29 = r18
            r30 = r22
            getImplMethods(r27, r28, r29, r30)     // Catch:{ all -> 0x039e }
            r27 = r22
            int r27 = r27.size()     // Catch:{ all -> 0x039e }
            r28 = 1
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0630
            r27 = r22
            int r27 = r27.size()     // Catch:{ all -> 0x039e }
            if (r27 != 0) goto L_0x062c
            java.lang.String r27 = "missing implementation for "
        L_0x0606:
            r23 = r27
            r27 = r4
            r28 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r29 = new java.lang.StringBuilder     // Catch:{ all -> 0x039e }
            r32 = r29
            r29 = r32
            r30 = r32
            r30.<init>()     // Catch:{ all -> 0x039e }
            r30 = r23
            java.lang.StringBuilder r29 = r29.append(r30)     // Catch:{ all -> 0x039e }
            r30 = r16
            java.lang.StringBuilder r29 = r29.append(r30)     // Catch:{ all -> 0x039e }
            java.lang.String r29 = r29.toString()     // Catch:{ all -> 0x039e }
            r27.error(r28, r29)     // Catch:{ all -> 0x039e }
            goto L_0x0461
        L_0x062c:
            java.lang.String r27 = "ambiguous implementation for "
            goto L_0x0606
        L_0x0630:
            r27 = r3
            r0 = r27
            gnu.bytecode.ClassType r0 = r0.instanceType     // Catch:{ all -> 0x039e }
            r27 = r0
            r28 = r17
            r29 = 1
            r30 = r18
            r31 = r19
            gnu.bytecode.Method r27 = r27.addMethod((java.lang.String) r28, (int) r29, (gnu.bytecode.Type[]) r30, (gnu.bytecode.Type) r31)     // Catch:{ all -> 0x039e }
            r23 = r27
            r27 = r23
            gnu.bytecode.CodeAttr r27 = r27.startCode()     // Catch:{ all -> 0x039e }
            r12 = r27
            r27 = r12
            gnu.bytecode.Scope r27 = r27.getCurrentScope()     // Catch:{ all -> 0x039e }
            gnu.bytecode.Variable r27 = r27.firstVar()     // Catch:{ all -> 0x039e }
            r24 = r27
        L_0x065a:
            r27 = r24
            if (r27 == 0) goto L_0x066e
            r27 = r12
            r28 = r24
            r27.emitLoad(r28)     // Catch:{ all -> 0x039e }
            r27 = r24
            gnu.bytecode.Variable r27 = r27.nextVar()     // Catch:{ all -> 0x039e }
            r24 = r27
            goto L_0x065a
        L_0x066e:
            r27 = r22
            r28 = 0
            java.lang.Object r27 = r27.elementAt(r28)     // Catch:{ all -> 0x039e }
            gnu.bytecode.Method r27 = (gnu.bytecode.Method) r27     // Catch:{ all -> 0x039e }
            r24 = r27
            r27 = r12
            r28 = r24
            r27.emitInvokeStatic(r28)     // Catch:{ all -> 0x039e }
            r27 = r12
            r27.emitReturn()     // Catch:{ all -> 0x039e }
            goto L_0x0461
        L_0x0688:
            r27 = r3
            r28 = r4
            r27.generateApplyMethods(r28)     // Catch:{ all -> 0x039e }
            r27 = r4
            r28 = r11
            r0 = r28
            r1 = r27
            r1.curLambda = r0     // Catch:{ all -> 0x039e }
            r27 = r7
            r15 = r27
            r27 = r4
            r28 = r5
            r0 = r28
            r1 = r27
            r1.curClass = r0
            r27 = r4
            r28 = r6
            r0 = r28
            r1 = r27
            r1.method = r0
            r27 = r15
            r3 = r27
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ClassExp.compileMembers(gnu.expr.Compilation):gnu.bytecode.ClassType");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        Compilation comp = visitor.getCompilation();
        if (comp == null) {
            return visitor.visitClassExp(this, d2);
        }
        ClassType saveClass = comp.curClass;
        try {
            comp.curClass = this.type;
            ClassExp visitClassExp = visitor.visitClassExp(this, d2);
            comp.curClass = saveClass;
            return visitClassExp;
        } catch (Throwable th) {
            Throwable th2 = th;
            comp.curClass = saveClass;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        Declaration firstParam;
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        LambdaExp save = visitor.currentLambda;
        visitor.currentLambda = this;
        this.supers = visitor.visitExps(this.supers, this.supers.length, d2);
        try {
            LambdaExp lambdaExp = this.firstChild;
            while (true) {
                LambdaExp child = lambdaExp;
                if (child == null || visitor.exitValue != null) {
                    visitor.currentLambda = save;
                } else {
                    if (!(this.instanceType == null || (firstParam = child.firstDecl()) == null || !firstParam.isThisParameter())) {
                        firstParam.setType(this.type);
                    }
                    R visitLambdaExp = visitor.visitLambdaExp(child, d2);
                    lambdaExp = child.nextSibling;
                }
            }
            visitor.currentLambda = save;
        } catch (Throwable th) {
            Throwable th2 = th;
            visitor.currentLambda = save;
            throw th2;
        }
    }

    static void loadSuperStaticLink(Expression superExp, ClassType superClass, Compilation compilation) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        superExp.compile(comp, Target.pushValue(Compilation.typeClassType));
        code.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
        code.emitCheckcast(superClass.getOuterLinkType());
    }

    static void invokeDefaultSuperConstructor(ClassType classType, Compilation compilation, LambdaExp lambdaExp) {
        ClassType superClass = classType;
        Compilation comp = compilation;
        LambdaExp lexp = lambdaExp;
        CodeAttr code = comp.getCode();
        Method superConstructor = superClass.getDeclaredMethod("<init>", 0);
        if (superConstructor == null) {
            comp.error('e', "super class does not have a default constructor");
            return;
        }
        code.emitPushThis();
        if (superClass.hasOuterLink() && (lexp instanceof ClassExp)) {
            ClassExp clExp = (ClassExp) lexp;
            loadSuperStaticLink(clExp.supers[clExp.superClassIndex], superClass, comp);
        }
        code.emitInvokeSpecial(superConstructor);
    }

    public void print(OutPort outPort) {
        StringBuilder sb;
        OutPort out = outPort;
        new StringBuilder();
        out.startLogicalBlock(sb.append("(").append(getExpClassName()).append("/").toString(), ")", 2);
        Object name = getSymbol();
        if (name != null) {
            out.print(name);
            out.print('/');
        }
        out.print(this.f60id);
        out.print("/fl:");
        out.print(Integer.toHexString(this.flags));
        if (this.supers.length > 0) {
            out.writeSpaceFill();
            out.startLogicalBlock("supers:", "", 2);
            for (int i = 0; i < this.supers.length; i++) {
                this.supers[i].print(out);
                out.writeSpaceFill();
            }
            out.endLogicalBlock("");
        }
        out.print('(');
        int i2 = 0;
        int length = this.keywords == null ? 0 : this.keywords.length;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                break;
            }
            if (i2 > 0) {
                out.print(' ');
            }
            decl.printInfo(out);
            i2++;
            firstDecl = decl.nextDecl();
        }
        out.print(") ");
        LambdaExp lambdaExp = this.firstChild;
        while (true) {
            LambdaExp child = lambdaExp;
            if (child == null) {
                break;
            }
            out.writeBreakLinear();
            child.print(out);
            lambdaExp = child.nextSibling;
        }
        if (this.body != null) {
            out.writeBreakLinear();
            this.body.print(out);
        }
        out.endLogicalBlock(")");
    }

    public Field compileSetField(Compilation comp) {
        ClassInitializer classInitializer;
        new ClassInitializer(this, comp);
        return classInitializer.field;
    }

    public static String slotToMethodName(String str, String str2) {
        StringBuffer stringBuffer;
        String prefix = str;
        String sname = str2;
        if (!Compilation.isValidJavaName(sname)) {
            sname = Compilation.mangleName(sname, false);
        }
        int slen = sname.length();
        new StringBuffer(slen + 3);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(prefix);
        if (slen > 0) {
            StringBuffer append2 = sbuf.append(Character.toTitleCase(sname.charAt(0)));
            StringBuffer append3 = sbuf.append(sname.substring(1));
        }
        return sbuf.toString();
    }

    public Declaration addMethod(LambdaExp lambdaExp, Object obj) {
        LambdaExp lexp = lambdaExp;
        Object mname = obj;
        Declaration mdecl = addDeclaration(mname, Compilation.typeProcedure);
        lexp.outer = this;
        lexp.setClassMethod(true);
        mdecl.noteValue(lexp);
        mdecl.setFlag(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
        mdecl.setProcedureDecl(true);
        lexp.setSymbol(mname);
        return mdecl;
    }
}
