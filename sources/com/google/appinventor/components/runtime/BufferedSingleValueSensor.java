package com.google.appinventor.components.runtime;

import android.hardware.SensorEvent;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class BufferedSingleValueSensor extends SingleValueSensor {
    private C0603a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BufferedSingleValueSensor(ComponentContainer componentContainer, int i, int i2) {
        super(componentContainer.$form(), i);
        C0603a aVar;
        new C0603a(this, i2, (byte) 0);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        SensorEvent sensorEvent2 = sensorEvent;
        if (this.enabled && sensorEvent2.sensor.getType() == this.sensorType) {
            float[] fArr = sensorEvent2.values;
            C0603a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            Float valueOf = Float.valueOf(fArr[0]);
            C0603a aVar2 = aVar;
            C0603a aVar3 = aVar2;
            Float[] fArr2 = aVar2.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            C0603a aVar4 = aVar3;
            int i = aVar4.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
            int i2 = i + 1;
            aVar4.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = i2;
            fArr2[i] = valueOf;
            if (aVar3.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO == aVar3.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.length) {
                aVar3.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 0;
            }
            super.onSensorChanged(sensorEvent2);
        }
    }

    /* access modifiers changed from: protected */
    public float getAverageValue() {
        double d;
        C0603a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        double d2 = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < aVar.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.length; i2++) {
            if (aVar.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2] != null) {
                d2 += (double) aVar.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2].floatValue();
                i++;
            }
        }
        if (i == 0) {
            d = d2;
        } else {
            d = d2 / ((double) i);
        }
        return (float) d;
    }

    /* renamed from: com.google.appinventor.components.runtime.BufferedSingleValueSensor$a */
    class C0603a {
        int hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
        private /* synthetic */ BufferedSingleValueSensor hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        Float[] f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0603a(BufferedSingleValueSensor bufferedSingleValueSensor, int i, byte b) {
            this(bufferedSingleValueSensor, i);
            byte b2 = b;
        }

        private C0603a(BufferedSingleValueSensor bufferedSingleValueSensor, int i) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bufferedSingleValueSensor;
            this.f344hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Float[i];
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 0;
        }
    }
}
