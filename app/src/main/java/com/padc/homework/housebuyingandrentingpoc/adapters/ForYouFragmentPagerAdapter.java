package com.padc.homework.housebuyingandrentingpoc.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;
import com.padc.homework.housebuyingandrentingpoc.fragments.TopCollectionFragment;

public class ForYouFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private HouseItemDelegate delegate;

    public ForYouFragmentPagerAdapter(FragmentManager fm, HouseItemDelegate delegate) {
        super(fm);
        this.delegate = delegate;
    }

    @Override
    public Fragment getItem(int item) {
        switch (item){
            case 0 : return new TopCollectionFragment(delegate);
            case 1 : return new TopCollectionFragment(delegate);
            case 2 : return new TopCollectionFragment(delegate);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Top Collection";
            case 1 : return "Near me";
            case 2 : return "Low to high price";
        }
        return null;
    }
}
