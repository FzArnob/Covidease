package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.support.p000v4.util.C1643ArraySet;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@KeepForSdk
@VisibleForTesting
public final class ClientSettings {
    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    private final Set<Scope> zabr;
    private final int zabt;
    private final View zabu;
    private final String zabv;
    private final String zabw;
    private final boolean zaby;
    private final Set<Scope> zaob;
    private final Map<Api<?>, OptionalApiSettings> zaoc;
    private final SignInOptions zaod;
    private Integer zaoe;
    private final Account zax;

    public static final class OptionalApiSettings {
        public final Set<Scope> mScopes;

        public OptionalApiSettings(Set<Scope> set) {
            Set<Scope> set2 = set;
            Object checkNotNull = Preconditions.checkNotNull(set2);
            this.mScopes = Collections.unmodifiableSet(set2);
        }
    }

    @KeepForSdk
    public static ClientSettings createDefault(Context context) {
        GoogleApiClient.Builder builder;
        new GoogleApiClient.Builder(context);
        return builder.buildClientSettings();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    @KeepForSdk
    public static final class Builder {
        private int zabt = 0;
        private View zabu;
        private String zabv;
        private String zabw;
        private boolean zaby;
        private Map<Api<?>, OptionalApiSettings> zaoc;
        private SignInOptions zaod = SignInOptions.DEFAULT;
        private C1643ArraySet<Scope> zaof;
        private Account zax;

        public Builder() {
        }

        public final Builder setAccount(Account account) {
            this.zax = account;
            return this;
        }

        public final Builder addRequiredScope(Scope scope) {
            C1643ArraySet<Scope> arraySet;
            Scope scope2 = scope;
            if (this.zaof == null) {
                new C1643ArraySet<>();
                this.zaof = arraySet;
            }
            boolean add = this.zaof.add(scope2);
            return this;
        }

        public final Builder addAllRequiredScopes(Collection<Scope> collection) {
            C1643ArraySet<Scope> arraySet;
            Collection<Scope> collection2 = collection;
            if (this.zaof == null) {
                new C1643ArraySet<>();
                this.zaof = arraySet;
            }
            boolean addAll = this.zaof.addAll(collection2);
            return this;
        }

        public final Builder setOptionalApiSettingsMap(Map<Api<?>, OptionalApiSettings> map) {
            this.zaoc = map;
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zabt = i;
            return this;
        }

        public final Builder setViewForPopups(View view) {
            this.zabu = view;
            return this;
        }

        @KeepForSdk
        public final Builder setRealClientPackageName(String str) {
            this.zabv = str;
            return this;
        }

        public final Builder setRealClientClassName(String str) {
            this.zabw = str;
            return this;
        }

        public final Builder setSignInOptions(SignInOptions signInOptions) {
            this.zaod = signInOptions;
            return this;
        }

        public final Builder enableSignInClientDisconnectFix() {
            this.zaby = true;
            return this;
        }

        @KeepForSdk
        public final ClientSettings build() {
            ClientSettings clientSettings;
            new ClientSettings(this.zax, this.zaof, this.zaoc, this.zabt, this.zabu, this.zabv, this.zabw, this.zaod, this.zaby);
            return clientSettings;
        }
    }

    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, OptionalApiSettings> map, int i, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        Set set2;
        Set<Scope> set3 = set;
        Map<Api<?>, OptionalApiSettings> map2 = map;
        int i2 = i;
        View view2 = view;
        String str3 = str;
        String str4 = str2;
        SignInOptions signInOptions2 = signInOptions;
        boolean z2 = z;
        this.zax = account;
        this.zabr = set3 == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set3);
        this.zaoc = map2 == null ? Collections.EMPTY_MAP : map2;
        this.zabu = view2;
        this.zabt = i2;
        this.zabv = str3;
        this.zabw = str4;
        this.zaod = signInOptions2;
        this.zaby = z2;
        new HashSet(this.zabr);
        Set set4 = set2;
        for (OptionalApiSettings optionalApiSettings : this.zaoc.values()) {
            boolean addAll = set4.addAll(optionalApiSettings.mScopes);
        }
        this.zaob = Collections.unmodifiableSet(set4);
    }

    @KeepForSdk
    @Deprecated
    @Nullable
    public final String getAccountName() {
        if (this.zax != null) {
            return this.zax.name;
        }
        return null;
    }

    @KeepForSdk
    @Nullable
    public final Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public final Account getAccountOrDefault() {
        Account account;
        if (this.zax != null) {
            return this.zax;
        }
        new Account("<<default account>>", AccountType.GOOGLE);
        return account;
    }

    @KeepForSdk
    public final int getGravityForPopups() {
        return this.zabt;
    }

    @KeepForSdk
    public final Set<Scope> getRequiredScopes() {
        return this.zabr;
    }

    @KeepForSdk
    public final Set<Scope> getAllRequestedScopes() {
        return this.zaob;
    }

    public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
        return this.zaoc;
    }

    @KeepForSdk
    @Nullable
    public final String getRealClientPackageName() {
        return this.zabv;
    }

    @Nullable
    public final String getRealClientClassName() {
        return this.zabw;
    }

    @KeepForSdk
    @Nullable
    public final View getViewForPopups() {
        return this.zabu;
    }

    @Nullable
    public final SignInOptions getSignInOptions() {
        return this.zaod;
    }

    @Nullable
    public final Integer getClientSessionId() {
        return this.zaoe;
    }

    public final void setClientSessionId(Integer num) {
        Integer num2 = num;
        this.zaoe = num2;
    }

    @KeepForSdk
    public final Set<Scope> getApplicableScopes(Api<?> api) {
        Set<Scope> set;
        OptionalApiSettings optionalApiSettings = this.zaoc.get(api);
        OptionalApiSettings optionalApiSettings2 = optionalApiSettings;
        if (optionalApiSettings == null || optionalApiSettings2.mScopes.isEmpty()) {
            return this.zabr;
        }
        new HashSet(this.zabr);
        Set<Scope> set2 = set;
        Set<Scope> set3 = set2;
        boolean addAll = set2.addAll(optionalApiSettings2.mScopes);
        return set3;
    }

    public final boolean isSignInClientDisconnectFixEnabled() {
        return this.zaby;
    }
}
