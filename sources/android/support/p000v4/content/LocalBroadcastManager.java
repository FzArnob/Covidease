package android.support.p000v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* renamed from: android.support.v4.content.LocalBroadcastManager */
public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts;
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers;

    /* renamed from: android.support.v4.content.LocalBroadcastManager$ReceiverRecord */
    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter _filter, BroadcastReceiver _receiver) {
            this.filter = _filter;
            this.receiver = _receiver;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder(128);
            StringBuilder builder = sb;
            StringBuilder append = builder.append("Receiver{");
            StringBuilder append2 = builder.append(this.receiver);
            StringBuilder append3 = builder.append(" filter=");
            StringBuilder append4 = builder.append(this.filter);
            if (this.dead) {
                StringBuilder append5 = builder.append(" DEAD");
            }
            StringBuilder append6 = builder.append("}");
            return builder.toString();
        }
    }

    /* renamed from: android.support.v4.content.LocalBroadcastManager$BroadcastRecord */
    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent _intent, ArrayList<ReceiverRecord> _receivers) {
            this.intent = _intent;
            this.receivers = _receivers;
        }
    }

    static {
        Object obj;
        new Object();
        mLock = obj;
    }

    @NonNull
    public static LocalBroadcastManager getInstance(@NonNull Context context) {
        LocalBroadcastManager localBroadcastManager;
        Context context2 = context;
        Object obj = mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (mInstance == null) {
                    new LocalBroadcastManager(context2.getApplicationContext());
                    mInstance = localBroadcastManager;
                }
                LocalBroadcastManager localBroadcastManager2 = mInstance;
                return localBroadcastManager2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private LocalBroadcastManager(Context context) {
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap;
        HashMap<String, ArrayList<ReceiverRecord>> hashMap2;
        ArrayList<BroadcastRecord> arrayList;
        Handler handler;
        Context context2 = context;
        new HashMap<>();
        this.mReceivers = hashMap;
        new HashMap<>();
        this.mActions = hashMap2;
        new ArrayList<>();
        this.mPendingBroadcasts = arrayList;
        this.mAppContext = context2;
        new Handler(this, context2.getMainLooper()) {
            final /* synthetic */ LocalBroadcastManager this$0;

            {
                this.this$0 = this$0;
            }

            public void handleMessage(Message message) {
                Message msg = message;
                switch (msg.what) {
                    case 1:
                        this.this$0.executePendingBroadcasts();
                        return;
                    default:
                        super.handleMessage(msg);
                        return;
                }
            }
        };
        this.mHandler = handler;
    }

    /* JADX INFO: finally extract failed */
    public void registerReceiver(@NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter) {
        Object obj;
        ArrayList arrayList;
        ArrayList arrayList2;
        BroadcastReceiver receiver = broadcastReceiver;
        IntentFilter filter = intentFilter;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap = this.mReceivers;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                new ReceiverRecord(filter, receiver);
                Object obj2 = obj;
                ArrayList arrayList3 = this.mReceivers.get(receiver);
                if (arrayList3 == null) {
                    new ArrayList(1);
                    arrayList3 = arrayList2;
                    ArrayList<ReceiverRecord> put = this.mReceivers.put(receiver, arrayList3);
                }
                boolean add = arrayList3.add(obj2);
                for (int i = 0; i < filter.countActions(); i++) {
                    String action = filter.getAction(i);
                    ArrayList arrayList4 = this.mActions.get(action);
                    if (arrayList4 == null) {
                        new ArrayList(1);
                        arrayList4 = arrayList;
                        ArrayList<ReceiverRecord> put2 = this.mActions.put(action, arrayList4);
                    }
                    boolean add2 = arrayList4.add(obj2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void unregisterReceiver(@NonNull BroadcastReceiver broadcastReceiver) {
        BroadcastReceiver receiver = broadcastReceiver;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap = this.mReceivers;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                ArrayList<ReceiverRecord> filters = this.mReceivers.remove(receiver);
                if (filters == null) {
                    return;
                }
                for (int i = filters.size() - 1; i >= 0; i--) {
                    ReceiverRecord filter = filters.get(i);
                    filter.dead = true;
                    for (int j = 0; j < filter.filter.countActions(); j++) {
                        String action = filter.filter.getAction(j);
                        ArrayList<ReceiverRecord> receivers = this.mActions.get(action);
                        if (receivers != null) {
                            for (int k = receivers.size() - 1; k >= 0; k--) {
                                ReceiverRecord rec = receivers.get(k);
                                if (rec.receiver == receiver) {
                                    rec.dead = true;
                                    ReceiverRecord remove = receivers.remove(k);
                                }
                            }
                            if (receivers.size() <= 0) {
                                ArrayList<ReceiverRecord> remove2 = this.mActions.remove(action);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean sendBroadcast(@NonNull Intent intent) {
        Object obj;
        String reason;
        StringBuilder sb;
        ArrayList arrayList;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        Intent intent2 = intent;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap = this.mReceivers;
        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                String action = intent2.getAction();
                String type = intent2.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                Uri data = intent2.getData();
                String scheme = intent2.getScheme();
                Set<String> categories = intent2.getCategories();
                boolean debug = (intent2.getFlags() & 8) != 0;
                if (debug) {
                    new StringBuilder();
                    int v = Log.v(TAG, sb5.append("Resolving type ").append(type).append(" scheme ").append(scheme).append(" of intent ").append(intent2).toString());
                }
                ArrayList<ReceiverRecord> entries = this.mActions.get(intent2.getAction());
                if (entries != null) {
                    if (debug) {
                        new StringBuilder();
                        int v2 = Log.v(TAG, sb4.append("Action list: ").append(entries).toString());
                    }
                    ArrayList arrayList2 = null;
                    for (int i = 0; i < entries.size(); i++) {
                        ReceiverRecord receiver = entries.get(i);
                        if (debug) {
                            new StringBuilder();
                            int v3 = Log.v(TAG, sb3.append("Matching against filter ").append(receiver.filter).toString());
                        }
                        if (!receiver.broadcasting) {
                            int match = receiver.filter.match(action, type, scheme, data, categories, TAG);
                            if (match >= 0) {
                                if (debug) {
                                    new StringBuilder();
                                    int v4 = Log.v(TAG, sb2.append("  Filter matched!  match=0x").append(Integer.toHexString(match)).toString());
                                }
                                if (arrayList2 == null) {
                                    new ArrayList();
                                    arrayList2 = arrayList;
                                }
                                boolean add = arrayList2.add(receiver);
                                receiver.broadcasting = true;
                            } else if (debug) {
                                switch (match) {
                                    case -4:
                                        reason = "category";
                                        break;
                                    case -3:
                                        reason = "action";
                                        break;
                                    case -2:
                                        reason = "data";
                                        break;
                                    case -1:
                                        reason = "type";
                                        break;
                                    default:
                                        reason = "unknown reason";
                                        break;
                                }
                                new StringBuilder();
                                int v5 = Log.v(TAG, sb.append("  Filter did not match: ").append(reason).toString());
                            }
                        } else if (debug) {
                            int v6 = Log.v(TAG, "  Filter's target already added");
                        }
                    }
                    if (arrayList2 != null) {
                        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                            ((ReceiverRecord) arrayList2.get(i2)).broadcasting = false;
                        }
                        new BroadcastRecord(intent2, arrayList2);
                        boolean add2 = this.mPendingBroadcasts.add(obj);
                        if (!this.mHandler.hasMessages(1)) {
                            boolean sendEmptyMessage = this.mHandler.sendEmptyMessage(1);
                        }
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    public void sendBroadcastSync(@NonNull Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void executePendingBroadcasts() {
        while (true) {
            HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap = this.mReceivers;
            HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap2 = hashMap;
            synchronized (hashMap) {
                try {
                    int N = this.mPendingBroadcasts.size();
                    if (N <= 0) {
                        return;
                    }
                    BroadcastRecord[] brs = new BroadcastRecord[N];
                    Object[] array = this.mPendingBroadcasts.toArray(brs);
                    this.mPendingBroadcasts.clear();
                    for (int i = 0; i < brs.length; i++) {
                        BroadcastRecord br = brs[i];
                        int nbr = br.receivers.size();
                        for (int j = 0; j < nbr; j++) {
                            ReceiverRecord rec = br.receivers.get(j);
                            if (!rec.dead) {
                                rec.receiver.onReceive(this.mAppContext, br.intent);
                            }
                        }
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> hashMap3 = hashMap2;
                        throw th2;
                    }
                }
            }
        }
    }
}
