package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorAxis extends TreeScanner {
    public AncestorAxis() {
    }

    public static AncestorAxis make(NodePredicate type) {
        AncestorAxis ancestorAxis;
        new AncestorAxis();
        AncestorAxis axis = ancestorAxis;
        axis.type = type;
        return axis;
    }

    private static void scan(AbstractSequence abstractSequence, int ipos, int i, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int end = i;
        NodePredicate type = nodePredicate;
        PositionConsumer out = positionConsumer;
        int ipos2 = seq.parentPos(ipos);
        if (ipos2 != end) {
            scan(seq, ipos2, end, type, out);
            if (type.isInstancePos(seq, ipos2)) {
                out.writePosition(seq, ipos2);
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer out) {
        AbstractSequence seq = abstractSequence;
        scan(seq, ipos, seq.endPos(), this.type, out);
    }
}
