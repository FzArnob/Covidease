package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantOrSelfAxis extends TreeScanner {
    public static final DescendantOrSelfAxis anyNode;

    static {
        DescendantOrSelfAxis descendantOrSelfAxis;
        new DescendantOrSelfAxis(NodeType.anyNodeTest);
        anyNode = descendantOrSelfAxis;
    }

    private DescendantOrSelfAxis(NodePredicate type) {
        this.type = type;
    }

    public static DescendantOrSelfAxis make(NodePredicate nodePredicate) {
        DescendantOrSelfAxis descendantOrSelfAxis;
        NodePredicate type = nodePredicate;
        if (type == NodeType.anyNodeTest) {
            return anyNode;
        }
        new DescendantOrSelfAxis(type);
        return descendantOrSelfAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        PositionConsumer out = positionConsumer;
        if (this.type.isInstancePos(seq, ipos)) {
            out.writePosition(seq, ipos);
        }
        if (!(seq instanceof TreeList)) {
            int firstChildPos = seq.firstChildPos(ipos);
            while (true) {
                int ipos2 = firstChildPos;
                if (ipos2 != 0) {
                    scan(seq, ipos2, out);
                    firstChildPos = seq.nextPos(ipos2);
                } else {
                    return;
                }
            }
        } else {
            int limit = seq.nextPos(ipos);
            int child = ipos;
            while (true) {
                child = seq.nextMatching(child, this.type, limit, true);
                if (child != 0) {
                    out.writePosition(seq, child);
                } else {
                    return;
                }
            }
        }
    }
}
