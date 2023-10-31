package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.p000v4.view.GravityCompat;
import android.support.p003v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.listener.OnSwipeTouchListener;
import de.hdodenhof.circleimageview.CircleImageView;

@DesignerComponent(category = ComponentCategory.VIEWS, description = "write in ode", iconName = "images/chatView.png", version = 3)
@UsesLibraries(libraries = "glide.jar, circleimageview.aar, circleimageview.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class KodularChatView extends AndroidViewComponent implements Component {
    private static final String LOG_TAG = "Kodular Chat View";
    private final Activity activity;
    private boolean automaticScrollDown;
    private int backgroundColor;
    private Drawable backgroundImageDrawable;
    /* access modifiers changed from: private */
    public ScrollView chatView;
    private LinearLayout chatViewLinearLayout;
    /* access modifiers changed from: private */
    public boolean clickable;
    private Context context;
    private int countDateTimestamp = 0;
    private int countMessages = 0;
    private Drawable defaultDrawable;
    /* access modifiers changed from: private */
    public boolean doubleTap;
    private Form form;
    private String imagePath = "";
    private boolean isCompanion = false;
    private int lastId = 0;
    private LinearLayout linearLayout;
    private float messageFontSize = 14.0f;
    private float messagesCornerRadius = 5.0f;
    private int receiversBackgroundColor;
    private int receiversMessageColor;
    private int receiversTitleColor;
    private int receiversTypefaceMessage = 0;
    private String receiversTypefaceMessageCustom = "";
    private int receiversTypefaceTitle = 0;
    private String receiversTypefaceTitleCustom = "";
    private boolean scrollbar = true;
    private int sendersBackgroundColor;
    private int sendersMessageColor;
    private int sendersTitleColor;
    private int sendersTypefaceMessage = 0;
    private String sendersTypefaceMessageCustom = "";
    private int sendersTypefaceTitle = 0;
    private String sendersTypefaceTitleCustom = "";
    private int size;
    /* access modifiers changed from: private */
    public boolean swipeable;
    private float timestampCornerRadius = 5.0f;
    private int timestampTextColor;
    private float timestapeFontSize = 14.0f;
    private float titleFontSize = 14.0f;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularChatView(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.imagePath = r3
            r2 = r0
            r3 = 1
            r2.scrollbar = r3
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = 0
            r2.lastId = r3
            r2 = r0
            r3 = 0
            r2.countMessages = r3
            r2 = r0
            r3 = 0
            r2.countDateTimestamp = r3
            r2 = r0
            r3 = 1084227584(0x40a00000, float:5.0)
            r2.messagesCornerRadius = r3
            r2 = r0
            r3 = 1084227584(0x40a00000, float:5.0)
            r2.timestampCornerRadius = r3
            r2 = r0
            r3 = 0
            r2.sendersTypefaceTitle = r3
            r2 = r0
            r3 = 0
            r2.sendersTypefaceMessage = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.sendersTypefaceTitleCustom = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.sendersTypefaceMessageCustom = r3
            r2 = r0
            r3 = 0
            r2.receiversTypefaceTitle = r3
            r2 = r0
            r3 = 0
            r2.receiversTypefaceMessage = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.receiversTypefaceTitleCustom = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.receiversTypefaceMessageCustom = r3
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.timestapeFontSize = r3
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.titleFontSize = r3
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.messageFontSize = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            android.content.Context r2 = r2.context
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            r2 = r0
            r2.CreateLayout()
            r2 = r0
            r4 = r2
            r2 = r4
            r3 = r4
            android.view.View r3 = r3.getView()
            android.graphics.drawable.Drawable r3 = r3.getBackground()
            r2.defaultDrawable = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x00a1
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x00a1:
            r2 = r0
            r3 = 0
            r2.BackgroundColor(r3)
            r2 = r0
            r3 = 1
            r2.AutomaticScrollDown(r3)
            r2 = r0
            r3 = 1
            r2.Scrollbar(r3)
            r2 = r0
            r3 = 0
            r2.Clickable(r3)
            r2 = r0
            r3 = 0
            r2.Swipeable(r3)
            r2 = r0
            r3 = 0
            r2.DoubleTap(r3)
            r2 = r0
            r3 = -7617974(0xffffffffff8bc24a, float:NaN)
            r2.SendersBackgroundColor(r3)
            r2 = r0
            r3 = -3355444(0xffffffffffcccccc, float:NaN)
            r2.ReceiversBackgroundColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.SendersTitleColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.ReceiversTitleColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.SendersMessageColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.ReceiversMessageColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.TimestampTextColor(r3)
            r2 = r0
            r3 = 0
            r2.SendersTypefaceTitle(r3)
            r2 = r0
            r3 = 0
            r2.SendersTypefaceMessage(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.SendersTypefaceTitleImport(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.SendersTypefaceMessageImport(r3)
            r2 = r0
            r3 = 0
            r2.ReceiversTypefaceTitle(r3)
            r2 = r0
            r3 = 0
            r2.ReceiversTypefaceMessage(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.ReceiversTypefaceTitleImport(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.ReceiversTypefaceMessageImport(r3)
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.TimestampFontSize(r3)
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.TitleFontSize(r3)
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.MessagesFontSize(r3)
            r2 = r0
            r3 = 1084227584(0x40a00000, float:5.0)
            r2.MessagesCornerRadius(r3)
            r2 = r0
            r3 = 1084227584(0x40a00000, float:5.0)
            r2.TimestampCornerRadius(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = -2
            r2.Width(r3)
            r2 = r0
            r3 = -2
            r2.Height(r3)
            java.lang.String r2 = "Kodular Chat View"
            java.lang.String r3 = "Kodular ChatView Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularChatView.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public View getView() {
        return this.linearLayout;
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleProperty
    public void Height(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Height(i2);
    }

    private void CreateLayout() {
        LinearLayout linearLayout2;
        ScrollView scrollView;
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout3;
        this.size = KodularUnitUtil.DpToPixels(this.context, 8);
        new LinearLayout(this.activity);
        this.linearLayout = linearLayout2;
        this.linearLayout.setOrientation(1);
        new ScrollView(this.activity);
        this.chatView = scrollView;
        this.chatView.setSmoothScrollingEnabled(true);
        new FrameLayout.LayoutParams(-1, -1);
        this.chatView.setLayoutParams(layoutParams);
        new LinearLayout(this.activity);
        this.chatViewLinearLayout = linearLayout3;
        this.chatViewLinearLayout.setOrientation(1);
        this.chatViewLinearLayout.setLayoutParams(getLayoutParams(true));
        this.chatView.addView(this.chatViewLinearLayout);
        this.linearLayout.addView(this.chatView);
    }

    private void removeMessage(int i) {
        try {
            LinearLayout view = getView(i);
            LinearLayout linearLayout2 = view;
            if (view == null) {
                int w = Log.w(LOG_TAG, "Can not perfom 'remove Message'. The object is null.");
                return;
            }
            linearLayout2.setVisibility(8);
            if (linearLayout2.getTag() == null) {
                return;
            }
            if (linearLayout2.getTag().toString().equals("addMessageLayout")) {
                this.countMessages--;
                return;
            }
            this.countDateTimestamp--;
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private LinearLayout getView(int i) {
        try {
            return (LinearLayout) this.form.findViewById(i);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            return null;
        }
    }

    private void updateMessage(String str, int i, String str2, int i2) {
        String str3 = str;
        String str4 = str2;
        int i3 = i2;
        try {
            LinearLayout view = getView(i);
            LinearLayout linearLayout2 = view;
            if (view == null) {
                int w = Log.w(LOG_TAG, "Can not perfom 'update Message'. The object is null.");
                return;
            }
            for (int i4 = 0; i4 < linearLayout2.getChildCount(); i4++) {
                View childAt = linearLayout2.getChildAt(i4);
                View view2 = childAt;
                if ((childAt instanceof TextView) && view2.getTag() != null && view2.getTag().toString().equals(str3)) {
                    TextView textView = (TextView) view2;
                    textView.setTextColor(i3);
                    TextViewUtil.setTextHTML(textView, str4);
                }
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void updateUserImage(int i, String str) {
        String str2 = str;
        try {
            LinearLayout view = getView(i);
            LinearLayout linearLayout2 = view;
            if (view == null) {
                int w = Log.w(LOG_TAG, "Can not perfom 'update User Image'. The object is null.");
            } else if (linearLayout2.getParent() != null) {
                LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getParent();
                for (int i2 = 0; i2 < linearLayout3.getChildCount(); i2++) {
                    View childAt = linearLayout3.getChildAt(i2);
                    View view2 = childAt;
                    if (childAt instanceof CircleImageView) {
                        KodularUtil.LoadPicture(this.context, str2 == null ? "" : str2, (CircleImageView) view2, this.isCompanion);
                    }
                }
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void addDateTimestamp(String str, int i, int i2, int i3) {
        LinearLayout linearLayout2;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (str2.isEmpty()) {
            int i7 = Log.i(LOG_TAG, "Case 1 - Date Timestamp - Date string is empty but needed.");
            return;
        }
        new LinearLayout(this.activity);
        LinearLayout linearLayout3 = linearLayout2;
        LinearLayout linearLayout4 = linearLayout3;
        linearLayout3.setId(i6);
        this.lastId = i6;
        this.countDateTimestamp++;
        linearLayout4.setTag("timestampLayout");
        linearLayout4.setOrientation(1);
        TextView newTextView = getNewTextView("timestamp", str2);
        TextView textView = newTextView;
        TextViewUtil.setAlignment(newTextView, 1, true);
        LinearLayout.LayoutParams layoutParams = getLayoutParams(false);
        layoutParams.gravity = 17;
        textView.setLayoutParams(layoutParams);
        TextViewUtil.setFontSize(textView, this.timestapeFontSize);
        LinearLayout.LayoutParams layoutParams2 = getLayoutParams(false);
        LinearLayout.LayoutParams layoutParams3 = layoutParams2;
        layoutParams2.setMargins(0, this.size / 2, 0, this.size / 2);
        layoutParams3.gravity = 17;
        setShape(linearLayout4, i5, i5 == 16777215 ? -1 : i5, this.timestampCornerRadius);
        linearLayout4.setLayoutParams(layoutParams3);
        textView.setTextColor(i4);
        linearLayout4.addView(textView);
        this.chatViewLinearLayout.addView(linearLayout4);
        scrollDownIfNeeded();
    }

    private CircleImageView addCircleImage(String str, int i) {
        CircleImageView circleImageView;
        String str2 = str;
        int i2 = i;
        try {
            new CircleImageView(this.activity);
            CircleImageView circleImageView2 = circleImageView;
            KodularUtil.LoadPicture(this.context, str2 == null ? "" : str2, circleImageView2, this.isCompanion);
            addCircleClickListener(circleImageView2, str2, i2);
            return circleImageView2;
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            return null;
        }
    }

    private void addCircleClickListener(CircleImageView circleImageView, String str, int i) {
        View.OnClickListener onClickListener;
        CircleImageView circleImageView2 = circleImageView;
        circleImageView2.setClickable(true);
        final int i2 = i;
        final String str2 = str;
        new View.OnClickListener(this) {
            private /* synthetic */ KodularChatView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void onClick(View view) {
                View view2 = view;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserImageClick(i2, str2);
            }
        };
        circleImageView2.setOnClickListener(onClickListener);
    }

    private void addMessage(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, AndroidViewComponent androidViewComponent, boolean z3, String str6, String str7, boolean z4) {
        LinearLayout linearLayout2;
        int displayWidth;
        LinearLayout linearLayout3;
        LinearLayout.LayoutParams layoutParams;
        StringBuilder sb;
        StringBuilder sb2;
        String str8 = str;
        String str9 = str2;
        String str10 = str3;
        String str11 = str4;
        int i2 = i;
        boolean z5 = z;
        String str12 = str5;
        boolean z6 = z2;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        boolean z7 = z3;
        String str13 = str6;
        String str14 = str7;
        boolean z8 = z4;
        if (str12.isEmpty() && z6) {
            int i3 = Log.i(LOG_TAG, "Case 1 - Imagepath is empty but needed.");
        } else if (str10.isEmpty() && !z6) {
            int i4 = Log.i(LOG_TAG, "Case 2 - Message is empty but needed.");
        } else if (str9.isEmpty() && str10.isEmpty() && str11.isEmpty() && !z6) {
            int i5 = Log.i(LOG_TAG, "Case 3 - Title, Message and Timestamp are empty but needed.");
        } else if (!str13.isEmpty() || !str14.isEmpty() || !z8) {
            new LinearLayout(this.activity);
            LinearLayout linearLayout4 = linearLayout2;
            LinearLayout linearLayout5 = linearLayout4;
            linearLayout4.setId(i2);
            this.lastId = i2;
            this.countMessages++;
            linearLayout5.setTag("addMessageLayout");
            TextView textView = null;
            AppCompatImageView appCompatImageView = null;
            AppCompatImageView appCompatImageView2 = null;
            TextView textView2 = null;
            TextView textView3 = null;
            View view = null;
            linearLayout5.setOrientation(1);
            if (!str9.isEmpty()) {
                TextView newTextView = getNewTextView("title", str9);
                textView = newTextView;
                newTextView.setLayoutParams(getLayoutParams(false));
                TextViewUtil.setFontSize(textView, this.titleFontSize);
                fontHelper(z5, (TextView) null, this.sendersTypefaceTitleCustom, this.receiversTypefaceTitleCustom, this.sendersTypefaceTitle, this.receiversTypefaceTitle);
            }
            if (z6 && !str12.isEmpty()) {
                try {
                    appCompatImageView = getNewImageView(str12);
                } catch (Exception e) {
                    new StringBuilder("Unable to load image. Reason: ");
                    int e2 = Log.e(LOG_TAG, sb2.append(e.getMessage()).toString());
                }
            }
            if (z8 && !str13.isEmpty() && !str14.isEmpty()) {
                try {
                    appCompatImageView2 = getNewImageView(str14);
                } catch (Exception e3) {
                    new StringBuilder("Unable to load video image thumbnail. Reason: ");
                    int e4 = Log.e(LOG_TAG, sb.append(e3.getMessage()).toString());
                }
            }
            if (z7 && androidViewComponent2 != null) {
                try {
                    View view2 = androidViewComponent2.getView();
                    view = view2;
                    ((ViewGroup) view2.getParent()).removeView(view);
                } catch (Exception e5) {
                    int e6 = Log.e(LOG_TAG, String.valueOf(e5));
                }
            }
            if (!str10.isEmpty()) {
                TextView newTextView2 = getNewTextView("message", str10);
                textView2 = newTextView2;
                newTextView2.setLayoutParams(getLayoutParams(false));
                TextViewUtil.setFontSize(textView2, this.messageFontSize);
                fontHelper(z5, textView2, this.sendersTypefaceMessageCustom, this.receiversTypefaceMessageCustom, this.sendersTypefaceMessage, this.receiversTypefaceMessage);
            }
            if (str11 != null && !str11.isEmpty()) {
                textView3 = getNewTextView("timestamp", str11);
                LinearLayout.LayoutParams layoutParams2 = getLayoutParams(false);
                layoutParams2.gravity = GravityCompat.END;
                textView3.setLayoutParams(layoutParams2);
                TextViewUtil.setFontSize(textView3, this.timestapeFontSize);
            }
            int i6 = -2;
            if (!(appCompatImageView == null && view == null && appCompatImageView2 == null)) {
                i6 = isScreenOrientationPortrait() ? 50 : 35;
            }
            LinearLayout.LayoutParams layoutParams3 = r38;
            if (i6 == -2) {
                displayWidth = -2;
            } else {
                displayWidth = (displayWidth() / 100) * i6;
            }
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(displayWidth, -2);
            LinearLayout.LayoutParams layoutParams5 = layoutParams3;
            if (str8.isEmpty()) {
                layoutParams5.setMargins(this.size, this.size / 2, this.size, this.size / 2);
            }
            layoutParams5.weight = 0.5f;
            int i7 = Log.i(LOG_TAG, "Send now as sender: ".concat(String.valueOf(z5)));
            if (z5) {
                layoutParams5.gravity = GravityCompat.END;
                messageHelper(linearLayout5, textView, textView2, this.sendersBackgroundColor, this.sendersTitleColor, this.sendersMessageColor);
            } else {
                layoutParams5.gravity = GravityCompat.START;
                messageHelper(linearLayout5, textView, textView2, this.receiversBackgroundColor, this.receiversTitleColor, this.receiversMessageColor);
            }
            if (textView3 != null) {
                textView3.setTextColor(this.timestampTextColor);
            }
            linearLayout5.setLayoutParams(layoutParams5);
            if (textView != null) {
                linearLayout5.addView(textView);
            }
            if (appCompatImageView != null) {
                linearLayout5.addView(appCompatImageView);
            }
            if (view != null) {
                linearLayout5.addView(view);
            }
            if (appCompatImageView2 != null) {
                linearLayout5.addView(appCompatImageView2);
            }
            if (textView2 != null) {
                linearLayout5.addView(textView2);
            }
            if (textView3 != null) {
                linearLayout5.addView(textView3);
            }
            addClickListener(linearLayout5, textView2, str9, str10, str11, i2, z5, str12, z6, str13, str14, z8, str8);
            if (str8 == null || str8.isEmpty()) {
                this.chatViewLinearLayout.addView(linearLayout5);
            } else {
                int i8 = Log.i(LOG_TAG, "The user wants a user image near the message.");
                LinearLayout.LayoutParams layoutParams6 = getLayoutParams(false);
                new LinearLayout(this.activity);
                LinearLayout linearLayout6 = linearLayout3;
                LinearLayout linearLayout7 = linearLayout6;
                linearLayout6.setOrientation(0);
                new LinearLayout.LayoutParams(KodularUnitUtil.DpToPixels((Context) this.activity, 45), KodularUnitUtil.DpToPixels((Context) this.activity, 45));
                LinearLayout.LayoutParams layoutParams7 = layoutParams;
                LinearLayout.LayoutParams layoutParams8 = layoutParams7;
                layoutParams7.setMargins(this.size, this.size / 2, this.size, 0);
                if (z5) {
                    layoutParams6.gravity = GravityCompat.END;
                    layoutParams6.setMargins(this.size, this.size / 2, 0, this.size / 2);
                    linearLayout7.setLayoutParams(layoutParams6);
                    linearLayout7.addView(linearLayout5);
                    linearLayout7.addView(addCircleImage(str8, i2), layoutParams8);
                } else {
                    layoutParams6.gravity = GravityCompat.START;
                    layoutParams6.setMargins(0, this.size / 2, this.size, this.size / 2);
                    linearLayout7.setLayoutParams(layoutParams6);
                    linearLayout7.addView(addCircleImage(str8, i2), layoutParams8);
                    linearLayout7.addView(linearLayout5);
                }
                this.chatViewLinearLayout.addView(linearLayout7);
            }
            scrollDownIfNeeded();
        } else {
            int i9 = Log.i(LOG_TAG, "Case 4 - File is empty but needed.");
        }
    }

    private void scrollDownIfNeeded() {
        Runnable runnable;
        if (this.automaticScrollDown) {
            new Runnable(this) {
                private /* synthetic */ KodularChatView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    boolean fullScroll = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.chatView.fullScroll(130);
                }
            };
            boolean post = this.chatView.post(runnable);
        }
    }

    private TextView getNewTextView(String str, String str2) {
        TextView textView;
        new TextView(this.activity);
        TextView textView2 = textView;
        TextView textView3 = textView2;
        textView2.setTag(str);
        TextViewUtil.setTextHTML(textView3, str2);
        textView3.setPadding(this.size, this.size / 5, this.size, this.size / 5);
        return textView3;
    }

    private AppCompatImageView getNewImageView(String str) {
        AppCompatImageView appCompatImageView;
        String str2 = str;
        new AppCompatImageView(this.activity);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        KodularUtil.LoadPicture(this.context, str2 == null ? "" : str2, appCompatImageView2, this.isCompanion);
        appCompatImageView2.setLayoutParams(getLayoutParams(true));
        appCompatImageView2.setPadding(this.size, this.size / 5, this.size, this.size / 5);
        return appCompatImageView2;
    }

    private LinearLayout.LayoutParams getLayoutParams(boolean z) {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout.LayoutParams layoutParams2;
        if (z) {
            new LinearLayout.LayoutParams(-1, -1);
            return layoutParams2;
        }
        new LinearLayout.LayoutParams(-2, -2);
        return layoutParams;
    }

    private void messageHelper(LinearLayout linearLayout2, TextView textView, TextView textView2, int i, int i2, int i3) {
        TextView textView3 = textView;
        TextView textView4 = textView2;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        setShape(linearLayout2, i4, i4 == 16777215 ? -1 : i4, this.messagesCornerRadius);
        if (textView3 != null) {
            textView3.setTextColor(i5);
        }
        if (textView4 != null) {
            textView4.setTextColor(i6);
        }
    }

    private void scrollDown(int i) {
        try {
            LinearLayout view = getView(i);
            LinearLayout linearLayout2 = view;
            if (view == null) {
                int w = Log.w(LOG_TAG, "Can not perfom 'scroll Down'. The object is null.");
            } else {
                this.chatView.smoothScrollTo(0, linearLayout2.getBottom() + (this.size / 2));
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void updateAppearance() {
        if (this.backgroundImageDrawable != null) {
            ViewUtil.setBackgroundImage(this.linearLayout, this.backgroundImageDrawable);
        } else if (this.backgroundColor == 0) {
            ViewUtil.setBackgroundDrawable(this.linearLayout, this.defaultDrawable);
        } else {
            ViewUtil.setBackgroundDrawable(this.linearLayout, (Drawable) null);
            this.linearLayout.setBackgroundColor(this.backgroundColor);
        }
    }

    private int displayWidth() {
        return this.context.getResources().getDisplayMetrics().widthPixels;
    }

    private void setShape(View view, int i, int i2, float f) {
        GradientDrawable gradientDrawable;
        View view2 = view;
        int i3 = i;
        int i4 = i2;
        float f2 = f;
        try {
            new GradientDrawable();
            GradientDrawable gradientDrawable2 = gradientDrawable;
            GradientDrawable gradientDrawable3 = gradientDrawable2;
            gradientDrawable2.setShape(0);
            gradientDrawable3.setCornerRadius(KodularUnitUtil.DpToPixels((Context) this.activity, f2));
            gradientDrawable3.setColor(i3);
            gradientDrawable3.setStroke(2, i4);
            view2.setBackground(gradientDrawable3);
            view2.invalidate();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private boolean isScreenOrientationPortrait() {
        return this.context.getResources().getConfiguration().orientation == 1;
    }

    private void fontHelper(boolean z, TextView textView, String str, String str2, int i, int i2) {
        TextView textView2 = textView;
        String str3 = str;
        String str4 = str2;
        int i3 = i;
        int i4 = i2;
        if (z) {
            if (str3 == null || str3.isEmpty()) {
                TextViewUtil.setFontTypeface(textView2, i3, false, false);
            } else {
                TextViewUtil.setCustomFontTypeface(this.form, textView2, str3, false, false);
            }
        } else if (str4 == null || str4.isEmpty()) {
            TextViewUtil.setFontTypeface(textView2, i4, false, false);
        } else {
            TextViewUtil.setCustomFontTypeface(this.form, textView2, str4, false, false);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void addClickListener(LinearLayout linearLayout2, TextView textView, String str, String str2, String str3, int i, boolean z, String str4, boolean z2, String str5, String str6, boolean z3, String str7) {
        View.OnTouchListener onTouchListener;
        TextView textView2 = textView;
        View.OnTouchListener onTouchListener2 = onTouchListener;
        final String str8 = str7;
        final String str9 = str;
        final String str10 = str2;
        final String str11 = str3;
        final int i2 = i;
        final boolean z4 = z;
        final String str12 = str4;
        final boolean z5 = z2;
        final String str13 = str5;
        final String str14 = str6;
        final boolean z6 = z3;
        new OnSwipeTouchListener(this, this.activity) {
            private /* synthetic */ KodularChatView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r17;
            }

            public final void onSwipeTop() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.swipeable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Swipe(3, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeRight() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.swipeable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Swipe(2, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeLeft() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.swipeable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Swipe(1, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeBottom() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.swipeable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Swipe(4, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onClick() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clickable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onLongClick() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clickable) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongClick(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onDoubleClick() {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.doubleTap) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DoubleTapClick(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }
        };
        linearLayout2.setOnTouchListener(onTouchListener2);
    }

    @SimpleFunction(description = "Scroll to a specific message in the chat view with the given id.")
    public void ScrollTo(int i) {
        scrollDown(i);
    }

    @SimpleFunction(description = "Returns the last used id.")
    public int GetLastUsedId() {
        return this.lastId;
    }

    @SimpleFunction(description = "Returns the number of all messages. 'Date Timestamp' messages are not included.")
    public int CountMessages() {
        return this.countMessages;
    }

    @SimpleFunction(description = "Returns the number of all date timestamps. Normal messages are not included.")
    public int CountDateTimestamp() {
        return this.countDateTimestamp;
    }

    @SimpleFunction(description = "Add a new simple message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddMessage(String str, String str2, String str3, String str4, int i, boolean z) {
        addMessage(str, str2, str3, str4, i, z, "", false, (AndroidViewComponent) null, false, "", "", false);
    }

    @SimpleFunction(description = "Add a new simple image message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddImageMessage(String str, String str2, String str3, String str4, String str5, int i, boolean z) {
        addMessage(str, str2, str3, str5, i, z, str4, true, (AndroidViewComponent) null, false, "", "", false);
    }

    @SimpleFunction(description = "Add a new simple file message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddFileMessage(String str, String str2, String str3, String str4, String str5, String str6, int i, boolean z) {
        addMessage(str, str2, str3, str6, i, z, "", false, (AndroidViewComponent) null, false, str4, str5, true);
    }

    @SimpleFunction(description = "Add a new simple date timestamp into the chat view. You NEED to write a date, else this block will do nothing. This means the field 'date' can NOT be empty. Timestamp messages are not clickable.")
    public void AddDateTimestamp(String str, int i, int i2, int i3) {
        addDateTimestamp(str, i, i2, i3);
    }

    @SimpleFunction(description = "Add a new simple component message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty. Make sure that the component is VISIBLE on the screen when you try to add it here. It will be then removed automatic from the screen and only visible again in the chat view.")
    public void AddComponentMessage(String str, String str2, String str3, String str4, AndroidViewComponent androidViewComponent, int i, boolean z) {
        addMessage(str, str2, str3, str4, i, z, "", false, androidViewComponent, true, "", "", false);
    }

    @SimpleFunction(description = "Update the title content of a chat view message.")
    public void UpdateTitleContent(int i, String str, int i2) {
        updateMessage("title", i, str, i2);
    }

    @SimpleFunction(description = "Update the message content of a chat view message.")
    public void UpdateMessageContent(int i, String str, int i2) {
        updateMessage("message", i, str, i2);
    }

    @SimpleFunction(description = "Update the timestamp content of a chat view message.")
    public void UpdateTimestampContent(int i, String str, int i2) {
        updateMessage("timestamp", i, str, i2);
    }

    @SimpleFunction(description = "Update the user image of a chat view message. The image can only be updated if there was before a old image.")
    public void UpdateUserImage(int i, String str) {
        updateUserImage(i, str);
    }

    @SimpleFunction(description = "Update the background color of a chat view message.")
    public void UpdateMessageBackgroundColor(int i, int i2) {
        int i3 = i2;
        try {
            LinearLayout view = getView(i);
            LinearLayout linearLayout2 = view;
            if (view == null) {
                int w = Log.w(LOG_TAG, "Can not perfom 'Update Message Background Color'. The object is null.");
                return;
            }
            if (i3 == 0) {
                ViewUtil.setBackgroundDrawable(linearLayout2, this.defaultDrawable);
            } else {
                ViewUtil.setBackgroundDrawable(linearLayout2, (Drawable) null);
                linearLayout2.setBackgroundColor(i3);
            }
            float f = 12.0f;
            if (linearLayout2.getTag() != null && linearLayout2.getTag().toString().equals("timestampLayout")) {
                f = 10.0f;
            }
            setShape(linearLayout2, i3, i3 == 16777215 ? -1 : i3, f);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a message or timestamp from the chat view.")
    public void RemoveMessage(int i) {
        removeMessage(i);
    }

    @SimpleFunction(description = "Removes all messages and timestamps from the chat view.")
    public void ClearChatView() {
        try {
            if (this.chatViewLinearLayout.getChildCount() > 0) {
                this.chatViewLinearLayout.removeAllViews();
                this.countMessages = 0;
                this.countDateTimestamp = 0;
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @DesignerProperty(defaultValue = "&HFF8BC24A", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for sender's messages.")
    public void SendersBackgroundColor(int i) {
        int i2 = i;
        this.sendersBackgroundColor = i2;
    }

    @SimpleProperty
    public int SendersBackgroundColor() {
        return this.sendersBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for sender's title messages.")
    public void SendersTitleColor(int i) {
        int i2 = i;
        this.sendersTitleColor = i2;
    }

    @SimpleProperty
    public int SendersTitleColor() {
        return this.sendersTitleColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for sender's messages.")
    public void SendersMessageColor(int i) {
        int i2 = i;
        this.sendersMessageColor = i2;
    }

    @SimpleProperty
    public int SendersMessageColor() {
        return this.sendersMessageColor;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for receiver's messages.")
    public void ReceiversBackgroundColor(int i) {
        int i2 = i;
        this.receiversBackgroundColor = i2;
    }

    @SimpleProperty
    public int ReceiversBackgroundColor() {
        return this.receiversBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for receivers's title messages.")
    public void ReceiversTitleColor(int i) {
        int i2 = i;
        this.receiversTitleColor = i2;
    }

    @SimpleProperty
    public int ReceiversTitleColor() {
        return this.receiversTitleColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for receivers's messages.")
    public void ReceiversMessageColor(int i) {
        int i2 = i;
        this.receiversMessageColor = i2;
    }

    @SimpleProperty
    public int ReceiversMessageColor() {
        return this.receiversMessageColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for the timestamp.")
    public void TimestampTextColor(int i) {
        int i2 = i;
        this.timestampTextColor = i2;
    }

    @SimpleProperty
    public int TimestampTextColor() {
        return this.timestampTextColor;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar.")
    public void Scrollbar(boolean z) {
        boolean z2 = z;
        this.scrollbar = z2;
        this.chatView.setVerticalScrollBarEnabled(z2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Scrollbar() {
        return this.scrollbar;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true the chat view will scroll down when a new message was send.")
    public void AutomaticScrollDown(boolean z) {
        boolean z2 = z;
        this.automaticScrollDown = z2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean AutomaticScrollDown() {
        return this.automaticScrollDown;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the component's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the component's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the component's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(String str) {
        String str2 = str;
        if (!str2.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            this.imagePath = str2 == null ? "" : str2;
            this.backgroundImageDrawable = null;
            if (this.imagePath.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception e) {
                    int e2 = Log.e(LOG_TAG, String.valueOf(e));
                }
            }
            updateAppearance();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Image() {
        return this.imagePath;
    }

    @SimpleEvent(description = "Click listener event for the user image.")
    public void UserImageClick(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UserImageClick", objArr2);
    }

    @SimpleEvent(description = "Click listener event.")
    public void Click(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        Object[] objArr = new Object[11];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i);
        Object[] objArr6 = objArr5;
        objArr6[5] = Boolean.valueOf(z);
        Object[] objArr7 = objArr6;
        objArr7[6] = str5;
        Object[] objArr8 = objArr7;
        objArr8[7] = Boolean.valueOf(z2);
        Object[] objArr9 = objArr8;
        objArr9[8] = str6;
        Object[] objArr10 = objArr9;
        objArr10[9] = str7;
        Object[] objArr11 = objArr10;
        objArr11[10] = Boolean.valueOf(z3);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", objArr11);
    }

    @SimpleEvent(description = "Long click listener event.")
    public void LongClick(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        Object[] objArr = new Object[11];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i);
        Object[] objArr6 = objArr5;
        objArr6[5] = Boolean.valueOf(z);
        Object[] objArr7 = objArr6;
        objArr7[6] = str5;
        Object[] objArr8 = objArr7;
        objArr8[7] = Boolean.valueOf(z2);
        Object[] objArr9 = objArr8;
        objArr9[8] = str6;
        Object[] objArr10 = objArr9;
        objArr10[9] = str7;
        Object[] objArr11 = objArr10;
        objArr11[10] = Boolean.valueOf(z3);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", objArr11);
    }

    @SimpleEvent(description = "Double tap click listener event.")
    public void DoubleTapClick(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        Object[] objArr = new Object[11];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(i);
        Object[] objArr6 = objArr5;
        objArr6[5] = Boolean.valueOf(z);
        Object[] objArr7 = objArr6;
        objArr7[6] = str5;
        Object[] objArr8 = objArr7;
        objArr8[7] = Boolean.valueOf(z2);
        Object[] objArr9 = objArr8;
        objArr9[8] = str6;
        Object[] objArr10 = objArr9;
        objArr10[9] = str7;
        Object[] objArr11 = objArr10;
        objArr11[10] = Boolean.valueOf(z3);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DoubleTapClick", objArr11);
    }

    @SimpleEvent(description = "Swipe listener event. The direction value returns '1' for right-to-left swipes, '2' for left-to-right swipes '3' for bottom-to-top swipes and '4' for top-to-bottom swipes")
    public void Swipe(int i, String str, String str2, String str3, String str4, int i2, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        Object[] objArr = new Object[12];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        Object[] objArr4 = objArr3;
        objArr4[3] = str3;
        Object[] objArr5 = objArr4;
        objArr5[4] = str4;
        Object[] objArr6 = objArr5;
        objArr6[5] = Integer.valueOf(i2);
        Object[] objArr7 = objArr6;
        objArr7[6] = Boolean.valueOf(z);
        Object[] objArr8 = objArr7;
        objArr8[7] = str5;
        Object[] objArr9 = objArr8;
        objArr9[8] = Boolean.valueOf(z2);
        Object[] objArr10 = objArr9;
        objArr10[9] = str6;
        Object[] objArr11 = objArr10;
        objArr11[10] = str7;
        Object[] objArr12 = objArr11;
        objArr12[11] = Boolean.valueOf(z3);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Swipe", objArr12);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public void Clickable(boolean z) {
        boolean z2 = z;
        this.clickable = z2;
    }

    @SimpleProperty
    public boolean Clickable() {
        return this.clickable;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Set the component enabled for double taps or not.")
    public void DoubleTap(boolean z) {
        boolean z2 = z;
        this.doubleTap = z2;
    }

    @SimpleProperty
    public boolean DoubleTap() {
        return this.doubleTap;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true you can swipe with your fingers through views.")
    public void Swipeable(boolean z) {
        boolean z2 = z;
        this.swipeable = z2;
    }

    @SimpleProperty
    public boolean Swipeable() {
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceTitle(int i) {
        int i2 = i;
        this.sendersTypefaceTitle = i2;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceMessage(int i) {
        int i2 = i;
        this.sendersTypefaceMessage = i2;
    }

    @DesignerProperty(editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceTitleImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.sendersTypefaceTitleCustom = str2;
        }
    }

    @DesignerProperty(editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceMessageImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.sendersTypefaceMessageCustom = str2;
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceTitle(int i) {
        int i2 = i;
        this.receiversTypefaceTitle = i2;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceMessage(int i) {
        int i2 = i;
        this.receiversTypefaceMessage = i2;
    }

    @DesignerProperty(editorType = "asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceTitleImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.receiversTypefaceTitleCustom = str2;
        }
    }

    @DesignerProperty(editorType = "asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceMessageImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.receiversTypefaceMessageCustom = str2;
        }
    }

    @DesignerProperty(defaultValue = "5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MessagesCornerRadius(float f) {
        float f2 = f;
        this.messagesCornerRadius = f2;
    }

    @DesignerProperty(defaultValue = "5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TimestampCornerRadius(float f) {
        float f2 = f;
        this.timestampCornerRadius = f2;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TimestampFontSize(float f) {
        float f2 = f;
        this.timestapeFontSize = f2;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TitleFontSize(float f) {
        float f2 = f;
        this.titleFontSize = f2;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MessagesFontSize(float f) {
        float f2 = f;
        this.messageFontSize = f2;
    }
}
