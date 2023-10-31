package android.support.transition;

import android.content.Context;
import android.util.AttributeSet;

public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        init();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Transition transition;
        Transition transition2;
        Transition transition3;
        TransitionSet ordering = setOrdering(1);
        new Fade(2);
        new ChangeBounds();
        new Fade(1);
        TransitionSet addTransition = addTransition(transition).addTransition(transition2).addTransition(transition3);
    }
}
