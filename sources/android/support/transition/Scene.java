package android.support.transition;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene {
    private Context mContext;
    private Runnable mEnterAction;
    private Runnable mExitAction;
    private View mLayout;
    private int mLayoutId = -1;
    private ViewGroup mSceneRoot;

    @NonNull
    public static Scene getSceneForLayout(@NonNull ViewGroup viewGroup, @LayoutRes int i, @NonNull Context context) {
        Scene scene;
        SparseArray sparseArray;
        ViewGroup sceneRoot = viewGroup;
        int layoutId = i;
        Context context2 = context;
        SparseArray sparseArray2 = (SparseArray) sceneRoot.getTag(C0211R.C0213id.transition_scene_layoutid_cache);
        if (sparseArray2 == null) {
            new SparseArray();
            sparseArray2 = sparseArray;
            sceneRoot.setTag(C0211R.C0213id.transition_scene_layoutid_cache, sparseArray2);
        }
        Scene scene2 = (Scene) sparseArray2.get(layoutId);
        if (scene2 != null) {
            return scene2;
        }
        new Scene(sceneRoot, layoutId, context2);
        Scene scene3 = scene;
        sparseArray2.put(layoutId, scene3);
        return scene3;
    }

    public Scene(@NonNull ViewGroup sceneRoot) {
        this.mSceneRoot = sceneRoot;
    }

    private Scene(ViewGroup sceneRoot, int layoutId, Context context) {
        this.mContext = context;
        this.mSceneRoot = sceneRoot;
        this.mLayoutId = layoutId;
    }

    public Scene(@NonNull ViewGroup sceneRoot, @NonNull View layout) {
        this.mSceneRoot = sceneRoot;
        this.mLayout = layout;
    }

    @NonNull
    public ViewGroup getSceneRoot() {
        return this.mSceneRoot;
    }

    public void exit() {
        if (getCurrentScene(this.mSceneRoot) == this && this.mExitAction != null) {
            this.mExitAction.run();
        }
    }

    public void enter() {
        if (this.mLayoutId > 0 || this.mLayout != null) {
            getSceneRoot().removeAllViews();
            if (this.mLayoutId > 0) {
                View inflate = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mSceneRoot);
            } else {
                this.mSceneRoot.addView(this.mLayout);
            }
        }
        if (this.mEnterAction != null) {
            this.mEnterAction.run();
        }
        setCurrentScene(this.mSceneRoot, this);
    }

    static void setCurrentScene(View view, Scene scene) {
        view.setTag(C0211R.C0213id.transition_current_scene, scene);
    }

    static Scene getCurrentScene(View view) {
        return (Scene) view.getTag(C0211R.C0213id.transition_current_scene);
    }

    public void setEnterAction(@Nullable Runnable action) {
        Runnable runnable = action;
        this.mEnterAction = runnable;
    }

    public void setExitAction(@Nullable Runnable action) {
        Runnable runnable = action;
        this.mExitAction = runnable;
    }

    /* access modifiers changed from: package-private */
    public boolean isCreatedFromLayoutResource() {
        return this.mLayoutId > 0;
    }
}
