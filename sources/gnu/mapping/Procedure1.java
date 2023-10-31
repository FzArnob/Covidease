package gnu.mapping;

import android.support.p000v4.app.FragmentTransaction;

public abstract class Procedure1 extends Procedure {
    public abstract Object apply1(Object obj) throws Throwable;

    public Procedure1() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Procedure1(String n) {
        super(n);
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply0() throws Throwable {
        Throwable th;
        Throwable th2 = th;
        new WrongArguments(this, 0);
        throw th2;
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Throwable th;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new WrongArguments(this, 2);
        throw th2;
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        Throwable th;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        Throwable th2 = th;
        new WrongArguments(this, 3);
        throw th2;
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        Throwable th;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        Throwable th2 = th;
        new WrongArguments(this, 4);
        throw th2;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Object[] args = objArr;
        if (args.length == 1) {
            return apply1(args[0]);
        }
        Throwable th2 = th;
        new WrongArguments(this, args.length);
        throw th2;
    }
}
