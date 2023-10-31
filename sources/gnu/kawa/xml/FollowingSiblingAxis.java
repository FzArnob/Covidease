package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingSiblingAxis extends TreeScanner {
    public FollowingSiblingAxis() {
    }

    public static FollowingSiblingAxis make(NodePredicate type) {
        FollowingSiblingAxis followingSiblingAxis;
        new FollowingSiblingAxis();
        FollowingSiblingAxis axis = followingSiblingAxis;
        axis.type = type;
        return axis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        PositionConsumer out = positionConsumer;
        int limit = seq.endPos();
        while (true) {
            ipos = seq.nextMatching(ipos, this.type, limit, false);
            if (ipos != 0) {
                out.writePosition(seq, ipos);
            } else {
                return;
            }
        }
    }
}
