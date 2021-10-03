package com.example.holyquran.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.holyquran.Quran;
import com.example.holyquran.R;
import com.example.holyquran.Adapters.ResultsAdapter;
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>> {

    RecyclerView recyclerView;
    ResultsAdapter resultsAdapter;

    ProgressBar progressBar;
    List<Quran> list = new ArrayList<>();

    String url="https://api.quran.com/api/v4/verses/by_chapter/2?language=en&words=true&translations=167&page=1&per_page=50";
    //urdu-158,eng-167,spanish-83,french-136,persian-135

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.results_progressBar);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);




        resultsAdapter = new ResultsAdapter(this,list);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        getSupportLoaderManager().initLoader(1,null,this).forceLoad();

    }

    /**======================================= LOADER FUNCTIONS ===========================================**/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {

        return new ApiLoader(this,url,2);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {
        list.clear();
        list.addAll(data);
        resultsAdapter.notifyDataSetChanged();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {
        resultsAdapter =new ResultsAdapter(this,new ArrayList<>());
    }
}