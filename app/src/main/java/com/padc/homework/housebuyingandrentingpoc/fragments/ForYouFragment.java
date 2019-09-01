package com.padc.homework.housebuyingandrentingpoc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.adapters.ForYouFragmentPagerAdapter;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;

public class ForYouFragment extends Fragment {

    private HouseItemDelegate delegate;

    public ForYouFragment(){}

    @SuppressLint("ValidFragment")
    public ForYouFragment(HouseItemDelegate delegate){
        this.delegate = delegate;
    }

    //@BindView(R.id.rv_top_collection)
    //RecyclerView rvTopCollection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.for_you_main_layout, container, false);
        //ButterKnife.bind(container);

        //RecyclerView rvTopCollection = view.findViewById(R.id.rv_top_collection);
        //rvTopCollection.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //HouseItemsViewRVAdapter adapter = new HouseItemsViewRVAdapter();
        //rvTopCollection.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_for_you);

        ForYouFragmentPagerAdapter pagerAdapter = new ForYouFragmentPagerAdapter(getFragmentManager(), delegate);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
