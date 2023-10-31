package android.support.p000v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import gnu.expr.Declaration;

@Deprecated
/* renamed from: android.support.v4.widget.Space */
public class Space extends View {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Space(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas) {
    }

    private static int getDefaultSize2(int i, int i2) {
        int size = i;
        int measureSpec = i2;
        int result = size;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case Integer.MIN_VALUE:
                result = Math.min(size, specSize);
                break;
            case 0:
                result = size;
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                result = specSize;
                break;
        }
        return result;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize2(getSuggestedMinimumHeight(), heightMeasureSpec));
    }
}
