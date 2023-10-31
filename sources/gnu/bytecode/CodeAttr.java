package gnu.bytecode;

import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeAttr extends Attribute implements AttrContainer {
    public static final int DONT_USE_JSR = 2;
    static final int FIXUP_CASE = 3;
    static final int FIXUP_DEFINE = 1;
    static final int FIXUP_DELETE3 = 8;
    static final int FIXUP_GOTO = 4;
    static final int FIXUP_JSR = 5;
    static final int FIXUP_LINE_NUMBER = 15;
    static final int FIXUP_LINE_PC = 14;
    static final int FIXUP_MOVE = 9;
    static final int FIXUP_MOVE_TO_END = 10;
    static final int FIXUP_NONE = 0;
    static final int FIXUP_SWITCH = 2;
    static final int FIXUP_TRANSFER = 6;
    static final int FIXUP_TRANSFER2 = 7;
    static final int FIXUP_TRY = 11;
    static final int FIXUP_TRY_END = 12;
    static final int FIXUP_TRY_HANDLER = 13;
    public static final int GENERATE_STACK_MAP_TABLE = 1;
    public static boolean instructionLineMode = false;

    /* renamed from: PC */
    int f55PC;

    /* renamed from: SP */
    int f56SP;
    Attribute attributes;
    byte[] code;
    ExitableBlock currentExitableBlock;
    short[] exception_table;
    int exception_table_length;
    int exitableBlockLevel;
    int fixup_count;
    Label[] fixup_labels;
    int[] fixup_offsets;
    int flags;
    IfState if_stack;
    LineNumbersAttr lines;
    Type[] local_types;
    public LocalVarsAttr locals;
    private int max_locals;
    private int max_stack;
    Label previousLabel;
    SourceDebugExtAttr sourceDbgExt;
    public StackMapTableAttr stackMap;
    public Type[] stack_types;
    TryState try_stack;
    private boolean unreachable_here;
    boolean[] varsSetInCurrentBlock;

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attributes2) {
        Attribute attribute = attributes2;
        this.attributes = attribute;
    }

    /* access modifiers changed from: package-private */
    public boolean useJsr() {
        return (this.flags & 2) == 0;
    }

    public final void fixupChain(Label here, Label target) {
        fixupAdd(9, 0, target);
        here.defineRaw(this);
    }

    public final void fixupAdd(int kind, Label label) {
        fixupAdd(kind, this.f55PC, label);
    }

    /* access modifiers changed from: package-private */
    public final void fixupAdd(int i, int i2, Label label) {
        int kind = i;
        int offset = i2;
        Label label2 = label;
        if (!(label2 == null || kind == 1 || kind == 0 || kind == 2 || kind == 11)) {
            label2.needsStackMapEntry = true;
        }
        int count = this.fixup_count;
        if (count == 0) {
            this.fixup_offsets = new int[30];
            this.fixup_labels = new Label[30];
        } else if (this.fixup_count == this.fixup_offsets.length) {
            int new_length = 2 * count;
            Label[] new_labels = new Label[new_length];
            System.arraycopy(this.fixup_labels, 0, new_labels, 0, count);
            this.fixup_labels = new_labels;
            int[] new_offsets = new int[new_length];
            System.arraycopy(this.fixup_offsets, 0, new_offsets, 0, count);
            this.fixup_offsets = new_offsets;
        }
        this.fixup_offsets[count] = (offset << 4) | kind;
        this.fixup_labels[count] = label2;
        this.fixup_count = count + 1;
    }

    private final int fixupOffset(int index) {
        return this.fixup_offsets[index] >> 4;
    }

    private final int fixupKind(int index) {
        return this.fixup_offsets[index] & 15;
    }

    public final Method getMethod() {
        return (Method) getContainer();
    }

    public final int getPC() {
        return this.f55PC;
    }

    public final int getSP() {
        return this.f56SP;
    }

    public final ConstantPool getConstants() {
        return getMethod().classfile.constants;
    }

    public final boolean reachableHere() {
        return !this.unreachable_here;
    }

    public final void setReachable(boolean val) {
        this.unreachable_here = !val;
    }

    public final void setUnreachable() {
        this.unreachable_here = true;
    }

    public int getMaxStack() {
        return this.max_stack;
    }

    public int getMaxLocals() {
        return this.max_locals;
    }

    public void setMaxStack(int n) {
        int i = n;
        this.max_stack = i;
    }

    public void setMaxLocals(int n) {
        int i = n;
        this.max_locals = i;
    }

    public byte[] getCode() {
        return this.code;
    }

    public void setCode(byte[] bArr) {
        byte[] code2 = bArr;
        this.code = code2;
        this.f55PC = code2.length;
    }

    public void setCodeLength(int len) {
        int i = len;
        this.f55PC = i;
    }

    public int getCodeLength() {
        return this.f55PC;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CodeAttr(Method method) {
        super("Code");
        Method meth = method;
        addToFrontOf(meth);
        meth.code = this;
        if (meth.getDeclaringClass().getClassfileMajorVersion() >= 50) {
            this.flags |= 3;
        }
    }

    public final void reserve(int i) {
        int bytes = i;
        if (this.code == null) {
            this.code = new byte[(100 + bytes)];
        } else if (this.f55PC + bytes > this.code.length) {
            byte[] new_code = new byte[((2 * this.code.length) + bytes)];
            System.arraycopy(this.code, 0, new_code, 0, this.f55PC);
            this.code = new_code;
        }
    }

    /* access modifiers changed from: package-private */
    public byte invert_opcode(byte opcode) {
        Throwable th;
        int iopcode = opcode & 255;
        if ((iopcode >= 153 && iopcode <= 166) || (iopcode >= 198 && iopcode <= 199)) {
            return (byte) (iopcode ^ 1);
        }
        Throwable th2 = th;
        new Error("unknown opcode to invert_opcode");
        throw th2;
    }

    public final void put1(int i) {
        byte[] bArr = this.code;
        int i2 = this.f55PC;
        int i3 = i2 + 1;
        this.f55PC = i3;
        bArr[i2] = (byte) i;
        this.unreachable_here = false;
    }

    public final void put2(int i) {
        int i2 = i;
        byte[] bArr = this.code;
        int i3 = this.f55PC;
        int i4 = i3 + 1;
        this.f55PC = i4;
        bArr[i3] = (byte) (i2 >> 8);
        byte[] bArr2 = this.code;
        int i5 = this.f55PC;
        int i6 = i5 + 1;
        this.f55PC = i6;
        bArr2[i5] = (byte) i2;
        this.unreachable_here = false;
    }

    public final void put4(int i) {
        int i2 = i;
        byte[] bArr = this.code;
        int i3 = this.f55PC;
        int i4 = i3 + 1;
        this.f55PC = i4;
        bArr[i3] = (byte) (i2 >> 24);
        byte[] bArr2 = this.code;
        int i5 = this.f55PC;
        int i6 = i5 + 1;
        this.f55PC = i6;
        bArr2[i5] = (byte) (i2 >> 16);
        byte[] bArr3 = this.code;
        int i7 = this.f55PC;
        int i8 = i7 + 1;
        this.f55PC = i8;
        bArr3[i7] = (byte) (i2 >> 8);
        byte[] bArr4 = this.code;
        int i9 = this.f55PC;
        int i10 = i9 + 1;
        this.f55PC = i10;
        bArr4[i9] = (byte) i2;
        this.unreachable_here = false;
    }

    public final void putIndex2(CpoolEntry cnst) {
        put2(cnst.index);
    }

    public final void putLineNumber(String str, int i) {
        String filename = str;
        int linenumber = i;
        if (filename != null) {
            getMethod().classfile.setSourceFile(filename);
        }
        putLineNumber(linenumber);
    }

    public final void putLineNumber(int i) {
        int linenumber = i;
        if (this.sourceDbgExt != null) {
            linenumber = this.sourceDbgExt.fixLine(linenumber);
        }
        fixupAdd(14, (Label) null);
        fixupAdd(15, linenumber, (Label) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void noteParamTypes() {
        /*
            r13 = this;
            r0 = r13
            r9 = r0
            gnu.bytecode.Method r9 = r9.getMethod()
            r1 = r9
            r9 = 0
            r2 = r9
            r9 = r1
            int r9 = r9.access_flags
            r10 = 8
            r9 = r9 & 8
            if (r9 != 0) goto L_0x0042
            r9 = r1
            gnu.bytecode.ClassType r9 = r9.classfile
            r3 = r9
            java.lang.String r9 = "<init>"
            r10 = r1
            java.lang.String r10 = r10.getName()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x003a
            java.lang.String r9 = "java.lang.Object"
            r10 = r3
            java.lang.String r10 = r10.getName()
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x003a
            r9 = r3
            gnu.bytecode.ClassType r9 = (gnu.bytecode.ClassType) r9
            gnu.bytecode.UninitializedType r9 = gnu.bytecode.UninitializedType.uninitializedThis(r9)
            r3 = r9
        L_0x003a:
            r9 = r0
            r10 = r2
            int r2 = r2 + 1
            r11 = r3
            r9.noteVarType(r10, r11)
        L_0x0042:
            r9 = r1
            gnu.bytecode.Type[] r9 = r9.arg_types
            int r9 = r9.length
            r3 = r9
            r9 = 0
            r4 = r9
        L_0x0049:
            r9 = r4
            r10 = r3
            if (r9 >= r10) goto L_0x006d
            r9 = r1
            gnu.bytecode.Type[] r9 = r9.arg_types
            r10 = r4
            r9 = r9[r10]
            r5 = r9
            r9 = r0
            r10 = r2
            int r2 = r2 + 1
            r11 = r5
            r9.noteVarType(r10, r11)
            r9 = r5
            int r9 = r9.getSizeInWords()
            r6 = r9
        L_0x0062:
            int r6 = r6 + -1
            r9 = r6
            if (r9 <= 0) goto L_0x006a
            int r2 = r2 + 1
            goto L_0x0062
        L_0x006a:
            int r4 = r4 + 1
            goto L_0x0049
        L_0x006d:
            r9 = r0
            int r9 = r9.flags
            r10 = 1
            r9 = r9 & 1
            if (r9 == 0) goto L_0x00d3
            r9 = r0
            gnu.bytecode.StackMapTableAttr r10 = new gnu.bytecode.StackMapTableAttr
            r12 = r10
            r10 = r12
            r11 = r12
            r11.<init>()
            r9.stackMap = r10
            r9 = 20
            r10 = r2
            int r9 = r9 + r10
            int[] r9 = new int[r9]
            r4 = r9
            r9 = 0
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x008b:
            r9 = r6
            r10 = r2
            if (r9 >= r10) goto L_0x00b8
            r9 = r0
            gnu.bytecode.StackMapTableAttr r9 = r9.stackMap
            r10 = r0
            gnu.bytecode.Type[] r10 = r10.local_types
            r11 = r6
            r10 = r10[r11]
            r11 = r0
            int r9 = r9.encodeVerificationType(r10, r11)
            r7 = r9
            r9 = r4
            r10 = r5
            int r5 = r5 + 1
            r11 = r7
            r9[r10] = r11
            r9 = r7
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r8 = r9
            r9 = r8
            r10 = 3
            if (r9 == r10) goto L_0x00b3
            r9 = r8
            r10 = 4
            if (r9 != r10) goto L_0x00b5
        L_0x00b3:
            int r6 = r6 + 1
        L_0x00b5:
            int r6 = r6 + 1
            goto L_0x008b
        L_0x00b8:
            r9 = r0
            gnu.bytecode.StackMapTableAttr r9 = r9.stackMap
            r10 = r4
            r9.encodedLocals = r10
            r9 = r0
            gnu.bytecode.StackMapTableAttr r9 = r9.stackMap
            r10 = r5
            r9.countLocals = r10
            r9 = r0
            gnu.bytecode.StackMapTableAttr r9 = r9.stackMap
            r10 = 10
            int[] r10 = new int[r10]
            r9.encodedStack = r10
            r9 = r0
            gnu.bytecode.StackMapTableAttr r9 = r9.stackMap
            r10 = 0
            r9.countStack = r10
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.noteParamTypes():void");
    }

    public void noteVarType(int i, Type type) {
        Type prev;
        int offset = i;
        Type type2 = type;
        int size = type2.getSizeInWords();
        if (this.local_types == null) {
            this.local_types = new Type[(offset + size + 20)];
        } else if (offset + size > this.local_types.length) {
            Type[] new_array = new Type[(2 * (offset + size))];
            System.arraycopy(this.local_types, 0, new_array, 0, this.local_types.length);
            this.local_types = new_array;
        }
        this.local_types[offset] = type2;
        if (this.varsSetInCurrentBlock == null) {
            this.varsSetInCurrentBlock = new boolean[this.local_types.length];
        } else if (this.varsSetInCurrentBlock.length <= offset) {
            boolean[] tmp = new boolean[this.local_types.length];
            System.arraycopy(this.varsSetInCurrentBlock, 0, tmp, 0, this.varsSetInCurrentBlock.length);
            this.varsSetInCurrentBlock = tmp;
        }
        this.varsSetInCurrentBlock[offset] = true;
        if (offset > 0 && (prev = this.local_types[offset - 1]) != null && prev.getSizeInWords() == 2) {
            this.local_types[offset - 1] = null;
        }
        while (true) {
            size--;
            if (size > 0) {
                offset++;
                this.local_types[offset] = null;
            } else {
                return;
            }
        }
    }

    public final void setTypes(Label label) {
        Label label2 = label;
        setTypes(label2.localTypes, label2.stackTypes);
    }

    public final void setTypes(Type[] typeArr, Type[] typeArr2) {
        Type[] labelLocals = typeArr;
        Type[] labelStack = typeArr2;
        int usedStack = labelStack.length;
        int usedLocals = labelLocals.length;
        if (this.local_types != null) {
            if (usedLocals > 0) {
                System.arraycopy(labelLocals, 0, this.local_types, 0, usedLocals);
            }
            for (int i = usedLocals; i < this.local_types.length; i++) {
                this.local_types[i] = null;
            }
        }
        if (this.stack_types == null || usedStack > this.stack_types.length) {
            this.stack_types = new Type[usedStack];
        } else {
            for (int i2 = usedStack; i2 < this.stack_types.length; i2++) {
                this.stack_types[i2] = null;
            }
        }
        System.arraycopy(labelStack, 0, this.stack_types, 0, usedStack);
        this.f56SP = usedStack;
    }

    public final void pushType(Type type) {
        Throwable th;
        Type type2 = type;
        if (type2.size == 0) {
            Throwable th2 = th;
            new Error("pushing void type onto stack");
            throw th2;
        }
        if (this.stack_types == null || this.stack_types.length == 0) {
            this.stack_types = new Type[20];
        } else if (this.f56SP + 1 >= this.stack_types.length) {
            Type[] new_array = new Type[(2 * this.stack_types.length)];
            System.arraycopy(this.stack_types, 0, new_array, 0, this.f56SP);
            this.stack_types = new_array;
        }
        if (type2.size == 8) {
            Type[] typeArr = this.stack_types;
            int i = this.f56SP;
            int i2 = i + 1;
            this.f56SP = i2;
            typeArr[i] = Type.voidType;
        }
        Type[] typeArr2 = this.stack_types;
        int i3 = this.f56SP;
        int i4 = i3 + 1;
        this.f56SP = i4;
        typeArr2[i3] = type2;
        if (this.f56SP > this.max_stack) {
            this.max_stack = this.f56SP;
        }
    }

    public final Type popType() {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        if (this.f56SP <= 0) {
            Throwable th3 = th2;
            new StringBuilder();
            new Error(sb.append("popType called with empty stack ").append(getMethod()).toString());
            throw th3;
        }
        Type[] typeArr = this.stack_types;
        int i = this.f56SP - 1;
        int i2 = i;
        this.f56SP = i2;
        Type type = typeArr[i];
        if (type.size != 8 || popType().isVoid()) {
            return type;
        }
        Throwable th4 = th;
        new Error("missing void type on stack");
        throw th4;
    }

    public final Type topType() {
        return this.stack_types[this.f56SP - 1];
    }

    public void emitPop(int i) {
        int nvalues = i;
        while (nvalues > 0) {
            reserve(1);
            if (popType().size > 4) {
                put1(88);
            } else if (nvalues > 1) {
                if (popType().size > 4) {
                    put1(87);
                    reserve(1);
                }
                put1(88);
                nvalues--;
            } else {
                put1(87);
            }
            nvalues--;
        }
    }

    public Label getLabel() {
        Label label;
        new Label();
        Label label2 = label;
        label2.defineRaw(this);
        return label2;
    }

    public void emitSwap() {
        reserve(1);
        Type type1 = popType();
        Type type2 = popType();
        if (type1.size > 4 || type2.size > 4) {
            pushType(type2);
            pushType(type1);
            emitDupX();
            emitPop(1);
            return;
        }
        pushType(type1);
        put1(95);
        pushType(type2);
    }

    public void emitDup() {
        reserve(1);
        Type type = topType();
        put1(type.size <= 4 ? 89 : 92);
        pushType(type);
    }

    public void emitDupX() {
        reserve(1);
        Type type = popType();
        Type skipedType = popType();
        if (skipedType.size <= 4) {
            put1(type.size <= 4 ? 90 : 93);
        } else {
            put1(type.size <= 4 ? 91 : 94);
        }
        pushType(type);
        pushType(skipedType);
        pushType(type);
    }

    public void emitDup(int i, int i2) {
        Throwable th;
        int kind;
        Throwable th2;
        Throwable th3;
        int i3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        int size = i;
        int offset = i2;
        if (size != 0) {
            reserve(1);
            Type copied1 = popType();
            Type copied2 = null;
            if (size == 1) {
                if (copied1.size > 4) {
                    Throwable th7 = th6;
                    new Error("using dup for 2-word type");
                    throw th7;
                }
            } else if (size != 2) {
                Throwable th8 = th5;
                new Error("invalid size to emitDup");
                throw th8;
            } else if (copied1.size <= 4) {
                copied2 = popType();
                if (copied2.size > 4) {
                    Throwable th9 = th4;
                    new Error("dup will cause invalid types on stack");
                    throw th9;
                }
            }
            Type skipped1 = null;
            Type skipped2 = null;
            if (offset == 0) {
                if (size == 1) {
                    i3 = 89;
                } else {
                    i3 = 92;
                }
                kind = i3;
            } else if (offset == 1) {
                kind = size == 1 ? 90 : 93;
                skipped1 = popType();
                if (skipped1.size > 4) {
                    Throwable th10 = th3;
                    new Error("dup will cause invalid types on stack");
                    throw th10;
                }
            } else if (offset == 2) {
                kind = size == 1 ? 91 : 94;
                skipped1 = popType();
                if (skipped1.size <= 4) {
                    skipped2 = popType();
                    if (skipped2.size > 4) {
                        Throwable th11 = th2;
                        new Error("dup will cause invalid types on stack");
                        throw th11;
                    }
                }
            } else {
                Throwable th12 = th;
                new Error("emitDup:  invalid offset");
                throw th12;
            }
            put1(kind);
            if (copied2 != null) {
                pushType(copied2);
            }
            pushType(copied1);
            if (skipped2 != null) {
                pushType(skipped2);
            }
            if (skipped1 != null) {
                pushType(skipped1);
            }
            if (copied2 != null) {
                pushType(copied2);
            }
            pushType(copied1);
        }
    }

    public void emitDup(int size) {
        emitDup(size, 0);
    }

    public void emitDup(Type type) {
        emitDup(type.size > 4 ? 2 : 1, 0);
    }

    public void enterScope(Scope scope) {
        Scope scope2 = scope;
        scope2.setStartPC(this);
        this.locals.enterScope(scope2);
    }

    public Scope pushScope() {
        Scope scope;
        LocalVarsAttr localVarsAttr;
        new Scope();
        Scope scope2 = scope;
        if (this.locals == null) {
            new LocalVarsAttr(getMethod());
            this.locals = localVarsAttr;
        }
        enterScope(scope2);
        if (this.locals.parameter_scope == null) {
            this.locals.parameter_scope = scope2;
        }
        return scope2;
    }

    public Scope getCurrentScope() {
        return this.locals.current_scope;
    }

    public Scope popScope() {
        Scope scope = this.locals.current_scope;
        this.locals.current_scope = scope.parent;
        scope.freeLocals(this);
        scope.end = getLabel();
        return scope;
    }

    public Variable getArg(int index) {
        return this.locals.parameter_scope.getVariable(index);
    }

    public Variable lookup(String str) {
        String name = str;
        Scope scope = this.locals.current_scope;
        while (true) {
            Scope scope2 = scope;
            if (scope2 == null) {
                return null;
            }
            Variable var = scope2.lookup(name);
            if (var != null) {
                return var;
            }
            scope = scope2.parent;
        }
    }

    public Variable addLocal(Type type) {
        return this.locals.current_scope.addVariable(this, type, (String) null);
    }

    public Variable addLocal(Type type, String name) {
        return this.locals.current_scope.addVariable(this, type, name);
    }

    public void addParamLocals() {
        Method method = getMethod();
        if ((method.access_flags & 8) == 0) {
            addLocal(method.classfile).setParameter(true);
        }
        int arg_count = method.arg_types.length;
        for (int i = 0; i < arg_count; i++) {
            addLocal(method.arg_types[i]).setParameter(true);
        }
    }

    public final void emitPushConstant(int i, Type type) {
        Throwable th;
        int val = i;
        switch (type.getSignature().charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                emitPushInt(val);
                return;
            case 'D':
                emitPushDouble((double) val);
                return;
            case 'F':
                emitPushFloat((float) val);
                return;
            case 'J':
                emitPushLong((long) val);
                return;
            default:
                Throwable th2 = th;
                new Error("bad type to emitPushConstant");
                throw th2;
        }
    }

    public final void emitPushConstant(CpoolEntry cpoolEntry) {
        CpoolEntry cnst = cpoolEntry;
        reserve(3);
        int index = cnst.index;
        if (cnst instanceof CpoolValue2) {
            put1(20);
            put2(index);
        } else if (index < 256) {
            put1(18);
            put1(index);
        } else {
            put1(19);
            put2(index);
        }
    }

    public final void emitPushInt(int i) {
        int i2 = i;
        reserve(3);
        if (i2 >= -1 && i2 <= 5) {
            put1(i2 + 3);
        } else if (i2 >= -128 && i2 < 128) {
            put1(16);
            put1(i2);
        } else if (i2 < -32768 || i2 >= 32768) {
            emitPushConstant(getConstants().addInt(i2));
        } else {
            put1(17);
            put2(i2);
        }
        pushType(Type.intType);
    }

    public void emitPushLong(long j) {
        long i = j;
        if (i == 0 || i == 1) {
            reserve(1);
            put1(9 + ((int) i));
        } else if (((long) ((int) i)) == i) {
            emitPushInt((int) i);
            reserve(1);
            Type popType = popType();
            put1(133);
        } else {
            emitPushConstant(getConstants().addLong(i));
        }
        pushType(Type.longType);
    }

    public void emitPushFloat(float f) {
        float x = f;
        int xi = (int) x;
        if (((float) xi) != x || xi < -128 || xi >= 128) {
            emitPushConstant(getConstants().addFloat(x));
        } else if (xi < 0 || xi > 2) {
            emitPushInt(xi);
            reserve(1);
            Type popType = popType();
            put1(134);
        } else {
            reserve(1);
            put1(11 + xi);
            if (xi == 0 && Float.floatToIntBits(x) != 0) {
                reserve(1);
                put1(118);
            }
        }
        pushType(Type.floatType);
    }

    public void emitPushDouble(double d) {
        double x = d;
        int xi = (int) x;
        if (((double) xi) != x || xi < -128 || xi >= 128) {
            emitPushConstant(getConstants().addDouble(x));
        } else if (xi == 0 || xi == 1) {
            reserve(1);
            put1(14 + xi);
            if (xi == 0 && Double.doubleToLongBits(x) != 0) {
                reserve(1);
                put1(119);
            }
        } else {
            emitPushInt(xi);
            reserve(1);
            Type popType = popType();
            put1(135);
        }
        pushType(Type.doubleType);
    }

    public static final String calculateSplit(String str) {
        StringBuffer stringBuffer;
        String str2 = str;
        int strLength = str2.length();
        new StringBuffer(20);
        StringBuffer sbuf = stringBuffer;
        int segmentStart = 0;
        int byteLength = 0;
        for (int i = 0; i < strLength; i++) {
            char ch = str2.charAt(i);
            int bytes = ch >= 2048 ? 3 : (ch >= 128 || ch == 0) ? 2 : 1;
            if (byteLength + bytes > 65535) {
                StringBuffer append = sbuf.append((char) (i - segmentStart));
                segmentStart = i;
                byteLength = 0;
            }
            byteLength += bytes;
        }
        StringBuffer append2 = sbuf.append((char) (strLength - segmentStart));
        return sbuf.toString();
    }

    public final void emitPushString(String str) {
        String str2 = str;
        if (str2 == null) {
            emitPushNull();
            return;
        }
        int length = str2.length();
        String segments = calculateSplit(str2);
        int numSegments = segments.length();
        if (numSegments <= 1) {
            emitPushConstant(getConstants().addString(str2));
            pushType(Type.javalangStringType);
            return;
        }
        if (numSegments == 2) {
            int firstSegment = segments.charAt(0);
            emitPushString(str2.substring(0, firstSegment));
            emitPushString(str2.substring(firstSegment));
            emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("concat", 1));
        } else {
            ClassType sbufType = ClassType.make("java.lang.StringBuffer");
            emitNew(sbufType);
            emitDup((Type) sbufType);
            emitPushInt(length);
            emitInvokeSpecial(sbufType.getDeclaredMethod("<init>", new Type[]{Type.intType}));
            Method appendMethod = sbufType.getDeclaredMethod("append", new Type[]{Type.javalangStringType});
            int segStart = 0;
            for (int seg = 0; seg < numSegments; seg++) {
                emitDup((Type) sbufType);
                int segEnd = segStart + segments.charAt(seg);
                emitPushString(str2.substring(segStart, segEnd));
                emitInvokeVirtual(appendMethod);
                segStart = segEnd;
            }
            emitInvokeVirtual(Type.toString_method);
        }
        if (str2 == str2.intern()) {
            emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("intern", 0));
        }
    }

    public final void emitPushClass(ObjectType ctype) {
        emitPushConstant(getConstants().addClass(ctype));
        pushType(Type.javalangClassType);
    }

    public void emitPushNull() {
        reserve(1);
        put1(1);
        pushType(Type.nullType);
    }

    public void emitPushDefaultValue(Type type) {
        Type type2 = type.getImplementationType();
        if (type2 instanceof PrimType) {
            emitPushConstant(0, type2);
        } else {
            emitPushNull();
        }
    }

    public void emitStoreDefaultValue(Variable variable) {
        Variable var = variable;
        emitPushDefaultValue(var.getType());
        emitStore(var);
    }

    public final void emitPushThis() {
        emitLoad(this.locals.used[0]);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emitPushPrimArray(java.lang.Object r17, gnu.bytecode.ArrayType r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r12 = r2
            gnu.bytecode.Type r12 = r12.getComponentType()
            r3 = r12
            r12 = r1
            int r12 = java.lang.reflect.Array.getLength(r12)
            r4 = r12
            r12 = r0
            r13 = r4
            r12.emitPushInt(r13)
            r12 = r0
            r13 = r3
            r12.emitNewArray((gnu.bytecode.Type) r13)
            r12 = r3
            java.lang.String r12 = r12.getSignature()
            r13 = 0
            char r12 = r12.charAt(r13)
            r5 = r12
            r12 = 0
            r6 = r12
        L_0x0029:
            r12 = r6
            r13 = r4
            if (r12 >= r13) goto L_0x0102
            r12 = 0
            r7 = r12
            r12 = 0
            r9 = r12
            r12 = 0
            r10 = r12
            r12 = r5
            switch(r12) {
                case 66: goto L_0x0096;
                case 67: goto L_0x0084;
                case 68: goto L_0x00d3;
                case 70: goto L_0x00c0;
                case 73: goto L_0x0060;
                case 74: goto L_0x004f;
                case 83: goto L_0x0072;
                case 90: goto L_0x00a8;
                default: goto L_0x0039;
            }
        L_0x0039:
            r12 = r0
            r13 = r2
            r12.emitDup((gnu.bytecode.Type) r13)
            r12 = r0
            r13 = r6
            r12.emitPushInt(r13)
            r12 = r5
            switch(r12) {
                case 66: goto L_0x00e5;
                case 67: goto L_0x00e5;
                case 68: goto L_0x00fb;
                case 70: goto L_0x00f4;
                case 73: goto L_0x00e5;
                case 74: goto L_0x00ed;
                case 83: goto L_0x00e5;
                case 90: goto L_0x00e5;
                default: goto L_0x0047;
            }
        L_0x0047:
            r12 = r0
            r13 = r3
            r12.emitArrayStore(r13)
        L_0x004c:
            int r6 = r6 + 1
            goto L_0x0029
        L_0x004f:
            r12 = r1
            long[] r12 = (long[]) r12
            long[] r12 = (long[]) r12
            r13 = r6
            r12 = r12[r13]
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x0060:
            r12 = r1
            int[] r12 = (int[]) r12
            int[] r12 = (int[]) r12
            r13 = r6
            r12 = r12[r13]
            long r12 = (long) r12
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x0072:
            r12 = r1
            short[] r12 = (short[]) r12
            short[] r12 = (short[]) r12
            r13 = r6
            short r12 = r12[r13]
            long r12 = (long) r12
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x0084:
            r12 = r1
            char[] r12 = (char[]) r12
            char[] r12 = (char[]) r12
            r13 = r6
            char r12 = r12[r13]
            long r12 = (long) r12
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x0096:
            r12 = r1
            byte[] r12 = (byte[]) r12
            byte[] r12 = (byte[]) r12
            r13 = r6
            byte r12 = r12[r13]
            long r12 = (long) r12
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x00a8:
            r12 = r1
            boolean[] r12 = (boolean[]) r12
            boolean[] r12 = (boolean[]) r12
            r13 = r6
            boolean r12 = r12[r13]
            if (r12 == 0) goto L_0x00bd
            r12 = 1
        L_0x00b4:
            r7 = r12
            r12 = r7
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x00bd:
            r12 = 0
            goto L_0x00b4
        L_0x00c0:
            r12 = r1
            float[] r12 = (float[]) r12
            float[] r12 = (float[]) r12
            r13 = r6
            r12 = r12[r13]
            r9 = r12
            r12 = r9
            double r12 = (double) r12
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x00d3:
            r12 = r1
            double[] r12 = (double[]) r12
            double[] r12 = (double[]) r12
            r13 = r6
            r12 = r12[r13]
            r10 = r12
            r12 = r10
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0039
            goto L_0x004c
        L_0x00e5:
            r12 = r0
            r13 = r7
            int r13 = (int) r13
            r12.emitPushInt(r13)
            goto L_0x0047
        L_0x00ed:
            r12 = r0
            r13 = r7
            r12.emitPushLong(r13)
            goto L_0x0047
        L_0x00f4:
            r12 = r0
            r13 = r9
            r12.emitPushFloat(r13)
            goto L_0x0047
        L_0x00fb:
            r12 = r0
            r13 = r10
            r12.emitPushDouble(r13)
            goto L_0x0047
        L_0x0102:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.emitPushPrimArray(java.lang.Object, gnu.bytecode.ArrayType):void");
    }

    /* access modifiers changed from: package-private */
    public void emitNewArray(int type_code) {
        reserve(2);
        put1(188);
        put1(type_code);
    }

    public final void emitArrayLength() {
        Throwable th;
        if (!(popType() instanceof ArrayType)) {
            Throwable th2 = th;
            new Error("non-array type in emitArrayLength");
            throw th2;
        }
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK);
        pushType(Type.intType);
    }

    private int adjustTypedOp(char sig) {
        switch (sig) {
            case 'B':
            case 'Z':
                return 5;
            case 'C':
                return 6;
            case 'D':
                return 3;
            case 'F':
                return 2;
            case 'I':
                return 0;
            case 'J':
                return 1;
            case 'S':
                return 7;
            default:
                return 4;
        }
    }

    private int adjustTypedOp(Type type) {
        return adjustTypedOp(type.getSignature().charAt(0));
    }

    private void emitTypedOp(int op, Type type) {
        reserve(1);
        put1(op + adjustTypedOp(type));
    }

    private void emitTypedOp(int op, char sig) {
        reserve(1);
        put1(op + adjustTypedOp(sig));
    }

    public void emitArrayStore(Type element_type) {
        Type popType = popType();
        Type popType2 = popType();
        Type popType3 = popType();
        emitTypedOp(79, element_type);
    }

    public void emitArrayStore() {
        Type popType = popType();
        Type popType2 = popType();
        emitTypedOp(79, ((ArrayType) popType().getImplementationType()).getComponentType());
    }

    public void emitArrayLoad(Type type) {
        Type element_type = type;
        Type popType = popType();
        Type popType2 = popType();
        emitTypedOp(46, element_type);
        pushType(element_type);
    }

    public void emitArrayLoad() {
        Type popType = popType();
        Type elementType = ((ArrayType) popType().getImplementationType()).getComponentType();
        emitTypedOp(46, elementType);
        pushType(elementType);
    }

    public void emitNew(ClassType classType) {
        Label label;
        Type type;
        ClassType type2 = classType;
        reserve(3);
        new Label(this);
        Label label2 = label;
        label2.defineRaw(this);
        put1(187);
        putIndex2(getConstants().addClass((ObjectType) type2));
        new UninitializedType(type2, label2);
        pushType(type);
    }

    public void emitNewArray(Type type, int i) {
        Throwable th;
        ObjectType objectType;
        Throwable th2;
        Throwable th3;
        Type type2;
        int code2;
        Throwable th4;
        Throwable th5;
        Type element_type = type;
        int dims = i;
        if (popType().promote() != Type.intType) {
            Throwable th6 = th5;
            new Error("non-int dim. spec. in emitNewArray");
            throw th6;
        }
        if (element_type instanceof PrimType) {
            switch (element_type.getSignature().charAt(0)) {
                case 'B':
                    code2 = 8;
                    break;
                case 'C':
                    code2 = 5;
                    break;
                case 'D':
                    code2 = 7;
                    break;
                case 'F':
                    code2 = 6;
                    break;
                case 'I':
                    code2 = 10;
                    break;
                case 'J':
                    code2 = 11;
                    break;
                case 'S':
                    code2 = 9;
                    break;
                case 'Z':
                    code2 = 4;
                    break;
                default:
                    Throwable th7 = th4;
                    new Error("bad PrimType in emitNewArray");
                    throw th7;
            }
            emitNewArray(code2);
        } else if (element_type instanceof ObjectType) {
            reserve(3);
            put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG);
            putIndex2(getConstants().addClass((ObjectType) element_type));
        } else if (element_type instanceof ArrayType) {
            reserve(4);
            put1(197);
            new ArrayType(element_type);
            putIndex2(getConstants().addClass(objectType));
            if (dims < 1 || dims > 255) {
                Throwable th8 = th2;
                new Error("dims out of range in emitNewArray");
                throw th8;
            }
            put1(dims);
            do {
                dims--;
                if (dims > 0) {
                }
            } while (popType().promote() == Type.intType);
            Throwable th9 = th3;
            new Error("non-int dim. spec. in emitNewArray");
            throw th9;
        } else {
            Throwable th10 = th;
            new Error("unimplemented type in emitNewArray");
            throw th10;
        }
        new ArrayType(element_type);
        pushType(type2);
    }

    public void emitNewArray(Type element_type) {
        emitNewArray(element_type, 1);
    }

    private void emitBinop(int i) {
        Throwable th;
        int base_code = i;
        Type type2 = popType().promote();
        Type type1_raw = popType();
        Type type1 = type1_raw.promote();
        if (type1 != type2 || !(type1 instanceof PrimType)) {
            Throwable th2 = th;
            new Error("non-matching or bad types in binary operation");
            throw th2;
        }
        emitTypedOp(base_code, type1);
        pushType(type1_raw);
    }

    private void emitBinop(int base_code, char c) {
        char sig = c;
        Type popType = popType();
        Type popType2 = popType();
        emitTypedOp(base_code, sig);
        pushType(Type.signatureToPrimitive(sig));
    }

    public void emitBinop(int base_code, Type type) {
        Type type2 = type;
        Type popType = popType();
        Type popType2 = popType();
        emitTypedOp(base_code, type2);
        pushType(type2);
    }

    public final void emitAdd(char sig) {
        emitBinop(96, sig);
    }

    public final void emitAdd(PrimType type) {
        emitBinop(96, (Type) type);
    }

    public final void emitAdd() {
        emitBinop(96);
    }

    public final void emitSub(char sig) {
        emitBinop(100, sig);
    }

    public final void emitSub(PrimType type) {
        emitBinop(100, (Type) type);
    }

    public final void emitSub() {
        emitBinop(100);
    }

    public final void emitMul() {
        emitBinop(104);
    }

    public final void emitDiv() {
        emitBinop(108);
    }

    public final void emitRem() {
        emitBinop(112);
    }

    public final void emitAnd() {
        emitBinop(126);
    }

    public final void emitIOr() {
        emitBinop(128);
    }

    public final void emitXOr() {
        emitBinop(130);
    }

    public final void emitShl() {
        emitShift(120);
    }

    public final void emitShr() {
        emitShift(122);
    }

    public final void emitUshr() {
        emitShift(124);
    }

    private void emitShift(int i) {
        Throwable th;
        Throwable th2;
        int base_code = i;
        Type type2 = popType().promote();
        Type type1_raw = popType();
        Type type1 = type1_raw.promote();
        if (type1 != Type.intType && type1 != Type.longType) {
            Throwable th3 = th2;
            new Error("the value shifted must be an int or a long");
            throw th3;
        } else if (type2 != Type.intType) {
            Throwable th4 = th;
            new Error("the amount of shift must be an int");
            throw th4;
        } else {
            emitTypedOp(base_code, type1);
            pushType(type1_raw);
        }
    }

    public final void emitNot(Type type) {
        Type type2 = type;
        emitPushConstant(1, type2);
        emitAdd();
        emitPushConstant(1, type2);
        emitAnd();
    }

    public void emitPrimop(int i, int i2, Type type) {
        int opcode = i;
        int arg_count = i2;
        Type retType = type;
        reserve(1);
        while (true) {
            arg_count--;
            if (arg_count >= 0) {
                Type popType = popType();
            } else {
                put1(opcode);
                pushType(retType);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void emitMaybeWide(int i, int i2) {
        int opcode = i;
        int index = i2;
        if (index >= 256) {
            put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
            put1(opcode);
            put2(index);
            return;
        }
        put1(opcode);
        put1(index);
    }

    public final void emitLoad(Variable variable) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Variable var = variable;
        if (var.dead()) {
            Throwable th3 = th2;
            new Error("attempting to push dead variable");
            throw th3;
        }
        int offset = var.offset;
        if (offset < 0 || !var.isSimple()) {
            Throwable th4 = th;
            new StringBuilder();
            new Error(sb.append("attempting to load from unassigned variable ").append(var).append(" simple:").append(var.isSimple()).append(", offset: ").append(offset).toString());
            throw th4;
        }
        Type type = var.getType().promote();
        reserve(4);
        int kind = adjustTypedOp(type);
        if (offset <= 3) {
            put1(26 + (4 * kind) + offset);
        } else {
            emitMaybeWide(21 + kind, offset);
        }
        pushType(var.getType());
    }

    public void emitStore(Variable variable) {
        Throwable th;
        StringBuilder sb;
        Variable var = variable;
        int offset = var.offset;
        if (offset < 0 || !var.isSimple()) {
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("attempting to store in unassigned ").append(var).append(" simple:").append(var.isSimple()).append(", offset: ").append(offset).toString());
            throw th2;
        }
        Type type = var.getType().promote();
        noteVarType(offset, type);
        reserve(4);
        Type popType = popType();
        int kind = adjustTypedOp(type);
        if (offset <= 3) {
            put1(59 + (4 * kind) + offset);
        } else {
            emitMaybeWide(54 + kind, offset);
        }
    }

    public void emitInc(Variable variable, short s) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Variable var = variable;
        short inc = s;
        if (var.dead()) {
            Throwable th4 = th3;
            new Error("attempting to increment dead variable");
            throw th4;
        }
        int offset = var.offset;
        if (offset < 0 || !var.isSimple()) {
            Throwable th5 = th;
            new StringBuilder();
            new Error(sb.append("attempting to increment unassigned variable").append(var.getName()).append(" simple:").append(var.isSimple()).append(", offset: ").append(offset).toString());
            throw th5;
        }
        reserve(6);
        if (var.getType().getImplementationType().promote() != Type.intType) {
            Throwable th6 = th2;
            new Error("attempting to increment non-int variable");
            throw th6;
        }
        if (offset > 255 || inc > 255 || inc < -256) {
            put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
            put1(132);
            put2(offset);
            put2(inc);
            return;
        }
        put1(132);
        put1(offset);
        put1(inc);
    }

    private final void emitFieldop(Field field, int opcode) {
        reserve(3);
        put1(opcode);
        putIndex2(getConstants().addFieldRef(field));
    }

    public final void emitGetStatic(Field field) {
        Field field2 = field;
        pushType(field2.type);
        emitFieldop(field2, 178);
    }

    public final void emitGetField(Field field) {
        Field field2 = field;
        Type popType = popType();
        pushType(field2.type);
        emitFieldop(field2, 180);
    }

    public final void emitPutStatic(Field field) {
        Type popType = popType();
        emitFieldop(field, 179);
    }

    public final void emitPutField(Field field) {
        Type popType = popType();
        Type popType2 = popType();
        emitFieldop(field, 181);
    }

    private int words(Type[] typeArr) {
        Type[] types = typeArr;
        int res = 0;
        int i = types.length;
        while (true) {
            i--;
            if (i < 0) {
                return res;
            }
            if (types[i].size > 4) {
                res += 2;
            } else {
                res++;
            }
        }
    }

    public void emitInvokeMethod(Method method, int i) {
        Throwable th;
        Type t;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        Method method2 = method;
        int opcode = i;
        reserve(opcode == 185 ? 5 : 3);
        int arg_count = method2.arg_types.length;
        boolean is_invokestatic = opcode == 184;
        boolean is_init = opcode == 183 && "<init>".equals(method2.getName());
        if (is_invokestatic != ((method2.access_flags & 8) != 0)) {
            Throwable th4 = th3;
            new StringBuilder();
            new Error(sb2.append("emitInvokeXxx static flag mis-match method.flags=").append(method2.access_flags).toString());
            throw th4;
        }
        if (!is_invokestatic && !is_init) {
            arg_count++;
        }
        put1(opcode);
        putIndex2(getConstants().addMethodRef(method2));
        if (opcode == 185) {
            put1(words(method2.arg_types) + 1);
            put1(0);
        }
        do {
            arg_count--;
            if (arg_count >= 0) {
                t = popType();
            } else {
                if (is_init) {
                    Type t2 = popType();
                    if (t2 instanceof UninitializedType) {
                        ClassType ctype = ((UninitializedType) t2).ctype;
                        for (int i2 = 0; i2 < this.f56SP; i2++) {
                            if (this.stack_types[i2] == t2) {
                                this.stack_types[i2] = ctype;
                            }
                        }
                        Variable[] used = this.locals.used;
                        int i3 = used == null ? 0 : used.length;
                        while (true) {
                            i3--;
                            if (i3 < 0) {
                                break;
                            }
                            Variable var = used[i3];
                            if (var != null && var.type == t2) {
                                var.type = ctype;
                            }
                        }
                        int i4 = this.local_types == null ? 0 : this.local_types.length;
                        while (true) {
                            i4--;
                            if (i4 < 0) {
                                break;
                            } else if (this.local_types[i4] == t2) {
                                this.local_types[i4] = ctype;
                            }
                        }
                    } else {
                        Throwable th5 = th;
                        new Error("calling <init> on already-initialized object");
                        throw th5;
                    }
                }
                if (method2.return_type.size != 0) {
                    pushType(method2.return_type);
                    return;
                }
                return;
            }
        } while (!(t instanceof UninitializedType));
        Throwable th6 = th2;
        new StringBuilder();
        new Error(sb.append("passing ").append(t).append(" as parameter").toString());
        throw th6;
    }

    public void emitInvoke(Method method) {
        int opcode;
        Method method2 = method;
        if ((method2.access_flags & 8) != 0) {
            opcode = 184;
        } else if (method2.classfile.isInterface()) {
            opcode = 185;
        } else if ("<init>".equals(method2.getName())) {
            opcode = 183;
        } else {
            opcode = 182;
        }
        emitInvokeMethod(method2, opcode);
    }

    public void emitInvokeVirtual(Method method) {
        emitInvokeMethod(method, 182);
    }

    public void emitInvokeSpecial(Method method) {
        emitInvokeMethod(method, 183);
    }

    public void emitInvokeStatic(Method method) {
        emitInvokeMethod(method, 184);
    }

    public void emitInvokeInterface(Method method) {
        emitInvokeMethod(method, 185);
    }

    /* access modifiers changed from: package-private */
    public final void emitTransfer(Label label, int opcode) {
        Label label2 = label;
        label2.setTypes(this);
        fixupAdd(6, label2);
        put1(opcode);
        this.f55PC += 2;
    }

    public final void emitGoto(Label label) {
        Label label2 = label;
        label2.setTypes(this);
        fixupAdd(4, label2);
        reserve(3);
        put1(167);
        this.f55PC += 2;
        setUnreachable();
    }

    public final void emitJsr(Label label) {
        fixupAdd(5, label);
        reserve(3);
        put1(168);
        this.f55PC += 2;
    }

    public ExitableBlock startExitableBlock(Type resultType, boolean runFinallyBlocks) {
        ExitableBlock exitableBlock;
        new ExitableBlock(resultType, this, runFinallyBlocks);
        ExitableBlock bl = exitableBlock;
        bl.outer = this.currentExitableBlock;
        this.currentExitableBlock = bl;
        return bl;
    }

    public void endExitableBlock() {
        ExitableBlock bl = this.currentExitableBlock;
        bl.finish();
        this.currentExitableBlock = bl.outer;
    }

    public final void emitGotoIfCompare1(Label label, int opcode) {
        Type popType = popType();
        reserve(3);
        emitTransfer(label, opcode);
    }

    public final void emitGotoIfIntEqZero(Label label) {
        emitGotoIfCompare1(label, 153);
    }

    public final void emitGotoIfIntNeZero(Label label) {
        emitGotoIfCompare1(label, 154);
    }

    public final void emitGotoIfIntLtZero(Label label) {
        emitGotoIfCompare1(label, 155);
    }

    public final void emitGotoIfIntGeZero(Label label) {
        emitGotoIfCompare1(label, 156);
    }

    public final void emitGotoIfIntGtZero(Label label) {
        emitGotoIfCompare1(label, 157);
    }

    public final void emitGotoIfIntLeZero(Label label) {
        emitGotoIfCompare1(label, 158);
    }

    public final void emitGotoIfCompare2(Label label, int i) {
        Throwable th;
        Throwable th2;
        Label label2 = label;
        int logop = i;
        if (logop < 153 || logop > 158) {
            Throwable th3 = th;
            new Error("emitGotoIfCompare2: logop must be one of ifeq...ifle");
            throw th3;
        }
        Type type2 = popType().promote();
        Type type1 = popType().promote();
        reserve(4);
        char sig1 = type1.getSignature().charAt(0);
        char sig2 = type2.getSignature().charAt(0);
        boolean cmpg = logop == 155 || logop == 158;
        if (sig1 == 'I' && sig2 == 'I') {
            logop += 6;
        } else if (sig1 == 'J' && sig2 == 'J') {
            put1(148);
        } else if (sig1 == 'F' && sig2 == 'F') {
            put1(cmpg ? 149 : 150);
        } else if (sig1 == 'D' && sig2 == 'D') {
            put1(cmpg ? 151 : 152);
        } else if ((sig1 == 'L' || sig1 == '[') && ((sig2 == 'L' || sig2 == '[') && logop <= 154)) {
            logop += 12;
        } else {
            Throwable th4 = th2;
            new Error("invalid types to emitGotoIfCompare2");
            throw th4;
        }
        emitTransfer(label2, logop);
    }

    public final void emitGotoIfEq(Label label, boolean invert) {
        emitGotoIfCompare2(label, invert ? 154 : 153);
    }

    public final void emitGotoIfEq(Label label) {
        emitGotoIfCompare2(label, 153);
    }

    public final void emitGotoIfNE(Label label) {
        emitGotoIfCompare2(label, 154);
    }

    public final void emitGotoIfLt(Label label) {
        emitGotoIfCompare2(label, 155);
    }

    public final void emitGotoIfGe(Label label) {
        emitGotoIfCompare2(label, 156);
    }

    public final void emitGotoIfGt(Label label) {
        emitGotoIfCompare2(label, 157);
    }

    public final void emitGotoIfLe(Label label) {
        emitGotoIfCompare2(label, 158);
    }

    public final void emitIfCompare1(int i) {
        IfState ifState;
        Throwable th;
        int opcode = i;
        new IfState(this);
        IfState new_if = ifState;
        if (popType().promote() != Type.intType) {
            Throwable th2 = th;
            new Error("non-int type to emitIfCompare1");
            throw th2;
        }
        reserve(3);
        emitTransfer(new_if.end_label, opcode);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfIntNotZero() {
        emitIfCompare1(153);
    }

    public final void emitIfIntEqZero() {
        emitIfCompare1(154);
    }

    public final void emitIfIntLEqZero() {
        emitIfCompare1(157);
    }

    public final void emitIfRefCompare1(int i) {
        IfState ifState;
        Throwable th;
        int opcode = i;
        new IfState(this);
        IfState new_if = ifState;
        if (!(popType() instanceof ObjectType)) {
            Throwable th2 = th;
            new Error("non-ref type to emitIfRefCompare1");
            throw th2;
        }
        reserve(3);
        emitTransfer(new_if.end_label, opcode);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfNotNull() {
        emitIfRefCompare1(198);
    }

    public final void emitIfNull() {
        emitIfRefCompare1(199);
    }

    public final void emitIfIntCompare(int opcode) {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        Type popType = popType();
        Type popType2 = popType();
        reserve(3);
        emitTransfer(new_if.end_label, opcode);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfIntLt() {
        emitIfIntCompare(162);
    }

    public final void emitIfNEq() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfEq(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfEq() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfNE(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfLt() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfGe(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfGe() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfLt(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfGt() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfLe(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public final void emitIfLe() {
        IfState ifState;
        new IfState(this);
        IfState new_if = ifState;
        emitGotoIfGt(new_if.end_label);
        new_if.start_stack_size = this.f56SP;
    }

    public void emitRet(Variable var) {
        int offset = var.offset;
        if (offset < 256) {
            reserve(2);
            put1(169);
            put1(offset);
            return;
        }
        reserve(4);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
        put1(169);
        put2(offset);
    }

    public final void emitThen() {
        this.if_stack.start_stack_size = this.f56SP;
    }

    public final void emitIfThen() {
        Object obj;
        Object obj2 = obj;
        new IfState(this, (Label) null);
    }

    public final void emitElse() {
        Label label;
        Label else_label = this.if_stack.end_label;
        if (reachableHere()) {
            new Label(this);
            Label end_label = label;
            this.if_stack.end_label = end_label;
            int growth = this.f56SP - this.if_stack.start_stack_size;
            this.if_stack.stack_growth = growth;
            if (growth > 0) {
                this.if_stack.then_stacked_types = new Type[growth];
                System.arraycopy(this.stack_types, this.if_stack.start_stack_size, this.if_stack.then_stacked_types, 0, growth);
            } else {
                this.if_stack.then_stacked_types = new Type[0];
            }
            emitGoto(end_label);
        } else {
            this.if_stack.end_label = null;
        }
        while (this.f56SP > this.if_stack.start_stack_size) {
            Type popType = popType();
        }
        this.f56SP = this.if_stack.start_stack_size;
        if (else_label != null) {
            else_label.define(this);
        }
        this.if_stack.doing_else = true;
    }

    public final void emitFi() {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        boolean make_unreachable = false;
        if (!this.if_stack.doing_else) {
            if (reachableHere() && this.f56SP != this.if_stack.start_stack_size) {
                Throwable th3 = th2;
                new StringBuilder();
                new Error(sb2.append("at PC ").append(this.f55PC).append(" then clause grows stack with no else clause").toString());
                throw th3;
            }
        } else if (this.if_stack.then_stacked_types != null) {
            int then_clause_stack_size = this.if_stack.start_stack_size + this.if_stack.stack_growth;
            if (!reachableHere()) {
                if (this.if_stack.stack_growth > 0) {
                    System.arraycopy(this.if_stack.then_stacked_types, 0, this.stack_types, this.if_stack.start_stack_size, this.if_stack.stack_growth);
                }
                this.f56SP = then_clause_stack_size;
            } else if (this.f56SP != then_clause_stack_size) {
                Throwable th4 = th;
                new StringBuilder();
                new Error(sb.append("at PC ").append(this.f55PC).append(": SP at end of 'then' was ").append(then_clause_stack_size).append(" while SP at end of 'else' was ").append(this.f56SP).toString());
                throw th4;
            }
        } else if (this.unreachable_here) {
            make_unreachable = true;
        }
        if (this.if_stack.end_label != null) {
            this.if_stack.end_label.define(this);
        }
        if (make_unreachable) {
            setUnreachable();
        }
        this.if_stack = this.if_stack.previous;
    }

    public final void emitConvert(Type type, Type type2) {
        Throwable th;
        Type from = type;
        Type to = type2;
        String to_sig = to.getSignature();
        String from_sig = from.getSignature();
        int op = -1;
        if (to_sig.length() == 1 || from_sig.length() == 1) {
            char to_sig0 = to_sig.charAt(0);
            char from_sig0 = from_sig.charAt(0);
            if (from_sig0 != to_sig0) {
                if (from.size < 4) {
                    from_sig0 = 'I';
                }
                if (to.size < 4) {
                    emitConvert(from, Type.intType);
                    from_sig0 = 'I';
                }
                if (from_sig0 != to_sig0) {
                    switch (from_sig0) {
                        case 'D':
                            switch (to_sig0) {
                                case 'F':
                                    op = 144;
                                    break;
                                case 'I':
                                    op = 142;
                                    break;
                                case 'J':
                                    op = 143;
                                    break;
                            }
                        case 'F':
                            switch (to_sig0) {
                                case 'D':
                                    op = 141;
                                    break;
                                case 'I':
                                    op = 139;
                                    break;
                                case 'J':
                                    op = 140;
                                    break;
                            }
                        case 'I':
                            switch (to_sig0) {
                                case 'B':
                                    op = 145;
                                    break;
                                case 'C':
                                    op = 146;
                                    break;
                                case 'D':
                                    op = 135;
                                    break;
                                case 'F':
                                    op = 134;
                                    break;
                                case 'J':
                                    op = 133;
                                    break;
                                case 'S':
                                    op = 147;
                                    break;
                            }
                        case 'J':
                            switch (to_sig0) {
                                case 'D':
                                    op = 138;
                                    break;
                                case 'F':
                                    op = 137;
                                    break;
                                case 'I':
                                    op = 136;
                                    break;
                            }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        if (op < 0) {
            Throwable th2 = th;
            new Error("unsupported CodeAttr.emitConvert");
            throw th2;
        }
        reserve(1);
        Type popType = popType();
        put1(op);
        pushType(to);
    }

    private void emitCheckcast(Type type, int opcode) {
        Throwable th;
        StringBuilder sb;
        Type type2 = type;
        reserve(3);
        Type popType = popType();
        put1(opcode);
        if (type2 instanceof ObjectType) {
            putIndex2(getConstants().addClass((ObjectType) type2));
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new Error(sb.append("unimplemented type ").append(type2).append(" in emitCheckcast/emitInstanceof").toString());
        throw th2;
    }

    public static boolean castNeeded(Type type, Type type2) {
        Type top = type;
        Type required = type2;
        if (top instanceof UninitializedType) {
            top = ((UninitializedType) top).getImplementationType();
        }
        while (top != required) {
            if ((required instanceof ClassType) && (top instanceof ClassType) && ((ClassType) top).isSubclass((ClassType) required)) {
                return false;
            }
            if (!(required instanceof ArrayType) || !(top instanceof ArrayType)) {
                return true;
            }
            required = ((ArrayType) required).getComponentType();
            top = ((ArrayType) top).getComponentType();
        }
        return false;
    }

    public void emitCheckcast(Type type) {
        Type type2 = type;
        if (castNeeded(topType(), type2)) {
            emitCheckcast(type2, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE);
            pushType(type2);
        }
    }

    public void emitInstanceof(Type type) {
        emitCheckcast(type, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP);
        pushType(Type.booleanType);
    }

    public final void emitThrow() {
        Type popType = popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY);
        setUnreachable();
    }

    public final void emitMonitorEnter() {
        Type popType = popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE);
    }

    public final void emitMonitorExit() {
        Type popType = popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN);
    }

    public final void emitReturn() {
        Object obj;
        if (this.try_stack != null) {
            Object obj2 = obj;
            new Error();
        }
        emitRawReturn();
    }

    /* access modifiers changed from: package-private */
    public final void emitRawReturn() {
        if (getMethod().getReturnType().size == 0) {
            reserve(1);
            put1(177);
        } else {
            emitTypedOp(172, popType().promote());
        }
        setUnreachable();
    }

    public void addHandler(int i, int i2, int i3, int i4) {
        int start_pc = i;
        int end_pc = i2;
        int handler_pc = i3;
        int catch_type = i4;
        int index = 4 * this.exception_table_length;
        if (this.exception_table == null) {
            this.exception_table = new short[20];
        } else if (this.exception_table.length <= index) {
            short[] new_table = new short[(2 * this.exception_table.length)];
            System.arraycopy(this.exception_table, 0, new_table, 0, index);
            this.exception_table = new_table;
        }
        int i5 = index;
        int index2 = index + 1;
        this.exception_table[i5] = (short) start_pc;
        int i6 = index2;
        int index3 = index2 + 1;
        this.exception_table[i6] = (short) end_pc;
        int i7 = index3;
        int index4 = index3 + 1;
        this.exception_table[i7] = (short) handler_pc;
        int i8 = index4;
        int index5 = index4 + 1;
        this.exception_table[i8] = (short) catch_type;
        this.exception_table_length++;
    }

    public void addHandler(Label label, Label label2, ClassType classType) {
        int catch_type_index;
        Label label3;
        Label start_try = label;
        Label end_try = label2;
        Type catch_type = classType;
        ConstantPool constants = getConstants();
        if (catch_type == null) {
            catch_type_index = 0;
        } else {
            catch_type_index = constants.addClass((ObjectType) catch_type).index;
        }
        fixupAdd(11, start_try);
        fixupAdd(12, catch_type_index, end_try);
        new Label();
        Label handler = label3;
        handler.localTypes = start_try.localTypes;
        handler.stackTypes = new Type[1];
        handler.stackTypes[0] = catch_type == null ? Type.javalangThrowableType : catch_type;
        setTypes(handler);
        fixupAdd(13, 0, handler);
    }

    public void emitWithCleanupStart() {
        int savedSP = this.f56SP;
        this.f56SP = 0;
        emitTryStart(false, (Type) null);
        this.f56SP = savedSP;
    }

    public void emitWithCleanupCatch(Variable variable) {
        Type[] savedTypes;
        Variable catchVar = variable;
        emitTryEnd();
        if (this.f56SP > 0) {
            savedTypes = new Type[this.f56SP];
            System.arraycopy(this.stack_types, 0, savedTypes, 0, this.f56SP);
            this.f56SP = 0;
        } else {
            savedTypes = null;
        }
        this.try_stack.savedTypes = savedTypes;
        this.try_stack.saved_result = catchVar;
        int i = this.f56SP;
        emitCatchStart(catchVar);
    }

    public void emitWithCleanupDone() {
        Variable catchVar = this.try_stack.saved_result;
        this.try_stack.saved_result = null;
        if (catchVar != null) {
            emitLoad(catchVar);
        }
        emitThrow();
        emitCatchEnd();
        Type[] savedTypes = this.try_stack.savedTypes;
        emitTryCatchEnd();
        if (savedTypes != null) {
            this.f56SP = savedTypes.length;
            if (this.f56SP >= this.stack_types.length) {
                this.stack_types = savedTypes;
                return;
            }
            System.arraycopy(savedTypes, 0, this.stack_types, 0, this.f56SP);
            return;
        }
        this.f56SP = 0;
    }

    public void emitTryStart(boolean z, Type type) {
        TryState tryState;
        Type[] startLocals;
        Label label;
        boolean has_finally = z;
        Type result_type = type;
        if (result_type != null && result_type.isVoid()) {
            result_type = null;
        }
        Variable[] savedStack = null;
        if (result_type != null || this.f56SP > 0) {
            Scope pushScope = pushScope();
        }
        if (this.f56SP > 0) {
            savedStack = new Variable[this.f56SP];
            int i = 0;
            while (this.f56SP > 0) {
                Variable var = addLocal(topType());
                emitStore(var);
                int i2 = i;
                i++;
                savedStack[i2] = var;
            }
        }
        new TryState(this);
        TryState try_state = tryState;
        try_state.savedStack = savedStack;
        int usedLocals = this.local_types == null ? 0 : this.local_types.length;
        while (usedLocals > 0 && this.local_types[usedLocals - 1] == null) {
            usedLocals--;
        }
        if (usedLocals == 0) {
            startLocals = Type.typeArray0;
        } else {
            startLocals = new Type[usedLocals];
            System.arraycopy(this.local_types, 0, startLocals, 0, usedLocals);
        }
        try_state.start_try.localTypes = startLocals;
        if (result_type != null) {
            try_state.saved_result = addLocal(result_type);
        }
        if (has_finally) {
            new Label();
            try_state.finally_subr = label;
        }
    }

    public void emitTryEnd() {
        emitTryEnd(false);
    }

    private void emitTryEnd(boolean z) {
        boolean fromFinally = z;
        if (!this.try_stack.tryClauseDone) {
            this.try_stack.tryClauseDone = true;
            if (this.try_stack.finally_subr != null) {
                this.try_stack.exception = addLocal(Type.javalangThrowableType);
            }
            gotoFinallyOrEnd(fromFinally);
            this.try_stack.end_try = getLabel();
        }
    }

    public void emitCatchStart(Variable variable) {
        Variable var = variable;
        emitTryEnd(false);
        setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
        if (this.try_stack.try_type != null) {
            emitCatchEnd();
        }
        ClassType type = var == null ? null : (ClassType) var.getType();
        this.try_stack.try_type = type;
        addHandler(this.try_stack.start_try, this.try_stack.end_try, type);
        if (var != null) {
            emitStore(var);
        }
    }

    public void emitCatchEnd() {
        gotoFinallyOrEnd(false);
        this.try_stack.try_type = null;
    }

    private void gotoFinallyOrEnd(boolean z) {
        Label label;
        boolean fromFinally = z;
        if (reachableHere()) {
            if (this.try_stack.saved_result != null) {
                emitStore(this.try_stack.saved_result);
            }
            if (this.try_stack.end_label == null) {
                TryState tryState = this.try_stack;
                new Label();
                tryState.end_label = label;
            }
            if (this.try_stack.finally_subr == null || useJsr()) {
                if (this.try_stack.finally_subr != null) {
                    emitJsr(this.try_stack.finally_subr);
                }
                emitGoto(this.try_stack.end_label);
                return;
            }
            if (this.try_stack.exitCases != null) {
                emitPushInt(0);
            }
            emitPushNull();
            if (!fromFinally) {
                emitGoto(this.try_stack.finally_subr);
            }
        }
    }

    public void emitFinallyStart() {
        Label endLabel;
        emitTryEnd(true);
        if (this.try_stack.try_type != null) {
            emitCatchEnd();
        }
        this.try_stack.end_try = getLabel();
        Scope pushScope = pushScope();
        if (useJsr()) {
            this.f56SP = 0;
            emitCatchStart((Variable) null);
            emitStore(this.try_stack.exception);
            emitJsr(this.try_stack.finally_subr);
            emitLoad(this.try_stack.exception);
            emitThrow();
        } else {
            setUnreachable();
            new Label(this);
            int fragment_cookie = beginFragment(endLabel);
            addHandler(this.try_stack.start_try, this.try_stack.end_try, Type.javalangThrowableType);
            if (this.try_stack.saved_result != null) {
                emitStoreDefaultValue(this.try_stack.saved_result);
            }
            if (this.try_stack.exitCases != null) {
                emitPushInt(-1);
                emitSwap();
            }
            emitGoto(this.try_stack.finally_subr);
            endFragment(fragment_cookie);
        }
        this.try_stack.finally_subr.define(this);
        if (useJsr()) {
            Type ret_addr_type = Type.objectType;
            this.try_stack.finally_ret_addr = addLocal(ret_addr_type);
            pushType(ret_addr_type);
            emitStore(this.try_stack.finally_ret_addr);
        }
    }

    public void emitFinallyEnd() {
        if (useJsr()) {
            emitRet(this.try_stack.finally_ret_addr);
        } else if (this.try_stack.end_label == null && this.try_stack.exitCases == null) {
            emitThrow();
        } else {
            emitStore(this.try_stack.exception);
            emitLoad(this.try_stack.exception);
            emitIfNotNull();
            emitLoad(this.try_stack.exception);
            emitThrow();
            emitElse();
            ExitableBlock exit = this.try_stack.exitCases;
            if (exit != null) {
                SwitchState sw = startSwitch();
                while (exit != null) {
                    ExitableBlock next = exit.nextCase;
                    exit.nextCase = null;
                    exit.currentTryState = null;
                    TryState nextTry = TryState.outerHandler(this.try_stack.previous, exit.initialTryState);
                    if (nextTry == exit.initialTryState) {
                        boolean addCaseGoto = sw.addCaseGoto(exit.switchCase, this, exit.endLabel);
                    } else {
                        boolean addCase = sw.addCase(exit.switchCase, this);
                        exit.exit(nextTry);
                    }
                    exit = next;
                }
                this.try_stack.exitCases = null;
                sw.addDefault(this);
                sw.finish(this);
            }
            emitFi();
            setUnreachable();
        }
        Scope popScope = popScope();
        this.try_stack.finally_subr = null;
    }

    public void emitTryCatchEnd() {
        if (this.try_stack.finally_subr != null) {
            emitFinallyEnd();
        }
        Variable[] vars = this.try_stack.savedStack;
        if (this.try_stack.end_label == null) {
            setUnreachable();
        } else {
            setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
            this.try_stack.end_label.define(this);
            if (vars != null) {
                int i = vars.length;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    Variable v = vars[i];
                    if (v != null) {
                        emitLoad(v);
                    }
                }
            }
            if (this.try_stack.saved_result != null) {
                emitLoad(this.try_stack.saved_result);
            }
        }
        if (!(this.try_stack.saved_result == null && vars == null)) {
            Scope popScope = popScope();
        }
        this.try_stack = this.try_stack.previous;
    }

    public final TryState getCurrentTry() {
        return this.try_stack;
    }

    public final boolean isInTry() {
        return this.try_stack != null;
    }

    public SwitchState startSwitch() {
        SwitchState switchState;
        new SwitchState(this);
        SwitchState sw = switchState;
        sw.switchValuePushed(this);
        return sw;
    }

    public void emitTailCall(boolean pop_args, Scope scope) {
        Scope scope2 = scope;
        if (pop_args) {
            Method meth = getMethod();
            int arg_slots = (meth.access_flags & 8) != 0 ? 0 : 1;
            int i = meth.arg_types.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                arg_slots += meth.arg_types[i].size > 4 ? 2 : 1;
            }
            int i2 = meth.arg_types.length;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                arg_slots -= meth.arg_types[i2].size > 4 ? 2 : 1;
                emitStore(this.locals.used[arg_slots]);
            }
        }
        emitGoto(scope2.start);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x049a, code lost:
        if (r15.stackTypes == null) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x04a4, code lost:
        if (r15.needsStackMapEntry == false) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x04a6, code lost:
        r12 = mergeLabels(r12, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x04b3, code lost:
        r13 = r13 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x04b6, code lost:
        r3 = r15.position - r8;
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = r2.code[r13];
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 8);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 & 255);
        r13 = r13 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0506, code lost:
        r3 = r15.position - r8;
        r16 = r2.code[r13];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x052a, code lost:
        if (r14 != 6) goto L_0x05b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x052c, code lost:
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = invert_opcode(r16);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = 0;
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = 8;
        r16 = -56;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0558, code lost:
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = r16;
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 24);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 16);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 8);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 & 255);
        r13 = r13 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x05b6, code lost:
        r16 = (byte) (r16 + com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.OR16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x05c4, code lost:
        r17 = (3 - r8) & 3;
        r18 = r8;
        r22 = r8;
        r8 = r8 + 1;
        r24 = r13;
        r13 = r13 + 1;
        r6[r22] = r2.code[r24];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x05ea, code lost:
        r17 = r17 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x05ee, code lost:
        if (r17 < 0) goto L_0x0665;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x05f0, code lost:
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x05fb, code lost:
        r3 = r2.fixup_labels[r9].position - r18;
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 24);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 16);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 >> 8);
        r22 = r8;
        r8 = r8 + 1;
        r6[r22] = (byte) (r3 & 255);
        r13 = r13 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0673, code lost:
        if (r9 >= r2.fixup_count) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0687, code lost:
        if (fixupKind(r9 + 1) != 3) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0689, code lost:
        r9 = r9 + 1;
        r19 = fixupOffset(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x069d, code lost:
        if (r13 >= r19) goto L_0x05fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x069f, code lost:
        r22 = r8;
        r8 = r8 + 1;
        r24 = r13;
        r13 = r13 + 1;
        r6[r22] = r2.code[r24];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x06b6, code lost:
        r15 = r2.fixup_labels[r9 + 2];
        r19 = fixupOffset(r9 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x06de, code lost:
        if (r2.stackMap == null) goto L_0x06ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x06e0, code lost:
        r12 = mergeLabels(r12, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x06ec, code lost:
        addHandler(r2.fixup_labels[r9].position, r2.fixup_labels[r9 + 1].position, r8, r19);
        r9 = r9 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0729, code lost:
        if (r2.lines != null) goto L_0x0740;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x072b, code lost:
        new gnu.bytecode.LineNumbersAttr(r2);
        r2.lines = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0740, code lost:
        r9 = r9 + 1;
        r20 = fixupOffset(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0754, code lost:
        if (r20 == r7) goto L_0x0765;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0756, code lost:
        r2.lines.put(r20, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0765, code lost:
        r7 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x076d, code lost:
        if (r15 != null) goto L_0x07ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0777, code lost:
        if (r5 == r8) goto L_0x07e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0779, code lost:
        r21 = r26;
        new java.lang.StringBuilder();
        new java.lang.Error(r26.append("PC confusion new_pc:").append(r8).append(" new_size:").append(r5).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x07ad, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x07ae, code lost:
        r9 = r15.first_fixup;
        r13 = fixupOffset(r9);
        r10 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x07d4, code lost:
        if (r15.position == r8) goto L_0x03b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x07d6, code lost:
        r21 = r26;
        new java.lang.Error("bad pc");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x07e4, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x07e5, code lost:
        r2.f55PC = r5;
        r2.code = r6;
        r2.fixup_count = 0;
        r2.fixup_labels = null;
        r2.fixup_offsets = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x013d, code lost:
        if ((r5 + 1) < r2.fixup_count) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x013f, code lost:
        r21 = r2.f55PC;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0147, code lost:
        r9 = r21;
        r2.fixup_offsets[r5] = (r9 << 4) | 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0161, code lost:
        if (r8 != null) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0163, code lost:
        r5 = r2.f55PC;
        r3 = 0;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0183, code lost:
        if (r6 >= r2.fixup_count) goto L_0x0389;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0185, code lost:
        r7 = r2.fixup_offsets[r6];
        r8 = r7 & 15;
        r9 = r2.fixup_labels[r6];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01ab, code lost:
        if (r9 == null) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01b5, code lost:
        if (r9.position >= 0) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01b7, code lost:
        r21 = r26;
        new java.lang.StringBuilder();
        new java.lang.Error(r26.append("undefined label ").append(r9).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01de, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01df, code lost:
        r21 = fixupOffset(r2.fixup_labels[r5 + 1].first_fixup);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01fd, code lost:
        r5 = r8.first_fixup;
        r3 = (r9 + r3) - fixupOffset(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0221, code lost:
        if (r9 == null) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x022b, code lost:
        if (r8 < 4) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0235, code lost:
        if (r8 > 7) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x024f, code lost:
        if ((r9.first_fixup + 1) >= r2.fixup_count) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0285, code lost:
        if (r2.fixup_offsets[r9.first_fixup + 1] != ((r2.fixup_offsets[r9.first_fixup] & 15) | 4)) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0287, code lost:
        r9 = r2.fixup_labels[r9.first_fixup + 1];
        r2.fixup_labels[r6] = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x02af, code lost:
        r7 = r7 >> 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x02b9, code lost:
        switch(r8) {
            case 0: goto L_0x02eb;
            case 1: goto L_0x02f1;
            case 2: goto L_0x0300;
            case 3: goto L_0x02eb;
            case 4: goto L_0x0321;
            case 5: goto L_0x0321;
            case 6: goto L_0x0321;
            case 7: goto L_0x02bc;
            case 8: goto L_0x02ec;
            case 9: goto L_0x0385;
            case 10: goto L_0x02bc;
            case 11: goto L_0x02cb;
            case 12: goto L_0x02bc;
            case 13: goto L_0x02bc;
            case 14: goto L_0x02e9;
            default: goto L_0x02bc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x02bc, code lost:
        r21 = r26;
        new java.lang.Error("unexpected fixup");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x02ca, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x02cb, code lost:
        r6 = r6 + 2;
        r2.fixup_labels[r6].position = r7 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x02e5, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x02e9, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x02ec, code lost:
        r3 = r3 - 3;
        r5 = r5 - 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x02f1, code lost:
        r9.position = r7 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0300, code lost:
        r10 = (3 - (r7 + r3)) & 3;
        r3 = r3 + r10;
        r5 = r5 + r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0321, code lost:
        r11 = r9.position - (r7 + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0340, code lost:
        if (((short) r11) != r11) goto L_0x0359;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0342, code lost:
        r2.fixup_offsets[r6] = (r7 << 4) | 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0359, code lost:
        r21 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0363, code lost:
        if (r8 != 6) goto L_0x037f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0365, code lost:
        r22 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0367, code lost:
        r3 = r21 + r22;
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0375, code lost:
        if (r8 != 6) goto L_0x0382;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0377, code lost:
        r22 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0379, code lost:
        r5 = r21 + r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x037f, code lost:
        r22 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0382, code lost:
        r22 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0387, code lost:
        if (r9 != null) goto L_0x03d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0389, code lost:
        r6 = new byte[r5];
        r7 = -1;
        r8 = 0;
        r9 = 0;
        r10 = fixupOffset(0);
        r12 = null;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x03bd, code lost:
        if (r13 >= r10) goto L_0x03f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x03bf, code lost:
        r22 = r8;
        r8 = r8 + 1;
        r24 = r13;
        r13 = r13 + 1;
        r6[r22] = r2.code[r24];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x03d6, code lost:
        r6 = r9.first_fixup;
        r3 = (r7 + r3) - fixupOffset(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x03f8, code lost:
        r14 = r2.fixup_offsets[r9] & 15;
        r15 = r2.fixup_labels[r9];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x041a, code lost:
        if (r12 == null) goto L_0x043f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x042a, code lost:
        if (r12.position >= r8) goto L_0x043f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x042c, code lost:
        r2.stackMap.emitStackMapEntry(r12, r2);
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0441, code lost:
        if (r12 == null) goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0451, code lost:
        if (r12.position <= r8) goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0453, code lost:
        r21 = r26;
        new java.lang.Error("labels out of order");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x006c, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0461, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0464, code lost:
        switch(r14) {
            case 0: goto L_0x0476;
            case 1: goto L_0x0484;
            case 2: goto L_0x05c4;
            case 3: goto L_0x0467;
            case 4: goto L_0x0506;
            case 5: goto L_0x0506;
            case 6: goto L_0x0506;
            case 7: goto L_0x04b6;
            case 8: goto L_0x04b3;
            case 9: goto L_0x076b;
            case 10: goto L_0x0467;
            case 11: goto L_0x06b6;
            case 12: goto L_0x0467;
            case 13: goto L_0x0467;
            case 14: goto L_0x0721;
            default: goto L_0x0467;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0467, code lost:
        r21 = r26;
        new java.lang.Error("unexpected fixup");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0475, code lost:
        throw r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0476, code lost:
        r9 = r9 + 1;
        r10 = fixupOffset(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x048c, code lost:
        if (r2.stackMap == null) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0490, code lost:
        if (r15 == null) goto L_0x0476;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processFixups() {
        /*
            r27 = this;
            r2 = r27
            r21 = r2
            r0 = r21
            int r0 = r0.fixup_count
            r21 = r0
            if (r21 > 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            r21 = 0
            r3 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.fixup_count
            r21 = r0
            r4 = r21
            r21 = r2
            r22 = 9
            r23 = 0
            r24 = 0
            r21.fixupAdd(r22, r23, r24)
            r21 = 0
            r5 = r21
        L_0x002a:
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r5
            r21 = r21[r22]
            r6 = r21
            r21 = r6
            r22 = 15
            r21 = r21 & 15
            r7 = r21
            r21 = r6
            r22 = 4
            int r21 = r21 >> 4
            r6 = r21
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r5
            r21 = r21[r22]
            r8 = r21
            r21 = r7
            switch(r21) {
                case 0: goto L_0x0071;
                case 1: goto L_0x0072;
                case 2: goto L_0x008b;
                case 3: goto L_0x0071;
                case 4: goto L_0x008e;
                case 5: goto L_0x00e1;
                case 6: goto L_0x00f6;
                case 7: goto L_0x005b;
                case 8: goto L_0x0071;
                case 9: goto L_0x012b;
                case 10: goto L_0x010b;
                case 11: goto L_0x006a;
                case 12: goto L_0x005b;
                case 13: goto L_0x005b;
                case 14: goto L_0x006f;
                default: goto L_0x005b;
            }
        L_0x005b:
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.String r23 = "unexpected fixup"
            r22.<init>(r23)
            throw r21
        L_0x006a:
            int r5 = r5 + 2
        L_0x006c:
            int r5 = r5 + 1
            goto L_0x002a
        L_0x006f:
            int r5 = r5 + 1
        L_0x0071:
            goto L_0x006c
        L_0x0072:
            r21 = r8
            r26 = r21
            r21 = r26
            r22 = r26
            r0 = r22
            int r0 = r0.position
            r22 = r0
            r23 = r3
            int r22 = r22 + r23
            r0 = r22
            r1 = r21
            r1.position = r0
            goto L_0x006c
        L_0x008b:
            int r3 = r3 + 3
            goto L_0x006c
        L_0x008e:
            r21 = r8
            r0 = r21
            int r0 = r0.first_fixup
            r21 = r0
            r22 = r5
            r23 = 1
            int r22 = r22 + 1
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x00e1
            r21 = r2
            r22 = r5
            r23 = 1
            int r22 = r22 + 1
            int r21 = r21.fixupOffset(r22)
            r22 = r6
            r23 = 3
            int r22 = r22 + 3
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x00e1
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r5
            r23 = r6
            r24 = 4
            int r23 = r23 << 4
            r24 = 8
            r23 = r23 | 8
            r21[r22] = r23
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r5
            r23 = 0
            r21[r22] = r23
            int r3 = r3 + -3
            goto L_0x006c
        L_0x00e1:
            r21 = r2
            r0 = r21
            int r0 = r0.f55PC
            r21 = r0
            r22 = 32768(0x8000, float:4.5918E-41)
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x006c
            int r3 = r3 + 2
            goto L_0x006c
        L_0x00f6:
            r21 = r2
            r0 = r21
            int r0 = r0.f55PC
            r21 = r0
            r22 = 32768(0x8000, float:4.5918E-41)
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x006c
            int r3 = r3 + 5
            goto L_0x006c
        L_0x010b:
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r4
            r23 = r2
            r0 = r23
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r23 = r0
            r24 = r5
            r25 = 1
            int r24 = r24 + 1
            r23 = r23[r24]
            r21[r22] = r23
            r21 = r6
            r4 = r21
        L_0x012b:
            r21 = r5
            r22 = 1
            int r21 = r21 + 1
            r22 = r2
            r0 = r22
            int r0 = r0.fixup_count
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x01df
            r21 = r2
            r0 = r21
            int r0 = r0.f55PC
            r21 = r0
        L_0x0147:
            r9 = r21
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r5
            r23 = r9
            r24 = 4
            int r23 = r23 << 4
            r24 = 9
            r23 = r23 | 9
            r21[r22] = r23
            r21 = r8
            if (r21 != 0) goto L_0x01fd
            r21 = r2
            r0 = r21
            int r0 = r0.f55PC
            r21 = r0
            r5 = r21
            r21 = 0
            r3 = r21
            r21 = 0
            r6 = r21
        L_0x0175:
            r21 = r6
            r22 = r2
            r0 = r22
            int r0 = r0.fixup_count
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0389
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r6
            r21 = r21[r22]
            r7 = r21
            r21 = r7
            r22 = 15
            r21 = r21 & 15
            r8 = r21
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r6
            r21 = r21[r22]
            r9 = r21
            r21 = r9
            if (r21 == 0) goto L_0x021f
            r21 = r9
            r0 = r21
            int r0 = r0.position
            r21 = r0
            if (r21 >= 0) goto L_0x021f
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r26 = r23
            r23 = r26
            r24 = r26
            r24.<init>()
            java.lang.String r24 = "undefined label "
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r9
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r23 = r23.toString()
            r22.<init>(r23)
            throw r21
        L_0x01df:
            r21 = r2
            r22 = r2
            r0 = r22
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r22 = r0
            r23 = r5
            r24 = 1
            int r23 = r23 + 1
            r22 = r22[r23]
            r0 = r22
            int r0 = r0.first_fixup
            r22 = r0
            int r21 = r21.fixupOffset(r22)
            goto L_0x0147
        L_0x01fd:
            r21 = r8
            r0 = r21
            int r0 = r0.first_fixup
            r21 = r0
            r5 = r21
            r21 = r2
            r22 = r5
            int r21 = r21.fixupOffset(r22)
            r10 = r21
            r21 = r9
            r22 = r3
            int r21 = r21 + r22
            r22 = r10
            int r21 = r21 - r22
            r3 = r21
            goto L_0x002a
        L_0x021f:
            r21 = r9
            if (r21 == 0) goto L_0x02af
            r21 = r8
            r22 = 4
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x02af
            r21 = r8
            r22 = 7
            r0 = r21
            r1 = r22
            if (r0 > r1) goto L_0x02af
            r21 = r9
            r0 = r21
            int r0 = r0.first_fixup
            r21 = r0
            r22 = 1
            int r21 = r21 + 1
            r22 = r2
            r0 = r22
            int r0 = r0.fixup_count
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x02af
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r9
            r0 = r22
            int r0 = r0.first_fixup
            r22 = r0
            r23 = 1
            int r22 = r22 + 1
            r21 = r21[r22]
            r22 = r2
            r0 = r22
            int[] r0 = r0.fixup_offsets
            r22 = r0
            r23 = r9
            r0 = r23
            int r0 = r0.first_fixup
            r23 = r0
            r22 = r22[r23]
            r23 = 15
            r22 = r22 & 15
            r23 = 4
            r22 = r22 | 4
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02af
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r9
            r0 = r22
            int r0 = r0.first_fixup
            r22 = r0
            r23 = 1
            int r22 = r22 + 1
            r21 = r21[r22]
            r9 = r21
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r6
            r23 = r9
            r21[r22] = r23
            goto L_0x021f
        L_0x02af:
            r21 = r7
            r22 = 4
            int r21 = r21 >> 4
            r7 = r21
            r21 = r8
            switch(r21) {
                case 0: goto L_0x02eb;
                case 1: goto L_0x02f1;
                case 2: goto L_0x0300;
                case 3: goto L_0x02eb;
                case 4: goto L_0x0321;
                case 5: goto L_0x0321;
                case 6: goto L_0x0321;
                case 7: goto L_0x02bc;
                case 8: goto L_0x02ec;
                case 9: goto L_0x0385;
                case 10: goto L_0x02bc;
                case 11: goto L_0x02cb;
                case 12: goto L_0x02bc;
                case 13: goto L_0x02bc;
                case 14: goto L_0x02e9;
                default: goto L_0x02bc;
            }
        L_0x02bc:
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.String r23 = "unexpected fixup"
            r22.<init>(r23)
            throw r21
        L_0x02cb:
            int r6 = r6 + 2
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r6
            r21 = r21[r22]
            r22 = r7
            r23 = r3
            int r22 = r22 + r23
            r0 = r22
            r1 = r21
            r1.position = r0
        L_0x02e5:
            int r6 = r6 + 1
            goto L_0x0175
        L_0x02e9:
            int r6 = r6 + 1
        L_0x02eb:
            goto L_0x02e5
        L_0x02ec:
            int r3 = r3 + -3
            int r5 = r5 + -3
            goto L_0x02e5
        L_0x02f1:
            r21 = r9
            r22 = r7
            r23 = r3
            int r22 = r22 + r23
            r0 = r22
            r1 = r21
            r1.position = r0
            goto L_0x02e5
        L_0x0300:
            r21 = 3
            r22 = r7
            r23 = r3
            int r22 = r22 + r23
            int r21 = 3 - r22
            r22 = 3
            r21 = r21 & 3
            r10 = r21
            r21 = r3
            r22 = r10
            int r21 = r21 + r22
            r3 = r21
            r21 = r5
            r22 = r10
            int r21 = r21 + r22
            r5 = r21
            goto L_0x02e5
        L_0x0321:
            r21 = r9
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r7
            r23 = r3
            int r22 = r22 + r23
            int r21 = r21 - r22
            r11 = r21
            r21 = r11
            r0 = r21
            short r0 = (short) r0
            r21 = r0
            r22 = r11
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0359
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r6
            r23 = r7
            r24 = 4
            int r23 = r23 << 4
            r24 = 7
            r23 = r23 | 7
            r21[r22] = r23
            goto L_0x02e5
        L_0x0359:
            r21 = r3
            r22 = r8
            r23 = 6
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x037f
            r22 = 5
        L_0x0367:
            int r21 = r21 + r22
            r3 = r21
            r21 = r5
            r22 = r8
            r23 = 6
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x0382
            r22 = 5
        L_0x0379:
            int r21 = r21 + r22
            r5 = r21
            goto L_0x02e5
        L_0x037f:
            r22 = 2
            goto L_0x0367
        L_0x0382:
            r22 = 2
            goto L_0x0379
        L_0x0385:
            r21 = r9
            if (r21 != 0) goto L_0x03d6
        L_0x0389:
            r21 = r5
            r0 = r21
            byte[] r0 = new byte[r0]
            r21 = r0
            r6 = r21
            r21 = -1
            r7 = r21
            r21 = 0
            r8 = r21
            r21 = 0
            r9 = r21
            r21 = r2
            r22 = 0
            int r21 = r21.fixupOffset(r22)
            r10 = r21
            r21 = -1
            r11 = r21
            r21 = 0
            r12 = r21
            r21 = 0
            r13 = r21
        L_0x03b5:
            r21 = r13
            r22 = r10
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x03f8
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r2
            r0 = r23
            byte[] r0 = r0.code
            r23 = r0
            r24 = r13
            int r13 = r13 + 1
            byte r23 = r23[r24]
            r21[r22] = r23
            goto L_0x03b5
        L_0x03d6:
            r21 = r9
            r0 = r21
            int r0 = r0.first_fixup
            r21 = r0
            r6 = r21
            r21 = r2
            r22 = r6
            int r21 = r21.fixupOffset(r22)
            r12 = r21
            r21 = r7
            r22 = r3
            int r21 = r21 + r22
            r22 = r12
            int r21 = r21 - r22
            r3 = r21
            goto L_0x0175
        L_0x03f8:
            r21 = r2
            r0 = r21
            int[] r0 = r0.fixup_offsets
            r21 = r0
            r22 = r9
            r21 = r21[r22]
            r22 = 15
            r21 = r21 & 15
            r14 = r21
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r9
            r21 = r21[r22]
            r15 = r21
            r21 = r12
            if (r21 == 0) goto L_0x043f
            r21 = r12
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r8
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x043f
            r21 = r2
            r0 = r21
            gnu.bytecode.StackMapTableAttr r0 = r0.stackMap
            r21 = r0
            r22 = r12
            r23 = r2
            r21.emitStackMapEntry(r22, r23)
            r21 = 0
            r12 = r21
        L_0x043f:
            r21 = r12
            if (r21 == 0) goto L_0x0462
            r21 = r12
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r8
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x0462
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.String r23 = "labels out of order"
            r22.<init>(r23)
            throw r21
        L_0x0462:
            r21 = r14
            switch(r21) {
                case 0: goto L_0x0476;
                case 1: goto L_0x0484;
                case 2: goto L_0x05c4;
                case 3: goto L_0x0467;
                case 4: goto L_0x0506;
                case 5: goto L_0x0506;
                case 6: goto L_0x0506;
                case 7: goto L_0x04b6;
                case 8: goto L_0x04b3;
                case 9: goto L_0x076b;
                case 10: goto L_0x0467;
                case 11: goto L_0x06b6;
                case 12: goto L_0x0467;
                case 13: goto L_0x0467;
                case 14: goto L_0x0721;
                default: goto L_0x0467;
            }
        L_0x0467:
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.String r23 = "unexpected fixup"
            r22.<init>(r23)
            throw r21
        L_0x0476:
            int r9 = r9 + 1
            r21 = r2
            r22 = r9
            int r21 = r21.fixupOffset(r22)
            r10 = r21
            goto L_0x03b5
        L_0x0484:
            r21 = r2
            r0 = r21
            gnu.bytecode.StackMapTableAttr r0 = r0.stackMap
            r21 = r0
            if (r21 == 0) goto L_0x0476
            r21 = r15
            if (r21 == 0) goto L_0x0476
            r21 = r15
            r0 = r21
            gnu.bytecode.Type[] r0 = r0.stackTypes
            r21 = r0
            if (r21 == 0) goto L_0x0476
            r21 = r15
            r0 = r21
            boolean r0 = r0.needsStackMapEntry
            r21 = r0
            if (r21 == 0) goto L_0x0476
            r21 = r2
            r22 = r12
            r23 = r15
            gnu.bytecode.Label r21 = r21.mergeLabels(r22, r23)
            r12 = r21
            goto L_0x0476
        L_0x04b3:
            int r13 = r13 + 3
            goto L_0x0476
        L_0x04b6:
            r21 = r15
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r8
            int r21 = r21 - r22
            r3 = r21
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r2
            r0 = r23
            byte[] r0 = r0.code
            r23 = r0
            r24 = r13
            byte r23 = r23[r24]
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 8
            int r23 = r23 >> 8
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 255(0xff, float:3.57E-43)
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            int r13 = r13 + 3
            goto L_0x0476
        L_0x0506:
            r21 = r15
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r8
            int r21 = r21 - r22
            r3 = r21
            r21 = r2
            r0 = r21
            byte[] r0 = r0.code
            r21 = r0
            r22 = r13
            byte r21 = r21[r22]
            r16 = r21
            r21 = r14
            r22 = 6
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x05b6
            r21 = r2
            r22 = r16
            byte r21 = r21.invert_opcode(r22)
            r16 = r21
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r16
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = 0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = 8
            r21[r22] = r23
            r21 = -56
            r16 = r21
        L_0x0558:
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r16
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 24
            int r23 = r23 >> 24
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 16
            int r23 = r23 >> 16
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 8
            int r23 = r23 >> 8
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 255(0xff, float:3.57E-43)
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            int r13 = r13 + 3
            goto L_0x0476
        L_0x05b6:
            r21 = r16
            r22 = 33
            int r21 = r21 + 33
            r0 = r21
            byte r0 = (byte) r0
            r21 = r0
            r16 = r21
            goto L_0x0558
        L_0x05c4:
            r21 = 3
            r22 = r8
            int r21 = 3 - r22
            r22 = 3
            r21 = r21 & 3
            r17 = r21
            r21 = r8
            r18 = r21
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r2
            r0 = r23
            byte[] r0 = r0.code
            r23 = r0
            r24 = r13
            int r13 = r13 + 1
            byte r23 = r23[r24]
            r21[r22] = r23
        L_0x05ea:
            int r17 = r17 + -1
            r21 = r17
            if (r21 < 0) goto L_0x0665
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = 0
            r21[r22] = r23
            goto L_0x05ea
        L_0x05fb:
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r9
            r21 = r21[r22]
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r18
            int r21 = r21 - r22
            r3 = r21
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 24
            int r23 = r23 >> 24
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 16
            int r23 = r23 >> 16
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 8
            int r23 = r23 >> 8
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r3
            r24 = 255(0xff, float:3.57E-43)
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            r21[r22] = r23
            int r13 = r13 + 4
        L_0x0665:
            r21 = r9
            r22 = r2
            r0 = r22
            int r0 = r0.fixup_count
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0476
            r21 = r2
            r22 = r9
            r23 = 1
            int r22 = r22 + 1
            int r21 = r21.fixupKind(r22)
            r22 = 3
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0476
            int r9 = r9 + 1
            r21 = r2
            r22 = r9
            int r21 = r21.fixupOffset(r22)
            r19 = r21
        L_0x0695:
            r21 = r13
            r22 = r19
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x05fb
            r21 = r6
            r22 = r8
            int r8 = r8 + 1
            r23 = r2
            r0 = r23
            byte[] r0 = r0.code
            r23 = r0
            r24 = r13
            int r13 = r13 + 1
            byte r23 = r23[r24]
            r21[r22] = r23
            goto L_0x0695
        L_0x06b6:
            r21 = r2
            r0 = r21
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r21 = r0
            r22 = r9
            r23 = 2
            int r22 = r22 + 2
            r21 = r21[r22]
            r15 = r21
            r21 = r2
            r22 = r9
            r23 = 1
            int r22 = r22 + 1
            int r21 = r21.fixupOffset(r22)
            r19 = r21
            r21 = r2
            r0 = r21
            gnu.bytecode.StackMapTableAttr r0 = r0.stackMap
            r21 = r0
            if (r21 == 0) goto L_0x06ec
            r21 = r2
            r22 = r12
            r23 = r15
            gnu.bytecode.Label r21 = r21.mergeLabels(r22, r23)
            r12 = r21
        L_0x06ec:
            r21 = r2
            r22 = r2
            r0 = r22
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r22 = r0
            r23 = r9
            r22 = r22[r23]
            r0 = r22
            int r0 = r0.position
            r22 = r0
            r23 = r2
            r0 = r23
            gnu.bytecode.Label[] r0 = r0.fixup_labels
            r23 = r0
            r24 = r9
            r25 = 1
            int r24 = r24 + 1
            r23 = r23[r24]
            r0 = r23
            int r0 = r0.position
            r23 = r0
            r24 = r8
            r25 = r19
            r21.addHandler(r22, r23, r24, r25)
            int r9 = r9 + 2
            goto L_0x0476
        L_0x0721:
            r21 = r2
            r0 = r21
            gnu.bytecode.LineNumbersAttr r0 = r0.lines
            r21 = r0
            if (r21 != 0) goto L_0x0740
            r21 = r2
            gnu.bytecode.LineNumbersAttr r22 = new gnu.bytecode.LineNumbersAttr
            r26 = r22
            r22 = r26
            r23 = r26
            r24 = r2
            r23.<init>(r24)
            r0 = r22
            r1 = r21
            r1.lines = r0
        L_0x0740:
            int r9 = r9 + 1
            r21 = r2
            r22 = r9
            int r21 = r21.fixupOffset(r22)
            r20 = r21
            r21 = r20
            r22 = r7
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0765
            r21 = r2
            r0 = r21
            gnu.bytecode.LineNumbersAttr r0 = r0.lines
            r21 = r0
            r22 = r20
            r23 = r8
            r21.put(r22, r23)
        L_0x0765:
            r21 = r20
            r7 = r21
            goto L_0x0476
        L_0x076b:
            r21 = r15
            if (r21 != 0) goto L_0x07ae
            r21 = r5
            r22 = r8
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x07e5
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r26 = r23
            r23 = r26
            r24 = r26
            r24.<init>()
            java.lang.String r24 = "PC confusion new_pc:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r8
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r24 = " new_size:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r5
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r23 = r23.toString()
            r22.<init>(r23)
            throw r21
        L_0x07ae:
            r21 = r15
            r0 = r21
            int r0 = r0.first_fixup
            r21 = r0
            r9 = r21
            r21 = r2
            r22 = r9
            int r21 = r21.fixupOffset(r22)
            r13 = r21
            r21 = r13
            r10 = r21
            r21 = r15
            r0 = r21
            int r0 = r0.position
            r21 = r0
            r22 = r8
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x03b5
            java.lang.Error r21 = new java.lang.Error
            r26 = r21
            r21 = r26
            r22 = r26
            java.lang.String r23 = "bad pc"
            r22.<init>(r23)
            throw r21
        L_0x07e5:
            r21 = r2
            r22 = r5
            r0 = r22
            r1 = r21
            r1.f55PC = r0
            r21 = r2
            r22 = r6
            r0 = r22
            r1 = r21
            r1.code = r0
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.fixup_count = r0
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.fixup_labels = r0
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.fixup_offsets = r0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.processFixups():void");
    }

    private Label mergeLabels(Label label, Label label2) {
        Label oldLabel = label;
        Label newLabel = label2;
        if (oldLabel != null) {
            newLabel.setTypes(oldLabel);
        }
        return newLabel;
    }

    public void assignConstants(ClassType classType) {
        LineNumbersAttr lineNumbersAttr;
        ClassType cl = classType;
        if (this.locals != null && this.locals.container == null && !this.locals.isEmpty()) {
            this.locals.addToFrontOf(this);
        }
        processFixups();
        if (this.stackMap != null && this.stackMap.numEntries > 0) {
            this.stackMap.addToFrontOf(this);
        }
        if (instructionLineMode) {
            if (this.lines == null) {
                new LineNumbersAttr(this);
                this.lines = lineNumbersAttr;
            }
            this.lines.linenumber_count = 0;
            int codeLen = getCodeLength();
            for (int i = 0; i < codeLen; i++) {
                this.lines.put(i, i);
            }
        }
        super.assignConstants(cl);
        Attribute.assignConstants(this, cl);
    }

    public final int getLength() {
        return 12 + getCodeLength() + (8 * this.exception_table_length) + Attribute.getLengthAll(this);
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(this.max_stack);
        dstr.writeShort(this.max_locals);
        dstr.writeInt(this.f55PC);
        dstr.write(this.code, 0, this.f55PC);
        dstr.writeShort(this.exception_table_length);
        int count = this.exception_table_length;
        int i = 0;
        while (true) {
            count--;
            if (count >= 0) {
                dstr.writeShort(this.exception_table[i]);
                dstr.writeShort(this.exception_table[i + 1]);
                dstr.writeShort(this.exception_table[i + 2]);
                dstr.writeShort(this.exception_table[i + 3]);
                i += 4;
            } else {
                Attribute.writeAll(this, dstr);
                return;
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", max_stack:");
        dst.print(this.max_stack);
        dst.print(", max_locals:");
        dst.print(this.max_locals);
        dst.print(", code_length:");
        int length = getCodeLength();
        dst.println(length);
        disAssemble(dst, 0, length);
        if (this.exception_table_length > 0) {
            dst.print("Exceptions (count: ");
            dst.print(this.exception_table_length);
            dst.println("):");
            int count = this.exception_table_length;
            int i = 0;
            while (true) {
                count--;
                if (count < 0) {
                    break;
                }
                dst.print("  start: ");
                dst.print(this.exception_table[i] & 65535);
                dst.print(", end: ");
                dst.print(this.exception_table[i + 1] & 65535);
                dst.print(", handler: ");
                dst.print(this.exception_table[i + 2] & 65535);
                dst.print(", type: ");
                int catch_type_index = this.exception_table[i + 3] & 65535;
                if (catch_type_index == 0) {
                    dst.print("0 /* finally */");
                } else {
                    dst.printOptionalIndex(catch_type_index);
                    dst.printConstantTersely(catch_type_index, 7);
                }
                dst.println();
                i += 4;
            }
        }
        dst.printAttributes(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v29, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v178, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disAssemble(gnu.bytecode.ClassTypeWriter r21, int r22, int r23) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r15 = 0
            r5 = r15
            r15 = r3
            r6 = r15
        L_0x000c:
            r15 = r6
            r16 = r4
            r0 = r16
            if (r15 >= r0) goto L_0x06e6
            r15 = r6
            int r6 = r6 + 1
            r7 = r15
            r15 = r1
            byte[] r15 = r15.code
            r16 = r7
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            r8 = r15
            r15 = r7
            java.lang.String r15 = java.lang.Integer.toString(r15)
            r9 = r15
            r15 = 0
            r10 = r15
            r15 = r9
            int r15 = r15.length()
            r11 = r15
        L_0x0031:
            int r11 = r11 + 1
            r15 = r11
            r16 = 3
            r0 = r16
            if (r15 > r0) goto L_0x0041
            r15 = r2
            r16 = 32
            r15.print(r16)
            goto L_0x0031
        L_0x0041:
            r15 = r2
            r16 = r9
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = ": "
            r15.print(r16)
            r15 = r8
            r16 = 120(0x78, float:1.68E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0225
            r15 = r8
            r16 = 87
            r0 = r16
            if (r15 >= r0) goto L_0x01e3
            r15 = r8
            r16 = 3
            r0 = r16
            if (r15 >= r0) goto L_0x0097
            r15 = r1
            java.lang.String r16 = "nop;aconst_null;iconst_m1;"
            r17 = r8
            r18 = r2
            r15.print(r16, r17, r18)
        L_0x006e:
            r15 = r10
            if (r15 <= 0) goto L_0x0091
            r15 = r10
            r16 = 1
            r0 = r16
            if (r15 != r0) goto L_0x06da
            r15 = 255(0xff, float:3.57E-43)
            r16 = r1
            r0 = r16
            byte[] r0 = r0.code
            r16 = r0
            r17 = r6
            int r6 = r6 + 1
            byte r16 = r16[r17]
            r15 = r15 & r16
            r12 = r15
        L_0x008b:
            r15 = r2
            r16 = r12
            r15.printConstantOperand(r16)
        L_0x0091:
            r15 = r2
            r15.println()
            goto L_0x000c
        L_0x0097:
            r15 = r8
            r16 = 9
            r0 = r16
            if (r15 >= r0) goto L_0x00b0
            r15 = r2
            java.lang.String r16 = "iconst_"
            r15.print(r16)
            r15 = r2
            r16 = r8
            r17 = 3
            int r16 = r16 + -3
            r15.print(r16)
            goto L_0x006e
        L_0x00b0:
            r15 = r8
            r16 = 16
            r0 = r16
            if (r15 >= r0) goto L_0x00ea
            r15 = r8
            r16 = 11
            r0 = r16
            if (r15 >= r0) goto L_0x00d7
            r15 = 108(0x6c, float:1.51E-43)
            r12 = r15
            int r8 = r8 + -9
        L_0x00c3:
            r15 = r2
            r16 = r12
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = "const_"
            r15.print(r16)
            r15 = r2
            r16 = r8
            r15.print(r16)
            goto L_0x006e
        L_0x00d7:
            r15 = r8
            r16 = 14
            r0 = r16
            if (r15 >= r0) goto L_0x00e4
            r15 = 102(0x66, float:1.43E-43)
            r12 = r15
            int r8 = r8 + -11
            goto L_0x00c3
        L_0x00e4:
            r15 = 100
            r12 = r15
            int r8 = r8 + -14
            goto L_0x00c3
        L_0x00ea:
            r15 = r8
            r16 = 21
            r0 = r16
            if (r15 >= r0) goto L_0x0148
            r15 = r8
            r16 = 18
            r0 = r16
            if (r15 >= r0) goto L_0x012c
            r15 = r1
            java.lang.String r16 = "bipush ;sipush ;"
            r17 = r8
            r18 = 16
            int r17 = r17 + -16
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = r8
            r16 = 16
            r0 = r16
            if (r15 != r0) goto L_0x0120
            r15 = r1
            byte[] r15 = r15.code
            r16 = r6
            int r6 = r6 + 1
            byte r15 = r15[r16]
            r12 = r15
        L_0x0118:
            r15 = r2
            r16 = r12
            r15.print(r16)
            goto L_0x006e
        L_0x0120:
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            short r15 = (short) r15
            r12 = r15
            int r6 = r6 + 2
            goto L_0x0118
        L_0x012c:
            r15 = r8
            r16 = 18
            r0 = r16
            if (r15 != r0) goto L_0x0146
            r15 = 1
        L_0x0134:
            r10 = r15
            r15 = r1
            java.lang.String r16 = "ldc;ldc_w;ldc2_w;"
            r17 = r8
            r18 = 18
            int r17 = r17 + -18
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x006e
        L_0x0146:
            r15 = 2
            goto L_0x0134
        L_0x0148:
            r15 = r8
            r16 = 54
            r0 = r16
            if (r15 >= r0) goto L_0x018f
            java.lang.String r15 = "load"
            r12 = r15
        L_0x0153:
            r15 = r8
            r16 = 26
            r0 = r16
            if (r15 >= r0) goto L_0x0196
            r15 = -1
            r13 = r15
            int r8 = r8 + -21
        L_0x015e:
            r15 = r2
            java.lang.String r16 = "ilfdabcs"
            r17 = r8
            char r16 = r16.charAt(r17)
            r15.print(r16)
            r15 = r13
            r16 = -2
            r0 = r16
            if (r15 != r0) goto L_0x0178
            r15 = r2
            r16 = 97
            r15.write(r16)
        L_0x0178:
            r15 = r2
            r16 = r12
            r15.print(r16)
            r15 = r13
            if (r15 < 0) goto L_0x01b1
            r15 = r2
            r16 = 95
            r15.write(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
        L_0x018d:
            goto L_0x006e
        L_0x018f:
            java.lang.String r15 = "store"
            r12 = r15
            int r8 = r8 + -33
            goto L_0x0153
        L_0x0196:
            r15 = r8
            r16 = 46
            r0 = r16
            if (r15 >= r0) goto L_0x01ac
            int r8 = r8 + -26
            r15 = r8
            r16 = 4
            int r15 = r15 % 4
            r13 = r15
            r15 = r8
            r16 = 2
            int r15 = r15 >> 2
            r8 = r15
            goto L_0x015e
        L_0x01ac:
            r15 = -2
            r13 = r15
            int r8 = r8 + -46
            goto L_0x015e
        L_0x01b1:
            r15 = r13
            r16 = -1
            r0 = r16
            if (r15 != r0) goto L_0x018d
            r15 = r5
            if (r15 == 0) goto L_0x01d4
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r13 = r15
            int r6 = r6 + 2
        L_0x01c5:
            r15 = 0
            r5 = r15
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            goto L_0x018d
        L_0x01d4:
            r15 = r1
            byte[] r15 = r15.code
            r16 = r6
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            r13 = r15
            int r6 = r6 + 1
            goto L_0x01c5
        L_0x01e3:
            r15 = r8
            r16 = 96
            r0 = r16
            if (r15 >= r0) goto L_0x01fb
            r15 = r1
            java.lang.String r16 = "pop;pop2;dup;dup_x1;dup_x2;dup2;dup2_x1;dup2_x2;swap;"
            r17 = r8
            r18 = 87
            int r17 = r17 + -87
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x006e
        L_0x01fb:
            r15 = r2
            java.lang.String r16 = "ilfda"
            r17 = r8
            r18 = 96
            int r17 = r17 + -96
            r18 = 4
            int r17 = r17 % 4
            char r16 = r16.charAt(r17)
            r15.print(r16)
            r15 = r1
            java.lang.String r16 = "add;sub;mul;div;rem;neg;"
            r17 = r8
            r18 = 96
            int r17 = r17 + -96
            r18 = 2
            int r17 = r17 >> 2
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x006e
        L_0x0225:
            r15 = r8
            r16 = 170(0xaa, float:2.38E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x03cb
            r15 = r8
            r16 = 132(0x84, float:1.85E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0259
            r15 = r2
            r16 = r8
            r17 = 1
            r16 = r16 & 1
            if (r16 != 0) goto L_0x0256
            r16 = 105(0x69, float:1.47E-43)
        L_0x023e:
            r15.print(r16)
            r15 = r1
            java.lang.String r16 = "shl;shr;ushr;and;or;xor;"
            r17 = r8
            r18 = 120(0x78, float:1.68E-43)
            int r17 = r17 + -120
            r18 = 1
            int r17 = r17 >> 1
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x006e
        L_0x0256:
            r16 = 108(0x6c, float:1.51E-43)
            goto L_0x023e
        L_0x0259:
            r15 = r8
            r16 = 132(0x84, float:1.85E-43)
            r0 = r16
            if (r15 != r0) goto L_0x02b9
            r15 = r2
            java.lang.String r16 = "iinc"
            r15.print(r16)
            r15 = r5
            if (r15 != 0) goto L_0x02a1
            r15 = 255(0xff, float:3.57E-43)
            r16 = r1
            r0 = r16
            byte[] r0 = r0.code
            r16 = r0
            r17 = r6
            int r6 = r6 + 1
            byte r16 = r16[r17]
            r15 = r15 & r16
            r12 = r15
            r15 = r1
            byte[] r15 = r15.code
            r16 = r6
            int r6 = r6 + 1
            byte r15 = r15[r16]
            r13 = r15
        L_0x0287:
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r12
            r15.print(r16)
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            goto L_0x006e
        L_0x02a1:
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r12 = r15
            int r6 = r6 + 2
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            short r15 = (short) r15
            r13 = r15
            int r6 = r6 + 2
            r15 = 0
            r5 = r15
            goto L_0x0287
        L_0x02b9:
            r15 = r8
            r16 = 148(0x94, float:2.07E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x02f6
            r15 = r2
            java.lang.String r16 = "ilfdi"
            r17 = r8
            r18 = 133(0x85, float:1.86E-43)
            r0 = r17
            int r0 = r0 + -133
            r17 = r0
            r18 = 3
            int r17 = r17 / 3
            char r16 = r16.charAt(r17)
            r15.print(r16)
            r15 = r2
            r16 = 50
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = "lfdifdildilfbcs"
            r17 = r8
            r18 = 133(0x85, float:1.86E-43)
            r0 = r17
            int r0 = r0 + -133
            r17 = r0
            char r16 = r16.charAt(r17)
            r15.print(r16)
            goto L_0x006e
        L_0x02f6:
            r15 = r8
            r16 = 153(0x99, float:2.14E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0312
            r15 = r1
            java.lang.String r16 = "lcmp;fcmpl;fcmpg;dcmpl;dcmpg;"
            r17 = r8
            r18 = 148(0x94, float:2.07E-43)
            r0 = r17
            int r0 = r0 + -148
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x006e
        L_0x0312:
            r15 = r8
            r16 = 169(0xa9, float:2.37E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x039e
            r15 = r8
            r16 = 159(0x9f, float:2.23E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0357
            r15 = r2
            java.lang.String r16 = "if"
            r15.print(r16)
            r15 = r1
            java.lang.String r16 = "eq;ne;lt;ge;gt;le;"
            r17 = r8
            r18 = 153(0x99, float:2.14E-43)
            r0 = r17
            int r0 = r0 + -153
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
        L_0x033a:
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            short r15 = (short) r15
            r12 = r15
            int r6 = r6 + 2
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
            goto L_0x006e
        L_0x0357:
            r15 = r8
            r16 = 167(0xa7, float:2.34E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x038a
            r15 = r8
            r16 = 165(0xa5, float:2.31E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0380
            r15 = r2
            java.lang.String r16 = "if_icmp"
            r15.print(r16)
        L_0x036c:
            r15 = r1
            java.lang.String r16 = "eq;ne;lt;ge;gt;le;"
            r17 = r8
            r18 = 159(0x9f, float:2.23E-43)
            r0 = r17
            int r0 = r0 + -159
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x033a
        L_0x0380:
            r15 = r2
            java.lang.String r16 = "if_acmp"
            r15.print(r16)
            int r8 = r8 + -6
            goto L_0x036c
        L_0x038a:
            r15 = r1
            java.lang.String r16 = "goto;jsr;"
            r17 = r8
            r18 = 167(0xa7, float:2.34E-43)
            r0 = r17
            int r0 = r0 + -167
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            goto L_0x033a
        L_0x039e:
            r15 = r2
            java.lang.String r16 = "ret "
            r15.print(r16)
            r15 = r5
            if (r15 == 0) goto L_0x03bc
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r12 = r15
            int r12 = r12 + 2
        L_0x03b2:
            r15 = 0
            r5 = r15
            r15 = r2
            r16 = r12
            r15.print(r16)
            goto L_0x006e
        L_0x03bc:
            r15 = r1
            byte[] r15 = r15.code
            r16 = r6
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            r12 = r15
            int r6 = r6 + 1
            goto L_0x03b2
        L_0x03cb:
            r15 = r8
            r16 = 172(0xac, float:2.41E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x04db
            r15 = r1
            int r15 = r15.fixup_count
            if (r15 > 0) goto L_0x03e1
            r15 = r6
            r16 = 3
            int r15 = r15 + 3
            r16 = -4
            r15 = r15 & -4
            r6 = r15
        L_0x03e1:
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r12 = r15
            int r6 = r6 + 4
            r15 = r8
            r16 = 170(0xaa, float:2.38E-43)
            r0 = r16
            if (r15 != r0) goto L_0x0470
            r15 = r2
            java.lang.String r16 = "tableswitch"
            r15.print(r16)
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r13 = r15
            int r6 = r6 + 4
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r14 = r15
            int r6 = r6 + 4
            r15 = r2
            java.lang.String r16 = " low: "
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = " high: "
            r15.print(r16)
            r15 = r2
            r16 = r14
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = " default: "
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
        L_0x0438:
            r15 = r13
            r16 = r14
            r0 = r16
            if (r15 > r0) goto L_0x046e
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r12 = r15
            int r6 = r6 + 4
            r15 = r2
            r15.println()
            r15 = r2
            java.lang.String r16 = "  "
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = ": "
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
            int r13 = r13 + 1
            goto L_0x0438
        L_0x046e:
            goto L_0x006e
        L_0x0470:
            r15 = r2
            java.lang.String r16 = "lookupswitch"
            r15.print(r16)
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r13 = r15
            int r6 = r6 + 4
            r15 = r2
            java.lang.String r16 = " npairs: "
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = " default: "
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
        L_0x049f:
            int r13 = r13 + -1
            r15 = r13
            if (r15 < 0) goto L_0x046e
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r14 = r15
            int r6 = r6 + 4
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r12 = r15
            int r6 = r6 + 4
            r15 = r2
            r15.println()
            r15 = r2
            java.lang.String r16 = "  "
            r15.print(r16)
            r15 = r2
            r16 = r14
            r15.print(r16)
            r15 = r2
            java.lang.String r16 = ": "
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
            goto L_0x049f
        L_0x04db:
            r15 = r8
            r16 = 178(0xb2, float:2.5E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0507
            r15 = r8
            r16 = 177(0xb1, float:2.48E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x04fe
            r15 = r2
            java.lang.String r16 = "ilfda"
            r17 = r8
            r18 = 172(0xac, float:2.41E-43)
            r0 = r17
            int r0 = r0 + -172
            r17 = r0
            char r16 = r16.charAt(r17)
            r15.print(r16)
        L_0x04fe:
            r15 = r2
            java.lang.String r16 = "return"
            r15.print(r16)
            goto L_0x006e
        L_0x0507:
            r15 = r8
            r16 = 182(0xb6, float:2.55E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0525
            r15 = r1
            java.lang.String r16 = "getstatic;putstatic;getfield;putfield;"
            r17 = r8
            r18 = 178(0xb2, float:2.5E-43)
            r0 = r17
            int r0 = r0 + -178
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = 2
            r10 = r15
            goto L_0x006e
        L_0x0525:
            r15 = r8
            r16 = 185(0xb9, float:2.59E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x054a
            r15 = r2
            java.lang.String r16 = "invoke"
            r15.print(r16)
            r15 = r1
            java.lang.String r16 = "virtual;special;static;"
            r17 = r8
            r18 = 182(0xb6, float:2.55E-43)
            r0 = r17
            int r0 = r0 + -182
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = 2
            r10 = r15
            goto L_0x006e
        L_0x054a:
            r15 = r8
            r16 = 185(0xb9, float:2.59E-43)
            r0 = r16
            if (r15 != r0) goto L_0x059d
            r15 = r2
            java.lang.String r16 = "invokeinterface ("
            r15.print(r16)
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r12 = r15
            int r6 = r6 + 2
            r15 = 255(0xff, float:3.57E-43)
            r16 = r1
            r0 = r16
            byte[] r0 = r0.code
            r16 = r0
            r17 = r6
            byte r16 = r16[r17]
            r15 = r15 & r16
            r13 = r15
            int r6 = r6 + 2
            r15 = r2
            java.lang.StringBuilder r16 = new java.lang.StringBuilder
            r19 = r16
            r16 = r19
            r17 = r19
            r17.<init>()
            r17 = r13
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = " args)"
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r16 = r16.toString()
            r15.print(r16)
            r15 = r2
            r16 = r12
            r15.printConstantOperand(r16)
            goto L_0x006e
        L_0x059d:
            r15 = r8
            r16 = 196(0xc4, float:2.75E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x0614
            r15 = r1
            java.lang.String r16 = "186;new;newarray;anewarray;arraylength;athrow;checkcast;instanceof;monitorenter;monitorexit;"
            r17 = r8
            r18 = 186(0xba, float:2.6E-43)
            r0 = r17
            int r0 = r0 + -186
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = r8
            r16 = 187(0xbb, float:2.62E-43)
            r0 = r16
            if (r15 == r0) goto L_0x05d3
            r15 = r8
            r16 = 189(0xbd, float:2.65E-43)
            r0 = r16
            if (r15 == r0) goto L_0x05d3
            r15 = r8
            r16 = 192(0xc0, float:2.69E-43)
            r0 = r16
            if (r15 == r0) goto L_0x05d3
            r15 = r8
            r16 = 193(0xc1, float:2.7E-43)
            r0 = r16
            if (r15 != r0) goto L_0x05d7
        L_0x05d3:
            r15 = 2
            r10 = r15
            goto L_0x006e
        L_0x05d7:
            r15 = r8
            r16 = 188(0xbc, float:2.63E-43)
            r0 = r16
            if (r15 != r0) goto L_0x006e
            r15 = r1
            byte[] r15 = r15.code
            r16 = r6
            int r6 = r6 + 1
            byte r15 = r15[r16]
            r12 = r15
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r12
            r16 = 4
            r0 = r16
            if (r15 < r0) goto L_0x060d
            r15 = r12
            r16 = 11
            r0 = r16
            if (r15 > r0) goto L_0x060d
            r15 = r1
            java.lang.String r16 = "boolean;char;float;double;byte;short;int;long;"
            r17 = r12
            r18 = 4
            int r17 = r17 + -4
            r18 = r2
            r15.print(r16, r17, r18)
        L_0x060b:
            goto L_0x006e
        L_0x060d:
            r15 = r2
            r16 = r12
            r15.print(r16)
            goto L_0x060b
        L_0x0614:
            r15 = r8
            r16 = 196(0xc4, float:2.75E-43)
            r0 = r16
            if (r15 != r0) goto L_0x0626
            r15 = r2
            java.lang.String r16 = "wide"
            r15.print(r16)
            r15 = 1
            r5 = r15
            goto L_0x006e
        L_0x0626:
            r15 = r8
            r16 = 197(0xc5, float:2.76E-43)
            r0 = r16
            if (r15 != r0) goto L_0x0665
            r15 = r2
            java.lang.String r16 = "multianewarray"
            r15.print(r16)
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r12 = r15
            int r6 = r6 + 2
            r15 = r2
            r16 = r12
            r15.printConstantOperand(r16)
            r15 = 255(0xff, float:3.57E-43)
            r16 = r1
            r0 = r16
            byte[] r0 = r0.code
            r16 = r0
            r17 = r6
            int r6 = r6 + 1
            byte r16 = r16[r17]
            r15 = r15 & r16
            r13 = r15
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r13
            r15.print(r16)
            goto L_0x006e
        L_0x0665:
            r15 = r8
            r16 = 200(0xc8, float:2.8E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x069c
            r15 = r1
            java.lang.String r16 = "ifnull;ifnonnull;"
            r17 = r8
            r18 = 198(0xc6, float:2.77E-43)
            r0 = r17
            int r0 = r0 + -198
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            short r15 = (short) r15
            r12 = r15
            int r6 = r6 + 2
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
            goto L_0x006e
        L_0x069c:
            r15 = r8
            r16 = 202(0xca, float:2.83E-43)
            r0 = r16
            if (r15 >= r0) goto L_0x06d2
            r15 = r1
            java.lang.String r16 = "goto_w;jsr_w;"
            r17 = r8
            r18 = 200(0xc8, float:2.8E-43)
            r0 = r17
            int r0 = r0 + -200
            r17 = r0
            r18 = r2
            r15.print(r16, r17, r18)
            r15 = r1
            r16 = r6
            int r15 = r15.readInt(r16)
            r12 = r15
            int r6 = r6 + 4
            r15 = r2
            r16 = 32
            r15.print(r16)
            r15 = r2
            r16 = r7
            r17 = r12
            int r16 = r16 + r17
            r15.print(r16)
            goto L_0x006e
        L_0x06d2:
            r15 = r2
            r16 = r8
            r15.print(r16)
            goto L_0x006e
        L_0x06da:
            r15 = r1
            r16 = r6
            int r15 = r15.readUnsignedShort(r16)
            r12 = r15
            int r6 = r6 + 2
            goto L_0x008b
        L_0x06e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.disAssemble(gnu.bytecode.ClassTypeWriter, int, int):void");
    }

    private int readUnsignedShort(int i) {
        int offset = i;
        return ((255 & this.code[offset]) << 8) | (255 & this.code[offset + 1]);
    }

    private int readInt(int i) {
        int offset = i;
        return (readUnsignedShort(offset) << 16) | readUnsignedShort(offset + 2);
    }

    private void print(String str, int i, PrintWriter printWriter) {
        String str2 = str;
        PrintWriter dst = printWriter;
        int last = 0;
        int pos = -1;
        for (int i2 = i; i2 >= 0; i2--) {
            last = pos + 1;
            pos = str2.indexOf(59, last);
        }
        dst.write(str2, last, pos - last);
    }

    public int beginFragment(Label after) {
        Label label;
        new Label();
        return beginFragment(label, after);
    }

    public int beginFragment(Label start, Label after) {
        int i = this.fixup_count;
        fixupAdd(10, after);
        start.define(this);
        return i;
    }

    public void endFragment(int i) {
        int cookie = i;
        this.fixup_offsets[cookie] = (this.fixup_count << 4) | 10;
        Label after = this.fixup_labels[cookie];
        fixupAdd(9, 0, (Label) null);
        after.define(this);
    }
}
