package gnu.mapping;

public class ConstrainedLocation extends Location {
    protected Location base;
    protected Procedure converter;

    public ConstrainedLocation() {
    }

    public static ConstrainedLocation make(Location base2, Procedure converter2) {
        ConstrainedLocation constrainedLocation;
        new ConstrainedLocation();
        ConstrainedLocation cloc = constrainedLocation;
        cloc.base = base2;
        cloc.converter = converter2;
        return cloc;
    }

    public Symbol getKeySymbol() {
        return this.base.getKeySymbol();
    }

    public Object getKeyProperty() {
        return this.base.getKeyProperty();
    }

    public boolean isConstant() {
        return this.base.isConstant();
    }

    public final Object get(Object defaultValue) {
        return this.base.get(defaultValue);
    }

    public boolean isBound() {
        return this.base.isBound();
    }

    /* access modifiers changed from: protected */
    public Object coerce(Object newValue) {
        try {
            return this.converter.apply1(newValue);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public final void set(Object newValue) {
        this.base.set(coerce(newValue));
    }

    public Object setWithSave(Object newValue) {
        return this.base.setWithSave(coerce(newValue));
    }

    public void setRestore(Object oldValue) {
        this.base.setRestore(oldValue);
    }
}
