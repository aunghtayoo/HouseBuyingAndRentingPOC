package com.padc.homework.housebuyingandrentingpoc.network.dataagents;

import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;

import java.util.List;

public interface GetHousesDataAgent {

    void getHouses(GetEventsFromNetworkDelegate delegate);

    interface GetEventsFromNetworkDelegate{
        void onSuccess(List<HouseInfoVO> houseInfoList);
        void onFailure(String message);
    }
}
