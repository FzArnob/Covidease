package gnu.kawa.functions;

import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* compiled from: GetNamedInstancePart */
class SetNamedInstancePart extends Procedure2 implements Externalizable {
    String pname;

    public SetNamedInstancePart() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedInstancePart");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SetNamedInstancePart(String name) {
        this();
        setPartName(name);
    }

    public void setPartName(String str) {
        StringBuilder sb;
        String name = str;
        new StringBuilder();
        setName(sb.append("set-instance-part:.").append(name).toString());
        this.pname = name;
    }

    public Object apply2(Object instance, Object value) throws Throwable {
        SlotSet.setField(instance, this.pname, value);
        return Values.empty;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.pname);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setPartName((String) in.readObject());
    }
}
