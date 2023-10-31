package android.support.p000v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.Preconditions;
import android.util.TypedValue;

/* renamed from: android.support.v4.content.res.ResourcesCompat */
public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        Resources res = resources;
        int id = i;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme2);
        }
        return res.getDrawable(id);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i, int i2, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        Resources res = resources;
        int id = i;
        int density = i2;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme2);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        Resources res = resources;
        int id = i;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme2);
        }
        return res.getColor(id);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        Resources res = resources;
        int id = i;
        Resources.Theme theme2 = theme;
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme2);
        }
        return res.getColorStateList(id);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i) throws Resources.NotFoundException {
        TypedValue typedValue;
        Context context2 = context;
        int id = i;
        if (context2.isRestricted()) {
            return null;
        }
        new TypedValue();
        return loadFont(context2, id, typedValue, 0, (FontCallback) null, (Handler) null, false);
    }

    /* renamed from: android.support.v4.content.res.ResourcesCompat$FontCallback */
    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);

        public FontCallback() {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackSuccessAsync(Typeface typeface, @Nullable Handler handler) {
            Runnable runnable;
            Handler handler2;
            Typeface typeface2 = typeface;
            Handler handler3 = handler;
            if (handler3 == null) {
                new Handler(Looper.getMainLooper());
                handler3 = handler2;
            }
            final Typeface typeface3 = typeface2;
            new Runnable(this) {
                final /* synthetic */ FontCallback this$0;

                {
                    this.this$0 = this$0;
                }

                public void run() {
                    this.this$0.onFontRetrieved(typeface3);
                }
            };
            boolean post = handler3.post(runnable);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackFailAsync(int i, @Nullable Handler handler) {
            Runnable runnable;
            Handler handler2;
            int reason = i;
            Handler handler3 = handler;
            if (handler3 == null) {
                new Handler(Looper.getMainLooper());
                handler3 = handler2;
            }
            final int i2 = reason;
            new Runnable(this) {
                final /* synthetic */ FontCallback this$0;

                {
                    this.this$0 = this$0;
                }

                public void run() {
                    this.this$0.onFontRetrievalFailed(i2);
                }
            };
            boolean post = handler3.post(runnable);
        }
    }

    public static void getFont(@NonNull Context context, @FontRes int i, @NonNull FontCallback fontCallback, @Nullable Handler handler) throws Resources.NotFoundException {
        TypedValue typedValue;
        Context context2 = context;
        int id = i;
        FontCallback fontCallback2 = fontCallback;
        Handler handler2 = handler;
        Object checkNotNull = Preconditions.checkNotNull(fontCallback2);
        if (context2.isRestricted()) {
            fontCallback2.callbackFailAsync(-4, handler2);
            return;
        }
        new TypedValue();
        Typeface loadFont = loadFont(context2, id, typedValue, 0, fontCallback2, handler2, false);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context, @FontRes int i, TypedValue typedValue, int i2, @Nullable FontCallback fontCallback) throws Resources.NotFoundException {
        Context context2 = context;
        int id = i;
        TypedValue value = typedValue;
        int style = i2;
        FontCallback fontCallback2 = fontCallback;
        if (context2.isRestricted()) {
            return null;
        }
        return loadFont(context2, id, value, style, fontCallback2, (Handler) null, true);
    }

    private static Typeface loadFont(@NonNull Context context, int i, TypedValue typedValue, int style, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean isRequestFromLayoutInflator) {
        Throwable th;
        StringBuilder sb;
        Context context2 = context;
        int id = i;
        TypedValue value = typedValue;
        FontCallback fontCallback2 = fontCallback;
        Resources resources = context2.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context2, resources, value, id, style, fontCallback2, handler, isRequestFromLayoutInflator);
        if (typeface != null || fontCallback2 != null) {
            return typeface;
        }
        Throwable th2 = th;
        new StringBuilder();
        new Resources.NotFoundException(sb.append("Font resource ID #0x").append(Integer.toHexString(id)).append(" could not be retrieved.").toString());
        throw th2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0123  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(@android.support.annotation.NonNull android.content.Context r21, android.content.res.Resources r22, android.util.TypedValue r23, int r24, int r25, @android.support.annotation.Nullable android.support.p000v4.content.res.ResourcesCompat.FontCallback r26, @android.support.annotation.Nullable android.os.Handler r27, boolean r28) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r27
            r7 = r28
            r12 = r2
            java.lang.CharSequence r12 = r12.string
            if (r12 != 0) goto L_0x005e
            android.content.res.Resources$NotFoundException r12 = new android.content.res.Resources$NotFoundException
            r20 = r12
            r12 = r20
            r13 = r20
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r20 = r14
            r14 = r20
            r15 = r20
            r15.<init>()
            java.lang.String r15 = "Resource \""
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r1
            r16 = r3
            java.lang.String r15 = r15.getResourceName(r16)
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = "\" ("
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r3
            java.lang.String r15 = java.lang.Integer.toHexString(r15)
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ") is not a Font: "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r12
        L_0x005e:
            r12 = r2
            java.lang.CharSequence r12 = r12.string
            java.lang.String r12 = r12.toString()
            r8 = r12
            r12 = r8
            java.lang.String r13 = "res/"
            boolean r12 = r12.startsWith(r13)
            if (r12 != 0) goto L_0x007c
            r12 = r5
            if (r12 == 0) goto L_0x0079
            r12 = r5
            r13 = -3
            r14 = r6
            r12.callbackFailAsync(r13, r14)
        L_0x0079:
            r12 = 0
            r0 = r12
        L_0x007b:
            return r0
        L_0x007c:
            r12 = r1
            r13 = r3
            r14 = r4
            android.graphics.Typeface r12 = android.support.p000v4.graphics.TypefaceCompat.findFromCache(r12, r13, r14)
            r9 = r12
            r12 = r9
            if (r12 == 0) goto L_0x0093
            r12 = r5
            if (r12 == 0) goto L_0x0090
            r12 = r5
            r13 = r9
            r14 = r6
            r12.callbackSuccessAsync(r13, r14)
        L_0x0090:
            r12 = r9
            r0 = r12
            goto L_0x007b
        L_0x0093:
            r12 = r8
            java.lang.String r12 = r12.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            java.lang.String r13 = ".xml"
            boolean r12 = r12.endsWith(r13)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            if (r12 == 0) goto L_0x00da
            r12 = r1
            r13 = r3
            android.content.res.XmlResourceParser r12 = r12.getXml(r13)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            r10 = r12
            r12 = r10
            r13 = r1
            android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry r12 = android.support.p000v4.content.res.FontResourcesParserCompat.parse(r12, r13)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            r11 = r12
            r12 = r11
            if (r12 != 0) goto L_0x00c8
            java.lang.String r12 = "ResourcesCompat"
            java.lang.String r13 = "Failed to find font-family tag"
            int r12 = android.util.Log.e(r12, r13)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            r12 = r5
            if (r12 == 0) goto L_0x00c5
            r12 = r5
            r13 = -3
            r14 = r6
            r12.callbackFailAsync(r13, r14)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
        L_0x00c5:
            r12 = 0
            r0 = r12
            goto L_0x007b
        L_0x00c8:
            r12 = r0
            r13 = r11
            r14 = r1
            r15 = r3
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r7
            android.graphics.Typeface r12 = android.support.p000v4.graphics.TypefaceCompat.createFromResourcesFamilyXml(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            r0 = r12
            goto L_0x007b
        L_0x00da:
            r12 = r0
            r13 = r1
            r14 = r3
            r15 = r8
            r16 = r4
            android.graphics.Typeface r12 = android.support.p000v4.graphics.TypefaceCompat.createFromResourcesFontFile(r12, r13, r14, r15, r16)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            r9 = r12
            r12 = r5
            if (r12 == 0) goto L_0x00f1
            r12 = r9
            if (r12 == 0) goto L_0x00f4
            r12 = r5
            r13 = r9
            r14 = r6
            r12.callbackSuccessAsync(r13, r14)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
        L_0x00f1:
            r12 = r9
            r0 = r12
            goto L_0x007b
        L_0x00f4:
            r12 = r5
            r13 = -3
            r14 = r6
            r12.callbackFailAsync(r13, r14)     // Catch:{ XmlPullParserException -> 0x00fb, IOException -> 0x012d }
            goto L_0x00f1
        L_0x00fb:
            r12 = move-exception
            r10 = r12
            java.lang.String r12 = "ResourcesCompat"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r20 = r13
            r13 = r20
            r14 = r20
            r14.<init>()
            java.lang.String r14 = "Failed to parse xml resource "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r8
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r14 = r10
            int r12 = android.util.Log.e(r12, r13, r14)
        L_0x0120:
            r12 = r5
            if (r12 == 0) goto L_0x0129
            r12 = r5
            r13 = -3
            r14 = r6
            r12.callbackFailAsync(r13, r14)
        L_0x0129:
            r12 = 0
            r0 = r12
            goto L_0x007b
        L_0x012d:
            r12 = move-exception
            r10 = r12
            java.lang.String r12 = "ResourcesCompat"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r20 = r13
            r13 = r20
            r14 = r20
            r14.<init>()
            java.lang.String r14 = "Failed to read xml resource "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r8
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r14 = r10
            int r12 = android.util.Log.e(r12, r13, r14)
            goto L_0x0120
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    private ResourcesCompat() {
    }
}
