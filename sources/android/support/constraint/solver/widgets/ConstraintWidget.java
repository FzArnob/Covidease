package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;

public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintWidgetGroup mBelongingGroup;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    boolean mOptimizerMeasured;
    ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;

    /* renamed from: mX */
    protected int f5mX;

    /* renamed from: mY */
    protected int f6mY;

    public enum ContentAlignment {
    }

    public enum DimensionBehaviour {
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int maxWidth) {
        this.mMaxDimension[0] = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxDimension[1] = maxHeight;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f5mX = 0;
        this.f6mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = -1.0f;
        this.mWeight[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
        this.mBelongingGroup = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int optimizationLevel) {
        Optimizer.analyze(optimizationLevel, this);
    }

    public void resolve() {
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        ResolutionDimension resolutionDimension;
        if (this.mResolutionWidth == null) {
            new ResolutionDimension();
            this.mResolutionWidth = resolutionDimension;
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        ResolutionDimension resolutionDimension;
        if (this.mResolutionHeight == null) {
            new ResolutionDimension();
            this.mResolutionHeight = resolutionDimension;
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        ConstraintAnchor constraintAnchor6;
        ConstraintAnchor constraintAnchor7;
        ConstraintAnchor constraintAnchor8;
        ArrayList<ConstraintAnchor> arrayList;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mLeft = constraintAnchor;
        new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mTop = constraintAnchor2;
        new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mRight = constraintAnchor3;
        new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBottom = constraintAnchor4;
        new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mBaseline = constraintAnchor5;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterX = constraintAnchor6;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenterY = constraintAnchor7;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor8;
        ConstraintAnchor[] constraintAnchorArr = new ConstraintAnchor[6];
        constraintAnchorArr[0] = this.mLeft;
        ConstraintAnchor[] constraintAnchorArr2 = constraintAnchorArr;
        constraintAnchorArr2[1] = this.mRight;
        ConstraintAnchor[] constraintAnchorArr3 = constraintAnchorArr2;
        constraintAnchorArr3[2] = this.mTop;
        ConstraintAnchor[] constraintAnchorArr4 = constraintAnchorArr3;
        constraintAnchorArr4[3] = this.mBottom;
        ConstraintAnchor[] constraintAnchorArr5 = constraintAnchorArr4;
        constraintAnchorArr5[4] = this.mBaseline;
        ConstraintAnchor[] constraintAnchorArr6 = constraintAnchorArr5;
        constraintAnchorArr6[5] = this.mCenter;
        this.mListAnchors = constraintAnchorArr6;
        new ArrayList<>();
        this.mAnchors = arrayList;
        DimensionBehaviour[] dimensionBehaviourArr = new DimensionBehaviour[2];
        dimensionBehaviourArr[0] = DimensionBehaviour.FIXED;
        DimensionBehaviour[] dimensionBehaviourArr2 = dimensionBehaviourArr;
        dimensionBehaviourArr2[1] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = dimensionBehaviourArr2;
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f5mX = 0;
        this.f6mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        ConstraintWidget[] constraintWidgetArr = new ConstraintWidget[2];
        constraintWidgetArr[0] = null;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidgetArr;
        constraintWidgetArr2[1] = null;
        this.mListNextMatchConstraintsWidget = constraintWidgetArr2;
        ConstraintWidget[] constraintWidgetArr3 = new ConstraintWidget[2];
        constraintWidgetArr3[0] = null;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidgetArr3;
        constraintWidgetArr4[1] = null;
        this.mNextChainWidget = constraintWidgetArr4;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int x, int y, int width, int height) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        ConstraintAnchor constraintAnchor6;
        ConstraintAnchor constraintAnchor7;
        ConstraintAnchor constraintAnchor8;
        ArrayList<ConstraintAnchor> arrayList;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mLeft = constraintAnchor;
        new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mTop = constraintAnchor2;
        new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mRight = constraintAnchor3;
        new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBottom = constraintAnchor4;
        new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mBaseline = constraintAnchor5;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterX = constraintAnchor6;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenterY = constraintAnchor7;
        new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor8;
        ConstraintAnchor[] constraintAnchorArr = new ConstraintAnchor[6];
        constraintAnchorArr[0] = this.mLeft;
        ConstraintAnchor[] constraintAnchorArr2 = constraintAnchorArr;
        constraintAnchorArr2[1] = this.mRight;
        ConstraintAnchor[] constraintAnchorArr3 = constraintAnchorArr2;
        constraintAnchorArr3[2] = this.mTop;
        ConstraintAnchor[] constraintAnchorArr4 = constraintAnchorArr3;
        constraintAnchorArr4[3] = this.mBottom;
        ConstraintAnchor[] constraintAnchorArr5 = constraintAnchorArr4;
        constraintAnchorArr5[4] = this.mBaseline;
        ConstraintAnchor[] constraintAnchorArr6 = constraintAnchorArr5;
        constraintAnchorArr6[5] = this.mCenter;
        this.mListAnchors = constraintAnchorArr6;
        new ArrayList<>();
        this.mAnchors = arrayList;
        DimensionBehaviour[] dimensionBehaviourArr = new DimensionBehaviour[2];
        dimensionBehaviourArr[0] = DimensionBehaviour.FIXED;
        DimensionBehaviour[] dimensionBehaviourArr2 = dimensionBehaviourArr;
        dimensionBehaviourArr2[1] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = dimensionBehaviourArr2;
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f5mX = 0;
        this.f6mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        ConstraintWidget[] constraintWidgetArr = new ConstraintWidget[2];
        constraintWidgetArr[0] = null;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidgetArr;
        constraintWidgetArr2[1] = null;
        this.mListNextMatchConstraintsWidget = constraintWidgetArr2;
        ConstraintWidget[] constraintWidgetArr3 = new ConstraintWidget[2];
        constraintWidgetArr3[0] = null;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidgetArr3;
        constraintWidgetArr4[1] = null;
        this.mNextChainWidget = constraintWidgetArr4;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.f5mX = x;
        this.f6mY = y;
        this.mWidth = width;
        this.mHeight = height;
        addAnchors();
        forceUpdateDrawPosition();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConstraintWidget(int width, int height) {
        this(0, 0, width, height);
    }

    public void resetSolverVariables(Cache cache) {
        Cache cache2 = cache;
        this.mLeft.resetSolverVariable(cache2);
        this.mTop.resetSolverVariable(cache2);
        this.mRight.resetSolverVariable(cache2);
        this.mBottom.resetSolverVariable(cache2);
        this.mBaseline.resetSolverVariable(cache2);
        this.mCenter.resetSolverVariable(cache2);
        this.mCenterX.resetSolverVariable(cache2);
        this.mCenterY.resetSolverVariable(cache2);
    }

    private void addAnchors() {
        boolean add = this.mAnchors.add(this.mLeft);
        boolean add2 = this.mAnchors.add(this.mTop);
        boolean add3 = this.mAnchors.add(this.mRight);
        boolean add4 = this.mAnchors.add(this.mBottom);
        boolean add5 = this.mAnchors.add(this.mCenterX);
        boolean add6 = this.mAnchors.add(this.mCenterY);
        boolean add7 = this.mAnchors.add(this.mCenter);
        boolean add8 = this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget widget = getParent();
        if (widget == null) {
            return false;
        }
        while (widget != null) {
            if (widget instanceof ConstraintWidgetContainer) {
                return true;
            }
            widget = widget.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        ConstraintWidget parent = getParent();
        if (parent == widget) {
            return true;
        }
        if (parent == widget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == widget) {
                return true;
            }
            if (parent == widget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget root;
        ConstraintWidget constraintWidget = this;
        while (true) {
            root = constraintWidget;
            if (root.getParent() == null) {
                break;
            }
            constraintWidget = root.getParent();
        }
        if (root instanceof WidgetContainer) {
            return (WidgetContainer) root;
        }
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        ConstraintWidget constraintWidget = widget;
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean widthWrapContent) {
        boolean z = widthWrapContent;
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean heightWrapContent) {
        boolean z = heightWrapContent;
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget target, float angle, int radius) {
        immediateConnect(ConstraintAnchor.Type.CENTER, target, ConstraintAnchor.Type.CENTER, radius, 0);
        this.mCircleConstraintAngle = angle;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        String str = type;
        this.mType = str;
    }

    public void setVisibility(int visibility) {
        int i = visibility;
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String name) {
        String str = name;
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        LinearSystem system = linearSystem;
        String name = str;
        this.mDebugName = name;
        SolverVariable left = system.createObjectVariable(this.mLeft);
        SolverVariable top = system.createObjectVariable(this.mTop);
        SolverVariable right = system.createObjectVariable(this.mRight);
        SolverVariable bottom = system.createObjectVariable(this.mBottom);
        new StringBuilder();
        left.setName(sb.append(name).append(".left").toString());
        new StringBuilder();
        top.setName(sb2.append(name).append(".top").toString());
        new StringBuilder();
        right.setName(sb3.append(name).append(".right").toString());
        new StringBuilder();
        bottom.setName(sb4.append(name).append(".bottom").toString());
        if (this.mBaselineDistance > 0) {
            new StringBuilder();
            system.createObjectVariable(this.mBaseline).setName(sb5.append(name).append(".baseline").toString());
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        LinearSystem system = linearSystem;
        SolverVariable createObjectVariable = system.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = system.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = system.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = system.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = system.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        StringBuilder sb;
        String str;
        String str2;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4 = sb;
        new StringBuilder();
        if (this.mType != null) {
            new StringBuilder();
            str = sb3.append("type: ").append(this.mType).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString();
        } else {
            str = "";
        }
        StringBuilder append = sb4.append(str);
        if (this.mDebugName != null) {
            new StringBuilder();
            str2 = sb2.append("id: ").append(this.mDebugName).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString();
        } else {
            str2 = "";
        }
        return append.append(str2).append("(").append(this.f5mX).append(", ").append(this.f6mY).append(") - (").append(this.mWidth).append(" x ").append(this.mHeight).append(") wrap: (").append(this.mWrapWidth).append(" x ").append(this.mWrapHeight).append(")").toString();
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawX() {
        return this.mDrawX;
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.f5mX;
    }

    public int getY() {
        return this.f6mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int w = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                w = Math.max(this.mMatchConstraintMinWidth, w);
            } else if (this.mMatchConstraintMinWidth > 0) {
                w = this.mMatchConstraintMinWidth;
                this.mWidth = w;
            } else {
                w = 0;
            }
            if (this.mMatchConstraintMaxWidth > 0 && this.mMatchConstraintMaxWidth < w) {
                w = this.mMatchConstraintMaxWidth;
            }
        }
        return w;
    }

    public int getOptimizerWrapHeight() {
        int h = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                h = Math.max(this.mMatchConstraintMinHeight, h);
            } else if (this.mMatchConstraintMinHeight > 0) {
                h = this.mMatchConstraintMinHeight;
                this.mHeight = h;
            } else {
                h = 0;
            }
            if (this.mMatchConstraintMaxHeight > 0 && this.mMatchConstraintMaxHeight < h) {
                h = this.mMatchConstraintMaxHeight;
            }
        }
        return h;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getLength(int i) {
        int orientation = i;
        if (orientation == 0) {
            return getWidth();
        }
        if (orientation == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.f5mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.f6mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        int orientation = i;
        if (orientation == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (orientation == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        int i = x;
        this.f5mX = i;
    }

    public void setY(int y) {
        int i = y;
        this.f6mY = i;
    }

    public void setOrigin(int x, int y) {
        this.f5mX = x;
        this.f6mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        int goneMargin = i;
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = goneMargin;
                return;
            case TOP:
                this.mTop.mGoneMargin = goneMargin;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = goneMargin;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = goneMargin;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int left = this.f5mX;
        int top = this.f6mY;
        int right = this.f5mX + this.mWidth;
        int bottom = this.f6mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void forceUpdateDrawPosition() {
        int left = this.f5mX;
        int top = this.f6mY;
        int right = this.f5mX + this.mWidth;
        int bottom = this.f6mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void setDrawOrigin(int x, int y) {
        this.mDrawX = x - this.mOffsetX;
        this.mDrawY = y - this.mOffsetY;
        this.f5mX = this.mDrawX;
        this.f6mY = this.mDrawY;
    }

    public void setDrawX(int x) {
        this.mDrawX = x - this.mOffsetX;
        this.f5mX = this.mDrawX;
    }

    public void setDrawY(int y) {
        this.mDrawY = y - this.mOffsetY;
        this.f6mY = this.mDrawY;
    }

    public void setDrawWidth(int drawWidth) {
        int i = drawWidth;
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int drawHeight) {
        int i = drawHeight;
        this.mDrawHeight = i;
    }

    public void setWidth(int w) {
        this.mWidth = w;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setLength(int i, int i2) {
        int length = i;
        int orientation = i2;
        if (orientation == 0) {
            setWidth(length);
        } else if (orientation == 1) {
            setHeight(length);
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max, float f) {
        float percent = f;
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max;
        this.mMatchConstraintPercentWidth = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max, float f) {
        float percent = f;
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max;
        this.mMatchConstraintPercentHeight = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String str) {
        int commaIndex;
        String ratio = str;
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex2 = ratio.indexOf(44);
        if (commaIndex2 <= 0 || commaIndex2 >= len - 1) {
            commaIndex = 0;
        } else {
            String dimension = ratio.substring(0, commaIndex2);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
            }
            commaIndex = commaIndex2 + 1;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex < 0 || colonIndex >= len - 1) {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                }
            }
        } else {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e2) {
                    NumberFormatException numberFormatException2 = e2;
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setDimensionRatio(float ratio, int dimensionRatioSide) {
        this.mDimensionRatio = ratio;
        this.mDimensionRatioSide = dimensionRatioSide;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        float f = horizontalBiasPercent;
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        float f = verticalBiasPercent;
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        int w = i;
        if (w < 0) {
            this.mMinWidth = 0;
            return;
        }
        this.mMinWidth = w;
    }

    public void setMinHeight(int i) {
        int h = i;
        if (h < 0) {
            this.mMinHeight = 0;
            return;
        }
        this.mMinHeight = h;
    }

    public void setWrapWidth(int w) {
        int i = w;
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int h) {
        int i = h;
        this.mWrapHeight = i;
    }

    public void setDimension(int w, int i) {
        int h = i;
        this.mWidth = w;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int right, int bottom) {
        int left = i;
        int top = i2;
        int w = right - left;
        int h = bottom - top;
        this.f5mX = left;
        this.f6mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mOptimizerMeasured = true;
    }

    public void setFrame(int i, int i2, int i3) {
        int start = i;
        int end = i2;
        int orientation = i3;
        if (orientation == 0) {
            setHorizontalDimension(start, end);
        } else if (orientation == 1) {
            setVerticalDimension(start, end);
        }
        this.mOptimizerMeasured = true;
    }

    public void setHorizontalDimension(int i, int right) {
        int left = i;
        this.f5mX = left;
        this.mWidth = right - left;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int bottom) {
        int top = i;
        this.f6mY = top;
        this.mHeight = bottom - top;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    /* access modifiers changed from: package-private */
    public int getRelativePositioning(int i) {
        int orientation = i;
        if (orientation == 0) {
            return this.mRelX;
        }
        if (orientation == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void setRelativePositioning(int i, int i2) {
        int offset = i;
        int orientation = i2;
        if (orientation == 0) {
            this.mRelX = offset;
        } else if (orientation == 1) {
            this.mRelY = offset;
        }
    }

    public void setBaselineDistance(int baseline) {
        int i = baseline;
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object companion) {
        Object obj = companion;
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        int skip = i;
        if (skip >= 0) {
            this.mContainerItemSkip = skip;
            return;
        }
        this.mContainerItemSkip = 0;
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mWeight[0] = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mWeight[1] = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        int i = horizontalChainStyle;
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        int i = verticalChainStyle;
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void connectedTo(ConstraintWidget source) {
    }

    public void immediateConnect(ConstraintAnchor.Type startType, ConstraintWidget target, ConstraintAnchor.Type endType, int margin, int goneMargin) {
        boolean connect = getAnchor(startType).connect(target.getAnchor(endType), margin, goneMargin, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin, int creator) {
        connect(from, to, margin, ConstraintAnchor.Strength.STRONG, creator);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin) {
        connect(from, to, margin, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, ConstraintAnchor.Strength strength, int i2) {
        ConstraintAnchor from = constraintAnchor;
        ConstraintAnchor to = constraintAnchor2;
        int margin = i;
        ConstraintAnchor.Strength strength2 = strength;
        int creator = i2;
        if (from.getOwner() == this) {
            connect(from.getType(), to.getOwner(), to.getType(), margin, strength2, creator);
        }
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin) {
        connect(constraintFrom, target, constraintTo, margin, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo) {
        connect(constraintFrom, target, constraintTo, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin, ConstraintAnchor.Strength strength) {
        connect(constraintFrom, target, constraintTo, margin, strength, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, ConstraintAnchor.Strength strength, int i2) {
        ConstraintAnchor.Type constraintFrom = type;
        ConstraintWidget target = constraintWidget;
        ConstraintAnchor.Type constraintTo = type2;
        int margin = i;
        ConstraintAnchor.Strength strength2 = strength;
        int creator = i2;
        if (constraintFrom == ConstraintAnchor.Type.CENTER) {
            if (constraintTo == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean centerX = false;
                boolean centerY = false;
                if ((left == null || !left.isConnected()) && (right == null || !right.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, target, ConstraintAnchor.Type.LEFT, 0, strength2, creator);
                    connect(ConstraintAnchor.Type.RIGHT, target, ConstraintAnchor.Type.RIGHT, 0, strength2, creator);
                    centerX = true;
                }
                if ((top == null || !top.isConnected()) && (bottom == null || !bottom.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, target, ConstraintAnchor.Type.TOP, 0, strength2, creator);
                    connect(ConstraintAnchor.Type.BOTTOM, target, ConstraintAnchor.Type.BOTTOM, 0, strength2, creator);
                    centerY = true;
                }
                if (centerX && centerY) {
                    boolean connect = getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(ConstraintAnchor.Type.CENTER), 0, creator);
                } else if (centerX) {
                    boolean connect2 = getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, creator);
                } else if (centerY) {
                    boolean connect3 = getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, creator);
                }
            } else if (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, target, constraintTo, 0, strength2, creator);
                connect(ConstraintAnchor.Type.RIGHT, target, constraintTo, 0, strength2, creator);
                boolean connect4 = getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0, creator);
            } else if (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM) {
                connect(ConstraintAnchor.Type.TOP, target, constraintTo, 0, strength2, creator);
                connect(ConstraintAnchor.Type.BOTTOM, target, constraintTo, 0, strength2, creator);
                boolean connect5 = getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0, creator);
            }
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor left2 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetAnchor = target.getAnchor(constraintTo);
            ConstraintAnchor right2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            boolean connect6 = left2.connect(targetAnchor, 0, creator);
            boolean connect7 = right2.connect(targetAnchor, 0, creator);
            boolean connect8 = getAnchor(ConstraintAnchor.Type.CENTER_X).connect(targetAnchor, 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor targetAnchor2 = target.getAnchor(constraintTo);
            boolean connect9 = getAnchor(ConstraintAnchor.Type.TOP).connect(targetAnchor2, 0, creator);
            boolean connect10 = getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetAnchor2, 0, creator);
            boolean connect11 = getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(targetAnchor2, 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && constraintTo == ConstraintAnchor.Type.CENTER_X) {
            boolean connect12 = getAnchor(ConstraintAnchor.Type.LEFT).connect(target.getAnchor(ConstraintAnchor.Type.LEFT), 0, creator);
            boolean connect13 = getAnchor(ConstraintAnchor.Type.RIGHT).connect(target.getAnchor(ConstraintAnchor.Type.RIGHT), 0, creator);
            boolean connect14 = getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(constraintTo), 0, creator);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && constraintTo == ConstraintAnchor.Type.CENTER_Y) {
            boolean connect15 = getAnchor(ConstraintAnchor.Type.TOP).connect(target.getAnchor(ConstraintAnchor.Type.TOP), 0, creator);
            boolean connect16 = getAnchor(ConstraintAnchor.Type.BOTTOM).connect(target.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, creator);
            boolean connect17 = getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(constraintTo), 0, creator);
        } else {
            ConstraintAnchor fromAnchor = getAnchor(constraintFrom);
            ConstraintAnchor toAnchor = target.getAnchor(constraintTo);
            if (fromAnchor.isValidConnection(toAnchor)) {
                if (constraintFrom == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor top2 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor bottom2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (top2 != null) {
                        top2.reset();
                    }
                    if (bottom2 != null) {
                        bottom2.reset();
                    }
                    margin = 0;
                } else if (constraintFrom == ConstraintAnchor.Type.TOP || constraintFrom == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor baseline = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (baseline != null) {
                        baseline.reset();
                    }
                    ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center.getTarget() != toAnchor) {
                        center.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerY2 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (centerY2.isConnected()) {
                        opposite.reset();
                        centerY2.reset();
                    }
                } else if (constraintFrom == ConstraintAnchor.Type.LEFT || constraintFrom == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor center2 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center2.getTarget() != toAnchor) {
                        center2.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerX2 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (centerX2.isConnected()) {
                        opposite2.reset();
                        centerX2.reset();
                    }
                }
                boolean connect18 = fromAnchor.connect(toAnchor, margin, strength2, creator);
                toAnchor.getOwner().connectedTo(fromAnchor.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT) {
                return;
            }
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor anchor = constraintAnchor;
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor centerX = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor centerY = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (anchor == center) {
                if (left.isConnected() && right.isConnected() && left.getTarget() == right.getTarget()) {
                    left.reset();
                    right.reset();
                }
                if (top.isConnected() && bottom.isConnected() && top.getTarget() == bottom.getTarget()) {
                    top.reset();
                    bottom.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (anchor == centerX) {
                if (left.isConnected() && right.isConnected() && left.getTarget().getOwner() == right.getTarget().getOwner()) {
                    left.reset();
                    right.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (anchor == centerY) {
                if (top.isConnected() && bottom.isConnected() && top.getTarget().getOwner() == bottom.getTarget().getOwner()) {
                    top.reset();
                    bottom.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (anchor == left || anchor == right) {
                if (left.isConnected() && left.getTarget() == right.getTarget()) {
                    center.reset();
                }
            } else if ((anchor == top || anchor == bottom) && top.isConnected() && top.getTarget() == bottom.getTarget()) {
                center.reset();
            }
            anchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int mAnchorsSize = this.mAnchors.size();
            for (int i = 0; i < mAnchorsSize; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        int connectionCreator = i;
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int mAnchorsSize = this.mAnchors.size();
            for (int i2 = 0; i2 < mAnchorsSize; i2++) {
                ConstraintAnchor anchor = this.mAnchors.get(i2);
                if (connectionCreator == anchor.getConnectionCreator()) {
                    if (anchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    anchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int anchorsSize = anchors.size();
        for (int i = 0; i < anchorsSize; i++) {
            ConstraintAnchor anchor = anchors.get(i);
            if (anchor.isConnected() && anchor.getTarget().getOwner() == widget) {
                anchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int anchorsSize = anchors.size();
        for (int i = 0; i < anchorsSize; i++) {
            ConstraintAnchor anchor = anchors.get(i);
            if (anchor.isConnected() && anchor.getTarget().getOwner() == widget && anchor.getConnectionCreator() == 2) {
                anchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        Throwable th;
        ConstraintAnchor.Type anchorType = type;
        switch (anchorType) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                Throwable th2 = th;
                new AssertionError(anchorType.name());
                throw th2;
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        int orientation = i;
        if (orientation == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (orientation == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        DimensionBehaviour behaviour = dimensionBehaviour;
        this.mListDimensionBehaviors[0] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        DimensionBehaviour behaviour = dimensionBehaviour;
        this.mListDimensionBehaviors[1] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if ((this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) && (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != this.mRight)) {
            return false;
        }
        return true;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintWidget found = null;
        if (isInHorizontalChain()) {
            ConstraintWidget tmp = this;
            while (true) {
                if (found != null || tmp == null) {
                    break;
                }
                ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
                ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
                if (target == getParent()) {
                    found = tmp;
                    break;
                }
                ConstraintAnchor targetAnchor = target == null ? null : target.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
                if (targetAnchor == null || targetAnchor.getOwner() == tmp) {
                    tmp = target;
                } else {
                    found = tmp;
                }
            }
        }
        return found;
    }

    public boolean isInVerticalChain() {
        if ((this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) && (this.mBottom.mTarget == null || this.mBottom.mTarget.mTarget != this.mBottom)) {
            return false;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintWidget found = null;
        if (isInVerticalChain()) {
            ConstraintWidget tmp = this;
            while (true) {
                if (found != null || tmp == null) {
                    break;
                }
                ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
                ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
                if (target == getParent()) {
                    found = tmp;
                    break;
                }
                ConstraintAnchor targetAnchor = target == null ? null : target.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
                if (targetAnchor == null || targetAnchor.getOwner() == tmp) {
                    tmp = target;
                } else {
                    found = tmp;
                }
            }
        }
        return found;
    }

    private boolean isChainHead(int orientation) {
        int offset = orientation * 2;
        return (this.mListAnchors[offset].mTarget == null || this.mListAnchors[offset].mTarget.mTarget == this.mListAnchors[offset] || this.mListAnchors[offset + 1].mTarget == null || this.mListAnchors[offset + 1].mTarget.mTarget != this.mListAnchors[offset + 1]) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:138:0x05bb, code lost:
        if (r2.mResolvedDimensionRatioSide == -1) goto L_0x05bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x033f, code lost:
        if (r2.mResolvedDimensionRatioSide == -1) goto L_0x0341;
     */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0577  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x059d  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x05cb  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0600  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0620  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x06ab  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x06e4  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x077b  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x077f  */
    /* JADX WARNING: Removed duplicated region for block: B:167:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:169:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0359  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0379  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x038d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(android.support.constraint.solver.LinearSystem r49) {
        /*
            r48 = this;
            r2 = r48
            r3 = r49
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mLeft
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r4 = r27
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r5 = r27
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r6 = r27
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r7 = r27
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r8 = r27
            r27 = 0
            r9 = r27
            r27 = 0
            r10 = r27
            r27 = 0
            r11 = r27
            r27 = 0
            r12 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x01b4
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x0453
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x044f
            r27 = 1
        L_0x0094:
            r11 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x045b
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0457
            r27 = 1
        L_0x00bc:
            r12 = r27
            r27 = r2
            r28 = 0
            boolean r27 = r27.isChainHead(r28)
            if (r27 == 0) goto L_0x045f
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r27 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r27
            r28 = r2
            r29 = 0
            r27.addChain(r28, r29)
            r27 = 1
            r9 = r27
        L_0x00dd:
            r27 = r2
            r28 = 1
            boolean r27 = r27.isChainHead(r28)
            if (r27 == 0) goto L_0x0469
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r27 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r27
            r28 = r2
            r29 = 1
            r27.addChain(r28, r29)
            r27 = 1
            r10 = r27
        L_0x00fc:
            r27 = r11
            if (r27 == 0) goto L_0x0153
            r27 = r2
            r0 = r27
            int r0 = r0.mVisibility
            r27 = r0
            r28 = 8
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0153
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mLeft
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r27 = r0
            if (r27 != 0) goto L_0x0153
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r27 = r0
            if (r27 != 0) goto L_0x0153
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r13 = r27
            r27 = r3
            r28 = r13
            r29 = r5
            r30 = 0
            r31 = 1
            r27.addGreaterThan(r28, r29, r30, r31)
        L_0x0153:
            r27 = r12
            if (r27 == 0) goto L_0x01b4
            r27 = r2
            r0 = r27
            int r0 = r0.mVisibility
            r27 = r0
            r28 = 8
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x01b4
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r27 = r0
            if (r27 != 0) goto L_0x01b4
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r27 = r0
            if (r27 != 0) goto L_0x01b4
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r27 = r0
            if (r27 != 0) goto L_0x01b4
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r13 = r27
            r27 = r3
            r28 = r13
            r29 = r7
            r30 = 0
            r31 = 1
            r27.addGreaterThan(r28, r29, r30, r31)
        L_0x01b4:
            r27 = r2
            r0 = r27
            int r0 = r0.mWidth
            r27 = r0
            r13 = r27
            r27 = r13
            r28 = r2
            r0 = r28
            int r0 = r0.mMinWidth
            r28 = r0
            r0 = r27
            r1 = r28
            if (r0 >= r1) goto L_0x01d8
            r27 = r2
            r0 = r27
            int r0 = r0.mMinWidth
            r27 = r0
            r13 = r27
        L_0x01d8:
            r27 = r2
            r0 = r27
            int r0 = r0.mHeight
            r27 = r0
            r14 = r27
            r27 = r14
            r28 = r2
            r0 = r28
            int r0 = r0.mMinHeight
            r28 = r0
            r0 = r27
            r1 = r28
            if (r0 >= r1) goto L_0x01fc
            r27 = r2
            r0 = r27
            int r0 = r0.mMinHeight
            r27 = r0
            r14 = r27
        L_0x01fc:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0473
            r27 = 1
        L_0x0212:
            r15 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0477
            r27 = 1
        L_0x022a:
            r16 = r27
            r27 = 0
            r17 = r27
            r27 = r2
            r28 = r2
            r0 = r28
            int r0 = r0.mDimensionRatioSide
            r28 = r0
            r0 = r28
            r1 = r27
            r1.mResolvedDimensionRatioSide = r0
            r27 = r2
            r28 = r2
            r0 = r28
            float r0 = r0.mDimensionRatio
            r28 = r0
            r0 = r28
            r1 = r27
            r1.mResolvedDimensionRatio = r0
            r27 = r2
            r0 = r27
            int r0 = r0.mMatchConstraintDefaultWidth
            r27 = r0
            r18 = r27
            r27 = r2
            r0 = r27
            int r0 = r0.mMatchConstraintDefaultHeight
            r27 = r0
            r19 = r27
            r27 = r2
            r0 = r27
            float r0 = r0.mDimensionRatio
            r27 = r0
            r28 = 0
            int r27 = (r27 > r28 ? 1 : (r27 == r28 ? 0 : -1))
            if (r27 <= 0) goto L_0x0307
            r27 = r2
            r0 = r27
            int r0 = r0.mVisibility
            r27 = r0
            r28 = 8
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0307
            r27 = 1
            r17 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x02a2
            r27 = r18
            if (r27 != 0) goto L_0x02a2
            r27 = 3
            r18 = r27
        L_0x02a2:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x02be
            r27 = r19
            if (r27 != 0) goto L_0x02be
            r27 = 3
            r19 = r27
        L_0x02be:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x047b
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x047b
            r27 = r18
            r28 = 3
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x047b
            r27 = r19
            r28 = 3
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x047b
            r27 = r2
            r28 = r11
            r29 = r12
            r30 = r15
            r31 = r16
            r27.setupDimensionRatio(r28, r29, r30, r31)
        L_0x0307:
            r27 = r2
            r0 = r27
            int[] r0 = r0.mResolvedMatchConstraintDefault
            r27 = r0
            r28 = 0
            r29 = r18
            r27[r28] = r29
            r27 = r2
            r0 = r27
            int[] r0 = r0.mResolvedMatchConstraintDefault
            r27 = r0
            r28 = 1
            r29 = r19
            r27[r28] = r29
            r27 = r17
            if (r27 == 0) goto L_0x0567
            r27 = r2
            r0 = r27
            int r0 = r0.mResolvedDimensionRatioSide
            r27 = r0
            if (r27 == 0) goto L_0x0341
            r27 = r2
            r0 = r27
            int r0 = r0.mResolvedDimensionRatioSide
            r27 = r0
            r28 = -1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0567
        L_0x0341:
            r27 = 1
        L_0x0343:
            r20 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x056b
            r27 = r2
            r0 = r27
            boolean r0 = r0 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            r27 = r0
            if (r27 == 0) goto L_0x056b
            r27 = 1
        L_0x0365:
            r21 = r27
            r27 = 1
            r22 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mCenter
            r27 = r0
            boolean r27 = r27.isConnected()
            if (r27 == 0) goto L_0x037d
            r27 = 0
            r22 = r27
        L_0x037d:
            r27 = r2
            r0 = r27
            int r0 = r0.mHorizontalResolution
            r27 = r0
            r28 = 2
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x043e
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x056f
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
        L_0x03ab:
            r23 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x0573
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mLeft
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
        L_0x03cb:
            r24 = r27
            r27 = r2
            r28 = r3
            r29 = r11
            r30 = r24
            r31 = r23
            r32 = r2
            r0 = r32
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r32 = r0
            r33 = 0
            r32 = r32[r33]
            r33 = r21
            r34 = r2
            r0 = r34
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mLeft
            r34 = r0
            r35 = r2
            r0 = r35
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            r35 = r0
            r36 = r2
            r0 = r36
            int r0 = r0.f5mX
            r36 = r0
            r37 = r13
            r38 = r2
            r0 = r38
            int r0 = r0.mMinWidth
            r38 = r0
            r39 = r2
            r0 = r39
            int[] r0 = r0.mMaxDimension
            r39 = r0
            r40 = 0
            r39 = r39[r40]
            r40 = r2
            r0 = r40
            float r0 = r0.mHorizontalBiasPercent
            r40 = r0
            r41 = r20
            r42 = r9
            r43 = r18
            r44 = r2
            r0 = r44
            int r0 = r0.mMatchConstraintMinWidth
            r44 = r0
            r45 = r2
            r0 = r45
            int r0 = r0.mMatchConstraintMaxWidth
            r45 = r0
            r46 = r2
            r0 = r46
            float r0 = r0.mMatchConstraintPercentWidth
            r46 = r0
            r47 = r22
            r27.applyConstraints(r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
        L_0x043e:
            r27 = r2
            r0 = r27
            int r0 = r0.mVerticalResolution
            r27 = r0
            r28 = 2
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0577
        L_0x044e:
            return
        L_0x044f:
            r27 = 0
            goto L_0x0094
        L_0x0453:
            r27 = 0
            goto L_0x0094
        L_0x0457:
            r27 = 0
            goto L_0x00bc
        L_0x045b:
            r27 = 0
            goto L_0x00bc
        L_0x045f:
            r27 = r2
            boolean r27 = r27.isInHorizontalChain()
            r9 = r27
            goto L_0x00dd
        L_0x0469:
            r27 = r2
            boolean r27 = r27.isInVerticalChain()
            r10 = r27
            goto L_0x00fc
        L_0x0473:
            r27 = 0
            goto L_0x0212
        L_0x0477:
            r27 = 0
            goto L_0x022a
        L_0x047b:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x04df
            r27 = r18
            r28 = 3
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x04df
            r27 = r2
            r28 = 0
            r0 = r28
            r1 = r27
            r1.mResolvedDimensionRatioSide = r0
            r27 = r2
            r0 = r27
            float r0 = r0.mResolvedDimensionRatio
            r27 = r0
            r28 = r2
            r0 = r28
            int r0 = r0.mHeight
            r28 = r0
            r0 = r28
            float r0 = (float) r0
            r28 = r0
            float r27 = r27 * r28
            r0 = r27
            int r0 = (int) r0
            r27 = r0
            r13 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0307
            r27 = 4
            r18 = r27
            r27 = 0
            r17 = r27
            goto L_0x0307
        L_0x04df:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0307
            r27 = r19
            r28 = 3
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0307
            r27 = r2
            r28 = 1
            r0 = r28
            r1 = r27
            r1.mResolvedDimensionRatioSide = r0
            r27 = r2
            r0 = r27
            int r0 = r0.mDimensionRatioSide
            r27 = r0
            r28 = -1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x052b
            r27 = r2
            r28 = 1065353216(0x3f800000, float:1.0)
            r29 = r2
            r0 = r29
            float r0 = r0.mResolvedDimensionRatio
            r29 = r0
            float r28 = r28 / r29
            r0 = r28
            r1 = r27
            r1.mResolvedDimensionRatio = r0
        L_0x052b:
            r27 = r2
            r0 = r27
            float r0 = r0.mResolvedDimensionRatio
            r27 = r0
            r28 = r2
            r0 = r28
            int r0 = r0.mWidth
            r28 = r0
            r0 = r28
            float r0 = (float) r0
            r28 = r0
            float r27 = r27 * r28
            r0 = r27
            int r0 = (int) r0
            r27 = r0
            r14 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 0
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x0307
            r27 = 4
            r19 = r27
            r27 = 0
            r17 = r27
            goto L_0x0307
        L_0x0567:
            r27 = 0
            goto L_0x0343
        L_0x056b:
            r27 = 0
            goto L_0x0365
        L_0x056f:
            r27 = 0
            goto L_0x03ab
        L_0x0573:
            r27 = 0
            goto L_0x03cb
        L_0x0577:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r27 = r0
            r28 = 1
            r27 = r27[r28]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r28 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0723
            r27 = r2
            r0 = r27
            boolean r0 = r0 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            r27 = r0
            if (r27 == 0) goto L_0x0723
            r27 = 1
        L_0x0597:
            r21 = r27
            r27 = r17
            if (r27 == 0) goto L_0x0727
            r27 = r2
            r0 = r27
            int r0 = r0.mResolvedDimensionRatioSide
            r27 = r0
            r28 = 1
            r0 = r27
            r1 = r28
            if (r0 == r1) goto L_0x05bd
            r27 = r2
            r0 = r27
            int r0 = r0.mResolvedDimensionRatioSide
            r27 = r0
            r28 = -1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0727
        L_0x05bd:
            r27 = 1
        L_0x05bf:
            r23 = r27
            r27 = r2
            r0 = r27
            int r0 = r0.mBaselineDistance
            r27 = r0
            if (r27 <= 0) goto L_0x05f6
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r27 = r0
            android.support.constraint.solver.widgets.ResolutionAnchor r27 = r27.getResolutionNode()
            r0 = r27
            int r0 = r0.state
            r27 = r0
            r28 = 1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x072b
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r27 = r0
            android.support.constraint.solver.widgets.ResolutionAnchor r27 = r27.getResolutionNode()
            r28 = r3
            r27.addResolvedValue(r28)
        L_0x05f6:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x077b
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
        L_0x0614:
            r24 = r27
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r27 = r0
            if (r27 == 0) goto L_0x077f
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
        L_0x0634:
            r25 = r27
            r27 = r2
            r28 = r3
            r29 = r12
            r30 = r25
            r31 = r24
            r32 = r2
            r0 = r32
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r32 = r0
            r33 = 1
            r32 = r32[r33]
            r33 = r21
            r34 = r2
            r0 = r34
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            r34 = r0
            r35 = r2
            r0 = r35
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            r35 = r0
            r36 = r2
            r0 = r36
            int r0 = r0.f6mY
            r36 = r0
            r37 = r14
            r38 = r2
            r0 = r38
            int r0 = r0.mMinHeight
            r38 = r0
            r39 = r2
            r0 = r39
            int[] r0 = r0.mMaxDimension
            r39 = r0
            r40 = 1
            r39 = r39[r40]
            r40 = r2
            r0 = r40
            float r0 = r0.mVerticalBiasPercent
            r40 = r0
            r41 = r23
            r42 = r10
            r43 = r19
            r44 = r2
            r0 = r44
            int r0 = r0.mMatchConstraintMinHeight
            r44 = r0
            r45 = r2
            r0 = r45
            int r0 = r0.mMatchConstraintMaxHeight
            r45 = r0
            r46 = r2
            r0 = r46
            float r0 = r0.mMatchConstraintPercentHeight
            r46 = r0
            r47 = r22
            r27.applyConstraints(r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
            r27 = r17
            if (r27 == 0) goto L_0x06d6
            r27 = 6
            r26 = r27
            r27 = r2
            r0 = r27
            int r0 = r0.mResolvedDimensionRatioSide
            r27 = r0
            r28 = 1
            r0 = r27
            r1 = r28
            if (r0 != r1) goto L_0x0783
            r27 = r3
            r28 = r7
            r29 = r6
            r30 = r5
            r31 = r4
            r32 = r2
            r0 = r32
            float r0 = r0.mResolvedDimensionRatio
            r32 = r0
            r33 = r26
            r27.addRatio(r28, r29, r30, r31, r32, r33)
        L_0x06d6:
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mCenter
            r27 = r0
            boolean r27 = r27.isConnected()
            if (r27 == 0) goto L_0x0721
            r27 = r3
            r28 = r2
            r29 = r2
            r0 = r29
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mCenter
            r29 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor r29 = r29.getTarget()
            android.support.constraint.solver.widgets.ConstraintWidget r29 = r29.getOwner()
            r30 = r2
            r0 = r30
            float r0 = r0.mCircleConstraintAngle
            r30 = r0
            r31 = 1119092736(0x42b40000, float:90.0)
            float r30 = r30 + r31
            r0 = r30
            double r0 = (double) r0
            r30 = r0
            double r30 = java.lang.Math.toRadians(r30)
            r0 = r30
            float r0 = (float) r0
            r30 = r0
            r31 = r2
            r0 = r31
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mCenter
            r31 = r0
            int r31 = r31.getMargin()
            r27.addCenterPoint(r28, r29, r30, r31)
        L_0x0721:
            goto L_0x044e
        L_0x0723:
            r27 = 0
            goto L_0x0597
        L_0x0727:
            r27 = 0
            goto L_0x05bf
        L_0x072b:
            r27 = r3
            r28 = r8
            r29 = r6
            r30 = r2
            int r30 = r30.getBaselineDistance()
            r31 = 6
            android.support.constraint.solver.ArrayRow r27 = r27.addEquality(r28, r29, r30, r31)
            r27 = r2
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r27 = r0
            r0 = r27
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r27 = r0
            if (r27 == 0) goto L_0x05f6
            r27 = r3
            r28 = r2
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            r28 = r0
            r0 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            r28 = r0
            android.support.constraint.solver.SolverVariable r27 = r27.createObjectVariable(r28)
            r24 = r27
            r27 = 0
            r25 = r27
            r27 = r3
            r28 = r8
            r29 = r24
            r30 = r25
            r31 = 6
            android.support.constraint.solver.ArrayRow r27 = r27.addEquality(r28, r29, r30, r31)
            r27 = 0
            r22 = r27
            goto L_0x05f6
        L_0x077b:
            r27 = 0
            goto L_0x0614
        L_0x077f:
            r27 = 0
            goto L_0x0634
        L_0x0783:
            r27 = r3
            r28 = r5
            r29 = r4
            r30 = r7
            r31 = r6
            r32 = r2
            r0 = r32
            float r0 = r0.mResolvedDimensionRatio
            r32 = r0
            r33 = r26
            r27.addRatio(r28, r29, r30, r31, r32, r33)
            goto L_0x06d6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        boolean hparentWrapContent = z;
        boolean vparentWrapContent = z2;
        boolean horizontalDimensionFixed = z3;
        boolean verticalDimensionFixed = z4;
        if (this.mResolvedDimensionRatioSide == -1) {
            if (horizontalDimensionFixed && !verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (hparentWrapContent && !vparentWrapContent) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!hparentWrapContent && vparentWrapContent) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && hparentWrapContent && vparentWrapContent) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    private void applyConstraints(LinearSystem linearSystem, boolean z, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7, float f2, boolean z5) {
        SolverVariable percentEnd;
        SolverVariable percentBegin;
        LinearSystem system = linearSystem;
        boolean parentWrapContent = z;
        SolverVariable parentMin = solverVariable;
        SolverVariable parentMax = solverVariable2;
        DimensionBehaviour dimensionBehaviour2 = dimensionBehaviour;
        boolean wrapContent = z2;
        ConstraintAnchor beginAnchor = constraintAnchor;
        ConstraintAnchor endAnchor = constraintAnchor2;
        int beginPosition = i;
        int dimension = i2;
        int minDimension = i3;
        int maxDimension = i4;
        float bias = f;
        boolean useRatio = z3;
        boolean inChain = z4;
        int matchConstraintDefault = i5;
        int matchMinDimension = i6;
        int matchMaxDimension = i7;
        float matchPercentDimension = f2;
        boolean applyPosition = z5;
        SolverVariable begin = system.createObjectVariable(beginAnchor);
        SolverVariable end = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget = system.createObjectVariable(endAnchor.getTarget());
        if (system.graphOptimizer && beginAnchor.getResolutionNode().state == 1 && endAnchor.getResolutionNode().state == 1) {
            LinearSystem linearSystem2 = system;
            if (LinearSystem.getMetrics() != null) {
                LinearSystem linearSystem3 = system;
                LinearSystem.getMetrics().resolvedWidgets++;
            }
            beginAnchor.getResolutionNode().addResolvedValue(system);
            endAnchor.getResolutionNode().addResolvedValue(system);
            if (!inChain && parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 6);
                return;
            }
            return;
        }
        LinearSystem linearSystem4 = system;
        if (LinearSystem.getMetrics() != null) {
            LinearSystem linearSystem5 = system;
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isBeginConnected = beginAnchor.isConnected();
        boolean isEndConnected = endAnchor.isConnected();
        boolean isCenterConnected = this.mCenter.isConnected();
        boolean variableSize = false;
        int numConnections = 0;
        if (isBeginConnected) {
            numConnections = 0 + 1;
        }
        if (isEndConnected) {
            numConnections++;
        }
        if (isCenterConnected) {
            numConnections++;
        }
        if (useRatio) {
            matchConstraintDefault = 3;
        }
        switch (dimensionBehaviour2) {
            case FIXED:
                variableSize = false;
                break;
            case WRAP_CONTENT:
                variableSize = false;
                break;
            case MATCH_PARENT:
                variableSize = false;
                break;
            case MATCH_CONSTRAINT:
                variableSize = true;
                if (matchConstraintDefault == 4) {
                    variableSize = false;
                    break;
                }
                break;
        }
        if (this.mVisibility == 8) {
            dimension = 0;
            variableSize = false;
        }
        if (applyPosition) {
            if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
                system.addEquality(begin, beginPosition);
            } else if (isBeginConnected && !isEndConnected) {
                ArrayRow addEquality = system.addEquality(begin, beginTarget, beginAnchor.getMargin(), 6);
            }
        }
        if (variableSize) {
            if (matchMinDimension == -2) {
                matchMinDimension = dimension;
            }
            if (matchMaxDimension == -2) {
                matchMaxDimension = dimension;
            }
            if (matchMinDimension > 0) {
                system.addGreaterThan(end, begin, matchMinDimension, 6);
                dimension = Math.max(dimension, matchMinDimension);
            }
            if (matchMaxDimension > 0) {
                system.addLowerThan(end, begin, matchMaxDimension, 6);
                dimension = Math.min(dimension, matchMaxDimension);
            }
            if (matchConstraintDefault == 1) {
                if (parentWrapContent) {
                    ArrayRow addEquality2 = system.addEquality(end, begin, dimension, 6);
                } else if (inChain) {
                    ArrayRow addEquality3 = system.addEquality(end, begin, dimension, 4);
                } else {
                    ArrayRow addEquality4 = system.addEquality(end, begin, dimension, 1);
                }
            } else if (matchConstraintDefault == 2) {
                if (beginAnchor.getType() == ConstraintAnchor.Type.TOP || beginAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                    percentBegin = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                } else {
                    percentBegin = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                }
                system.addConstraint(system.createRow().createRowDimensionRatio(end, begin, percentEnd, percentBegin, matchPercentDimension));
                variableSize = false;
            }
            if (variableSize && numConnections != 2 && !useRatio) {
                variableSize = false;
                int d = Math.max(matchMinDimension, dimension);
                if (matchMaxDimension > 0) {
                    d = Math.min(matchMaxDimension, d);
                }
                ArrayRow addEquality5 = system.addEquality(end, begin, d, 6);
            }
        } else if (wrapContent) {
            ArrayRow addEquality6 = system.addEquality(end, begin, 0, 3);
            if (minDimension > 0) {
                system.addGreaterThan(end, begin, minDimension, 6);
            }
            if (maxDimension < Integer.MAX_VALUE) {
                system.addLowerThan(end, begin, maxDimension, 6);
            }
        } else {
            ArrayRow addEquality7 = system.addEquality(end, begin, dimension, 6);
        }
        if (applyPosition && !inChain) {
            if (isBeginConnected || isEndConnected || isCenterConnected) {
                if (!isBeginConnected || isEndConnected) {
                    if (!isBeginConnected && isEndConnected) {
                        ArrayRow addEquality8 = system.addEquality(end, endTarget, -endAnchor.getMargin(), 6);
                        if (parentWrapContent) {
                            system.addGreaterThan(begin, parentMin, 0, 5);
                        }
                    } else if (isBeginConnected && isEndConnected) {
                        boolean applyBoundsCheck = false;
                        boolean applyCentering = false;
                        int centeringStrength = 5;
                        if (variableSize) {
                            if (parentWrapContent && minDimension == 0) {
                                system.addGreaterThan(end, begin, 0, 6);
                            }
                            if (matchConstraintDefault == 0) {
                                int strength = 6;
                                if (matchMaxDimension > 0 || matchMinDimension > 0) {
                                    strength = 4;
                                    applyBoundsCheck = true;
                                }
                                ArrayRow addEquality9 = system.addEquality(begin, beginTarget, beginAnchor.getMargin(), strength);
                                ArrayRow addEquality10 = system.addEquality(end, endTarget, -endAnchor.getMargin(), strength);
                                if (matchMaxDimension > 0 || matchMinDimension > 0) {
                                    applyCentering = true;
                                }
                            } else if (matchConstraintDefault == 1) {
                                applyCentering = true;
                                applyBoundsCheck = true;
                                centeringStrength = 6;
                            } else if (matchConstraintDefault == 3) {
                                applyCentering = true;
                                applyBoundsCheck = true;
                                int strength2 = 4;
                                if (!useRatio) {
                                    if (this.mResolvedDimensionRatioSide != -1 && matchMaxDimension <= 0) {
                                        strength2 = 6;
                                    }
                                }
                                ArrayRow addEquality11 = system.addEquality(begin, beginTarget, beginAnchor.getMargin(), strength2);
                                ArrayRow addEquality12 = system.addEquality(end, endTarget, -endAnchor.getMargin(), strength2);
                            }
                        } else {
                            applyCentering = true;
                        }
                        int startStrength = 5;
                        int endStrength = 5;
                        boolean applyStartConstraint = parentWrapContent;
                        boolean applyEndConstraint = parentWrapContent;
                        if (applyCentering) {
                            system.addCentering(begin, beginTarget, beginAnchor.getMargin(), bias, endTarget, end, endAnchor.getMargin(), centeringStrength);
                            boolean isBeginAnchorBarrier = beginAnchor.mTarget.mOwner instanceof Barrier;
                            boolean isEndAnchorBarrier = endAnchor.mTarget.mOwner instanceof Barrier;
                            if (isBeginAnchorBarrier && !isEndAnchorBarrier) {
                                endStrength = 6;
                                applyEndConstraint = true;
                            } else if (!isBeginAnchorBarrier && isEndAnchorBarrier) {
                                startStrength = 6;
                                applyStartConstraint = true;
                            }
                        }
                        if (applyBoundsCheck) {
                            startStrength = 6;
                            endStrength = 6;
                        }
                        if ((!variableSize && applyStartConstraint) || applyBoundsCheck) {
                            system.addGreaterThan(begin, beginTarget, beginAnchor.getMargin(), startStrength);
                        }
                        if ((!variableSize && applyEndConstraint) || applyBoundsCheck) {
                            system.addLowerThan(end, endTarget, -endAnchor.getMargin(), endStrength);
                        }
                        if (parentWrapContent) {
                            system.addGreaterThan(begin, parentMin, 0, 6);
                        }
                    }
                } else if (parentWrapContent) {
                    system.addGreaterThan(parentMax, end, 0, 5);
                }
            } else if (parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 5);
            }
            if (parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 6);
            }
        } else if (numConnections < 2 && parentWrapContent) {
            system.addGreaterThan(begin, parentMin, 0, 6);
            system.addGreaterThan(parentMax, end, 0, 6);
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        LinearSystem system = linearSystem;
        int left = system.getObjectVariableValue(this.mLeft);
        int top = system.getObjectVariableValue(this.mTop);
        int right = system.getObjectVariableValue(this.mRight);
        int bottom = system.getObjectVariableValue(this.mBottom);
        int h = bottom - top;
        if (right - left < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        setFrame(left, top, right, bottom);
    }
}
