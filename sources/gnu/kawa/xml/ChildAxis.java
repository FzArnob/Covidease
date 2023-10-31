package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ChildAxis extends TreeScanner {
    public ChildAxis() {
    }

    public static ChildAxis make(NodePredicate type) {
        ChildAxis childAxis;
        new ChildAxis();
        ChildAxis axis = childAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        PositionConsumer out = positionConsumer;
        int firstChildPos = seq.firstChildPos(ipos, this.type);
        while (true) {
            int child = firstChildPos;
            if (child != 0) {
                out.writePosition(seq, child);
                firstChildPos = seq.nextMatching(child, this.type, -1, false);
            } else {
                return;
            }
        }
    }
}
