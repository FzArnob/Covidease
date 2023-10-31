package android.support.p000v4.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompat */
public class AccessibilityNodeProviderCompat {
    public static final int HOST_VIEW_ID = -1;
    private final Object mProvider;

    @RequiresApi(16)
    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompat$AccessibilityNodeProviderApi16 */
    static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        final AccessibilityNodeProviderCompat mCompat;

        AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat compat) {
            this.mCompat = compat;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            AccessibilityNodeInfoCompat compatInfo = this.mCompat.createAccessibilityNodeInfo(virtualViewId);
            if (compatInfo == null) {
                return null;
            }
            return compatInfo.unwrap();
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
            List<AccessibilityNodeInfo> list;
            List<AccessibilityNodeInfoCompat> compatInfos = this.mCompat.findAccessibilityNodeInfosByText(text, virtualViewId);
            if (compatInfos == null) {
                return null;
            }
            new ArrayList();
            List<AccessibilityNodeInfo> infoList = list;
            int infoCount = compatInfos.size();
            for (int i = 0; i < infoCount; i++) {
                boolean add = infoList.add(compatInfos.get(i).unwrap());
            }
            return infoList;
        }

        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return this.mCompat.performAction(virtualViewId, action, arguments);
        }
    }

    @RequiresApi(19)
    /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompat$AccessibilityNodeProviderApi19 */
    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat compat) {
            super(compat);
        }

        public AccessibilityNodeInfo findFocus(int focus) {
            AccessibilityNodeInfoCompat compatInfo = this.mCompat.findFocus(focus);
            if (compatInfo == null) {
                return null;
            }
            return compatInfo.unwrap();
        }
    }

    public AccessibilityNodeProviderCompat() {
        Object obj;
        Object obj2;
        if (Build.VERSION.SDK_INT >= 19) {
            new AccessibilityNodeProviderApi19(this);
            this.mProvider = obj2;
        } else if (Build.VERSION.SDK_INT >= 16) {
            new AccessibilityNodeProviderApi16(this);
            this.mProvider = obj;
        } else {
            this.mProvider = null;
        }
    }

    public AccessibilityNodeProviderCompat(Object provider) {
        this.mProvider = provider;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    @Nullable
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
        int i2 = i;
        return null;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        int i3 = i;
        int i4 = i2;
        Bundle bundle2 = bundle;
        return false;
    }

    @Nullable
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String str, int i) {
        String str2 = str;
        int i2 = i;
        return null;
    }

    @Nullable
    public AccessibilityNodeInfoCompat findFocus(int i) {
        int i2 = i;
        return null;
    }
}
