package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zame = false;
    private ArrayList<Integer> zamf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    @KeepForSdk
    public final T get(int i) {
        int i2;
        int intValue;
        int i3 = i;
        zacb();
        int zah = zah(i3);
        if (i3 < 0 || i3 == this.zamf.size()) {
            i2 = 0;
        } else {
            if (i3 == this.zamf.size() - 1) {
                intValue = this.mDataHolder.getCount() - this.zamf.get(i3).intValue();
            } else {
                intValue = this.zamf.get(i3 + 1).intValue() - this.zamf.get(i3).intValue();
            }
            if (intValue == 1) {
                int zah2 = zah(i3);
                int windowIndex = this.mDataHolder.getWindowIndex(zah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                String str = childDataMarkerColumn;
                if (childDataMarkerColumn != null && this.mDataHolder.getString(str, zah2, windowIndex) == null) {
                    i2 = 0;
                }
            }
            i2 = intValue;
        }
        return getEntry(zah, i2);
    }

    @KeepForSdk
    public int getCount() {
        zacb();
        return this.zamf.size();
    }

    /* JADX INFO: finally extract failed */
    private final void zacb() {
        ArrayList<Integer> arrayList;
        Throwable th;
        StringBuilder sb;
        synchronized (this) {
            try {
                if (!this.zame) {
                    int count = this.mDataHolder.getCount();
                    new ArrayList();
                    this.zamf = arrayList;
                    if (count > 0) {
                        boolean add = this.zamf.add(0);
                        String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                        String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                        for (int i = 1; i < count; i++) {
                            int windowIndex = this.mDataHolder.getWindowIndex(i);
                            String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                            String str = string2;
                            if (string2 == null) {
                                Throwable th2 = th;
                                new StringBuilder(78 + String.valueOf(primaryDataMarkerColumn).length());
                                new NullPointerException(sb.append("Missing value for markerColumn: ").append(primaryDataMarkerColumn).append(", at row: ").append(i).append(", for window: ").append(windowIndex).toString());
                                throw th2;
                            }
                            if (!str.equals(string)) {
                                string = str;
                                boolean add2 = this.zamf.add(Integer.valueOf(i));
                            }
                        }
                    }
                    this.zame = true;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                throw th4;
            }
        }
    }

    private final int zah(int i) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        if (i2 >= 0 && i2 < this.zamf.size()) {
            return this.zamf.get(i2).intValue();
        }
        Throwable th2 = th;
        new StringBuilder(53);
        new IllegalArgumentException(sb.append("Position ").append(i2).append(" is out of bounds for this buffer").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }
}
