package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class Namespace implements Externalizable, HasNamedParts {
    public static final Namespace EmptyNamespace = valueOf("");
    protected static final Hashtable nsTable;
    int log2Size;
    private int mask;
    String name;
    int num_bindings;
    protected String prefix;
    protected SymbolRef[] table;

    static {
        Hashtable hashtable;
        new Hashtable(50);
        nsTable = hashtable;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name2) {
        String str = name2;
        this.name = str;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected Namespace() {
        this(64);
    }

    protected Namespace(int i) {
        int capacity = i;
        this.prefix = "";
        this.log2Size = 4;
        while (capacity > (1 << this.log2Size)) {
            this.log2Size++;
        }
        int capacity2 = 1 << this.log2Size;
        this.table = new SymbolRef[capacity2];
        this.mask = capacity2 - 1;
    }

    public static Namespace create(int capacity) {
        Namespace namespace;
        new Namespace(capacity);
        return namespace;
    }

    public static Namespace create() {
        Namespace namespace;
        Namespace namespace2 = namespace;
        new Namespace(64);
        return namespace2;
    }

    public static Namespace getDefault() {
        return EmptyNamespace;
    }

    public static Symbol getDefaultSymbol(String name2) {
        return EmptyNamespace.getSymbol(name2);
    }

    public static Namespace valueOf() {
        return EmptyNamespace;
    }

    public static Namespace valueOf(String str) {
        Namespace namespace;
        String name2 = str;
        if (name2 == null) {
            name2 = "";
        }
        Hashtable hashtable = nsTable;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            try {
                Namespace ns = (Namespace) nsTable.get(name2);
                if (ns != null) {
                    Namespace namespace2 = ns;
                    return namespace2;
                }
                new Namespace();
                Namespace ns2 = namespace;
                ns2.setName(name2.intern());
                Object put = nsTable.put(name2, ns2);
                Namespace namespace3 = ns2;
                return namespace3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Hashtable hashtable3 = hashtable2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public static Namespace valueOf(String str, String str2) {
        StringBuilder sb;
        Namespace namespace;
        String uri = str;
        String prefix2 = str2;
        if (prefix2 == null || prefix2.length() == 0) {
            return valueOf(uri);
        }
        new StringBuilder();
        String xname = sb.append(prefix2).append(" -> ").append(uri).toString();
        Hashtable hashtable = nsTable;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            try {
                Object old = nsTable.get(xname);
                if (old instanceof Namespace) {
                    Namespace namespace2 = (Namespace) old;
                    return namespace2;
                }
                new Namespace();
                Namespace ns = namespace;
                ns.setName(uri.intern());
                ns.prefix = prefix2.intern();
                Object put = nsTable.put(xname, ns);
                Namespace namespace3 = ns;
                return namespace3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Hashtable hashtable3 = hashtable2;
                throw th2;
            }
        }
    }

    public static Namespace valueOf(String uri, SimpleSymbol simpleSymbol) {
        SimpleSymbol prefix2 = simpleSymbol;
        return valueOf(uri, prefix2 == null ? null : prefix2.getName());
    }

    public static Namespace makeUnknownNamespace(String str) {
        String uri;
        StringBuilder sb;
        String prefix2 = str;
        if (prefix2 == null || prefix2 == "") {
            uri = "";
        } else {
            new StringBuilder();
            uri = sb.append("http://kawa.gnu.org/unknown-namespace/").append(prefix2).toString();
        }
        return valueOf(uri, prefix2);
    }

    public Object get(String key) {
        return Environment.getCurrent().get(getSymbol(key));
    }

    public boolean isConstant(String str) {
        String str2 = str;
        return false;
    }

    public Symbol getSymbol(String str) {
        String key = str;
        return lookup(key, key.hashCode(), true);
    }

    public Symbol lookup(String str) {
        String key = str;
        return lookup(key, key.hashCode(), false);
    }

    /* access modifiers changed from: protected */
    public final Symbol lookupInternal(String str, int hash) {
        String key = str;
        int index = hash & this.mask;
        SymbolRef prev = null;
        SymbolRef symbolRef = this.table[index];
        while (true) {
            SymbolRef ref = symbolRef;
            if (ref == null) {
                return null;
            }
            SymbolRef next = ref.next;
            Symbol sym = ref.getSymbol();
            if (sym == null) {
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    prev.next = next;
                }
                this.num_bindings--;
            } else if (sym.getLocalPart().equals(key)) {
                return sym;
            } else {
                prev = ref;
            }
            symbolRef = next;
        }
    }

    public Symbol add(Symbol symbol, int hash) {
        SymbolRef symbolRef;
        Symbol sym = symbol;
        int index = hash & this.mask;
        new SymbolRef(sym, this);
        SymbolRef ref = symbolRef;
        sym.namespace = this;
        ref.next = this.table[index];
        this.table[index] = ref;
        this.num_bindings++;
        if (this.num_bindings >= this.table.length) {
            rehash();
        }
        return sym;
    }

    /* JADX INFO: finally extract failed */
    public Symbol lookup(String str, int i, boolean z) {
        Symbol symbol;
        Symbol sym;
        Symbol symbol2;
        String key = str;
        int hash = i;
        boolean create = z;
        synchronized (this) {
            try {
                Symbol sym2 = lookupInternal(key, hash);
                if (sym2 != null) {
                    Symbol symbol3 = sym2;
                    return symbol3;
                } else if (create) {
                    if (this == EmptyNamespace) {
                        new SimpleSymbol(key);
                        sym = symbol2;
                    } else {
                        new Symbol(this, key);
                        sym = symbol;
                    }
                    Symbol add = add(sym, hash);
                    return add;
                } else {
                    return null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean remove(Symbol symbol) {
        Symbol symbol2 = symbol;
        synchronized (this) {
            try {
                int index = symbol2.getLocalPart().hashCode() & this.mask;
                SymbolRef prev = null;
                SymbolRef symbolRef = this.table[index];
                while (true) {
                    SymbolRef ref = symbolRef;
                    if (ref != null) {
                        SymbolRef next = ref.next;
                        Symbol refsym = ref.getSymbol();
                        if (refsym == null || refsym == symbol2) {
                            if (prev == null) {
                                this.table[index] = next;
                            } else {
                                prev.next = next;
                            }
                            this.num_bindings--;
                            if (refsym != null) {
                                return true;
                            }
                        } else {
                            prev = ref;
                        }
                        symbolRef = next;
                    } else {
                        return false;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        int oldCapacity = this.table.length;
        int newCapacity = 2 * oldCapacity;
        int newMask = newCapacity - 1;
        int countInserted = 0;
        SymbolRef[] oldTable = this.table;
        SymbolRef[] newTable = new SymbolRef[newCapacity];
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                SymbolRef symbolRef = oldTable[i];
                while (true) {
                    SymbolRef ref = symbolRef;
                    if (ref != null) {
                        SymbolRef next = ref.next;
                        Symbol sym = ref.getSymbol();
                        if (sym != null) {
                            int index = sym.getName().hashCode() & newMask;
                            countInserted++;
                            ref.next = newTable[index];
                            newTable[index] = ref;
                        }
                        symbolRef = next;
                    }
                }
            } else {
                this.table = newTable;
                this.log2Size++;
                this.mask = newMask;
                this.num_bindings = countInserted;
                return;
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(getName());
        out.writeObject(this.prefix);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.name = ((String) in.readObject()).intern();
        this.prefix = (String) in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        String str;
        StringBuilder sb;
        String name2 = getName();
        if (name2 != null) {
            if (this.prefix == null || this.prefix.length() == 0) {
                str = name2;
            } else {
                new StringBuilder();
                str = sb.append(this.prefix).append(" -> ").append(name2).toString();
            }
            String xname = str;
            Namespace ns = (Namespace) nsTable.get(xname);
            if (ns != null) {
                return ns;
            }
            Object put = nsTable.put(xname, this);
        }
        return this;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder("#,(namespace \"");
        StringBuilder sbuf = sb;
        StringBuilder append = sbuf.append(this.name);
        StringBuilder append2 = sbuf.append('\"');
        if (!(this.prefix == null || this.prefix == "")) {
            StringBuilder append3 = sbuf.append(' ');
            StringBuilder append4 = sbuf.append(this.prefix);
        }
        StringBuilder append5 = sbuf.append(')');
        return sbuf.toString();
    }
}
