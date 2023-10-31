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
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides a high-level interface to a LEGO MINDSTORMS EV3 robot, with functions to draw graphs on EV3 screen.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3UI extends LegoMindstormsEv3Base {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Ev3UI(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3UI");
    }

    @SimpleFunction(description = "Draw a point on the screen.")
    public void DrawPoint(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        String str = "DrawPoint";
        if (i4 == 0 || i4 == 1) {
            Object[] objArr = new Object[4];
            objArr[0] = (byte) 2;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i4);
            Object[] objArr3 = objArr2;
            objArr3[2] = Short.valueOf((short) i5);
            Object[] objArr4 = objArr3;
            objArr4[3] = Short.valueOf((short) i6);
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "cccc", objArr4), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }

    @SimpleFunction(description = "Draw a built-in icon on screen.")
    public void DrawIcon(int i, int i2, int i3, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        String str = "DrawIcon";
        if (i6 == 0 || i6 == 1) {
            Object[] objArr = new Object[6];
            objArr[0] = (byte) 6;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i6);
            Object[] objArr3 = objArr2;
            objArr3[2] = Short.valueOf((short) i7);
            Object[] objArr4 = objArr3;
            objArr4[3] = Short.valueOf((short) i8);
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(i9);
            Object[] objArr6 = objArr5;
            objArr6[5] = Integer.valueOf(i10);
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "cccccc", objArr6), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }

    @SimpleFunction(description = "Draw a line on the screen.")
    public void DrawLine(int i, int i2, int i3, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        String str = "DrawLine";
        if (i6 == 0 || i6 == 1) {
            Object[] objArr = new Object[6];
            objArr[0] = (byte) 3;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i6);
            Object[] objArr3 = objArr2;
            objArr3[2] = Short.valueOf((short) i7);
            Object[] objArr4 = objArr3;
            objArr4[3] = Short.valueOf((short) i8);
            Object[] objArr5 = objArr4;
            objArr5[4] = Short.valueOf((short) i9);
            Object[] objArr6 = objArr5;
            objArr6[5] = Short.valueOf((short) i10);
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "cccccc", objArr6), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }

    @SimpleFunction(description = "Draw a rectangle on the screen.")
    public void DrawRect(int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        boolean z2 = z;
        String str = "DrawRect";
        if (i6 == 0 || i6 == 1) {
            Object[] objArr = new Object[6];
            Object[] objArr2 = objArr;
            objArr[0] = Byte.valueOf(z2 ? (byte) 9 : 10);
            Object[] objArr3 = objArr2;
            objArr3[1] = Byte.valueOf((byte) i6);
            Object[] objArr4 = objArr3;
            objArr4[2] = Short.valueOf((short) i7);
            Object[] objArr5 = objArr4;
            objArr5[3] = Short.valueOf((short) i8);
            Object[] objArr6 = objArr5;
            objArr6[4] = Short.valueOf((short) i9);
            Object[] objArr7 = objArr6;
            objArr7[5] = Short.valueOf((short) i10);
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "cccccc", objArr7), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }

    @SimpleFunction(description = "Draw a circle on the screen.")
    public void DrawCircle(int i, int i2, int i3, int i4, boolean z) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z2 = z;
        String str = "DrawCircle";
        if ((i5 == 0 || i5 == 1) && i8 >= 0) {
            Object[] objArr = new Object[5];
            Object[] objArr2 = objArr;
            objArr[0] = Byte.valueOf(z2 ? (byte) 24 : 4);
            Object[] objArr3 = objArr2;
            objArr3[1] = Byte.valueOf((byte) i5);
            Object[] objArr4 = objArr3;
            objArr4[2] = Short.valueOf((short) i6);
            Object[] objArr5 = objArr4;
            objArr5[3] = Short.valueOf((short) i7);
            Object[] objArr6 = objArr5;
            objArr6[4] = Short.valueOf((short) i8);
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "ccccc", objArr6), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }

    @SimpleFunction(description = "Fill the screen with a color.")
    public void FillScreen(int i) {
        int i2 = i;
        String str = "FillScreen";
        if (i2 == 0 || i2 == 1) {
            Object[] objArr = new Object[4];
            objArr[0] = (byte) 19;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i2);
            Object[] objArr3 = objArr2;
            objArr3[2] = (short) 0;
            Object[] objArr4 = objArr3;
            objArr4[3] = (short) 0;
            byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "cccc", objArr4), false);
            byte[] sendCommand2 = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.UI_DRAW, false, 0, 0, "c", (byte) 0), false);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
    }
}
