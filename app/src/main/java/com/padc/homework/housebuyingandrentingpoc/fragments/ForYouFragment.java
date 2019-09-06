package com.padc.homework.housebuyingandrentingpoc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.adapters.ForYouFragmentPagerAdapter;
import com.padc.homework.housebuyingandrentingpoc.delegates.ForYouFragmentDelegate;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;


public class ForYouFragment extends BaseFragment {

    private HouseItemDelegate mHouseItemDelegate;
    private ForYouFragmentDelegate mForYouFragmentDelegate;

    public ForYouFragment(){}

    @SuppressLint("ValidFragment")
    public ForYouFragment(HouseItemDelegate mHouseItemDelegate, ForYouFragmentDelegate mForYouFragmentDelegate){
        this.mHouseItemDelegate = mHouseItemDelegate;
        this.mForYouFragmentDelegate = mForYouFragmentDelegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.for_you_main_layout, container, false);

        //ButterKnife.bind(this, container);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_for_you);
        ForYouFragmentPagerAdapter pagerAdapter = new ForYouFragmentPagerAdapter(getFragmentManager(), mHouseItemDelegate);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        final EditText textSearch = view.findViewById(R.id.text_search_address);

        ImageView imgSearch = view.findViewById(R.id.ic_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWord = textSearch.getText().toString();
                mForYouFragmentDelegate.onSearchFilter(searchWord);
            }
        });

        ImageView imgLinearly = view.findViewById(R.id.ic_linearly);
        imgLinearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mForYouFragmentDelegate.onTabListView();
            }
        });

        ImageView imgGrid = view.findViewById(R.id.ic_grid);
        imgGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mForYouFragmentDelegate.onTabGridView();
            }
        });

        return view;
    }
}
