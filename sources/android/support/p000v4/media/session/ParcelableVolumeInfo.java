package android.support.p000v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.session.ParcelableVolumeInfo */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR;
    public int audioStream;
    public int controlType;
    public int currentVolume;
    public int maxVolume;
    public int volumeType;

    public ParcelableVolumeInfo(int volumeType2, int audioStream2, int controlType2, int maxVolume2, int currentVolume2) {
        this.volumeType = volumeType2;
        this.audioStream = audioStream2;
        this.controlType = controlType2;
        this.maxVolume = maxVolume2;
        this.currentVolume = currentVolume2;
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        Parcel from = parcel;
        this.volumeType = from.readInt();
        this.controlType = from.readInt();
        this.maxVolume = from.readInt();
        this.currentVolume = from.readInt();
        this.audioStream = from.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel dest = parcel;
        int i2 = i;
        dest.writeInt(this.volumeType);
        dest.writeInt(this.controlType);
        dest.writeInt(this.maxVolume);
        dest.writeInt(this.currentVolume);
        dest.writeInt(this.audioStream);
    }

    static {
        Parcelable.Creator<ParcelableVolumeInfo> creator;
        new Parcelable.Creator<ParcelableVolumeInfo>() {
            public ParcelableVolumeInfo createFromParcel(Parcel in) {
                ParcelableVolumeInfo parcelableVolumeInfo;
                new ParcelableVolumeInfo(in);
                return parcelableVolumeInfo;
            }

            public ParcelableVolumeInfo[] newArray(int size) {
                return new ParcelableVolumeInfo[size];
            }
        };
        CREATOR = creator;
    }
}
