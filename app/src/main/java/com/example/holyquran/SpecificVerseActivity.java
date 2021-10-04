package com.example.holyquran;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.holyquran.Activities.IndexActivity;
import com.example.holyquran.Activities.MainActivity;
import com.example.holyquran.Adapters.IndexAdapter;
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.List;

public class SpecificVerseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>>,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener,
        IndexAdapter.indexItemOnClick {


    TextView headerTextView,networkTextView;
    LinearLayout specificVerseLayout;
    Spinner chapterSpinner,verseSpinner;
    RadioButton nameRadio, numberRadio;
    Button getVerseButton;

    ProgressBar progressBar;

    List<String> indexList=new ArrayList<>();

    //for specific verse type MODE
    String[] chapterSpinnerList,verseSpinnerList;
    int chapterSpinnerIndex=0,verseSpinnerIndex=0;
    public static int[] versesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_verse);

        headerTextView=findViewById(R.id.specific_verse_heading);
        networkTextView=findViewById(R.id.specific_verse_network_message);
        progressBar=findViewById(R.id.specific_verse_progressBar);

        specificVerseLayout=findViewById(R.id.specific_verse_layout);
        chapterSpinner=findViewById(R.id.chapter_selector_spinner);
        verseSpinner=findViewById(R.id.verse_selector_spinner);
        nameRadio =findViewById(R.id.by_name_radio);
        numberRadio =findViewById(R.id.by_number_radio);
        nameRadio.setChecked(true);
        getVerseButton=findViewById(R.id.get_verse_button);


        hideUIContents();

        Intent intent=getIntent();

        headerTextView.setText(intent.getStringExtra(MainActivity.INDEX_KEYS));

        initUIElements();
    }

    public void hideUIContents(){
        specificVerseLayout.setVisibility(View.GONE);

        networkTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void initUIElements(){
        specificVerseLayout.setVisibility(View.VISIBLE);


        nameRadio.setOnClickListener(this);
        numberRadio.setOnClickListener(this);
        getVerseButton.setOnClickListener(this);

        getSupportLoaderManager().initLoader(IndexActivity.INDEX_LOADER_ID, null, this).forceLoad();

        chapterSpinner.setOnItemSelectedListener(this);
        verseSpinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onClick(View v) {


        if(v==nameRadio){
            //recursion for resetting spinner
            initUIElements();
        }
        else if(v==numberRadio){
            for(int i=0;i<chapterSpinnerList.length;i++)
                chapterSpinnerList[i]="Chapter (Surah) - "+(i+1);

            ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,chapterSpinnerList);
            chapterSpinner.setAdapter(adapter);

            chapterSpinner.setSelection(chapterSpinnerIndex);
        }
        else if(v==getVerseButton)
            onClick(chapterSpinnerIndex,String.valueOf(verseSpinnerIndex+1));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==chapterSpinner)
        {
            chapterSpinnerIndex=position;
        }
        else if(parent==verseSpinner)
        {
            verseSpinnerIndex=position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**==================================================== LOADER METHODS ==============================================**/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {
        hideUIContents();

        if(id==IndexActivity.INDEX_LOADER_ID)
            return new ApiLoader(this, IndexActivity.ChaptersURL,id);

        else
            return new ApiLoader(this,"",0);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {
        specificVerseLayout.setVisibility(View.VISIBLE);

        chapterSpinnerList=new String[data.size()];

        for(int i=0;i<data.size();i++){
            chapterSpinnerList[i]=data.get(i).getByChapter();
        }


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected)
        {
            networkTextView.setText("No Internet Available");
            hideUIContents();
        }
        else
        {
            networkTextView.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);

        /*for Specific Verse type MODE*/
        //for ChapterSelector Spinner
        ArrayAdapter<String> chapterSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,chapterSpinnerList);
        chapterSpinner.setAdapter(chapterSpinnerAdapter);

        chapterSpinner.setSelection(chapterSpinnerIndex);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {

    }

    @Override
    public void onClick(int position, String listClickedValue) {

    }
}