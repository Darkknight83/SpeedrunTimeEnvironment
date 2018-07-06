package com.example.speedruntimeenvironment.controllers.speedrun.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SpeedrunRestClient {
    private static final String TAG = "SpeedrunRestClient";

    private static final String BASE_URL = "https://www.speedrun.com/api/v1";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getAbsolute(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if(params == null) {
            client.get(url, responseHandler);
        } else {
            client.get(url, params, responseHandler);
        }

    }

    private static String getAbsoluteUrl(String relativeUrl) {
        StringBuilder sb = new StringBuilder(BASE_URL);
        sb.append(relativeUrl);
        return sb.toString();
    }

}
