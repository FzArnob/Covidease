package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@KeepForSdk
public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @KeepForSdk
    public FastSafeParcelableJsonResponse() {
    }

    @VisibleForTesting
    public Object getValueObject(String str) {
        String str2 = str;
        return null;
    }

    @VisibleForTesting
    public boolean isPrimitiveFieldSet(String str) {
        String str2 = str;
        return false;
    }

    @KeepForSdk
    public byte[] toByteArray() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public final int describeContents() {
        return 0;
    }

    public int hashCode() {
        int i = 0;
        for (FastJsonResponse.Field next : getFieldMappings().values()) {
            if (isFieldSet(next)) {
                i = (i * 31) + getFieldValue(next).hashCode();
            }
        }
        return i;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r5 = r0
            r6 = r1
            if (r5 != r6) goto L_0x0009
            r5 = 1
            r0 = r5
        L_0x0008:
            return r0
        L_0x0009:
            r5 = r0
            java.lang.Class r5 = r5.getClass()
            r6 = r1
            boolean r5 = r5.isInstance(r6)
            if (r5 != 0) goto L_0x0018
            r5 = 0
            r0 = r5
            goto L_0x0008
        L_0x0018:
            r5 = r1
            com.google.android.gms.common.server.response.FastJsonResponse r5 = (com.google.android.gms.common.server.response.FastJsonResponse) r5
            r2 = r5
            r5 = r0
            java.util.Map r5 = r5.getFieldMappings()
            java.util.Collection r5 = r5.values()
            java.util.Iterator r5 = r5.iterator()
            r3 = r5
        L_0x002a:
            r5 = r3
            boolean r5 = r5.hasNext()
            if (r5 == 0) goto L_0x006d
            r5 = r3
            java.lang.Object r5 = r5.next()
            com.google.android.gms.common.server.response.FastJsonResponse$Field r5 = (com.google.android.gms.common.server.response.FastJsonResponse.Field) r5
            r4 = r5
            r5 = r0
            r6 = r4
            boolean r5 = r5.isFieldSet(r6)
            if (r5 == 0) goto L_0x0061
            r5 = r2
            r6 = r4
            boolean r5 = r5.isFieldSet(r6)
            if (r5 == 0) goto L_0x005e
            r5 = r0
            r6 = r4
            java.lang.Object r5 = r5.getFieldValue(r6)
            r6 = r2
            r7 = r4
            java.lang.Object r6 = r6.getFieldValue(r7)
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x006c
            r5 = 0
            r0 = r5
            goto L_0x0008
        L_0x005e:
            r5 = 0
            r0 = r5
            goto L_0x0008
        L_0x0061:
            r5 = r2
            r6 = r4
            boolean r5 = r5.isFieldSet(r6)
            if (r5 == 0) goto L_0x006c
            r5 = 0
            r0 = r5
            goto L_0x0008
        L_0x006c:
            goto L_0x002a
        L_0x006d:
            r5 = 1
            r0 = r5
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse.equals(java.lang.Object):boolean");
    }
}
