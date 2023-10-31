package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Keyword extends Symbol implements Printable, Externalizable {
    public static final Namespace keywordNamespace = Namespace.create();

    static {
        keywordNamespace.setName("(keywords)");
    }

    public Keyword() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Keyword(String name) {
        super(keywordNamespace, name);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Keyword(Namespace namespace, String name) {
        super(namespace, name);
    }

    public Symbol asSymbol() {
        return Namespace.EmptyNamespace.getSymbol(getName());
    }

    public static Keyword make(String str) {
        Keyword keyword;
        String name = str;
        int hash = name.hashCode();
        Keyword keyword2 = (Keyword) keywordNamespace.lookup(name, hash, false);
        if (keyword2 == null) {
            new Keyword(name);
            keyword2 = keyword;
            Symbol add = keywordNamespace.add(keyword2, hash);
        }
        return keyword2;
    }

    public static boolean isKeyword(Object obj) {
        return obj instanceof Keyword;
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getName()).append(':').toString();
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        Symbols.print(getName(), out);
        out.write(58);
    }

    public static Object searchForKeyword(Object[] objArr, int offset, Object obj) {
        Object[] vals = objArr;
        Object keyword = obj;
        for (int i = offset; i < vals.length; i += 2) {
            if (vals[i] == keyword) {
                return vals[i + 1];
            }
        }
        return Special.dfault;
    }

    public static Object searchForKeyword(Object[] objArr, int offset, Object obj, Object obj2) {
        Object[] vals = objArr;
        Object keyword = obj;
        Object dfault = obj2;
        for (int i = offset; i < vals.length; i += 2) {
            if (vals[i] == keyword) {
                return vals[i + 1];
            }
        }
        return dfault;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String str = (String) in.readObject();
        this.name = str;
    }

    public Object readResolve() throws ObjectStreamException {
        return make(keywordNamespace, getName());
    }
}
