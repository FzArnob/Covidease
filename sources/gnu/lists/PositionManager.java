package gnu.lists;

public class PositionManager {
    static final PositionManager manager;
    int freeListHead = -1;
    int[] ivals = new int[50];
    SeqPosition[] positions = new SeqPosition[50];

    static {
        PositionManager positionManager;
        new PositionManager();
        manager = positionManager;
    }

    public static SeqPosition getPositionObject(int i) {
        int ipos = i;
        PositionManager m = manager;
        PositionManager positionManager = m;
        PositionManager positionManager2 = positionManager;
        synchronized (positionManager) {
            try {
                SeqPosition seqPosition = m.positions[ipos];
                return seqPosition;
            } catch (Throwable th) {
                Throwable th2 = th;
                PositionManager positionManager3 = positionManager2;
                throw th2;
            }
        }
    }

    private void addToFreeList(int[] iArr, int first, int i) {
        int[] ivals2 = iArr;
        int end = i;
        int head = this.freeListHead;
        for (int i2 = first; i2 < end; i2++) {
            ivals2[i2] = head;
            head = i2;
        }
        this.freeListHead = head;
    }

    private int getFreeSlot() {
        int head = this.freeListHead;
        if (head < 0) {
            int old_size = this.positions.length;
            SeqPosition[] npositions = new SeqPosition[(2 * old_size)];
            int[] nvals = new int[(2 * old_size)];
            System.arraycopy(this.positions, 0, npositions, 0, old_size);
            System.arraycopy(this.ivals, 0, nvals, 0, old_size);
            this.positions = npositions;
            this.ivals = nvals;
            addToFreeList(nvals, old_size, 2 * old_size);
            head = this.freeListHead;
        }
        this.freeListHead = this.ivals[head];
        return head;
    }

    public PositionManager() {
        addToFreeList(this.ivals, 1, this.ivals.length);
    }

    public synchronized int register(SeqPosition seqPosition) {
        int i;
        SeqPosition pos = seqPosition;
        synchronized (this) {
            int i2 = getFreeSlot();
            this.positions[i2] = pos;
            this.ivals[i2] = -1;
            i = i2;
        }
        return i;
    }

    public synchronized void release(int i) {
        int ipos = i;
        synchronized (this) {
            SeqPosition pos = this.positions[ipos];
            if (pos instanceof ExtPosition) {
                ((ExtPosition) pos).position = -1;
            }
            this.positions[ipos] = null;
            this.ivals[ipos] = this.freeListHead;
            this.freeListHead = ipos;
            pos.release();
        }
    }
}
