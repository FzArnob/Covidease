package gnu.mapping;

public class KeyPair implements EnvironmentKey {
    Symbol name;
    Object property;

    public KeyPair(Symbol name2, Object property2) {
        this.name = name2;
        this.property = property2;
    }

    public Symbol getKeySymbol() {
        return this.name;
    }

    public Object getKeyProperty() {
        return this.property;
    }

    public final boolean matches(EnvironmentKey environmentKey) {
        EnvironmentKey key = environmentKey;
        return Symbol.equals(key.getKeySymbol(), this.name) && key.getKeyProperty() == this.property;
    }

    public final boolean matches(Symbol symbol, Object property2) {
        return Symbol.equals(symbol, this.name) && property2 == this.property;
    }

    public boolean equals(Object obj) {
        Object x = obj;
        if (!(x instanceof KeyPair)) {
            return false;
        }
        KeyPair e2 = (KeyPair) x;
        return this.property == e2.property && (this.name != null ? this.name.equals(e2.name) : e2.name == null);
    }

    public int hashCode() {
        return this.name.hashCode() ^ System.identityHashCode(this.property);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("KeyPair[sym:").append(this.name).append(" prop:").append(this.property).append("]").toString();
    }
}
