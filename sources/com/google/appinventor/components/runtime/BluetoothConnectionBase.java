package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
public abstract class BluetoothConnectionBase extends AndroidNonvisibleComponent implements Component, Deleteable, OnDestroyListener {
    private final List<GeoJSONUtil.PropertyApplication> bluetoothConnectionListeners;
    private ByteOrder byteOrder;
    private Object connectedBluetoothSocket;
    private byte delimiter;
    private String encoding;
    private InputStream inputStream;
    protected final String logTag;
    private OutputStream outputStream;
    protected boolean secure;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected BluetoothConnectionBase(ComponentContainer componentContainer, String str) {
        this(componentContainer.$form(), str);
        this.form.registerForOnDestroy(this);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BluetoothConnectionBase(Form form, String str) {
        super(form);
        List<GeoJSONUtil.PropertyApplication> list;
        new ArrayList();
        this.bluetoothConnectionListeners = list;
        this.logTag = str;
        HighByteFirst(false);
        CharacterEncoding("UTF-8");
        DelimiterByte(0);
        Secure(true);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected BluetoothConnectionBase(OutputStream outputStream2, InputStream inputStream2) {
        this((Form) null, (String) null);
        this.connectedBluetoothSocket = "Not Null";
        this.outputStream = outputStream2;
        this.inputStream = inputStream2;
    }

    /* access modifiers changed from: package-private */
    public void addBluetoothConnectionListener$70312fc3(GeoJSONUtil.PropertyApplication propertyApplication) {
        boolean add = this.bluetoothConnectionListeners.add(propertyApplication);
    }

    /* access modifiers changed from: package-private */
    public void removeBluetoothConnectionListener$70312fc3(GeoJSONUtil.PropertyApplication propertyApplication) {
        boolean remove = this.bluetoothConnectionListeners.remove(propertyApplication);
    }

    private void fireAfterConnectEvent() {
        for (GeoJSONUtil.PropertyApplication afterConnect : this.bluetoothConnectionListeners) {
            afterConnect.afterConnect(this);
        }
    }

    private void fireBeforeDisconnectEvent() {
        for (GeoJSONUtil.PropertyApplication beforeDisconnect : this.bluetoothConnectionListeners) {
            beforeDisconnect.beforeDisconnect(this);
        }
    }

    public final void Initialize() {
    }

    @SimpleEvent(description = "The BluetoothError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public void BluetoothError(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void bluetoothError(String str, int i, Object... objArr) {
        this.form.dispatchErrorOccurredEvent(this, str, i, objArr);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is available on the device")
    public boolean Available() {
        return BluetoothReflection.getBluetoothAdapter() != null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is enabled")
    public boolean Enabled() {
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        Object obj = bluetoothAdapter;
        if (bluetoothAdapter == null || !BluetoothReflection.isBluetoothEnabled(obj)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final void setConnection(Object obj) throws IOException {
        InputStream inputStream2;
        OutputStream outputStream2;
        this.connectedBluetoothSocket = obj;
        new BufferedInputStream(BluetoothReflection.getInputStream(this.connectedBluetoothSocket));
        this.inputStream = inputStream2;
        new BufferedOutputStream(BluetoothReflection.getOutputStream(this.connectedBluetoothSocket));
        this.outputStream = outputStream2;
        fireAfterConnectEvent();
    }

    @SimpleFunction(description = "Disconnect from the connected Bluetooth device.")
    public final void Disconnect() {
        StringBuilder sb;
        if (this.connectedBluetoothSocket != null) {
            fireBeforeDisconnectEvent();
            try {
                BluetoothReflection.closeBluetoothSocket(this.connectedBluetoothSocket);
                int i = Log.i(this.logTag, "Disconnected from Bluetooth device.");
            } catch (Exception e) {
                Exception exc = e;
                String str = this.logTag;
                new StringBuilder("Error while disconnecting: ");
                int w = Log.w(str, sb.append(exc.getMessage()).toString());
            }
            this.connectedBluetoothSocket = null;
        }
        this.inputStream = null;
        this.outputStream = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean IsConnected() {
        return this.connectedBluetoothSocket != null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to invoke SSP (Simple Secure Pairing), which is supported on devices with Bluetooth v2.1 or higher. When working with embedded Bluetooth devices, this property may need to be set to False. For Android 2.0-2.2, this property setting will be ignored.")
    public boolean Secure() {
        return this.secure;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Secure(boolean z) {
        boolean z2 = z;
        this.secure = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HighByteFirst() {
        return this.byteOrder == ByteOrder.BIG_ENDIAN;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void HighByteFirst(boolean z) {
        this.byteOrder = z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    @DesignerProperty(defaultValue = "UTF-8", editorType = "string")
    @SimpleProperty
    public void CharacterEncoding(String str) {
        String str2 = str;
        try {
            byte[] bytes = "check".getBytes(str2);
            this.encoding = str2;
        } catch (UnsupportedEncodingException e) {
            bluetoothError("CharacterEncoding", ErrorMessages.ERROR_BLUETOOTH_UNSUPPORTED_ENCODING, str2);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String CharacterEncoding() {
        return this.encoding;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void DelimiterByte(int i) {
        int i2 = i;
        String str = "DelimiterByte";
        byte b = (byte) i2;
        int i3 = i2 >> 8;
        int i4 = i3;
        if (i3 == 0 || i4 == -1) {
            this.delimiter = b;
            return;
        }
        bluetoothError(str, 511, Integer.valueOf(i2));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int DelimiterByte() {
        return this.delimiter;
    }

    @SimpleFunction(description = "Send text to the connected Bluetooth device.")
    public void SendText(String str) {
        StringBuilder sb;
        byte[] bytes;
        String str2 = str;
        try {
            bytes = str2.getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            String str3 = this.logTag;
            new StringBuilder("UnsupportedEncodingException: ");
            int w = Log.w(str3, sb.append(unsupportedEncodingException.getMessage()).toString());
            bytes = str2.getBytes();
        }
        write("SendText", bytes);
    }

    @SimpleFunction(description = "Send a 1-byte number to the connected Bluetooth device.")
    public void Send1ByteNumber(String str) {
        String str2 = str;
        String str3 = "Send1ByteNumber";
        try {
            int intValue = Integer.decode(str2).intValue();
            byte b = (byte) intValue;
            int i = intValue >> 8;
            int i2 = i;
            if (i == 0 || i2 == -1) {
                write(str3, b);
                return;
            }
            bluetoothError(str3, 511, str2);
        } catch (NumberFormatException e) {
            bluetoothError(str3, ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str2);
        }
    }

    @SimpleFunction(description = "Send a 2-byte number to the connected Bluetooth device.")
    public void Send2ByteNumber(String str) {
        int i;
        String str2 = str;
        String str3 = "Send2ByteNumber";
        try {
            int intValue = Integer.decode(str2).intValue();
            byte[] bArr = new byte[2];
            if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
                bArr[1] = (byte) intValue;
                i = intValue >> 8;
                bArr[0] = (byte) i;
            } else {
                bArr[0] = (byte) intValue;
                i = intValue >> 8;
                bArr[1] = (byte) i;
            }
            int i2 = i >> 8;
            int i3 = i2;
            if (i2 == 0 || i3 == -1) {
                write(str3, bArr);
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = str2;
            Object[] objArr2 = objArr;
            objArr2[1] = 2;
            bluetoothError(str3, 512, objArr2);
        } catch (NumberFormatException e) {
            bluetoothError(str3, ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str2);
        }
    }

    @SimpleFunction(description = "Send a 4-byte number to the connected Bluetooth device.")
    public void Send4ByteNumber(String str) {
        long j;
        String str2 = str;
        String str3 = "Send4ByteNumber";
        try {
            long longValue = Long.decode(str2).longValue();
            byte[] bArr = new byte[4];
            if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
                bArr[3] = (byte) ((int) (longValue & 255));
                long j2 = longValue >> 8;
                bArr[2] = (byte) ((int) (j2 & 255));
                long j3 = j2 >> 8;
                bArr[1] = (byte) ((int) (j3 & 255));
                j = j3 >> 8;
                bArr[0] = (byte) ((int) (j & 255));
            } else {
                bArr[0] = (byte) ((int) (longValue & 255));
                long j4 = longValue >> 8;
                bArr[1] = (byte) ((int) (j4 & 255));
                long j5 = j4 >> 8;
                bArr[2] = (byte) ((int) (j5 & 255));
                j = j5 >> 8;
                bArr[3] = (byte) ((int) (j & 255));
            }
            long j6 = j >> 8;
            long j7 = j6;
            if (j6 == 0 || j7 == -1) {
                write(str3, bArr);
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = str2;
            Object[] objArr2 = objArr;
            objArr2[1] = 4;
            bluetoothError(str3, 512, objArr2);
        } catch (NumberFormatException e) {
            bluetoothError(str3, ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str2);
        }
    }

    @SimpleFunction(description = "Send a list of byte values to the connected Bluetooth device.")
    public void SendBytes(YailList yailList) {
        String str = "SendBytes";
        Object[] array = yailList.toArray();
        Object[] objArr = array;
        byte[] bArr = new byte[array.length];
        int i = 0;
        while (i < objArr.length) {
            try {
                int intValue = Integer.decode(objArr[i].toString()).intValue();
                bArr[i] = (byte) intValue;
                int i2 = intValue >> 8;
                int i3 = i2;
                if (i2 == 0 || i3 == -1) {
                    i++;
                } else {
                    bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_FIT_ELEMENT_IN_BYTE, Integer.valueOf(i + 1));
                    return;
                }
            } catch (NumberFormatException e) {
                bluetoothError(str, 513, Integer.valueOf(i + 1));
                return;
            }
        }
        write(str, bArr);
    }

    /* access modifiers changed from: protected */
    public void write(String str, byte b) {
        StringBuilder sb;
        String str2 = str;
        byte b2 = b;
        if (!IsConnected()) {
            bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(b2);
            this.outputStream.flush();
        } catch (Exception e) {
            new StringBuilder();
            bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, sb.append(e.getMessage()).toString());
        }
    }

    /* access modifiers changed from: protected */
    public void write(String str, byte[] bArr) {
        StringBuilder sb;
        String str2 = str;
        byte[] bArr2 = bArr;
        if (!IsConnected()) {
            bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(bArr2);
            this.outputStream.flush();
        } catch (Exception e) {
            new StringBuilder();
            bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, sb.append(e.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Returns an estimate of the number of bytes that can be received without blocking")
    public int BytesAvailableToReceive() {
        StringBuilder sb;
        String str = "BytesAvailableToReceive";
        if (!IsConnected()) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return 0;
        }
        try {
            return this.inputStream.available();
        } catch (Exception e) {
            new StringBuilder();
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, sb.append(e.getMessage()).toString());
            return 0;
        }
    }

    @SimpleFunction(description = "Receive text from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public String ReceiveText(int i) {
        String str;
        StringBuilder sb;
        String str2;
        String str3;
        int i2 = i;
        byte[] read = read("ReceiveText", i2);
        if (i2 < 0) {
            try {
                new String(read, 0, read.length - 1, this.encoding);
                return str3;
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException = e;
                String str4 = this.logTag;
                new StringBuilder("UnsupportedEncodingException: ");
                int w = Log.w(str4, sb.append(unsupportedEncodingException.getMessage()).toString());
                new String(read);
                return str2;
            }
        } else {
            String str5 = str;
            new String(read, this.encoding);
            return str5;
        }
    }

    @SimpleFunction(description = "Receive a signed 1-byte number from the connected Bluetooth device.")
    public int ReceiveSigned1ByteNumber() {
        byte[] read = read("ReceiveSigned1ByteNumber", 1);
        byte[] bArr = read;
        if (read.length != 1) {
            return 0;
        }
        return bArr[0];
    }

    @SimpleFunction(description = "Receive an unsigned 1-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned1ByteNumber() {
        byte[] read = read("ReceiveUnsigned1ByteNumber", 1);
        byte[] bArr = read;
        if (read.length != 1) {
            return 0;
        }
        return bArr[0] & Ev3Constants.Opcode.TST;
    }

    @SimpleFunction(description = "Receive a signed 2-byte number from the connected Bluetooth device.")
    public int ReceiveSigned2ByteNumber() {
        byte[] read = read("ReceiveSigned2ByteNumber", 2);
        byte[] bArr = read;
        if (read.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bArr[1] & Ev3Constants.Opcode.TST) | (bArr[0] << 8);
        }
        return (bArr[0] & Ev3Constants.Opcode.TST) | (bArr[1] << 8);
    }

    @SimpleFunction(description = "Receive a unsigned 2-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned2ByteNumber() {
        byte[] read = read("ReceiveUnsigned2ByteNumber", 2);
        byte[] bArr = read;
        if (read.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bArr[1] & Ev3Constants.Opcode.TST) | ((bArr[0] & Ev3Constants.Opcode.TST) << 8);
        }
        return (bArr[0] & Ev3Constants.Opcode.TST) | ((bArr[1] & Ev3Constants.Opcode.TST) << 8);
    }

    @SimpleFunction(description = "Receive a signed 4-byte number from the connected Bluetooth device.")
    public long ReceiveSigned4ByteNumber() {
        byte[] read = read("ReceiveSigned4ByteNumber", 4);
        byte[] bArr = read;
        if (read.length != 4) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (long) ((bArr[3] & Ev3Constants.Opcode.TST) | ((bArr[2] & Ev3Constants.Opcode.TST) << 8) | ((bArr[1] & Ev3Constants.Opcode.TST) << 16) | (bArr[0] << 24));
        }
        return (long) ((bArr[0] & Ev3Constants.Opcode.TST) | ((bArr[1] & Ev3Constants.Opcode.TST) << 8) | ((bArr[2] & Ev3Constants.Opcode.TST) << 16) | (bArr[3] << 24));
    }

    @SimpleFunction(description = "Receive a unsigned 4-byte number from the connected Bluetooth device.")
    public long ReceiveUnsigned4ByteNumber() {
        byte[] read = read("ReceiveUnsigned4ByteNumber", 4);
        byte[] bArr = read;
        if (read.length != 4) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (((long) bArr[3]) & 255) | ((((long) bArr[2]) & 255) << 8) | ((((long) bArr[1]) & 255) << 16) | ((((long) bArr[0]) & 255) << 24);
        }
        return (((long) bArr[0]) & 255) | ((((long) bArr[1]) & 255) << 8) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[3]) & 255) << 24);
    }

    @SimpleFunction(description = "Receive multiple signed byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveSignedBytes(int i) {
        List<Integer> list;
        byte[] read = read("ReceiveSignedBytes", i);
        new ArrayList();
        List<Integer> list2 = list;
        for (int i2 = 0; i2 < read.length; i2++) {
            boolean add = list2.add(Integer.valueOf(read[i2]));
        }
        return list2;
    }

    @SimpleFunction(description = "Receive multiple unsigned byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveUnsignedBytes(int i) {
        List<Integer> list;
        byte[] read = read("ReceiveUnsignedBytes", i);
        new ArrayList();
        List<Integer> list2 = list;
        for (int i2 = 0; i2 < read.length; i2++) {
            boolean add = list2.add(Integer.valueOf(read[i2] & Ev3Constants.Opcode.TST));
        }
        return list2;
    }

    /* access modifiers changed from: protected */
    public final byte[] read(String str, int i) {
        ByteArrayOutputStream byteArrayOutputStream;
        StringBuilder sb;
        int i2;
        StringBuilder sb2;
        String str2 = str;
        int i3 = i;
        if (!IsConnected()) {
            bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return new byte[0];
        }
        new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        if (i3 >= 0) {
            byte[] bArr = new byte[i3];
            int i4 = 0;
            while (true) {
                i2 = i4;
                if (i2 >= i3) {
                    break;
                }
                try {
                    int read = this.inputStream.read(bArr, i2, i3 - i2);
                    int i5 = read;
                    if (read == -1) {
                        bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new Object[0]);
                        break;
                    }
                    i4 = i2 + i5;
                } catch (Exception e) {
                    new StringBuilder();
                    bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, sb2.append(e.getMessage()).toString());
                }
            }
            byteArrayOutputStream2.write(bArr, 0, i2);
        } else {
            while (true) {
                try {
                    int read2 = this.inputStream.read();
                    int i6 = read2;
                    if (read2 != -1) {
                        byteArrayOutputStream2.write(i6);
                        if (i6 == this.delimiter) {
                            break;
                        }
                    } else {
                        bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new Object[0]);
                        break;
                    }
                } catch (Exception e2) {
                    new StringBuilder();
                    bluetoothError(str2, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, sb.append(e2.getMessage()).toString());
                }
            }
        }
        return byteArrayOutputStream2.toByteArray();
    }

    public void onDestroy() {
        prepareToDie();
    }

    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.connectedBluetoothSocket != null) {
            Disconnect();
        }
    }
}
