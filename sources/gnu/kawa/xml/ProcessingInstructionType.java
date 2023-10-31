package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ProcessingInstructionType extends NodeType implements TypeValue, Externalizable {
    static final Method coerceMethod = typeProcessingInstructionType.getDeclaredMethod("coerce", 2);
    static final Method coerceOrNullMethod = typeProcessingInstructionType.getDeclaredMethod("coerceOrNull", 2);
    public static final ProcessingInstructionType piNodeTest;
    public static final ClassType typeProcessingInstructionType = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
    String target;

    static {
        ProcessingInstructionType processingInstructionType;
        new ProcessingInstructionType((String) null);
        piNodeTest = processingInstructionType;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProcessingInstructionType(java.lang.String r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            if (r3 != 0) goto L_0x0011
            java.lang.String r3 = "processing-instruction()"
        L_0x0009:
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.target = r3
            return
        L_0x0011:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            java.lang.String r4 = "processing-instruction("
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = r1
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ")"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.ProcessingInstructionType.<init>(java.lang.String):void");
    }

    public static ProcessingInstructionType getInstance(String str) {
        ProcessingInstructionType processingInstructionType;
        ProcessingInstructionType processingInstructionType2;
        String target2 = str;
        if (target2 == null) {
            processingInstructionType2 = piNodeTest;
        } else {
            processingInstructionType2 = processingInstructionType;
            new ProcessingInstructionType(target2);
        }
        return processingInstructionType2;
    }

    public Type getImplementationType() {
        return ClassType.make("gnu.kawa.xml.KProcessingInstruction");
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        code.emitPushString(this.target);
        code.emitInvokeStatic(coerceMethod);
    }

    public Object coerceFromObject(Object obj) {
        return coerce(obj, this.target);
    }

    public boolean isInstancePos(AbstractSequence abstractSequence, int i) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        int kind = seq.getNextKind(ipos);
        if (kind == 37) {
            return this.target == null || this.target.equals(seq.getNextTypeObject(ipos));
        } else if (kind == 32) {
            return isInstance(seq.getPosNext(ipos));
        } else {
            return false;
        }
    }

    public boolean isInstance(Object obj) {
        return coerceOrNull(obj, this.target) != null;
    }

    public static KProcessingInstruction coerceOrNull(Object obj, String str) {
        String target2 = str;
        KProcessingInstruction pos = (KProcessingInstruction) NodeType.coerceOrNull(obj, 32);
        return (pos == null || (target2 != null && !target2.equals(pos.getTarget()))) ? null : pos;
    }

    public static KProcessingInstruction coerce(Object obj, String target2) {
        Throwable th;
        KProcessingInstruction pos = coerceOrNull(obj, target2);
        if (pos != null) {
            return pos;
        }
        Throwable th2 = th;
        new ClassCastException();
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void emitCoerceOrNullMethod(Variable variable, Compilation comp) {
        Variable incoming = variable;
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        code.emitPushString(this.target);
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.target);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String str = (String) in.readObject();
        this.target = str;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ProcessingInstructionType ").append(this.target == null ? "*" : this.target).toString();
    }
}
