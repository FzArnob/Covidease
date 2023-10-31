package android.support.p000v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.C0020R;
import android.support.p000v4.provider.FontRequest;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.content.res.FontResourcesParserCompat */
public class FontResourcesParserCompat {
    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    /* renamed from: android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry */
    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.content.res.FontResourcesParserCompat$FetchStrategy */
    public @interface FetchStrategy {
    }

    /* renamed from: android.support.v4.content.res.FontResourcesParserCompat$ProviderResourceEntry */
    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        @NonNull
        private final FontRequest mRequest;
        private final int mStrategy;
        private final int mTimeoutMs;

        public ProviderResourceEntry(@NonNull FontRequest request, int strategy, int timeoutMs) {
            this.mRequest = request;
            this.mStrategy = strategy;
            this.mTimeoutMs = timeoutMs;
        }

        @NonNull
        public FontRequest getRequest() {
            return this.mRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    /* renamed from: android.support.v4.content.res.FontResourcesParserCompat$FontFileResourceEntry */
    public static final class FontFileResourceEntry {
        @NonNull
        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mTtcIndex;
        private String mVariationSettings;
        private int mWeight;

        public FontFileResourceEntry(@NonNull String fileName, int weight, boolean italic, @Nullable String variationSettings, int ttcIndex, int resourceId) {
            this.mFileName = fileName;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mVariationSettings = variationSettings;
            this.mTtcIndex = ttcIndex;
            this.mResourceId = resourceId;
        }

        @NonNull
        public String getFileName() {
            return this.mFileName;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        @Nullable
        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getResourceId() {
            return this.mResourceId;
        }
    }

    /* renamed from: android.support.v4.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry */
    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        @NonNull
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(@NonNull FontFileResourceEntry[] entries) {
            this.mEntries = entries;
        }

        @NonNull
        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    @Nullable
    public static FamilyResourceEntry parse(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int type;
        Throwable th;
        XmlPullParser parser = xmlPullParser;
        Resources resources2 = resources;
        do {
            int next = parser.next();
            type = next;
            if (next == 2 || type == 1) {
            }
            int next2 = parser.next();
            type = next2;
            break;
        } while (type == 1);
        if (type == 2) {
            return readFamilies(parser, resources2);
        }
        Throwable th2 = th;
        new XmlPullParserException("No start tag found");
        throw th2;
    }

    @Nullable
    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        XmlPullParser parser = xmlPullParser;
        Resources resources2 = resources;
        parser.require(2, (String) null, "font-family");
        if (parser.getName().equals("font-family")) {
            return readFamily(parser, resources2);
        }
        skip(parser);
        return null;
    }

    @Nullable
    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        List<FontFileResourceEntry> list;
        FamilyResourceEntry familyResourceEntry;
        FamilyResourceEntry familyResourceEntry2;
        FontRequest fontRequest;
        XmlPullParser parser = xmlPullParser;
        Resources resources2 = resources;
        TypedArray array = resources2.obtainAttributes(Xml.asAttributeSet(parser), C0020R.styleable.FontFamily);
        String authority = array.getString(C0020R.styleable.FontFamily_fontProviderAuthority);
        String providerPackage = array.getString(C0020R.styleable.FontFamily_fontProviderPackage);
        String query = array.getString(C0020R.styleable.FontFamily_fontProviderQuery);
        int certsId = array.getResourceId(C0020R.styleable.FontFamily_fontProviderCerts, 0);
        int strategy = array.getInteger(C0020R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int timeoutMs = array.getInteger(C0020R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        array.recycle();
        if (authority == null || providerPackage == null || query == null) {
            new ArrayList<>();
            List<FontFileResourceEntry> fonts = list;
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    if (parser.getName().equals("font")) {
                        boolean add = fonts.add(readFont(parser, resources2));
                    } else {
                        skip(parser);
                    }
                }
            }
            if (fonts.isEmpty()) {
                return null;
            }
            new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) fonts.toArray(new FontFileResourceEntry[fonts.size()]));
            return familyResourceEntry;
        }
        while (parser.next() != 3) {
            skip(parser);
        }
        new FontRequest(authority, providerPackage, query, readCerts(resources2, certsId));
        new ProviderResourceEntry(fontRequest, strategy, timeoutMs);
        return familyResourceEntry2;
    }

    private static int getType(TypedArray typedArray, int i) {
        TypedValue typedValue;
        TypedArray typedArray2 = typedArray;
        int index = i;
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray2.getType(index);
        }
        new TypedValue();
        TypedValue tv = typedValue;
        boolean value = typedArray2.getValue(index, tv);
        return tv.type;
    }

    /* JADX INFO: finally extract failed */
    public static List<List<byte[]>> readCerts(Resources resources, @ArrayRes int i) {
        List list;
        Resources resources2 = resources;
        int certsId = i;
        if (certsId == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArray = resources2.obtainTypedArray(certsId);
        try {
            if (typedArray.length() == 0) {
                List<List<byte[]>> emptyList = Collections.emptyList();
                typedArray.recycle();
                return emptyList;
            }
            new ArrayList();
            List list2 = list;
            if (getType(typedArray, 0) == 1) {
                for (int i2 = 0; i2 < typedArray.length(); i2++) {
                    int certId = typedArray.getResourceId(i2, 0);
                    if (certId != 0) {
                        boolean add = list2.add(toByteArrayList(resources2.getStringArray(certId)));
                    }
                }
            } else {
                boolean add2 = list2.add(toByteArrayList(resources2.getStringArray(certsId)));
            }
            List list3 = list2;
            typedArray.recycle();
            return list3;
        } catch (Throwable th) {
            Throwable th2 = th;
            typedArray.recycle();
            throw th2;
        }
    }

    private static List<byte[]> toByteArrayList(String[] stringArray) {
        List<byte[]> list;
        new ArrayList();
        List<byte[]> result = list;
        String[] strArr = stringArray;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            boolean add = result.add(Base64.decode(strArr[i], 0));
        }
        return result;
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        FontFileResourceEntry fontFileResourceEntry;
        XmlPullParser parser = xmlPullParser;
        TypedArray array = resources.obtainAttributes(Xml.asAttributeSet(parser), C0020R.styleable.FontFamilyFont);
        int weight = array.getInt(array.hasValue(C0020R.styleable.FontFamilyFont_fontWeight) ? C0020R.styleable.FontFamilyFont_fontWeight : C0020R.styleable.FontFamilyFont_android_fontWeight, 400);
        boolean isItalic = 1 == array.getInt(array.hasValue(C0020R.styleable.FontFamilyFont_fontStyle) ? C0020R.styleable.FontFamilyFont_fontStyle : C0020R.styleable.FontFamilyFont_android_fontStyle, 0);
        int ttcIndexAttr = array.hasValue(C0020R.styleable.FontFamilyFont_ttcIndex) ? C0020R.styleable.FontFamilyFont_ttcIndex : C0020R.styleable.FontFamilyFont_android_ttcIndex;
        String variationSettings = array.getString(array.hasValue(C0020R.styleable.FontFamilyFont_fontVariationSettings) ? C0020R.styleable.FontFamilyFont_fontVariationSettings : C0020R.styleable.FontFamilyFont_android_fontVariationSettings);
        int ttcIndex = array.getInt(ttcIndexAttr, 0);
        int resourceAttr = array.hasValue(C0020R.styleable.FontFamilyFont_font) ? C0020R.styleable.FontFamilyFont_font : C0020R.styleable.FontFamilyFont_android_font;
        int resourceId = array.getResourceId(resourceAttr, 0);
        String filename = array.getString(resourceAttr);
        array.recycle();
        while (parser.next() != 3) {
            skip(parser);
        }
        new FontFileResourceEntry(filename, weight, isItalic, variationSettings, ttcIndex, resourceId);
        return fontFileResourceEntry;
    }

    private static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XmlPullParser parser = xmlPullParser;
        int depth = 1;
        while (depth > 0) {
            switch (parser.next()) {
                case 2:
                    depth++;
                    break;
                case 3:
                    depth--;
                    break;
            }
        }
    }

    private FontResourcesParserCompat() {
    }
}
