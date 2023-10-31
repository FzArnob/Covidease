package android.support.p000v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import java.util.List;

/* renamed from: android.support.v4.media.session.IMediaControllerCallback */
public interface IMediaControllerCallback extends IInterface {
    void onCaptioningEnabledChanged(boolean z) throws RemoteException;

    void onEvent(String str, Bundle bundle) throws RemoteException;

    void onExtrasChanged(Bundle bundle) throws RemoteException;

    void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException;

    void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException;

    void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException;

    void onQueueTitleChanged(CharSequence charSequence) throws RemoteException;

    void onRepeatModeChanged(int i) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onSessionReady() throws RemoteException;

    void onShuffleModeChanged(int i) throws RemoteException;

    void onShuffleModeChangedRemoved(boolean z) throws RemoteException;

    void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException;

    /* renamed from: android.support.v4.media.session.IMediaControllerCallback$Stub */
    public static abstract class Stub extends Binder implements IMediaControllerCallback {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onCaptioningEnabledChanged = 11;
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onRepeatModeChanged = 9;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onSessionReady = 13;
        static final int TRANSACTION_onShuffleModeChanged = 12;
        static final int TRANSACTION_onShuffleModeChangedRemoved = 10;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerCallback asInterface(IBinder iBinder) {
            IMediaControllerCallback iMediaControllerCallback;
            IBinder obj = iBinder;
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IMediaControllerCallback)) {
                return (IMediaControllerCallback) iin;
            }
            new Proxy(obj);
            return iMediaControllerCallback;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ParcelableVolumeInfo _arg0;
            Bundle _arg02;
            CharSequence _arg03;
            MediaMetadataCompat _arg04;
            PlaybackStateCompat _arg05;
            Bundle _arg1;
            int code = i;
            Parcel data = parcel;
            Parcel reply = parcel2;
            int flags = i2;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    if (0 != data.readInt()) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onEvent(_arg06, _arg1);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onSessionDestroyed();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg05 = PlaybackStateCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    onPlaybackStateChanged(_arg05);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg04 = MediaMetadataCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    onMetadataChanged(_arg04);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onQueueChanged(data.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg03 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    onQueueTitleChanged(_arg03);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg02 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    onExtrasChanged(_arg02);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg0 = ParcelableVolumeInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onVolumeInfoChanged(_arg0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onRepeatModeChanged(data.readInt());
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    onShuffleModeChangedRemoved(0 != data.readInt());
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    onCaptioningEnabledChanged(0 != data.readInt());
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    onShuffleModeChanged(data.readInt());
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    onSessionReady();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* renamed from: android.support.v4.media.session.IMediaControllerCallback$Stub$Proxy */
        private static class Proxy implements IMediaControllerCallback {
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

            public void onEvent(String str, Bundle bundle) throws RemoteException {
                String event = str;
                Bundle extras = bundle;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(event);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
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

            public void onSessionDestroyed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean transact = this.mRemote.transact(2, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                PlaybackStateCompat state = playbackStateCompat;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(3, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                MediaMetadataCompat metadata = mediaMetadataCompat;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(4, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                List<MediaSessionCompat.QueueItem> queue = list;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(queue);
                    boolean transact = this.mRemote.transact(5, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                CharSequence title = charSequence;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (title != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(title, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(6, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Bundle extras = bundle;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(7, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                ParcelableVolumeInfo info = parcelableVolumeInfo;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean transact = this.mRemote.transact(8, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onRepeatModeChanged(int i) throws RemoteException {
                int repeatMode = i;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(repeatMode);
                    boolean transact = this.mRemote.transact(9, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onShuffleModeChangedRemoved(boolean z) throws RemoteException {
                boolean enabled = z;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean transact = this.mRemote.transact(10, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
                boolean enabled = z;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean transact = this.mRemote.transact(11, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onShuffleModeChanged(int i) throws RemoteException {
                int shuffleMode = i;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(shuffleMode);
                    boolean transact = this.mRemote.transact(12, _data, (Parcel) null, 1);
                    _data.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _data.recycle();
                    throw th2;
                }
            }

            public void onSessionReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean transact = this.mRemote.transact(13, _data, (Parcel) null, 1);
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
