package android.support.p000v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

/* renamed from: android.support.v4.provider.FontRequest */
public final class FontRequest {
    private final List<List<byte[]>> mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query, @NonNull List<List<byte[]>> certificates) {
        StringBuilder sb;
        this.mProviderAuthority = (String) Preconditions.checkNotNull(providerAuthority);
        this.mProviderPackage = (String) Preconditions.checkNotNull(providerPackage);
        this.mQuery = (String) Preconditions.checkNotNull(query);
        this.mCertificates = (List) Preconditions.checkNotNull(certificates);
        this.mCertificatesArray = 0;
        new StringBuilder(this.mProviderAuthority);
        this.mIdentifier = sb.append("-").append(this.mProviderPackage).append("-").append(this.mQuery).toString();
    }

    public FontRequest(@NonNull String providerAuthority, @NonNull String providerPackage, @NonNull String query, @ArrayRes int i) {
        StringBuilder sb;
        int certificates = i;
        this.mProviderAuthority = (String) Preconditions.checkNotNull(providerAuthority);
        this.mProviderPackage = (String) Preconditions.checkNotNull(providerPackage);
        this.mQuery = (String) Preconditions.checkNotNull(query);
        this.mCertificates = null;
        Preconditions.checkArgument(certificates != 0);
        this.mCertificatesArray = certificates;
        new StringBuilder(this.mProviderAuthority);
        this.mIdentifier = sb.append("-").append(this.mProviderPackage).append("-").append(this.mQuery).toString();
    }

    @NonNull
    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    @NonNull
    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    @NonNull
    public String getQuery() {
        return this.mQuery;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        new StringBuilder();
        StringBuilder builder = sb;
        new StringBuilder();
        StringBuilder append = builder.append(sb2.append("FontRequest {mProviderAuthority: ").append(this.mProviderAuthority).append(", mProviderPackage: ").append(this.mProviderPackage).append(", mQuery: ").append(this.mQuery).append(", mCertificates:").toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            StringBuilder append2 = builder.append(" [");
            List<byte[]> set = this.mCertificates.get(i);
            for (int j = 0; j < set.size(); j++) {
                StringBuilder append3 = builder.append(" \"");
                StringBuilder append4 = builder.append(Base64.encodeToString(set.get(j), 0));
                StringBuilder append5 = builder.append("\"");
            }
            StringBuilder append6 = builder.append(" ]");
        }
        StringBuilder append7 = builder.append("}");
        new StringBuilder();
        StringBuilder append8 = builder.append(sb3.append("mCertificatesArray: ").append(this.mCertificatesArray).toString());
        return builder.toString();
    }
}
