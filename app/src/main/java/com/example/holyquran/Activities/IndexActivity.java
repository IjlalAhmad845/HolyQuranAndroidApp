package com.example.holyquran.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.holyquran.Adapters.IndexAdapter;
import com.example.holyquran.R;
import com.example.holyquran.Utils.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {

    TextView headerTextView,networkTextView;
    RecyclerView recyclerView;
    IndexAdapter indexAdapter;
    ProgressBar progressBar;

    String ChaptersURL="https://api.quran.com/api/v4/chapters?language=en\n";
    String PagesURL="https://api.quran.com/api/v4/verses/by_page/1?language=en&words=true&page=1&per_page=10\n";

    List<String> chaptersList=new ArrayList<>();
    List<String> pagesList=new ArrayList<>();
    List<String> juzList=new ArrayList<>();
    List<String> hizbList=new ArrayList<>();

    List<String> indexList=new ArrayList<>();

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
        indexAdapter=new IndexAdapter(this,indexList);
        recyclerView.setAdapter(indexAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        Intent intent=getIntent();


        initData(intent);
    }

    public void initData(Intent intent){
        type=intent.getStringExtra(MainActivity.INDEX_KEYS);
        headerTextView.setText(type);


        if(type.equals("Juz")){
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

        }
        else if(type.equals("Pages")){
            indexList.clear();
            for(int i=1;i<=604;i++)
                indexList.add("Page No. - "+i);

            indexAdapter.notifyDataSetChanged();

            networkTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }

        else if(type.equals("Chapters")){
            indexList.clear();

            indexList.addAll(NetworkRequest.getChaptersFromJSON());

            indexAdapter.notifyDataSetChanged();

            networkTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
    }
}