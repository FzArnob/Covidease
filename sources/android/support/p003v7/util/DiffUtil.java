package android.support.p003v7.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p003v7.widget.RecyclerView;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: android.support.v7.util.DiffUtil */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR;

    private DiffUtil() {
    }

    static {
        Comparator<Snake> comparator;
        new Comparator<Snake>() {
            public int compare(Snake snake, Snake snake2) {
                Snake o1 = snake;
                Snake o2 = snake2;
                int cmpX = o1.f40x - o2.f40x;
                return cmpX == 0 ? o1.f41y - o2.f41y : cmpX;
            }
        };
        SNAKE_COMPARATOR = comparator;
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback cb) {
        return calculateDiff(cb, true);
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback, boolean z) {
        List<Snake> list;
        List<Range> list2;
        Object obj;
        List<Range> list3;
        DiffResult diffResult;
        Range remove;
        Range range;
        Callback cb = callback;
        boolean detectMoves = z;
        int oldSize = cb.getOldListSize();
        int newSize = cb.getNewListSize();
        new ArrayList<>();
        List<Snake> snakes = list;
        new ArrayList<>();
        List<Range> stack = list2;
        new Range(0, oldSize, 0, newSize);
        boolean add = stack.add(obj);
        int max = oldSize + newSize + Math.abs(oldSize - newSize);
        int[] forward = new int[(max * 2)];
        int[] backward = new int[(max * 2)];
        new ArrayList<>();
        List<Range> rangePool = list3;
        while (!stack.isEmpty()) {
            Range range2 = stack.remove(stack.size() - 1);
            Snake snake = diffPartial(cb, range2.oldListStart, range2.oldListEnd, range2.newListStart, range2.newListEnd, forward, backward, max);
            if (snake != null) {
                if (snake.size > 0) {
                    boolean add2 = snakes.add(snake);
                }
                snake.f40x += range2.oldListStart;
                snake.f41y += range2.newListStart;
                if (rangePool.isEmpty()) {
                    remove = range;
                    new Range();
                } else {
                    remove = rangePool.remove(rangePool.size() - 1);
                }
                Range left = remove;
                left.oldListStart = range2.oldListStart;
                left.newListStart = range2.newListStart;
                if (snake.reverse) {
                    left.oldListEnd = snake.f40x;
                    left.newListEnd = snake.f41y;
                } else if (snake.removal) {
                    left.oldListEnd = snake.f40x - 1;
                    left.newListEnd = snake.f41y;
                } else {
                    left.oldListEnd = snake.f40x;
                    left.newListEnd = snake.f41y - 1;
                }
                boolean add3 = stack.add(left);
                Range right = range2;
                if (!snake.reverse) {
                    right.oldListStart = snake.f40x + snake.size;
                    right.newListStart = snake.f41y + snake.size;
                } else if (snake.removal) {
                    right.oldListStart = snake.f40x + snake.size + 1;
                    right.newListStart = snake.f41y + snake.size;
                } else {
                    right.oldListStart = snake.f40x + snake.size;
                    right.newListStart = snake.f41y + snake.size + 1;
                }
                boolean add4 = stack.add(right);
            } else {
                boolean add5 = rangePool.add(range2);
            }
        }
        Collections.sort(snakes, SNAKE_COMPARATOR);
        new DiffResult(cb, snakes, forward, backward, detectMoves);
        return diffResult;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.p003v7.util.DiffUtil.Snake diffPartial(android.support.p003v7.util.DiffUtil.Callback r28, int r29, int r30, int r31, int r32, int[] r33, int[] r34, int r35) {
        /*
            r2 = r28
            r3 = r29
            r4 = r30
            r5 = r31
            r6 = r32
            r7 = r33
            r8 = r34
            r9 = r35
            r22 = r4
            r23 = r3
            int r22 = r22 - r23
            r10 = r22
            r22 = r6
            r23 = r5
            int r22 = r22 - r23
            r11 = r22
            r22 = r4
            r23 = r3
            int r22 = r22 - r23
            r23 = 1
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x003c
            r22 = r6
            r23 = r5
            int r22 = r22 - r23
            r23 = 1
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x0041
        L_0x003c:
            r22 = 0
            r2 = r22
        L_0x0040:
            return r2
        L_0x0041:
            r22 = r10
            r23 = r11
            int r22 = r22 - r23
            r12 = r22
            r22 = r10
            r23 = r11
            int r22 = r22 + r23
            r23 = 1
            int r22 = r22 + 1
            r23 = 2
            int r22 = r22 / 2
            r13 = r22
            r22 = r7
            r23 = r9
            r24 = r13
            int r23 = r23 - r24
            r24 = 1
            int r23 = r23 + -1
            r24 = r9
            r25 = r13
            int r24 = r24 + r25
            r25 = 1
            int r24 = r24 + 1
            r25 = 0
            java.util.Arrays.fill(r22, r23, r24, r25)
            r22 = r8
            r23 = r9
            r24 = r13
            int r23 = r23 - r24
            r24 = 1
            int r23 = r23 + -1
            r24 = r12
            int r23 = r23 + r24
            r24 = r9
            r25 = r13
            int r24 = r24 + r25
            r25 = 1
            int r24 = r24 + 1
            r25 = r12
            int r24 = r24 + r25
            r25 = r10
            java.util.Arrays.fill(r22, r23, r24, r25)
            r22 = r12
            r23 = 2
            int r22 = r22 % 2
            if (r22 == 0) goto L_0x0148
            r22 = 1
        L_0x00a1:
            r14 = r22
            r22 = 0
            r15 = r22
        L_0x00a7:
            r22 = r15
            r23 = r13
            r0 = r22
            r1 = r23
            if (r0 > r1) goto L_0x039c
            r22 = r15
            r0 = r22
            int r0 = -r0
            r22 = r0
            r16 = r22
        L_0x00ba:
            r22 = r16
            r23 = r15
            r0 = r22
            r1 = r23
            if (r0 > r1) goto L_0x0222
            r22 = r16
            r23 = r15
            r0 = r23
            int r0 = -r0
            r23 = r0
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x00ff
            r22 = r16
            r23 = r15
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x014c
            r22 = r7
            r23 = r9
            r24 = r16
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r22 = r22[r23]
            r23 = r7
            r24 = r9
            r25 = r16
            int r24 = r24 + r25
            r25 = 1
            int r24 = r24 + 1
            r23 = r23[r24]
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x014c
        L_0x00ff:
            r22 = r7
            r23 = r9
            r24 = r16
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + 1
            r22 = r22[r23]
            r17 = r22
            r22 = 0
            r18 = r22
        L_0x0113:
            r22 = r17
            r23 = r16
            int r22 = r22 - r23
            r19 = r22
        L_0x011b:
            r22 = r17
            r23 = r10
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x0165
            r22 = r19
            r23 = r11
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x0165
            r22 = r2
            r23 = r3
            r24 = r17
            int r23 = r23 + r24
            r24 = r5
            r25 = r19
            int r24 = r24 + r25
            boolean r22 = r22.areItemsTheSame(r23, r24)
            if (r22 == 0) goto L_0x0165
            int r17 = r17 + 1
            int r19 = r19 + 1
            goto L_0x011b
        L_0x0148:
            r22 = 0
            goto L_0x00a1
        L_0x014c:
            r22 = r7
            r23 = r9
            r24 = r16
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r22 = r22[r23]
            r23 = 1
            int r22 = r22 + 1
            r17 = r22
            r22 = 1
            r18 = r22
            goto L_0x0113
        L_0x0165:
            r22 = r7
            r23 = r9
            r24 = r16
            int r23 = r23 + r24
            r24 = r17
            r22[r23] = r24
            r22 = r14
            if (r22 == 0) goto L_0x021e
            r22 = r16
            r23 = r12
            r24 = r15
            int r23 = r23 - r24
            r24 = 1
            int r23 = r23 + 1
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x021e
            r22 = r16
            r23 = r12
            r24 = r15
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r0 = r22
            r1 = r23
            if (r0 > r1) goto L_0x021e
            r22 = r7
            r23 = r9
            r24 = r16
            int r23 = r23 + r24
            r22 = r22[r23]
            r23 = r8
            r24 = r9
            r25 = r16
            int r24 = r24 + r25
            r23 = r23[r24]
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x021e
            android.support.v7.util.DiffUtil$Snake r22 = new android.support.v7.util.DiffUtil$Snake
            r27 = r22
            r22 = r27
            r23 = r27
            r23.<init>()
            r20 = r22
            r22 = r20
            r23 = r8
            r24 = r9
            r25 = r16
            int r24 = r24 + r25
            r23 = r23[r24]
            r0 = r23
            r1 = r22
            r1.f40x = r0
            r22 = r20
            r23 = r20
            r0 = r23
            int r0 = r0.f40x
            r23 = r0
            r24 = r16
            int r23 = r23 - r24
            r0 = r23
            r1 = r22
            r1.f41y = r0
            r22 = r20
            r23 = r7
            r24 = r9
            r25 = r16
            int r24 = r24 + r25
            r23 = r23[r24]
            r24 = r8
            r25 = r9
            r26 = r16
            int r25 = r25 + r26
            r24 = r24[r25]
            int r23 = r23 - r24
            r0 = r23
            r1 = r22
            r1.size = r0
            r22 = r20
            r23 = r18
            r0 = r23
            r1 = r22
            r1.removal = r0
            r22 = r20
            r23 = 0
            r0 = r23
            r1 = r22
            r1.reverse = r0
            r22 = r20
            r2 = r22
            goto L_0x0040
        L_0x021e:
            int r16 = r16 + 2
            goto L_0x00ba
        L_0x0222:
            r22 = r15
            r0 = r22
            int r0 = -r0
            r22 = r0
            r16 = r22
        L_0x022b:
            r22 = r16
            r23 = r15
            r0 = r22
            r1 = r23
            if (r0 > r1) goto L_0x0398
            r22 = r16
            r23 = r12
            int r22 = r22 + r23
            r17 = r22
            r22 = r17
            r23 = r15
            r24 = r12
            int r23 = r23 + r24
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x0280
            r22 = r17
            r23 = r15
            r0 = r23
            int r0 = -r0
            r23 = r0
            r24 = r12
            int r23 = r23 + r24
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x02c5
            r22 = r8
            r23 = r9
            r24 = r17
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r22 = r22[r23]
            r23 = r8
            r24 = r9
            r25 = r17
            int r24 = r24 + r25
            r25 = 1
            int r24 = r24 + 1
            r23 = r23[r24]
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x02c5
        L_0x0280:
            r22 = r8
            r23 = r9
            r24 = r17
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r22 = r22[r23]
            r18 = r22
            r22 = 0
            r19 = r22
        L_0x0294:
            r22 = r18
            r23 = r17
            int r22 = r22 - r23
            r20 = r22
        L_0x029c:
            r22 = r18
            if (r22 <= 0) goto L_0x02de
            r22 = r20
            if (r22 <= 0) goto L_0x02de
            r22 = r2
            r23 = r3
            r24 = r18
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + -1
            r24 = r5
            r25 = r20
            int r24 = r24 + r25
            r25 = 1
            int r24 = r24 + -1
            boolean r22 = r22.areItemsTheSame(r23, r24)
            if (r22 == 0) goto L_0x02de
            int r18 = r18 + -1
            int r20 = r20 + -1
            goto L_0x029c
        L_0x02c5:
            r22 = r8
            r23 = r9
            r24 = r17
            int r23 = r23 + r24
            r24 = 1
            int r23 = r23 + 1
            r22 = r22[r23]
            r23 = 1
            int r22 = r22 + -1
            r18 = r22
            r22 = 1
            r19 = r22
            goto L_0x0294
        L_0x02de:
            r22 = r8
            r23 = r9
            r24 = r17
            int r23 = r23 + r24
            r24 = r18
            r22[r23] = r24
            r22 = r14
            if (r22 != 0) goto L_0x0394
            r22 = r16
            r23 = r12
            int r22 = r22 + r23
            r23 = r15
            r0 = r23
            int r0 = -r0
            r23 = r0
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x0394
            r22 = r16
            r23 = r12
            int r22 = r22 + r23
            r23 = r15
            r0 = r22
            r1 = r23
            if (r0 > r1) goto L_0x0394
            r22 = r7
            r23 = r9
            r24 = r17
            int r23 = r23 + r24
            r22 = r22[r23]
            r23 = r8
            r24 = r9
            r25 = r17
            int r24 = r24 + r25
            r23 = r23[r24]
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x0394
            android.support.v7.util.DiffUtil$Snake r22 = new android.support.v7.util.DiffUtil$Snake
            r27 = r22
            r22 = r27
            r23 = r27
            r23.<init>()
            r21 = r22
            r22 = r21
            r23 = r8
            r24 = r9
            r25 = r17
            int r24 = r24 + r25
            r23 = r23[r24]
            r0 = r23
            r1 = r22
            r1.f40x = r0
            r22 = r21
            r23 = r21
            r0 = r23
            int r0 = r0.f40x
            r23 = r0
            r24 = r17
            int r23 = r23 - r24
            r0 = r23
            r1 = r22
            r1.f41y = r0
            r22 = r21
            r23 = r7
            r24 = r9
            r25 = r17
            int r24 = r24 + r25
            r23 = r23[r24]
            r24 = r8
            r25 = r9
            r26 = r17
            int r25 = r25 + r26
            r24 = r24[r25]
            int r23 = r23 - r24
            r0 = r23
            r1 = r22
            r1.size = r0
            r22 = r21
            r23 = r19
            r0 = r23
            r1 = r22
            r1.removal = r0
            r22 = r21
            r23 = 1
            r0 = r23
            r1 = r22
            r1.reverse = r0
            r22 = r21
            r2 = r22
            goto L_0x0040
        L_0x0394:
            int r16 = r16 + 2
            goto L_0x022b
        L_0x0398:
            int r15 = r15 + 1
            goto L_0x00a7
        L_0x039c:
            java.lang.IllegalStateException r22 = new java.lang.IllegalStateException
            r27 = r22
            r22 = r27
            r23 = r27
            java.lang.String r24 = "DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation."
            r23.<init>(r24)
            throw r22
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.util.DiffUtil.diffPartial(android.support.v7.util.DiffUtil$Callback, int, int, int, int, int[], int[], int):android.support.v7.util.DiffUtil$Snake");
    }

    /* renamed from: android.support.v7.util.DiffUtil$Callback */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        public Callback() {
        }

        @Nullable
        public Object getChangePayload(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            return null;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$ItemCallback */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t, @NonNull T t2);

        public abstract boolean areItemsTheSame(@NonNull T t, @NonNull T t2);

        public ItemCallback() {
        }

        @Nullable
        public Object getChangePayload(@NonNull T t, @NonNull T t2) {
            T t3 = t;
            T t4 = t2;
            return null;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$Snake */
    static class Snake {
        boolean removal;
        boolean reverse;
        int size;

        /* renamed from: x */
        int f40x;

        /* renamed from: y */
        int f41y;

        Snake() {
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$Range */
    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int oldListStart2, int oldListEnd2, int newListStart2, int newListEnd2) {
            this.oldListStart = oldListStart2;
            this.oldListEnd = oldListEnd2;
            this.newListStart = newListStart2;
            this.newListEnd = newListEnd2;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$DiffResult */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> snakes, int[] oldItemStatuses, int[] newItemStatuses, boolean detectMoves) {
            Callback callback2 = callback;
            this.mSnakes = snakes;
            this.mOldItemStatuses = oldItemStatuses;
            this.mNewItemStatuses = newItemStatuses;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback2;
            this.mOldListSize = callback2.getOldListSize();
            this.mNewListSize = callback2.getNewListSize();
            this.mDetectMoves = detectMoves;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake;
            Snake firstSnake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (firstSnake == null || firstSnake.f40x != 0 || firstSnake.f41y != 0) {
                new Snake();
                Snake root = snake;
                root.f40x = 0;
                root.f41y = 0;
                root.removal = false;
                root.size = 0;
                root.reverse = false;
                this.mSnakes.add(0, root);
            }
        }

        private void findMatchingItems() {
            int posOld = this.mOldListSize;
            int posNew = this.mNewListSize;
            for (int i = this.mSnakes.size() - 1; i >= 0; i--) {
                Snake snake = this.mSnakes.get(i);
                int endX = snake.f40x + snake.size;
                int endY = snake.f41y + snake.size;
                if (this.mDetectMoves) {
                    while (posOld > endX) {
                        findAddition(posOld, posNew, i);
                        posOld--;
                    }
                    while (posNew > endY) {
                        findRemoval(posOld, posNew, i);
                        posNew--;
                    }
                }
                for (int j = 0; j < snake.size; j++) {
                    int oldItemPos = snake.f40x + j;
                    int newItemPos = snake.f41y + j;
                    int changeFlag = this.mCallback.areContentsTheSame(oldItemPos, newItemPos) ? 1 : 2;
                    this.mOldItemStatuses[oldItemPos] = (newItemPos << 5) | changeFlag;
                    this.mNewItemStatuses[newItemPos] = (oldItemPos << 5) | changeFlag;
                }
                posOld = snake.f40x;
                posNew = snake.f41y;
            }
        }

        private void findAddition(int i, int i2, int i3) {
            int x = i;
            int y = i2;
            int snakeIndex = i3;
            if (this.mOldItemStatuses[x - 1] == 0) {
                boolean findMatchingItem = findMatchingItem(x, y, snakeIndex, false);
            }
        }

        private void findRemoval(int i, int i2, int i3) {
            int x = i;
            int y = i2;
            int snakeIndex = i3;
            if (this.mNewItemStatuses[y - 1] == 0) {
                boolean findMatchingItem = findMatchingItem(x, y, snakeIndex, true);
            }
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int i) {
            Throwable th;
            StringBuilder sb;
            int oldListPosition = i;
            if (oldListPosition < 0 || oldListPosition >= this.mOldItemStatuses.length) {
                Throwable th2 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("Index out of bounds - passed position = ").append(oldListPosition).append(", old list size = ").append(this.mOldItemStatuses.length).toString());
                throw th2;
            }
            int status = this.mOldItemStatuses[oldListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int i) {
            Throwable th;
            StringBuilder sb;
            int newListPosition = i;
            if (newListPosition < 0 || newListPosition >= this.mNewItemStatuses.length) {
                Throwable th2 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("Index out of bounds - passed position = ").append(newListPosition).append(", new list size = ").append(this.mNewItemStatuses.length).toString());
                throw th2;
            }
            int status = this.mNewItemStatuses[newListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        private boolean findMatchingItem(int i, int i2, int i3, boolean z) {
            int myItemPos;
            int curX;
            int curY;
            int x = i;
            int y = i2;
            int snakeIndex = i3;
            boolean removal = z;
            if (removal) {
                myItemPos = y - 1;
                curX = x;
                curY = y - 1;
            } else {
                myItemPos = x - 1;
                curX = x - 1;
                curY = y;
            }
            for (int i4 = snakeIndex; i4 >= 0; i4--) {
                Snake snake = this.mSnakes.get(i4);
                int endX = snake.f40x + snake.size;
                int endY = snake.f41y + snake.size;
                if (removal) {
                    for (int pos = curX - 1; pos >= endX; pos--) {
                        if (this.mCallback.areItemsTheSame(pos, myItemPos)) {
                            int changeFlag = this.mCallback.areContentsTheSame(pos, myItemPos) ? 8 : 4;
                            this.mNewItemStatuses[myItemPos] = (pos << 5) | 16;
                            this.mOldItemStatuses[pos] = (myItemPos << 5) | changeFlag;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int pos2 = curY - 1; pos2 >= endY; pos2--) {
                        if (this.mCallback.areItemsTheSame(myItemPos, pos2)) {
                            int changeFlag2 = this.mCallback.areContentsTheSame(myItemPos, pos2) ? 8 : 4;
                            this.mOldItemStatuses[x - 1] = (pos2 << 5) | 16;
                            this.mNewItemStatuses[pos2] = ((x - 1) << 5) | changeFlag2;
                            return true;
                        }
                    }
                    continue;
                }
                curX = snake.f40x;
                curY = snake.f41y;
            }
            return false;
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            ListUpdateCallback listUpdateCallback;
            new AdapterListUpdateCallback(adapter);
            dispatchUpdatesTo(listUpdateCallback);
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            ListUpdateCallback listUpdateCallback2;
            ListUpdateCallback batchingCallback;
            List<PostponedUpdate> list;
            ListUpdateCallback updateCallback = listUpdateCallback;
            if (updateCallback instanceof BatchingListUpdateCallback) {
                batchingCallback = (BatchingListUpdateCallback) updateCallback;
            } else {
                new BatchingListUpdateCallback(updateCallback);
                batchingCallback = listUpdateCallback2;
                ListUpdateCallback updateCallback2 = batchingCallback;
            }
            new ArrayList<>();
            List<PostponedUpdate> postponedUpdates = list;
            int posOld = this.mOldListSize;
            int posNew = this.mNewListSize;
            for (int snakeIndex = this.mSnakes.size() - 1; snakeIndex >= 0; snakeIndex--) {
                Snake snake = this.mSnakes.get(snakeIndex);
                int snakeSize = snake.size;
                int endX = snake.f40x + snakeSize;
                int endY = snake.f41y + snakeSize;
                if (endX < posOld) {
                    dispatchRemovals(postponedUpdates, batchingCallback, endX, posOld - endX, endX);
                }
                if (endY < posNew) {
                    dispatchAdditions(postponedUpdates, batchingCallback, endX, posNew - endY, endY);
                }
                for (int i = snakeSize - 1; i >= 0; i--) {
                    if ((this.mOldItemStatuses[snake.f40x + i] & 31) == 2) {
                        batchingCallback.onChanged(snake.f40x + i, 1, this.mCallback.getChangePayload(snake.f40x + i, snake.f41y + i));
                    }
                }
                posOld = snake.f40x;
                posNew = snake.f41y;
            }
            batchingCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i, boolean z) {
            List<PostponedUpdate> updates = list;
            int pos = i;
            boolean removal = z;
            for (int i2 = updates.size() - 1; i2 >= 0; i2--) {
                PostponedUpdate update = updates.get(i2);
                if (update.posInOwnerList == pos && update.removal == removal) {
                    PostponedUpdate remove = updates.remove(i2);
                    for (int j = i2; j < updates.size(); j++) {
                        updates.get(j).currentPos += removal ? 1 : -1;
                    }
                    return update;
                }
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            Throwable th;
            StringBuilder sb;
            Object obj;
            List<PostponedUpdate> postponedUpdates = list;
            ListUpdateCallback updateCallback = listUpdateCallback;
            int start = i;
            int count = i2;
            int globalIndex = i3;
            if (!this.mDetectMoves) {
                updateCallback.onInserted(start, count);
                return;
            }
            for (int i4 = count - 1; i4 >= 0; i4--) {
                int status = this.mNewItemStatuses[globalIndex + i4] & 31;
                switch (status) {
                    case 0:
                        updateCallback.onInserted(start, 1);
                        for (PostponedUpdate postponedUpdate : postponedUpdates) {
                            postponedUpdate.currentPos++;
                        }
                        break;
                    case 4:
                    case 8:
                        int pos = this.mNewItemStatuses[globalIndex + i4] >> 5;
                        updateCallback.onMoved(removePostponedUpdate(postponedUpdates, pos, true).currentPos, start);
                        if (status != 4) {
                            break;
                        } else {
                            updateCallback.onChanged(start, 1, this.mCallback.getChangePayload(pos, globalIndex + i4));
                            break;
                        }
                    case 16:
                        new PostponedUpdate(globalIndex + i4, start, false);
                        boolean add = postponedUpdates.add(obj);
                        break;
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalStateException(sb.append("unknown flag for pos ").append(globalIndex + i4).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(Long.toBinaryString((long) status)).toString());
                        throw th2;
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            Throwable th;
            StringBuilder sb;
            Object obj;
            List<PostponedUpdate> postponedUpdates = list;
            ListUpdateCallback updateCallback = listUpdateCallback;
            int start = i;
            int count = i2;
            int globalIndex = i3;
            if (!this.mDetectMoves) {
                updateCallback.onRemoved(start, count);
                return;
            }
            for (int i4 = count - 1; i4 >= 0; i4--) {
                int status = this.mOldItemStatuses[globalIndex + i4] & 31;
                switch (status) {
                    case 0:
                        updateCallback.onRemoved(start + i4, 1);
                        for (PostponedUpdate next : postponedUpdates) {
                            next.currentPos--;
                        }
                        break;
                    case 4:
                    case 8:
                        int pos = this.mOldItemStatuses[globalIndex + i4] >> 5;
                        PostponedUpdate update = removePostponedUpdate(postponedUpdates, pos, false);
                        updateCallback.onMoved(start + i4, update.currentPos - 1);
                        if (status != 4) {
                            break;
                        } else {
                            updateCallback.onChanged(update.currentPos - 1, 1, this.mCallback.getChangePayload(globalIndex + i4, pos));
                            break;
                        }
                    case 16:
                        new PostponedUpdate(globalIndex + i4, start + i4, true);
                        boolean add = postponedUpdates.add(obj);
                        break;
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalStateException(sb.append("unknown flag for pos ").append(globalIndex + i4).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(Long.toBinaryString((long) status)).toString());
                        throw th2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$PostponedUpdate */
    private static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int posInOwnerList2, int currentPos2, boolean removal2) {
            this.posInOwnerList = posInOwnerList2;
            this.currentPos = currentPos2;
            this.removal = removal2;
        }
    }
}
