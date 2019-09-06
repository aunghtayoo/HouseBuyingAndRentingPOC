package com.padc.homework.housebuyingandrentingpoc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;

public class TopCollectionFragment extends BaseFragment{

    private HouseItemDelegate delegate;

    public TopCollectionFragment() { }

    @SuppressLint("ValidFragment")
    public TopCollectionFragment(HouseItemDelegate delegate){
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_collection, container, false);
        delegate.onGetHouseItems(this, view);
        return view;
    }
}
