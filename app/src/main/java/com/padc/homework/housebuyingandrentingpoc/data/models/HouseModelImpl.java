package com.padc.homework.housebuyingandrentingpoc.data.models;

import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesDataAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseModelImpl extends BaseModel implements HouseInfoModel{

    private static HouseModelImpl mHouseModel;
    private Map<Integer, HouseInfoVO> housesDataRepository;

    private HouseModelImpl(){
        housesDataRepository = new HashMap<>();
    }

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
                for (HouseInfoVO houseInfo : houseInfoList) {
                    housesDataRepository.put(houseInfo.getId(), houseInfo);
                }
                delegate.onSuccess(houseInfoList);
            }

            @Override
            public void onFailure(String message) {
                delegate.onFailure(message);
            }
        });
    }

    public HouseInfoVO findHouseById(int id){
        return housesDataRepository.get(id);
    }

    public List<HouseInfoVO> getDataRepository(){
        List<HouseInfoVO> houseInfoVOList = new ArrayList<>(housesDataRepository.values());
        return houseInfoVOList;
    }

    public List<HouseInfoVO> findHouseByName(String name){
        List<HouseInfoVO> houseInfoVOList = new ArrayList<>(housesDataRepository.values());
        List<HouseInfoVO> returnList = new ArrayList<>();
        for (HouseInfoVO houseInfoVO : houseInfoVOList) {
            if(houseInfoVO.getName().contains(name)){
                returnList.add(houseInfoVO);
            }
        }
        return returnList;
    }
}
