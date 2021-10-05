package com.example.holyquran.Activities;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.example.holyquran.Adapters.IndexAdapter;
import com.example.holyquran.Quran;
import com.example.holyquran.R;
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

    public static final String BASE_URL="https://api.quran.com/api/v4/verses/?language=en&words=true&translations=84";
    public static final String ByVerse="by_key/";

    ArrayAdapter<String> chapterSpinnerAdapter,verseSpinnerAdapter;

    //List of permanent storage for Chapter Names
    List<String> chaptersList=new ArrayList<>();

    String[] chapterSpinnerArray, verseSpinnerArray;
    int chapterSpinnerIndex=0,verseSpinnerIndex=0;
    public static int[] versesCount;

    public static final int SPECIFIC_VERSE_LOADER=102;
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

        //initializing type when this activity is opened instead of IndexActivity
        IndexActivity.type=intent.getStringExtra(MainActivity.INDEX_KEYS);

        initUIElements();
    }

    public void hideUIContents(){
        specificVerseLayout.setVisibility(View.GONE);
        networkTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void initUIElements(){

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

            for(int i = 0; i< chaptersList.size(); i++)
                chapterSpinnerArray[i]=chaptersList.get(i);

            chapterSpinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, chapterSpinnerArray);
            chapterSpinner.setAdapter(chapterSpinnerAdapter);
            chapterSpinner.setSelection(chapterSpinnerIndex);

        }
        else if(v==numberRadio){

           for(int i = 0; i< chaptersList.size(); i++)
                chapterSpinnerArray[i]="Chapter (Surah) - "+(i+1);

            chapterSpinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, chapterSpinnerArray);
            chapterSpinner.setAdapter(chapterSpinnerAdapter);
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

            verseSpinnerArray =new String[versesCount[position]];
            for(int i=0;i<versesCount[position];i++)
                verseSpinnerArray[i]=String.valueOf(i+1);

            verseSpinnerAdapter=null;
            verseSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, verseSpinnerArray);
            verseSpinner.setAdapter(verseSpinnerAdapter);
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

        chaptersList.clear();
        //Total Verses in all Chapters
        versesCount=new int[data.size()];

        for(int i=0;i<data.size();i++){
            chaptersList.add(data.get(i).getByChapter());
            versesCount[i]=data.get(i).getVerseCount();
        }


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected)
        {
            networkTextView.setText(R.string.offlineNetwork);
            hideUIContents();
        }
        else if(data.size()==0)
        {
            networkTextView.setText(R.string.timeout);
            specificVerseLayout.setVisibility(View.GONE);
        }
        else
            networkTextView.setVisibility(View.GONE);

        if(data.size()==0)
        {
            networkTextView.setText(R.string.timeout);
            specificVerseLayout.setVisibility(View.GONE);
        }

        progressBar.setVisibility(View.GONE);

        /*For ChapterSelector Spinner*/
        chapterSpinnerArray = new String[chaptersList.size()];
        chapterSpinnerArray=chaptersList.toArray(chapterSpinnerArray);

        chapterSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chapterSpinnerArray);
        chapterSpinner.setAdapter(chapterSpinnerAdapter);

        chapterSpinner.setSelection(chapterSpinnerIndex);

        /*For verseSelector Spinner*/

        if(data.size()!=0){
            //defined new array for verses list in current Chapter
            verseSpinnerArray =new String[versesCount[chapterSpinnerIndex]];

            for(int i=0;i<versesCount[chapterSpinnerIndex];i++)
                verseSpinnerArray[i]=String.valueOf(i+1);

            verseSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, verseSpinnerArray);
            verseSpinner.setAdapter(verseSpinnerAdapter);
            verseSpinner.setSelection(verseSpinnerIndex);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {
            chapterSpinnerAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, (Object[]) null);
            verseSpinnerAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, (Object[]) null);
    }

    @Override
    public void onClick(int position, String listClickedValue) {

        Intent intent=new Intent(SpecificVerseActivity.this, ResultActivity.class);
        intent.putExtra("ListPosition",position+1+"");

        intent.putExtra("URLType",ByVerse);

        intent.putExtra("BASEUrl",BASE_URL);
        intent.putExtra("ListClickedValue",listClickedValue);
        intent.putExtra("AdapterClickedIndex",String.valueOf(position+1));

        startActivity(intent);
    }

}