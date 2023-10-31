package gnu.mapping;

import java.util.Map;

public abstract class NamedLocation extends IndirectableLocation implements Map.Entry, EnvironmentKey {
    final Symbol name;
    NamedLocation next;
    final Object property;

    public boolean entered() {
        return this.next != null;
    }

    public Environment getEnvironment() {
        Environment env;
        NamedLocation namedLocation = this;
        while (true) {
            NamedLocation loc = namedLocation;
            if (loc == null) {
                return super.getEnvironment();
            }
            if (loc.name == null && (env = (Environment) loc.value) != null) {
                return env;
            }
            namedLocation = loc.next;
        }
    }

    public NamedLocation(NamedLocation namedLocation) {
        NamedLocation loc = namedLocation;
        this.name = loc.name;
        this.property = loc.property;
    }

    public NamedLocation(Symbol name2, Object property2) {
        this.name = name2;
        this.property = property2;
    }

    public final Symbol getKeySymbol() {
        return this.name;
    }

    public final Object getKeyProperty() {
        return this.property;
    }

    public final boolean matches(EnvironmentKey environmentKey) {
        EnvironmentKey key = environmentKey;
        return Symbol.equals(key.getKeySymbol(), this.name) && key.getKeyProperty() == this.property;
    }

    public final boolean matches(Symbol symbol, Object property2) {
        return Symbol.equals(symbol, this.name) && property2 == this.property;
    }

    public final Object getKey() {
        if (this.property == null) {
            return this.name;
        }
        return this;
    }

    public boolean equals(Object obj) {
        Object x = obj;
        if (!(x instanceof NamedLocation)) {
            return false;
        }
        NamedLocation e2 = (NamedLocation) x;
        if (this.name != null ? !this.name.equals(e2.name) : e2.name != null) {
            return false;
        }
        if (this.property != e2.property) {
            return false;
        }
        Object val1 = getValue();
        Object val2 = e2.getValue();
        if (val1 == val2) {
            return true;
        }
        if (val1 == null || val2 == null) {
            return false;
        }
        return val1.equals(val2);
    }

    public int hashCode() {
        int h = this.name.hashCode() ^ System.identityHashCode(this.property);
        Object val = getValue();
        if (val != null) {
            h ^= val.hashCode();
        }
        return h;
    }

    public synchronized Object setWithSave(Object obj) {
        Object obj2;
        Object newValue = obj;
        synchronized (this) {
            if (this.value == INDIRECT_FLUIDS) {
                obj2 = this.base.setWithSave(newValue);
            } else {
                ThreadLocation thloc = ThreadLocation.makeAnonymous(this.name);
                thloc.global.base = this.base;
                thloc.global.value = this.value;
                setAlias(thloc);
                NamedLocation entry = thloc.getLocation();
                entry.value = newValue;
                entry.base = null;
                obj2 = thloc.global;
            }
        }
        return obj2;
    }

    public synchronized void setRestore(Object obj) {
        Object oldValue = obj;
        synchronized (this) {
            if (this.value == INDIRECT_FLUIDS) {
                this.base.setRestore(oldValue);
            } else if (oldValue instanceof Location) {
                this.value = null;
                this.base = (Location) oldValue;
            } else {
                this.value = oldValue;
                this.base = null;
            }
        }
    }
}
