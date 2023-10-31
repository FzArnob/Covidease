package gnu.mapping;

import android.support.p000v4.app.FragmentTransaction;

public class Setter extends ProcedureN {
    protected Procedure getter;

    public Setter(Procedure procedure) {
        StringBuilder sb;
        Procedure getter2 = procedure;
        this.getter = getter2;
        String name = getter2.getName();
        if (name != null) {
            new StringBuilder();
            setName(sb.append("(setter ").append(name).append(")").toString());
        }
    }

    public int numArgs() {
        int get_args = this.getter.numArgs();
        if (get_args < 0) {
            return get_args + 1;
        }
        return get_args + FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object applyN(Object[] args) throws Throwable {
        this.getter.setN(args);
        return Values.empty;
    }
}
