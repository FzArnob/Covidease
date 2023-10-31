package android.support.p003v7.widget;

import android.support.p003v7.widget.AdapterHelper;
import java.util.List;

/* renamed from: android.support.v7.widget.OpReorderer */
class OpReorderer {
    final Callback mCallback;

    /* renamed from: android.support.v7.widget.OpReorderer$Callback */
    interface Callback {
        AdapterHelper.UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj);

        void recycleUpdateOp(AdapterHelper.UpdateOp updateOp);
    }

    OpReorderer(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: package-private */
    public void reorderOps(List<AdapterHelper.UpdateOp> list) {
        List<AdapterHelper.UpdateOp> ops = list;
        while (true) {
            int lastMoveOutOfOrder = getLastMoveOutOfOrder(ops);
            int badMove = lastMoveOutOfOrder;
            if (lastMoveOutOfOrder != -1) {
                swapMoveOp(ops, badMove, badMove + 1);
            } else {
                return;
            }
        }
    }

    private void swapMoveOp(List<AdapterHelper.UpdateOp> list, int i, int i2) {
        List<AdapterHelper.UpdateOp> list2 = list;
        int badMove = i;
        int next = i2;
        AdapterHelper.UpdateOp moveOp = list2.get(badMove);
        AdapterHelper.UpdateOp nextOp = list2.get(next);
        switch (nextOp.cmd) {
            case 1:
                swapMoveAdd(list2, badMove, moveOp, next, nextOp);
                return;
            case 2:
                swapMoveRemove(list2, badMove, moveOp, next, nextOp);
                return;
            case 4:
                swapMoveUpdate(list2, badMove, moveOp, next, nextOp);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void swapMoveRemove(List<AdapterHelper.UpdateOp> list, int i, AdapterHelper.UpdateOp updateOp, int i2, AdapterHelper.UpdateOp updateOp2) {
        boolean moveIsBackwards;
        List<AdapterHelper.UpdateOp> list2 = list;
        int movePos = i;
        AdapterHelper.UpdateOp moveOp = updateOp;
        int removePos = i2;
        AdapterHelper.UpdateOp removeOp = updateOp2;
        AdapterHelper.UpdateOp extraRm = null;
        boolean revertedMove = false;
        if (moveOp.positionStart < moveOp.itemCount) {
            moveIsBackwards = false;
            if (removeOp.positionStart == moveOp.positionStart && removeOp.itemCount == moveOp.itemCount - moveOp.positionStart) {
                revertedMove = true;
            }
        } else {
            moveIsBackwards = true;
            if (removeOp.positionStart == moveOp.itemCount + 1 && removeOp.itemCount == moveOp.positionStart - moveOp.itemCount) {
                revertedMove = true;
            }
        }
        if (moveOp.itemCount < removeOp.positionStart) {
            AdapterHelper.UpdateOp updateOp3 = removeOp;
            updateOp3.positionStart--;
        } else if (moveOp.itemCount < removeOp.positionStart + removeOp.itemCount) {
            AdapterHelper.UpdateOp updateOp4 = removeOp;
            updateOp4.itemCount--;
            moveOp.cmd = 2;
            moveOp.itemCount = 1;
            if (removeOp.itemCount == 0) {
                AdapterHelper.UpdateOp remove = list2.remove(removePos);
                this.mCallback.recycleUpdateOp(removeOp);
                return;
            }
            return;
        }
        if (moveOp.positionStart <= removeOp.positionStart) {
            removeOp.positionStart++;
        } else if (moveOp.positionStart < removeOp.positionStart + removeOp.itemCount) {
            extraRm = this.mCallback.obtainUpdateOp(2, moveOp.positionStart + 1, (removeOp.positionStart + removeOp.itemCount) - moveOp.positionStart, (Object) null);
            removeOp.itemCount = moveOp.positionStart - removeOp.positionStart;
        }
        if (revertedMove) {
            AdapterHelper.UpdateOp updateOp5 = list2.set(movePos, removeOp);
            AdapterHelper.UpdateOp remove2 = list2.remove(removePos);
            this.mCallback.recycleUpdateOp(moveOp);
            return;
        }
        if (moveIsBackwards) {
            if (extraRm != null) {
                if (moveOp.positionStart > extraRm.positionStart) {
                    moveOp.positionStart -= extraRm.itemCount;
                }
                if (moveOp.itemCount > extraRm.positionStart) {
                    moveOp.itemCount -= extraRm.itemCount;
                }
            }
            if (moveOp.positionStart > removeOp.positionStart) {
                moveOp.positionStart -= removeOp.itemCount;
            }
            if (moveOp.itemCount > removeOp.positionStart) {
                moveOp.itemCount -= removeOp.itemCount;
            }
        } else {
            if (extraRm != null) {
                if (moveOp.positionStart >= extraRm.positionStart) {
                    moveOp.positionStart -= extraRm.itemCount;
                }
                if (moveOp.itemCount >= extraRm.positionStart) {
                    moveOp.itemCount -= extraRm.itemCount;
                }
            }
            if (moveOp.positionStart >= removeOp.positionStart) {
                moveOp.positionStart -= removeOp.itemCount;
            }
            if (moveOp.itemCount >= removeOp.positionStart) {
                moveOp.itemCount -= removeOp.itemCount;
            }
        }
        AdapterHelper.UpdateOp updateOp6 = list2.set(movePos, removeOp);
        if (moveOp.positionStart != moveOp.itemCount) {
            AdapterHelper.UpdateOp updateOp7 = list2.set(removePos, moveOp);
        } else {
            AdapterHelper.UpdateOp remove3 = list2.remove(removePos);
        }
        if (extraRm != null) {
            list2.add(movePos, extraRm);
        }
    }

    private void swapMoveAdd(List<AdapterHelper.UpdateOp> list, int i, AdapterHelper.UpdateOp updateOp, int i2, AdapterHelper.UpdateOp updateOp2) {
        List<AdapterHelper.UpdateOp> list2 = list;
        int move = i;
        AdapterHelper.UpdateOp moveOp = updateOp;
        int add = i2;
        AdapterHelper.UpdateOp addOp = updateOp2;
        int offset = 0;
        if (moveOp.itemCount < addOp.positionStart) {
            offset = 0 - 1;
        }
        if (moveOp.positionStart < addOp.positionStart) {
            offset++;
        }
        if (addOp.positionStart <= moveOp.positionStart) {
            moveOp.positionStart += addOp.itemCount;
        }
        if (addOp.positionStart <= moveOp.itemCount) {
            moveOp.itemCount += addOp.itemCount;
        }
        addOp.positionStart += offset;
        AdapterHelper.UpdateOp updateOp3 = list2.set(move, addOp);
        AdapterHelper.UpdateOp updateOp4 = list2.set(add, moveOp);
    }

    /* access modifiers changed from: package-private */
    public void swapMoveUpdate(List<AdapterHelper.UpdateOp> list, int i, AdapterHelper.UpdateOp updateOp, int i2, AdapterHelper.UpdateOp updateOp2) {
        List<AdapterHelper.UpdateOp> list2 = list;
        int move = i;
        AdapterHelper.UpdateOp moveOp = updateOp;
        int update = i2;
        AdapterHelper.UpdateOp updateOp3 = updateOp2;
        AdapterHelper.UpdateOp extraUp1 = null;
        AdapterHelper.UpdateOp extraUp2 = null;
        if (moveOp.itemCount < updateOp3.positionStart) {
            AdapterHelper.UpdateOp updateOp4 = updateOp3;
            updateOp4.positionStart--;
        } else if (moveOp.itemCount < updateOp3.positionStart + updateOp3.itemCount) {
            AdapterHelper.UpdateOp updateOp5 = updateOp3;
            updateOp5.itemCount--;
            extraUp1 = this.mCallback.obtainUpdateOp(4, moveOp.positionStart, 1, updateOp3.payload);
        }
        if (moveOp.positionStart <= updateOp3.positionStart) {
            updateOp3.positionStart++;
        } else if (moveOp.positionStart < updateOp3.positionStart + updateOp3.itemCount) {
            int remaining = (updateOp3.positionStart + updateOp3.itemCount) - moveOp.positionStart;
            extraUp2 = this.mCallback.obtainUpdateOp(4, moveOp.positionStart + 1, remaining, updateOp3.payload);
            updateOp3.itemCount -= remaining;
        }
        AdapterHelper.UpdateOp updateOp6 = list2.set(update, moveOp);
        if (updateOp3.itemCount > 0) {
            AdapterHelper.UpdateOp updateOp7 = list2.set(move, updateOp3);
        } else {
            AdapterHelper.UpdateOp remove = list2.remove(move);
            this.mCallback.recycleUpdateOp(updateOp3);
        }
        if (extraUp1 != null) {
            list2.add(move, extraUp1);
        }
        if (extraUp2 != null) {
            list2.add(move, extraUp2);
        }
    }

    private int getLastMoveOutOfOrder(List<AdapterHelper.UpdateOp> list) {
        List<AdapterHelper.UpdateOp> list2 = list;
        boolean foundNonMove = false;
        for (int i = list2.size() - 1; i >= 0; i--) {
            if (list2.get(i).cmd != 8) {
                foundNonMove = true;
            } else if (foundNonMove) {
                return i;
            }
        }
        return -1;
    }
}
