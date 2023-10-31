package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.physicaloid.lib.Physicaloid;
import java.io.UnsupportedEncodingException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Arduino USB Serial Component", iconName = "images/arduino.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "physicaloid.jar")
public class MakeroidArduino extends AndroidNonvisibleComponent implements Component {
    private Context context;
    private Physicaloid hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = 9600;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidArduino(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 9600(0x2580, float:1.3452E-41)
            r2.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Makeroid Arduino USB Serial Component"
            java.lang.String r3 = "Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidArduino.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Initializes Arduino Connection")
    public void InitializeArduino() {
        Physicaloid physicaloid;
        new Physicaloid(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = physicaloid;
        int d = Log.d("Makeroid Arduino USB Serial Component", "Initialized");
    }

    @SimpleFunction(description = "Opens Arduino Connection")
    public boolean OpenArduino() {
        int d = Log.d("Makeroid Arduino USB Serial Component", "Opening connection");
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.open();
    }

    @SimpleFunction(description = "Closes Arduino Connection")
    public boolean CloseArduino() {
        int d = Log.d("Makeroid Arduino USB Serial Component", "Closing connection");
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
    }

    @SimpleFunction(description = "Default baud rate is 9600 bps")
    public void BaudRate(int i) {
        int i2 = i;
        this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = i2;
        boolean baudrate = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBaudrate(i2);
        int d = Log.d("Makeroid Arduino USB Serial Component", "Baud Rate: ".concat(String.valueOf(i2)));
    }

    @SimpleFunction(description = "Read from Serial")
    public void ReadArduino() {
        String str;
        byte[] bArr = new byte[256];
        boolean z = true;
        String str2 = "";
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.read(bArr) > 0) {
            try {
                String str3 = str;
                new String(bArr, "UTF-8");
                str2 = str3;
            } catch (UnsupportedEncodingException e) {
                z = false;
                int e2 = Log.e("Makeroid Arduino USB Serial Component", e.getMessage());
            }
        } else {
            z = false;
        }
        AfterReadArduino(z, str2);
    }

    @SimpleFunction(description = "Write Data to Serial")
    public void WriteArduino(String str) {
        String str2 = str;
        if (!str2.isEmpty()) {
            int write = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.write(str2.getBytes());
        }
    }

    @SimpleFunction(description = "Returns true when the Arduino connection is open")
    public boolean IsOpenedArduino() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isOpened();
    }

    @SimpleEvent(description = "Triggered after Read function")
    public void AfterReadArduino(boolean z, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterRead", objArr2);
    }
}
