package android.support.p000v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: android.support.v4.content.Loader */
public class Loader<D> {
    boolean mAbandoned = false;
    boolean mContentChanged = false;
    Context mContext;
    int mId;
    OnLoadCompleteListener<D> mListener;
    OnLoadCanceledListener<D> mOnLoadCanceledListener;
    boolean mProcessingChange = false;
    boolean mReset = true;
    boolean mStarted = false;

    /* renamed from: android.support.v4.content.Loader$OnLoadCanceledListener */
    public interface OnLoadCanceledListener<D> {
        void onLoadCanceled(@NonNull Loader<D> loader);
    }

    /* renamed from: android.support.v4.content.Loader$OnLoadCompleteListener */
    public interface OnLoadCompleteListener<D> {
        void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d);
    }

    /* renamed from: android.support.v4.content.Loader$ForceLoadContentObserver */
    public final class ForceLoadContentObserver extends ContentObserver {
        final /* synthetic */ Loader this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ForceLoadContentObserver(android.support.p000v4.content.Loader r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                android.os.Handler r3 = new android.os.Handler
                r5 = r3
                r3 = r5
                r4 = r5
                r4.<init>()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.content.Loader.ForceLoadContentObserver.<init>(android.support.v4.content.Loader):void");
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            boolean z2 = z;
            this.this$0.onContentChanged();
        }
    }

    public Loader(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @MainThread
    public void deliverResult(@Nullable D d) {
        D data = d;
        if (this.mListener != null) {
            this.mListener.onLoadComplete(this, data);
        }
    }

    @MainThread
    public void deliverCancellation() {
        if (this.mOnLoadCanceledListener != null) {
            this.mOnLoadCanceledListener.onLoadCanceled(this);
        }
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    @MainThread
    public void registerListener(int i, @NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        Throwable th;
        int id = i;
        OnLoadCompleteListener<D> listener = onLoadCompleteListener;
        if (this.mListener != null) {
            Throwable th2 = th;
            new IllegalStateException("There is already a listener registered");
            throw th2;
        }
        this.mListener = listener;
        this.mId = id;
    }

    @MainThread
    public void unregisterListener(@NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        Throwable th;
        Throwable th2;
        OnLoadCompleteListener<D> listener = onLoadCompleteListener;
        if (this.mListener == null) {
            Throwable th3 = th2;
            new IllegalStateException("No listener register");
            throw th3;
        } else if (this.mListener != listener) {
            Throwable th4 = th;
            new IllegalArgumentException("Attempting to unregister the wrong listener");
            throw th4;
        } else {
            this.mListener = null;
        }
    }

    @MainThread
    public void registerOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        Throwable th;
        OnLoadCanceledListener<D> listener = onLoadCanceledListener;
        if (this.mOnLoadCanceledListener != null) {
            Throwable th2 = th;
            new IllegalStateException("There is already a listener registered");
            throw th2;
        }
        this.mOnLoadCanceledListener = listener;
    }

    @MainThread
    public void unregisterOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        Throwable th;
        Throwable th2;
        OnLoadCanceledListener<D> listener = onLoadCanceledListener;
        if (this.mOnLoadCanceledListener == null) {
            Throwable th3 = th2;
            new IllegalStateException("No listener register");
            throw th3;
        } else if (this.mOnLoadCanceledListener != listener) {
            Throwable th4 = th;
            new IllegalArgumentException("Attempting to unregister the wrong listener");
            throw th4;
        } else {
            this.mOnLoadCanceledListener = null;
        }
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    @MainThread
    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onStartLoading() {
    }

    @MainThread
    public boolean cancelLoad() {
        return onCancelLoad();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public boolean onCancelLoad() {
        return false;
    }

    @MainThread
    public void forceLoad() {
        onForceLoad();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onForceLoad() {
    }

    @MainThread
    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onStopLoading() {
    }

    @MainThread
    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onAbandon() {
    }

    @MainThread
    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onReset() {
    }

    public boolean takeContentChanged() {
        boolean res = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= res;
        return res;
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    @MainThread
    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    @NonNull
    public String dataToString(@Nullable D data) {
        StringBuilder sb;
        new StringBuilder(64);
        StringBuilder sb2 = sb;
        DebugUtils.buildShortClassTag(data, sb2);
        StringBuilder append = sb2.append("}");
        return sb2.toString();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(64);
        StringBuilder sb2 = sb;
        DebugUtils.buildShortClassTag(this, sb2);
        StringBuilder append = sb2.append(" id=");
        StringBuilder append2 = sb2.append(this.mId);
        StringBuilder append3 = sb2.append("}");
        return sb2.toString();
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String prefix = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter writer = printWriter;
        String[] strArr2 = strArr;
        writer.print(prefix);
        writer.print("mId=");
        writer.print(this.mId);
        writer.print(" mListener=");
        writer.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            writer.print(prefix);
            writer.print("mStarted=");
            writer.print(this.mStarted);
            writer.print(" mContentChanged=");
            writer.print(this.mContentChanged);
            writer.print(" mProcessingChange=");
            writer.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            writer.print(prefix);
            writer.print("mAbandoned=");
            writer.print(this.mAbandoned);
            writer.print(" mReset=");
            writer.println(this.mReset);
        }
    }
}
