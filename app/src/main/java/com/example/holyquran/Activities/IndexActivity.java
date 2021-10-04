package com.example.holyquran.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.holyquran.Adapters.IndexAdapter;
import com.example.holyquran.Quran;
import com.example.holyquran.R;
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>>,IndexAdapter.indexItemOnClick {

    TextView headerTextView,networkTextView;
    RecyclerView recyclerView;
    IndexAdapter indexAdapter;
    ProgressBar progressBar;


    public static final String BASE_URL="https://api.quran.com/api/v4/verses/?language=en&words=true&translations=167&page=1&per_page=50";
    public static String ChaptersURL="https://api.quran.com/api/v4/chapters?language=en";

    public static String ByChapter="by_chapter/";
    public static String ByPages="by_page/";
    public static String ByJuz="by_juz/";


    //All in one list for incoming data
    List<String> indexList=new ArrayList<>();

    public static final int INDEX_LOADER_ID=100;

    public static String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        headerTextView =findViewById(R.id.IndexHeading);
        networkTextView=findViewById(R.id.index_network_message);
        recyclerView=findViewById(R.id.index_recyclerView);
        progressBar=findViewById(R.id.index_progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        indexAdapter=new IndexAdapter(this,indexList,this);
        recyclerView.setAdapter(indexAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        Intent intent=getIntent();


        initData(intent);
    }

    public void initData(Intent intent){
        type=intent.getStringExtra(MainActivity.INDEX_KEYS);
        headerTextView.setText(type);


        switch (type) {
            case "Juz":
                indexList.clear();

                indexList.add("Alīf-Lām-Mīm");
                indexList.add("Sayaqūlu");
                indexList.add("Tilka ’r-Rusulu");
                indexList.add("Lan tanaloo albirra");
                indexList.add("Wa’l-muḥṣanātu");
                indexList.add("Lā yuḥibbu-’llāhu");
                indexList.add("Wa ’Idha Samiʿū");
                indexList.add("Wa-law annanā");
                indexList.add("Qāla ’l-mala’u");
                indexList.add("Wa-’aʿlamū");
                indexList.add("Yaʿtazerūn");
                indexList.add("Wa mā min dābbatin");
                indexList.add("Wa mā ubarri’u");
                indexList.add("Alīf-Lām-Rā’/Rubamā");
                indexList.add("Subḥāna ’lladhī");
                indexList.add("Qāla ’alam");
                indexList.add("Iqtaraba li’n-nāsi");
                indexList.add("Qad ’aflaḥa");
                indexList.add("Wa-qāla ’lladhīna");
                indexList.add("’A’man Khalaqa");
                indexList.add("Otlu ma oohiya");
                indexList.add("Wa-man yaqnut");
                indexList.add("Wa-Mali");
                indexList.add("Fa-man ’aẓlamu");
                indexList.add("Ilayhi yuraddu");
                indexList.add("Ḥā’ Mīm");
                indexList.add("Qāla fa-mā khaṭbukum");
                indexList.add("Qad samiʿa ’llāhu");
                indexList.add("Tabāraka ’lladhī");
                indexList.add("‘Amma");

                indexAdapter.notifyDataSetChanged();

                networkTextView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                break;
            case "Pages":
                indexList.clear();
                for (int i = 1; i <= 604; i++)
                    indexList.add("Page No. - " + i);

                indexAdapter.notifyDataSetChanged();

                networkTextView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                break;
            case "Chapters":
                getSupportLoaderManager().initLoader(INDEX_LOADER_ID, null, this).forceLoad();
                break;

            case "Specific Verse":

                break;
        }
    }

    /**=========================================== LOADER METHODS =====================================================**/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {
        if(id==INDEX_LOADER_ID)
            return new ApiLoader(this,ChaptersURL,id);


        else return new ApiLoader(this,"",0);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {
        indexList.clear();

        for(int i=0;i<data.size();i++)
            indexList.add(data.get(i).getByChapter());

        indexAdapter.notifyDataSetChanged();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected)
            networkTextView.setText("No Internet Available");
        else
            networkTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quran>> loader) {
            indexAdapter=new IndexAdapter(this,new ArrayList<>(),this);
    }

    /**==================================================== ONCLICK =========================================================**/
    @Override
    public void onClick(int position,String listClickedValue) {

        //created new intent to start results activity by clinked value
        Intent intent=new Intent(IndexActivity.this,ResultActivity.class);
        intent.putExtra("ListPosition",position+1+"");
        switch (type){
            case "Chapters":
                intent.putExtra("URLType",ByChapter);
                break;
            case "Pages":
                intent.putExtra("URLType",ByPages);
                break;
            case "Juz":
                intent.putExtra("URLType",ByJuz);
                break;
        }
        intent.putExtra("BASEUrl",BASE_URL);
        intent.putExtra("ListClickedValue",listClickedValue);
        intent.putExtra("AdapterClickedIndex",String.valueOf(position+1));

        startActivity(intent);
    }


}