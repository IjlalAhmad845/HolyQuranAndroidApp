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


    public static List<Quran> getVersesListByChapter(String JSON){
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

                list.add(new Quran(arabic.toString(),verseTranslation.toString(),null,null));
            }
            Log.d(TAG, size+"");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, size+"");
        }

        return list;
    }


    public static List<Quran> getVersesListByPages(String JSON){
        List<Quran> list=new ArrayList<>();

        return list;
    }
    public static List<Quran> getChaptersFromJSON(String JSON){
        List<Quran> list=new ArrayList<>();

        try {
            JSONObject rootObject=new JSONObject(JSON);

            JSONArray chaptersArray=rootObject.getJSONArray("chapters");

            for(int i=0;i<chaptersArray.length();i++)
                list.add(new Quran(null,null,chaptersArray.getJSONObject(i).getString("name_complex")+
                        "\t("+chaptersArray.getJSONObject(i).getJSONObject("translated_name").getString("name")+")",null));

            System.out.println(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
