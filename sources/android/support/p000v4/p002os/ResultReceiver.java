package android.support.p000v4.p002os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;
import android.support.p000v4.p002os.IResultReceiver;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.os.ResultReceiver */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR;
    final Handler mHandler;
    final boolean mLocal;
    IResultReceiver mReceiver;

    /* renamed from: android.support.v4.os.ResultReceiver$MyRunnable */
    class MyRunnable implements Runnable {
        final int mResultCode;
        final Bundle mResultData;
        final /* synthetic */ ResultReceiver this$0;

        MyRunnable(ResultReceiver this$02, int resultCode, Bundle resultData) {
            this.this$0 = this$02;
            this.mResultCode = resultCode;
            this.mResultData = resultData;
        }

        public void run() {
            this.this$0.onReceiveResult(this.mResultCode, this.mResultData);
        }
    }

    /* renamed from: android.support.v4.os.ResultReceiver$MyResultReceiver */
    class MyResultReceiver extends IResultReceiver.Stub {
        final /* synthetic */ ResultReceiver this$0;

        MyResultReceiver(ResultReceiver this$02) {
            this.this$0 = this$02;
        }

        public void send(int i, Bundle bundle) {
            Runnable runnable;
            int resultCode = i;
            Bundle resultData = bundle;
            if (this.this$0.mHandler != null) {
                new MyRunnable(this.this$0, resultCode, resultData);
                boolean post = this.this$0.mHandler.post(runnable);
                return;
            }
            this.this$0.onReceiveResult(resultCode, resultData);
        }
    }

    public ResultReceiver(Handler handler) {
        this.mLocal = true;
        this.mHandler = handler;
    }

    public void send(int i, Bundle bundle) {
        Runnable runnable;
        int resultCode = i;
        Bundle resultData = bundle;
        if (this.mLocal) {
            if (this.mHandler != null) {
                new MyRunnable(this, resultCode, resultData);
                boolean post = this.mHandler.post(runnable);
                return;
            }
            onReceiveResult(resultCode, resultData);
        } else if (this.mReceiver != null) {
            try {
                this.mReceiver.send(resultCode, resultData);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int resultCode, Bundle resultData) {
    }

    public int describeContents() {
        return 0;
    }

    /* JADX INFO: finally extract failed */
    public void writeToParcel(Parcel parcel, int i) {
        IResultReceiver iResultReceiver;
        Parcel out = parcel;
        int i2 = i;
        synchronized (this) {
            try {
                if (this.mReceiver == null) {
                    new MyResultReceiver(this);
                    this.mReceiver = iResultReceiver;
                }
                out.writeStrongBinder(this.mReceiver.asBinder());
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    ResultReceiver(Parcel in) {
        this.mLocal = false;
        this.mHandler = null;
        this.mReceiver = IResultReceiver.Stub.asInterface(in.readStrongBinder());
    }

    static {
        Parcelable.Creator<ResultReceiver> creator;
        new Parcelable.Creator<ResultReceiver>() {
            public ResultReceiver createFromParcel(Parcel in) {
                ResultReceiver resultReceiver;
                new ResultReceiver(in);
                return resultReceiver;
            }

            public ResultReceiver[] newArray(int size) {
                return new ResultReceiver[size];
            }
        };
        CREATOR = creator;
    }
}
