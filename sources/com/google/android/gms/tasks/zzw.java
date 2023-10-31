package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzw implements Continuation<Void, List<TResult>> {
    private final /* synthetic */ Collection zzae;

    zzw(Collection collection) {
        this.zzae = collection;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        List list;
        Task task2 = task;
        if (this.zzae.size() == 0) {
            return Collections.emptyList();
        }
        new ArrayList();
        List list2 = list;
        for (Task result : this.zzae) {
            boolean add = list2.add(result.getResult());
        }
        return list2;
    }
}
