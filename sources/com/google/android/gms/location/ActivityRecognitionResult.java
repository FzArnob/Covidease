package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "ActivityRecognitionResultCreator")
@SafeParcelable.Reserved({1000})
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR;
    @SafeParcelable.Field(mo25277id = 5)
    private Bundle extras;
    @SafeParcelable.Field(mo25277id = 1)
    private List<DetectedActivity> zze;
    @SafeParcelable.Field(mo25277id = 2)
    private long zzf;
    @SafeParcelable.Field(mo25277id = 3)
    private long zzg;
    @SafeParcelable.Field(mo25277id = 4)
    private int zzh;

    static {
        Parcelable.Creator<ActivityRecognitionResult> creator;
        new zzb();
        CREATOR = creator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, (Bundle) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this((List<DetectedActivity>) Collections.singletonList(detectedActivity), j, j2, 0, (Bundle) null);
        int i2 = i;
        Bundle bundle2 = bundle;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, (Bundle) null);
    }

    @SafeParcelable.Constructor
    public ActivityRecognitionResult(@SafeParcelable.Param(mo25280id = 1) List<DetectedActivity> list, @SafeParcelable.Param(mo25280id = 2) long j, @SafeParcelable.Param(mo25280id = 3) long j2, @SafeParcelable.Param(mo25280id = 4) int i, @SafeParcelable.Param(mo25280id = 5) Bundle bundle) {
        List<DetectedActivity> list2 = list;
        long j3 = j;
        long j4 = j2;
        int i2 = i;
        Bundle bundle2 = bundle;
        Preconditions.checkArgument(list2 != null && list2.size() > 0, "Must have at least 1 detected activity");
        Preconditions.checkArgument(j3 > 0 && j4 > 0, "Must set times");
        this.zze = list2;
        this.zzf = j3;
        this.zzg = j4;
        this.zzh = i2;
        this.extras = bundle2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.location.ActivityRecognitionResult extractResult(android.content.Intent r9) {
        /*
            r0 = r9
            r5 = r0
            r8 = r5
            r5 = r8
            r6 = r8
            r3 = r6
            boolean r5 = hasResult(r5)
            if (r5 == 0) goto L_0x003d
            r5 = r3
            android.os.Bundle r5 = r5.getExtras()
            java.lang.String r6 = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT"
            java.lang.Object r5 = r5.get(r6)
            r8 = r5
            r5 = r8
            r6 = r8
            r4 = r6
            boolean r5 = r5 instanceof byte[]
            if (r5 == 0) goto L_0x0034
            r5 = r4
            byte[] r5 = (byte[]) r5
            android.os.Parcelable$Creator<com.google.android.gms.location.ActivityRecognitionResult> r6 = CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromBytes(r5, r6)
            com.google.android.gms.location.ActivityRecognitionResult r5 = (com.google.android.gms.location.ActivityRecognitionResult) r5
        L_0x002b:
            r8 = r5
            r5 = r8
            r6 = r8
            r1 = r6
            if (r5 == 0) goto L_0x003f
            r5 = r1
            r0 = r5
        L_0x0033:
            return r0
        L_0x0034:
            r5 = r4
            boolean r5 = r5 instanceof com.google.android.gms.location.ActivityRecognitionResult
            if (r5 == 0) goto L_0x003d
            r5 = r4
            com.google.android.gms.location.ActivityRecognitionResult r5 = (com.google.android.gms.location.ActivityRecognitionResult) r5
            goto L_0x002b
        L_0x003d:
            r5 = 0
            goto L_0x002b
        L_0x003f:
            r5 = r0
            java.util.List r5 = zza(r5)
            r8 = r5
            r5 = r8
            r6 = r8
            r2 = r6
            if (r5 == 0) goto L_0x0051
            r5 = r2
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0054
        L_0x0051:
            r5 = 0
            r0 = r5
            goto L_0x0033
        L_0x0054:
            r5 = r2
            r6 = r2
            int r6 = r6.size()
            r7 = 1
            int r6 = r6 + -1
            java.lang.Object r5 = r5.get(r6)
            com.google.android.gms.location.ActivityRecognitionResult r5 = (com.google.android.gms.location.ActivityRecognitionResult) r5
            r0 = r5
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.extractResult(android.content.Intent):com.google.android.gms.location.ActivityRecognitionResult");
    }

    public static boolean hasResult(Intent intent) {
        Intent intent2 = intent;
        if (intent2 == null) {
            return false;
        }
        Intent intent3 = intent2;
        if (intent3 == null ? false : intent3.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List<ActivityRecognitionResult> zza = zza(intent2);
        return zza != null && !zza.isEmpty();
    }

    @Nullable
    private static List<ActivityRecognitionResult> zza(Intent intent) {
        Intent intent2 = intent;
        Intent intent3 = intent2;
        if (!(intent3 == null ? false : intent3.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST"))) {
            return null;
        }
        return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent2, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
    }

    private static boolean zza(Bundle bundle, Bundle bundle2) {
        Bundle bundle3 = bundle;
        Bundle bundle4 = bundle2;
        if (bundle3 == null && bundle4 == null) {
            return true;
        }
        if ((bundle3 == null && bundle4 != null) || (bundle3 != null && bundle4 == null)) {
            return false;
        }
        if (bundle3.size() != bundle4.size()) {
            return false;
        }
        for (String str : bundle3.keySet()) {
            if (!bundle4.containsKey(str)) {
                return false;
            }
            if (bundle3.get(str) == null) {
                if (bundle4.get(str) != null) {
                    return false;
                }
            } else if (bundle3.get(str) instanceof Bundle) {
                if (!zza(bundle3.getBundle(str), bundle4.getBundle(str))) {
                    return false;
                }
            } else if (!bundle3.get(str).equals(bundle4.get(str))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r1 = r8
            r2 = r9
            r4 = r1
            r5 = r2
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r1 = r4
        L_0x0008:
            return r1
        L_0x0009:
            r4 = r2
            if (r4 == 0) goto L_0x0018
            r4 = r1
            java.lang.Class r4 = r4.getClass()
            r5 = r2
            java.lang.Class r5 = r5.getClass()
            if (r4 == r5) goto L_0x001b
        L_0x0018:
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x001b:
            r4 = r2
            com.google.android.gms.location.ActivityRecognitionResult r4 = (com.google.android.gms.location.ActivityRecognitionResult) r4
            r3 = r4
            r4 = r1
            long r4 = r4.zzf
            r6 = r3
            long r6 = r6.zzf
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0056
            r4 = r1
            long r4 = r4.zzg
            r6 = r3
            long r6 = r6.zzg
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0056
            r4 = r1
            int r4 = r4.zzh
            r5 = r3
            int r5 = r5.zzh
            if (r4 != r5) goto L_0x0056
            r4 = r1
            java.util.List<com.google.android.gms.location.DetectedActivity> r4 = r4.zze
            r5 = r3
            java.util.List<com.google.android.gms.location.DetectedActivity> r5 = r5.zze
            boolean r4 = com.google.android.gms.common.internal.Objects.equal(r4, r5)
            if (r4 == 0) goto L_0x0056
            r4 = r1
            android.os.Bundle r4 = r4.extras
            r5 = r3
            android.os.Bundle r5 = r5.extras
            boolean r4 = zza(r4, r5)
            if (r4 == 0) goto L_0x0056
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0056:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.equals(java.lang.Object):boolean");
    }

    public int getActivityConfidence(int i) {
        int i2 = i;
        for (DetectedActivity next : this.zze) {
            DetectedActivity detectedActivity = next;
            if (next.getType() == i2) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }

    public DetectedActivity getMostProbableActivity() {
        return this.zze.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zze;
    }

    public long getTime() {
        return this.zzf;
    }

    public int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = Long.valueOf(this.zzf);
        Object[] objArr2 = objArr;
        objArr2[1] = Long.valueOf(this.zzg);
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(this.zzh);
        Object[] objArr4 = objArr3;
        objArr4[3] = this.zze;
        Object[] objArr5 = objArr4;
        objArr5[4] = this.extras;
        return Objects.hashCode(objArr5);
    }

    public String toString() {
        StringBuilder sb;
        String valueOf = String.valueOf(this.zze);
        long j = this.zzf;
        long j2 = this.zzg;
        new StringBuilder(124 + String.valueOf(valueOf).length());
        return sb.append("ActivityRecognitionResult [probableActivities=").append(valueOf).append(", timeMillis=").append(j).append(", elapsedRealtimeMillis=").append(j2).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 1, this.zze, false);
        SafeParcelWriter.writeLong(parcel2, 2, this.zzf);
        SafeParcelWriter.writeLong(parcel2, 3, this.zzg);
        SafeParcelWriter.writeInt(parcel2, 4, this.zzh);
        SafeParcelWriter.writeBundle(parcel2, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
