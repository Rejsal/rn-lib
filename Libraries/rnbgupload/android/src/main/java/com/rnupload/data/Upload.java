package com.rnupload.data;

import android.util.Log;

import com.rnupload.fs.FS;

import java.util.ArrayList;

public class Upload {

    private static  Upload  instance = new Upload();

    private static Upload getInstance(){
            return instance;
    }

    public static void init(){
        getInstance().startUpload();
    }


    boolean isUploading = false;
    UPData uploadingObject = null;

    private   void startUpload(){

        if(isUploading){
            Log.d("upload on progress", uploadingObject.toString());
            return;
        }
        isUploading = true;

        ArrayList<UPData> data = UPData.loadData();
        if(data.size() != 0){
            uploadingObject =  data.get(0);
            upload();
            //REMOVE THIS OBJECT

            UPData.remove(uploadingObject);
            isUploading = false;
            //CALL LOOP
            startUpload();
        }else {
            isUploading = false;
        }
    }

    private void upload(){

        if(isUploading && uploadingObject != null){

            for (int i = uploadingObject.current; i < uploadingObject.totalCount ; i++) {

                try {
                    NetWork example = new NetWork();
                    String url = uploadingObject.url+"?chunk="+i+"&chunks="+uploadingObject.totalCount+"&name=randam3.mp4";
                  int  chunkSize = uploadingObject.fileSize/uploadingObject.totalCount;
                    int skip = chunkSize*i;

                    if( i+1 == uploadingObject.totalCount ){
                        chunkSize =  uploadingObject.fileSize - skip;
                    }
                    String response = example.post(url, FS.read(uploadingObject.fileUrl,chunkSize,skip),uploadingObject.token);
                    System.out.println(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //Log.d("Upload",i+"--"+uploadingObject.toString());

            }
        }

    }

}
