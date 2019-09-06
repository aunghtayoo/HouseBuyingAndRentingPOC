package com.padc.homework.housebuyingandrentingpoc.views.viewsholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    //generic type for all data for view holders
    protected T mData;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data);
}
