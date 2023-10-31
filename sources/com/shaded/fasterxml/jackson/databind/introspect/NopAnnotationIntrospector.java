package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.Serializable;

public abstract class NopAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
    public static final NopAnnotationIntrospector instance;
    private static final long serialVersionUID = 1;

    public NopAnnotationIntrospector() {
    }

    static {
        NopAnnotationIntrospector nopAnnotationIntrospector;
        new NopAnnotationIntrospector() {
            private static final long serialVersionUID = 1;

            public Version version() {
                return PackageVersion.VERSION;
            }
        };
        instance = nopAnnotationIntrospector;
    }

    public Version version() {
        return Version.unknownVersion();
    }
}
