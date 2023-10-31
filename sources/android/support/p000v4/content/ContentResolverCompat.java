package android.support.p000v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.OperationCanceledException;
import android.support.p000v4.p002os.CancellationSignal;

/* renamed from: android.support.v4.content.ContentResolverCompat */
public final class ContentResolverCompat {
    private ContentResolverCompat() {
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        Object obj;
        Throwable th;
        ContentResolver resolver = contentResolver;
        Uri uri2 = uri;
        String[] projection = strArr;
        String selection = str;
        String[] selectionArgs = strArr2;
        String sortOrder = str2;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        if (Build.VERSION.SDK_INT >= 16) {
            if (cancellationSignal2 != null) {
                try {
                    obj = cancellationSignal2.getCancellationSignalObject();
                } catch (Exception e) {
                    Exception e2 = e;
                    if (e2 instanceof OperationCanceledException) {
                        Throwable th2 = th;
                        new android.support.p000v4.p002os.OperationCanceledException();
                        throw th2;
                    }
                    throw e2;
                }
            } else {
                obj = null;
            }
            return resolver.query(uri2, projection, selection, selectionArgs, sortOrder, (android.os.CancellationSignal) obj);
        }
        if (cancellationSignal2 != null) {
            cancellationSignal2.throwIfCanceled();
        }
        return resolver.query(uri2, projection, selection, selectionArgs, sortOrder);
    }
}
