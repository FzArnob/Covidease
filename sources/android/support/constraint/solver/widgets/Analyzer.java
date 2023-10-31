package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Analyzer {
    private Analyzer() {
    }

    public static void determineGroups(ConstraintWidgetContainer constraintWidgetContainer) {
        ConstraintWidgetContainer layoutWidget = constraintWidgetContainer;
        if ((layoutWidget.getOptimizationLevel() & 32) != 32) {
            singleGroup(layoutWidget);
            return;
        }
        layoutWidget.mSkipSolver = true;
        layoutWidget.mGroupsWrapOptimized = false;
        layoutWidget.mHorizontalWrapOptimized = false;
        layoutWidget.mVerticalWrapOptimized = false;
        List<ConstraintWidget> widgets = layoutWidget.mChildren;
        List<ConstraintWidgetGroup> widgetGroups = layoutWidget.mWidgetGroups;
        boolean horizontalWrapContent = layoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean verticalWrapContent = layoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean hasWrapContent = horizontalWrapContent || verticalWrapContent;
        widgetGroups.clear();
        for (ConstraintWidget widget : widgets) {
            widget.mBelongingGroup = null;
            widget.mGroupsToSolver = false;
            widget.resetResolutionNodes();
        }
        for (ConstraintWidget widget2 : widgets) {
            if (widget2.mBelongingGroup == null && !determineGroups(widget2, widgetGroups, hasWrapContent)) {
                singleGroup(layoutWidget);
                layoutWidget.mSkipSolver = false;
                return;
            }
        }
        int measuredWidth = 0;
        int measuredHeight = 0;
        for (ConstraintWidgetGroup group : widgetGroups) {
            measuredWidth = Math.max(measuredWidth, getMaxDimension(group, 0));
            measuredHeight = Math.max(measuredHeight, getMaxDimension(group, 1));
        }
        if (horizontalWrapContent) {
            layoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            layoutWidget.setWidth(measuredWidth);
            layoutWidget.mGroupsWrapOptimized = true;
            layoutWidget.mHorizontalWrapOptimized = true;
            layoutWidget.mWrapFixedWidth = measuredWidth;
        }
        if (verticalWrapContent) {
            layoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            layoutWidget.setHeight(measuredHeight);
            layoutWidget.mGroupsWrapOptimized = true;
            layoutWidget.mVerticalWrapOptimized = true;
            layoutWidget.mWrapFixedHeight = measuredHeight;
        }
        setPosition(widgetGroups, 0, layoutWidget.getWidth());
        setPosition(widgetGroups, 1, layoutWidget.getHeight());
    }

    private static boolean determineGroups(ConstraintWidget widget, List<ConstraintWidgetGroup> list, boolean hasWrapContent) {
        ConstraintWidgetGroup constraintWidgetGroup;
        List list2;
        List<ConstraintWidgetGroup> widgetGroups = list;
        new ArrayList();
        new ConstraintWidgetGroup(list2, true);
        ConstraintWidgetGroup traverseList = constraintWidgetGroup;
        boolean add = widgetGroups.add(traverseList);
        return traverse(widget, traverseList, widgetGroups, hasWrapContent);
    }

    private static boolean traverse(ConstraintWidget constraintWidget, ConstraintWidgetGroup constraintWidgetGroup, List<ConstraintWidgetGroup> list, boolean z) {
        ConstraintWidget widget = constraintWidget;
        ConstraintWidgetGroup upperGroup = constraintWidgetGroup;
        List<ConstraintWidgetGroup> widgetGroups = list;
        boolean hasWrapContent = z;
        if (widget == null) {
            return true;
        }
        widget.mOptimizerMeasured = false;
        ConstraintWidgetContainer layoutWidget = (ConstraintWidgetContainer) widget.getParent();
        if (widget.mBelongingGroup == null) {
            widget.mOptimizerMeasurable = true;
            boolean add = upperGroup.mConstrainedGroup.add(widget);
            widget.mBelongingGroup = upperGroup;
            if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null && widget.mTop.mTarget == null && widget.mBottom.mTarget == null && widget.mBaseline.mTarget == null && widget.mCenter.mTarget == null) {
                invalidate(layoutWidget, widget, upperGroup);
                if (hasWrapContent) {
                    return false;
                }
            }
            if (!(widget.mTop.mTarget == null || widget.mBottom.mTarget == null)) {
                boolean z2 = layoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (hasWrapContent) {
                    invalidate(layoutWidget, widget, upperGroup);
                    return false;
                } else if (!(widget.mTop.mTarget.mOwner == widget.getParent() && widget.mBottom.mTarget.mOwner == widget.getParent())) {
                    invalidate(layoutWidget, widget, upperGroup);
                }
            }
            if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                boolean z3 = layoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (hasWrapContent) {
                    invalidate(layoutWidget, widget, upperGroup);
                    return false;
                } else if (!(widget.mLeft.mTarget.mOwner == widget.getParent() && widget.mRight.mTarget.mOwner == widget.getParent())) {
                    invalidate(layoutWidget, widget, upperGroup);
                }
            }
            if (((widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) ^ (widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) && widget.mDimensionRatio != 0.0f) {
                int resolveDimensionRatio = resolveDimensionRatio(widget);
            } else if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                invalidate(layoutWidget, widget, upperGroup);
                if (hasWrapContent) {
                    return false;
                }
            }
            if (((widget.mLeft.mTarget == null && widget.mRight.mTarget == null) || ((widget.mLeft.mTarget != null && widget.mLeft.mTarget.mOwner == widget.mParent && widget.mRight.mTarget == null) || ((widget.mRight.mTarget != null && widget.mRight.mTarget.mOwner == widget.mParent && widget.mLeft.mTarget == null) || (widget.mLeft.mTarget != null && widget.mLeft.mTarget.mOwner == widget.mParent && widget.mRight.mTarget != null && widget.mRight.mTarget.mOwner == widget.mParent)))) && widget.mCenter.mTarget == null && !(widget instanceof Guideline) && !(widget instanceof Helper)) {
                boolean add2 = upperGroup.mStartHorizontalWidgets.add(widget);
            }
            if (((widget.mTop.mTarget == null && widget.mBottom.mTarget == null) || ((widget.mTop.mTarget != null && widget.mTop.mTarget.mOwner == widget.mParent && widget.mBottom.mTarget == null) || ((widget.mBottom.mTarget != null && widget.mBottom.mTarget.mOwner == widget.mParent && widget.mTop.mTarget == null) || (widget.mTop.mTarget != null && widget.mTop.mTarget.mOwner == widget.mParent && widget.mBottom.mTarget != null && widget.mBottom.mTarget.mOwner == widget.mParent)))) && widget.mCenter.mTarget == null && widget.mBaseline.mTarget == null && !(widget instanceof Guideline) && !(widget instanceof Helper)) {
                boolean add3 = upperGroup.mStartVerticalWidgets.add(widget);
            }
            if (widget instanceof Helper) {
                invalidate(layoutWidget, widget, upperGroup);
                if (hasWrapContent) {
                    return false;
                }
                Helper hWidget = (Helper) widget;
                for (int widgetsCount = 0; widgetsCount < hWidget.mWidgetsCount; widgetsCount++) {
                    if (!traverse(hWidget.mWidgets[widgetsCount], upperGroup, widgetGroups, hasWrapContent)) {
                        return false;
                    }
                }
            }
            int anchorsSize = widget.mListAnchors.length;
            for (int i = 0; i < anchorsSize; i++) {
                ConstraintAnchor anchor = widget.mListAnchors[i];
                if (!(anchor.mTarget == null || anchor.mTarget.mOwner == widget.getParent())) {
                    if (anchor.mType == ConstraintAnchor.Type.CENTER) {
                        invalidate(layoutWidget, widget, upperGroup);
                        if (hasWrapContent) {
                            return false;
                        }
                    } else {
                        setConnection(anchor);
                    }
                    if (!traverse(anchor.mTarget.mOwner, upperGroup, widgetGroups, hasWrapContent)) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (widget.mBelongingGroup != upperGroup) {
            boolean addAll = upperGroup.mConstrainedGroup.addAll(widget.mBelongingGroup.mConstrainedGroup);
            boolean addAll2 = upperGroup.mStartHorizontalWidgets.addAll(widget.mBelongingGroup.mStartHorizontalWidgets);
            boolean addAll3 = upperGroup.mStartVerticalWidgets.addAll(widget.mBelongingGroup.mStartVerticalWidgets);
            if (!widget.mBelongingGroup.mSkipSolver) {
                upperGroup.mSkipSolver = false;
            }
            boolean remove = widgetGroups.remove(widget.mBelongingGroup);
            for (ConstraintWidget auxWidget : widget.mBelongingGroup.mConstrainedGroup) {
                auxWidget.mBelongingGroup = upperGroup;
            }
        }
        return true;
    }

    private static void invalidate(ConstraintWidgetContainer layoutWidget, ConstraintWidget widget, ConstraintWidgetGroup group) {
        group.mSkipSolver = false;
        layoutWidget.mSkipSolver = false;
        widget.mOptimizerMeasurable = false;
    }

    private static int getMaxDimension(ConstraintWidgetGroup constraintWidgetGroup, int i) {
        ConstraintWidgetGroup group = constraintWidgetGroup;
        int orientation = i;
        int dimension = 0;
        int offset = orientation * 2;
        List<ConstraintWidget> startWidgets = group.getStartWidgets(orientation);
        int size = startWidgets.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget widget = startWidgets.get(i2);
            dimension = Math.max(dimension, getMaxDimensionTraversal(widget, orientation, widget.mListAnchors[offset + 1].mTarget == null || !(widget.mListAnchors[offset].mTarget == null || widget.mListAnchors[offset + 1].mTarget == null), 0));
        }
        group.mGroupDimensions[orientation] = dimension;
        return dimension;
    }

    private static int getMaxDimensionTraversal(ConstraintWidget constraintWidget, int i, boolean z, int i2) {
        int baselinePreDistance;
        int baselinePostDistance;
        int endOffset;
        int startOffset;
        int flow;
        int dimensionPost;
        ConstraintWidget parent;
        ConstraintWidget widget = constraintWidget;
        int orientation = i;
        boolean topLeftFlow = z;
        int depth = i2;
        if (!widget.mOptimizerMeasurable) {
            return 0;
        }
        int dimensionPre = 0;
        int dimensionPost2 = 0;
        boolean hasBaseline = widget.mBaseline.mTarget != null && orientation == 1;
        if (topLeftFlow) {
            baselinePreDistance = widget.getBaselineDistance();
            baselinePostDistance = widget.getHeight() - widget.getBaselineDistance();
            startOffset = orientation * 2;
            endOffset = startOffset + 1;
        } else {
            baselinePreDistance = widget.getHeight() - widget.getBaselineDistance();
            baselinePostDistance = widget.getBaselineDistance();
            endOffset = orientation * 2;
            startOffset = endOffset + 1;
        }
        if (widget.mListAnchors[endOffset].mTarget == null || widget.mListAnchors[startOffset].mTarget != null) {
            flow = 1;
        } else {
            flow = -1;
            int aux = startOffset;
            startOffset = endOffset;
            endOffset = aux;
        }
        if (hasBaseline) {
            depth -= baselinePreDistance;
        }
        int dimension = (widget.mListAnchors[startOffset].getMargin() * flow) + getParentBiasOffset(widget, orientation);
        int downDepth = dimension + depth;
        int postTemp = (orientation == 0 ? widget.getWidth() : widget.getHeight()) * flow;
        Iterator it = widget.mListAnchors[startOffset].getResolutionNode().dependents.iterator();
        while (it.hasNext()) {
            dimensionPre = Math.max(dimensionPre, getMaxDimensionTraversal(((ResolutionAnchor) ((ResolutionNode) it.next())).myAnchor.mOwner, orientation, topLeftFlow, downDepth));
        }
        Iterator it2 = widget.mListAnchors[endOffset].getResolutionNode().dependents.iterator();
        while (it2.hasNext()) {
            dimensionPost2 = Math.max(dimensionPost2, getMaxDimensionTraversal(((ResolutionAnchor) ((ResolutionNode) it2.next())).myAnchor.mOwner, orientation, topLeftFlow, postTemp + downDepth));
        }
        if (hasBaseline) {
            dimensionPre -= baselinePreDistance;
            dimensionPost = dimensionPost2 + baselinePostDistance;
        } else {
            dimensionPost = dimensionPost2 + ((orientation == 0 ? widget.getWidth() : widget.getHeight()) * flow);
        }
        int dimensionBaseline = 0;
        if (orientation == 1) {
            Iterator it3 = widget.mBaseline.getResolutionNode().dependents.iterator();
            while (it3.hasNext()) {
                ResolutionAnchor anchor = (ResolutionAnchor) ((ResolutionNode) it3.next());
                if (flow == 1) {
                    dimensionBaseline = Math.max(dimensionBaseline, getMaxDimensionTraversal(anchor.myAnchor.mOwner, orientation, topLeftFlow, baselinePreDistance + downDepth));
                } else {
                    dimensionBaseline = Math.max(dimensionBaseline, getMaxDimensionTraversal(anchor.myAnchor.mOwner, orientation, topLeftFlow, (baselinePostDistance * flow) + downDepth));
                }
            }
            if (widget.mBaseline.getResolutionNode().dependents.size() > 0 && !hasBaseline) {
                if (flow == 1) {
                    dimensionBaseline += baselinePreDistance;
                } else {
                    dimensionBaseline -= baselinePostDistance;
                }
            }
        }
        int distanceBeforeWidget = dimension;
        int dimension2 = dimension + Math.max(dimensionPre, Math.max(dimensionPost, dimensionBaseline));
        int leftTop = depth + distanceBeforeWidget;
        int end = leftTop + postTemp;
        if (flow == -1) {
            int aux2 = end;
            end = leftTop;
            leftTop = aux2;
        }
        if (topLeftFlow) {
            Optimizer.setOptimizedWidget(widget, orientation, leftTop);
            widget.setFrame(leftTop, end, orientation);
        } else {
            widget.mBelongingGroup.addWidgetsToSet(widget, orientation);
            widget.setRelativePositioning(leftTop, orientation);
        }
        if (widget.getDimensionBehaviour(orientation) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mDimensionRatio != 0.0f) {
            widget.mBelongingGroup.addWidgetsToSet(widget, orientation);
        }
        if (widget.mListAnchors[startOffset].mTarget != null && widget.mListAnchors[endOffset].mTarget != null && widget.mListAnchors[startOffset].mTarget.mOwner == (parent = widget.getParent()) && widget.mListAnchors[endOffset].mTarget.mOwner == parent) {
            widget.mBelongingGroup.addWidgetsToSet(widget, orientation);
        }
        return dimension2;
    }

    private static void setConnection(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor originAnchor = constraintAnchor;
        ResolutionNode originNode = originAnchor.getResolutionNode();
        if (originAnchor.mTarget != null && originAnchor.mTarget.mTarget != originAnchor) {
            originAnchor.mTarget.getResolutionNode().addDependent(originNode);
        }
    }

    private static void singleGroup(ConstraintWidgetContainer constraintWidgetContainer) {
        Object obj;
        ConstraintWidgetContainer layoutWidget = constraintWidgetContainer;
        layoutWidget.mWidgetGroups.clear();
        new ConstraintWidgetGroup(layoutWidget.mChildren);
        layoutWidget.mWidgetGroups.add(0, obj);
    }

    public static void setPosition(List<ConstraintWidgetGroup> list, int i, int i2) {
        List<ConstraintWidgetGroup> groups = list;
        int orientation = i;
        int containerLength = i2;
        int groupsSize = groups.size();
        for (int i3 = 0; i3 < groupsSize; i3++) {
            for (ConstraintWidget widget : groups.get(i3).getWidgetsToSet(orientation)) {
                if (widget.mOptimizerMeasurable) {
                    updateSizeDependentWidgets(widget, orientation, containerLength);
                }
            }
        }
    }

    private static void updateSizeDependentWidgets(ConstraintWidget constraintWidget, int i, int i2) {
        ConstraintWidget widget = constraintWidget;
        int orientation = i;
        int containerLength = i2;
        int offset = orientation * 2;
        ConstraintAnchor startAnchor = widget.mListAnchors[offset];
        ConstraintAnchor endAnchor = widget.mListAnchors[offset + 1];
        if ((startAnchor.mTarget == null || endAnchor.mTarget == null) ? false : true) {
            Optimizer.setOptimizedWidget(widget, orientation, getParentBiasOffset(widget, orientation) + startAnchor.getMargin());
        } else if (widget.mDimensionRatio == 0.0f || widget.getDimensionBehaviour(orientation) != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int end = containerLength - widget.getRelativePositioning(orientation);
            int start = end - widget.getLength(orientation);
            widget.setFrame(start, end, orientation);
            Optimizer.setOptimizedWidget(widget, orientation, start);
        } else {
            int length = resolveDimensionRatio(widget);
            int start2 = (int) widget.mListAnchors[offset].getResolutionNode().resolvedOffset;
            endAnchor.getResolutionNode().resolvedTarget = startAnchor.getResolutionNode();
            endAnchor.getResolutionNode().resolvedOffset = (float) length;
            endAnchor.getResolutionNode().state = 1;
            widget.setFrame(start2, start2 + length, orientation);
        }
    }

    private static int getParentBiasOffset(ConstraintWidget constraintWidget, int i) {
        ConstraintWidget widget = constraintWidget;
        int orientation = i;
        int offset = orientation * 2;
        ConstraintAnchor startAnchor = widget.mListAnchors[offset];
        ConstraintAnchor endAnchor = widget.mListAnchors[offset + 1];
        if (startAnchor.mTarget == null || startAnchor.mTarget.mOwner != widget.mParent || endAnchor.mTarget == null || endAnchor.mTarget.mOwner != widget.mParent) {
            return 0;
        }
        return (int) (((float) (((widget.mParent.getLength(orientation) - startAnchor.getMargin()) - endAnchor.getMargin()) - widget.getLength(orientation))) * (orientation == 0 ? widget.mHorizontalBiasPercent : widget.mVerticalBiasPercent));
    }

    private static int resolveDimensionRatio(ConstraintWidget constraintWidget) {
        int length;
        ConstraintWidget widget = constraintWidget;
        int length2 = -1;
        if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (widget.mDimensionRatioSide == 0) {
                length2 = (int) (((float) widget.getHeight()) * widget.mDimensionRatio);
            } else {
                length2 = (int) (((float) widget.getHeight()) / widget.mDimensionRatio);
            }
            widget.setWidth(length2);
        } else if (widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (widget.mDimensionRatioSide == 1) {
                length = (int) (((float) widget.getWidth()) * widget.mDimensionRatio);
            } else {
                length = (int) (((float) widget.getWidth()) / widget.mDimensionRatio);
            }
            widget.setHeight(length2);
        }
        return length2;
    }
}
