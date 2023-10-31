package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class zzh extends PathClassLoader {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    /* access modifiers changed from: protected */
    public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        String str2 = str;
        boolean z2 = z;
        if (!str2.startsWith("java.") && !str2.startsWith("android.")) {
            try {
                return findClass(str2);
            } catch (ClassNotFoundException e) {
            }
        }
        return super.loadClass(str2, z2);
    }
}
