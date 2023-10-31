package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Symbol implements EnvironmentKey, Comparable, Externalizable {
    public static final Symbol FUNCTION = makeUninterned("(function)");
    public static final Symbol PLIST = makeUninterned("(property-list)");
    protected String name;
    Namespace namespace;

    public final Symbol getKeySymbol() {
        return this;
    }

    public final Object getKeyProperty() {
        return null;
    }

    public boolean matches(EnvironmentKey environmentKey) {
        EnvironmentKey key = environmentKey;
        return equals(key.getKeySymbol(), this) && key.getKeyProperty() == null;
    }

    public boolean matches(Symbol symbol, Object property) {
        return equals(symbol, this) && property == null;
    }

    public final String getNamespaceURI() {
        Namespace ns = getNamespace();
        return ns == null ? null : ns.getName();
    }

    public final String getLocalPart() {
        return this.name;
    }

    public final String getPrefix() {
        Namespace ns = this.namespace;
        return ns == null ? "" : ns.prefix;
    }

    public final boolean hasEmptyNamespace() {
        boolean z;
        Namespace ns = getNamespace();
        if (ns != null) {
            String name2 = ns.getName();
            String nsname = name2;
            if (!(name2 == null || nsname.length() == 0)) {
                z = false;
                return z;
            }
        }
        z = true;
        return z;
    }

    public final String getLocalName() {
        return this.name;
    }

    public final String getName() {
        return this.name;
    }

    public static Symbol make(String uri, String name2, String prefix) {
        return Namespace.valueOf(uri, prefix).getSymbol(name2.intern());
    }

    public static Symbol make(Object obj, String str) {
        Object namespace2 = obj;
        String name2 = str;
        Namespace ns = namespace2 instanceof String ? Namespace.valueOf((String) namespace2) : (Namespace) namespace2;
        if (ns == null || name2 == null) {
            return makeUninterned(name2);
        }
        return ns.getSymbol(name2.intern());
    }

    public static SimpleSymbol valueOf(String name2) {
        return (SimpleSymbol) Namespace.EmptyNamespace.getSymbol(name2.intern());
    }

    public static Symbol valueOf(String str, Object obj) {
        Namespace ns;
        String name2 = str;
        Object spec = obj;
        if (spec == null || spec == Boolean.FALSE) {
            return makeUninterned(name2);
        }
        if (spec instanceof Namespace) {
            ns = (Namespace) spec;
        } else if (spec == Boolean.TRUE) {
            ns = Namespace.EmptyNamespace;
        } else {
            ns = Namespace.valueOf(((CharSequence) spec).toString());
        }
        return ns.getSymbol(name2.intern());
    }

    public static Symbol valueOf(String name2, String namespace2, String prefix) {
        return Namespace.valueOf(namespace2, prefix).getSymbol(name2.intern());
    }

    public static Symbol parse(String str) {
        String symbol = str;
        int slen = symbol.length();
        int lbr = -1;
        int rbr = -1;
        int braceCount = 0;
        int mainStart = 0;
        int prefixEnd = 0;
        int i = 0;
        while (true) {
            if (i < slen) {
                char ch = symbol.charAt(i);
                if (ch == ':' && braceCount == 0) {
                    prefixEnd = i;
                    mainStart = i + 1;
                    break;
                }
                if (ch == '{') {
                    if (lbr < 0) {
                        prefixEnd = i;
                        lbr = i;
                    }
                    braceCount++;
                }
                if (ch == '}') {
                    braceCount--;
                    if (braceCount == 0) {
                        rbr = i;
                        mainStart = (i >= slen || symbol.charAt(i + 1) != ':') ? i + 1 : i + 2;
                    } else if (braceCount < 0) {
                        mainStart = prefixEnd;
                        break;
                    }
                }
                i++;
            } else {
                break;
            }
        }
        if (lbr >= 0 && rbr > 0) {
            return valueOf(symbol.substring(mainStart), symbol.substring(lbr + 1, rbr), prefixEnd > 0 ? symbol.substring(0, prefixEnd) : null);
        } else if (prefixEnd > 0) {
            return makeWithUnknownNamespace(symbol.substring(mainStart), symbol.substring(0, prefixEnd));
        } else {
            return valueOf(symbol);
        }
    }

    public static Symbol makeWithUnknownNamespace(String local, String prefix) {
        return Namespace.makeUnknownNamespace(prefix).getSymbol(local.intern());
    }

    public Symbol() {
    }

    public static Symbol makeUninterned(String name2) {
        Symbol symbol;
        new Symbol((Namespace) null, name2);
        return symbol;
    }

    public Symbol(Namespace ns, String name2) {
        this.name = name2;
        this.namespace = ns;
    }

    public int compareTo(Object o) {
        Throwable th;
        Symbol other = (Symbol) o;
        if (getNamespaceURI() == other.getNamespaceURI()) {
            return getLocalName().compareTo(other.getLocalName());
        }
        Throwable th2 = th;
        new IllegalArgumentException("comparing Symbols in different namespaces");
        throw th2;
    }

    public static boolean equals(Symbol symbol, Symbol symbol2) {
        Symbol sym1 = symbol;
        Symbol sym2 = symbol2;
        if (sym1 == sym2) {
            return true;
        }
        if (sym1 == null || sym2 == null) {
            return false;
        }
        if (sym1.name == sym2.name) {
            Namespace namespace1 = sym1.namespace;
            Namespace namespace2 = sym2.namespace;
            if (!(namespace1 == null || namespace2 == null)) {
                return namespace1.name == namespace2.name;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        Object o = obj;
        return (o instanceof Symbol) && equals(this, (Symbol) o);
    }

    public int hashCode() {
        return this.name == null ? 0 : this.name.hashCode();
    }

    public final Namespace getNamespace() {
        return this.namespace;
    }

    public final void setNamespace(Namespace ns) {
        Namespace namespace2 = ns;
        this.namespace = namespace2;
    }

    public String toString() {
        return toString('P');
    }

    public String toString(char c) {
        StringBuilder sb;
        char style = c;
        String uri = getNamespaceURI();
        String prefix = getPrefix();
        boolean hasUri = uri != null && uri.length() > 0;
        boolean hasPrefix = prefix != null && prefix.length() > 0;
        String name2 = getName();
        if (!hasUri && !hasPrefix) {
            return name2;
        }
        new StringBuilder();
        StringBuilder sbuf = sb;
        if (hasPrefix && (style != 'U' || !hasUri)) {
            StringBuilder append = sbuf.append(prefix);
        }
        if (hasUri && (style != 'P' || !hasPrefix)) {
            StringBuilder append2 = sbuf.append('{');
            StringBuilder append3 = sbuf.append(getNamespaceURI());
            StringBuilder append4 = sbuf.append('}');
        }
        StringBuilder append5 = sbuf.append(':');
        StringBuilder append6 = sbuf.append(name2);
        return sbuf.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(getNamespace());
        out.writeObject(getName());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.namespace = (Namespace) in.readObject();
        this.name = (String) in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        if (this.namespace != null) {
            return make(this.namespace, getName());
        }
        return this;
    }
}
