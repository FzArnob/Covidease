package android.support.p000v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: android.support.v4.app.TaskStackBuilder */
public final class TaskStackBuilder implements Iterable<Intent> {
    private static final String TAG = "TaskStackBuilder";
    private final ArrayList<Intent> mIntents;
    private final Context mSourceContext;

    /* renamed from: android.support.v4.app.TaskStackBuilder$SupportParentable */
    public interface SupportParentable {
        @Nullable
        Intent getSupportParentActivityIntent();
    }

    private TaskStackBuilder(Context a) {
        ArrayList<Intent> arrayList;
        new ArrayList<>();
        this.mIntents = arrayList;
        this.mSourceContext = a;
    }

    @NonNull
    public static TaskStackBuilder create(@NonNull Context context) {
        TaskStackBuilder taskStackBuilder;
        new TaskStackBuilder(context);
        return taskStackBuilder;
    }

    @Deprecated
    public static TaskStackBuilder from(Context context) {
        return create(context);
    }

    @NonNull
    public TaskStackBuilder addNextIntent(@NonNull Intent nextIntent) {
        boolean add = this.mIntents.add(nextIntent);
        return this;
    }

    @NonNull
    public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent intent) {
        Intent nextIntent = intent;
        ComponentName target = nextIntent.getComponent();
        if (target == null) {
            target = nextIntent.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if (target != null) {
            TaskStackBuilder addParentStack = addParentStack(target);
        }
        TaskStackBuilder addNextIntent = addNextIntent(nextIntent);
        return this;
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Activity activity) {
        Activity sourceActivity = activity;
        Intent parent = null;
        if (sourceActivity instanceof SupportParentable) {
            parent = ((SupportParentable) sourceActivity).getSupportParentActivityIntent();
        }
        if (parent == null) {
            parent = NavUtils.getParentActivityIntent(sourceActivity);
        }
        if (parent != null) {
            ComponentName target = parent.getComponent();
            if (target == null) {
                target = parent.resolveActivity(this.mSourceContext.getPackageManager());
            }
            TaskStackBuilder addParentStack = addParentStack(target);
            TaskStackBuilder addNextIntent = addNextIntent(parent);
        }
        return this;
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Class<?> sourceActivityClass) {
        ComponentName componentName;
        new ComponentName(this.mSourceContext, sourceActivityClass);
        return addParentStack(componentName);
    }

    public TaskStackBuilder addParentStack(ComponentName componentName) {
        Throwable th;
        ComponentName sourceActivityName = componentName;
        int insertAt = this.mIntents.size();
        try {
            Intent parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, sourceActivityName);
            while (true) {
                Intent parent = parentActivityIntent;
                if (parent != null) {
                    this.mIntents.add(insertAt, parent);
                    parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, parent.getComponent());
                } else {
                    return this;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException e2 = e;
            int e3 = Log.e(TAG, "Bad ComponentName while traversing activity parent metadata");
            Throwable th2 = th;
            new IllegalArgumentException(e2);
            throw th2;
        }
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    @Deprecated
    public Intent getIntent(int index) {
        return editIntentAt(index);
    }

    @Nullable
    public Intent editIntentAt(int index) {
        return this.mIntents.get(index);
    }

    @Deprecated
    public Iterator<Intent> iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        startActivities((Bundle) null);
    }

    public void startActivities(@Nullable Bundle bundle) {
        Intent intent;
        Intent intent2;
        Throwable th;
        Bundle options = bundle;
        if (this.mIntents.isEmpty()) {
            Throwable th2 = th;
            new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            throw th2;
        }
        Intent[] intents = (Intent[]) this.mIntents.toArray(new Intent[this.mIntents.size()]);
        new Intent(intents[0]);
        intents[0] = intent.addFlags(268484608);
        if (!ContextCompat.startActivities(this.mSourceContext, intents, options)) {
            new Intent(intents[intents.length - 1]);
            Intent topIntent = intent2;
            Intent addFlags = topIntent.addFlags(Declaration.IS_DYNAMIC);
            this.mSourceContext.startActivity(topIntent);
        }
    }

    @Nullable
    public PendingIntent getPendingIntent(int requestCode, int flags) {
        return getPendingIntent(requestCode, flags, (Bundle) null);
    }

    @Nullable
    public PendingIntent getPendingIntent(int i, int i2, @Nullable Bundle bundle) {
        Intent intent;
        Throwable th;
        int requestCode = i;
        int flags = i2;
        Bundle options = bundle;
        if (this.mIntents.isEmpty()) {
            Throwable th2 = th;
            new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
            throw th2;
        }
        Intent[] intents = (Intent[]) this.mIntents.toArray(new Intent[this.mIntents.size()]);
        new Intent(intents[0]);
        intents[0] = intent.addFlags(268484608);
        if (Build.VERSION.SDK_INT >= 16) {
            return PendingIntent.getActivities(this.mSourceContext, requestCode, intents, flags, options);
        }
        return PendingIntent.getActivities(this.mSourceContext, requestCode, intents, flags);
    }

    @NonNull
    public Intent[] getIntents() {
        Intent intent;
        Intent intent2;
        Intent[] intents = new Intent[this.mIntents.size()];
        if (intents.length == 0) {
            return intents;
        }
        new Intent(this.mIntents.get(0));
        intents[0] = intent.addFlags(268484608);
        for (int i = 1; i < intents.length; i++) {
            new Intent(this.mIntents.get(i));
            intents[i] = intent2;
        }
        return intents;
    }
}
