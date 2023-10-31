package gnu.bytecode;

public class Location {
    protected String name;
    int name_index;
    int signature_index;
    protected Type type;

    public Location() {
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name2) {
        String str = name2;
        this.name = str;
    }

    public final void setName(int i, ConstantPool constantPool) {
        int name_index2 = i;
        ConstantPool constants = constantPool;
        if (name_index2 <= 0) {
            this.name = null;
        } else {
            this.name = ((CpoolUtf8) constants.getForced(name_index2, 1)).string;
        }
        this.name_index = name_index2;
    }

    public final Type getType() {
        return this.type;
    }

    public final void setType(Type type2) {
        Type type3 = type2;
        this.type = type3;
    }

    public final String getSignature() {
        return this.type.getSignature();
    }

    public void setSignature(int i, ConstantPool constants) {
        int signature_index2 = i;
        CpoolUtf8 sigConstant = (CpoolUtf8) constants.getForced(signature_index2, 1);
        this.signature_index = signature_index2;
        this.type = Type.signatureToType(sigConstant.string);
    }
}
