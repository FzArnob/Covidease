package android.support.p000v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
/* renamed from: android.support.v4.app.NotificationCompatJellybean */
class NotificationCompatJellybean {
    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_LABEL = "label";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_RESULT_KEY = "resultKey";
    private static final String KEY_SEMANTIC_ACTION = "semanticAction";
    private static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;

    static {
        Object obj;
        Object obj2;
        new Object();
        sExtrasLock = obj;
        new Object();
        sActionsLock = obj2;
    }

    public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> list) {
        SparseArray<Bundle> sparseArray;
        List<Bundle> actionExtrasList = list;
        SparseArray<Bundle> actionExtrasMap = null;
        int count = actionExtrasList.size();
        for (int i = 0; i < count; i++) {
            Bundle actionExtras = actionExtrasList.get(i);
            if (actionExtras != null) {
                if (actionExtrasMap == null) {
                    new SparseArray<>();
                    actionExtrasMap = sparseArray;
                }
                actionExtrasMap.put(i, actionExtras);
            }
        }
        return actionExtrasMap;
    }

    public static Bundle getExtras(Notification notification) {
        Bundle bundle;
        Notification notif = notification;
        Object obj = sExtrasLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (sExtrasFieldAccessFailed) {
                    return null;
                }
                if (sExtrasField == null) {
                    Field extrasField = Notification.class.getDeclaredField(KEY_EXTRAS);
                    if (!Bundle.class.isAssignableFrom(extrasField.getType())) {
                        int e = Log.e(TAG, "Notification.extras field is not of type Bundle");
                        sExtrasFieldAccessFailed = true;
                        return null;
                    }
                    extrasField.setAccessible(true);
                    sExtrasField = extrasField;
                }
                Bundle extras = (Bundle) sExtrasField.get(notif);
                if (extras == null) {
                    new Bundle();
                    extras = bundle;
                    sExtrasField.set(notif, extras);
                }
                Bundle bundle2 = extras;
                return bundle2;
            } catch (IllegalAccessException e2) {
                int e3 = Log.e(TAG, "Unable to access notification extras", e2);
                sExtrasFieldAccessFailed = true;
                return null;
            } catch (NoSuchFieldException e4) {
                int e5 = Log.e(TAG, "Unable to access notification extras", e4);
                sExtrasFieldAccessFailed = true;
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public static NotificationCompat.Action readAction(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        NotificationCompat.Action action;
        int icon = i;
        CharSequence title = charSequence;
        PendingIntent actionIntent = pendingIntent;
        Bundle extras = bundle;
        RemoteInput[] remoteInputs = null;
        RemoteInput[] dataOnlyRemoteInputs = null;
        boolean allowGeneratedReplies = false;
        if (extras != null) {
            remoteInputs = fromBundleArray(getBundleArrayFromBundle(extras, NotificationCompatExtras.EXTRA_REMOTE_INPUTS));
            dataOnlyRemoteInputs = fromBundleArray(getBundleArrayFromBundle(extras, EXTRA_DATA_ONLY_REMOTE_INPUTS));
            allowGeneratedReplies = extras.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES);
        }
        new NotificationCompat.Action(icon, title, actionIntent, extras, remoteInputs, dataOnlyRemoteInputs, allowGeneratedReplies, 0, true);
        return action;
    }

    public static Bundle writeActionAndGetExtras(Notification.Builder builder, NotificationCompat.Action action) {
        Bundle bundle;
        NotificationCompat.Action action2 = action;
        Notification.Builder addAction = builder.addAction(action2.getIcon(), action2.getTitle(), action2.getActionIntent());
        new Bundle(action2.getExtras());
        Bundle actionExtras = bundle;
        if (action2.getRemoteInputs() != null) {
            actionExtras.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, toBundleArray(action2.getRemoteInputs()));
        }
        if (action2.getDataOnlyRemoteInputs() != null) {
            actionExtras.putParcelableArray(EXTRA_DATA_ONLY_REMOTE_INPUTS, toBundleArray(action2.getDataOnlyRemoteInputs()));
        }
        actionExtras.putBoolean(EXTRA_ALLOW_GENERATED_REPLIES, action2.getAllowGeneratedReplies());
        return actionExtras;
    }

    public static int getActionCount(Notification notification) {
        Notification notif = notification;
        Object obj = sActionsLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Object[] actionObjects = getActionObjectsLocked(notif);
                int length = actionObjects != null ? actionObjects.length : 0;
                return length;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.p000v4.app.NotificationCompat.Action getAction(android.app.Notification r14, int r15) {
        /*
            r0 = r14
            r1 = r15
            java.lang.Object r9 = sActionsLock
            r13 = r9
            r9 = r13
            r10 = r13
            r2 = r10
            monitor-enter(r9)
            r9 = r0
            java.lang.Object[] r9 = getActionObjectsLocked(r9)     // Catch:{ IllegalAccessException -> 0x005e }
            r3 = r9
            r9 = r3
            if (r9 == 0) goto L_0x0059
            r9 = r3
            r10 = r1
            r9 = r9[r10]     // Catch:{ IllegalAccessException -> 0x005e }
            r4 = r9
            r9 = 0
            r5 = r9
            r9 = r0
            android.os.Bundle r9 = getExtras(r9)     // Catch:{ IllegalAccessException -> 0x005e }
            r6 = r9
            r9 = r6
            if (r9 == 0) goto L_0x0037
            r9 = r6
            java.lang.String r10 = "android.support.actionExtras"
            android.util.SparseArray r9 = r9.getSparseParcelableArray(r10)     // Catch:{ IllegalAccessException -> 0x005e }
            r7 = r9
            r9 = r7
            if (r9 == 0) goto L_0x0037
            r9 = r7
            r10 = r1
            java.lang.Object r9 = r9.get(r10)     // Catch:{ IllegalAccessException -> 0x005e }
            android.os.Bundle r9 = (android.os.Bundle) r9     // Catch:{ IllegalAccessException -> 0x005e }
            r5 = r9
        L_0x0037:
            java.lang.reflect.Field r9 = sActionIconField     // Catch:{ IllegalAccessException -> 0x005e }
            r10 = r4
            int r9 = r9.getInt(r10)     // Catch:{ IllegalAccessException -> 0x005e }
            java.lang.reflect.Field r10 = sActionTitleField     // Catch:{ IllegalAccessException -> 0x005e }
            r11 = r4
            java.lang.Object r10 = r10.get(r11)     // Catch:{ IllegalAccessException -> 0x005e }
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ IllegalAccessException -> 0x005e }
            java.lang.reflect.Field r11 = sActionIntentField     // Catch:{ IllegalAccessException -> 0x005e }
            r12 = r4
            java.lang.Object r11 = r11.get(r12)     // Catch:{ IllegalAccessException -> 0x005e }
            android.app.PendingIntent r11 = (android.app.PendingIntent) r11     // Catch:{ IllegalAccessException -> 0x005e }
            r12 = r5
            android.support.v4.app.NotificationCompat$Action r9 = readAction(r9, r10, r11, r12)     // Catch:{ IllegalAccessException -> 0x005e }
            r10 = r2
            monitor-exit(r10)     // Catch:{ all -> 0x006f }
            r0 = r9
        L_0x0058:
            return r0
        L_0x0059:
            r9 = r2
            monitor-exit(r9)     // Catch:{ all -> 0x006f }
            r9 = 0
            r0 = r9
            goto L_0x0058
        L_0x005e:
            r9 = move-exception
            r3 = r9
            java.lang.String r9 = "NotificationCompat"
            java.lang.String r10 = "Unable to access notification actions"
            r11 = r3
            int r9 = android.util.Log.e(r9, r10, r11)     // Catch:{ all -> 0x006f }
            r9 = 1
            sActionsAccessFailed = r9     // Catch:{ all -> 0x006f }
            goto L_0x0059
        L_0x006f:
            r9 = move-exception
            r8 = r9
            r9 = r2
            monitor-exit(r9)     // Catch:{ all -> 0x006f }
            r9 = r8
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.NotificationCompatJellybean.getAction(android.app.Notification, int):android.support.v4.app.NotificationCompat$Action");
    }

    private static Object[] getActionObjectsLocked(Notification notification) {
        Notification notif = notification;
        Object obj = sActionsLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!ensureActionReflectionReadyLocked()) {
                    return null;
                }
                Object[] objArr = (Object[]) sActionsField.get(notif);
                return objArr;
            } catch (IllegalAccessException e) {
                int e2 = Log.e(TAG, "Unable to access notification actions", e);
                sActionsAccessFailed = true;
                Object obj3 = obj2;
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj4 = obj2;
                throw th2;
            }
        }
    }

    private static boolean ensureActionReflectionReadyLocked() {
        boolean z;
        if (sActionsAccessFailed) {
            return false;
        }
        try {
            if (sActionsField == null) {
                sActionClass = Class.forName("android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField(KEY_ICON);
                sActionTitleField = sActionClass.getDeclaredField(KEY_TITLE);
                sActionIntentField = sActionClass.getDeclaredField(KEY_ACTION_INTENT);
                sActionsField = Notification.class.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        } catch (ClassNotFoundException e) {
            int e2 = Log.e(TAG, "Unable to access notification actions", e);
            sActionsAccessFailed = true;
        } catch (NoSuchFieldException e3) {
            int e4 = Log.e(TAG, "Unable to access notification actions", e3);
            sActionsAccessFailed = true;
        }
        if (!sActionsAccessFailed) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    static NotificationCompat.Action getActionFromBundle(Bundle bundle) {
        NotificationCompat.Action action;
        Bundle bundle2 = bundle;
        Bundle extras = bundle2.getBundle(KEY_EXTRAS);
        boolean allowGeneratedReplies = false;
        if (extras != null) {
            allowGeneratedReplies = extras.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES, false);
        }
        new NotificationCompat.Action(bundle2.getInt(KEY_ICON), bundle2.getCharSequence(KEY_TITLE), (PendingIntent) bundle2.getParcelable(KEY_ACTION_INTENT), bundle2.getBundle(KEY_EXTRAS), fromBundleArray(getBundleArrayFromBundle(bundle2, KEY_REMOTE_INPUTS)), fromBundleArray(getBundleArrayFromBundle(bundle2, KEY_DATA_ONLY_REMOTE_INPUTS)), allowGeneratedReplies, bundle2.getInt(KEY_SEMANTIC_ACTION), bundle2.getBoolean(KEY_SHOWS_USER_INTERFACE));
        return action;
    }

    static Bundle getBundleForAction(NotificationCompat.Action action) {
        Bundle bundle;
        Bundle bundle2;
        Bundle actionExtras;
        Bundle bundle3;
        NotificationCompat.Action action2 = action;
        new Bundle();
        Bundle bundle4 = bundle;
        bundle4.putInt(KEY_ICON, action2.getIcon());
        bundle4.putCharSequence(KEY_TITLE, action2.getTitle());
        bundle4.putParcelable(KEY_ACTION_INTENT, action2.getActionIntent());
        if (action2.getExtras() != null) {
            new Bundle(action2.getExtras());
            actionExtras = bundle3;
        } else {
            new Bundle();
            actionExtras = bundle2;
        }
        actionExtras.putBoolean(EXTRA_ALLOW_GENERATED_REPLIES, action2.getAllowGeneratedReplies());
        bundle4.putBundle(KEY_EXTRAS, actionExtras);
        bundle4.putParcelableArray(KEY_REMOTE_INPUTS, toBundleArray(action2.getRemoteInputs()));
        bundle4.putBoolean(KEY_SHOWS_USER_INTERFACE, action2.getShowsUserInterface());
        bundle4.putInt(KEY_SEMANTIC_ACTION, action2.getSemanticAction());
        return bundle4;
    }

    private static RemoteInput fromBundle(Bundle bundle) {
        Set<String> set;
        RemoteInput remoteInput;
        Bundle data = bundle;
        ArrayList<String> allowedDataTypesAsList = data.getStringArrayList(KEY_ALLOWED_DATA_TYPES);
        new HashSet<>();
        Set<String> allowedDataTypes = set;
        if (allowedDataTypesAsList != null) {
            Iterator<String> it = allowedDataTypesAsList.iterator();
            while (it.hasNext()) {
                boolean add = allowedDataTypes.add(it.next());
            }
        }
        new RemoteInput(data.getString(KEY_RESULT_KEY), data.getCharSequence(KEY_LABEL), data.getCharSequenceArray("choices"), data.getBoolean(KEY_ALLOW_FREE_FORM_INPUT), data.getBundle(KEY_EXTRAS), allowedDataTypes);
        return remoteInput;
    }

    private static Bundle toBundle(RemoteInput remoteInput) {
        Bundle bundle;
        ArrayList arrayList;
        RemoteInput remoteInput2 = remoteInput;
        new Bundle();
        Bundle data = bundle;
        data.putString(KEY_RESULT_KEY, remoteInput2.getResultKey());
        data.putCharSequence(KEY_LABEL, remoteInput2.getLabel());
        data.putCharSequenceArray("choices", remoteInput2.getChoices());
        data.putBoolean(KEY_ALLOW_FREE_FORM_INPUT, remoteInput2.getAllowFreeFormInput());
        data.putBundle(KEY_EXTRAS, remoteInput2.getExtras());
        Set<String> allowedDataTypes = remoteInput2.getAllowedDataTypes();
        if (allowedDataTypes != null && !allowedDataTypes.isEmpty()) {
            new ArrayList(allowedDataTypes.size());
            ArrayList arrayList2 = arrayList;
            for (String type : allowedDataTypes) {
                boolean add = arrayList2.add(type);
            }
            data.putStringArrayList(KEY_ALLOWED_DATA_TYPES, arrayList2);
        }
        return data;
    }

    private static RemoteInput[] fromBundleArray(Bundle[] bundleArr) {
        Bundle[] bundles = bundleArr;
        if (bundles == null) {
            return null;
        }
        RemoteInput[] remoteInputs = new RemoteInput[bundles.length];
        for (int i = 0; i < bundles.length; i++) {
            remoteInputs[i] = fromBundle(bundles[i]);
        }
        return remoteInputs;
    }

    private static Bundle[] toBundleArray(RemoteInput[] remoteInputArr) {
        RemoteInput[] remoteInputs = remoteInputArr;
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundles[i] = toBundle(remoteInputs[i]);
        }
        return bundles;
    }

    private static Bundle[] getBundleArrayFromBundle(Bundle bundle, String str) {
        Bundle bundle2 = bundle;
        String key = str;
        Parcelable[] array = bundle2.getParcelableArray(key);
        if ((array instanceof Bundle[]) || array == null) {
            return (Bundle[]) array;
        }
        Bundle[] typedArray = (Bundle[]) Arrays.copyOf(array, array.length, Bundle[].class);
        bundle2.putParcelableArray(key, typedArray);
        return typedArray;
    }

    private NotificationCompatJellybean() {
    }
}
