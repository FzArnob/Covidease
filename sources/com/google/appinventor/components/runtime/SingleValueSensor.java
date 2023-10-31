package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
public abstract class SingleValueSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, SensorComponent {
    private Sensor Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    protected boolean enabled = true;
    protected int refreshTime = 1000;
    protected final SensorManager sensorManager;
    protected int sensorType;
    protected float value;

    /* access modifiers changed from: protected */
    public abstract void onValueChanged(float f);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SingleValueSensor(com.google.appinventor.components.runtime.ComponentContainer r8, int r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r0
            r4 = r2
            r3.sensorType = r4
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnResume(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            r4 = r0
            r3.registerForOnPause(r4)
            r3 = r0
            r4 = 1000(0x3e8, float:1.401E-42)
            r3.refreshTime = r4
            r3 = r0
            r4 = 1
            r3.enabled = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            java.lang.String r5 = "sensor"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.hardware.SensorManager r4 = (android.hardware.SensorManager) r4
            r3.sensorManager = r4
            r3 = r0
            r6 = r3
            r3 = r6
            r4 = r6
            android.hardware.SensorManager r4 = r4.sensorManager
            r5 = r2
            android.hardware.Sensor r4 = r4.getDefaultSensor(r5)
            r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r4
            r3 = r0
            r3.startListening()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SingleValueSensor.<init>(com.google.appinventor.components.runtime.ComponentContainer, int):void");
    }

    /* access modifiers changed from: protected */
    public void startListening() {
        if (Build.VERSION.SDK_INT >= 9) {
            boolean registerListener = this.sensorManager.registerListener(this, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB, this.refreshTime * 1000);
            return;
        }
        boolean registerListener2 = this.sensorManager.registerListener(this, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB, 2);
    }

    /* access modifiers changed from: protected */
    public void stopListening() {
        this.sensorManager.unregisterListener(this);
    }

    @SimpleProperty(description = "Specifies whether or not the device has an ambient air pressure sensor.")
    public boolean Available() {
        return isAvailable();
    }

    @SimpleProperty(description = "If enabled, then device will listen for changes.")
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        setEnabled(z);
    }

    @SimpleProperty(description = "The requested minimum time in milliseconds between changes in air pressure being reported. Android is not guaranteed to honor the request. Setting this property has no effect on pre-Gingerbread devices.")
    public int RefreshTime() {
        return this.refreshTime;
    }

    @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
    @SimpleProperty
    public void RefreshTime(int i) {
        this.refreshTime = i;
        if (this.enabled) {
            stopListening();
            startListening();
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        SensorEvent sensorEvent2 = sensorEvent;
        if (this.enabled && sensorEvent2.sensor.getType() == this.sensorType) {
            this.value = sensorEvent2.values[0];
            onValueChanged(this.value);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return this.sensorManager.getSensorList(this.sensorType).size() > 0;
    }

    /* access modifiers changed from: protected */
    public void setEnabled(boolean z) {
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

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onPause() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onResume() {
        if (this.enabled) {
            startListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }

    /* access modifiers changed from: protected */
    public float getValue() {
        return this.value;
    }
}
