package com.example.holyquran.Utils;

import android.util.Log;

import com.example.holyquran.Quran;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkRequest {
    private static final String TAG = "NetworkRequest";


    public static List<Quran> getVersesListFromJSON(String JSON){
        List<Quran> list=new ArrayList<>();

        int size=0;
        StringBuilder verseTranslation,arabic;
        try {
            JSONObject rootObject=new JSONObject(JSON);
            size=rootObject.getJSONObject("pagination").getInt("per_page");
            JSONArray verses=rootObject.getJSONArray("verses");
            for(int i=0;i<size;i++){
                verseTranslation=new StringBuilder();
                arabic=new StringBuilder();
                JSONObject currentVerse=verses.getJSONObject(i);
                JSONArray words=currentVerse.getJSONArray("words");

                verseTranslation.append(currentVerse.getJSONArray("translations").getJSONObject(0).getString("text"));
                for(int j=0;j<words.length()-1;j++){
                    if(!words.getJSONObject(j).getJSONObject("translation").getString("text").equals("null")) {
                        arabic.append(words.getJSONObject(j).getJSONObject("transliteration").getString("text"));
                        arabic.append(" ");
                    }
                }

                list.add(new Quran(arabic.toString(),verseTranslation.toString()));
            }
            Log.d(TAG, size+"");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, size+"");
        }

        return list;
    }

}
