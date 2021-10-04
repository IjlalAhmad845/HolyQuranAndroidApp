package com.example.holyquran;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.holyquran.Activities.MainActivity;

import java.util.List;

public class SpecificVerseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>>,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener {


    TextView headerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_verse);

        headerTextView=findViewById(R.id.specific_verse_heading);
        Intent intent=getIntent();

        headerTextView.setText(intent.getStringExtra(MainActivity.INDEX_KEYS));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**==================================================== LOADER METHODS ==============================================**/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {

    }
}