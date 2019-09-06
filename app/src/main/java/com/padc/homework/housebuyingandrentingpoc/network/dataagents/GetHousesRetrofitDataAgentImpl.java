package com.padc.homework.housebuyingandrentingpoc.network.dataagents;

import com.padc.homework.housebuyingandrentingpoc.network.HouseApi;
import com.padc.homework.housebuyingandrentingpoc.network.responses.GetHousesResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants.BASE_URL;

public class GetHousesRetrofitDataAgentImpl implements GetHousesDataAgent {

    private static GetHousesRetrofitDataAgentImpl objInstance;

    private HouseApi houseApi;

    private GetHousesRetrofitDataAgentImpl(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        houseApi = retrofit.create(HouseApi.class);
    }

    public static GetHousesRetrofitDataAgentImpl getObjInstance(){
        if(objInstance == null){
            objInstance = new GetHousesRetrofitDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void getHouses(final GetEventsFromNetworkDelegate delegate) {

        Call<GetHousesResponse> response = houseApi.getHouseInfo();

        response.enqueue(new Callback<GetHousesResponse>() {
            @Override
            public void onResponse(Call<GetHousesResponse> call, Response<GetHousesResponse> response) {
                GetHousesResponse getHousesResponse = response.body();
                if(getHousesResponse != null){
                    delegate.onSuccess(getHousesResponse.getData());
                } else {
                    delegate.onFailure(getHousesResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetHousesResponse> call, Throwable t) {
                delegate.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
