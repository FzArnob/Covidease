package gnu.bytecode;

public class UninitializedType extends ObjectType {
    ClassType ctype;
    Label label;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    UninitializedType(gnu.bytecode.ClassType r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.getName()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.getSignature()
            r2.setSignature(r3)
            r2 = r0
            r3 = r1
            r2.ctype = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.UninitializedType.<init>(gnu.bytecode.ClassType):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    UninitializedType(ClassType ctype2, Label label2) {
        this(ctype2);
        this.label = label2;
    }

    static UninitializedType uninitializedThis(ClassType ctype2) {
        UninitializedType uninitializedType;
        new UninitializedType(ctype2);
        return uninitializedType;
    }

    public Type getImplementationType() {
        return this.ctype;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Uninitialized<").append(this.ctype.getName()).append('>').toString();
    }
}
