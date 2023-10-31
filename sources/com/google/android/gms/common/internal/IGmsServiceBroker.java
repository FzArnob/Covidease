package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface IGmsServiceBroker extends IInterface {
    @KeepForSdk
    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException;

    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        private static class zza implements IGmsServiceBroker {
            private final IBinder zza;

            zza(IBinder iBinder) {
                this.zza = iBinder;
            }

            public final IBinder asBinder() {
                return this.zza;
            }

            public final void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException {
                IGmsCallbacks iGmsCallbacks2 = iGmsCallbacks;
                GetServiceRequest getServiceRequest2 = getServiceRequest;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(iGmsCallbacks2 != null ? iGmsCallbacks2.asBinder() : null);
                    if (getServiceRequest2 != null) {
                        obtain.writeInt(1);
                        getServiceRequest2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    boolean transact = this.zza.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }
        }

        @KeepForSdk
        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IGmsCallbacks iGmsCallbacks;
            IGmsCallbacks iGmsCallbacks2;
            Throwable th;
            Throwable th2;
            int i3 = i;
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i4 = i2;
            if (i3 > 16777215) {
                return super.onTransact(i3, parcel3, parcel4, i4);
            }
            parcel3.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder readStrongBinder = parcel3.readStrongBinder();
            IBinder iBinder = readStrongBinder;
            if (readStrongBinder == null) {
                iGmsCallbacks2 = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                IInterface iInterface = queryLocalInterface;
                if (queryLocalInterface instanceof IGmsCallbacks) {
                    iGmsCallbacks2 = (IGmsCallbacks) iInterface;
                } else {
                    iGmsCallbacks2 = iGmsCallbacks;
                    new zzl(iBinder);
                }
            }
            IGmsCallbacks iGmsCallbacks3 = iGmsCallbacks2;
            if (i3 == 46) {
                GetServiceRequest getServiceRequest = null;
                if (0 != parcel3.readInt()) {
                    getServiceRequest = GetServiceRequest.CREATOR.createFromParcel(parcel3);
                }
                getService(iGmsCallbacks3, getServiceRequest);
                parcel4.writeNoException();
                return true;
            } else if (i3 == 47) {
                if (0 != parcel3.readInt()) {
                    zzr createFromParcel = zzr.CREATOR.createFromParcel(parcel3);
                }
                Throwable th3 = th2;
                new UnsupportedOperationException();
                throw th3;
            } else {
                int readInt = parcel3.readInt();
                if (i3 != 4) {
                    String readString = parcel3.readString();
                }
                switch (i3) {
                    case 1:
                        String readString2 = parcel3.readString();
                        String[] createStringArray = parcel3.createStringArray();
                        String readString3 = parcel3.readString();
                        if (0 != parcel3.readInt()) {
                            Object createFromParcel2 = Bundle.CREATOR.createFromParcel(parcel3);
                            break;
                        }
                        break;
                    case 2:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 23:
                    case 25:
                    case 27:
                    case 37:
                    case 38:
                    case 41:
                    case 43:
                        if (0 != parcel3.readInt()) {
                            Object createFromParcel3 = Bundle.CREATOR.createFromParcel(parcel3);
                            break;
                        }
                        break;
                    case 9:
                        String readString4 = parcel3.readString();
                        String[] createStringArray2 = parcel3.createStringArray();
                        String readString5 = parcel3.readString();
                        IBinder readStrongBinder2 = parcel3.readStrongBinder();
                        String readString6 = parcel3.readString();
                        if (0 != parcel3.readInt()) {
                            Object createFromParcel4 = Bundle.CREATOR.createFromParcel(parcel3);
                            break;
                        }
                        break;
                    case 10:
                        String readString7 = parcel3.readString();
                        String[] createStringArray3 = parcel3.createStringArray();
                        break;
                    case 19:
                        IBinder readStrongBinder3 = parcel3.readStrongBinder();
                        if (0 != parcel3.readInt()) {
                            Object createFromParcel5 = Bundle.CREATOR.createFromParcel(parcel3);
                            break;
                        }
                        break;
                    case 20:
                    case 30:
                        String[] createStringArray4 = parcel3.createStringArray();
                        String readString8 = parcel3.readString();
                        if (0 != parcel3.readInt()) {
                            Object createFromParcel6 = Bundle.CREATOR.createFromParcel(parcel3);
                            break;
                        }
                        break;
                    case 34:
                        String readString9 = parcel3.readString();
                        break;
                }
                Throwable th4 = th;
                new UnsupportedOperationException();
                throw th4;
            }
        }
    }
}
