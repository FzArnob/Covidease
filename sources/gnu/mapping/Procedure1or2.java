package gnu.mapping;

public abstract class Procedure1or2 extends Procedure {
    public abstract Object apply1(Object obj) throws Throwable;

    public abstract Object apply2(Object obj, Object obj2) throws Throwable;

    public Procedure1or2() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Procedure1or2(String n) {
        super(n);
    }

    public int numArgs() {
        return 8193;
    }

    public Object apply0() {
        Throwable th;
        Throwable th2 = th;
        new WrongArguments(this, 0);
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
        if (args.length == 1) {
            return apply1(args[0]);
        }
        if (args.length == 2) {
            return apply2(args[0], args[1]);
        }
        Throwable th2 = th;
        new WrongArguments(this, args.length);
        throw th2;
    }
}
