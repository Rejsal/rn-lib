package com.rnupload.fs;

import android.net.Uri;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FS {


    public static  byte[] read(String filepath) {
        try {
            InputStream inputStream = getInputStream(filepath);
            byte[] inputData = getInputStreamBytes(inputStream);
            String base64Content = Base64.encodeToString(inputData, Base64.NO_WRAP);
            return (inputData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new byte[1];
    }
    public static byte[] read(String filepath, int length, int position) {
        try {
            InputStream inputStream = getInputStream(filepath);
            byte[] buffer = new byte[length];
            inputStream.skip(position);
            int bytesRead = inputStream.read(buffer, 0, length);

           // String base64Content = Base64.encodeToString(buffer, 0, bytesRead, Base64.NO_WRAP);
            return  buffer;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new byte[1];

    }

    private static InputStream getInputStream(String filepath) {
      //  Uri uri = getFileUri(filepath);
        InputStream stream =null;
        try {
            File f = new File(filepath);
            if(f.exists()) {
                stream = new FileInputStream(f);
            }

        } catch (FileNotFoundException ex) {
        }

        return stream;
    }
    private static Uri getFileUri(String filepath) {
        Uri uri = Uri.parse(filepath);
        if (uri.getScheme() == null) {
            // No prefix, assuming that provided path is absolute path to file
            File file = new File(filepath);
            if (file.isDirectory()) {
                return null;
            }
            uri = Uri.parse("file://" + filepath);
        }
        return uri;
    }
    private static byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        byte[] bytesResult;
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        try {
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            bytesResult = byteBuffer.toByteArray();
        } finally {
            try {
                byteBuffer.close();
            } catch (IOException ignored) {
            }
        }
        return bytesResult;
    }


    public static  int getSize(String selectedPath) {
        File file = new File(selectedPath);
        return Integer.parseInt(String.valueOf(file.length()));
    }
}
