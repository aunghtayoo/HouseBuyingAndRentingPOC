package com.padc.homework.housebuyingandrentingpoc.delegates;

import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.padc.homework.housebuyingandrentingpoc.fragments.TopCollectionFragment;

public interface HouseItemDelegate {
    void onTabHouseItem(int id);
    void onGetHouseItems(TopCollectionFragment context, View view);
    void onTabLocation(LatLng latLng);
}
