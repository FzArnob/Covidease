package gnu.mapping;

import java.io.PrintWriter;

public abstract class Location {
    public static final String UNBOUND;

    public abstract Object get(Object obj);

    public abstract void set(Object obj);

    public Location() {
    }

    public Symbol getKeySymbol() {
        return null;
    }

    public Object getKeyProperty() {
        return null;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(getClass().getName());
        Symbol sym = getKeySymbol();
        StringBuffer append2 = sbuf.append('[');
        if (sym != null) {
            StringBuffer append3 = sbuf.append(sym);
            Object property = getKeyProperty();
            if (!(property == null || property == this)) {
                StringBuffer append4 = sbuf.append('/');
                StringBuffer append5 = sbuf.append(property);
            }
        }
        StringBuffer append6 = sbuf.append("]");
        return sbuf.toString();
    }

    static {
        String str;
        new String("(unbound)");
        UNBOUND = str;
    }

    public final Object get() {
        Throwable th;
        String unb = UNBOUND;
        Object val = get(unb);
        if (val != unb) {
            return val;
        }
        Throwable th2 = th;
        new UnboundLocationException(this);
        throw th2;
    }

    public void undefine() {
        set(UNBOUND);
    }

    public Object setWithSave(Object newValue) {
        Object old = get(UNBOUND);
        set(newValue);
        return old;
    }

    public void setRestore(Object oldValue) {
        set(oldValue);
    }

    public boolean isBound() {
        String unb = UNBOUND;
        return get(unb) != unb;
    }

    public boolean isConstant() {
        return false;
    }

    public Location getBase() {
        return this;
    }

    public final Object getValue() {
        return get((Object) null);
    }

    public final Object setValue(Object newValue) {
        Object value = get((Object) null);
        set(newValue);
        return value;
    }

    public boolean entered() {
        return false;
    }

    public void print(PrintWriter printWriter) {
        PrintWriter ps = printWriter;
        ps.print("#<location ");
        Symbol name = getKeySymbol();
        if (name != null) {
            ps.print(name);
        }
        String unb = UNBOUND;
        Object value = get(unb);
        if (value != unb) {
            ps.print(" -> ");
            ps.print(value);
        } else {
            ps.print("(unbound)");
        }
        ps.print('>');
    }

    public static Location make(Object init, String name) {
        ThreadLocation threadLocation;
        new ThreadLocation(name);
        ThreadLocation loc = threadLocation;
        loc.setGlobal(init);
        return loc;
    }

    public static IndirectableLocation make(String name) {
        PlainLocation plainLocation;
        new PlainLocation(Namespace.EmptyNamespace.getSymbol(name.intern()), (Object) null);
        PlainLocation loc = plainLocation;
        loc.base = null;
        loc.value = UNBOUND;
        return loc;
    }

    public static IndirectableLocation make(Symbol name) {
        PlainLocation plainLocation;
        new PlainLocation(name, (Object) null);
        PlainLocation loc = plainLocation;
        loc.base = null;
        loc.value = UNBOUND;
        return loc;
    }
}
