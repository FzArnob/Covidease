package gnu.kawa.xml;

import gnu.mapping.Symbol;

public class Notation {
    Symbol qname;

    public Notation() {
    }

    public boolean equals(Notation n1, Notation n2) {
        return n1.qname.equals(n2.qname);
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        return (obj2 instanceof Notation) && equals(this, (Notation) obj2);
    }

    public int hashCode() {
        return this.qname.hashCode();
    }
}
