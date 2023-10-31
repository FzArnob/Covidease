package gnu.bytecode;

public class IfState {
    boolean doing_else;
    Label end_label;
    IfState previous;
    int stack_growth;
    int start_stack_size;
    Type[] then_stacked_types;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IfState(gnu.bytecode.CodeAttr r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            gnu.bytecode.Label r4 = new gnu.bytecode.Label
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>((gnu.bytecode.CodeAttr) r6)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.IfState.<init>(gnu.bytecode.CodeAttr):void");
    }

    public IfState(CodeAttr codeAttr, Label endLabel) {
        CodeAttr code = codeAttr;
        this.previous = code.if_stack;
        code.if_stack = this;
        this.end_label = endLabel;
        this.start_stack_size = code.f56SP;
    }
}
