package gnu.kawa.util;

import java.util.Hashtable;

public class RangeTable implements Cloneable {
    Hashtable hash;
    Object[] index = new Object[128];

    public RangeTable() {
        Hashtable hashtable;
        new Hashtable(200);
        this.hash = hashtable;
    }

    public Object lookup(int i, Object obj) {
        Object obj2;
        int key = i;
        Object obj3 = obj;
        if ((key & 127) == key) {
            return this.index[key];
        }
        new Integer(key);
        return this.hash.get(obj2);
    }

    public void set(int i, int i2, Object obj) {
        Object obj2;
        int lo = i;
        int hi = i2;
        Object value = obj;
        if (lo <= hi) {
            int i3 = lo;
            while (true) {
                if ((i3 & 127) == i3) {
                    this.index[i3] = value;
                } else {
                    new Integer(i3);
                    Object put = this.hash.put(obj2, value);
                }
                if (i3 != hi) {
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void set(int i, Object value) {
        int key = i;
        set(key, key, value);
    }

    public void remove(int i, int i2) {
        Object obj;
        int lo = i;
        int hi = i2;
        if (lo <= hi) {
            int i3 = lo;
            while (true) {
                if ((i3 & 127) == i3) {
                    this.index[i3] = null;
                } else {
                    new Integer(i3);
                    Object remove = this.hash.remove(obj);
                }
                if (i3 != hi) {
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void remove(int i) {
        int key = i;
        remove(key, key);
    }

    public RangeTable copy() {
        RangeTable rangeTable;
        new RangeTable();
        RangeTable copy = rangeTable;
        copy.index = (Object[]) this.index.clone();
        copy.hash = (Hashtable) this.hash.clone();
        return copy;
    }

    public Object clone() {
        return copy();
    }
}
