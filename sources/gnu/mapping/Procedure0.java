package gnu.mapping;

public abstract class Procedure0 extends Procedure {
    public abstract Object apply0() throws Throwable;

    public Procedure0() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Procedure0(String n) {
        super(n);
    }

    public int numArgs() {
        return 0;
    }

    public Object apply1(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new WrongArguments(this, 1);
        throw th2;
    }

    public Object apply2(Object obj, Object obj2) {
        Throwable th;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new WrongArguments(this, 2);
        throw th2;
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        Throwable th2 = th;
        new WrongArguments(this, 3);
        throw th2;
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) {
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
        if (args.length == 0) {
            return apply0();
        }
        Throwable th2 = th;
        new WrongArguments(this, args.length);
        throw th2;
    }
}
