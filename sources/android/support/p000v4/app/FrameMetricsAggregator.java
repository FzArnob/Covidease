package android.support.p000v4.app;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: android.support.v4.app.FrameMetricsAggregator */
public class FrameMetricsAggregator {
    public static final int ANIMATION_DURATION = 256;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 32;
    public static final int COMMAND_INDEX = 5;
    private static final boolean DBG = false;
    public static final int DELAY_DURATION = 128;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 511;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    private static final int LAST_INDEX = 8;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 64;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    private static final String TAG = "FrameMetrics";
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX = 0;
    private FrameMetricsBaseImpl mInstance;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.app.FrameMetricsAggregator$MetricType */
    public @interface MetricType {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i) {
        FrameMetricsBaseImpl frameMetricsBaseImpl;
        FrameMetricsBaseImpl frameMetricsBaseImpl2;
        int metricTypeFlags = i;
        if (Build.VERSION.SDK_INT >= 24) {
            new FrameMetricsApi24Impl(metricTypeFlags);
            this.mInstance = frameMetricsBaseImpl2;
            return;
        }
        new FrameMetricsBaseImpl();
        this.mInstance = frameMetricsBaseImpl;
    }

    public void add(@NonNull Activity activity) {
        this.mInstance.add(activity);
    }

    @Nullable
    public SparseIntArray[] remove(@NonNull Activity activity) {
        return this.mInstance.remove(activity);
    }

    @Nullable
    public SparseIntArray[] stop() {
        return this.mInstance.stop();
    }

    @Nullable
    public SparseIntArray[] reset() {
        return this.mInstance.reset();
    }

    @Nullable
    public SparseIntArray[] getMetrics() {
        return this.mInstance.getMetrics();
    }

    /* renamed from: android.support.v4.app.FrameMetricsAggregator$FrameMetricsBaseImpl */
    private static class FrameMetricsBaseImpl {
        FrameMetricsBaseImpl() {
        }

        public void add(Activity activity) {
        }

        public SparseIntArray[] remove(Activity activity) {
            Activity activity2 = activity;
            return null;
        }

        public SparseIntArray[] stop() {
            return null;
        }

        public SparseIntArray[] getMetrics() {
            return null;
        }

        public SparseIntArray[] reset() {
            return null;
        }
    }

    @RequiresApi(24)
    /* renamed from: android.support.v4.app.FrameMetricsAggregator$FrameMetricsApi24Impl */
    private static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {
        private static final int NANOS_PER_MS = 1000000;
        private static final int NANOS_ROUNDING_VALUE = 500000;
        private static Handler sHandler = null;
        private static HandlerThread sHandlerThread = null;
        private ArrayList<WeakReference<Activity>> mActivities;
        Window.OnFrameMetricsAvailableListener mListener;
        SparseIntArray[] mMetrics = new SparseIntArray[9];
        int mTrackingFlags;

        FrameMetricsApi24Impl(int trackingFlags) {
            ArrayList<WeakReference<Activity>> arrayList;
            Window.OnFrameMetricsAvailableListener onFrameMetricsAvailableListener;
            new ArrayList<>();
            this.mActivities = arrayList;
            new Window.OnFrameMetricsAvailableListener(this) {
                final /* synthetic */ FrameMetricsApi24Impl this$0;

                {
                    this.this$0 = this$0;
                }

                public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
                    Window window2 = window;
                    FrameMetrics frameMetrics2 = frameMetrics;
                    int i2 = i;
                    if ((this.this$0.mTrackingFlags & 1) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[0], frameMetrics2.getMetric(8));
                    }
                    if ((this.this$0.mTrackingFlags & 2) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[1], frameMetrics2.getMetric(1));
                    }
                    if ((this.this$0.mTrackingFlags & 4) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[2], frameMetrics2.getMetric(3));
                    }
                    if ((this.this$0.mTrackingFlags & 8) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[3], frameMetrics2.getMetric(4));
                    }
                    if ((this.this$0.mTrackingFlags & 16) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[4], frameMetrics2.getMetric(5));
                    }
                    if ((this.this$0.mTrackingFlags & 64) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[6], frameMetrics2.getMetric(7));
                    }
                    if ((this.this$0.mTrackingFlags & 32) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[5], frameMetrics2.getMetric(6));
                    }
                    if ((this.this$0.mTrackingFlags & 128) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[7], frameMetrics2.getMetric(0));
                    }
                    if ((this.this$0.mTrackingFlags & 256) != 0) {
                        this.this$0.addDurationItem(this.this$0.mMetrics[8], frameMetrics2.getMetric(2));
                    }
                }
            };
            this.mListener = onFrameMetricsAvailableListener;
            this.mTrackingFlags = trackingFlags;
        }

        /* access modifiers changed from: package-private */
        public void addDurationItem(SparseIntArray sparseIntArray, long j) {
            SparseIntArray buckets = sparseIntArray;
            long duration = j;
            if (buckets != null) {
                int durationMs = (int) ((duration + 500000) / 1000000);
                if (duration >= 0) {
                    buckets.put(durationMs, buckets.get(durationMs) + 1);
                }
            }
        }

        public void add(Activity activity) {
            Object obj;
            SparseIntArray sparseIntArray;
            HandlerThread handlerThread;
            Handler handler;
            Activity activity2 = activity;
            if (sHandlerThread == null) {
                new HandlerThread("FrameMetricsAggregator");
                sHandlerThread = handlerThread;
                sHandlerThread.start();
                new Handler(sHandlerThread.getLooper());
                sHandler = handler;
            }
            for (int i = 0; i <= 8; i++) {
                if (this.mMetrics[i] == null && (this.mTrackingFlags & (1 << i)) != 0) {
                    new SparseIntArray();
                    this.mMetrics[i] = sparseIntArray;
                }
            }
            activity2.getWindow().addOnFrameMetricsAvailableListener(this.mListener, sHandler);
            new WeakReference(activity2);
            boolean add = this.mActivities.add(obj);
        }

        public SparseIntArray[] remove(Activity activity) {
            Activity activity2 = activity;
            Iterator<WeakReference<Activity>> it = this.mActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WeakReference<Activity> activityRef = it.next();
                if (activityRef.get() == activity2) {
                    boolean remove = this.mActivities.remove(activityRef);
                    break;
                }
            }
            activity2.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
            return this.mMetrics;
        }

        public SparseIntArray[] stop() {
            for (int i = this.mActivities.size() - 1; i >= 0; i--) {
                WeakReference<Activity> ref = this.mActivities.get(i);
                Activity activity = (Activity) ref.get();
                if (ref.get() != null) {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
                    WeakReference<Activity> remove = this.mActivities.remove(i);
                }
            }
            return this.mMetrics;
        }

        public SparseIntArray[] getMetrics() {
            return this.mMetrics;
        }

        public SparseIntArray[] reset() {
            SparseIntArray[] returnVal = this.mMetrics;
            this.mMetrics = new SparseIntArray[9];
            return returnVal;
        }
    }
}
