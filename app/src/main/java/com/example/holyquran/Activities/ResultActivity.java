package com.example.holyquran.Activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>> {

    public static final int RESULT_LOADER_ID = 101;
    RecyclerView recyclerView;
    ResultsAdapter resultsAdapter;
    ProgressBar progressBar;
    TextView networkTextView,pagesTextView;

    List<Quran> list = new ArrayList<>();
    //urdu-158,eng-167,spanish-83,french-136,persian-135

    String url;

    public static int TOTAL_RECORDS=0;
    public static int CURRENT_PAGE=0;
    public static String hasNext=null,hasPrev=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.results_progressBar);
        networkTextView=findViewById(R.id.result_network_message);
        pagesTextView=findViewById(R.id.pages_textview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        resultsAdapter = new ResultsAdapter(this, list);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Intent intent=getIntent();
        URLBuilder(intent);
    }

    public void URLBuilder(Intent intent){
        url=intent.getStringExtra("BASEUrl");
        //extracting URL Type
        String TYPE=intent.getStringExtra("URLType");

        //Building URL based Upon type and Position Clicked
        url=url.substring(0,url.indexOf("verses")+7)+TYPE+intent.getStringExtra("ListPosition")+url.substring(url.indexOf("?"));

        System.out.println(url);
        getSupportLoaderManager().initLoader(RESULT_LOADER_ID, null, this).forceLoad();
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