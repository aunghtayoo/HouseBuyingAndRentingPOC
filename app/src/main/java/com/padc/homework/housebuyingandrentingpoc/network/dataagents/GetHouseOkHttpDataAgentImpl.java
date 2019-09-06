package com.padc.homework.housebuyingandrentingpoc.network.dataagents;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padc.homework.housebuyingandrentingpoc.network.responses.GetHousesResponse;
import com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants.BASE_URL;
import static com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants.DUMMY_ACCESS_TOKEN;
import static com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants.GET_HOUSES;
import static com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants.PARAM_ACCESS_TOKEN;

public class GetHouseOkHttpDataAgentImpl implements GetHousesDataAgent {

    private static GetHouseOkHttpDataAgentImpl objInstance;
    private OkHttpClient mOkHttpClient;

    private GetHouseOkHttpDataAgentImpl(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static GetHouseOkHttpDataAgentImpl getObjInstance(){
        if(objInstance == null){
            objInstance = new GetHouseOkHttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void getHouses(GetEventsFromNetworkDelegate delegate) {
        new OkHttpTask(delegate, mOkHttpClient, DUMMY_ACCESS_TOKEN).execute();
    }


    public static class OkHttpTask extends AsyncTask<Void, Void, GetHousesResponse>{

        private GetEventsFromNetworkDelegate delegate;
        private OkHttpClient okHttpClient;
        private String accessToken;

        public OkHttpTask(GetEventsFromNetworkDelegate delegate, OkHttpClient okHttpClient, String accessToken) {
            this.okHttpClient = okHttpClient;
            this.delegate = delegate;
            this.accessToken = accessToken;
        }

        @Override
        protected GetHousesResponse doInBackground(Void... voids) {

            RequestBody formBody = new FormBody.Builder()
                    .add(PARAM_ACCESS_TOKEN,DUMMY_ACCESS_TOKEN)
                    .build();

            Request request = new Request.Builder()
                    .url(BASE_URL + GET_HOUSES)
                    .post(formBody)
                    .build();

            try{

                Response response = okHttpClient.newCall(request).execute();

                if(response.isSuccessful()){
                    String responseString = response.body().toString();
                    GetHousesResponse getHousesResponse = new Gson().fromJson(responseString, GetHousesResponse.class);
                    return getHousesResponse;

                } else{

                }

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(GetHousesResponse getHousesResponse) {
            super.onPostExecute(getHousesResponse);
            if(getHousesResponse != null){
                if(getHousesResponse.isResponseOk()){
                    delegate.onSuccess(getHousesResponse.getData());
                } else {
                    delegate.onFailure(getHousesResponse.getMessage());
                }

            } else {
                delegate.onFailure(EventsConstants.EM_NULL_EVENT_RESPONSE);
            }
        }
    }
}