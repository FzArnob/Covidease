package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.Space;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

final class IndicatorViewController {
    private static final int CAPTION_OPACITY_FADE_ANIMATION_DURATION = 167;
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    private static final int CAPTION_TRANSLATE_Y_ANIMATION_DURATION = 217;
    static final int COUNTER_INDEX = 2;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    @Nullable
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private int captionToShow;
    private final float captionTranslationYPx = ((float) this.context.getResources().getDimensionPixelSize(C0064R.dimen.design_textinput_caption_translate_y));
    private int captionViewsAdded;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    /* access modifiers changed from: private */
    public TextView errorView;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    static /* synthetic */ int access$002(IndicatorViewController x0, int x1) {
        int i = x1;
        int i2 = i;
        x0.captionDisplayed = i2;
        return i;
    }

    static /* synthetic */ Animator access$102(IndicatorViewController x0, Animator x1) {
        Animator animator = x1;
        Animator animator2 = animator;
        x0.captionAnimator = animator2;
        return animator;
    }

    public IndicatorViewController(TextInputLayout textInputLayout) {
        TextInputLayout textInputView2 = textInputLayout;
        this.context = textInputView2.getContext();
        this.textInputView = textInputView2;
    }

    /* access modifiers changed from: package-private */
    public void showHelper(CharSequence charSequence) {
        CharSequence helperText2 = charSequence;
        cancelCaptionAnimator();
        this.helperText = helperText2;
        this.helperTextView.setText(helperText2);
        if (this.captionDisplayed != 2) {
            this.captionToShow = 2;
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, helperText2));
    }

    /* access modifiers changed from: package-private */
    public void hideHelperText() {
        cancelCaptionAnimator();
        if (this.captionDisplayed == 2) {
            this.captionToShow = 0;
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, (CharSequence) null));
    }

    /* access modifiers changed from: package-private */
    public void showError(CharSequence charSequence) {
        CharSequence errorText2 = charSequence;
        cancelCaptionAnimator();
        this.errorText = errorText2;
        this.errorView.setText(errorText2);
        if (this.captionDisplayed != 1) {
            this.captionToShow = 1;
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, errorText2));
    }

    /* access modifiers changed from: package-private */
    public void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (!this.helperTextEnabled || TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 0;
            } else {
                this.captionToShow = 2;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, (CharSequence) null));
    }

    private boolean shouldAnimateCaptionView(TextView textView, @Nullable CharSequence captionText) {
        TextView captionView = textView;
        return ViewCompat.isLaidOut(this.textInputView) && this.textInputView.isEnabled() && (this.captionToShow != this.captionDisplayed || captionView == null || !TextUtils.equals(captionView.getText(), captionText));
    }

    private void updateCaptionViewsVisibility(int i, int i2, boolean z) {
        AnimatorSet animatorSet;
        List<Animator> list;
        Animator.AnimatorListener animatorListener;
        int captionToHide = i;
        int captionToShow2 = i2;
        boolean animate = z;
        if (animate) {
            new AnimatorSet();
            AnimatorSet captionAnimator2 = animatorSet;
            this.captionAnimator = captionAnimator2;
            new ArrayList<>();
            List<Animator> captionAnimatorList = list;
            createCaptionAnimators(captionAnimatorList, this.helperTextEnabled, this.helperTextView, 2, captionToHide, captionToShow2);
            createCaptionAnimators(captionAnimatorList, this.errorEnabled, this.errorView, 1, captionToHide, captionToShow2);
            AnimatorSetCompat.playTogether(captionAnimator2, captionAnimatorList);
            TextView captionViewToHide = getCaptionViewFromDisplayState(captionToHide);
            final int i3 = captionToShow2;
            final TextView textView = captionViewToHide;
            final int i4 = captionToHide;
            final TextView captionViewFromDisplayState = getCaptionViewFromDisplayState(captionToShow2);
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ IndicatorViewController this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    int access$002 = IndicatorViewController.access$002(this.this$0, i3);
                    Animator access$102 = IndicatorViewController.access$102(this.this$0, (Animator) null);
                    if (textView != null) {
                        textView.setVisibility(4);
                        if (i4 == 1 && this.this$0.errorView != null) {
                            this.this$0.errorView.setText((CharSequence) null);
                        }
                    }
                }

                public void onAnimationStart(Animator animator) {
                    Animator animator2 = animator;
                    if (captionViewFromDisplayState != null) {
                        captionViewFromDisplayState.setVisibility(0);
                    }
                }
            };
            captionAnimator2.addListener(animatorListener);
            captionAnimator2.start();
        } else {
            setCaptionViewVisibilities(captionToHide, captionToShow2);
        }
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateLabelState(animate);
        this.textInputView.updateTextInputBoxState();
    }

    private void setCaptionViewVisibilities(int i, int i2) {
        TextView captionViewDisplayed;
        TextView captionViewToShow;
        int captionToHide = i;
        int captionToShow2 = i2;
        if (captionToHide != captionToShow2) {
            if (!(captionToShow2 == 0 || (captionViewToShow = getCaptionViewFromDisplayState(captionToShow2)) == null)) {
                captionViewToShow.setVisibility(0);
                captionViewToShow.setAlpha(1.0f);
            }
            if (!(captionToHide == 0 || (captionViewDisplayed = getCaptionViewFromDisplayState(captionToHide)) == null)) {
                captionViewDisplayed.setVisibility(4);
                if (captionToHide == 1) {
                    captionViewDisplayed.setText((CharSequence) null);
                }
            }
            this.captionDisplayed = captionToShow2;
        }
    }

    private void createCaptionAnimators(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        List<Animator> captionAnimatorList = list;
        boolean captionEnabled = z;
        TextView captionView = textView;
        int captionState = i;
        int captionToHide = i2;
        int captionToShow2 = i3;
        if (captionView != null && captionEnabled) {
            if (captionState == captionToShow2 || captionState == captionToHide) {
                boolean add = captionAnimatorList.add(createCaptionOpacityAnimator(captionView, captionToShow2 == captionState));
                if (captionToShow2 == captionState) {
                    boolean add2 = captionAnimatorList.add(createCaptionTranslationYAnimator(captionView));
                }
            }
        }
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView captionView, boolean display) {
        ObjectAnimator opacityAnimator = ObjectAnimator.ofFloat(captionView, View.ALPHA, new float[]{display ? 1.0f : 0.0f});
        ObjectAnimator duration = opacityAnimator.setDuration(167);
        opacityAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return opacityAnimator;
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView captionView) {
        Property property = View.TRANSLATION_Y;
        float[] fArr = new float[2];
        fArr[0] = -this.captionTranslationYPx;
        float[] fArr2 = fArr;
        fArr2[1] = 0.0f;
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(captionView, property, fArr2);
        ObjectAnimator duration = translationYAnimator.setDuration(217);
        translationYAnimator.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        return translationYAnimator;
    }

    /* access modifiers changed from: package-private */
    public void cancelCaptionAnimator() {
        if (this.captionAnimator != null) {
            this.captionAnimator.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCaptionView(int i) {
        int index = i;
        return index == 0 || index == 1;
    }

    @Nullable
    private TextView getCaptionViewFromDisplayState(int captionDisplayState) {
        switch (captionDisplayState) {
            case 1:
                return this.errorView;
            case 2:
                return this.helperTextView;
            default:
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void adjustIndicatorPadding() {
        if (canAdjustIndicatorPadding()) {
            ViewCompat.setPaddingRelative(this.indicatorArea, ViewCompat.getPaddingStart(this.textInputView.getEditText()), 0, ViewCompat.getPaddingEnd(this.textInputView.getEditText()), 0);
        }
    }

    private boolean canAdjustIndicatorPadding() {
        return (this.indicatorArea == null || this.textInputView.getEditText() == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void addIndicator(TextView textView, int i) {
        LinearLayout linearLayout;
        FrameLayout frameLayout;
        ViewGroup.LayoutParams layoutParams;
        View view;
        ViewGroup.LayoutParams spacerLp;
        TextView indicator = textView;
        int index = i;
        if (this.indicatorArea == null && this.captionArea == null) {
            new LinearLayout(this.context);
            this.indicatorArea = linearLayout;
            this.indicatorArea.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            new FrameLayout(this.context);
            this.captionArea = frameLayout;
            new FrameLayout.LayoutParams(-2, -2);
            this.indicatorArea.addView(this.captionArea, -1, layoutParams);
            new Space(this.context);
            new LinearLayout.LayoutParams(0, 0, 1.0f);
            this.indicatorArea.addView(view, spacerLp);
            if (this.textInputView.getEditText() != null) {
                adjustIndicatorPadding();
            }
        }
        if (isCaptionView(index)) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(indicator);
            this.captionViewsAdded++;
        } else {
            this.indicatorArea.addView(indicator, index);
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    /* access modifiers changed from: package-private */
    public void removeIndicator(TextView textView, int i) {
        TextView indicator = textView;
        int index = i;
        if (this.indicatorArea != null) {
            if (!isCaptionView(index) || this.captionArea == null) {
                this.indicatorArea.removeView(indicator);
            } else {
                this.captionViewsAdded--;
                setViewGroupGoneIfEmpty(this.captionArea, this.captionViewsAdded);
                this.captionArea.removeView(indicator);
            }
            this.indicatorsAdded--;
            setViewGroupGoneIfEmpty(this.indicatorArea, this.indicatorsAdded);
        }
    }

    private void setViewGroupGoneIfEmpty(ViewGroup viewGroup, int indicatorsAdded2) {
        ViewGroup viewGroup2 = viewGroup;
        if (indicatorsAdded2 == 0) {
            viewGroup2.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void setErrorEnabled(boolean z) {
        TextView textView;
        boolean enabled = z;
        if (this.errorEnabled != enabled) {
            cancelCaptionAnimator();
            if (enabled) {
                new AppCompatTextView(this.context);
                this.errorView = textView;
                this.errorView.setId(C0064R.C0066id.textinput_error);
                if (this.typeface != null) {
                    this.errorView.setTypeface(this.typeface);
                }
                setErrorTextAppearance(this.errorTextAppearance);
                this.errorView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.errorView, 1);
                addIndicator(this.errorView, 0);
            } else {
                hideError();
                removeIndicator(this.errorView, 0);
                this.errorView = null;
                this.textInputView.updateEditTextBackground();
                this.textInputView.updateTextInputBoxState();
            }
            this.errorEnabled = enabled;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isHelperTextEnabled() {
        return this.helperTextEnabled;
    }

    /* access modifiers changed from: package-private */
    public void setHelperTextEnabled(boolean z) {
        TextView textView;
        boolean enabled = z;
        if (this.helperTextEnabled != enabled) {
            cancelCaptionAnimator();
            if (enabled) {
                new AppCompatTextView(this.context);
                this.helperTextView = textView;
                this.helperTextView.setId(C0064R.C0066id.textinput_helper_text);
                if (this.typeface != null) {
                    this.helperTextView.setTypeface(this.typeface);
                }
                this.helperTextView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.helperTextView, 1);
                setHelperTextAppearance(this.helperTextTextAppearance);
                addIndicator(this.helperTextView, 1);
            } else {
                hideHelperText();
                removeIndicator(this.helperTextView, 1);
                this.helperTextView = null;
                this.textInputView.updateEditTextBackground();
                this.textInputView.updateTextInputBoxState();
            }
            this.helperTextEnabled = enabled;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean errorIsDisplayed() {
        return isCaptionStateError(this.captionDisplayed);
    }

    /* access modifiers changed from: package-private */
    public boolean errorShouldBeShown() {
        return isCaptionStateError(this.captionToShow);
    }

    private boolean isCaptionStateError(int captionState) {
        return captionState == 1 && this.errorView != null && !TextUtils.isEmpty(this.errorText);
    }

    /* access modifiers changed from: package-private */
    public boolean helperTextIsDisplayed() {
        return isCaptionStateHelperText(this.captionDisplayed);
    }

    /* access modifiers changed from: package-private */
    public boolean helperTextShouldBeShown() {
        return isCaptionStateHelperText(this.captionToShow);
    }

    private boolean isCaptionStateHelperText(int captionState) {
        return captionState == 2 && this.helperTextView != null && !TextUtils.isEmpty(this.helperText);
    }

    /* access modifiers changed from: package-private */
    public CharSequence getErrorText() {
        return this.errorText;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getHelperText() {
        return this.helperText;
    }

    /* access modifiers changed from: package-private */
    public void setTypefaces(Typeface typeface2) {
        Typeface typeface3 = typeface2;
        if (typeface3 != this.typeface) {
            this.typeface = typeface3;
            setTextViewTypeface(this.errorView, typeface3);
            setTextViewTypeface(this.helperTextView, typeface3);
        }
    }

    private void setTextViewTypeface(@Nullable TextView textView, Typeface typeface2) {
        TextView captionView = textView;
        Typeface typeface3 = typeface2;
        if (captionView != null) {
            captionView.setTypeface(typeface3);
        }
    }

    /* access modifiers changed from: package-private */
    @ColorInt
    public int getErrorViewCurrentTextColor() {
        return this.errorView != null ? this.errorView.getCurrentTextColor() : -1;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ColorStateList getErrorViewTextColors() {
        return this.errorView != null ? this.errorView.getTextColors() : null;
    }

    /* access modifiers changed from: package-private */
    public void setErrorViewTextColor(@Nullable ColorStateList colorStateList) {
        ColorStateList textColors = colorStateList;
        if (this.errorView != null) {
            this.errorView.setTextColor(textColors);
        }
    }

    /* access modifiers changed from: package-private */
    public void setErrorTextAppearance(@StyleRes int i) {
        int resId = i;
        this.errorTextAppearance = resId;
        if (this.errorView != null) {
            this.textInputView.setTextAppearanceCompatWithErrorFallback(this.errorView, resId);
        }
    }

    /* access modifiers changed from: package-private */
    @ColorInt
    public int getHelperTextViewCurrentTextColor() {
        return this.helperTextView != null ? this.helperTextView.getCurrentTextColor() : -1;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ColorStateList getHelperTextViewColors() {
        return this.helperTextView != null ? this.helperTextView.getTextColors() : null;
    }

    /* access modifiers changed from: package-private */
    public void setHelperTextViewTextColor(@Nullable ColorStateList colorStateList) {
        ColorStateList textColors = colorStateList;
        if (this.helperTextView != null) {
            this.helperTextView.setTextColor(textColors);
        }
    }

    /* access modifiers changed from: package-private */
    public void setHelperTextAppearance(@StyleRes int i) {
        int resId = i;
        this.helperTextTextAppearance = resId;
        if (this.helperTextView != null) {
            TextViewCompat.setTextAppearance(this.helperTextView, resId);
        }
    }
}
