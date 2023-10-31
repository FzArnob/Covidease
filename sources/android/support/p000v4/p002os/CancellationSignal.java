package android.support.p000v4.p002os;

import android.os.Build;

/* renamed from: android.support.v4.os.CancellationSignal */
public final class CancellationSignal {
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    /* renamed from: android.support.v4.os.CancellationSignal$OnCancelListener */
    public interface OnCancelListener {
        void onCancel();
    }

    public CancellationSignal() {
    }

    public boolean isCanceled() {
        synchronized (this) {
            try {
                boolean z = this.mIsCanceled;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void throwIfCanceled() {
        Throwable th;
        if (isCanceled()) {
            Throwable th2 = th;
            new OperationCanceledException();
            throw th2;
        }
    }

    public void cancel() {
        Throwable th;
        synchronized (this) {
            try {
                if (this.mIsCanceled) {
                    return;
                }
                this.mIsCanceled = true;
                this.mCancelInProgress = true;
                OnCancelListener listener = this.mOnCancelListener;
                Object obj = this.mCancellationSignalObj;
                if (listener != null) {
                    try {
                        listener.onCancel();
                    } catch (Throwable th2) {
                        while (true) {
                            Throwable th3 = th2;
                            throw th3;
                        }
                    }
                }
                if (obj != null && Build.VERSION.SDK_INT >= 16) {
                    ((android.os.CancellationSignal) obj).cancel();
                }
                synchronized (this) {
                    try {
                        this.mCancelInProgress = false;
                        notifyAll();
                    } catch (Throwable th4) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            } finally {
                while (true) {
                    th = th4;
                    Throwable th5 = th;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void setOnCancelListener(OnCancelListener onCancelListener) {
        OnCancelListener listener = onCancelListener;
        synchronized (this) {
            try {
                waitForCancelFinishedLocked();
                if (this.mOnCancelListener == listener) {
                    return;
                }
                this.mOnCancelListener = listener;
                if (!this.mIsCanceled || listener == null) {
                    return;
                }
                listener.onCancel();
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    public Object getCancellationSignalObject() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            try {
                if (this.mCancellationSignalObj == null) {
                    new android.os.CancellationSignal();
                    this.mCancellationSignalObj = obj;
                    if (this.mIsCanceled) {
                        ((android.os.CancellationSignal) this.mCancellationSignalObj).cancel();
                    }
                }
                Object obj2 = this.mCancellationSignalObj;
                return obj2;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                wait();
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
            }
        }
    }
}
