package com.padc.homework.housebuyingandrentingpoc.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;

import java.util.List;

public class GetHousesResponse{

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<HouseInfoVO> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HouseInfoVO> getData() {
        return data;
    }

    public void setData(List<HouseInfoVO> data) {
        this.data = data;
    }

    public boolean isResponseOk(){
        return code==200 && data!=null;
    }
}
