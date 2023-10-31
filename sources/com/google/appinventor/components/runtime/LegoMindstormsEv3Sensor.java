package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@SimpleObject
public class LegoMindstormsEv3Sensor extends LegoMindstormsEv3Base {
    protected static final String DEFAULT_SENSOR_PORT = "1";
    protected int sensorPortNumber;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LegoMindstormsEv3Sensor(ComponentContainer componentContainer, String str) {
        super(componentContainer, str);
        SensorPort(DEFAULT_SENSOR_PORT);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor port that the sensor is connected to.", userVisible = false)
    public String SensorPort() {
        return portNumberToSensorPortLetter(this.sensorPortNumber);
    }

    @DesignerProperty(defaultValue = "1", editorType = "lego_ev3_sensor_port")
    @SimpleProperty
    public void SensorPort(String str) {
        setSensorPort("SensorPort", str);
    }

    /* access modifiers changed from: protected */
    public final void setSensorPort(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        try {
            this.sensorPortNumber = sensorPortLetterToPortNumber(str4);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str3, ErrorMessages.ERROR_EV3_ILLEGAL_SENSOR_PORT, str4);
        }
    }

    /* access modifiers changed from: protected */
    public final int readInputPercentage(String str, int i, int i2, int i3, int i4) {
        Throwable th;
        String str2 = str;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (i5 < 0 || i5 > 3 || i6 < 0 || i6 > 3 || i8 < -1 || i8 > 7) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[7];
        objArr[0] = (byte) 27;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i5);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) i6);
        Object[] objArr4 = objArr3;
        objArr4[3] = Byte.valueOf((byte) i7);
        Object[] objArr5 = objArr4;
        objArr5[4] = Byte.valueOf((byte) i8);
        Object[] objArr6 = objArr5;
        objArr6[5] = (byte) 1;
        Object[] objArr7 = objArr6;
        objArr7[6] = (byte) 0;
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand((byte) -103, true, 1, 0, "ccccccg", objArr7), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr.length == 2 && bArr[0] == 2) {
            return bArr[1];
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public final double readInputSI(String str, int i, int i2, int i3, int i4) {
        Throwable th;
        String str2 = str;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (i5 < 0 || i5 > 3 || i6 < 0 || i6 > 3 || i8 < -1 || i8 > 7) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[7];
        objArr[0] = (byte) 29;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i5);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) i6);
        Object[] objArr4 = objArr3;
        objArr4[3] = Byte.valueOf((byte) i7);
        Object[] objArr5 = objArr4;
        objArr5[4] = Byte.valueOf((byte) i8);
        Object[] objArr6 = objArr5;
        objArr6[5] = (byte) 1;
        Object[] objArr7 = objArr6;
        objArr7[6] = (byte) 0;
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand((byte) -103, true, 4, 0, "ccccccg", objArr7), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr.length == 5 && bArr[0] == 2) {
            return (double) ((Float) Ev3BinaryParser.unpack("xf", bArr)[0]).floatValue();
        }
        this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return -1.0d;
    }
}
