package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class SelfAxis extends TreeScanner {
    public SelfAxis() {
    }

    public static SelfAxis make(NodePredicate type) {
        SelfAxis selfAxis;
        new SelfAxis();
        SelfAxis axis = selfAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        PositionConsumer out = positionConsumer;
        if (this.type.isInstancePos(seq, ipos)) {
            out.writePosition(seq, ipos);
        }
    }
}
