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

    static String JSONQuery="{\n" +
            "  \"chapters\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 5,\n" +
            "      \"bismillah_pre\": false,\n" +
            "      \"name_simple\": \"Al-Fatihah\",\n" +
            "      \"name_complex\": \"Al-Fātiĥah\",\n" +
            "      \"name_arabic\": \"الفاتحة\",\n" +
            "      \"verses_count\": 7,\n" +
            "      \"pages\": [\n" +
            "        1,\n" +
            "        1\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Opener\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 87,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Baqarah\",\n" +
            "      \"name_complex\": \"Al-Baqarah\",\n" +
            "      \"name_arabic\": \"البقرة\",\n" +
            "      \"verses_count\": 286,\n" +
            "      \"pages\": [\n" +
            "        2,\n" +
            "        49\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Cow\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 89,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ali 'Imran\",\n" +
            "      \"name_complex\": \"Āli `Imrān\",\n" +
            "      \"name_arabic\": \"آل عمران\",\n" +
            "      \"verses_count\": 200,\n" +
            "      \"pages\": [\n" +
            "        50,\n" +
            "        76\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Family of Imran\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 92,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nisa\",\n" +
            "      \"name_complex\": \"An-Nisā\",\n" +
            "      \"name_arabic\": \"النساء\",\n" +
            "      \"verses_count\": 176,\n" +
            "      \"pages\": [\n" +
            "        77,\n" +
            "        106\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Women\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 5,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 112,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ma'idah\",\n" +
            "      \"name_complex\": \"Al-Mā'idah\",\n" +
            "      \"name_arabic\": \"المائدة\",\n" +
            "      \"verses_count\": 120,\n" +
            "      \"pages\": [\n" +
            "        106,\n" +
            "        127\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Table Spread\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 6,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 55,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-An'am\",\n" +
            "      \"name_complex\": \"Al-'An`ām\",\n" +
            "      \"name_arabic\": \"الأنعام\",\n" +
            "      \"verses_count\": 165,\n" +
            "      \"pages\": [\n" +
            "        128,\n" +
            "        150\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Cattle\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 39,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-A'raf\",\n" +
            "      \"name_complex\": \"Al-'A`rāf\",\n" +
            "      \"name_arabic\": \"الأعراف\",\n" +
            "      \"verses_count\": 206,\n" +
            "      \"pages\": [\n" +
            "        151,\n" +
            "        176\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Heights\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 8,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 88,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Anfal\",\n" +
            "      \"name_complex\": \"Al-'Anfāl\",\n" +
            "      \"name_arabic\": \"الأنفال\",\n" +
            "      \"verses_count\": 75,\n" +
            "      \"pages\": [\n" +
            "        177,\n" +
            "        186\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Spoils of War\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 113,\n" +
            "      \"bismillah_pre\": false,\n" +
            "      \"name_simple\": \"At-Tawbah\",\n" +
            "      \"name_complex\": \"At-Tawbah\",\n" +
            "      \"name_arabic\": \"التوبة\",\n" +
            "      \"verses_count\": 129,\n" +
            "      \"pages\": [\n" +
            "        187,\n" +
            "        207\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Repentance\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 51,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Yunus\",\n" +
            "      \"name_complex\": \"Yūnus\",\n" +
            "      \"name_arabic\": \"يونس\",\n" +
            "      \"verses_count\": 109,\n" +
            "      \"pages\": [\n" +
            "        208,\n" +
            "        221\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Jonah\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 11,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 52,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Hud\",\n" +
            "      \"name_complex\": \"Hūd\",\n" +
            "      \"name_arabic\": \"هود\",\n" +
            "      \"verses_count\": 123,\n" +
            "      \"pages\": [\n" +
            "        221,\n" +
            "        235\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Hud\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 12,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 53,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Yusuf\",\n" +
            "      \"name_complex\": \"Yūsuf\",\n" +
            "      \"name_arabic\": \"يوسف\",\n" +
            "      \"verses_count\": 111,\n" +
            "      \"pages\": [\n" +
            "        235,\n" +
            "        248\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Joseph\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 13,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 96,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ar-Ra'd\",\n" +
            "      \"name_complex\": \"Ar-Ra`d\",\n" +
            "      \"name_arabic\": \"الرعد\",\n" +
            "      \"verses_count\": 43,\n" +
            "      \"pages\": [\n" +
            "        249,\n" +
            "        255\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Thunder\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 14,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 72,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ibrahim\",\n" +
            "      \"name_complex\": \"Ibrāhīm\",\n" +
            "      \"name_arabic\": \"ابراهيم\",\n" +
            "      \"verses_count\": 52,\n" +
            "      \"pages\": [\n" +
            "        255,\n" +
            "        261\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Abraham\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 15,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 54,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Hijr\",\n" +
            "      \"name_complex\": \"Al-Ĥijr\",\n" +
            "      \"name_arabic\": \"الحجر\",\n" +
            "      \"verses_count\": 99,\n" +
            "      \"pages\": [\n" +
            "        262,\n" +
            "        267\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Rocky Tract\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 16,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 70,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nahl\",\n" +
            "      \"name_complex\": \"An-Naĥl\",\n" +
            "      \"name_arabic\": \"النحل\",\n" +
            "      \"verses_count\": 128,\n" +
            "      \"pages\": [\n" +
            "        267,\n" +
            "        281\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Bee\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 17,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 50,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Isra\",\n" +
            "      \"name_complex\": \"Al-'Isrā\",\n" +
            "      \"name_arabic\": \"الإسراء\",\n" +
            "      \"verses_count\": 111,\n" +
            "      \"pages\": [\n" +
            "        282,\n" +
            "        293\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Night Journey\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 18,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 69,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Kahf\",\n" +
            "      \"name_complex\": \"Al-Kahf\",\n" +
            "      \"name_arabic\": \"الكهف\",\n" +
            "      \"verses_count\": 110,\n" +
            "      \"pages\": [\n" +
            "        293,\n" +
            "        304\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Cave\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 19,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 44,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Maryam\",\n" +
            "      \"name_complex\": \"Maryam\",\n" +
            "      \"name_arabic\": \"مريم\",\n" +
            "      \"verses_count\": 98,\n" +
            "      \"pages\": [\n" +
            "        305,\n" +
            "        312\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Mary\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 20,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 45,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Taha\",\n" +
            "      \"name_complex\": \"Ţāhā\",\n" +
            "      \"name_arabic\": \"طه\",\n" +
            "      \"verses_count\": 135,\n" +
            "      \"pages\": [\n" +
            "        312,\n" +
            "        321\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Ta-Ha\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 21,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 73,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Anbya\",\n" +
            "      \"name_complex\": \"Al-'Anbyā\",\n" +
            "      \"name_arabic\": \"الأنبياء\",\n" +
            "      \"verses_count\": 112,\n" +
            "      \"pages\": [\n" +
            "        322,\n" +
            "        331\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Prophets\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 22,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 103,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Hajj\",\n" +
            "      \"name_complex\": \"Al-Ĥajj\",\n" +
            "      \"name_arabic\": \"الحج\",\n" +
            "      \"verses_count\": 78,\n" +
            "      \"pages\": [\n" +
            "        332,\n" +
            "        341\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Pilgrimage\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 23,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 74,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mu'minun\",\n" +
            "      \"name_complex\": \"Al-Mu'minūn\",\n" +
            "      \"name_arabic\": \"المؤمنون\",\n" +
            "      \"verses_count\": 118,\n" +
            "      \"pages\": [\n" +
            "        342,\n" +
            "        349\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Believers\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 24,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 102,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nur\",\n" +
            "      \"name_complex\": \"An-Nūr\",\n" +
            "      \"name_arabic\": \"النور\",\n" +
            "      \"verses_count\": 64,\n" +
            "      \"pages\": [\n" +
            "        350,\n" +
            "        359\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Light\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 25,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 42,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Furqan\",\n" +
            "      \"name_complex\": \"Al-Furqān\",\n" +
            "      \"name_arabic\": \"الفرقان\",\n" +
            "      \"verses_count\": 77,\n" +
            "      \"pages\": [\n" +
            "        359,\n" +
            "        366\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Criterion\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 26,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 47,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ash-Shu'ara\",\n" +
            "      \"name_complex\": \"Ash-Shu`arā\",\n" +
            "      \"name_arabic\": \"الشعراء\",\n" +
            "      \"verses_count\": 227,\n" +
            "      \"pages\": [\n" +
            "        367,\n" +
            "        376\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Poets\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 27,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 48,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Naml\",\n" +
            "      \"name_complex\": \"An-Naml\",\n" +
            "      \"name_arabic\": \"النمل\",\n" +
            "      \"verses_count\": 93,\n" +
            "      \"pages\": [\n" +
            "        377,\n" +
            "        385\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Ant\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 28,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 49,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qasas\",\n" +
            "      \"name_complex\": \"Al-Qaşaş\",\n" +
            "      \"name_arabic\": \"القصص\",\n" +
            "      \"verses_count\": 88,\n" +
            "      \"pages\": [\n" +
            "        385,\n" +
            "        396\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Stories\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 29,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 85,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-'Ankabut\",\n" +
            "      \"name_complex\": \"Al-`Ankabūt\",\n" +
            "      \"name_arabic\": \"العنكبوت\",\n" +
            "      \"verses_count\": 69,\n" +
            "      \"pages\": [\n" +
            "        396,\n" +
            "        404\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Spider\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 30,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 84,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ar-Rum\",\n" +
            "      \"name_complex\": \"Ar-Rūm\",\n" +
            "      \"name_arabic\": \"الروم\",\n" +
            "      \"verses_count\": 60,\n" +
            "      \"pages\": [\n" +
            "        404,\n" +
            "        410\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Romans\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 31,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 57,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Luqman\",\n" +
            "      \"name_complex\": \"Luqmān\",\n" +
            "      \"name_arabic\": \"لقمان\",\n" +
            "      \"verses_count\": 34,\n" +
            "      \"pages\": [\n" +
            "        411,\n" +
            "        414\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Luqman\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 32,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 75,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"As-Sajdah\",\n" +
            "      \"name_complex\": \"As-Sajdah\",\n" +
            "      \"name_arabic\": \"السجدة\",\n" +
            "      \"verses_count\": 30,\n" +
            "      \"pages\": [\n" +
            "        415,\n" +
            "        417\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Prostration\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 33,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 90,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ahzab\",\n" +
            "      \"name_complex\": \"Al-'Aĥzāb\",\n" +
            "      \"name_arabic\": \"الأحزاب\",\n" +
            "      \"verses_count\": 73,\n" +
            "      \"pages\": [\n" +
            "        418,\n" +
            "        427\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Combined Forces\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 34,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 58,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Saba\",\n" +
            "      \"name_complex\": \"Saba\",\n" +
            "      \"name_arabic\": \"سبإ\",\n" +
            "      \"verses_count\": 54,\n" +
            "      \"pages\": [\n" +
            "        428,\n" +
            "        434\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Sheba\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 35,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 43,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Fatir\",\n" +
            "      \"name_complex\": \"Fāţir\",\n" +
            "      \"name_arabic\": \"فاطر\",\n" +
            "      \"verses_count\": 45,\n" +
            "      \"pages\": [\n" +
            "        434,\n" +
            "        440\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Originator\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 36,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 41,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ya-Sin\",\n" +
            "      \"name_complex\": \"Yā-Sīn\",\n" +
            "      \"name_arabic\": \"يس\",\n" +
            "      \"verses_count\": 83,\n" +
            "      \"pages\": [\n" +
            "        440,\n" +
            "        445\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Ya Sin\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 37,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 56,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"As-Saffat\",\n" +
            "      \"name_complex\": \"Aş-Şāffāt\",\n" +
            "      \"name_arabic\": \"الصافات\",\n" +
            "      \"verses_count\": 182,\n" +
            "      \"pages\": [\n" +
            "        446,\n" +
            "        452\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Those who set the Ranks\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 38,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 38,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Sad\",\n" +
            "      \"name_complex\": \"Şād\",\n" +
            "      \"name_arabic\": \"ص\",\n" +
            "      \"verses_count\": 88,\n" +
            "      \"pages\": [\n" +
            "        453,\n" +
            "        458\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Letter \\\"Saad\\\"\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 39,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 59,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Az-Zumar\",\n" +
            "      \"name_complex\": \"Az-Zumar\",\n" +
            "      \"name_arabic\": \"الزمر\",\n" +
            "      \"verses_count\": 75,\n" +
            "      \"pages\": [\n" +
            "        458,\n" +
            "        467\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Troops\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 40,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 60,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ghafir\",\n" +
            "      \"name_complex\": \"Ghāfir\",\n" +
            "      \"name_arabic\": \"غافر\",\n" +
            "      \"verses_count\": 85,\n" +
            "      \"pages\": [\n" +
            "        467,\n" +
            "        476\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Forgiver\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 41,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 61,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Fussilat\",\n" +
            "      \"name_complex\": \"Fuşşilat\",\n" +
            "      \"name_arabic\": \"فصلت\",\n" +
            "      \"verses_count\": 54,\n" +
            "      \"pages\": [\n" +
            "        477,\n" +
            "        482\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Explained in Detail\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 42,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 62,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ash-Shuraa\",\n" +
            "      \"name_complex\": \"Ash-Shūraá\",\n" +
            "      \"name_arabic\": \"الشورى\",\n" +
            "      \"verses_count\": 53,\n" +
            "      \"pages\": [\n" +
            "        483,\n" +
            "        489\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Consultation\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 43,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 63,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Az-Zukhruf\",\n" +
            "      \"name_complex\": \"Az-Zukhruf\",\n" +
            "      \"name_arabic\": \"الزخرف\",\n" +
            "      \"verses_count\": 89,\n" +
            "      \"pages\": [\n" +
            "        489,\n" +
            "        495\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Ornaments of Gold\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 44,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 64,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ad-Dukhan\",\n" +
            "      \"name_complex\": \"Ad-Dukhān\",\n" +
            "      \"name_arabic\": \"الدخان\",\n" +
            "      \"verses_count\": 59,\n" +
            "      \"pages\": [\n" +
            "        496,\n" +
            "        498\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Smoke\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 45,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 65,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Jathiyah\",\n" +
            "      \"name_complex\": \"Al-Jāthiyah\",\n" +
            "      \"name_arabic\": \"الجاثية\",\n" +
            "      \"verses_count\": 37,\n" +
            "      \"pages\": [\n" +
            "        499,\n" +
            "        502\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Crouching\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 46,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 66,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ahqaf\",\n" +
            "      \"name_complex\": \"Al-'Aĥqāf\",\n" +
            "      \"name_arabic\": \"الأحقاف\",\n" +
            "      \"verses_count\": 35,\n" +
            "      \"pages\": [\n" +
            "        502,\n" +
            "        506\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Wind-Curved Sandhills\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 47,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 95,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Muhammad\",\n" +
            "      \"name_complex\": \"Muĥammad\",\n" +
            "      \"name_arabic\": \"محمد\",\n" +
            "      \"verses_count\": 38,\n" +
            "      \"pages\": [\n" +
            "        507,\n" +
            "        510\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Muhammad\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 48,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 111,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Fath\",\n" +
            "      \"name_complex\": \"Al-Fatĥ\",\n" +
            "      \"name_arabic\": \"الفتح\",\n" +
            "      \"verses_count\": 29,\n" +
            "      \"pages\": [\n" +
            "        511,\n" +
            "        515\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Victory\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 49,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 106,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Hujurat\",\n" +
            "      \"name_complex\": \"Al-Ĥujurāt\",\n" +
            "      \"name_arabic\": \"الحجرات\",\n" +
            "      \"verses_count\": 18,\n" +
            "      \"pages\": [\n" +
            "        515,\n" +
            "        517\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Rooms\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 50,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 34,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Qaf\",\n" +
            "      \"name_complex\": \"Qāf\",\n" +
            "      \"name_arabic\": \"ق\",\n" +
            "      \"verses_count\": 45,\n" +
            "      \"pages\": [\n" +
            "        518,\n" +
            "        520\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Letter \\\"Qaf\\\"\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 51,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 67,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Adh-Dhariyat\",\n" +
            "      \"name_complex\": \"Adh-Dhāriyāt\",\n" +
            "      \"name_arabic\": \"الذاريات\",\n" +
            "      \"verses_count\": 60,\n" +
            "      \"pages\": [\n" +
            "        520,\n" +
            "        523\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Winnowing Winds\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 52,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 76,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Tur\",\n" +
            "      \"name_complex\": \"Aţ-Ţūr\",\n" +
            "      \"name_arabic\": \"الطور\",\n" +
            "      \"verses_count\": 49,\n" +
            "      \"pages\": [\n" +
            "        523,\n" +
            "        525\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Mount\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 53,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 23,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Najm\",\n" +
            "      \"name_complex\": \"An-Najm\",\n" +
            "      \"name_arabic\": \"النجم\",\n" +
            "      \"verses_count\": 62,\n" +
            "      \"pages\": [\n" +
            "        526,\n" +
            "        528\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Star\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 54,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 37,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qamar\",\n" +
            "      \"name_complex\": \"Al-Qamar\",\n" +
            "      \"name_arabic\": \"القمر\",\n" +
            "      \"verses_count\": 55,\n" +
            "      \"pages\": [\n" +
            "        528,\n" +
            "        531\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Moon\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 55,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 97,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ar-Rahman\",\n" +
            "      \"name_complex\": \"Ar-Raĥmān\",\n" +
            "      \"name_arabic\": \"الرحمن\",\n" +
            "      \"verses_count\": 78,\n" +
            "      \"pages\": [\n" +
            "        531,\n" +
            "        534\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Beneficent\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 56,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 46,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Waqi'ah\",\n" +
            "      \"name_complex\": \"Al-Wāqi`ah\",\n" +
            "      \"name_arabic\": \"الواقعة\",\n" +
            "      \"verses_count\": 96,\n" +
            "      \"pages\": [\n" +
            "        534,\n" +
            "        537\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Inevitable\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 57,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 94,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Hadid\",\n" +
            "      \"name_complex\": \"Al-Ĥadīd\",\n" +
            "      \"name_arabic\": \"الحديد\",\n" +
            "      \"verses_count\": 29,\n" +
            "      \"pages\": [\n" +
            "        537,\n" +
            "        541\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Iron\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 58,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 105,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mujadila\",\n" +
            "      \"name_complex\": \"Al-Mujādila\",\n" +
            "      \"name_arabic\": \"المجادلة\",\n" +
            "      \"verses_count\": 22,\n" +
            "      \"pages\": [\n" +
            "        542,\n" +
            "        545\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Pleading Woman\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 59,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 101,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Hashr\",\n" +
            "      \"name_complex\": \"Al-Ĥashr\",\n" +
            "      \"name_arabic\": \"الحشر\",\n" +
            "      \"verses_count\": 24,\n" +
            "      \"pages\": [\n" +
            "        545,\n" +
            "        548\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Exile\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 60,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 91,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mumtahanah\",\n" +
            "      \"name_complex\": \"Al-Mumtaĥanah\",\n" +
            "      \"name_arabic\": \"الممتحنة\",\n" +
            "      \"verses_count\": 13,\n" +
            "      \"pages\": [\n" +
            "        549,\n" +
            "        551\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"She that is to be examined\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 61,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 109,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"As-Saf\",\n" +
            "      \"name_complex\": \"Aş-Şaf\",\n" +
            "      \"name_arabic\": \"الصف\",\n" +
            "      \"verses_count\": 14,\n" +
            "      \"pages\": [\n" +
            "        551,\n" +
            "        552\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Ranks\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 62,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 110,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Jumu'ah\",\n" +
            "      \"name_complex\": \"Al-Jumu`ah\",\n" +
            "      \"name_arabic\": \"الجمعة\",\n" +
            "      \"verses_count\": 11,\n" +
            "      \"pages\": [\n" +
            "        553,\n" +
            "        554\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Congregation, Friday\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 63,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 104,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Munafiqun\",\n" +
            "      \"name_complex\": \"Al-Munāfiqūn\",\n" +
            "      \"name_arabic\": \"المنافقون\",\n" +
            "      \"verses_count\": 11,\n" +
            "      \"pages\": [\n" +
            "        554,\n" +
            "        555\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Hypocrites\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 64,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 108,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Taghabun\",\n" +
            "      \"name_complex\": \"At-Taghābun\",\n" +
            "      \"name_arabic\": \"التغابن\",\n" +
            "      \"verses_count\": 18,\n" +
            "      \"pages\": [\n" +
            "        556,\n" +
            "        557\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Mutual Disillusion\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 65,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 99,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Talaq\",\n" +
            "      \"name_complex\": \"Aţ-Ţalāq\",\n" +
            "      \"name_arabic\": \"الطلاق\",\n" +
            "      \"verses_count\": 12,\n" +
            "      \"pages\": [\n" +
            "        558,\n" +
            "        559\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Divorce\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 66,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 107,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Tahrim\",\n" +
            "      \"name_complex\": \"At-Taĥrīm\",\n" +
            "      \"name_arabic\": \"التحريم\",\n" +
            "      \"verses_count\": 12,\n" +
            "      \"pages\": [\n" +
            "        560,\n" +
            "        561\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Prohibition\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 67,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 77,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mulk\",\n" +
            "      \"name_complex\": \"Al-Mulk\",\n" +
            "      \"name_arabic\": \"الملك\",\n" +
            "      \"verses_count\": 30,\n" +
            "      \"pages\": [\n" +
            "        562,\n" +
            "        564\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Sovereignty\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 68,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 2,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qalam\",\n" +
            "      \"name_complex\": \"Al-Qalam\",\n" +
            "      \"name_arabic\": \"القلم\",\n" +
            "      \"verses_count\": 52,\n" +
            "      \"pages\": [\n" +
            "        564,\n" +
            "        566\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Pen\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 69,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 78,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Haqqah\",\n" +
            "      \"name_complex\": \"Al-Ĥāqqah\",\n" +
            "      \"name_arabic\": \"الحاقة\",\n" +
            "      \"verses_count\": 52,\n" +
            "      \"pages\": [\n" +
            "        566,\n" +
            "        568\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Reality\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 70,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 79,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ma'arij\",\n" +
            "      \"name_complex\": \"Al-Ma`ārij\",\n" +
            "      \"name_arabic\": \"المعارج\",\n" +
            "      \"verses_count\": 44,\n" +
            "      \"pages\": [\n" +
            "        568,\n" +
            "        570\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Ascending Stairways\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 71,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 71,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Nuh\",\n" +
            "      \"name_complex\": \"Nūĥ\",\n" +
            "      \"name_arabic\": \"نوح\",\n" +
            "      \"verses_count\": 28,\n" +
            "      \"pages\": [\n" +
            "        570,\n" +
            "        571\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Noah\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 72,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 40,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Jinn\",\n" +
            "      \"name_complex\": \"Al-Jinn\",\n" +
            "      \"name_arabic\": \"الجن\",\n" +
            "      \"verses_count\": 28,\n" +
            "      \"pages\": [\n" +
            "        572,\n" +
            "        573\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Jinn\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 73,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 3,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Muzzammil\",\n" +
            "      \"name_complex\": \"Al-Muzzammil\",\n" +
            "      \"name_arabic\": \"المزمل\",\n" +
            "      \"verses_count\": 20,\n" +
            "      \"pages\": [\n" +
            "        574,\n" +
            "        575\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Enshrouded One\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 74,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 4,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Muddaththir\",\n" +
            "      \"name_complex\": \"Al-Muddaththir\",\n" +
            "      \"name_arabic\": \"المدثر\",\n" +
            "      \"verses_count\": 56,\n" +
            "      \"pages\": [\n" +
            "        575,\n" +
            "        577\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Cloaked One\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 75,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 31,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qiyamah\",\n" +
            "      \"name_complex\": \"Al-Qiyāmah\",\n" +
            "      \"name_arabic\": \"القيامة\",\n" +
            "      \"verses_count\": 40,\n" +
            "      \"pages\": [\n" +
            "        577,\n" +
            "        578\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Resurrection\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 76,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 98,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Insan\",\n" +
            "      \"name_complex\": \"Al-'Insān\",\n" +
            "      \"name_arabic\": \"الانسان\",\n" +
            "      \"verses_count\": 31,\n" +
            "      \"pages\": [\n" +
            "        578,\n" +
            "        580\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Man\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 77,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 33,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mursalat\",\n" +
            "      \"name_complex\": \"Al-Mursalāt\",\n" +
            "      \"name_arabic\": \"المرسلات\",\n" +
            "      \"verses_count\": 50,\n" +
            "      \"pages\": [\n" +
            "        580,\n" +
            "        581\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Emissaries\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 78,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 80,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Naba\",\n" +
            "      \"name_complex\": \"An-Naba\",\n" +
            "      \"name_arabic\": \"النبإ\",\n" +
            "      \"verses_count\": 40,\n" +
            "      \"pages\": [\n" +
            "        582,\n" +
            "        583\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Tidings\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 79,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 81,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nazi'at\",\n" +
            "      \"name_complex\": \"An-Nāzi`āt\",\n" +
            "      \"name_arabic\": \"النازعات\",\n" +
            "      \"verses_count\": 46,\n" +
            "      \"pages\": [\n" +
            "        583,\n" +
            "        584\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Those who drag forth\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 80,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 24,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"'Abasa\",\n" +
            "      \"name_complex\": \"`Abasa\",\n" +
            "      \"name_arabic\": \"عبس\",\n" +
            "      \"verses_count\": 42,\n" +
            "      \"pages\": [\n" +
            "        585,\n" +
            "        585\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"He Frowned\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 81,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 7,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Takwir\",\n" +
            "      \"name_complex\": \"At-Takwīr\",\n" +
            "      \"name_arabic\": \"التكوير\",\n" +
            "      \"verses_count\": 29,\n" +
            "      \"pages\": [\n" +
            "        586,\n" +
            "        586\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Overthrowing\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 82,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 82,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Infitar\",\n" +
            "      \"name_complex\": \"Al-'Infiţār\",\n" +
            "      \"name_arabic\": \"الإنفطار\",\n" +
            "      \"verses_count\": 19,\n" +
            "      \"pages\": [\n" +
            "        587,\n" +
            "        587\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Cleaving\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 83,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 86,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Mutaffifin\",\n" +
            "      \"name_complex\": \"Al-Muţaffifīn\",\n" +
            "      \"name_arabic\": \"المطففين\",\n" +
            "      \"verses_count\": 36,\n" +
            "      \"pages\": [\n" +
            "        587,\n" +
            "        589\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Defrauding\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 84,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 83,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Inshiqaq\",\n" +
            "      \"name_complex\": \"Al-'Inshiqāq\",\n" +
            "      \"name_arabic\": \"الإنشقاق\",\n" +
            "      \"verses_count\": 25,\n" +
            "      \"pages\": [\n" +
            "        589,\n" +
            "        589\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Sundering\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 85,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 27,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Buruj\",\n" +
            "      \"name_complex\": \"Al-Burūj\",\n" +
            "      \"name_arabic\": \"البروج\",\n" +
            "      \"verses_count\": 22,\n" +
            "      \"pages\": [\n" +
            "        590,\n" +
            "        590\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Mansions of the Stars\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 86,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 36,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Tariq\",\n" +
            "      \"name_complex\": \"Aţ-Ţāriq\",\n" +
            "      \"name_arabic\": \"الطارق\",\n" +
            "      \"verses_count\": 17,\n" +
            "      \"pages\": [\n" +
            "        591,\n" +
            "        591\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Nightcommer\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 87,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 8,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-A'la\",\n" +
            "      \"name_complex\": \"Al-'A`lá\",\n" +
            "      \"name_arabic\": \"الأعلى\",\n" +
            "      \"verses_count\": 19,\n" +
            "      \"pages\": [\n" +
            "        591,\n" +
            "        592\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Most High\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 88,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 68,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ghashiyah\",\n" +
            "      \"name_complex\": \"Al-Ghāshiyah\",\n" +
            "      \"name_arabic\": \"الغاشية\",\n" +
            "      \"verses_count\": 26,\n" +
            "      \"pages\": [\n" +
            "        592,\n" +
            "        592\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Overwhelming\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 89,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 10,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Fajr\",\n" +
            "      \"name_complex\": \"Al-Fajr\",\n" +
            "      \"name_arabic\": \"الفجر\",\n" +
            "      \"verses_count\": 30,\n" +
            "      \"pages\": [\n" +
            "        593,\n" +
            "        594\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Dawn\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 90,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 35,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Balad\",\n" +
            "      \"name_complex\": \"Al-Balad\",\n" +
            "      \"name_arabic\": \"البلد\",\n" +
            "      \"verses_count\": 20,\n" +
            "      \"pages\": [\n" +
            "        594,\n" +
            "        594\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The City\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 91,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 26,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ash-Shams\",\n" +
            "      \"name_complex\": \"Ash-Shams\",\n" +
            "      \"name_arabic\": \"الشمس\",\n" +
            "      \"verses_count\": 15,\n" +
            "      \"pages\": [\n" +
            "        595,\n" +
            "        595\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Sun\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 92,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 9,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Layl\",\n" +
            "      \"name_complex\": \"Al-Layl\",\n" +
            "      \"name_arabic\": \"الليل\",\n" +
            "      \"verses_count\": 21,\n" +
            "      \"pages\": [\n" +
            "        595,\n" +
            "        596\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Night\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 93,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 11,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ad-Duhaa\",\n" +
            "      \"name_complex\": \"Ađ-Đuĥaá\",\n" +
            "      \"name_arabic\": \"الضحى\",\n" +
            "      \"verses_count\": 11,\n" +
            "      \"pages\": [\n" +
            "        596,\n" +
            "        596\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Morning Hours\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 94,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 12,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Ash-Sharh\",\n" +
            "      \"name_complex\": \"Ash-Sharĥ\",\n" +
            "      \"name_arabic\": \"الشرح\",\n" +
            "      \"verses_count\": 8,\n" +
            "      \"pages\": [\n" +
            "        596,\n" +
            "        596\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Relief\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 95,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 28,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Tin\",\n" +
            "      \"name_complex\": \"At-Tīn\",\n" +
            "      \"name_arabic\": \"التين\",\n" +
            "      \"verses_count\": 8,\n" +
            "      \"pages\": [\n" +
            "        597,\n" +
            "        597\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Fig\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 96,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 1,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-'Alaq\",\n" +
            "      \"name_complex\": \"Al-`Alaq\",\n" +
            "      \"name_arabic\": \"العلق\",\n" +
            "      \"verses_count\": 19,\n" +
            "      \"pages\": [\n" +
            "        597,\n" +
            "        597\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Clot\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 97,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 25,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qadr\",\n" +
            "      \"name_complex\": \"Al-Qadr\",\n" +
            "      \"name_arabic\": \"القدر\",\n" +
            "      \"verses_count\": 5,\n" +
            "      \"pages\": [\n" +
            "        598,\n" +
            "        598\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Power\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 98,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 100,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Bayyinah\",\n" +
            "      \"name_complex\": \"Al-Bayyinah\",\n" +
            "      \"name_arabic\": \"البينة\",\n" +
            "      \"verses_count\": 8,\n" +
            "      \"pages\": [\n" +
            "        598,\n" +
            "        599\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Clear Proof\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 99,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 93,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Az-Zalzalah\",\n" +
            "      \"name_complex\": \"Az-Zalzalah\",\n" +
            "      \"name_arabic\": \"الزلزلة\",\n" +
            "      \"verses_count\": 8,\n" +
            "      \"pages\": [\n" +
            "        599,\n" +
            "        599\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Earthquake\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 100,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 14,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-'Adiyat\",\n" +
            "      \"name_complex\": \"Al-`Ādiyāt\",\n" +
            "      \"name_arabic\": \"العاديات\",\n" +
            "      \"verses_count\": 11,\n" +
            "      \"pages\": [\n" +
            "        599,\n" +
            "        600\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Courser\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 101,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 30,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Qari'ah\",\n" +
            "      \"name_complex\": \"Al-Qāri`ah\",\n" +
            "      \"name_arabic\": \"القارعة\",\n" +
            "      \"verses_count\": 11,\n" +
            "      \"pages\": [\n" +
            "        600,\n" +
            "        600\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Calamity\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 102,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 16,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"At-Takathur\",\n" +
            "      \"name_complex\": \"At-Takāthur\",\n" +
            "      \"name_arabic\": \"التكاثر\",\n" +
            "      \"verses_count\": 8,\n" +
            "      \"pages\": [\n" +
            "        600,\n" +
            "        600\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Rivalry in world increase\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 103,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 13,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-'Asr\",\n" +
            "      \"name_complex\": \"Al-`Aşr\",\n" +
            "      \"name_arabic\": \"العصر\",\n" +
            "      \"verses_count\": 3,\n" +
            "      \"pages\": [\n" +
            "        601,\n" +
            "        601\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Declining Day\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 104,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 32,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Humazah\",\n" +
            "      \"name_complex\": \"Al-Humazah\",\n" +
            "      \"name_arabic\": \"الهمزة\",\n" +
            "      \"verses_count\": 9,\n" +
            "      \"pages\": [\n" +
            "        601,\n" +
            "        601\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Traducer\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 105,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 19,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Fil\",\n" +
            "      \"name_complex\": \"Al-Fīl\",\n" +
            "      \"name_arabic\": \"الفيل\",\n" +
            "      \"verses_count\": 5,\n" +
            "      \"pages\": [\n" +
            "        601,\n" +
            "        601\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Elephant\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 106,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 29,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Quraysh\",\n" +
            "      \"name_complex\": \"Quraysh\",\n" +
            "      \"name_arabic\": \"قريش\",\n" +
            "      \"verses_count\": 4,\n" +
            "      \"pages\": [\n" +
            "        602,\n" +
            "        602\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Quraysh\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 107,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 17,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ma'un\",\n" +
            "      \"name_complex\": \"Al-Mā`ūn\",\n" +
            "      \"name_arabic\": \"الماعون\",\n" +
            "      \"verses_count\": 7,\n" +
            "      \"pages\": [\n" +
            "        602,\n" +
            "        602\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Small kindnesses\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 108,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 15,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Kawthar\",\n" +
            "      \"name_complex\": \"Al-Kawthar\",\n" +
            "      \"name_arabic\": \"الكوثر\",\n" +
            "      \"verses_count\": 3,\n" +
            "      \"pages\": [\n" +
            "        602,\n" +
            "        602\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Abundance\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 109,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 18,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Kafirun\",\n" +
            "      \"name_complex\": \"Al-Kāfirūn\",\n" +
            "      \"name_arabic\": \"الكافرون\",\n" +
            "      \"verses_count\": 6,\n" +
            "      \"pages\": [\n" +
            "        603,\n" +
            "        603\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Disbelievers\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 110,\n" +
            "      \"revelation_place\": \"madinah\",\n" +
            "      \"revelation_order\": 114,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nasr\",\n" +
            "      \"name_complex\": \"An-Naşr\",\n" +
            "      \"name_arabic\": \"النصر\",\n" +
            "      \"verses_count\": 3,\n" +
            "      \"pages\": [\n" +
            "        603,\n" +
            "        603\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Divine Support\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 111,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 6,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Masad\",\n" +
            "      \"name_complex\": \"Al-Masad\",\n" +
            "      \"name_arabic\": \"المسد\",\n" +
            "      \"verses_count\": 5,\n" +
            "      \"pages\": [\n" +
            "        603,\n" +
            "        603\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Palm Fiber\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 112,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 22,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Ikhlas\",\n" +
            "      \"name_complex\": \"Al-'Ikhlāş\",\n" +
            "      \"name_arabic\": \"الإخلاص\",\n" +
            "      \"verses_count\": 4,\n" +
            "      \"pages\": [\n" +
            "        604,\n" +
            "        604\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Sincerity\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 113,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 20,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"Al-Falaq\",\n" +
            "      \"name_complex\": \"Al-Falaq\",\n" +
            "      \"name_arabic\": \"الفلق\",\n" +
            "      \"verses_count\": 5,\n" +
            "      \"pages\": [\n" +
            "        604,\n" +
            "        604\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"The Daybreak\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 114,\n" +
            "      \"revelation_place\": \"makkah\",\n" +
            "      \"revelation_order\": 21,\n" +
            "      \"bismillah_pre\": true,\n" +
            "      \"name_simple\": \"An-Nas\",\n" +
            "      \"name_complex\": \"An-Nās\",\n" +
            "      \"name_arabic\": \"الناس\",\n" +
            "      \"verses_count\": 6,\n" +
            "      \"pages\": [\n" +
            "        604,\n" +
            "        604\n" +
            "      ],\n" +
            "      \"translated_name\": {\n" +
            "        \"language_name\": \"english\",\n" +
            "        \"name\": \"Mankind\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static List<String> getChaptersFromJSON(){
        List<String> list=new ArrayList<>();

        try {
            JSONObject rootObject=new JSONObject(JSONQuery);

            JSONArray chaptersArray=rootObject.getJSONArray("chapters");

            for(int i=0;i<chaptersArray.length();i++)
                list.add(chaptersArray.getJSONObject(i).getString("name_complex")+"\t("+chaptersArray.getJSONObject(i).getJSONObject("translated_name").getString("name")+")");

            System.out.println(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
