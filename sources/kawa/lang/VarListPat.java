package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.Pair;

public class VarListPat extends Pattern {
    int min_length;

    public VarListPat(int min) {
        this.min_length = min;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        Object obj2 = obj;
        Object[] vars = objArr;
        int start_vars = i;
        int i2 = 0;
        while (i2 < this.min_length) {
            if (!(obj2 instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) obj2;
            vars[start_vars + i2] = p.getCar();
            obj2 = p.getCdr();
            i2++;
        }
        vars[start_vars + i2] = obj2;
        return true;
    }

    public int varCount() {
        return this.min_length + 1;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<varlist-pattern min:");
        out.writeInt(this.min_length);
        out.write(62);
    }
}
