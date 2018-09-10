package com.rnupload;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.rnupload.data.UPData;
import com.rnupload.data.Upload;
import com.rnupload.fs.FS;


import java.util.ArrayList;
import java.util.List;


public class RNUpload extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;
    public RNUpload(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNUpload";
    }

    @ReactMethod
    public void test() {
        Log.d("wOW", "from RN");
        upload();
        //  Log.d("DATA",UPData.loadData().size()+"Nos");

    }


    @ReactMethod
    public void upload() {
        try {
            String filepath = Environment.getExternalStorageDirectory()+"/im.mp4";
            UPData.addItem(new UPData("https://api.google.com/api/upload/", filepath, FS.getSize(filepath), 3, 0, "1RwW8eMXI0mJKrp5KhZHY3nWo9QzQBnz7wNOaXerQ"));
            Upload.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}