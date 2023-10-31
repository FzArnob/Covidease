package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0064R;
import android.support.p000v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.Log;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    @Nullable
    private Typeface font;
    @Nullable
    public final String fontFamily;
    @FontRes
    private final int fontFamilyResourceId;
    private boolean fontResolved = false;
    @Nullable
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    @Nullable
    public final ColorStateList textColor;
    @Nullable
    public final ColorStateList textColorHint;
    @Nullable
    public final ColorStateList textColorLink;
    public final float textSize;
    public final int textStyle;
    public final int typeface;

    static /* synthetic */ Typeface access$002(TextAppearance x0, Typeface x1) {
        Typeface typeface2 = x1;
        Typeface typeface3 = typeface2;
        x0.font = typeface3;
        return typeface2;
    }

    static /* synthetic */ boolean access$102(TextAppearance x0, boolean x1) {
        boolean z = x1;
        boolean z2 = z;
        x0.fontResolved = z2;
        return z;
    }

    public TextAppearance(Context context, @StyleRes int id) {
        Context context2 = context;
        TypedArray a = context2.obtainStyledAttributes(id, C0064R.styleable.TextAppearance);
        this.textSize = a.getDimension(C0064R.styleable.TextAppearance_android_textSize, 0.0f);
        this.textColor = MaterialResources.getColorStateList(context2, a, C0064R.styleable.TextAppearance_android_textColor);
        this.textColorHint = MaterialResources.getColorStateList(context2, a, C0064R.styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context2, a, C0064R.styleable.TextAppearance_android_textColorLink);
        this.textStyle = a.getInt(C0064R.styleable.TextAppearance_android_textStyle, 0);
        this.typeface = a.getInt(C0064R.styleable.TextAppearance_android_typeface, 1);
        int fontFamilyIndex = MaterialResources.getIndexWithValue(a, C0064R.styleable.TextAppearance_fontFamily, C0064R.styleable.TextAppearance_android_fontFamily);
        this.fontFamilyResourceId = a.getResourceId(fontFamilyIndex, 0);
        this.fontFamily = a.getString(fontFamilyIndex);
        this.textAllCaps = a.getBoolean(C0064R.styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context2, a, C0064R.styleable.TextAppearance_android_shadowColor);
        this.shadowDx = a.getFloat(C0064R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.shadowDy = a.getFloat(C0064R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.shadowRadius = a.getFloat(C0064R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
    }

    @VisibleForTesting
    @NonNull
    public Typeface getFont(Context context) {
        StringBuilder sb;
        Context context2 = context;
        if (this.fontResolved) {
            return this.font;
        }
        if (!context2.isRestricted()) {
            try {
                this.font = ResourcesCompat.getFont(context2, this.fontFamilyResourceId);
                if (this.font != null) {
                    this.font = Typeface.create(this.font, this.textStyle);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException e) {
                Object obj = e;
            } catch (Exception e2) {
                new StringBuilder();
                int d = Log.d(TAG, sb.append("Error loading font ").append(this.fontFamily).toString(), e2);
            }
        }
        createFallbackTypeface();
        this.fontResolved = true;
        return this.font;
    }

    public void getFontAsync(Context context, TextPaint textPaint, @NonNull ResourcesCompat.FontCallback fontCallback) {
        StringBuilder sb;
        ResourcesCompat.FontCallback fontCallback2;
        Context context2 = context;
        TextPaint textPaint2 = textPaint;
        ResourcesCompat.FontCallback callback = fontCallback;
        if (this.fontResolved) {
            updateTextPaintMeasureState(textPaint2, this.font);
            return;
        }
        createFallbackTypeface();
        if (context2.isRestricted()) {
            this.fontResolved = true;
            updateTextPaintMeasureState(textPaint2, this.font);
            return;
        }
        Context context3 = context2;
        try {
            final TextPaint textPaint3 = textPaint2;
            final ResourcesCompat.FontCallback fontCallback3 = callback;
            new ResourcesCompat.FontCallback(this) {
                final /* synthetic */ TextAppearance this$0;

                {
                    this.this$0 = this$0;
                }

                public void onFontRetrieved(@NonNull Typeface typeface) {
                    Typeface typeface2 = typeface;
                    Typeface access$002 = TextAppearance.access$002(this.this$0, Typeface.create(typeface2, this.this$0.textStyle));
                    this.this$0.updateTextPaintMeasureState(textPaint3, typeface2);
                    boolean access$102 = TextAppearance.access$102(this.this$0, true);
                    fontCallback3.onFontRetrieved(typeface2);
                }

                public void onFontRetrievalFailed(int reason) {
                    this.this$0.createFallbackTypeface();
                    boolean access$102 = TextAppearance.access$102(this.this$0, true);
                    fontCallback3.onFontRetrievalFailed(reason);
                }
            };
            ResourcesCompat.getFont(context3, this.fontFamilyResourceId, fontCallback2, (Handler) null);
        } catch (Resources.NotFoundException | UnsupportedOperationException e) {
            Object obj = e;
        } catch (Exception e2) {
            new StringBuilder();
            int d = Log.d(TAG, sb.append("Error loading font ").append(this.fontFamily).toString(), e2);
        }
    }

    /* access modifiers changed from: private */
    public void createFallbackTypeface() {
        if (this.font == null) {
            this.font = Typeface.create(this.fontFamily, this.textStyle);
        }
        if (this.font == null) {
            switch (this.typeface) {
                case 1:
                    this.font = Typeface.SANS_SERIF;
                    break;
                case 2:
                    this.font = Typeface.SERIF;
                    break;
                case 3:
                    this.font = Typeface.MONOSPACE;
                    break;
                default:
                    this.font = Typeface.DEFAULT;
                    break;
            }
            if (this.font != null) {
                this.font = Typeface.create(this.font, this.textStyle);
            }
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, ResourcesCompat.FontCallback callback) {
        int i;
        TextPaint textPaint2 = textPaint;
        updateMeasureState(context, textPaint2, callback);
        textPaint2.setColor(this.textColor != null ? this.textColor.getColorForState(textPaint2.drawableState, this.textColor.getDefaultColor()) : -16777216);
        TextPaint textPaint3 = textPaint2;
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        if (this.shadowColor != null) {
            i = this.shadowColor.getColorForState(textPaint2.drawableState, this.shadowColor.getDefaultColor());
        } else {
            i = 0;
        }
        textPaint3.setShadowLayer(f, f2, f3, i);
    }

    public void updateMeasureState(Context context, TextPaint textPaint, @Nullable ResourcesCompat.FontCallback fontCallback) {
        Context context2 = context;
        TextPaint textPaint2 = textPaint;
        ResourcesCompat.FontCallback callback = fontCallback;
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            updateTextPaintMeasureState(textPaint2, getFont(context2));
            return;
        }
        getFontAsync(context2, textPaint2, callback);
        if (!this.fontResolved) {
            updateTextPaintMeasureState(textPaint2, this.font);
        }
    }

    public void updateTextPaintMeasureState(@NonNull TextPaint textPaint, @NonNull Typeface typeface2) {
        TextPaint textPaint2 = textPaint;
        Typeface typeface3 = typeface2;
        Typeface typeface4 = textPaint2.setTypeface(typeface3);
        int fake = this.textStyle & (typeface3.getStyle() ^ -1);
        textPaint2.setFakeBoldText((fake & 1) != 0);
        textPaint2.setTextSkewX((fake & 2) != 0 ? -0.25f : 0.0f);
        textPaint2.setTextSize(this.textSize);
    }
}
