package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion("2.2.2", "com.shaded.fasterxml.jackson.core", "jackson-core");

    public PackageVersion() {
    }

    public Version version() {
        return VERSION;
    }
}
