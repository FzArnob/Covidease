package gnu.mapping;

public class ReadOnlyLocation extends ConstrainedLocation {
    public ReadOnlyLocation() {
    }

    public static ReadOnlyLocation make(Location base) {
        ReadOnlyLocation readOnlyLocation;
        new ReadOnlyLocation();
        ReadOnlyLocation rloc = readOnlyLocation;
        rloc.base = base;
        return rloc;
    }

    public boolean isConstant() {
        return true;
    }

    /* access modifiers changed from: protected */
    public Object coerce(Object obj) {
        StringBuffer stringBuffer;
        Throwable th;
        Object obj2 = obj;
        new StringBuffer("attempt to modify read-only location");
        StringBuffer sbuf = stringBuffer;
        Symbol name = getKeySymbol();
        if (name != null) {
            StringBuffer append = sbuf.append(": ");
            StringBuffer append2 = sbuf.append(name);
        }
        Throwable th2 = th;
        new IllegalStateException(sbuf.toString());
        throw th2;
    }
}
