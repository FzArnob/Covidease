package gnu.mapping;

public abstract class IndirectableLocation extends Location {
    protected static final Object DIRECT_ON_SET;
    protected static final Object INDIRECT_FLUIDS;
    protected Location base;
    protected Object value;

    public IndirectableLocation() {
    }

    static {
        Object obj;
        Object obj2;
        new String("(direct-on-set)");
        DIRECT_ON_SET = obj;
        new String("(indirect-fluids)");
        INDIRECT_FLUIDS = obj2;
    }

    public Symbol getKeySymbol() {
        return this.base != null ? this.base.getKeySymbol() : null;
    }

    public Object getKeyProperty() {
        return this.base != null ? this.base.getKeyProperty() : null;
    }

    public boolean isConstant() {
        return this.base != null && this.base.isConstant();
    }

    public Location getBase() {
        return this.base == null ? this : this.base.getBase();
    }

    public Location getBaseForce() {
        IndirectableLocation indirectableLocation;
        if (this.base != null) {
            return this.base;
        }
        new PlainLocation(getKeySymbol(), getKeyProperty(), this.value);
        return indirectableLocation;
    }

    public void setBase(Location base2) {
        this.base = base2;
        this.value = null;
    }

    public void setAlias(Location base2) {
        this.base = base2;
        this.value = INDIRECT_FLUIDS;
    }

    public void undefine() {
        this.base = null;
        this.value = UNBOUND;
    }

    public Environment getEnvironment() {
        return this.base instanceof NamedLocation ? ((NamedLocation) this.base).getEnvironment() : null;
    }
}
