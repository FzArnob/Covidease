package com.google.appinventor.components.runtime;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.DeviceStorage;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", iconName = "images/devicetools.png", nonVisible = true, version = 1)
public class MemoryInfo extends AndroidNonvisibleComponent implements Component {
    private final ComponentContainer container;
    private final Context context;
    private final long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MemoryInfo(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r1 = r6
            r2 = r7
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r1
            r4 = 1048576(0x100000, double:5.180654E-318)
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r1
            r4 = r2
            r3.container = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MemoryInfo.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Total RAM size in Gigabytes.")
    public float MemoryTotal() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_TOTAL);
    }

    @SimpleFunction(description = "Total free RAM size in Gigabytes.")
    public float MemoryFree() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-memory in Gigabytes.")
    public float MemoryUsed() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_USED);
    }

    @SimpleFunction(description = "Total external storage size in Gigabytes.")
    public float ExternalStorageTotal() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Available size of external storage in Gigabytes.")
    public float ExternalStorageAvailable() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-external-storage in Gigabytes.")
    public float ExternalStorageUsed() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_USED);
    }

    @SimpleFunction(description = "Total size of internal storage in Gigabytes.")
    public float InternalStorageTotal() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Size of available internal storage in Gigabytes.")
    public float InternalStorageAvailable() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-internal-storage in Gigabytes.")
    public float InternalStorageUsed() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_USED);
    }

    private static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage deviceStorage) {
        StatFs statFs;
        DeviceStorage deviceStorage2 = deviceStorage;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0.0f;
        }
        new StatFs(Environment.getExternalStorageDirectory().getPath());
        StatFs statFs2 = statFs;
        StatFs statFs3 = statFs2;
        long blockSize = (long) statFs2.getBlockSize();
        long blockCount = (long) statFs3.getBlockCount();
        long availableBlocks = (long) statFs3.getAvailableBlocks();
        long j = (blockCount * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = (availableBlocks * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j - j2;
        switch (deviceStorage2) {
            case EXTERNAL_STORAGE_TOTAL:
                return ((float) j) / 1000.0f;
            case EXTERNAL_STORAGE_AVAILABLE:
                return ((float) j2) / 1000.0f;
            case EXTERNAL_STORAGE_USED:
                return ((float) j3) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    private static float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage deviceStorage) {
        StatFs statFs;
        new StatFs(Environment.getDataDirectory().getPath());
        StatFs statFs2 = statFs;
        StatFs statFs3 = statFs2;
        long blockSize = (long) statFs2.getBlockSize();
        long availableBlocks = (long) statFs3.getAvailableBlocks();
        long blockCount = (long) statFs3.getBlockCount();
        long j = (availableBlocks * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = (blockCount * blockSize) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j2;
        long j4 = j2 - j;
        switch (deviceStorage) {
            case INTERNAL_STORAGE_TOTAL:
                return ((float) j3) / 1000.0f;
            case INTERNAL_STORAGE_AVAILABLE:
                return ((float) j) / 1000.0f;
            case INTERNAL_STORAGE_USED:
                return ((float) j4) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    private float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage deviceStorage) {
        ActivityManager.MemoryInfo memoryInfo;
        new ActivityManager.MemoryInfo();
        ActivityManager.MemoryInfo memoryInfo2 = memoryInfo;
        ((ActivityManager) this.context.getSystemService("activity")).getMemoryInfo(memoryInfo2);
        long j = memoryInfo2.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j2 = memoryInfo2.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        long j3 = j2;
        long j4 = j2 - j;
        switch (deviceStorage) {
            case MEMORY_TOTAL:
                return ((float) j3) / 1000.0f;
            case MEMORY_AVAILABLE:
                return ((float) j) / 1000.0f;
            case MEMORY_USED:
                return ((float) j4) / 1000.0f;
            default:
                return 0.0f;
        }
    }
}
