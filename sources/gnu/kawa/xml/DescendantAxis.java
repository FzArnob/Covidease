package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantAxis extends TreeScanner {
    public DescendantAxis() {
    }

    public static DescendantAxis make(NodePredicate type) {
        DescendantAxis descendantAxis;
        new DescendantAxis();
        DescendantAxis axis = descendantAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        PositionConsumer out = positionConsumer;
        if (!(seq instanceof TreeList)) {
            int firstChildPos = seq.firstChildPos(ipos);
            while (true) {
                int ipos2 = firstChildPos;
                if (ipos2 != 0) {
                    if (this.type.isInstancePos(seq, ipos2)) {
                        out.writePosition(seq, ipos2);
                    }
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
