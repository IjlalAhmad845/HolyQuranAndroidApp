package com.example.holyquran.Utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.holyquran.Quran;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ApiLoader extends AsyncTaskLoader<List<Quran>> {
    private static final String TAG = "ApiLoader";
    String URL;

    public ApiLoader(@NonNull Context context, String url, int id) {
        super(context);
        this.URL = url;
    }

    @Nullable
    @Override
    public List<Quran> loadInBackground() {
        List<Quran> list;
        System.out.println("hello");


        URL url = null;
        StringBuilder JSONResponse = new StringBuilder();
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            bufferedReader = new BufferedReader(inputStreamReader);

            line = bufferedReader.readLine();
            while (line != null) {
                JSONResponse.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

        //Log.d(TAG, "loadInBackground: "+JSONResponse.toString());
        list = NetworkRequest.getVersesListFromJSON(JSONResponse.toString());
        return list;
    }
}
