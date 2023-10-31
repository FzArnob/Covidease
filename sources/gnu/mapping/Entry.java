package gnu.mapping;

import java.lang.ref.WeakReference;

/* compiled from: Table2D */
class Entry {
    Entry chain;
    Object key1;
    Object key2;
    Object value;

    Entry() {
    }

    public Object getKey1() {
        if (this.key1 instanceof WeakReference) {
            return ((WeakReference) this.key1).get();
        }
        return this.key1;
    }

    public Object getKey2() {
        if (this.key2 instanceof WeakReference) {
            return ((WeakReference) this.key2).get();
        }
        return this.key2;
    }

    public boolean matches(Object key12, Object key22) {
        return key12 == getKey1() && key22 == getKey2();
    }

    public Object getValue() {
        return this.value == this ? null : this.value;
    }
}
