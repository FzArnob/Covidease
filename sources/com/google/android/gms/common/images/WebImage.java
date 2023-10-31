package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getWidth", mo25277id = 3)
    private final int zane;
    @SafeParcelable.Field(getter = "getHeight", mo25277id = 4)
    private final int zanf;
    @SafeParcelable.Field(getter = "getUrl", mo25277id = 2)
    private final Uri zang;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) Uri uri, @SafeParcelable.Param(mo25280id = 3) int i2, @SafeParcelable.Param(mo25280id = 4) int i3) {
        this.zalf = i;
        this.zang = uri;
        this.zane = i2;
        this.zanf = i3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebImage(android.net.Uri r11, int r12, int r13) throws java.lang.IllegalArgumentException {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = 1
            r6 = r1
            r7 = r2
            r8 = r3
            r4.<init>(r5, r6, r7, r8)
            r4 = r1
            if (r4 != 0) goto L_0x001b
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r9 = r4
            r4 = r9
            r5 = r9
            java.lang.String r6 = "url cannot be null"
            r5.<init>(r6)
            throw r4
        L_0x001b:
            r4 = r2
            if (r4 < 0) goto L_0x0021
            r4 = r3
            if (r4 >= 0) goto L_0x002d
        L_0x0021:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r9 = r4
            r4 = r9
            r5 = r9
            java.lang.String r6 = "width and height must not be negative"
            r5.<init>(r6)
            throw r4
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.<init>(android.net.Uri, int, int):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebImage(org.json.JSONObject r9) throws java.lang.IllegalArgumentException {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            android.net.Uri r3 = zaa(r3)
            r4 = r1
            java.lang.String r5 = "width"
            r6 = 0
            int r4 = r4.optInt(r5, r6)
            r5 = r1
            java.lang.String r6 = "height"
            r7 = 0
            int r5 = r5.optInt(r6, r7)
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.<init>(org.json.JSONObject):void");
    }

    private static Uri zaa(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        Uri uri = null;
        if (jSONObject2.has(ImagesContract.URL)) {
            try {
                uri = Uri.parse(jSONObject2.getString(ImagesContract.URL));
            } catch (JSONException e) {
            }
        }
        return uri;
    }

    public final Uri getUrl() {
        return this.zang;
    }

    public final int getWidth() {
        return this.zane;
    }

    public final int getHeight() {
        return this.zanf;
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.zane);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zanf);
        Object[] objArr3 = objArr2;
        objArr3[2] = this.zang.toString();
        return String.format(locale, "Image %dx%d %s", objArr3);
    }

    @KeepForSdk
    public final JSONObject toJson() {
        JSONObject jSONObject;
        new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        try {
            JSONObject put = jSONObject2.put(ImagesContract.URL, this.zang.toString());
            JSONObject put2 = jSONObject2.put("width", this.zane);
            JSONObject put3 = jSONObject2.put("height", this.zanf);
        } catch (JSONException e) {
        }
        return jSONObject2;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
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
            if (r3 == 0) goto L_0x0011
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.images.WebImage
            if (r3 != 0) goto L_0x0014
        L_0x0011:
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0014:
            r3 = r1
            com.google.android.gms.common.images.WebImage r3 = (com.google.android.gms.common.images.WebImage) r3
            r2 = r3
            r3 = r0
            android.net.Uri r3 = r3.zang
            r4 = r2
            android.net.Uri r4 = r4.zang
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0037
            r3 = r0
            int r3 = r3.zane
            r4 = r2
            int r4 = r4.zane
            if (r3 != r4) goto L_0x0037
            r3 = r0
            int r3 = r3.zanf
            r4 = r2
            int r4 = r4.zanf
            if (r3 != r4) goto L_0x0037
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0037:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = this.zang;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zane);
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(this.zanf);
        return Objects.hashCode(objArr3);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel2, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel2, 3, getWidth());
        SafeParcelWriter.writeInt(parcel2, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<WebImage> creator;
        new zae();
        CREATOR = creator;
    }
}
