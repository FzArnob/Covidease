package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> zaaf;
    @VisibleForTesting
    public static final Scope zar;
    @VisibleForTesting
    public static final Scope zas;
    @VisibleForTesting
    public static final Scope zat;
    @VisibleForTesting
    public static final Scope zau;
    @VisibleForTesting
    public static final Scope zav;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int versionCode;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", mo25277id = 6)
    public final boolean zaaa;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getServerClientId", mo25277id = 7)
    public String zaab;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getHostedDomain", mo25277id = 8)
    public String zaac;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getExtensions", mo25277id = 9)
    public ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaae;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getScopes", mo25277id = 2)
    public final ArrayList<Scope> zaw;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getAccount", mo25277id = 3)
    public Account zax;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isIdTokenRequested", mo25277id = 4)
    public boolean zay;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", mo25277id = 5)
    public final boolean zaz;

    @Nullable
    public static GoogleSignInOptions zab(@Nullable String str) throws JSONException {
        JSONObject jSONObject;
        Set set;
        GoogleSignInOptions googleSignInOptions;
        ArrayList arrayList;
        Map map;
        Account account;
        Object obj;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        new JSONObject(str2);
        JSONObject jSONObject2 = jSONObject;
        new HashSet();
        Set set2 = set;
        JSONArray jSONArray = jSONObject2.getJSONArray("scopes");
        JSONArray jSONArray2 = jSONArray;
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            new Scope(jSONArray2.getString(i));
            boolean add = set2.add(obj);
        }
        Account account2 = null;
        String optString = jSONObject2.optString(OAuth2Helper.PREF_ACCOUNT_NAME, (String) null);
        String str3 = optString;
        if (!TextUtils.isEmpty(optString)) {
            new Account(str3, AccountType.GOOGLE);
            account2 = account;
        }
        new ArrayList(set2);
        new HashMap();
        new GoogleSignInOptions(3, (ArrayList<Scope>) arrayList, account2, jSONObject2.getBoolean("idTokenRequested"), jSONObject2.getBoolean("serverAuthRequested"), jSONObject2.getBoolean("forceCodeForRefreshToken"), jSONObject2.optString("serverClientId", (String) null), jSONObject2.optString("hostedDomain", (String) null), (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map);
        return googleSignInOptions;
    }

    public static final class Builder {
        private Set<Scope> mScopes;
        private boolean zaaa;
        private String zaab;
        private String zaac;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaag;
        private Account zax;
        private boolean zay;
        private boolean zaz;

        public Builder() {
            Set<Scope> set;
            Map<Integer, GoogleSignInOptionsExtensionParcelable> map;
            new HashSet();
            this.mScopes = set;
            new HashMap();
            this.zaag = map;
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            Set<Scope> set;
            Map<Integer, GoogleSignInOptionsExtensionParcelable> map;
            Set<Scope> set2;
            GoogleSignInOptions googleSignInOptions2 = googleSignInOptions;
            new HashSet();
            this.mScopes = set;
            new HashMap();
            this.zaag = map;
            Object checkNotNull = Preconditions.checkNotNull(googleSignInOptions2);
            new HashSet(googleSignInOptions2.zaw);
            this.mScopes = set2;
            this.zaz = googleSignInOptions2.zaz;
            this.zaaa = googleSignInOptions2.zaaa;
            this.zay = googleSignInOptions2.zay;
            this.zaab = googleSignInOptions2.zaab;
            this.zax = googleSignInOptions2.zax;
            this.zaac = googleSignInOptions2.zaac;
            this.zaag = GoogleSignInOptions.zaa((List<GoogleSignInOptionsExtensionParcelable>) googleSignInOptions2.zaad);
        }

        public final Builder requestId() {
            boolean add = this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }

        public final Builder requestEmail() {
            boolean add = this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }

        public final Builder requestProfile() {
            boolean add = this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            boolean add = this.mScopes.add(scope);
            boolean addAll = this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zay = true;
            this.zaab = zac(str);
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zaz = true;
            this.zaab = zac(str);
            this.zaaa = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            Account account;
            new Account(Preconditions.checkNotEmpty(str), AccountType.GOOGLE);
            this.zax = account;
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zaac = Preconditions.checkNotEmpty(str);
            return this;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            Object obj;
            Throwable th;
            GoogleSignInOptionsExtension googleSignInOptionsExtension2 = googleSignInOptionsExtension;
            if (this.zaag.containsKey(Integer.valueOf(googleSignInOptionsExtension2.getExtensionType()))) {
                Throwable th2 = th;
                new IllegalStateException("Only one extension per type may be added");
                throw th2;
            }
            if (googleSignInOptionsExtension2.getImpliedScopes() != null) {
                boolean addAll = this.mScopes.addAll(googleSignInOptionsExtension2.getImpliedScopes());
            }
            new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension2);
            GoogleSignInOptionsExtensionParcelable put = this.zaag.put(Integer.valueOf(googleSignInOptionsExtension2.getExtensionType()), obj);
            return this;
        }

        public final GoogleSignInOptions build() {
            GoogleSignInOptions googleSignInOptions;
            ArrayList arrayList;
            if (this.mScopes.contains(GoogleSignInOptions.zav) && this.mScopes.contains(GoogleSignInOptions.zau)) {
                boolean remove = this.mScopes.remove(GoogleSignInOptions.zau);
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                Builder requestId = requestId();
            }
            new ArrayList(this.mScopes);
            new GoogleSignInOptions(3, arrayList, this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaag, (zac) null);
            return googleSignInOptions;
        }

        private final String zac(String str) {
            String str2 = str;
            String checkNotEmpty = Preconditions.checkNotEmpty(str2);
            Preconditions.checkArgument(this.zaab == null || this.zaab.equals(str2), "two different server client ids provided");
            return str2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @SafeParcelable.Constructor
    GoogleSignInOptions(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) ArrayList<Scope> arrayList, @SafeParcelable.Param(mo25280id = 3) Account account, @SafeParcelable.Param(mo25280id = 4) boolean z, @SafeParcelable.Param(mo25280id = 5) boolean z2, @SafeParcelable.Param(mo25280id = 6) boolean z3, @SafeParcelable.Param(mo25280id = 7) String str, @SafeParcelable.Param(mo25280id = 8) String str2, @SafeParcelable.Param(mo25280id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2) {
        this(i, arrayList, account, z, z2, z3, str, str2, zaa((List<GoogleSignInOptionsExtensionParcelable>) arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map) {
        ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2;
        Map<Integer, GoogleSignInOptionsExtensionParcelable> map2 = map;
        this.versionCode = i;
        this.zaw = arrayList;
        this.zax = account;
        this.zay = z;
        this.zaz = z2;
        this.zaaa = z3;
        this.zaab = str;
        this.zaac = str2;
        new ArrayList<>(map2.values());
        this.zaad = arrayList2;
        this.zaae = map2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.google.android.gms.common.api.Scope> getScopes() {
        /*
            r5 = this;
            r0 = r5
            java.util.ArrayList r1 = new java.util.ArrayList
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r3 = r3.zaw
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.getScopes():java.util.ArrayList");
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zaw.toArray(new Scope[this.zaw.size()]);
    }

    @KeepForSdk
    public Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zay;
    }

    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }

    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    @KeepForSdk
    public String getServerClientId() {
        return this.zaab;
    }

    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }

    /* access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(@Nullable List<GoogleSignInOptionsExtensionParcelable> list) {
        Map<Integer, GoogleSignInOptionsExtensionParcelable> map;
        List<GoogleSignInOptionsExtensionParcelable> list2 = list;
        new HashMap();
        Map<Integer, GoogleSignInOptionsExtensionParcelable> map2 = map;
        if (list2 == null) {
            return map2;
        }
        for (GoogleSignInOptionsExtensionParcelable next : list2) {
            GoogleSignInOptionsExtensionParcelable put = map2.put(Integer.valueOf(next.getType()), next);
        }
        return map2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(parcel2, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel2, 3, getAccount(), i, false);
        SafeParcelWriter.writeBoolean(parcel2, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel2, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel2, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel2, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel2, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel2, 9, getExtensions(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj2;
            if (this.zaad.size() > 0 || googleSignInOptions.zaad.size() > 0) {
                return false;
            }
            if (this.zaw.size() != googleSignInOptions.getScopes().size() || !this.zaw.containsAll(googleSignInOptions.getScopes())) {
                return false;
            }
            if (this.zax != null ? this.zax.equals(googleSignInOptions.getAccount()) : googleSignInOptions.getAccount() == null) {
                if (!TextUtils.isEmpty(this.zaab) ? this.zaab.equals(googleSignInOptions.getServerClientId()) : TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                    if (this.zaaa == googleSignInOptions.isForceCodeForRefreshToken() && this.zay == googleSignInOptions.isIdTokenRequested() && this.zaz == googleSignInOptions.isServerAuthCodeRequested()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        ArrayList arrayList;
        HashAccumulator hashAccumulator;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = this.zaw;
        ArrayList arrayList4 = arrayList3;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            i++;
            boolean add = arrayList2.add(((Scope) arrayList4.get(i)).getScopeUri());
        }
        Collections.sort(arrayList2);
        new HashAccumulator();
        return hashAccumulator.addObject(arrayList2).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).hash();
    }

    public final String zae() {
        return zad().toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v42, resolved type: org.json.JSONArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.json.JSONObject zad() {
        /*
            r11 = this;
            r0 = r11
            org.json.JSONObject r7 = new org.json.JSONObject
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            r1 = r7
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00ac }
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()     // Catch:{ JSONException -> 0x00ac }
            r2 = r7
            r7 = r0
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r7 = r7.zaw     // Catch:{ JSONException -> 0x00ac }
            java.util.Comparator<com.google.android.gms.common.api.Scope> r8 = zaaf     // Catch:{ JSONException -> 0x00ac }
            java.util.Collections.sort(r7, r8)     // Catch:{ JSONException -> 0x00ac }
            r7 = r0
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r7 = r7.zaw     // Catch:{ JSONException -> 0x00ac }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ JSONException -> 0x00ac }
            r10 = r7
            r7 = r10
            r8 = r10
            r4 = r8
            int r7 = r7.size()     // Catch:{ JSONException -> 0x00ac }
            r5 = r7
            r7 = 0
            r6 = r7
        L_0x002b:
            r7 = r6
            r8 = r5
            if (r7 >= r8) goto L_0x0045
            r7 = r4
            r8 = r6
            java.lang.Object r7 = r7.get(r8)     // Catch:{ JSONException -> 0x00ac }
            int r6 = r6 + 1
            com.google.android.gms.common.api.Scope r7 = (com.google.android.gms.common.api.Scope) r7     // Catch:{ JSONException -> 0x00ac }
            r3 = r7
            r7 = r2
            r8 = r3
            java.lang.String r8 = r8.getScopeUri()     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONArray r7 = r7.put(r8)     // Catch:{ JSONException -> 0x00ac }
            goto L_0x002b
        L_0x0045:
            r7 = r1
            java.lang.String r8 = "scopes"
            r9 = r2
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
            r7 = r0
            android.accounts.Account r7 = r7.zax     // Catch:{ JSONException -> 0x00ac }
            if (r7 == 0) goto L_0x0060
            r7 = r1
            java.lang.String r8 = "accountName"
            r9 = r0
            android.accounts.Account r9 = r9.zax     // Catch:{ JSONException -> 0x00ac }
            java.lang.String r9 = r9.name     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
        L_0x0060:
            r7 = r1
            java.lang.String r8 = "idTokenRequested"
            r9 = r0
            boolean r9 = r9.zay     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
            r7 = r1
            java.lang.String r8 = "forceCodeForRefreshToken"
            r9 = r0
            boolean r9 = r9.zaaa     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
            r7 = r1
            java.lang.String r8 = "serverAuthRequested"
            r9 = r0
            boolean r9 = r9.zaz     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
            r7 = r0
            java.lang.String r7 = r7.zaab     // Catch:{ JSONException -> 0x00ac }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 != 0) goto L_0x0095
            r7 = r1
            java.lang.String r8 = "serverClientId"
            r9 = r0
            java.lang.String r9 = r9.zaab     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
        L_0x0095:
            r7 = r0
            java.lang.String r7 = r7.zaac     // Catch:{ JSONException -> 0x00ac }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x00ac }
            if (r7 != 0) goto L_0x00a9
            r7 = r1
            java.lang.String r8 = "hostedDomain"
            r9 = r0
            java.lang.String r9 = r9.zaac     // Catch:{ JSONException -> 0x00ac }
            org.json.JSONObject r7 = r7.put(r8, r9)     // Catch:{ JSONException -> 0x00ac }
        L_0x00a9:
            r7 = r1
            r0 = r7
            return r0
        L_0x00ac:
            r7 = move-exception
            r2 = r7
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            r10 = r7
            r7 = r10
            r8 = r10
            r9 = r2
            r8.<init>(r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.zad():org.json.JSONObject");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, zac zac) {
        this(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, str, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map);
        int i2 = i;
        zac zac2 = zac;
    }

    static {
        Scope scope;
        Scope scope2;
        Scope scope3;
        Scope scope4;
        Scope scope5;
        Builder builder;
        Builder builder2;
        Parcelable.Creator<GoogleSignInOptions> creator;
        Comparator<Scope> comparator;
        new Scope(Scopes.PROFILE);
        zar = scope;
        new Scope("email");
        zas = scope2;
        new Scope(Scopes.OPEN_ID);
        zat = scope3;
        new Scope(Scopes.GAMES_LITE);
        zau = scope4;
        new Scope(Scopes.GAMES);
        zav = scope5;
        new Builder();
        DEFAULT_SIGN_IN = builder.requestId().requestProfile().build();
        new Builder();
        DEFAULT_GAMES_SIGN_IN = builder2.requestScopes(zau, new Scope[0]).build();
        new zad();
        CREATOR = creator;
        new zac();
        zaaf = comparator;
    }
}
