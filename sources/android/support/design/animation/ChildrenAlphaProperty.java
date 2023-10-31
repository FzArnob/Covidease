package android.support.design.animation;

import android.support.design.C0064R;
import android.util.Property;
import android.view.ViewGroup;

public class ChildrenAlphaProperty extends Property<ViewGroup, Float> {
    public static final Property<ViewGroup, Float> CHILDREN_ALPHA;

    static {
        Property<ViewGroup, Float> property;
        new ChildrenAlphaProperty("childrenAlpha");
        CHILDREN_ALPHA = property;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ChildrenAlphaProperty(String name) {
        super(Float.class, name);
    }

    public Float get(ViewGroup object) {
        Float alpha = (Float) object.getTag(C0064R.C0066id.mtrl_internal_children_alpha_tag);
        if (alpha != null) {
            return alpha;
        }
        return Float.valueOf(1.0f);
    }

    public void set(ViewGroup viewGroup, Float value) {
        ViewGroup object = viewGroup;
        float alpha = value.floatValue();
        object.setTag(C0064R.C0066id.mtrl_internal_children_alpha_tag, Float.valueOf(alpha));
        int count = object.getChildCount();
        for (int i = 0; i < count; i++) {
            object.getChildAt(i).setAlpha(alpha);
        }
    }
}
