package com.padc.homework.housebuyingandrentingpoc.adapters;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;
import com.padc.homework.housebuyingandrentingpoc.views.viewsholder.HouseItemViewHolder;

public class HouseItemsViewRVAdapter extends BaseRecyclerAdapter<HouseItemViewHolder, HouseInfoVO> {

    private HouseItemDelegate delegate;

    public HouseItemsViewRVAdapter(){}

    public HouseItemsViewRVAdapter(HouseItemDelegate delegate){
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public HouseItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_top_collection, viewGroup, false);
        return new HouseItemViewHolder(view, delegate);
    }

}
