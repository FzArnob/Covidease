package android.support.p000v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.Loader;
import android.support.p000v4.p002os.CancellationSignal;
import android.support.p000v4.p002os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* renamed from: android.support.v4.content.CursorLoader */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    CancellationSignal mCancellationSignal;
    Cursor mCursor;
    final Loader<Cursor>.ForceLoadContentObserver mObserver;
    String[] mProjection;
    String mSelection;
    String[] mSelectionArgs;
    String mSortOrder;
    Uri mUri;

    /* JADX INFO: finally extract failed */
    public Cursor loadInBackground() {
        CancellationSignal cancellationSignal;
        Cursor cursor;
        Throwable th;
        synchronized (this) {
            try {
                if (isLoadInBackgroundCanceled()) {
                    Throwable th2 = th;
                    new OperationCanceledException();
                    throw th2;
                }
                new CancellationSignal();
                this.mCancellationSignal = cancellationSignal;
                try {
                    cursor = ContentResolverCompat.query(getContext().getContentResolver(), this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder, this.mCancellationSignal);
                    if (cursor != null) {
                        int count = cursor.getCount();
                        cursor.registerContentObserver(this.mObserver);
                    }
                    Cursor cursor2 = cursor;
                    synchronized (this) {
                        try {
                            this.mCancellationSignal = null;
                            return cursor2;
                        } catch (Throwable th3) {
                            while (true) {
                                Throwable th4 = th3;
                                throw th4;
                            }
                        }
                    }
                } catch (RuntimeException e) {
                    RuntimeException ex = e;
                    cursor.close();
                    throw ex;
                } catch (Throwable th5) {
                    Throwable th6 = th5;
                    synchronized (this) {
                        try {
                            this.mCancellationSignal = null;
                            throw th6;
                        } catch (Throwable th7) {
                            while (true) {
                                Throwable th8 = th7;
                                throw th8;
                            }
                        }
                    }
                }
            } catch (Throwable th9) {
                Throwable th10 = th9;
                throw th10;
            }
        }
    }

    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            try {
                if (this.mCancellationSignal != null) {
                    this.mCancellationSignal.cancel();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void deliverResult(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (!isReset()) {
            Cursor oldCursor = this.mCursor;
            this.mCursor = cursor2;
            if (isStarted()) {
                super.deliverResult(cursor2);
            }
            if (oldCursor != null && oldCursor != cursor2 && !oldCursor.isClosed()) {
                oldCursor.close();
            }
        } else if (cursor2 != null) {
            cursor2.close();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CursorLoader(@NonNull Context context) {
        super(context);
        Loader<Cursor>.ForceLoadContentObserver forceLoadContentObserver;
        new Loader.ForceLoadContentObserver(this);
        this.mObserver = forceLoadContentObserver;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        super(context);
        Loader<Cursor>.ForceLoadContentObserver forceLoadContentObserver;
        new Loader.ForceLoadContentObserver(this);
        this.mObserver = forceLoadContentObserver;
        this.mUri = uri;
        this.mProjection = projection;
        this.mSelection = selection;
        this.mSelectionArgs = selectionArgs;
        this.mSortOrder = sortOrder;
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        if (this.mCursor != null) {
            deliverResult(this.mCursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        boolean cancelLoad = cancelLoad();
    }

    public void onCanceled(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (cursor2 != null && !cursor2.isClosed()) {
            cursor2.close();
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        onStopLoading();
        if (this.mCursor != null && !this.mCursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    @NonNull
    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(@NonNull Uri uri) {
        Uri uri2 = uri;
        this.mUri = uri2;
    }

    @Nullable
    public String[] getProjection() {
        return this.mProjection;
    }

    public void setProjection(@Nullable String[] projection) {
        String[] strArr = projection;
        this.mProjection = strArr;
    }

    @Nullable
    public String getSelection() {
        return this.mSelection;
    }

    public void setSelection(@Nullable String selection) {
        String str = selection;
        this.mSelection = str;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public void setSelectionArgs(@Nullable String[] selectionArgs) {
        String[] strArr = selectionArgs;
        this.mSelectionArgs = strArr;
    }

    @Nullable
    public String getSortOrder() {
        return this.mSortOrder;
    }

    public void setSortOrder(@Nullable String sortOrder) {
        String str = sortOrder;
        this.mSortOrder = str;
    }

    @Deprecated
    public void dump(String str, FileDescriptor fd, PrintWriter printWriter, String[] args) {
        String prefix = str;
        PrintWriter writer = printWriter;
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("mUri=");
        writer.println(this.mUri);
        writer.print(prefix);
        writer.print("mProjection=");
        writer.println(Arrays.toString(this.mProjection));
        writer.print(prefix);
        writer.print("mSelection=");
        writer.println(this.mSelection);
        writer.print(prefix);
        writer.print("mSelectionArgs=");
        writer.println(Arrays.toString(this.mSelectionArgs));
        writer.print(prefix);
        writer.print("mSortOrder=");
        writer.println(this.mSortOrder);
        writer.print(prefix);
        writer.print("mCursor=");
        writer.println(this.mCursor);
        writer.print(prefix);
        writer.print("mContentChanged=");
        writer.println(this.mContentChanged);
    }
}
