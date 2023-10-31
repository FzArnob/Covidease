package com.google.android.gms.common;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

final class zaa implements Continuation<Map<zai<?>, String>, Void> {
    zaa(GoogleApiAvailability googleApiAvailability) {
        GoogleApiAvailability googleApiAvailability2 = googleApiAvailability;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        Object result = task.getResult();
        return null;
    }
}
