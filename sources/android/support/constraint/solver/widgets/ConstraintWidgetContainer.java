package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    int mDebugSolverPassCount;
    public boolean mGroupsWrapOptimized;
    private boolean mHeightMeasuredTooSmall;
    ChainHead[] mHorizontalChainsArray;
    int mHorizontalChainsSize;
    public boolean mHorizontalWrapOptimized;
    private boolean mIsRtl = false;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    ChainHead[] mVerticalChainsArray;
    int mVerticalChainsSize;
    public boolean mVerticalWrapOptimized;
    public List<ConstraintWidgetGroup> mWidgetGroups;
    private boolean mWidthMeasuredTooSmall;
    public int mWrapFixedHeight;
    public int mWrapFixedWidth;

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
        LinearSystem linearSystem;
        List<ConstraintWidgetGroup> list;
        new LinearSystem();
        this.mSystem = linearSystem;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        new ArrayList();
        this.mWidgetGroups = list;
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintWidgetContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
        LinearSystem linearSystem;
        List<ConstraintWidgetGroup> list;
        new LinearSystem();
        this.mSystem = linearSystem;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        new ArrayList();
        this.mWidgetGroups = list;
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintWidgetContainer(int width, int height) {
        super(width, height);
        LinearSystem linearSystem;
        List<ConstraintWidgetGroup> list;
        new LinearSystem();
        this.mSystem = linearSystem;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        new ArrayList();
        this.mWidgetGroups = list;
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public void setOptimizationLevel(int value) {
        int i = value;
        this.mOptimizationLevel = i;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        int feature = i;
        return (this.mOptimizationLevel & feature) == feature;
    }

    public String getType() {
        return "ConstraintLayout";
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mWidgetGroups.clear();
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        LinearSystem system = linearSystem;
        addToSolver(system);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            if (widget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour horizontalBehaviour = widget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour verticalBehaviour = widget.mListDimensionBehaviors[1];
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                widget.addToSolver(system);
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(horizontalBehaviour);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(verticalBehaviour);
                }
            } else {
                Optimizer.checkMatchParent(this, system, widget);
                widget.addToSolver(system);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, system, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, system, 1);
        }
        return true;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        LinearSystem system = linearSystem;
        boolean[] flags = zArr;
        flags[2] = false;
        updateFromSolver(system);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            widget.updateFromSolver(system);
            if (widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getWidth() < widget.getWrapWidth()) {
                flags[2] = true;
            }
            if (widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getHeight() < widget.getWrapHeight()) {
                flags[2] = true;
            }
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
    }

    public void setRtl(boolean isRtl) {
        boolean z = isRtl;
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public void analyze(int i) {
        int optimizationLevel = i;
        super.analyze(optimizationLevel);
        int count = this.mChildren.size();
        for (int i2 = 0; i2 < count; i2++) {
            ((ConstraintWidget) this.mChildren.get(i2)).analyze(optimizationLevel);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x016c, code lost:
        r21 = getVerticalDimensionBehaviour();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r27 = this;
            r2 = r27
            r21 = r2
            r0 = r21
            int r0 = r0.f5mX
            r21 = r0
            r3 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.f6mY
            r21 = r0
            r4 = r21
            r21 = 0
            r22 = r2
            int r22 = r22.getWidth()
            int r21 = java.lang.Math.max(r21, r22)
            r5 = r21
            r21 = 0
            r22 = r2
            int r22 = r22.getHeight()
            int r21 = java.lang.Math.max(r21, r22)
            r6 = r21
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.mWidthMeasuredTooSmall = r0
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.mHeightMeasuredTooSmall = r0
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r21 = r0
            if (r21 == 0) goto L_0x01b1
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.Snapshot r0 = r0.mSnapshot
            r21 = r0
            if (r21 != 0) goto L_0x006f
            r21 = r2
            android.support.constraint.solver.widgets.Snapshot r22 = new android.support.constraint.solver.widgets.Snapshot
            r26 = r22
            r22 = r26
            r23 = r26
            r24 = r2
            r23.<init>(r24)
            r0 = r22
            r1 = r21
            r1.mSnapshot = r0
        L_0x006f:
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.Snapshot r0 = r0.mSnapshot
            r21 = r0
            r22 = r2
            r21.updateFrom(r22)
            r21 = r2
            r22 = r2
            r0 = r22
            int r0 = r0.mPaddingLeft
            r22 = r0
            r21.setX(r22)
            r21 = r2
            r22 = r2
            r0 = r22
            int r0 = r0.mPaddingTop
            r22 = r0
            r21.setY(r22)
            r21 = r2
            r21.resetAnchors()
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r22 = r0
            android.support.constraint.solver.Cache r22 = r22.getCache()
            r21.resetSolverVariables(r22)
        L_0x00ac:
            r21 = r2
            r0 = r21
            int r0 = r0.mOptimizationLevel
            r21 = r0
            if (r21 == 0) goto L_0x01c7
            r21 = r2
            r22 = 8
            boolean r21 = r21.optimizeFor(r22)
            if (r21 != 0) goto L_0x00c5
            r21 = r2
            r21.optimizeReset()
        L_0x00c5:
            r21 = r2
            r22 = 32
            boolean r21 = r21.optimizeFor(r22)
            if (r21 != 0) goto L_0x00d4
            r21 = r2
            r21.optimize()
        L_0x00d4:
            r21 = r2
            r0 = r21
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r21 = r0
            r22 = 1
            r0 = r22
            r1 = r21
            r1.graphOptimizer = r0
        L_0x00e4:
            r21 = 0
            r7 = r21
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            r21 = r21[r22]
            r8 = r21
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            r21 = r21[r22]
            r9 = r21
            r21 = r2
            r21.resetChains()
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            int r21 = r21.size()
            if (r21 != 0) goto L_0x0142
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            r21.clear()
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            r22 = 0
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r23 = new android.support.constraint.solver.widgets.ConstraintWidgetGroup
            r26 = r23
            r23 = r26
            r24 = r26
            r25 = r2
            r0 = r25
            java.util.ArrayList r0 = r0.mChildren
            r25 = r0
            r24.<init>(r25)
            r21.add(r22, r23)
        L_0x0142:
            r21 = 0
            r10 = r21
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            int r21 = r21.size()
            r11 = r21
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren
            r21 = r0
            r12 = r21
            r21 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r21 = r21.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x017a
            r21 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r21 = r21.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x01d9
        L_0x017a:
            r21 = 1
        L_0x017c:
            r13 = r21
            r21 = 0
            r14 = r21
        L_0x0182:
            r21 = r14
            r22 = r11
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0602
            r21 = r2
            r0 = r21
            boolean r0 = r0.mSkipSolver
            r21 = r0
            if (r21 != 0) goto L_0x0602
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            r22 = r14
            java.lang.Object r21 = r21.get(r22)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r21 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r21
            r0 = r21
            boolean r0 = r0.mSkipSolver
            r21 = r0
            if (r21 == 0) goto L_0x01dc
        L_0x01ae:
            int r14 = r14 + 1
            goto L_0x0182
        L_0x01b1:
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.f5mX = r0
            r21 = r2
            r22 = 0
            r0 = r22
            r1 = r21
            r1.f6mY = r0
            goto L_0x00ac
        L_0x01c7:
            r21 = r2
            r0 = r21
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r21 = r0
            r22 = 0
            r0 = r22
            r1 = r21
            r1.graphOptimizer = r0
            goto L_0x00e4
        L_0x01d9:
            r21 = 0
            goto L_0x017c
        L_0x01dc:
            r21 = r2
            r22 = 32
            boolean r21 = r21.optimizeFor(r22)
            if (r21 == 0) goto L_0x0220
            r21 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r21 = r21.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x026b
            r21 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r21 = r21.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x026b
            r21 = r2
            r22 = r2
            r0 = r22
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r22 = r0
            r23 = r14
            java.lang.Object r22 = r22.get(r23)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r22 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r22
            java.util.List r22 = r22.getWidgetsToSolve()
            java.util.ArrayList r22 = (java.util.ArrayList) r22
            r0 = r22
            r1 = r21
            r1.mChildren = r0
        L_0x0220:
            r21 = r2
            r21.resetChains()
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren
            r21 = r0
            int r21 = r21.size()
            r15 = r21
            r21 = 0
            r10 = r21
            r21 = 0
            r16 = r21
        L_0x023b:
            r21 = r16
            r22 = r15
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x028c
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren
            r21 = r0
            r22 = r16
            java.lang.Object r21 = r21.get(r22)
            android.support.constraint.solver.widgets.ConstraintWidget r21 = (android.support.constraint.solver.widgets.ConstraintWidget) r21
            r17 = r21
            r21 = r17
            r0 = r21
            boolean r0 = r0 instanceof android.support.constraint.solver.widgets.WidgetContainer
            r21 = r0
            if (r21 == 0) goto L_0x0268
            r21 = r17
            android.support.constraint.solver.widgets.WidgetContainer r21 = (android.support.constraint.solver.widgets.WidgetContainer) r21
            r21.layout()
        L_0x0268:
            int r16 = r16 + 1
            goto L_0x023b
        L_0x026b:
            r21 = r2
            r22 = r2
            r0 = r22
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r22 = r0
            r23 = r14
            java.lang.Object r22 = r22.get(r23)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r22 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r22
            r0 = r22
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r0.mConstrainedGroup
            r22 = r0
            java.util.ArrayList r22 = (java.util.ArrayList) r22
            r0 = r22
            r1 = r21
            r1.mChildren = r0
            goto L_0x0220
        L_0x028c:
            r21 = 1
            r16 = r21
        L_0x0290:
            r21 = r16
            if (r21 == 0) goto L_0x05ed
            int r10 = r10 + 1
            r21 = r2
            r0 = r21
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem     // Catch:{ Exception -> 0x038a }
            r21 = r0
            r21.reset()     // Catch:{ Exception -> 0x038a }
            r21 = r2
            r21.resetChains()     // Catch:{ Exception -> 0x038a }
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem     // Catch:{ Exception -> 0x038a }
            r22 = r0
            r21.createObjectVariables(r22)     // Catch:{ Exception -> 0x038a }
            r21 = 0
            r17 = r21
        L_0x02b7:
            r21 = r17
            r22 = r15
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x02e3
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren     // Catch:{ Exception -> 0x038a }
            r21 = r0
            r22 = r17
            java.lang.Object r21 = r21.get(r22)     // Catch:{ Exception -> 0x038a }
            android.support.constraint.solver.widgets.ConstraintWidget r21 = (android.support.constraint.solver.widgets.ConstraintWidget) r21     // Catch:{ Exception -> 0x038a }
            r18 = r21
            r21 = r18
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem     // Catch:{ Exception -> 0x038a }
            r22 = r0
            r21.createObjectVariables(r22)     // Catch:{ Exception -> 0x038a }
            int r17 = r17 + 1
            goto L_0x02b7
        L_0x02e3:
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem     // Catch:{ Exception -> 0x038a }
            r22 = r0
            boolean r21 = r21.addChildrenToSolver(r22)     // Catch:{ Exception -> 0x038a }
            r16 = r21
            r21 = r16
            if (r21 == 0) goto L_0x0302
            r21 = r2
            r0 = r21
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem     // Catch:{ Exception -> 0x038a }
            r21 = r0
            r21.minimize()     // Catch:{ Exception -> 0x038a }
        L_0x0302:
            r21 = r16
            if (r21 == 0) goto L_0x03b5
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r22 = r0
            boolean[] r23 = android.support.constraint.solver.widgets.Optimizer.flags
            r21.updateChildrenFromSolver(r22, r23)
        L_0x0315:
            r21 = 0
            r16 = r21
            r21 = r13
            if (r21 == 0) goto L_0x04cf
            r21 = r10
            r22 = 8
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x04cf
            boolean[] r21 = android.support.constraint.solver.widgets.Optimizer.flags
            r22 = 2
            boolean r21 = r21[r22]
            if (r21 == 0) goto L_0x04cf
            r21 = 0
            r17 = r21
            r21 = 0
            r18 = r21
            r21 = 0
            r19 = r21
        L_0x033b:
            r21 = r19
            r22 = r15
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0445
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren
            r21 = r0
            r22 = r19
            java.lang.Object r21 = r21.get(r22)
            android.support.constraint.solver.widgets.ConstraintWidget r21 = (android.support.constraint.solver.widgets.ConstraintWidget) r21
            r20 = r21
            r21 = r17
            r22 = r20
            r0 = r22
            int r0 = r0.f5mX
            r22 = r0
            r23 = r20
            int r23 = r23.getWidth()
            int r22 = r22 + r23
            int r21 = java.lang.Math.max(r21, r22)
            r17 = r21
            r21 = r18
            r22 = r20
            r0 = r22
            int r0 = r0.f6mY
            r22 = r0
            r23 = r20
            int r23 = r23.getHeight()
            int r22 = r22 + r23
            int r21 = java.lang.Math.max(r21, r22)
            r18 = r21
            int r19 = r19 + 1
            goto L_0x033b
        L_0x038a:
            r21 = move-exception
            r17 = r21
            r21 = r17
            r21.printStackTrace()
            java.io.PrintStream r21 = java.lang.System.out
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r26 = r22
            r22 = r26
            r23 = r26
            r23.<init>()
            java.lang.String r23 = "EXCEPTION : "
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r17
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r21.println(r22)
            goto L_0x0302
        L_0x03b5:
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r22 = r0
            r21.updateFromSolver(r22)
            r21 = 0
            r17 = r21
        L_0x03c6:
            r21 = r17
            r22 = r15
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0315
            r21 = r2
            r0 = r21
            java.util.ArrayList r0 = r0.mChildren
            r21 = r0
            r22 = r17
            java.lang.Object r21 = r21.get(r22)
            android.support.constraint.solver.widgets.ConstraintWidget r21 = (android.support.constraint.solver.widgets.ConstraintWidget) r21
            r18 = r21
            r21 = r18
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            r21 = r21[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0412
            r21 = r18
            int r21 = r21.getWidth()
            r22 = r18
            int r22 = r22.getWrapWidth()
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0412
            boolean[] r21 = android.support.constraint.solver.widgets.Optimizer.flags
            r22 = 2
            r23 = 1
            r21[r22] = r23
            goto L_0x0315
        L_0x0412:
            r21 = r18
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            r21 = r21[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0442
            r21 = r18
            int r21 = r21.getHeight()
            r22 = r18
            int r22 = r22.getWrapHeight()
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x0442
            boolean[] r21 = android.support.constraint.solver.widgets.Optimizer.flags
            r22 = 2
            r23 = 1
            r21[r22] = r23
            goto L_0x0315
        L_0x0442:
            int r17 = r17 + 1
            goto L_0x03c6
        L_0x0445:
            r21 = r2
            r0 = r21
            int r0 = r0.mMinWidth
            r21 = r0
            r22 = r17
            int r21 = java.lang.Math.max(r21, r22)
            r17 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.mMinHeight
            r21 = r0
            r22 = r18
            int r21 = java.lang.Math.max(r21, r22)
            r18 = r21
            r21 = r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x049a
            r21 = r2
            int r21 = r21.getWidth()
            r22 = r17
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x049a
            r21 = r2
            r22 = r17
            r21.setWidth(r22)
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r21[r22] = r23
            r21 = 1
            r7 = r21
            r21 = 1
            r16 = r21
        L_0x049a:
            r21 = r8
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x04cf
            r21 = r2
            int r21 = r21.getHeight()
            r22 = r18
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x04cf
            r21 = r2
            r22 = r18
            r21.setHeight(r22)
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r21[r22] = r23
            r21 = 1
            r7 = r21
            r21 = 1
            r16 = r21
        L_0x04cf:
            r21 = r2
            r0 = r21
            int r0 = r0.mMinWidth
            r21 = r0
            r22 = r2
            int r22 = r22.getWidth()
            int r21 = java.lang.Math.max(r21, r22)
            r17 = r21
            r21 = r17
            r22 = r2
            int r22 = r22.getWidth()
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x050e
            r21 = r2
            r22 = r17
            r21.setWidth(r22)
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r21[r22] = r23
            r21 = 1
            r7 = r21
            r21 = 1
            r16 = r21
        L_0x050e:
            r21 = r2
            r0 = r21
            int r0 = r0.mMinHeight
            r21 = r0
            r22 = r2
            int r22 = r22.getHeight()
            int r21 = java.lang.Math.max(r21, r22)
            r18 = r21
            r21 = r18
            r22 = r2
            int r22 = r22.getHeight()
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x054d
            r21 = r2
            r22 = r18
            r21.setHeight(r22)
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r21[r22] = r23
            r21 = 1
            r7 = r21
            r21 = 1
            r16 = r21
        L_0x054d:
            r21 = r7
            if (r21 != 0) goto L_0x05eb
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            r21 = r21[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x059e
            r21 = r5
            if (r21 <= 0) goto L_0x059e
            r21 = r2
            int r21 = r21.getWidth()
            r22 = r5
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x059e
            r21 = r2
            r22 = 1
            r0 = r22
            r1 = r21
            r1.mWidthMeasuredTooSmall = r0
            r21 = 1
            r7 = r21
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r21[r22] = r23
            r21 = r2
            r22 = r5
            r21.setWidth(r22)
            r21 = 1
            r16 = r21
        L_0x059e:
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            r21 = r21[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r22 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x05eb
            r21 = r6
            if (r21 <= 0) goto L_0x05eb
            r21 = r2
            int r21 = r21.getHeight()
            r22 = r6
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x05eb
            r21 = r2
            r22 = 1
            r0 = r22
            r1 = r21
            r1.mHeightMeasuredTooSmall = r0
            r21 = 1
            r7 = r21
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r23 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r21[r22] = r23
            r21 = r2
            r22 = r6
            r21.setHeight(r22)
            r21 = 1
            r16 = r21
        L_0x05eb:
            goto L_0x0290
        L_0x05ed:
            r21 = r2
            r0 = r21
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r0.mWidgetGroups
            r21 = r0
            r22 = r14
            java.lang.Object r21 = r21.get(r22)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r21 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r21
            r21.updateUnresolvedWidgets()
            goto L_0x01ae
        L_0x0602:
            r21 = r2
            r22 = r12
            java.util.ArrayList r22 = (java.util.ArrayList) r22
            r0 = r22
            r1 = r21
            r1.mChildren = r0
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.mParent
            r21 = r0
            if (r21 == 0) goto L_0x06c8
            r21 = r2
            r0 = r21
            int r0 = r0.mMinWidth
            r21 = r0
            r22 = r2
            int r22 = r22.getWidth()
            int r21 = java.lang.Math.max(r21, r22)
            r14 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.mMinHeight
            r21 = r0
            r22 = r2
            int r22 = r22.getHeight()
            int r21 = java.lang.Math.max(r21, r22)
            r15 = r21
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.Snapshot r0 = r0.mSnapshot
            r21 = r0
            r22 = r2
            r21.applyTo(r22)
            r21 = r2
            r22 = r14
            r23 = r2
            r0 = r23
            int r0 = r0.mPaddingLeft
            r23 = r0
            int r22 = r22 + r23
            r23 = r2
            r0 = r23
            int r0 = r0.mPaddingRight
            r23 = r0
            int r22 = r22 + r23
            r21.setWidth(r22)
            r21 = r2
            r22 = r15
            r23 = r2
            r0 = r23
            int r0 = r0.mPaddingTop
            r23 = r0
            int r22 = r22 + r23
            r23 = r2
            r0 = r23
            int r0 = r0.mPaddingBottom
            r23 = r0
            int r22 = r22 + r23
            r21.setHeight(r22)
        L_0x0683:
            r21 = r7
            if (r21 == 0) goto L_0x06a3
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 0
            r23 = r9
            r21[r22] = r23
            r21 = r2
            r0 = r21
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r21 = r0
            r22 = 1
            r23 = r8
            r21[r22] = r23
        L_0x06a3:
            r21 = r2
            r22 = r2
            r0 = r22
            android.support.constraint.solver.LinearSystem r0 = r0.mSystem
            r22 = r0
            android.support.constraint.solver.Cache r22 = r22.getCache()
            r21.resetSolverVariables(r22)
            r21 = r2
            r22 = r2
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r22 = r22.getRootConstraintContainer()
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x06c7
            r21 = r2
            r21.updateDrawPosition()
        L_0x06c7:
            return
        L_0x06c8:
            r21 = r2
            r22 = r3
            r0 = r22
            r1 = r21
            r1.f5mX = r0
            r21 = r2
            r22 = r4
            r0 = r22
            r1 = r21
            r1.f6mY = r0
            goto L_0x0683
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void solveGraph() {
        ResolutionAnchor leftNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor topNode = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        leftNode.resolve((ResolutionAnchor) null, 0.0f);
        topNode.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void resetGraph() {
        ResolutionAnchor leftNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor topNode = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        leftNode.invalidateAnchors();
        topNode.invalidateAnchors();
        leftNode.resolve((ResolutionAnchor) null, 0.0f);
        topNode.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void optimizeForDimensions(int i, int i2) {
        int width = i;
        int height = i2;
        if (!(this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mResolutionWidth == null)) {
            this.mResolutionWidth.resolve(width);
        }
        if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionHeight != null) {
            this.mResolutionHeight.resolve(height);
        }
    }

    public void optimizeReset() {
        int count = this.mChildren.size();
        resetResolutionNodes();
        for (int i = 0; i < count; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetResolutionNodes();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<android.support.constraint.solver.widgets.Guideline> getVerticalGuidelines() {
        /*
            r9 = this;
            r0 = r9
            java.util.ArrayList r6 = new java.util.ArrayList
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r1 = r6
            r6 = 0
            r2 = r6
            r6 = r0
            java.util.ArrayList r6 = r6.mChildren
            int r6 = r6.size()
            r3 = r6
        L_0x0014:
            r6 = r2
            r7 = r3
            if (r6 >= r7) goto L_0x003d
            r6 = r0
            java.util.ArrayList r6 = r6.mChildren
            r7 = r2
            java.lang.Object r6 = r6.get(r7)
            android.support.constraint.solver.widgets.ConstraintWidget r6 = (android.support.constraint.solver.widgets.ConstraintWidget) r6
            r4 = r6
            r6 = r4
            boolean r6 = r6 instanceof android.support.constraint.solver.widgets.Guideline
            if (r6 == 0) goto L_0x003a
            r6 = r4
            android.support.constraint.solver.widgets.Guideline r6 = (android.support.constraint.solver.widgets.Guideline) r6
            r5 = r6
            r6 = r5
            int r6 = r6.getOrientation()
            r7 = 1
            if (r6 != r7) goto L_0x003a
            r6 = r1
            r7 = r5
            boolean r6 = r6.add(r7)
        L_0x003a:
            int r2 = r2 + 1
            goto L_0x0014
        L_0x003d:
            r6 = r1
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.getVerticalGuidelines():java.util.ArrayList");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<android.support.constraint.solver.widgets.Guideline> getHorizontalGuidelines() {
        /*
            r9 = this;
            r0 = r9
            java.util.ArrayList r6 = new java.util.ArrayList
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r1 = r6
            r6 = 0
            r2 = r6
            r6 = r0
            java.util.ArrayList r6 = r6.mChildren
            int r6 = r6.size()
            r3 = r6
        L_0x0014:
            r6 = r2
            r7 = r3
            if (r6 >= r7) goto L_0x003c
            r6 = r0
            java.util.ArrayList r6 = r6.mChildren
            r7 = r2
            java.lang.Object r6 = r6.get(r7)
            android.support.constraint.solver.widgets.ConstraintWidget r6 = (android.support.constraint.solver.widgets.ConstraintWidget) r6
            r4 = r6
            r6 = r4
            boolean r6 = r6 instanceof android.support.constraint.solver.widgets.Guideline
            if (r6 == 0) goto L_0x0039
            r6 = r4
            android.support.constraint.solver.widgets.Guideline r6 = (android.support.constraint.solver.widgets.Guideline) r6
            r5 = r6
            r6 = r5
            int r6 = r6.getOrientation()
            if (r6 != 0) goto L_0x0039
            r6 = r1
            r7 = r5
            boolean r6 = r6.add(r7)
        L_0x0039:
            int r2 = r2 + 1
            goto L_0x0014
        L_0x003c:
            r6 = r1
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.getHorizontalGuidelines():java.util.ArrayList");
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        int type = i;
        ConstraintWidget widget = constraintWidget;
        if (type == 0) {
            addHorizontalChain(widget);
        } else if (type == 1) {
            addVerticalChain(widget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        ChainHead chainHead;
        ConstraintWidget widget = constraintWidget;
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        new ChainHead(widget, 0, isRtl());
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = chainHead;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        ChainHead chainHead;
        ConstraintWidget widget = constraintWidget;
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        new ChainHead(widget, 1, isRtl());
        this.mVerticalChainsArray[this.mVerticalChainsSize] = chainHead;
        this.mVerticalChainsSize++;
    }

    public List<ConstraintWidgetGroup> getWidgetGroups() {
        return this.mWidgetGroups;
    }
}
