package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CpoolEntry {
    int hash;
    public int index;
    CpoolEntry next;

    public abstract int getTag();

    public abstract void print(ClassTypeWriter classTypeWriter, int i);

    /* access modifiers changed from: package-private */
    public abstract void write(DataOutputStream dataOutputStream) throws IOException;

    public int getIndex() {
        return this.index;
    }

    public int hashCode() {
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void add_hashed(ConstantPool cpool) {
        CpoolEntry[] hashTab = cpool.hashTab;
        int index2 = (this.hash & Integer.MAX_VALUE) % hashTab.length;
        this.next = hashTab[index2];
        hashTab[index2] = this;
    }

    protected CpoolEntry() {
    }

    public CpoolEntry(ConstantPool constantPool, int h) {
        Throwable th;
        ConstantPool cpool = constantPool;
        this.hash = h;
        if (cpool.locked) {
            Throwable th2 = th;
            new Error("adding new entry to locked contant pool");
            throw th2;
        }
        ConstantPool constantPool2 = cpool;
        int i = constantPool2.count + 1;
        constantPool2.count = i;
        this.index = i;
        if (cpool.pool == null) {
            cpool.pool = new CpoolEntry[60];
        } else if (this.index >= cpool.pool.length) {
            int old_size = cpool.pool.length;
            CpoolEntry[] new_pool = new CpoolEntry[(2 * cpool.pool.length)];
            for (int i2 = 0; i2 < old_size; i2++) {
                new_pool[i2] = cpool.pool[i2];
            }
            cpool.pool = new_pool;
        }
        if (cpool.hashTab == null || ((double) this.index) >= 0.6d * ((double) cpool.hashTab.length)) {
            cpool.rehash();
        }
        cpool.pool[this.index] = this;
        add_hashed(cpool);
    }
}
