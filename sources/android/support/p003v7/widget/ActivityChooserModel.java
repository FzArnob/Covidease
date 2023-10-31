package android.support.p003v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* renamed from: android.support.v7.widget.ActivityChooserModel */
class ActivityChooserModel extends DataSetObservable {
    static final String ATTRIBUTE_ACTIVITY = "activity";
    static final String ATTRIBUTE_TIME = "time";
    static final String ATTRIBUTE_WEIGHT = "weight";
    static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
    static final String TAG_HISTORICAL_RECORD = "historical-record";
    static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityResolveInfo> mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    boolean mCanReadHistoricalData = true;
    final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged = true;
    final String mHistoryFileName;
    private int mHistoryMaxSize = 50;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled = false;
    private boolean mReloadActivities = false;

    /* renamed from: android.support.v7.widget.ActivityChooserModel$ActivityChooserModelClient */
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$ActivitySorter */
    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$OnChooseActivityListener */
    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    static {
        Object obj;
        Map<String, ActivityChooserModel> map;
        new Object();
        sRegistryLock = obj;
        new HashMap();
        sDataModelRegistry = map;
    }

    /* JADX INFO: finally extract failed */
    public static ActivityChooserModel get(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        Context context2 = context;
        String historyFileName = str;
        Object obj = sRegistryLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ActivityChooserModel dataModel = sDataModelRegistry.get(historyFileName);
                if (dataModel == null) {
                    new ActivityChooserModel(context2, historyFileName);
                    dataModel = activityChooserModel;
                    ActivityChooserModel put = sDataModelRegistry.put(historyFileName, dataModel);
                }
                ActivityChooserModel activityChooserModel2 = dataModel;
                return activityChooserModel2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private ActivityChooserModel(Context context, String str) {
        Object obj;
        List<ActivityResolveInfo> list;
        List<HistoricalRecord> list2;
        ActivitySorter activitySorter;
        StringBuilder sb;
        String historyFileName = str;
        new Object();
        this.mInstanceLock = obj;
        new ArrayList();
        this.mActivities = list;
        new ArrayList();
        this.mHistoricalRecords = list2;
        new DefaultSorter();
        this.mActivitySorter = activitySorter;
        this.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(historyFileName) || historyFileName.endsWith(HISTORY_FILE_EXTENSION)) {
            this.mHistoryFileName = historyFileName;
            return;
        }
        new StringBuilder();
        this.mHistoryFileName = sb.append(historyFileName).append(HISTORY_FILE_EXTENSION).toString();
    }

    /* JADX INFO: finally extract failed */
    public void setIntent(Intent intent) {
        Intent intent2 = intent;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mIntent == intent2) {
                    return;
                }
                this.mIntent = intent2;
                this.mReloadActivities = true;
                ensureConsistentState();
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public Intent getIntent() {
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Intent intent = this.mIntent;
                return intent;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public int getActivityCount() {
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                int size = this.mActivities.size();
                return size;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public ResolveInfo getActivity(int i) {
        int index = i;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                ResolveInfo resolveInfo = this.mActivities.get(index).resolveInfo;
                return resolveInfo;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public int getActivityIndex(ResolveInfo resolveInfo) {
        ResolveInfo activity = resolveInfo;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                List<ActivityResolveInfo> activities = this.mActivities;
                int activityCount = activities.size();
                for (int i = 0; i < activityCount; i++) {
                    if (activities.get(i).resolveInfo == activity) {
                        int i2 = i;
                        return i2;
                    }
                }
                return -1;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public Intent chooseActivity(int i) {
        ComponentName componentName;
        Intent intent;
        HistoricalRecord historicalRecord;
        Intent choiceIntentCopy;
        int index = i;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mIntent == null) {
                    return null;
                }
                ensureConsistentState();
                ActivityResolveInfo chosenActivity = this.mActivities.get(index);
                new ComponentName(chosenActivity.resolveInfo.activityInfo.packageName, chosenActivity.resolveInfo.activityInfo.name);
                ComponentName chosenName = componentName;
                new Intent(this.mIntent);
                Intent choiceIntent = intent;
                Intent component = choiceIntent.setComponent(chosenName);
                if (this.mActivityChoserModelPolicy != null) {
                    new Intent(choiceIntent);
                    if (this.mActivityChoserModelPolicy.onChooseActivity(this, choiceIntentCopy)) {
                        return null;
                    }
                }
                new HistoricalRecord(chosenName, System.currentTimeMillis(), (float) DEFAULT_HISTORICAL_RECORD_WEIGHT);
                boolean addHistoricalRecord = addHistoricalRecord(historicalRecord);
                Intent intent2 = choiceIntent;
                return intent2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        OnChooseActivityListener listener = onChooseActivityListener;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.mActivityChoserModelPolicy = listener;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public ResolveInfo getDefaultActivity() {
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                if (!this.mActivities.isEmpty()) {
                    ResolveInfo resolveInfo = this.mActivities.get(0).resolveInfo;
                    return resolveInfo;
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void setDefaultActivity(int i) {
        float weight;
        ComponentName defaultName;
        HistoricalRecord historicalRecord;
        int index = i;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                ActivityResolveInfo newDefaultActivity = this.mActivities.get(index);
                ActivityResolveInfo oldDefaultActivity = this.mActivities.get(0);
                if (oldDefaultActivity != null) {
                    weight = (oldDefaultActivity.weight - newDefaultActivity.weight) + 5.0f;
                } else {
                    weight = 1.0f;
                }
                new ComponentName(newDefaultActivity.resolveInfo.activityInfo.packageName, newDefaultActivity.resolveInfo.activityInfo.name);
                new HistoricalRecord(defaultName, System.currentTimeMillis(), weight);
                boolean addHistoricalRecord = addHistoricalRecord(historicalRecord);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private void persistHistoricalDataIfNeeded() {
        PersistHistoryAsyncTask persistHistoryAsyncTask;
        Object obj;
        Throwable th;
        if (!this.mReadShareHistoryCalled) {
            Throwable th2 = th;
            new IllegalStateException("No preceding call to #readHistoricalData");
            throw th2;
        } else if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (!TextUtils.isEmpty(this.mHistoryFileName)) {
                PersistHistoryAsyncTask persistHistoryAsyncTask2 = persistHistoryAsyncTask;
                new PersistHistoryAsyncTask(this);
                Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                Object[] objArr = new Object[2];
                new ArrayList(this.mHistoricalRecords);
                objArr[0] = obj;
                Object[] objArr2 = objArr;
                objArr2[1] = this.mHistoryFileName;
                AsyncTask executeOnExecutor = persistHistoryAsyncTask2.executeOnExecutor(executor, objArr2);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void setActivitySorter(ActivitySorter activitySorter) {
        ActivitySorter activitySorter2 = activitySorter;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mActivitySorter == activitySorter2) {
                    return;
                }
                this.mActivitySorter = activitySorter2;
                if (sortActivitiesIfNeeded()) {
                    notifyChanged();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void setHistoryMaxSize(int i) {
        int historyMaxSize = i;
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.mHistoryMaxSize == historyMaxSize) {
                    return;
                }
                this.mHistoryMaxSize = historyMaxSize;
                pruneExcessiveHistoricalRecordsIfNeeded();
                if (sortActivitiesIfNeeded()) {
                    notifyChanged();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public int getHistoryMaxSize() {
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                int i = this.mHistoryMaxSize;
                return i;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public int getHistorySize() {
        Object obj = this.mInstanceLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                ensureConsistentState();
                int size = this.mHistoricalRecords.size();
                return size;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private void ensureConsistentState() {
        boolean stateChanged = loadActivitiesIfNeeded() | readHistoricalDataIfNeeded();
        pruneExcessiveHistoricalRecordsIfNeeded();
        if (stateChanged) {
            boolean sortActivitiesIfNeeded = sortActivitiesIfNeeded();
            notifyChanged();
        }
    }

    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter == null || this.mIntent == null || this.mActivities.isEmpty() || this.mHistoricalRecords.isEmpty()) {
            return false;
        }
        this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
        return true;
    }

    private boolean loadActivitiesIfNeeded() {
        Object obj;
        if (!this.mReloadActivities || this.mIntent == null) {
            return false;
        }
        this.mReloadActivities = false;
        this.mActivities.clear();
        List<ResolveInfo> resolveInfos = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int resolveInfoCount = resolveInfos.size();
        for (int i = 0; i < resolveInfoCount; i++) {
            new ActivityResolveInfo(resolveInfos.get(i));
            boolean add = this.mActivities.add(obj);
        }
        return true;
    }

    private boolean readHistoricalDataIfNeeded() {
        if (!this.mCanReadHistoricalData || !this.mHistoricalRecordsChanged || TextUtils.isEmpty(this.mHistoryFileName)) {
            return false;
        }
        this.mCanReadHistoricalData = false;
        this.mReadShareHistoryCalled = true;
        readHistoricalDataImpl();
        return true;
    }

    private boolean addHistoricalRecord(HistoricalRecord historicalRecord) {
        boolean added = this.mHistoricalRecords.add(historicalRecord);
        if (added) {
            this.mHistoricalRecordsChanged = true;
            pruneExcessiveHistoricalRecordsIfNeeded();
            persistHistoricalDataIfNeeded();
            boolean sortActivitiesIfNeeded = sortActivitiesIfNeeded();
            notifyChanged();
        }
        return added;
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int pruneCount = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (pruneCount > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < pruneCount; i++) {
                HistoricalRecord remove = this.mHistoricalRecords.remove(0);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$HistoricalRecord */
    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public HistoricalRecord(String activityName, long time2, float weight2) {
            this(ComponentName.unflattenFromString(activityName), time2, weight2);
        }

        public HistoricalRecord(ComponentName activityName, long time2, float weight2) {
            this.activity = activityName;
            this.time = time2;
            this.weight = weight2;
        }

        public int hashCode() {
            return (31 * ((31 * ((31 * 1) + (this.activity == null ? 0 : this.activity.hashCode()))) + ((int) (this.time ^ (this.time >>> 32))))) + Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (obj2 == null) {
                return false;
            }
            if (getClass() != obj2.getClass()) {
                return false;
            }
            HistoricalRecord other = (HistoricalRecord) obj2;
            if (this.activity == null) {
                if (other.activity != null) {
                    return false;
                }
            } else if (!this.activity.equals(other.activity)) {
                return false;
            }
            if (this.time != other.time) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb;
            Object obj;
            new StringBuilder();
            StringBuilder builder = sb;
            StringBuilder append = builder.append("[");
            StringBuilder append2 = builder.append("; activity:").append(this.activity);
            StringBuilder append3 = builder.append("; time:").append(this.time);
            new BigDecimal((double) this.weight);
            StringBuilder append4 = builder.append("; weight:").append(obj);
            StringBuilder append5 = builder.append("]");
            return builder.toString();
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$ActivityResolveInfo */
    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo2) {
            this.resolveInfo = resolveInfo2;
        }

        public int hashCode() {
            return 31 + Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (obj2 == null) {
                return false;
            }
            if (getClass() != obj2.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo) obj2).weight)) {
                return false;
            }
            return true;
        }

        public int compareTo(ActivityResolveInfo another) {
            return Float.floatToIntBits(another.weight) - Float.floatToIntBits(this.weight);
        }

        public String toString() {
            StringBuilder sb;
            Object obj;
            new StringBuilder();
            StringBuilder builder = sb;
            StringBuilder append = builder.append("[");
            StringBuilder append2 = builder.append("resolveInfo:").append(this.resolveInfo.toString());
            new BigDecimal((double) this.weight);
            StringBuilder append3 = builder.append("; weight:").append(obj);
            StringBuilder append4 = builder.append("]");
            return builder.toString();
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$DefaultSorter */
    private static final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap;

        DefaultSorter() {
            Map<ComponentName, ActivityResolveInfo> map;
            new HashMap();
            this.mPackageNameToActivityMap = map;
        }

        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Object obj;
            Intent intent2 = intent;
            List<ActivityResolveInfo> activities = list;
            List<HistoricalRecord> historicalRecords = list2;
            Map<ComponentName, ActivityResolveInfo> componentNameToActivityMap = this.mPackageNameToActivityMap;
            componentNameToActivityMap.clear();
            int activityCount = activities.size();
            for (int i = 0; i < activityCount; i++) {
                ActivityResolveInfo activity = activities.get(i);
                activity.weight = 0.0f;
                new ComponentName(activity.resolveInfo.activityInfo.packageName, activity.resolveInfo.activityInfo.name);
                ActivityResolveInfo put = componentNameToActivityMap.put(obj, activity);
            }
            float nextRecordWeight = 1.0f;
            for (int i2 = historicalRecords.size() - 1; i2 >= 0; i2--) {
                HistoricalRecord historicalRecord = historicalRecords.get(i2);
                ActivityResolveInfo activity2 = componentNameToActivityMap.get(historicalRecord.activity);
                if (activity2 != null) {
                    activity2.weight += historicalRecord.weight * nextRecordWeight;
                    nextRecordWeight *= WEIGHT_DECAY_COEFFICIENT;
                }
            }
            Collections.sort(activities);
        }
    }

    private void readHistoricalDataImpl() {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        Object obj;
        Throwable th2;
        FileInputStream fis = null;
        try {
            fis = this.mContext.openFileInput(this.mHistoryFileName);
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(fis, "UTF-8");
                int type = 0;
                while (type != 1 && type != 2) {
                    type = parser.next();
                }
                if (!TAG_HISTORICAL_RECORDS.equals(parser.getName())) {
                    Throwable th3 = th2;
                    new XmlPullParserException("Share records file does not start with historical-records tag.");
                    throw th3;
                }
                List<HistoricalRecord> historicalRecords = this.mHistoricalRecords;
                historicalRecords.clear();
                while (true) {
                    int type2 = parser.next();
                    if (type2 == 1) {
                        if (fis != null) {
                            try {
                                fis.close();
                                return;
                            } catch (IOException e) {
                                IOException iOException = e;
                                return;
                            }
                        } else {
                            return;
                        }
                    } else if (!(type2 == 3 || type2 == 4)) {
                        if (!TAG_HISTORICAL_RECORD.equals(parser.getName())) {
                            Throwable th4 = th;
                            new XmlPullParserException("Share records file not well-formed.");
                            throw th4;
                        }
                        new HistoricalRecord(parser.getAttributeValue((String) null, ATTRIBUTE_ACTIVITY), Long.parseLong(parser.getAttributeValue((String) null, ATTRIBUTE_TIME)), Float.parseFloat(parser.getAttributeValue((String) null, ATTRIBUTE_WEIGHT)));
                        boolean add = historicalRecords.add(obj);
                    }
                }
            } catch (XmlPullParserException e2) {
                XmlPullParserException xppe = e2;
                String str = LOG_TAG;
                new StringBuilder();
                int e3 = Log.e(str, sb2.append("Error reading historical recrod file: ").append(this.mHistoryFileName).toString(), xppe);
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e4) {
                        IOException iOException2 = e4;
                    }
                }
            } catch (IOException e5) {
                IOException ioe = e5;
                String str2 = LOG_TAG;
                new StringBuilder();
                int e6 = Log.e(str2, sb.append("Error reading historical recrod file: ").append(this.mHistoryFileName).toString(), ioe);
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e7) {
                        IOException ioe2 = e7;
                    }
                }
            } catch (Throwable th5) {
                Throwable th6 = th5;
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e8) {
                        IOException iOException3 = e8;
                    }
                }
                throw th6;
            }
        } catch (FileNotFoundException e9) {
            FileNotFoundException fileNotFoundException = e9;
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserModel$PersistHistoryAsyncTask */
    private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ ActivityChooserModel this$0;

        PersistHistoryAsyncTask(ActivityChooserModel activityChooserModel) {
            this.this$0 = activityChooserModel;
        }

        public Void doInBackground(Object... objArr) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            Object[] args = objArr;
            List<HistoricalRecord> historicalRecords = (List) args[0];
            String historyFileName = (String) args[1];
            try {
                FileOutputStream fos = this.this$0.mContext.openFileOutput(historyFileName, 0);
                XmlSerializer serializer = Xml.newSerializer();
                try {
                    serializer.setOutput(fos, (String) null);
                    serializer.startDocument("UTF-8", true);
                    XmlSerializer startTag = serializer.startTag((String) null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                    int recordCount = historicalRecords.size();
                    for (int i = 0; i < recordCount; i++) {
                        HistoricalRecord record = historicalRecords.remove(0);
                        XmlSerializer startTag2 = serializer.startTag((String) null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                        XmlSerializer attribute = serializer.attribute((String) null, ActivityChooserModel.ATTRIBUTE_ACTIVITY, record.activity.flattenToString());
                        XmlSerializer attribute2 = serializer.attribute((String) null, ActivityChooserModel.ATTRIBUTE_TIME, String.valueOf(record.time));
                        XmlSerializer attribute3 = serializer.attribute((String) null, ActivityChooserModel.ATTRIBUTE_WEIGHT, String.valueOf(record.weight));
                        XmlSerializer endTag = serializer.endTag((String) null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                    }
                    XmlSerializer endTag2 = serializer.endTag((String) null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                    serializer.endDocument();
                    this.this$0.mCanReadHistoricalData = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            IOException iOException = e;
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    IllegalArgumentException iae = e2;
                    String str = ActivityChooserModel.LOG_TAG;
                    new StringBuilder();
                    int e3 = Log.e(str, sb4.append("Error writing historical record file: ").append(this.this$0.mHistoryFileName).toString(), iae);
                    this.this$0.mCanReadHistoricalData = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e4) {
                            IOException iOException2 = e4;
                        }
                    }
                } catch (IllegalStateException e5) {
                    IllegalStateException ise = e5;
                    String str2 = ActivityChooserModel.LOG_TAG;
                    new StringBuilder();
                    int e6 = Log.e(str2, sb3.append("Error writing historical record file: ").append(this.this$0.mHistoryFileName).toString(), ise);
                    this.this$0.mCanReadHistoricalData = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e7) {
                            IOException iOException3 = e7;
                        }
                    }
                } catch (IOException e8) {
                    IOException ioe = e8;
                    String str3 = ActivityChooserModel.LOG_TAG;
                    new StringBuilder();
                    int e9 = Log.e(str3, sb2.append("Error writing historical record file: ").append(this.this$0.mHistoryFileName).toString(), ioe);
                    this.this$0.mCanReadHistoricalData = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e10) {
                            IOException ioe2 = e10;
                        }
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this.this$0.mCanReadHistoricalData = true;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e11) {
                            IOException iOException4 = e11;
                        }
                    }
                    throw th2;
                }
                return null;
            } catch (FileNotFoundException e12) {
                FileNotFoundException fnfe = e12;
                String str4 = ActivityChooserModel.LOG_TAG;
                new StringBuilder();
                int e13 = Log.e(str4, sb.append("Error writing historical record file: ").append(historyFileName).toString(), fnfe);
                return null;
            }
        }
    }
}
