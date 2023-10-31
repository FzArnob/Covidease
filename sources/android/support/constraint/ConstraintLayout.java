package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.ResolutionAnchor;
import android.support.p000v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds;
    private boolean mDirtyHierarchy = true;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 7;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets;

    public void setDesignInformation(int type, Object obj, Object obj2) {
        HashMap<String, Integer> hashMap;
        Object value1 = obj;
        Object value2 = obj2;
        if (type == 0 && (value1 instanceof String) && (value2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                new HashMap<>();
                this.mDesignIds = hashMap;
            }
            String name = (String) value1;
            int index = name.indexOf("/");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            Integer put = this.mDesignIds.put(name, Integer.valueOf(((Integer) value2).intValue()));
        }
    }

    public Object getDesignInformation(int type, Object obj) {
        Object value = obj;
        if (type == 0 && (value instanceof String)) {
            String name = (String) value;
            if (this.mDesignIds != null && this.mDesignIds.containsKey(name)) {
                return this.mDesignIds.get(name);
            }
        }
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintLayout(Context context) {
        super(context);
        SparseArray<View> sparseArray;
        ArrayList<ConstraintHelper> arrayList;
        ArrayList<ConstraintWidget> arrayList2;
        ConstraintWidgetContainer constraintWidgetContainer;
        HashMap<String, Integer> hashMap;
        new SparseArray<>();
        this.mChildrenByIds = sparseArray;
        new ArrayList<>(4);
        this.mConstraintHelpers = arrayList;
        new ArrayList<>(100);
        this.mVariableDimensionsWidgets = arrayList2;
        new ConstraintWidgetContainer();
        this.mLayoutWidget = constraintWidgetContainer;
        new HashMap<>();
        this.mDesignIds = hashMap;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init((AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ConstraintLayout(android.content.Context r9, android.util.AttributeSet r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            android.util.SparseArray r4 = new android.util.SparseArray
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3.mChildrenByIds = r4
            r3 = r0
            java.util.ArrayList r4 = new java.util.ArrayList
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = 4
            r5.<init>(r6)
            r3.mConstraintHelpers = r4
            r3 = r0
            java.util.ArrayList r4 = new java.util.ArrayList
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = 100
            r5.<init>(r6)
            r3.mVariableDimensionsWidgets = r4
            r3 = r0
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r4 = new android.support.constraint.solver.widgets.ConstraintWidgetContainer
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3.mLayoutWidget = r4
            r3 = r0
            r4 = 0
            r3.mMinWidth = r4
            r3 = r0
            r4 = 0
            r3.mMinHeight = r4
            r3 = r0
            r4 = 2147483647(0x7fffffff, float:NaN)
            r3.mMaxWidth = r4
            r3 = r0
            r4 = 2147483647(0x7fffffff, float:NaN)
            r3.mMaxHeight = r4
            r3 = r0
            r4 = 1
            r3.mDirtyHierarchy = r4
            r3 = r0
            r4 = 7
            r3.mOptimizationLevel = r4
            r3 = r0
            r4 = 0
            r3.mConstraintSet = r4
            r3 = r0
            r4 = -1
            r3.mConstraintSetId = r4
            r3 = r0
            java.util.HashMap r4 = new java.util.HashMap
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3.mDesignIds = r4
            r3 = r0
            r4 = -1
            r3.mLastMeasureWidth = r4
            r3 = r0
            r4 = -1
            r3.mLastMeasureHeight = r4
            r3 = r0
            r4 = -1
            r3.mLastMeasureWidthSize = r4
            r3 = r0
            r4 = -1
            r3.mLastMeasureHeightSize = r4
            r3 = r0
            r4 = 0
            r3.mLastMeasureWidthMode = r4
            r3 = r0
            r4 = 0
            r3.mLastMeasureHeightMode = r4
            r3 = r0
            r4 = r2
            r3.init(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ConstraintLayout(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            android.util.SparseArray r5 = new android.util.SparseArray
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()
            r4.mChildrenByIds = r5
            r4 = r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = 4
            r6.<init>(r7)
            r4.mConstraintHelpers = r5
            r4 = r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = 100
            r6.<init>(r7)
            r4.mVariableDimensionsWidgets = r5
            r4 = r0
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r5 = new android.support.constraint.solver.widgets.ConstraintWidgetContainer
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()
            r4.mLayoutWidget = r5
            r4 = r0
            r5 = 0
            r4.mMinWidth = r5
            r4 = r0
            r5 = 0
            r4.mMinHeight = r5
            r4 = r0
            r5 = 2147483647(0x7fffffff, float:NaN)
            r4.mMaxWidth = r5
            r4 = r0
            r5 = 2147483647(0x7fffffff, float:NaN)
            r4.mMaxHeight = r5
            r4 = r0
            r5 = 1
            r4.mDirtyHierarchy = r5
            r4 = r0
            r5 = 7
            r4.mOptimizationLevel = r5
            r4 = r0
            r5 = 0
            r4.mConstraintSet = r5
            r4 = r0
            r5 = -1
            r4.mConstraintSetId = r5
            r4 = r0
            java.util.HashMap r5 = new java.util.HashMap
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()
            r4.mDesignIds = r5
            r4 = r0
            r5 = -1
            r4.mLastMeasureWidth = r5
            r4 = r0
            r5 = -1
            r4.mLastMeasureHeight = r5
            r4 = r0
            r5 = -1
            r4.mLastMeasureWidthSize = r5
            r4 = r0
            r5 = -1
            r4.mLastMeasureHeightSize = r5
            r4 = r0
            r5 = 0
            r4.mLastMeasureWidthMode = r5
            r4 = r0
            r5 = 0
            r4.mLastMeasureHeightMode = r5
            r4 = r0
            r5 = r2
            r4.init(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setId(int id) {
        this.mChildrenByIds.remove(getId());
        super.setId(id);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attributeSet) {
        ConstraintSet constraintSet;
        AttributeSet attrs = attributeSet;
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0025R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == C0025R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == C0025R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == C0025R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == C0025R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == C0025R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == C0025R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);
                    try {
                        new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        this.mConstraintSet.load(getContext(), id);
                    } catch (Resources.NotFoundException e) {
                        Resources.NotFoundException notFoundException = e;
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = id;
                }
            }
            a.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    public void addView(View view, int index, ViewGroup.LayoutParams params) {
        View child = view;
        super.addView(child, index, params);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(child);
        }
    }

    public void removeView(View view) {
        View view2 = view;
        super.removeView(view2);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view2);
        }
    }

    public void onViewAdded(View view) {
        ConstraintWidget constraintWidget;
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view2);
        }
        ConstraintWidget widget = getViewWidget(view2);
        if ((view2 instanceof Guideline) && !(widget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view2.getLayoutParams();
            new Guideline();
            layoutParams.widget = constraintWidget;
            layoutParams.isGuideline = true;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view2 instanceof ConstraintHelper) {
            ConstraintHelper helper = (ConstraintHelper) view2;
            helper.validateParams();
            ((LayoutParams) view2.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(helper)) {
                boolean add = this.mConstraintHelpers.add(helper);
            }
        }
        this.mChildrenByIds.put(view2.getId(), view2);
        this.mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view2);
        }
        this.mChildrenByIds.remove(view2.getId());
        ConstraintWidget widget = getViewWidget(view2);
        this.mLayoutWidget.remove(widget);
        boolean remove = this.mConstraintHelpers.remove(view2);
        boolean remove2 = this.mVariableDimensionsWidgets.remove(widget);
        this.mDirtyHierarchy = true;
    }

    public void setMinWidth(int i) {
        int value = i;
        if (value != this.mMinWidth) {
            this.mMinWidth = value;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        int value = i;
        if (value != this.mMinHeight) {
            this.mMinHeight = value;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        int value = i;
        if (value != this.mMaxWidth) {
            this.mMaxWidth = value;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        int value = i;
        if (value != this.mMaxHeight) {
            this.mMaxHeight = value;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int count = getChildCount();
        boolean recompute = false;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                recompute = true;
                break;
            } else {
                i++;
            }
        }
        if (recompute) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void setChildrenConstraints() {
        ConstraintWidget target;
        ConstraintWidget target2;
        ConstraintWidget target3;
        ConstraintWidget target4;
        boolean isInEditMode = isInEditMode();
        int count = getChildCount();
        if (isInEditMode) {
            for (int i = 0; i < count; i++) {
                View view = getChildAt(i);
                try {
                    String IdAsString = getResources().getResourceName(view.getId());
                    setDesignInformation(0, IdAsString, Integer.valueOf(view.getId()));
                    int slashIndex = IdAsString.indexOf(47);
                    if (slashIndex != -1) {
                        IdAsString = IdAsString.substring(slashIndex + 1);
                    }
                    getTargetWidget(view.getId()).setDebugName(IdAsString);
                } catch (Resources.NotFoundException e) {
                    Resources.NotFoundException notFoundException = e;
                }
            }
        }
        for (int i2 = 0; i2 < count; i2++) {
            ConstraintWidget widget = getViewWidget(getChildAt(i2));
            if (widget != null) {
                widget.reset();
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i3 = 0; i3 < count; i3++) {
                View child = getChildAt(i3);
                if (child.getId() == this.mConstraintSetId && (child instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) child).getConstraintSet();
                }
            }
        }
        if (this.mConstraintSet != null) {
            this.mConstraintSet.applyToInternal(this);
        }
        this.mLayoutWidget.removeAllChildren();
        int i4 = this.mConstraintHelpers.size();
        if (i4 > 0) {
            for (int i5 = 0; i5 < i4; i5++) {
                this.mConstraintHelpers.get(i5).updatePreLayout(this);
            }
        }
        for (int i6 = 0; i6 < count; i6++) {
            View child2 = getChildAt(i6);
            if (child2 instanceof Placeholder) {
                ((Placeholder) child2).updatePreLayout(this);
            }
        }
        for (int i7 = 0; i7 < count; i7++) {
            View child3 = getChildAt(i7);
            ConstraintWidget widget2 = getViewWidget(child3);
            if (widget2 != null) {
                LayoutParams layoutParams = (LayoutParams) child3.getLayoutParams();
                layoutParams.validate();
                if (layoutParams.helped) {
                    layoutParams.helped = false;
                } else if (isInEditMode) {
                    try {
                        String IdAsString2 = getResources().getResourceName(child3.getId());
                        setDesignInformation(0, IdAsString2, Integer.valueOf(child3.getId()));
                        getTargetWidget(child3.getId()).setDebugName(IdAsString2.substring(IdAsString2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException e2) {
                        Resources.NotFoundException notFoundException2 = e2;
                    }
                }
                widget2.setVisibility(child3.getVisibility());
                if (layoutParams.isInPlaceholder) {
                    widget2.setVisibility(8);
                }
                widget2.setCompanionWidget(child3);
                this.mLayoutWidget.add(widget2);
                if (!layoutParams.verticalDimensionFixed || !layoutParams.horizontalDimensionFixed) {
                    boolean add = this.mVariableDimensionsWidgets.add(widget2);
                }
                if (layoutParams.isGuideline) {
                    Guideline guideline = (Guideline) widget2;
                    int resolvedGuideBegin = layoutParams.resolvedGuideBegin;
                    int resolvedGuideEnd = layoutParams.resolvedGuideEnd;
                    float resolvedGuidePercent = layoutParams.resolvedGuidePercent;
                    if (Build.VERSION.SDK_INT < 17) {
                        resolvedGuideBegin = layoutParams.guideBegin;
                        resolvedGuideEnd = layoutParams.guideEnd;
                        resolvedGuidePercent = layoutParams.guidePercent;
                    }
                    if (resolvedGuidePercent != -1.0f) {
                        guideline.setGuidePercent(resolvedGuidePercent);
                    } else if (resolvedGuideBegin != -1) {
                        guideline.setGuideBegin(resolvedGuideBegin);
                    } else if (resolvedGuideEnd != -1) {
                        guideline.setGuideEnd(resolvedGuideEnd);
                    }
                } else if (layoutParams.leftToLeft != -1 || layoutParams.leftToRight != -1 || layoutParams.rightToLeft != -1 || layoutParams.rightToRight != -1 || layoutParams.startToStart != -1 || layoutParams.startToEnd != -1 || layoutParams.endToStart != -1 || layoutParams.endToEnd != -1 || layoutParams.topToTop != -1 || layoutParams.topToBottom != -1 || layoutParams.bottomToTop != -1 || layoutParams.bottomToBottom != -1 || layoutParams.baselineToBaseline != -1 || layoutParams.editorAbsoluteX != -1 || layoutParams.editorAbsoluteY != -1 || layoutParams.circleConstraint != -1 || layoutParams.width == -1 || layoutParams.height == -1) {
                    int resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
                    int resolvedLeftToRight = layoutParams.resolvedLeftToRight;
                    int resolvedRightToLeft = layoutParams.resolvedRightToLeft;
                    int resolvedRightToRight = layoutParams.resolvedRightToRight;
                    int resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
                    int resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
                    float resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
                    if (Build.VERSION.SDK_INT < 17) {
                        resolvedLeftToLeft = layoutParams.leftToLeft;
                        resolvedLeftToRight = layoutParams.leftToRight;
                        resolvedRightToLeft = layoutParams.rightToLeft;
                        resolvedRightToRight = layoutParams.rightToRight;
                        resolveGoneLeftMargin = layoutParams.goneLeftMargin;
                        resolveGoneRightMargin = layoutParams.goneRightMargin;
                        resolvedHorizontalBias = layoutParams.horizontalBias;
                        if (resolvedLeftToLeft == -1 && resolvedLeftToRight == -1) {
                            if (layoutParams.startToStart != -1) {
                                resolvedLeftToLeft = layoutParams.startToStart;
                            } else if (layoutParams.startToEnd != -1) {
                                resolvedLeftToRight = layoutParams.startToEnd;
                            }
                        }
                        if (resolvedRightToLeft == -1 && resolvedRightToRight == -1) {
                            if (layoutParams.endToStart != -1) {
                                resolvedRightToLeft = layoutParams.endToStart;
                            } else if (layoutParams.endToEnd != -1) {
                                resolvedRightToRight = layoutParams.endToEnd;
                            }
                        }
                    }
                    if (layoutParams.circleConstraint != -1) {
                        ConstraintWidget target5 = getTargetWidget(layoutParams.circleConstraint);
                        if (target5 != null) {
                            widget2.connectCircularConstraint(target5, layoutParams.circleAngle, layoutParams.circleRadius);
                        }
                    } else {
                        if (resolvedLeftToLeft != -1) {
                            ConstraintWidget target6 = getTargetWidget(resolvedLeftToLeft);
                            if (target6 != null) {
                                widget2.immediateConnect(ConstraintAnchor.Type.LEFT, target6, ConstraintAnchor.Type.LEFT, layoutParams.leftMargin, resolveGoneLeftMargin);
                            }
                        } else if (!(resolvedLeftToRight == -1 || (target = getTargetWidget(resolvedLeftToRight)) == null)) {
                            widget2.immediateConnect(ConstraintAnchor.Type.LEFT, target, ConstraintAnchor.Type.RIGHT, layoutParams.leftMargin, resolveGoneLeftMargin);
                        }
                        if (resolvedRightToLeft != -1) {
                            ConstraintWidget target7 = getTargetWidget(resolvedRightToLeft);
                            if (target7 != null) {
                                widget2.immediateConnect(ConstraintAnchor.Type.RIGHT, target7, ConstraintAnchor.Type.LEFT, layoutParams.rightMargin, resolveGoneRightMargin);
                            }
                        } else if (!(resolvedRightToRight == -1 || (target2 = getTargetWidget(resolvedRightToRight)) == null)) {
                            widget2.immediateConnect(ConstraintAnchor.Type.RIGHT, target2, ConstraintAnchor.Type.RIGHT, layoutParams.rightMargin, resolveGoneRightMargin);
                        }
                        if (layoutParams.topToTop != -1) {
                            ConstraintWidget target8 = getTargetWidget(layoutParams.topToTop);
                            if (target8 != null) {
                                widget2.immediateConnect(ConstraintAnchor.Type.TOP, target8, ConstraintAnchor.Type.TOP, layoutParams.topMargin, layoutParams.goneTopMargin);
                            }
                        } else if (!(layoutParams.topToBottom == -1 || (target3 = getTargetWidget(layoutParams.topToBottom)) == null)) {
                            widget2.immediateConnect(ConstraintAnchor.Type.TOP, target3, ConstraintAnchor.Type.BOTTOM, layoutParams.topMargin, layoutParams.goneTopMargin);
                        }
                        if (layoutParams.bottomToTop != -1) {
                            ConstraintWidget target9 = getTargetWidget(layoutParams.bottomToTop);
                            if (target9 != null) {
                                widget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, target9, ConstraintAnchor.Type.TOP, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                            }
                        } else if (!(layoutParams.bottomToBottom == -1 || (target4 = getTargetWidget(layoutParams.bottomToBottom)) == null)) {
                            widget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, target4, ConstraintAnchor.Type.BOTTOM, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                        }
                        if (layoutParams.baselineToBaseline != -1) {
                            View view2 = this.mChildrenByIds.get(layoutParams.baselineToBaseline);
                            ConstraintWidget target10 = getTargetWidget(layoutParams.baselineToBaseline);
                            if (!(target10 == null || view2 == null || !(view2.getLayoutParams() instanceof LayoutParams))) {
                                LayoutParams targetParams = (LayoutParams) view2.getLayoutParams();
                                layoutParams.needsBaseline = true;
                                targetParams.needsBaseline = true;
                                boolean connect = widget2.getAnchor(ConstraintAnchor.Type.BASELINE).connect(target10.getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                                widget2.getAnchor(ConstraintAnchor.Type.TOP).reset();
                                widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                            }
                        }
                        if (resolvedHorizontalBias >= 0.0f && resolvedHorizontalBias != 0.5f) {
                            widget2.setHorizontalBiasPercent(resolvedHorizontalBias);
                        }
                        if (layoutParams.verticalBias >= 0.0f && layoutParams.verticalBias != 0.5f) {
                            widget2.setVerticalBiasPercent(layoutParams.verticalBias);
                        }
                    }
                    if (isInEditMode && !(layoutParams.editorAbsoluteX == -1 && layoutParams.editorAbsoluteY == -1)) {
                        widget2.setOrigin(layoutParams.editorAbsoluteX, layoutParams.editorAbsoluteY);
                    }
                    if (layoutParams.horizontalDimensionFixed) {
                        widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                        widget2.setWidth(layoutParams.width);
                    } else if (layoutParams.width == -1) {
                        widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        widget2.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = layoutParams.leftMargin;
                        widget2.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = layoutParams.rightMargin;
                    } else {
                        widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        widget2.setWidth(0);
                    }
                    if (layoutParams.verticalDimensionFixed) {
                        widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                        widget2.setHeight(layoutParams.height);
                    } else if (layoutParams.height == -1) {
                        widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        widget2.getAnchor(ConstraintAnchor.Type.TOP).mMargin = layoutParams.topMargin;
                        widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = layoutParams.bottomMargin;
                    } else {
                        widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        widget2.setHeight(0);
                    }
                    if (layoutParams.dimensionRatio != null) {
                        widget2.setDimensionRatio(layoutParams.dimensionRatio);
                    }
                    widget2.setHorizontalWeight(layoutParams.horizontalWeight);
                    widget2.setVerticalWeight(layoutParams.verticalWeight);
                    widget2.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
                    widget2.setVerticalChainStyle(layoutParams.verticalChainStyle);
                    widget2.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
                    widget2.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
                }
            }
        }
    }

    private final ConstraintWidget getTargetWidget(int i) {
        int id = i;
        if (id == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(id);
        if (view == null) {
            view = findViewById(id);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        return view == null ? null : ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        View view2 = view;
        if (view2 == this) {
            return this.mLayoutWidget;
        }
        return view2 == null ? null : ((LayoutParams) view2.getLayoutParams()).widget;
    }

    private void internalMeasureChildren(int i, int i2) {
        int baseline;
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        int parentWidthSpec = i;
        int parentHeightSpec = i2;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int widgetsCount = getChildCount();
        for (int i3 = 0; i3 < widgetsCount; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                ConstraintWidget widget = params.widget;
                if (!params.isGuideline && !params.isHelper) {
                    widget.setVisibility(child.getVisibility());
                    int width = params.width;
                    int height = params.height;
                    boolean didWrapMeasureWidth = false;
                    boolean didWrapMeasureHeight = false;
                    if (params.horizontalDimensionFixed || params.verticalDimensionFixed || (!params.horizontalDimensionFixed && params.matchConstraintDefaultWidth == 1) || params.width == -1 || (!params.verticalDimensionFixed && (params.matchConstraintDefaultHeight == 1 || params.height == -1))) {
                        if (width == 0) {
                            childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, -2);
                            didWrapMeasureWidth = true;
                        } else if (width == -1) {
                            childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, -1);
                        } else {
                            if (width == -2) {
                                didWrapMeasureWidth = true;
                            }
                            childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, width);
                        }
                        if (height == 0) {
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -2);
                            didWrapMeasureHeight = true;
                        } else if (height == -1) {
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -1);
                        } else {
                            if (height == -2) {
                                didWrapMeasureHeight = true;
                            }
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, height);
                        }
                        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        if (this.mMetrics != null) {
                            this.mMetrics.measures++;
                        }
                        widget.setWidthWrapContent(width == -2);
                        widget.setHeightWrapContent(height == -2);
                        width = child.getMeasuredWidth();
                        height = child.getMeasuredHeight();
                    }
                    widget.setWidth(width);
                    widget.setHeight(height);
                    if (didWrapMeasureWidth) {
                        widget.setWrapWidth(width);
                    }
                    if (didWrapMeasureHeight) {
                        widget.setWrapHeight(height);
                    }
                    if (params.needsBaseline && (baseline = child.getBaseline()) != -1) {
                        widget.setBaselineDistance(baseline);
                    }
                }
            }
        }
    }

    private void updatePostMeasures() {
        int widgetsCount = getChildCount();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Placeholder) {
                ((Placeholder) child).updatePostMeasure(this);
            }
        }
        int i2 = this.mConstraintHelpers.size();
        if (i2 > 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                this.mConstraintHelpers.get(i3).updatePostMeasure(this);
            }
        }
    }

    private void internalMeasureDimensions(int i, int i2) {
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        int baseline;
        int baseline2;
        int parentWidthSpec = i;
        int parentHeightSpec = i2;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int widgetsCount = getChildCount();
        for (int i3 = 0; i3 < widgetsCount; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                ConstraintWidget widget = params.widget;
                if (!params.isGuideline && !params.isHelper) {
                    widget.setVisibility(child.getVisibility());
                    int width = params.width;
                    int height = params.height;
                    if (width == 0 || height == 0) {
                        widget.getResolutionWidth().invalidate();
                        widget.getResolutionHeight().invalidate();
                    } else {
                        boolean didWrapMeasureWidth = false;
                        boolean didWrapMeasureHeight = false;
                        if (width == -2) {
                            didWrapMeasureWidth = true;
                        }
                        int childWidthMeasureSpec2 = getChildMeasureSpec(parentWidthSpec, widthPadding, width);
                        if (height == -2) {
                            didWrapMeasureHeight = true;
                        }
                        child.measure(childWidthMeasureSpec2, getChildMeasureSpec(parentHeightSpec, heightPadding, height));
                        if (this.mMetrics != null) {
                            this.mMetrics.measures++;
                        }
                        widget.setWidthWrapContent(width == -2);
                        widget.setHeightWrapContent(height == -2);
                        int width2 = child.getMeasuredWidth();
                        int height2 = child.getMeasuredHeight();
                        widget.setWidth(width2);
                        widget.setHeight(height2);
                        if (didWrapMeasureWidth) {
                            widget.setWrapWidth(width2);
                        }
                        if (didWrapMeasureHeight) {
                            widget.setWrapHeight(height2);
                        }
                        if (params.needsBaseline && (baseline2 = child.getBaseline()) != -1) {
                            widget.setBaselineDistance(baseline2);
                        }
                        if (params.horizontalDimensionFixed && params.verticalDimensionFixed) {
                            widget.getResolutionWidth().resolve(width2);
                            widget.getResolutionHeight().resolve(height2);
                        }
                    }
                }
            }
        }
        this.mLayoutWidget.solveGraph();
        for (int i4 = 0; i4 < widgetsCount; i4++) {
            View child2 = getChildAt(i4);
            if (child2.getVisibility() != 8) {
                LayoutParams params2 = (LayoutParams) child2.getLayoutParams();
                ConstraintWidget widget2 = params2.widget;
                if (!params2.isGuideline && !params2.isHelper) {
                    widget2.setVisibility(child2.getVisibility());
                    int width3 = params2.width;
                    int height3 = params2.height;
                    if (width3 == 0 || height3 == 0) {
                        ResolutionAnchor left = widget2.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
                        ResolutionAnchor right = widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getResolutionNode();
                        boolean bothHorizontal = (widget2.getAnchor(ConstraintAnchor.Type.LEFT).getTarget() == null || widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() == null) ? false : true;
                        ResolutionAnchor top = widget2.getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
                        ResolutionAnchor bottom = widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getResolutionNode();
                        boolean bothVertical = (widget2.getAnchor(ConstraintAnchor.Type.TOP).getTarget() == null || widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() == null) ? false : true;
                        if (width3 != 0 || height3 != 0 || !bothHorizontal || !bothVertical) {
                            boolean didWrapMeasureWidth2 = false;
                            boolean didWrapMeasureHeight2 = false;
                            boolean resolveWidth = this.mLayoutWidget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            boolean resolveHeight = this.mLayoutWidget.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            if (!resolveWidth) {
                                widget2.getResolutionWidth().invalidate();
                            }
                            if (!resolveHeight) {
                                widget2.getResolutionHeight().invalidate();
                            }
                            if (width3 == 0) {
                                if (!resolveWidth || !widget2.isSpreadWidth() || !bothHorizontal || !left.isResolved() || !right.isResolved()) {
                                    childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, -2);
                                    didWrapMeasureWidth2 = true;
                                    resolveWidth = false;
                                } else {
                                    width3 = (int) (right.getResolvedValue() - left.getResolvedValue());
                                    widget2.getResolutionWidth().resolve(width3);
                                    childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, width3);
                                }
                            } else if (width3 == -1) {
                                childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, -1);
                            } else {
                                if (width3 == -2) {
                                    didWrapMeasureWidth2 = true;
                                }
                                childWidthMeasureSpec = getChildMeasureSpec(parentWidthSpec, widthPadding, width3);
                            }
                            if (height3 == 0) {
                                if (!resolveHeight || !widget2.isSpreadHeight() || !bothVertical || !top.isResolved() || !bottom.isResolved()) {
                                    childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -2);
                                    didWrapMeasureHeight2 = true;
                                    resolveHeight = false;
                                } else {
                                    height3 = (int) (bottom.getResolvedValue() - top.getResolvedValue());
                                    widget2.getResolutionHeight().resolve(height3);
                                    childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, height3);
                                }
                            } else if (height3 == -1) {
                                childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -1);
                            } else {
                                if (height3 == -2) {
                                    didWrapMeasureHeight2 = true;
                                }
                                childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, height3);
                            }
                            child2.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                            if (this.mMetrics != null) {
                                this.mMetrics.measures++;
                            }
                            widget2.setWidthWrapContent(width3 == -2);
                            widget2.setHeightWrapContent(height3 == -2);
                            int width4 = child2.getMeasuredWidth();
                            int height4 = child2.getMeasuredHeight();
                            widget2.setWidth(width4);
                            widget2.setHeight(height4);
                            if (didWrapMeasureWidth2) {
                                widget2.setWrapWidth(width4);
                            }
                            if (didWrapMeasureHeight2) {
                                widget2.setWrapHeight(height4);
                            }
                            if (resolveWidth) {
                                widget2.getResolutionWidth().resolve(width4);
                            } else {
                                widget2.getResolutionWidth().remove();
                            }
                            if (resolveHeight) {
                                widget2.getResolutionHeight().resolve(height4);
                            } else {
                                widget2.getResolutionHeight().remove();
                            }
                            if (params2.needsBaseline && (baseline = child2.getBaseline()) != -1) {
                                widget2.setBaselineDistance(baseline);
                            }
                        }
                    }
                }
            }
        }
    }

    public void fillMetrics(Metrics metrics) {
        Metrics metrics2 = metrics;
        this.mMetrics = metrics2;
        this.mLayoutWidget.fillMetrics(metrics2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthSpec;
        int heightSpec;
        int baseline;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        long currentTimeMillis = System.currentTimeMillis();
        int REMEASURES_A = 0;
        int REMEASURES_B = 0;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(widthMeasureSpec, heightMeasureSpec);
        int startingWidth = this.mLayoutWidget.getWidth();
        int startingHeight = this.mLayoutWidget.getHeight();
        boolean runAnalyzer = false;
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            runAnalyzer = true;
        }
        boolean optimiseDimensions = (this.mOptimizationLevel & 8) == 8;
        if (optimiseDimensions) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(startingWidth, startingHeight);
            internalMeasureDimensions(widthMeasureSpec, heightMeasureSpec);
        } else {
            internalMeasureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && runAnalyzer) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        if (this.mLayoutWidget.mGroupsWrapOptimized) {
            if (this.mLayoutWidget.mHorizontalWrapOptimized && widthMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedWidth < widthSize) {
                    this.mLayoutWidget.setWidth(this.mLayoutWidget.mWrapFixedWidth);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && heightMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedHeight < heightSize) {
                    this.mLayoutWidget.setHeight(this.mLayoutWidget.mWrapFixedHeight);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
        }
        if ((this.mOptimizationLevel & 32) == 32) {
            int width = this.mLayoutWidget.getWidth();
            int height = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth != width && widthMode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width);
            }
            if (this.mLastMeasureHeight != height && heightMode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height);
            }
            if (this.mLayoutWidget.mHorizontalWrapOptimized) {
                if (this.mLayoutWidget.mWrapFixedWidth > widthSize) {
                    Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, widthSize);
                }
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized) {
                if (this.mLayoutWidget.mWrapFixedHeight > heightSize) {
                    Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, heightSize);
                }
            }
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int childState = 0;
        int sizeDependentWidgetsCount = this.mVariableDimensionsWidgets.size();
        int heightPadding = paddingTop + getPaddingBottom();
        int widthPadding = paddingLeft + getPaddingRight();
        if (sizeDependentWidgetsCount > 0) {
            boolean needSolverPass = false;
            boolean containerWrapWidth = this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean containerWrapHeight = this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int minWidth = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int minHeight = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            for (int i3 = 0; i3 < sizeDependentWidgetsCount; i3++) {
                ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i3);
                View child = (View) widget.getCompanionWidget();
                if (child != null) {
                    LayoutParams params = (LayoutParams) child.getLayoutParams();
                    if (!params.isHelper && !params.isGuideline && child.getVisibility() != 8 && (!optimiseDimensions || !widget.getResolutionWidth().isResolved() || !widget.getResolutionHeight().isResolved())) {
                        if (params.width != -2 || !params.horizontalDimensionFixed) {
                            widthSpec = View.MeasureSpec.makeMeasureSpec(widget.getWidth(), Declaration.MODULE_REFERENCE);
                        } else {
                            widthSpec = getChildMeasureSpec(widthMeasureSpec, widthPadding, params.width);
                        }
                        if (params.height != -2 || !params.verticalDimensionFixed) {
                            heightSpec = View.MeasureSpec.makeMeasureSpec(widget.getHeight(), Declaration.MODULE_REFERENCE);
                        } else {
                            heightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, params.height);
                        }
                        child.measure(widthSpec, heightSpec);
                        if (this.mMetrics != null) {
                            this.mMetrics.additionalMeasures++;
                        }
                        REMEASURES_A++;
                        int measuredWidth = child.getMeasuredWidth();
                        int measuredHeight = child.getMeasuredHeight();
                        if (measuredWidth != widget.getWidth()) {
                            widget.setWidth(measuredWidth);
                            if (optimiseDimensions) {
                                widget.getResolutionWidth().resolve(measuredWidth);
                            }
                            if (containerWrapWidth && widget.getRight() > minWidth) {
                                minWidth = Math.max(minWidth, widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                            }
                            needSolverPass = true;
                        }
                        if (measuredHeight != widget.getHeight()) {
                            widget.setHeight(measuredHeight);
                            if (optimiseDimensions) {
                                widget.getResolutionHeight().resolve(measuredHeight);
                            }
                            if (containerWrapHeight && widget.getBottom() > minHeight) {
                                minHeight = Math.max(minHeight, widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                            }
                            needSolverPass = true;
                        }
                        if (!(!params.needsBaseline || (baseline = child.getBaseline()) == -1 || baseline == widget.getBaselineDistance())) {
                            widget.setBaselineDistance(baseline);
                            needSolverPass = true;
                        }
                        if (Build.VERSION.SDK_INT >= 11) {
                            childState = combineMeasuredStates(childState, child.getMeasuredState());
                        }
                    }
                }
            }
            if (needSolverPass) {
                this.mLayoutWidget.setWidth(startingWidth);
                this.mLayoutWidget.setHeight(startingHeight);
                if (optimiseDimensions) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                boolean needSolverPass2 = false;
                if (this.mLayoutWidget.getWidth() < minWidth) {
                    this.mLayoutWidget.setWidth(minWidth);
                    needSolverPass2 = true;
                }
                if (this.mLayoutWidget.getHeight() < minHeight) {
                    this.mLayoutWidget.setHeight(minHeight);
                    needSolverPass2 = true;
                }
                if (needSolverPass2) {
                    solveLinearSystem("3rd pass");
                }
            }
            for (int i4 = 0; i4 < sizeDependentWidgetsCount; i4++) {
                ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i4);
                View child2 = (View) widget2.getCompanionWidget();
                if (!(child2 == null || ((child2.getMeasuredWidth() == widget2.getWidth() && child2.getMeasuredHeight() == widget2.getHeight()) || widget2.getVisibility() == 8))) {
                    child2.measure(View.MeasureSpec.makeMeasureSpec(widget2.getWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(widget2.getHeight(), Declaration.MODULE_REFERENCE));
                    if (this.mMetrics != null) {
                        this.mMetrics.additionalMeasures++;
                    }
                    REMEASURES_B++;
                }
            }
        }
        int androidLayoutWidth = this.mLayoutWidget.getWidth() + widthPadding;
        int androidLayoutHeight = this.mLayoutWidget.getHeight() + heightPadding;
        if (Build.VERSION.SDK_INT >= 11) {
            int resolvedWidthSize = Math.min(this.mMaxWidth, resolveSizeAndState(androidLayoutWidth, widthMeasureSpec, childState) & 16777215);
            int resolvedHeightSize = Math.min(this.mMaxHeight, resolveSizeAndState(androidLayoutHeight, heightMeasureSpec, childState << 16) & 16777215);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                resolvedWidthSize |= 16777216;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                resolvedHeightSize |= 16777216;
            }
            setMeasuredDimension(resolvedWidthSize, resolvedHeightSize);
            this.mLastMeasureWidth = resolvedWidthSize;
            this.mLastMeasureHeight = resolvedHeightSize;
            return;
        }
        setMeasuredDimension(androidLayoutWidth, androidLayoutHeight);
        this.mLastMeasureWidth = androidLayoutWidth;
        this.mLastMeasureHeight = androidLayoutHeight;
    }

    private void setSelfDimensionBehaviour(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour widthBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour heightBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        int desiredWidth = 0;
        int desiredHeight = 0;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        switch (widthMode) {
            case Integer.MIN_VALUE:
                widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                desiredWidth = widthSize;
                break;
            case 0:
                widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                desiredWidth = Math.min(this.mMaxWidth, widthSize) - widthPadding;
                break;
        }
        switch (heightMode) {
            case Integer.MIN_VALUE:
                heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                desiredHeight = heightSize;
                break;
            case 0:
                heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                desiredHeight = Math.min(this.mMaxHeight, heightSize) - heightPadding;
                break;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(widthBehaviour);
        this.mLayoutWidget.setWidth(desiredWidth);
        this.mLayoutWidget.setVerticalDimensionBehaviour(heightBehaviour);
        this.mLayoutWidget.setHeight(desiredHeight);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void solveLinearSystem(String str) {
        String str2 = str;
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            this.mMetrics.resolutions++;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int widgetsCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i9 = 0; i9 < widgetsCount; i9++) {
            View child = getChildAt(i9);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            ConstraintWidget widget = params.widget;
            if ((child.getVisibility() != 8 || params.isGuideline || params.isHelper || isInEditMode) && !params.isInPlaceholder) {
                int l = widget.getDrawX();
                int t = widget.getDrawY();
                int r = l + widget.getWidth();
                int b = t + widget.getHeight();
                child.layout(l, t, r, b);
                if ((child instanceof Placeholder) && (content = ((Placeholder) child).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(l, t, r, b);
                }
            }
        }
        int i10 = this.mConstraintHelpers.size();
        if (i10 > 0) {
            for (int i11 = 0; i11 < i10; i11++) {
                this.mConstraintHelpers.get(i11).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int level) {
        this.mLayoutWidget.setOptimizationLevel(level);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-2, -2);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(p);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet set) {
        ConstraintSet constraintSet = set;
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int id) {
        return this.mChildrenByIds.get(id);
    }

    public void dispatchDraw(Canvas canvas) {
        Object tag;
        Paint paint;
        Canvas canvas2 = canvas;
        super.dispatchDraw(canvas2);
        if (isInEditMode()) {
            int count = getChildCount();
            float cw = (float) getWidth();
            float ch = (float) getHeight();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (!(child.getVisibility() == 8 || (tag = child.getTag()) == null || !(tag instanceof String))) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int x = Integer.parseInt(split[0]);
                        int y = Integer.parseInt(split[1]);
                        int x2 = (int) ((((float) x) / 1080.0f) * cw);
                        int y2 = (int) ((((float) y) / 1920.0f) * ch);
                        int w = (int) ((((float) Integer.parseInt(split[2])) / 1080.0f) * cw);
                        int h = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * ch);
                        new Paint();
                        Paint paint2 = paint;
                        paint2.setColor(SupportMenu.CATEGORY_MASK);
                        canvas2.drawLine((float) x2, (float) y2, (float) (x2 + w), (float) y2, paint2);
                        canvas2.drawLine((float) (x2 + w), (float) y2, (float) (x2 + w), (float) (y2 + h), paint2);
                        canvas2.drawLine((float) (x2 + w), (float) (y2 + h), (float) x2, (float) (y2 + h), paint2);
                        canvas2.drawLine((float) x2, (float) (y2 + h), (float) x2, (float) y2, paint2);
                        paint2.setColor(-16711936);
                        canvas2.drawLine((float) x2, (float) y2, (float) (x2 + w), (float) (y2 + h), paint2);
                        canvas2.drawLine((float) x2, (float) (y2 + h), (float) (x2 + w), (float) y2, paint2);
                    }
                }
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = true;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = true;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget;

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.support.constraint.ConstraintLayout.LayoutParams r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = -1
                r2.guideBegin = r3
                r2 = r0
                r3 = -1
                r2.guideEnd = r3
                r2 = r0
                r3 = -1082130432(0xffffffffbf800000, float:-1.0)
                r2.guidePercent = r3
                r2 = r0
                r3 = -1
                r2.leftToLeft = r3
                r2 = r0
                r3 = -1
                r2.leftToRight = r3
                r2 = r0
                r3 = -1
                r2.rightToLeft = r3
                r2 = r0
                r3 = -1
                r2.rightToRight = r3
                r2 = r0
                r3 = -1
                r2.topToTop = r3
                r2 = r0
                r3 = -1
                r2.topToBottom = r3
                r2 = r0
                r3 = -1
                r2.bottomToTop = r3
                r2 = r0
                r3 = -1
                r2.bottomToBottom = r3
                r2 = r0
                r3 = -1
                r2.baselineToBaseline = r3
                r2 = r0
                r3 = -1
                r2.circleConstraint = r3
                r2 = r0
                r3 = 0
                r2.circleRadius = r3
                r2 = r0
                r3 = 0
                r2.circleAngle = r3
                r2 = r0
                r3 = -1
                r2.startToEnd = r3
                r2 = r0
                r3 = -1
                r2.startToStart = r3
                r2 = r0
                r3 = -1
                r2.endToStart = r3
                r2 = r0
                r3 = -1
                r2.endToEnd = r3
                r2 = r0
                r3 = -1
                r2.goneLeftMargin = r3
                r2 = r0
                r3 = -1
                r2.goneTopMargin = r3
                r2 = r0
                r3 = -1
                r2.goneRightMargin = r3
                r2 = r0
                r3 = -1
                r2.goneBottomMargin = r3
                r2 = r0
                r3 = -1
                r2.goneStartMargin = r3
                r2 = r0
                r3 = -1
                r2.goneEndMargin = r3
                r2 = r0
                r3 = 1056964608(0x3f000000, float:0.5)
                r2.horizontalBias = r3
                r2 = r0
                r3 = 1056964608(0x3f000000, float:0.5)
                r2.verticalBias = r3
                r2 = r0
                r3 = 0
                r2.dimensionRatio = r3
                r2 = r0
                r3 = 0
                r2.dimensionRatioValue = r3
                r2 = r0
                r3 = 1
                r2.dimensionRatioSide = r3
                r2 = r0
                r3 = -1082130432(0xffffffffbf800000, float:-1.0)
                r2.horizontalWeight = r3
                r2 = r0
                r3 = -1082130432(0xffffffffbf800000, float:-1.0)
                r2.verticalWeight = r3
                r2 = r0
                r3 = 0
                r2.horizontalChainStyle = r3
                r2 = r0
                r3 = 0
                r2.verticalChainStyle = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintDefaultWidth = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintDefaultHeight = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintMinWidth = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintMinHeight = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintMaxWidth = r3
                r2 = r0
                r3 = 0
                r2.matchConstraintMaxHeight = r3
                r2 = r0
                r3 = 1065353216(0x3f800000, float:1.0)
                r2.matchConstraintPercentWidth = r3
                r2 = r0
                r3 = 1065353216(0x3f800000, float:1.0)
                r2.matchConstraintPercentHeight = r3
                r2 = r0
                r3 = -1
                r2.editorAbsoluteX = r3
                r2 = r0
                r3 = -1
                r2.editorAbsoluteY = r3
                r2 = r0
                r3 = -1
                r2.orientation = r3
                r2 = r0
                r3 = 0
                r2.constrainedWidth = r3
                r2 = r0
                r3 = 0
                r2.constrainedHeight = r3
                r2 = r0
                r3 = 1
                r2.horizontalDimensionFixed = r3
                r2 = r0
                r3 = 1
                r2.verticalDimensionFixed = r3
                r2 = r0
                r3 = 0
                r2.needsBaseline = r3
                r2 = r0
                r3 = 0
                r2.isGuideline = r3
                r2 = r0
                r3 = 0
                r2.isHelper = r3
                r2 = r0
                r3 = 0
                r2.isInPlaceholder = r3
                r2 = r0
                r3 = -1
                r2.resolvedLeftToLeft = r3
                r2 = r0
                r3 = -1
                r2.resolvedLeftToRight = r3
                r2 = r0
                r3 = -1
                r2.resolvedRightToLeft = r3
                r2 = r0
                r3 = -1
                r2.resolvedRightToRight = r3
                r2 = r0
                r3 = -1
                r2.resolveGoneLeftMargin = r3
                r2 = r0
                r3 = -1
                r2.resolveGoneRightMargin = r3
                r2 = r0
                r3 = 1056964608(0x3f000000, float:0.5)
                r2.resolvedHorizontalBias = r3
                r2 = r0
                android.support.constraint.solver.widgets.ConstraintWidget r3 = new android.support.constraint.solver.widgets.ConstraintWidget
                r5 = r3
                r3 = r5
                r4 = r5
                r4.<init>()
                r2.widget = r3
                r2 = r0
                r3 = 0
                r2.helped = r3
                r2 = r0
                r3 = r1
                int r3 = r3.guideBegin
                r2.guideBegin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.guideEnd
                r2.guideEnd = r3
                r2 = r0
                r3 = r1
                float r3 = r3.guidePercent
                r2.guidePercent = r3
                r2 = r0
                r3 = r1
                int r3 = r3.leftToLeft
                r2.leftToLeft = r3
                r2 = r0
                r3 = r1
                int r3 = r3.leftToRight
                r2.leftToRight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.rightToLeft
                r2.rightToLeft = r3
                r2 = r0
                r3 = r1
                int r3 = r3.rightToRight
                r2.rightToRight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.topToTop
                r2.topToTop = r3
                r2 = r0
                r3 = r1
                int r3 = r3.topToBottom
                r2.topToBottom = r3
                r2 = r0
                r3 = r1
                int r3 = r3.bottomToTop
                r2.bottomToTop = r3
                r2 = r0
                r3 = r1
                int r3 = r3.bottomToBottom
                r2.bottomToBottom = r3
                r2 = r0
                r3 = r1
                int r3 = r3.baselineToBaseline
                r2.baselineToBaseline = r3
                r2 = r0
                r3 = r1
                int r3 = r3.circleConstraint
                r2.circleConstraint = r3
                r2 = r0
                r3 = r1
                int r3 = r3.circleRadius
                r2.circleRadius = r3
                r2 = r0
                r3 = r1
                float r3 = r3.circleAngle
                r2.circleAngle = r3
                r2 = r0
                r3 = r1
                int r3 = r3.startToEnd
                r2.startToEnd = r3
                r2 = r0
                r3 = r1
                int r3 = r3.startToStart
                r2.startToStart = r3
                r2 = r0
                r3 = r1
                int r3 = r3.endToStart
                r2.endToStart = r3
                r2 = r0
                r3 = r1
                int r3 = r3.endToEnd
                r2.endToEnd = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneLeftMargin
                r2.goneLeftMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneTopMargin
                r2.goneTopMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneRightMargin
                r2.goneRightMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneBottomMargin
                r2.goneBottomMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneStartMargin
                r2.goneStartMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.goneEndMargin
                r2.goneEndMargin = r3
                r2 = r0
                r3 = r1
                float r3 = r3.horizontalBias
                r2.horizontalBias = r3
                r2 = r0
                r3 = r1
                float r3 = r3.verticalBias
                r2.verticalBias = r3
                r2 = r0
                r3 = r1
                java.lang.String r3 = r3.dimensionRatio
                r2.dimensionRatio = r3
                r2 = r0
                r3 = r1
                float r3 = r3.dimensionRatioValue
                r2.dimensionRatioValue = r3
                r2 = r0
                r3 = r1
                int r3 = r3.dimensionRatioSide
                r2.dimensionRatioSide = r3
                r2 = r0
                r3 = r1
                float r3 = r3.horizontalWeight
                r2.horizontalWeight = r3
                r2 = r0
                r3 = r1
                float r3 = r3.verticalWeight
                r2.verticalWeight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.horizontalChainStyle
                r2.horizontalChainStyle = r3
                r2 = r0
                r3 = r1
                int r3 = r3.verticalChainStyle
                r2.verticalChainStyle = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.constrainedWidth
                r2.constrainedWidth = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.constrainedHeight
                r2.constrainedHeight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintDefaultWidth
                r2.matchConstraintDefaultWidth = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintDefaultHeight
                r2.matchConstraintDefaultHeight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintMinWidth
                r2.matchConstraintMinWidth = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintMaxWidth
                r2.matchConstraintMaxWidth = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintMinHeight
                r2.matchConstraintMinHeight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.matchConstraintMaxHeight
                r2.matchConstraintMaxHeight = r3
                r2 = r0
                r3 = r1
                float r3 = r3.matchConstraintPercentWidth
                r2.matchConstraintPercentWidth = r3
                r2 = r0
                r3 = r1
                float r3 = r3.matchConstraintPercentHeight
                r2.matchConstraintPercentHeight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.editorAbsoluteX
                r2.editorAbsoluteX = r3
                r2 = r0
                r3 = r1
                int r3 = r3.editorAbsoluteY
                r2.editorAbsoluteY = r3
                r2 = r0
                r3 = r1
                int r3 = r3.orientation
                r2.orientation = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.horizontalDimensionFixed
                r2.horizontalDimensionFixed = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.verticalDimensionFixed
                r2.verticalDimensionFixed = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.needsBaseline
                r2.needsBaseline = r3
                r2 = r0
                r3 = r1
                boolean r3 = r3.isGuideline
                r2.isGuideline = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolvedLeftToLeft
                r2.resolvedLeftToLeft = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolvedLeftToRight
                r2.resolvedLeftToRight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolvedRightToLeft
                r2.resolvedRightToLeft = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolvedRightToRight
                r2.resolvedRightToRight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolveGoneLeftMargin
                r2.resolveGoneLeftMargin = r3
                r2 = r0
                r3 = r1
                int r3 = r3.resolveGoneRightMargin
                r2.resolveGoneRightMargin = r3
                r2 = r0
                r3 = r1
                float r3 = r3.resolvedHorizontalBias
                r2.resolvedHorizontalBias = r3
                r2 = r0
                r3 = r1
                android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.widget
                r2.widget = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.<init>(android.support.constraint.ConstraintLayout$LayoutParams):void");
        }

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map;

            private Table() {
            }

            static {
                SparseIntArray sparseIntArray;
                new SparseIntArray();
                map = sparseIntArray;
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(C0025R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(C0025R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r24, android.util.AttributeSet r25) {
            /*
                r23 = this;
                r2 = r23
                r3 = r24
                r4 = r25
                r17 = r2
                r18 = r3
                r19 = r4
                r17.<init>(r18, r19)
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.guideBegin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.guideEnd = r0
                r17 = r2
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                r0 = r18
                r1 = r17
                r1.guidePercent = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.leftToLeft = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.leftToRight = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.rightToLeft = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.rightToRight = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.topToTop = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.topToBottom = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.bottomToTop = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.bottomToBottom = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.baselineToBaseline = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.circleConstraint = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.circleRadius = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.circleAngle = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.startToEnd = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.startToStart = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.endToStart = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.endToEnd = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneLeftMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneTopMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneRightMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneBottomMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneStartMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.goneEndMargin = r0
                r17 = r2
                r18 = 1056964608(0x3f000000, float:0.5)
                r0 = r18
                r1 = r17
                r1.horizontalBias = r0
                r17 = r2
                r18 = 1056964608(0x3f000000, float:0.5)
                r0 = r18
                r1 = r17
                r1.verticalBias = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.dimensionRatio = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.dimensionRatioValue = r0
                r17 = r2
                r18 = 1
                r0 = r18
                r1 = r17
                r1.dimensionRatioSide = r0
                r17 = r2
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                r0 = r18
                r1 = r17
                r1.horizontalWeight = r0
                r17 = r2
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                r0 = r18
                r1 = r17
                r1.verticalWeight = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.horizontalChainStyle = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.verticalChainStyle = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintDefaultWidth = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintDefaultHeight = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintMinWidth = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintMinHeight = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxWidth = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxHeight = r0
                r17 = r2
                r18 = 1065353216(0x3f800000, float:1.0)
                r0 = r18
                r1 = r17
                r1.matchConstraintPercentWidth = r0
                r17 = r2
                r18 = 1065353216(0x3f800000, float:1.0)
                r0 = r18
                r1 = r17
                r1.matchConstraintPercentHeight = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.editorAbsoluteX = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.editorAbsoluteY = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.orientation = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.constrainedWidth = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.constrainedHeight = r0
                r17 = r2
                r18 = 1
                r0 = r18
                r1 = r17
                r1.horizontalDimensionFixed = r0
                r17 = r2
                r18 = 1
                r0 = r18
                r1 = r17
                r1.verticalDimensionFixed = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.needsBaseline = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.isGuideline = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.isHelper = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.isInPlaceholder = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolvedLeftToLeft = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolvedLeftToRight = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolvedRightToLeft = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolvedRightToRight = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolveGoneLeftMargin = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.resolveGoneRightMargin = r0
                r17 = r2
                r18 = 1056964608(0x3f000000, float:0.5)
                r0 = r18
                r1 = r17
                r1.resolvedHorizontalBias = r0
                r17 = r2
                android.support.constraint.solver.widgets.ConstraintWidget r18 = new android.support.constraint.solver.widgets.ConstraintWidget
                r22 = r18
                r18 = r22
                r19 = r22
                r19.<init>()
                r0 = r18
                r1 = r17
                r1.widget = r0
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.helped = r0
                r17 = r3
                r18 = r4
                int[] r19 = android.support.constraint.C0025R.styleable.ConstraintLayout_Layout
                android.content.res.TypedArray r17 = r17.obtainStyledAttributes(r18, r19)
                r5 = r17
                r17 = r5
                int r17 = r17.getIndexCount()
                r6 = r17
                r17 = 0
                r7 = r17
            L_0x029c:
                r17 = r7
                r18 = r6
                r0 = r17
                r1 = r18
                if (r0 >= r1) goto L_0x0b9d
                r17 = r5
                r18 = r7
                int r17 = r17.getIndex(r18)
                r8 = r17
                android.util.SparseIntArray r17 = android.support.constraint.ConstraintLayout.LayoutParams.Table.map
                r18 = r8
                int r17 = r17.get(r18)
                r9 = r17
                r17 = r9
                switch(r17) {
                    case 0: goto L_0x02c2;
                    case 1: goto L_0x05f9;
                    case 2: goto L_0x04dd;
                    case 3: goto L_0x0519;
                    case 4: goto L_0x0533;
                    case 5: goto L_0x05ab;
                    case 6: goto L_0x05c5;
                    case 7: goto L_0x05df;
                    case 8: goto L_0x02c3;
                    case 9: goto L_0x02fe;
                    case 10: goto L_0x0339;
                    case 11: goto L_0x0375;
                    case 12: goto L_0x03b1;
                    case 13: goto L_0x03ed;
                    case 14: goto L_0x0429;
                    case 15: goto L_0x0465;
                    case 16: goto L_0x04a1;
                    case 17: goto L_0x0613;
                    case 18: goto L_0x064f;
                    case 19: goto L_0x068b;
                    case 20: goto L_0x06c7;
                    case 21: goto L_0x0703;
                    case 22: goto L_0x071d;
                    case 23: goto L_0x0737;
                    case 24: goto L_0x0751;
                    case 25: goto L_0x076b;
                    case 26: goto L_0x0785;
                    case 27: goto L_0x09b1;
                    case 28: goto L_0x09cb;
                    case 29: goto L_0x079f;
                    case 30: goto L_0x07b9;
                    case 31: goto L_0x09e5;
                    case 32: goto L_0x0a13;
                    case 33: goto L_0x0a41;
                    case 34: goto L_0x0a86;
                    case 35: goto L_0x0acb;
                    case 36: goto L_0x0aeb;
                    case 37: goto L_0x0b30;
                    case 38: goto L_0x0b75;
                    case 39: goto L_0x0b95;
                    case 40: goto L_0x0b97;
                    case 41: goto L_0x0b99;
                    case 42: goto L_0x0b9b;
                    case 43: goto L_0x02bf;
                    case 44: goto L_0x07d3;
                    case 45: goto L_0x0955;
                    case 46: goto L_0x096f;
                    case 47: goto L_0x0989;
                    case 48: goto L_0x099d;
                    case 49: goto L_0x0577;
                    case 50: goto L_0x0591;
                    default: goto L_0x02bf;
                }
            L_0x02bf:
                int r7 = r7 + 1
                goto L_0x029c
            L_0x02c2:
                goto L_0x02bf
            L_0x02c3:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.leftToLeft
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.leftToLeft = r0
                r17 = r2
                r0 = r17
                int r0 = r0.leftToLeft
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.leftToLeft = r0
                goto L_0x02bf
            L_0x02fe:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.leftToRight
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.leftToRight = r0
                r17 = r2
                r0 = r17
                int r0 = r0.leftToRight
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.leftToRight = r0
                goto L_0x02bf
            L_0x0339:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.rightToLeft
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.rightToLeft = r0
                r17 = r2
                r0 = r17
                int r0 = r0.rightToLeft
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.rightToLeft = r0
                goto L_0x02bf
            L_0x0375:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.rightToRight
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.rightToRight = r0
                r17 = r2
                r0 = r17
                int r0 = r0.rightToRight
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.rightToRight = r0
                goto L_0x02bf
            L_0x03b1:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.topToTop
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.topToTop = r0
                r17 = r2
                r0 = r17
                int r0 = r0.topToTop
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.topToTop = r0
                goto L_0x02bf
            L_0x03ed:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.topToBottom
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.topToBottom = r0
                r17 = r2
                r0 = r17
                int r0 = r0.topToBottom
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.topToBottom = r0
                goto L_0x02bf
            L_0x0429:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.bottomToTop
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.bottomToTop = r0
                r17 = r2
                r0 = r17
                int r0 = r0.bottomToTop
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.bottomToTop = r0
                goto L_0x02bf
            L_0x0465:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.bottomToBottom
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.bottomToBottom = r0
                r17 = r2
                r0 = r17
                int r0 = r0.bottomToBottom
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.bottomToBottom = r0
                goto L_0x02bf
            L_0x04a1:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.baselineToBaseline
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.baselineToBaseline = r0
                r17 = r2
                r0 = r17
                int r0 = r0.baselineToBaseline
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.baselineToBaseline = r0
                goto L_0x02bf
            L_0x04dd:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.circleConstraint
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.circleConstraint = r0
                r17 = r2
                r0 = r17
                int r0 = r0.circleConstraint
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.circleConstraint = r0
                goto L_0x02bf
            L_0x0519:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.circleRadius
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.circleRadius = r0
                goto L_0x02bf
            L_0x0533:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.circleAngle
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r19 = 1135869952(0x43b40000, float:360.0)
                float r18 = r18 % r19
                r0 = r18
                r1 = r17
                r1.circleAngle = r0
                r17 = r2
                r0 = r17
                float r0 = r0.circleAngle
                r17 = r0
                r18 = 0
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 >= 0) goto L_0x02bf
                r17 = r2
                r18 = 1135869952(0x43b40000, float:360.0)
                r19 = r2
                r0 = r19
                float r0 = r0.circleAngle
                r19 = r0
                float r18 = r18 - r19
                r19 = 1135869952(0x43b40000, float:360.0)
                float r18 = r18 % r19
                r0 = r18
                r1 = r17
                r1.circleAngle = r0
                goto L_0x02bf
            L_0x0577:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.editorAbsoluteX
                r20 = r0
                int r18 = r18.getDimensionPixelOffset(r19, r20)
                r0 = r18
                r1 = r17
                r1.editorAbsoluteX = r0
                goto L_0x02bf
            L_0x0591:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.editorAbsoluteY
                r20 = r0
                int r18 = r18.getDimensionPixelOffset(r19, r20)
                r0 = r18
                r1 = r17
                r1.editorAbsoluteY = r0
                goto L_0x02bf
            L_0x05ab:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.guideBegin
                r20 = r0
                int r18 = r18.getDimensionPixelOffset(r19, r20)
                r0 = r18
                r1 = r17
                r1.guideBegin = r0
                goto L_0x02bf
            L_0x05c5:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.guideEnd
                r20 = r0
                int r18 = r18.getDimensionPixelOffset(r19, r20)
                r0 = r18
                r1 = r17
                r1.guideEnd = r0
                goto L_0x02bf
            L_0x05df:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.guidePercent
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r0 = r18
                r1 = r17
                r1.guidePercent = r0
                goto L_0x02bf
            L_0x05f9:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.orientation
                r20 = r0
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.orientation = r0
                goto L_0x02bf
            L_0x0613:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.startToEnd
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.startToEnd = r0
                r17 = r2
                r0 = r17
                int r0 = r0.startToEnd
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.startToEnd = r0
                goto L_0x02bf
            L_0x064f:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.startToStart
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.startToStart = r0
                r17 = r2
                r0 = r17
                int r0 = r0.startToStart
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.startToStart = r0
                goto L_0x02bf
            L_0x068b:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.endToStart
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.endToStart = r0
                r17 = r2
                r0 = r17
                int r0 = r0.endToStart
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.endToStart = r0
                goto L_0x02bf
            L_0x06c7:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.endToEnd
                r20 = r0
                int r18 = r18.getResourceId(r19, r20)
                r0 = r18
                r1 = r17
                r1.endToEnd = r0
                r17 = r2
                r0 = r17
                int r0 = r0.endToEnd
                r17 = r0
                r18 = -1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = -1
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.endToEnd = r0
                goto L_0x02bf
            L_0x0703:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneLeftMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneLeftMargin = r0
                goto L_0x02bf
            L_0x071d:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneTopMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneTopMargin = r0
                goto L_0x02bf
            L_0x0737:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneRightMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneRightMargin = r0
                goto L_0x02bf
            L_0x0751:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneBottomMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneBottomMargin = r0
                goto L_0x02bf
            L_0x076b:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneStartMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneStartMargin = r0
                goto L_0x02bf
            L_0x0785:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.goneEndMargin
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)
                r0 = r18
                r1 = r17
                r1.goneEndMargin = r0
                goto L_0x02bf
            L_0x079f:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.horizontalBias
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r0 = r18
                r1 = r17
                r1.horizontalBias = r0
                goto L_0x02bf
            L_0x07b9:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.verticalBias
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r0 = r18
                r1 = r17
                r1.verticalBias = r0
                goto L_0x02bf
            L_0x07d3:
                r17 = r2
                r18 = r5
                r19 = r8
                java.lang.String r18 = r18.getString(r19)
                r0 = r18
                r1 = r17
                r1.dimensionRatio = r0
                r17 = r2
                r18 = 2143289344(0x7fc00000, float:NaN)
                r0 = r18
                r1 = r17
                r1.dimensionRatioValue = r0
                r17 = r2
                r18 = -1
                r0 = r18
                r1 = r17
                r1.dimensionRatioSide = r0
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                if (r17 == 0) goto L_0x02bf
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                int r17 = r17.length()
                r10 = r17
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = 44
                int r17 = r17.indexOf(r18)
                r11 = r17
                r17 = r11
                if (r17 <= 0) goto L_0x090d
                r17 = r11
                r18 = r10
                r19 = 1
                int r18 = r18 + -1
                r0 = r17
                r1 = r18
                if (r0 >= r1) goto L_0x090d
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = 0
                r19 = r11
                java.lang.String r17 = r17.substring(r18, r19)
                r12 = r17
                r17 = r12
                java.lang.String r18 = "W"
                boolean r17 = r17.equalsIgnoreCase(r18)
                if (r17 == 0) goto L_0x08f6
                r17 = r2
                r18 = 0
                r0 = r18
                r1 = r17
                r1.dimensionRatioSide = r0
            L_0x0858:
                int r11 = r11 + 1
            L_0x085a:
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = 58
                int r17 = r17.indexOf(r18)
                r12 = r17
                r17 = r12
                if (r17 < 0) goto L_0x092a
                r17 = r12
                r18 = r10
                r19 = 1
                int r18 = r18 + -1
                r0 = r17
                r1 = r18
                if (r0 >= r1) goto L_0x092a
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = r11
                r19 = r12
                java.lang.String r17 = r17.substring(r18, r19)
                r13 = r17
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = r12
                r19 = 1
                int r18 = r18 + 1
                java.lang.String r17 = r17.substring(r18)
                r14 = r17
                r17 = r13
                int r17 = r17.length()
                if (r17 <= 0) goto L_0x08f4
                r17 = r14
                int r17 = r17.length()
                if (r17 <= 0) goto L_0x08f4
                r17 = r13
                float r17 = java.lang.Float.parseFloat(r17)     // Catch:{ NumberFormatException -> 0x0926 }
                r15 = r17
                r17 = r14
                float r17 = java.lang.Float.parseFloat(r17)     // Catch:{ NumberFormatException -> 0x0926 }
                r16 = r17
                r17 = r15
                r18 = 0
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 <= 0) goto L_0x08f4
                r17 = r16
                r18 = 0
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 <= 0) goto L_0x08f4
                r17 = r2
                r0 = r17
                int r0 = r0.dimensionRatioSide     // Catch:{ NumberFormatException -> 0x0926 }
                r17 = r0
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0913
                r17 = r2
                r18 = r16
                r19 = r15
                float r18 = r18 / r19
                float r18 = java.lang.Math.abs(r18)     // Catch:{ NumberFormatException -> 0x0926 }
                r0 = r18
                r1 = r17
                r1.dimensionRatioValue = r0     // Catch:{ NumberFormatException -> 0x0926 }
            L_0x08f4:
                goto L_0x02bf
            L_0x08f6:
                r17 = r12
                java.lang.String r18 = "H"
                boolean r17 = r17.equalsIgnoreCase(r18)
                if (r17 == 0) goto L_0x0858
                r17 = r2
                r18 = 1
                r0 = r18
                r1 = r17
                r1.dimensionRatioSide = r0
                goto L_0x0858
            L_0x090d:
                r17 = 0
                r11 = r17
                goto L_0x085a
            L_0x0913:
                r17 = r2
                r18 = r15
                r19 = r16
                float r18 = r18 / r19
                float r18 = java.lang.Math.abs(r18)     // Catch:{ NumberFormatException -> 0x0926 }
                r0 = r18
                r1 = r17
                r1.dimensionRatioValue = r0     // Catch:{ NumberFormatException -> 0x0926 }
                goto L_0x08f4
            L_0x0926:
                r17 = move-exception
                r15 = r17
                goto L_0x08f4
            L_0x092a:
                r17 = r2
                r0 = r17
                java.lang.String r0 = r0.dimensionRatio
                r17 = r0
                r18 = r11
                java.lang.String r17 = r17.substring(r18)
                r13 = r17
                r17 = r13
                int r17 = r17.length()
                if (r17 <= 0) goto L_0x08f4
                r17 = r2
                r18 = r13
                float r18 = java.lang.Float.parseFloat(r18)     // Catch:{ NumberFormatException -> 0x0951 }
                r0 = r18
                r1 = r17
                r1.dimensionRatioValue = r0     // Catch:{ NumberFormatException -> 0x0951 }
                goto L_0x08f4
            L_0x0951:
                r17 = move-exception
                r14 = r17
                goto L_0x08f4
            L_0x0955:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.horizontalWeight
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r0 = r18
                r1 = r17
                r1.horizontalWeight = r0
                goto L_0x02bf
            L_0x096f:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                float r0 = r0.verticalWeight
                r20 = r0
                float r18 = r18.getFloat(r19, r20)
                r0 = r18
                r1 = r17
                r1.verticalWeight = r0
                goto L_0x02bf
            L_0x0989:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = 0
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.horizontalChainStyle = r0
                goto L_0x02bf
            L_0x099d:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = 0
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.verticalChainStyle = r0
                goto L_0x02bf
            L_0x09b1:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                boolean r0 = r0.constrainedWidth
                r20 = r0
                boolean r18 = r18.getBoolean(r19, r20)
                r0 = r18
                r1 = r17
                r1.constrainedWidth = r0
                goto L_0x02bf
            L_0x09cb:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                boolean r0 = r0.constrainedHeight
                r20 = r0
                boolean r18 = r18.getBoolean(r19, r20)
                r0 = r18
                r1 = r17
                r1.constrainedHeight = r0
                goto L_0x02bf
            L_0x09e5:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = 0
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.matchConstraintDefaultWidth = r0
                r17 = r2
                r0 = r17
                int r0 = r0.matchConstraintDefaultWidth
                r17 = r0
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                java.lang.String r17 = "ConstraintLayout"
                java.lang.String r18 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead."
                int r17 = android.util.Log.e(r17, r18)
                goto L_0x02bf
            L_0x0a13:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = 0
                int r18 = r18.getInt(r19, r20)
                r0 = r18
                r1 = r17
                r1.matchConstraintDefaultHeight = r0
                r17 = r2
                r0 = r17
                int r0 = r0.matchConstraintDefaultHeight
                r17 = r0
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x02bf
                java.lang.String r17 = "ConstraintLayout"
                java.lang.String r18 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead."
                int r17 = android.util.Log.e(r17, r18)
                goto L_0x02bf
            L_0x0a41:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.matchConstraintMinWidth     // Catch:{ Exception -> 0x0a5b }
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)     // Catch:{ Exception -> 0x0a5b }
                r0 = r18
                r1 = r17
                r1.matchConstraintMinWidth = r0     // Catch:{ Exception -> 0x0a5b }
                goto L_0x02bf
            L_0x0a5b:
                r17 = move-exception
                r10 = r17
                r17 = r5
                r18 = r8
                r19 = r2
                r0 = r19
                int r0 = r0.matchConstraintMinWidth
                r19 = r0
                int r17 = r17.getInt(r18, r19)
                r11 = r17
                r17 = r11
                r18 = -2
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0a84
                r17 = r2
                r18 = -2
                r0 = r18
                r1 = r17
                r1.matchConstraintMinWidth = r0
            L_0x0a84:
                goto L_0x02bf
            L_0x0a86:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.matchConstraintMaxWidth     // Catch:{ Exception -> 0x0aa0 }
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)     // Catch:{ Exception -> 0x0aa0 }
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxWidth = r0     // Catch:{ Exception -> 0x0aa0 }
                goto L_0x02bf
            L_0x0aa0:
                r17 = move-exception
                r10 = r17
                r17 = r5
                r18 = r8
                r19 = r2
                r0 = r19
                int r0 = r0.matchConstraintMaxWidth
                r19 = r0
                int r17 = r17.getInt(r18, r19)
                r11 = r17
                r17 = r11
                r18 = -2
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0ac9
                r17 = r2
                r18 = -2
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxWidth = r0
            L_0x0ac9:
                goto L_0x02bf
            L_0x0acb:
                r17 = r2
                r18 = 0
                r19 = r5
                r20 = r8
                r21 = r2
                r0 = r21
                float r0 = r0.matchConstraintPercentWidth
                r21 = r0
                float r19 = r19.getFloat(r20, r21)
                float r18 = java.lang.Math.max(r18, r19)
                r0 = r18
                r1 = r17
                r1.matchConstraintPercentWidth = r0
                goto L_0x02bf
            L_0x0aeb:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.matchConstraintMinHeight     // Catch:{ Exception -> 0x0b05 }
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)     // Catch:{ Exception -> 0x0b05 }
                r0 = r18
                r1 = r17
                r1.matchConstraintMinHeight = r0     // Catch:{ Exception -> 0x0b05 }
                goto L_0x02bf
            L_0x0b05:
                r17 = move-exception
                r10 = r17
                r17 = r5
                r18 = r8
                r19 = r2
                r0 = r19
                int r0 = r0.matchConstraintMinHeight
                r19 = r0
                int r17 = r17.getInt(r18, r19)
                r11 = r17
                r17 = r11
                r18 = -2
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0b2e
                r17 = r2
                r18 = -2
                r0 = r18
                r1 = r17
                r1.matchConstraintMinHeight = r0
            L_0x0b2e:
                goto L_0x02bf
            L_0x0b30:
                r17 = r2
                r18 = r5
                r19 = r8
                r20 = r2
                r0 = r20
                int r0 = r0.matchConstraintMaxHeight     // Catch:{ Exception -> 0x0b4a }
                r20 = r0
                int r18 = r18.getDimensionPixelSize(r19, r20)     // Catch:{ Exception -> 0x0b4a }
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxHeight = r0     // Catch:{ Exception -> 0x0b4a }
                goto L_0x02bf
            L_0x0b4a:
                r17 = move-exception
                r10 = r17
                r17 = r5
                r18 = r8
                r19 = r2
                r0 = r19
                int r0 = r0.matchConstraintMaxHeight
                r19 = r0
                int r17 = r17.getInt(r18, r19)
                r11 = r17
                r17 = r11
                r18 = -2
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0b73
                r17 = r2
                r18 = -2
                r0 = r18
                r1 = r17
                r1.matchConstraintMaxHeight = r0
            L_0x0b73:
                goto L_0x02bf
            L_0x0b75:
                r17 = r2
                r18 = 0
                r19 = r5
                r20 = r8
                r21 = r2
                r0 = r21
                float r0 = r0.matchConstraintPercentHeight
                r21 = r0
                float r19 = r19.getFloat(r20, r21)
                float r18 = java.lang.Math.max(r18, r19)
                r0 = r18
                r1 = r17
                r1.matchConstraintPercentHeight = r0
                goto L_0x02bf
            L_0x0b95:
                goto L_0x02bf
            L_0x0b97:
                goto L_0x02bf
            L_0x0b99:
                goto L_0x02bf
            L_0x0b9b:
                goto L_0x02bf
            L_0x0b9d:
                r17 = r5
                r17.recycle()
                r17 = r2
                r17.validate()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void validate() {
            ConstraintWidget constraintWidget;
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = true;
                this.horizontalDimensionFixed = true;
                this.verticalDimensionFixed = true;
                if (!(this.widget instanceof Guideline)) {
                    new Guideline();
                    this.widget = constraintWidget;
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            ConstraintWidget constraintWidget;
            new ConstraintWidget();
            this.widget = constraintWidget;
            this.helped = false;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            ConstraintWidget constraintWidget;
            new ConstraintWidget();
            this.widget = constraintWidget;
            this.helped = false;
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int layoutDirection) {
            int preLeftMargin = this.leftMargin;
            int preRightMargin = this.rightMargin;
            super.resolveLayoutDirection(layoutDirection);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            if (1 == getLayoutDirection()) {
                boolean startEndDefined = false;
                if (this.startToEnd != -1) {
                    this.resolvedRightToLeft = this.startToEnd;
                    startEndDefined = true;
                } else if (this.startToStart != -1) {
                    this.resolvedRightToRight = this.startToStart;
                    startEndDefined = true;
                }
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    startEndDefined = true;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    startEndDefined = true;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (startEndDefined) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    if (this.guidePercent != -1.0f) {
                        this.resolvedGuidePercent = 1.0f - this.guidePercent;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    } else if (this.guideBegin != -1) {
                        this.resolvedGuideEnd = this.guideBegin;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuidePercent = -1.0f;
                    } else if (this.guideEnd != -1) {
                        this.resolvedGuideBegin = this.guideEnd;
                        this.resolvedGuideEnd = -1;
                        this.resolvedGuidePercent = -1.0f;
                    }
                }
            } else {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft != -1) {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                } else if (this.rightToRight != -1) {
                    this.resolvedRightToRight = this.rightToRight;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                }
                if (this.leftToLeft != -1) {
                    this.resolvedLeftToLeft = this.leftToLeft;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                } else if (this.leftToRight != -1) {
                    this.resolvedLeftToRight = this.leftToRight;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                }
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
