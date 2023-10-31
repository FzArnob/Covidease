package com.shaded.fasterxml.jackson.core;

import java.io.Serializable;

public class Version implements Comparable<Version>, Serializable {
    private static final Version UNKNOWN_VERSION;
    private static final long serialVersionUID = 1;
    protected final String _artifactId;
    protected final String _groupId;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _snapshotInfo;

    static {
        Version version;
        new Version(0, 0, 0, (String) null, (String) null, (String) null);
        UNKNOWN_VERSION = version;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Version(int i, int i2, int i3, String str) {
        this(i, i2, i3, str, (String) null, (String) null);
    }

    public Version(int i, int i2, int i3, String str, String str2, String str3) {
        String str4 = str2;
        String str5 = str3;
        this._majorVersion = i;
        this._minorVersion = i2;
        this._patchLevel = i3;
        this._snapshotInfo = str;
        this._groupId = str4 == null ? "" : str4;
        this._artifactId = str5 == null ? "" : str5;
    }

    public static Version unknownVersion() {
        return UNKNOWN_VERSION;
    }

    public boolean isUknownVersion() {
        return this == UNKNOWN_VERSION;
    }

    public boolean isSnapshot() {
        return this._snapshotInfo != null && this._snapshotInfo.length() > 0;
    }

    public int getMajorVersion() {
        return this._majorVersion;
    }

    public int getMinorVersion() {
        return this._minorVersion;
    }

    public int getPatchLevel() {
        return this._patchLevel;
    }

    public String getGroupId() {
        return this._groupId;
    }

    public String getArtifactId() {
        return this._artifactId;
    }

    public String toFullString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this._groupId).append('/').append(this._artifactId).append('/').append(toString()).toString();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(this._majorVersion).append('.');
        StringBuilder append2 = sb2.append(this._minorVersion).append('.');
        StringBuilder append3 = sb2.append(this._patchLevel);
        if (isSnapshot()) {
            StringBuilder append4 = sb2.append('-').append(this._snapshotInfo);
        }
        return sb2.toString();
    }

    public int hashCode() {
        return this._artifactId.hashCode() ^ (((this._groupId.hashCode() + this._majorVersion) - this._minorVersion) + this._patchLevel);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 != 0) goto L_0x000f
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x000f:
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001e:
            r3 = r1
            com.shaded.fasterxml.jackson.core.Version r3 = (com.shaded.fasterxml.jackson.core.Version) r3
            r2 = r3
            r3 = r2
            int r3 = r3._majorVersion
            r4 = r0
            int r4 = r4._majorVersion
            if (r3 != r4) goto L_0x0055
            r3 = r2
            int r3 = r3._minorVersion
            r4 = r0
            int r4 = r4._minorVersion
            if (r3 != r4) goto L_0x0055
            r3 = r2
            int r3 = r3._patchLevel
            r4 = r0
            int r4 = r4._patchLevel
            if (r3 != r4) goto L_0x0055
            r3 = r2
            java.lang.String r3 = r3._artifactId
            r4 = r0
            java.lang.String r4 = r4._artifactId
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0055
            r3 = r2
            java.lang.String r3 = r3._groupId
            r4 = r0
            java.lang.String r4 = r4._groupId
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0055
            r3 = 1
        L_0x0053:
            r0 = r3
            goto L_0x0008
        L_0x0055:
            r3 = 0
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.Version.equals(java.lang.Object):boolean");
    }

    public int compareTo(Version version) {
        Version version2 = version;
        if (version2 == this) {
            return 0;
        }
        int compareTo = this._groupId.compareTo(version2._groupId);
        if (compareTo == 0) {
            compareTo = this._artifactId.compareTo(version2._artifactId);
            if (compareTo == 0) {
                compareTo = this._majorVersion - version2._majorVersion;
                if (compareTo == 0) {
                    compareTo = this._minorVersion - version2._minorVersion;
                    if (compareTo == 0) {
                        compareTo = this._patchLevel - version2._patchLevel;
                    }
                }
            }
        }
        return compareTo;
    }
}
