package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class ArraySet extends ProcedureN {
    public static final ArraySet arraySet;

    public ArraySet() {
    }

    static {
        ArraySet arraySet2;
        new ArraySet();
        arraySet = arraySet2;
    }

    public static void arraySet(Array array, Sequence sequence, Object obj) {
        Array array2 = array;
        Sequence index = sequence;
        Object value = obj;
        int dims = index.size();
        int[] indexes = new int[dims];
        for (int i = 0; i < dims; i++) {
            indexes[i] = ((Number) index.get(i)).intValue();
        }
        Object obj2 = array2.set(indexes, value);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        Object arg0 = obj;
        Object arg1 = obj2;
        Object arg2 = obj3;
        if (!(arg1 instanceof Sequence)) {
            return super.apply3(arg0, arg1, arg2);
        }
        arraySet((Array) arg0, (Sequence) arg1, arg2);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Object[] args = objArr;
        Array array = (Array) args[0];
        if (args.length == 3) {
            Object arg1 = args[1];
            if (arg1 instanceof Sequence) {
                arraySet(array, (Sequence) arg1, args[2]);
                return Values.empty;
            }
        }
        int dim = args.length - 2;
        int[] indexes = new int[dim];
        int i = dim;
        while (true) {
            i--;
            if (i >= 0) {
                indexes[i] = ((Number) args[i + 1]).intValue();
            } else {
                Object obj = array.set(indexes, args[dim + 1]);
                return Values.empty;
            }
        }
    }
}
