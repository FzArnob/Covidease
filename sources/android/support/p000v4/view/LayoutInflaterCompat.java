package android.support.p000v4.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

/* renamed from: android.support.v4.view.LayoutInflaterCompat */
public final class LayoutInflaterCompat {
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    /* renamed from: android.support.v4.view.LayoutInflaterCompat$Factory2Wrapper */
    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory delegateFactory) {
            this.mDelegateFactory = delegateFactory;
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return this.mDelegateFactory.onCreateView((View) null, name, context, attrs);
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(parent, name, context, attributeSet);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(getClass().getName()).append("{").append(this.mDelegateFactory).append("}").toString();
        }
    }

    private static void forceSetFactory2(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        StringBuilder sb;
        StringBuilder sb2;
        LayoutInflater inflater = layoutInflater;
        LayoutInflater.Factory2 factory = factory2;
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                new StringBuilder();
                int e2 = Log.e(TAG, sb2.append("forceSetFactory2 Could not find field 'mFactory2' on class ").append(LayoutInflater.class.getName()).append("; inflation may have unexpected results.").toString(), e);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field != null) {
            try {
                sLayoutInflaterFactory2Field.set(inflater, factory);
            } catch (IllegalAccessException e3) {
                new StringBuilder();
                int e4 = Log.e(TAG, sb.append("forceSetFactory2 could not set the Factory2 on LayoutInflater ").append(inflater).append("; inflation may have unexpected results.").toString(), e3);
            }
        }
    }

    private LayoutInflaterCompat() {
    }

    @Deprecated
    public static void setFactory(@NonNull LayoutInflater layoutInflater, @NonNull LayoutInflaterFactory layoutInflaterFactory) {
        LayoutInflater.Factory2 factory2;
        LayoutInflater.Factory2 factory22;
        LayoutInflater.Factory2 factory23;
        LayoutInflater.Factory2 factory24;
        LayoutInflater inflater = layoutInflater;
        LayoutInflaterFactory factory = layoutInflaterFactory;
        if (Build.VERSION.SDK_INT >= 21) {
            LayoutInflater layoutInflater2 = inflater;
            if (factory != null) {
                factory23 = factory24;
                new Factory2Wrapper(factory);
            } else {
                factory23 = null;
            }
            layoutInflater2.setFactory2(factory23);
            return;
        }
        if (factory != null) {
            factory2 = factory22;
            new Factory2Wrapper(factory);
        } else {
            factory2 = null;
        }
        LayoutInflater.Factory2 factory25 = factory2;
        inflater.setFactory2(factory25);
        LayoutInflater.Factory f = inflater.getFactory();
        if (f instanceof LayoutInflater.Factory2) {
            forceSetFactory2(inflater, (LayoutInflater.Factory2) f);
        } else {
            forceSetFactory2(inflater, factory25);
        }
    }

    public static void setFactory2(@NonNull LayoutInflater layoutInflater, @NonNull LayoutInflater.Factory2 factory2) {
        LayoutInflater inflater = layoutInflater;
        LayoutInflater.Factory2 factory = factory2;
        inflater.setFactory2(factory);
        if (Build.VERSION.SDK_INT < 21) {
            LayoutInflater.Factory f = inflater.getFactory();
            if (f instanceof LayoutInflater.Factory2) {
                forceSetFactory2(inflater, (LayoutInflater.Factory2) f);
            } else {
                forceSetFactory2(inflater, factory);
            }
        }
    }

    @Deprecated
    public static LayoutInflaterFactory getFactory(LayoutInflater inflater) {
        LayoutInflater.Factory factory = inflater.getFactory();
        if (factory instanceof Factory2Wrapper) {
            return ((Factory2Wrapper) factory).mDelegateFactory;
        }
        return null;
    }
}
