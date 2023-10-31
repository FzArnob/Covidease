package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides a high-level interface to sound functionalities on LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3Sound extends LegoMindstormsEv3Base {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Ev3Sound(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3Sound");
    }

    @SimpleFunction(description = "Make the robot play a tone.")
    public void PlayTone(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (i4 < 0 || i4 > 100 || i5 < 250 || i5 > 10000 || i6 < 0 || i6 > 65535) {
            this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, methodName);
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = (byte) 1;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i4);
        Object[] objArr3 = objArr2;
        objArr3[2] = Short.valueOf((short) i5);
        Object[] objArr4 = objArr3;
        objArr4[3] = Short.valueOf((short) i6);
        byte[] sendCommand = sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -108, true, 0, 0, "cccc", objArr4), true);
    }

    @SimpleFunction(description = "Stop any sound on the robot.")
    public void StopSound() {
        byte[] sendCommand = sendCommand(Thread.currentThread().getStackTrace()[1].getMethodName(), Ev3BinaryParser.encodeDirectCommand((byte) -108, false, 0, 0, "c", (byte) 0), false);
    }
}
