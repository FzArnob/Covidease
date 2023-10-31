package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class RegistrationMethods<A extends Api.AnyClient, L> {
    public final RegisterListenerMethod<A, L> zajz;
    public final UnregisterListenerMethod<A, L> zaka;

    private RegistrationMethods(RegisterListenerMethod<A, L> registerListenerMethod, UnregisterListenerMethod<A, L> unregisterListenerMethod) {
        this.zajz = registerListenerMethod;
        this.zaka = unregisterListenerMethod;
    }

    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, L> {
        private boolean zajw;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<Void>> zakb;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<Boolean>> zakc;
        private ListenerHolder<L> zakd;
        private Feature[] zake;

        private Builder() {
            this.zajw = true;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, L> register(BiConsumer<A, TaskCompletionSource<Void>> biConsumer) {
            RemoteCall<A, TaskCompletionSource<Void>> remoteCall;
            new zaby(biConsumer);
            this.zakb = remoteCall;
            return this;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, L> unregister(BiConsumer<A, TaskCompletionSource<Boolean>> biConsumer) {
            RemoteCall<A, TaskCompletionSource<Void>> remoteCall;
            BiConsumer<A, TaskCompletionSource<Boolean>> biConsumer2 = biConsumer;
            new zabz(this);
            this.zakb = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> register(RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            this.zakb = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> unregister(RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            this.zakc = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> withHolder(ListenerHolder<L> listenerHolder) {
            this.zakd = listenerHolder;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> setFeatures(Feature[] featureArr) {
            this.zake = featureArr;
            return this;
        }

        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(boolean z) {
            this.zajw = z;
            return this;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.android.gms.common.annotation.KeepForSdk
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.common.api.internal.RegistrationMethods<A, L> build() {
            /*
                r10 = this;
                r0 = r10
                r1 = r0
                com.google.android.gms.common.api.internal.RemoteCall<A, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r1 = r1.zakb
                if (r1 == 0) goto L_0x0052
                r1 = 1
            L_0x0007:
                java.lang.String r2 = "Must set register function"
                com.google.android.gms.common.internal.Preconditions.checkArgument(r1, r2)
                r1 = r0
                com.google.android.gms.common.api.internal.RemoteCall<A, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Boolean>> r1 = r1.zakc
                if (r1 == 0) goto L_0x0054
                r1 = 1
            L_0x0013:
                java.lang.String r2 = "Must set unregister function"
                com.google.android.gms.common.internal.Preconditions.checkArgument(r1, r2)
                r1 = r0
                com.google.android.gms.common.api.internal.ListenerHolder<L> r1 = r1.zakd
                if (r1 == 0) goto L_0x0056
                r1 = 1
            L_0x001f:
                java.lang.String r2 = "Must set holder"
                com.google.android.gms.common.internal.Preconditions.checkArgument(r1, r2)
                com.google.android.gms.common.api.internal.RegistrationMethods r1 = new com.google.android.gms.common.api.internal.RegistrationMethods
                r9 = r1
                r1 = r9
                r2 = r9
                com.google.android.gms.common.api.internal.zaca r3 = new com.google.android.gms.common.api.internal.zaca
                r9 = r3
                r3 = r9
                r4 = r9
                r5 = r0
                r6 = r0
                com.google.android.gms.common.api.internal.ListenerHolder<L> r6 = r6.zakd
                r7 = r0
                com.google.android.gms.common.Feature[] r7 = r7.zake
                r8 = r0
                boolean r8 = r8.zajw
                r4.<init>(r5, r6, r7, r8)
                com.google.android.gms.common.api.internal.zacb r4 = new com.google.android.gms.common.api.internal.zacb
                r9 = r4
                r4 = r9
                r5 = r9
                r6 = r0
                r7 = r0
                com.google.android.gms.common.api.internal.ListenerHolder<L> r7 = r7.zakd
                com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r7 = r7.getListenerKey()
                r5.<init>(r6, r7)
                r5 = 0
                r2.<init>(r3, r4, r5)
                r0 = r1
                return r0
            L_0x0052:
                r1 = 0
                goto L_0x0007
            L_0x0054:
                r1 = 0
                goto L_0x0013
            L_0x0056:
                r1 = 0
                goto L_0x001f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.RegistrationMethods.Builder.build():com.google.android.gms.common.api.internal.RegistrationMethods");
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void zaa(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
            this.zakb.accept(anyClient, taskCompletionSource);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Builder(zabx zabx) {
            this();
            zabx zabx2 = zabx;
        }
    }

    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        Builder<A, L> builder;
        Builder<A, L> builder2 = builder;
        new Builder<>((zabx) null);
        return builder2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ RegistrationMethods(RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, zabx zabx) {
        this(registerListenerMethod, unregisterListenerMethod);
        zabx zabx2 = zabx;
    }
}
