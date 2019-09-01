package com.padc.homework.housebuyingandrentingpoc.network.responses;

import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;

import java.util.List;

public class GetHousesResponse{

    private int code;
    private String message;
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
