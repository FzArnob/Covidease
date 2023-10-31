package gnu.bytecode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ClassFileInput extends DataInputStream {
    ClassType ctype;
    InputStream str;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassFileInput(InputStream str2) throws IOException {
        super(str2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassFileInput(ClassType classType, InputStream str2) throws IOException, ClassFormatError {
        super(str2);
        Throwable th;
        ClassType ctype2 = classType;
        this.ctype = ctype2;
        if (!readHeader()) {
            Throwable th2 = th;
            new ClassFormatError("invalid magic number");
            throw th2;
        }
        ctype2.constants = readConstants();
        readClassInfo();
        readFields();
        readMethods();
        int readAttributes = readAttributes(ctype2);
    }

    public static ClassType readClassType(InputStream str2) throws IOException, ClassFormatError {
        ClassType classType;
        Object obj;
        new ClassType();
        ClassType ctype2 = classType;
        Object obj2 = obj;
        new ClassFileInput(ctype2, str2);
        return ctype2;
    }

    public boolean readHeader() throws IOException {
        if (readInt() != -889275714) {
            return false;
        }
        readFormatVersion();
        return true;
    }

    public void readFormatVersion() throws IOException {
        this.ctype.classfileFormatVersion = (readUnsignedShort() * 65536) + readUnsignedShort();
    }

    public ConstantPool readConstants() throws IOException {
        ConstantPool constantPool;
        new ConstantPool(this);
        return constantPool;
    }

    public void readClassInfo() throws IOException {
        StringBuilder sb;
        this.ctype.access_flags = readUnsignedShort();
        this.ctype.thisClassIndex = readUnsignedShort();
        String name = getClassConstant(this.ctype.thisClassIndex).name.string;
        this.ctype.this_name = name.replace('/', '.');
        ClassType classType = this.ctype;
        new StringBuilder();
        classType.setSignature(sb.append("L").append(name).append(";").toString());
        this.ctype.superClassIndex = readUnsignedShort();
        if (this.ctype.superClassIndex == 0) {
            this.ctype.setSuper((ClassType) null);
        } else {
            this.ctype.setSuper(getClassConstant(this.ctype.superClassIndex).name.string.replace('/', '.'));
        }
        int nInterfaces = readUnsignedShort();
        if (nInterfaces > 0) {
            this.ctype.interfaces = new ClassType[nInterfaces];
            this.ctype.interfaceIndexes = new int[nInterfaces];
            for (int i = 0; i < nInterfaces; i++) {
                int index = readUnsignedShort();
                this.ctype.interfaceIndexes[i] = index;
                this.ctype.interfaces[i] = ClassType.make(((CpoolClass) this.ctype.constants.getForced(index, 7)).name.string.replace('/', '.'));
            }
        }
    }

    public int readAttributes(AttrContainer attrContainer) throws IOException {
        AttrContainer container = attrContainer;
        int count = readUnsignedShort();
        Attribute last = container.getAttributes();
        for (int i = 0; i < count; i++) {
            if (last != null) {
                while (true) {
                    Attribute next = last.getNext();
                    if (next == null) {
                        break;
                    }
                    last = next;
                }
            }
            int index = readUnsignedShort();
            CpoolUtf8 nameConstant = (CpoolUtf8) this.ctype.constants.getForced(index, 1);
            int length = readInt();
            nameConstant.intern();
            Attribute attr = readAttribute(nameConstant.string, length, container);
            if (attr != null) {
                if (attr.getNameIndex() == 0) {
                    attr.setNameIndex(index);
                }
                if (last == null) {
                    container.setAttributes(attr);
                } else {
                    if (container.getAttributes() == attr) {
                        container.setAttributes(attr.getNext());
                        attr.setNext((Attribute) null);
                    }
                    last.setNext(attr);
                }
                last = attr;
            }
        }
        return count;
    }

    public final void skipAttribute(int i) throws IOException {
        Throwable th;
        int length = i;
        int i2 = 0;
        while (true) {
            int read = i2;
            if (read < length) {
                int skipped = (int) skip((long) (length - read));
                if (skipped == 0) {
                    if (read() < 0) {
                        Throwable th2 = th;
                        new EOFException("EOF while reading class files attributes");
                        throw th2;
                    }
                    skipped = 1;
                }
                i2 = read + skipped;
            } else {
                return;
            }
        }
    }

    public Attribute readAttribute(String str2, int i, AttrContainer attrContainer) throws IOException {
        Attribute attribute;
        SourceDebugExtAttr sourceDebugExtAttr;
        Attribute attribute2;
        Attribute attribute3;
        Attribute attribute4;
        Attribute attribute5;
        Attribute attribute6;
        Attribute attribute7;
        LocalVarsAttr localVarsAttr;
        Variable variable;
        Scope scope;
        Label label;
        Label label2;
        Label label3;
        Attribute attribute8;
        CodeAttr codeAttr;
        Attribute attribute9;
        String name = str2;
        int length = i;
        AttrContainer container = attrContainer;
        if (name == "SourceFile" && (container instanceof ClassType)) {
            new SourceFileAttr(readUnsignedShort(), (ClassType) container);
            return attribute9;
        } else if (name == "Code" && (container instanceof Method)) {
            new CodeAttr((Method) container);
            CodeAttr code = codeAttr;
            code.fixup_count = -1;
            code.setMaxStack(readUnsignedShort());
            code.setMaxLocals(readUnsignedShort());
            byte[] insns = new byte[readInt()];
            readFully(insns);
            code.setCode(insns);
            int exception_table_length = readUnsignedShort();
            for (int i2 = 0; i2 < exception_table_length; i2++) {
                code.addHandler(readUnsignedShort(), readUnsignedShort(), readUnsignedShort(), readUnsignedShort());
            }
            int readAttributes = readAttributes(code);
            return code;
        } else if (name == "LineNumberTable" && (container instanceof CodeAttr)) {
            int count = 2 * readUnsignedShort();
            short[] numbers = new short[count];
            for (int i3 = 0; i3 < count; i3++) {
                numbers[i3] = readShort();
            }
            new LineNumbersAttr(numbers, (CodeAttr) container);
            return attribute8;
        } else if (name == "LocalVariableTable" && (container instanceof CodeAttr)) {
            CodeAttr code2 = (CodeAttr) container;
            new LocalVarsAttr(code2);
            LocalVarsAttr attr = localVarsAttr;
            Method method = attr.getMethod();
            if (attr.parameter_scope == null) {
                attr.parameter_scope = method.pushScope();
            }
            Scope scope2 = attr.parameter_scope;
            if (scope2.end == null) {
                new Label(code2.f55PC);
                scope2.end = label3;
            }
            ConstantPool constants = method.getConstants();
            int count2 = readUnsignedShort();
            int prev_start = scope2.start.position;
            int prev_end = scope2.end.position;
            for (int i4 = 0; i4 < count2; i4++) {
                new Variable();
                Variable var = variable;
                int start_pc = readUnsignedShort();
                int end_pc = start_pc + readUnsignedShort();
                if (start_pc != prev_start || end_pc != prev_end) {
                    while (scope2.parent != null && (start_pc < scope2.start.position || end_pc > scope2.end.position)) {
                        scope2 = scope2.parent;
                    }
                    Scope parent = scope2;
                    new Label(start_pc);
                    new Label(end_pc);
                    new Scope(label, label2);
                    scope2 = scope;
                    scope2.linkChild(parent);
                    prev_start = start_pc;
                    prev_end = end_pc;
                }
                scope2.addVariable(var);
                var.setName(readUnsignedShort(), constants);
                var.setSignature(readUnsignedShort(), constants);
                var.offset = readUnsignedShort();
            }
            return attr;
        } else if (name == "Signature" && (container instanceof Member)) {
            new SignatureAttr(readUnsignedShort(), (Member) container);
            return attribute7;
        } else if (name == "StackMapTable" && (container instanceof CodeAttr)) {
            byte[] data = new byte[length];
            readFully(data, 0, length);
            new StackMapTableAttr(data, (CodeAttr) container);
            return attribute6;
        } else if ((name == "RuntimeVisibleAnnotations" || name == "RuntimeInvisibleAnnotations") && ((container instanceof Field) || (container instanceof Method) || (container instanceof ClassType))) {
            byte[] data2 = new byte[length];
            readFully(data2, 0, length);
            new RuntimeAnnotationsAttr(name, data2, container);
            return attribute5;
        } else if (name == "ConstantValue" && (container instanceof Field)) {
            new ConstantValueAttr(readUnsignedShort());
            return attribute4;
        } else if (name == "InnerClasses" && (container instanceof ClassType)) {
            int count3 = 4 * readUnsignedShort();
            short[] data3 = new short[count3];
            for (int i5 = 0; i5 < count3; i5++) {
                data3[i5] = readShort();
            }
            new InnerClassesAttr(data3, (ClassType) container);
            return attribute3;
        } else if (name == "EnclosingMethod" && (container instanceof ClassType)) {
            new EnclosingMethodAttr(readUnsignedShort(), readUnsignedShort(), (ClassType) container);
            return attribute2;
        } else if (name == "Exceptions" && (container instanceof Method)) {
            Method meth = (Method) container;
            int count4 = readUnsignedShort();
            short[] exn_indices = new short[count4];
            for (int i6 = 0; i6 < count4; i6++) {
                exn_indices[i6] = readShort();
            }
            meth.setExceptions(exn_indices);
            return meth.getExceptionAttr();
        } else if (name != "SourceDebugExtension" || !(container instanceof ClassType)) {
            byte[] data4 = new byte[length];
            readFully(data4, 0, length);
            new MiscAttr(name, data4);
            return attribute;
        } else {
            new SourceDebugExtAttr((ClassType) container);
            SourceDebugExtAttr attr2 = sourceDebugExtAttr;
            byte[] data5 = new byte[length];
            readFully(data5, 0, length);
            attr2.data = data5;
            attr2.dlength = length;
            return attr2;
        }
    }

    public void readFields() throws IOException {
        int nFields = readUnsignedShort();
        ConstantPool constants = this.ctype.constants;
        for (int i = 0; i < nFields; i++) {
            int flags = readUnsignedShort();
            int nameIndex = readUnsignedShort();
            int descriptorIndex = readUnsignedShort();
            Field fld = this.ctype.addField();
            fld.setName(nameIndex, constants);
            fld.setSignature(descriptorIndex, constants);
            fld.flags = flags;
            int readAttributes = readAttributes(fld);
        }
    }

    public void readMethods() throws IOException {
        int nMethods = readUnsignedShort();
        for (int i = 0; i < nMethods; i++) {
            int flags = readUnsignedShort();
            int nameIndex = readUnsignedShort();
            int descriptorIndex = readUnsignedShort();
            Method meth = this.ctype.addMethod((String) null, flags);
            meth.setName(nameIndex);
            meth.setSignature(descriptorIndex);
            int readAttributes = readAttributes(meth);
        }
    }

    /* access modifiers changed from: package-private */
    public CpoolClass getClassConstant(int index) {
        return (CpoolClass) this.ctype.constants.getForced(index, 7);
    }
}
