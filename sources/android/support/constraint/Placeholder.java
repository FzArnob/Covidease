package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

public class Placeholder extends View {
    private View mContent = null;
    private int mContentId = -1;
    private int mEmptyVisibility = 4;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Placeholder(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Placeholder(android.content.Context r7, android.util.AttributeSet r8) {
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
            r4 = -1
            r3.mContentId = r4
            r3 = r0
            r4 = 0
            r3.mContent = r4
            r3 = r0
            r4 = 4
            r3.mEmptyVisibility = r4
            r3 = r0
            r4 = r2
            r3.init(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Placeholder.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Placeholder(android.content.Context r9, android.util.AttributeSet r10, int r11) {
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
            r5 = -1
            r4.mContentId = r5
            r4 = r0
            r5 = 0
            r4.mContent = r5
            r4 = r0
            r5 = 4
            r4.mEmptyVisibility = r5
            r4 = r0
            r5 = r2
            r4.init(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Placeholder.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Placeholder(android.content.Context r10, android.util.AttributeSet r11, int r12, int r13) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = -1
            r5.mContentId = r6
            r5 = r0
            r6 = 0
            r5.mContent = r6
            r5 = r0
            r6 = 4
            r5.mEmptyVisibility = r6
            r5 = r0
            r6 = r2
            r5.init(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.Placeholder.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    private void init(AttributeSet attributeSet) {
        AttributeSet attrs = attributeSet;
        super.setVisibility(this.mEmptyVisibility);
        this.mContentId = -1;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0025R.styleable.ConstraintLayout_placeholder);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == C0025R.styleable.ConstraintLayout_placeholder_content) {
                    this.mContentId = a.getResourceId(attr, this.mContentId);
                } else if (attr == C0025R.styleable.ConstraintLayout_placeholder_emptyVisibility) {
                    this.mEmptyVisibility = a.getInt(attr, this.mEmptyVisibility);
                }
            }
        }
    }

    public void setEmptyVisibility(int visibility) {
        int i = visibility;
        this.mEmptyVisibility = i;
    }

    public int getEmptyVisibility() {
        return this.mEmptyVisibility;
    }

    public View getContent() {
        return this.mContent;
    }

    public void onDraw(Canvas canvas) {
        Paint paint;
        Rect rect;
        Canvas canvas2 = canvas;
        if (isInEditMode()) {
            canvas2.drawRGB(223, 223, 223);
            new Paint();
            Paint paint2 = paint;
            paint2.setARGB(255, 210, 210, 210);
            paint2.setTextAlign(Paint.Align.CENTER);
            Typeface typeface = paint2.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            new Rect();
            Rect r = rect;
            boolean clipBounds = canvas2.getClipBounds(r);
            paint2.setTextSize((float) r.height());
            int cHeight = r.height();
            int cWidth = r.width();
            paint2.setTextAlign(Paint.Align.LEFT);
            String text = "?";
            paint2.getTextBounds(text, 0, text.length(), r);
            canvas2.drawText(text, ((((float) cWidth) / 2.0f) - (((float) r.width()) / 2.0f)) - ((float) r.left), ((((float) cHeight) / 2.0f) + (((float) r.height()) / 2.0f)) - ((float) r.bottom), paint2);
        }
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) {
        ConstraintLayout container = constraintLayout;
        if (this.mContentId == -1 && !isInEditMode()) {
            setVisibility(this.mEmptyVisibility);
        }
        this.mContent = container.findViewById(this.mContentId);
        if (this.mContent != null) {
            ((ConstraintLayout.LayoutParams) this.mContent.getLayoutParams()).isInPlaceholder = true;
            this.mContent.setVisibility(0);
            setVisibility(0);
        }
    }

    public void setContentId(int i) {
        View v;
        int id = i;
        if (this.mContentId != id) {
            if (this.mContent != null) {
                this.mContent.setVisibility(0);
                ((ConstraintLayout.LayoutParams) this.mContent.getLayoutParams()).isInPlaceholder = false;
                this.mContent = null;
            }
            this.mContentId = id;
            if (id != -1 && (v = ((View) getParent()).findViewById(id)) != null) {
                v.setVisibility(8);
            }
        }
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout) {
        ConstraintLayout constraintLayout2 = constraintLayout;
        if (this.mContent != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
            ConstraintLayout.LayoutParams layoutParamsContent = (ConstraintLayout.LayoutParams) this.mContent.getLayoutParams();
            layoutParamsContent.widget.setVisibility(0);
            layoutParams.widget.setWidth(layoutParamsContent.widget.getWidth());
            layoutParams.widget.setHeight(layoutParamsContent.widget.getHeight());
            layoutParamsContent.widget.setVisibility(8);
        }
    }
}
