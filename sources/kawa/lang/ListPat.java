package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.ReportFormat;

public class ListPat extends Pattern {
    Object default_value;
    int max_length;
    int min_length;

    public ListPat(int i) {
        int len = i;
        this.min_length = len;
        this.max_length = len;
    }

    public ListPat(int min, int max) {
        this.min_length = min;
        this.max_length = max;
    }

    public ListPat(int min, int max, Object default_val) {
        this.min_length = min;
        this.max_length = max;
        this.default_value = default_val;
    }

    public static boolean match(int i, int i2, Object obj, Object obj2, Object[] objArr, int i3) {
        int min = i;
        int max = i2;
        Object default_val = obj;
        Object obj3 = obj2;
        Object[] vars = objArr;
        int start_vars = i3;
        int i4 = 0;
        while (true) {
            if (i4 >= max) {
                break;
            } else if (obj3 instanceof Pair) {
                Pair p = (Pair) obj3;
                vars[start_vars + i4] = p.getCar();
                obj3 = p.getCdr();
                i4++;
            } else if (i4 < min) {
                return false;
            }
        }
        if (i4 == max && obj3 != LList.Empty) {
            return false;
        }
        while (i4 < max) {
            vars[start_vars + i4] = default_val;
            i4++;
        }
        return true;
    }

    public static Object[] match(int min, int i, Object default_val, Object obj) {
        int max = i;
        Object[] vars = new Object[max];
        return match(min, max, default_val, obj, vars, 0) ? vars : null;
    }

    public boolean match(Object obj, Object[] vars, int start_vars) {
        return match(this.min_length, this.max_length, this.default_value, obj, vars, start_vars);
    }

    public int varCount() {
        return this.max_length;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<list-pattern min:");
        out.write(Integer.toString(this.min_length));
        out.write(" max:");
        out.write(Integer.toString(this.max_length));
        out.write(" default:");
        ReportFormat.print(this.default_value, out);
        out.write(62);
    }
}
