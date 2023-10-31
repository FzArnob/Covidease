package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import java.util.ArrayList;

public class WidgetContainer extends ConstraintWidget {
    protected ArrayList<ConstraintWidget> mChildren;

    public WidgetContainer() {
        ArrayList<ConstraintWidget> arrayList;
        new ArrayList<>();
        this.mChildren = arrayList;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
        ArrayList<ConstraintWidget> arrayList;
        new ArrayList<>();
        this.mChildren = arrayList;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetContainer(int width, int height) {
        super(width, height);
        ArrayList<ConstraintWidget> arrayList;
        new ArrayList<>();
        this.mChildren = arrayList;
    }

    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    public void add(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        boolean add = this.mChildren.add(widget);
        if (widget.getParent() != null) {
            ((WidgetContainer) widget.getParent()).remove(widget);
        }
        widget.setParent(this);
    }

    public void add(ConstraintWidget... constraintWidgetArr) {
        ConstraintWidget[] widgets = constraintWidgetArr;
        int count = widgets.length;
        for (int i = 0; i < count; i++) {
            add(widgets[i]);
        }
    }

    public void remove(ConstraintWidget constraintWidget) {
        ConstraintWidget widget = constraintWidget;
        boolean remove = this.mChildren.remove(widget);
        widget.setParent((ConstraintWidget) null);
    }

    public ArrayList<ConstraintWidget> getChildren() {
        return this.mChildren;
    }

    public ConstraintWidgetContainer getRootConstraintContainer() {
        ConstraintWidget parent = getParent();
        ConstraintWidgetContainer container = null;
        if (this instanceof ConstraintWidgetContainer) {
            container = (ConstraintWidgetContainer) this;
        }
        while (parent != null) {
            ConstraintWidget item = parent;
            parent = item.getParent();
            if (item instanceof ConstraintWidgetContainer) {
                container = (ConstraintWidgetContainer) item;
            }
        }
        return container;
    }

    public ConstraintWidget findWidget(float f, float f2) {
        float x = f;
        float y = f2;
        ConstraintWidget found = null;
        int l = getDrawX();
        int t = getDrawY();
        int r = l + getWidth();
        int b = t + getHeight();
        if (x >= ((float) l) && x <= ((float) r) && y >= ((float) t) && y <= ((float) b)) {
            found = this;
        }
        int mChildrenSize = this.mChildren.size();
        for (int i = 0; i < mChildrenSize; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            if (widget instanceof WidgetContainer) {
                ConstraintWidget f3 = ((WidgetContainer) widget).findWidget(x, y);
                if (f3 != null) {
                    found = f3;
                }
            } else {
                int l2 = widget.getDrawX();
                int t2 = widget.getDrawY();
                int r2 = l2 + widget.getWidth();
                int b2 = t2 + widget.getHeight();
                if (x >= ((float) l2) && x <= ((float) r2) && y >= ((float) t2) && y <= ((float) b2)) {
                    found = widget;
                }
            }
        }
        return found;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> findWidgets(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            java.util.ArrayList r11 = new java.util.ArrayList
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r5 = r11
            android.support.constraint.solver.widgets.Rectangle r11 = new android.support.constraint.solver.widgets.Rectangle
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r6 = r11
            r11 = r6
            r12 = r1
            r13 = r2
            r14 = r3
            r15 = r4
            r11.setBounds(r12, r13, r14, r15)
            r11 = 0
            r7 = r11
            r11 = r0
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r11 = r11.mChildren
            int r11 = r11.size()
            r8 = r11
        L_0x0034:
            r11 = r7
            r12 = r8
            if (r11 >= r12) goto L_0x0078
            r11 = r0
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r11 = r11.mChildren
            r12 = r7
            java.lang.Object r11 = r11.get(r12)
            android.support.constraint.solver.widgets.ConstraintWidget r11 = (android.support.constraint.solver.widgets.ConstraintWidget) r11
            r9 = r11
            android.support.constraint.solver.widgets.Rectangle r11 = new android.support.constraint.solver.widgets.Rectangle
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r10 = r11
            r11 = r10
            r12 = r9
            int r12 = r12.getDrawX()
            r13 = r9
            int r13 = r13.getDrawY()
            r14 = r9
            int r14 = r14.getWidth()
            r15 = r9
            int r15 = r15.getHeight()
            r11.setBounds(r12, r13, r14, r15)
            r11 = r6
            r12 = r10
            boolean r11 = r11.intersects(r12)
            if (r11 == 0) goto L_0x0075
            r11 = r5
            r12 = r9
            boolean r11 = r11.add(r12)
        L_0x0075:
            int r7 = r7 + 1
            goto L_0x0034
        L_0x0078:
            r11 = r5
            r0 = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.WidgetContainer.findWidgets(int, int, int, int):java.util.ArrayList");
    }

    public static Rectangle getBounds(ArrayList<ConstraintWidget> arrayList) {
        Rectangle rectangle;
        ArrayList<ConstraintWidget> widgets = arrayList;
        new Rectangle();
        Rectangle bounds = rectangle;
        if (widgets.size() == 0) {
            return bounds;
        }
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        int widgetsSize = widgets.size();
        for (int i = 0; i < widgetsSize; i++) {
            ConstraintWidget widget = widgets.get(i);
            if (widget.getX() < minX) {
                minX = widget.getX();
            }
            if (widget.getY() < minY) {
                minY = widget.getY();
            }
            if (widget.getRight() > maxX) {
                maxX = widget.getRight();
            }
            if (widget.getBottom() > maxY) {
                maxY = widget.getBottom();
            }
        }
        bounds.setBounds(minX, minY, maxX - minX, maxY - minY);
        return bounds;
    }

    public void setOffset(int x, int y) {
        super.setOffset(x, y);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            this.mChildren.get(i).setOffset(getRootX(), getRootY());
        }
    }

    public void updateDrawPosition() {
        super.updateDrawPosition();
        if (this.mChildren != null) {
            int count = this.mChildren.size();
            for (int i = 0; i < count; i++) {
                ConstraintWidget widget = this.mChildren.get(i);
                widget.setOffset(getDrawX(), getDrawY());
                if (!(widget instanceof ConstraintWidgetContainer)) {
                    widget.updateDrawPosition();
                }
            }
        }
    }

    public void layout() {
        updateDrawPosition();
        if (this.mChildren != null) {
            int count = this.mChildren.size();
            for (int i = 0; i < count; i++) {
                ConstraintWidget widget = this.mChildren.get(i);
                if (widget instanceof WidgetContainer) {
                    ((WidgetContainer) widget).layout();
                }
            }
        }
    }

    public void resetSolverVariables(Cache cache) {
        Cache cache2 = cache;
        super.resetSolverVariables(cache2);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            this.mChildren.get(i).resetSolverVariables(cache2);
        }
    }

    public void removeAllChildren() {
        this.mChildren.clear();
    }
}
