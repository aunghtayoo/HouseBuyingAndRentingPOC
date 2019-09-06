package com.padc.homework.housebuyingandrentingpoc.data.models;

import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHouseOkHttpDataAgentImpl;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesDataAgent;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesHttpConnectionDataAgentImpl;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesRetrofitDataAgentImpl;

public abstract class BaseModel {

    protected GetHousesDataAgent mDataAgent;

    public BaseModel() {
        //mDataAgent = GetHousesHttpConnectionDataAgentImpl.getObjInstance();
        //mDataAgent = GetHouseOkHttpDataAgentImpl.getObjInstance();
        mDataAgent = GetHousesRetrofitDataAgentImpl.getObjInstance();
    }
}
