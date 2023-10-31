package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ListRepeatPat extends Pattern implements Printable, Externalizable {
    Pattern element_pattern;

    public ListRepeatPat() {
    }

    public ListRepeatPat(Pattern element_pattern2) {
        this.element_pattern = element_pattern2;
    }

    public static ListRepeatPat make(Pattern element_pattern2) {
        ListRepeatPat listRepeatPat;
        new ListRepeatPat(element_pattern2);
        return listRepeatPat;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<list-repeat-pattern ");
        this.element_pattern.print(out);
        out.write(62);
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        Object obj2 = obj;
        Object[] vars = objArr;
        int start_vars = i;
        int length = LList.listLength(obj2, false);
        if (length < 0) {
            return false;
        }
        int var_count = this.element_pattern.varCount();
        int i2 = var_count;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            vars[start_vars + i2] = new Object[length];
        }
        Object[] element_vars = new Object[var_count];
        for (int j = 0; j < length; j++) {
            Pair pair = (Pair) obj2;
            if (!this.element_pattern.match(pair.getCar(), element_vars, 0)) {
                return false;
            }
            for (int i3 = 0; i3 < var_count; i3++) {
                ((Object[]) vars[start_vars + i3])[j] = element_vars[i3];
            }
            obj2 = pair.getCdr();
        }
        return true;
    }

    public int varCount() {
        return this.element_pattern.varCount();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.element_pattern);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Pattern pattern = (Pattern) in.readObject();
        this.element_pattern = pattern;
    }
}
