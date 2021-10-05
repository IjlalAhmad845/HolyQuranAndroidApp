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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.holyquran.Adapters.IndexAdapter;
import com.example.holyquran.Quran;
import com.example.holyquran.R;
import com.example.holyquran.Utils.ApiLoader;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quran>>,IndexAdapter.indexItemOnClick,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    TextView headerTextView,networkTextView;
    RecyclerView recyclerView;
    IndexAdapter indexAdapter;
    ProgressBar progressBar;

    //for specific verse type MODE
    LinearLayout specificVerseLayout;
    Spinner chapterSpinner,verseSpinner;
    RadioButton nameRadio, numberRadio;
    Button getVerseButton;



    public static final String BASE_URL="https://api.quran.com/api/v4/verses/?language=en&words=true&translations=84&page=1&per_page=50";
    public static String ChaptersURL="https://api.quran.com/api/v4/chapters?language=en";

    public static String ByChapter="by_chapter/";
    public static String ByPages="by_page/";
    public static String ByJuz="by_juz/";
    public static String ByKey="by_key/";


    //All in one list for incoming data
    List<String> indexList=new ArrayList<>();

    //for specific verse type MODE
    String[] chapterSpinnerList,verseSpinnerList;
    int chapterSpinnerIndex=0,verseSpinnerIndex=0;
    public static int[] versesCount;


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

        //for specific verse type MODE
        specificVerseLayout=findViewById(R.id.specific_verse_layout);
        chapterSpinner=findViewById(R.id.chapter_selector_spinner);
        verseSpinner=findViewById(R.id.verse_selector_spinner);
        nameRadio =findViewById(R.id.by_name_radio);
        numberRadio =findViewById(R.id.by_number_radio);
        nameRadio.setChecked(true);
        getVerseButton=findViewById(R.id.get_verse_button);


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
                verseByTypeMode();

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
                verseByTypeMode();

                for (int i = 1; i <= 604; i++)
                    indexList.add("Page No. - " + i);

                indexAdapter.notifyDataSetChanged();

                networkTextView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                break;
            case "Chapters":
                verseByTypeMode();

                getSupportLoaderManager().initLoader(INDEX_LOADER_ID, null, this).forceLoad();
                break;

            case "Specific Verse":
                specificVerseMode();
                break;
        }
    }

    public void verseByTypeMode(){
        specificVerseLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        networkTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void specificVerseMode(){
        specificVerseLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        nameRadio.setOnClickListener(this);
        numberRadio.setOnClickListener(this);
        getVerseButton.setOnClickListener(this);

        getSupportLoaderManager().initLoader(INDEX_LOADER_ID, null, this).forceLoad();

        chapterSpinner.setOnItemSelectedListener(this);
        verseSpinner.setOnItemSelectedListener(this);

    }



    @Override
    public void onClick(View v) {

        if(v==nameRadio){
            //recursion for resetting spinner
            specificVerseMode();
        }
        else if(v==numberRadio){
            for(int i=0;i<indexList.size();i++)
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

            verseSpinnerList=new String[versesCount[position]];
              for(int i=0;i<versesCount[position];i++)
                verseSpinnerList[i]=String.valueOf(i+1);

            ArrayAdapter<String> verseSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,verseSpinnerList);
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

    /**=========================================== LOADER METHODS =====================================================**/
    @NonNull
    @Override
    public Loader<List<Quran>> onCreateLoader(int id, @Nullable Bundle args) {
        verseByTypeMode();

        if(id==INDEX_LOADER_ID)
            return new ApiLoader(this,ChaptersURL,id);

        else
            return new ApiLoader(this,"",0);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quran>> loader, List<Quran> data) {
        specificVerseLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        indexList.clear();
        chapterSpinnerList=new String[data.size()];
        versesCount=new int[data.size()];

        for(int i=0;i<data.size();i++){
            indexList.add(data.get(i).getByChapter());
            chapterSpinnerList[i]=data.get(i).getByChapter();
            versesCount[i]=data.get(i).getVerseCount();
        }

        indexAdapter.notifyDataSetChanged();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected)
        {
            networkTextView.setText("No Internet Available");
            verseByTypeMode();
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


        //for verseSelector Spinner
        verseSpinnerList=new String[versesCount[chapterSpinnerIndex]];
        for(int i=0;i<versesCount[chapterSpinnerIndex];i++)
            verseSpinnerList[i]=String.valueOf(i+1);

        ArrayAdapter<String> verseSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,verseSpinnerList);
        verseSpinner.setAdapter(verseSpinnerAdapter);

        verseSpinner.setSelection(verseSpinnerIndex);

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
            case "Specific Verse":
                intent.putExtra("URLType",ByKey);
                break;
        }
        intent.putExtra("BASEUrl",BASE_URL);
        intent.putExtra("ListClickedValue",listClickedValue);
        intent.putExtra("AdapterClickedIndex",String.valueOf(position+1));

        startActivity(intent);
    }


}