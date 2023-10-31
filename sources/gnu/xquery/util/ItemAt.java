package gnu.xquery.util;

import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class ItemAt extends Procedure2 {
    public static final ItemAt itemAt;

    public ItemAt() {
    }

    static {
        ItemAt itemAt2;
        new ItemAt();
        itemAt = itemAt2;
    }

    public static Object itemAt(Object obj, int i) {
        Throwable th;
        Object seq = obj;
        int index = i;
        if (seq instanceof Values) {
            Values vals = (Values) seq;
            if (vals.isEmpty()) {
                return Values.empty;
            }
            return vals.get(index - 1);
        } else if (index == 1) {
            return seq;
        } else {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
    }

    public Object apply2(Object arg1, Object arg2) {
        return itemAt(arg1, ((Number) arg2).intValue());
    }
}
