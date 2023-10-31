package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingSiblingAxis extends TreeScanner {
    public PrecedingSiblingAxis() {
    }

    public static PrecedingSiblingAxis make(NodePredicate type) {
        PrecedingSiblingAxis precedingSiblingAxis;
        new PrecedingSiblingAxis();
        PrecedingSiblingAxis axis = precedingSiblingAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        PositionConsumer out = positionConsumer;
        int end = seq.endPos();
        int parent = seq.parentPos(ipos);
        if (parent != end) {
            int child = seq.firstChildPos(parent);
            if (child != 0) {
                if (this.type.isInstancePos(seq, child)) {
                    out.writePosition(seq, child);
                }
                while (true) {
                    child = seq.nextMatching(child, this.type, ipos, false);
                    if (child != 0) {
                        out.writePosition(seq, child);
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
