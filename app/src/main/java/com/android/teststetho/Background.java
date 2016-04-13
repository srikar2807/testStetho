package com.android.teststetho;

import android.os.AsyncTask;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Background extends AsyncTask<Void, Void, String> {

    OkHttpClient client = new OkHttpClient();

    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected String doInBackground(Void... params) {

        client.networkInterceptors().add(new StethoInterceptor());

        String resp = "No Get Response";
        try {
            resp = doGetRequest("http://jsonplaceholder.typicode.com/posts");
        } catch (IOException ignore) {
        }

        return resp;
    }
}
