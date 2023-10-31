package android.support.p000v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.p000v4.content.IntentCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.ShareCompat */
public final class ShareCompat {
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    private ShareCompat() {
    }

    public static String getCallingPackage(Activity activity) {
        Activity calledActivity = activity;
        String result = calledActivity.getCallingPackage();
        if (result == null) {
            result = calledActivity.getIntent().getStringExtra(EXTRA_CALLING_PACKAGE);
        }
        return result;
    }

    public static ComponentName getCallingActivity(Activity activity) {
        Activity calledActivity = activity;
        ComponentName result = calledActivity.getCallingActivity();
        if (result == null) {
            result = (ComponentName) calledActivity.getIntent().getParcelableExtra(EXTRA_CALLING_ACTIVITY);
        }
        return result;
    }

    public static void configureMenuItem(MenuItem menuItem, IntentBuilder intentBuilder) {
        ShareActionProvider provider;
        StringBuilder sb;
        ShareActionProvider shareActionProvider;
        MenuItem item = menuItem;
        IntentBuilder shareIntent = intentBuilder;
        ActionProvider itemProvider = item.getActionProvider();
        if (!(itemProvider instanceof ShareActionProvider)) {
            new ShareActionProvider(shareIntent.getActivity());
            provider = shareActionProvider;
        } else {
            provider = (ShareActionProvider) itemProvider;
        }
        new StringBuilder();
        provider.setShareHistoryFileName(sb.append(HISTORY_FILENAME_PREFIX).append(shareIntent.getActivity().getClass().getName()).toString());
        provider.setShareIntent(shareIntent.getIntent());
        MenuItem actionProvider = item.setActionProvider(provider);
        if (Build.VERSION.SDK_INT < 16 && !item.hasSubMenu()) {
            MenuItem intent = item.setIntent(shareIntent.createChooserIntent());
        }
    }

    public static void configureMenuItem(Menu menu, int i, IntentBuilder intentBuilder) {
        Throwable th;
        StringBuilder sb;
        int menuItemId = i;
        IntentBuilder shareIntent = intentBuilder;
        MenuItem item = menu.findItem(menuItemId);
        if (item == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Could not find menu item with id ").append(menuItemId).append(" in the supplied menu").toString());
            throw th2;
        }
        configureMenuItem(item, shareIntent);
    }

    /* renamed from: android.support.v4.app.ShareCompat$IntentBuilder */
    public static class IntentBuilder {
        private Activity mActivity;
        private ArrayList<String> mBccAddresses;
        private ArrayList<String> mCcAddresses;
        private CharSequence mChooserTitle;
        private Intent mIntent;
        private ArrayList<Uri> mStreams;
        private ArrayList<String> mToAddresses;

        public static IntentBuilder from(Activity launchingActivity) {
            IntentBuilder intentBuilder;
            new IntentBuilder(launchingActivity);
            return intentBuilder;
        }

        private IntentBuilder(Activity activity) {
            Intent intent;
            Activity launchingActivity = activity;
            this.mActivity = launchingActivity;
            new Intent();
            this.mIntent = intent.setAction("android.intent.action.SEND");
            Intent putExtra = this.mIntent.putExtra(ShareCompat.EXTRA_CALLING_PACKAGE, launchingActivity.getPackageName());
            Intent putExtra2 = this.mIntent.putExtra(ShareCompat.EXTRA_CALLING_ACTIVITY, launchingActivity.getComponentName());
            Intent addFlags = this.mIntent.addFlags(524288);
        }

        public Intent getIntent() {
            if (this.mToAddresses != null) {
                combineArrayExtra("android.intent.extra.EMAIL", this.mToAddresses);
                this.mToAddresses = null;
            }
            if (this.mCcAddresses != null) {
                combineArrayExtra("android.intent.extra.CC", this.mCcAddresses);
                this.mCcAddresses = null;
            }
            if (this.mBccAddresses != null) {
                combineArrayExtra("android.intent.extra.BCC", this.mBccAddresses);
                this.mBccAddresses = null;
            }
            boolean needsSendMultiple = this.mStreams != null && this.mStreams.size() > 1;
            boolean isSendMultiple = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if (!needsSendMultiple && isSendMultiple) {
                Intent action = this.mIntent.setAction("android.intent.action.SEND");
                if (this.mStreams == null || this.mStreams.isEmpty()) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                } else {
                    Intent putExtra = this.mIntent.putExtra("android.intent.extra.STREAM", this.mStreams.get(0));
                }
                this.mStreams = null;
            }
            if (needsSendMultiple && !isSendMultiple) {
                Intent action2 = this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.mStreams == null || this.mStreams.isEmpty()) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                } else {
                    Intent putParcelableArrayListExtra = this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
                }
            }
            return this.mIntent;
        }

        /* access modifiers changed from: package-private */
        public Activity getActivity() {
            return this.mActivity;
        }

        private void combineArrayExtra(String str, ArrayList<String> arrayList) {
            String extra = str;
            ArrayList<String> add = arrayList;
            String[] currentAddresses = this.mIntent.getStringArrayExtra(extra);
            int currentLength = currentAddresses != null ? currentAddresses.length : 0;
            String[] finalAddresses = new String[(currentLength + add.size())];
            Object[] array = add.toArray(finalAddresses);
            if (currentAddresses != null) {
                System.arraycopy(currentAddresses, 0, finalAddresses, add.size(), currentLength);
            }
            Intent putExtra = this.mIntent.putExtra(extra, finalAddresses);
        }

        private void combineArrayExtra(String str, String[] strArr) {
            String extra = str;
            String[] add = strArr;
            Intent intent = getIntent();
            String[] old = intent.getStringArrayExtra(extra);
            int oldLength = old != null ? old.length : 0;
            String[] result = new String[(oldLength + add.length)];
            if (old != null) {
                System.arraycopy(old, 0, result, 0, oldLength);
            }
            System.arraycopy(add, 0, result, oldLength, add.length);
            Intent putExtra = intent.putExtra(extra, result);
        }

        public Intent createChooserIntent() {
            return Intent.createChooser(getIntent(), this.mChooserTitle);
        }

        public void startChooser() {
            this.mActivity.startActivity(createChooserIntent());
        }

        public IntentBuilder setChooserTitle(CharSequence title) {
            this.mChooserTitle = title;
            return this;
        }

        public IntentBuilder setChooserTitle(@StringRes int resId) {
            return setChooserTitle(this.mActivity.getText(resId));
        }

        public IntentBuilder setType(String mimeType) {
            Intent type = this.mIntent.setType(mimeType);
            return this;
        }

        public IntentBuilder setText(CharSequence text) {
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.TEXT", text);
            return this;
        }

        public IntentBuilder setHtmlText(String str) {
            String htmlText = str;
            Intent putExtra = this.mIntent.putExtra(IntentCompat.EXTRA_HTML_TEXT, htmlText);
            if (!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
                IntentBuilder text = setText(Html.fromHtml(htmlText));
            }
            return this;
        }

        public IntentBuilder setStream(Uri uri) {
            Uri streamUri = uri;
            if (!this.mIntent.getAction().equals("android.intent.action.SEND")) {
                Intent action = this.mIntent.setAction("android.intent.action.SEND");
            }
            this.mStreams = null;
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.STREAM", streamUri);
            return this;
        }

        public IntentBuilder addStream(Uri uri) {
            ArrayList<Uri> arrayList;
            Uri streamUri = uri;
            Uri currentStream = (Uri) this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if (this.mStreams == null && currentStream == null) {
                return setStream(streamUri);
            }
            if (this.mStreams == null) {
                new ArrayList<>();
                this.mStreams = arrayList;
            }
            if (currentStream != null) {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
                boolean add = this.mStreams.add(currentStream);
            }
            boolean add2 = this.mStreams.add(streamUri);
            return this;
        }

        public IntentBuilder setEmailTo(String[] strArr) {
            String[] addresses = strArr;
            if (this.mToAddresses != null) {
                this.mToAddresses = null;
            }
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.EMAIL", addresses);
            return this;
        }

        public IntentBuilder addEmailTo(String str) {
            ArrayList<String> arrayList;
            String address = str;
            if (this.mToAddresses == null) {
                new ArrayList<>();
                this.mToAddresses = arrayList;
            }
            boolean add = this.mToAddresses.add(address);
            return this;
        }

        public IntentBuilder addEmailTo(String[] addresses) {
            combineArrayExtra("android.intent.extra.EMAIL", addresses);
            return this;
        }

        public IntentBuilder setEmailCc(String[] addresses) {
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.CC", addresses);
            return this;
        }

        public IntentBuilder addEmailCc(String str) {
            ArrayList<String> arrayList;
            String address = str;
            if (this.mCcAddresses == null) {
                new ArrayList<>();
                this.mCcAddresses = arrayList;
            }
            boolean add = this.mCcAddresses.add(address);
            return this;
        }

        public IntentBuilder addEmailCc(String[] addresses) {
            combineArrayExtra("android.intent.extra.CC", addresses);
            return this;
        }

        public IntentBuilder setEmailBcc(String[] addresses) {
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.BCC", addresses);
            return this;
        }

        public IntentBuilder addEmailBcc(String str) {
            ArrayList<String> arrayList;
            String address = str;
            if (this.mBccAddresses == null) {
                new ArrayList<>();
                this.mBccAddresses = arrayList;
            }
            boolean add = this.mBccAddresses.add(address);
            return this;
        }

        public IntentBuilder addEmailBcc(String[] addresses) {
            combineArrayExtra("android.intent.extra.BCC", addresses);
            return this;
        }

        public IntentBuilder setSubject(String subject) {
            Intent putExtra = this.mIntent.putExtra("android.intent.extra.SUBJECT", subject);
            return this;
        }
    }

    /* renamed from: android.support.v4.app.ShareCompat$IntentReader */
    public static class IntentReader {
        private static final String TAG = "IntentReader";
        private Activity mActivity;
        private ComponentName mCallingActivity;
        private String mCallingPackage;
        private Intent mIntent;
        private ArrayList<Uri> mStreams;

        public static IntentReader from(Activity activity) {
            IntentReader intentReader;
            new IntentReader(activity);
            return intentReader;
        }

        private IntentReader(Activity activity) {
            Activity activity2 = activity;
            this.mActivity = activity2;
            this.mIntent = activity2.getIntent();
            this.mCallingPackage = ShareCompat.getCallingPackage(activity2);
            this.mCallingActivity = ShareCompat.getCallingActivity(activity2);
        }

        public boolean isShareIntent() {
            String action = this.mIntent.getAction();
            return "android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action);
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.mIntent.getAction());
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        }

        public String getType() {
            return this.mIntent.getType();
        }

        public CharSequence getText() {
            return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getHtmlText() {
            StringBuilder sb;
            String result = this.mIntent.getStringExtra(IntentCompat.EXTRA_HTML_TEXT);
            if (result == null) {
                CharSequence text = getText();
                if (text instanceof Spanned) {
                    result = Html.toHtml((Spanned) text);
                } else if (text != null) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        result = Html.escapeHtml(text);
                    } else {
                        new StringBuilder();
                        StringBuilder out = sb;
                        withinStyle(out, text, 0, text.length());
                        result = out.toString();
                    }
                }
            }
            return result;
        }

        private static void withinStyle(StringBuilder sb, CharSequence charSequence, int start, int i) {
            StringBuilder sb2;
            StringBuilder out = sb;
            CharSequence text = charSequence;
            int end = i;
            int i2 = start;
            while (i2 < end) {
                char c = text.charAt(i2);
                if (c == '<') {
                    StringBuilder append = out.append("&lt;");
                } else if (c == '>') {
                    StringBuilder append2 = out.append("&gt;");
                } else if (c == '&') {
                    StringBuilder append3 = out.append("&amp;");
                } else if (c > '~' || c < ' ') {
                    new StringBuilder();
                    StringBuilder append4 = out.append(sb2.append("&#").append(c).append(";").toString());
                } else if (c == ' ') {
                    while (i2 + 1 < end && text.charAt(i2 + 1) == ' ') {
                        StringBuilder append5 = out.append("&nbsp;");
                        i2++;
                    }
                    StringBuilder append6 = out.append(' ');
                } else {
                    StringBuilder append7 = out.append(c);
                }
                i2++;
            }
        }

        public Uri getStream() {
            return (Uri) this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int i) {
            Throwable th;
            StringBuilder sb;
            int index = i;
            if (this.mStreams == null && isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            if (this.mStreams != null) {
                return this.mStreams.get(index);
            }
            if (index == 0) {
                return (Uri) this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            }
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append("Stream items available: ").append(getStreamCount()).append(" index requested: ").append(index).toString());
            throw th2;
        }

        public int getStreamCount() {
            if (this.mStreams == null && isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            if (this.mStreams != null) {
                return this.mStreams.size();
            }
            return this.mIntent.hasExtra("android.intent.extra.STREAM") ? 1 : 0;
        }

        public String[] getEmailTo() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String[] getEmailCc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailBcc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String getSubject() {
            return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
        }

        public String getCallingPackage() {
            return this.mCallingPackage;
        }

        public ComponentName getCallingActivity() {
            return this.mCallingActivity;
        }

        public Drawable getCallingActivityIcon() {
            if (this.mCallingActivity == null) {
                return null;
            }
            try {
                return this.mActivity.getPackageManager().getActivityIcon(this.mCallingActivity);
            } catch (PackageManager.NameNotFoundException e) {
                int e2 = Log.e(TAG, "Could not retrieve icon for calling activity", e);
                return null;
            }
        }

        public Drawable getCallingApplicationIcon() {
            if (this.mCallingPackage == null) {
                return null;
            }
            try {
                return this.mActivity.getPackageManager().getApplicationIcon(this.mCallingPackage);
            } catch (PackageManager.NameNotFoundException e) {
                int e2 = Log.e(TAG, "Could not retrieve icon for calling application", e);
                return null;
            }
        }

        public CharSequence getCallingApplicationLabel() {
            if (this.mCallingPackage == null) {
                return null;
            }
            PackageManager pm = this.mActivity.getPackageManager();
            try {
                return pm.getApplicationLabel(pm.getApplicationInfo(this.mCallingPackage, 0));
            } catch (PackageManager.NameNotFoundException e) {
                int e2 = Log.e(TAG, "Could not retrieve label for calling application", e);
                return null;
            }
        }
    }
}
