package com.padc.homework.housebuyingandrentingpoc.data.models;

import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;

import java.util.List;

public interface HouseInfoModel {

    void getHouseInfo(GetEventsFromDataLayerDelegate delegate);

    interface GetEventsFromDataLayerDelegate {
        void onSuccess(List<HouseInfoVO> houseInfoVOS);
        void onFailure(String message);
    }
}
