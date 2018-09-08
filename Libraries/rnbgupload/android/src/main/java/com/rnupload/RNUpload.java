package com.rnupload;

import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class RNUpload extends ReactContextBaseJavaModule {

    public RNUpload(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "RNUpload";
    }

    @ReactMethod
    public void test(){
        Log.d("wOW","from RN");
    }
}
