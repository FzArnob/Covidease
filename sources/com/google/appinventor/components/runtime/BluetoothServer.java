package com.google.appinventor.components.runtime;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth server component", iconName = "images/bluetoothServer.png", nonVisible = true, version = 5)
@UsesPermissions(permissionNames = "android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothServer extends BluetoothConnectionBase {
    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    /* access modifiers changed from: private */
    public final Handler androidUIHandler;
    /* access modifiers changed from: private */
    public final AtomicReference<Object> arBluetoothServerSocket;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BluetoothServer(ComponentContainer componentContainer) {
        super(componentContainer, "BluetoothServer");
        Handler handler;
        AtomicReference<Object> atomicReference;
        new Handler();
        this.androidUIHandler = handler;
        new AtomicReference<>();
        this.arBluetoothServerSocket = atomicReference;
    }

    @SimpleFunction(description = "Accept an incoming connection with the Serial Port Profile (SPP).")
    public final void AcceptConnection(String str) {
        accept("AcceptConnection", str, SPP_UUID);
    }

    @SimpleFunction(description = "Accept an incoming connection with a specific UUID.")
    public final void AcceptConnectionWithUUID(String str, String str2) {
        accept("AcceptConnectionWithUUID", str, str2);
    }

    private void accept(String str, String str2, String str3) {
        Object listenUsingRfcommWithServiceRecord;
        Runnable runnable;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        Object obj = bluetoothAdapter;
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, str4, 501, new Object[0]);
        } else if (!BluetoothReflection.isBluetoothEnabled(obj)) {
            this.form.dispatchErrorOccurredEvent(this, str4, 502, new Object[0]);
        } else {
            try {
                UUID fromString = UUID.fromString(str6);
                try {
                    if (this.secure || Build.VERSION.SDK_INT < 10) {
                        listenUsingRfcommWithServiceRecord = BluetoothReflection.listenUsingRfcommWithServiceRecord(obj, str5, fromString);
                    } else {
                        listenUsingRfcommWithServiceRecord = BluetoothReflection.listenUsingInsecureRfcommWithServiceRecord(obj, str5, fromString);
                    }
                    this.arBluetoothServerSocket.set(listenUsingRfcommWithServiceRecord);
                    final String str7 = str4;
                    new Runnable(this) {
                        final /* synthetic */ BluetoothServer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            Runnable runnable;
                            Runnable runnable2;
                            Object obj = null;
                            Object obj2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.arBluetoothServerSocket.get();
                            Object obj3 = obj2;
                            if (obj2 != null) {
                                try {
                                    obj = BluetoothReflection.accept(obj3);
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.StopAccepting();
                                } catch (Exception e) {
                                    new Runnable(this) {
                                        private /* synthetic */ C05991 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                                        {
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                                        }

                                        public final void run() {
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str7, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_ACCEPT, new Object[0]);
                                        }
                                    };
                                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable2);
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.StopAccepting();
                                    return;
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.StopAccepting();
                                    throw th2;
                                }
                            }
                            if (obj != null) {
                                final Object obj4 = obj;
                                new Runnable(this) {
                                    private /* synthetic */ C05991 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                                    {
                                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                                    }

                                    public final void run() {
                                        try {
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setConnection(obj4);
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ConnectionAccepted();
                                        } catch (Exception e) {
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Disconnect();
                                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str7, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_ACCEPT, new Object[0]);
                                        }
                                    }
                                };
                                boolean post2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                            }
                        }
                    };
                    AsynchUtil.runAsynchronously(runnable);
                } catch (Exception e) {
                    this.form.dispatchErrorOccurredEvent(this, str4, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_LISTEN, new Object[0]);
                }
            } catch (IllegalArgumentException e2) {
                this.form.dispatchErrorOccurredEvent(this, str4, ErrorMessages.ERROR_BLUETOOTH_INVALID_UUID, str6);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean IsAccepting() {
        return this.arBluetoothServerSocket.get() != null;
    }

    @SimpleFunction(description = "Stop accepting an incoming connection.")
    public final void StopAccepting() {
        StringBuilder sb;
        Object andSet = this.arBluetoothServerSocket.getAndSet((Object) null);
        Object obj = andSet;
        if (andSet != null) {
            try {
                BluetoothReflection.closeBluetoothServerSocket(obj);
            } catch (Exception e) {
                Exception exc = e;
                String str = this.logTag;
                new StringBuilder("Error while closing bluetooth server socket: ");
                int w = Log.w(str, sb.append(exc.getMessage()).toString());
            }
        }
    }

    @SimpleEvent(description = "Indicates that a bluetooth connection has been accepted.")
    public final void ConnectionAccepted() {
        int i = Log.i(this.logTag, "Successfully accepted bluetooth connection.");
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ConnectionAccepted", new Object[0]);
    }
}
