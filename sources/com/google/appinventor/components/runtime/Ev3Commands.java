package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.Ev3Constants;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides a low-level interface to a LEGO MINDSTORMS EV3 robot, with functions to send system or direct commands to EV3 robots.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3Commands extends LegoMindstormsEv3Base {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Ev3Commands(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3Commands");
    }

    @SimpleFunction(description = "Keep the EV3 brick from shutdown for a period of time.")
    public void KeepAlive(int i) {
        int i2 = i;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (i2 < 0 || i2 > 255) {
            this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, methodName);
            return;
        }
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.KEEP_ALIVE, false, 0, 0, "c", Byte.valueOf((byte) i2)), false);
    }

    @SimpleFunction(description = "Get the battery voltage.")
    public double GetBatteryVoltage() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[2];
        objArr[0] = (byte) 1;
        Object[] objArr2 = objArr;
        objArr2[1] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 4, 0, "cg", objArr2), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr.length == 5 && bArr[0] == 2) {
            return (double) ((Float) Ev3BinaryParser.unpack("xf", bArr)[0]).floatValue();
        }
        return -1.0d;
    }

    @SimpleFunction(description = "Get the battery current.")
    public double GetBatteryCurrent() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[2];
        objArr[0] = (byte) 2;
        Object[] objArr2 = objArr;
        objArr2[1] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 4, 0, "cg", objArr2), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr.length == 5 && bArr[0] == 2) {
            return (double) ((Float) Ev3BinaryParser.unpack("xf", bArr)[0]).floatValue();
        }
        return -1.0d;
    }

    @SimpleFunction(description = "Get the OS version on EV3.")
    public String GetOSVersion() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 3;
        Object[] objArr2 = objArr;
        objArr2[1] = (short) 100;
        Object[] objArr3 = objArr2;
        objArr3[2] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 100, 0, "ccg", objArr3), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr[0] == 2) {
            return String.valueOf(Ev3BinaryParser.unpack("xS", bArr)[0]);
        }
        this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }

    @SimpleFunction(description = "Get the OS build on EV3.")
    public String GetOSBuild() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 12;
        Object[] objArr2 = objArr;
        objArr2[1] = (short) 100;
        Object[] objArr3 = objArr2;
        objArr3[2] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) 3, true, 100, 0, "ccg", objArr3), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr[0] == 2) {
            return String.valueOf(Ev3BinaryParser.unpack("xS", bArr)[0]);
        }
        this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }

    @SimpleFunction(description = "Get the firmware version on EV3.")
    public String GetFirmwareVersion() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 10;
        Object[] objArr2 = objArr;
        objArr2[1] = (short) 100;
        Object[] objArr3 = objArr2;
        objArr3[2] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 100, 0, "ccg", objArr3), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr[0] == 2) {
            return String.valueOf(Ev3BinaryParser.unpack("xS", bArr)[0]);
        }
        this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }

    @SimpleFunction(description = "Get the firmware build on EV3.")
    public String GetFirmwareBuild() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[2];
        objArr[0] = Byte.valueOf(Ev3Constants.Opcode.MEMORY_READ);
        Object[] objArr2 = objArr;
        objArr2[1] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 100, 0, "cg", objArr2), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr[0] == 2) {
            return String.valueOf(Ev3BinaryParser.unpack("xS", bArr)[0]);
        }
        this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }

    @SimpleFunction(description = "Get the hardware version of EV3.")
    public String GetHardwareVersion() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 9;
        Object[] objArr2 = objArr;
        objArr2[1] = (short) 100;
        Object[] objArr3 = objArr2;
        objArr3[2] = (byte) 0;
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -127, true, 100, 0, "ccg", objArr3), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr[0] == 2) {
            return String.valueOf(Ev3BinaryParser.unpack("xS", bArr)[0]);
        }
        this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }
}
