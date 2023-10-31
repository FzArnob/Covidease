package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public final class ManifestParser {
    private static final String GLIDE_MODULE_VALUE = "GlideModule";
    private final Context context;

    public ManifestParser(Context context2) {
        this.context = context2;
    }

    public List<GlideModule> parse() {
        List<GlideModule> list;
        Throwable th;
        new ArrayList();
        List<GlideModule> modules = list;
        try {
            ApplicationInfo appInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (appInfo.metaData != null) {
                for (String key : appInfo.metaData.keySet()) {
                    if (GLIDE_MODULE_VALUE.equals(appInfo.metaData.get(key))) {
                        boolean add = modules.add(parseModule(key));
                    }
                }
            }
            return modules;
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException e2 = e;
            Throwable th2 = th;
            new RuntimeException("Unable to find metadata to parse GlideModules", e2);
            throw th2;
        }
    }

    private static GlideModule parseModule(String className) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        Throwable th4;
        StringBuilder sb3;
        try {
            Class<?> clazz = Class.forName(className);
            try {
                Object module = clazz.newInstance();
                if (module instanceof GlideModule) {
                    return (GlideModule) module;
                }
                Throwable th5 = th4;
                new StringBuilder();
                new RuntimeException(sb3.append("Expected instanceof GlideModule, but found: ").append(module).toString());
                throw th5;
            } catch (InstantiationException e) {
                InstantiationException e2 = e;
                Throwable th6 = th3;
                new StringBuilder();
                new RuntimeException(sb2.append("Unable to instantiate GlideModule implementation for ").append(clazz).toString(), e2);
                throw th6;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                Throwable th7 = th2;
                new StringBuilder();
                new RuntimeException(sb.append("Unable to instantiate GlideModule implementation for ").append(clazz).toString(), e4);
                throw th7;
            }
        } catch (ClassNotFoundException e5) {
            ClassNotFoundException e6 = e5;
            Throwable th8 = th;
            new IllegalArgumentException("Unable to find GlideModule implementation", e6);
            throw th8;
        }
    }
}
