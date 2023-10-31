package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.Setter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* compiled from: GetNamedPart */
class NamedPartSetter extends Setter implements Externalizable {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NamedPartSetter(NamedPart getter) {
        super(getter);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPartSetter");
    }

    public int numArgs() {
        if (((NamedPart) this.getter).kind == 'D') {
            return 8193;
        }
        return -4096;
    }

    /* access modifiers changed from: package-private */
    public Procedure getGetter() {
        return this.getter;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getter);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Procedure procedure = (Procedure) in.readObject();
        this.getter = procedure;
    }
}
