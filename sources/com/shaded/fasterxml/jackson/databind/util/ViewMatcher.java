package com.shaded.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class ViewMatcher {
    public abstract boolean isVisibleForView(Class<?> cls);

    public ViewMatcher() {
    }

    public static ViewMatcher construct(Class<?>[] clsArr) {
        ViewMatcher viewMatcher;
        ViewMatcher viewMatcher2;
        Class<?>[] clsArr2 = clsArr;
        if (clsArr2 == null) {
            return Empty.instance;
        }
        switch (clsArr2.length) {
            case 0:
                return Empty.instance;
            case 1:
                new Single(clsArr2[0]);
                return viewMatcher;
            default:
                new Multi(clsArr2);
                return viewMatcher2;
        }
    }

    private static final class Empty extends ViewMatcher implements Serializable {
        static final Empty instance;
        private static final long serialVersionUID = 1;

        private Empty() {
        }

        static {
            Empty empty;
            new Empty();
            instance = empty;
        }

        public boolean isVisibleForView(Class<?> cls) {
            Class<?> cls2 = cls;
            return false;
        }
    }

    private static final class Single extends ViewMatcher implements Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> _view;

        public Single(Class<?> cls) {
            this._view = cls;
        }

        public boolean isVisibleForView(Class<?> cls) {
            Class<?> cls2 = cls;
            return cls2 == this._view || this._view.isAssignableFrom(cls2);
        }
    }

    private static final class Multi extends ViewMatcher implements Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?>[] _views;

        public Multi(Class<?>[] clsArr) {
            this._views = clsArr;
        }

        public boolean isVisibleForView(Class<?> cls) {
            Class<?> cls2 = cls;
            int length = this._views.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls3 = this._views[i];
                if (cls2 == cls3 || cls3.isAssignableFrom(cls2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
