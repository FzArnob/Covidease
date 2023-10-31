package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zaqv;
    @SafeParcelable.Field(getter = "getSerializedDictionary", mo25277id = 2)
    private final ArrayList<zal> zaqw;
    @SafeParcelable.Field(getter = "getRootClassName", mo25277id = 3)
    private final String zaqx;

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zak(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 1) int r24, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 2) java.util.ArrayList<com.google.android.gms.common.server.response.zal> r25, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 3) java.lang.String r26) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r16 = r2
            r16.<init>()
            r16 = r2
            r17 = r3
            r0 = r17
            r1 = r16
            r1.zalf = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.zaqw = r0
            r16 = r2
            r17 = r4
            r6 = r17
            java.util.HashMap r17 = new java.util.HashMap
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r7 = r17
            r17 = r6
            int r17 = r17.size()
            r8 = r17
            r17 = 0
            r9 = r17
        L_0x0040:
            r17 = r9
            r18 = r8
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x00c1
            r17 = r6
            r18 = r9
            java.lang.Object r17 = r17.get(r18)
            com.google.android.gms.common.server.response.zal r17 = (com.google.android.gms.common.server.response.zal) r17
            r10 = r17
            r17 = r7
            r18 = r10
            r0 = r18
            java.lang.String r0 = r0.className
            r18 = r0
            r19 = r10
            r11 = r19
            java.util.HashMap r19 = new java.util.HashMap
            r22 = r19
            r19 = r22
            r20 = r22
            r20.<init>()
            r12 = r19
            r19 = r11
            r0 = r19
            java.util.ArrayList<com.google.android.gms.common.server.response.zam> r0 = r0.zaqy
            r19 = r0
            int r19 = r19.size()
            r13 = r19
            r19 = 0
            r14 = r19
        L_0x0083:
            r19 = r14
            r20 = r13
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x00b8
            r19 = r11
            r0 = r19
            java.util.ArrayList<com.google.android.gms.common.server.response.zam> r0 = r0.zaqy
            r19 = r0
            r20 = r14
            java.lang.Object r19 = r19.get(r20)
            com.google.android.gms.common.server.response.zam r19 = (com.google.android.gms.common.server.response.zam) r19
            r15 = r19
            r19 = r12
            r20 = r15
            r0 = r20
            java.lang.String r0 = r0.zaqz
            r20 = r0
            r21 = r15
            r0 = r21
            com.google.android.gms.common.server.response.FastJsonResponse$Field<?, ?> r0 = r0.zara
            r21 = r0
            java.lang.Object r19 = r19.put(r20, r21)
            int r14 = r14 + 1
            goto L_0x0083
        L_0x00b8:
            r19 = r12
            java.lang.Object r17 = r17.put(r18, r19)
            int r9 = r9 + 1
            goto L_0x0040
        L_0x00c1:
            r17 = r7
            r0 = r17
            r1 = r16
            r1.zaqv = r0
            r16 = r2
            r17 = r5
            java.lang.Object r17 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r17)
            java.lang.String r17 = (java.lang.String) r17
            r0 = r17
            r1 = r16
            r1.zaqx = r0
            r16 = r2
            r16.zacr()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.zak.<init>(int, java.util.ArrayList, java.lang.String):void");
    }

    public final void zacr() {
        for (String str : this.zaqv.keySet()) {
            Map map = this.zaqv.get(str);
            Map map2 = map;
            for (String str2 : map.keySet()) {
                ((FastJsonResponse.Field) map2.get(str2)).zaa(this);
            }
        }
    }

    public final void zacs() {
        HashMap hashMap;
        for (String next : this.zaqv.keySet()) {
            Map map = this.zaqv.get(next);
            new HashMap();
            HashMap hashMap2 = hashMap;
            for (String str : map.keySet()) {
                Object put = hashMap2.put(str, ((FastJsonResponse.Field) map.get(str)).zacl());
            }
            Map<String, FastJsonResponse.Field<?, ?>> put2 = this.zaqv.put(next, hashMap2);
        }
    }

    public zak(Class<? extends FastJsonResponse> cls) {
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> hashMap;
        this.zalf = 1;
        this.zaqw = null;
        new HashMap<>();
        this.zaqv = hashMap;
        this.zaqx = cls.getCanonicalName();
    }

    public final void zaa(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        Map<String, FastJsonResponse.Field<?, ?>> put = this.zaqv.put(cls.getCanonicalName(), map);
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> zai(String str) {
        return this.zaqv.get(str);
    }

    public final boolean zaa(Class<? extends FastJsonResponse> cls) {
        return this.zaqv.containsKey(cls.getCanonicalName());
    }

    public final String zact() {
        return this.zaqx;
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        for (String next : this.zaqv.keySet()) {
            StringBuilder append = sb2.append(next).append(":\n");
            Map map = this.zaqv.get(next);
            Map map2 = map;
            for (String str : map.keySet()) {
                StringBuilder append2 = sb2.append("  ").append(str).append(": ");
                StringBuilder append3 = sb2.append(map2.get(str));
            }
        }
        return sb2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ArrayList arrayList;
        Object obj;
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        Parcel parcel3 = parcel2;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (String next : this.zaqv.keySet()) {
            new zal(next, this.zaqv.get(next));
            boolean add = arrayList2.add(obj);
        }
        SafeParcelWriter.writeTypedList(parcel3, 2, arrayList2, false);
        SafeParcelWriter.writeString(parcel2, 3, this.zaqx, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zak> creator;
        new zan();
        CREATOR = creator;
    }
}
