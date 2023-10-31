package android.support.design.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.support.p000v4.view.AbsSavedState;

public class ExtendableSavedState extends AbsSavedState {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR;
    public final C1650SimpleArrayMap<String, Bundle> extendableStates;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ ExtendableSavedState(Parcel x0, ClassLoader x1, C00931 r10) {
        this(x0, x1);
        C00931 r3 = r10;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtendableSavedState(Parcelable superState) {
        super(superState);
        C1650SimpleArrayMap<String, Bundle> simpleArrayMap;
        new C1650SimpleArrayMap<>();
        this.extendableStates = simpleArrayMap;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ExtendableSavedState(android.os.Parcel r13, java.lang.ClassLoader r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r7 = r0
            r8 = r1
            r9 = r2
            r7.<init>(r8, r9)
            r7 = r1
            int r7 = r7.readInt()
            r3 = r7
            r7 = r3
            java.lang.String[] r7 = new java.lang.String[r7]
            r4 = r7
            r7 = r1
            r8 = r4
            r7.readStringArray(r8)
            r7 = r3
            android.os.Bundle[] r7 = new android.os.Bundle[r7]
            r5 = r7
            r7 = r1
            r8 = r5
            android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
            r7.readTypedArray(r8, r9)
            r7 = r0
            android.support.v4.util.SimpleArrayMap r8 = new android.support.v4.util.SimpleArrayMap
            r11 = r8
            r8 = r11
            r9 = r11
            r10 = r3
            r9.<init>((int) r10)
            r7.extendableStates = r8
            r7 = 0
            r6 = r7
        L_0x0031:
            r7 = r6
            r8 = r3
            if (r7 >= r8) goto L_0x0047
            r7 = r0
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.os.Bundle> r7 = r7.extendableStates
            r8 = r4
            r9 = r6
            r8 = r8[r9]
            r9 = r5
            r10 = r6
            r9 = r9[r10]
            java.lang.Object r7 = r7.put(r8, r9)
            int r6 = r6 + 1
            goto L_0x0031
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.stateful.ExtendableSavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
    }

    public void writeToParcel(Parcel parcel, int flags) {
        Parcel out = parcel;
        super.writeToParcel(out, flags);
        int size = this.extendableStates.size();
        out.writeInt(size);
        String[] keys = new String[size];
        Bundle[] states = new Bundle[size];
        for (int i = 0; i < size; i++) {
            keys[i] = this.extendableStates.keyAt(i);
            states[i] = this.extendableStates.valueAt(i);
        }
        out.writeStringArray(keys);
        out.writeTypedArray(states, 0);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ExtendableSavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" states=").append(this.extendableStates).append("}").toString();
    }

    static {
        Parcelable.Creator<ExtendableSavedState> creator;
        new Parcelable.ClassLoaderCreator<ExtendableSavedState>() {
            public ExtendableSavedState createFromParcel(Parcel in, ClassLoader loader) {
                ExtendableSavedState extendableSavedState;
                new ExtendableSavedState(in, loader, (C00931) null);
                return extendableSavedState;
            }

            public ExtendableSavedState createFromParcel(Parcel in) {
                ExtendableSavedState extendableSavedState;
                new ExtendableSavedState(in, (ClassLoader) null, (C00931) null);
                return extendableSavedState;
            }

            public ExtendableSavedState[] newArray(int size) {
                return new ExtendableSavedState[size];
            }
        };
        CREATOR = creator;
    }
}
