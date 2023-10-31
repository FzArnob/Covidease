package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kawa.Telnet;

@SimpleObject
public class LegoMindstormsNxtBase extends AndroidNonvisibleComponent implements Component, Deleteable, GeoJSONUtil.PropertyApplication {
    private static final Map<Integer, String> vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    protected BluetoothClient bluetooth;
    protected final String logTag;

    static {
        Map<Integer, String> map;
        new HashMap();
        Map<Integer, String> map2 = map;
        vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = map2;
        String put = map2.put(32, "Pending communication transaction in progress");
        String put2 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(64, "Specified mailbox queue is empty");
        String put3 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(129, "No more handles");
        String put4 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(130, "No space");
        String put5 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(131, "No more files");
        String put6 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(132, "End of file expected");
        String put7 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(133, "End of file");
        String put8 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(134, "Not a linear file");
        String put9 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(135, "File not found");
        String put10 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(136, "Handle already closed");
        String put11 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(137, "No linear space");
        String put12 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(138, "Undefined error");
        String put13 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(139, "File is busy");
        String put14 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(140, "No write buffers");
        String put15 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(141, "Append not possible");
        String put16 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(142, "File is full");
        String put17 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(143, "File exists");
        String put18 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(144, "Module not found");
        String put19 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(145, "Out of boundary");
        String put20 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(146, "Illegal file name");
        String put21 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(147, "Illegal handle");
        String put22 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG), "Request failed (i.e. specified file not found)");
        String put23 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK), "Unknown command opcode");
        String put24 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY), "Insane packet");
        String put25 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE), "Data contains out-of-range values");
        String put26 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(221, "Communication bus error");
        String put27 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(222, "No free memory in communication buffer");
        String put28 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(223, "Specified channel/connection is not valid");
        String put29 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(YaVersion.YOUNG_ANDROID_VERSION), "Specified channel/connection not configured or busy");
        String put30 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(236, "No active program");
        String put31 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(237, "Illegal size specified");
        String put32 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(238, "Illegal mailbox queue ID specified");
        String put33 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(239, "Attempted to access invalid field of a structure");
        String put34 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(240, "Bad input or output specified");
        String put35 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(Integer.valueOf(Telnet.WILL), "Insufficient memory available");
        String put36 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(255, "Bad arguments");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LegoMindstormsNxtBase(ComponentContainer componentContainer, String str) {
        super(componentContainer.$form());
        this.logTag = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LegoMindstormsNxtBase() {
        super((Form) null);
        this.logTag = null;
    }

    public final void Initialize() {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.", userVisible = false)
    public BluetoothClient BluetoothClient() {
        return this.bluetooth;
    }

    @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
    @SimpleProperty(userVisible = false)
    public void BluetoothClient(BluetoothClient bluetoothClient) {
        BluetoothClient bluetoothClient2 = bluetoothClient;
        if (this.bluetooth != null) {
            this.bluetooth.removeBluetoothConnectionListener$70312fc3(this);
            this.bluetooth.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Component) this);
            this.bluetooth = null;
        }
        if (bluetoothClient2 != null) {
            this.bluetooth = bluetoothClient2;
            boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.bluetooth.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this, Collections.singleton(2052));
            this.bluetooth.addBluetoothConnectionListener$70312fc3(this);
            if (this.bluetooth.IsConnected()) {
                afterConnect(this.bluetooth);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void setOutputState(String str, int i, int i2, int i3, int i4, int i5, int i6, long j) {
        int sanitizePower = sanitizePower(i2);
        byte[] bArr = new byte[12];
        byte[] bArr2 = bArr;
        bArr[0] = Byte.MIN_VALUE;
        bArr2[1] = 4;
        copyUBYTEValueToBytes(i, bArr2, 2);
        copySBYTEValueToBytes(sanitizePower, bArr2, 3);
        copyUBYTEValueToBytes(i3, bArr2, 4);
        copyUBYTEValueToBytes(i4, bArr2, 5);
        copySBYTEValueToBytes(i5, bArr2, 6);
        copyUBYTEValueToBytes(i6, bArr2, 7);
        copyULONGValueToBytes(j, bArr2, 8);
        sendCommand(str, bArr2);
    }

    /* access modifiers changed from: protected */
    public final void setInputMode(String str, int i, int i2, int i3) {
        byte[] bArr = new byte[5];
        byte[] bArr2 = bArr;
        bArr[0] = Byte.MIN_VALUE;
        bArr2[1] = 5;
        copyUBYTEValueToBytes(i, bArr2, 2);
        copyUBYTEValueToBytes(i2, bArr2, 3);
        copyUBYTEValueToBytes(i3, bArr2, 4);
        sendCommand(str, bArr2);
    }

    /* access modifiers changed from: protected */
    public final byte[] getInputValues(String str, int i) {
        StringBuilder sb;
        String str2 = str;
        byte[] bArr = new byte[3];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 7;
        copyUBYTEValueToBytes(i, bArr2, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str2, bArr2);
        if (evaluateStatus(str2, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 16) {
                return sendCommandAndReceiveReturnPackage;
            }
            String str3 = this.logTag;
            new StringBuilder();
            int w = Log.w(str3, sb.append(str2).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage.length).append(" (expected 16)").toString());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void resetInputScaledValue(String str, int i) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = bArr;
        bArr[0] = Byte.MIN_VALUE;
        bArr2[1] = 8;
        copyUBYTEValueToBytes(i, bArr2, 2);
        sendCommand(str, bArr2);
    }

    /* access modifiers changed from: protected */
    public final int lsGetStatus(String str, int i) {
        StringBuilder sb;
        String str2 = str;
        byte[] bArr = new byte[3];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 14;
        copyUBYTEValueToBytes(i, bArr2, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str2, bArr2);
        if (evaluateStatus(str2, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 4) {
                return getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str3 = this.logTag;
            new StringBuilder();
            int w = Log.w(str3, sb.append(str2).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage.length).append(" (expected 4)").toString());
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void lsWrite(String str, int i, byte[] bArr, int i2) {
        Throwable th;
        String str2 = str;
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        if (bArr2.length > 16) {
            Throwable th2 = th;
            new IllegalArgumentException("length must be <= 16");
            throw th2;
        }
        byte[] bArr3 = new byte[(5 + bArr2.length)];
        byte[] bArr4 = bArr3;
        bArr3[0] = 0;
        bArr4[1] = 15;
        copyUBYTEValueToBytes(i3, bArr4, 2);
        copyUBYTEValueToBytes(bArr2.length, bArr4, 3);
        copyUBYTEValueToBytes(i4, bArr4, 4);
        System.arraycopy(bArr2, 0, bArr4, 5, bArr2.length);
        boolean evaluateStatus = evaluateStatus(str2, sendCommandAndReceiveReturnPackage(str2, bArr4), bArr4[1]);
    }

    /* access modifiers changed from: protected */
    public final byte[] lsRead(String str, int i) {
        StringBuilder sb;
        String str2 = str;
        byte[] bArr = new byte[3];
        byte[] bArr2 = bArr;
        bArr[0] = 0;
        bArr2[1] = 16;
        copyUBYTEValueToBytes(i, bArr2, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str2, bArr2);
        if (evaluateStatus(str2, sendCommandAndReceiveReturnPackage, bArr2[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 20) {
                return sendCommandAndReceiveReturnPackage;
            }
            String str3 = this.logTag;
            new StringBuilder();
            int w = Log.w(str3, sb.append(str2).append(": unexpected return package length ").append(sendCommandAndReceiveReturnPackage.length).append(" (expected 20)").toString());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean checkBluetooth(String str) {
        String str2 = str;
        if (this.bluetooth == null) {
            this.form.dispatchErrorOccurredEvent(this, str2, 401, new Object[0]);
            return false;
        } else if (this.bluetooth.IsConnected()) {
            return true;
        } else {
            this.form.dispatchErrorOccurredEvent(this, str2, 402, new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final byte[] sendCommandAndReceiveReturnPackage(String str, byte[] bArr) {
        String str2 = str;
        sendCommand(str2, bArr);
        String str3 = str2;
        byte[] read = this.bluetooth.read(str3, 2);
        byte[] bArr2 = read;
        if (read.length == 2) {
            byte[] read2 = this.bluetooth.read(str3, getUWORDValueFromBytes(bArr2, 0));
            byte[] bArr3 = read2;
            if (read2.length >= 3) {
                return bArr3;
            }
        }
        this.form.dispatchErrorOccurredEvent(this, str3, 403, new Object[0]);
        return new byte[0];
    }

    /* access modifiers changed from: protected */
    public final void sendCommand(String str, byte[] bArr) {
        String str2 = str;
        byte[] bArr2 = bArr;
        byte[] bArr3 = new byte[2];
        copyUWORDValueToBytes(bArr2.length, bArr3, 0);
        this.bluetooth.write(str2, bArr3);
        this.bluetooth.write(str2, bArr2);
    }

    /* access modifiers changed from: protected */
    public final boolean evaluateStatus(String str, byte[] bArr, byte b) {
        StringBuilder sb;
        String str2 = str;
        int status = getStatus(str2, bArr, b);
        int i = status;
        if (status == 0) {
            return true;
        }
        int i2 = i;
        String str3 = str2;
        if (i2 >= 0) {
            String str4 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.get(Integer.valueOf(i2));
            String str5 = str4;
            if (str4 != null) {
                this.form.dispatchErrorOccurredEvent(this, str3, 404, str5);
            } else {
                new StringBuilder("Error code 0x");
                this.form.dispatchErrorOccurredEvent(this, str3, 404, sb.append(Integer.toHexString(i2 & 255)).toString());
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final int getStatus(String str, byte[] bArr, byte b) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str2 = str;
        byte[] bArr2 = bArr;
        byte b2 = b;
        if (bArr2.length >= 3) {
            if (bArr2[0] != 2) {
                String str3 = this.logTag;
                new StringBuilder();
                int w = Log.w(str3, sb3.append(str2).append(": unexpected return package byte 0: 0x").append(Integer.toHexString(bArr2[0] & Ev3Constants.Opcode.TST)).append(" (expected 0x02)").toString());
            }
            if (bArr2[1] != b2) {
                String str4 = this.logTag;
                new StringBuilder();
                int w2 = Log.w(str4, sb2.append(str2).append(": unexpected return package byte 1: 0x").append(Integer.toHexString(bArr2[1] & Ev3Constants.Opcode.TST)).append(" (expected 0x").append(Integer.toHexString(b2 & Ev3Constants.Opcode.TST)).append(")").toString());
            }
            return getUBYTEValueFromBytes(bArr2, 2);
        }
        String str5 = this.logTag;
        new StringBuilder();
        int w3 = Log.w(str5, sb.append(str2).append(": unexpected return package length ").append(bArr2.length).append(" (expected >= 3)").toString());
        return -1;
    }

    /* access modifiers changed from: protected */
    public final void copyBooleanValueToBytes(boolean z, byte[] bArr, int i) {
        bArr[i] = z ? (byte) 1 : 0;
    }

    /* access modifiers changed from: protected */
    public final void copySBYTEValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public final void copyUBYTEValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public final void copySWORDValueToBytes(int i, byte[] bArr, int i2) {
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        bArr2[i4] = (byte) i3;
        bArr2[i4 + 1] = (byte) (i3 >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copyUWORDValueToBytes(int i, byte[] bArr, int i2) {
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        bArr2[i4] = (byte) i3;
        bArr2[i4 + 1] = (byte) (i3 >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copySLONGValueToBytes(int i, byte[] bArr, int i2) {
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        bArr2[i4] = (byte) i3;
        int i5 = i3 >> 8;
        bArr2[i4 + 1] = (byte) i5;
        int i6 = i5 >> 8;
        bArr2[i4 + 2] = (byte) i6;
        bArr2[i4 + 3] = (byte) (i6 >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copyULONGValueToBytes(long j, byte[] bArr, int i) {
        long j2 = j;
        byte[] bArr2 = bArr;
        int i2 = i;
        bArr2[i2] = (byte) ((int) (j2 & 255));
        long j3 = j2 >> 8;
        bArr2[i2 + 1] = (byte) ((int) (j3 & 255));
        long j4 = j3 >> 8;
        bArr2[i2 + 2] = (byte) ((int) (j4 & 255));
        bArr2[i2 + 3] = (byte) ((int) ((j4 >> 8) & 255));
    }

    /* access modifiers changed from: protected */
    public final void copyStringValueToBytes(String str, byte[] bArr, int i, int i2) {
        StringBuilder sb;
        byte[] bytes;
        String str2 = str;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (str2.length() > i4) {
            str2 = str2.substring(0, i4);
        }
        try {
            bytes = str2.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            String str3 = this.logTag;
            new StringBuilder("UnsupportedEncodingException: ");
            int w = Log.w(str3, sb.append(unsupportedEncodingException.getMessage()).toString());
            bytes = str2.getBytes();
        }
        System.arraycopy(bytes, 0, bArr2, i3, Math.min(i4, bytes.length));
    }

    /* access modifiers changed from: protected */
    public final boolean getBooleanValueFromBytes(byte[] bArr, int i) {
        return bArr[i] != 0;
    }

    /* access modifiers changed from: protected */
    public final int getSBYTEValueFromBytes(byte[] bArr, int i) {
        return bArr[i];
    }

    /* access modifiers changed from: protected */
    public final int getUBYTEValueFromBytes(byte[] bArr, int i) {
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: protected */
    public final int getSWORDValueFromBytes(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (bArr2[i2] & Ev3Constants.Opcode.TST) | (bArr2[i2 + 1] << 8);
    }

    /* access modifiers changed from: protected */
    public final int getUWORDValueFromBytes(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (bArr2[i2] & Ev3Constants.Opcode.TST) | ((bArr2[i2 + 1] & Ev3Constants.Opcode.TST) << 8);
    }

    /* access modifiers changed from: protected */
    public final int getSLONGValueFromBytes(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (bArr2[i2] & Ev3Constants.Opcode.TST) | ((bArr2[i2 + 1] & Ev3Constants.Opcode.TST) << 8) | ((bArr2[i2 + 2] & Ev3Constants.Opcode.TST) << 16) | (bArr2[i2 + 3] << 24);
    }

    /* access modifiers changed from: protected */
    public final long getULONGValueFromBytes(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (((long) bArr2[i2]) & 255) | ((((long) bArr2[i2 + 1]) & 255) << 8) | ((((long) bArr2[i2 + 2]) & 255) << 16) | ((((long) bArr2[i2 + 3]) & 255) << 24);
    }

    /* access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        int i3 = 0;
        int i4 = i2;
        while (true) {
            if (i4 >= bArr2.length) {
                break;
            } else if (bArr2[i4] == 0) {
                i3 = i4 - i2;
                break;
            } else {
                i4++;
            }
        }
        return getStringValueFromBytes(bArr2, i2, i3);
    }

    /* access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bArr, int i, int i2) {
        StringBuilder sb;
        String str;
        String str2;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        try {
            String str3 = str2;
            new String(bArr2, i3, i4, "ISO-8859-1");
            return str3;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            String str4 = this.logTag;
            new StringBuilder("UnsupportedEncodingException: ");
            int w = Log.w(str4, sb.append(unsupportedEncodingException.getMessage()).toString());
            new String(bArr2, i3, i4);
            return str;
        }
    }

    /* access modifiers changed from: protected */
    public final int convertMotorPortLetterToNumber(String str) {
        Throwable th;
        String str2 = str;
        if (str2.length() == 1) {
            return convertMotorPortLetterToNumber(str2.charAt(0));
        }
        Throwable th2 = th;
        new IllegalArgumentException("Illegal motor port letter ".concat(String.valueOf(str2)));
        throw th2;
    }

    /* access modifiers changed from: protected */
    public final int convertMotorPortLetterToNumber(char c) {
        Throwable th;
        char c2 = c;
        if (c2 == 'A' || c2 == 'a') {
            return 0;
        }
        if (c2 == 'B' || c2 == 'b') {
            return 1;
        }
        if (c2 == 'C' || c2 == 'c') {
            return 2;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Illegal motor port letter ".concat(String.valueOf(c2)));
        throw th2;
    }

    /* access modifiers changed from: protected */
    public final int convertSensorPortLetterToNumber(String str) {
        Throwable th;
        String str2 = str;
        if (str2.length() == 1) {
            return convertSensorPortLetterToNumber(str2.charAt(0));
        }
        Throwable th2 = th;
        new IllegalArgumentException("Illegal sensor port letter ".concat(String.valueOf(str2)));
        throw th2;
    }

    /* access modifiers changed from: protected */
    public final int convertSensorPortLetterToNumber(char c) {
        Throwable th;
        char c2 = c;
        if (c2 == '1') {
            return 0;
        }
        if (c2 == '2') {
            return 1;
        }
        if (c2 == '3') {
            return 2;
        }
        if (c2 == '4') {
            return 3;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Illegal sensor port letter ".concat(String.valueOf(c2)));
        throw th2;
    }

    /* access modifiers changed from: protected */
    public final int sanitizePower(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int i2 = i;
        if (i2 < -100) {
            String str = this.logTag;
            new StringBuilder("power ");
            int w = Log.w(str, sb2.append(i2).append(" is invalid, using -100.").toString());
            i2 = -100;
        }
        if (i2 > 100) {
            String str2 = this.logTag;
            new StringBuilder("power ");
            int w2 = Log.w(str2, sb.append(i2).append(" is invalid, using 100.").toString());
            i2 = 100;
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public final int sanitizeTurnRatio(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int i2 = i;
        if (i2 < -100) {
            String str = this.logTag;
            new StringBuilder("turnRatio ");
            int w = Log.w(str, sb2.append(i2).append(" is invalid, using -100.").toString());
            i2 = -100;
        }
        if (i2 > 100) {
            String str2 = this.logTag;
            new StringBuilder("turnRatio ");
            int w2 = Log.w(str2, sb.append(i2).append(" is invalid, using 100.").toString());
            i2 = 100;
        }
        return i2;
    }

    public void afterConnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    public void onDelete() {
        if (this.bluetooth != null) {
            this.bluetooth.removeBluetoothConnectionListener$70312fc3(this);
            this.bluetooth.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Component) this);
            this.bluetooth = null;
        }
    }
}
