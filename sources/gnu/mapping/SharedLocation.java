package gnu.mapping;

public class SharedLocation extends NamedLocation {
    int timestamp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SharedLocation(Symbol symbol, Object property, int timestamp2) {
        super(symbol, property);
        this.timestamp = timestamp2;
    }

    public final synchronized Object get(Object obj) {
        Object obj2;
        Object defaultValue = obj;
        synchronized (this) {
            obj2 = this.base != null ? this.base.get(defaultValue) : this.value == Location.UNBOUND ? defaultValue : this.value;
        }
        return obj2;
    }

    public synchronized boolean isBound() {
        boolean isBound;
        synchronized (this) {
            isBound = this.base != null ? this.base.isBound() : this.value != Location.UNBOUND;
        }
        return isBound;
    }

    public final synchronized void set(Object obj) {
        Object newValue = obj;
        synchronized (this) {
            if (this.base == null) {
                this.value = newValue;
            } else if (this.value == DIRECT_ON_SET) {
                this.base = null;
                this.value = newValue;
            } else if (this.base.isConstant()) {
                getEnvironment().put(getKeySymbol(), getKeyProperty(), newValue);
            } else {
                this.base.set(newValue);
            }
        }
    }
}
