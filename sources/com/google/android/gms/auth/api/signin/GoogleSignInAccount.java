package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR;
    @VisibleForTesting
    private static Clock zae = DefaultClock.getInstance();
    @SafeParcelable.Field(getter = "getId", mo25277id = 2)
    private String mId;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getIdToken", mo25277id = 3)
    private String zaf;
    @SafeParcelable.Field(getter = "getEmail", mo25277id = 4)
    private String zag;
    @SafeParcelable.Field(getter = "getDisplayName", mo25277id = 5)
    private String zah;
    @SafeParcelable.Field(getter = "getPhotoUrl", mo25277id = 6)
    private Uri zai;
    @SafeParcelable.Field(getter = "getServerAuthCode", mo25277id = 7)
    private String zaj;
    @SafeParcelable.Field(getter = "getExpirationTimeSecs", mo25277id = 8)
    private long zak;
    @SafeParcelable.Field(getter = "getObfuscatedIdentifier", mo25277id = 9)
    private String zal;
    @SafeParcelable.Field(mo25277id = 10)
    private List<Scope> zam;
    @SafeParcelable.Field(getter = "getGivenName", mo25277id = 11)
    private String zan;
    @SafeParcelable.Field(getter = "getFamilyName", mo25277id = 12)
    private String zao;
    private Set<Scope> zap;

    @Nullable
    public static GoogleSignInAccount zaa(@Nullable String str) throws JSONException {
        JSONObject jSONObject;
        Set set;
        Object obj;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        new JSONObject(str2);
        JSONObject jSONObject2 = jSONObject;
        Uri uri = null;
        String optString = jSONObject2.optString("photoUrl", (String) null);
        String str3 = optString;
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(str3);
        }
        long parseLong = Long.parseLong(jSONObject2.getString("expirationTime"));
        new HashSet();
        Set set2 = set;
        JSONArray jSONArray = jSONObject2.getJSONArray("grantedScopes");
        JSONArray jSONArray2 = jSONArray;
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            new Scope(jSONArray2.getString(i));
            boolean add = set2.add(obj);
        }
        GoogleSignInAccount zaa = zaa(jSONObject2.optString("id"), jSONObject2.optString("tokenId", (String) null), jSONObject2.optString("email", (String) null), jSONObject2.optString("displayName", (String) null), jSONObject2.optString("givenName", (String) null), jSONObject2.optString("familyName", (String) null), uri, Long.valueOf(parseLong), jSONObject2.getString("obfuscatedIdentifier"), set2);
        GoogleSignInAccount googleSignInAccount = zaa;
        zaa.zaj = jSONObject2.optString("serverAuthCode", (String) null);
        return googleSignInAccount;
    }

    private static GoogleSignInAccount zaa(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set<Scope> set) {
        GoogleSignInAccount googleSignInAccount;
        List list;
        String str8 = str;
        String str9 = str2;
        String str10 = str3;
        String str11 = str4;
        String str12 = str5;
        String str13 = str6;
        Uri uri2 = uri;
        Long l2 = l;
        String str14 = str7;
        Set<Scope> set2 = set;
        if (l2 == null) {
            l2 = Long.valueOf(zae.currentTimeMillis() / 1000);
        }
        new ArrayList((Collection) Preconditions.checkNotNull(set2));
        new GoogleSignInAccount(3, str8, str9, str10, str11, uri2, (String) null, l2.longValue(), Preconditions.checkNotEmpty(str14), list, str12, str13);
        return googleSignInAccount;
    }

    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        Account account;
        Set set;
        new Account("<<default account>>", AccountType.GOOGLE);
        new HashSet();
        Account account2 = account;
        return zaa((String) null, (String) null, account2.name, (String) null, (String) null, (String) null, (Uri) null, 0L, account2.name, set);
    }

    @SafeParcelable.Constructor
    GoogleSignInAccount(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) String str2, @SafeParcelable.Param(mo25280id = 4) String str3, @SafeParcelable.Param(mo25280id = 5) String str4, @SafeParcelable.Param(mo25280id = 6) Uri uri, @SafeParcelable.Param(mo25280id = 7) String str5, @SafeParcelable.Param(mo25280id = 8) long j, @SafeParcelable.Param(mo25280id = 9) String str6, @SafeParcelable.Param(mo25280id = 10) List<Scope> list, @SafeParcelable.Param(mo25280id = 11) String str7, @SafeParcelable.Param(mo25280id = 12) String str8) {
        Set<Scope> set;
        new HashSet();
        this.zap = set;
        this.versionCode = i;
        this.mId = str;
        this.zaf = str2;
        this.zag = str3;
        this.zah = str4;
        this.zai = uri;
        this.zaj = str5;
        this.zak = j;
        this.zal = str6;
        this.zam = list;
        this.zan = str7;
        this.zao = str8;
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    @Nullable
    public String getIdToken() {
        return this.zaf;
    }

    @Nullable
    public String getEmail() {
        return this.zag;
    }

    @Nullable
    public Account getAccount() {
        Account account;
        if (this.zag == null) {
            return null;
        }
        new Account(this.zag, AccountType.GOOGLE);
        return account;
    }

    @Nullable
    public String getDisplayName() {
        return this.zah;
    }

    @Nullable
    public String getGivenName() {
        return this.zan;
    }

    @Nullable
    public String getFamilyName() {
        return this.zao;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zai;
    }

    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(Scope... scopeArr) {
        Scope[] scopeArr2 = scopeArr;
        if (scopeArr2 != null) {
            boolean addAll = Collections.addAll(this.zap, scopeArr2);
        }
        return this;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.zaj;
    }

    @KeepForSdk
    public boolean isExpired() {
        return zae.currentTimeMillis() / 1000 >= this.zak - 300;
    }

    @NonNull
    public final String zab() {
        return this.zal;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        Set<Scope> set;
        new HashSet(this.zam);
        return set;
    }

    @KeepForSdk
    @NonNull
    public Set<Scope> getRequestedScopes() {
        Set<Scope> set;
        new HashSet(this.zam);
        Set<Scope> set2 = set;
        Set<Scope> set3 = set2;
        boolean addAll = set2.addAll(this.zap);
        return set3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel2, 2, getId(), false);
        SafeParcelWriter.writeString(parcel2, 3, getIdToken(), false);
        SafeParcelWriter.writeString(parcel2, 4, getEmail(), false);
        SafeParcelWriter.writeString(parcel2, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel2, 6, getPhotoUrl(), i, false);
        SafeParcelWriter.writeString(parcel2, 7, getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel2, 8, this.zak);
        SafeParcelWriter.writeString(parcel2, 9, this.zal, false);
        SafeParcelWriter.writeTypedList(parcel2, 10, this.zam, false);
        SafeParcelWriter.writeString(parcel2, 11, getGivenName(), false);
        SafeParcelWriter.writeString(parcel2, 12, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public int hashCode() {
        return ((527 + this.zal.hashCode()) * 31) + getRequestedScopes().hashCode();
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.auth.api.signin.GoogleSignInAccount
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r3 = (com.google.android.gms.auth.api.signin.GoogleSignInAccount) r3
            r5 = r3
            r3 = r5
            r4 = r5
            r2 = r4
            java.lang.String r3 = r3.zal
            r4 = r0
            java.lang.String r4 = r4.zal
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0036
            r3 = r2
            java.util.Set r3 = r3.getRequestedScopes()
            r4 = r0
            java.util.Set r4 = r4.getRequestedScopes()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0036
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0036:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInAccount.equals(java.lang.Object):boolean");
    }

    public final String zac() {
        JSONObject zad = zad();
        Object remove = zad.remove("serverAuthCode");
        return zad.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v42, resolved type: org.json.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.json.JSONObject zad() {
        /*
            r13 = this;
            r0 = r13
            org.json.JSONObject r8 = new org.json.JSONObject
            r12 = r8
            r8 = r12
            r9 = r12
            r9.<init>()
            r1 = r8
            r8 = r0
            java.lang.String r8 = r8.getId()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x001e
            r8 = r1
            java.lang.String r9 = "id"
            r10 = r0
            java.lang.String r10 = r10.getId()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x001e:
            r8 = r0
            java.lang.String r8 = r8.getIdToken()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x0032
            r8 = r1
            java.lang.String r9 = "tokenId"
            r10 = r0
            java.lang.String r10 = r10.getIdToken()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x0032:
            r8 = r0
            java.lang.String r8 = r8.getEmail()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x0046
            r8 = r1
            java.lang.String r9 = "email"
            r10 = r0
            java.lang.String r10 = r10.getEmail()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x0046:
            r8 = r0
            java.lang.String r8 = r8.getDisplayName()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x005a
            r8 = r1
            java.lang.String r9 = "displayName"
            r10 = r0
            java.lang.String r10 = r10.getDisplayName()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x005a:
            r8 = r0
            java.lang.String r8 = r8.getGivenName()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x006e
            r8 = r1
            java.lang.String r9 = "givenName"
            r10 = r0
            java.lang.String r10 = r10.getGivenName()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x006e:
            r8 = r0
            java.lang.String r8 = r8.getFamilyName()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x0082
            r8 = r1
            java.lang.String r9 = "familyName"
            r10 = r0
            java.lang.String r10 = r10.getFamilyName()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x0082:
            r8 = r0
            android.net.Uri r8 = r8.getPhotoUrl()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x009a
            r8 = r1
            java.lang.String r9 = "photoUrl"
            r10 = r0
            android.net.Uri r10 = r10.getPhotoUrl()     // Catch:{ JSONException -> 0x0113 }
            java.lang.String r10 = r10.toString()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x009a:
            r8 = r0
            java.lang.String r8 = r8.getServerAuthCode()     // Catch:{ JSONException -> 0x0113 }
            if (r8 == 0) goto L_0x00ae
            r8 = r1
            java.lang.String r9 = "serverAuthCode"
            r10 = r0
            java.lang.String r10 = r10.getServerAuthCode()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
        L_0x00ae:
            r8 = r1
            java.lang.String r9 = "expirationTime"
            r10 = r0
            long r10 = r10.zak     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
            r8 = r1
            java.lang.String r9 = "obfuscatedIdentifier"
            r10 = r0
            java.lang.String r10 = r10.zal     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0113 }
            r12 = r8
            r8 = r12
            r9 = r12
            r9.<init>()     // Catch:{ JSONException -> 0x0113 }
            r2 = r8
            r8 = r0
            java.util.List<com.google.android.gms.common.api.Scope> r8 = r8.zam     // Catch:{ JSONException -> 0x0113 }
            r9 = r0
            java.util.List<com.google.android.gms.common.api.Scope> r9 = r9.zam     // Catch:{ JSONException -> 0x0113 }
            int r9 = r9.size()     // Catch:{ JSONException -> 0x0113 }
            com.google.android.gms.common.api.Scope[] r9 = new com.google.android.gms.common.api.Scope[r9]     // Catch:{ JSONException -> 0x0113 }
            java.lang.Object[] r8 = r8.toArray(r9)     // Catch:{ JSONException -> 0x0113 }
            com.google.android.gms.common.api.Scope[] r8 = (com.google.android.gms.common.api.Scope[]) r8     // Catch:{ JSONException -> 0x0113 }
            r12 = r8
            r8 = r12
            r9 = r12
            r3 = r9
            java.util.Comparator r9 = com.google.android.gms.auth.api.signin.zaa.zaq     // Catch:{ JSONException -> 0x0113 }
            java.util.Arrays.sort(r8, r9)     // Catch:{ JSONException -> 0x0113 }
            r8 = r3
            r12 = r8
            r8 = r12
            r9 = r12
            r4 = r9
            int r8 = r8.length     // Catch:{ JSONException -> 0x0113 }
            r5 = r8
            r8 = 0
            r6 = r8
        L_0x00f1:
            r8 = r6
            r9 = r5
            if (r8 >= r9) goto L_0x0107
            r8 = r4
            r9 = r6
            r8 = r8[r9]     // Catch:{ JSONException -> 0x0113 }
            r7 = r8
            r8 = r2
            r9 = r7
            java.lang.String r9 = r9.getScopeUri()     // Catch:{ JSONException -> 0x0113 }
            org.json.JSONArray r8 = r8.put(r9)     // Catch:{ JSONException -> 0x0113 }
            int r6 = r6 + 1
            goto L_0x00f1
        L_0x0107:
            r8 = r1
            java.lang.String r9 = "grantedScopes"
            r10 = r2
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x0113 }
            r8 = r1
            r0 = r8
            return r0
        L_0x0113:
            r8 = move-exception
            r2 = r8
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = r2
            r9.<init>(r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInAccount.zad():org.json.JSONObject");
    }

    static {
        Parcelable.Creator<GoogleSignInAccount> creator;
        new zab();
        CREATOR = creator;
    }
}
