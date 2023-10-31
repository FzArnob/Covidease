package android.support.p000v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.p000v4.util.Pools;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: android.support.v4.view.AsyncLayoutInflater */
public final class AsyncLayoutInflater {
    private static final String TAG = "AsyncLayoutInflater";
    Handler mHandler;
    private Handler.Callback mHandlerCallback;
    InflateThread mInflateThread = InflateThread.getInstance();
    LayoutInflater mInflater;

    /* renamed from: android.support.v4.view.AsyncLayoutInflater$OnInflateFinishedListener */
    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View view, @LayoutRes int i, @Nullable ViewGroup viewGroup);
    }

    public AsyncLayoutInflater(@NonNull Context context) {
        Handler.Callback callback;
        LayoutInflater layoutInflater;
        Handler handler;
        new Handler.Callback(this) {
            final /* synthetic */ AsyncLayoutInflater this$0;

            {
                this.this$0 = this$0;
            }

            public boolean handleMessage(Message msg) {
                InflateRequest request = (InflateRequest) msg.obj;
                if (request.view == null) {
                    request.view = this.this$0.mInflater.inflate(request.resid, request.parent, false);
                }
                request.callback.onInflateFinished(request.view, request.resid, request.parent);
                this.this$0.mInflateThread.releaseRequest(request);
                return true;
            }
        };
        this.mHandlerCallback = callback;
        new BasicInflater(context);
        this.mInflater = layoutInflater;
        new Handler(this.mHandlerCallback);
        this.mHandler = handler;
    }

    @UiThread
    public void inflate(@LayoutRes int i, @Nullable ViewGroup viewGroup, @NonNull OnInflateFinishedListener onInflateFinishedListener) {
        Throwable th;
        int resid = i;
        ViewGroup parent = viewGroup;
        OnInflateFinishedListener callback = onInflateFinishedListener;
        if (callback == null) {
            Throwable th2 = th;
            new NullPointerException("callback argument may not be null!");
            throw th2;
        }
        InflateRequest request = this.mInflateThread.obtainRequest();
        request.inflater = this;
        request.resid = resid;
        request.parent = parent;
        request.callback = callback;
        this.mInflateThread.enqueue(request);
    }

    /* renamed from: android.support.v4.view.AsyncLayoutInflater$InflateRequest */
    private static class InflateRequest {
        OnInflateFinishedListener callback;
        AsyncLayoutInflater inflater;
        ViewGroup parent;
        int resid;
        View view;

        InflateRequest() {
        }
    }

    /* renamed from: android.support.v4.view.AsyncLayoutInflater$BasicInflater */
    private static class BasicInflater extends LayoutInflater {
        private static final String[] sClassPrefixList;

        static {
            String[] strArr = new String[3];
            strArr[0] = "android.widget.";
            String[] strArr2 = strArr;
            strArr2[1] = "android.webkit.";
            String[] strArr3 = strArr2;
            strArr3[2] = "android.app.";
            sClassPrefixList = strArr3;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        BasicInflater(Context context) {
            super(context);
        }

        public LayoutInflater cloneInContext(Context newContext) {
            BasicInflater basicInflater;
            new BasicInflater(newContext);
            return basicInflater;
        }

        /* access modifiers changed from: protected */
        public View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
            String name = str;
            AttributeSet attrs = attributeSet;
            String[] strArr = sClassPrefixList;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                try {
                    View view = createView(name, strArr[i], attrs);
                    if (view != null) {
                        return view;
                    }
                    i++;
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException classNotFoundException = e;
                }
            }
            return super.onCreateView(name, attrs);
        }
    }

    /* renamed from: android.support.v4.view.AsyncLayoutInflater$InflateThread */
    private static class InflateThread extends Thread {
        private static final InflateThread sInstance;
        private ArrayBlockingQueue<InflateRequest> mQueue;
        private Pools.SynchronizedPool<InflateRequest> mRequestPool;

        private InflateThread() {
            ArrayBlockingQueue<InflateRequest> arrayBlockingQueue;
            Pools.SynchronizedPool<InflateRequest> synchronizedPool;
            new ArrayBlockingQueue<>(10);
            this.mQueue = arrayBlockingQueue;
            new Pools.SynchronizedPool<>(10);
            this.mRequestPool = synchronizedPool;
        }

        static {
            InflateThread inflateThread;
            new InflateThread();
            sInstance = inflateThread;
            sInstance.start();
        }

        public static InflateThread getInstance() {
            return sInstance;
        }

        public void runInner() {
            try {
                InflateRequest request = this.mQueue.take();
                try {
                    request.view = request.inflater.mInflater.inflate(request.resid, request.parent, false);
                } catch (RuntimeException e) {
                    int w = Log.w(AsyncLayoutInflater.TAG, "Failed to inflate resource in the background! Retrying on the UI thread", e);
                }
                Message.obtain(request.inflater.mHandler, 0, request).sendToTarget();
            } catch (InterruptedException e2) {
                int w2 = Log.w(AsyncLayoutInflater.TAG, e2);
            }
        }

        public void run() {
            while (true) {
                runInner();
            }
        }

        public InflateRequest obtainRequest() {
            InflateRequest inflateRequest;
            InflateRequest obj = this.mRequestPool.acquire();
            if (obj == null) {
                new InflateRequest();
                obj = inflateRequest;
            }
            return obj;
        }

        public void releaseRequest(InflateRequest inflateRequest) {
            InflateRequest obj = inflateRequest;
            obj.callback = null;
            obj.inflater = null;
            obj.parent = null;
            obj.resid = 0;
            obj.view = null;
            boolean release = this.mRequestPool.release(obj);
        }

        public void enqueue(InflateRequest request) {
            Throwable th;
            try {
                this.mQueue.put(request);
            } catch (InterruptedException e) {
                InterruptedException e2 = e;
                Throwable th2 = th;
                new RuntimeException("Failed to enqueue async inflate request", e2);
                throw th2;
            }
        }
    }
}
