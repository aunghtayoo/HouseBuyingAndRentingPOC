package com.padc.homework.housebuyingandrentingpoc.data.models;

import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesDataAgent;

import java.util.List;

public class HouseModelImpl extends BaseModel implements HouseInfoModel{

    private static HouseModelImpl mHouseModel;

    private HouseModelImpl(){ }

    public static HouseModelImpl getObjInstance(){
        if(mHouseModel == null){
            mHouseModel = new HouseModelImpl();
        }
        return mHouseModel;
    }

    @Override
    public void getHouseInfo(final GetEventsFromDataLayerDelegate delegate) {
        mDataAgent.getHouses(new GetHousesDataAgent.GetEventsFromNetworkDelegate() {
            @Override
            public void onSuccess(List<HouseInfoVO> houseInfoList) {
                delegate.onSuccess(houseInfoList);
            }

            @Override
            public void onFailure(String message) {
                delegate.onFailure(message);
            }
        });
    }
}
