package gnu.lists;

public abstract class ExtSequence extends AbstractSequence {
    public ExtSequence() {
    }

    public int copyPos(int i) {
        int ipos = i;
        if (ipos <= 0) {
            return ipos;
        }
        return PositionManager.manager.register(PositionManager.getPositionObject(ipos).copy());
    }

    /* access modifiers changed from: protected */
    public void releasePos(int i) {
        int ipos = i;
        if (ipos > 0) {
            PositionManager.manager.release(ipos);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        int ipos = i;
        if (ipos <= 0) {
            return ipos < 0;
        }
        return (PositionManager.getPositionObject(ipos).ipos & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        int ipos = i;
        return ipos == -1 ? size() : ipos == 0 ? 0 : PositionManager.getPositionObject(ipos).nextIndex();
    }
}
