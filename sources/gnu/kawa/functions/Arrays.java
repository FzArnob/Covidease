package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.lists.GeneralArray;
import gnu.lists.SimpleVector;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Arrays {
    static final int[] shapeStrides = {2, 1};
    static final int[] zeros2 = new int[2];

    public Arrays() {
    }

    public static Array shape(Object[] objArr) {
        FVector fVector;
        Throwable th;
        Object[] vals = objArr;
        int len = vals.length;
        if ((len & 1) != 0) {
            Throwable th2 = th;
            new RuntimeException("shape: not an even number of arguments");
            throw th2;
        }
        int[] iArr = new int[2];
        iArr[0] = len >> 1;
        int[] iArr2 = iArr;
        iArr2[1] = 2;
        int[] dims = iArr2;
        new FVector(vals);
        return fVector.transpose(zeros2, dims, 0, shapeStrides);
    }

    public static Array make(Array array, Object obj) {
        SimpleVector simpleVector;
        Array shape = array;
        Object value = obj;
        int rank = shape.getSize(0);
        int[] dimensions = new int[rank];
        int[] lowBounds = null;
        int total = 1;
        int i = rank;
        while (true) {
            i--;
            if (i >= 0) {
                int lo = ((Number) shape.getRowMajor(2 * i)).intValue();
                int size = ((Number) shape.getRowMajor((2 * i) + 1)).intValue() - lo;
                dimensions[i] = size;
                if (lo != 0) {
                    if (lowBounds == null) {
                        lowBounds = new int[rank];
                    }
                    lowBounds[i] = lo;
                }
                total *= size;
            } else {
                new FVector(total, value);
                return GeneralArray.makeSimple(lowBounds, dimensions, simpleVector);
            }
        }
    }

    public static Array makeSimple(Array array, SimpleVector simpleVector) {
        Array shape = array;
        SimpleVector base = simpleVector;
        int rank = shape.getSize(0);
        int[] dimensions = new int[rank];
        int[] lowBounds = null;
        int i = rank;
        while (true) {
            i--;
            if (i < 0) {
                return GeneralArray.makeSimple(lowBounds, dimensions, base);
            }
            int lo = ((Number) shape.getRowMajor(2 * i)).intValue();
            dimensions[i] = ((Number) shape.getRowMajor((2 * i) + 1)).intValue() - lo;
            if (lo != 0) {
                if (lowBounds == null) {
                    lowBounds = new int[rank];
                }
                lowBounds[i] = lo;
            }
        }
    }

    public static int effectiveIndex(Array array, Procedure proc, Object[] args, int[] iArr) throws Throwable {
        Array array2 = array;
        int[] work = iArr;
        Object mapval = proc.applyN(args);
        if (mapval instanceof Values) {
            Values mapvals = (Values) mapval;
            int i = 0;
            int j = 0;
            while (true) {
                int nextPos = mapvals.nextPos(i);
                i = nextPos;
                if (nextPos == 0) {
                    break;
                }
                work[j] = ((Number) mapvals.getPosPrevious(i)).intValue();
                j++;
            }
        } else {
            work[0] = ((Number) mapval).intValue();
        }
        return array2.getEffectiveIndex(work);
    }

    public static Array shareArray(Array array, Array array2, Procedure procedure) throws Throwable {
        int offset0;
        Array array3 = array;
        Array shape = array2;
        Procedure proc = procedure;
        int rank = shape.getSize(0);
        Object[] args = new Object[rank];
        int[] dimensions = new int[rank];
        int[] lowBounds = new int[rank];
        boolean empty = false;
        int i = rank;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Object low = shape.getRowMajor(2 * i);
            args[i] = low;
            int lo = ((Number) low).intValue();
            lowBounds[i] = lo;
            int size = ((Number) shape.getRowMajor((2 * i) + 1)).intValue() - lo;
            dimensions[i] = size;
            if (size <= 0) {
                empty = true;
            }
        }
        int arank = array3.rank();
        int[] offsets = new int[rank];
        if (!empty) {
            int[] work = new int[arank];
            offset0 = effectiveIndex(array3, proc, args, work);
            int i2 = rank;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                int size2 = dimensions[i2];
                int lo2 = lowBounds[i2];
                if (size2 <= 1) {
                    offsets[i2] = 0;
                } else {
                    Object low2 = args[i2];
                    args[i2] = IntNum.make(lo2 + 1);
                    offsets[i2] = effectiveIndex(array3, proc, args, work) - offset0;
                    args[i2] = low2;
                }
            }
        } else {
            offset0 = 0;
        }
        return array3.transpose(lowBounds, dimensions, offset0, offsets);
    }
}
