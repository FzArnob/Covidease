package com.google.appinventor.components.runtime;

import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth client component", iconName = "images/bluetoothClient.png", nonVisible = true, version = 6)
@UsesPermissions(permissionNames = "android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothClient extends BluetoothConnectionBase {
    private final List<Component> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private Set<Integer> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BluetoothClient(ComponentContainer componentContainer) {
        super(componentContainer, PropertyTypeConstants.PROPERTY_TYPE_BLUETOOTHCLIENT);
        List<Component> list;
        new ArrayList();
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = list;
    }

    /* access modifiers changed from: package-private */
    public final boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Component component, Set<Integer> set) {
        Set<Integer> set2;
        Set<Integer> set3;
        Component component2 = component;
        Set<Integer> set4 = set;
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.isEmpty()) {
            if (set4 == null) {
                set3 = null;
            } else {
                set3 = set2;
                new HashSet(set4);
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = set3;
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            if (set4 != null) {
                return false;
            }
        } else if (set4 == null) {
            return false;
        } else {
            if (!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.containsAll(set4)) {
                return false;
            }
            if (!set4.containsAll(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return false;
            }
        }
        boolean add = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.add(component2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Component component) {
        boolean remove = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.remove(component);
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.isEmpty()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    @SimpleFunction(description = "Checks whether the Bluetooth device with the specified address is paired.")
    public final boolean IsDevicePaired(String str) {
        String str2 = str;
        String str3 = "IsDevicePaired";
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        Object obj = bluetoothAdapter;
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, str3, 501, new Object[0]);
            return false;
        } else if (!BluetoothReflection.isBluetoothEnabled(obj)) {
            this.form.dispatchErrorOccurredEvent(this, str3, 502, new Object[0]);
            return false;
        } else {
            int indexOf = str2.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            int i = indexOf;
            if (indexOf != -1) {
                str2 = str2.substring(0, i);
            }
            if (BluetoothReflection.checkBluetoothAddress(obj, str2)) {
                return BluetoothReflection.isBonded(BluetoothReflection.getRemoteDevice(obj, str2));
            }
            this.form.dispatchErrorOccurredEvent(this, str3, 503, new Object[0]);
            return false;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The addresses and names of paired Bluetooth devices")
    public final List<String> AddressesAndNames() {
        List<String> list;
        StringBuilder sb;
        new ArrayList();
        List<String> list2 = list;
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        Object obj = bluetoothAdapter;
        if (bluetoothAdapter != null && BluetoothReflection.isBluetoothEnabled(obj)) {
            for (Object next : BluetoothReflection.getBondedDevices(obj)) {
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(next)) {
                    String bluetoothDeviceName = BluetoothReflection.getBluetoothDeviceName(next);
                    String bluetoothDeviceAddress = BluetoothReflection.getBluetoothDeviceAddress(next);
                    new StringBuilder();
                    boolean add = list2.add(sb.append(bluetoothDeviceAddress).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(bluetoothDeviceName).toString());
                }
            }
        }
        return list2;
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        Object obj2 = obj;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return true;
        }
        Object bluetoothClass = BluetoothReflection.getBluetoothClass(obj2);
        Object obj3 = bluetoothClass;
        if (bluetoothClass == null) {
            return false;
        }
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.contains(Integer.valueOf(BluetoothReflection.getDeviceClass(obj3)));
    }

    @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and the Serial Port Profile (SPP). Returns true if the connection was successful.")
    public final boolean Connect(String str) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Connect", str, "00001101-0000-1000-8000-00805F9B34FB");
    }

    @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and UUID. Returns true if the connection was successful.")
    public final boolean ConnectWithUUID(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("ConnectWithUUID", str, str2);
    }

    @SimpleFunction(description = "Remove the Name from a Bluetooth Address and Name String.")
    public final String RemoveNameFromAddress(String str) {
        String str2 = str;
        int indexOf = str2.indexOf(32);
        int i = indexOf;
        if (indexOf != -1) {
            return str2.substring(0, i);
        }
        return str2;
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, String str3) {
        Object createRfcommSocketToServiceRecord;
        StringBuilder sb;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        Object obj = bluetoothAdapter;
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, str4, 501, new Object[0]);
            return false;
        } else if (!BluetoothReflection.isBluetoothEnabled(obj)) {
            this.form.dispatchErrorOccurredEvent(this, str4, 502, new Object[0]);
            return false;
        } else {
            int indexOf = str5.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            int i = indexOf;
            if (indexOf != -1) {
                str5 = str5.substring(0, i);
            }
            if (!BluetoothReflection.checkBluetoothAddress(obj, str5)) {
                this.form.dispatchErrorOccurredEvent(this, str4, 503, new Object[0]);
                return false;
            }
            Object remoteDevice = BluetoothReflection.getRemoteDevice(obj, str5);
            Object obj2 = remoteDevice;
            if (!BluetoothReflection.isBonded(remoteDevice)) {
                this.form.dispatchErrorOccurredEvent(this, str4, 504, new Object[0]);
                return false;
            } else if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2)) {
                this.form.dispatchErrorOccurredEvent(this, str4, 505, new Object[0]);
                return false;
            } else {
                try {
                    UUID fromString = UUID.fromString(str6);
                    Disconnect();
                    Object obj3 = obj2;
                    try {
                        if (this.secure || Build.VERSION.SDK_INT < 10) {
                            createRfcommSocketToServiceRecord = BluetoothReflection.createRfcommSocketToServiceRecord(obj3, fromString);
                        } else {
                            createRfcommSocketToServiceRecord = BluetoothReflection.createInsecureRfcommSocketToServiceRecord(obj3, fromString);
                        }
                        BluetoothReflection.connectToBluetoothSocket(createRfcommSocketToServiceRecord);
                        setConnection(createRfcommSocketToServiceRecord);
                        String str7 = this.logTag;
                        new StringBuilder("Connected to Bluetooth device ");
                        int i2 = Log.i(str7, sb.append(BluetoothReflection.getBluetoothDeviceAddress(obj3)).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(BluetoothReflection.getBluetoothDeviceName(obj3)).append(".").toString());
                        return true;
                    } catch (Exception e) {
                        Disconnect();
                        this.form.dispatchErrorOccurredEvent(this, str4, 507, new Object[0]);
                        return false;
                    }
                } catch (IllegalArgumentException e2) {
                    this.form.dispatchErrorOccurredEvent(this, str4, ErrorMessages.ERROR_BLUETOOTH_INVALID_UUID, str6);
                    return false;
                }
            }
        }
    }
}
