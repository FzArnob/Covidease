package android.support.p003v7.widget;

import android.support.p000v4.util.Pools;
import android.support.p003v7.widget.OpReorderer;
import android.support.p003v7.widget.RecyclerView;
import com.google.appinventor.components.runtime.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: android.support.v7.widget.AdapterHelper */
class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    /* renamed from: android.support.v7.widget.AdapterHelper$Callback */
    interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i);

        void markViewHoldersUpdated(int i, int i2, Object obj);

        void offsetPositionsForAdd(int i, int i2);

        void offsetPositionsForMove(int i, int i2);

        void offsetPositionsForRemovingInvisible(int i, int i2);

        void offsetPositionsForRemovingLaidOutOrNewView(int i, int i2);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean disableRecycler) {
        Pools.Pool<UpdateOp> pool;
        ArrayList<UpdateOp> arrayList;
        ArrayList<UpdateOp> arrayList2;
        OpReorderer opReorderer;
        new Pools.SimplePool(30);
        this.mUpdateOpPool = pool;
        new ArrayList<>();
        this.mPendingUpdates = arrayList;
        new ArrayList<>();
        this.mPostponedList = arrayList2;
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = disableRecycler;
        new OpReorderer(this);
        this.mOpReorderer = opReorderer;
    }

    /* access modifiers changed from: package-private */
    public AdapterHelper addUpdateOp(UpdateOp... ops) {
        boolean addAll = Collections.addAll(this.mPendingUpdates, ops);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    /* access modifiers changed from: package-private */
    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int count = this.mPendingUpdates.size();
        for (int i = 0; i < count; i++) {
            UpdateOp op = this.mPendingUpdates.get(i);
            switch (op.cmd) {
                case 1:
                    applyAdd(op);
                    break;
                case 2:
                    applyRemove(op);
                    break;
                case 4:
                    applyUpdate(op);
                    break;
                case 8:
                    applyMove(op);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    /* access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int count = this.mPostponedList.size();
        for (int i = 0; i < count; i++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    private void applyMove(UpdateOp op) {
        postponeAndUpdateViewHolders(op);
    }

    private void applyRemove(UpdateOp updateOp) {
        UpdateOp op = updateOp;
        int tmpStart = op.positionStart;
        int tmpCount = 0;
        int tmpEnd = op.positionStart + op.itemCount;
        int type = -1;
        int position = op.positionStart;
        while (position < tmpEnd) {
            boolean typeChanged = false;
            if (this.mCallback.findViewHolder(position) != null || canFindInPreLayout(position)) {
                if (type == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, tmpStart, tmpCount, (Object) null));
                    typeChanged = true;
                }
                type = 1;
            } else {
                if (type == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, tmpStart, tmpCount, (Object) null));
                    typeChanged = true;
                }
                type = 0;
            }
            if (typeChanged) {
                position -= tmpCount;
                tmpEnd -= tmpCount;
                tmpCount = 1;
            } else {
                tmpCount++;
            }
            position++;
        }
        if (tmpCount != op.itemCount) {
            recycleUpdateOp(op);
            op = obtainUpdateOp(2, tmpStart, tmpCount, (Object) null);
        }
        if (type == 0) {
            dispatchAndUpdateViewHolders(op);
        } else {
            postponeAndUpdateViewHolders(op);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int i;
        UpdateOp op = updateOp;
        int tmpStart = op.positionStart;
        int tmpCount = 0;
        int tmpEnd = op.positionStart + op.itemCount;
        int type = -1;
        for (int position = op.positionStart; position < tmpEnd; position++) {
            if (this.mCallback.findViewHolder(position) != null || canFindInPreLayout(position)) {
                if (type == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, tmpStart, tmpCount, op.payload));
                    tmpCount = 0;
                    tmpStart = position;
                }
                i = 1;
            } else {
                if (type == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, tmpStart, tmpCount, op.payload));
                    tmpCount = 0;
                    tmpStart = position;
                }
                i = 0;
            }
            type = i;
            tmpCount++;
        }
        if (tmpCount != op.itemCount) {
            Object payload = op.payload;
            recycleUpdateOp(op);
            op = obtainUpdateOp(4, tmpStart, tmpCount, payload);
        }
        if (type == 0) {
            dispatchAndUpdateViewHolders(op);
        } else {
            postponeAndUpdateViewHolders(op);
        }
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        Throwable th;
        int positionMultiplier;
        Throwable th2;
        StringBuilder sb;
        UpdateOp op = updateOp;
        if (op.cmd == 1 || op.cmd == 8) {
            Throwable th3 = th;
            new IllegalArgumentException("should not dispatch add or move for pre layout");
            throw th3;
        }
        int tmpStart = updatePositionWithPostponed(op.positionStart, op.cmd);
        int tmpCnt = 1;
        int offsetPositionForPartial = op.positionStart;
        switch (op.cmd) {
            case 2:
                positionMultiplier = 0;
                break;
            case 4:
                positionMultiplier = 1;
                break;
            default:
                Throwable th4 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb.append("op should be remove or update.").append(op).toString());
                throw th4;
        }
        for (int p = 1; p < op.itemCount; p++) {
            int updatedPos = updatePositionWithPostponed(op.positionStart + (positionMultiplier * p), op.cmd);
            boolean continuous = false;
            switch (op.cmd) {
                case 2:
                    continuous = updatedPos == tmpStart;
                    break;
                case 4:
                    continuous = updatedPos == tmpStart + 1;
                    break;
            }
            if (continuous) {
                tmpCnt++;
            } else {
                UpdateOp tmp = obtainUpdateOp(op.cmd, tmpStart, tmpCnt, op.payload);
                dispatchFirstPassAndUpdateViewHolders(tmp, offsetPositionForPartial);
                recycleUpdateOp(tmp);
                if (op.cmd == 4) {
                    offsetPositionForPartial += tmpCnt;
                }
                tmpStart = updatedPos;
                tmpCnt = 1;
            }
        }
        Object payload = op.payload;
        recycleUpdateOp(op);
        if (tmpCnt > 0) {
            UpdateOp tmp2 = obtainUpdateOp(op.cmd, tmpStart, tmpCnt, payload);
            dispatchFirstPassAndUpdateViewHolders(tmp2, offsetPositionForPartial);
            recycleUpdateOp(tmp2);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i) {
        Throwable th;
        UpdateOp op = updateOp;
        int offsetStart = i;
        this.mCallback.onDispatchFirstPass(op);
        switch (op.cmd) {
            case 2:
                this.mCallback.offsetPositionsForRemovingInvisible(offsetStart, op.itemCount);
                return;
            case 4:
                this.mCallback.markViewHoldersUpdated(offsetStart, op.itemCount, op.payload);
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
                throw th2;
        }
    }

    private int updatePositionWithPostponed(int i, int i2) {
        int start;
        int end;
        int pos = i;
        int cmd = i2;
        for (int i3 = this.mPostponedList.size() - 1; i3 >= 0; i3--) {
            UpdateOp postponed = this.mPostponedList.get(i3);
            if (postponed.cmd == 8) {
                if (postponed.positionStart < postponed.itemCount) {
                    start = postponed.positionStart;
                    end = postponed.itemCount;
                } else {
                    start = postponed.itemCount;
                    end = postponed.positionStart;
                }
                if (pos < start || pos > end) {
                    if (pos < postponed.positionStart) {
                        if (cmd == 1) {
                            postponed.positionStart++;
                            postponed.itemCount++;
                        } else if (cmd == 2) {
                            UpdateOp updateOp = postponed;
                            updateOp.positionStart--;
                            UpdateOp updateOp2 = postponed;
                            updateOp2.itemCount--;
                        }
                    }
                } else if (start == postponed.positionStart) {
                    if (cmd == 1) {
                        postponed.itemCount++;
                    } else if (cmd == 2) {
                        UpdateOp updateOp3 = postponed;
                        updateOp3.itemCount--;
                    }
                    pos++;
                } else {
                    if (cmd == 1) {
                        postponed.positionStart++;
                    } else if (cmd == 2) {
                        UpdateOp updateOp4 = postponed;
                        updateOp4.positionStart--;
                    }
                    pos--;
                }
            } else if (postponed.positionStart <= pos) {
                if (postponed.cmd == 1) {
                    pos -= postponed.itemCount;
                } else if (postponed.cmd == 2) {
                    pos += postponed.itemCount;
                }
            } else if (cmd == 1) {
                postponed.positionStart++;
            } else if (cmd == 2) {
                UpdateOp updateOp5 = postponed;
                updateOp5.positionStart--;
            }
        }
        for (int i4 = this.mPostponedList.size() - 1; i4 >= 0; i4--) {
            UpdateOp op = this.mPostponedList.get(i4);
            if (op.cmd == 8) {
                if (op.itemCount == op.positionStart || op.itemCount < 0) {
                    UpdateOp remove = this.mPostponedList.remove(i4);
                    recycleUpdateOp(op);
                }
            } else if (op.itemCount <= 0) {
                UpdateOp remove2 = this.mPostponedList.remove(i4);
                recycleUpdateOp(op);
            }
        }
        return pos;
    }

    private boolean canFindInPreLayout(int i) {
        int position = i;
        int count = this.mPostponedList.size();
        for (int i2 = 0; i2 < count; i2++) {
            UpdateOp op = this.mPostponedList.get(i2);
            if (op.cmd == 8) {
                if (findPositionOffset(op.itemCount, i2 + 1) == position) {
                    return true;
                }
            } else if (op.cmd == 1) {
                int end = op.positionStart + op.itemCount;
                for (int pos = op.positionStart; pos < end; pos++) {
                    if (findPositionOffset(pos, i2 + 1) == position) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void applyAdd(UpdateOp op) {
        postponeAndUpdateViewHolders(op);
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        Throwable th;
        StringBuilder sb;
        UpdateOp op = updateOp;
        boolean add = this.mPostponedList.add(op);
        switch (op.cmd) {
            case 1:
                this.mCallback.offsetPositionsForAdd(op.positionStart, op.itemCount);
                return;
            case 2:
                this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(op.positionStart, op.itemCount);
                return;
            case 4:
                this.mCallback.markViewHoldersUpdated(op.positionStart, op.itemCount, op.payload);
                return;
            case 8:
                this.mCallback.offsetPositionsForMove(op.positionStart, op.itemCount);
                return;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unknown update op type for ").append(op).toString());
                throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int updateTypes) {
        return (this.mExistingUpdateTypes & updateTypes) != 0;
    }

    /* access modifiers changed from: package-private */
    public int findPositionOffset(int position) {
        return findPositionOffset(position, 0);
    }

    /* access modifiers changed from: package-private */
    public int findPositionOffset(int i, int firstPostponedItem) {
        int position = i;
        int count = this.mPostponedList.size();
        for (int i2 = firstPostponedItem; i2 < count; i2++) {
            UpdateOp op = this.mPostponedList.get(i2);
            if (op.cmd == 8) {
                if (op.positionStart == position) {
                    position = op.itemCount;
                } else {
                    if (op.positionStart < position) {
                        position--;
                    }
                    if (op.itemCount <= position) {
                        position++;
                    }
                }
            } else if (op.positionStart > position) {
                continue;
            } else if (op.cmd == 2) {
                if (position < op.positionStart + op.itemCount) {
                    return -1;
                }
                position -= op.itemCount;
            } else if (op.cmd == 1) {
                position += op.itemCount;
            }
        }
        return position;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeChanged(int i, int i2, Object obj) {
        int positionStart = i;
        int itemCount = i2;
        Object payload = obj;
        if (itemCount < 1) {
            return false;
        }
        boolean add = this.mPendingUpdates.add(obtainUpdateOp(4, positionStart, itemCount, payload));
        this.mExistingUpdateTypes |= 4;
        return this.mPendingUpdates.size() == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeInserted(int i, int i2) {
        int positionStart = i;
        int itemCount = i2;
        if (itemCount < 1) {
            return false;
        }
        boolean add = this.mPendingUpdates.add(obtainUpdateOp(1, positionStart, itemCount, (Object) null));
        this.mExistingUpdateTypes |= 1;
        return this.mPendingUpdates.size() == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeRemoved(int i, int i2) {
        int positionStart = i;
        int itemCount = i2;
        if (itemCount < 1) {
            return false;
        }
        boolean add = this.mPendingUpdates.add(obtainUpdateOp(2, positionStart, itemCount, (Object) null));
        this.mExistingUpdateTypes |= 2;
        return this.mPendingUpdates.size() == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeMoved(int i, int i2, int i3) {
        Throwable th;
        int from = i;
        int to = i2;
        int itemCount = i3;
        if (from == to) {
            return false;
        }
        if (itemCount != 1) {
            Throwable th2 = th;
            new IllegalArgumentException("Moving more than 1 item is not supported yet");
            throw th2;
        }
        boolean add = this.mPendingUpdates.add(obtainUpdateOp(8, from, to, (Object) null));
        this.mExistingUpdateTypes |= 8;
        return this.mPendingUpdates.size() == 1;
    }

    /* access modifiers changed from: package-private */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int count = this.mPendingUpdates.size();
        for (int i = 0; i < count; i++) {
            UpdateOp op = this.mPendingUpdates.get(i);
            switch (op.cmd) {
                case 1:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForAdd(op.positionStart, op.itemCount);
                    break;
                case 2:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForRemovingInvisible(op.positionStart, op.itemCount);
                    break;
                case 4:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.markViewHoldersUpdated(op.positionStart, op.itemCount, op.payload);
                    break;
                case 8:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForMove(op.positionStart, op.itemCount);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public int applyPendingUpdatesToPosition(int i) {
        int position = i;
        int size = this.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp op = this.mPendingUpdates.get(i2);
            switch (op.cmd) {
                case 1:
                    if (op.positionStart > position) {
                        break;
                    } else {
                        position += op.itemCount;
                        break;
                    }
                case 2:
                    if (op.positionStart <= position) {
                        if (op.positionStart + op.itemCount <= position) {
                            position -= op.itemCount;
                            break;
                        } else {
                            return -1;
                        }
                    } else {
                        continue;
                    }
                case 8:
                    if (op.positionStart != position) {
                        if (op.positionStart < position) {
                            position--;
                        }
                        if (op.itemCount > position) {
                            break;
                        } else {
                            position++;
                            break;
                        }
                    } else {
                        position = op.itemCount;
                        break;
                    }
            }
        }
        return position;
    }

    /* access modifiers changed from: package-private */
    public boolean hasUpdates() {
        return !this.mPostponedList.isEmpty() && !this.mPendingUpdates.isEmpty();
    }

    /* renamed from: android.support.v7.widget.AdapterHelper$UpdateOp */
    static class UpdateOp {
        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int cmd2, int positionStart2, int itemCount2, Object payload2) {
            this.cmd = cmd2;
            this.positionStart = positionStart2;
            this.itemCount = itemCount2;
            this.payload = payload2;
        }

        /* access modifiers changed from: package-private */
        public String cmdToString() {
            switch (this.cmd) {
                case 1:
                    return Component.DEFAULT_VALUE_FAB_ICON_NAME;
                case 2:
                    return "rm";
                case 4:
                    return "up";
                case 8:
                    return "mv";
                default:
                    return "??";
            }
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(Integer.toHexString(System.identityHashCode(this))).append("[").append(cmdToString()).append(",s:").append(this.positionStart).append("c:").append(this.itemCount).append(",p:").append(this.payload).append("]").toString();
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UpdateOp op = (UpdateOp) o;
            if (this.cmd != op.cmd) {
                return false;
            }
            if (this.cmd == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == op.positionStart && this.positionStart == op.itemCount) {
                return true;
            }
            if (this.itemCount != op.itemCount) {
                return false;
            }
            if (this.positionStart != op.positionStart) {
                return false;
            }
            if (this.payload != null) {
                if (!this.payload.equals(op.payload)) {
                    return false;
                }
            } else if (op.payload != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (31 * ((31 * this.cmd) + this.positionStart)) + this.itemCount;
        }
    }

    public UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj) {
        UpdateOp updateOp;
        int cmd = i;
        int positionStart = i2;
        int itemCount = i3;
        Object payload = obj;
        UpdateOp op = this.mUpdateOpPool.acquire();
        if (op == null) {
            new UpdateOp(cmd, positionStart, itemCount, payload);
            op = updateOp;
        } else {
            op.cmd = cmd;
            op.positionStart = positionStart;
            op.itemCount = itemCount;
            op.payload = payload;
        }
        return op;
    }

    public void recycleUpdateOp(UpdateOp updateOp) {
        UpdateOp op = updateOp;
        if (!this.mDisableRecycler) {
            op.payload = null;
            boolean release = this.mUpdateOpPool.release(op);
        }
    }

    /* access modifiers changed from: package-private */
    public void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        List<UpdateOp> ops = list;
        int count = ops.size();
        for (int i = 0; i < count; i++) {
            recycleUpdateOp(ops.get(i));
        }
        ops.clear();
    }
}
