package com.example.holyquran.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holyquran.Activities.ResultActivity;
import com.example.holyquran.Quran;
import com.example.holyquran.R;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    List<Quran> list = new ArrayList<>();
    Context mContext;

    public ResultsAdapter(Context mContext, List<Quran> list) {
        this.mContext = mContext;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.result_list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(list.size()!=1)
        holder.textView.setText(50*(ResultActivity.CURRENT_PAGE-1)+(position+1)+")");
        else
            holder.textView.setVisibility(View.GONE);
        holder.verse.setText(list.get(position).getTranslation());
        holder.arabic.setText(list.get(position).getArabic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        TextView textView;
        TextView verse;
        TextView arabic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.verseNumber);
            verse=itemView.findViewById(R.id.verseTranslation);
            arabic=itemView.findViewById(R.id.arabic);
        }
    }
}
