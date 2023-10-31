package android.support.p000v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;

/* renamed from: android.support.v4.content.MimeTypeFilter */
public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    private static boolean mimeTypeAgainstFilter(@NonNull String[] strArr, @NonNull String[] strArr2) {
        Throwable th;
        Throwable th2;
        String[] mimeTypeParts = strArr;
        String[] filterParts = strArr2;
        if (filterParts.length != 2) {
            Throwable th3 = th2;
            new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
            throw th3;
        } else if (filterParts[0].isEmpty() || filterParts[1].isEmpty()) {
            Throwable th4 = th;
            new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
            throw th4;
        } else if (mimeTypeParts.length != 2) {
            return false;
        } else {
            if (!"*".equals(filterParts[0]) && !filterParts[0].equals(mimeTypeParts[0])) {
                return false;
            }
            if ("*".equals(filterParts[1]) || filterParts[1].equals(mimeTypeParts[1])) {
                return true;
            }
            return false;
        }
    }

    public static boolean matches(@Nullable String str, @NonNull String str2) {
        String mimeType = str;
        String filter = str2;
        if (mimeType == null) {
            return false;
        }
        return mimeTypeAgainstFilter(mimeType.split("/"), filter.split("/"));
    }

    @Nullable
    public static String matches(@Nullable String str, @NonNull String[] strArr) {
        String mimeType = str;
        String[] filters = strArr;
        if (mimeType == null) {
            return null;
        }
        String[] mimeTypeParts = mimeType.split("/");
        String[] strArr2 = filters;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            String filter = strArr2[i];
            if (mimeTypeAgainstFilter(mimeTypeParts, filter.split("/"))) {
                return filter;
            }
        }
        return null;
    }

    @Nullable
    public static String matches(@Nullable String[] strArr, @NonNull String str) {
        String[] mimeTypes = strArr;
        String filter = str;
        if (mimeTypes == null) {
            return null;
        }
        String[] filterParts = filter.split("/");
        String[] strArr2 = mimeTypes;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            String mimeType = strArr2[i];
            if (mimeTypeAgainstFilter(mimeType.split("/"), filterParts)) {
                return mimeType;
            }
        }
        return null;
    }

    @NonNull
    public static String[] matchesMany(@Nullable String[] strArr, @NonNull String str) {
        ArrayList arrayList;
        String[] mimeTypes = strArr;
        String filter = str;
        if (mimeTypes == null) {
            return new String[0];
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        String[] filterParts = filter.split("/");
        String[] strArr2 = mimeTypes;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            String mimeType = strArr2[i];
            if (mimeTypeAgainstFilter(mimeType.split("/"), filterParts)) {
                boolean add = arrayList2.add(mimeType);
            }
        }
        return (String[]) arrayList2.toArray(new String[arrayList2.size()]);
    }
}
