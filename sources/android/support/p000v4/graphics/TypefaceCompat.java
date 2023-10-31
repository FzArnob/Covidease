package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.provider.FontsContractCompat;
import android.support.p000v4.util.C1648LruCache;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompat */
public class TypefaceCompat {
    private static final String TAG = "TypefaceCompat";
    private static final C1648LruCache<String, Typeface> sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        TypefaceCompatBaseImpl typefaceCompatBaseImpl;
        TypefaceCompatBaseImpl typefaceCompatBaseImpl2;
        TypefaceCompatBaseImpl typefaceCompatBaseImpl3;
        TypefaceCompatBaseImpl typefaceCompatBaseImpl4;
        C1648LruCache<String, Typeface> lruCache;
        TypefaceCompatBaseImpl typefaceCompatBaseImpl5;
        if (Build.VERSION.SDK_INT >= 28) {
            new TypefaceCompatApi28Impl();
            sTypefaceCompatImpl = typefaceCompatBaseImpl5;
        } else if (Build.VERSION.SDK_INT >= 26) {
            new TypefaceCompatApi26Impl();
            sTypefaceCompatImpl = typefaceCompatBaseImpl4;
        } else if (Build.VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
            new TypefaceCompatApi24Impl();
            sTypefaceCompatImpl = typefaceCompatBaseImpl3;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new TypefaceCompatApi21Impl();
            sTypefaceCompatImpl = typefaceCompatBaseImpl2;
        } else {
            new TypefaceCompatBaseImpl();
            sTypefaceCompatImpl = typefaceCompatBaseImpl;
        }
        new C1648LruCache<>(16);
        sTypefaceCache = lruCache;
    }

    private TypefaceCompat() {
    }

    @Nullable
    public static Typeface findFromCache(@NonNull Resources resources, int id, int style) {
        return sTypefaceCache.get(createResourceUid(resources, id, style));
    }

    private static String createResourceUid(Resources resources, int i, int style) {
        StringBuilder sb;
        int id = i;
        new StringBuilder();
        return sb.append(resources.getResourcePackageName(id)).append("-").append(id).append("-").append(style).toString();
    }

    @Nullable
    public static Typeface createFromResourcesFamilyXml(@NonNull Context context, @NonNull FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, @NonNull Resources resources, int i, int i2, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Typeface typeface;
        Context context2 = context;
        FontResourcesParserCompat.FamilyResourceEntry entry = familyResourceEntry;
        Resources resources2 = resources;
        int id = i;
        int style = i2;
        ResourcesCompat.FontCallback fontCallback2 = fontCallback;
        Handler handler2 = handler;
        boolean isRequestFromLayoutInflator = z;
        if (entry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerEntry = (FontResourcesParserCompat.ProviderResourceEntry) entry;
            typeface = FontsContractCompat.getFontSync(context2, providerEntry.getRequest(), fontCallback2, handler2, isRequestFromLayoutInflator ? providerEntry.getFetchStrategy() == 0 : fontCallback2 == null, isRequestFromLayoutInflator ? providerEntry.getTimeout() : -1, style);
        } else {
            typeface = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context2, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) entry, resources2, style);
            if (fontCallback2 != null) {
                if (typeface != null) {
                    fontCallback2.callbackSuccessAsync(typeface, handler2);
                } else {
                    fontCallback2.callbackFailAsync(-3, handler2);
                }
            }
        }
        if (typeface != null) {
            Typeface put = sTypefaceCache.put(createResourceUid(resources2, id, style), typeface);
        }
        return typeface;
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(@NonNull Context context, @NonNull Resources resources, int i, String path, int i2) {
        Resources resources2 = resources;
        int id = i;
        int style = i2;
        Typeface typeface = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources2, id, path, style);
        if (typeface != null) {
            Typeface put = sTypefaceCache.put(createResourceUid(resources2, id, style), typeface);
        }
        return typeface;
    }

    @Nullable
    public static Typeface createFromFontInfo(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fonts, style);
    }
}
