package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothReflection {
    private BluetoothReflection() {
    }

    public static Object getBluetoothAdapter() {
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class.forName("android.bluetooth.BluetoothAdapter"), "getDefaultAdapter"));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isBluetoothEnabled(Object obj) {
        Object obj2 = obj;
        return ((Boolean) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "isEnabled"), obj2, new Object[0])).booleanValue();
    }

    public static Set getBondedDevices(Object obj) {
        Object obj2 = obj;
        return (Set) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getBondedDevices"), obj2, new Object[0]);
    }

    public static boolean checkBluetoothAddress(Object obj, String str) {
        Object obj2 = obj;
        return ((Boolean) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj2.getClass(), "checkBluetoothAddress", (Class<?>[]) new Class[]{String.class}), obj2, str)).booleanValue();
    }

    public static Object getRemoteDevice(Object obj, String str) throws IllegalArgumentException {
        Object obj2 = obj;
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj2.getClass(), "getRemoteDevice", (Class<?>[]) new Class[]{String.class}), obj2, str);
    }

    public static Object listenUsingRfcommWithServiceRecord(Object obj, String str, UUID uuid) throws IOException {
        Object obj2 = obj;
        Class<?> cls = obj2.getClass();
        Class[] clsArr = new Class[2];
        clsArr[0] = String.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = UUID.class;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = uuid;
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) cls, "listenUsingRfcommWithServiceRecord", (Class<?>[]) clsArr2), obj2, objArr2);
    }

    public static Object listenUsingInsecureRfcommWithServiceRecord(Object obj, String str, UUID uuid) throws IOException {
        Object obj2 = obj;
        Class<?> cls = obj2.getClass();
        Class[] clsArr = new Class[2];
        clsArr[0] = String.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = UUID.class;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = uuid;
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) cls, "listenUsingInsecureRfcommWithServiceRecord", (Class<?>[]) clsArr2), obj2, objArr2);
    }

    public static String getBluetoothDeviceName(Object obj) {
        Object obj2 = obj;
        return (String) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getName"), obj2, new Object[0]);
    }

    public static String getBluetoothDeviceAddress(Object obj) {
        Object obj2 = obj;
        return (String) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getAddress"), obj2, new Object[0]);
    }

    public static boolean isBonded(Object obj) {
        Object obj2 = obj;
        return ((Integer) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getBondState"), obj2, new Object[0])).intValue() == 12;
    }

    public static Object getBluetoothClass(Object obj) {
        Object obj2 = obj;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getBluetoothClass"), obj2, new Object[0]);
    }

    public static Object createRfcommSocketToServiceRecord(Object obj, UUID uuid) throws IOException {
        Object obj2 = obj;
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj2.getClass(), "createRfcommSocketToServiceRecord", (Class<?>[]) new Class[]{UUID.class}), obj2, uuid);
    }

    public static Object createInsecureRfcommSocketToServiceRecord(Object obj, UUID uuid) throws IOException {
        Object obj2 = obj;
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj2.getClass(), "createInsecureRfcommSocketToServiceRecord", (Class<?>[]) new Class[]{UUID.class}), obj2, uuid);
    }

    public static int getDeviceClass(Object obj) {
        Object obj2 = obj;
        return ((Integer) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getDeviceClass"), obj2, new Object[0])).intValue();
    }

    public static void connectToBluetoothSocket(Object obj) throws IOException {
        Object obj2 = obj;
        Object wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "connect"), obj2, new Object[0]);
    }

    public static InputStream getInputStream(Object obj) throws IOException {
        Object obj2 = obj;
        return (InputStream) wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getInputStream"), obj2, new Object[0]);
    }

    public static OutputStream getOutputStream(Object obj) throws IOException {
        Object obj2 = obj;
        return (OutputStream) wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "getOutputStream"), obj2, new Object[0]);
    }

    public static void closeBluetoothSocket(Object obj) throws IOException {
        Object obj2 = obj;
        Object wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "close"), obj2, new Object[0]);
    }

    public static Object accept(Object obj) throws IOException {
        Object obj2 = obj;
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "accept"), obj2, new Object[0]);
    }

    public static void closeBluetoothServerSocket(Object obj) throws IOException {
        Object obj2 = obj;
        Object wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2.getClass(), "close"), obj2, new Object[0]);
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class cls, String str) {
        Throwable th;
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            Throwable th2 = th;
            new RuntimeException(noSuchMethodException);
            throw th2;
        }
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class cls, String str, Class<?>... clsArr) {
        Throwable th;
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            Throwable th2 = th;
            new RuntimeException(noSuchMethodException);
            throw th2;
        }
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Method method) {
        Throwable th;
        Throwable th2;
        try {
            return method.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new RuntimeException(illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            Throwable th4 = cause;
            cause.printStackTrace();
            if (th4 instanceof RuntimeException) {
                throw ((RuntimeException) th4);
            }
            Throwable th5 = th;
            new RuntimeException(th4);
            throw th5;
        }
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Method method, Object obj, Object... objArr) {
        Throwable th;
        Throwable th2;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new RuntimeException(illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            Throwable th4 = cause;
            cause.printStackTrace();
            if (th4 instanceof RuntimeException) {
                throw ((RuntimeException) th4);
            }
            Throwable th5 = th;
            new RuntimeException(th4);
            throw th5;
        }
    }

    private static Object B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Method method, Object obj, Object... objArr) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new RuntimeException(illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            InvocationTargetException invocationTargetException = e2;
            InvocationTargetException invocationTargetException2 = invocationTargetException;
            Throwable cause = invocationTargetException.getCause();
            Throwable th4 = cause;
            cause.printStackTrace();
            if (th4 instanceof IllegalArgumentException) {
                throw ((IllegalArgumentException) th4);
            } else if (th4 instanceof RuntimeException) {
                throw ((RuntimeException) th4);
            } else {
                Throwable th5 = th;
                new RuntimeException(invocationTargetException2);
                throw th5;
            }
        }
    }

    private static Object wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Method method, Object obj, Object... objArr) throws IOException {
        Throwable th;
        Throwable th2;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            Throwable th3 = th2;
            new RuntimeException(illegalAccessException);
            throw th3;
        } catch (InvocationTargetException e2) {
            InvocationTargetException invocationTargetException = e2;
            InvocationTargetException invocationTargetException2 = invocationTargetException;
            Throwable cause = invocationTargetException.getCause();
            Throwable th4 = cause;
            cause.printStackTrace();
            if (th4 instanceof IOException) {
                throw ((IOException) th4);
            } else if (th4 instanceof RuntimeException) {
                throw ((RuntimeException) th4);
            } else {
                Throwable th5 = th;
                new RuntimeException(invocationTargetException2);
                throw th5;
            }
        }
    }
}
