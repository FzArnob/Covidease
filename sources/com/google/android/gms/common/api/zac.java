package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

@ShowFirstParty
public abstract class zac {
    private static final Object sLock;
    @GuardedBy("sLock")
    private static final Map<Object, zac> zack;

    public zac() {
    }

    public abstract void remove(int i);

    static {
        Map<Object, zac> map;
        Object obj;
        new WeakHashMap();
        zack = map;
        new Object();
        sLock = obj;
    }
}
