package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class SnackbarManager {
    private static final int LONG_DURATION_MS = 2750;
    static final int MSG_TIMEOUT = 0;
    private static final int SHORT_DURATION_MS = 1500;
    private static SnackbarManager snackbarManager;
    private SnackbarRecord currentSnackbar;
    private final Handler handler;
    private final Object lock;
    private SnackbarRecord nextSnackbar;

    interface Callback {
        void dismiss(int i);

        void show();
    }

    static SnackbarManager getInstance() {
        SnackbarManager snackbarManager2;
        if (snackbarManager == null) {
            new SnackbarManager();
            snackbarManager = snackbarManager2;
        }
        return snackbarManager;
    }

    private SnackbarManager() {
        Object obj;
        Handler handler2;
        Handler.Callback callback;
        new Object();
        this.lock = obj;
        new Handler.Callback(this) {
            final /* synthetic */ SnackbarManager this$0;

            {
                this.this$0 = this$0;
            }

            public boolean handleMessage(Message message) {
                Message message2 = message;
                switch (message2.what) {
                    case 0:
                        this.this$0.handleTimeout((SnackbarRecord) message2.obj);
                        return true;
                    default:
                        return false;
                }
            }
        };
        new Handler(Looper.getMainLooper(), callback);
        this.handler = handler2;
    }

    /* JADX INFO: finally extract failed */
    public void show(int i, Callback callback) {
        SnackbarRecord snackbarRecord;
        int duration = i;
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2)) {
                    this.currentSnackbar.duration = duration;
                    this.handler.removeCallbacksAndMessages(this.currentSnackbar);
                    scheduleTimeoutLocked(this.currentSnackbar);
                    return;
                }
                if (isNextSnackbarLocked(callback2)) {
                    this.nextSnackbar.duration = duration;
                } else {
                    new SnackbarRecord(duration, callback2);
                    this.nextSnackbar = snackbarRecord;
                }
                if (this.currentSnackbar == null || !cancelSnackbarLocked(this.currentSnackbar, 4)) {
                    this.currentSnackbar = null;
                    showNextSnackbarLocked();
                    return;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void dismiss(Callback callback, int i) {
        Callback callback2 = callback;
        int event = i;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2)) {
                    boolean cancelSnackbarLocked = cancelSnackbarLocked(this.currentSnackbar, event);
                } else if (isNextSnackbarLocked(callback2)) {
                    boolean cancelSnackbarLocked2 = cancelSnackbarLocked(this.nextSnackbar, event);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public void onDismissed(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2)) {
                    this.currentSnackbar = null;
                    if (this.nextSnackbar != null) {
                        showNextSnackbarLocked();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onShown(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2)) {
                    scheduleTimeoutLocked(this.currentSnackbar);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public void pauseTimeout(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2) && !this.currentSnackbar.paused) {
                    this.currentSnackbar.paused = true;
                    this.handler.removeCallbacksAndMessages(this.currentSnackbar);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void restoreTimeoutIfPaused(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (isCurrentSnackbarLocked(callback2) && this.currentSnackbar.paused) {
                    this.currentSnackbar.paused = false;
                    scheduleTimeoutLocked(this.currentSnackbar);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public boolean isCurrent(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean isCurrentSnackbarLocked = isCurrentSnackbarLocked(callback2);
                return isCurrentSnackbarLocked;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean isCurrentOrNext(Callback callback) {
        Callback callback2 = callback;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = isCurrentSnackbarLocked(callback2) || isNextSnackbarLocked(callback2);
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private static class SnackbarRecord {
        final WeakReference<Callback> callback;
        int duration;
        boolean paused;

        SnackbarRecord(int duration2, Callback callback2) {
            WeakReference<Callback> weakReference;
            new WeakReference<>(callback2);
            this.callback = weakReference;
            this.duration = duration2;
        }

        /* access modifiers changed from: package-private */
        public boolean isSnackbar(Callback callback2) {
            Callback callback3 = callback2;
            return callback3 != null && this.callback.get() == callback3;
        }
    }

    private void showNextSnackbarLocked() {
        if (this.nextSnackbar != null) {
            this.currentSnackbar = this.nextSnackbar;
            this.nextSnackbar = null;
            Callback callback = (Callback) this.currentSnackbar.callback.get();
            if (callback != null) {
                callback.show();
            } else {
                this.currentSnackbar = null;
            }
        }
    }

    private boolean cancelSnackbarLocked(SnackbarRecord snackbarRecord, int i) {
        SnackbarRecord record = snackbarRecord;
        int event = i;
        Callback callback = (Callback) record.callback.get();
        if (callback == null) {
            return false;
        }
        this.handler.removeCallbacksAndMessages(record);
        callback.dismiss(event);
        return true;
    }

    private boolean isCurrentSnackbarLocked(Callback callback) {
        return this.currentSnackbar != null && this.currentSnackbar.isSnackbar(callback);
    }

    private boolean isNextSnackbarLocked(Callback callback) {
        return this.nextSnackbar != null && this.nextSnackbar.isSnackbar(callback);
    }

    private void scheduleTimeoutLocked(SnackbarRecord snackbarRecord) {
        SnackbarRecord r = snackbarRecord;
        if (r.duration != -2) {
            int durationMs = LONG_DURATION_MS;
            if (r.duration > 0) {
                durationMs = r.duration;
            } else if (r.duration == -1) {
                durationMs = 1500;
            }
            this.handler.removeCallbacksAndMessages(r);
            boolean sendMessageDelayed = this.handler.sendMessageDelayed(Message.obtain(this.handler, 0, r), (long) durationMs);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void handleTimeout(SnackbarRecord snackbarRecord) {
        SnackbarRecord record = snackbarRecord;
        Object obj = this.lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.currentSnackbar == record || this.nextSnackbar == record) {
                    boolean cancelSnackbarLocked = cancelSnackbarLocked(record, 2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
