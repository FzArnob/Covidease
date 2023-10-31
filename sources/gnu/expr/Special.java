package gnu.expr;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Special implements Printable, Externalizable {
    public static final Special abstractSpecial;
    public static final Special dfault;
    public static final Object eof = Sequence.eofValue;
    public static final Special key;
    public static final Special optional;
    public static final Special rest;
    public static final Special undefined;
    private String name;

    static {
        Special special;
        Special special2;
        Special special3;
        Special special4;
        Special special5;
        Special special6;
        new Special("undefined");
        undefined = special;
        new Special("optional");
        optional = special2;
        new Special("rest");
        rest = special3;
        new Special("key");
        key = special4;
        new Special("default");
        dfault = special5;
        new Special("abstract");
        abstractSpecial = special6;
    }

    public Special() {
    }

    private Special(String n) {
        String str;
        new String(n);
        this.name = str;
    }

    public static Special make(String str) {
        Special special;
        String name2 = str;
        if (name2 == "optional") {
            return optional;
        }
        if (name2 == "rest") {
            return rest;
        }
        if (name2 == "key") {
            return key;
        }
        if (name2 == "default") {
            return dfault;
        }
        new Special(name2);
        return special;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("#!").append(this.name).toString();
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#!");
        out.write(this.name);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String readUTF = in.readUTF();
        this.name = readUTF;
    }

    public Object readResolve() throws ObjectStreamException {
        return make(this.name);
    }
}
