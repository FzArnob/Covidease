package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;
import java.util.HashSet;

public class ConstraintAnchor {
    private static final boolean ALLOW_BINARY = false;
    public static final int AUTO_CONSTRAINT_CREATOR = 2;
    public static final int SCOUT_CREATOR = 1;
    private static final int UNSET_GONE_MARGIN = -1;
    public static final int USER_CREATOR = 0;
    private int mConnectionCreator = 0;
    private ConnectionType mConnectionType = ConnectionType.RELAXED;
    int mGoneMargin = -1;
    public int mMargin = 0;
    final ConstraintWidget mOwner;
    private ResolutionAnchor mResolutionAnchor;
    SolverVariable mSolverVariable;
    private Strength mStrength = Strength.NONE;
    ConstraintAnchor mTarget;
    final Type mType;

    public enum ConnectionType {
    }

    public enum Strength {
    }

    public enum Type {
    }

    public ResolutionAnchor getResolutionNode() {
        return this.mResolutionAnchor;
    }

    public ConstraintAnchor(ConstraintWidget owner, Type type) {
        ResolutionAnchor resolutionAnchor;
        new ResolutionAnchor(this);
        this.mResolutionAnchor = resolutionAnchor;
        this.mOwner = owner;
        this.mType = type;
    }

    public SolverVariable getSolverVariable() {
        return this.mSolverVariable;
    }

    public void resetSolverVariable(Cache cache) {
        SolverVariable solverVariable;
        Cache cache2 = cache;
        if (this.mSolverVariable == null) {
            new SolverVariable(SolverVariable.Type.UNRESTRICTED, (String) null);
            this.mSolverVariable = solverVariable;
            return;
        }
        this.mSolverVariable.reset();
    }

    public ConstraintWidget getOwner() {
        return this.mOwner;
    }

    public Type getType() {
        return this.mType;
    }

    public int getMargin() {
        if (this.mOwner.getVisibility() == 8) {
            return 0;
        }
        if (this.mGoneMargin <= -1 || this.mTarget == null || this.mTarget.mOwner.getVisibility() != 8) {
            return this.mMargin;
        }
        return this.mGoneMargin;
    }

    public Strength getStrength() {
        return this.mStrength;
    }

    public ConstraintAnchor getTarget() {
        return this.mTarget;
    }

    public ConnectionType getConnectionType() {
        return this.mConnectionType;
    }

    public void setConnectionType(ConnectionType type) {
        ConnectionType connectionType = type;
        this.mConnectionType = connectionType;
    }

    public int getConnectionCreator() {
        return this.mConnectionCreator;
    }

    public void setConnectionCreator(int creator) {
        int i = creator;
        this.mConnectionCreator = i;
    }

    public void reset() {
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = -1;
        this.mStrength = Strength.STRONG;
        this.mConnectionCreator = 0;
        this.mConnectionType = ConnectionType.RELAXED;
        this.mResolutionAnchor.reset();
    }

    public boolean connect(ConstraintAnchor toAnchor, int margin, Strength strength, int creator) {
        return connect(toAnchor, margin, -1, strength, creator, false);
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i, int i2, Strength strength, int i3, boolean z) {
        ConstraintAnchor toAnchor = constraintAnchor;
        int margin = i;
        int goneMargin = i2;
        Strength strength2 = strength;
        int creator = i3;
        boolean forceConnection = z;
        if (toAnchor == null) {
            this.mTarget = null;
            this.mMargin = 0;
            this.mGoneMargin = -1;
            this.mStrength = Strength.NONE;
            this.mConnectionCreator = 2;
            return true;
        } else if (!forceConnection && !isValidConnection(toAnchor)) {
            return false;
        } else {
            this.mTarget = toAnchor;
            if (margin > 0) {
                this.mMargin = margin;
            } else {
                this.mMargin = 0;
            }
            this.mGoneMargin = goneMargin;
            this.mStrength = strength2;
            this.mConnectionCreator = creator;
            return true;
        }
    }

    public boolean connect(ConstraintAnchor toAnchor, int margin, int creator) {
        return connect(toAnchor, margin, -1, Strength.STRONG, creator, false);
    }

    public boolean connect(ConstraintAnchor toAnchor, int margin) {
        return connect(toAnchor, margin, -1, Strength.STRONG, 0, false);
    }

    public boolean isConnected() {
        return this.mTarget != null;
    }

    public boolean isValidConnection(ConstraintAnchor constraintAnchor) {
        Throwable th;
        ConstraintAnchor anchor = constraintAnchor;
        if (anchor == null) {
            return false;
        }
        Type target = anchor.getType();
        if (target != this.mType) {
            switch (this.mType) {
                case CENTER:
                    return (target == Type.BASELINE || target == Type.CENTER_X || target == Type.CENTER_Y) ? false : true;
                case LEFT:
                case RIGHT:
                    boolean isCompatible = target == Type.LEFT || target == Type.RIGHT;
                    if (anchor.getOwner() instanceof Guideline) {
                        isCompatible = isCompatible || target == Type.CENTER_X;
                    }
                    return isCompatible;
                case TOP:
                case BOTTOM:
                    boolean isCompatible2 = target == Type.TOP || target == Type.BOTTOM;
                    if (anchor.getOwner() instanceof Guideline) {
                        isCompatible2 = isCompatible2 || target == Type.CENTER_Y;
                    }
                    return isCompatible2;
                case BASELINE:
                case CENTER_X:
                case CENTER_Y:
                case NONE:
                    return false;
                default:
                    Throwable th2 = th;
                    new AssertionError(this.mType.name());
                    throw th2;
            }
        } else if (this.mType != Type.BASELINE || (anchor.getOwner().hasBaseline() && getOwner().hasBaseline())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSideAnchor() {
        Throwable th;
        switch (this.mType) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return false;
            case LEFT:
            case RIGHT:
            case TOP:
            case BOTTOM:
                return true;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor anchor) {
        Throwable th;
        Type target = anchor.getType();
        if (target == this.mType) {
            return true;
        }
        switch (this.mType) {
            case CENTER:
                return target != Type.BASELINE;
            case LEFT:
            case RIGHT:
            case CENTER_X:
                return target == Type.LEFT || target == Type.RIGHT || target == Type.CENTER_X;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
                return target == Type.TOP || target == Type.BOTTOM || target == Type.CENTER_Y || target == Type.BASELINE;
            case NONE:
                return false;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public void setStrength(Strength strength) {
        Strength strength2 = strength;
        if (isConnected()) {
            this.mStrength = strength2;
        }
    }

    public void setMargin(int i) {
        int margin = i;
        if (isConnected()) {
            this.mMargin = margin;
        }
    }

    public void setGoneMargin(int i) {
        int margin = i;
        if (isConnected()) {
            this.mGoneMargin = margin;
        }
    }

    public boolean isVerticalAnchor() {
        Throwable th;
        switch (this.mType) {
            case CENTER:
            case LEFT:
            case RIGHT:
            case CENTER_X:
                return false;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
            case NONE:
                return true;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this.mOwner.getDebugName()).append(":").append(this.mType.toString()).toString();
    }

    public int getSnapPriorityLevel() {
        Throwable th;
        switch (this.mType) {
            case CENTER:
                return 3;
            case LEFT:
                return 1;
            case RIGHT:
                return 1;
            case TOP:
                return 0;
            case BOTTOM:
                return 0;
            case BASELINE:
                return 2;
            case CENTER_X:
                return 0;
            case CENTER_Y:
                return 1;
            case NONE:
                return 0;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public int getPriorityLevel() {
        Throwable th;
        switch (this.mType) {
            case CENTER:
                return 2;
            case LEFT:
                return 2;
            case RIGHT:
                return 2;
            case TOP:
                return 2;
            case BOTTOM:
                return 2;
            case BASELINE:
                return 1;
            case CENTER_X:
                return 0;
            case CENTER_Y:
                return 0;
            case NONE:
                return 0;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public boolean isSnapCompatibleWith(ConstraintAnchor constraintAnchor) {
        Throwable th;
        ConstraintAnchor anchor = constraintAnchor;
        if (this.mType == Type.CENTER) {
            return false;
        }
        if (this.mType == anchor.getType()) {
            return true;
        }
        switch (this.mType) {
            case CENTER:
            case BASELINE:
            case NONE:
                return false;
            case LEFT:
                switch (anchor.getType()) {
                    case RIGHT:
                        return true;
                    case CENTER_X:
                        return true;
                    default:
                        return false;
                }
            case RIGHT:
                switch (anchor.getType()) {
                    case LEFT:
                        return true;
                    case CENTER_X:
                        return true;
                    default:
                        return false;
                }
            case TOP:
                switch (anchor.getType()) {
                    case BOTTOM:
                        return true;
                    case CENTER_Y:
                        return true;
                    default:
                        return false;
                }
            case BOTTOM:
                switch (anchor.getType()) {
                    case TOP:
                        return true;
                    case CENTER_Y:
                        return true;
                    default:
                        return false;
                }
            case CENTER_X:
                switch (anchor.getType()) {
                    case LEFT:
                        return true;
                    case RIGHT:
                        return true;
                    default:
                        return false;
                }
            case CENTER_Y:
                switch (anchor.getType()) {
                    case TOP:
                        return true;
                    case BOTTOM:
                        return true;
                    default:
                        return false;
                }
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }

    public boolean isConnectionAllowed(ConstraintWidget target, ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor;
        return isConnectionAllowed(target);
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget) {
        HashSet hashSet;
        ConstraintWidget target = constraintWidget;
        new HashSet();
        if (isConnectionToMe(target, hashSet)) {
            return false;
        }
        ConstraintWidget parent = getOwner().getParent();
        if (parent == target) {
            return true;
        }
        if (target.getParent() == parent) {
            return true;
        }
        return false;
    }

    private boolean isConnectionToMe(ConstraintWidget constraintWidget, HashSet<ConstraintWidget> hashSet) {
        ConstraintWidget target = constraintWidget;
        HashSet<ConstraintWidget> checked = hashSet;
        if (checked.contains(target)) {
            return false;
        }
        boolean add = checked.add(target);
        if (target == getOwner()) {
            return true;
        }
        ArrayList<ConstraintAnchor> targetAnchors = target.getAnchors();
        int targetAnchorsSize = targetAnchors.size();
        for (int i = 0; i < targetAnchorsSize; i++) {
            ConstraintAnchor anchor = targetAnchors.get(i);
            if (anchor.isSimilarDimensionConnection(this) && anchor.isConnected() && isConnectionToMe(anchor.getTarget().getOwner(), checked)) {
                return true;
            }
        }
        return false;
    }

    public final ConstraintAnchor getOpposite() {
        Throwable th;
        switch (this.mType) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return null;
            case LEFT:
                return this.mOwner.mRight;
            case RIGHT:
                return this.mOwner.mLeft;
            case TOP:
                return this.mOwner.mBottom;
            case BOTTOM:
                return this.mOwner.mTop;
            default:
                Throwable th2 = th;
                new AssertionError(this.mType.name());
                throw th2;
        }
    }
}
