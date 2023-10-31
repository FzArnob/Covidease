package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingAxis extends TreeScanner {
    public FollowingAxis() {
    }

    public static FollowingAxis make(NodePredicate type) {
        FollowingAxis followingAxis;
        new FollowingAxis();
        FollowingAxis axis = followingAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int ipos, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        PositionConsumer out = positionConsumer;
        int limit = seq.endPos();
        int ipos2 = seq.nextPos(ipos);
        if (ipos2 != 0 && this.type.isInstancePos(seq, ipos2)) {
            out.writePosition(seq, ipos2);
        }
        while (true) {
            ipos2 = seq.nextMatching(ipos2, this.type, limit, true);
            if (ipos2 != 0) {
                out.writePosition(seq, ipos2);
            } else {
                return;
            }
        }
    }
}
