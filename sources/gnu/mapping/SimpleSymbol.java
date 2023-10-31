package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class SimpleSymbol extends Symbol {
    public SimpleSymbol() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleSymbol(String key) {
        super(Namespace.EmptyNamespace, key);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String intern = ((String) in.readObject()).intern();
        this.name = intern;
    }

    public Object readResolve() throws ObjectStreamException {
        return Namespace.EmptyNamespace.getSymbol(getName().intern());
    }
}
