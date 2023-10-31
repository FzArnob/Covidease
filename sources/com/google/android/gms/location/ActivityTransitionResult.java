package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "ActivityTransitionResultCreator")
@SafeParcelable.Reserved({1000})
public class ActivityTransitionResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActivityTransitionResult> CREATOR;
    @SafeParcelable.Field(getter = "getTransitionEvents", mo25277id = 1)
    private final List<ActivityTransitionEvent> zzn;

    static {
        Parcelable.Creator<ActivityTransitionResult> creator;
        new zzg();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public ActivityTransitionResult(@SafeParcelable.Param(mo25280id = 1) List<ActivityTransitionEvent> list) {
        List<ActivityTransitionEvent> list2 = list;
        Object checkNotNull = Preconditions.checkNotNull(list2, "transitionEvents list can't be null.");
        List<ActivityTransitionEvent> list3 = list2;
        List<ActivityTransitionEvent> list4 = list3;
        if (!list3.isEmpty()) {
            for (int i = 1; i < list4.size(); i++) {
                Preconditions.checkArgument(list4.get(i).getElapsedRealTimeNanos() >= list4.get(i + -1).getElapsedRealTimeNanos());
            }
        }
        this.zzn = Collections.unmodifiableList(list2);
    }

    @Nullable
    public static ActivityTransitionResult extractResult(Intent intent) {
        Intent intent2 = intent;
        if (!hasResult(intent2)) {
            return null;
        }
        return (ActivityTransitionResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent2, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", CREATOR);
    }

    public static boolean hasResult(@Nullable Intent intent) {
        Intent intent2 = intent;
        if (intent2 == null) {
            return false;
        }
        return intent2.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            if (r2 != r3) goto L_0x0009
            r2 = 1
            r0 = r2
        L_0x0008:
            return r0
        L_0x0009:
            r2 = r1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            java.lang.Class r2 = r2.getClass()
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            if (r2 == r3) goto L_0x001b
        L_0x0018:
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x001b:
            r2 = r0
            java.util.List<com.google.android.gms.location.ActivityTransitionEvent> r2 = r2.zzn
            r3 = r1
            com.google.android.gms.location.ActivityTransitionResult r3 = (com.google.android.gms.location.ActivityTransitionResult) r3
            java.util.List<com.google.android.gms.location.ActivityTransitionEvent> r3 = r3.zzn
            boolean r2 = r2.equals(r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityTransitionResult.equals(java.lang.Object):boolean");
    }

    public List<ActivityTransitionEvent> getTransitionEvents() {
        return this.zzn;
    }

    public int hashCode() {
        return this.zzn.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 1, getTransitionEvents(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
