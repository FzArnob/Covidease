package android.support.p003v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.ColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.p000v4.graphics.drawable.WrappedDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* renamed from: android.support.v7.widget.AppCompatProgressBarHelper */
class AppCompatProgressBarHelper {
    private static final int[] TINT_ATTRS = {16843067, 16843068};
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    AppCompatProgressBarHelper(ProgressBar view) {
        this.mView = view;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, TINT_ATTRS, defStyleAttr, 0);
        Drawable drawable = a.getDrawableIfKnown(0);
        if (drawable != null) {
            this.mView.setIndeterminateDrawable(tileifyIndeterminate(drawable));
        }
        Drawable drawable2 = a.getDrawableIfKnown(1);
        if (drawable2 != null) {
            this.mView.setProgressDrawable(tileify(drawable2, false));
        }
        a.recycle();
    }

    private Drawable tileify(Drawable drawable, boolean z) {
        ShapeDrawable shapeDrawable;
        Shader shader;
        ShapeDrawable shapeDrawable2;
        ShapeDrawable shapeDrawable3;
        LayerDrawable layerDrawable;
        Drawable drawable2 = drawable;
        boolean clip = z;
        if (drawable2 instanceof WrappedDrawable) {
            Drawable inner = ((WrappedDrawable) drawable2).getWrappedDrawable();
            if (inner != null) {
                ((WrappedDrawable) drawable2).setWrappedDrawable(tileify(inner, clip));
            }
        } else if (drawable2 instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable) drawable2;
            int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];
            for (int i = 0; i < N; i++) {
                int id = background.getId(i);
                outDrawables[i] = tileify(background.getDrawable(i), id == 16908301 || id == 16908303);
            }
            new LayerDrawable(outDrawables);
            LayerDrawable newBg = layerDrawable;
            for (int i2 = 0; i2 < N; i2++) {
                newBg.setId(i2, background.getId(i2));
            }
            return newBg;
        } else if (drawable2 instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable2;
            Bitmap tileBitmap = bitmapDrawable.getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = tileBitmap;
            }
            new ShapeDrawable(getDrawableShape());
            ShapeDrawable shapeDrawable4 = shapeDrawable;
            new BitmapShader(tileBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            Shader shader2 = shapeDrawable4.getPaint().setShader(shader);
            ColorFilter colorFilter = shapeDrawable4.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            if (clip) {
                shapeDrawable2 = shapeDrawable3;
                new ClipDrawable(shapeDrawable4, 3, 1);
            } else {
                shapeDrawable2 = shapeDrawable4;
            }
            return shapeDrawable2;
        }
        return drawable2;
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = drawable;
        if (drawable3 instanceof AnimationDrawable) {
            AnimationDrawable background = (AnimationDrawable) drawable3;
            int N = background.getNumberOfFrames();
            new AnimationDrawable();
            Drawable newBg = drawable2;
            newBg.setOneShot(background.isOneShot());
            for (int i = 0; i < N; i++) {
                Drawable frame = tileify(background.getFrame(i), true);
                boolean level = frame.setLevel(10000);
                newBg.addFrame(frame, background.getDuration(i));
            }
            boolean level2 = newBg.setLevel(10000);
            drawable3 = newBg;
        }
        return drawable3;
    }

    private Shape getDrawableShape() {
        Shape shape;
        new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, (RectF) null, (float[]) null);
        return shape;
    }

    /* access modifiers changed from: package-private */
    public Bitmap getSampleTime() {
        return this.mSampleTile;
    }
}
