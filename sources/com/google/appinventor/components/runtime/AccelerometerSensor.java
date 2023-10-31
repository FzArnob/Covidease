package com.google.appinventor.components.runtime;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.Queue;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component that can detect shaking and measure acceleration approximately in three dimensions using SI units (m/s<sup>2</sup>).  The components are: <ul>\n<li> <strong>xAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when the phone is tilted to the right (i.e.,      its left side is raised), and negative when the phone is tilted      to the left (i.e., its right size is raised).</li>\n <li> <strong>yAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when its bottom is raised, and negative when      its top is raised. </li>\n <li> <strong>zAccel</strong>: Equal to -9.8 (earth's gravity in meters per      second per second when the device is at rest parallel to the ground      with the display facing up,      0 when perpendicular to the ground, and +9.8 when facing down.       The value can also be affected by accelerating it with or against      gravity. </li></ul>", helpUrl = "https://docs.kodular.io/components/sensors/accelerometer/", iconName = "images/accelerometersensor.png", nonVisible = true, version = 4)
public class AccelerometerSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, SensorComponent {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "AccelerometerSensor";
    private static final int SENSOR_CACHE_SIZE = 10;
    private static final double moderateShakeThreshold = 13.0d;
    private static final double strongShakeThreshold = 20.0d;
    private static final double weakShakeThreshold = 5.0d;
    private final Queue<Float> X_CACHE;
    private final Queue<Float> Y_CACHE;
    private final Queue<Float> Z_CACHE;
    private Sensor accelerometerSensor;
    private int accuracy;
    private final Handler androidUIHandler;
    /* access modifiers changed from: private */
    public volatile int deviceDefaultOrientation;
    private boolean enabled;
    private boolean legacyMode = false;
    private int minimumInterval;
    private final Resources resources;
    private int sensitivity;
    private final SensorManager sensorManager;
    private long timeLastShook;
    private final WindowManager windowManager;
    private float xAccel;
    private float yAccel;
    private float zAccel;

    static /* synthetic */ int access$002(AccelerometerSensor accelerometerSensor2, int i) {
        int i2 = i;
        int i3 = i2;
        accelerometerSensor2.deviceDefaultOrientation = i3;
        return i2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AccelerometerSensor(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.util.LinkedList r3 = new java.util.LinkedList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.X_CACHE = r3
            r2 = r0
            java.util.LinkedList r3 = new java.util.LinkedList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.Y_CACHE = r3
            r2 = r0
            java.util.LinkedList r3 = new java.util.LinkedList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.Z_CACHE = r3
            r2 = r0
            r3 = 0
            r2.legacyMode = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnPause(r3)
            r2 = r0
            r3 = 1
            r2.enabled = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            android.content.res.Resources r3 = r3.getResources()
            r2.resources = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            java.lang.String r4 = "window"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            r2.windowManager = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            java.lang.String r4 = "sensor"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.hardware.SensorManager r3 = (android.hardware.SensorManager) r3
            r2.sensorManager = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.hardware.SensorManager r3 = r3.sensorManager
            r4 = 1
            android.hardware.Sensor r3 = r3.getDefaultSensor(r4)
            r2.accelerometerSensor = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r2.startListening()
            r2 = r0
            r3 = 400(0x190, float:5.6E-43)
            r2.MinimumInterval(r3)
            r2 = r0
            r3 = 2
            r2.Sensitivity(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AccelerometerSensor.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The minimum interval, in milliseconds, between phone shakes")
    public int MinimumInterval() {
        return this.minimumInterval;
    }

    @DesignerProperty(defaultValue = "400", editorType = "non_negative_integer")
    @SimpleProperty
    public void MinimumInterval(int i) {
        int i2 = i;
        this.minimumInterval = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how sensitive the accelerometer is. The choices are: 1 = weak, 2 = moderate,  3 = strong.")
    public int Sensitivity() {
        return this.sensitivity;
    }

    @DesignerProperty(defaultValue = "2", editorType = "accelerometer_sensitivity")
    @SimpleProperty
    public void Sensitivity(int i) {
        int i2 = i;
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            this.sensitivity = i2;
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "Sensitivity", ErrorMessages.ERROR_BAD_VALUE_FOR_ACCELEROMETER_SENSITIVITY, Integer.valueOf(i2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0059, code lost:
        if (isShaking(r12.Z_CACHE, r3) != false) goto L_0x005b;
     */
    @com.google.appinventor.components.annotations.SimpleEvent
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void AccelerationChanged(float r14, float r15, float r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r6 = r0
            r7 = r1
            r6.xAccel = r7
            r6 = r0
            r7 = r2
            r6.yAccel = r7
            r6 = r0
            r7 = r3
            r6.zAccel = r7
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.X_CACHE
            r8 = r1
            r6.addToSensorCache(r7, r8)
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.Y_CACHE
            r8 = r2
            r6.addToSensorCache(r7, r8)
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.Z_CACHE
            r8 = r3
            r6.addToSensorCache(r7, r8)
            long r6 = java.lang.System.currentTimeMillis()
            r4 = r6
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.X_CACHE
            r8 = r1
            boolean r6 = r6.isShaking(r7, r8)
            if (r6 != 0) goto L_0x005b
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.Y_CACHE
            r8 = r2
            boolean r6 = r6.isShaking(r7, r8)
            if (r6 != 0) goto L_0x005b
            r6 = r0
            r12 = r6
            r6 = r12
            r7 = r12
            java.util.Queue<java.lang.Float> r7 = r7.Z_CACHE
            r8 = r3
            boolean r6 = r6.isShaking(r7, r8)
            if (r6 == 0) goto L_0x0079
        L_0x005b:
            r6 = r0
            long r6 = r6.timeLastShook
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x0071
            r6 = r4
            r8 = r0
            long r8 = r8.timeLastShook
            r10 = r0
            int r10 = r10.minimumInterval
            long r10 = (long) r10
            long r8 = r8 + r10
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 < 0) goto L_0x0079
        L_0x0071:
            r6 = r0
            r7 = r4
            r6.timeLastShook = r7
            r6 = r0
            r6.Shaking()
        L_0x0079:
            r6 = r0
            java.lang.String r7 = "AccelerationChanged"
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 0
            r11 = r1
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 1
            r11 = r2
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 2
            r11 = r3
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            r9[r10] = r11
            boolean r6 = com.google.appinventor.components.runtime.EventDispatcher.dispatchEvent(r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AccelerometerSensor.AccelerationChanged(float, float, float):void");
    }

    public int getDeviceDefaultOrientation() {
        StringBuilder sb;
        if (Build.VERSION.SDK_INT < 8) {
            return 1;
        }
        Configuration configuration = this.resources.getConfiguration();
        int rotation = this.windowManager.getDefaultDisplay().getRotation();
        int d = Log.d(LOG_TAG, "rotation = ".concat(String.valueOf(rotation)));
        new StringBuilder("config.orientation = ");
        int d2 = Log.d(LOG_TAG, sb.append(configuration.orientation).toString());
        if (((rotation == 0 || rotation == 2) && configuration.orientation == 2) || ((rotation == 1 || rotation == 3) && configuration.orientation == 1)) {
            return 2;
        }
        return 1;
    }

    @SimpleEvent
    public void Shaking() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Shaking", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Available() {
        return this.sensorManager.getSensorList(1).size() > 0;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    private void startListening() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ AccelerometerSensor hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                StringBuilder sb;
                int access$002 = AccelerometerSensor.access$002(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDeviceDefaultOrientation());
                new StringBuilder("deviceDefaultOrientation = ");
                int d = Log.d(AccelerometerSensor.LOG_TAG, sb.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.deviceDefaultOrientation).toString());
                int d2 = Log.d(AccelerometerSensor.LOG_TAG, "Configuration.ORIENTATION_LANDSCAPE = 2");
                int d3 = Log.d(AccelerometerSensor.LOG_TAG, "Configuration.ORIENTATION_PORTRAIT = 1");
            }
        };
        boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 32);
        boolean registerListener = this.sensorManager.registerListener(this, this.accelerometerSensor, 1);
    }

    private void stopListening() {
        this.sensorManager.unregisterListener(this);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        boolean z2 = z;
        if (this.enabled != z2) {
            this.enabled = z2;
            if (z2) {
                startListening();
            } else {
                stopListening();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float XAccel() {
        return this.xAccel;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float YAccel() {
        return this.yAccel;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float ZAccel() {
        return this.zAccel;
    }

    private void addToSensorCache(Queue<Float> queue, float f) {
        Queue<Float> queue2 = queue;
        float f2 = f;
        if (queue2.size() >= 10) {
            Float remove = queue2.remove();
        }
        boolean add = queue2.add(Float.valueOf(f2));
    }

    private boolean isShaking(Queue<Float> queue, float f) {
        Queue<Float> queue2 = queue;
        float f2 = f;
        float f3 = 0.0f;
        for (Float floatValue : queue2) {
            f3 += floatValue.floatValue();
        }
        float size = f3 / ((float) queue2.size());
        if (Sensitivity() == 1) {
            return ((double) Math.abs(size - f2)) > strongShakeThreshold;
        }
        if (Sensitivity() == 2) {
            if (((double) Math.abs(size - f2)) <= moderateShakeThreshold || ((double) Math.abs(size - f2)) >= strongShakeThreshold) {
                return false;
            }
            return true;
        } else if (((double) Math.abs(size - f2)) <= weakShakeThreshold || ((double) Math.abs(size - f2)) >= moderateShakeThreshold) {
            return false;
        } else {
            return true;
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Prior to the release that added this property the AccelerometerSensor component passed through sensor values directly as received from the Android system. However these values do not compensate for tablets that default to Landscape mode, requiring the MIT App Inventor programmer to compensate. However compensating would result in incorrect results in Portrait mode devices such as phones. We now detect Landscape mode tablets and perform the compensation. However if your project is already compensating for the change, you will now get incorrect results. Although our preferred solution is for you to update your project, you can also just set this property to “true” and our compensation code will be deactivated. Note: We recommend that you update your project as we may remove this property in a future release.", userVisible = false)
    public void LegacyMode(boolean z) {
        boolean z2 = z;
        this.legacyMode = z2;
    }

    public boolean LegacyMode() {
        return this.legacyMode;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        SensorEvent sensorEvent2 = sensorEvent;
        if (this.enabled) {
            float[] fArr = sensorEvent2.values;
            if (this.deviceDefaultOrientation != 2 || this.legacyMode) {
                this.xAccel = fArr[0];
                this.yAccel = fArr[1];
            } else {
                this.xAccel = fArr[1];
                this.yAccel = -fArr[0];
            }
            this.zAccel = fArr[2];
            this.accuracy = sensorEvent2.accuracy;
            AccelerationChanged(this.xAccel, this.yAccel, this.zAccel);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onResume() {
        if (this.enabled) {
            startListening();
        }
    }

    public void onPause() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }
}
