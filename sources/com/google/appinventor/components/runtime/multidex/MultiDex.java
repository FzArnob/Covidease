package com.google.appinventor.components.runtime.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import org.shaded.apache.http.cookie.ClientCookie;

public final class MultiDex {
    private static final String Sh2mKYKwuehs0F1mmv0TRntZIP9rdF7C9GwSzNCd1X4nGlljhrD3h4lWlup0CGLh;
    private static final boolean dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(System.getProperty("java.vm.version"));
    private static final Set<String> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Object[] objArr2 = objArr;
        Object obj2 = obj;
        Object obj3 = obj2;
        Field hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2, str);
        Field field = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        Object[] objArr3 = (Object[]) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(obj3);
        Object[] objArr4 = objArr3;
        Object[] objArr5 = (Object[]) Array.newInstance(objArr3.getClass().getComponentType(), objArr4.length + objArr2.length);
        System.arraycopy(objArr4, 0, objArr5, 0, objArr4.length);
        System.arraycopy(objArr2, 0, objArr5, objArr4.length, objArr2.length);
        field.set(obj3, objArr5);
    }

    static {
        StringBuilder sb;
        Set<String> set;
        new StringBuilder("code_cache");
        Sh2mKYKwuehs0F1mmv0TRntZIP9rdF7C9GwSzNCd1X4nGlljhrD3h4lWlup0CGLh = sb.append(File.separator).append("secondary-dexes").toString();
        new HashSet();
        hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = set;
    }

    private MultiDex() {
    }

    public static boolean install(Context context, boolean z) {
        Throwable th;
        StringBuilder sb;
        File file;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Context context2 = context;
        boolean z2 = z;
        hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.clear();
        int i = Log.i("MultiDex", "install: doIt = ".concat(String.valueOf(z2)));
        if (dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw) {
            int i2 = Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
            return true;
        } else if (Build.VERSION.SDK_INT < 4) {
            Throwable th4 = th3;
            new StringBuilder("Multi dex installation failed. SDK ");
            new RuntimeException(sb3.append(Build.VERSION.SDK_INT).append(" is unsupported. Min SDK version is 4.").toString());
            throw th4;
        } else {
            try {
                ApplicationInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2);
                ApplicationInfo applicationInfo = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
                    int d = Log.d("MultiDex", "applicationInfo is null, returning");
                    return true;
                }
                Set<String> set = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                Set<String> set2 = set;
                synchronized (set) {
                    try {
                        String str = applicationInfo.sourceDir;
                        if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.contains(str)) {
                            return true;
                        }
                        boolean add = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.add(str);
                        if (Build.VERSION.SDK_INT > 20) {
                            new StringBuilder("MultiDex is not guaranteed to work in SDK version ");
                            int w = Log.w("MultiDex", sb2.append(Build.VERSION.SDK_INT).append(": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"").append(System.getProperty("java.vm.version")).append("\"").toString());
                        }
                        ClassLoader classLoader = context2.getClassLoader();
                        if (classLoader == null) {
                            int e = Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                            return true;
                        }
                        try {
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2);
                        } catch (Throwable th5) {
                            int w2 = Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", th5);
                        }
                        new File(applicationInfo.dataDir, Sh2mKYKwuehs0F1mmv0TRntZIP9rdF7C9GwSzNCd1X4nGlljhrD3h4lWlup0CGLh);
                        File file2 = file;
                        if (z2 || !C1113a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, applicationInfo)) {
                            int d2 = Log.d("MultiDex", "Proceeding with installation...");
                            List<File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = C1113a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, applicationInfo, file2, false);
                            List<File> list = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2)) {
                                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader, file2, list);
                            } else {
                                int w3 = Log.w("MultiDex", "Files were not valid zip files.  Forcing a reload.");
                                List<File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = C1113a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, applicationInfo, file2, true);
                                List<File> list2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3;
                                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3)) {
                                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader, file2, list2);
                                } else {
                                    Throwable th6 = th2;
                                    new RuntimeException("Zip files were not valid.");
                                    throw th6;
                                }
                            }
                            int i3 = Log.i("MultiDex", "install done");
                            return true;
                        }
                        int d3 = Log.d("MultiDex", "Returning because of mustLoad");
                        return false;
                    } catch (RuntimeException e2) {
                        int w4 = Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", e2);
                        Set<String> set3 = set2;
                        return true;
                    } catch (Throwable th7) {
                        Throwable th8 = th7;
                        Set<String> set4 = set2;
                        throw th8;
                    }
                }
            } catch (Exception e3) {
                Exception exc = e3;
                int e4 = Log.e("MultiDex", "Multidex installation failure", exc);
                Throwable th9 = th;
                new StringBuilder("Multi dex installation failed (");
                new RuntimeException(sb.append(exc.getMessage()).append(").").toString());
                throw th9;
            }
        }
    }

    private static ApplicationInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) throws PackageManager.NameNotFoundException {
        Context context2 = context;
        try {
            PackageManager packageManager = context2.getPackageManager();
            String packageName = context2.getPackageName();
            if (packageManager == null || packageName == null) {
                return null;
            }
            return packageManager.getApplicationInfo(packageName, 128);
        } catch (RuntimeException e) {
            int w = Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
        StringBuilder sb;
        String str2 = str;
        boolean z = false;
        if (str2 != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str2);
            Matcher matcher2 = matcher;
            if (matcher.matches()) {
                try {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    z = parseInt > 2 || (parseInt == 2 && Integer.parseInt(matcher2.group(2)) > 0);
                } catch (NumberFormatException e) {
                }
            }
        }
        new StringBuilder("VM with version ");
        int i = Log.i("MultiDex", sb.append(str2).append(z ? " has multidex support" : " does not have multidex support").toString());
        return z;
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, File file, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        ClassLoader classLoader2 = classLoader;
        File file2 = file;
        List<File> list2 = list;
        if (list2.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            File file3 = file2;
            File file4 = file3;
            C1111b.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, list2, file3);
        } else if (Build.VERSION.SDK_INT >= 14) {
            File file5 = file2;
            File file6 = file5;
            C1110a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, list2, file5);
        } else {
            C1112c.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, list2);
        }
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<File> list) {
        for (File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME : list) {
            if (!C1113a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static Field hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str) throws NoSuchFieldException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        String str2 = str;
        Class<?> cls = obj2.getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2 != null) {
                try {
                    Field declaredField = cls2.getDeclaredField(str2);
                    Field field = declaredField;
                    if (!declaredField.isAccessible()) {
                        field.setAccessible(true);
                    }
                    return field;
                } catch (NoSuchFieldException e) {
                    cls = cls2.getSuperclass();
                }
            } else {
                Throwable th2 = th;
                new StringBuilder("Field ");
                new NoSuchFieldException(sb.append(str2).append(" not found in ").append(obj2.getClass()).toString());
                throw th2;
            }
        }
    }

    /* access modifiers changed from: private */
    public static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        String str2 = str;
        Class<?>[] clsArr2 = clsArr;
        Class<?> cls = obj2.getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2 != null) {
                try {
                    Method declaredMethod = cls2.getDeclaredMethod(str2, clsArr2);
                    Method method = declaredMethod;
                    if (!declaredMethod.isAccessible()) {
                        method.setAccessible(true);
                    }
                    return method;
                } catch (NoSuchMethodException e) {
                    cls = cls2.getSuperclass();
                }
            } else {
                Throwable th2 = th;
                new StringBuilder("Method ");
                new NoSuchMethodException(sb.append(str2).append(" with parameters ").append(Arrays.asList(clsArr2)).append(" not found in ").append(obj2.getClass()).toString());
                throw th2;
            }
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static void m63hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) throws Exception {
        File file;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        new File(context.getFilesDir(), "secondary-dexes");
        File file2 = file;
        File file3 = file2;
        if (file2.isDirectory()) {
            new StringBuilder("Clearing old secondary dex dir (");
            int i = Log.i("MultiDex", sb.append(file3.getPath()).append(").").toString());
            File[] listFiles = file3.listFiles();
            File[] fileArr = listFiles;
            if (listFiles == null) {
                new StringBuilder("Failed to list secondary dex dir content (");
                int w = Log.w("MultiDex", sb7.append(file3.getPath()).append(").").toString());
                return;
            }
            int length = fileArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                File file4 = fileArr[i2];
                new StringBuilder("Trying to delete old file ");
                int i3 = Log.i("MultiDex", sb4.append(file4.getPath()).append(" of size ").append(file4.length()).toString());
                if (!file4.delete()) {
                    new StringBuilder("Failed to delete old file ");
                    int w2 = Log.w("MultiDex", sb6.append(file4.getPath()).toString());
                } else {
                    new StringBuilder("Deleted old file ");
                    int i4 = Log.i("MultiDex", sb5.append(file4.getPath()).toString());
                }
            }
            if (!file3.delete()) {
                new StringBuilder("Failed to delete secondary dex dir ");
                int w3 = Log.w("MultiDex", sb3.append(file3.getPath()).toString());
                return;
            }
            new StringBuilder("Deleted old secondary dex dir ");
            int i5 = Log.i("MultiDex", sb2.append(file3.getPath()).toString());
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.multidex.MultiDex$b */
    static final class C1111b {
        static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            ArrayList arrayList;
            Object obj;
            IOException[] iOExceptionArr;
            ClassLoader classLoader2 = classLoader;
            Object obj2 = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, "pathList").get(classLoader2);
            new ArrayList();
            ArrayList arrayList2 = arrayList;
            Object obj3 = obj2;
            Object obj4 = obj2;
            new ArrayList(list);
            Object obj5 = obj;
            Object obj6 = obj4;
            Object obj7 = obj6;
            Class[] clsArr = new Class[3];
            clsArr[0] = ArrayList.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = File.class;
            Class[] clsArr3 = clsArr2;
            clsArr3[2] = ArrayList.class;
            Object[] objArr = new Object[3];
            objArr[0] = obj5;
            Object[] objArr2 = objArr;
            objArr2[1] = file;
            Object[] objArr3 = objArr2;
            objArr3[2] = arrayList2;
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj3, "dexElements", (Object[]) MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj7, "makeDexElements", (Class<?>[]) clsArr3).invoke(obj6, objArr3));
            if (arrayList2.size() > 0) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    int w = Log.w("MultiDex", "Exception in makeDexElement", (IOException) it.next());
                }
                Field B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, "dexElementsSuppressedExceptions");
                Field field = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
                IOException[] iOExceptionArr2 = (IOException[]) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(classLoader2);
                IOException[] iOExceptionArr3 = iOExceptionArr2;
                if (iOExceptionArr2 == null) {
                    ArrayList arrayList3 = arrayList2;
                    iOExceptionArr = (IOException[]) arrayList3.toArray(new IOException[arrayList3.size()]);
                } else {
                    IOException[] iOExceptionArr4 = new IOException[(arrayList2.size() + iOExceptionArr3.length)];
                    Object[] array = arrayList2.toArray(iOExceptionArr4);
                    System.arraycopy(iOExceptionArr3, 0, iOExceptionArr4, arrayList2.size(), iOExceptionArr3.length);
                    iOExceptionArr = iOExceptionArr4;
                }
                field.set(classLoader2, iOExceptionArr);
            }
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.multidex.MultiDex$a */
    static final class C1110a {
        static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            Object obj;
            ClassLoader classLoader2 = classLoader;
            Object obj2 = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, "pathList").get(classLoader2);
            Object obj3 = obj2;
            new ArrayList(list);
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj5;
            Class[] clsArr = new Class[2];
            clsArr[0] = ArrayList.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = File.class;
            Object[] objArr = new Object[2];
            objArr[0] = obj4;
            Object[] objArr2 = objArr;
            objArr2[1] = file;
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj3, "dexElements", (Object[]) MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj6, "makeDexElements", (Class<?>[]) clsArr2).invoke(obj5, objArr2));
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.multidex.MultiDex$c */
    static final class C1112c {
        static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            StringBuilder sb;
            ZipFile zipFile;
            StringBuilder sb2;
            ClassLoader classLoader2 = classLoader;
            List<File> list2 = list;
            int size = list2.size();
            Field B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader2, ClientCookie.PATH_ATTR);
            new StringBuilder((String) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(classLoader2));
            StringBuilder sb3 = sb;
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<File> listIterator = list2.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                File file = next;
                String absolutePath = next.getAbsolutePath();
                StringBuilder append = sb3.append(':').append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                strArr[previousIndex] = absolutePath;
                fileArr[previousIndex] = file;
                new ZipFile(file);
                zipFileArr[previousIndex] = zipFile;
                new StringBuilder();
                dexFileArr[previousIndex] = DexFile.loadDex(absolutePath, sb2.append(absolutePath).append(".dex").toString(), 0);
            }
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.set(classLoader2, sb3.toString());
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Object) classLoader2, "mPaths", (Object[]) strArr);
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Object) classLoader2, "mFiles", (Object[]) fileArr);
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Object) classLoader2, "mZips", (Object[]) zipFileArr);
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Object) classLoader2, "mDexs", (Object[]) dexFileArr);
        }
    }
}
