package com.padc.homework.housebuyingandrentingpoc.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.padc.homework.housebuyingandrentingpoc.data.models.HouseModelImpl;

public abstract class BaseActivity extends AppCompatActivity {

    protected HouseModelImpl mHouseModel;

    public BaseActivity() {
        mHouseModel = HouseModelImpl.getObjInstance();
    }
}
