package gnu.mapping;

public class PlainLocation extends NamedLocation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlainLocation(Symbol symbol, Object property) {
        super(symbol, property);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlainLocation(Symbol symbol, Object property, Object value) {
        super(symbol, property);
        this.value = value;
    }

    public final Object get(Object obj) {
        Object defaultValue = obj;
        return this.base != null ? this.base.get(defaultValue) : this.value == Location.UNBOUND ? defaultValue : this.value;
    }

    public boolean isBound() {
        return this.base != null ? this.base.isBound() : this.value != Location.UNBOUND;
    }

    public final void set(Object obj) {
        Object newValue = obj;
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
