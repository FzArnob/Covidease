package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public final class Objects {
    @KeepForSdk
    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        Object obj3 = obj;
        Object obj4 = obj2;
        return obj3 == obj4 || (obj3 != null && obj3.equals(obj4));
    }

    @KeepForSdk
    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @KeepForSdk
    public static ToStringHelper toStringHelper(Object obj) {
        ToStringHelper toStringHelper;
        new ToStringHelper(obj, (zzq) null);
        return toStringHelper;
    }

    @KeepForSdk
    public static final class ToStringHelper {
        private final List<String> zzer;
        private final Object zzes;

        private ToStringHelper(Object obj) {
            List<String> list;
            this.zzes = Preconditions.checkNotNull(obj);
            new ArrayList();
            this.zzer = list;
        }

        @KeepForSdk
        public final ToStringHelper add(String str, @Nullable Object obj) {
            StringBuilder sb;
            List<String> list = this.zzer;
            String str2 = (String) Preconditions.checkNotNull(str);
            String valueOf = String.valueOf(obj);
            new StringBuilder(1 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
            boolean add = list.add(sb.append(str2).append("=").append(valueOf).toString());
            return this;
        }

        @KeepForSdk
        public final String toString() {
            StringBuilder sb;
            new StringBuilder(100);
            StringBuilder append = sb.append(this.zzes.getClass().getSimpleName()).append('{');
            int size = this.zzer.size();
            for (int i = 0; i < size; i++) {
                StringBuilder append2 = append.append(this.zzer.get(i));
                if (i < size - 1) {
                    StringBuilder append3 = append.append(", ");
                }
            }
            return append.append('}').toString();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ ToStringHelper(Object obj, zzq zzq) {
            this(obj);
            zzq zzq2 = zzq;
        }
    }

    private Objects() {
        Throwable th;
        Throwable th2 = th;
        new AssertionError("Uninstantiable");
        throw th2;
    }
}
