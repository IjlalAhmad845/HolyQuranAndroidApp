package com.example.holyquran.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.holyquran.R;

public class MainActivity extends AppCompatActivity {

    public static final String INDEX_KEYS="indexActivityHeaders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void chapterButton(View v){
        Intent intent=new Intent(MainActivity.this,IndexActivity.class);
        intent.putExtra(INDEX_KEYS,this.getResources().getString(R.string.Chapters));
        startActivity(intent);
    }

    public void pageButton(View v){
        Intent intent=new Intent(MainActivity.this,IndexActivity.class);
        intent.putExtra(INDEX_KEYS,this.getResources().getString(R.string.Pages));
        startActivity(intent);
    }

    public void juzButton(View v){
        Intent intent=new Intent(MainActivity.this,IndexActivity.class);
        intent.putExtra(INDEX_KEYS,this.getResources().getString(R.string.Juz));
        startActivity(intent);
    }

    public void specificVerseButton(View v){
        Intent intent=new Intent(MainActivity.this, SpecificVerseActivity.class);
        intent.putExtra(INDEX_KEYS,this.getResources().getString(R.string.SpecificVerse));
        startActivity(intent);
    }
}