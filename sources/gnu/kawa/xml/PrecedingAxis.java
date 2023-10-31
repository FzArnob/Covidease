package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingAxis extends TreeScanner {
    public PrecedingAxis() {
    }

    public static PrecedingAxis make(NodePredicate type) {
        PrecedingAxis precedingAxis;
        new PrecedingAxis();
        PrecedingAxis axis = precedingAxis;
        axis.type = type;
        return axis;
    }

    private static void scan(AbstractSequence abstractSequence, int i, int i2, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        int end = i2;
        NodePredicate type = nodePredicate;
        PositionConsumer out = positionConsumer;
        int parent = seq.parentPos(ipos);
        if (parent != end) {
            scan(seq, parent, end, type, out);
            int child = seq.firstChildPos(parent);
            if (child != 0) {
                if (type.isInstancePos(seq, child)) {
                    out.writePosition(seq, child);
                }
                while (true) {
                    child = seq.nextMatching(child, type, ipos, true);
                    if (child != 0) {
                        out.writePosition(seq, child);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer out) {
        AbstractSequence seq = abstractSequence;
        scan(seq, ipos, seq.endPos(), this.type, out);
    }
}
