package com.padc.homework.housebuyingandrentingpoc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.adapters.HouseItemsViewRVAdapter;
import com.padc.homework.housebuyingandrentingpoc.data.models.HouseInfoModel;
import com.padc.homework.housebuyingandrentingpoc.data.models.HouseModelImpl;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;

import java.util.List;

public class TopCollectionFragment extends BaseFragment {

    private HouseItemDelegate delegate;

    public TopCollectionFragment() {
    }

    @SuppressLint("ValidFragment")
    public TopCollectionFragment(HouseItemDelegate delegate){
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_collection, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.rv_top_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        final HouseItemsViewRVAdapter adapter = new HouseItemsViewRVAdapter(delegate);

        HouseModelImpl.getObjInstance().getHouseInfo(new HouseInfoModel.GetEventsFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseInfoVO> houseInfoVOS) {
                adapter.setNewData(houseInfoVOS);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String message) {
                showSnackBar(message);
            }
        });

        return view;
    }
}
