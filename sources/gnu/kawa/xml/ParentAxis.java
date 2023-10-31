package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ParentAxis extends TreeScanner {
    public ParentAxis() {
    }

    public static ParentAxis make(NodePredicate type) {
        ParentAxis parentAxis;
        new ParentAxis();
        ParentAxis axis = parentAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        PositionConsumer out = positionConsumer;
        int ipos2 = seq.parentPos(ipos);
        if (ipos2 != seq.endPos() && this.type.isInstancePos(seq, ipos2)) {
            out.writePosition(seq, ipos2);
        }
    }
}
