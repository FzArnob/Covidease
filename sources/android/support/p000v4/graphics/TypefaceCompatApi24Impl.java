package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.provider.FontsContractCompat;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatApi24Impl */
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    TypefaceCompatApi24Impl() {
    }

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method createFromFamiliesWithDefaultMethod;
        try {
            fontFamilyClass = Class.forName(FONT_FAMILY_CLASS);
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            Class[] clsArr = new Class[5];
            clsArr[0] = ByteBuffer.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = Integer.TYPE;
            Class[] clsArr3 = clsArr2;
            clsArr3[2] = List.class;
            Class[] clsArr4 = clsArr3;
            clsArr4[3] = Integer.TYPE;
            Class[] clsArr5 = clsArr4;
            clsArr5[4] = Boolean.TYPE;
            addFontMethod = fontFamilyClass.getMethod(ADD_FONT_WEIGHT_STYLE_METHOD, clsArr5);
            createFromFamiliesWithDefaultMethod = Typeface.class.getMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(fontFamilyClass, 1).getClass()});
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            ReflectiveOperationException e2 = e;
            int e3 = Log.e(TAG, e2.getClass().getName(), e2);
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            createFromFamiliesWithDefaultMethod = null;
        }
        sFontFamilyCtor = fontFamilyCtor;
        sFontFamily = fontFamilyClass;
        sAddFontWeightStyle = addFontMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
    }

    public static boolean isUsable() {
        if (sAddFontWeightStyle == null) {
            int w = Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontWeightStyle != null;
    }

    private static Object newFamily() {
        Throwable th;
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private static boolean addFontWeightStyle(Object obj, ByteBuffer buffer, int i, int i2, boolean z) {
        Throwable th;
        Object family = obj;
        int ttcIndex = i;
        int weight = i2;
        boolean style = z;
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
            objArr5[4] = Boolean.valueOf(style);
            return ((Boolean) sAddFontWeightStyle.invoke(family, objArr5)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object obj) {
        Throwable th;
        Object family = obj;
        try {
            Object familyArray = Array.newInstance(sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{familyArray});
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int i) {
        C1650SimpleArrayMap simpleArrayMap;
        Context context2 = context;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        int style = i;
        Object family = newFamily();
        new C1650SimpleArrayMap();
        C1650SimpleArrayMap simpleArrayMap2 = simpleArrayMap;
        FontsContractCompat.FontInfo[] fontInfoArr = fonts;
        int length = fontInfoArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            FontsContractCompat.FontInfo font = fontInfoArr[i2];
            Uri uri = font.getUri();
            ByteBuffer buffer = (ByteBuffer) simpleArrayMap2.get(uri);
            if (buffer == null) {
                buffer = TypefaceCompatUtil.mmap(context2, cancellationSignal2, uri);
                Object put = simpleArrayMap2.put(uri, buffer);
            }
            if (!addFontWeightStyle(family, buffer, font.getTtcIndex(), font.getWeight(), font.isItalic())) {
                return null;
            }
        }
        return Typeface.create(createFromFamiliesWithDefault(family), style);
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int i) {
        Context context2 = context;
        Resources resources2 = resources;
        int i2 = i;
        Object family = newFamily();
        FontResourcesParserCompat.FontFileResourceEntry[] entries = entry.getEntries();
        int length = entries.length;
        for (int i3 = 0; i3 < length; i3++) {
            FontResourcesParserCompat.FontFileResourceEntry e = entries[i3];
            ByteBuffer buffer = TypefaceCompatUtil.copyToDirectBuffer(context2, resources2, e.getResourceId());
            if (buffer == null) {
                return null;
            }
            if (!addFontWeightStyle(family, buffer, e.getTtcIndex(), e.getWeight(), e.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(family);
    }
}
