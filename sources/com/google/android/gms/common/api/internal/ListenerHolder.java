package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;

@KeepForSdk
public final class ListenerHolder<L> {
    private final zaa zajj;
    private volatile L zajk;
    private final ListenerKey<L> zajl;

    @KeepForSdk
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(L l);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    @KeepForSdk
    ListenerHolder(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        zaa zaa2;
        ListenerKey<L> listenerKey;
        L l2 = l;
        new zaa(this, looper);
        this.zajj = zaa2;
        this.zajk = Preconditions.checkNotNull(l2, "Listener must not be null");
        new ListenerKey<>(l2, Preconditions.checkNotEmpty(str));
        this.zajl = listenerKey;
    }

    private final class zaa extends zap {
        private final /* synthetic */ ListenerHolder zajm;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zaa(ListenerHolder listenerHolder, Looper looper) {
            super(looper);
            this.zajm = listenerHolder;
        }

        public final void handleMessage(Message message) {
            Message message2 = message;
            Preconditions.checkArgument(message2.what == 1);
            this.zajm.notifyListenerInternal((Notifier) message2.obj);
        }
    }

    @KeepForSdk
    public static final class ListenerKey<L> {
        private final L zajk;
        private final String zajn;

        @KeepForSdk
        ListenerKey(L l, String str) {
            this.zajk = l;
            this.zajn = str;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r3 = r0
                r4 = r1
                if (r3 != r4) goto L_0x0009
                r3 = 1
                r0 = r3
            L_0x0008:
                return r0
            L_0x0009:
                r3 = r1
                boolean r3 = r3 instanceof com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey
                if (r3 != 0) goto L_0x0011
                r3 = 0
                r0 = r3
                goto L_0x0008
            L_0x0011:
                r3 = r1
                com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r3 = (com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey) r3
                r2 = r3
                r3 = r0
                L r3 = r3.zajk
                r4 = r2
                L r4 = r4.zajk
                if (r3 != r4) goto L_0x002c
                r3 = r0
                java.lang.String r3 = r3.zajn
                r4 = r2
                java.lang.String r4 = r4.zajn
                boolean r3 = r3.equals(r4)
                if (r3 == 0) goto L_0x002c
                r3 = 1
                r0 = r3
                goto L_0x0008
            L_0x002c:
                r3 = 0
                r0 = r3
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey.equals(java.lang.Object):boolean");
        }

        public final int hashCode() {
            int identityHashCode = System.identityHashCode(this.zajk);
            int i = identityHashCode;
            return (identityHashCode * 31) + this.zajn.hashCode();
        }
    }

    @KeepForSdk
    public final void notifyListener(Notifier<? super L> notifier) {
        Notifier<? super L> notifier2 = notifier;
        Object checkNotNull = Preconditions.checkNotNull(notifier2, "Notifier must not be null");
        boolean sendMessage = this.zajj.sendMessage(this.zajj.obtainMessage(1, notifier2));
    }

    @KeepForSdk
    public final boolean hasListener() {
        return this.zajk != null;
    }

    @KeepForSdk
    public final void clear() {
        this.zajk = null;
    }

    @KeepForSdk
    @NonNull
    public final ListenerKey<L> getListenerKey() {
        return this.zajl;
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    public final void notifyListenerInternal(Notifier<? super L> notifier) {
        Notifier<? super L> notifier2 = notifier;
        L l = this.zajk;
        L l2 = l;
        if (l == null) {
            notifier2.onNotifyListenerFailed();
            return;
        }
        try {
            notifier2.notifyListener(l2);
        } catch (RuntimeException e) {
            RuntimeException runtimeException = e;
            notifier2.onNotifyListenerFailed();
            throw runtimeException;
        }
    }
}
