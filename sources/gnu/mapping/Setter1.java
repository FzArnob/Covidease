package gnu.mapping;

public class Setter1 extends Setter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Setter1(Procedure getter) {
        super(getter);
    }

    public int numArgs() {
        return 8194;
    }

    public Object apply2(Object arg, Object value) throws Throwable {
        this.getter.set1(arg, value);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Throwable th;
        Object[] args = objArr;
        int nargs = args.length;
        if (nargs != 2) {
            Throwable th2 = th;
            new WrongArguments(this, nargs);
            throw th2;
        }
        this.getter.set1(args[0], args[1]);
        return Values.empty;
    }
}
