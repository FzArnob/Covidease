package android.support.p000v4.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.view.KeyEventDispatcher */
public class KeyEventDispatcher {
    private static boolean sActionBarFieldsFetched = false;
    private static Method sActionBarOnMenuKeyMethod = null;
    private static boolean sDialogFieldsFetched = false;
    private static Field sDialogKeyListenerField = null;

    /* renamed from: android.support.v4.view.KeyEventDispatcher$Component */
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent keyEvent);
    }

    private KeyEventDispatcher() {
    }

    public static boolean dispatchBeforeHierarchy(@NonNull View root, @NonNull KeyEvent event) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(root, event);
    }

    public static boolean dispatchKeyEvent(@NonNull Component component, @Nullable View view, @Nullable Window.Callback callback, @NonNull KeyEvent keyEvent) {
        Component component2 = component;
        View root = view;
        Window.Callback callback2 = callback;
        KeyEvent event = keyEvent;
        if (component2 == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return component2.superDispatchKeyEvent(event);
        }
        if (callback2 instanceof Activity) {
            return activitySuperDispatchKeyEventPre28((Activity) callback2, event);
        }
        if (callback2 instanceof Dialog) {
            return dialogSuperDispatchKeyEventPre28((Dialog) callback2, event);
        }
        return (root != null && ViewCompat.dispatchUnhandledKeyEventBeforeCallback(root, event)) || component2.superDispatchKeyEvent(event);
    }

    private static boolean actionBarOnMenuKeyEventPre28(ActionBar actionBar, KeyEvent keyEvent) {
        ActionBar actionBar2 = actionBar;
        KeyEvent event = keyEvent;
        if (!sActionBarFieldsFetched) {
            try {
                sActionBarOnMenuKeyMethod = actionBar2.getClass().getMethod("onMenuKeyEvent", new Class[]{KeyEvent.class});
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
            }
            sActionBarFieldsFetched = true;
        }
        if (sActionBarOnMenuKeyMethod != null) {
            try {
                return ((Boolean) sActionBarOnMenuKeyMethod.invoke(actionBar2, new Object[]{event})).booleanValue();
            } catch (IllegalAccessException e2) {
                IllegalAccessException illegalAccessException = e2;
            } catch (InvocationTargetException e3) {
                InvocationTargetException invocationTargetException = e3;
            }
        }
        return false;
    }

    private static boolean activitySuperDispatchKeyEventPre28(Activity activity, KeyEvent keyEvent) {
        Activity activity2 = activity;
        KeyEvent event = keyEvent;
        activity2.onUserInteraction();
        Window win = activity2.getWindow();
        if (win.hasFeature(8)) {
            ActionBar actionBar = activity2.getActionBar();
            if (event.getKeyCode() == 82 && actionBar != null && actionBarOnMenuKeyEventPre28(actionBar, event)) {
                return true;
            }
        }
        if (win.superDispatchKeyEvent(event)) {
            return true;
        }
        View decor = win.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(decor, event)) {
            return true;
        }
        return event.dispatch(activity2, decor != null ? decor.getKeyDispatcherState() : null, activity2);
    }

    private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog dialog) {
        Dialog dialog2 = dialog;
        if (!sDialogFieldsFetched) {
            try {
                sDialogKeyListenerField = Dialog.class.getDeclaredField("mOnKeyListener");
                sDialogKeyListenerField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                NoSuchFieldException noSuchFieldException = e;
            }
            sDialogFieldsFetched = true;
        }
        if (sDialogKeyListenerField != null) {
            try {
                return (DialogInterface.OnKeyListener) sDialogKeyListenerField.get(dialog2);
            } catch (IllegalAccessException e2) {
                IllegalAccessException illegalAccessException = e2;
            }
        }
        return null;
    }

    private static boolean dialogSuperDispatchKeyEventPre28(Dialog dialog, KeyEvent keyEvent) {
        Dialog dialog2 = dialog;
        KeyEvent event = keyEvent;
        DialogInterface.OnKeyListener onKeyListener = getDialogKeyListenerPre28(dialog2);
        if (onKeyListener != null && onKeyListener.onKey(dialog2, event.getKeyCode(), event)) {
            return true;
        }
        Window win = dialog2.getWindow();
        if (win.superDispatchKeyEvent(event)) {
            return true;
        }
        View decor = win.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(decor, event)) {
            return true;
        }
        return event.dispatch(dialog2, decor != null ? decor.getKeyDispatcherState() : null, dialog2);
    }
}
