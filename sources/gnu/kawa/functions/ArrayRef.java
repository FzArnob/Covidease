package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;

public class ArrayRef extends ProcedureN {
    public static final ArrayRef arrayRef;

    public ArrayRef() {
    }

    static {
        ArrayRef arrayRef2;
        new ArrayRef();
        arrayRef = arrayRef2;
    }

    public static Object arrayRef(Array array, Sequence sequence) {
        Array array2 = array;
        Sequence index = sequence;
        int dims = index.size();
        int[] indexes = new int[dims];
        for (int i = 0; i < dims; i++) {
            indexes[i] = ((Number) index.get(i)).intValue();
        }
        return array2.get(indexes);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Object arg0 = obj;
        Object arg1 = obj2;
        if (arg1 instanceof Sequence) {
            return arrayRef((Array) arg0, (Sequence) arg1);
        }
        return super.apply2(arg0, arg1);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Object[] args = objArr;
        Array array = (Array) args[0];
        if (args.length == 2) {
            Object arg1 = args[1];
            if (arg1 instanceof Sequence) {
                return arrayRef(array, (Sequence) arg1);
            }
        }
        int[] indexes = new int[(args.length - 1)];
        int i = args.length - 1;
        while (true) {
            i--;
            if (i < 0) {
                return array.get(indexes);
            }
            indexes[i] = ((Number) args[i + 1]).intValue();
        }
    }
}
