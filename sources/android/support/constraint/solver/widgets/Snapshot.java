package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

public class Snapshot {
    private ArrayList<Connection> mConnections;
    private int mHeight;
    private int mWidth;

    /* renamed from: mX */
    private int f12mX;

    /* renamed from: mY */
    private int f13mY;

    static class Connection {
        private ConstraintAnchor mAnchor;
        private int mCreator;
        private int mMargin;
        private ConstraintAnchor.Strength mStrengh;
        private ConstraintAnchor mTarget;

        public Connection(ConstraintAnchor constraintAnchor) {
            ConstraintAnchor anchor = constraintAnchor;
            this.mAnchor = anchor;
            this.mTarget = anchor.getTarget();
            this.mMargin = anchor.getMargin();
            this.mStrengh = anchor.getStrength();
            this.mCreator = anchor.getConnectionCreator();
        }

        public void updateFrom(ConstraintWidget widget) {
            this.mAnchor = widget.getAnchor(this.mAnchor.getType());
            if (this.mAnchor != null) {
                this.mTarget = this.mAnchor.getTarget();
                this.mMargin = this.mAnchor.getMargin();
                this.mStrengh = this.mAnchor.getStrength();
                this.mCreator = this.mAnchor.getConnectionCreator();
                return;
            }
            this.mTarget = null;
            this.mMargin = 0;
            this.mStrengh = ConstraintAnchor.Strength.STRONG;
            this.mCreator = 0;
        }

        public void applyTo(ConstraintWidget widget) {
            boolean connect = widget.getAnchor(this.mAnchor.getType()).connect(this.mTarget, this.mMargin, this.mStrengh, this.mCreator);
        }
    }

    public Snapshot(ConstraintWidget constraintWidget) {
        ArrayList<Connection> arrayList;
        Object obj;
        ConstraintWidget widget = constraintWidget;
        new ArrayList<>();
        this.mConnections = arrayList;
        this.f12mX = widget.getX();
        this.f13mY = widget.getY();
        this.mWidth = widget.getWidth();
        this.mHeight = widget.getHeight();
        ArrayList<ConstraintAnchor> anchors = widget.getAnchors();
        int anchorsSize = anchors.size();
        for (int i = 0; i < anchorsSize; i++) {
            new Connection(anchors.get(i));
            boolean add = this.mConnections.add(obj);
        }
    }

    public void updateFrom(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        this.f12mX = widget.getX();
        this.f13mY = widget.getY();
        this.mWidth = widget.getWidth();
        this.mHeight = widget.getHeight();
        int connections = this.mConnections.size();
        for (int i = 0; i < connections; i++) {
            this.mConnections.get(i).updateFrom(widget);
        }
    }

    public void applyTo(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        widget.setX(this.f12mX);
        widget.setY(this.f13mY);
        widget.setWidth(this.mWidth);
        widget.setHeight(this.mHeight);
        int mConnectionsSize = this.mConnections.size();
        for (int i = 0; i < mConnectionsSize; i++) {
            this.mConnections.get(i).applyTo(widget);
        }
    }
}
