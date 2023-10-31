package android.support.p000v4.p002os;

import android.os.Build;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.os.LocaleListHelper */
final class LocaleListHelper {
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB;
    private static final Locale LOCALE_EN_XA;
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList;
    @GuardedBy("sLock")
    private static Locale sLastDefaultLocale = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock;
    private final Locale[] mList;
    @NonNull
    private final String mStringRepresentation;

    static {
        LocaleListHelper localeListHelper;
        Locale locale;
        Locale locale2;
        Object obj;
        new LocaleListHelper(new Locale[0]);
        sEmptyLocaleList = localeListHelper;
        new Locale("en", "XA");
        LOCALE_EN_XA = locale;
        new Locale("ar", "XB");
        LOCALE_AR_XB = locale2;
        new Object();
        sLock = obj;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale get(int i) {
        int index = i;
        return (0 > index || index >= this.mList.length) ? null : this.mList[index];
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    /* access modifiers changed from: package-private */
    @IntRange(from = 0)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int size() {
        return this.mList.length;
    }

    /* access modifiers changed from: package-private */
    @IntRange(from = -1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int indexOf(Locale locale) {
        Locale locale2 = locale;
        for (int i = 0; i < this.mList.length; i++) {
            if (this.mList[i].equals(locale2)) {
                return i;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (other == this) {
            return true;
        }
        if (!(other instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] otherList = ((LocaleListHelper) other).mList;
        if (this.mList.length != otherList.length) {
            return false;
        }
        for (int i = 0; i < this.mList.length; i++) {
            if (!this.mList[i].equals(otherList[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 1;
        for (int i = 0; i < this.mList.length; i++) {
            result = (31 * result) + this.mList[i].hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("[");
        for (int i = 0; i < this.mList.length; i++) {
            StringBuilder append2 = sb2.append(this.mList[i]);
            if (i < this.mList.length - 1) {
                StringBuilder append3 = sb2.append(',');
            }
        }
        StringBuilder append4 = sb2.append("]");
        return sb2.toString();
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale... localeArr) {
        HashSet hashSet;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        StringBuilder sb3;
        Locale[] list = localeArr;
        if (list.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeList = new Locale[list.length];
        new HashSet();
        HashSet hashSet2 = hashSet;
        new StringBuilder();
        StringBuilder sb4 = sb;
        int i = 0;
        while (i < list.length) {
            Locale l = list[i];
            if (l == null) {
                Throwable th3 = th;
                new StringBuilder();
                new NullPointerException(sb2.append("list[").append(i).append("] is null").toString());
                throw th3;
            } else if (hashSet2.contains(l)) {
                Throwable th4 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("list[").append(i).append("] is a repetition").toString());
                throw th4;
            } else {
                Locale localeClone = (Locale) l.clone();
                localeList[i] = localeClone;
                StringBuilder append = sb4.append(LocaleHelper.toLanguageTag(localeClone));
                if (i < list.length - 1) {
                    StringBuilder append2 = sb4.append(',');
                }
                boolean add = hashSet2.add(localeClone);
                i++;
            }
        }
        this.mList = localeList;
        this.mStringRepresentation = sb4.toString();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale locale, LocaleListHelper localeListHelper) {
        StringBuilder sb;
        Throwable th;
        Locale topLocale = locale;
        LocaleListHelper otherLocales = localeListHelper;
        if (topLocale == null) {
            Throwable th2 = th;
            new NullPointerException("topLocale is null");
            throw th2;
        }
        int inputLength = otherLocales == null ? 0 : otherLocales.mList.length;
        int topLocaleIndex = -1;
        int i = 0;
        while (true) {
            if (i >= inputLength) {
                break;
            } else if (topLocale.equals(otherLocales.mList[i])) {
                topLocaleIndex = i;
                break;
            } else {
                i++;
            }
        }
        int i2 = inputLength + (topLocaleIndex == -1 ? 1 : 0);
        Locale[] localeList = new Locale[i2];
        localeList[0] = (Locale) topLocale.clone();
        if (topLocaleIndex == -1) {
            for (int i3 = 0; i3 < inputLength; i3++) {
                localeList[i3 + 1] = (Locale) otherLocales.mList[i3].clone();
            }
        } else {
            for (int i4 = 0; i4 < topLocaleIndex; i4++) {
                localeList[i4 + 1] = (Locale) otherLocales.mList[i4].clone();
            }
            for (int i5 = topLocaleIndex + 1; i5 < inputLength; i5++) {
                localeList[i5] = (Locale) otherLocales.mList[i5].clone();
            }
        }
        new StringBuilder();
        StringBuilder sb2 = sb;
        for (int i6 = 0; i6 < i2; i6++) {
            StringBuilder append = sb2.append(LocaleHelper.toLanguageTag(localeList[i6]));
            if (i6 < i2 - 1) {
                StringBuilder append2 = sb2.append(',');
            }
        }
        this.mList = localeList;
        this.mStringRepresentation = sb2.toString();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper forLanguageTags(@Nullable String str) {
        LocaleListHelper localeListHelper;
        String list = str;
        if (list == null || list.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] tags = list.split(",", -1);
        Locale[] localeArray = new Locale[tags.length];
        for (int i = 0; i < localeArray.length; i++) {
            localeArray[i] = LocaleHelper.forLanguageTag(tags[i]);
        }
        new LocaleListHelper(localeArray);
        return localeListHelper;
    }

    private static String getLikelyScript(Locale locale) {
        Locale locale2 = locale;
        if (Build.VERSION.SDK_INT < 21) {
            return "";
        }
        String script = locale2.getScript();
        if (!script.isEmpty()) {
            return script;
        }
        return "";
    }

    private static boolean isPseudoLocale(String str) {
        String locale = str;
        return STRING_EN_XA.equals(locale) || STRING_AR_XB.equals(locale);
    }

    private static boolean isPseudoLocale(Locale locale) {
        Locale locale2 = locale;
        return LOCALE_EN_XA.equals(locale2) || LOCALE_AR_XB.equals(locale2);
    }

    @IntRange(from = 0, mo109to = 1)
    private static int matchScore(Locale locale, Locale locale2) {
        Locale supported = locale;
        Locale desired = locale2;
        if (supported.equals(desired)) {
            return 1;
        }
        if (!supported.getLanguage().equals(desired.getLanguage())) {
            return 0;
        }
        if (isPseudoLocale(supported) || isPseudoLocale(desired)) {
            return 0;
        }
        String supportedScr = getLikelyScript(supported);
        if (supportedScr.isEmpty()) {
            String supportedRegion = supported.getCountry();
            return (supportedRegion.isEmpty() || supportedRegion.equals(desired.getCountry())) ? 1 : 0;
        }
        return supportedScr.equals(getLikelyScript(desired)) ? 1 : 0;
    }

    private int findFirstMatchIndex(Locale locale) {
        Locale supportedLocale = locale;
        for (int idx = 0; idx < this.mList.length; idx++) {
            if (matchScore(supportedLocale, this.mList[idx]) > 0) {
                return idx;
            }
        }
        return Integer.MAX_VALUE;
    }

    private int computeFirstMatchIndex(Collection<String> collection, boolean z) {
        Collection<String> supportedLocales = collection;
        boolean assumeEnglishIsSupported = z;
        if (this.mList.length == 1) {
            return 0;
        }
        if (this.mList.length == 0) {
            return -1;
        }
        int bestIndex = Integer.MAX_VALUE;
        if (assumeEnglishIsSupported) {
            int idx = findFirstMatchIndex(EN_LATN);
            if (idx == 0) {
                return 0;
            }
            if (idx < Integer.MAX_VALUE) {
                bestIndex = idx;
            }
        }
        for (String languageTag : supportedLocales) {
            int idx2 = findFirstMatchIndex(LocaleHelper.forLanguageTag(languageTag));
            if (idx2 == 0) {
                return 0;
            }
            if (idx2 < bestIndex) {
                bestIndex = idx2;
            }
        }
        if (bestIndex == Integer.MAX_VALUE) {
            return 0;
        }
        return bestIndex;
    }

    private Locale computeFirstMatch(Collection<String> supportedLocales, boolean assumeEnglishIsSupported) {
        int bestIndex = computeFirstMatchIndex(supportedLocales, assumeEnglishIsSupported);
        return bestIndex == -1 ? null : this.mList[bestIndex];
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatch(String[] supportedLocales) {
        return computeFirstMatch(Arrays.asList(supportedLocales), false);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndex(String[] supportedLocales) {
        return computeFirstMatchIndex(Arrays.asList(supportedLocales), false);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatchWithEnglishSupported(String[] supportedLocales) {
        return computeFirstMatch(Arrays.asList(supportedLocales), true);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(Collection<String> supportedLocales) {
        return computeFirstMatchIndex(supportedLocales, true);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(String[] supportedLocales) {
        return getFirstMatchIndexWithEnglishSupported((Collection<String>) Arrays.asList(supportedLocales));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static boolean isPseudoLocalesOnly(@Nullable String[] strArr) {
        String[] supportedLocales = strArr;
        if (supportedLocales == null) {
            return true;
        }
        if (supportedLocales.length > 3) {
            return false;
        }
        String[] strArr2 = supportedLocales;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            String locale = strArr2[i];
            if (!locale.isEmpty() && !isPseudoLocale(locale)) {
                return false;
            }
        }
        return true;
    }

    @Size(min = 1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper getDefault() {
        LocaleListHelper localeListHelper;
        Locale defaultLocale = Locale.getDefault();
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!defaultLocale.equals(sLastDefaultLocale)) {
                    sLastDefaultLocale = defaultLocale;
                    if (sDefaultLocaleList == null || !defaultLocale.equals(sDefaultLocaleList.get(0))) {
                        new LocaleListHelper(defaultLocale, sLastExplicitlySetLocaleList);
                        sDefaultLocaleList = localeListHelper;
                        sDefaultAdjustedLocaleList = sDefaultLocaleList;
                    } else {
                        LocaleListHelper localeListHelper2 = sDefaultLocaleList;
                        return localeListHelper2;
                    }
                }
                LocaleListHelper localeListHelper3 = sDefaultLocaleList;
                return localeListHelper3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @Size(min = 1)
    @NonNull
    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper = getDefault();
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                LocaleListHelper localeListHelper2 = sDefaultAdjustedLocaleList;
                return localeListHelper2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void setDefault(@Size(min = 1) @NonNull LocaleListHelper locales) {
        setDefault(locales, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void setDefault(@Size(min = 1) @NonNull LocaleListHelper localeListHelper, int i) {
        LocaleListHelper localeListHelper2;
        Throwable th;
        Throwable th2;
        LocaleListHelper locales = localeListHelper;
        int localeIndex = i;
        if (locales == null) {
            Throwable th3 = th2;
            new NullPointerException("locales is null");
            throw th3;
        } else if (locales.isEmpty()) {
            Throwable th4 = th;
            new IllegalArgumentException("locales is empty");
            throw th4;
        } else {
            Object obj = sLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    sLastDefaultLocale = locales.get(localeIndex);
                    Locale.setDefault(sLastDefaultLocale);
                    sLastExplicitlySetLocaleList = locales;
                    sDefaultLocaleList = locales;
                    if (localeIndex == 0) {
                        sDefaultAdjustedLocaleList = sDefaultLocaleList;
                    } else {
                        new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
                        sDefaultAdjustedLocaleList = localeListHelper2;
                    }
                } catch (Throwable th5) {
                    Throwable th6 = th5;
                    Object obj3 = obj2;
                    throw th6;
                }
            }
        }
    }
}
