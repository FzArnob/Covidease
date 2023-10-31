package org.shaded.apache.http.client.utils;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class Punycode {
    private static final Idn impl;

    public Punycode() {
    }

    static {
        Idn idn;
        Idn _impl;
        Idn idn2;
        try {
            Idn idn3 = idn2;
            new JdkIdn();
            _impl = idn3;
        } catch (Exception e) {
            Exception exc = e;
            new Rfc3492Idn();
            _impl = idn;
        }
        impl = _impl;
    }

    public static String toUnicode(String punycode) {
        return impl.toUnicode(punycode);
    }
}
