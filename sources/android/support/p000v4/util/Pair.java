package android.support.p000v4.util;

import android.support.annotation.Nullable;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* renamed from: android.support.v4.util.Pair */
public class Pair<F, S> {
    @Nullable
    public final F first;
    @Nullable
    public final S second;

    public Pair(@Nullable F first2, @Nullable S second2) {
        this.first = first2;
        this.second = second2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair) o;
        return ObjectsCompat.equals(p.first, this.first) && ObjectsCompat.equals(p.second, this.second);
    }

    public int hashCode() {
        return (this.first == null ? 0 : this.first.hashCode()) ^ (this.second == null ? 0 : this.second.hashCode());
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("Pair{").append(String.valueOf(this.first)).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(String.valueOf(this.second)).append("}").toString();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <A, B> android.support.p000v4.util.Pair<A, B> create(@android.support.annotation.Nullable A r7, @android.support.annotation.Nullable B r8) {
        /*
            r0 = r7
            r1 = r8
            android.support.v4.util.Pair r2 = new android.support.v4.util.Pair
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.util.Pair.create(java.lang.Object, java.lang.Object):android.support.v4.util.Pair");
    }
}
