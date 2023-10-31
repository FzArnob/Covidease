package android.support.v13.view;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

public final class DragAndDropPermissionsCompat {
    private Object mDragAndDropPermissions;

    private DragAndDropPermissionsCompat(Object dragAndDropPermissions) {
        this.mDragAndDropPermissions = dragAndDropPermissions;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static DragAndDropPermissionsCompat request(Activity activity, DragEvent dragEvent) {
        DragAndDropPermissions dragAndDropPermissions;
        DragAndDropPermissionsCompat dragAndDropPermissionsCompat;
        Activity activity2 = activity;
        DragEvent dragEvent2 = dragEvent;
        if (Build.VERSION.SDK_INT < 24 || (dragAndDropPermissions = activity2.requestDragAndDropPermissions(dragEvent2)) == null) {
            return null;
        }
        new DragAndDropPermissionsCompat(dragAndDropPermissions);
        return dragAndDropPermissionsCompat;
    }

    public void release() {
        if (Build.VERSION.SDK_INT >= 24) {
            ((DragAndDropPermissions) this.mDragAndDropPermissions).release();
        }
    }
}
