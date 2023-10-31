package kawa.standard;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class append extends ProcedureN {
    public static final append append;

    public append() {
    }

    static {
        append append2;
        new append();
        append = append2;
        append.setName("append");
    }

    public Object applyN(Object[] args) {
        return append$V(args);
    }

    public static Object append$V(Object[] objArr) {
        Throwable th;
        Object obj;
        Object[] args = objArr;
        int count = args.length;
        if (count == 0) {
            return LList.Empty;
        }
        Object result = args[count - 1];
        int i = count - 1;
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            Object list = args[i];
            Object copy = null;
            Object obj2 = null;
            while (list instanceof Pair) {
                Pair list_pair = (Pair) list;
                new Pair(list_pair.getCar(), (Object) null);
                Object obj3 = obj;
                if (obj2 == null) {
                    copy = obj3;
                } else {
                    obj2.setCdr(obj3);
                }
                obj2 = obj3;
                list = list_pair.getCdr();
            }
            if (list != LList.Empty) {
                Throwable th2 = th;
                new WrongType((Procedure) append, i + 1, args[i], "list");
                throw th2;
            } else if (obj2 != null) {
                obj2.setCdr(result);
                result = copy;
            }
        }
    }
}
