package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final /* synthetic */ class zaa implements Comparator {
    static final Comparator zaq;

    static {
        Comparator comparator;
        new zaa();
        zaq = comparator;
    }

    private zaa() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
