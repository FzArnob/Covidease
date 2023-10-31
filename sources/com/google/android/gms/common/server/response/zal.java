package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryEntryCreator")
public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zal> CREATOR;
    @SafeParcelable.Field(mo25277id = 2)
    final String className;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    @SafeParcelable.Field(mo25277id = 3)
    final ArrayList<zam> zaqy;

    @SafeParcelable.Constructor
    zal(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) ArrayList<zam> arrayList) {
        this.versionCode = i;
        this.className = str;
        this.zaqy = arrayList;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zal(java.lang.String r16, java.util.Map<java.lang.String, com.google.android.gms.common.server.response.FastJsonResponse.Field<?, ?>> r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r8 = r0
            r8.<init>()
            r8 = r0
            r9 = 1
            r8.versionCode = r9
            r8 = r0
            r9 = r1
            r8.className = r9
            r8 = r0
            r9 = r2
            r14 = r9
            r9 = r14
            r10 = r14
            r3 = r10
            if (r9 != 0) goto L_0x001d
            r9 = 0
        L_0x001a:
            r8.zaqy = r9
            return
        L_0x001d:
            java.util.ArrayList r9 = new java.util.ArrayList
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()
            r4 = r9
            r9 = r3
            java.util.Set r9 = r9.keySet()
            java.util.Iterator r9 = r9.iterator()
            r5 = r9
        L_0x0030:
            r9 = r5
            boolean r9 = r9.hasNext()
            if (r9 == 0) goto L_0x0058
            r9 = r5
            java.lang.Object r9 = r9.next()
            java.lang.String r9 = (java.lang.String) r9
            r6 = r9
            com.google.android.gms.common.server.response.zam r9 = new com.google.android.gms.common.server.response.zam
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r6
            r12 = r3
            r13 = r6
            java.lang.Object r12 = r12.get(r13)
            com.google.android.gms.common.server.response.FastJsonResponse$Field r12 = (com.google.android.gms.common.server.response.FastJsonResponse.Field) r12
            r10.<init>(r11, r12)
            r7 = r9
            r9 = r4
            r10 = r7
            boolean r9 = r9.add(r10)
            goto L_0x0030
        L_0x0058:
            r9 = r4
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.zal.<init>(java.lang.String, java.util.Map):void");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel2, 2, this.className, false);
        SafeParcelWriter.writeTypedList(parcel2, 3, this.zaqy, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zal> creator;
        new zao();
        CREATOR = creator;
    }
}
