package com.padc.homework.housebuyingandrentingpoc.network.dataagents;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padc.homework.housebuyingandrentingpoc.network.responses.GetHousesResponse;
import com.padc.homework.housebuyingandrentingpoc.utils.EventsConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHousesHttpConnectionDataAgentImpl implements GetHousesDataAgent{

    private static GetHousesHttpConnectionDataAgentImpl mGetHousesDataAgent;
    private GetEventsFromNetworkDelegate mGetEventsFromNetworkDelegate;

    private GetHousesHttpConnectionDataAgentImpl(){ }

    public static GetHousesHttpConnectionDataAgentImpl getObjInstance(){
        if(mGetHousesDataAgent == null){
            mGetHousesDataAgent = new GetHousesHttpConnectionDataAgentImpl();
        }
        return mGetHousesDataAgent;
    }

    @Override
    public void getHouses(GetEventsFromNetworkDelegate delegate) {
        mGetEventsFromNetworkDelegate = delegate;
        new GetHousesTask().execute();
    }

    private class GetHousesTask extends AsyncTask<Void, Void, GetHousesResponse>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected GetHousesResponse doInBackground(Void... voids) {

            URL url;
            BufferedReader reader = null;
            StringBuffer stringBuffer;

            try{

                url = new URL(EventsConstants.BASE_URL+ EventsConstants.GET_HOUSES);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(15 * 1000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //put the request parameters into NameValuePair list.
                //List<NameValuePair> params = new ArrayList<>();
                //params.add(new BasicNameValuePair(EventsConstants.PARAM_ACCESS_TOKEN, accessToken));

                //write the parameters from NameValuePair list into connections obj.
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.flush();
                writer.close();
                outputStream.close();

                connection.connect();

                //read the output from the server
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuffer = new StringBuffer();

                String line = null;
                while ((line = reader.readLine()) != null){
                    stringBuffer.append(line+"\n");
                }

                String responseString = stringBuffer.toString();

                GetHousesResponse response = new Gson()
                        .fromJson(responseString, GetHousesResponse.class);

                return response;

            } catch (Exception e){
                e.printStackTrace();

            } finally {
                //close the reader; this can throw an exception too, so
                //wrap it in another try/catch block.
                if(reader != null){
                    try{
                        reader.close();
                    } catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(GetHousesResponse getHousesResponse) {
            super.onPostExecute(getHousesResponse);

            if(getHousesResponse != null){

                //Response OK | code=200, data!=null
                if(getHousesResponse.isResponseOk()){
                    mGetEventsFromNetworkDelegate.onSuccess(getHousesResponse.getData());
                } else {
                    mGetEventsFromNetworkDelegate.onFailure(getHousesResponse.getMessage());
                }

            } else {
                mGetEventsFromNetworkDelegate.onFailure(EventsConstants.EM_NULL_EVENT_RESPONSE);
            }
        }

    }
}
