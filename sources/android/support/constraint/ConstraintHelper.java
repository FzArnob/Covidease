package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.C0025R;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.Helper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds = new int[32];
    private String mReferenceIds;
    protected boolean mUseViewMeasure = false;
    protected Context myContext;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ConstraintHelper(android.content.Context r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 32
            int[] r3 = new int[r3]
            r2.mIds = r3
            r2 = r0
            r3 = 0
            r2.mUseViewMeasure = r3
            r2 = r0
            r3 = r1
            r2.myContext = r3
            r2 = r0
            r3 = 0
            r2.init(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintHelper.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ConstraintHelper(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            r4 = 32
            int[] r4 = new int[r4]
            r3.mIds = r4
            r3 = r0
            r4 = 0
            r3.mUseViewMeasure = r4
            r3 = r0
            r4 = r1
            r3.myContext = r4
            r3 = r0
            r4 = r2
            r3.init(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintHelper.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ConstraintHelper(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            r5 = 32
            int[] r5 = new int[r5]
            r4.mIds = r5
            r4 = r0
            r5 = 0
            r4.mUseViewMeasure = r5
            r4 = r0
            r5 = r1
            r4.myContext = r5
            r4 = r0
            r5 = r2
            r4.init(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintHelper.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet) {
        AttributeSet attrs = attributeSet;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0025R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == C0025R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    this.mReferenceIds = a.getString(attr);
                    setIds(this.mReferenceIds);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void setReferencedIds(int[] iArr) {
        int[] ids = iArr;
        this.mCount = 0;
        for (int i = 0; i < ids.length; i++) {
            setTag(ids[i], (Object) null);
        }
    }

    public void setTag(int i, Object obj) {
        int tag = i;
        Object obj2 = obj;
        if (this.mCount + 1 > this.mIds.length) {
            this.mIds = Arrays.copyOf(this.mIds, this.mIds.length * 2);
        }
        this.mIds[this.mCount] = tag;
        this.mCount++;
    }

    public void onDraw(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (this.mUseViewMeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void validateParams() {
        if (this.mHelperWidget != null) {
            ViewGroup.LayoutParams params = getLayoutParams();
            if (params instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) params).widget = this.mHelperWidget;
            }
        }
    }

    private void addID(String str) {
        StringBuilder sb;
        Object value;
        String idString = str;
        if (idString != null && this.myContext != null) {
            String idString2 = idString.trim();
            int tag = 0;
            try {
                tag = C0025R.C0026id.class.getField(idString2).getInt((Object) null);
            } catch (Exception e) {
                Exception exc = e;
            }
            if (tag == 0) {
                tag = this.myContext.getResources().getIdentifier(idString2, "id", this.myContext.getPackageName());
            }
            if (tag == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout) && (value = ((ConstraintLayout) getParent()).getDesignInformation(0, idString2)) != null && (value instanceof Integer)) {
                tag = ((Integer) value).intValue();
            }
            if (tag != 0) {
                setTag(tag, (Object) null);
                return;
            }
            new StringBuilder();
            int w = Log.w("ConstraintHelper", sb.append("Could not find id of \"").append(idString2).append("\"").toString());
        }
    }

    private void setIds(String str) {
        String idList = str;
        if (idList != null) {
            int i = 0;
            while (true) {
                int begin = i;
                int end = idList.indexOf(44, begin);
                if (end == -1) {
                    addID(idList.substring(begin));
                    return;
                } else {
                    addID(idList.substring(begin, end));
                    i = end + 1;
                }
            }
        }
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) {
        ConstraintLayout container = constraintLayout;
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        if (this.mHelperWidget != null) {
            this.mHelperWidget.removeAllIds();
            for (int i = 0; i < this.mCount; i++) {
                View view = container.getViewById(this.mIds[i]);
                if (view != null) {
                    this.mHelperWidget.add(container.getViewWidget(view));
                }
            }
        }
    }

    public void updatePostLayout(ConstraintLayout container) {
    }

    public void updatePostMeasure(ConstraintLayout container) {
    }
}
