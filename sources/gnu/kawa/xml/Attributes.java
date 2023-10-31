package gnu.kawa.xml;

import android.support.p000v4.app.FragmentTransaction;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.io.PrintStream;

public class Attributes extends MethodProc {
    public static final Attributes attributes;

    public Attributes() {
    }

    static {
        Attributes attributes2;
        new Attributes();
        attributes = attributes2;
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public static void attributes(TreeList treeList, int index, Consumer consumer) {
        StringBuilder sb;
        TreeList tlist = treeList;
        Consumer consumer2 = consumer;
        int attr = tlist.gotoAttributesStart(index);
        PrintStream printStream = System.out;
        new StringBuilder();
        printStream.print(sb.append("Attributes called, at:").append(attr).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString());
        tlist.dump();
        while (attr >= 0) {
            int ipos = attr << 1;
            if (tlist.getNextKind(ipos) == 35) {
                int next = tlist.nextDataIndex(attr);
                if (consumer2 instanceof PositionConsumer) {
                    ((PositionConsumer) consumer2).writePosition(tlist, ipos);
                } else {
                    int consumeIRange = tlist.consumeIRange(attr, next, consumer2);
                }
                attr = next;
            } else {
                return;
            }
        }
    }

    public static void attributes(Object obj, Consumer consumer) {
        Object node = obj;
        Consumer consumer2 = consumer;
        if (node instanceof TreeList) {
            attributes((TreeList) node, 0, consumer2);
        } else if ((node instanceof SeqPosition) && !(node instanceof TreePosition)) {
            SeqPosition pos = (SeqPosition) node;
            if (pos.sequence instanceof TreeList) {
                attributes((TreeList) pos.sequence, pos.ipos >> 1, consumer2);
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
                        attributes(tlist.getPosNext(index << 1), consumer);
                    } else {
                        attributes(tlist, index, consumer);
                    }
                    i = tlist.nextDataIndex(index);
                } else {
                    return;
                }
            }
        } else {
            attributes(node, consumer);
        }
    }
}
