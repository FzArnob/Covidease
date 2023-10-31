package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.Ev3Constants;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides both high- and low-level interfaces to a LEGO MINDSTORMS EV3 robot, with functions that can control the motors.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
public class Ev3Motors extends LegoMindstormsEv3Base {
    private boolean EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = false;
    private int KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = 1;
    private boolean W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = true;
    private double hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 4.32d;
    private Handler hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Runnable f373hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = false;
    private boolean kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = true;
    private boolean lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = false;
    /* access modifiers changed from: private */
    public int sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = 0;

    static /* synthetic */ int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Ev3Motors ev3Motors, int i) {
        int i2 = i;
        int i3 = i2;
        ev3Motors.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = i3;
        return i2;
    }

    static /* synthetic */ boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Ev3Motors(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3Motors");
        Handler handler;
        Runnable runnable;
        new Handler();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = handler;
        new Runnable(this) {
            private /* synthetic */ Ev3Motors hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                String str = "";
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bluetooth != null && this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bluetooth.IsConnected()) {
                    int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                    boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = Ev3Motors.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou();
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb && Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TachoCountChanged(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                    }
                    int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                }
                boolean postDelayed = Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).postDelayed(this, 50);
            }
        };
        this.f373hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = runnable;
        boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.post(this.f373hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        MotorPorts("ABC");
        StopBeforeDisconnect(true);
        EnableSpeedRegulation(true);
        ReverseDirection(false);
        WheelDiameter(4.32d);
        TachoCountChangedEventEnabled(false);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The motor ports that the motors are connected to. The ports are specified by a sequence of port letters.", userVisible = false)
    public String MotorPorts() {
        return bitFieldToMotorPortLetters(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2);
    }

    @DesignerProperty(defaultValue = "ABC", editorType = "string")
    @SimpleProperty
    public void MotorPorts(String str) {
        String str2 = str;
        String str3 = "MotorPorts";
        try {
            this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = motorPortLettersToBitField(str2);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str3, ErrorMessages.ERROR_EV3_ILLEGAL_MOTOR_PORT, str2);
        }
    }

    @DesignerProperty(defaultValue = "4.32", editorType = "float")
    @SimpleProperty
    public void WheelDiameter(double d) {
        double d2 = d;
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = d2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The diameter of the wheels attached on the motors in centimeters.", userVisible = false)
    public double WheelDiameter() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ReverseDirection(boolean z) {
        boolean z2 = z;
        String str = "ReverseDirection";
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, z2 ? -1 : 1);
            this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = z2;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "It specifies if the direction of the motors is reversed.")
    public boolean ReverseDirection() {
        return this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnableSpeedRegulation(boolean z) {
        boolean z2 = z;
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The robot adjusts the power to maintain the speed if speed regulation is enabled.")
    public boolean EnableSpeedRegulation() {
        return this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to stop the motor before disconnecting.")
    public boolean StopBeforeDisconnect() {
        return this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void StopBeforeDisconnect(boolean z) {
        boolean z2 = z;
        this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the TachoCountChanged event should fire when the angle is changed.")
    public boolean TachoCountChangedEventEnabled() {
        return this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void TachoCountChangedEventEnabled(boolean z) {
        boolean z2 = z;
        this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = z2;
    }

    @SimpleFunction(description = "Start to rotate the motors.")
    public void RotateIndefinitely(int i) {
        Throwable th;
        Throwable th2;
        int i2 = i;
        String str = "RotateIndefinitely";
        try {
            if (this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) {
                int i3 = i2;
                int i4 = this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
                String str2 = str;
                if (i4 < 0 || i4 > 15) {
                    Throwable th3 = th2;
                    new IllegalArgumentException();
                    throw th3;
                }
                Object[] objArr = new Object[3];
                objArr[0] = (byte) 0;
                Object[] objArr2 = objArr;
                objArr2[1] = Byte.valueOf((byte) i4);
                Object[] objArr3 = objArr2;
                objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i3));
                byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POWER, false, 0, 0, "ccc", objArr3), false);
            } else {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i2);
            }
            int i5 = this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
            String str3 = str;
            if (i5 < 0 || i5 > 15) {
                Throwable th4 = th;
                new IllegalArgumentException();
                throw th4;
            }
            Object[] objArr4 = new Object[2];
            objArr4[0] = (byte) 0;
            Object[] objArr5 = objArr4;
            objArr5[1] = Byte.valueOf((byte) i5);
            byte[] sendCommand2 = sendCommand(str3, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_START, false, 0, 0, "cc", objArr5), false);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors in a number of tacho counts.")
    public void RotateInTachoCounts(int i, int i2, boolean z) {
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        String str = "RotateInTachoCounts";
        try {
            if (this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i3, i4, z2);
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i3, i4, z2);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors in a period of time.")
    public void RotateInDuration(int i, int i2, boolean z) {
        Throwable th;
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        String str = "RotateInDuration";
        try {
            if (this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i3, i4, z2);
                return;
            }
            boolean z3 = z2;
            int i5 = i4;
            int i6 = i3;
            int i7 = this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
            String str2 = str;
            if (i7 < 0 || i7 > 15 || i5 < 0) {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
            Object[] objArr = new Object[7];
            objArr[0] = (byte) 0;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i7);
            Object[] objArr3 = objArr2;
            objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i6));
            Object[] objArr4 = objArr3;
            objArr4[3] = 0;
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(i5);
            Object[] objArr6 = objArr5;
            objArr6[5] = 0;
            Object[] objArr7 = objArr6;
            Object[] objArr8 = objArr7;
            objArr7[6] = Byte.valueOf((byte) (z3 ? 1 : 0));
            byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_POWER, false, 0, 0, "ccccccc", objArr8), false);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors in a distance.")
    public void RotateInDistance(int i, double d, boolean z) {
        int i2 = i;
        boolean z2 = z;
        String str = "RotateInDistance";
        int i3 = (int) (((d * 360.0d) / this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) / 3.141592653589793d);
        try {
            if (this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i2, i3, z2);
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i2, i3, z2);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Start to rotate the motors at the same speed.")
    public void RotateSyncIndefinitely(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        String str = "RotateSyncIndefinitely";
        try {
            if (this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i3);
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i3, i4, 0, true);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed for a distance in cm.")
    public void RotateSyncInDistance(int i, int i2, int i3, boolean z) {
        int i4 = i;
        int i5 = i3;
        boolean z2 = z;
        String str = "RotateSyncInDuration";
        int i6 = (int) (((((double) i2) * 360.0d) / this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) / 3.141592653589793d);
        try {
            if (this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i4, i6, z2);
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i4, i5, i6, z2);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a period of time.")
    public void RotateSyncInDuration(int i, int i2, int i3, boolean z) {
        Throwable th;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = z;
        String str = "RotateSyncInDuration";
        try {
            if (this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2)) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i4, i5, z2);
                return;
            }
            boolean z3 = z2;
            int i7 = i5;
            int i8 = i6;
            int i9 = i4;
            int i10 = this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
            String str2 = str;
            if (i10 < 0 || i10 > 15 || i7 < 0) {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
            Object[] objArr = new Object[6];
            objArr[0] = (byte) 0;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i10);
            Object[] objArr3 = objArr2;
            objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i9));
            Object[] objArr4 = objArr3;
            objArr4[3] = Short.valueOf((short) i8);
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(i7);
            Object[] objArr6 = objArr5;
            Object[] objArr7 = objArr6;
            objArr6[5] = Byte.valueOf((byte) (z3 ? 1 : 0));
            byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SYNC, false, 0, 0, "cccccc", objArr7), false);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a number of tacho counts.")
    public void RotateSyncInTachoCounts(int i, int i2, int i3, boolean z) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = z;
        String str = "RotateSyncInTachoCounts";
        try {
            if (this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i4, i5, z2);
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, i4, i6, i5, z2);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Stop the motors of the robot.")
    public void Stop(boolean z) {
        String str = "Stop";
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, z);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Toggle the direction of motors.")
    public void ToggleDirection() {
        String str = "ToggleDirection";
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, 0);
            this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = !this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Set the current tacho count to zero.")
    public void ResetTachoCount() {
        Throwable th;
        String str = "ResetTachoCount";
        String str2 = str;
        try {
            int i = this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
            String str3 = str2;
            if (i < 0 || i > 15) {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
            Object[] objArr = new Object[2];
            objArr[0] = (byte) 0;
            Object[] objArr2 = objArr;
            objArr2[1] = Byte.valueOf((byte) i);
            byte[] sendCommand = sendCommand(str3, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_CLR_COUNT, false, 0, 0, "cc", objArr2), false);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
        }
    }

    @SimpleFunction(description = "Get the current tacho count.")
    public int GetTachoCount() {
        String str = "GetTachoCount";
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
            return 0;
        }
    }

    @SimpleEvent(description = "Called when the tacho count has changed.")
    public void TachoCountChanged(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TachoCountChanged", Integer.valueOf(i));
    }

    private static int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        int i2 = i;
        if (i2 < -100) {
            return -100;
        }
        if (i2 > 100) {
            return 100;
        }
        return i2;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        int i2 = i;
        if (i2 != 0) {
            int i3 = i2;
            if ((i3 & ((i3 ^ (i2 - 1)) ^ -1)) == 0) {
                return true;
            }
        }
        return false;
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, boolean z) {
        Throwable th;
        String str2 = str;
        int i2 = i;
        boolean z2 = z;
        if (i2 < 0 || i2 > 15) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i2);
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr3;
        objArr3[2] = Byte.valueOf(z2 ? (byte) 1 : 0);
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STOP, false, 0, 0, "ccc", objArr4), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2, int i3, boolean z) {
        Throwable th;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = z;
        if (i4 < 0 || i4 > 15 || i6 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[7];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i4);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i5));
        Object[] objArr4 = objArr3;
        objArr4[3] = 0;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i6);
        Object[] objArr6 = objArr5;
        objArr6[5] = 0;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr7;
        objArr7[6] = Byte.valueOf((byte) (z2 ? 1 : 0));
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_POWER, false, 0, 0, "ccccccc", objArr8), false);
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i, int i2, int i3, boolean z) {
        Throwable th;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = z;
        if (i4 < 0 || i4 > 15 || i6 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[7];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i4);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i5));
        Object[] objArr4 = objArr3;
        objArr4[3] = 0;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i6);
        Object[] objArr6 = objArr5;
        objArr6[5] = 0;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr7;
        objArr7[6] = Byte.valueOf((byte) (z2 ? 1 : 0));
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SPEED, false, 0, 0, "ccccccc", objArr8), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2, int i3, int i4, boolean z) {
        Throwable th;
        String str2 = str;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z2 = z;
        if (i5 < 0 || i5 > 15 || i7 < -200 || i7 > 200) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[6];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i5);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i6));
        Object[] objArr4 = objArr3;
        objArr4[3] = Short.valueOf((short) i7);
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i8);
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        objArr6[5] = Byte.valueOf((byte) (z2 ? 1 : 0));
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SYNC, false, 0, 0, "cccccc", objArr7), false);
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str, int i, int i2, int i3, boolean z) {
        Throwable th;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        boolean z2 = z;
        if (i4 < 0 || i4 > 15 || i6 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[7];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i4);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i5));
        Object[] objArr4 = objArr3;
        objArr4[3] = 0;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i6);
        Object[] objArr6 = objArr5;
        objArr6[5] = 0;
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr7;
        objArr7[6] = Byte.valueOf((byte) (z2 ? 1 : 0));
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SPEED, false, 0, 0, "ccccccc", objArr8), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2) {
        Throwable th;
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (i3 < 0 || i3 > 15 || i4 < -1 || i4 > 1) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i3);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) i4);
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POLARITY, false, 0, 0, "ccc", objArr3), false);
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i, int i2) {
        Throwable th;
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (i3 < 0 || i3 > 15) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i3);
        Object[] objArr3 = objArr2;
        objArr3[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i4));
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_SPEED, false, 0, 0, "ccc", objArr3), false);
    }

    /* access modifiers changed from: private */
    public int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i) {
        Throwable th;
        int i2;
        Throwable th2;
        String str2 = str;
        int i3 = i;
        if (i3 < 0 || i3 > 15) {
            Throwable th3 = th;
            new IllegalArgumentException();
            throw th3;
        }
        int i4 = i3;
        switch (((i4 ^ (i4 - 1)) + 1) >>> 1) {
            case 1:
                i2 = 0;
                break;
            case 2:
                i2 = 1;
                break;
            case 4:
                i2 = 2;
                break;
            case 8:
                i2 = 3;
                break;
            default:
                Throwable th4 = th2;
                new IllegalArgumentException();
                throw th4;
        }
        Object[] objArr = new Object[3];
        objArr[0] = (byte) 0;
        Object[] objArr2 = objArr;
        objArr2[1] = Byte.valueOf((byte) i2);
        Object[] objArr3 = objArr2;
        objArr3[2] = (byte) 0;
        byte[] sendCommand = sendCommand(str2, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_GET_COUNT, true, 4, 0, "ccg", objArr3), true);
        byte[] bArr = sendCommand;
        if (sendCommand != null && bArr.length == 5 && bArr[0] == 2) {
            return ((Integer) Ev3BinaryParser.unpack("xi", bArr)[0]).intValue();
        }
        return 0;
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase) {
        BluetoothConnectionBase bluetoothConnectionBase2 = bluetoothConnectionBase;
        String str = "beforeDisconnect";
        if (this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA) {
            try {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, true);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, str);
            }
        }
    }
}
