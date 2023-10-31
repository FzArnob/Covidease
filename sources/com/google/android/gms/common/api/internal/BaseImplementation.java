package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

@KeepForSdk
public class BaseImplementation {

    @KeepForSdk
    public interface ResultHolder<R> {
        @KeepForSdk
        void setFailedResult(Status status);

        @KeepForSdk
        void setResult(R r);
    }

    public BaseImplementation() {
    }

    @KeepForSdk
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {
        @KeepForSdk
        private final Api<?> mApi;
        @KeepForSdk
        private final Api.AnyClientKey<A> mClientKey;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @KeepForSdk
        @Deprecated
        protected ApiMethodImpl(@NonNull Api.AnyClientKey<A> anyClientKey, @NonNull GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            this.mClientKey = (Api.AnyClientKey) Preconditions.checkNotNull(anyClientKey);
            this.mApi = null;
        }

        /* access modifiers changed from: protected */
        @KeepForSdk
        public abstract void doExecute(@NonNull A a) throws RemoteException;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @KeepForSdk
        protected ApiMethodImpl(@NonNull Api<?> api, @NonNull GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            Api<?> api2 = api;
            Object checkNotNull = Preconditions.checkNotNull(api2, "Api must not be null");
            this.mClientKey = api2.getClientKey();
            this.mApi = api2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @VisibleForTesting
        @KeepForSdk
        protected ApiMethodImpl(@NonNull BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.mClientKey = null;
            this.mApi = null;
        }

        @KeepForSdk
        public final Api.AnyClientKey<A> getClientKey() {
            return this.mClientKey;
        }

        @KeepForSdk
        public final Api<?> getApi() {
            return this.mApi;
        }

        @KeepForSdk
        public final void run(@NonNull A a) throws DeadObjectException {
            A a2 = a;
            if (a2 instanceof SimpleClientAdapter) {
                a2 = ((SimpleClientAdapter) a2).getClient();
            }
            try {
                doExecute(a2);
            } catch (DeadObjectException e) {
                DeadObjectException deadObjectException = e;
                setFailedResult((RemoteException) deadObjectException);
                throw deadObjectException;
            } catch (RemoteException e2) {
                setFailedResult(e2);
            }
        }

        @KeepForSdk
        public final void setFailedResult(@NonNull Status status) {
            Status status2 = status;
            Preconditions.checkArgument(!status2.isSuccess(), "Failed result must not be success");
            Result createFailedResult = createFailedResult(status2);
            setResult(createFailedResult);
            onSetFailedResult(createFailedResult);
        }

        /* access modifiers changed from: protected */
        @KeepForSdk
        public void onSetFailedResult(@NonNull R r) {
        }

        @KeepForSdk
        private void setFailedResult(@NonNull RemoteException remoteException) {
            Status status;
            new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null);
            setFailedResult(status);
        }

        @KeepForSdk
        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((Result) obj);
        }
    }
}
