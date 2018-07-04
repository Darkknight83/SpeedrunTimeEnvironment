package com.example.speedruntimeenvironment.controllers.speedrun.http.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class UTIL {
    private static final String TAG = "UTIL";

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            Log.e(TAG, "getBitmapFromURL: Couldn't get Image as Bitmap", e);
            return null;
        }
    }

    public static int[] erzeugeTimeFormat(long num)
    {
        Log.i(TAG, "erzeugeTimeFormat: START");
        int hours = (int) num / 3600;
        int remainder = (int) num - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        int[] ints = {hours , mins , secs};
        return ints;
    }
}
