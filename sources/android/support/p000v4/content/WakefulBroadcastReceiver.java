package android.support.p000v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
/* renamed from: android.support.v4.content.WakefulBroadcastReceiver */
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static int mNextId = 1;
    private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks;

    public WakefulBroadcastReceiver() {
    }

    static {
        SparseArray<PowerManager.WakeLock> sparseArray;
        new SparseArray<>();
        sActiveWakeLocks = sparseArray;
    }

    public static ComponentName startWakefulService(Context context, Intent intent) {
        StringBuilder sb;
        Context context2 = context;
        Intent intent2 = intent;
        SparseArray<PowerManager.WakeLock> sparseArray = sActiveWakeLocks;
        SparseArray<PowerManager.WakeLock> sparseArray2 = sparseArray;
        synchronized (sparseArray) {
            try {
                int id = mNextId;
                mNextId++;
                if (mNextId <= 0) {
                    mNextId = 1;
                }
                Intent putExtra = intent2.putExtra(EXTRA_WAKE_LOCK_ID, id);
                ComponentName comp = context2.startService(intent2);
                if (comp == null) {
                    return null;
                }
                new StringBuilder();
                PowerManager.WakeLock wl = ((PowerManager) context2.getSystemService("power")).newWakeLock(1, sb.append("androidx.core:wake:").append(comp.flattenToShortString()).toString());
                wl.setReferenceCounted(false);
                wl.acquire(60000);
                sActiveWakeLocks.put(id, wl);
                ComponentName componentName = comp;
                return componentName;
            } catch (Throwable th) {
                Throwable th2 = th;
                SparseArray<PowerManager.WakeLock> sparseArray3 = sparseArray2;
                throw th2;
            }
        }
    }

    public static boolean completeWakefulIntent(Intent intent) {
        StringBuilder sb;
        int id = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        if (id == 0) {
            return false;
        }
        SparseArray<PowerManager.WakeLock> sparseArray = sActiveWakeLocks;
        SparseArray<PowerManager.WakeLock> sparseArray2 = sparseArray;
        synchronized (sparseArray) {
            try {
                PowerManager.WakeLock wl = sActiveWakeLocks.get(id);
                if (wl != null) {
                    wl.release();
                    sActiveWakeLocks.remove(id);
                    return true;
                }
                new StringBuilder();
                int w = Log.w("WakefulBroadcastReceiv.", sb.append("No active wake lock id #").append(id).toString());
                return true;
            } catch (Throwable th) {
                Throwable th2 = th;
                SparseArray<PowerManager.WakeLock> sparseArray3 = sparseArray2;
                throw th2;
            }
        }
    }
}
