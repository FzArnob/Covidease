package android.support.p003v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: android.support.v7.widget.DefaultItemAnimator */
public class DefaultItemAnimator extends SimpleItemAnimator {
    private static final boolean DEBUG = false;
    private static TimeInterpolator sDefaultInterpolator;
    ArrayList<RecyclerView.ViewHolder> mAddAnimations;
    ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList;
    ArrayList<RecyclerView.ViewHolder> mChangeAnimations;
    ArrayList<ArrayList<ChangeInfo>> mChangesList;
    ArrayList<RecyclerView.ViewHolder> mMoveAnimations;
    ArrayList<ArrayList<MoveInfo>> mMovesList;
    private ArrayList<RecyclerView.ViewHolder> mPendingAdditions;
    private ArrayList<ChangeInfo> mPendingChanges;
    private ArrayList<MoveInfo> mPendingMoves;
    private ArrayList<RecyclerView.ViewHolder> mPendingRemovals;
    ArrayList<RecyclerView.ViewHolder> mRemoveAnimations;

    public DefaultItemAnimator() {
        ArrayList<RecyclerView.ViewHolder> arrayList;
        ArrayList<RecyclerView.ViewHolder> arrayList2;
        ArrayList<MoveInfo> arrayList3;
        ArrayList<ChangeInfo> arrayList4;
        ArrayList<ArrayList<RecyclerView.ViewHolder>> arrayList5;
        ArrayList<ArrayList<MoveInfo>> arrayList6;
        ArrayList<ArrayList<ChangeInfo>> arrayList7;
        ArrayList<RecyclerView.ViewHolder> arrayList8;
        ArrayList<RecyclerView.ViewHolder> arrayList9;
        ArrayList<RecyclerView.ViewHolder> arrayList10;
        ArrayList<RecyclerView.ViewHolder> arrayList11;
        new ArrayList<>();
        this.mPendingRemovals = arrayList;
        new ArrayList<>();
        this.mPendingAdditions = arrayList2;
        new ArrayList<>();
        this.mPendingMoves = arrayList3;
        new ArrayList<>();
        this.mPendingChanges = arrayList4;
        new ArrayList<>();
        this.mAdditionsList = arrayList5;
        new ArrayList<>();
        this.mMovesList = arrayList6;
        new ArrayList<>();
        this.mChangesList = arrayList7;
        new ArrayList<>();
        this.mAddAnimations = arrayList8;
        new ArrayList<>();
        this.mMoveAnimations = arrayList9;
        new ArrayList<>();
        this.mRemoveAnimations = arrayList10;
        new ArrayList<>();
        this.mChangeAnimations = arrayList11;
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator$MoveInfo */
    private static class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        MoveInfo(RecyclerView.ViewHolder holder2, int fromX2, int fromY2, int toX2, int toY2) {
            this.holder = holder2;
            this.fromX = fromX2;
            this.fromY = fromY2;
            this.toX = toX2;
            this.toY = toY2;
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator$ChangeInfo */
    private static class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder oldHolder2, RecyclerView.ViewHolder newHolder2) {
            this.oldHolder = oldHolder2;
            this.newHolder = newHolder2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        ChangeInfo(RecyclerView.ViewHolder oldHolder2, RecyclerView.ViewHolder newHolder2, int fromX2, int fromY2, int toX2, int toY2) {
            this(oldHolder2, newHolder2);
            this.fromX = fromX2;
            this.fromY = fromY2;
            this.toX = toX2;
            this.toY = toY2;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("ChangeInfo{oldHolder=").append(this.oldHolder).append(", newHolder=").append(this.newHolder).append(", fromX=").append(this.fromX).append(", fromY=").append(this.fromY).append(", toX=").append(this.toX).append(", toY=").append(this.toY).append('}').toString();
        }
    }

    public void runPendingAnimations() {
        ArrayList arrayList;
        Runnable runnable;
        ArrayList arrayList2;
        Runnable runnable2;
        ArrayList arrayList3;
        Runnable runnable3;
        boolean removalsPending = !this.mPendingRemovals.isEmpty();
        boolean movesPending = !this.mPendingMoves.isEmpty();
        boolean changesPending = !this.mPendingChanges.isEmpty();
        boolean additionsPending = !this.mPendingAdditions.isEmpty();
        if (removalsPending || movesPending || additionsPending || changesPending) {
            Iterator<RecyclerView.ViewHolder> it = this.mPendingRemovals.iterator();
            while (it.hasNext()) {
                animateRemoveImpl(it.next());
            }
            this.mPendingRemovals.clear();
            if (movesPending) {
                new ArrayList();
                ArrayList arrayList4 = arrayList3;
                boolean addAll = arrayList4.addAll(this.mPendingMoves);
                boolean add = this.mMovesList.add(arrayList4);
                this.mPendingMoves.clear();
                final ArrayList arrayList5 = arrayList4;
                new Runnable(this) {
                    final /* synthetic */ DefaultItemAnimator this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        Iterator it = arrayList5.iterator();
                        while (it.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo) it.next();
                            this.this$0.animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }
                        arrayList5.clear();
                        boolean remove = this.this$0.mMovesList.remove(arrayList5);
                    }
                };
                Runnable mover = runnable3;
                if (removalsPending) {
                    ViewCompat.postOnAnimationDelayed(((MoveInfo) arrayList4.get(0)).holder.itemView, mover, getRemoveDuration());
                } else {
                    mover.run();
                }
            }
            if (changesPending) {
                new ArrayList();
                ArrayList arrayList6 = arrayList2;
                boolean addAll2 = arrayList6.addAll(this.mPendingChanges);
                boolean add2 = this.mChangesList.add(arrayList6);
                this.mPendingChanges.clear();
                final ArrayList arrayList7 = arrayList6;
                new Runnable(this) {
                    final /* synthetic */ DefaultItemAnimator this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        Iterator it = arrayList7.iterator();
                        while (it.hasNext()) {
                            this.this$0.animateChangeImpl((ChangeInfo) it.next());
                        }
                        arrayList7.clear();
                        boolean remove = this.this$0.mChangesList.remove(arrayList7);
                    }
                };
                Runnable changer = runnable2;
                if (removalsPending) {
                    ViewCompat.postOnAnimationDelayed(((ChangeInfo) arrayList6.get(0)).oldHolder.itemView, changer, getRemoveDuration());
                } else {
                    changer.run();
                }
            }
            if (additionsPending) {
                new ArrayList();
                ArrayList arrayList8 = arrayList;
                boolean addAll3 = arrayList8.addAll(this.mPendingAdditions);
                boolean add3 = this.mAdditionsList.add(arrayList8);
                this.mPendingAdditions.clear();
                final ArrayList arrayList9 = arrayList8;
                new Runnable(this) {
                    final /* synthetic */ DefaultItemAnimator this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        Iterator it = arrayList9.iterator();
                        while (it.hasNext()) {
                            this.this$0.animateAddImpl((RecyclerView.ViewHolder) it.next());
                        }
                        arrayList9.clear();
                        boolean remove = this.this$0.mAdditionsList.remove(arrayList9);
                    }
                };
                Runnable adder = runnable;
                if (removalsPending || movesPending || changesPending) {
                    ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder) arrayList8.get(0)).itemView, adder, (removalsPending ? getRemoveDuration() : 0) + Math.max(movesPending ? getMoveDuration() : 0, changesPending ? getChangeDuration() : 0));
                } else {
                    adder.run();
                }
            }
        }
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder holder = viewHolder;
        resetAnimation(holder);
        boolean add = this.mPendingRemovals.add(holder);
        return true;
    }

    private void animateRemoveImpl(RecyclerView.ViewHolder viewHolder) {
        Animator.AnimatorListener animatorListener;
        RecyclerView.ViewHolder holder = viewHolder;
        View view = holder.itemView;
        ViewPropertyAnimator animation = view.animate();
        boolean add = this.mRemoveAnimations.add(holder);
        final RecyclerView.ViewHolder viewHolder2 = holder;
        final ViewPropertyAnimator viewPropertyAnimator = animation;
        final View view2 = view;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ DefaultItemAnimator this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.dispatchRemoveStarting(viewHolder2);
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                ViewPropertyAnimator listener = viewPropertyAnimator.setListener((Animator.AnimatorListener) null);
                view2.setAlpha(1.0f);
                this.this$0.dispatchRemoveFinished(viewHolder2);
                boolean remove = this.this$0.mRemoveAnimations.remove(viewHolder2);
                this.this$0.dispatchFinishedWhenDone();
            }
        };
        animation.setDuration(getRemoveDuration()).alpha(0.0f).setListener(animatorListener).start();
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder holder = viewHolder;
        resetAnimation(holder);
        holder.itemView.setAlpha(0.0f);
        boolean add = this.mPendingAdditions.add(holder);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void animateAddImpl(RecyclerView.ViewHolder viewHolder) {
        Animator.AnimatorListener animatorListener;
        RecyclerView.ViewHolder holder = viewHolder;
        View view = holder.itemView;
        ViewPropertyAnimator animation = view.animate();
        boolean add = this.mAddAnimations.add(holder);
        final RecyclerView.ViewHolder viewHolder2 = holder;
        final View view2 = view;
        final ViewPropertyAnimator viewPropertyAnimator = animation;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ DefaultItemAnimator this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.dispatchAddStarting(viewHolder2);
            }

            public void onAnimationCancel(Animator animator) {
                Animator animator2 = animator;
                view2.setAlpha(1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                ViewPropertyAnimator listener = viewPropertyAnimator.setListener((Animator.AnimatorListener) null);
                this.this$0.dispatchAddFinished(viewHolder2);
                boolean remove = this.this$0.mAddAnimations.remove(viewHolder2);
                this.this$0.dispatchFinishedWhenDone();
            }
        };
        animation.alpha(1.0f).setDuration(getAddDuration()).setListener(animatorListener).start();
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int fromX, int fromY, int i, int i2) {
        Object obj;
        RecyclerView.ViewHolder holder = viewHolder;
        int toX = i;
        int toY = i2;
        View view = holder.itemView;
        int fromX2 = fromX + ((int) holder.itemView.getTranslationX());
        int fromY2 = fromY + ((int) holder.itemView.getTranslationY());
        resetAnimation(holder);
        int deltaX = toX - fromX2;
        int deltaY = toY - fromY2;
        if (deltaX == 0 && deltaY == 0) {
            dispatchMoveFinished(holder);
            return false;
        }
        if (deltaX != 0) {
            view.setTranslationX((float) (-deltaX));
        }
        if (deltaY != 0) {
            view.setTranslationY((float) (-deltaY));
        }
        new MoveInfo(holder, fromX2, fromY2, toX, toY);
        boolean add = this.mPendingMoves.add(obj);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void animateMoveImpl(RecyclerView.ViewHolder viewHolder, int fromX, int fromY, int toX, int toY) {
        Animator.AnimatorListener animatorListener;
        RecyclerView.ViewHolder holder = viewHolder;
        View view = holder.itemView;
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        if (deltaX != 0) {
            ViewPropertyAnimator translationX = view.animate().translationX(0.0f);
        }
        if (deltaY != 0) {
            ViewPropertyAnimator translationY = view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animation = view.animate();
        boolean add = this.mMoveAnimations.add(holder);
        final RecyclerView.ViewHolder viewHolder2 = holder;
        final int i = deltaX;
        final View view2 = view;
        final int i2 = deltaY;
        final ViewPropertyAnimator viewPropertyAnimator = animation;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ DefaultItemAnimator this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.dispatchMoveStarting(viewHolder2);
            }

            public void onAnimationCancel(Animator animator) {
                Animator animator2 = animator;
                if (i != 0) {
                    view2.setTranslationX(0.0f);
                }
                if (i2 != 0) {
                    view2.setTranslationY(0.0f);
                }
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                ViewPropertyAnimator listener = viewPropertyAnimator.setListener((Animator.AnimatorListener) null);
                this.this$0.dispatchMoveFinished(viewHolder2);
                boolean remove = this.this$0.mMoveAnimations.remove(viewHolder2);
                this.this$0.dispatchFinishedWhenDone();
            }
        };
        animation.setDuration(getMoveDuration()).setListener(animatorListener).start();
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        Object obj;
        RecyclerView.ViewHolder oldHolder = viewHolder;
        RecyclerView.ViewHolder newHolder = viewHolder2;
        int fromX = i;
        int fromY = i2;
        int toX = i3;
        int toY = i4;
        if (oldHolder == newHolder) {
            return animateMove(oldHolder, fromX, fromY, toX, toY);
        }
        float prevTranslationX = oldHolder.itemView.getTranslationX();
        float prevTranslationY = oldHolder.itemView.getTranslationY();
        float prevAlpha = oldHolder.itemView.getAlpha();
        resetAnimation(oldHolder);
        int deltaX = (int) (((float) (toX - fromX)) - prevTranslationX);
        int deltaY = (int) (((float) (toY - fromY)) - prevTranslationY);
        oldHolder.itemView.setTranslationX(prevTranslationX);
        oldHolder.itemView.setTranslationY(prevTranslationY);
        oldHolder.itemView.setAlpha(prevAlpha);
        if (newHolder != null) {
            resetAnimation(newHolder);
            newHolder.itemView.setTranslationX((float) (-deltaX));
            newHolder.itemView.setTranslationY((float) (-deltaY));
            newHolder.itemView.setAlpha(0.0f);
        }
        new ChangeInfo(oldHolder, newHolder, fromX, fromY, toX, toY);
        boolean add = this.mPendingChanges.add(obj);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void animateChangeImpl(ChangeInfo changeInfo) {
        Animator.AnimatorListener animatorListener;
        Animator.AnimatorListener animatorListener2;
        ChangeInfo changeInfo2 = changeInfo;
        RecyclerView.ViewHolder holder = changeInfo2.oldHolder;
        View view = holder == null ? null : holder.itemView;
        RecyclerView.ViewHolder newHolder = changeInfo2.newHolder;
        View newView = newHolder != null ? newHolder.itemView : null;
        if (view != null) {
            ViewPropertyAnimator oldViewAnim = view.animate().setDuration(getChangeDuration());
            boolean add = this.mChangeAnimations.add(changeInfo2.oldHolder);
            ViewPropertyAnimator translationX = oldViewAnim.translationX((float) (changeInfo2.toX - changeInfo2.fromX));
            ViewPropertyAnimator translationY = oldViewAnim.translationY((float) (changeInfo2.toY - changeInfo2.fromY));
            final ChangeInfo changeInfo3 = changeInfo2;
            final ViewPropertyAnimator viewPropertyAnimator = oldViewAnim;
            final View view2 = view;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ DefaultItemAnimator this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationStart(Animator animator) {
                    Animator animator2 = animator;
                    this.this$0.dispatchChangeStarting(changeInfo3.oldHolder, true);
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    ViewPropertyAnimator listener = viewPropertyAnimator.setListener((Animator.AnimatorListener) null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    this.this$0.dispatchChangeFinished(changeInfo3.oldHolder, true);
                    boolean remove = this.this$0.mChangeAnimations.remove(changeInfo3.oldHolder);
                    this.this$0.dispatchFinishedWhenDone();
                }
            };
            oldViewAnim.alpha(0.0f).setListener(animatorListener2).start();
        }
        if (newView != null) {
            ViewPropertyAnimator newViewAnimation = newView.animate();
            boolean add2 = this.mChangeAnimations.add(changeInfo2.newHolder);
            final ChangeInfo changeInfo4 = changeInfo2;
            final ViewPropertyAnimator viewPropertyAnimator2 = newViewAnimation;
            final View view3 = newView;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ DefaultItemAnimator this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationStart(Animator animator) {
                    Animator animator2 = animator;
                    this.this$0.dispatchChangeStarting(changeInfo4.newHolder, false);
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    ViewPropertyAnimator listener = viewPropertyAnimator2.setListener((Animator.AnimatorListener) null);
                    view3.setAlpha(1.0f);
                    view3.setTranslationX(0.0f);
                    view3.setTranslationY(0.0f);
                    this.this$0.dispatchChangeFinished(changeInfo4.newHolder, false);
                    boolean remove = this.this$0.mChangeAnimations.remove(changeInfo4.newHolder);
                    this.this$0.dispatchFinishedWhenDone();
                }
            };
            newViewAnimation.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(animatorListener).start();
        }
    }

    private void endChangeAnimation(List<ChangeInfo> list, RecyclerView.ViewHolder viewHolder) {
        List<ChangeInfo> infoList = list;
        RecyclerView.ViewHolder item = viewHolder;
        for (int i = infoList.size() - 1; i >= 0; i--) {
            ChangeInfo changeInfo = infoList.get(i);
            if (endChangeAnimationIfNecessary(changeInfo, item) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                boolean remove = infoList.remove(changeInfo);
            }
        }
    }

    private void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
        ChangeInfo changeInfo2 = changeInfo;
        if (changeInfo2.oldHolder != null) {
            boolean endChangeAnimationIfNecessary = endChangeAnimationIfNecessary(changeInfo2, changeInfo2.oldHolder);
        }
        if (changeInfo2.newHolder != null) {
            boolean endChangeAnimationIfNecessary2 = endChangeAnimationIfNecessary(changeInfo2, changeInfo2.newHolder);
        }
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        ChangeInfo changeInfo2 = changeInfo;
        RecyclerView.ViewHolder item = viewHolder;
        boolean oldItem = false;
        if (changeInfo2.newHolder == item) {
            changeInfo2.newHolder = null;
        } else if (changeInfo2.oldHolder != item) {
            return false;
        } else {
            changeInfo2.oldHolder = null;
            oldItem = true;
        }
        item.itemView.setAlpha(1.0f);
        item.itemView.setTranslationX(0.0f);
        item.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(item, oldItem);
        return true;
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder item = viewHolder;
        View view = item.itemView;
        view.animate().cancel();
        for (int i = this.mPendingMoves.size() - 1; i >= 0; i--) {
            if (this.mPendingMoves.get(i).holder == item) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchMoveFinished(item);
                MoveInfo remove = this.mPendingMoves.remove(i);
            }
        }
        endChangeAnimation(this.mPendingChanges, item);
        if (this.mPendingRemovals.remove(item)) {
            view.setAlpha(1.0f);
            dispatchRemoveFinished(item);
        }
        if (this.mPendingAdditions.remove(item)) {
            view.setAlpha(1.0f);
            dispatchAddFinished(item);
        }
        for (int i2 = this.mChangesList.size() - 1; i2 >= 0; i2--) {
            ArrayList<ChangeInfo> changes = this.mChangesList.get(i2);
            endChangeAnimation(changes, item);
            if (changes.isEmpty()) {
                ArrayList<ChangeInfo> remove2 = this.mChangesList.remove(i2);
            }
        }
        for (int i3 = this.mMovesList.size() - 1; i3 >= 0; i3--) {
            ArrayList<MoveInfo> moves = this.mMovesList.get(i3);
            int j = moves.size() - 1;
            while (true) {
                if (j < 0) {
                    break;
                } else if (moves.get(j).holder == item) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchMoveFinished(item);
                    MoveInfo remove3 = moves.remove(j);
                    if (moves.isEmpty()) {
                        ArrayList<MoveInfo> remove4 = this.mMovesList.remove(i3);
                    }
                } else {
                    j--;
                }
            }
        }
        for (int i4 = this.mAdditionsList.size() - 1; i4 >= 0; i4--) {
            ArrayList<RecyclerView.ViewHolder> additions = this.mAdditionsList.get(i4);
            if (additions.remove(item)) {
                view.setAlpha(1.0f);
                dispatchAddFinished(item);
                if (additions.isEmpty()) {
                    ArrayList<RecyclerView.ViewHolder> remove5 = this.mAdditionsList.remove(i4);
                }
            }
        }
        if (this.mRemoveAnimations.remove(item)) {
        }
        if (this.mAddAnimations.remove(item)) {
        }
        if (this.mChangeAnimations.remove(item)) {
        }
        if (this.mMoveAnimations.remove(item)) {
        }
        dispatchFinishedWhenDone();
    }

    private void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        ValueAnimator valueAnimator;
        RecyclerView.ViewHolder holder = viewHolder;
        if (sDefaultInterpolator == null) {
            new ValueAnimator();
            sDefaultInterpolator = valueAnimator.getInterpolator();
        }
        ViewPropertyAnimator interpolator = holder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(holder);
    }

    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimations() {
        for (int i = this.mPendingMoves.size() - 1; i >= 0; i--) {
            MoveInfo item = this.mPendingMoves.get(i);
            View view = item.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchMoveFinished(item.holder);
            MoveInfo remove = this.mPendingMoves.remove(i);
        }
        for (int i2 = this.mPendingRemovals.size() - 1; i2 >= 0; i2--) {
            dispatchRemoveFinished(this.mPendingRemovals.get(i2));
            RecyclerView.ViewHolder remove2 = this.mPendingRemovals.remove(i2);
        }
        for (int i3 = this.mPendingAdditions.size() - 1; i3 >= 0; i3--) {
            RecyclerView.ViewHolder item2 = this.mPendingAdditions.get(i3);
            item2.itemView.setAlpha(1.0f);
            dispatchAddFinished(item2);
            RecyclerView.ViewHolder remove3 = this.mPendingAdditions.remove(i3);
        }
        for (int i4 = this.mPendingChanges.size() - 1; i4 >= 0; i4--) {
            endChangeAnimationIfNecessary(this.mPendingChanges.get(i4));
        }
        this.mPendingChanges.clear();
        if (isRunning()) {
            for (int i5 = this.mMovesList.size() - 1; i5 >= 0; i5--) {
                ArrayList<MoveInfo> moves = this.mMovesList.get(i5);
                for (int j = moves.size() - 1; j >= 0; j--) {
                    MoveInfo moveInfo = moves.get(j);
                    View view2 = moveInfo.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchMoveFinished(moveInfo.holder);
                    MoveInfo remove4 = moves.remove(j);
                    if (moves.isEmpty()) {
                        boolean remove5 = this.mMovesList.remove(moves);
                    }
                }
            }
            for (int i6 = this.mAdditionsList.size() - 1; i6 >= 0; i6--) {
                ArrayList<RecyclerView.ViewHolder> additions = this.mAdditionsList.get(i6);
                for (int j2 = additions.size() - 1; j2 >= 0; j2--) {
                    RecyclerView.ViewHolder item3 = additions.get(j2);
                    item3.itemView.setAlpha(1.0f);
                    dispatchAddFinished(item3);
                    RecyclerView.ViewHolder remove6 = additions.remove(j2);
                    if (additions.isEmpty()) {
                        boolean remove7 = this.mAdditionsList.remove(additions);
                    }
                }
            }
            for (int i7 = this.mChangesList.size() - 1; i7 >= 0; i7--) {
                ArrayList<ChangeInfo> changes = this.mChangesList.get(i7);
                for (int j3 = changes.size() - 1; j3 >= 0; j3--) {
                    endChangeAnimationIfNecessary(changes.get(j3));
                    if (changes.isEmpty()) {
                        boolean remove8 = this.mChangesList.remove(changes);
                    }
                }
            }
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            dispatchAnimationsFinished();
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelAll(List<RecyclerView.ViewHolder> list) {
        List<RecyclerView.ViewHolder> viewHolders = list;
        for (int i = viewHolders.size() - 1; i >= 0; i--) {
            viewHolders.get(i).itemView.animate().cancel();
        }
    }

    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> list) {
        List<Object> payloads = list;
        return !payloads.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }
}
