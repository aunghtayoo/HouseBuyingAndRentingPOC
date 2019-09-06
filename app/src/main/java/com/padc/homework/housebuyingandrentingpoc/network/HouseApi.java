package com.padc.homework.housebuyingandrentingpoc.network;

import com.padc.homework.housebuyingandrentingpoc.network.responses.GetHousesResponse;
import com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants;

import retrofit2.Call;
import retrofit2.http.POST;

public interface HouseApi {
    @POST(EventsConstants.GET_HOUSES)
    Call<GetHousesResponse> getHouseInfo();
}
