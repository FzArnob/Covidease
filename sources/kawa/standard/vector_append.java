package kawa.standard;

import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class vector_append extends ProcedureN {
    public static final vector_append vectorAppend;

    static {
        vector_append vector_append;
        new vector_append("vector-append");
        vectorAppend = vector_append;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public vector_append(String name) {
        super(name);
    }

    public Object applyN(Object[] args) {
        return apply$V(args);
    }

    public static FVector apply$V(Object[] objArr) {
        FVector fVector;
        Throwable th;
        Object[] args = objArr;
        int length = 0;
        int args_length = args.length;
        int i = args_length;
        while (true) {
            i--;
            if (i >= 0) {
                Object arg = args[i];
                if (arg instanceof FVector) {
                    length += ((FVector) arg).size();
                } else {
                    int n = LList.listLength(arg, false);
                    if (n < 0) {
                        Throwable th2 = th;
                        new WrongType((Procedure) vectorAppend, i, arg, "list or vector");
                        throw th2;
                    }
                    length += n;
                }
            } else {
                Object[] result = new Object[length];
                int position = 0;
                for (int i2 = 0; i2 < args_length; i2++) {
                    Object arg2 = args[i2];
                    if (arg2 instanceof FVector) {
                        FVector vec = (FVector) arg2;
                        int vec_length = vec.size();
                        for (int j = 0; j < vec_length; j++) {
                            int i3 = position;
                            position++;
                            result[i3] = vec.get(j);
                        }
                    } else if (arg2 instanceof Pair) {
                        while (arg2 != LList.Empty) {
                            Pair pair = (Pair) arg2;
                            int i4 = position;
                            position++;
                            result[i4] = pair.getCar();
                            arg2 = pair.getCdr();
                        }
                    }
                }
                new FVector(result);
                return fVector;
            }
        }
    }
}
