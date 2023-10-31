package android.support.p003v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.ContextThemeWrapper;
import android.support.p003v7.widget.AppCompatAutoCompleteTextView;
import android.support.p003v7.widget.AppCompatButton;
import android.support.p003v7.widget.AppCompatCheckBox;
import android.support.p003v7.widget.AppCompatCheckedTextView;
import android.support.p003v7.widget.AppCompatEditText;
import android.support.p003v7.widget.AppCompatImageButton;
import android.support.p003v7.widget.AppCompatImageView;
import android.support.p003v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.p003v7.widget.AppCompatRadioButton;
import android.support.p003v7.widget.AppCompatRatingBar;
import android.support.p003v7.widget.AppCompatSeekBar;
import android.support.p003v7.widget.AppCompatSpinner;
import android.support.p003v7.widget.AppCompatTextView;
import android.support.p003v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: android.support.v7.app.AppCompatViewInflater */
public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final String[] sClassPrefixList;
    private static final Map<String, Constructor<? extends View>> sConstructorMap;
    private static final Class<?>[] sConstructorSignature;
    private static final int[] sOnClickAttrs = {16843375};
    private final Object[] mConstructorArgs = new Object[2];

    public AppCompatViewInflater() {
    }

    static {
        Map<String, Constructor<? extends View>> map;
        Class<?>[] clsArr = new Class[2];
        clsArr[0] = Context.class;
        Class<?>[] clsArr2 = clsArr;
        clsArr2[1] = AttributeSet.class;
        sConstructorSignature = clsArr2;
        String[] strArr = new String[3];
        strArr[0] = "android.widget.";
        String[] strArr2 = strArr;
        strArr2[1] = "android.view.";
        String[] strArr3 = strArr2;
        strArr3[2] = "android.webkit.";
        sClassPrefixList = strArr3;
        new C1642ArrayMap();
        sConstructorMap = map;
    }

    /* access modifiers changed from: package-private */
    public final View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean inheritContext, boolean z, boolean z2, boolean z3) {
        View view2;
        View parent = view;
        String name = str;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        boolean readAndroidTheme = z;
        boolean readAppTheme = z2;
        boolean wrapContext = z3;
        Context originalContext = context2;
        if (inheritContext && parent != null) {
            context2 = parent.getContext();
        }
        if (readAndroidTheme || readAppTheme) {
            context2 = themifyContext(context2, attrs, readAndroidTheme, readAppTheme);
        }
        if (wrapContext) {
            context2 = TintContextWrapper.wrap(context2);
        }
        String str2 = name;
        boolean z4 = true;
        switch (str2.hashCode()) {
            case -1946472170:
                if (str2.equals("RatingBar")) {
                    z4 = true;
                    break;
                }
                break;
            case -1455429095:
                if (str2.equals("CheckedTextView")) {
                    z4 = true;
                    break;
                }
                break;
            case -1346021293:
                if (str2.equals("MultiAutoCompleteTextView")) {
                    z4 = true;
                    break;
                }
                break;
            case -938935918:
                if (str2.equals("TextView")) {
                    z4 = false;
                    break;
                }
                break;
            case -937446323:
                if (str2.equals("ImageButton")) {
                    z4 = true;
                    break;
                }
                break;
            case -658531749:
                if (str2.equals("SeekBar")) {
                    z4 = true;
                    break;
                }
                break;
            case -339785223:
                if (str2.equals("Spinner")) {
                    z4 = true;
                    break;
                }
                break;
            case 776382189:
                if (str2.equals("RadioButton")) {
                    z4 = true;
                    break;
                }
                break;
            case 1125864064:
                if (str2.equals("ImageView")) {
                    z4 = true;
                    break;
                }
                break;
            case 1413872058:
                if (str2.equals("AutoCompleteTextView")) {
                    z4 = true;
                    break;
                }
                break;
            case 1601505219:
                if (str2.equals("CheckBox")) {
                    z4 = true;
                    break;
                }
                break;
            case 1666676343:
                if (str2.equals("EditText")) {
                    z4 = true;
                    break;
                }
                break;
            case 2001146706:
                if (str2.equals("Button")) {
                    z4 = true;
                    break;
                }
                break;
        }
        switch (z4) {
            case false:
                view2 = createTextView(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createImageView(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createButton(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createEditText(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createSpinner(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createImageButton(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createCheckBox(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createRadioButton(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createCheckedTextView(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createAutoCompleteTextView(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createMultiAutoCompleteTextView(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createRatingBar(context2, attrs);
                verifyNotNull(view2, name);
                break;
            case true:
                view2 = createSeekBar(context2, attrs);
                verifyNotNull(view2, name);
                break;
            default:
                view2 = createView(context2, name, attrs);
                break;
        }
        if (view2 == null && originalContext != context2) {
            view2 = createViewFromTag(context2, name, attrs);
        }
        if (view2 != null) {
            checkOnClickListener(view2, attrs);
        }
        return view2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatTextView createTextView(Context context, AttributeSet attrs) {
        AppCompatTextView appCompatTextView;
        new AppCompatTextView(context, attrs);
        return appCompatTextView;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatImageView createImageView(Context context, AttributeSet attrs) {
        AppCompatImageView appCompatImageView;
        new AppCompatImageView(context, attrs);
        return appCompatImageView;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatButton createButton(Context context, AttributeSet attrs) {
        AppCompatButton appCompatButton;
        new AppCompatButton(context, attrs);
        return appCompatButton;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatEditText createEditText(Context context, AttributeSet attrs) {
        AppCompatEditText appCompatEditText;
        new AppCompatEditText(context, attrs);
        return appCompatEditText;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatSpinner createSpinner(Context context, AttributeSet attrs) {
        AppCompatSpinner appCompatSpinner;
        new AppCompatSpinner(context, attrs);
        return appCompatSpinner;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatImageButton createImageButton(Context context, AttributeSet attrs) {
        AppCompatImageButton appCompatImageButton;
        new AppCompatImageButton(context, attrs);
        return appCompatImageButton;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attrs) {
        AppCompatCheckBox appCompatCheckBox;
        new AppCompatCheckBox(context, attrs);
        return appCompatCheckBox;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attrs) {
        AppCompatRadioButton appCompatRadioButton;
        new AppCompatRadioButton(context, attrs);
        return appCompatRadioButton;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attrs) {
        AppCompatCheckedTextView appCompatCheckedTextView;
        new AppCompatCheckedTextView(context, attrs);
        return appCompatCheckedTextView;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attrs) {
        AppCompatAutoCompleteTextView appCompatAutoCompleteTextView;
        new AppCompatAutoCompleteTextView(context, attrs);
        return appCompatAutoCompleteTextView;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        AppCompatMultiAutoCompleteTextView appCompatMultiAutoCompleteTextView;
        new AppCompatMultiAutoCompleteTextView(context, attrs);
        return appCompatMultiAutoCompleteTextView;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatRatingBar createRatingBar(Context context, AttributeSet attrs) {
        AppCompatRatingBar appCompatRatingBar;
        new AppCompatRatingBar(context, attrs);
        return appCompatRatingBar;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatSeekBar createSeekBar(Context context, AttributeSet attrs) {
        AppCompatSeekBar appCompatSeekBar;
        new AppCompatSeekBar(context, attrs);
        return appCompatSeekBar;
    }

    private void verifyNotNull(View view, String str) {
        Throwable th;
        StringBuilder sb;
        String name = str;
        if (view == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append(getClass().getName()).append(" asked to inflate view for <").append(name).append(">, but returned null").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public View createView(Context context, String str, AttributeSet attributeSet) {
        Context context2 = context;
        String str2 = str;
        AttributeSet attributeSet2 = attributeSet;
        return null;
    }

    private View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        Context context2 = context;
        String name = str;
        AttributeSet attrs = attributeSet;
        if (name.equals("view")) {
            name = attrs.getAttributeValue((String) null, "class");
        }
        try {
            this.mConstructorArgs[0] = context2;
            this.mConstructorArgs[1] = attrs;
            if (-1 == name.indexOf(46)) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    View view = createViewByPrefix(context2, name, sClassPrefixList[i]);
                    if (view != null) {
                        View view2 = view;
                        this.mConstructorArgs[0] = null;
                        this.mConstructorArgs[1] = null;
                        return view2;
                    }
                }
                this.mConstructorArgs[0] = null;
                this.mConstructorArgs[1] = null;
                return null;
            }
            View createViewByPrefix = createViewByPrefix(context2, name, (String) null);
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
            return createViewByPrefix;
        } catch (Exception e) {
            Exception exc = e;
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
            throw th2;
        }
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        View.OnClickListener onClickListener;
        View view2 = view;
        AttributeSet attrs = attributeSet;
        Context context = view2.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view2)) {
            TypedArray a = context.obtainStyledAttributes(attrs, sOnClickAttrs);
            String handlerName = a.getString(0);
            if (handlerName != null) {
                new DeclaredOnClickListener(view2, handlerName);
                view2.setOnClickListener(onClickListener);
            }
            a.recycle();
        }
    }

    private View createViewByPrefix(Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        String str3;
        StringBuilder sb;
        Context context2 = context;
        String name = str;
        String prefix = str2;
        Constructor<? extends U> constructor = sConstructorMap.get(name);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context2.getClassLoader();
                if (prefix != null) {
                    new StringBuilder();
                    str3 = sb.append(prefix).append(name).toString();
                } else {
                    str3 = name;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                Constructor<? extends View> put = sConstructorMap.put(name, constructor);
            } catch (Exception e) {
                Exception exc = e;
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.mConstructorArgs);
    }

    private static Context themifyContext(Context context, AttributeSet attrs, boolean useAndroidTheme, boolean z) {
        Context context2;
        Context context3 = context;
        boolean useAppTheme = z;
        TypedArray a = context3.obtainStyledAttributes(attrs, C0425R.styleable.View, 0, 0);
        int themeId = 0;
        if (useAndroidTheme) {
            themeId = a.getResourceId(C0425R.styleable.View_android_theme, 0);
        }
        if (useAppTheme && themeId == 0) {
            themeId = a.getResourceId(C0425R.styleable.View_theme, 0);
            if (themeId != 0) {
                int i = Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        a.recycle();
        if (themeId != 0 && (!(context3 instanceof ContextThemeWrapper) || ((ContextThemeWrapper) context3).getThemeResId() != themeId)) {
            new ContextThemeWrapper(context3, themeId);
            context3 = context2;
        }
        return context3;
    }

    /* renamed from: android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener */
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(@NonNull View hostView, @NonNull String methodName) {
            this.mHostView = hostView;
            this.mMethodName = methodName;
        }

        public void onClick(@NonNull View view) {
            Throwable th;
            Throwable th2;
            View v = view;
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                Object invoke = this.mResolvedMethod.invoke(this.mResolvedContext, new Object[]{v});
            } catch (IllegalAccessException e) {
                IllegalAccessException e2 = e;
                Throwable th3 = th2;
                new IllegalStateException("Could not execute non-public method for android:onClick", e2);
                throw th3;
            } catch (InvocationTargetException e3) {
                InvocationTargetException e4 = e3;
                Throwable th4 = th;
                new IllegalStateException("Could not execute method for android:onClick", e4);
                throw th4;
            }
        }

        @NonNull
        private void resolveMethod(@Nullable Context context, @NonNull String str) {
            StringBuilder sb;
            String sb2;
            Throwable th;
            StringBuilder sb3;
            Context context2 = context;
            String str2 = str;
            while (context2 != null) {
                try {
                    if (!context2.isRestricted()) {
                        Method method = context2.getClass().getMethod(this.mMethodName, new Class[]{View.class});
                        if (method != null) {
                            this.mResolvedMethod = method;
                            this.mResolvedContext = context2;
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    NoSuchMethodException noSuchMethodException = e;
                }
                if (context2 instanceof ContextWrapper) {
                    context2 = ((ContextWrapper) context2).getBaseContext();
                } else {
                    context2 = null;
                }
            }
            int id = this.mHostView.getId();
            if (id == -1) {
                sb2 = "";
            } else {
                new StringBuilder();
                sb2 = sb.append(" with id '").append(this.mHostView.getContext().getResources().getResourceEntryName(id)).append("'").toString();
            }
            String idText = sb2;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb3.append("Could not find method ").append(this.mMethodName).append("(View) in a parent or ancestor Context for android:onClick ").append("attribute defined on view ").append(this.mHostView.getClass()).append(idText).toString());
            throw th2;
        }
    }
}
