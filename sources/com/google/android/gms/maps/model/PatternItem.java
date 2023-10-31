package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR;
    private static final String TAG = PatternItem.class.getSimpleName();
    @SafeParcelable.Field(getter = "getType", mo25277id = 2)
    private final int type;
    @Nullable
    @SafeParcelable.Field(getter = "getLength", mo25277id = 3)
    private final Float zzdv;

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(mo25280id = 2) int i, @Nullable @SafeParcelable.Param(mo25280id = 3) Float f) {
        StringBuilder sb;
        int i2 = i;
        Float f2 = f;
        boolean z = i2 == 1 || (f2 != null && f2.floatValue() >= 0.0f);
        String valueOf = String.valueOf(f2);
        new StringBuilder(45 + String.valueOf(valueOf).length());
        Preconditions.checkArgument(z, sb.append("Invalid PatternItem: type=").append(i2).append(" length=").append(valueOf).toString());
        this.type = i2;
        this.zzdv = f2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel2, 3, this.zzdv, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.type);
        Object[] objArr2 = objArr;
        objArr2[1] = this.zzdv;
        return Objects.hashCode(objArr2);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.PatternItem
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.PatternItem r3 = (com.google.android.gms.maps.model.PatternItem) r3
            r2 = r3
            r3 = r0
            int r3 = r3.type
            r4 = r2
            int r4 = r4.type
            if (r3 != r4) goto L_0x002c
            r3 = r0
            java.lang.Float r3 = r3.zzdv
            r4 = r2
            java.lang.Float r4 = r4.zzdv
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x002c
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x002c:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.PatternItem.equals(java.lang.Object):boolean");
    }

    public String toString() {
        StringBuilder sb;
        int i = this.type;
        String valueOf = String.valueOf(this.zzdv);
        new StringBuilder(39 + String.valueOf(valueOf).length());
        return sb.append("[PatternItem: type=").append(i).append(" length=").append(valueOf).append("]").toString();
    }

    @Nullable
    static List<PatternItem> zza(@Nullable List<PatternItem> list) {
        List<PatternItem> list2;
        PatternItem patternItem;
        PatternItem patternItem2;
        PatternItem patternItem3;
        PatternItem patternItem4;
        StringBuilder sb;
        List<PatternItem> list3 = list;
        if (list3 == null) {
            return null;
        }
        new ArrayList(list3.size());
        List<PatternItem> list4 = list2;
        for (PatternItem next : list3) {
            List<PatternItem> list5 = list4;
            if (next != null) {
                PatternItem patternItem5 = next;
                PatternItem patternItem6 = patternItem5;
                switch (patternItem5.type) {
                    case 0:
                        patternItem2 = patternItem4;
                        new Dash(patternItem6.zzdv.floatValue());
                        break;
                    case 1:
                        patternItem2 = patternItem3;
                        new Dot();
                        break;
                    case 2:
                        patternItem2 = patternItem;
                        new Gap(patternItem6.zzdv.floatValue());
                        break;
                    default:
                        String str = TAG;
                        int i = patternItem6.type;
                        new StringBuilder(37);
                        int w = Log.w(str, sb.append("Unknown PatternItem type: ").append(i).toString());
                        patternItem2 = patternItem6;
                        break;
                }
            } else {
                patternItem2 = null;
            }
            boolean add = list5.add(patternItem2);
        }
        return list4;
    }

    static {
        Parcelable.Creator<PatternItem> creator;
        new zzi();
        CREATOR = creator;
    }
}
