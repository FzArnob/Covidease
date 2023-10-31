package gnu.mapping;

import android.support.p000v4.app.FragmentTransaction;

public class Setter0 extends Setter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Setter0(Procedure getter) {
        super(getter);
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply1(Object result) throws Throwable {
        this.getter.set0(result);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Object[] args = objArr;
        int nargs = args.length;
        if (nargs != 1) {
            Throwable th2 = th;
            new WrongArguments(this, nargs);
            throw th2;
        }
        this.getter.set0(args[0]);
        return Values.empty;
    }
}
