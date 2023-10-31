package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Stack;

public class Method implements AttrContainer, Member {
    int access_flags;
    Type[] arg_types;
    Attribute attributes;
    ClassType classfile;
    CodeAttr code;
    ExceptionsAttr exceptions;
    private String name;
    int name_index;
    Method next;
    Type return_type;
    String signature;
    int signature_index;

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attributes2) {
        Attribute attribute = attributes2;
        this.attributes = attribute;
    }

    public final ExceptionsAttr getExceptionAttr() {
        return this.exceptions;
    }

    public void setExceptions(short[] sArr) {
        ExceptionsAttr exceptionsAttr;
        short[] exn_indices = sArr;
        if (this.exceptions == null) {
            new ExceptionsAttr(this);
            this.exceptions = exceptionsAttr;
        }
        this.exceptions.setExceptions(exn_indices, this.classfile);
    }

    public void setExceptions(ClassType[] classTypeArr) {
        ExceptionsAttr exceptionsAttr;
        ClassType[] exn_types = classTypeArr;
        if (this.exceptions == null) {
            new ExceptionsAttr(this);
            this.exceptions = exceptionsAttr;
        }
        this.exceptions.setExceptions(exn_types);
    }

    public final CodeAttr getCode() {
        return this.code;
    }

    private Method() {
    }

    public static Method makeCloneMethod(Type returnType) {
        Method method;
        new Method();
        Method method2 = method;
        method2.name = "clone";
        method2.access_flags = 1;
        method2.arg_types = Type.typeArray0;
        method2.return_type = returnType;
        method2.classfile = Type.pointer_type;
        return method2;
    }

    public Method(Method method, ClassType clas) {
        Method base = method;
        this.arg_types = base.arg_types;
        this.return_type = base.return_type;
        this.name = base.name;
        this.access_flags = base.access_flags;
        this.classfile = clas;
    }

    Method(ClassType classType, int i) {
        ClassType clfile = classType;
        int flags = i;
        if (clfile.last_method == null) {
            clfile.methods = this;
        } else {
            clfile.last_method.next = this;
        }
        clfile.last_method = this;
        clfile.methods_count++;
        this.access_flags = flags;
        this.classfile = clfile;
    }

    public final void setStaticFlag(boolean is_static) {
        if (is_static) {
            this.access_flags |= 8;
            return;
        }
        this.access_flags ^= -9;
    }

    public final boolean getStaticFlag() {
        return (this.access_flags & 8) != 0;
    }

    public final boolean isAbstract() {
        return (this.access_flags & 1024) != 0;
    }

    public int getModifiers() {
        return this.access_flags;
    }

    public void setModifiers(int modifiers) {
        int i = modifiers;
        this.access_flags = i;
    }

    public final ConstantPool getConstants() {
        return this.classfile.constants;
    }

    public Scope pushScope() {
        prepareCode(0);
        return this.code.pushScope();
    }

    public final boolean reachableHere() {
        return this.code.reachableHere();
    }

    public Scope popScope() {
        return this.code.popScope();
    }

    public void allocate_local(Variable local) {
        local.allocateLocal(this.code);
    }

    public void initCode() {
        ConstantPool constantPool;
        if (this.classfile.constants == null) {
            ClassType classType = this.classfile;
            new ConstantPool();
            classType.constants = constantPool;
        }
        prepareCode(0);
        this.code.sourceDbgExt = this.classfile.sourceDbgExt;
        this.code.noteParamTypes();
        Scope pushScope = this.code.pushScope();
    }

    public void init_param_slots() {
        CodeAttr startCode = startCode();
    }

    public CodeAttr startCode() {
        initCode();
        this.code.addParamLocals();
        return this.code;
    }

    /* access modifiers changed from: package-private */
    public void kill_local(Variable var) {
        var.freeLocal(this.code);
    }

    /* access modifiers changed from: package-private */
    public void prepareCode(int i) {
        CodeAttr codeAttr;
        int max_size = i;
        if (this.code == null) {
            new CodeAttr(this);
            this.code = codeAttr;
        }
        this.code.reserve(max_size);
    }

    /* access modifiers changed from: package-private */
    public void instruction_start_hook(int max_size) {
        prepareCode(max_size);
    }

    /* access modifiers changed from: package-private */
    public final Type pop_stack_type() {
        return this.code.popType();
    }

    /* access modifiers changed from: package-private */
    public final void push_stack_type(Type type) {
        this.code.pushType(type);
    }

    public void compile_checkcast(Type type) {
        this.code.emitCheckcast(type);
    }

    public void maybe_compile_checkcast(Type type) {
        Type type2 = type;
        if (type2 != this.code.topType()) {
            this.code.emitCheckcast(type2);
        }
    }

    public void push_var(Variable var) {
        this.code.emitLoad(var);
    }

    public void compile_push_value(Variable var) {
        this.code.emitLoad(var);
    }

    public void compile_store_value(Variable var) {
        this.code.emitStore(var);
    }

    public void compile_push_this() {
        this.code.emitPushThis();
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream, ClassType classType) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        ClassType classType2 = classType;
        dstr.writeShort(this.access_flags);
        dstr.writeShort(this.name_index);
        dstr.writeShort(this.signature_index);
        Attribute.writeAll(this, dstr);
    }

    public static String makeSignature(Type[] typeArr, Type type) {
        StringBuilder sb;
        Type[] arg_types2 = typeArr;
        Type return_type2 = type;
        new StringBuilder(100);
        StringBuilder buf = sb;
        int args_count = arg_types2.length;
        StringBuilder append = buf.append('(');
        for (int i = 0; i < args_count; i++) {
            StringBuilder append2 = buf.append(arg_types2[i].getSignature());
        }
        StringBuilder append3 = buf.append(')');
        StringBuilder append4 = buf.append(return_type2.getSignature());
        return buf.toString();
    }

    public String getSignature() {
        if (this.signature == null) {
            this.signature = makeSignature(this.arg_types, this.return_type);
        }
        return this.signature;
    }

    public void setSignature(String str) {
        Throwable th;
        Stack stack;
        Throwable th2;
        String signature2 = str;
        int len = signature2.length();
        if (len < 3 || signature2.charAt(0) != '(') {
            Throwable th3 = th;
            new ClassFormatError("bad method signature");
            throw th3;
        }
        int pos = 1;
        new Stack();
        Stack stack2 = stack;
        while (true) {
            int arg_sig_len = Type.signatureLength(signature2, pos);
            if (arg_sig_len < 0) {
                break;
            }
            Object push = stack2.push(Type.signatureToType(signature2, pos, arg_sig_len));
            pos += arg_sig_len;
        }
        if (pos >= len || signature2.charAt(pos) != ')') {
            Throwable th4 = th2;
            new ClassFormatError("bad method signature");
            throw th4;
        }
        this.arg_types = new Type[stack2.size()];
        int i = stack2.size();
        while (true) {
            i--;
            if (i >= 0) {
                this.arg_types[i] = (Type) stack2.pop();
            } else {
                this.return_type = Type.signatureToType(signature2, pos + 1, (len - pos) - 1);
                return;
            }
        }
    }

    public void setSignature(int i) {
        int signature_index2 = i;
        CpoolUtf8 sigConstant = (CpoolUtf8) getConstants().getForced(signature_index2, 1);
        this.signature_index = signature_index2;
        setSignature(sigConstant.string);
    }

    /* access modifiers changed from: package-private */
    public void assignConstants() {
        ConstantPool constants = getConstants();
        if (this.name_index == 0 && this.name != null) {
            this.name_index = constants.addUtf8(this.name).index;
        }
        if (this.signature_index == 0) {
            this.signature_index = constants.addUtf8(getSignature()).index;
        }
        Attribute.assignConstants(this, this.classfile);
    }

    public ClassType getDeclaringClass() {
        return this.classfile;
    }

    public final Type getReturnType() {
        return this.return_type;
    }

    public final Type[] getParameterTypes() {
        return this.arg_types;
    }

    public final ClassType[] getExceptions() {
        if (this.exceptions == null) {
            return null;
        }
        return this.exceptions.getExceptions();
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name2) {
        String str = name2;
        this.name = str;
    }

    public final void setName(int i) {
        int name_index2 = i;
        if (name_index2 <= 0) {
            this.name = null;
        } else {
            this.name = ((CpoolUtf8) getConstants().getForced(name_index2, 1)).string;
        }
        this.name_index = name_index2;
    }

    public final Method getNext() {
        return this.next;
    }

    public void listParameters(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        int args_count = this.arg_types.length;
        StringBuffer append = sbuf.append('(');
        for (int i = 0; i < args_count; i++) {
            if (i > 0) {
                StringBuffer append2 = sbuf.append(',');
            }
            StringBuffer append3 = sbuf.append(this.arg_types[i].getName());
        }
        StringBuffer append4 = sbuf.append(')');
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(getDeclaringClass().getName());
        StringBuffer append2 = sbuf.append('.');
        StringBuffer append3 = sbuf.append(this.name);
        if (this.arg_types != null) {
            listParameters(sbuf);
            StringBuffer append4 = sbuf.append(this.return_type.getName());
        }
        return sbuf.toString();
    }

    public void cleanupAfterCompilation() {
        this.attributes = null;
        this.exceptions = null;
        this.code = null;
    }
}
