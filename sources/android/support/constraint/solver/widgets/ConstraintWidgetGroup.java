package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConstraintWidgetGroup {
    public List<ConstraintWidget> mConstrainedGroup;
    public final int[] mGroupDimensions;
    int mGroupHeight = -1;
    int mGroupWidth = -1;
    public boolean mSkipSolver = false;
    List<ConstraintWidget> mStartHorizontalWidgets;
    List<ConstraintWidget> mStartVerticalWidgets;
    List<ConstraintWidget> mUnresolvedWidgets;
    HashSet<ConstraintWidget> mWidgetsToSetHorizontal;
    HashSet<ConstraintWidget> mWidgetsToSetVertical;
    List<ConstraintWidget> mWidgetsToSolve;

    ConstraintWidgetGroup(List<ConstraintWidget> widgets) {
        List<ConstraintWidget> list;
        List<ConstraintWidget> list2;
        HashSet<ConstraintWidget> hashSet;
        HashSet<ConstraintWidget> hashSet2;
        List<ConstraintWidget> list3;
        List<ConstraintWidget> list4;
        int[] iArr = new int[2];
        iArr[0] = this.mGroupWidth;
        int[] iArr2 = iArr;
        iArr2[1] = this.mGroupHeight;
        this.mGroupDimensions = iArr2;
        new ArrayList();
        this.mStartHorizontalWidgets = list;
        new ArrayList();
        this.mStartVerticalWidgets = list2;
        new HashSet<>();
        this.mWidgetsToSetHorizontal = hashSet;
        new HashSet<>();
        this.mWidgetsToSetVertical = hashSet2;
        new ArrayList();
        this.mWidgetsToSolve = list3;
        new ArrayList();
        this.mUnresolvedWidgets = list4;
        this.mConstrainedGroup = widgets;
    }

    ConstraintWidgetGroup(List<ConstraintWidget> widgets, boolean skipSolver) {
        List<ConstraintWidget> list;
        List<ConstraintWidget> list2;
        HashSet<ConstraintWidget> hashSet;
        HashSet<ConstraintWidget> hashSet2;
        List<ConstraintWidget> list3;
        List<ConstraintWidget> list4;
        int[] iArr = new int[2];
        iArr[0] = this.mGroupWidth;
        int[] iArr2 = iArr;
        iArr2[1] = this.mGroupHeight;
        this.mGroupDimensions = iArr2;
        new ArrayList();
        this.mStartHorizontalWidgets = list;
        new ArrayList();
        this.mStartVerticalWidgets = list2;
        new HashSet<>();
        this.mWidgetsToSetHorizontal = hashSet;
        new HashSet<>();
        this.mWidgetsToSetVertical = hashSet2;
        new ArrayList();
        this.mWidgetsToSolve = list3;
        new ArrayList();
        this.mUnresolvedWidgets = list4;
        this.mConstrainedGroup = widgets;
        this.mSkipSolver = skipSolver;
    }

    public List<ConstraintWidget> getStartWidgets(int i) {
        int orientation = i;
        if (orientation == 0) {
            return this.mStartHorizontalWidgets;
        }
        if (orientation == 1) {
            return this.mStartVerticalWidgets;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Set<ConstraintWidget> getWidgetsToSet(int i) {
        int orientation = i;
        if (orientation == 0) {
            return this.mWidgetsToSetHorizontal;
        }
        if (orientation == 1) {
            return this.mWidgetsToSetVertical;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void addWidgetsToSet(ConstraintWidget constraintWidget, int i) {
        ConstraintWidget widget = constraintWidget;
        int orientation = i;
        if (orientation == 0) {
            boolean add = this.mWidgetsToSetHorizontal.add(widget);
        } else if (orientation == 1) {
            boolean add2 = this.mWidgetsToSetVertical.add(widget);
        }
    }

    /* access modifiers changed from: package-private */
    public List<ConstraintWidget> getWidgetsToSolve() {
        if (!this.mWidgetsToSolve.isEmpty()) {
            return this.mWidgetsToSolve;
        }
        int size = this.mConstrainedGroup.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget widget = this.mConstrainedGroup.get(i);
            if (!widget.mOptimizerMeasurable) {
                getWidgetsToSolveTraversal((ArrayList) this.mWidgetsToSolve, widget);
            }
        }
        this.mUnresolvedWidgets.clear();
        boolean addAll = this.mUnresolvedWidgets.addAll(this.mConstrainedGroup);
        boolean removeAll = this.mUnresolvedWidgets.removeAll(this.mWidgetsToSolve);
        return this.mWidgetsToSolve;
    }

    private void getWidgetsToSolveTraversal(ArrayList<ConstraintWidget> arrayList, ConstraintWidget constraintWidget) {
        ArrayList<ConstraintWidget> widgetsToSolve = arrayList;
        ConstraintWidget widget = constraintWidget;
        if (!widget.mGroupsToSolver) {
            boolean add = widgetsToSolve.add(widget);
            widget.mGroupsToSolver = true;
            if (!widget.isFullyResolved()) {
                if (widget instanceof Helper) {
                    Helper helper = (Helper) widget;
                    int widgetCount = helper.mWidgetsCount;
                    for (int i = 0; i < widgetCount; i++) {
                        getWidgetsToSolveTraversal(widgetsToSolve, helper.mWidgets[i]);
                    }
                }
                int count = widget.mListAnchors.length;
                for (int i2 = 0; i2 < count; i2++) {
                    ConstraintAnchor targetAnchor = widget.mListAnchors[i2].mTarget;
                    if (targetAnchor != null) {
                        ConstraintWidget targetWidget = targetAnchor.mOwner;
                        if (!(targetAnchor == null || targetWidget == widget.getParent())) {
                            getWidgetsToSolveTraversal(widgetsToSolve, targetWidget);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateUnresolvedWidgets() {
        int size = this.mUnresolvedWidgets.size();
        for (int i = 0; i < size; i++) {
            updateResolvedDimension(this.mUnresolvedWidgets.get(i));
        }
    }

    private void updateResolvedDimension(ConstraintWidget constraintWidget) {
        ConstraintAnchor targetAnchor;
        int end;
        ConstraintAnchor targetAnchor2;
        int end2;
        ConstraintWidget widget = constraintWidget;
        int end3 = 0;
        if (widget.mOptimizerMeasurable && !widget.isFullyResolved()) {
            boolean rightSide = widget.mRight.mTarget != null;
            if (rightSide) {
                targetAnchor = widget.mRight.mTarget;
            } else {
                targetAnchor = widget.mLeft.mTarget;
            }
            if (targetAnchor != null) {
                if (!targetAnchor.mOwner.mOptimizerMeasured) {
                    updateResolvedDimension(targetAnchor.mOwner);
                }
                if (targetAnchor.mType == ConstraintAnchor.Type.RIGHT) {
                    end3 = targetAnchor.mOwner.f5mX + targetAnchor.mOwner.getWidth();
                } else if (targetAnchor.mType == ConstraintAnchor.Type.LEFT) {
                    end3 = targetAnchor.mOwner.f5mX;
                }
            }
            if (rightSide) {
                end = end3 - widget.mRight.getMargin();
            } else {
                end = end3 + widget.mLeft.getMargin() + widget.getWidth();
            }
            widget.setHorizontalDimension(end - widget.getWidth(), end);
            if (widget.mBaseline.mTarget != null) {
                ConstraintAnchor targetAnchor3 = widget.mBaseline.mTarget;
                if (!targetAnchor3.mOwner.mOptimizerMeasured) {
                    updateResolvedDimension(targetAnchor3.mOwner);
                }
                int start = (targetAnchor3.mOwner.f6mY + targetAnchor3.mOwner.mBaselineDistance) - widget.mBaselineDistance;
                widget.setVerticalDimension(start, start + widget.mHeight);
                widget.mOptimizerMeasured = true;
                return;
            }
            boolean bottomSide = widget.mBottom.mTarget != null;
            if (bottomSide) {
                targetAnchor2 = widget.mBottom.mTarget;
            } else {
                targetAnchor2 = widget.mTop.mTarget;
            }
            if (targetAnchor2 != null) {
                if (!targetAnchor2.mOwner.mOptimizerMeasured) {
                    updateResolvedDimension(targetAnchor2.mOwner);
                }
                if (targetAnchor2.mType == ConstraintAnchor.Type.BOTTOM) {
                    end = targetAnchor2.mOwner.f6mY + targetAnchor2.mOwner.getHeight();
                } else if (targetAnchor2.mType == ConstraintAnchor.Type.TOP) {
                    end = targetAnchor2.mOwner.f6mY;
                }
            }
            if (bottomSide) {
                end2 = end - widget.mBottom.getMargin();
            } else {
                end2 = end + widget.mTop.getMargin() + widget.getHeight();
            }
            widget.setVerticalDimension(end2 - widget.getHeight(), end2);
            widget.mOptimizerMeasured = true;
        }
    }
}
