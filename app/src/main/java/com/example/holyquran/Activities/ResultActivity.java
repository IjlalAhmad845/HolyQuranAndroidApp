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
    TextView networkTextView;

    List<Quran> list = new ArrayList<>();
    //urdu-158,eng-167,spanish-83,french-136,persian-135
    String url = "https://api.quran.com/api/v4/verses/by_chapter/?language=en&words=true&translations=167&page=1&per_page=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.results_progressBar);
        networkTextView=findViewById(R.id.result_network_message);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        resultsAdapter = new ResultsAdapter(this, list);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Intent intent=getIntent();
        URLBuilder(intent);
    }

    public void URLBuilder(Intent intent){
        url=url.substring(0,url.indexOf("?"))+intent.getStringExtra("ChapterNumber")+url.substring(url.indexOf("?"));

        getSupportLoaderManager().initLoader(RESULT_LOADER_ID, null, this).forceLoad();
    }
    /**
     * ======================================= LOADER FUNCTIONS ===========================================
     **/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {

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
        else
            networkTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {
        resultsAdapter = new ResultsAdapter(this, new ArrayList<>());
    }
}