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
        String value=intent.getStringExtra(MainActivity.INDEX_KEYS);
        headerTextView.setText(value);


        if(value.equals("Juz")){
            indexList.clear();

            juzList.add("Alīf-Lām-Mīm");
            juzList.add("Sayaqūlu");
            juzList.add("Tilka ’r-Rusulu");
            juzList.add("Lan tanaloo albirra");
            juzList.add("Wa’l-muḥṣanātu");
            juzList.add("Lā yuḥibbu-’llāhu");
            juzList.add("Wa ’Idha Samiʿū");
            juzList.add("Wa-law annanā");
            juzList.add("Qāla ’l-mala’u");
            juzList.add("Wa-’aʿlamū");
            juzList.add("Yaʿtazerūn");
            juzList.add("Wa mā min dābbatin");
            juzList.add("Wa mā ubarri’u");
            juzList.add("Alīf-Lām-Rā’/Rubamā");
            juzList.add("Subḥāna ’lladhī");
            juzList.add("Qāla ’alam");
            juzList.add("Iqtaraba li’n-nāsi");
            juzList.add("Qad ’aflaḥa");
            juzList.add("Wa-qāla ’lladhīna");
            juzList.add("’A’man Khalaqa");
            juzList.add("Otlu ma oohiya");
            juzList.add("Wa-man yaqnut");
            juzList.add("Wa-Mali");
            juzList.add("Fa-man ’aẓlamu");
            juzList.add("Ilayhi yuraddu");
            juzList.add("Ḥā’ Mīm");
            juzList.add("Qāla fa-mā khaṭbukum");
            juzList.add("Qad samiʿa ’llāhu");
            juzList.add("Tabāraka ’lladhī");
            juzList.add("‘Amma");

            indexList.addAll(juzList);
            indexAdapter.notifyDataSetChanged();

            networkTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

        }
    }
}