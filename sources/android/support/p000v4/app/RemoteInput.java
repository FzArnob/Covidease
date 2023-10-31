package android.support.p000v4.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: android.support.v4.app.RemoteInput */
public final class RemoteInput {
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormTextInput;
    private final Set<String> mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    RemoteInput(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormTextInput, Bundle extras, Set<String> allowedDataTypes) {
        this.mResultKey = resultKey;
        this.mLabel = label;
        this.mChoices = choices;
        this.mAllowFreeFormTextInput = allowFreeFormTextInput;
        this.mExtras = extras;
        this.mAllowedDataTypes = allowedDataTypes;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public Set<String> getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public boolean isDataOnly() {
        return !getAllowFreeFormInput() && (getChoices() == null || getChoices().length == 0) && getAllowedDataTypes() != null && !getAllowedDataTypes().isEmpty();
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    /* renamed from: android.support.v4.app.RemoteInput$Builder */
    public static final class Builder {
        private boolean mAllowFreeFormTextInput = true;
        private final Set<String> mAllowedDataTypes;
        private CharSequence[] mChoices;
        private final Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(@NonNull String str) {
            Set<String> set;
            Bundle bundle;
            Throwable th;
            String resultKey = str;
            new HashSet();
            this.mAllowedDataTypes = set;
            new Bundle();
            this.mExtras = bundle;
            if (resultKey == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Result key can't be null");
                throw th2;
            }
            this.mResultKey = resultKey;
        }

        @NonNull
        public Builder setLabel(@Nullable CharSequence label) {
            this.mLabel = label;
            return this;
        }

        @NonNull
        public Builder setChoices(@Nullable CharSequence[] choices) {
            this.mChoices = choices;
            return this;
        }

        @NonNull
        public Builder setAllowDataType(@NonNull String str, boolean doAllow) {
            String mimeType = str;
            if (doAllow) {
                boolean add = this.mAllowedDataTypes.add(mimeType);
            } else {
                boolean remove = this.mAllowedDataTypes.remove(mimeType);
            }
            return this;
        }

        @NonNull
        public Builder setAllowFreeFormInput(boolean allowFreeFormTextInput) {
            this.mAllowFreeFormTextInput = allowFreeFormTextInput;
            return this;
        }

        @NonNull
        public Builder addExtras(@NonNull Bundle bundle) {
            Bundle extras = bundle;
            if (extras != null) {
                this.mExtras.putAll(extras);
            }
            return this;
        }

        @NonNull
        public Bundle getExtras() {
            return this.mExtras;
        }

        @NonNull
        public RemoteInput build() {
            RemoteInput remoteInput;
            new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
            return remoteInput;
        }
    }

    public static Map<String, Uri> getDataResultsFromIntent(Intent intent, String str) {
        Map<String, Uri> map;
        String uriStr;
        Intent intent2 = intent;
        String remoteInputResultKey = str;
        if (Build.VERSION.SDK_INT >= 26) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent2, remoteInputResultKey);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            Intent clipDataIntent = getClipDataIntentFromIntent(intent2);
            if (clipDataIntent == null) {
                return null;
            }
            new HashMap();
            Map<String, Uri> results = map;
            for (String key : clipDataIntent.getExtras().keySet()) {
                if (key.startsWith(EXTRA_DATA_TYPE_RESULTS_DATA)) {
                    String mimeType = key.substring(EXTRA_DATA_TYPE_RESULTS_DATA.length());
                    if (!mimeType.isEmpty() && (uriStr = clipDataIntent.getBundleExtra(key).getString(remoteInputResultKey)) != null && !uriStr.isEmpty()) {
                        Uri put = results.put(mimeType, Uri.parse(uriStr));
                    }
                }
            }
            return results.isEmpty() ? null : results;
        }
        int w = Log.w(TAG, "RemoteInput is only supported from API Level 16");
        return null;
    }

    public static Bundle getResultsFromIntent(Intent intent) {
        Intent intent2 = intent;
        if (Build.VERSION.SDK_INT >= 20) {
            return android.app.RemoteInput.getResultsFromIntent(intent2);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            Intent clipDataIntent = getClipDataIntentFromIntent(intent2);
            if (clipDataIntent == null) {
                return null;
            }
            return (Bundle) clipDataIntent.getExtras().getParcelable(EXTRA_RESULTS_DATA);
        }
        int w = Log.w(TAG, "RemoteInput is only supported from API Level 16");
        return null;
    }

    public static void addResultsToIntent(RemoteInput[] remoteInputArr, Intent intent, Bundle bundle) {
        Bundle bundle2;
        Intent intent2;
        RemoteInput[] remoteInputs = remoteInputArr;
        Intent intent3 = intent;
        Bundle results = bundle;
        if (Build.VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addResultsToIntent(fromCompat(remoteInputs), intent3, results);
        } else if (Build.VERSION.SDK_INT >= 20) {
            Bundle existingTextResults = getResultsFromIntent(intent3);
            if (existingTextResults == null) {
                existingTextResults = results;
            } else {
                existingTextResults.putAll(results);
            }
            RemoteInput[] remoteInputArr2 = remoteInputs;
            int length = remoteInputArr2.length;
            for (int i = 0; i < length; i++) {
                RemoteInput input = remoteInputArr2[i];
                Map<String, Uri> existingDataResults = getDataResultsFromIntent(intent3, input.getResultKey());
                android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[]{input}), intent3, existingTextResults);
                if (existingDataResults != null) {
                    addDataResultToIntent(input, intent3, existingDataResults);
                }
            }
        } else if (Build.VERSION.SDK_INT >= 16) {
            Intent clipDataIntent = getClipDataIntentFromIntent(intent3);
            if (clipDataIntent == null) {
                new Intent();
                clipDataIntent = intent2;
            }
            Bundle resultsBundle = clipDataIntent.getBundleExtra(EXTRA_RESULTS_DATA);
            if (resultsBundle == null) {
                new Bundle();
                resultsBundle = bundle2;
            }
            RemoteInput[] remoteInputArr3 = remoteInputs;
            int length2 = remoteInputArr3.length;
            for (int i2 = 0; i2 < length2; i2++) {
                RemoteInput remoteInput = remoteInputArr3[i2];
                Object result = results.get(remoteInput.getResultKey());
                if (result instanceof CharSequence) {
                    resultsBundle.putCharSequence(remoteInput.getResultKey(), (CharSequence) result);
                }
            }
            Intent putExtra = clipDataIntent.putExtra(EXTRA_RESULTS_DATA, resultsBundle);
            intent3.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntent));
        } else {
            int w = Log.w(TAG, "RemoteInput is only supported from API Level 16");
        }
    }

    public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map<String, Uri> map) {
        Bundle bundle;
        Intent intent2;
        RemoteInput remoteInput2 = remoteInput;
        Intent intent3 = intent;
        Map<String, Uri> results = map;
        if (Build.VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addDataResultToIntent(fromCompat(remoteInput2), intent3, results);
        } else if (Build.VERSION.SDK_INT >= 16) {
            Intent clipDataIntent = getClipDataIntentFromIntent(intent3);
            if (clipDataIntent == null) {
                new Intent();
                clipDataIntent = intent2;
            }
            for (Map.Entry<String, Uri> entry : results.entrySet()) {
                String mimeType = entry.getKey();
                Uri uri = entry.getValue();
                if (mimeType != null) {
                    Bundle resultsBundle = clipDataIntent.getBundleExtra(getExtraResultsKeyForData(mimeType));
                    if (resultsBundle == null) {
                        new Bundle();
                        resultsBundle = bundle;
                    }
                    resultsBundle.putString(remoteInput2.getResultKey(), uri.toString());
                    Intent putExtra = clipDataIntent.putExtra(getExtraResultsKeyForData(mimeType), resultsBundle);
                }
            }
            intent3.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntent));
        } else {
            int w = Log.w(TAG, "RemoteInput is only supported from API Level 16");
        }
    }

    private static String getExtraResultsKeyForData(String mimeType) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(EXTRA_DATA_TYPE_RESULTS_DATA).append(mimeType).toString();
    }

    @RequiresApi(20)
    static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        RemoteInput[] srcArray = remoteInputArr;
        if (srcArray == null) {
            return null;
        }
        android.app.RemoteInput[] result = new android.app.RemoteInput[srcArray.length];
        for (int i = 0; i < srcArray.length; i++) {
            result[i] = fromCompat(srcArray[i]);
        }
        return result;
    }

    @RequiresApi(20)
    static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        RemoteInput.Builder builder;
        RemoteInput src = remoteInput;
        new RemoteInput.Builder(src.getResultKey());
        return builder.setLabel(src.getLabel()).setChoices(src.getChoices()).setAllowFreeFormInput(src.getAllowFreeFormInput()).addExtras(src.getExtras()).build();
    }

    @RequiresApi(16)
    private static Intent getClipDataIntentFromIntent(Intent intent) {
        ClipData clipData = intent.getClipData();
        if (clipData == null) {
            return null;
        }
        ClipDescription clipDescription = clipData.getDescription();
        if (!clipDescription.hasMimeType("text/vnd.android.intent")) {
            return null;
        }
        if (!clipDescription.getLabel().equals(RESULTS_CLIP_LABEL)) {
            return null;
        }
        return clipData.getItemAt(0).getIntent();
    }
}
