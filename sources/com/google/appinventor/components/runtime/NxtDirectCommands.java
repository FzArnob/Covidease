package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NXT, description = "A component that provides a low-level interface to a LEGO MINDSTORMS NXT robot, with functions to send NXT Direct Commands.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class NxtDirectCommands extends LegoMindstormsNxtBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NxtDirectCommands(ComponentContainer componentContainer) {
        super(componentContainer, "NxtDirectCommands");
    }

    @SimpleFunction(description = "Start execution of a previously downloaded program on the robot.")
    public void StartProgram(String str) {
        StringBuilder sb;
        String str2 = str;
        String str3 = "StartProgram";
        if (checkBluetooth(str3)) {
            if (str2.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, str3, 405, new Object[0]);
                return;
            }
            if (str2.indexOf(".") == -1) {
                new StringBuilder();
                str2 = sb.append(str2).append(".rxe").toString();
            }
            byte[] bArr = new byte[22];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 0;
            copyStringValueToBytes(str2, bArr2, 2, 19);
            sendCommand(str3, bArr2);
        }
    }

    @SimpleFunction(description = "Stop execution of the currently running program on the robot.")
    public void StopProgram() {
        String str = "StopProgram";
        if (checkBluetooth(str)) {
            byte[] bArr = new byte[2];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 1;
            sendCommand(str, bArr2);
        }
    }

    @SimpleFunction(description = "Play a sound file on the robot.")
    public void PlaySoundFile(String str) {
        StringBuilder sb;
        String str2 = str;
        String str3 = "PlaySoundFile";
        if (checkBluetooth(str3)) {
            if (str2.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, str3, 406, new Object[0]);
                return;
            }
            if (str2.indexOf(".") == -1) {
                new StringBuilder();
                str2 = sb.append(str2).append(".rso").toString();
            }
            byte[] bArr = new byte[23];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 2;
            copyBooleanValueToBytes(false, bArr2, 2);
            copyStringValueToBytes(str2, bArr2, 3, 19);
            sendCommand(str3, bArr2);
        }
    }

    @SimpleFunction(description = "Make the robot play a tone.")
    public void PlayTone(int i, int i2) {
        StringBuilder sb;
        StringBuilder sb2;
        int i3 = i;
        int i4 = i2;
        String str = "PlayTone";
        if (checkBluetooth(str)) {
            if (i3 < 200) {
                String str2 = this.logTag;
                new StringBuilder("frequencyHz ");
                int w = Log.w(str2, sb2.append(i3).append(" is invalid, using 200.").toString());
                i3 = 200;
            }
            if (i3 > 14000) {
                String str3 = this.logTag;
                new StringBuilder("frequencyHz ");
                int w2 = Log.w(str3, sb.append(i3).append(" is invalid, using 14000.").toString());
                i3 = 14000;
            }
            byte[] bArr = new byte[6];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 3;
            copyUWORDValueToBytes(i3, bArr2, 2);
            copyUWORDValueToBytes(i4, bArr2, 4);
            sendCommand(str, bArr2);
        }
    }

    @SimpleFunction(description = "Sets the output state of a motor on the robot.")
    public void SetOutputState(String str, int i, int i2, int i3, int i4, int i5, long j) {
        String str2 = str;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        long j2 = j;
        String str3 = "SetOutputState";
        if (checkBluetooth(str3)) {
            try {
                setOutputState(str3, convertMotorPortLetterToNumber(str2), i6, i7, i8, sanitizeTurnRatio(i9), i10, j2);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, str3, 407, str2);
            }
        }
    }

    @SimpleFunction(description = "Configure an input sensor on the robot.")
    public void SetInputMode(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        String str3 = "SetInputMode";
        if (checkBluetooth(str3)) {
            try {
                setInputMode(str3, convertSensorPortLetterToNumber(str2), i3, i4);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x014d  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Reads the output state of a motor on the robot.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Number> GetOutputState(java.lang.String r15) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            java.lang.String r5 = "GetOutputState"
            r2 = r5
            r5 = r0
            r6 = r2
            boolean r5 = r5.checkBluetooth(r6)
            if (r5 != 0) goto L_0x0018
            java.util.ArrayList r5 = new java.util.ArrayList
            r13 = r5
            r5 = r13
            r6 = r13
            r6.<init>()
            r0 = r5
        L_0x0017:
            return r0
        L_0x0018:
            r5 = r0
            r6 = r1
            int r5 = r5.convertMotorPortLetterToNumber((java.lang.String) r6)     // Catch:{ IllegalArgumentException -> 0x00fe }
            r3 = r5
            r5 = r0
            r1 = r5
            r5 = 3
            byte[] r5 = new byte[r5]
            r13 = r5
            r5 = r13
            r6 = r13
            r4 = r6
            r6 = 0
            r7 = 0
            r5[r6] = r7
            r5 = r4
            r6 = 1
            r7 = 6
            r5[r6] = r7
            r5 = r1
            r6 = r3
            r7 = r4
            r8 = 2
            r5.copyUBYTEValueToBytes(r6, r7, r8)
            r5 = r1
            r6 = r2
            r7 = r4
            byte[] r5 = r5.sendCommandAndReceiveReturnPackage(r6, r7)
            r3 = r5
            r5 = r1
            r6 = r2
            r7 = r3
            r8 = r4
            r9 = 1
            byte r8 = r8[r9]
            boolean r5 = r5.evaluateStatus(r6, r7, r8)
            if (r5 == 0) goto L_0x014a
            r5 = r3
            int r5 = r5.length
            r6 = 25
            if (r5 != r6) goto L_0x011e
            r5 = r3
        L_0x0054:
            r13 = r5
            r5 = r13
            r6 = r13
            r1 = r6
            if (r5 == 0) goto L_0x014d
            java.util.ArrayList r5 = new java.util.ArrayList
            r13 = r5
            r5 = r13
            r6 = r13
            r6.<init>()
            r13 = r5
            r5 = r13
            r6 = r13
            r2 = r6
            r6 = r0
            r7 = r1
            r8 = 4
            int r6 = r6.getSBYTEValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 5
            int r6 = r6.getUBYTEValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 6
            int r6 = r6.getUBYTEValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 7
            int r6 = r6.getSBYTEValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 8
            int r6 = r6.getUBYTEValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 9
            long r6 = r6.getULONGValueFromBytes(r7, r8)
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 13
            int r6 = r6.getSLONGValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 17
            int r6 = r6.getSLONGValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r6 = r0
            r7 = r1
            r8 = 21
            int r6 = r6.getSLONGValueFromBytes(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = r2
            r0 = r5
            goto L_0x0017
        L_0x00fe:
            r5 = move-exception
            r5 = r0
            com.google.appinventor.components.runtime.Form r5 = r5.form
            r6 = r0
            r7 = r2
            r8 = 407(0x197, float:5.7E-43)
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = 0
            r12 = r1
            r10[r11] = r12
            r5.dispatchErrorOccurredEvent(r6, r7, r8, r9)
            java.util.ArrayList r5 = new java.util.ArrayList
            r13 = r5
            r5 = r13
            r6 = r13
            r6.<init>()
            r0 = r5
            goto L_0x0017
        L_0x011e:
            r5 = r1
            java.lang.String r5 = r5.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r13 = r6
            r6 = r13
            r7 = r13
            r7.<init>()
            r7 = r2
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = ": unexpected return package length "
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r3
            int r7 = r7.length
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " (expected 25)"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            int r5 = android.util.Log.w(r5, r6)
        L_0x014a:
            r5 = 0
            goto L_0x0054
        L_0x014d:
            java.util.ArrayList r5 = new java.util.ArrayList
            r13 = r5
            r5 = r13
            r6 = r13
            r6.<init>()
            r0 = r5
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NxtDirectCommands.GetOutputState(java.lang.String):java.util.List");
    }

    @SimpleFunction(description = "Reads the values of an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public List<Object> GetInputValues(String str) {
        List<Object> list;
        List<Object> list2;
        List<Object> list3;
        List<Object> list4;
        String str2 = str;
        String str3 = "GetInputValues";
        if (!checkBluetooth(str3)) {
            new ArrayList();
            return list4;
        }
        try {
            byte[] inputValues = getInputValues(str3, convertSensorPortLetterToNumber(str2));
            byte[] bArr = inputValues;
            if (inputValues != null) {
                new ArrayList();
                List<Object> list5 = list3;
                List<Object> list6 = list5;
                boolean add = list5.add(Boolean.valueOf(getBooleanValueFromBytes(bArr, 4)));
                boolean add2 = list6.add(Boolean.valueOf(getBooleanValueFromBytes(bArr, 5)));
                boolean add3 = list6.add(Integer.valueOf(getUBYTEValueFromBytes(bArr, 6)));
                boolean add4 = list6.add(Integer.valueOf(getUBYTEValueFromBytes(bArr, 7)));
                boolean add5 = list6.add(Integer.valueOf(getUWORDValueFromBytes(bArr, 8)));
                boolean add6 = list6.add(Integer.valueOf(getUWORDValueFromBytes(bArr, 10)));
                boolean add7 = list6.add(Integer.valueOf(getSWORDValueFromBytes(bArr, 12)));
                boolean add8 = list6.add(Integer.valueOf(getSWORDValueFromBytes(bArr, 14)));
                return list6;
            }
            new ArrayList();
            return list2;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            new ArrayList();
            return list;
        }
    }

    @SimpleFunction(description = "Reset the scaled value of an input sensor on the robot.")
    public void ResetInputScaledValue(String str) {
        String str2 = str;
        String str3 = "ResetInputScaledValue";
        if (checkBluetooth(str3)) {
            try {
                int convertSensorPortLetterToNumber = convertSensorPortLetterToNumber(str2);
                resetInputScaledValue(str3, convertSensorPortLetterToNumber);
                byte[] bArr = new byte[3];
                byte[] bArr2 = bArr;
                bArr[0] = Byte.MIN_VALUE;
                bArr2[1] = 8;
                copyUBYTEValueToBytes(convertSensorPortLetterToNumber, bArr2, 2);
                sendCommand(str3, bArr2);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            }
        }
    }

    @SimpleFunction(description = "Write a message to a mailbox (1-10) on the robot.")
    public void MessageWrite(int i, String str) {
        int i2 = i;
        String str2 = str;
        String str3 = "MessageWrite";
        if (checkBluetooth(str3)) {
            if (i2 <= 0 || i2 > 10) {
                this.form.dispatchErrorOccurredEvent(this, str3, 409, Integer.valueOf(i2));
                return;
            }
            int length = str2.length();
            int i3 = length;
            if (length > 58) {
                this.form.dispatchErrorOccurredEvent(this, str3, 410, new Object[0]);
                return;
            }
            byte[] bArr = new byte[(i3 + 4 + 1)];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 9;
            copyUBYTEValueToBytes(i2 - 1, bArr2, 2);
            copyUBYTEValueToBytes(i3 + 1, bArr2, 3);
            copyStringValueToBytes(str2, bArr2, 4, i3);
            sendCommand(str3, bArr2);
        }
    }

    @SimpleFunction(description = "Reset motor position.")
    public void ResetMotorPosition(String str, boolean z) {
        String str2 = str;
        boolean z2 = z;
        String str3 = "ResetMotorPosition";
        if (checkBluetooth(str3)) {
            try {
                int convertMotorPortLetterToNumber = convertMotorPortLetterToNumber(str2);
                byte[] bArr = new byte[4];
                byte[] bArr2 = bArr;
                bArr[0] = Byte.MIN_VALUE;
                bArr2[1] = 10;
                copyUBYTEValueToBytes(convertMotorPortLetterToNumber, bArr2, 2);
                copyBooleanValueToBytes(z2, bArr2, 3);
                sendCommand(str3, bArr2);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, str3, 407, str2);
            }
        }
    }

    @SimpleFunction(description = "Get the battery level for the robot. Returns the voltage in millivolts.")
    public int GetBatteryLevel() {
        StringBuilder sb;
        String str = "GetBatteryLevel";
        if (!checkBluetooth(str)) {
            return 0;
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 11;
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 5) {
                return getUWORDValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str2 = this.logTag;
            new StringBuilder("GetBatteryLevel: unexpected return package length ");
            int w = Log.w(str2, sb.append(sendCommandAndReceiveReturnPackage.length).append(" (expected 5)").toString());
        }
        return 0;
    }

    @SimpleFunction(description = "Stop sound playback.")
    public void StopSoundPlayback() {
        String str = "StopSoundPlayback";
        if (checkBluetooth(str)) {
            byte[] bArr = new byte[2];
            byte[] bArr2 = bArr;
            bArr[0] = Byte.MIN_VALUE;
            bArr2[1] = 12;
            sendCommand(str, bArr2);
        }
    }

    @SimpleFunction(description = "Keep Alive. Returns the current sleep time limit in milliseconds.")
    public long KeepAlive() {
        StringBuilder sb;
        String str = "KeepAlive";
        if (!checkBluetooth(str)) {
            return 0;
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 13;
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 7) {
                return getULONGValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str2 = this.logTag;
            new StringBuilder("KeepAlive: unexpected return package length ");
            int w = Log.w(str2, sb.append(sendCommandAndReceiveReturnPackage.length).append(" (expected 7)").toString());
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the count of available bytes to read.")
    public int LsGetStatus(String str) {
        String str2 = str;
        String str3 = "LsGetStatus";
        if (!checkBluetooth(str3)) {
            return 0;
        }
        try {
            return lsGetStatus(str3, convertSensorPortLetterToNumber(str2));
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            return 0;
        }
    }

    @SimpleFunction(description = "Writes low speed data to an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public void LsWrite(String str, YailList yailList, int i) {
        String str2 = str;
        YailList yailList2 = yailList;
        int i2 = i;
        String str3 = "LsWrite";
        if (checkBluetooth(str3)) {
            try {
                int convertSensorPortLetterToNumber = convertSensorPortLetterToNumber(str2);
                if (yailList2.size() > 16) {
                    this.form.dispatchErrorOccurredEvent(this, str3, 411, new Object[0]);
                    return;
                }
                Object[] array = yailList2.toArray();
                Object[] objArr = array;
                byte[] bArr = new byte[array.length];
                int i3 = 0;
                while (i3 < objArr.length) {
                    try {
                        int intValue = Integer.decode(objArr[i3].toString()).intValue();
                        bArr[i3] = (byte) intValue;
                        int i4 = intValue >> 8;
                        int i5 = i4;
                        if (i4 == 0 || i5 == -1) {
                            i3++;
                        } else {
                            this.form.dispatchErrorOccurredEvent(this, str3, 413, Integer.valueOf(i3 + 1));
                            return;
                        }
                    } catch (NumberFormatException e) {
                        this.form.dispatchErrorOccurredEvent(this, str3, 412, Integer.valueOf(i3 + 1));
                        return;
                    }
                }
                lsWrite(str3, convertSensorPortLetterToNumber, bArr, i2);
            } catch (IllegalArgumentException e2) {
                this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            }
        }
    }

    @SimpleFunction(description = "Reads unsigned low speed data from an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public List<Integer> LsRead(String str) {
        List<Integer> list;
        List<Integer> list2;
        List<Integer> list3;
        List<Integer> list4;
        String str2 = str;
        String str3 = "LsRead";
        if (!checkBluetooth(str3)) {
            new ArrayList();
            return list4;
        }
        try {
            byte[] lsRead = lsRead(str3, convertSensorPortLetterToNumber(str2));
            byte[] bArr = lsRead;
            if (lsRead != null) {
                new ArrayList();
                List<Integer> list5 = list3;
                int uBYTEValueFromBytes = getUBYTEValueFromBytes(bArr, 3);
                for (int i = 0; i < uBYTEValueFromBytes; i++) {
                    boolean add = list5.add(Integer.valueOf(bArr[i + 4] & Ev3Constants.Opcode.TST));
                }
                return list5;
            }
            new ArrayList();
            return list2;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str3, 408, str2);
            new ArrayList();
            return list;
        }
    }

    @SimpleFunction(description = "Get the name of currently running program on the robot.")
    public String GetCurrentProgramName() {
        String str = "GetCurrentProgramName";
        if (!checkBluetooth(str)) {
            return "";
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 17;
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        int status = getStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1]);
        int i = status;
        if (status == 0) {
            return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
        }
        if (i == 236) {
            return "";
        }
        boolean evaluateStatus = evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1]);
        return "";
    }

    @SimpleFunction(description = "Read a message from a mailbox (1-10) on the robot.")
    public String MessageRead(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int i2 = i;
        String str = "MessageRead";
        if (!checkBluetooth(str)) {
            return "";
        }
        if (i2 <= 0 || i2 > 10) {
            this.form.dispatchErrorOccurredEvent(this, str, 409, Integer.valueOf(i2));
            return "";
        }
        int i3 = i2 - 1;
        byte[] bArr = new byte[5];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 19;
        copyUBYTEValueToBytes(0, bArr2, 2);
        copyUBYTEValueToBytes(i3, bArr2, 3);
        copyBooleanValueToBytes(true, bArr2, 4);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 64) {
                int uBYTEValueFromBytes = getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
                int i4 = uBYTEValueFromBytes;
                if (uBYTEValueFromBytes != i3) {
                    String str2 = this.logTag;
                    new StringBuilder("MessageRead: unexpected return mailbox: ");
                    int w = Log.w(str2, sb2.append(i4).append(" (expected ").append(i3).append(")").toString());
                }
                return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 5, getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 4) - 1);
            }
            String str3 = this.logTag;
            new StringBuilder("MessageRead: unexpected return package length ");
            int w2 = Log.w(str3, sb.append(sendCommandAndReceiveReturnPackage.length).append(" (expected 64)").toString());
        }
        return "";
    }

    /* JADX INFO: finally extract failed */
    @SimpleFunction(description = "Download a file to the robot.")
    public void DownloadFile(String str, String str2) {
        StringBuilder sb;
        InputStream inputStream;
        Integer num;
        Integer num2;
        Throwable th;
        int i;
        StringBuilder sb2;
        StringBuilder sb3;
        Throwable th2;
        StringBuilder sb4;
        StringBuilder sb5;
        String str3 = str;
        String str4 = str2;
        String str5 = "DownloadFile";
        if (checkBluetooth(str5)) {
            if (str3.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, str5, 414, new Object[0]);
            } else if (str4.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, str5, 415, new Object[0]);
            } else {
                try {
                    File copyMediaToTempFile = MediaUtil.copyMediaToTempFile(this.form, str3);
                    try {
                        InputStream inputStream2 = inputStream;
                        new BufferedInputStream(FileUtil.openFile(copyMediaToTempFile), 1024);
                        InputStream inputStream3 = inputStream2;
                        try {
                            long length = copyMediaToTempFile.length();
                            if (str4.endsWith(".rxe") || str4.endsWith(".ric")) {
                                String str6 = str5;
                                byte[] bArr = new byte[26];
                                byte[] bArr2 = bArr;
                                bArr[0] = 1;
                                bArr2[1] = Ev3Constants.Opcode.BP1;
                                copyStringValueToBytes(str4, bArr2, 2, 19);
                                copyULONGValueToBytes(length, bArr2, 22);
                                byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str6, bArr2);
                                if (evaluateStatus(str6, sendCommandAndReceiveReturnPackage, bArr2[1])) {
                                    if (sendCommandAndReceiveReturnPackage.length == 4) {
                                        num = Integer.valueOf(getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3));
                                    } else {
                                        new StringBuilder();
                                        int w = Log.w(this.logTag, sb4.append(str6).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage.length).append(" (expected 4)").toString());
                                    }
                                }
                                num = null;
                            } else {
                                String str7 = str5;
                                byte[] bArr3 = new byte[26];
                                byte[] bArr4 = bArr3;
                                bArr3[0] = 1;
                                bArr4[1] = -127;
                                copyStringValueToBytes(str4, bArr4, 2, 19);
                                copyULONGValueToBytes(length, bArr4, 22);
                                byte[] sendCommandAndReceiveReturnPackage2 = sendCommandAndReceiveReturnPackage(str7, bArr4);
                                if (evaluateStatus(str7, sendCommandAndReceiveReturnPackage2, bArr4[1])) {
                                    if (sendCommandAndReceiveReturnPackage2.length == 4) {
                                        num = Integer.valueOf(getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage2, 3));
                                    } else {
                                        new StringBuilder();
                                        int w2 = Log.w(this.logTag, sb5.append(str7).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage2.length).append(" (expected 4)").toString());
                                    }
                                }
                                num = null;
                            }
                            Integer num3 = num;
                            num2 = num3;
                            if (num3 == null) {
                                inputStream3.close();
                                boolean delete = copyMediaToTempFile.delete();
                                return;
                            }
                            byte[] bArr5 = new byte[32];
                            long j = 0;
                            while (j < length) {
                                int min = (int) Math.min(32, length - j);
                                int read = inputStream3.read(bArr5, 0, min);
                                int i2 = min;
                                byte[] bArr6 = bArr5;
                                int intValue = num2.intValue();
                                String str8 = str5;
                                if (i2 > 32) {
                                    Throwable th3 = th;
                                    new IllegalArgumentException("length must be <= 32");
                                    throw th3;
                                }
                                byte[] bArr7 = new byte[(i2 + 3)];
                                byte[] bArr8 = bArr7;
                                bArr7[0] = 1;
                                bArr8[1] = Ev3Constants.Opcode.UI_BUTTON;
                                copyUBYTEValueToBytes(intValue, bArr8, 2);
                                System.arraycopy(bArr6, 0, bArr8, 3, i2);
                                byte[] sendCommandAndReceiveReturnPackage3 = sendCommandAndReceiveReturnPackage(str8, bArr8);
                                if (evaluateStatus(str8, sendCommandAndReceiveReturnPackage3, bArr8[1])) {
                                    if (sendCommandAndReceiveReturnPackage3.length == 6) {
                                        int uWORDValueFromBytes = getUWORDValueFromBytes(sendCommandAndReceiveReturnPackage3, 4);
                                        int i3 = uWORDValueFromBytes;
                                        if (uWORDValueFromBytes != i2) {
                                            new StringBuilder();
                                            int e = Log.e(this.logTag, sb3.append(str8).append(": only ").append(i3).append(" bytes were written (expected ").append(i2).append(")").toString());
                                            Throwable th4 = th2;
                                            new IOException("Unable to write file on robot");
                                            throw th4;
                                        }
                                        i = i3;
                                        j += (long) i;
                                    } else {
                                        new StringBuilder();
                                        int w3 = Log.w(this.logTag, sb2.append(str8).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage3.length).append(" (expected 6)").toString());
                                    }
                                }
                                i = 0;
                                j += (long) i;
                            }
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str5, num2.intValue());
                            inputStream3.close();
                            boolean delete2 = copyMediaToTempFile.delete();
                        } catch (Throwable th5) {
                            Throwable th6 = th5;
                            inputStream3.close();
                            throw th6;
                        }
                    } catch (Throwable th7) {
                        Throwable th8 = th7;
                        boolean delete3 = copyMediaToTempFile.delete();
                        throw th8;
                    }
                } catch (IOException e2) {
                    new StringBuilder();
                    this.form.dispatchErrorOccurredEvent(this, str5, 416, sb.append(e2.getMessage()).toString());
                }
            }
        }
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i) {
        String str2 = str;
        byte[] bArr = new byte[3];
        byte[] bArr2 = bArr;
        bArr[0] = 1;
        bArr2[1] = Ev3Constants.Opcode.UI_DRAW;
        copyUBYTEValueToBytes(i, bArr2, 2);
        boolean evaluateStatus = evaluateStatus(str2, sendCommandAndReceiveReturnPackage(str2, bArr2), bArr2[1]);
    }

    @SimpleFunction(description = "Delete a file on the robot.")
    public void DeleteFile(String str) {
        String str2 = str;
        String str3 = "DeleteFile";
        if (checkBluetooth(str3)) {
            if (str2.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, str3, 406, new Object[0]);
                return;
            }
            byte[] bArr = new byte[22];
            byte[] bArr2 = bArr;
            bArr[0] = 1;
            bArr2[1] = Ev3Constants.Opcode.TIMER_WAIT;
            copyStringValueToBytes(str2, bArr2, 2, 19);
            boolean evaluateStatus = evaluateStatus(str3, sendCommandAndReceiveReturnPackage(str3, bArr2), bArr2[1]);
        }
    }

    @SimpleFunction(description = "Returns a list containing the names of matching files found on the robot.")
    public List<String> ListFiles(String str) {
        List<String> list;
        List<String> list2;
        String str2 = str;
        String str3 = "ListFiles";
        if (!checkBluetooth(str3)) {
            new ArrayList();
            return list2;
        }
        new ArrayList();
        List<String> list3 = list;
        if (str2.length() == 0) {
            str2 = "*.*";
        }
        byte[] bArr = new byte[22];
        byte[] bArr2 = bArr;
        bArr[0] = 1;
        bArr2[1] = Ev3Constants.Opcode.TIMER_READY;
        copyStringValueToBytes(str2, bArr2, 2, 19);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str3, bArr2);
        int status = getStatus(str3, sendCommandAndReceiveReturnPackage, bArr2[1]);
        while (status == 0) {
            int uBYTEValueFromBytes = getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            boolean add = list3.add(getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 4));
            byte[] bArr3 = new byte[3];
            byte[] bArr4 = bArr3;
            bArr3[0] = 1;
            bArr4[1] = Ev3Constants.Opcode.TIMER_READ;
            copyUBYTEValueToBytes(uBYTEValueFromBytes, bArr4, 2);
            sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str3, bArr4);
            status = getStatus(str3, sendCommandAndReceiveReturnPackage, bArr4[1]);
        }
        return list3;
    }

    @SimpleFunction(description = "Get the firmware and protocol version numbers for the robot as a list where the first element is the firmware version number and the second element is the protocol version number.")
    public List<String> GetFirmwareVersion() {
        List<String> list;
        List<String> list2;
        StringBuilder sb;
        StringBuilder sb2;
        List<String> list3;
        String str = "GetFirmwareVersion";
        if (!checkBluetooth(str)) {
            new ArrayList();
            return list3;
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = bArr;
        bArr[0] = 1;
        bArr2[1] = Ev3Constants.Opcode.BP0;
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            new ArrayList();
            List<String> list4 = list2;
            List<String> list5 = list4;
            new StringBuilder();
            boolean add = list4.add(sb.append(sendCommandAndReceiveReturnPackage[6]).append(".").append(sendCommandAndReceiveReturnPackage[5]).toString());
            new StringBuilder();
            boolean add2 = list5.add(sb2.append(sendCommandAndReceiveReturnPackage[4]).append(".").append(sendCommandAndReceiveReturnPackage[3]).toString());
            return list5;
        }
        new ArrayList();
        return list;
    }

    @SimpleFunction(description = "Set the brick name of the robot.")
    public void SetBrickName(String str) {
        String str2 = str;
        String str3 = "SetBrickName";
        if (checkBluetooth(str3)) {
            byte[] bArr = new byte[18];
            byte[] bArr2 = bArr;
            bArr[0] = 1;
            bArr2[1] = -104;
            copyStringValueToBytes(str2, bArr2, 2, 15);
            boolean evaluateStatus = evaluateStatus(str3, sendCommandAndReceiveReturnPackage(str3, bArr2), bArr2[1]);
        }
    }

    @SimpleFunction(description = "Get the brick name of the robot.")
    public String GetBrickName() {
        String str = "GetBrickName";
        if (!checkBluetooth(str)) {
            return "";
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = bArr;
        bArr[0] = 1;
        bArr2[1] = -101;
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr2);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
        }
        return "";
    }
}
