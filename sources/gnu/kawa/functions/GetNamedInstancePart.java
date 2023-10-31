package gnu.kawa.functions;

import android.support.p000v4.app.FragmentTransaction;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GetNamedInstancePart extends ProcedureN implements Externalizable, HasSetter {
    boolean isField;
    String pname;

    public GetNamedInstancePart() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedInstancePart");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetNamedInstancePart(String name) {
        this();
        setPartName(name);
    }

    public void setPartName(String str) {
        StringBuilder sb;
        String name = str;
        new StringBuilder();
        setName(sb.append("get-instance-part:").append(name).toString());
        if (name.length() <= 1 || name.charAt(0) != '.') {
            this.isField = false;
            this.pname = name;
            return;
        }
        this.isField = true;
        this.pname = name.substring(1);
    }

    public int numArgs() {
        return this.isField ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : -4095;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Object[] args = objArr;
        checkArgCount(this, args.length);
        if (this.isField) {
            return SlotGet.field(args[0], this.pname);
        }
        Object[] xargs = new Object[(args.length + 1)];
        xargs[0] = args[0];
        xargs[1] = this.pname;
        System.arraycopy(args, 1, xargs, 2, args.length - 1);
        return Invoke.invoke.applyN(xargs);
    }

    public Procedure getSetter() {
        Procedure procedure;
        Throwable th;
        if (!this.isField) {
            Throwable th2 = th;
            new RuntimeException("no setter for instance method call");
            throw th2;
        }
        new SetNamedInstancePart(this.pname);
        return procedure;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        String str;
        StringBuilder sb;
        ObjectOutput objectOutput = out;
        if (this.isField) {
            new StringBuilder();
            str = sb.append(".").append(this.pname).toString();
        } else {
            str = this.pname;
        }
        objectOutput.writeObject(str);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setPartName((String) in.readObject());
    }
}
