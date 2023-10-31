package gnu.lists;

public class GeneralArray extends AbstractSequence implements Array {
    static final int[] zeros = new int[8];
    SimpleVector base;
    int[] dimensions;
    int[] lowBounds;
    int offset;
    boolean simple = true;
    int[] strides;

    public static Array makeSimple(int[] iArr, int[] iArr2, SimpleVector simpleVector) {
        GeneralArray generalArray;
        int[] lowBounds2 = iArr;
        int[] dimensions2 = iArr2;
        SimpleVector base2 = simpleVector;
        int d = dimensions2.length;
        if (lowBounds2 == null) {
            lowBounds2 = zeros;
            if (d > lowBounds2.length) {
                lowBounds2 = new int[d];
            }
        }
        if (d == 1 && lowBounds2[0] == 0) {
            return base2;
        }
        new GeneralArray();
        GeneralArray array = generalArray;
        int[] strides2 = new int[d];
        int n = 1;
        int i = d;
        while (true) {
            i--;
            if (i >= 0) {
                strides2[i] = n;
                n *= dimensions2[i];
            } else {
                array.strides = strides2;
                array.dimensions = dimensions2;
                array.lowBounds = lowBounds2;
                array.base = base2;
                return array;
            }
        }
    }

    public GeneralArray() {
    }

    public GeneralArray(int[] iArr) {
        SimpleVector simpleVector;
        int[] dimensions2 = iArr;
        int total = 1;
        int rank = dimensions2.length;
        if (rank <= zeros.length) {
            this.lowBounds = zeros;
        } else {
            this.lowBounds = new int[rank];
        }
        int[] strides2 = new int[rank];
        int i = rank;
        while (true) {
            i--;
            if (i >= 0) {
                strides2[i] = total;
                total *= dimensions2[i];
            } else {
                new FVector(total);
                this.base = simpleVector;
                this.dimensions = dimensions2;
                this.offset = 0;
                return;
            }
        }
    }

    public int rank() {
        return this.dimensions.length;
    }

    public int getEffectiveIndex(int[] iArr) {
        Throwable th;
        int[] indexes = iArr;
        int result = this.offset;
        int i = this.dimensions.length;
        while (true) {
            i--;
            if (i >= 0) {
                int index = indexes[i];
                int low = this.lowBounds[i];
                if (index < low) {
                    break;
                }
                int i2 = index - low;
                int index2 = i2;
                if (i2 >= this.dimensions[i]) {
                    break;
                }
                result += this.strides[i] * index2;
            } else {
                return result;
            }
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public Object get(int index) {
        return getRowMajor(index);
    }

    public int createPos(int i, boolean z) {
        int index = i;
        boolean isAfter = z;
        int total = this.offset;
        int i2 = this.dimensions.length;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            int dim = this.dimensions[i2];
            int cur = index % dim;
            index /= dim;
            total += this.strides[i2] * cur;
        }
        return (total << 1) | (isAfter ? 1 : 0);
    }

    public Object getRowMajor(int i) {
        int index = i;
        if (this.simple) {
            return this.base.get(index);
        }
        int total = this.offset;
        int i2 = this.dimensions.length;
        while (true) {
            i2--;
            if (i2 < 0) {
                return this.base.get(total);
            }
            int dim = this.dimensions[i2];
            int cur = index % dim;
            index /= dim;
            total += this.strides[i2] * cur;
        }
    }

    public Object get(int[] indexes) {
        return this.base.get(getEffectiveIndex(indexes));
    }

    public Object set(int[] indexes, Object value) {
        return this.base.set(getEffectiveIndex(indexes), value);
    }

    public int size() {
        int total = 1;
        int i = this.dimensions.length;
        while (true) {
            i--;
            if (i < 0) {
                return total;
            }
            total *= this.dimensions[i];
        }
    }

    public int getLowBound(int dim) {
        return this.lowBounds[dim];
    }

    public int getSize(int dim) {
        return this.dimensions[dim];
    }

    public Array transpose(int[] iArr, int[] iArr2, int i, int[] iArr3) {
        GeneralArray generalArray;
        GeneralArray generalArray2;
        GeneralArray generalArray3;
        int[] lowBounds2 = iArr;
        int[] dimensions2 = iArr2;
        int offset0 = i;
        int[] factors = iArr3;
        if (dimensions2.length == 1 && lowBounds2[0] == 0) {
            generalArray2 = generalArray3;
            new GeneralArray1();
        } else {
            generalArray2 = generalArray;
            new GeneralArray();
        }
        GeneralArray array = generalArray2;
        array.offset = offset0;
        array.strides = factors;
        array.dimensions = dimensions2;
        array.lowBounds = lowBounds2;
        array.base = this.base;
        array.simple = false;
        return array;
    }

    public static void toString(Array array, StringBuffer stringBuffer) {
        Array array2 = array;
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("#<array");
        int r = array2.rank();
        for (int i = 0; i < r; i++) {
            StringBuffer append2 = sbuf.append(' ');
            int lo = array2.getLowBound(i);
            int sz = array2.getSize(i);
            if (lo != 0) {
                StringBuffer append3 = sbuf.append(lo);
                StringBuffer append4 = sbuf.append(':');
            }
            StringBuffer append5 = sbuf.append(lo + sz);
        }
        StringBuffer append6 = sbuf.append('>');
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        toString(this, sbuf);
        return sbuf.toString();
    }
}
