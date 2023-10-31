package gnu.kawa.xml;

import android.support.p000v4.app.FragmentTransaction;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Children extends MethodProc {
    public static final Children children;

    public Children() {
    }

    static {
        Children children2;
        new Children();
        children = children2;
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public static void children(TreeList treeList, int i, Consumer consumer) {
        TreeList tlist = treeList;
        int index = i;
        Consumer consumer2 = consumer;
        int child = tlist.gotoChildrenStart(index);
        if (child >= 0) {
            int limit = tlist.nextDataIndex(index);
            while (true) {
                int ipos = child << 1;
                int next = tlist.nextNodeIndex(child, limit);
                int i2 = next;
                if (next == child) {
                    next = tlist.nextDataIndex(child);
                }
                if (next >= 0) {
                    if (consumer2 instanceof PositionConsumer) {
                        ((PositionConsumer) consumer2).writePosition(tlist, ipos);
                    } else {
                        int consumeIRange = tlist.consumeIRange(child, next, consumer2);
                    }
                    child = next;
                } else {
                    return;
                }
            }
        }
    }

    public static void children(Object obj, Consumer consumer) {
        Object node = obj;
        Consumer consumer2 = consumer;
        if (node instanceof TreeList) {
            children((TreeList) node, 0, consumer2);
        } else if ((node instanceof SeqPosition) && !(node instanceof TreePosition)) {
            SeqPosition pos = (SeqPosition) node;
            if (pos.sequence instanceof TreeList) {
                children((TreeList) pos.sequence, pos.ipos >> 1, consumer2);
            }
        }
    }

    public void apply(CallContext callContext) {
        CallContext ctx = callContext;
        Consumer consumer = ctx.consumer;
        Object node = ctx.getNextArg();
        ctx.lastArg();
        if (node instanceof Values) {
            TreeList tlist = (TreeList) node;
            int i = 0;
            while (true) {
                int index = i;
                int kind = tlist.getNextKind(index << 1);
                if (kind != 0) {
                    if (kind == 32) {
                        children(tlist.getPosNext(index << 1), consumer);
                    } else {
                        children(tlist, index, consumer);
                    }
                    i = tlist.nextDataIndex(index);
                } else {
                    return;
                }
            }
        } else {
            children(node, consumer);
        }
    }
}
