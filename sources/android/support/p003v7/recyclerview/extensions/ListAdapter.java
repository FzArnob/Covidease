package android.support.p003v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p003v7.util.AdapterListUpdateCallback;
import android.support.p003v7.util.DiffUtil;
import android.support.p003v7.util.ListUpdateCallback;
import android.support.p003v7.widget.RecyclerView;
import android.support.p003v7.widget.RecyclerView.ViewHolder;
import java.util.List;

/* renamed from: android.support.v7.recyclerview.extensions.ListAdapter */
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncListDiffer<T> mHelper;

    protected ListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        AsyncListDiffer<T> asyncListDiffer;
        ListUpdateCallback listUpdateCallback;
        AsyncDifferConfig.Builder builder;
        new AdapterListUpdateCallback(this);
        new AsyncDifferConfig.Builder(diffCallback);
        new AsyncListDiffer<>(listUpdateCallback, builder.build());
        this.mHelper = asyncListDiffer;
    }

    protected ListAdapter(@NonNull AsyncDifferConfig<T> config) {
        AsyncListDiffer<T> asyncListDiffer;
        ListUpdateCallback listUpdateCallback;
        new AdapterListUpdateCallback(this);
        new AsyncListDiffer<>(listUpdateCallback, config);
        this.mHelper = asyncListDiffer;
    }

    public void submitList(@Nullable List<T> list) {
        this.mHelper.submitList(list);
    }

    /* access modifiers changed from: protected */
    public T getItem(int position) {
        return this.mHelper.getCurrentList().get(position);
    }

    public int getItemCount() {
        return this.mHelper.getCurrentList().size();
    }
}
