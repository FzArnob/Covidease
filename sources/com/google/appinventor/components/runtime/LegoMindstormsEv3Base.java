package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import java.util.Collections;

@SimpleObject
public class LegoMindstormsEv3Base extends AndroidNonvisibleComponent implements Component, Deleteable, GeoJSONUtil.PropertyApplication {
    protected BluetoothClient bluetooth;
    protected int commandCount;
    protected final String logTag;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LegoMindstormsEv3Base(ComponentContainer componentContainer, String str) {
        super(componentContainer.$form());
        this.logTag = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected LegoMindstormsEv3Base() {
        super((Form) null);
        this.logTag = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.")
    public BluetoothClient BluetoothClient() {
        return this.bluetooth;
    }

    @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
    @SimpleProperty
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
    public final boolean isBluetoothConnected(String str) {
        String str2 = str;
        if (this.bluetooth == null) {
            this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_EV3_BLUETOOTH_NOT_SET, new Object[0]);
            return false;
        } else if (this.bluetooth.IsConnected()) {
            return true;
        } else {
            this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_EV3_NOT_CONNECTED_TO_ROBOT, new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final byte[] sendCommand(String str, byte[] bArr, boolean z) {
        String str2 = str;
        byte[] bArr2 = bArr;
        boolean z2 = z;
        if (!isBluetoothConnected(str2)) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Short.valueOf((short) (bArr2.length + 2));
        Object[] objArr2 = objArr;
        objArr2[1] = Short.valueOf((short) this.commandCount);
        byte[] pack = Ev3BinaryParser.pack("hh", objArr2);
        this.commandCount++;
        this.bluetooth.write(str2, pack);
        this.bluetooth.write(str2, bArr2);
        if (!z2) {
            return null;
        }
        byte[] read = this.bluetooth.read(str2, 4);
        byte[] bArr3 = read;
        if (read.length == 4) {
            Object[] unpack = Ev3BinaryParser.unpack("hh", bArr3);
            int shortValue = ((Short) unpack[0]).shortValue() - 2;
            short shortValue2 = ((Short) unpack[1]).shortValue();
            byte[] read2 = this.bluetooth.read(str2, shortValue);
            byte[] bArr4 = read2;
            if (read2.length == shortValue) {
                return bArr4;
            }
            this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
            return null;
        }
        this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        return null;
    }

    /* access modifiers changed from: protected */
    public final int sensorPortLetterToPortNumber(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String str2 = str;
        if (str2.length() != 1) {
            Throwable th3 = th2;
            new StringBuilder("String \"");
            new IllegalArgumentException(sb2.append(str2).append("\" is not a valid sensor port letter").toString());
            throw th3;
        }
        int charAt = str2.charAt(0) - '1';
        int i = charAt;
        if (charAt >= 0 && i <= 3) {
            return i;
        }
        Throwable th4 = th;
        new StringBuilder("String \"");
        new IllegalArgumentException(sb.append(str2).append("\" is not a valid sensor port letter").toString());
        throw th4;
    }

    /* access modifiers changed from: protected */
    public final String portNumberToSensorPortLetter(int i) {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        int i2 = i;
        if (i2 < 0 || i2 > 3) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append(i2).append(" is not a valid port number").toString());
            throw th2;
        }
        new StringBuilder();
        return sb2.append(i2 + 49).toString();
    }

    /* access modifiers changed from: protected */
    public final int motorPortLettersToBitField(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Throwable th6;
        StringBuilder sb6;
        String str2 = str;
        if (str2.length() > 4) {
            Throwable th7 = th6;
            new StringBuilder("Malformed motor port letters \"");
            new IllegalArgumentException(sb6.append(str2).append("\"").toString());
            throw th7;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < str2.length(); i5++) {
            switch (str2.charAt(i5)) {
                case 'A':
                    if (i == 0) {
                        i = 1;
                        break;
                    } else {
                        Throwable th8 = th5;
                        new StringBuilder("Malformed motor port letters \"");
                        new IllegalArgumentException(sb5.append(str2).append("\"").toString());
                        throw th8;
                    }
                case 'B':
                    if (i2 == 0) {
                        i2 = 2;
                        break;
                    } else {
                        Throwable th9 = th4;
                        new StringBuilder("Malformed motor port letters \"");
                        new IllegalArgumentException(sb4.append(str2).append("\"").toString());
                        throw th9;
                    }
                case 'C':
                    if (i3 == 0) {
                        i3 = 4;
                        break;
                    } else {
                        Throwable th10 = th3;
                        new StringBuilder("Malformed motor port letters \"");
                        new IllegalArgumentException(sb3.append(str2).append("\"").toString());
                        throw th10;
                    }
                case 'D':
                    if (i4 == 0) {
                        i4 = 8;
                        break;
                    } else {
                        Throwable th11 = th2;
                        new StringBuilder("Malformed motor port letters \"");
                        new IllegalArgumentException(sb2.append(str2).append("\"").toString());
                        throw th11;
                    }
                default:
                    Throwable th12 = th;
                    new StringBuilder("Malformed motor port letters \"");
                    new IllegalArgumentException(sb.append(str2).append("\"").toString());
                    throw th12;
            }
        }
        return i | i2 | i3 | i4;
    }

    /* access modifiers changed from: protected */
    public final String bitFieldToMotorPortLetters(int i) {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        int i2 = i;
        if (i2 < 0 || i2 > 15) {
            Throwable th2 = th;
            new IllegalArgumentException("Invalid bit field number ".concat(String.valueOf(i2)));
            throw th2;
        }
        String str = "";
        if ((i2 & 1) != 0) {
            new StringBuilder();
            str = sb4.append(str).append("A").toString();
        }
        if ((i2 & 2) != 0) {
            new StringBuilder();
            str = sb3.append(str).append("B").toString();
        }
        if ((i2 & 4) != 0) {
            new StringBuilder();
            str = sb2.append(str).append("C").toString();
        }
        if ((i2 & 8) != 0) {
            new StringBuilder();
            str = sb.append(str).append("D").toString();
        }
        return str;
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
