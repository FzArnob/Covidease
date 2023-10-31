package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorOrSelfAxis extends TreeScanner {
    public AncestorOrSelfAxis() {
    }

    public static AncestorOrSelfAxis make(NodePredicate type) {
        AncestorOrSelfAxis ancestorOrSelfAxis;
        new AncestorOrSelfAxis();
        AncestorOrSelfAxis axis = ancestorOrSelfAxis;
        axis.type = type;
        return axis;
    }

    private static void scan(AbstractSequence abstractSequence, int i, int i2, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        int end = i2;
        NodePredicate type = nodePredicate;
        PositionConsumer out = positionConsumer;
        if (ipos != end) {
            scan(seq, seq.parentPos(ipos), end, type, out);
            if (type.isInstancePos(seq, ipos)) {
                out.writePosition(seq, ipos);
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer out) {
        AbstractSequence seq = abstractSequence;
        int end = seq.endPos();
        scan(seq, ipos, end, this.type, out);
    }
}
