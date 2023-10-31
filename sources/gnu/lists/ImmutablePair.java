package gnu.lists;

public class ImmutablePair extends Pair {
    public ImmutablePair(Object carval, Object cdrval) {
        this.car = carval;
        this.cdr = cdrval;
    }

    public ImmutablePair() {
    }

    public void setCar(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException("cannot modify read-only pair");
        throw th2;
    }

    public void setCdr(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException("cannot modify read-only pair");
        throw th2;
    }
}
