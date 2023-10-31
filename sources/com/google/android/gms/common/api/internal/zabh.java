package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import com.google.android.gms.internal.base.zam;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public final class zabh {
    private static final ExecutorService zahw;

    public static ExecutorService zabb() {
        return zahw;
    }

    static {
        ThreadFactory threadFactory;
        new NumberedThreadFactory("GAC_Executor");
        zahw = zam.zacv().zaa(2, threadFactory, 9);
    }
}
