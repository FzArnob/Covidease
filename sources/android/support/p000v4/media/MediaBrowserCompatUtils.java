package android.support.p000v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.media.MediaBrowserCompatUtils */
public class MediaBrowserCompatUtils {
    public static boolean areSameOptions(Bundle bundle, Bundle bundle2) {
        Bundle options1 = bundle;
        Bundle options2 = bundle2;
        if (options1 == options2) {
            return true;
        }
        if (options1 == null) {
            return options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1;
        } else if (options2 == null) {
            return options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1;
        } else {
            return options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) && options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        }
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle2) {
        int i;
        int i2;
        int endIndex1;
        int startIndex1;
        int endIndex2;
        int startIndex2;
        Bundle options1 = bundle;
        Bundle options2 = bundle2;
        int page1 = options1 == null ? -1 : options1.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int page2 = options2 == null ? -1 : options2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        if (options1 == null) {
            i = -1;
        } else {
            i = options1.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        }
        int pageSize1 = i;
        if (options2 == null) {
            i2 = -1;
        } else {
            i2 = options2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        }
        int pageSize2 = i2;
        if (page1 == -1 || pageSize1 == -1) {
            startIndex1 = 0;
            endIndex1 = Integer.MAX_VALUE;
        } else {
            startIndex1 = pageSize1 * page1;
            endIndex1 = (startIndex1 + pageSize1) - 1;
        }
        if (page2 == -1 || pageSize2 == -1) {
            startIndex2 = 0;
            endIndex2 = Integer.MAX_VALUE;
        } else {
            startIndex2 = pageSize2 * page2;
            endIndex2 = (startIndex2 + pageSize2) - 1;
        }
        return endIndex1 >= startIndex2 && endIndex2 >= startIndex1;
    }

    private MediaBrowserCompatUtils() {
    }
}
