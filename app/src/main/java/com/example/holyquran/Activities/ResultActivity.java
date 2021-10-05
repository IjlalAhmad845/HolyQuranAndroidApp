package com.example.holyquran.Activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holyquran.Adapters.ResultsAdapter;
import com.example.holyquran.Quran;
import com.example.holyquran.R;
import com.example.holyquran.SpecificVerseActivity;
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>>, AdapterView.OnItemSelectedListener {

    public static final int RESULT_LOADER_ID = 101;
    RecyclerView recyclerView;
    ResultsAdapter resultsAdapter;
    ProgressBar progressBar;
    TextView networkTextView,pagesTextView,headerTextView,typeTextView;
    Spinner languageSpinner;

    List<Quran> list = new ArrayList<>();
    //urdu-158,eng-167,spanish-83,french-136,persian-135
    public static HashMap<String,Integer> hashMap=new HashMap<>();
    String[] languages=new String[]{"English","Urdu","Hindi","Persian","Spanish","Italian","Russian","French"};

    String url;

    public static int TOTAL_RECORDS=0;
    public static int CURRENT_PAGE=0;

    String receivingTYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.results_progressBar);
        networkTextView=findViewById(R.id.result_network_message);
        pagesTextView=findViewById(R.id.pages_textview);
        headerTextView=findViewById(R.id.header_text_view);
        typeTextView=findViewById(R.id.type_text_view);
        languageSpinner = (Spinner) findViewById(R.id.language_selector_spinner);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        resultsAdapter = new ResultsAdapter(this, list);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        Intent intent=getIntent();
        typeTextView.setText(intent.getStringExtra("AdapterClickedIndex"));
        //conditions for settings type text view for Content Range
        switch (IndexActivity.type){
            case "Chapters":
                typeTextView.append("-114");
                headerTextView.setText(intent.getStringExtra("ListClickedValue"));
                break;

            case "Pages":
                typeTextView.append("-604");
                headerTextView.setText(intent.getStringExtra("ListClickedValue"));
                break;

            case "Juz":
                typeTextView.append("-30");
                headerTextView.setText(intent.getStringExtra("ListClickedValue"));
                break;

            case "Specific Verse":
                typeTextView.append("-114");
                headerTextView.setText("Verse No. - "+intent.getStringExtra("ListClickedValue"));
                break;
        }


        URLBuilder(intent);

        setSpinner();
    }

    public void URLBuilder(Intent intent){
        url=intent.getStringExtra("BASEUrl");
        //extracting URL Type
        receivingTYPE=intent.getStringExtra("URLType");

        if(receivingTYPE.equals(IndexActivity.ByChapter) || receivingTYPE.equals(IndexActivity.ByPages) || receivingTYPE.equals(IndexActivity.ByJuz))
            //Building URL based Upon type and Position Clicked
            url=url.substring(0,url.indexOf("verses")+7)+receivingTYPE+intent.getStringExtra("ListPosition")+url.substring(url.indexOf("?"));
        else if(receivingTYPE.equals(SpecificVerseActivity.ByVerse))
            url=url.substring(0,url.indexOf("verses")+7)+receivingTYPE+intent.getStringExtra("ListPosition")+":"+
                    intent.getStringExtra("ListClickedValue")+url.substring(url.indexOf("?"));
        System.out.println(url);
        //getSupportLoaderManager().initLoader(RESULT_LOADER_ID, null, this).forceLoad();
    }

    public void NextPage(View v){
        if(CURRENT_PAGE<TOTAL_RECORDS){
            CURRENT_PAGE++;

            url=url.substring(0,url.indexOf("&page=")+6)+CURRENT_PAGE+url.substring(url.indexOf("&per_page"));
            getSupportLoaderManager().restartLoader(RESULT_LOADER_ID, null, this).forceLoad();
        }
    }

    public void PrevPage(View v){
        if(CURRENT_PAGE>1){
            CURRENT_PAGE--;
            url=url.substring(0,url.indexOf("&page=")+6)+CURRENT_PAGE+url.substring(url.indexOf("&per_page"));
            getSupportLoaderManager().restartLoader(RESULT_LOADER_ID, null, this).forceLoad();
        }
    }

    public void setSpinner() {

       hashMap.put(languages[0],84);//English
        hashMap.put(languages[1],234);//urdu
        hashMap.put(languages[2],122);//hindi
        hashMap.put(languages[3],135);//persian
        hashMap.put(languages[4],28);//spanish
        hashMap.put(languages[5],209);//italian
        hashMap.put(languages[6],79);//russian
        hashMap.put(languages[7],31);//french



        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,languages);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(receivingTYPE.equals(IndexActivity.ByChapter) || receivingTYPE.equals(IndexActivity.ByPages) || receivingTYPE.equals(IndexActivity.ByJuz))
            url=url.substring(0,url.indexOf("translations")+13)+hashMap.get(languages[position])+url.substring(url.indexOf("&page"));
        else
            url=url.substring(0,url.indexOf("translations")+13)+hashMap.get(languages[position]);
        //getSupportLoaderManager().restartLoader(RESULT_LOADER_ID, null, this).forceLoad();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * ======================================= LOADER FUNCTIONS ===========================================
     **/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {

        progressBar.setVisibility(View.VISIBLE);
        networkTextView.setVisibility(View.VISIBLE);
        list.clear();
        resultsAdapter.notifyDataSetChanged();

        if (id == RESULT_LOADER_ID)
            return new ApiLoader(this, url, id);
        else
            return new ApiLoader(this, "", 0);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {
        list.clear();
        list.addAll(data);
        resultsAdapter.notifyDataSetChanged();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected)
            networkTextView.setText("No Internet Available");
        else{
            networkTextView.setVisibility(View.GONE);
            pagesTextView.setText(CURRENT_PAGE+"/"+TOTAL_RECORDS);
        }

        progressBar.setVisibility(View.GONE);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {
        resultsAdapter = new ResultsAdapter(this, new ArrayList<>());
    }
}