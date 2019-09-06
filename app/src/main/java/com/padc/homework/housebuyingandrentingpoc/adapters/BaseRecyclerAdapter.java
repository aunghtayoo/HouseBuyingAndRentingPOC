package com.padc.homework.housebuyingandrentingpoc.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.padc.homework.housebuyingandrentingpoc.views.viewsholder.BaseViewHolder;

import java.util.List;

//T(view_holder) -> W -> W(data)
public abstract class BaseRecyclerAdapter<T extends BaseViewHolder<W>, W> extends RecyclerView.Adapter<T> {

    private List<W> mData;

    @Override
    public void onBindViewHolder(@NonNull T viewHolder, int position) {
        viewHolder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<W> data){
            mData = data;
            notifyDataSetChanged(); //call onBindViewHolder() again and data will be refreshed.
    }

}
