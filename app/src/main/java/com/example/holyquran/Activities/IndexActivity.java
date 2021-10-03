package com.example.holyquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.holyquran.R;

public class IndexActivity extends AppCompatActivity {

    TextView headerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        headerTextView =findViewById(R.id.IndexHeading);
        Intent intent=getIntent();

        headerTextView.setText(intent.getStringExtra(MainActivity.INDEX_KEYS));
    }
}