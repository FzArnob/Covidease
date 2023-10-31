package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.provider.FontsContractCompat;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatApi26Impl */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        StringBuilder sb;
        Class fontFamily;
        Constructor fontFamilyCtor;
        Method addFontFromAssetManager;
        Method addFontFromBuffer;
        Method freeze;
        Method abortCreation;
        Method createFromFamiliesWithDefault;
        try {
            fontFamily = obtainFontFamily();
            fontFamilyCtor = obtainFontFamilyCtor(fontFamily);
            addFontFromAssetManager = obtainAddFontFromAssetManagerMethod(fontFamily);
            addFontFromBuffer = obtainAddFontFromBufferMethod(fontFamily);
            freeze = obtainFreezeMethod(fontFamily);
            abortCreation = obtainAbortCreationMethod(fontFamily);
            createFromFamiliesWithDefault = obtainCreateFromFamiliesWithDefaultMethod(fontFamily);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            ReflectiveOperationException e2 = e;
            new StringBuilder();
            int e3 = Log.e(TAG, sb.append("Unable to collect necessary methods for class ").append(e2.getClass().getName()).toString(), e2);
            fontFamily = null;
            fontFamilyCtor = null;
            addFontFromAssetManager = null;
            addFontFromBuffer = null;
            freeze = null;
            abortCreation = null;
            createFromFamiliesWithDefault = null;
        }
        this.mFontFamily = fontFamily;
        this.mFontFamilyCtor = fontFamilyCtor;
        this.mAddFontFromAssetManager = addFontFromAssetManager;
        this.mAddFontFromBuffer = addFontFromBuffer;
        this.mFreeze = freeze;
        this.mAbortCreation = abortCreation;
        this.mCreateFromFamiliesWithDefault = createFromFamiliesWithDefault;
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            int w = Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    private Object newFamily() {
        Throwable th;
        try {
            return this.mFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3, @Nullable FontVariationAxis[] fontVariationAxisArr) {
        Throwable th;
        Object family = obj;
        String fileName = str;
        int ttcIndex = i;
        int weight = i2;
        int style = i3;
        FontVariationAxis[] axes = fontVariationAxisArr;
        try {
            Object[] objArr = new Object[8];
            objArr[0] = context.getAssets();
            Object[] objArr2 = objArr;
            objArr2[1] = fileName;
            Object[] objArr3 = objArr2;
            objArr3[2] = 0;
            Object[] objArr4 = objArr3;
            objArr4[3] = false;
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(ttcIndex);
            Object[] objArr6 = objArr5;
            objArr6[5] = Integer.valueOf(weight);
            Object[] objArr7 = objArr6;
            objArr7[6] = Integer.valueOf(style);
            Object[] objArr8 = objArr7;
            objArr8[7] = axes;
            return ((Boolean) this.mAddFontFromAssetManager.invoke(family, objArr8)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private boolean addFontFromBuffer(Object obj, ByteBuffer buffer, int i, int i2, int i3) {
        Throwable th;
        Object family = obj;
        int ttcIndex = i;
        int weight = i2;
        int style = i3;
        try {
            Object[] objArr = new Object[5];
            objArr[0] = buffer;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(ttcIndex);
            Object[] objArr3 = objArr2;
            objArr3[2] = null;
            Object[] objArr4 = objArr3;
            objArr4[3] = Integer.valueOf(weight);
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(style);
            return ((Boolean) this.mAddFontFromBuffer.invoke(family, objArr5)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public Typeface createFromFamiliesWithDefault(Object obj) {
        Throwable th;
        Object family = obj;
        try {
            Object familyArray = Array.newInstance(this.mFontFamily, 1);
            Array.set(familyArray, 0, family);
            Method method = this.mCreateFromFamiliesWithDefault;
            Object[] objArr = new Object[3];
            objArr[0] = familyArray;
            Object[] objArr2 = objArr;
            objArr2[1] = -1;
            Object[] objArr3 = objArr2;
            objArr3[2] = -1;
            return (Typeface) method.invoke((Object) null, objArr3);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private boolean freeze(Object family) {
        Throwable th;
        try {
            return ((Boolean) this.mFreeze.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private void abortCreation(Object family) {
        Throwable th;
        try {
            Object invoke = this.mAbortCreation.invoke(family, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        Context context2 = context;
        FontResourcesParserCompat.FontFamilyFilesResourceEntry entry = fontFamilyFilesResourceEntry;
        Resources resources2 = resources;
        int style = i;
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context2, entry, resources2, style);
        }
        Object fontFamily = newFamily();
        FontResourcesParserCompat.FontFileResourceEntry[] entries = entry.getEntries();
        int length = entries.length;
        for (int i2 = 0; i2 < length; i2++) {
            FontResourcesParserCompat.FontFileResourceEntry fontFile = entries[i2];
            if (!addFontFromAssetManager(context2, fontFamily, fontFile.getFileName(), fontFile.getTtcIndex(), fontFile.getWeight(), fontFile.isItalic() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontFile.getVariationSettings()))) {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (!freeze(fontFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(fontFamily);
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        Throwable th;
        Typeface.Builder builder;
        Context context2 = context;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        FontsContractCompat.FontInfo[] fonts = fontInfoArr;
        int style = i;
        if (fonts.length < 1) {
            return null;
        }
        if (!isFontFamilyPrivateAPIAvailable()) {
            FontsContractCompat.FontInfo bestFont = findBestInfo(fonts, style);
            try {
                ParcelFileDescriptor pfd = context2.getContentResolver().openFileDescriptor(bestFont.getUri(), "r", cancellationSignal2);
                if (pfd == null) {
                    if (pfd != null) {
                        if (0 != 0) {
                            try {
                                pfd.close();
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                Throwable th4 = null;
                                th4.addSuppressed(th3);
                            }
                        } else {
                            pfd.close();
                        }
                    }
                    return null;
                }
                try {
                    new Typeface.Builder(pfd.getFileDescriptor());
                    Typeface build = builder.setWeight(bestFont.getWeight()).setItalic(bestFont.isItalic()).build();
                    if (pfd != null) {
                        if (0 != 0) {
                            try {
                                pfd.close();
                            } catch (Throwable th5) {
                                Throwable th6 = th5;
                                Throwable th7 = null;
                                th7.addSuppressed(th6);
                            }
                        } else {
                            pfd.close();
                        }
                    }
                    return build;
                } catch (Throwable th8) {
                    Throwable th9 = th8;
                    if (pfd != null) {
                        if (th != null) {
                            pfd.close();
                        } else {
                            pfd.close();
                        }
                    }
                    throw th9;
                }
            } catch (IOException e) {
                IOException iOException = e;
                return null;
            } catch (Throwable th10) {
                th.addSuppressed(th10);
            }
        } else {
            Map<Uri, ByteBuffer> uriBuffer = FontsContractCompat.prepareFontData(context2, fonts, cancellationSignal2);
            Object fontFamily = newFamily();
            boolean atLeastOneFont = false;
            FontsContractCompat.FontInfo[] fontInfoArr2 = fonts;
            int length = fontInfoArr2.length;
            for (int i2 = 0; i2 < length; i2++) {
                FontsContractCompat.FontInfo font = fontInfoArr2[i2];
                ByteBuffer fontBuffer = uriBuffer.get(font.getUri());
                if (fontBuffer != null) {
                    if (!addFontFromBuffer(fontFamily, fontBuffer, font.getTtcIndex(), font.getWeight(), font.isItalic() ? 1 : 0)) {
                        abortCreation(fontFamily);
                        return null;
                    }
                    atLeastOneFont = true;
                }
            }
            if (!atLeastOneFont) {
                abortCreation(fontFamily);
                return null;
            } else if (!freeze(fontFamily)) {
                return null;
            } else {
                return Typeface.create(createFromFamiliesWithDefault(fontFamily), style);
            }
        }
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Context context2 = context;
        Resources resources2 = resources;
        int id = i;
        String path = str;
        int style = i2;
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context2, resources2, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context2, fontFamily, path, 0, -1, -1, (FontVariationAxis[]) null)) {
            abortCreation(fontFamily);
            return null;
        } else if (!freeze(fontFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(fontFamily);
        }
    }

    /* access modifiers changed from: protected */
    public Class obtainFontFamily() throws ClassNotFoundException {
        return Class.forName(FONT_FAMILY_CLASS);
    }

    /* access modifiers changed from: protected */
    public Constructor obtainFontFamilyCtor(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getConstructor(new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromAssetManagerMethod(Class fontFamily) throws NoSuchMethodException {
        Class[] clsArr = new Class[8];
        clsArr[0] = AssetManager.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = String.class;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = Integer.TYPE;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = Boolean.TYPE;
        Class[] clsArr5 = clsArr4;
        clsArr5[4] = Integer.TYPE;
        Class[] clsArr6 = clsArr5;
        clsArr6[5] = Integer.TYPE;
        Class[] clsArr7 = clsArr6;
        clsArr7[6] = Integer.TYPE;
        Class[] clsArr8 = clsArr7;
        clsArr8[7] = FontVariationAxis[].class;
        return fontFamily.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, clsArr8);
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromBufferMethod(Class fontFamily) throws NoSuchMethodException {
        Class[] clsArr = new Class[5];
        clsArr[0] = ByteBuffer.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = Integer.TYPE;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = FontVariationAxis[].class;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = Integer.TYPE;
        Class[] clsArr5 = clsArr4;
        clsArr5[4] = Integer.TYPE;
        return fontFamily.getMethod(ADD_FONT_FROM_BUFFER_METHOD, clsArr5);
    }

    /* access modifiers changed from: protected */
    public Method obtainFreezeMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(FREEZE_METHOD, new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainAbortCreationMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ABORT_CREATION_METHOD, new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class fontFamily) throws NoSuchMethodException {
        Class[] clsArr = new Class[3];
        clsArr[0] = Array.newInstance(fontFamily, 1).getClass();
        Class[] clsArr2 = clsArr;
        clsArr2[1] = Integer.TYPE;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = Integer.TYPE;
        Method m = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, clsArr3);
        m.setAccessible(true);
        return m;
    }
}
