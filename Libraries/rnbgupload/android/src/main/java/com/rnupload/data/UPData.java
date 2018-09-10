package com.rnupload.data;

import android.os.Environment;
import android.util.Log;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UPData implements Serializable {

    public static boolean isUpdating = false;

    String url;

    String fileUrl;

    int fileSize;

    int totalCount;

    int current;

    String token;

    public UPData(String url, String fileUrl, int fileSize, int totalCount, int current, String token) {
        this.url = url;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.totalCount = totalCount;
        this.current = current;
        this.token = token;
    }

    @Override
    public String toString() {
        return token + " == "+ current ;
    }

    private static void saveData(ArrayList<UPData> data) {
        FileOutputStream outStream = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            outStream = new FileOutputStream(f);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(data);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static  void addItem(UPData dataItem){
        if(isUpdating){
            //WAIT AS SEC
            addItem(dataItem);
            return;
        }
        isUpdating = true;
        ArrayList<UPData> data = UPData.loadData();
        data.add(dataItem);
        UPData.saveData(data);
        isUpdating = false;

    }

    public static ArrayList<UPData> loadData()
    {
        FileInputStream inStream = null;
        ArrayList<UPData> data;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            inStream = new FileInputStream(f);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            data = ((ArrayList<UPData>) objectInStream.readObject());
            objectInStream.close();
            return  data;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return new ArrayList<UPData>();
    }

    public static void remove(UPData current){

        if(isUpdating){
            //WAIT
            remove(current);
            return;
        }else{
            isUpdating = true;
            ArrayList<UPData> data = loadData();
            UPData sameObject = null;
            for (UPData dataItem:data) {
                if(dataItem.token.equals(current.token)){
                    sameObject = dataItem;
                }
            }
            if(sameObject != null){
                data.remove(sameObject);
                saveData(data);

                Log.d("balance", data.toString());
            }
            isUpdating = false;

        }

    }
}
