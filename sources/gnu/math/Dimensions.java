package gnu.math;

public class Dimensions {
    public static Dimensions Empty;
    private static Dimensions[] hashTable = new Dimensions[100];
    BaseUnit[] bases;
    private Dimensions chain;
    int hash_code;
    short[] powers;

    static {
        Dimensions dimensions;
        new Dimensions();
        Empty = dimensions;
    }

    public final int hashCode() {
        return this.hash_code;
    }

    private void enterHash(int i) {
        int hash_code2 = i;
        this.hash_code = hash_code2;
        int index = (hash_code2 & Integer.MAX_VALUE) % hashTable.length;
        this.chain = hashTable[index];
        hashTable[index] = this;
    }

    private Dimensions() {
        this.bases = new BaseUnit[1];
        this.bases[0] = Unit.Empty;
        enterHash(0);
    }

    Dimensions(BaseUnit baseUnit) {
        BaseUnit unit = baseUnit;
        this.bases = new BaseUnit[2];
        this.powers = new short[1];
        this.bases[0] = unit;
        this.bases[1] = Unit.Empty;
        this.powers[0] = 1;
        enterHash(unit.index);
    }

    private Dimensions(Dimensions dimensions, int i, Dimensions dimensions2, int i2, int i3) {
        int pow;
        Throwable th;
        Dimensions a = dimensions;
        int mul_a = i;
        Dimensions b = dimensions2;
        int mul_b = i2;
        int hash_code2 = i3;
        this.hash_code = hash_code2;
        int a_i = 0;
        while (a.bases[a_i] != Unit.Empty) {
            a_i++;
        }
        int b_i = 0;
        while (b.bases[b_i] != Unit.Empty) {
            b_i++;
        }
        int t_i = a_i + b_i + 1;
        this.bases = new BaseUnit[t_i];
        this.powers = new short[t_i];
        int t_i2 = 0;
        int b_i2 = 0;
        int a_i2 = 0;
        while (true) {
            BaseUnit a_base = a.bases[a_i2];
            BaseUnit b_base = b.bases[b_i2];
            if (a_base.index < b_base.index) {
                pow = a.powers[a_i2] * mul_a;
                a_i2++;
            } else if (b_base.index < a_base.index) {
                a_base = b_base;
                pow = b.powers[b_i2] * mul_b;
                b_i2++;
            } else if (b_base == Unit.Empty) {
                this.bases[t_i2] = Unit.Empty;
                enterHash(hash_code2);
                return;
            } else {
                pow = (a.powers[a_i2] * mul_a) + (b.powers[b_i2] * mul_b);
                a_i2++;
                b_i2++;
                if (pow == 0) {
                    continue;
                }
            }
            if (((short) pow) != pow) {
                Throwable th2 = th;
                new ArithmeticException("overflow in dimensions");
                throw th2;
            }
            this.bases[t_i2] = a_base;
            int i4 = t_i2;
            t_i2++;
            this.powers[i4] = (short) pow;
        }
    }

    private boolean matchesProduct(Dimensions dimensions, int i, Dimensions dimensions2, int i2) {
        int pow;
        Dimensions a = dimensions;
        int mul_a = i;
        Dimensions b = dimensions2;
        int mul_b = i2;
        int a_i = 0;
        int b_i = 0;
        int t_i = 0;
        while (true) {
            BaseUnit a_base = a.bases[a_i];
            BaseUnit b_base = b.bases[b_i];
            if (a_base.index < b_base.index) {
                pow = a.powers[a_i] * mul_a;
                a_i++;
            } else if (b_base.index < a_base.index) {
                a_base = b_base;
                pow = b.powers[b_i] * mul_b;
                b_i++;
            } else if (b_base == Unit.Empty) {
                return this.bases[t_i] == b_base;
            } else {
                pow = (a.powers[a_i] * mul_a) + (b.powers[b_i] * mul_b);
                a_i++;
                b_i++;
                if (pow == 0) {
                    continue;
                }
            }
            if (this.bases[t_i] == a_base && this.powers[t_i] == pow) {
                t_i++;
            }
        }
        return false;
    }

    public static Dimensions product(Dimensions dimensions, int i, Dimensions dimensions2, int i2) {
        Dimensions a;
        Dimensions a2 = dimensions;
        int mul_a = i;
        Dimensions b = dimensions2;
        int mul_b = i2;
        int hash = (a2.hashCode() * mul_a) + (b.hashCode() * mul_b);
        Dimensions dimensions3 = hashTable[(hash & Integer.MAX_VALUE) % hashTable.length];
        while (true) {
            Dimensions dim = dimensions3;
            if (dim == null) {
                new Dimensions(a2, mul_a, b, mul_b, hash);
                return a;
            } else if (dim.hash_code == hash && dim.matchesProduct(a2, mul_a, b, mul_b)) {
                return dim;
            } else {
                dimensions3 = dim.chain;
            }
        }
    }

    public int getPower(BaseUnit baseUnit) {
        BaseUnit unit = baseUnit;
        for (int i = 0; this.bases[i].index <= unit.index; i++) {
            if (this.bases[i] == unit) {
                return this.powers[i];
            }
        }
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        for (int i = 0; this.bases[i] != Unit.Empty; i++) {
            if (i > 0) {
                StringBuffer append = buf.append('*');
            }
            StringBuffer append2 = buf.append(this.bases[i]);
            int pow = this.powers[i];
            if (pow != 1) {
                StringBuffer append3 = buf.append('^');
                StringBuffer append4 = buf.append(pow);
            }
        }
        return buf.toString();
    }
}
