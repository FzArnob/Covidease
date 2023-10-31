package android.support.p000v4.p002os;

import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import java.util.Locale;

/* renamed from: android.support.v4.os.LocaleListCompat */
public final class LocaleListCompat {
    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList;

    static {
        LocaleListCompat localeListCompat;
        LocaleListInterface localeListInterface;
        LocaleListInterface localeListInterface2;
        new LocaleListCompat();
        sEmptyLocaleList = localeListCompat;
        if (Build.VERSION.SDK_INT >= 24) {
            new LocaleListCompatApi24Impl();
            IMPL = localeListInterface2;
            return;
        }
        new LocaleListCompatBaseImpl();
        IMPL = localeListInterface;
    }

    /* renamed from: android.support.v4.os.LocaleListCompat$LocaleListCompatBaseImpl */
    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList;

        LocaleListCompatBaseImpl() {
            LocaleListHelper localeListHelper;
            new LocaleListHelper(new Locale[0]);
            this.mLocaleList = localeListHelper;
        }

        public void setLocaleList(@NonNull Locale... list) {
            LocaleListHelper localeListHelper;
            LocaleListHelper localeListHelper2 = localeListHelper;
            new LocaleListHelper(list);
            this.mLocaleList = localeListHelper2;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public Locale get(int index) {
            return this.mLocaleList.get(index);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @IntRange(from = 0)
        public int size() {
            return this.mLocaleList.size();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        public boolean equals(Object other) {
            return this.mLocaleList.equals(((LocaleListCompat) other).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            String[] supportedLocales = strArr;
            if (this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(supportedLocales);
            }
            return null;
        }
    }

    @RequiresApi(24)
    /* renamed from: android.support.v4.os.LocaleListCompat$LocaleListCompatApi24Impl */
    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList;

        LocaleListCompatApi24Impl() {
            LocaleList localeList;
            new LocaleList(new Locale[0]);
            this.mLocaleList = localeList;
        }

        public void setLocaleList(@NonNull Locale... list) {
            LocaleList localeList;
            LocaleList localeList2 = localeList;
            new LocaleList(list);
            this.mLocaleList = localeList2;
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public Locale get(int index) {
            return this.mLocaleList.get(index);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @IntRange(from = 0)
        public int size() {
            return this.mLocaleList.size();
        }

        @IntRange(from = -1)
        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        public boolean equals(Object other) {
            return this.mLocaleList.equals(((LocaleListCompat) other).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Nullable
        public Locale getFirstMatch(String[] strArr) {
            String[] supportedLocales = strArr;
            if (this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(supportedLocales);
            }
            return null;
        }
    }

    private LocaleListCompat() {
    }

    @RequiresApi(24)
    public static LocaleListCompat wrap(Object obj) {
        LocaleListCompat localeListCompat;
        Object object = obj;
        new LocaleListCompat();
        LocaleListCompat instance = localeListCompat;
        if (object instanceof LocaleList) {
            instance.setLocaleList((LocaleList) object);
        }
        return instance;
    }

    @Nullable
    public Object unwrap() {
        return IMPL.getLocaleList();
    }

    public static LocaleListCompat create(@NonNull Locale... localeList) {
        LocaleListCompat localeListCompat;
        new LocaleListCompat();
        LocaleListCompat instance = localeListCompat;
        instance.setLocaleListArray(localeList);
        return instance;
    }

    public Locale get(int index) {
        return IMPL.get(index);
    }

    public boolean isEmpty() {
        return IMPL.isEmpty();
    }

    @IntRange(from = 0)
    public int size() {
        return IMPL.size();
    }

    @IntRange(from = -1)
    public int indexOf(Locale locale) {
        return IMPL.indexOf(locale);
    }

    @NonNull
    public String toLanguageTags() {
        return IMPL.toLanguageTags();
    }

    public Locale getFirstMatch(String[] supportedLocales) {
        return IMPL.getFirstMatch(supportedLocales);
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String str) {
        LocaleListCompat localeListCompat;
        Locale forLanguageTag;
        String list = str;
        if (list == null || list.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] tags = list.split(",", -1);
        Locale[] localeArray = new Locale[tags.length];
        for (int i = 0; i < localeArray.length; i++) {
            Locale[] localeArr = localeArray;
            int i2 = i;
            if (Build.VERSION.SDK_INT >= 21) {
                forLanguageTag = Locale.forLanguageTag(tags[i]);
            } else {
                forLanguageTag = LocaleHelper.forLanguageTag(tags[i]);
            }
            localeArr[i2] = forLanguageTag;
        }
        new LocaleListCompat();
        LocaleListCompat instance = localeListCompat;
        instance.setLocaleListArray(localeArray);
        return instance;
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getAdjustedDefault() {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getAdjustedDefault());
        }
        return create(Locale.getDefault());
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListCompat getDefault() {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getDefault());
        }
        return create(Locale.getDefault());
    }

    public boolean equals(Object other) {
        return IMPL.equals(other);
    }

    public int hashCode() {
        return IMPL.hashCode();
    }

    public String toString() {
        return IMPL.toString();
    }

    @RequiresApi(24)
    private void setLocaleList(LocaleList localeList) {
        LocaleList localeList2 = localeList;
        int localeListSize = localeList2.size();
        if (localeListSize > 0) {
            Locale[] localeArrayList = new Locale[localeListSize];
            for (int i = 0; i < localeListSize; i++) {
                localeArrayList[i] = localeList2.get(i);
            }
            IMPL.setLocaleList(localeArrayList);
        }
    }

    private void setLocaleListArray(Locale... localeArrayList) {
        IMPL.setLocaleList(localeArrayList);
    }
}
