package gnu.text;

import gnu.kawa.util.AbstractWeakHashTable;

/* compiled from: Char */
class CharMap extends AbstractWeakHashTable<Char, Char> {
    CharMap() {
    }

    public Char get(int i) {
        Char charR;
        int key = i;
        cleanup();
        AbstractWeakHashTable.WEntry<Char, Char> wEntry = ((AbstractWeakHashTable.WEntry[]) this.table)[hashToIndex(key)];
        while (true) {
            AbstractWeakHashTable.WEntry<Char, Char> node = wEntry;
            if (node != null) {
                Char val = node.getValue();
                if (val != null && val.intValue() == key) {
                    return val;
                }
                wEntry = node.next;
            } else {
                new Char(key);
                Char val2 = charR;
                Object put = super.put(val2, val2);
                return val2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Char getKeyFromValue(Char ch) {
        return ch;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Char oldValue, Char newValue) {
        return oldValue.intValue() == newValue.intValue();
    }
}
