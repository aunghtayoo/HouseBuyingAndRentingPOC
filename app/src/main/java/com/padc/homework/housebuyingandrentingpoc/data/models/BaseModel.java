package com.padc.homework.housebuyingandrentingpoc.data.models;

import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesDataAgent;
import com.padc.homework.housebuyingandrentingpoc.network.dataagents.GetHousesDataAgentImpl;

public abstract class BaseModel {

    protected GetHousesDataAgent mDataAgent;

    public BaseModel() {
        mDataAgent = GetHousesDataAgentImpl.getObjInstance();
    }
}
