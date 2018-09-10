package com.rnupload.data;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetWork {
    public static final MediaType OCTET = MediaType.get("application/octet-stream;");

    public static final OkHttpClient client = new OkHttpClient();

    String post(String url, byte[] fileChunk,String token) throws IOException {
      //  Log.d("chunk",fileChunk);
        RequestBody body = RequestBody.create(OCTET, fileChunk);
        Request request = new Request.Builder()
                .addHeader("Transfer-Encoding","Chunked")
                .addHeader("Authorization",token)
                .url(url)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
