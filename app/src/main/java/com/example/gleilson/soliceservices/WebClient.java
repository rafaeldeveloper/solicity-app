package com.example.gleilson.soliceservices;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClient {

    private String url;

    public WebClient(String url) {
        this.url = url;
    }

    public JSONObject post(String json) throws JSONException {

        String jsonResponse = "";

        try {
            OkHttpClient client = new OkHttpClient();

            Request.Builder builder = new Request.Builder();
            builder.url(this.url);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, json);
            builder.post(body);

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            jsonResponse = response.body().string();

            Log.d("TESTE 2", jsonResponse);

        } catch (MalformedURLException e) {
            Log.d("MalformedURLException", e.getMessage());
            e.printStackTrace();

            jsonResponse = "{success: false, msg: " + e.getMessage() + "}";
        } catch (IOException e) {
            Log.d("IOException", e.getMessage());
            e.printStackTrace();

            jsonResponse = "{success: false, msg: " + e.getMessage() + "}";
        }

        return new JSONObject(jsonResponse);
    }

}
