package android.support.p000v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.util.Preconditions;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import org.shaded.apache.http.protocol.HTTP;

/* renamed from: android.support.v4.graphics.drawable.IconCompat */
public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public byte[] mData;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt2;
    Object mObj1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Parcelable mParcelable;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ColorStateList mTintList = null;
    PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String mTintModeStr;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mType;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.graphics.drawable.IconCompat$IconType */
    public @interface IconType {
    }

    public static IconCompat createWithResource(Context context, @DrawableRes int i) {
        Throwable th;
        Context context2 = context;
        int resId = i;
        if (context2 != null) {
            return createWithResource(context2.getResources(), context2.getPackageName(), resId);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Context must not be null.");
        throw th2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static IconCompat createWithResource(Resources resources, String str, @DrawableRes int i) {
        IconCompat iconCompat;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Resources r = resources;
        String pkg = str;
        int resId = i;
        if (pkg == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Package must not be null.");
            throw th4;
        } else if (resId == 0) {
            Throwable th5 = th2;
            new IllegalArgumentException("Drawable resource ID must not be 0");
            throw th5;
        } else {
            new IconCompat(2);
            IconCompat rep = iconCompat;
            rep.mInt1 = resId;
            if (r != null) {
                try {
                    rep.mObj1 = r.getResourceName(resId);
                } catch (Resources.NotFoundException e) {
                    Resources.NotFoundException notFoundException = e;
                    Throwable th6 = th;
                    new IllegalArgumentException("Icon resource cannot be found");
                    throw th6;
                }
            } else {
                rep.mObj1 = pkg;
            }
            return rep;
        }
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        IconCompat iconCompat;
        Throwable th;
        Bitmap bits = bitmap;
        if (bits == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Bitmap must not be null.");
            throw th2;
        }
        new IconCompat(1);
        IconCompat rep = iconCompat;
        rep.mObj1 = bits;
        return rep;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        IconCompat iconCompat;
        Throwable th;
        Bitmap bits = bitmap;
        if (bits == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Bitmap must not be null.");
            throw th2;
        }
        new IconCompat(5);
        IconCompat rep = iconCompat;
        rep.mObj1 = bits;
        return rep;
    }

    public static IconCompat createWithData(byte[] bArr, int i, int i2) {
        IconCompat iconCompat;
        Throwable th;
        byte[] data = bArr;
        int offset = i;
        int length = i2;
        if (data == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Data must not be null.");
            throw th2;
        }
        new IconCompat(3);
        IconCompat rep = iconCompat;
        rep.mObj1 = data;
        rep.mInt1 = offset;
        rep.mInt2 = length;
        return rep;
    }

    public static IconCompat createWithContentUri(String str) {
        IconCompat iconCompat;
        Throwable th;
        String uri = str;
        if (uri == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Uri must not be null.");
            throw th2;
        }
        new IconCompat(4);
        IconCompat rep = iconCompat;
        rep.mObj1 = uri;
        return rep;
    }

    public static IconCompat createWithContentUri(Uri uri) {
        Throwable th;
        Uri uri2 = uri;
        if (uri2 != null) {
            return createWithContentUri(uri2.toString());
        }
        Throwable th2 = th;
        new IllegalArgumentException("Uri must not be null.");
        throw th2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public IconCompat() {
    }

    private IconCompat(int mType2) {
        this.mType = mType2;
    }

    public int getType() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return this.mType;
        }
        return getType((Icon) this.mObj1);
    }

    @NonNull
    public String getResPackage() {
        Throwable th;
        StringBuilder sb;
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return ((String) this.mObj1).split(":", -1)[0];
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("called getResPackage() on ").append(this).toString());
        throw th2;
    }

    @IdRes
    public int getResId() {
        Throwable th;
        StringBuilder sb;
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return this.mInt1;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("called getResId() on ").append(this).toString());
        throw th2;
    }

    @NonNull
    public Uri getUri() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return Uri.parse((String) this.mObj1);
        }
        return getUri((Icon) this.mObj1);
    }

    public IconCompat setTint(@ColorInt int tint) {
        return setTintList(ColorStateList.valueOf(tint));
    }

    public IconCompat setTintList(ColorStateList tintList) {
        this.mTintList = tintList;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        return this;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        Icon icon;
        Throwable th;
        switch (this.mType) {
            case -1:
                return (Icon) this.mObj1;
            case 1:
                icon = Icon.createWithBitmap((Bitmap) this.mObj1);
                break;
            case 2:
                icon = Icon.createWithResource(getResPackage(), this.mInt1);
                break;
            case 3:
                icon = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
                break;
            case 4:
                icon = Icon.createWithContentUri((String) this.mObj1);
                break;
            case 5:
                if (Build.VERSION.SDK_INT < 26) {
                    icon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
                    break;
                } else {
                    icon = Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
                    break;
                }
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Unknown type");
                throw th2;
        }
        if (this.mTintList != null) {
            Icon tintList = icon.setTintList(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            Icon tintMode = icon.setTintMode(this.mTintMode);
        }
        return icon;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void checkResource(Context context) {
        StringBuilder sb;
        Context context2 = context;
        if (this.mType == 2) {
            String resPackage = (String) this.mObj1;
            if (resPackage.contains(":")) {
                String resName = resPackage.split(":", -1)[1];
                String resType = resName.split("/", -1)[0];
                String resName2 = resName.split("/", -1)[1];
                String resPackage2 = resPackage.split(":", -1)[0];
                int id = getResources(context2, resPackage2).getIdentifier(resName2, resType, resPackage2);
                if (this.mInt1 != id) {
                    new StringBuilder();
                    int i = Log.i(TAG, sb.append("Id has changed for ").append(resPackage2).append("/").append(resName2).toString());
                    this.mInt1 = id;
                }
            }
        }
    }

    public Drawable loadDrawable(Context context) {
        Context context2 = context;
        checkResource(context2);
        if (Build.VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context2);
        }
        Drawable result = loadDrawableInner(context2);
        if (!(result == null || (this.mTintList == null && this.mTintMode == DEFAULT_TINT_MODE))) {
            Drawable mutate = result.mutate();
            DrawableCompat.setTintList(result, this.mTintList);
            DrawableCompat.setTintMode(result, this.mTintMode);
        }
        return result;
    }

    private Drawable loadDrawableInner(Context context) {
        Drawable drawable;
        StringBuilder sb;
        StringBuilder sb2;
        InputStream inputStream;
        File file;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Context context2 = context;
        switch (this.mType) {
            case 1:
                new BitmapDrawable(context2.getResources(), (Bitmap) this.mObj1);
                return drawable4;
            case 2:
                String resPackage = getResPackage();
                if (TextUtils.isEmpty(resPackage)) {
                    resPackage = context2.getPackageName();
                }
                try {
                    return ResourcesCompat.getDrawable(getResources(context2, resPackage), this.mInt1, context2.getTheme());
                } catch (RuntimeException e) {
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(this.mInt1);
                    Object[] objArr2 = objArr;
                    objArr2[1] = this.mObj1;
                    int e2 = Log.e(TAG, String.format("Unable to load resource 0x%08x from pkg=%s", objArr2), e);
                    break;
                }
            case 3:
                new BitmapDrawable(context2.getResources(), BitmapFactory.decodeByteArray((byte[]) this.mObj1, this.mInt1, this.mInt2));
                return drawable2;
            case 4:
                Uri uri = Uri.parse((String) this.mObj1);
                String scheme = uri.getScheme();
                InputStream is = null;
                if ("content".equals(scheme) || "file".equals(scheme)) {
                    try {
                        is = context2.getContentResolver().openInputStream(uri);
                    } catch (Exception e3) {
                        new StringBuilder();
                        int w = Log.w(TAG, sb.append("Unable to load image from URI: ").append(uri).toString(), e3);
                    }
                } else {
                    try {
                        InputStream inputStream2 = inputStream;
                        new File((String) this.mObj1);
                        new FileInputStream(file);
                        is = inputStream2;
                    } catch (FileNotFoundException e4) {
                        new StringBuilder();
                        int w2 = Log.w(TAG, sb2.append("Unable to load image from path: ").append(uri).toString(), e4);
                    }
                }
                if (is != null) {
                    new BitmapDrawable(context2.getResources(), BitmapFactory.decodeStream(is));
                    return drawable;
                }
                break;
            case 5:
                new BitmapDrawable(context2.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
                return drawable3;
        }
        return null;
    }

    private static Resources getResources(Context context, String str) {
        Context context2 = context;
        String resPackage = str;
        if ("android".equals(resPackage)) {
            return Resources.getSystem();
        }
        PackageManager pm = context2.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(resPackage, 8192);
            if (ai != null) {
                return pm.getResourcesForApplication(ai);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            int e2 = Log.e(TAG, String.format("Unable to find pkg=%s for icon", new Object[]{resPackage}), e);
            return null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent, @Nullable Drawable drawable, @NonNull Context context) {
        Throwable th;
        StringBuilder sb;
        Bitmap icon;
        Canvas canvas;
        Canvas canvas2;
        Throwable th2;
        Intent outIntent = intent;
        Drawable badge = drawable;
        Context c = context;
        checkResource(c);
        switch (this.mType) {
            case 1:
                icon = (Bitmap) this.mObj1;
                if (badge != null) {
                    icon = icon.copy(icon.getConfig(), true);
                    break;
                }
                break;
            case 2:
                try {
                    Context context2 = c.createPackageContext(getResPackage(), 0);
                    if (badge != null) {
                        Drawable dr = ContextCompat.getDrawable(context2, this.mInt1);
                        if (dr.getIntrinsicWidth() <= 0 || dr.getIntrinsicHeight() <= 0) {
                            int size = ((ActivityManager) context2.getSystemService("activity")).getLauncherLargeIconSize();
                            icon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                        } else {
                            icon = Bitmap.createBitmap(dr.getIntrinsicWidth(), dr.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        }
                        dr.setBounds(0, 0, icon.getWidth(), icon.getHeight());
                        new Canvas(icon);
                        dr.draw(canvas);
                        break;
                    } else {
                        Intent putExtra = outIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context2, this.mInt1));
                        return;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    PackageManager.NameNotFoundException e2 = e;
                    Throwable th3 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Can't find package ").append(this.mObj1).toString(), e2);
                    throw th3;
                }
                break;
            case 5:
                icon = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
                break;
            default:
                Throwable th4 = th2;
                new IllegalArgumentException("Icon type not supported for intent shortcuts");
                throw th4;
        }
        if (badge != null) {
            int w = icon.getWidth();
            int h = icon.getHeight();
            badge.setBounds(w / 2, h / 2, w, h);
            new Canvas(icon);
            badge.draw(canvas2);
        }
        Intent putExtra2 = outIntent.putExtra("android.intent.extra.shortcut.ICON", icon);
    }

    public Bundle toBundle() {
        Bundle bundle;
        Throwable th;
        new Bundle();
        Bundle bundle2 = bundle;
        switch (this.mType) {
            case -1:
                bundle2.putParcelable(EXTRA_OBJ, (Parcelable) this.mObj1);
                break;
            case 1:
            case 5:
                bundle2.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
                break;
            case 2:
            case 4:
                bundle2.putString(EXTRA_OBJ, (String) this.mObj1);
                break;
            case 3:
                bundle2.putByteArray(EXTRA_OBJ, (byte[]) this.mObj1);
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Invalid icon");
                throw th2;
        }
        bundle2.putInt(EXTRA_TYPE, this.mType);
        bundle2.putInt(EXTRA_INT1, this.mInt1);
        bundle2.putInt(EXTRA_INT2, this.mInt2);
        if (this.mTintList != null) {
            bundle2.putParcelable(EXTRA_TINT_LIST, this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            bundle2.putString(EXTRA_TINT_MODE, this.mTintMode.name());
        }
        return bundle2;
    }

    public String toString() {
        StringBuilder sb;
        if (this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        new StringBuilder("Icon(typ=");
        StringBuilder sb2 = sb.append(typeToString(this.mType));
        switch (this.mType) {
            case 1:
            case 5:
                StringBuilder append = sb2.append(" size=").append(((Bitmap) this.mObj1).getWidth()).append("x").append(((Bitmap) this.mObj1).getHeight());
                break;
            case 2:
                StringBuilder append2 = sb2.append(" pkg=").append(getResPackage()).append(" id=").append(String.format("0x%08x", new Object[]{Integer.valueOf(getResId())}));
                break;
            case 3:
                StringBuilder append3 = sb2.append(" len=").append(this.mInt1);
                if (this.mInt2 != 0) {
                    StringBuilder append4 = sb2.append(" off=").append(this.mInt2);
                    break;
                }
                break;
            case 4:
                StringBuilder append5 = sb2.append(" uri=").append(this.mObj1);
                break;
        }
        if (this.mTintList != null) {
            StringBuilder append6 = sb2.append(" tint=");
            StringBuilder append7 = sb2.append(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            StringBuilder append8 = sb2.append(" mode=").append(this.mTintMode);
        }
        StringBuilder append9 = sb2.append(")");
        return sb2.toString();
    }

    public void onPreParceling(boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        boolean isStream = z;
        this.mTintModeStr = this.mTintMode.name();
        switch (this.mType) {
            case -1:
                if (isStream) {
                    Throwable th2 = th;
                    new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                    throw th2;
                }
                this.mParcelable = (Parcelable) this.mObj1;
                return;
            case 1:
            case 5:
                if (isStream) {
                    Bitmap bitmap = (Bitmap) this.mObj1;
                    new ByteArrayOutputStream();
                    ByteArrayOutputStream data = byteArrayOutputStream;
                    boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, data);
                    this.mData = data.toByteArray();
                    return;
                }
                this.mParcelable = (Parcelable) this.mObj1;
                return;
            case 2:
                this.mData = ((String) this.mObj1).getBytes(Charset.forName(HTTP.UTF_16));
                return;
            case 3:
                this.mData = (byte[]) this.mObj1;
                return;
            case 4:
                this.mData = this.mObj1.toString().getBytes(Charset.forName(HTTP.UTF_16));
                return;
            default:
                return;
        }
    }

    public void onPostParceling() {
        Object obj;
        Throwable th;
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        switch (this.mType) {
            case -1:
                if (this.mParcelable != null) {
                    this.mObj1 = this.mParcelable;
                    return;
                }
                Throwable th2 = th;
                new IllegalArgumentException("Invalid icon");
                throw th2;
            case 1:
            case 5:
                if (this.mParcelable != null) {
                    this.mObj1 = this.mParcelable;
                    return;
                }
                this.mObj1 = this.mData;
                this.mType = 3;
                this.mInt1 = 0;
                this.mInt2 = this.mData.length;
                return;
            case 2:
            case 4:
                new String(this.mData, Charset.forName(HTTP.UTF_16));
                this.mObj1 = obj;
                return;
            case 3:
                this.mObj1 = this.mData;
                return;
            default:
                return;
        }
    }

    private static String typeToString(int x) {
        switch (x) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle) {
        IconCompat iconCompat;
        StringBuilder sb;
        Bundle bundle2 = bundle;
        int type = bundle2.getInt(EXTRA_TYPE);
        new IconCompat(type);
        IconCompat icon = iconCompat;
        icon.mInt1 = bundle2.getInt(EXTRA_INT1);
        icon.mInt2 = bundle2.getInt(EXTRA_INT2);
        if (bundle2.containsKey(EXTRA_TINT_LIST)) {
            icon.mTintList = (ColorStateList) bundle2.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle2.containsKey(EXTRA_TINT_MODE)) {
            icon.mTintMode = PorterDuff.Mode.valueOf(bundle2.getString(EXTRA_TINT_MODE));
        }
        switch (type) {
            case -1:
            case 1:
            case 5:
                icon.mObj1 = bundle2.getParcelable(EXTRA_OBJ);
                break;
            case 2:
            case 4:
                icon.mObj1 = bundle2.getString(EXTRA_OBJ);
                break;
            case 3:
                icon.mObj1 = bundle2.getByteArray(EXTRA_OBJ);
                break;
            default:
                new StringBuilder();
                int w = Log.w(TAG, sb.append("Unknown type ").append(type).toString());
                return null;
        }
        return icon;
    }

    @Nullable
    @RequiresApi(23)
    public static IconCompat createFromIcon(@NonNull Context context, @NonNull Icon icon) {
        Throwable th;
        IconCompat iconCompat;
        Context context2 = context;
        Icon icon2 = icon;
        Object checkNotNull = Preconditions.checkNotNull(icon2);
        switch (getType(icon2)) {
            case 2:
                String resPackage = getResPackage(icon2);
                try {
                    return createWithResource(getResources(context2, resPackage), resPackage, getResId(icon2));
                } catch (Resources.NotFoundException e) {
                    Resources.NotFoundException notFoundException = e;
                    Throwable th2 = th;
                    new IllegalArgumentException("Icon resource cannot be found");
                    throw th2;
                }
            case 4:
                return createWithContentUri(getUri(icon2));
            default:
                new IconCompat(-1);
                IconCompat iconCompat2 = iconCompat;
                iconCompat2.mObj1 = icon2;
                return iconCompat2;
        }
    }

    @Nullable
    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static IconCompat createFromIcon(@NonNull Icon icon) {
        IconCompat iconCompat;
        Icon icon2 = icon;
        Object checkNotNull = Preconditions.checkNotNull(icon2);
        switch (getType(icon2)) {
            case 2:
                return createWithResource((Resources) null, getResPackage(icon2), getResId(icon2));
            case 4:
                return createWithContentUri(getUri(icon2));
            default:
                new IconCompat(-1);
                IconCompat iconCompat2 = iconCompat;
                iconCompat2.mObj1 = icon2;
                return iconCompat2;
        }
    }

    @RequiresApi(23)
    private static int getType(@NonNull Icon icon) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Icon icon2 = icon;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon2.getType();
        }
        try {
            return ((Integer) icon2.getClass().getMethod("getType", new Class[0]).invoke(icon2, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            new StringBuilder();
            int e2 = Log.e(TAG, sb3.append("Unable to get icon type ").append(icon2).toString(), e);
            return -1;
        } catch (InvocationTargetException e3) {
            new StringBuilder();
            int e4 = Log.e(TAG, sb2.append("Unable to get icon type ").append(icon2).toString(), e3);
            return -1;
        } catch (NoSuchMethodException e5) {
            new StringBuilder();
            int e6 = Log.e(TAG, sb.append("Unable to get icon type ").append(icon2).toString(), e5);
            return -1;
        }
    }

    @Nullable
    @RequiresApi(23)
    private static String getResPackage(@NonNull Icon icon) {
        Icon icon2 = icon;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon2.getResPackage();
        }
        try {
            return (String) icon2.getClass().getMethod("getResPackage", new Class[0]).invoke(icon2, new Object[0]);
        } catch (IllegalAccessException e) {
            int e2 = Log.e(TAG, "Unable to get icon package", e);
            return null;
        } catch (InvocationTargetException e3) {
            int e4 = Log.e(TAG, "Unable to get icon package", e3);
            return null;
        } catch (NoSuchMethodException e5) {
            int e6 = Log.e(TAG, "Unable to get icon package", e5);
            return null;
        }
    }

    @RequiresApi(23)
    @DrawableRes
    @IdRes
    private static int getResId(@NonNull Icon icon) {
        Icon icon2 = icon;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon2.getResId();
        }
        try {
            return ((Integer) icon2.getClass().getMethod("getResId", new Class[0]).invoke(icon2, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            int e2 = Log.e(TAG, "Unable to get icon resource", e);
            return 0;
        } catch (InvocationTargetException e3) {
            int e4 = Log.e(TAG, "Unable to get icon resource", e3);
            return 0;
        } catch (NoSuchMethodException e5) {
            int e6 = Log.e(TAG, "Unable to get icon resource", e5);
            return 0;
        }
    }

    @Nullable
    @RequiresApi(23)
    private static Uri getUri(@NonNull Icon icon) {
        Icon icon2 = icon;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon2.getUri();
        }
        try {
            return (Uri) icon2.getClass().getMethod("getUri", new Class[0]).invoke(icon2, new Object[0]);
        } catch (IllegalAccessException e) {
            int e2 = Log.e(TAG, "Unable to get icon uri", e);
            return null;
        } catch (InvocationTargetException e3) {
            int e4 = Log.e(TAG, "Unable to get icon uri", e3);
            return null;
        } catch (NoSuchMethodException e5) {
            int e6 = Log.e(TAG, "Unable to get icon uri", e5);
            return null;
        }
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean addShadow) {
        Canvas canvas;
        Paint paint;
        BitmapShader bitmapShader;
        Matrix matrix;
        Bitmap adaptiveIconBitmap = bitmap;
        int size = (int) (DEFAULT_VIEW_PORT_SCALE * ((float) Math.min(adaptiveIconBitmap.getWidth(), adaptiveIconBitmap.getHeight())));
        Bitmap icon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        new Canvas(icon);
        Canvas canvas2 = canvas;
        new Paint(3);
        Paint paint2 = paint;
        float center = ((float) size) * 0.5f;
        float radius = center * ICON_DIAMETER_FACTOR;
        if (addShadow) {
            float blur = BLUR_FACTOR * ((float) size);
            paint2.setColor(0);
            paint2.setShadowLayer(blur, 0.0f, KEY_SHADOW_OFFSET_FACTOR * ((float) size), 1023410176);
            canvas2.drawCircle(center, center, radius, paint2);
            paint2.setShadowLayer(blur, 0.0f, 0.0f, 503316480);
            canvas2.drawCircle(center, center, radius, paint2);
            paint2.clearShadowLayer();
        }
        paint2.setColor(-16777216);
        new BitmapShader(adaptiveIconBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BitmapShader shader = bitmapShader;
        new Matrix();
        Matrix shift = matrix;
        shift.setTranslate((float) ((-(adaptiveIconBitmap.getWidth() - size)) / 2), (float) ((-(adaptiveIconBitmap.getHeight() - size)) / 2));
        shader.setLocalMatrix(shift);
        Shader shader2 = paint2.setShader(shader);
        canvas2.drawCircle(center, center, radius, paint2);
        canvas2.setBitmap((Bitmap) null);
        return icon;
    }
}
