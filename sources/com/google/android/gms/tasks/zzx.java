package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class zzx implements Continuation<Void, Task<List<Task<?>>>> {
    private final /* synthetic */ Collection zzae;

    zzx(Collection collection) {
        this.zzae = collection;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        List list;
        Task task2 = task;
        new ArrayList();
        List list2 = list;
        List list3 = list2;
        boolean addAll = list2.addAll(this.zzae);
        return Tasks.forResult(list3);
    }
}
