package android.support.p000v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: android.support.v4.app.INotificationSideChannel */
public interface INotificationSideChannel extends IInterface {
    void cancel(String str, int i, String str2) throws RemoteException;

    void cancelAll(String str) throws RemoteException;

    void notify(String str, int i, String str2, Notification notification) throws RemoteException;

    /* renamed from: android.support.v4.app.INotificationSideChannel$Stub */
    public static abstract class Stub extends Binder implements INotificationSideChannel {
        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationSideChannel asInterface(IBinder iBinder) {
            INotificationSideChannel iNotificationSideChannel;
            IBinder obj = iBinder;
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INotificationSideChannel)) {
                return (INotificationSideChannel) iin;
            }
            new Proxy(obj);
            return iNotificationSideChannel;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Notification _arg3;
            int code = i;
            Parcel data = parcel;
            Parcel reply = parcel2;
            int flags = i2;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    String _arg2 = data.readString();
                    if (0 != data.readInt()) {
                        _arg3 = (Notification) Notification.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    notify(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    cancel(data.readString(), data.readInt(), data.readString());
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    cancelAll(data.readString());
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* renamed from: android.support.v4.app.INotificationSideChannel$Stub$Proxy */
        private static class Proxy implements INotificationSideChannel {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void notify(String str, int i, String str2, Notification notification) throws RemoteException {
                String packageName = str;
                int id = i;
                String tag = str2;
                Notification notification2 = notification;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    if (notification2 != null) {
                        _data.writeInt(1);
                        notification2.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(1, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void cancel(String str, int i, String str2) throws RemoteException {
                String packageName = str;
                int id = i;
                String tag = str2;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    boolean transact = this.mRemote.transact(2, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void cancelAll(String str) throws RemoteException {
                String packageName = str;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean transact = this.mRemote.transact(3, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }
        }
    }
}
